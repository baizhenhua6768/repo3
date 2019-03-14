/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SnUnionConsumerService
 * Author:   18010645_黄成
 * Date:     2018/9/12 18:06
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer;

/**
 * 功能描述：获取苏宁联盟v购视频会员编码
 *
 * @author 18010645_黄成
 * @since 2018/9/12
 */
public interface SuisConsumerService {

    /**
     * 功能：查询苏宁联盟v购视频会员编码
     *
     * @param employedId 员工工号
     * @author 18010645_黄成
     * @since 18:10 2018/9/12
     */
    String queryVedioCustomerNum(String employedId);

}
