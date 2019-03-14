/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BaoziConsumerService
 * Author:   18041004_余长杰
 * Date:     2018/9/6 15:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer;

import java.util.List;

import com.suning.baozi.rsfservice.dto.O2oVGuideInfoDto;
import com.suning.baozi.rsfservice.dto.StoreGuideDto;

/**
 * 功能描述：鲍子系统Consumer
 *
 * @author 18041004_余长杰
 * @since 2018/9/6
 */
public interface BaoziConsumerService {

    /**
     * 根据门店编码查询该门店下各品类最优导购
     *
     * @param storeCode 门店编码
     * @return List<StoreGuideDto>
     * @author 18041004_余长杰
     * @since 15:31 2018/9/6
     */
    List<StoreGuideDto> queryStoreBestGuideId(String storeCode);

    /**
     * 根据门店列表、品类编码、会员编码查询导购列表
     *
     * @param storeList  门店编码列表
     * @param categoryId 品类Id
     * @param custNo     会员编号
     * @return List<String>
     * @author 18041004_余长杰
     * @since 15:31 2018/9/6
     */
    List<String> queryTopGuideIdList(List<String> storeList, String categoryId, String custNo);

    /**
     * 小程序查询导购列表
     *
     * @param storeCode 门店编码
     * @param custNo    会员编码
     * @author 88397670_张辉
     * @since 9:21 2018-9-10
     */
    List<String> queryTopGuiders(String storeCode, String custNo);

    /**
     * 根据导购信息列表更新V购导购人员信息表
     *
     * @param o2oVGuideInfoDtos 人员变更信息
     * @author 88396455_白振华
     * @since 21:17  2018-10-15
     */
    void updateGuideInfoTable(List<O2oVGuideInfoDto> o2oVGuideInfoDtos);
}
