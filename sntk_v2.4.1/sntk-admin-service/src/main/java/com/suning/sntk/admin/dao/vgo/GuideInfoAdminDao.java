/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoAdminDao
 * Author:   88396455_白振华
 * Date:     2018-8-18 9:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.dto.vgo.GuideInfoRespDto;
import com.suning.sntk.dto.vgo.PositionDto;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMethod;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.annotation.DalPrimitiveParam;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：导购信息数据库操作类
 *
 * @author 88396455_白振华
 * @since 2018-8-18
 */
@DalMapper("guide_info_admin")
public interface GuideInfoAdminDao extends DalBaseDao<GuideInfo, Long> {

    /**
     * 根据工号查询导购数量
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 9:55  2018-8-18
     */
    @DalParams({ "guideId" })
    long countByGuideId(String guideId);

    /**
     * 根据导购人员工号查询导购详细信息
     *
     * @param guideId 导购人员工号
     * @author 88397670_张辉
     * @since 10:55 2018-8-21
     */
    GuideInfo getGuideInfo(@DalParam("guideId") String guideId);

    /**
     * 根据工号查询导购信息
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 15:23  2018-8-20
     */
    @DalParams({ "guideId" })
    GuideInfoDto queryByGuideId(String guideId);

    /**
     * 通过导购员工号集合查询导购信息
     *
     * @param guideIdList 导购员工号集合
     * @author 88397670_张辉
     * @since 14:32 2018-8-22
     */
    List<GuideInfo> queryGuideInfoList(@DalPrimitiveParam @DalParam("guideIdList") List<String> guideIdList);

    /**
     * 根据条件分页查询导购信息
     *
     * @param guideInfoReqDto 导购查询条件
     * @param pageable        分页信息
     * @author 88396455_白振华
     * @since 16:38  2018-8-17
     */
    @DalParams({ "guideInfoReqDto" })
    Page<GuideInfoRespDto> queryGuideInfoForPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable);

    /**
     * 根据条件查询导购信息数量
     *
     * @param guideInfoReqDto 查询导购信息条件
     * @author 88396455_白振华
     * @since 15:41  2018-8-17
     */
    @DalMethod(name = "queryGuideInfoForPage#count", params = { "guideInfoReqDto" })
    Long queryGuideInfoCount(GuideInfoReqDto guideInfoReqDto);

    /**
     * 根据条件分页查询导购信息
     *
     * @param guideInfoReqDto 导购查询条件
     * @param pageable        分页信息
     * @author 88396455_白振华
     * @since 16:38  2018-8-17
     */
    @DalParams({ "guideInfoReqDto" })
    Page<GuideInfoRespDto> queryGuideInfoPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable);

    /**
     * 获取待处理图片信息
     *
     * @author 88396455_白振华
     * @since 10:53  2018-9-13
     */
    List<GuideInfo> obtainToBeSynPicUrl();

    /**
     * 根据门店编码获取经纬度
     *
     * @param storeCode 门店编码
     * @author 18032490_赵亚奇
     */
    @DalParams({ "storeCode" })
    PositionDto findStoreInfo(String storeCode);
}
