/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: TipsReqDto
 * Author:   18010645
 * Date:     2018/8/17 11:11
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.dto.vgo;

import java.io.Serializable;

/**
 * 功能描述:查询云信提示语dto <br>
 * <p>
 * Created by 18010645 on 2018/8/17
 */
public class TipsReqDto implements Serializable {

    /**
     * 序列号: <br>
     */
    private static final long serialVersionUID = -7056327914713366294L;
    //门店名称
    private String storeName;
    //店员名称
    private String staffName;
    //1:欢迎语 2：店员离线提示
    private String type;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TipsReqDto{" +
                "storeName='" + storeName + '\'' +
                ", staffName='" + staffName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
