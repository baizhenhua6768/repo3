/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoCommonServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/9/4 10:05
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo.impl;

import com.suning.sntk.consumer.HrConsumerService;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dto.vgo.BookNoDto;
import com.suning.sntk.dto.vgo.OrderNoDto;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.support.common.GuideInfoConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;
import com.suning.sntk.util.VgoAdminUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：vgo公共方法实现类
 *
 * @author 18010645_黄成
 * @since 2018/9/4
 */
@Service
public class VgoCommonServiceImpl implements VgoCommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoCommonServiceImpl.class);

    @Autowired
    private GuideInfoDao guideInfoDao;

    @Autowired
    private HrConsumerService hrConsumerService;

    /**
     * 性别：男
     */
    private static final String SEX_MALE = "1";


    /**
     * 品类、项目分隔符
     */
    private static final String CATE_NAME_SPLIT_FALG = "、";

    /**
     * 功能：接单数（服务数）和获赞数
     *
     * @param guideId 导购工号
     * @author 18010645_黄成
     * @since 10:25 2018/9/4
     */
    @Override
    public Map<String, String> queryOrderNumAndReceivePraise(String guideId) {
        Map<String, String> mapResult = new HashMap<String, String>();
        //查询预约数
        Long bookingCount = guideInfoDao.queryGuideBookingInfo(guideId);
        LOGGER.info("queryOrderNumAndReceivePraise.queryGuideBookingInfo,guideId:{}", guideId);
        //接单数：库中的orderNum + 预约数 * 3
        String orderNum = StringUtils.EMPTY;
        Long number = guideInfoDao.queryOrderNum(guideId);
        if (null != number && number >= 0) {
            orderNum = String.valueOf(number + bookingCount * VgoConstants.ORDER_NUM_MULTIPLE);
        }
        //获赞数：基数1500 + 预约数 * 2
        String receivePraise = String.valueOf(GuideInfoConstants
                .INITIAL_RECEIVE_PRAISE + bookingCount * VgoConstants.RECEIVE_PRAISE_NUM_MULTIPLE);
        //保存接单数信息
        mapResult.put(VgoConstants.ORDER_NUM_MAP_KEY, orderNum);
        //保存获赞数
        mapResult.put(VgoConstants.RECEIVE_PRAISE_MAP_KEY, receivePraise);
        return mapResult;
    }

    @Override
    public String queryInfantGuideServiceItem(String guideId) {
        List<String> infantGuideServiceItems = guideInfoDao.queryInfantGuideServiceItems(guideId);
        infantGuideServiceItems = VgoAdminUtils.cutOffPicUrl(infantGuideServiceItems);
        String guideServiceItem = StringUtils.EMPTY;
        if (CollectionUtils.isNotEmpty(infantGuideServiceItems)) {
            StringBuilder builder = new StringBuilder();
            for (String serviceItem : infantGuideServiceItems) {
                builder.append(serviceItem).append(CATE_NAME_SPLIT_FALG);

            }
            guideServiceItem = builder.toString().substring(0, builder.length() - 1);
        }

        return guideServiceItem;
    }

    @Override
    public String queryGuideDefaultPhoto(String guideId) {
        String sex = hrConsumerService.queryPerson(guideId, "gendercode");
        String guidePhoto = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(sex)) {
            if (SEX_MALE.equals(sex)) {
                guidePhoto = ScmPropertiesUtil
                        .getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, GuideInfoConstants.MALE_PHOTO_URL);
            } else {
                guidePhoto = ScmPropertiesUtil
                        .getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, GuideInfoConstants.FEMALE_PHOTO_URL);
            }
        }
        return guidePhoto;
    }

    /**
     * 功能：批量获取点赞数和接单数
     *
     * @param guideIds 导购工号集合
     * @author 18010645_黄成
     * @since 10:07 2018/10/8
     */
    @Override
    public Map<String, Map<String, String>> queryBatchOrderNumAndReceivePraise(List<String> guideIds) {
        Map<String, Map<String, String>> mapList = new HashMap<>();
        Map<String, String> orderNumList = new HashMap<>();
        Map<String, String> receivePraiseList = new HashMap<>();
        //批量查询预约数
        List<BookNoDto> bookNoList = guideInfoDao.queryBatchGuideBookingCount(guideIds);
        LOGGER.info("queryBatchOrderNumAndReceivePraise.queryBatchGuideBookingCount,guideIds:{},bookNoList:{}", guideIds, bookNoList);
        //批量查询接单数：库中的orderNum + 预约数 * 3
        List<OrderNoDto> orderNoList = guideInfoDao.queryBatchOrderNumber(guideIds);
        //导购工号相等计算出接单数
        for (BookNoDto bookList : bookNoList) {
            for (OrderNoDto orderList : orderNoList) {
                if (StringUtils.equals(bookList.getGuideId(), orderList.getGuideId())) {
                    //计算接单数
                    String orderNum = String.valueOf(orderList.getOrderNum() + bookList.getNum() * VgoConstants.ORDER_NUM_MULTIPLE);
                    orderNumList.put(bookList.getGuideId(), orderNum);
                    break;
                }
            }
            //获赞数：基数1500 + 预约数 * 2
            String receivePraise = String.valueOf(GuideInfoConstants.INITIAL_RECEIVE_PRAISE + bookList.getNum() * VgoConstants.RECEIVE_PRAISE_NUM_MULTIPLE);
            receivePraiseList.put(bookList.getGuideId(), receivePraise);
        }
        mapList.put("orderNum", orderNumList);
        mapList.put("receivePraise", receivePraiseList);
        return mapList;
    }
}
