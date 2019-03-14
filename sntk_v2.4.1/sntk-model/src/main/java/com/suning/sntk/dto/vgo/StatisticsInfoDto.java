package com.suning.sntk.dto.vgo;

import java.io.Serializable;
/**
 * 功能描述:统计 <br>
 * @author 18010645
 */
public class StatisticsInfoDto implements Serializable {

    /**
     * 序号
     */
    private static final long serialVersionUID = 3535214020649672375L;

    //预约编码
    private String bookCode;

    //商品编码
    private String commodityCode;

    //渠道来源
    private String channel;

    //页面来源
    private String resourceType;

    //创建时间
    private String createTime;

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    @Override
    public String toString() {
        return "StatisticsInfoDto{" +
                "bookCode='" + bookCode + '\'' +
                ", commodityCode='" + commodityCode + '\'' +
                ", channel='" + channel + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
