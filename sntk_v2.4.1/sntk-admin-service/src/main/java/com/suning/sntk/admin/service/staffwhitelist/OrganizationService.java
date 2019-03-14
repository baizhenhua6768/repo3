/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: OrganizationService
 * Author:   18032490_赵亚奇
 * Date:     2018/7/3 10:30
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.staffwhitelist;

import java.util.List;

import com.suning.sntk.admin.vo.CompanyVo;
import com.suning.sntk.admin.vo.RegionVo;
import com.suning.sntk.admin.vo.StoreVo;

/**
 * 查询组织信息
 *
 * @author 18032490
 * @since 2018/7/3
 */
public interface OrganizationService {

    /**
     * 查询所有大区列表
     */
    List<RegionVo> queryRegionList();

    /**
     * 查询指定大区下的分公司列表
     *
     * @param regionCode
     * @return
     */
    List<CompanyVo> queryBranchList(String regionCode);

    /**
     * 查询指定分公司下门店列表
     * @param compCode
     * @return
     */
    List<StoreVo> queryStoreList(String compCode);

}
