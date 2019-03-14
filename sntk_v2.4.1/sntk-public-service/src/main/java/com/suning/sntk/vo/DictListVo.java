package com.suning.sntk.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Desciption 字典列表vo
 * @Author by 17092777 李明 on 2017/10/20.
 */
public class DictListVo implements Serializable{

    private static final long serialVersionUID = -3171703879347962145L;


    private List<DictVo> dictList;

    private Long totalRecord;

    public List<DictVo> getDictList() {
        return dictList;
    }

    public void setDictList(List<DictVo> dictList) {
        this.dictList = dictList;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    @Override
    public String toString() {
        return ToStringBuilder
                .reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
