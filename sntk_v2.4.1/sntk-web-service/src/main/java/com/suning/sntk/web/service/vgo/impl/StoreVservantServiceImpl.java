/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreVservantServiceImpl
 * Author:   18010645_黄成
 * Date:     2018/8/30 16:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo.impl;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.vgo.StoreServantRespDto;
import com.suning.sntk.rsf.vgo.GuideInfoRsfService;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.sntk.web.service.vgo.StoreVservantService;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 功能描述：pc获取门店所有V购人员及门店基本信息
 *
 * @author 18010645_黄成
 * @since 2018/8/30
 */
@Service
public class StoreVservantServiceImpl implements StoreVservantService {

    private GuideInfoRsfService guideInfoRsfService = ServiceLocator.getService(GuideInfoRsfService.class, "1.0.0");

    /**
     * 功能：PC四级页-获取门店所有V购人员及门店基本信息
     *
     * @param storeCode 门店编码
     * @param guideId   导购工号
     * @author 18010645_黄成
     * @since 14:58 2018/8/31
     */
    @Override
    public RsfResponseDto<StoreServantRespDto> getStoreVServants(String storeCode, String guideId) {
        Validators.ifInValid(StringUtils.isBlank(storeCode) || StringUtils.isBlank(guideId)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        return RsfResponseDto.of(guideInfoRsfService.getStoreInfo(storeCode, guideId));
    }
}
