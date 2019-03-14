/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: ShopperDto
 * Author:   88402362_欧小冬
 * Date:     2018/7/10 20:02
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.advisor;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 功能描述： 店员详情信息
 *
 * @author 88402362_欧小冬
 * @since 2018/7/10
 */
public class ShopperDto implements Serializable {

    private static final long serialVersionUID = 6623054115564047696L;

    /**
     * 店员工号
     */
    private String staffId;

    /**
     * 店员姓名
     */
    private String staffName;

    /**
     * 店员性别
     */
    private String staffGender;

    /**
     * 店员手机号码
     */
    private String staffMobile;

    /**
     * 店员擅长领域
     */
    private List<String> skillList;

    /**
     * 门店ID
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 客户经理 1：是, 0：不是
     */
    private int manage;
    /**
     * 店员是否在白名单 1:在白名单 0：不在白名单
     */
    private int whiteList;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    public List<String> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<String> skillList) {
        this.skillList = skillList;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getManage() {
        return manage;
    }

    public void setManage(int manage) {
        this.manage = manage;
    }

    public int getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(int whiteList) {
        this.whiteList = whiteList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
