/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MyInfoRsfServiceImpl
 * Author:   88397670_张辉
 * Date:     2018-8-31 10:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.vgo;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dto.vgo.CategoryDto;
import com.suning.sntk.dto.vgo.ClerkCheckResDto;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ServiceItemDto;
import com.suning.sntk.rsf.util.ScmRsfConfigUtil;
import com.suning.sntk.rsf.vgo.ClerkRsfService;
import com.suning.sntk.service.vgo.CategoryService;
import com.suning.sntk.service.vgo.VgoGuideService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-8-31
 */
@Implement(contract = ClerkRsfService.class, implCode = "1.0.0")
@Service
@Trace
public class ClerkRsfServiceImpl implements ClerkRsfService {

    /**
     * 个签模板scm配置key
     */
    private static final String SIGNATURE_TEMPLATE = "SIGNATURE_TEMPLATE";

    public static final Logger LOGGER = LoggerFactory.getLogger(ClerkRsfServiceImpl.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VgoGuideService vgoGuideService;

    @Override
    public RsfResponseDto<List<CategoryDto>> queryCategoryList() {
        return RsfResponseDto.of(categoryService.queryCategoryList());
    }

    @Override
    public RsfResponseDto<List<ServiceItemDto>> queryServiceItemList() {
        List<ServiceItemDto> itemDtos = categoryService.queryServiceItemList();
        if (CollectionUtils.isNotEmpty(itemDtos)) {
            // 处理务项目 数据库中有部分老数据 存的是：图片,服务项目。需要对查询结果进行切割
            for (ServiceItemDto itemDto : itemDtos) {
                if (itemDto.getItemVal().contains(",")) {
                    String[] serverItemArr = itemDto.getItemVal().split(",");
                    itemDto.setItemVal(serverItemArr[serverItemArr.length - 1]);
                }
            }
        }
        return RsfResponseDto.of(itemDtos);
    }

    @Override
    public RsfResponseDto<List<String>> querySignature() {
        return RsfResponseDto.of(Arrays.asList(ScmRsfConfigUtil.getConfig(SIGNATURE_TEMPLATE).split("#")));
    }

    @Override
    public RsfResponseDto modifyGuideAuditInfo(GuideInfoDto guideInfoDto) {
        LOGGER.info("ClerkRsfServiceImpl.modifyGuideAuditInfo param guideInfoDto:{}", guideInfoDto);
        if (guideInfoDto.getIsVgo() == null) {//若店员详情的v购标识为空则表示该店员不是v购导购
            guideInfoDto.setIsVgo(VgoConstants.IS_NOT_VGO);
        }
        vgoGuideService.modifyAuditGuideInfo(guideInfoDto);
        return RsfResponseDto.success();
    }

    @Override
    public RsfResponseDto<GuideInfoDto> queryGuideDetail(String guideId, String type) {
        return RsfResponseDto.of(vgoGuideService.queryGuideDetail(guideId, type));
    }

    @Override
    public RsfResponseDto<ClerkCheckResDto> checkClerkInfoIntegrity(String guideId) {
        return RsfResponseDto.of(vgoGuideService.checkClerkInfo(guideId));
    }

    @Override
    public RsfResponseDto<GuideInfoDto> queryClerkMarkInfo(String guideId) {
        return RsfResponseDto.of(vgoGuideService.queryClerkMarkInfo(guideId));
    }
}
