/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: Manager
 * Author:   18032490_赵亚奇
 * Date:     2018/8/17 8:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.controller.vgo;

import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.support.exception.vgo.AppointmentErrorEnum;
import com.suning.sntk.web.service.vgo.ManagerService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 客户经理的查询与建立
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/17
 */
@RestController
@Trace
@Validated
@RequestMapping("vgo/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);

    /**
     * 查询我的客户经理(所有业态)
     *
     * @author 18032490_赵亚奇
     */
    @RequestMapping("/queryManagerList")
    @ResponseBody
    public RsfResponseDto<List<ManagerInfoDto>> queryManagerList(HttpServletRequest request) {
        String custNo = request.getRemoteUser();
        LOGGER.info("查询我的客户经理，会员编码:{}", custNo);
        return managerService.queryManagerList(custNo);
    }

    /**
     * 查询我的客户经理(区分业态)
     *
     * @param storeCode 门店编码
     * @author 18032490_赵亚奇
     */
    @RequestMapping("/queryManagerInfo")
    @ResponseBody
    public RsfResponseDto<ManagerInfoDto> queryManagerInfo(HttpServletRequest request, String storeCode) {
        String custNo = request.getRemoteUser();
        LOGGER.info("查询我的客户经理，会员编码:{}", custNo);
        return managerService.queryManagerInfo(custNo, storeCode);
    }

    /**
     * 建立客户经理关系
     *
     * @param staffId   员工id
     * @param storeCode 门店编码
     * @param channel   渠道 0:微信 1:易购2:预存，3:预售 4:小程序’
     * @author 18032490_赵亚奇
     */
    @RequestMapping("/buildManagerRelation")
    @ResponseBody
    public RsfResponseDto buildManagerRelation(HttpServletRequest request, @NotBlank String staffId, @NotBlank String
            storeCode, @NotBlank String channel) {
        String custNo = request.getRemoteUser();
        LOGGER.info("查询我的预约列表 会员编码:{}", custNo);
        Validators.ifBlank(custNo).thenThrow(AppointmentErrorEnum.PARAM_IS_NULL);
        return managerService.buildManagerRelation(custNo, staffId, storeCode, channel);
    }

    /**
     * 查询原有的客户经理
     *
     * @param staffId   店员编码
     * @param storeCode 门店编码
     * @author 18032490_赵亚奇
     * @since 10:17  2018/9/5
     */
    @RequestMapping("/queryOldManager")
    @ResponseBody
    public RsfResponseDto<List<GuideInfoDto>> queryOldManager(HttpServletRequest request, @NotBlank String staffId, @NotBlank String
            storeCode) {
        String custNo = request.getRemoteUser();
        LOGGER.info("查询我的预约列表 会员编码:{}", custNo);
        Validators.ifBlank(custNo).thenThrow(AppointmentErrorEnum.PARAM_IS_NULL);
        return managerService.queryOldManager(custNo, staffId, storeCode);
    }

    /**
     * 功能：查询导购工号
     *
     * @param businessType 业态
     * @author 18010645_黄成
     * @since 17:13 2018/10/16
     */
    @RequestMapping("/queryManagerInfoNew.do")
    @ResponseBody
    public RsfResponseDto<String> queryManagerInfoNew(HttpServletRequest request, String businessType) {
        String customerNo = request.getRemoteUser();
        LOGGER.info("查询会员客户经理，会员编码:{}，业态：{}", customerNo, businessType);
        Validators.ifInValid(StringUtils.isBlank(customerNo) || StringUtils.isBlank(businessType)).thenThrow(AppointmentErrorEnum.PARAM_IS_NULL);
        return managerService.queryManagerInfoNew(customerNo, businessType);
    }


}
