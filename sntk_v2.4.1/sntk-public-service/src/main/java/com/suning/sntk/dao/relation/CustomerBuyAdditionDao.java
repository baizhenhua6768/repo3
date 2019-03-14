/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerAdvisorDao
 * Author:   17061157_王薛
 * Date:     2018/7/7 19:53
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.relation;

import java.util.List;

import com.suning.sntk.dto.relation.CustomerLabelStringDto;
import com.suning.sntk.entity.relation.CustomerBuyAddition;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：购买意向附加信息
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@DalMapper("customerBuyAddition")
@Trace
public interface CustomerBuyAdditionDao extends DalBaseDao<CustomerBuyAddition, Long> {

    /**
     * 根据店员工号和unionId查询自定义标签
     *
     * @param staffId 工号
     * @param unionId 公众平台客户唯一编号
     * @return java.lang.String
     * @author 18041004_余长杰
     * @since 16:41 2018/7/10
     */
    @DalParams({ "staffId", "unionId" })
    CustomerBuyAddition querySelfLabel(String staffId, String unionId);

    /**
     * 根据店员工号和unionId查询自定义标签列表
     *
     * @param staffId 工号
     * @param unionIds 公众平台客户唯一编号
     * @return List<CustomerLabelStringDto>
     * @author 18041004_余长杰
     * @since 16:33 2018/7/16
     */
    @DalParams({ "staffId", "unionIds" })
    List<CustomerLabelStringDto> querySelfLabelList(String staffId, List<String> unionIds);

    /**
     * 删除客户购买意向附加信息
     *
     * @author 88395115_史小配
     * @param staffId 店员工号
     * @param unionId 公众平台客户唯一编号
     * @since 10:40 2018/7/12
     */
    @DalParams({"staffId","unionId"})
    void deleteBuyAddition(String staffId,String unionId);
}
