/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BaoziConsumerServiceImpl
 * Author:   18041004_余长杰
 * Date:     2018/9/6 15:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.suning.baozi.rsfservice.StoreGuideService;
import com.suning.baozi.rsfservice.constant.ResponseContent;
import com.suning.baozi.rsfservice.dto.O2oVGuideInfoDto;
import com.suning.baozi.rsfservice.dto.StoreGuideDto;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：鲍子系统Consumer
 *
 * @author 18041004_余长杰
 * @since 2018/9/6
 */
@Service
public class BaoziConsumerServiceImpl implements BaoziConsumerService {

    /**
     * 接口查询返回标志 0-成功
     */
    private static final String RETURN_SUCCESS_FLAG = "0";

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaoziConsumerServiceImpl.class);

    private StoreGuideService storeGuideService = ServiceLocator.getService(StoreGuideService.class, null);

    @Override
    public List<StoreGuideDto> queryStoreBestGuideId(String storeCode) {
        LOGGER.info("BaoziConsumerServiceImpl.queryStoreBestGuideId,params[storeCode:{}]", storeCode);
        ResponseContent<List<StoreGuideDto>> storeGuideInfo = storeGuideService.queryStoreBestGuideId(storeCode);
        LOGGER.info("BaoziConsumerServiceImpl.queryStoreBestGuideId,result[{}]", storeGuideInfo);
        if (storeGuideInfo != null && RETURN_SUCCESS_FLAG.equals(storeGuideInfo.getResponseCode())) {
            return storeGuideInfo.getResponseObject();
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> queryTopGuideIdList(List<String> storeList, String categoryId, String custNo) {
        LOGGER.info("BaoziConsumerServiceImpl.queryTopGuideIdList,params[storeList:{},categoryId:{},custNo:{}]", storeList, categoryId,
                custNo);
        ResponseContent<List<String>> storeCode = storeGuideService.queryTopGuideIdList(storeList, categoryId, custNo);
        LOGGER.info("BaoziConsumerServiceImpl.queryTopGuideIdList,result[{}]", storeCode);
        if (storeCode != null && RETURN_SUCCESS_FLAG.equals(storeCode.getResponseCode())) {
            return storeCode.getResponseObject();
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> queryTopGuiders(String storeCode, String custNo) {
        LOGGER.info("BaoziConsumerServiceImpl.queryTopGuiders,params[storeCode:{},custNo:{}]", storeCode, custNo);
        ResponseContent<List<String>> guiders = storeGuideService.queryTopGuiders(storeCode, custNo);
        LOGGER.info("BaoziConsumerServiceImpl.queryTopGuiders,result:{}", guiders);
        if (guiders != null && RETURN_SUCCESS_FLAG.equals(guiders.getResponseCode())) {
            return guiders.getResponseObject();
        }

        return Collections.emptyList();
    }

    @Override
    public void updateGuideInfoTable(List<O2oVGuideInfoDto> o2oVGuideInfoDtos) {
        LOGGER.info("BaoziConsumerServiceImpl.updateGuideInfoTable params[{}]", o2oVGuideInfoDtos);
        ResponseContent responseContent = storeGuideService.updateGuideInfoTable(o2oVGuideInfoDtos);
        LOGGER.info("BaoziConsumerServiceImpl.updateGuideInfoTable result[{}]", responseContent);
        Validators.ifInValid(null == responseContent || ResponseContent.FAIL.equals(responseContent.getResponseCode())).thenThrow
                (VgoErrorEnum.CALL_BAOZI_ERROR);
    }

}
