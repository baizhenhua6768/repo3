/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreVservantService
 * Author:   18010645_黄成
 * Date:     2018/8/30 16:34
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo;

import com.suning.sntk.dto.vgo.StoreServantRespDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018/8/30
 */
public interface StoreVservantService {

    /**
     * 功能：PC四级页-获取门店所有V购人员及门店基本信息
     *
     * @param storeCode 门店编码
     * @param guideId   导购工号
     * @author 18010645_黄成
     * @since 16:37 2018/8/30
     */
    RsfResponseDto<StoreServantRespDto> getStoreVServants(String storeCode, String guideId);
}
