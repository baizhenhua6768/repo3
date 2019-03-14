/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopFrequencyEsbService
 * Author:   17061157_王薛
 * Date:     2018/7/11 20:36
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.esb;

import com.suning.rsc.dto.responsedto.MbfResponse;
import com.suning.rsc.httpservice.annotation.EsbEIHttpMethod;
import com.suning.rsc.httpservice.annotation.EsbEIHttpService;
import com.suning.sntk.dto.esb.ShopFrequencyReq;
import com.suning.sntk.dto.esb.ShopFrequencyResp;

/**
 * 功能描述：查询首购复购标记 esb 接口
 *
 * @author 17061157_王薛
 * @since 2018/7/11
 */
@EsbEIHttpService(appCode = "SNTK", serviceCode = "MPDSShopInfoMant")
public interface ShopFrequencyEsbService {

    @EsbEIHttpMethod(operation = "queryShopFrequency", serviceResource = "", reqMbfBodyNode = true,
            timeOut = 3000, soTimeOut = 5000, requestBodyClass = ShopFrequencyReq.class,
            responseBodyClass = ShopFrequencyResp.class)
    MbfResponse queryShopFrequency(ShopFrequencyReq req);
}
