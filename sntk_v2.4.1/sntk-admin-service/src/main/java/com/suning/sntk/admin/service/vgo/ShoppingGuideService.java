/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ShoppingGuideService
 * Author:   88396455_白振华
 * Date:     2018-8-17 15:37
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo;

import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.dto.vgo.GuideInfoRespDto;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：导购管理服务
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
public interface ShoppingGuideService {

    /**
     * 根据条件分页查询导购信息
     *
     * @param guideInfoReqDto 查询导购信息条件
     * @param pageable        分页信息
     * @author 88396455_白振华
     * @since 15:41  2018-8-17
     */
    Page<GuideInfoRespDto> queryGuideInfoForPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable);

    /**
     * 根据导购工号查询导购详细信息
     *
     * @param guideId      导购工号
     * @param businessType 业态
     * @author 88396455_白振华
     * @since 15:06  2018-8-20
     */
    GuideInfoDto queryGuideDetail(String guideId, String businessType);

    /**
     * 删除导购信息
     *
     * @param guideId      导购工号
     * @param staffId      操作人工号
     * @param businessType 业态
     * @param storeCode    门店编码
     * @author 18070876
     */
    Boolean deleteGuide(String guideId, String staffId, String storeCode, String businessType);

    /**
     * 根据条件查询导购信息数量
     *
     * @param guideInfoReqDto 查询导购信息条件
     * @author 88396455_白振华
     * @since 15:41  2018-8-17
     */
    Long queryGuideInfoCount(GuideInfoReqDto guideInfoReqDto);

    /**
     * 根据业态查询导购总数
     *
     * @param guideInfoReqDto 查询导购信息条件
     * @author 88396455_白振华
     * @since 8:56  2018-9-6
     */
    Long countGuideByBusinessType(GuideInfoReqDto guideInfoReqDto);

    /**
     * 根据条件分页查询导购信息(不含分公司名称)
     *
     * @param guideInfoReqDto 查询导购信息条件
     * @param pageable        分页信息
     * @author 88396455_白振华
     * @since 15:41  2018-8-17
     */
    Page<GuideInfoRespDto> queryGuideInfoPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable);
}
