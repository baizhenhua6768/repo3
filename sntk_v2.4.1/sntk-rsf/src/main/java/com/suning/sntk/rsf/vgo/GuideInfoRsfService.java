/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoRsfService
 * Author:   18041004_余长杰
 * Date:     2018/8/16 14:08
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.vgo;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.*;
import com.suning.store.commons.rsf.RsfResponseDto;

import java.util.List;

/**
 * 功能描述：易购app导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
@Contract(name = "guideInfoRsfService", description = "易购app查询导购相关接口")
public interface GuideInfoRsfService {

    /**
     * 从麦琪获取符合条件的导购信息（卡片页用）
     *
     * @param storeCodeList 门店列表（必填）
     * @param category      品类（非必填）
     * @param custNo        会员编号（非必填）
     * @return com.suning.store.commons.rsf.RsfResponseDto<java.util.List               <               java.lang.String>>
     * @since 19:39 2018/9/22
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "从麦琪获取符合条件的导购信息（卡片页用）")
    RsfResponseDto<List<String>> queryGuideListFromMatch(List<String> storeCodeList, String category, String custNo);


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
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询店员主页信息")
    RsfResponseDto<StaffPageDto> queryStaffPageInfos(String customerNum, String guideId, Integer fromType, Integer pageNo, Integer pageSize);

    /**
     * 功能：pc获取门店所有V购人员及门店基本信息
     *
     * @param storeCode 门店编码
     * @param guideId   导购工号
     * @author 18010645_黄成
     * @since 17:13 2018/8/30
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "获取门店V购人员及门店基本信息")
    StoreServantRespDto getStoreInfo(String storeCode, String guideId);

    /**
     * 微信小程序首页根据门店及登录用户匹配导购信息
     *
     * @param storeCode 门店编码
     * @param custNo    登录用户账号
     * @return 匹配到的店员信息
     * @author 18046191_王政
     * @since 16:30  2018/9/6
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "微信小程序首页匹配导购信息")
    RsfResponseDto<StoreGuideInfoDto> queryStoreGuideForSmallRoutine(String storeCode, String custNo);

    /**
     * 微信小程序查询店员卡片
     *
     * @param storeCode  门店编码
     * @param custNo     会员编码
     * @param preGuideId 上一页展示的导购id（用于将其展示在卡片页第一条记录）
     * @author 18046191_王政
     * @since 16:30  2018/9/6
     */
    @Method(idempotent = true, timeout = 5000, retryTimes = 3, description = "微信小程序查询店员卡片")
    RsfResponseDto<List<StoreGuideInfoDto>> queryGuideListForSmallRoutine(String storeCode, String custNo, String preGuideId);

    /**
     * 查询省市
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @return RsfResponseDto<GuideRegionInfoListDto>
     * @author 18041004_余长杰
     * @since 11:41 2018/9/11
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询省市")
    RsfResponseDto<GuideRegionInfoListDto> queryRegionListByParentCode(String parentRegionCode, String regionalLevel);

    /**
     * 查询当前省市
     *
     * @param regionCode    区域编码
     * @param regionalLevel 层级编码
     * @return RsfResponseDto<GuideRegionInfoDto>
     * @author 18041004_余长杰
     * @since 20:06 2018/9/11
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询当前省市")
    RsfResponseDto<GuideRegionInfoDto> queryRegionInfoByRegionalCode(String regionCode, String regionalLevel);


}
