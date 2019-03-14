/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreLableTree
 * Author:   88396455_白振华
 * Date:     2018-8-17 10:42
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：城市店铺信息树
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
public class StoreLableTree implements Serializable {

    private static final long serialVersionUID = -1172881133003495337L;

    /**
     * 父节点Id
     */
    private String pid;

    /**
     * 节点Id
     */
    private String id;

    /**
     * 节点名
     */
    private String name;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
