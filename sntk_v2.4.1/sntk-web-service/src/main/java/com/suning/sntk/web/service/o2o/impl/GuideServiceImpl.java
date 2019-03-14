/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideServiceImpl.java
 * Author:   15050536
 * Date:     2018年3月28日 上午10:53:05
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.service.o2o.impl;

import org.springframework.stereotype.Service;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.rsf.dto.o2o.GuideDetailDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideReqDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideRspDto;
import com.suning.sntk.rsf.o2o.GuideRsfService;
import com.suning.sntk.web.service.o2o.GuideService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 导购业务处理的Service
 *
 * @author 15050536 石键平
 */
@Service
@Trace
public class GuideServiceImpl implements GuideService {

	private GuideRsfService guideRsfService = ServiceLocator.getService(GuideRsfService.class, "1.0.0");

	@Override
	public RsfResponseDto<MyGuideRspDto> queryMyGuide(String custNo, String cityCode) {
		return guideRsfService.queryMyGuide(custNo, cityCode);
	}

	@Override
	public RsfResponseDto<String> bindGuide(MyGuideReqDto myGuideReq) {
		return guideRsfService.bindGuide(myGuideReq);
	}

	@Override
	public RsfResponseDto<GuideDetailDto> findGuideDetail(String staffId, String custNo) {
		return guideRsfService.findGuideDetail(staffId, custNo);
	}

	@Override
	public RsfResponseDto<MyGuideRspDto> queryGuidesByCity(String cityCode, Integer page, Integer size) {
		return guideRsfService.queryGuidesByCity(cityCode, page, size);

	}

	@Override
	public RsfResponseDto<String> modifyGuide(String custNo, String oldStaffId, String newStaffId) {
		return guideRsfService.modifyStaffCustRel(custNo, newStaffId, oldStaffId);
	}

	@Override
	public RsfResponseDto<String> scanBindGuide(String staffId, String channel, String custNo) {
		return guideRsfService.scanBindGuide(staffId, channel, custNo);
	}

}
