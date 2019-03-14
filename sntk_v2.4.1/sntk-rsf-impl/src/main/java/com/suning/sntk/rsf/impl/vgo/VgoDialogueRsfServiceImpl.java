/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DialogRsfServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/8/19 18:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.impl.vgo;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dao.vgo.StaffBeStoreDao;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.StaffBeStoreRespDto;
import com.suning.sntk.dto.vgo.TipsReqDto;
import com.suning.sntk.dto.vgo.VgoDialogueTipDto;
import com.suning.sntk.rsf.vgo.VgoDialogueRsfService;
import com.suning.sntk.service.vgo.ManagerPublicService;
import com.suning.sntk.service.vgo.VgoDialogueService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
@Implement(contract = VgoDialogueRsfService.class, implCode = "VgoDialogueRsfServiceImpl")
@Service
public class VgoDialogueRsfServiceImpl implements VgoDialogueRsfService {

    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(VgoDialogueRsfServiceImpl.class);

    @Autowired
    private ManagerPublicService managerPublicService;

    @Autowired
    private VgoDialogueService vgoDialogueService;

    @Autowired
    private StaffBeStoreDao staffBeStoreDao;

    /**
     * 功能：会员是否存在客户经理
     *
     * @param custNo  会员编码
     * @param staffId 店员工号
     * @author 18010645_黄成
     * @since 10:18 2018/8/24
     */
    @Override
    public RsfResponseDto<String> existsCustomerManager(String custNo, String staffId) {
        Validators.ifInValid(StringUtils.isEmpty(custNo) || StringUtils.isEmpty(staffId)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        String messageResponse;
        ManagerInfoDto guideCustInfo = managerPublicService.queryManagerInfo(custNo, staffId);
        LOGGER.info("queryManagerInfo,custNo:{},storeCode:{}", custNo, staffId);
        //判断数据库查询是否有客户经理，返回flag，0：有,1：没有
        if (null != guideCustInfo) {
            messageResponse = VgoConstants.EXSIT_CUSTOMER_MANAGER;
        } else {
            messageResponse = VgoConstants.NO_CUSTOMER_MANAGER;
        }
        return RsfResponseDto.of(messageResponse);
    }


    /**
     * 功能：会员设置客户经理
     *
     * @param customerNo 会员编码
     * @param staffId    店员工号
     * @author 18010645_黄成
     * @since 10:15 2018/8/24
     */
    @Override
    public RsfResponseDto<String> bindCustomerManager(String customerNo, String staffId) {
        Validators.ifInValid(StringUtils.isEmpty(customerNo) || StringUtils.isEmpty(staffId)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        String storeCode = staffBeStoreDao.queryStoreCodeByStaffId(staffId);
        Validators.ifInValid(StringUtils.isEmpty(storeCode)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        ManagerInfoDto guideCustInfo = managerPublicService.queryManagerInfo(customerNo, staffId);
        if (null != guideCustInfo) {
            return RsfResponseDto.of(VgoConstants.IS_EXIST_CUSTOMER_MANAGER);
        }
        RsfResponseDto rsfResponseDto = managerPublicService.buildManagerRelation(customerNo, staffId, storeCode, O2OConstants.YUN_XIN_LINE);
        if (rsfResponseDto.isSuccess()) {
            return RsfResponseDto.of(VgoConstants.SET_CUSTOMER_MANAGER_SUCCESS);
        } else {
            return RsfResponseDto.error(rsfResponseDto.getErrorCode(), rsfResponseDto.getErrorMsg());
        }
    }


    /**
     * 功能：会员是否存在客户经理（新）
     *
     * @param customerNo 会员编码
     * @param staffId    店员工号
     * @author 18010645_黄成
     * @since 10:18 2018/8/24
     */
    @Override
    public RsfResponseDto<VgoDialogueTipDto> existsCustomerManagerNew(String customerNo, String staffId) {
        Validators.ifInValid(StringUtils.isEmpty(customerNo) || StringUtils.isEmpty(staffId)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        return RsfResponseDto.of(vgoDialogueService.isCustomerManagerNew(customerNo, staffId));
    }

    /**
     * 功能：会员设置客户经理（新）
     *
     * @param customerNo 会员编码
     * @param staffId    店员工号
     * @author 18010645_黄成
     * @since 10:15 2018/8/24
     */
    @Override
    public RsfResponseDto<VgoDialogueTipDto> setCustomerManagerNew(String customerNo, String staffId) {
        Validators.ifInValid(StringUtils.isEmpty(customerNo) || StringUtils.isEmpty(staffId)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        return RsfResponseDto.of(vgoDialogueService.setCustomerManagerNew(customerNo, staffId));
    }

    @Override
    public RsfResponseDto<VgoDialogueTipDto> queryBusyTemplate(String customerNo) {
        Validators.ifInValid(StringUtils.isEmpty(customerNo)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        return RsfResponseDto.of(vgoDialogueService.queryBusyTemplate(customerNo));
    }

    @Override
    public RsfResponseDto<StaffBeStoreRespDto> queryStoreAddr(String storeCode) {
        return RsfResponseDto.of(vgoDialogueService.getStoreInfo(storeCode));
    }

    @Override
    public RsfResponseDto<String> queryDialogueTemplate(TipsReqDto tipsReqDto) {
        return RsfResponseDto.of(vgoDialogueService.queryTipsInfo(tipsReqDto));
    }


}
