/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryRelAdminDao
 * Author:   88396455_白振华
 * Date:     2018-8-20 15:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.dao.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.entity.vgo.CategoryRelInfo;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：人员擅长品类数据库操作类
 *
 * @author 88396455_白振华
 * @since 2018-8-20
 */
@DalMapper("categoryRel_admin")
public interface CategoryRelAdminDao extends DalBaseDao<CategoryRelInfo, Long> {

    /**
     * 根据导购工号查询擅长品类
     *
     * @param guideId      导购工号
     * @author 88396455_白振华
     * @since 15:54  2018-8-20
     */
    @DalParams({ "guideId" })
    List<VcategoryRelInfoDto> queryByGuideId(String guideId);

    /**
     * 根据导购工号删除擅长品类
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 9:09  2018-9-4
     */
    @DalParams({ "guideId" })
    void deleteByGuideId(String guideId);

    /**
     * 根据导购工号查询擅长品类编码
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 10:53  2018-9-7
     */
    @DalParams({ "guideId" })
    List<String> queryGuideCategoryCode(String guideId);
}
