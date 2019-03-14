/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideAppointmentDao
 * Author:   88395115_史小配
 * Date:     2018/8/16 14:41
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import com.suning.sntk.dto.vgo.BookingInfoDto;
import com.suning.sntk.dto.vgo.GuideAppointmentDto;
import com.suning.sntk.dto.vgo.GuideDto;
import com.suning.sntk.dto.vgo.QueryAppointmentReqDto;
import com.suning.sntk.entity.vgo.AppointmentInfo;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMaster;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.annotation.DalPrimitiveParam;
import com.suning.store.dal.base.DalBaseDao;

import java.sql.Timestamp;
import java.util.List;

/**
 * 功能描述：V购导购预约dao接口
 *
 * @author 88395115_史小配
 * @since 2018/8/16
 */
@DalMapper("guide")
@Trace
public interface GuideAppointmentDao extends DalBaseDao<AppointmentInfo, Long> {

    /**
     * 查询会员的是否已经预约过
     *
     * @param cust     会员号
     * @param bookTime 预约时间
     * @param today    今天的时间
     * @author 88395115_史小配
     * @since 15:01 2018/8/17
     */
    @DalMaster
    @DalParams({ "cust", "bookTime", "today" })
    boolean isExistCustBooking(String cust, String bookTime, String today);

    /**
     * 添加预约单
     *
     * @param bookingDto
     * @author 88395115_史小配
     * @since 15:16 2018/8/17
     */
    void addBookGuide(@DalParam("bookingDto") BookingInfoDto bookingDto);

    /**
     * 判断预约单号是否存在
     *
     * @param bookCode 预约单号
     * @author 88395115_史小配
     * @since 15:36 2018/8/17
     */
    Boolean isExistBookCode(@DalParam("bookCode") String bookCode);

    /**
     * 获取导购信息
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 17:17 2018/8/17
     */
    GuideDto getGuideInfo(@DalParam("guideId") String guideId);

    /**
     * 查询我的预约列表
     *
     * @param custNo
     * @param size
     * @return
     */
    @DalParams({ "custNo", "size" })
    List<BookingInfoDto> queryBookList(String custNo, Integer size);

    /**
     * 根据预约单号查询预约信息
     *
     * @param bookCode 预约单编号
     * @author 88395115_史小配
     * @since 19:41 2018/8/20
     */
    BookingInfoDto getAppointmentByBookCode(@DalParam("bookCode") String bookCode);

    /**
     * @param guideId
     * @author 88395115_史小配
     * @since 17:58 2018/9/19
     */
    BookingInfoDto queryBookGuide(@DalParam("guideId") String guideId);

    /**
     * 分页查询预约列表
     *
     * @param queryAppointmentReqDto
     * @param pageable
     * @author 88395115_史小配
     * @since 9:13 2018/8/20
     */
    Page<GuideAppointmentDto> queryAppointmentList(@DalParam("reqDto") QueryAppointmentReqDto queryAppointmentReqDto, Pageable pageable);

    /**
     * 根据预约单号查找预约信息
     *
     * @param bookCode
     * @return
     */
    @DalParams({ "bookCode" })
    BookingInfoDto getBookingInfoByBookCode(String bookCode);

    /**
     * 取消预约
     *
     * @param infoDto
     */
    @DalParams({ "infoDto" })
    void cancelBook(BookingInfoDto infoDto);

    /**
     * 更新备注
     *
     * @param bookId 预约单id
     * @param remark 备注
     * @author 88395115_史小配
     * @since 14:46 2018/8/20
     */
    @DalParams({ "id", "remark" })
    void updateRemark(Long bookId, String remark);

    /**
     * 更新预约单已发送短信
     *
     * @param bookCode 预约单id
     * @author 88395115_史小配
     * @since 9:37 2018/8/29
     */
    void updateSendSms(@DalParam("bookCode") String bookCode);

    /**
     * 查询创建时间之前的预约单号
     *
     * @param createTime 创建时间
     * @author 88395115_史小配
     * @since 15:50 2018/9/8
     */
    List<AppointmentInfo> queryAppointmentByCreatTime(@DalParam("createTime") Timestamp createTime);

    /**
     * 预约记录设置为离职状态
     *
     * @param staffList
     * @author 18032490_赵亚奇
     * @since 14:45  2018/8/30
     */
    @DalParams({ "staffList" })
    void updateDimissionFlag(@DalPrimitiveParam List<String> staffList);

    /**
     * 根据预约编码查询预约记录
     *
     * @param bookCode 预约编码
     * @author 18032490_赵亚奇
     * @since 20:19  2018/9/10
     */
    @DalParams({ "bookCode" })
    BookingInfoDto findBookingByBookCode(String bookCode);

    /**
     * 查询最近的记录
     *
     * @param custNo 会员编码
     * @author 18032490_赵亚奇
     * @since 20:19  2018/9/10
     */
    @DalParams({ "custNo" })
    List<BookingInfoDto> queryNearAppointment(String custNo);

    /**
     * 从库中查询导购对应预约单的会员编码
     *
     * @param staffId 会员编码
     * @author 18032490_赵亚奇
     * @since 15:12  2018/9/25
     */
    @DalParams({ "staffId" })
    List<String> queryCustNo(String staffId);

    /**
     * 功能：会员当天是否存在未完成预约单
     *
     * @param customerNo 会员编码
     * @param today      当天
     * @author 18010645_黄成
     * @since 15:11 2018/10/11
     */
    @DalParams({"customerNo", "today"})
    boolean existNoCompleteBookingOrder(String customerNo, String today);

}