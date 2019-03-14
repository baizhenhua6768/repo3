/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: MemberConsumerService
 * Author:   17061157_王薛
 * Date:     2018/7/7 11:27
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer;

import com.suning.mds.dto.MemShoppingGenesInfo;

/**
 * 功能描述：会员中台服务 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public interface MemberConsumerService {

    /**
     * 功能描述: 查询会员首付够信息 <br>
     *
     * @param custNum
     * @return com.suning.mds.dto.MemShoppingGenesInfo
     * @author 17061157_王薛
     * @since 11:46  2018/7/7
     */
    MemShoppingGenesInfo queryMemberFirstPurchaseInfo(String custNum);

    /**
     * 功能：查询会员手机号码
     *
     * @param custNum 会员编码
     * @author 18010645_黄成
     * @since 19:55 2018/8/22
     */
    String queryMemberPhone(String custNum);
}
