/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffPageGuideDetaiDto
 * Author:   18010645_黄成
 * Date:     2018/9/3 14:18
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：店员主页导购详情和v购视频
 *
 * @author 18010645_黄成
 * @since 2018/9/3
 */
public class StaffPageDto implements Serializable {
    private static final long serialVersionUID = -4959156768426775801L;
    //店员主页导购详情
    private GuideDetailDto guideDetailDto;
    //会员最近一次预约信息
    private CustomerBookDto customerBookDto;
    //vg购视频列表
    private List<VgoVideoDto> vgoVideoDtoList;

    public GuideDetailDto getGuideDetailDto() {
        return guideDetailDto;
    }

    public void setGuideDetailDto(GuideDetailDto guideDetailDto) {
        this.guideDetailDto = guideDetailDto;
    }

    public CustomerBookDto getCustomerBookDto() {
        return customerBookDto;
    }

    public void setCustomerBookDto(CustomerBookDto customerBookDto) {
        this.customerBookDto = customerBookDto;
    }

    public List<VgoVideoDto> getVgoVideoDtoList() {
        return vgoVideoDtoList;
    }

    public void setVgoVideoDtoList(List<VgoVideoDto> vgoVideoDtoList) {
        this.vgoVideoDtoList = vgoVideoDtoList;
    }

    @Override
    public String toString() {
        return "StaffPageDto{" +
                "guideDetailDto=" + guideDetailDto +
                ", customerBookDto=" + customerBookDto +
                ", vgoVideoDtoList=" + vgoVideoDtoList +
                '}';
    }
}
