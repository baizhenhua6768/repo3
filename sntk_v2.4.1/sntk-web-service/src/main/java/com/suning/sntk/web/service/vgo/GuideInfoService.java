/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoService
 * Author:   18041004_余长杰
 * Date:     2018/8/16 14:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo;

import com.suning.sntk.dto.vgo.*;
import com.suning.store.commons.rsf.RsfResponseDto;

import java.util.List;

/**
 * 功能描述：易购app导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
public interface GuideInfoService {

    /**
     * 查询门店导购
     *
     * @param storeGuide 查询门店导购请求
     * @return RsfResponseDto<StoreGuideRespDto>
     * @author 18041004_余长杰
     * @since 10:31 2018/8/18
     */
    RsfResponseDto<StoreGuideInfoDto> queryStoreGuide(StoreGuideReqDto storeGuide);

    /**
     * 查询我的客户经理
     *
     * @param storeGuide 查询门店导购请求
     * @return RsfResponseDto<StoreGuideRespDto>
     * @author 18041004_余长杰
     * @since 15:40 2018/9/5
     */
    RsfResponseDto<StoreGuideRespDto> queryCustManager(StoreGuideReqDto storeGuide);

    /**
     * 查询卡片页导购列表
     *
     * @param storeGuide
     * @return RsfResponseDto<List < StoreGuideInfoDto>>
     * @author 18041004_余长杰
     * @since 15:40 2018/9/5
     */
    RsfResponseDto<List<StoreGuideInfoDto>> queryGuideList(StoreGuideReqDto storeGuide);

    /**
     * 功能：查询店员主页信息
     *
     * @param guideId 导购工号
     * @author 18010645_黄成
     * @since 14:33 2018/9/3
     */
    /**
     * 功能：查询店员主页信息（店员详情、最近预约记录、v购视频）
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @param pageNo      页数
     * @param pageSize    条数
     * @param fromType    渠道
     * @author 18010645_黄成
     * @since 14:04 2018/9/13
     */
    RsfResponseDto<StaffPageDto> queryStaffPageInfo(String customerNum, String guideId, Integer fromType, Integer pageNo, Integer pageSize);

    /**
     * 查询省市编码
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @return RsfResponseDto<GuideRegionInfoListDto>
     * @author 18041004_余长杰
     * @since 11:45 2018/9/11
     */
    RsfResponseDto<GuideRegionInfoListDto> queryRegionListByParentCode(String parentRegionCode, String regionalLevel);

    /**
     * 查询当前省市编码
     *
     * @param regionCode    区域编码
     * @param regionalLevel 层级编码
     * @return RsfResponseDto<GuideRegionInfoDto>
     * @author 18041004_余长杰
     * @since 20:07 2018/9/11
     */
    RsfResponseDto<GuideRegionInfoDto> queryRegionInfoByRegionalCode(String regionCode, String regionalLevel);

}
