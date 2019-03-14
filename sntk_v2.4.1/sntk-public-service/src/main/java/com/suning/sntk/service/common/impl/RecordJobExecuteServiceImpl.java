/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: RecordJobExecuteServiceImpl
 * Author:   88397670_张辉
 * Date:     2018-9-20 11:06
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.common.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.dao.JobRecordDao;
import com.suning.sntk.entity.JobRecordEntity;
import com.suning.sntk.service.common.RecordJobExecuteService;
import com.suning.sntk.support.common.utils.DateUtils;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-9-20
 */
@Service
public class RecordJobExecuteServiceImpl implements RecordJobExecuteService {

    @Autowired
    private JobRecordDao jobRecordDao;

    @Override
    public Long addRecord(String jobDesc) {
        JobRecordEntity jobRecordEntity = new JobRecordEntity();
        jobRecordEntity.setBeginTime(new Date());
        jobRecordEntity.setDescription(jobDesc);
        return jobRecordDao.insert(jobRecordEntity).getId();
    }

    @Override
    public Boolean updateRecord(Long id, Boolean isSuccess, String errorMessage) {
        JobRecordEntity jobRecordEntity = jobRecordDao.findById(id);
        jobRecordEntity.setEndTime(new Date());
        jobRecordEntity.setTotalTime(DateUtils.estimatedSeconds(jobRecordEntity.getBeginTime(),jobRecordEntity.getEndTime()));
        jobRecordEntity.setSuccess(isSuccess);
        jobRecordEntity.setErrorMessage(errorMessage);
        return jobRecordDao.update(jobRecordEntity);
    }

}
