/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoUpdateRedisJob
 * Author:   18041004_余长杰
 * Date:     2018/9/7 9:44
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.job.vgo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.suning.sntk.enums.JobEnum;
import com.suning.sntk.service.common.RecordJobExecuteService;
import com.suning.sntk.service.vgo.VgoUpdateRedisService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.exception.vgo.SntkRedisJobException;

/**
 * 功能描述：V购定时更新redis
 *
 * @author 18041004_余长杰
 * @since 2018/9/7
 */
@Component
public class VgoUpdateRedisJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoUpdateRedisJob.class);

    @Autowired
    private VgoUpdateRedisService vgoUpdateRedisService;

    @Autowired
    private RecordJobExecuteService recordJobExecuteService;

    /**
     * 将mysql中的数据灌到redis中，便于后续缓存处理
     *
     * @author 18041004_余长杰
     * @since 16:59 2018/9/14
     */
    public void updateVgoRedis() {
        Long id = null;
        try {
            id = recordJobExecuteService.addRecord(JobEnum.VGO_UPDATEREDIS_JOB.getJobDesc());

            //查询所有的客户经理，放入redis
            vgoUpdateRedisService.saveAllCustManagerToCache();

            //查询三级目录对应品类，放入redis
            vgoUpdateRedisService.saveAllThreeDirectoryCategoryToCache();

            //根据城市查询导购存入redis
            vgoUpdateRedisService.saveGuideInfoToCacheByCity();

            //根据城市、业态查询导购存入redis
            vgoUpdateRedisService.saveGuideInfoToCacheByCityAndBusinessType();

            //查询所有的导购信息
            vgoUpdateRedisService.saveAllGuideInfoToCache();

            //查询所有V购门店
            vgoUpdateRedisService.saveAllVgoStoreListToCache();

            recordJobExecuteService.updateRecord(id, true, null);
        } catch (Exception e) {
            LOGGER.error("同步vgo信息至redis缓存job失败!", e);
            int length = e.getMessage().length();
            int lastIndex = length > VgoConstants.MAX_ERROR_MESSAGE_LENGTH ? VgoConstants.MAX_ERROR_MESSAGE_LENGTH : length;
            String errorMessage = e.getMessage().substring(0, lastIndex);
            if (null != id) {
                recordJobExecuteService.updateRecord(id, false, errorMessage);
            }
            throw new SntkRedisJobException(e);
        }

    }

}
