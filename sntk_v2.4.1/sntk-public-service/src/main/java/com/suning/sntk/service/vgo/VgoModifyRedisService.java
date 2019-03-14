/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoModifyRedisService
 * Author:   88396455_白振华
 * Date:     2018-10-8 10:06
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo;

import java.util.List;

import com.suning.store.dal.annotation.DalParams;

/**
 * 功能描述：删除V购redis缓存
 *
 * @author 88396455_白振华
 * @since 2018-10-8
 */
public interface VgoModifyRedisService {

    /**
     * 根据会员编码和业态修改redis缓存
     *
     * @param CustNo       会员编码
     * @param guideId      导购工号
     * @param businessType 业态
     * @author 88396455_白振华
     * @since 10:14  2018-10-8
     */
    void changeCustomerManagerCache(String CustNo, String guideId, String businessType);

    /**
     * 根据会员编码删除导购信息
     *
     * @param guideId      会员编码
     * @param storeCode    门店编码
     * @param businessType 业态
     * @author 88396455_白振华
     * @since 10:25  2018-10-8
     */
    void deleteGuideInfoCache(String guideId, String storeCode, String businessType);

    /**
     * 根据导购工号批量删除导购缓存
     *
     * @param guideIdList 导购工号集合
     * @author 88396455_白振华
     * @since 10:56  2018-10-10
     */
    void batchDeleteGuideInfoCache(List<String> guideIdList);
}
