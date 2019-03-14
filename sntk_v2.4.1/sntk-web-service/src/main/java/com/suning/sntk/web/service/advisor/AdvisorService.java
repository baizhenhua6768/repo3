/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorService
 * Author:   17061157_王薛
 * Date:     2018/7/10 19:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.advisor;

import com.suning.sntk.dto.advisor.CustomerManagerDto;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：专业顾问 service
 *
 * @author 17061157_王薛
 * @since 2018/7/10
 */
public interface AdvisorService {

    /**
     * 功能描述: 设置客户经理 <br>
     * @author 17061157_王薛
     * @param customerManagerDto
     * @return com.suning.store.commons.rsf.RsfResponseDto<java.lang.String>
     * @since 19:40  2018/7/10
     */
    RsfResponseDto<Void> bindCustomerManager(CustomerManagerDto customerManagerDto);

    /**
     * 功能描述：查询店员详细信息
     *
     * @param staffId 店员工号
     * @param storeCode 店员所属门店编号
     * @param customerNo 客户会员编码
     * @author 88402362 欧小冬
     * @since 11:24 2018-07-11
     */
    RsfResponseDto<ShopperDto> queryShopperInfo(String staffId, String storeCode, String customerNo);

}
