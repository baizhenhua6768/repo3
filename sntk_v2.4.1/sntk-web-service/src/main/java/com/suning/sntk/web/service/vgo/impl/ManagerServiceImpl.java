/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ManagerServiceImpl
 * Author:   18032490_赵亚奇
 * Date:     2018/8/20 19:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo.impl;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.rsf.vgo.ManagerRsfService;
import com.suning.sntk.web.service.vgo.ManagerService;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户经理模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/20
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRsfService managerRsfService = ServiceLocator
            .getService(ManagerRsfService.class, "ManagerRsfServiceImpl");

    @Override
    public RsfResponseDto<List<ManagerInfoDto>> queryManagerList(String custNo) {
        return managerRsfService.queryManagerList(custNo);
    }

    @Override
    public RsfResponseDto buildManagerRelation(String custNo, String staffId, String storeCode,String channel) {
        return managerRsfService.buildManagerRelation(custNo, staffId, storeCode,channel);
    }

    @Override
    public RsfResponseDto<ManagerInfoDto> queryManagerInfo(String custNo, String storeCode) {
        return managerRsfService.queryManagerInfo(custNo, storeCode);
    }

    @Override
    public RsfResponseDto<List<GuideInfoDto>> queryOldManager(String custNo, String staffId, String storeCode) {
        return managerRsfService.queryOldManager(custNo, staffId, storeCode);
    }

    @Override
    public RsfResponseDto<String> queryManagerInfoNew(String customerNo,String businessType) {

        return managerRsfService.queryManagerInfoNew(customerNo,businessType);
    }

}
