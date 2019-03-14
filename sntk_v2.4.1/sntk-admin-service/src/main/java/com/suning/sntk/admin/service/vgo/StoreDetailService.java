/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreDetailService
 * Author:   88396455_白振华
 * Date:     2018-8-31 14:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo;

import java.util.List;

import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.dto.vgo.OrgInfoDto;

/**
 * 功能描述：门店详情服务
 *
 * @author 88396455_白振华
 * @since 2018-8-31
 */
public interface StoreDetailService {

    /**
     * 查询当前登录人可以查看的大区列表
     *
     * @param loginUser 人员信息
     * @author 88396455_白振华
     * @since 14:24  2018-8-31
     */
    List<OrgInfoDto> queryRegionInfoList(EmployeeInfo loginUser);

    /**
     * 查询大区下的分公司列表
     *
     * @param regionId  大区编码
     * @param loginUser 当前登陆人
     * @author 88396455_白振华
     * @since 15:24  2018-8-31
     */
    List<OrgInfoDto> queryCompanyInfoList(String regionId, EmployeeInfo loginUser);

    /**
     * 查询分公司下的商店列表
     *
     * @param orgId     分公司编码
     * @param loginUser 当前登陆人
     * @author 88396455_白振华
     * @since 15:37  2018-8-31
     */
    List<OrgInfoDto> queryStoreInfoList(String orgId, EmployeeInfo loginUser);
}
