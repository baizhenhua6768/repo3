/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DialogRsfService
 * Author:   18010645_黄成
 * Date:     2018/8/19 17:57
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.vgo;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.dto.vgo.StaffBeStoreRespDto;
import com.suning.sntk.dto.vgo.TipsReqDto;
import com.suning.sntk.dto.vgo.VgoDialogueTipDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：vgo会话卡片Rsf
 *
 * @author 18010645_黄成
 * @since 2018/8/19
 */
@Contract(name = "VgoDialogueRsfService", description = "会话预约卡片")
public interface VgoDialogueRsfService {

    /**
     * 功能：查询会员是否有客户经理
     *
     * @param custNo  会员编码
     * @param staffId 店员工号
     * @author 18010645_黄成
     * @since 15:20 2018/9/3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询用户是否有客户经理")
    RsfResponseDto<String> existsCustomerManager(String custNo, String staffId);

    /**
     * 功能：设置客户经理
     *
     * @param custNo  会员编码
     * @param staffId 店员工号
     * @author 18010645_黄成
     * @since 15:22 2018/9/3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "设置客户经理")
    RsfResponseDto<String> bindCustomerManager(String custNo, String staffId);

    /**
     * 功能：查询店员所属门店信息
     *
     * @param storeCode 门店编码
     * @author 18010645_黄成
     * @since 15:20 2018/9/3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询门店地址")
    RsfResponseDto<StaffBeStoreRespDto> queryStoreAddr(String storeCode);

    /**
     * 功能：查询云信对话模板
     *
     * @param tipsDto 提示语dto
     * @author 18010645_黄成
     * @since 15:21 2018/9/3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询云信对话模板")
    RsfResponseDto<String> queryDialogueTemplate(TipsReqDto tipsDto);

    /**
     * 功能：查询忙碌提示语（新）
     *
     * @param customerNo 会员编码
     * @author 18010645_黄成
     * @since 15:22 2018/9/3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询忙碌提示语")
    RsfResponseDto<VgoDialogueTipDto> queryBusyTemplate(String customerNo);

    /**
     * 功能：查询会员是否有客户经理（新）
     *
     * @param customerNo 会员编码
     * @param staffId    店员工号
     * @author 18010645_黄成
     * @since 15:20 2018/9/3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "查询用户是否有客户经理")
    RsfResponseDto<VgoDialogueTipDto> existsCustomerManagerNew(String customerNo, String staffId);

    /**
     * 功能：设置客户经理（新）
     *
     * @param customerNo 会员编码
     * @param staffId    店员工号
     * @author 18010645_黄成
     * @since 15:22 2018/9/3
     */
    @Method(idempotent = true, timeout = 3000, retryTimes = 3, description = "设置客户经理")
    RsfResponseDto<VgoDialogueTipDto> setCustomerManagerNew(String customerNo, String staffId);

}
