/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MeternalInfantErrorEnum
 * Author:   88396455_白振华
 * Date:     2018-7-3 14:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.enums;

import com.suning.store.commons.lang.exception.ServiceExceptionNameProvider;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-3
 */
public enum MeternalInfantErrorEnum implements ServiceExceptionNameProvider {

    /**
     * 3002 名字不能为空
     */
    NAME_CAN_NOT_NULL("sntk.motherInfant.nameCannotBeNull"),
    /**
     * 3003 名字长度不能超过12个字符
     */
    NAME_SUPER_LONG("sntk.motherInfant.namesuperLong"),
    /**
     * 3004 区域信息不能为空
     */
    AREA_CAN_NOT_NULL("sntk.motherInfant.areaInfoCannotBeNull"),
    /**
     * 3005 详细地址不能为空
     */
    DETAIL_ADDRESS_CAN_NOT_NULL("sntk.motherInfant.detailAddressCannotBeNull"),
    /**
     * 3006 详细地址超长（最多30个字符）
     */
    DETAIL_ADDRESS_SUPER_LONG("sntk.motherInfant.detailAddressSuperLong"),
    /**
     * 3007 生育状态必填
     */
    MOM_STAT_CAN_NOT_NULL("sntk.motherInfant.momStatusCannotBeNull"),
    /**
     * 3008 预产期必输（怀孕中）
     */
    DUE_DATE_CAN_NOT_NULL("sntk.motherInfant.dueDateCannotBeNull"),
    /**
     * 3009 婴儿信息必输（有宝宝）
     */
    BABY_INFO_CAN_NOT_NULL("sntk.motherInfant.babyInfoCannotBeNull"),
    /**
     * 3010 宝宝性别必填
     */
    BABY_GENDER_CAN_NOT_NULL("sntk.motherInfant.babyGenderCannotBeNull"),
    /**
     * 3011 宝宝生日必填
     */
    BABY_BIRTHDAY_CAN_NOT_NULL("sntk.motherInfant.babyBirthdayCannotBeNull"),
    /**
     * 3012 奶粉用量长度超长（三位以内）
     */
    DOSAGE_SUPER_LONG("sntk.motherInfant.milkPowerDosageCannotBeNull"),
    /**
     * 3013 店员工号不能为空
     */
    PERSON_NO_CAN_NOT_NULL("sntk.motherInfant.personNoCannotBeNull");

    private String name;

    private MeternalInfantErrorEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

