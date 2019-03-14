/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoDao
 * Author:   18041004_余长杰
 * Date:     2018/8/16 17:36
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.AppointPromptDto;
import com.suning.sntk.dto.vgo.BookNoDto;
import com.suning.sntk.dto.vgo.CategoryOutRelDto;
import com.suning.sntk.dto.vgo.ClerkIntegrityDto;
import com.suning.sntk.dto.vgo.CustomerBookDto;
import com.suning.sntk.dto.vgo.GuideDetailDto;
import com.suning.sntk.dto.vgo.GuideDto;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.OrderNoDto;
import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.StoreInfoDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMaster;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.annotation.DalPrimitiveParam;

/**
 * 功能描述：易购app导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
@DalMapper("guideInfo")
public interface GuideInfoDao {

    /**
     * 查询所有客户经理
     *
     * @return List<ManagerInfoDto>
     * @author 18041004_余长杰
     * @since 10:53 2018/8/27
     */
    List<ManagerInfoDto> queryAllCustManager();

    /**
     * 根据导购Id查询所有导购信息
     *
     * @return List<GuideInfoDto>
     * @author 18041004_余长杰
     * @since 11:12 2018/8/27
     */
    @DalParams({ "guideId" })
    StoreGuideInfoDto queryAllGuideInfoByGuideId(String guideId);

    /**
     * 查询所有导购信息数量
     *
     * @return Long
     * @author 18041004_余长杰
     * @since 11:12 2018/8/27
     */
    Long queryAllGuideInfoCount();

    /**
     * 查询所有导购信息
     *
     * @return List<GuideInfoDto>
     * @author 18041004_余长杰
     * @since 11:12 2018/8/27
     */
    @DalParams({"offset", "size"})
    List<StoreGuideInfoDto> queryAllGuideInfo(int offset, int size);

    /**
     * 查询母婴导购服务项目
     *
     * @return List<String>
     * @author 18041004_余长杰
     * @since 15:55 2018/9/27
     */
    @DalParams({ "guideId" })
    List<String> queryInfantGuideServiceItems(String guideId);

    /**
     * 查询所有三级目录对应的品类
     *
     * @return List<String>
     * @author 18041004_余长杰
     * @since 8:58 2018/9/3
     */
    List<CategoryOutRelDto> queryAllThreeDirectoryCategory();

    /**
     * 查询所有品类
     *
     * @return List<String>
     * @author 18041004_余长杰
     * @since 15:56 2018/9/7
     */
    List<String> queryAllCategory();

    /**
     * 根据城市Id查询城市下所有V购门店数量
     *
     * @return Long
     * @author 18041004_余长杰
     * @since 9:25 2018/9/3
     */
    @DalParams({ "businessType" })
    Long queryAllVgoStoreListCount(String businessType);

    /**
     * 根据城市Id查询城市下所有V购门店
     *
     * @return List<StoreInfoDto>
     * @author 18041004_余长杰
     * @since 9:25 2018/9/3
     */
    @DalParams({"offset", "size", "businessType" })
    List<StoreInfoDto> queryAllVgoStoreList(int offset, int size, String businessType);

    /**
     * 查询所有门店
     *
     * @return List<StoreInfoDto>
     * @author 18041004_余长杰
     * @since 10:30 2018/9/14
     */
    List<StoreInfoDto> queryAllStoreList();

    /**
     * 查询电器导购
     *
     * @param storeCode 门店编码
     * @param cateCode  品类编码
     * @return List<StoreGuideInfoDto>
     * @author 18041004_余长杰
     * @since 11:44 2018/9/3
     */
    @DalParams({ "storeCode", "cateCode" })
    @DalMaster
    List<StoreGuideInfoDto> queryElectricStoreGuide(String storeCode, String cateCode);

    /**
     * 查询母婴导购
     *
     * @return List<StoreGuideInfoDto>
     * @author 18041004_余长杰
     * @since 14:14 2018/9/3
     */
    @DalParams({ "storeCode" })
    @DalMaster
    List<StoreGuideInfoDto> queryInfantStoreGuide(String storeCode);

    /**
     * 查询所有有导购的城市Id
     *
     * @return List<String>
     * @author 18041004_余长杰
     * @since 11:27 2018/10/8
     */
    List<String> queryAllGuideCity();

    /**
     * 根据城市、品类查询导购
     *
     * @param cityId   城市Id
     * @param cateCode 品类
     * @return List<StoreGuideInfoDto>
     * @author 18041004_余长杰
     * @since 11:58 2018/10/8
     */
    @DalParams({ "cityId", "cateCode" })
    List<StoreGuideInfoDto> queryGuideByCityAndCategory(String cityId, String cateCode);

    /**
     * 根据城市查询导购信息
     *
     * @param cityId 城市Id
     * @return List<StoreGuideInfoDto>
     * @author 18041004_余长杰
     * @since 15:06 2018/10/8
     */
    @DalParams({ "cityId" })
    List<StoreGuideInfoDto> queryGuideByCity(String cityId);

    void updateGuideInfo(@DalParam("guideDto") GuideDto guideDto);

    /**
     * 查询导购预约数
     *
     * @param guideId
     * @return Long
     * @author 18041004_余长杰
     * @since 9:58 2018/8/21
     */
    @DalParams({ "guideId" })
    @DalMaster
    Long queryGuideBookingInfo(String guideId);

    /**
     * 销单数加1
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 14:21 2018/8/21
     */
    void updateOrderNum(@DalParam("guideId") String guideId);

    /**
     * 后台导购销单数加1
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 14:23 2018/8/21
     */
    void updateOrderNumB(@DalParam("guideId") String guideId);

    /**
     * 将导购设置为离职(前台)
     *
     * @param staffList
     * @author 18032490_赵亚奇
     * @since 14:59  2018/8/30
     */
    @DalParams({ "staffList" })
    void updateDimissionFlag(@DalPrimitiveParam List<String> staffList);

    /**
     * 查询审核表导购详情
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 10:41 2018/8/31
     */
    GuideInfoDto queryGuideDetaiAudit(@DalParam("guideId") String guideId);

    /**
     * 查询导购表导购详情
     *
     * @param guideId 导购id
     * @author 88395115_史小配
     * @since 11:39 2018/8/31
     */
    GuideInfoDto queryGuideDetai(@DalParam("guideId") String guideId);

    /**
     * 功能：店员主页导购详情
     *
     * @param guideId 导购工号
     * @author 18010645_黄成
     * @since 9:19 2018/9/3
     */
    @DalParams({ "guideId" })
    GuideDetailDto queryStaffPageGuideDetail(String guideId);

    /**
     * 功能：查询导购是否客户经理
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @author 18010645_黄成
     * @since 10:08 2018/9/3
     */
    @DalParams({ "customerNum", "guideId" })
    StaffCustRel queryIsCustomerManager(String customerNum, String guideId);

    /**
     * 查询导购详情集合
     *
     * @param staffIdList 导购id集合
     * @author 18032490_赵亚奇
     * @since 11:57  2018/9/3
     */
    @DalParams({ "staffIdList" })
    List<GuideInfoDto> queryGuideDetailList(@DalPrimitiveParam List<String> staffIdList);

    /**
     * 校验导购信息完整性
     *
     * @param guideId 导购工号
     * @author 88397670_张辉
     * @since 17:20 2018-9-4
     */
    ClerkIntegrityDto checkClerkInfo(@DalParam("guideId") String guideId);

    /**
     * 将导购设置为离职(后台)
     *
     * @param staffList
     * @author 18032490_赵亚奇
     * @since 14:59  2018/8/30
     */
    @DalParams({ "staffList" })
    void updatebDimissionFlag(@DalPrimitiveParam List<String> staffList);

    /**
     * 功能：导购表查询orderNum
     *
     * @param guideId 导购工号
     * @author 18010645_黄成
     * @since 16:15 2018/9/5
     */
    @DalParams({ "guideId" })
    @DalMaster
    Long queryOrderNum(String guideId);

    /**
     * 功能：查询最近一次预约改导购信息
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @param today       当天日期
     * @author 18010645_黄成
     * @since 17:35 2018/9/6
     */
    @DalParams({ "customerNum", "guideId", "today" })
    CustomerBookDto queryNearBookingInfo(String customerNum, String guideId, String today);

    /**
     * 功能：查询当天预约的导购工号
     *
     * @param customerNum 会员编码
     * @param bookTime    当预约时间
     * @param today       当天时间
     * @author 18010645_黄成
     * @since 11:00 2018/9/7
     */
    @DalParams({ "customerNum", "bookTime", "today" })
    String queryBookingGuideId(String customerNum, String bookTime, String today);

    /**
     * 功能：查询预约弹窗语
     *
     * @param guideId     导购工号
     * @param customerNum 会员编码
     * @param bookingTime 预约时间
     * @param today       当天时间
     * @author 18010645_黄成
     * @since 10:39 2018/9/7
     */
    @DalParams({ "guideId", "customerNum", "bookingTime", "today" })
    AppointPromptDto queryBookingDialogue(String guideId, String customerNum, String bookingTime, String today);

    /**
     * 查询当前门店业态
     *
     * @param storeCode
     * @return
     */
    String queryBusinessType(@DalParam("storeCode") String storeCode);

    /**
     * 功能：批量查询预约数量
     *
     * @param guideIds 导购工号集合
     * @author 18010645_黄成
     * @since 9:44 2018/10/7
     */
    @DalParams({ "guideIds" })
    List<BookNoDto> queryBatchGuideBookingCount(List<String> guideIds);

    /**
     * 功能：批量查询接单数
     *
     * @param guideIds 导购工号集合
     * @author 18010645_黄成
     * @since 9:46 2018/10/7
     */
    @DalParams({ "guideIds" })
    List<OrderNoDto> queryBatchOrderNumber(List<String> guideIds);

    /**
     * 根据导购工号查询导购信息
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 20:30  2018-10-8
     */
    @DalParams({ "guideId" })
    StoreGuideInfoDto queryGuideInfoByGuideId(String guideId);

    /**
     * 根据导购工号批量查询导购信息
     *
     * @param guideIdList 导购工号集合
     * @author 88396455_白振华
     * @since 10:59  2018-10-10
     */
    @DalParams({ "guideIdList" })
    List<GuideInfo> batchQueryGuideInfo(List<String> guideIdList);
}
