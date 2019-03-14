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

import com.suning.sntk.dto.advisor.CustomerListDto;
import com.suning.sntk.dto.advisor.CustomerQueryDto;
import com.suning.sntk.dto.relation.CustomerLabelStringDto;
import com.suning.sntk.dto.relation.CustomerPurchasePriceDto;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParam;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：专业顾问Dao
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@DalMapper("customerAdvisor")
public interface CustomerAdvisorDao extends DalBaseDao<CustomerAdvisor, Long> {

    /**
     * 查询顾客信息
     *
     * @param staffId   店员id
     * @param unionId   公众平台客户唯一编号
     * @param storeCode 门店编码
     * @author 88396455_白振华
     * @since 15:10  2018-7-10
     */
    @DalParams({ "staffId", "unionId", "storeCode" })
    CustomerAdvisor queryCustomerInfo(String staffId, String unionId, String storeCode);

    /**
     * 根据店员工号和unionId查询客户信息
     *
     * @param staffId 店员工号
     * @param unionId 公众平台客户唯一编号
     * @author 88395115_史小配
     * @since 15:36 2018/7/10
     */
    @DalParams({ "staffId", "unionId" })
    CustomerAdvisor queryCustomer(String staffId, String unionId);

    /**
     * 功能描述: 查询客户列表 <br>
     *
     * @param queryParam
     * @return java.util.List<com.suning.sntk.dto.advisor.CustomerListDto>
     * @author 17061157_王薛
     * @since 15:46  2018/7/12
     */
    @DalParams({ "queryParam" })
    List<CustomerListDto> queryCustomerList(CustomerQueryDto queryParam);

    @DalParams({ "staffId", "unionIds" })
    List<CustomerLabelStringDto> queryCustomersLabel(String staffId, List<String> unionIds);

    @DalParams({ "staffId", "unionIds" })
    List<CustomerPurchasePriceDto> queryCustomersPurchase(String staffId, List<String> unionIds);

    /**
     * 功能描述： 根据店员工号、客户名称、时间分页查询数据
     *
     * @param staffId     店员工号
     * @param searchParam 客户昵称
     * @param isToday     是否是当天
     * @param buyTime     预计购买时间
     */
    @DalParams({ "staffId", "searchParam", "isToday", "buyTime" })
    Page<CustomerListDto> queryByStaffIdWithBuyTimePage(String staffId, String searchParam, boolean isToday,
            String buyTime, Pageable pageable);

    /**
     * 功能描述： 根据店员工号、客户名称分页查询数据
     *
     * @param staffId     店员工号
     * @param searchParam 客户昵称
     */
    @DalParams({ "staffId", "searchParam" })
    Page<CustomerListDto> queryByStaffIdPage(String staffId, String searchParam, Pageable pageable);

    /**
     * 功能描述:  <br>
     *
     * @param customerNo
     * @param staffId
     * @return void
     * @author 17061157_王薛
     * @since 21:38  2018/7/10
     */
    @DalParams({ "customerNo", "staffId" })
    void setManagerFlag(String customerNo, String staffId);

    /**
     * 更新专属顾问最后扫码时间
     *
     * @param id           专属顾问关系id
     * @param lastScanTime 最后扫码时间
     * @author 88396455_白振华
     * @since 11:21  2018-7-11
     */
    @DalParams({ "id", "lastScanTime" })
    void updateLastScanTimeSkipNull(Long id, String lastScanTime);

    /**
     * 更新客户详细信息
     *
     * @param customerAdvisor 客户信息
     * @author 88395115_史小配
     * @since 18:59 2018/7/16
     */
    void updateCustomer(@DalParam("advisor") CustomerAdvisor customerAdvisor);

    /**
     * 查询备注名为空的顾客列表
     *
     * @param queryParam 查询条件
     * @author 88395115_史小配
     * @since 10:03 2018/7/20
     */
    @DalParams({ "queryParam" })
    List<CustomerListDto> queryCustomerListDefaultName(CustomerQueryDto queryParam);

    /**
     * 查询是否存在专属顾问关系
     *
     * @param custNo
     * @param staffId
     * @author 18032490_赵亚奇
     * @since 16:16  2018/8/31
     */
    @DalParams({ "custNo", "staffId" })
    CustomerAdvisor queryInfoByStaffAndCustNo(String custNo, String staffId);
}
