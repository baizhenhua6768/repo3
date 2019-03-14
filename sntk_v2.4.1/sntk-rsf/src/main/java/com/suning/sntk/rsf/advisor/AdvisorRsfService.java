/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorRsfService
 * Author:   17061157_王薛
 * Date:     2018/7/7 20:02
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.rsf.advisor;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.advisor.CustomerDetailDto;
import com.suning.sntk.dto.advisor.CustomerDetailEditDto;
import com.suning.sntk.dto.advisor.CustomerListDto;
import com.suning.sntk.dto.advisor.CustomerManagerDto;
import com.suning.sntk.dto.advisor.CustomerQueryDto;
import com.suning.sntk.dto.advisor.ShopperDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：专业顾问 rsf 接口
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@Contract(name = "advisorRsfService", description = "微信吸粉建立的专业顾问关系相关服务接口")
public interface AdvisorRsfService {

    /**
     * 功能描述: 查询店员信息 <br>
     *
     * @param staffId    店员工号
     * @param storeCode  门店编码
     * @param customerNo 客户会员编码
     * @return com.suning.store.commons.rsf.RsfResponseDto<java.lang.Object>
     * @author 17061157_王薛
     * @since 20:16  2018/7/7
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询扫码关联的店员信息接口")
    RsfResponseDto<ShopperDto> queryShopperInfo(@NotBlank String staffId, @NotBlank String storeCode, String customerNo);

    /**
     * 功能描述: 分页查询客户列表 <br>
     *
     * @param queryParam 查询参数
     * @return com.suning.store.commons.rsf.RsfResponseDto<CustomerBaseDto>
     * @author 17061157_王薛
     * @since 20:31  2018/7/7
     */
    @Method(idempotent = true, timeout = 5000, retryTimes = 3, description = "分页查询客户列表接口")
    RsfResponseDto<List<CustomerListDto>> queryCustomerList(CustomerQueryDto queryParam);

    /**
     * 功能描述: 设置客户经理 <br>
     *
     * @param customerManagerDto 客户经理关系
     * @return com.suning.store.commons.rsf.RsfResponseDto<java.lang.String>
     * @author 17061157_王薛
     * @since 19:54  2018/7/10
     */
    @Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "设置客户经理")
    RsfResponseDto<Void> bindCustomerManager(CustomerManagerDto customerManagerDto);

    /**
     * 查询客户编辑资料
     *
     * @param staffId 店员工号
     * @param unionId 公众号用户唯一标识
     * @return RsfResponseDto<CustomerDetailEditDto>
     * @author 18041004_余长杰
     * @since 9:40 2018/7/11
     */
    @Method(idempotent = true, timeout = 5000, retryTimes = 3, description = "查询客户编辑资料")
    RsfResponseDto<CustomerDetailEditDto> queryCustomerEditInfo(@NotBlank String staffId, @NotBlank String unionId);

    /**
     * 功能描述: 更新客户详细信息 <br>
     *
     * @param detailDto 客户详细信息
     * @return com.suning.store.commons.rsf.RsfResponseDto<Boolean>
     * @author 17061157_王薛
     * @since 20:38  2018/7/7
     */
    @Method(idempotent = false, timeout = 5000, retryTimes = 0, description = "更新客户详细信息接口")
    RsfResponseDto<Boolean> updateCustomerDetail(@Valid @NotNull CustomerDetailDto detailDto);
}
