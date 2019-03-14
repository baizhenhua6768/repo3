/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: NsfuaaConsumerServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/7 14:58
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suning.nsfuaa.employee.EmployeeService;
import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.nsfuaa.employee.dto.EmployeeBuListDto;
import com.suning.nsfuaa.employee.dto.EmployeeExt;
import com.suning.nsfuaa.orgnization.OrganizationService;
import com.suning.nsfuaa.orgnization.dto.OrgAttribution;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.NsfuaaConsumerService;

/**
 * 功能描述：Nsfuaa 系统调用 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Service
public class NsfuaaConsumerServiceImpl implements NsfuaaConsumerService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NsfuaaConsumerServiceImpl.class);

    /**
     * RSF 人员信息服务类
     */
    private EmployeeService employeeService = ServiceLocator.getService(EmployeeService.class, null);

    /**
     * RSF 组织信息服务类
     */
    private OrganizationService organizationService = ServiceLocator.getService(OrganizationService.class, null);

    @Override
    public Employee queryEmployeeBasicInfo(String personNo) {
        LOGGER.info("NsfuaaConsumerServiceImpl.queryEmployeeBasicInfo,param[{}]", personNo);
        Employee employee = employeeService.queryEmployeeBasicInfo(personNo);
        LOGGER.info("NsfuaaConsumerServiceImpl.queryEmployeeBasicInfo,result[{}]", employee);
        return (employee != null && Employee.SUCCESS == employee.getStatus()) ? employee : null;
    }

    @Override
    public EmployeeBuListDto queryBuBrandListByPersonNo(String personNo) {
        LOGGER.info("NsfuaaConsumerServiceImpl.queryBuBrandListByPersonNo,param[{}]", personNo);
        EmployeeBuListDto employeeBuListDto = employeeService.queryUumEployeeBuBrandListByPersonNo(personNo);
        LOGGER.info("NsfuaaConsumerServiceImpl.queryBuBrandListByPersonNo,result[{}]", employeeBuListDto);
        return (employeeBuListDto != null && EmployeeBuListDto.SUCCESS == employeeBuListDto.getStatus()) ? employeeBuListDto : null;
    }

    @Override
    public OrgAttribution queryOrgAttribution(String storeCode) {
        LOGGER.info("NsfuaaConsumerServiceImpl.queryOrgAttribution,params[storeCode:{}]", storeCode);
        OrgAttribution orgAttribution = organizationService.queryOrgAttribution(storeCode);
        LOGGER.info("NsfuaaConsumerServiceImpl.queryOrgAttribution,result[{}]", orgAttribution);
        return (orgAttribution != null && OrgAttribution.SUCCESS == orgAttribution.getStatus()) ? orgAttribution : null;
    }

    @Override
    public EmployeeExt queryEmployeeExt(String personNo) {
        LOGGER.info("NsfuaaConsumerServiceImpl.queryEmployeeExt,params[personNo:{}]", personNo);
        EmployeeExt employeeExt = employeeService.queryEmployeeExt(personNo);
        LOGGER.info("NsfuaaConsumerServiceImpl.queryEmployeeExt,result[{}]", employeeExt);
        return (null != employeeExt && EmployeeExt.SUCCESS == employeeExt.getStatus()) ? employeeExt : null;
    }

}
