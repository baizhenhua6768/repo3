/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrganizationController
 * Author:   18032490_赵亚奇
 * Date:     2018/7/4 10:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.controller.staffwhitelist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.suning.sntk.admin.service.staffwhitelist.OrganizationService;
import com.suning.sntk.admin.vo.CompanyVo;
import com.suning.sntk.admin.vo.RegionVo;
import com.suning.sntk.admin.vo.StationVo;
import com.suning.sntk.admin.vo.StoreVo;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.interceptor.RequestUserHolder;
import com.suning.sntk.support.enums.StationEnum;

/**
 * @author 18032490_赵亚奇
 * @since 2018/7/4
 */
@Controller
@RequestMapping("/admin/organization")
public class OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    /**
     * 组织级别：总部
     */
    private static final String HQ_ORGLEVEL = "0";

    /**
     * 组织级别：大区
     */
    private static final String REGION_ORGLEVEL = "2";

    /**
     * 组织级别：门店
     */
    private static final String STORE_ORGLEVEL = "4";

    /**
     * 查询所有大区
     */
    @RequestMapping(value = "/queryRegionList")
    @ResponseBody
    public List<RegionVo> queryRegionList() {
        //获取当前用户信息
        EmployeeInfo user = RequestUserHolder.getRequestUser();
        LOGGER.info("当前用户信息 user:{}", user);

        List<RegionVo> regionVos = Lists.newArrayList();
        //如果为总部人员则展示所有大区,否则显示当前人员所在大区
        if (HQ_ORGLEVEL.equals(user.getOrgLevel())) {
            regionVos = organizationService.queryRegionList();
            LOGGER.info("查询所有大区结果 regionVos:{}", regionVos);
        } else {
            regionVos.add(new RegionVo(user.getRegion5Code(), user.getRegionName()));
        }
        return regionVos;
    }

    /**
     * 查询指定大区下的分公司列表
     *
     * @param regionCode 大区编码
     */
    @RequestMapping(value = "/queryBranchList")
    @ResponseBody
    public List<CompanyVo> queryBranchList(@NotBlank String regionCode) {
        //获取当前用户信息
        EmployeeInfo user = RequestUserHolder.getRequestUser();
        LOGGER.info("查询指定大区下的分公司列表，入参 regionCode:{},user:{}", regionCode, user);
        String orgLevel = user.getOrgLevel();
        List<CompanyVo> companyVos = Lists.newArrayList();
        //如果为组织等级为总部和大区则可以查询所有分公司,否则只显示当前分公司
        if (HQ_ORGLEVEL.equals(orgLevel) || REGION_ORGLEVEL.equals(orgLevel)) {
            companyVos = organizationService.queryBranchList(regionCode);
            LOGGER.info("查询指定大区下的分公司列表结果 companyVos:{}", companyVos);
        } else {
            companyVos.add(new CompanyVo(user.getBranchCode(), user.getBranchName()));
        }
        return companyVos;
    }

    /**
     * 查询指定分公司下门店列表
     *
     * @param compCode 分公司编码
     */
    @RequestMapping(value = "/queryStoreList")
    @ResponseBody
    public List<StoreVo> queryStoreList(@NotBlank String compCode) {
        //获取当前用户信息
        EmployeeInfo user = RequestUserHolder.getRequestUser();
        LOGGER.info("查询指定分公司下的门店列表，入参 compCode:{},user:{}", compCode, user);
        List<StoreVo> storeVos = Lists.newArrayList();
        //
        if (!STORE_ORGLEVEL.equals(user.getOrgLevel())) {
            storeVos = organizationService.queryStoreList(compCode);
            LOGGER.info("查询指定分公司下的门店列表结果，storeVos:{}", storeVos);
        } else {
            storeVos.add(new StoreVo(user.getStoreCode(), user.getStoreName()));
        }
        return storeVos;
    }

    /**
     * 查询岗位列表
     */
    @RequestMapping(value = "/queryStationList")
    @ResponseBody
    public List<StationVo> queryStationList() {
        List<StationVo> stationList = new ArrayList<>();
        for (StationEnum e : StationEnum.values()) {
            StationVo vo = new StationVo();
            vo.setStationCode(e.getStation());
            vo.setStationName(e.getDescription());
            stationList.add(vo);
        }
        LOGGER.info("查询所有岗位结果，stationList:{}", stationList);
        return stationList;
    }

    /**
     * 查询登录用户的组织信息
     */
    @RequestMapping(value = "/queryUserInfo")
    @ResponseBody
    public EmployeeInfo queryUserInfo() {
        //获取当前用户信息
        EmployeeInfo user = RequestUserHolder.getRequestUser();
        LOGGER.info("人员组织信息：user:{}", user);
        return user;
    }

}
