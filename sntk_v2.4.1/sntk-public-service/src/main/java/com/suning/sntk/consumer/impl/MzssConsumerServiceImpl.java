/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MoingSocialConsumerServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/9/12 18:41
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.consumer.impl;

import com.suning.mzss.rsf.video.dto.VFrontContentDto;
import com.suning.mzss.rsf.video.service.RsfFrontVContentInfoService;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.consumer.MzssConsumerService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：
 *
 * @author 18010645_黄成
 * @since 2018/9/12
 */
@Service
public class MzssConsumerServiceImpl implements MzssConsumerService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MzssConsumerServiceImpl.class);

    //移动社交相关系统(查询v购视频列表)
    private RsfFrontVContentInfoService rsfFrontVContentInfoService = ServiceLocator
            .getService(RsfFrontVContentInfoService.class, "def");

    /**
     * 功能：查询社交相关系统v购视频列表
     *
     * @param custNo   联盟会员编码
     * @param pageNo   页数
     * @param pageSize 条数
     * @param fromType 渠道
     * @author 18010645_黄成
     * @since 18:47 2018/9/12
     */
    @Override
    public List<VFrontContentDto> queryVgoVideoList(String custNo, Integer fromType, Integer pageNo, Integer pageSize) {
        LOGGER.info("MzssConsumerService.queryVgoVideoList,params[custNo:{},fromType:{},pageNo:{},pageSize:{}]", custNo, fromType, pageNo, pageSize);
        try {
            List<VFrontContentDto> list = rsfFrontVContentInfoService.getVVideoNewList(custNo, fromType, pageNo, pageSize);
            LOGGER.info("MzssConsumerService.getVVideoNewList,params[custNo:{},fromType:{},pageNo:{},pageSize:{}]", custNo, fromType, pageNo, pageSize);
            if (CollectionUtils.isNotEmpty(list)) {
                return list;
            }
        } catch (Exception e) {
            LOGGER.warn("调用社交相关系统v购视频列表失败！", e);
        }
        return null;
    }
}
