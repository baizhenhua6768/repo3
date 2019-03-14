/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoCategoryRsfService
 * Author:   17061157_王薛
 * Date:     2018/10/8 19:09
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.rsf.vgo;

import java.util.List;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.CategoryOutRelDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：三级类目和品类关系
 *
 * @author 17061157_王薛
 * @since 2018/10/8
 */
@Contract(name = "vgoCategoryRsfService", description = "三级类目和品类关系服务")
public interface VgoCategoryRsfService {

    /**
     * 功能描述: 查询所有三级目录对应的品类 <br>
     * @author 17061157_王薛
     * @param
     * @return RsfResponseDto<java.util.List<CategoryOutRelDto>>
     * @since 19:13  2018/10/8
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询所有三级目录对应的品类")
    RsfResponseDto<List<CategoryOutRelDto>> queryAllThreeDirectoryCategory();
}
