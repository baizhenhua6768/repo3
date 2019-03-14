/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopperServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/10 11:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.shopper.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.suning.nsfbus.organization.rsfservice.dto.SiteInfoDto;
import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.nsfuaa.employee.dto.EmployeeBuDto;
import com.suning.nsfuaa.employee.dto.EmployeeBuListDto;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.consumer.NsfuaaConsumerService;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.sntk.enums.SexEnum;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.service.shopper.ShopperService;
import com.suning.sntk.service.staffwhitelist.StaffWhiteListService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.exception.enums.AdvisorErrorEnum;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述： 店员详情数据查询service
 *
 * @author 17061157_王薛
 * @since 2018/7/10
 */
@Service
public class ShopperServiceImpl implements ShopperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopperServiceImpl.class);

    private final String CATE_TYPE = "01";

    private final String CATE_BRAND_TYPE = "02";

    private final String FRIDGE_WASHER = "冰洗";

    private final String FRIDGE = "冰箱";

    private final String WASHER = "洗衣机";

    /**
     * 功能描述： 查询店员信息接口数据
     */
    @Autowired
    private NsfuaaConsumerService nsfuaaConsumerService;

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Autowired
    private GuideService guideService;

    @Autowired
    private StaffWhiteListService staffWhiteListService;

    @Override
    public ShopperDto queryShopperInfo(String staffId, String storeCode, String customerNo) {
        LOGGER.info("ShopperServiceImpl.queryShopperInfo,param[staffId={},customerNo={},storeNum={}]",
                staffId, customerNo, storeCode);
        Employee employee = nsfuaaConsumerService.queryEmployeeBasicInfo(staffId);
        Validators.ifNull(employee).thenThrow(AdvisorErrorEnum.STAFF_NOT_EXIST);

        ShopperDto dto = new ShopperDto();
        dto.setStaffId(staffId);
        dto.setStaffName(employee.getEmployeeName());
        dto.setStaffGender(StringUtils.isNotBlank(employee.getGender()) ? employee.getGender() : SexEnum.MAN.getCode());

        // 优先设置公司号
        if (StringUtils.isNotBlank(employee.getCompanyCellphone())) {
            dto.setStaffMobile(employee.getCompanyCellphone());
        } else {
            dto.setStaffMobile(employee.getPersonalCellphone());
        }

        // 设置擅长领域
        List<String> cateBrand = queryAdeptCateBrandList(staffId);
        dto.setSkillList(cateBrand);

        SiteInfoDto siteInfo = nsfbusConsumerService.queryStoreInfo(storeCode);
        if (null != siteInfo && SiteInfoDto.SUCCESS == siteInfo.getStatus()) {
            dto.setStoreCode(storeCode);
            dto.setStoreName(siteInfo.getSiteName());
        }

        Staff staff = staffWhiteListService.queryByStaffIdWithStore(staffId, storeCode);
        dto.setWhiteList(null != staff ? CommonConstants.YES : CommonConstants.NO);
        StaffCustRel custRel = guideService.findRelByCustNoAndStaffId(staffId, customerNo);
        dto.setManage(null != custRel ? CommonConstants.YES : CommonConstants.NO);

        LOGGER.info("ShopperServiceImpl.queryShopperInfo,result[{}]", dto);
        return dto;
    }

    public static final Map<String, String> BU_NAME_MAP = new HashMap<String, String>() {
        {
            put("冰洗", "冰箱#洗衣机");
            put("黑电", "电视机");
            put("通讯", "通讯");
            put("数码", "数码");
            put("厨卫", "厨卫");
            put("电脑", "电脑");
            put("空调", "空调");
            put("小家电", "小家电");
        }

    };

    /**
     * 功能描述: 查询店员擅长的品牌和品类列表 <br>
     *
     * @param staffId
     * @return java.util.List<java.lang.String>
     * @author 17061157_王薛
     * @since 11:41  2018/7/10
     */
    private List<String> queryAdeptCateBrandList(String staffId) {
        EmployeeBuListDto response = nsfuaaConsumerService.queryBuBrandListByPersonNo(staffId);
        if (response == null || EmployeeBuListDto.SUCCESS != response.getStatus()
                || CollectionUtils.isEmpty(response.getList())) {
            return Collections.emptyList();
        }

        Set<String> set = new HashSet<>();
        List<EmployeeBuDto> buDtos = response.getList();
        for (EmployeeBuDto buDto : buDtos) {
            // 01 取品类  02取品类和品牌
            if (CATE_BRAND_TYPE.equals(buDto.getType())) {
                set.add(buDto.getMaterialGroupDesc());
                addBrands(set, buDto.getBuName());
            } else if (CATE_TYPE.equals(buDto.getType())) {
                addBrands(set, buDto.getBuName());
            }
        }
        return Lists.newArrayList(set);
    }

    private void addBrands(Set<String> set, String buName) {
        if (FRIDGE_WASHER.equals(buName)) {
            set.add(FRIDGE);
            set.add(WASHER);
        } else {
            set.add(BU_NAME_MAP.get(buName));
        }
    }
}
