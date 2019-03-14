/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideService.java
 * Author:   15050536
 * Date:     2018年3月28日 上午10:52:07
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.web.service.o2o;

import com.suning.sntk.rsf.dto.o2o.GuideDetailDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideReqDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideRspDto;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 导购业务处理的service
 *
 * @author 15050536 石键平
 */
public interface GuideService {

	/**
	 * 查询我的专属导购
	 *
	 * @param custNo
	 *            会员编码
	 * @param cityCode
	 *            城市编码
	 * @return
	 */
	public RsfResponseDto<MyGuideRspDto> queryMyGuide(String custNo, String cityCode);

	/**
	 * 绑定专属导购
	 *
	 * @param myGuideReq
	 *            含会员编码、城市编码、业态类型、品类编码、门店列表
	 * @return
	 */
	public RsfResponseDto<String> bindGuide(MyGuideReqDto myGuideReq);

	/**
	 * 查询导购详细信息
	 *
	 * @param staffId
	 *            导购工号
	 * @param custNo
	 *            会员编码
	 * @return
	 */
	public RsfResponseDto<GuideDetailDto> findGuideDetail(String staffId, String custNo);

	/**
	 * 
	 * 根据城市分页查询导购信息
	 * 
	 * @param cityCode
	 * @param page
	 * @param size
	 * @return
	 * @author 17121439 朱稳俊
	 */
	public RsfResponseDto<MyGuideRspDto> queryGuidesByCity(String cityCode, Integer page, Integer size);

	/**
	 * 更新专属导购
	 * 
	 * @param custNo
	 * @param oldStaffId
	 * @param newStaffId
	 * @author 17121439 朱稳俊
	 */
	public RsfResponseDto<String> modifyGuide(String custNo, String oldStaffId, String newStaffId);

	/**
	 * 扫码绑定专属导购
	 * 
	 * @param staffId
	 * @param custNo
	 * @author 17121439 朱稳俊
	 */
	public RsfResponseDto<String> scanBindGuide(String staffId, String channel, String custNo);

}
