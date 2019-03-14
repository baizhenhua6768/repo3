/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SnUnionConsumerServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/9/12 18:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import com.suning.api.rsf.service.ApiRemoteMapService;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.SuisConsumerService;
import com.suning.sntk.support.common.VgoConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：苏宁联盟系统（查询v购视频会员编码）
 *
 * @author 18010645_黄成
 * @since 2018/9/12
 */
@Service
public class SuisConsumerServiceImpl implements SuisConsumerService {

    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(SuisConsumerServiceImpl.class);

    //苏宁联盟系统（查询v购视频会员编码）
    private ApiRemoteMapService remoteMapService = ServiceLocator
            .getService(ApiRemoteMapService.class, "suis-staffmemberinfo-queryCipher");

    /**
     * 功能：获取v购视频会员编码
     *
     * @param employedId 员工工号
     * @author 18010645_黄成
     * @since 14:11 2018/9/13
     */
    @Override
    public String queryVedioCustomerNum(String employedId) {
        LOGGER.info("SuisConsumerService.queryVedioCustomerNum,params[employedId:{}]", employedId);
        Map<String, Object> map = new HashMap<>();
        map.put("employeeId", employedId);
        try {
            Map<String, Object> mapResult = remoteMapService.execute(map);
            LOGGER.info("SuisConsumerService.execute,result[{}]", mapResult);
            if (null != mapResult && VgoConstants.SUIS_SUCCESS_STATUS.equals(mapResult.get("status"))) {
                return String.valueOf(mapResult.get("cipher"));
            }
        } catch (Exception e) {
            LOGGER.warn("调用苏宁联盟v购视频会员编码失败！", e);
        }
        return null;
    }
}
