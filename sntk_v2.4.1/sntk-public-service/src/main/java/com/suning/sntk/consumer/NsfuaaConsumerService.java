/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: NafuaaConsumerService
 * Author:   17061157_王薛
 * Date:     2018/7/7 11:26
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.consumer;

import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.nsfuaa.employee.dto.EmployeeBuListDto;
import com.suning.nsfuaa.employee.dto.EmployeeExt;
import com.suning.nsfuaa.orgnization.dto.OrgAttribution;

/**
 * 功能描述：Nsfuaa 系统调用 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public interface NsfuaaConsumerService {

    /**
     * 功能描述: 查询店员基本信息 <br>
     *
     * @param personNo
     * @return com.suning.nsfuaa.employee.dto.Employee
     * @author 17061157_王薛
     * @since 11:32  2018/7/7
     */
    Employee queryEmployeeBasicInfo(String personNo);

    /**
     * 功能描述: 根据工号查询擅长领域(品类 or 品牌 ？) <br>
     *
     * @param personNo
     * @return EmployeeBuListDto
     * @author 17061157_王薛
     * @since 11:32  2018/7/7
     */
    EmployeeBuListDto queryBuBrandListByPersonNo(String personNo);

    /**
     * 查询门店组织信息
     *
     * @param storeCode 门店编码
     * @author 88396455_白振华
     * @since 10:48  2018-7-11
     */
    OrgAttribution queryOrgAttribution(String storeCode);

    /**
     * 查询组织扩展信息
     *
     * @param personNo 人员工号
     * @author 88396455_白振华
     * @since 10:22  2018-9-3
     */
    EmployeeExt queryEmployeeExt(String personNo);
}
