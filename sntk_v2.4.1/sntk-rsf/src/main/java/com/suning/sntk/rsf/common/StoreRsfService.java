/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: StoreRsfService
 * Author:   17061157_王薛
 * Date:     2018/9/22 20:02
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.rsf.common;

import java.util.List;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.common.StoreGeoDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：门店信息 rsf 接口
 *
 * @author 17061157_王薛
 * @since 2018/9/22
 */
@Contract(name = "storeRsfService", description = "门店信息查询")
public interface StoreRsfService {

    /**
     * 功能描述: 查询门店总数 <br>
     *
     * @return RsfResponseDto<Long>
     * @author 17061157_王薛
     * @since 20:16  2018/7/7
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询门店总数")
    RsfResponseDto<Long> queryStoreCount();

    /**
     * 功能描述: 分页查询门店列表 <br>
     *
     * @return com.suning.store.commons.rsf.RsfResponseDto<CustomerBaseDto>
     * @author 17061157_王薛
     * @since 20:31  2018/7/7
     */
    @Method(idempotent = true, timeout = 5000, retryTimes = 3, description = "分页查询门店列表")
    RsfResponseDto<List<StoreGeoDto>> queryStorePageList(int offset, int size);

    /**
     * 功能描述: 查询最近有变化的门店信息列表 <br>
     *
     * @param modifyHour 多少小时内更新
     * @return com.suning.store.commons.rsf.RsfResponseDto<CustomerBaseDto>
     * @author 17061157_王薛
     * @since 20:31  2018/7/7
     */
    @Method(idempotent = true, timeout = 5000, retryTimes = 3, description = "查询最近有变化的门店信息列表")
    RsfResponseDto<List<StoreGeoDto>> queryModifyStoreList(int modifyHour);

}
