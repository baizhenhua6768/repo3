/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopFrequencyServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/7/11 20:37
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.esb.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suning.rsc.dto.responsedto.MbfResponse;
import com.suning.rsc.httpservice.annotation.EsbEIHttpWired;
import com.suning.sntk.consumer.esb.ShopFrequencyService;
import com.suning.sntk.dto.esb.ShopFrequencyInfo;
import com.suning.sntk.dto.esb.ShopFrequencyReq;
import com.suning.sntk.dto.esb.ShopFrequencyResp;
import com.suning.sntk.service.esb.ShopFrequencyEsbService;

/**
 * 功能描述：查询会员首购复购状态
 *
 * @author 17061157_王薛
 * @since 2018/7/11
 */
@Service
public class ShopFrequencyServiceImpl implements ShopFrequencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopFrequencyServiceImpl.class);

    private ShopFrequencyEsbService shopFrequencyEsbService;

    public ShopFrequencyEsbService getShopFrequencyEsbService() {
        return shopFrequencyEsbService;
    }

    @EsbEIHttpWired
    public void setShopFrequencyEsbService(ShopFrequencyEsbService shopFrequencyEsbService) {
        this.shopFrequencyEsbService = shopFrequencyEsbService;
    }

    @Override
    public Map<String, String> queryShopFrequency(List<String> custList) {
        if (CollectionUtils.isEmpty(custList)) {
            return Collections.emptyMap();
        }

        Map<String, String> shopFreMap = new HashMap<String, String>();
        MbfResponse response = null;
        try {
            ShopFrequencyReq request = new ShopFrequencyReq(custList);
            LOGGER.info("queryShopFrequency,param[{}]", request);
            response = shopFrequencyEsbService.queryShopFrequency(request);
            LOGGER.info("ShopFrequencyServiceImpl.queryShopFrequency,result[{}]", response);
        } catch (Exception e) {
            LOGGER.error("ShopFrequencyServiceImpl,error", e);
        }

        if (null != response && null != response.getOutput()) {
            ShopFrequencyResp resp = (ShopFrequencyResp) response.getOutput().getMbfBody(ShopFrequencyResp.class);
            if (resp != null && CollectionUtils.isNotEmpty(resp.getCustList())) {
                List<ShopFrequencyInfo> shopFreList = resp.getCustList();
                for (ShopFrequencyInfo info : shopFreList) {
                    shopFreMap.put(info.getCustNum(), info.getShopFrequency());
                }
            }
        }
        return shopFreMap;
    }
}
