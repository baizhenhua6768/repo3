/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoRsfServiceImpl
 * Author:   18041004_余长杰
 * Date:     2018/8/16 14:11
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.vgo;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.dto.vgo.*;
import com.suning.sntk.rsf.vgo.GuideInfoRsfService;
import com.suning.sntk.service.vgo.GuideInfoService;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述：易购app导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
@Implement(contract = GuideInfoRsfService.class, implCode = "1.0.0")
@Service
public class GuideInfoRsfServiceImpl implements GuideInfoRsfService {

    @Autowired
    private GuideInfoService guideInfoService;

    @Autowired
    private BaoziConsumerService baoziConsumerService;

    @Override
    public RsfResponseDto<List<String>> queryGuideListFromMatch(List<String> storeCodeList, String category, String custNo) {
        return RsfResponseDto.of(baoziConsumerService.queryTopGuideIdList(storeCodeList, category, custNo));
    }

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
    @Override
    public RsfResponseDto<StaffPageDto> queryStaffPageInfos(String customerNum, String guideId, Integer fromType, Integer pageNo, Integer pageSize) {
        return RsfResponseDto.of(guideInfoService.queryGuideInfoAndVgoVideo(customerNum, guideId, fromType, pageNo, pageSize));
    }

    /**
     * 功能：pc获取门店所有V购人员及门店基本信息
     *
     * @param storeCode 门店编码
     * @param guideId   导购工号
     * @author 18010645_黄成
     * @since 11:39 2018/8/31
     */
    @Override
    public StoreServantRespDto getStoreInfo(String storeCode, String guideId) {
        //获取门店及所有导购信息
        return guideInfoService.queryStoreGuides(storeCode, guideId);
    }

    @Override
    public RsfResponseDto<StoreGuideInfoDto> queryStoreGuideForSmallRoutine(String storeCode, String custNo) {
        return RsfResponseDto.of(guideInfoService.queryAppletCustManager(storeCode, custNo));
    }

    @Override
    public RsfResponseDto<List<StoreGuideInfoDto>> queryGuideListForSmallRoutine(String storeCode, String custNo, String preGuideId) {
        return RsfResponseDto.of(guideInfoService.queryGuideListForSmallRoutine(storeCode, custNo, preGuideId));
    }

    @Override
    public RsfResponseDto<GuideRegionInfoListDto> queryRegionListByParentCode(String parentRegionCode, String regionalLevel) {
        return RsfResponseDto.of(guideInfoService.queryRegionListByParentCode(parentRegionCode, regionalLevel));
    }

    @Override
    public RsfResponseDto<GuideRegionInfoDto> queryRegionInfoByRegionalCode(String regionCode, String regionalLevel) {
        return RsfResponseDto.of(guideInfoService.queryRegionInfoByRegionalCode(regionCode, regionalLevel));
    }
}
