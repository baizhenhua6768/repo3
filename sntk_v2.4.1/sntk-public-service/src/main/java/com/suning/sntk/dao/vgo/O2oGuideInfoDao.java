/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: O2oGuideInfoDao
 * Author:   88397670_张辉
 * Date:     2018-8-18 15:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import java.util.List;

import com.suning.sntk.entity.vgo.O2oGuideInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：中台- 导购人员信息服务
 *
 * @author 88397670_张辉
 * @since 2018-8-18
 */
@DalMapper(namespace = "o2o_guide_info")
public interface O2oGuideInfoDao extends DalBaseDao<O2oGuideInfo, Long> {

    /**
     * 根据导购工号查询记录id
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 10:11  2018-9-4
     */
    @DalParams({ "guideId" })
    Long selectIdByGuideId(String guideId);

    /**
     * 批量查询中台V购信息
     *
     * @param guideIds 导购工号集合
     * @author 88396455_白振华
     * @since 11:37  2018-9-5
     */
    @DalParams({ "guideIds" })
    List<O2oGuideInfo> queryGuideInfoList(List<String> guideIds);

    /**
     * 删除客户经理关系
     *
     * @param staffId 店员工号
     * @author 18032490_赵亚奇
     */
    @DalParams({ "staffId" })
    void deleteByStaffId(String staffId);

    /**
     * 根据店员编码查询会员
     *
     * @param staffId 店员工号
     * @author 18032490_赵亚奇
     */
    @DalParams({ "staffId" })
    List<String> selectRel(String staffId);
}
