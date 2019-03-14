/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoCategoryRsfServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/10/8 19:15
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.vgo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dto.vgo.CategoryOutRelDto;
import com.suning.sntk.rsf.vgo.VgoCategoryRsfService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.rsf.RsfResponseDto;
import com.suning.store.commons.rsf.advice.RsfAdvice;

/**
 * 功能描述：三级类目和品类关系
 *
 * @author 17061157_王薛
 * @since 2018/10/8
 */
@Implement(contract = VgoCategoryRsfService.class, implCode = "1.0.0")
@Service
@Trace
@RsfAdvice
public class VgoCategoryRsfServiceImpl implements VgoCategoryRsfService {

    @Autowired
    private GuideInfoDao guideInfoDao;

    /**
     * 功能描述: 查询所有三级目录对应的品类 <br>
     * @author 17061157_王薛
     * @param
     * @return RsfResponseDto<java.util.List<CategoryOutRelDto>>
     * @since 19:17  2018/10/8
     */
    @Override
    public RsfResponseDto<List<CategoryOutRelDto>> queryAllThreeDirectoryCategory() {
        return RsfResponseDto.of(guideInfoDao.queryAllThreeDirectoryCategory());
    }
}
