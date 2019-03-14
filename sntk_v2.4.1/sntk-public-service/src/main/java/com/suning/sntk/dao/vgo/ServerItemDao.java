/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ServerItemDao
 * Author:   88396455_白振华
 * Date:     2018-9-3 15:34
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import java.util.List;

import com.suning.sntk.dto.vgo.ServiceItemDto;
import com.suning.sntk.entity.vgo.ReservationGuide;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：服务项目数据库操作
 *
 * @author 88396455_白振华
 * @since 2018-9-3
 */
@DalMapper(namespace = "o2oServerItem")
public interface ServerItemDao extends DalBaseDao<ReservationGuide, Long> {

    /**
     * 查询导购服务项目
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 15:44  2018-9-3
     */
    @DalParams({ "guideId" })
    List<String> queryByGuideId(String guideId);

    /**
     * 根据导购工号删除对应的服务项目
     *
     * @param guideId 导购工号
     * @author 88396455_白振华
     * @since 9:43  2018-9-4
     */
    @DalParams({ "guideId" })
    void deleteByGuideId(String guideId);

    /**
     * 查询母婴服务项目列表
     *
     * @author 88397670_张辉
     * @since 17:10 2018-9-3
     */
    List<ServiceItemDto> queryServiceItemList();
}
