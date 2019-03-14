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

import com.suning.sntk.dto.advisor.CustomerLabelDto;
import com.suning.sntk.dto.advisor.RequiredLabelTypeDto;
import com.suning.sntk.entity.relation.CustomerLabelRelation;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：客户标签关联信息Dao
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@DalMapper("customerLabelRelation")
public interface CustomerLabelRelationDao extends DalBaseDao<CustomerLabelRelation, Long> {

    /**
     * 查询必选标签
     *
     * @return List<CustomerLabelDto>
     * @author 18041004_余长杰
     * @since 16:24 2018/7/10
     */
    @DalParams({ "staffId", "unionId" })
    List<CustomerLabelDto> queryRequiredLabelList(String staffId, String unionId);

    /**
     * 查询必选标签类型
     *
     * @return List<RequiredLabelTypeDto>
     * @author 18041004_余长杰
     * @since 20:39 2018/7/12
     */
    @DalParams({ "staffId", "unionId" })
    List<RequiredLabelTypeDto> queryRequiredLabelTypeList(String staffId, String unionId);

    /**
     * 查询其他标签
     *
     * @return List<CustomerLabelDto>
     * @author 18041004_余长杰
     * @since 16:31 2018/7/10
     */
    @DalParams({ "staffId", "unionId" })
    List<CustomerLabelDto> queryOtherLabelList(String staffId, String unionId);

    /**
     * 删除数据库中已存在的专属标签
     *
     * @author 88395115_史小配
     * @param unionId 公众平台客户唯一编号
     * @param staffId 店员工号
     * @since 16:15 2018/7/10
     */
    @DalParams({"unionId","staffId"})
    int deleteLabelRelation(String unionId,String staffId);


    /**
     * 功能描述： 查询客户的公共标签字符串
     *
     * @param unionId 客户公众号平台唯一编号
     * @return String
     * @author 88402362 欧小冬
     * @since 9:52 2018/7/11
     */
    @DalParams("unionId")
    String queryCustomerLabelInfo(String unionId);

    /**
     * 查询客户标签
     *
     * @param staffId 店员工号
     * @param unionId 公众平台客户唯一编号
     * @return List<String>
     * @author 18041004_余长杰
     * @since 19:58 2018/7/11
     */
    @DalParams({ "staffId", "unionId" })
    List<String> queryCustomerLabelList(String staffId, String unionId);

}
