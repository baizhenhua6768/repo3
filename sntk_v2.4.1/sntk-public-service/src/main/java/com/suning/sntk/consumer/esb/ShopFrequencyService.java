/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopFrequencyService
 * Author:   17061157_王薛
 * Date:     2018/7/11 20:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.esb;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：查询会员首购复购状态
 *
 * @author 17061157_王薛
 * @since 2018/7/11
 */
public interface ShopFrequencyService {

    /**
     * 根据会员编码查询会员首购  复购状态，支持批量查询
     *
     * @param custList
     * @return
     */
    Map<String, String> queryShopFrequency(List<String> custList);
}
