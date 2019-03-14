/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopFrequencyServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/11 17:23
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.esb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.rsc.dto.responsedto.MbfResponse;
import com.suning.rsc.httpservice.annotation.EsbEIHttpWired;
import com.suning.sntk.BaseTest;
import com.suning.sntk.consumer.esb.ShopFrequencyService;
import com.suning.sntk.dto.esb.ShopFrequencyInfo;
import com.suning.sntk.dto.esb.ShopFrequencyReq;
import com.suning.sntk.dto.esb.ShopFrequencyResp;
import com.suning.sntk.service.esb.ShopFrequencyEsbService;

/**
 * 功能描述： esb 首付购测试（提供方缺少sit环境）
 *
 * @author 88402362_欧小冬
 * @since 2018/7/11
 */
public class ShopFrequencyServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopFrequencyServiceTest.class);

    @Autowired
    private ShopFrequencyService shopFrequencyService;

    private ShopFrequencyEsbService shopFrequencyEsbService;

    public ShopFrequencyEsbService getShopFrequencyEsbService() {
        return shopFrequencyEsbService;
    }

    @EsbEIHttpWired
    public void setShopFrequencyEsbService(ShopFrequencyEsbService shopFrequencyEsbService) {
        this.shopFrequencyEsbService = shopFrequencyEsbService;
    }

    @Test
    public void queryInfoTest(){
        List<String> custList = new ArrayList<>();
        custList.add("60010012332");
        ShopFrequencyReq request = new ShopFrequencyReq(custList);
        MbfResponse mbfResponse = null;
        try {
            mbfResponse = shopFrequencyEsbService.queryShopFrequency(request);

            if (null != mbfResponse && null != mbfResponse.getOutput()) {
                ShopFrequencyResp resp = (ShopFrequencyResp) mbfResponse.getOutput().getMbfBody(ShopFrequencyResp.class);
                if (resp != null && CollectionUtils.isNotEmpty(resp.getCustList())) {
                    List<ShopFrequencyInfo> shopFreList = resp.getCustList();
                    for (ShopFrequencyInfo info : shopFreList) {
                       LOGGER.info("EBS.result,result[key:{},value[]]" , info.getCustNum() , info.getShopFrequency());
                    }
                }
            }
        }catch (Exception e){
            LOGGER.info("首付购异常");
        }
        Map<String, String> result = shopFrequencyService.queryShopFrequency(custList);
        LOGGER.info("queryShopFrequency,result[{}]", result);
    }
}
