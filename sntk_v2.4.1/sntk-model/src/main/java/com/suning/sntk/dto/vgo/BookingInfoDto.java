package com.suning.sntk.dto.vgo;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：预约信息dto
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
public class BookingInfoDto implements Serializable {

    /**
     * 序号
     */
    private static final long serialVersionUID = 3689098271300629113L;
    // 预约编码
    private String bookCode;
    // 预约时间
    private String bookingTime;
    // 导购ID
    private String guideId;
    // 订单完成情况
    private String complete;
    // 导购手机号码
    private String telephone;
    // 备注
    private String reMark;
    // 易购会员账号
    private String cust;
    // 回访：1拨打未接听，2 拨打已接听
    private String visit;
    // 预约状态：0未到店、1到店购买、2到店未购买、3未销单、4通用5、用户取消 6、系统取消
    private String bookingStatus;
    // 是否评价：0:未到店，1：已到店（可评价），2已评价
    private String judgeFlag;
    // 预约创建时间
    private String createTime;
    // 接待时间
    private String reviceTime;
    //门店编码
    private String storeCode;
    //原因（未到店、到店未购买）
    private String reason;
    // 到店已购买金额
    private String price;
    //转换日期
    private long createTimeLong;
    /**
     * 预约业务code：1为V购 , 2-母婴;
     */
    private String businessCode;

    /**
     * 预约时间
     */
    private Date bookTime;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 0：线上 1：线下
     */
    private String vtype;
    // 门店名称
    private String storeName;
    // 品类名称
    private String categoryName;
    // 导购头像
    private String guidePhoto;
    // 导购姓名
    private String guideName;
    // 导购星级
    private String starLevel;
    // 预约单数
    private String oderNum;
    //易购会员电话号码
    private String custTele;
    //商品名称
    private String productName;
    //商品价格
    private String productPrice;
    //导购开关状态
    private String guideOpenFlag;
    //门店开关状态
    private String storeOpenFlag;
    /**
     * 门店类型
     */
    private String storeType;
    /**
     * 00：任性付账户已开通（有资格已开通）
     * 01：任性付账户未激活（有资格未开通）
     * 02：任性付账户不存在（没资格）
     */
    private String accountState;
    /**
     * 最大额度，accountState为00或01时必填，单位元，保留两位小数-
     */
    private String maxAmt;
    /**
     * 可用额度
     */
    private String avaliableAmt;
    //地址
    private String address;
    /**
     * 预约服务项目类型（1：服务项目，2：服务特色及保障，3：厂家授权品牌）
     */
    private String serviceItemType;
    /**
     * 预约服务项目
     */
    private String serviceItemVal;
    /**
     * 用户看到的订单状态
     */
    private String orderStatus;
    /**
     * 人员是否离职
     */
    private String dimissionFlag;

    /**
     * 当前时间
     */
    private Date nowTime;

    private Date receiveTime;

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public void setDimissionFlag(String dimissionFlag) {
        this.dimissionFlag = dimissionFlag;
    }

    public String getDimissionFlag() {
        return dimissionFlag;
    }

    public long getCreateTimeLong() {
        return createTimeLong;
    }

    public void setCreateTimeLong(long createTimeLong) {
        this.createTimeLong = createTimeLong;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGuidePhoto() {
        return guidePhoto;
    }

    public void setGuidePhoto(String guidePhoto) {
        this.guidePhoto = guidePhoto;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getOderNum() {
        return oderNum;
    }

    public void setOderNum(String oderNum) {
        this.oderNum = oderNum;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public String getCustTele() {
        return custTele;
    }

    public void setCustTele(String custTele) {
        this.custTele = custTele;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getJudgeFlag() {
        return judgeFlag;
    }

    public void setJudgeFlag(String judgeFlag) {
        this.judgeFlag = judgeFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReviceTime() {
        return reviceTime;
    }

    public void setReviceTime(String reviceTime) {
        this.reviceTime = reviceTime;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getGuideOpenFlag() {
        return guideOpenFlag;
    }

    public void setGuideOpenFlag(String guideOpenFlag) {
        this.guideOpenFlag = guideOpenFlag;
    }

    public String getStoreOpenFlag() {
        return storeOpenFlag;
    }

    public void setStoreOpenFlag(String storeOpenFlag) {
        this.storeOpenFlag = storeOpenFlag;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(String maxAmt) {
        this.maxAmt = maxAmt;
    }

    public String getAvaliableAmt() {
        return avaliableAmt;
    }

    public void setAvaliableAmt(String avaliableAmt) {
        this.avaliableAmt = avaliableAmt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getServiceItemType() {
        return serviceItemType;
    }

    public void setServiceItemType(String serviceItemType) {
        this.serviceItemType = serviceItemType;
    }

    public String getServiceItemVal() {
        return serviceItemVal;
    }

    public void setServiceItemVal(String serviceItemVal) {
        this.serviceItemVal = serviceItemVal;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    @Override
    public String toString() {
        return "BookingInfoDto{" +
                "bookCode='" + bookCode + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                ", guideId='" + guideId + '\'' +
                ", complete='" + complete + '\'' +
                ", telephone='" + telephone + '\'' +
                ", reMark='" + reMark + '\'' +
                ", cust='" + cust + '\'' +
                ", visit='" + visit + '\'' +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", judgeFlag='" + judgeFlag + '\'' +
                ", createTime='" + createTime + '\'' +
                ", reviceTime='" + reviceTime + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", reason='" + reason + '\'' +
                ", price='" + price + '\'' +
                ", createTimeLong=" + createTimeLong +
                ", businessCode='" + businessCode + '\'' +
                ", bookTime=" + bookTime +
                ", channel='" + channel + '\'' +
                ", vtype='" + vtype + '\'' +
                ", storeName='" + storeName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", guidePhoto='" + guidePhoto + '\'' +
                ", guideName='" + guideName + '\'' +
                ", starLevel='" + starLevel + '\'' +
                ", oderNum='" + oderNum + '\'' +
                ", custTele='" + custTele + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", guideOpenFlag='" + guideOpenFlag + '\'' +
                ", storeOpenFlag='" + storeOpenFlag + '\'' +
                ", storeType='" + storeType + '\'' +
                ", accountState='" + accountState + '\'' +
                ", maxAmt='" + maxAmt + '\'' +
                ", avaliableAmt='" + avaliableAmt + '\'' +
                ", address='" + address + '\'' +
                ", serviceItemType='" + serviceItemType + '\'' +
                ", serviceItemVal='" + serviceItemVal + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", dimissionFlag='" + dimissionFlag + '\'' +
                ", receiveTime=" + receiveTime +
                '}';
    }
}
