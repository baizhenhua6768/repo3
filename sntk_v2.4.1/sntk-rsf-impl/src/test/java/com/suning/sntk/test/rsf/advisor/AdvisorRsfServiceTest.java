/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorRsfServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/13 9:24
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.test.rsf.advisor;

import java.util.ArrayList;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.consumer.MemberConsumerService;
import com.suning.sntk.dto.advisor.CustomerDetailDto;
import com.suning.sntk.dto.advisor.CustomerDetailEditDto;
import com.suning.sntk.dto.advisor.CustomerListDto;
import com.suning.sntk.dto.advisor.CustomerManagerDto;
import com.suning.sntk.dto.advisor.CustomerQueryDto;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.rsf.impl.advisor.AdvisorRsfServiceImpl;
import com.suning.sntk.service.advisor.AdvisorService;
import com.suning.sntk.service.customer.CustomerService;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.service.shopper.ShopperService;

/**
 * 功能描述： 专业顾问 rsf 测试类
 *
 * @author 88402362_欧小冬
 * @since 2018/7/13
 */
public class AdvisorRsfServiceTest {

    @InjectMocks
    AdvisorRsfServiceImpl advisorRsfService;

    @Mock
    CustomerService customerService;

    @Mock
    AdvisorService advisorService;

    @Mock
    GuideService guideService;

    @Mock
    ShopperService shopperService;

    @Mock
    private MemberConsumerService memberConsumerService;

    private StaffCustRel staffCustRel;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        staffCustRel = new StaffCustRel();
        staffCustRel.setCustNo("15072856");
        staffCustRel.setStaffId("12061818");
        guideService.create(staffCustRel);
    }

    @Test
    public void queryShopperInfoTest() {
        String staffId = "15072856";
        String storeCode = "7611";
        String customerNo = "6001849344";
        ShopperDto shopperDto = new ShopperDto();
        BDDMockito.when(shopperService.queryShopperInfo(staffId, storeCode, customerNo))
                .thenReturn(shopperDto);
        advisorRsfService.queryShopperInfo(staffId, storeCode, customerNo);

    }

    @Test
    public void queryCustomerListTest() {
        CustomerQueryDto queryParam = new CustomerQueryDto();
        queryParam.setStaffId("12061818");
        queryParam.setBuyDateType(1);
        queryParam.setIsToday("0");
        queryParam.setStartIndex(1);
        queryParam.setCustomerName(null);
        queryParam.setPageSize(2);

        List<CustomerListDto> customerList = new ArrayList<>();
        BDDMockito.when(advisorService.queryCustomerList(queryParam)).thenReturn(customerList);
        advisorRsfService.queryCustomerList(queryParam);

    }

    @Test
    public void bindCustomerManagerTest() {
        String customerNo = staffCustRel.getCustNo();
        String staffId = staffCustRel.getStaffId();
        List<StaffCustRel> staffCustRel = new ArrayList<>();
        BDDMockito.when(guideService.queryStaffCustListByCustNo(customerNo))
                .thenReturn(staffCustRel);
        guideService.deleteRelByStaffIdAndCustNo(staffId, customerNo);
        advisorService.setManagerFlag(customerNo, staffId);

        CustomerManagerDto dto = new CustomerManagerDto();
        dto.setCustomerNo(customerNo);
        dto.setStaffId(staffId);
        advisorRsfService.bindCustomerManager(dto);

    }

    @Test
    public void queryCustomerEditInfoTest() {
        String staffId = "11020026";
        String unionId = "oM7Ayt_XPyjcF9sw2OH_Syqg47-Q";

        CustomerDetailEditDto detailEditDto = new CustomerDetailEditDto();
        BDDMockito.when(customerService.queryCustomerEditInfo(staffId, unionId)).
                thenReturn(detailEditDto);
        this.advisorRsfService.queryCustomerEditInfo(staffId, unionId);
        BDDMockito.when(memberConsumerService.queryMemberFirstPurchaseInfo("6054660259")).thenReturn(null);

    }

    @Test
    public void updateCustomerDetailTest() {
        CustomerDetailDto detailDto = new CustomerDetailDto();
        detailDto.setStaffId("11020026");
        detailDto.setUnionId("oM7Ayt_XPyjcF9sw2OH_Syqg47-Q");
        detailDto.setRemarkName("测试昵称");
        detailDto.setBuyTime("2018-09-01");
        detailDto.setRemarkInfo("双开冰箱");

        Boolean aBoolean = new Boolean(false);
        BDDMockito.when(customerService.saveCustomerInfo(detailDto)).thenReturn(aBoolean);
        this.advisorRsfService.updateCustomerDetail(detailDto);
    }
}
