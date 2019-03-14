/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoMatchGuideInfoJob
 * Author:   18041004_余长杰
 * Date:     2018/9/4 10:24
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

import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.enums.JobEnum;
import com.suning.sntk.service.common.RecordJobExecuteService;
import com.suning.sntk.service.vgo.VgoUpdateRedisService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.exception.vgo.SntkRedisJobException;

/**
 * 功能描述：麦琪匹配导购定时任务
 *
 * @author 18041004_余长杰
 * @since 2018/9/4
 */
@Component
public class VgoMatchGuideInfoJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoMatchGuideInfoJob.class);

    @Autowired
    private VgoUpdateRedisService vgoUpdateRedisService;

    @Autowired
    RedisCacheUtils redisCacheUtils;

    @Autowired
    GuideInfoDao guideInfoDao;

    @Autowired
    BaoziConsumerService baoziConsumerService;

    @Autowired
    private RecordJobExecuteService recordJobExecuteService;

    public void queryGuideInfoByMatch() {
        Long id = null;
        try {
            id = recordJobExecuteService.addRecord(JobEnum.VGO_MATCHGUIDE_JOB.getJobDesc());

            //根据门店查询导购存入redis
            vgoUpdateRedisService.saveGuideInfoToCacheByStore();
            recordJobExecuteService.updateRecord(id, true, null);
        } catch (Exception e) {
            LOGGER.error("麦琪匹配导购定时任务失败！", e);
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
