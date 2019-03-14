/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: JobEnum
 * Author:   88397670_张辉
 * Date:     2018-9-20 11:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.enums;

/**
 * 功能描述：job功能名称枚举
 *
 * @author 88397670_张辉
 * @since 2018-9-20
 */
public enum JobEnum {

    VGO_UPDATEREDIS_JOB("VgoUpdateRedisJob","定时更新Vgo信息至redis缓存"),
    VGO_MATCHGUIDE_JOB("VgoMatchGuideInfoJob","麦琪匹配导购定时任务");

    String jobName;
    
    String jobDesc;

    JobEnum(String jobName, String jobDesc) {
        this.jobName = jobName;
        this.jobDesc = jobDesc;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }
}
