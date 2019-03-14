/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: HrConsumerService
 * Author:   88397670_张辉
 * Date:     2018-9-7 10:59
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer;

/**
 * 功能描述：HR人员信息查询接口
 *
 * @author 88397670_张辉
 * @since 2018-9-7
 */
public interface HrConsumerService {

    /**
     * HR接口通过组织Id查询所属
     *
     * @param orgId 组织Id
     * @author 88397670_张辉
     * @since 15:00 2018-9-14
     */
    String queryOrganization(String orgId);

    /**
     * HR接口查询人员信息
     *
     * @param guideId 店员工号
     * @param result  返回结果
     * @author 88397670_张辉
     * @since 15:01 2018-9-14
     */
    String queryPerson(String guideId, String result);
}
