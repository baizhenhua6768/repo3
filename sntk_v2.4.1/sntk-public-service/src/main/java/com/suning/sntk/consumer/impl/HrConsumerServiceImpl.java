/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: HrConsumerServiceImpl
 * Author:   88397670_张辉
 * Date:     2018-9-7 11:01
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.suning.rsf.consumer.ServiceAgent;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.HrConsumerService;
import com.suning.sntk.support.util.DES3;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-7
 */
@Service
public class HrConsumerServiceImpl implements HrConsumerService {

    /**
     * hr查询导购基本信息接口
     */
    private ServiceAgent serviceAgent = ServiceLocator.getServiceAgent("com.suning.hrqz.service.RSFQueryServiceIntf",
            "HRQZ");

    @Value("${hrAccessSecretKey}")
    private String systemAccessKey;

    @Value("${hrdataSecretKey}")
    private String dataSecretKey;

    private static final String SNTK = "SNTK";

    /**
     * 组织查询参数名
     */
    private static final String ORG_QUERY_CRITERIA_PARAM = "objectID";

    /**
     * hr接口查询请求map参数名
     */
    private static final String QUERY_REQ_PARAM = "queryCriteria";

    /**
     * 组织查询返参名
     */
    private static final String ORG_QUERY_RESULT = "departmentsystem";

    /**
     * 组织查询方法名
     */
    private static final String ORG_QUERY_METHOD = "queryOrganization";

    /**
     * 人员信息查询参数名
     */
    private static final String PERSON_QUERY_CRITERIA_PARAM = "personNo";

    /**
     * 人员信息查询方法名
     */
    private static final String PERSON_QUERY_METHOD = "queryPerson";

    /**
     * 查询接口请求参数系统参数名
     */
    private static final String QUERY_SYSTEM_PARAM_NAME = "systemName";

    /**
     * 查询接口请求秘钥参数名
     */
    private static final String QUERY_SYSTEM_ACCESS_NAME = "systemAccessKey";

    /**
     * 查询接口请求参数名
     */
    private static final String QUERY_REQ_NAME = "sysInfo";

    /**
     * 返回接口实体
     */
    private static final String RESULT_DATA = "data";

    /**
     * 结果转换json取值下标
     */
    private static final int JSON_OBJECT_INDEX = 0;

    @Override
    public String queryOrganization(String OrgId) {
        Gson gSon = new Gson();
        Map<String, Object> req = buildReqMap(ORG_QUERY_CRITERIA_PARAM, OrgId);
        String rsp = (String) serviceAgent.invoke(ORG_QUERY_METHOD, new Object[] { gSon.toJson(req) }, new Class[] { String.class });
        return parsingResult(rsp, ORG_QUERY_RESULT);
    }

    @Override
    public String queryPerson(String guideId, String resultName) {
        Gson gSon = new Gson();
        Map<String, Object> req = buildReqMap(PERSON_QUERY_CRITERIA_PARAM, guideId);
        String rsp = (String) serviceAgent.invoke(PERSON_QUERY_METHOD, new Object[] { gSon.toJson(req) }, new Class[] { String.class });
        return parsingResult(rsp, resultName);
    }

    /**
     * 封装Hr接口通用请求map
     *
     * @param paramName 参数名
     * @param param     参数
     * @author 88397670_张辉
     * @since 18:01 2018-9-27
     */
    private Map<String, Object> buildReqMap(String paramName, String param) {
        Map<String, Object> req = new HashMap<>();
        // 系统信息
        Map<String, String> sysInfoParam = new HashMap<>();
        sysInfoParam.put(QUERY_SYSTEM_PARAM_NAME, SNTK);
        sysInfoParam.put(QUERY_SYSTEM_ACCESS_NAME, systemAccessKey);
        req.put(QUERY_REQ_NAME, sysInfoParam);
        // 查询条件
        Map<String, String> queryCriteriaParam = new HashMap<>();
        queryCriteriaParam.put(paramName, param);
        req.put(QUERY_REQ_PARAM, queryCriteriaParam);
        return req;
    }

    /**
     * 解析Hr接口的返回报文
     *
     * @param rsp        返回报文
     * @param resultName 需要解析获取的参数
     * @author 88397670_张辉
     * @since 16:27 2018-9-14
     */
    private String parsingResult(String rsp, String resultName) {
        String result = null;
        if (StringUtils.isNotBlank(rsp) && JSONObject.parseObject(rsp) != null) {
            JSONArray jsonArray = (JSONArray) JSONObject.parseObject(rsp).get(RESULT_DATA);
            if (jsonArray != null && CollectionUtils.isNotEmpty(jsonArray)) {
                JSONObject jo = jsonArray.getJSONObject(JSON_OBJECT_INDEX);
                if (null != jo && jo.get(resultName) != null && StringUtils.isNotBlank(jo.get(resultName).toString())) {
                    result = DES3.decryptProperty(jo.get(resultName).toString(), dataSecretKey);
                }
            }
        }
        return result;
    }
}
