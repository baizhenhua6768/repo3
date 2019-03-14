/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ManagerPublicService
 * Author:   18032490_赵亚奇
 * Date:     2018/8/18 9:51
 * Description: 客户经理关系的建立与查询
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 * 赵亚奇                2018/8/23 9:25      2.2.1
 */

package com.suning.sntk.service.vgo;

import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.store.commons.rsf.RsfResponseDto;

import java.util.List;

/**
 * 客户经理关系的建立与查询
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/18
 */
public interface ManagerPublicService {

    /**
     * 查询客户经理关系(所有业态)
     *
     * @param custNo 会员编码
     * @author 18032490_赵亚奇
     * @since 10:23  2018/9/5
     */
    List<ManagerInfoDto> queryManagerList(String custNo);

    /**
     * 建立客户经理关系
     *
     * @param custNo    会员编码
     * @param staffId   员工工号
     * @param storeCode 门店编码
     * @param channel   渠道
     * @author 18032490_赵亚奇
     */
    RsfResponseDto buildManagerRelation(String custNo, String staffId, String storeCode, String channel);

    /**
     * 查询客户经理关系(区分业态)
     *
     * @param custNo  会员编码
     * @param staffId 员工编码
     * @author 18032490_赵亚奇
     */
    ManagerInfoDto queryManagerInfo(String custNo, String staffId);

    /**
     * 查询原有的客户经理
     *
     * @param custNo    会员编码
     * @param staffId   员工工号
     * @param storeCode 门店编码
     * @author 18032490_赵亚奇
     * @since 10:23  2018/9/5
     */
    List<GuideInfoDto> queryOldManager(String custNo, String staffId, String storeCode);

    /**
     * 功能：查询会员客户经理
     *
     * @param customerNo   会员编码
     * @param businessType 业态
     * @author 18010645_黄成
     * @since 16:14 2018/10/16
     */
    String queryManagerInfoNew(String customerNo, String businessType);
}
