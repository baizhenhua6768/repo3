/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopperService
 * Author:   17061157_王薛
 * Date:     2018/7/7 17:25
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.shopper;

import com.suning.sntk.dto.advisor.ShopperDto;

/**
 * 功能描述：店员 service
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public interface ShopperService {

    /**
     * 功能描述: 查询店员信息 <br>
     * @author 17061157_王薛
     * @param staffId 店员工号
     * @param customerNo 客户会员编码
     * @param storeCode 门店编号
     * @return com.suning.sntk.dto.relation.ShopperDto
     * @since 17:26  2018/7/7
     */
    ShopperDto queryShopperInfo(String staffId,  String storeCode, String customerNo);
}
