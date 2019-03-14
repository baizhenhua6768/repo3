/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreDetailServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-8-31 14:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.admin.dao.vgo.O2oStoreDetailDao;
import com.suning.sntk.admin.service.vgo.StoreDetailService;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.dto.region.OrgInfo;
import com.suning.sntk.dto.vgo.OrgInfoDto;
import com.suning.sntk.dto.vgo.StaffUnitDto;

/**
 * 功能描述：门店详情服务
 *
 * @author 88396455_白振华
 * @since 2018-8-31
 */
@Service
public class StoreDetailServiceImpl implements StoreDetailService {

    @Autowired
    private O2oStoreDetailDao storeDetailDao;

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Override
    public List<OrgInfoDto> queryRegionInfoList(EmployeeInfo loginUser) {
        String orgLevel = loginUser.getOrgLevel();
        String orgCode = loginUser.getOrgCode();
        StaffUnitDto staffUnitDto = new StaffUnitDto();
        if (OrgInfo.ORG_LEVEL.REGION_LEVEL.equals(orgLevel)) {
            staffUnitDto.setRegionId(orgCode);
        } else if (OrgInfo.ORG_LEVEL.COMPANY_LEVEL.equals(orgLevel)) {
            staffUnitDto.setOrgId(orgCode);
        } else if (OrgInfo.ORG_LEVEL.STORE_LEVEL.equals(orgLevel)) {
            staffUnitDto.setStoreCode(orgCode);
        }
        return storeDetailDao.queryRegionInfoByStaffUnit(staffUnitDto);
    }

    @Override
    public List<OrgInfoDto> queryCompanyInfoList(String regionId, EmployeeInfo loginUser) {
        String orgLevel = loginUser.getOrgLevel();
        String orgCode = loginUser.getOrgCode();
        StaffUnitDto staffUnitDto = new StaffUnitDto();
        if (OrgInfo.ORG_LEVEL.COMPANY_LEVEL.equals(orgLevel)) {
            staffUnitDto.setOrgId(orgCode);
        } else if (OrgInfo.ORG_LEVEL.STORE_LEVEL.equals(orgLevel)) {
            staffUnitDto.setStoreCode(orgCode);
        } else {
            staffUnitDto.setRegionId(regionId);
        }
        List<OrgInfoDto> orgInfoDtos = storeDetailDao.queryCompanyInfoList(staffUnitDto);
        if (CollectionUtils.isNotEmpty(orgInfoDtos)) {
            for (OrgInfoDto orgInfoDto : orgInfoDtos) {
                String saleOrgName = nsfbusConsumerService.querySaleOrgName(orgInfoDto.getOrgId());
                orgInfoDto.setOrgName(saleOrgName);
            }
        }
        return orgInfoDtos;
    }

    @Override
    public List<OrgInfoDto> queryStoreInfoList(String orgId, EmployeeInfo loginUser) {
        String orgLevel = loginUser.getOrgLevel();
        String orgCode = loginUser.getOrgCode();
        StaffUnitDto staffUnitDto = new StaffUnitDto();
        if (OrgInfo.ORG_LEVEL.STORE_LEVEL.equals(orgLevel)) {
            staffUnitDto.setStoreCode(orgCode);
        } else {
            staffUnitDto.setOrgId(orgId);
        }
        return storeDetailDao.queryStoreInfoList(staffUnitDto);
    }

}
