/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreVServantController
 * Author:   18010645_黄成
 * Date:     2018/8/30 16:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.controller.vgo;

import com.suning.sntk.dto.vgo.StoreServantRespDto;
import com.suning.sntk.web.service.vgo.StoreVservantService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述：PC四级页V购-门店V购<br>
 *
 * @author 18010645_黄成
 * @since 2018/8/30
 */
@Controller
@Trace
@RequestMapping("/pc")
public class StoreVServantController {

    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreVServantController.class);

    @Autowired
    private StoreVservantService storeVservantService;

    /**
     * 功能：pc获取门店所有V购人员及门店基本信息
     *
     * @param storeCode 门店编码
     * @author 18010645_黄成
     * @since 14:56 2018/8/31
     */
    @RequestMapping("/getStoreVServants.htm")
    @ResponseBody
    public RsfResponseDto<StoreServantRespDto> getStoreVServants(String storeCode, String guideId) {
        LOGGER.info("pc端获取门店所有V购人员及门店基本信息，storeCode:{},guideId:{}", storeCode, guideId);
        // 获取数据
        return storeVservantService.getStoreVServants(storeCode, guideId);
    }


}
