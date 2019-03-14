/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerService
 * Author:   17061157_王薛
 * Date:     2018/7/7 17:38
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.service.customer;

import com.suning.sntk.dto.advisor.CustomerDetailDto;
import com.suning.sntk.dto.advisor.CustomerDetailEditDto;

/**
 * 功能描述：客户 service
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public interface CustomerService {

    /**
     * 查询客户编辑资料
     *
     * @param staffId 店员工号
     * @param unionId 用户公众号唯一标识
     * @return CustomerDetailEditDto
     * @author 18041004_余长杰
     * @since 16:08 2018/7/10
     */
    CustomerDetailEditDto queryCustomerEditInfo(String staffId, String unionId);

    /**
     * 功能描述: 保存客户信息 <br>
     *
     * @param detail 客户信息
     * @return boolean
     * @author 17061157_王薛
     * @since 17:41  2018/7/7
     */
    boolean saveCustomerInfo(CustomerDetailDto detail);

    /**
     * 功能描述： 查询客户的公共标签字符串
     *
     * @param unionId 客户公众号平台唯一编号
     * @return String
     * @author 88402362 欧小冬
     * @since 9:52 2018/7/11
     */
    String queryCustomerLabelInfo(String unionId);
}
