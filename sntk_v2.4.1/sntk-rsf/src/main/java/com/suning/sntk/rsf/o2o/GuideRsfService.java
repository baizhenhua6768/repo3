/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideRsfService.java
 * Author:   15050536
 * Date:     2018年3月28日 上午10:55:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.o2o;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.suning.rsf.provider.annotation.Contract;
import com.suning.rsf.provider.annotation.Method;
import com.suning.sntk.rsf.dto.o2o.GuideDetailDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideReqDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideRspDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 导购的rsf相关接口
 *
 * @author 15050536 石键平
 */
@Contract(name = "guideRsfService", description = "o2o导购项目，处理导购相关业务的rsf接口类")
public interface GuideRsfService {

	/**
	 * 查询我的专属导购
	 *
	 * @param custNo
	 *            含会员编码
	 * @param cityCode
	 *            城市编码
	 */
	@Method(idempotent = true, timeout = 3000, retryTimes = 0, description = "查询我的专属导购接口")
	RsfResponseDto<MyGuideRspDto> queryMyGuide(@NotBlank String custNo, @NotBlank String cityCode);

	/**
	 * 绑定专属导购
	 *
	 * @param myGuideReq
	 * @return
	 */
	@Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "绑定专属导购接口")
	RsfResponseDto<String> bindGuide(@NotNull @Valid MyGuideReqDto myGuideReq);

	/**
	 * 查询导购的详细信息
	 *
	 * @param staffId
	 *            导购工号
	 * @param custNo
	 *            会员编码
	 * @return
	 */
	@Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "查询导购详细信息接口")
	RsfResponseDto<GuideDetailDto> findGuideDetail(@NotBlank String staffId, @NotBlank String custNo);

	/**
	 * 更新专属导购
	 *
	 * @param custNo
	 *            会员编码
	 * @param newStaffId
	 *            新导购工号
	 * @param oldStaffId
	 *            原导购工号
	 * @return
	 */

	@Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "更新导购和顾客的关系接口")
	RsfResponseDto<String> modifyStaffCustRel(@NotBlank String custNo, @NotBlank String newStaffId, @NotBlank String oldStaffId);

	/**
	 * 扫码绑定，成为专属导购
	 */
	@Method(idempotent = false, timeout = 3000, retryTimes = 0, description = "绑定导购和顾客之间的关系")
	RsfResponseDto<String> scanBindGuide(@NotBlank String staffId, @NotBlank String channel, @NotBlank String custNo);

	/**
	 * 根据城市分页查询导购信息
	 */
	@Method(idempotent = true, timeout = 3000, retryTimes = 0, description = "根据城市分页查询导购信息")
	RsfResponseDto<MyGuideRspDto> queryGuidesByCity(@NotBlank String cityCode, @NotNull @Min(0) Integer page, @NotNull @Min(0) Integer size);
}
