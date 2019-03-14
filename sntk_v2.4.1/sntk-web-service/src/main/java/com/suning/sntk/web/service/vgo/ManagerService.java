/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ManagerService
 * Author:   18032490_赵亚奇
 * Date:     2018/8/20 19:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo;

import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.store.commons.rsf.RsfResponseDto;

import java.util.List;

/**
 * 客户经理模块
 *
 * @author 18032490_赵亚奇
 * @since 2018/8/20
 */
public interface ManagerService {

    RsfResponseDto<List<ManagerInfoDto>> queryManagerList(String custNo);

    RsfResponseDto buildManagerRelation(String custNo, String staffId, String storeCode, String channel);

    RsfResponseDto<ManagerInfoDto> queryManagerInfo(String custNo, String storeCode);

    RsfResponseDto<List<GuideInfoDto>> queryOldManager(String custNo, String staffId, String storeCode);

    RsfResponseDto<String> queryManagerInfoNew(String customerNo, String businessType);
}

