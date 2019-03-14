package com.suning.sntk.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.consumer.NsfbusConsumerService;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-9-20
 */
public class NsfbusConsumerServiceImplTest extends BaseTest {

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Test
    public void testQuerySaleOrgName() {
        nsfbusConsumerService.querySaleOrgName("1100");
    }
}