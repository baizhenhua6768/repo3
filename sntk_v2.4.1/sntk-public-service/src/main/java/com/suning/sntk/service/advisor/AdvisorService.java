/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorService
 * Author:   17061157_王薛
 * Date:     2018/7/7 17:18
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.service.advisor;

import java.util.List;

import com.suning.sntk.dto.advisor.CustomerListDto;
import com.suning.sntk.dto.advisor.CustomerQueryDto;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.entity.relation.WechatFans;
import com.suning.store.dal.service.DalService;

/**
 * 功能描述：专业顾问 service 类
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public interface AdvisorService extends DalService<CustomerAdvisor, Long> {

    /**
     * 查询顾客信息
     *
     * @param staffId   店员id
     * @param unionId   公众平台客户唯一编号
     * @param storeCode 门店编码
     * @author 88396455_白振华
     * @since 15:10  2018-7-10
     */
    CustomerAdvisor queryCustomer(String staffId, String unionId, String storeCode);

    /**
     * 查询客户列表
     *
     * @param queryParam 查询参数
     * @return
     */
    List<CustomerListDto> queryCustomerList(CustomerQueryDto queryParam);

    /**
     * 功能描述: 设置专业顾问表的客户经理标志 <br>
     *
     * @param customerNo
     * @param staffId
     * @author 17061157_王薛
     * @since 21:34  2018/7/10
     */
    void setManagerFlag(String customerNo, String staffId);

    /**
     * 建立粉丝和专属顾问关系
     *
     * @param customerAdvisor
     * @param wechatFans
     * @author 88396455_白振华
     * @since 16:54  2018-7-10
     */
    void buildFansAndAdvisorRelation(CustomerAdvisor customerAdvisor, WechatFans wechatFans);

    /**
     * 更新专属顾问最后扫码时间
     *
     * @param id           专属顾问关系id
     * @param lastScanTime 最后扫码时间
     * @author 88396455_白振华
     * @since 11:21  2018-7-11
     */
    void updateLastScanTimeSkipNull(Long id, String lastScanTime);

    /**
     * 创建专属顾问关系实体
     *
     * @param customerAdvisorReq 客户关系实体请求参数
     * @author 88396455_白振华
     * @since 11:46  2018-7-11
     */
    CustomerAdvisor createAdvisorEntity(CustomerAdvisor customerAdvisorReq);
}
