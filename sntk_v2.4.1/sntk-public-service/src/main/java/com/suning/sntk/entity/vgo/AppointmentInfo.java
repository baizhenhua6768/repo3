package com.suning.sntk.entity.vgo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "O2O_V_GUIDE_APPOINTMENT")
public class AppointmentInfo {
	
	/**
	 * 表主键id
	 */
	private long id;
	
	/**
	 * 预约编码
	 */
	private String bookCode;
	
	/**
	 * 会员编号
	 */
	private String cust;
	
	/**
	 * 导购id
	 */
	private String guideId;
	
	/**
	 * 电话号码
	 */
	private String telephone;
	
	/**
	 * 预约时间
	 */
	private Timestamp bookingTime;
	
	/**
	 * 接待时间
	 */
	private Timestamp receiveTime;
	
	/**
	 * 预约完成状态
	 */
	private String bookingStatus;
	
	/**
	 * 订单完成情况 0-未到店 1-到店购买 2-到店未购买
	 */
	private String complete;
	
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	
	/**
	 * 回访
	 */
	private String visit;
	
	/**
	 * 是否评价
	 */
	private String judgeFlag;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 门店名称
	 */
    private String storeName;
    
    /**
	 * 门店编码
	 */
    private String storeCode;
    
    /**
	 * 渠道
	 */
    private String channel;
    
    /**
	 * 查询开始时间
	 */
    private String startime;
    
    /**
	 * 查询结束时间
	 */
    private String endtime;
    
    /**
	 * 原因
	 */
    private String reason;
    
    /**
	 * 购买金额
	 */
    private String price;
    
    /**
	 * 城市名称
	 */
    private String cityName;
    
    /**
	 * 大区名称
	 */
    private String regionName;

	/**
	 * 预约业务code
	 */
	private String businessCode;

	/**
	 * 0：线上   1：线下
	 */
    private String vType;

	/**
	 * 是否已发送短信，0：未发送，1：已发送
	 */
	private int isSendSms;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "BOOKCODE")
	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	@Column(name = "CUST")
	public String getCust() {
		return cust;
	}

	public void setCust(String cust) {
		this.cust = cust;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	@Column(name = "GUIDE_ID")
	public String getGuideId() {
		return guideId;
	}

	public void setGuideId(String guideId) {
		this.guideId = guideId;
	}

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column(name = "BOOKING_STATUS")
	public String getBookingStatus() {
		return bookingStatus;
	}

	@Column(name = "BOOKING_TIME")
	public Timestamp getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Timestamp bookingTime) {
		this.bookingTime = bookingTime;
	}

	@Column(name = "RECEIVE_TIME")
	public Timestamp getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@Column(name = "COMPLETE")
	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	@Column(name = "VISIT")
	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	@Column(name = "JUDGE_FLAG")
	public String getJudgeFlag() {
		return judgeFlag;
	}

	public void setJudgeFlag(String judgeFlag) {
		this.judgeFlag = judgeFlag;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "STORE_CODE")
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Column(name = "CHANNEL")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name = "PRICE")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(name = "business_code")
	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	@Column(name = "v_type")
	public String getvType() {
		return vType;
	}

	public void setvType(String vType) {
		this.vType = vType;
	}

	@Column(name = "is_send_sms")
	public int getIsSendSms() {
		return isSendSms;
	}

	public void setIsSendSms(int isSendSms) {
		this.isSendSms = isSendSms;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
