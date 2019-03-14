/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DialogServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/8/21 9:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo.impl;

import com.alibaba.fastjson.JSON;
import com.suning.sntk.dao.DictDao;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.dao.vgo.StaffBeStoreDao;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.StaffBeStoreRespDto;
import com.suning.sntk.dto.vgo.TipsReqDto;
import com.suning.sntk.dto.vgo.VgoDialogueTipDto;
import com.suning.sntk.service.vgo.ManagerPublicService;
import com.suning.sntk.service.vgo.VgoDialogueService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.redis.vgo.VgoCacheKeyConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;

/**
 * 功能描述：云信会话实现类
 *
 * @author 18010645_黄成
 * @since 2018/8/21
 */
@Service
public class VgoDialogueServiceImpl implements VgoDialogueService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VgoDialogueServiceImpl.class);

    @Autowired
    private StaffBeStoreDao staffBeStoreDao;

    @Autowired
    private DictDao dictDao;

    @Autowired
    private ManagerPublicService managerPublicService;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private GuideAppointmentDao guideAppointmentDao;

    /**
     * 功能描述：店员所属门店
     *
     * @param storeCode 门店编码
     * @author 18010645_黄成
     * @since 2018/8/21
     */
    @Override
    public StaffBeStoreRespDto getStoreInfo(String storeCode) {
        Validators.ifInValid(StringUtils.isEmpty(storeCode)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        //缓存中获取
        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_STAFF_BE_STORE, storeCode);
        String val = redisCacheUtils.get(key);
        if (StringUtils.isNotEmpty(val)) {
            //json字符串转成java对象
            return JsonUtils.json2Object(val, StaffBeStoreRespDto.class);
        }
        //数据库中获取
        StaffBeStoreRespDto staffBeStoreRespDto = staffBeStoreDao.queryStaffBeStoreAddressInfo(storeCode);
        LOGGER.info("VgoDialogueServiceImpl.queryStaffBeStoreInfo,storeCode:{}", storeCode);
        if (null != staffBeStoreRespDto) {
            //缓存1天
            redisCacheUtils.setex(key, VgoCacheKeyConstants.ONE_DAY_TIME, JSON.toJSONString(staffBeStoreRespDto));
        }
        return staffBeStoreRespDto;
    }

    /**
     * 功能描述：查询云信提示语模板
     *
     * @param tipsReqDto 提示语模板请求
     * @author 18010645_黄成
     * @since 2018/8/21
     */
    @Override
    public String queryTipsInfo(TipsReqDto tipsReqDto) {
        LOGGER.info("云信获取在线、离线提示语模板入参：TipsReqDto={}", tipsReqDto);
        //参数校验
        Validators.ifInValid(StringUtils.isBlank(tipsReqDto.getType())).thenThrow(VgoErrorEnum.PARAM_ERROR);
        //type为1时必传(店员名称)
        if (VgoConstants.YUN_XIN_TIP_TYPE.equals(tipsReqDto.getType())) {
            Validators.ifInValid(StringUtils.isBlank(tipsReqDto.getStaffName())).thenThrow(VgoErrorEnum.PARAM_ERROR);
        }
        String messageResponse = null;
        //查库操作
        DictVo dictVo = dictDao.findDictByTypeAndCode(VgoConstants.YUN_XIN_TEMPLATE, tipsReqDto.getType());
        LOGGER.info("VgoDialogueServiceImpl.findDictByTypeAndCode,template:{},type:{}", VgoConstants.YUN_XIN_TEMPLATE, tipsReqDto.getType());
        if (null != dictVo) {
            //判断云信传入type类型：1:欢迎语 2：店员离线提示
            if (VgoConstants.WELCOME_TO_YOU.equals(tipsReqDto.getType())) {
                //门店短名称为空
                if (StringUtils.isBlank(tipsReqDto.getStoreName())) {
                    messageResponse = MessageFormat.format(dictVo.getDictValue(), StringUtils.EMPTY, tipsReqDto.getStaffName());
                } else {
                    messageResponse = MessageFormat.format(dictVo.getDictValue(), tipsReqDto.getStoreName(), tipsReqDto.getStaffName());
                }
            } else if (VgoConstants.SORRY_TO_YOU.equals(tipsReqDto.getType())) {
                messageResponse = dictVo.getDictValue();
            } else {
                //云信提示语模板传入类型不合法
                Validators.throwAnyway(VgoErrorEnum.TEMPLATE_TYPE_ERROR);
            }
        }
        LOGGER.info("提示语模板出参：messageResponse={}", messageResponse);
        return messageResponse;
    }

    /**
     * 功能：设置客户经理（新）
     *
     * @param customerNo 会员编码
     * @param staffId    导购工号
     * @author 18010645_黄成
     * @since 12:46 2018/10/11
     */
    @Override
    public VgoDialogueTipDto setCustomerManagerNew(String customerNo, String staffId) {
        ManagerInfoDto guideCustInfo = managerPublicService.queryManagerInfo(customerNo, staffId);
        LOGGER.info("setCustomerManager.queryManagerInfo,customerNo:{},staffId:{}", customerNo, staffId);
        VgoDialogueTipDto vgoDialogueTipDto = new VgoDialogueTipDto();
        if (null != guideCustInfo) {
            vgoDialogueTipDto.setFlag(VgoConstants.IS_CUSTOMER_MANAGER_YX);
            return vgoDialogueTipDto;
        }
        String storeCode = staffBeStoreDao.queryStoreCodeByStaffId(staffId);
        LOGGER.info("setCustomerManager.queryStoreCodeByStaffId,staffId:{}", staffId);
        if (StringUtils.isNotBlank(storeCode)) {
            RsfResponseDto rsfResponseDto = managerPublicService.buildManagerRelation(customerNo, staffId, storeCode, O2OConstants.YUN_XIN_LINE);
            if (rsfResponseDto.isSuccess()) {
                vgoDialogueTipDto.setFlag(VgoConstants.SET_CUSTOMER_MANAGER_SUCCESS_FLAG);
                DictVo yxDictVo = dictDao.findDictByTypeAndCode(VgoConstants.SET_MANAGER_TIPS, VgoConstants.SUCCESS_YX);
                if (null != yxDictVo) {
                    vgoDialogueTipDto.setTemplate(yxDictVo.getDictValue());
                }
                DictVo djDictVo = dictDao.findDictByTypeAndCode(VgoConstants.SET_MANAGER_TIPS, VgoConstants.SUCCESS_DJ);
                if (null != djDictVo) {
                    vgoDialogueTipDto.setSawpTemplate(djDictVo.getDictValue());
                }
            } else {
                vgoDialogueTipDto.setFlag(VgoConstants.SET_CUSTOMER_MANAGER_FAIL_FLAG);
                DictVo failDictVo = dictDao.findDictByTypeAndCode(VgoConstants.SET_MANAGER_TIPS, VgoConstants.FAIL_YX);
                if (null != failDictVo) {
                    vgoDialogueTipDto.setTemplate(failDictVo.getDictValue());
                }
            }
        }
        return vgoDialogueTipDto;
    }

    /**
     * 功能：会员有无客户经理（新）
     *
     * @param customerNo 会员编码
     * @param staffId    导购工号
     * @author 18010645_黄成
     * @since 12:47 2018/10/11
     */
    @Override
    public VgoDialogueTipDto isCustomerManagerNew(String customerNo, String staffId) {
        ManagerInfoDto guideCustInfo = managerPublicService.queryManagerInfo(customerNo, staffId);
        LOGGER.info("existsCustomerManager.queryManagerInfo,customerNo:{},storeCode:{}", customerNo, staffId);
        VgoDialogueTipDto vgoDialogueTipDto = new VgoDialogueTipDto();
        //判断数据库查询是否有客户经理，返回flag，0：有,1：没有
        if (null != guideCustInfo) {
            vgoDialogueTipDto.setFlag(VgoConstants.IS_CUSTOMER_MANAGER_YX);
        } else {
            vgoDialogueTipDto.setFlag(VgoConstants.NO_CUSTOMER_MANAGER_YX);
            DictVo dictVo = dictDao.findDictByTypeAndCode(VgoConstants.YX_DIALOGUE_TIPS, VgoConstants.NO_MANAGER_TEMPALTE);
            if (null != dictVo) {
                vgoDialogueTipDto.setTemplate(dictVo.getDictValue());
            }
        }
        return vgoDialogueTipDto;
    }

    /**
     * 功能：忙碌时预约到店提示语（新）
     *
     * @param customerNo 会员编码
     * @author 18010645_黄成
     * @since 15:19 2018/10/11
     */
    @Override
    public VgoDialogueTipDto queryBusyTemplate(String customerNo) {
        VgoDialogueTipDto vgoDialogueTipDto = new VgoDialogueTipDto();
        boolean flag = guideAppointmentDao.existNoCompleteBookingOrder(customerNo, DateUtils.formatPatten10(new Date()));
        LOGGER.info("queryBusyTemplate.existNoCompleteBookingOrder,customerNo:{},today:{}", customerNo, DateUtils.formatPatten10(new Date()));
        if (flag) {
            vgoDialogueTipDto.setFlag(VgoConstants.IS_BOOK_ORDER);
        } else {
            vgoDialogueTipDto.setFlag(VgoConstants.NO_BOOK_ORDER);
            DictVo dictVo = dictDao.findDictByTypeAndCode(VgoConstants.YX_DIALOGUE_TIPS, VgoConstants.SHOPPER_BUSY_TEMPLAT);
            if (null != dictVo) {
                vgoDialogueTipDto.setTemplate(dictVo.getDictValue());
            }
        }
        return vgoDialogueTipDto;
    }

}
