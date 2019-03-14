/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrgInfo
 * Author:   88396455_白振华
 * Date:     2018-7-2 11:46
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.region;

import java.io.Serializable;

/**
 * 功能描述：区域信息
 *
 * @author 88396455_白振华
 * @since 2018-7-2
 */
public class OrgInfo implements Serializable {

    private static final long serialVersionUID = 4946525045009503703L;
    /**
     * 组织ID
     */
    private String orgId;

    /**
     * 对应组织编码 门店层级为门店编码
     */
    private String orgCode;

    /**
     * 对应组织名称
     */
    private String orgName;

    /**
     * 岗位编码
     */
    private String posId;

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 组织层级 0-总部；1-分公司；4-门店
     */
    private String level;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public interface ORG_LEVEL {
        /**
         * 总部
         */
        String HQ_LEVEL = "0";
        /**
         * 分公司
         */
        String COMPANY_LEVEL = "1";
        /**
         * 大区
         */
        String REGION_LEVEL = "2";
        /**
         * 门店
         */
        String STORE_LEVEL = "4";
    }

    public interface ORG_LEVEL_NAME {
        /**
         * 总部
         */
        String HQ_LEVEL = "总部";
        /**
         * 分公司
         */
        String COMPANY_LEVEL = "分公司";
        /**
         * 大区
         */
        String REGION_LEVEL = "大区";
        /**
         * 门店
         */
        String STORE_LEVEL = "门店";
    }
}
