/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: DialogService
 * Author:   18010645_黄成
 * Date:     2018/8/21 9:07
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.service.vgo;

import com.suning.sntk.dto.vgo.StaffBeStoreRespDto;
import com.suning.sntk.dto.vgo.TipsReqDto;
import com.suning.sntk.dto.vgo.VgoDialogueTipDto;

/**
 * 功能描述：云信会话相关接口
 *
 * @author 18010645_黄成
 * @since 2018/8/21
 */
public interface VgoDialogueService {

    /**
     * 功能：店家查询门店地址
     *
     * @param storeCode 门店编码
     * @author 18010645_黄成
     * @since 14:27 2018/8/18
     */
    StaffBeStoreRespDto getStoreInfo(String storeCode);

    /**
     * 功能：提示语信息（云信）
     *
     * @param tipsReqDto 提示语请求体
     * @author 18010645_黄成
     * @since 15:57 2018/8/17
     */
    String queryTipsInfo(TipsReqDto tipsReqDto);

    /**
     * 功能：设置客户经理（云信）
     *
     * @param customerNo 会员编码
     * @param staffId    导购工号
     * @author 18010645_黄成
     * @since 12:36 2018/10/11
     */
    VgoDialogueTipDto setCustomerManagerNew(String customerNo, String staffId);

    /**
     * 功能：会员有无客户经理（云信）
     *
     * @param customerNo 会员编码
     * @param staffId    导购工号
     * @author 18010645_黄成
     * @since 12:36 2018/10/11
     */
    VgoDialogueTipDto isCustomerManagerNew(String customerNo, String staffId);

    /**
     * 功能：忙碌时提示语模板（云信）
     *
     * @param customerNo 会员编码
     * @author 18010645_黄成
     * @since 12:51 2018/10/11
     */
    VgoDialogueTipDto queryBusyTemplate(String customerNo);
}
