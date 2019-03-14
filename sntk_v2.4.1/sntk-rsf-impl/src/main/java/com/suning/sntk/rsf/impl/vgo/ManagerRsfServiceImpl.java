/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ManagerRsfServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/8/20 19:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.vgo;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.rsf.vgo.ManagerRsfService;
import com.suning.sntk.service.vgo.ManagerPublicService;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 客户经理模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/20
 */
@Implement(contract = ManagerRsfService.class, implCode = "ManagerRsfServiceImpl")
@Service
@Validated
public class ManagerRsfServiceImpl implements ManagerRsfService {

    @Autowired
    private ManagerPublicService managerPublicService;

    @Override
    public RsfResponseDto<List<ManagerInfoDto>> queryManagerList(String custNo) {
        return RsfResponseDto.of(managerPublicService.queryManagerList(custNo));
    }

    @Override
    public RsfResponseDto buildManagerRelation(String custNo, String staffId, String storeCode, String channel) {
        return managerPublicService.buildManagerRelation(custNo, staffId, storeCode, channel);
    }

    @Override
    public RsfResponseDto<ManagerInfoDto> queryManagerInfo(String custNo, String storeCode) {
        return RsfResponseDto.of(managerPublicService.queryManagerInfo(custNo, storeCode));
    }

    @Override
    public RsfResponseDto<List<GuideInfoDto>> queryOldManager(String custNo, String staffId, String storeCode) {
        return RsfResponseDto.of(managerPublicService.queryOldManager(custNo, staffId, storeCode));
    }

    @Override
    public RsfResponseDto<String> queryManagerInfoNew(String customerNo, String businessType) {
        return RsfResponseDto.of(managerPublicService.queryManagerInfoNew(customerNo, businessType));
    }
}
