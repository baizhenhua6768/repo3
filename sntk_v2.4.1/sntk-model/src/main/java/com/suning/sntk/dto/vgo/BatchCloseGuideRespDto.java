/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: BatchCloseGuideRespDto
 * Author:   88396455_白振华
 * Date:     2018-9-5 10:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-9-5
 */
public class BatchCloseGuideRespDto implements Serializable {

    private static final long serialVersionUID = 342729617401180484L;
    /**
     * 失败列表
     */
    private List<FailParseGuideExcelDto> failList;

    /**
     * 失败条数
     */
    private Integer failCount;

    /**
     * 成功条数
     */
    private Integer successCount;

    public BatchCloseGuideRespDto() {
        //empty constructor
    }

    public BatchCloseGuideRespDto(List<FailParseGuideExcelDto> failList, Integer failCount, Integer successCount) {
        this.failCount = failCount;
        this.successCount = successCount;
        this.failList = failList;
    }

    public List<FailParseGuideExcelDto> getFailList() {
        return failList;
    }

    public void setFailList(List<FailParseGuideExcelDto> failList) {
        this.failList = failList;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
