/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: O2oStoreStaffRel
 * Author:   88396455_白振华
 * Date:     2018-8-17 11:05
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.entity.vgo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述：人员单位关系信息
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
@Entity(name = "o2ob_store_staff_rel")
public class O2oStoreStaffRel implements Serializable {

    private static final long serialVersionUID = 7462841768332874968L;

    // 主键
    private long ssRelId;
    // 配置人编号
    private String configureId;
    // 操作时间
    private Timestamp createTime;
    // 行政层级（0 门店 1 城市 2 大区 3 总部）
    private String indexNum;
    // 单位编号
    private String storeId;
    // 员工编号
    private String userId;

    private String storeName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ss_rel_id")
    public long getSsRelId() {
        return ssRelId;
    }

    public void setSsRelId(long ssRelId) {
        this.ssRelId = ssRelId;
    }

    @Column(name = "configure_id")
    public String getConfigureId() {
        return this.configureId;
    }

    public void setConfigureId(String configureId) {
        this.configureId = configureId;
    }

    @Column(name = "index_num")
    public String getIndexNum() {
        return this.indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    @Column(name = "store_id")
    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "store_name")
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
