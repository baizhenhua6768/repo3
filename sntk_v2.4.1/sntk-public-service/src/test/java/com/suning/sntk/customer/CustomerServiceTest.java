/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CustomerServiceTest
 * Author:   18041004_余长杰
 * Date:     2018/7/10 19:08
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.relation.CustomerAdvisorDao;
import com.suning.sntk.dto.advisor.CustomerBrandDto;
import com.suning.sntk.dto.advisor.CustomerDetailDto;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.service.customer.CustomerService;

/**
 * 功能描述：客户信息测试类
 *
 * @author 18041004_余长杰
 * @since 2018/7/10
 */
public class CustomerServiceTest extends BaseTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerAdvisorDao advisorDao;

    @Test
    public void queryCustomerEditInfoTest() {
        saveBuyAdditionTest();
        System.out.println(customerService.queryCustomerEditInfo("12061818", "oM7Aytw"));
    }

    @Test
    public void saveBuyAdditionTest() {
        if (advisorDao.queryCustomer("12061818", "oM7Aytw") == null) {
            CustomerAdvisor advisor = new CustomerAdvisor();
            advisor.setUnionId("oM7Aytw");
            advisor.setStaffId("12061818");
            advisor.setRemarkName("李大叔");
            advisorDao.insert(advisor);
        }
        CustomerDetailDto detail = new CustomerDetailDto();
        detail.setStaffId("12061818");
        detail.setUnionId("oM7Aytw");
        detail.setRemarkName("李大叔");
        detail.setCustMobile("11267829009");
        detail.setCustLabel("1#3");
        detail.setSelfLabel("喜欢蓝色#中年");
        List<CustomerBrandDto> buyDatas = new ArrayList<>();
        CustomerBrandDto brandDto = new CustomerBrandDto();
        brandDto.setCategoryNo("00003");
        brandDto.setCategoryName("黑电");
        brandDto.setBrandName("海尔#美的#奥克斯");
        brandDto.setMinPrice(3000);
        brandDto.setMaxPrice(5000);
        buyDatas.add(brandDto);
        detail.setBuyDatas(buyDatas);
        detail.setBuyTime("2018-07-23");
        customerService.saveCustomerInfo(detail);
    }

    @Test
    public void queryCustomerLabelInfoTest() {
        System.out.println(customerService.queryCustomerLabelInfo("oM7Aytw"));
    }
}
