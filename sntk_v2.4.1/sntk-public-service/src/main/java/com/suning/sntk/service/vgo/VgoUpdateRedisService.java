/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoUpdateRedisService
 * Author:   18041004_余长杰
 * Date:     2018/10/8 9:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo;

import com.suning.sntk.dto.vgo.StoreGuideInfoDto;

/**
 * 功能描述：V购更新redis
 *
 * @author 18041004_余长杰
 * @since 2018/10/8
 */
public interface VgoUpdateRedisService {

    /**
     * 查询所有的客户经理，放入redis
     *
     * @author 18041004_余长杰
     * @since 9:39 2018/10/8
     */
    void saveAllCustManagerToCache();

    /**
     * 查询三级目录对应品类
     *
     * @author 18041004_余长杰
     * @since 9:55 2018/10/8
     */
    void saveAllThreeDirectoryCategoryToCache();

    /**
     * 根据门店查询导购存入redis
     *
     * @author 18041004_余长杰
     * @since 10:06 2018/10/8
     */
    void saveGuideInfoToCacheByStore();

    /**
     * 根据城市查询导购存入redis
     *
     * @author 18041004_余长杰
     * @since 10:56 2018/10/8
     */
    void saveGuideInfoToCacheByCity();

    /**
     * 根据城市、业态查询导购存入redis
     *
     * @author 18041004_余长杰
     * @since 14:50 2018/10/8
     */
    void saveGuideInfoToCacheByCityAndBusinessType();

    /**
     * 查询所有的导购信息，放入redis
     *
     * @author 18041004_余长杰
     * @since 10:54 2018/10/10
     */
    void saveAllGuideInfoToCache();

    /**
     * 查询所有V购门店
     *
     * @author 18041004_余长杰
     * @since 10:58 2018/10/10
     */
    void saveAllVgoStoreListToCache();

    /**
     * 查询预约数和获赞数
     *
     * @param guideInfo 导购信息
     * @author 88396455_白振华
     * @since 17:21  2018-10-8
     */
    void queryGuideOrderNumAndPraise(StoreGuideInfoDto guideInfo);
}
