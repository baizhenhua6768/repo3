/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideTest.java
 * Author:   15050536
 * Date:     2018年4月8日 上午10:44:10
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.test.rsf.o2o;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.nsfdas.dto.GuideInfoDto;
import com.suning.nsfdas.dto.ResponseContent;
import com.suning.nsfdas.service.MemGuidService;
import com.suning.nsfdas.service.QueryGuideInfoService;
import com.suning.nsfuaa.employee.EmployeeService;
import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.rsf.dto.o2o.GuideDetailDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideReqDto;
import com.suning.sntk.rsf.impl.o2o.GuideRsfServiceImpl;
import com.suning.sntk.service.configuration.DictService;
import com.suning.sntk.service.o2o.GuideCommentService;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.enums.DictEnum;
import com.suning.sntk.support.exception.SntkServiceException;
import com.suning.sntk.support.exception.enums.GuideErrorEnum;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.lang.exception.ServiceException;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 导购相关功能的测试
 *
 * @author 15050536 石键平
 */
public class GuideTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuideTest.class);

	@InjectMocks
	private GuideRsfServiceImpl guideRsfServiceImpl;
	@Mock
	GuideService guideService;
	@Mock
	QueryGuideInfoService queryGuideInfoService;
	@Mock
	EmployeeService employeeService;
	@Mock
	GuideCommentService commentService;
	@Mock
	MemGuidService memGuidService;
	@Mock
	DictService dictService;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	/**
	 * 测试查询我的专属导购
	 *
	 */
	@Test
	public void testQueryMyGuide() {
		StaffCustRel sc = getStaffCustRel();
		List<StaffCustRel> staffCustList = new ArrayList<>();
		staffCustList.add(sc);

		DictVo dict = new DictVo();
		dict.setDictValue("010,025,021");
		BDDMockito.willReturn(dict).given(dictService).findDictByDictEnum(DictEnum.CITY_WHILE_LIST);

		BDDMockito.willReturn(staffCustList).given(guideService).queryStaffCustListByCustNo(Matchers.anyString());
		BDDMockito.willReturn(getEmployee(1)).given(employeeService).queryEmployeeBasicInfo(Matchers.anyString());
		System.out.println(guideRsfServiceImpl.queryMyGuide("123", "025"));
		BDDMockito.willReturn(null).given(employeeService).queryEmployeeBasicInfo(Matchers.anyString());
		System.out.println(guideRsfServiceImpl.queryMyGuide("123", "025"));
		BDDMockito.willReturn(null).given(guideService).queryStaffCustListByCustNo(Matchers.anyString());
		BDDMockito.willReturn(getEmployee(0)).given(employeeService).queryEmployeeBasicInfo(Matchers.anyString());

		System.out.println(guideRsfServiceImpl.queryMyGuide("123", "025"));
		try {
			System.out.println(guideRsfServiceImpl.queryMyGuide("123", "015"));
		} catch (ServiceException e) {
			LOGGER.info("测试业务异常", e);
		}

	}

	private StaffCustRel getStaffCustRel() {
		StaffCustRel sc = new StaffCustRel();
		sc.setCustNo("123");
		sc.setStaffId("10000001");
		sc.setStaffName("测试员工");
		sc.setBusinessType(O2OConstants.MACHINE);
		sc.setChannel(O2OConstants.CHANNEL_ONLINE);
		sc.setCreateTime(DateUtils.format(new Date()));
		sc.setStoreCode("837A");
		return sc;
	}

	private Employee getEmployee(int status) {
		Employee employee = new Employee();
		employee.setStatus(status);
		return employee;
	}

	/**
	 * 绑定专属导购
	 *
	 */
	@Test
	public void testBindGuide() {
		// 城市在白名单中
		// 存在导购分支
		List<StaffCustRel> list = getStaffCustRelList();
		BDDMockito.when(guideService.queryStaffCustListByCustNo(Matchers.anyString())).thenReturn(list);
		MyGuideReqDto myGuideReq = new MyGuideReqDto();
		myGuideReq.setCityCode("010");
		RsfResponseDto<String> bindGuide = guideRsfServiceImpl.bindGuide(myGuideReq);
		assertEquals(bindGuide.getData(), "123");

		// 不存在导购，匹配不到导购分支
		BDDMockito.when(guideService.queryStaffCustListByCustNo(Matchers.anyString())).thenReturn(null);
		myGuideReq.setCategoryCode("5666");
		myGuideReq.setCityCode("010");
		myGuideReq.setCustNo("12321");
		myGuideReq.setStoreList("111,222,333,444");
		findGudie();
		BDDMockito.when(memGuidService.queryBestEmployeeid(Matchers.anyString(), Matchers.anyListOf(String.class), Matchers.anyString())).thenReturn(null);
		BDDMockito.when(guideService.saveStaffCustRel(any(StaffCustRel.class))).thenReturn(null);
		try {
			guideRsfServiceImpl.bindGuide(myGuideReq);
		} catch (ServiceException e) {
			assertEquals(e.getName(), GuideErrorEnum.MATCH_FAIL.getName());
			LOGGER.info("测试麦琪匹配导购为空", e);
		}

		// 不存在导购，匹配到导购分支
		BDDMockito.when(guideService.queryStaffCustListByCustNo(Matchers.anyString())).thenReturn(null);
		ResponseContent<Map<String, Double>> resonseMatch = new ResponseContent<>();
		Map<String, Double> map = new HashMap<>();
		map.put("22222", 1.2);
		resonseMatch.setResponseObject(map);
		BDDMockito.when(memGuidService.queryBestEmployeeid(Matchers.anyString(), Matchers.anyListOf(String.class), Matchers.anyString())).thenReturn(resonseMatch);
		BDDMockito.when(guideService.saveStaffCustRel(any(StaffCustRel.class))).thenReturn(null);
		RsfResponseDto<String> bindGuide3 = guideRsfServiceImpl.bindGuide(myGuideReq);
		assertEquals(bindGuide3.getData(), "22222");

		// 不存在导购，匹配到导购 ，根据工号，查询导购的详细信息异常分支
		BDDMockito.when(guideService.queryStaffCustListByCustNo(Matchers.anyString())).thenReturn(null);
		BDDMockito.when(queryGuideInfoService.queryGuideInfoByEmployeeId(anyString())).thenReturn(null);
		BDDMockito.when(memGuidService.queryBestEmployeeid(Matchers.anyString(), Matchers.anyListOf(String.class), Matchers.anyString())).thenReturn(resonseMatch);
		BDDMockito.when(guideService.saveStaffCustRel(any(StaffCustRel.class))).thenReturn(null);
		try {
			guideRsfServiceImpl.bindGuide(myGuideReq);
		} catch (ServiceException e) {
			assertEquals(e.getName(), GuideErrorEnum.QUERY_NSF_GUIDE_BY_STAFF_ID_FAIL.getName());
			LOGGER.info("测试查询das详细信息失败", e);
		}

	}

	@Test(expectedExceptions = ServiceException.class)
	public void testBindGuide2() {
		// 检查城市白名单异常分支
		DictVo dictVo = new DictVo();
		dictVo.setDictValue("010");
		when(dictService.findDictByDictEnum(DictEnum.CITY_WHILE_LIST)).thenReturn(dictVo);
		MyGuideReqDto myGuideReq = new MyGuideReqDto();
		myGuideReq.setCityCode("022");
		guideRsfServiceImpl.bindGuide(myGuideReq);

	}

	private ResponseContent<GuideInfoDto> findGudie() {
		ResponseContent<GuideInfoDto> content = new ResponseContent<>();
		GuideInfoDto guideInfoDto = new GuideInfoDto();
		guideInfoDto.setCityCode("319");
		guideInfoDto.setStaffId("11111");
		guideInfoDto.setStaffName("导购1");
		guideInfoDto.setStoreAddress("徐庄软件园");
		guideInfoDto.setStoreCode("001");
		guideInfoDto.setStoreName("门店1");
		content.setResponseObject(guideInfoDto);
		content.setResponseCode("0");// 0:成功 1：失败
		when(queryGuideInfoService.queryGuideInfoByEmployeeId(anyString())).thenReturn(content);
		return content;
	}

	private List<StaffCustRel> getStaffCustRelList() {
		List<StaffCustRel> list = new ArrayList<>();
		StaffCustRel staffCustRel = new StaffCustRel();
		staffCustRel.setCustNo("101");
		staffCustRel.setStaffId("123");
		staffCustRel.setStaffName("导购2");
		staffCustRel.setStoreCode("002");
		staffCustRel.setCreateTime(DateUtils.format(new Date()));
		staffCustRel.setChannel("线上");
		list.add(staffCustRel);
		return list;
	}

	/**
	 * 查询导购详情
	 */
	@Test
	public void testFindGuideDetail() {
		// 本地导购列表不存在
		findGudie();
		when(commentService.findAvgStarByStaffId(anyString())).thenReturn("3");
		Map<String, Integer> value = new HashMap<>();
		value.put("服务周到", 5);
		value.put("态度好", 5);
		when(commentService.findProfileCommentByStaffId(anyString())).thenReturn(value);
		when(guideService.queryStaffCustListByCustNo(anyString())).thenReturn(null);
		RsfResponseDto<GuideDetailDto> dto = guideRsfServiceImpl.findGuideDetail("123", "213");
		GuideDetailDto data = dto.getData();
		assertEquals(data.getIfMyGuide(), "0");

		// 本地导购列表存在，专属导购和会员当前专属导购一致
		findGudie();
		when(commentService.findAvgStarByStaffId(anyString())).thenReturn("3");
		when(commentService.findProfileCommentByStaffId(anyString())).thenReturn(value);
		when(guideService.queryStaffCustListByCustNo(anyString())).thenReturn(getStaffCustRelList());
		RsfResponseDto<GuideDetailDto> dto2 = guideRsfServiceImpl.findGuideDetail("123", "213");
		GuideDetailDto data2 = dto2.getData();
		assertEquals(data2.getIfMyGuide(), "1");

		// 本地导购列表存在，专属导购和会员当前专属导购不一致
		findGudie();
		when(commentService.findAvgStarByStaffId(anyString())).thenReturn("3");
		when(commentService.findProfileCommentByStaffId(anyString())).thenReturn(value);
		when(guideService.queryStaffCustListByCustNo(anyString())).thenReturn(getStaffCustRelList());
		RsfResponseDto<GuideDetailDto> dto3 = guideRsfServiceImpl.findGuideDetail("555", "213");
		GuideDetailDto data3 = dto3.getData();
		assertEquals(data3.getIfMyGuide(), "0");

	}

	@Test(expectedExceptions = ServiceException.class)
	public void testFindGuideDetail2() {
		// 大数据查询导购信息返回null异常分支
		when(queryGuideInfoService.queryGuideInfoByEmployeeId(anyString())).thenReturn(null);
		guideRsfServiceImpl.findGuideDetail("123", "213");
	}

	@Test(expectedExceptions = ServiceException.class)
	public void testFindGuideDetail3() {
		// 大数据查询导购信息接口异常分支
		when(queryGuideInfoService.queryGuideInfoByEmployeeId(anyString())).thenThrow(new SntkServiceException(GuideErrorEnum.QUERY_NSF_GUIDE_BY_STAFF_ID_FAIL));
		guideRsfServiceImpl.findGuideDetail("123", "213");
	}

}
