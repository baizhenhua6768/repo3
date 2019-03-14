/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopperServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/11 10:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.shopper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.service.shopper.ShopperService;

/**
 * 功能描述：
 *
 * @author 88402362_欧小冬
 * @since 2018/7/11
 */
public class ShopperServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopperServiceTest.class);

    @Autowired
    private ShopperService shopperService;

    @Autowired
    private GuideService guideService;

    private StaffCustRel staffCustRel;

    @BeforeMethod
    public void init(){
        staffCustRel = new StaffCustRel();
        staffCustRel.setId(1L);
        staffCustRel.setStaffId("00120009");
        staffCustRel.setCustNo("6002008499");
        staffCustRel.setStoreCode("7611");
        guideService.create(staffCustRel);
    }

    @Test
    public void queryShopperInfoTest() {
        String staffId = staffCustRel.getStaffId();
        String custNo = staffCustRel.getCustNo();
        String storeCode = staffCustRel.getStoreCode();
        ShopperDto dto = shopperService.queryShopperInfo(staffId, storeCode, custNo);

    }


}
