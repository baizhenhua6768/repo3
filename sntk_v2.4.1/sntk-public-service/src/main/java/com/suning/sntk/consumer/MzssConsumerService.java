/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MovingSocialConsumerService
 * Author:   18010645_黄成
 * Date:     2018/9/12 18:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer;

import com.suning.mzss.rsf.video.dto.VFrontContentDto;

import java.util.List;

/**
 * 功能描述：查询社交相关系统v购视频列表
 *
 * @author 18010645_黄成
 * @since 2018/9/12
 */
public interface MzssConsumerService {

    /**
     * 功能：查询社交相关系统v购视频列表
     *
     * @param custNo   苏宁联盟会员编码
     * @param pageNo   页码
     * @param pageSize 条数
     * @param fromType 渠道
     * @author 18010645_黄成
     * @since 20:00 2018/9/12
     */
    List<VFrontContentDto> queryVgoVideoList(String custNo, Integer fromType, Integer pageNo, Integer pageSize);
}
