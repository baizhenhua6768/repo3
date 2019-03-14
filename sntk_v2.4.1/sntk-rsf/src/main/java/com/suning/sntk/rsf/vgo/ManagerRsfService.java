/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ManagerRsfService
 * Author:   18032490_赵亚奇
 * Date:     2018/8/20 19:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.vgo;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.store.commons.rsf.RsfResponseDto;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 客户经理模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/20
 */
@Contract(name = "managerRsfService", description = "客户经理相关服务接口")
public interface ManagerRsfService {

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询客户经理")
    RsfResponseDto<List<ManagerInfoDto>> queryManagerList(@NotBlank String custNo);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "建立客户经理关系")
    RsfResponseDto buildManagerRelation(@NotBlank String custNo, @NotBlank String staffId, @NotBlank String storeCode, @NotBlank String
            channel);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询客户经理详情")
    RsfResponseDto<ManagerInfoDto> queryManagerInfo(@NotBlank String custNo, @NotBlank String storeCode);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询客户原有的客户经理")
    RsfResponseDto<List<GuideInfoDto>> queryOldManager(@NotBlank String custNo, @NotBlank String staffId, @NotBlank String storeCode);

    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询会员客户经理")
    RsfResponseDto<String> queryManagerInfoNew(@NotBlank String customerNo, @NotBlank String businessType);

}