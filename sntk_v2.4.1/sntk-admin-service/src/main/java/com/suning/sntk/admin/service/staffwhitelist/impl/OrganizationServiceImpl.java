/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrganizationServiceImpl
 * Date:     2018/7/3 10:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.staffwhitelist.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.suning.nsfbus.organization.rsfservice.SiteRsfService;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SaleOrgDto;
import com.suning.nsfbus.organization.rsfservice.dto.SaleOrgListDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoListDto;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.admin.dao.staffwhitelist.StaffDao;
import com.suning.sntk.admin.service.staffwhitelist.OrganizationService;
import com.suning.sntk.admin.vo.CompanyVo;
import com.suning.sntk.admin.vo.RegionVo;
import com.suning.sntk.admin.vo.StoreVo;

/**
 * 查询组织信息
 *
 * @author 18032490
 * @since 2018/7/3
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private SiteRsfService siteRsfService = ServiceLocator.getService(SiteRsfService.class, null);

    @Autowired
    private StaffDao staffDao;

    @Override
    public List<RegionVo> queryRegionList() {
        LOGGER.info("调用rsf接口查询所有大区列表");
        DistrictOrgDto response = siteRsfService.queryAllDistrictOrg5();
        LOGGER.info("大区列表查询结果 response: {}", response);

        if (response == null) {
            LOGGER.info("大区列表查询失败");
            return Collections.emptyList();
        }

        List<RegionVo> resList;
        if (DistrictOrgDto.SUCCESS == response.getStatus()) {
            List<DistrictOrgDto.DistrictOrg> districtList = response.getDistrictOrgList();
            resList = convertRegionData(districtList);
        } else {
            LOGGER.info("大区列表查询失败 error:{}", response.getMessage());
            resList = Collections.emptyList();
        }
        return resList;
    }

    @Override
    public List<CompanyVo> queryBranchList(String regionCode) {
        LOGGER.info("调用rsf接口查询指定大区下分公司列表");
        SaleOrgListDto response = siteRsfService.queryAllSaleOrgByDistrictCode(regionCode);
        LOGGER.info(" 分公司列表查询结果 response: {}", response);

        if (response == null) {
            LOGGER.info("查询分公司列表失败");
            return Collections.emptyList();
        }

        List<CompanyVo> resList;
        if (SaleOrgListDto.SUCCESS == response.getStatus()) {
            List<SaleOrgDto> branchList = response.getSaleOrgDtoList();
            resList = convertBranchData(branchList);
        } else {
            LOGGER.info("查询分公司列表失败 error:{}", response.getMessage());
            resList = Collections.emptyList();
        }
        return resList;
    }

    @Override
    public List<StoreVo> queryStoreList(String compCode) {
        LOGGER.info("调用rsf接口查询指定分公司下的门店列表");
        SiteInfoListDto response = siteRsfService.queryAllSitesBySaleOrg(compCode);
        LOGGER.info("门店列表查询结果 response: {}", response);

        if (response == null) {
            LOGGER.info("查询门店列表失败");
            return Collections.emptyList();
        }

        List<StoreVo> resList;
        if (SiteInfoListDto.SUCCESS == response.getStatus()) {
            List<SiteInfoDto> storeList = response.getSiteInfoDtoList();
            resList = convertStoreData(storeList);
        } else {
            LOGGER.info("查询门店列表失败 error:{}", response.getMessage());
            resList = Collections.emptyList();
        }
        return resList;
    }

    private List<RegionVo> convertRegionData(List<DistrictOrgDto.DistrictOrg> districtList) {
        List<RegionVo> resList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(districtList)) {
            for (DistrictOrgDto.DistrictOrg dis : districtList) {
                if (dis != null) {
                    resList.add(new RegionVo(dis.getDistrictCode(), dis.getDistrictName()));
                }
            }
        }
        return resList;
    }

    private List<CompanyVo> convertBranchData(List<SaleOrgDto> branchList) {
        List<CompanyVo> resList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(branchList)) {
            for (SaleOrgDto branch : branchList) {
                if (branch != null) {
                    resList.add(new CompanyVo(branch.getSaleOrgCode(), branch.getSaleOrgName()));
                }
            }
        }
        return resList;
    }

    private List<StoreVo> convertStoreData(List<SiteInfoDto> storeList) {
        List<StoreVo> resList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(storeList)) {
            for (SiteInfoDto store : storeList) {
                if (store != null) {
                    resList.add(new StoreVo(store.getSiteCode(), store.getSiteName()));
                }
            }
        }
        return resList;
    }
}
