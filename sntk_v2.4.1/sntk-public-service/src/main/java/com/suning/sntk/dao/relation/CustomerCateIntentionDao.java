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

import org.hibernate.validator.constraints.NotBlank;

import com.suning.sntk.dto.advisor.CustomerBrandDto;
import com.suning.sntk.entity.relation.CustomerCateIntention;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：顾客购买意向品类信息Dao
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@DalMapper("customerCateIntention")
public interface CustomerCateIntentionDao extends DalBaseDao<CustomerCateIntention, Long> {

    /**
     * 根据店员工号和unionId 删除顾客购买意向品类信息
     *
     * @author 88395115_史小配
     * @param staffId 店员工号
	 * @param unionId 客户微信公众号唯一编号
     * @since 16:59 2018/7/10
     */
    @DalParams({"staffId","unionId"})
    int deleteCateIntention(@NotBlank String staffId,@NotBlank String unionId);
    
    /**
     * 查询顾客购买意向品类信息
     * @author 88395115_史小配
     * @param staffId 店员工号
	 * @param unionId 客户微信公众号唯一编号
     * @since 18:03 2018/7/10
     */
    @DalParams({"staffId","unionId"})
    List<CustomerBrandDto> queryCateIntention(String staffId, String unionId);
}
