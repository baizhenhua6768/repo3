/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoService
 * Author:   18041004_余长杰
 * Date:     2018/8/16 15:14
 * Description: //易购app导购查询
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo;

import com.suning.sntk.dto.vgo.*;

import java.util.List;

/**
 * 功能描述：易购app导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
public interface GuideInfoService {


    /**
     * 功能：店员主页(导购详情、最近预约信息、视频)
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @param pageNo      页数
     * @param pageSize    条数
     * @param fromType    渠道
     * @author 18010645_黄成
     * @since 14:22 2018/9/13
     */
    StaffPageDto queryGuideInfoAndVgoVideo(String customerNum, String guideId, Integer fromType, Integer pageNo, Integer pageSize);

    /**
     * 功能：导购详情
     *
     * @param guideId 导购工号
     * @author 18010645_黄成
     * @since 14:31 2018/9/21
     */
    GuideDetailDto getGuideDetail(String guideId);

    /**
     * 功能：pc获取门店V购人员及门店基本信息
     *
     * @param storeCode 门店编码
     * @param guideId   导购工号
     * @author 18010645_黄成
     * @since 18:04 2018/8/30
     */
    StoreServantRespDto queryStoreGuides(String storeCode, String guideId);

    /**
     * 查询省、市信息
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @return RegionInfoListDto
     * @author 18041004_余长杰
     * @since 16:42 2018/8/30
     */
    GuideRegionInfoListDto queryRegionListByParentCode(String parentRegionCode, String regionalLevel);

    /**
     * @param regionCode    区域编码
     * @param regionalLevel 层级编码
     * @return GuideRegionInfoDto
     * @author 18041004_余长杰
     * @since 20:03 2018/9/11
     */
    GuideRegionInfoDto queryRegionInfoByRegionalCode(String regionCode, String regionalLevel);


    /**
     * 微信小程序查询会员客户经理
     *
     * @param storeCode 门店编码
     * @param custNo    会员编号
     * @author 88397670_张辉
     * @since 14:57 2018-9-8
     */
    StoreGuideInfoDto queryAppletCustManager(String storeCode, String custNo);

    /**
     * 微信小程序店员卡片页
     *
     * @param storeCode  门店编码
     * @param custNo     会员编号呢
     * @param preGuideId 上级页匹配导购工号
     * @author 88397670_张辉
     * @since 10:25 2018-9-10
     */
    List<StoreGuideInfoDto> queryGuideListForSmallRoutine(String storeCode, String custNo, String preGuideId);
}
