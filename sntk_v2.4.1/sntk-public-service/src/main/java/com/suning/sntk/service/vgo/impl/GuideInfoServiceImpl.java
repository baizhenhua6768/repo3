/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoServiceImpl
 * Author:   18041004_余长杰
 * Date:     2018/8/16 15:16
 * Description: //易购app导购查询
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * 18041004_余长杰        2018/8/16          2.1.1
 */

package com.suning.sntk.service.vgo.impl;

import com.alibaba.fastjson.JSON;
import com.suning.mzss.rsf.video.dto.VFrontContentDto;
import com.suning.nsfbus.logistics.dto.RegionInfoDto;
import com.suning.nsfbus.logistics.dto.RegionInfoListDto;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.consumer.MzssConsumerService;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.consumer.SuisConsumerService;
import com.suning.sntk.dao.o2o.GuideDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dto.vgo.*;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.service.vgo.GuideInfoService;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.support.common.GuideInfoConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.redis.vgo.VgoCacheKeyConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.enums.vgo.GuideInfoStatusEnum;
import com.suning.sntk.support.util.RandomNumberUtil;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;
import com.suning.store.commons.lang.advice.Trace;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

/**
 * 功能描述：易购app导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
@Service
@Trace
public class GuideInfoServiceImpl implements GuideInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideInfoServiceImpl.class);

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private GuideInfoDao guideInfoDao;

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Autowired
    private GuideDao guideDao;

    @Autowired
    private VgoCommonService vgoCommonService;

    @Autowired
    private BaoziConsumerService baoziConsumerService;

    @Autowired
    private SuisConsumerService suisConsumerService;

    @Autowired
    private MzssConsumerService mzssConsumerService;

    @Value("${tipUrl}")
    private String tipUrl;

    /**
     * 卡片页最大展示导购信息的索引号(每页显示10个导购信息)
     */
    private static final int MAX_GUIDE_INDEX = 10;

    /**
     * 最大取值数量
     */
    private static final int MAX_NUM_INDEX = 6;

    /**
     * 去除首个后的取值数量
     */
    private static final int MINOR_NUM_INDEX = 5;

    /**
     * 根据会员编号查询客户经理
     *
     * @param custNo       会员编号
     * @param businessType 业态
     * @return StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 11:57 2018/9/4
     */
    private StoreGuideInfoDto queryCustManagerByCustNo(String custNo, String businessType) {
        //redis获取导购信息返回(初始化塞入redis)
        String custNoKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CUST_MANAGER, custNo);
        StoreGuideInfoDto guideInfo = JsonUtils.json2Object(redisCacheUtils.hget(custNoKey, businessType), StoreGuideInfoDto.class);
        if (null != guideInfo) {
            guideInfo.setIsCustManager(GuideInfoStatusEnum.IS_CUST_MANAGER.getStatus());
        }
        return guideInfo;
    }

    /**
     * 查询省、市信息
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @return RegionInfoListDto
     * @author 18041004_余长杰
     * @since 16:42 2018/8/30
     */
    @Override
    public GuideRegionInfoListDto queryRegionListByParentCode(String parentRegionCode, String regionalLevel) {
        GuideRegionInfoListDto guideRegionInfoList = new GuideRegionInfoListDto();
        List<GuideRegionInfoDto> list = new ArrayList<>();
        //调nsfbus接口查询省市信息
        RegionInfoListDto nsfbusRegionInfo = nsfbusConsumerService.queryRegionListByParentCode(parentRegionCode, regionalLevel);
        for (RegionInfoDto regionInfoDto : nsfbusRegionInfo.getRegionInfoDtoList()) {
            GuideRegionInfoDto guideRegionInfo = new GuideRegionInfoDto();
            guideRegionInfo.setRegionalCode(regionInfoDto.getRegionalCode());
            guideRegionInfo.setRegionalName(regionInfoDto.getRegionalName());
            guideRegionInfo.setRegionalLevel(regionInfoDto.getRegionalLevel());
            guideRegionInfo.setParentRegionalCode(regionInfoDto.getParentRegionalCode());
            guideRegionInfo.setExtension(regionInfoDto.getExtension());
            list.add(guideRegionInfo);
        }
        guideRegionInfoList.setRegionInfoDtoList(list);
        return guideRegionInfoList;
    }

    /**
     * @param regionCode    区域编码
     * @param regionalLevel 层级编码
     * @return GuideRegionInfoDto
     * @author 18041004_余长杰
     * @since 20:03 2018/9/11
     */
    @Override
    public GuideRegionInfoDto queryRegionInfoByRegionalCode(String regionCode, String regionalLevel) {
        GuideRegionInfoDto guideRegionInfo = new GuideRegionInfoDto();
        //调nsfbus接口查询当前省市信息
        RegionInfoDto regionInfoDto = nsfbusConsumerService.queryRegionInfoByRegionalCode(regionCode, regionalLevel);
        guideRegionInfo.setRegionalCode(regionInfoDto.getRegionalCode());
        guideRegionInfo.setRegionalName(regionInfoDto.getRegionalName());
        guideRegionInfo.setRegionalLevel(regionInfoDto.getRegionalLevel());
        guideRegionInfo.setParentRegionalCode(regionInfoDto.getParentRegionalCode());
        guideRegionInfo.setExtension(regionInfoDto.getExtension());

        return guideRegionInfo;
    }

    /**
     * 微信小程序查询会员客户经理
     *
     * @param storeCode 门店编码
     * @param custNo    会员编号
     * @author 88397670_张辉
     * @since 14:57 2018-9-8
     */
    @Override
    public StoreGuideInfoDto queryAppletCustManager(String storeCode, String custNo) {
        LOGGER.info("Enter GuideInfoServiceImpl.queryAppletCustManager! storeCode:{}" + storeCode + "custNo:{}" + custNo);
        StoreGuideInfoDto guideInfo = null;
        String businessType = guideInfoDao.queryBusinessType(storeCode);
        if (StringUtils.isBlank(businessType)) {
            return guideInfo;
        }
        guideInfo = queryCustManagerByCustNo(custNo, businessType);
        LOGGER.info("GuideInfoServiceImpl.queryCustManagerByCustNo result:{}", guideInfo);
        //会员有专属客户经理
        if (guideInfo != null && StringUtils.isNotBlank(guideInfo.getGuideId())) {
            // 导购提示和内容
            guideInfo.setTipTitle(
                    ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, GuideInfoConstants.FOURTH_PAGE_TIP_TITLE));
            guideInfo.setTipContent(
                    ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, GuideInfoConstants.TIP_CONTENT));
            // 通过会员编号取缓存中的客户经理信息不为空，则表明该导购确为当前用户的客户经理
            guideInfo.setIsCustManager(GuideInfoStatusEnum.IS_CUST_MANAGER.getStatus());
            return guideInfo;
        } else {
            //会员无专属客户经理
            if (StringUtils.isNotBlank(custNo)) {
                //取会员编码后四位缓存key值
                custNo = custNo.substring(custNo.length() - VgoCacheKeyConstants.SUB_COUNT_OF_CUSTNO);
            }
            String custKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CUST_MANAGER, custNo);
            guideInfo = JsonUtils.json2Object(redisCacheUtils.hget(custKey, businessType), StoreGuideInfoDto.class);
            //该会员为首次匹配导购
            if (null == guideInfo) {
                String cateCode;
                // 若当前门店业态为电器，则取redis缓存中的随机品类
                if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                    String categoryCodeConfig = ScmPropertiesUtil
                            .getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, GuideInfoConstants.CATEGORY_CODE);
                    String[] categoryCodes = categoryCodeConfig.split(GuideInfoConstants.SPLIT_LABEL);
                    cateCode = RandomNumberUtil.getRandomElement(categoryCodes);
                } else {
                    // 否则为母婴业态则取全部
                    cateCode = GuideInfoConstants.SERVER_NAME;
                }
                LOGGER.info("Faild to match only custManager!start to randomMatchGuide! "
                        + "storeCode:{}" + storeCode + "cateCode:{}" + cateCode + "businessType:{}" + businessType);
                guideInfo = randomMatchGuide(storeCode, cateCode, businessType);
                if (null != guideInfo) {
                    //设置匹配出的导购为该会员当天客户经理（防止小程序每次进首页匹配导购都不相同）
                    redisCacheUtils.hset(custKey, businessType, JsonUtils.object2Json(guideInfo));
                    redisCacheUtils.expire(custKey, VgoCacheKeyConstants.ONE_DAY_TIME);
                }
            }
            // 通过redis匹配的导购，则不为当前用户的客户经理
            if (guideInfo != null)
                guideInfo.setIsCustManager(GuideInfoStatusEnum.NOT_CUST_MANAGER.getStatus());
        }

        return guideInfo;
    }

    /**
     * 微信小程序店员卡片页
     *
     * @param storeCode  门店编码
     * @param custNo     会员编号呢
     * @param preGuideId 上级页匹配导购工号
     * @author 88397670_张辉
     * @since 10:25 2018-9-10
     */
    @Override
    public List<StoreGuideInfoDto> queryGuideListForSmallRoutine(String storeCode, String custNo, String preGuideId) {
        LOGGER.info("Enter GuideInfoServiceImpl.queryGuideListForSmallRoutine param: "
                + "storeCode:{}" + storeCode + "custNo:{}" + custNo + "preGuideId:{}" + preGuideId);
        List<StoreGuideInfoDto> storeGuideInfoList = new ArrayList<>();
        LOGGER.info("调用麦奇baozi服务查询导购列表，storeCode：{}，custNo：{}", storeCode, custNo);
        List<String> guideIds = baoziConsumerService.queryTopGuiders(storeCode, custNo);
        LOGGER.info("麦奇系统匹配的导购列表，result:{}", guideIds);
        String businessType = guideInfoDao.queryBusinessType(storeCode);
        // 若麦琦匹配失败，则从缓存中取一个导购返回给小程序
        if (CollectionUtils.isEmpty(guideIds)) {
            LOGGER.info("baoziConsumerService.queryTopGuiders return null!");
            String cateCode;
            // 若当前门店业态为电器，则取redis缓存中的随机品类
            if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                String categoryCodeConfig = ScmPropertiesUtil
                        .getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, GuideInfoConstants.CATEGORY_CODE);
                String[] categoryCodes = categoryCodeConfig.split(GuideInfoConstants.SPLIT_LABEL);
                cateCode = RandomNumberUtil.getRandomElement(categoryCodes);
            } else {
                // 否则为母婴业态则取全部
                cateCode = GuideInfoConstants.SERVER_NAME;
            }
            LOGGER.info("最终取得品类，cateCode:{}", cateCode);
            // 若上级匹配导购Id为空，则随机匹配一个店员信息
            if (StringUtils.isBlank(preGuideId)) {
                StoreGuideInfoDto guideInfo = randomMatchGuide(storeCode, cateCode, businessType);
                setGuideListLablUrl(businessType, guideInfo);
                storeGuideInfoList.add(guideInfo);
            } else {
                // 不为空则从缓存中直接取上级匹配导购信息
                StoreGuideInfoDto guideInfo = JsonUtils.json2Object(redisCacheUtils.get(
                        MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, preGuideId)), StoreGuideInfoDto.class);
                setGuideListLablUrl(businessType, guideInfo);
                storeGuideInfoList.add(guideInfo);
            }
        } else {
            // 麦奇匹配导购列表成功，则根据导购工号取出导购信息
            storeGuideInfoList = getAppletGuideInfo(guideIds, preGuideId, businessType);
        }
        return storeGuideInfoList;
    }

    /**
     * 根据导购工号列表取redis缓存中的导购信息
     *
     * @param guideIds     导购工号列表
     * @param preGuideId   上级页匹配的导购工号
     * @param businessType 业态 1-电器 2-母婴
     * @author 88397670_张辉
     * @since 15:23 2018-9-10
     */
    private List<StoreGuideInfoDto> getAppletGuideInfo(List<String> guideIds, String preGuideId, String businessType) {
        LOGGER.info("进入取缓存中导购信息方法，入参：{}", guideIds, preGuideId, businessType);
        List<StoreGuideInfoDto> list = new ArrayList<>();
        // 将上级页面匹配的导购放在导购列表的首位
        if (StringUtils.isNotBlank(preGuideId)) {
            // 若麦奇匹配的导购工号列表存在上级页匹配的导购，则将匹配的导购列表去除上级页匹配导购，并在返回的导购信息列表的首位增加上级页匹配的导购信息
            if (guideIds.contains(preGuideId)) {
                guideIds.remove(preGuideId);
            }
            StoreGuideInfoDto guideInfo = JsonUtils.json2Object(redisCacheUtils.get(
                    MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, preGuideId)), StoreGuideInfoDto.class);
            setGuideListLablUrl(businessType, guideInfo);
            list.add(guideInfo);
        }
        // 再将麦奇匹配到的所有金牌导购信息放入到导购列表中
        if (CollectionUtils.isNotEmpty(guideIds)) {
            int guideIndex = guideIds.size() > MAX_GUIDE_INDEX ? MAX_GUIDE_INDEX : guideIds.size();
            List<String> guideList = guideIds.subList(0, guideIndex);
            for (String guideId : guideList) {
                String guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, guideId);
                StoreGuideInfoDto guideInfo = JsonUtils.json2Object(redisCacheUtils.get(guideKey), StoreGuideInfoDto.class);
                setGuideListLablUrl(businessType, guideInfo);
                list.add(guideInfo);
            }
        }
        LOGGER.info("缓存取得导购信息列表：{}", list);
        return list;
    }

    /**
     * 功能：查询店员主页（导购详情，vgo视频）
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @param fromType    渠道号
     * @param pageNo      页码
     * @param pageSize    每页条数
     * @author 18010645_黄成
     * @since 16:12  2018/9/13
     */
    public StaffPageDto queryGuideInfoAndVgoVideo(String customerNum, String guideId, Integer fromType, Integer pageNo, Integer pageSize) {
        LOGGER.info("进入查询导购详情、v购视频、会员预约信息方法,customerNum:{},guideId:{},fromType:{},pageNo:{},pageSize:{}"
                , customerNum, guideId, fromType, pageNo, pageSize);
        //获取导购详情
        StaffPageDto staffPageResponse = new StaffPageDto();
        GuideDetailDto guideDetailDto = getGuideInfo(customerNum, guideId);
        staffPageResponse.setGuideDetailDto(guideDetailDto);
        //已登陆取会员最近一次预约该导购信息(不包含过期)
        if (StringUtils.isNotEmpty(customerNum)) {
            CustomerBookDto customerBookDto = guideInfoDao
                    .queryNearBookingInfo(customerNum, guideId, DateUtils.format(new Date(), DateUtils.PATTEN_10));
            LOGGER.info("查询最近一次预约信息（不含过期），customerNum：{}，guideId：{}，bookingTime：{}，", customerNum, guideId,
                    DateUtils.format(new Date(), DateUtils.PATTEN_10));
            if (null != customerBookDto && StringUtils.isNotEmpty(customerBookDto.getBookingTime())) {
                //预约时间有效且未销单
                customerBookDto = customerBookDto.getBookingTime().compareTo(DateUtils.format(new Date(), DateUtils.PATTEN_10))
                        >= 0 ? customerBookDto : null;
                staffPageResponse.setCustomerBookDto(customerBookDto);
            }
        } else {
            //未登陆(无任何预约信息)
            LOGGER.info("未登陆无任何预约信息");
            staffPageResponse.setCustomerBookDto(null);
        }
        //视频编码
        String videoCustomerNo = suisConsumerService.queryVedioCustomerNum(guideId);
        LOGGER.info("调用苏宁联盟suis服务查询视频会员编码，guideId：{}", guideId);
        List<VgoVideoDto> vgoVideoDtoList = new ArrayList<>();
        if (StringUtils.isNotEmpty(videoCustomerNo)) {
            //查询v购视频列表信息
            List<VFrontContentDto> vFrontContentDto = mzssConsumerService.queryVgoVideoList(videoCustomerNo, fromType, pageNo, pageSize);
            LOGGER.info("调用社交相关系统mzss服务查询视频列表，videoCustomerNo:{},fromType:{},pageNo:{},pageSize:{}", videoCustomerNo, fromType, pageNo,
                    pageSize);
            if (CollectionUtils.isNotEmpty(vFrontContentDto)) {
                for (VFrontContentDto vgoVideoList : vFrontContentDto) {
                    VgoVideoDto vgoVideoDto = new VgoVideoDto();
                    BeanUtils.copyProperties(vgoVideoList, vgoVideoDto);
                    vgoVideoDtoList.add(vgoVideoDto);
                }
            }
        }
        staffPageResponse.setVgoVideoDtoList(vgoVideoDtoList);
        return staffPageResponse;
    }

    /**
     * 功能：获取店员主页导购详情
     *
     * @param guideId 员工工号
     * @author 18010645_黄成
     * @since 15:40 2018/9/7
     */
    private GuideDetailDto getGuideInfo(String customerNum, String guideId) {
        //获取导购详情（店员主页）
        GuideDetailDto guideDetailDtoResponse = getGuideDetail(guideId);
        if (guideDetailDtoResponse != null) {
            //获取接单数（服务数）和获赞数
            Map<String, String> mapResult = vgoCommonService.queryOrderNumAndReceivePraise(guideId);
            LOGGER.info("获取接单数（服务数）和获赞数，guideId：{}", guideId);
            //接单数（服务数）
            guideDetailDtoResponse.setOrderNum(mapResult.get(VgoConstants.ORDER_NUM_MAP_KEY));
            //获赞数
            guideDetailDtoResponse.setReceivePraise(mapResult.get(VgoConstants.RECEIVE_PRAISE_MAP_KEY));
            //服务器时间(yyyy-MM-dd HH:mm:ss)
            guideDetailDtoResponse.setCurrentTime(DateUtils.format(new Date()));
            //设置母婴导购服务项目
            if (BusinessTypesEnum.MOM_INFANT.getCode().equals(guideDetailDtoResponse.getBusinessType())) {
                guideDetailDtoResponse.setCategoryName(vgoCommonService.queryInfantGuideServiceItem(guideDetailDtoResponse.getGuideId()));
            }
            //有会员编码则查询客户经理
            if (StringUtils.isNotEmpty(customerNum)) {
                StaffCustRel staffCustRel = guideInfoDao.queryIsCustomerManager(customerNum, guideId);
                LOGGER.info("查询是否有客户经理，customerNum：{}，guideId：{}", customerNum, guideId);
                if (null != staffCustRel) {
                    //客户经理标识:1是
                    guideDetailDtoResponse.setCustomerManagerFlag(VgoConstants.IS_CUSTOMER_MANAGER);
                } else {
                    //客户经理标识:0否
                    guideDetailDtoResponse.setCustomerManagerFlag(VgoConstants.NOT_CUSTOMER_MANAGER);
                }
            } else {
                //未登陆无客户经理
                LOGGER.info("未登陆无客户经理");
                guideDetailDtoResponse.setCustomerManagerFlag(VgoConstants.NOT_CUSTOMER_MANAGER);
            }
        }
        return guideDetailDtoResponse;
    }

    /**
     * 功能：获取导购详情（店员主页）
     *
     * @param guideId 导购工号
     * @author 18010645_黄成
     * @since 17:42 2018/9/1
     */
    @Override
    public GuideDetailDto getGuideDetail(String guideId) {
        //redis获取导购详情（店员主页）
        String value = redisCacheUtils
                .get(MessageFormat.format(VgoCacheKeyConstants.KEY_STAFF_PAGE_GUIDE_DETAIL, guideId));
        if (StringUtils.isNotBlank(value)) {
            return JsonUtils.json2Object(value, GuideDetailDto.class);
        }
        //导购详情（店员主页）
        GuideDetailDto guideDetailDtoResponse = guideInfoDao.queryStaffPageGuideDetail(guideId);
        LOGGER.info("查询导购详情，guideId：{}", guideId);
        //设置缓存
        if (null != guideDetailDtoResponse)
            redisCacheUtils.setex(MessageFormat.format(VgoCacheKeyConstants.KEY_STAFF_PAGE_GUIDE_DETAIL, guideId)
                    , VgoCacheKeyConstants.TWO_HOUR, JSON.toJSONString(guideDetailDtoResponse));
        return guideDetailDtoResponse;
    }

    /**
     * 功能：pc端预约页:获取门店V购人员及门店基本信息
     *
     * @param storeCode 门店编码
     * @author 18010645_黄成
     * @since 10:32 2018/8/31
     */
    @Override
    public StoreServantRespDto queryStoreGuides(String storeCode, String guideId) {
        StoreServantRespDto storeServantRespDto = redisCacheUtils.getBin(MessageFormat.format(VgoCacheKeyConstants.UNBG_STORE_VGO_SERVANTS
                , storeCode, guideId), StoreServantRespDto.class);
        if (null == storeServantRespDto) {
            // 导购人员列表
            List<ServantInfoPcDto> servantList = guideDao.getStoreServants(storeCode);
            LOGGER.info("queryStoreGuides.getStoreVServants,storeCode：{}", storeCode);
            List<ServantInfoPcDto> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(servantList)) {
                ServantInfoPcDto fourGuideInfo = null;
                // 遍历找到这个导购将其移除
                for (ServantInfoPcDto servant : servantList) {
                    if (null != servant && StringUtils.equals(servant.getGuideId(), guideId)) {
                        fourGuideInfo = servant;
                        break;
                    }
                }
                if (fourGuideInfo != null) {
                    //该导购放入集合列表首位
                    list.add(fourGuideInfo);
                    servantList.remove(fourGuideInfo);
                }
                // 随机取5个导购
                if (servantList.size() >= MAX_NUM_INDEX) {
                    int startIndex = RandomNumberUtil.getRandomNum(servantList.size() - MINOR_NUM_INDEX);
                    list.addAll(servantList.subList(startIndex, startIndex + MINOR_NUM_INDEX));
                } else {
                    list.addAll(servantList);
                }
            }
            // 门店信息
            StoreInfoPcDto storeInfoPcDto = guideDao.getPcStoreInfo(storeCode);
            LOGGER.info("queryStoreGuides.getPcStoreInfo，storeCode：{}", storeCode);
            storeServantRespDto = new StoreServantRespDto();
            storeServantRespDto.setuServantInfoList(list);
            storeServantRespDto.setStoreInfo(storeInfoPcDto);
            // 放入缓存
            redisCacheUtils.setexBin(MessageFormat.format(VgoCacheKeyConstants.UNBG_STORE_VGO_SERVANTS
                    , storeCode, guideId), storeServantRespDto, VgoCacheKeyConstants.ONE_HOUR);
        }
        //服务器初始时间
        storeServantRespDto.setInitTime(String.valueOf(System.currentTimeMillis()));
        // 设置接单数
        transfOrderList(storeServantRespDto.getuServantInfoList());
        return storeServantRespDto;
    }

    /**
     * 功能：门店下所有导购统一设置接单数
     *
     * @param servantsList 服务人员（导购）
     * @author 18010645_黄成
     * @since 10:34 2018/10/8
     */
    private void transfOrderList(List<ServantInfoPcDto> servantsList) {
        if (CollectionUtils.isNotEmpty(servantsList)) {
            List<String> guideIds = new ArrayList<>();
            //组装导购工号
            for (ServantInfoPcDto list : servantsList) {
                if (null != list) {
                    guideIds.add(list.getGuideId());
                }
            }
            //批量获取接单数、获赞数
            Map<String, Map<String, String>> mapList = vgoCommonService.queryBatchOrderNumAndReceivePraise(guideIds);
            Map<String, String> orderNumList = mapList.get("orderNum");
            //相同导购设置接单数
            for (ServantInfoPcDto servants : servantsList) {
                for (Map.Entry<String, String> entry : orderNumList.entrySet()) {
                    if (StringUtils.equals(servants.getGuideId(), entry.getKey())) {
                        //设置接单数
                        servants.setOrderNum(orderNumList.get(entry.getKey()));
                    }
                }
            }
        }
    }

    /**
     * 匹配缓存中的导购
     *
     * @param storeCode    门店编码
     * @param cateCode     品类编码
     * @param businessType 业态 1-电器 2-母婴
     * @author 88397670_张辉
     * @since 16:32 2018-9-8
     */

    private StoreGuideInfoDto randomMatchGuide(String storeCode, String cateCode, String businessType) {
        //redis中获取导购信息
        String guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode, cateCode,
                businessType);
        StoreGuideInfoDto guideInfo = JsonUtils.json2Object(redisCacheUtils.get(guideKey), StoreGuideInfoDto.class);
        LOGGER.info("randomMatchGuide result guideInfo:{}", guideInfo);
        setGuideLablUrl(businessType, guideInfo);
        return guideInfo;
    }

    /**
     * 设置导购图标url
     *
     * @param businessType 业态 1-电器 2-母婴
     * @param guideInfo    导购信息
     * @author 88397670_张辉
     * @since 17:26 2018-9-13
     */
    private void setGuideLablUrl(String businessType, StoreGuideInfoDto guideInfo) {
        if (null != guideInfo) {
            if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG,
                        GuideInfoConstants.VGO_GUIDE_LABEL_URL));
            } else {
                guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG,
                        GuideInfoConstants.INFANT_GUIDE_LABEL_URL));
            }
        }
    }

    /**
     * 设置卡片页导购图标url
     *
     * @param businessType 业态 1-电器 2-母婴
     * @param guideInfo    导购信息
     * @author 88397670_张辉
     * @since 17:26 2018-9-13
     */
    private void setGuideListLablUrl(String businessType, StoreGuideInfoDto guideInfo) {
        if (null != guideInfo) {
            if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG,
                        GuideInfoConstants.VGO_GUIDE_LIST_LABEL_URL));
            } else {
                guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG,
                        GuideInfoConstants.INFANT_GUIDE_LIST_LABEL_URL));
            }
        }
    }
}
