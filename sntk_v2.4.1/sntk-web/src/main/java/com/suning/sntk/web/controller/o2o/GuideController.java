/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideController.java
 * Author:   15050536
 * Date:     2018年3月27日 下午2:32:08
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.controller.o2o;

import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suning.sntk.rsf.dto.o2o.GuideDetailDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideReqDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideRspDto;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.web.service.o2o.GuideService;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 导购前台controller
 *
 * @author 15050536 石键平
 */
@RestController
@Validated
@RequestMapping("/o2o/guide/")
public class GuideController {

	@Autowired
	private GuideService guideService;

	/**
	 * 查询我的专属导购信息
	 */
	@GetMapping("querymyguides.do")
	public RsfResponseDto<MyGuideRspDto> queryMyGuides(@NotBlank String cityCode, Principal principal) {
		String custNo = principal.getName();
		return guideService.queryMyGuide(custNo, cityCode);
	}

	/**
	 * 绑定专属导购
	 */
	@RequestMapping("bind.do")
	public RsfResponseDto<String> bind(@Valid @NotNull MyGuideReqDto myGuideReq, Principal principal) {
		String custNo = principal.getName();
		myGuideReq.setCustNo(custNo);
		return guideService.bindGuide(myGuideReq);
	}

	/**
	 * 查询导购详细信息
	 */
	@RequestMapping("findguidedetail.do")
	public RsfResponseDto<GuideDetailDto> findGuideDetail(@NotNull String staffId, Principal principal) {
		String custNo = principal.getName();
		return guideService.findGuideDetail(staffId, custNo);
	}

	/**
	 * 更新专属导购
	 */
	@RequestMapping("changeguide.do")
	public RsfResponseDto<String> updateGuide(@NotBlank String oldStaffId, @NotBlank String newStaffId, Principal principal) {
		String custNo = principal.getName();
		return guideService.modifyGuide(custNo, oldStaffId, newStaffId);
	}

	/**
	 * 扫码绑定专属导购
	 */
	@RequestMapping("scanqrcode.do")
	public RsfResponseDto<String> scanBindGuide(@NotBlank String staffId, @RequestParam(defaultValue = O2OConstants.CHANNEL_OFFLINE) String channel, Principal principal) {
		String custNo = principal.getName();
		return guideService.scanBindGuide(staffId, channel, custNo);
	}

	/**
	 * 根据城市编码查询导购信息
	 */
	@RequestMapping("queryguidesbycity.do")
	public RsfResponseDto<MyGuideRspDto> queryGuidesByCity(@NotBlank String cityCode, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		return guideService.queryGuidesByCity(cityCode, pageNo, pageSize);
	}
}
