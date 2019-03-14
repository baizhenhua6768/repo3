package com.suning.sntk.consumer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.consumer.NsfuaaConsumerService;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-9-20
 */
public class NsfuaaConsumerServiceImplTest extends BaseTest {

    @Autowired
    private NsfuaaConsumerService nsfuaaConsumerService;

    @Test
    public void testQueryOrgAttribution() {
        nsfuaaConsumerService.queryOrgAttribution("7010");
        nsfuaaConsumerService.queryOrgAttribution("aadd");
    }

    @Test
    public void testQueryEmployeeExt() {
        nsfuaaConsumerService.queryEmployeeExt("08071858");
        nsfuaaConsumerService.queryEmployeeExt("99556677");
    }
}