package com.suning.sntk.test.rsf.o2o;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.nsfdas.dto.GuideInfoDto;
import com.suning.nsfdas.dto.QueryResult;
import com.suning.nsfdas.dto.ResponseContent;
import com.suning.nsfdas.service.QueryGuideInfoService;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.rsf.dto.o2o.MyGuideRspDto;
import com.suning.sntk.rsf.impl.o2o.GuideRsfServiceImpl;
import com.suning.sntk.service.configuration.DictService;
import com.suning.sntk.service.o2o.GuideCommentService;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.enums.DictEnum;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.lang.exception.ServiceException;
import com.suning.store.commons.rsf.RsfResponseDto;

public class GuideRsfServiceTest {
	@InjectMocks
	private GuideRsfServiceImpl guideRsfServiceImpl;
	@Mock
	private QueryGuideInfoService queryGuideInfoService;
	@Mock
	private GuideCommentService commentService;
	@Mock
	private DictService dictService;
	@Mock
	private GuideService guideService;
	@Mock
	private RedisCacheUtils redisClient;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void queryGuidesByCity() {
		ResponseContent<GuideInfoDto> content = getGuideInfoDtoResponseContent();

		when(queryGuideInfoService.queryGuideInfoByCity(anyString(), anyInt(), anyInt())).thenReturn(content);
		when(commentService.findAvgStarByStaffId(anyString())).thenReturn("");
		when(dictService.findDictByDictEnum(any(DictEnum.class))).thenReturn(null);
		List<GuideInfoDto> list = new ArrayList<>();
		when(redisClient.get(anyString(), (Type) anyObject())).thenReturn(list);
		RsfResponseDto<MyGuideRspDto> dto = guideRsfServiceImpl.queryGuidesByCity("319", 1, 20);
		assertEquals(dto.getData().getGuideList().get(0).getStoreCode(), "001");

		when(commentService.findAvgStarByStaffId(anyString())).thenReturn("3");
		RsfResponseDto<MyGuideRspDto> dto2 = guideRsfServiceImpl.queryGuidesByCity("319", 1, 20);
		assertEquals(dto2.getData().getGuideList().get(0).getStoreCode(), "001");

		// 检查城市白名单异常分支
		DictVo dictVo = new DictVo();
		dictVo.setDictValue("010");
		when(dictService.findDictByDictEnum(any(DictEnum.class))).thenReturn(dictVo);
		guideRsfServiceImpl.queryGuidesByCity("319", 1, 20);

	}

	private ResponseContent<GuideInfoDto> getGuideInfoDtoResponseContent() {
		ResponseContent<GuideInfoDto> content = new ResponseContent<>();
		QueryResult<GuideInfoDto> queryResult = content.getQueryResult();
		List<GuideInfoDto> resultObject = queryResult.getResultObject();
		GuideInfoDto infoDto = new GuideInfoDto();
		infoDto.setCityCode("319");
		infoDto.setStaffId("21332543");
		infoDto.setStaffName("导购1");
		infoDto.setStoreAddress("徐庄软件园");
		infoDto.setStoreCode("001");
		infoDto.setStoreName("门店名称1");
		resultObject.add(infoDto);
		queryResult.setResultObject(resultObject);
		content.setQueryResult(queryResult);
		content.setResponseCode("0");
		content.setResponseObject(infoDto);
		return content;
	}

	@Test(expectedExceptions = ServiceException.class)
	public void queryGuidesByCity2() {
		// 麦其系统返回编码异常
		ResponseContent<GuideInfoDto> content = new ResponseContent<>();
		content.setResponseCode("1");
		when(queryGuideInfoService.queryGuideInfoByCity(anyString(), anyInt(), anyInt())).thenReturn(content);
		guideRsfServiceImpl.queryGuidesByCity("319", 1, 20);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void queryGuidesByCity3() {
		// 麦其系统返回QueryResult异常
		ResponseContent<GuideInfoDto> content = new ResponseContent<>();
		content.setResponseCode("0");
		content.setQueryResult(null);
		when(queryGuideInfoService.queryGuideInfoByCity(anyString(), anyInt(), anyInt())).thenReturn(content);
		guideRsfServiceImpl.queryGuidesByCity("319", 1, 20);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void queryGuidesByCity4() {
		// 麦其系统返回null异常
		when(queryGuideInfoService.queryGuideInfoByCity(anyString(), anyInt(), anyInt())).thenReturn(null);
		guideRsfServiceImpl.queryGuidesByCity("319", 1, 20);
	}

	@Test(expectedExceptions = ServiceException.class)
	public void modifyStaffCustRel() {
		findGudie();
		when(guideService.deleteRelByStaffIdAndCustNo(anyString(), anyString())).thenReturn(true);

		RsfResponseDto<String> scanBindGuide = guideRsfServiceImpl.modifyStaffCustRel("123", "123", "123");
		assertEquals(scanBindGuide.getData(), "更新成功");

		// 异常分支
		when(guideService.saveStaffCustRel(any(StaffCustRel.class))).thenReturn(null);
		when(queryGuideInfoService.queryGuideInfoByEmployeeId(anyString())).thenReturn(null);
		guideRsfServiceImpl.modifyStaffCustRel("123", "123", "213");
	}

	private ResponseContent<GuideInfoDto> findGudie() {
		ResponseContent<GuideInfoDto> content = getGuideInfoDtoResponseContent();
		when(queryGuideInfoService.queryGuideInfoByEmployeeId(anyString())).thenReturn(content);
		return content;
	}

	@Test(expectedExceptions = ServiceException.class)
	public void scanBindGuide() {
		List<StaffCustRel> list = new ArrayList<>();
		StaffCustRel staffCustRel = new StaffCustRel();
		staffCustRel.setCustNo("101");
		staffCustRel.setStaffId("123123");
		staffCustRel.setStaffName("导购2");
		staffCustRel.setStoreCode("002");
		staffCustRel.setCreateTime(DateUtils.format(new Date()));
		staffCustRel.setChannel("线上");
		list.add(staffCustRel);
		StaffCustRel staffCustRel2 = new StaffCustRel();
		staffCustRel2.setCustNo("102");
		staffCustRel2.setStaffId("111111");
		staffCustRel2.setStaffName("导购3");
		staffCustRel2.setStoreCode("003");
		staffCustRel2.setCreateTime(DateUtils.format(new Date()));
		staffCustRel2.setChannel("线上");
		list.add(staffCustRel2);
		when(guideService.queryStaffCustListByCustNo(anyString())).thenReturn(list);
		when(guideService.saveStaffCustRel(any(StaffCustRel.class))).thenReturn(staffCustRel);
		RsfResponseDto<String> scanBindGuide = guideRsfServiceImpl.scanBindGuide("167", "0", "312312");
		assertEquals(scanBindGuide.getData(), "167");

		when(guideService.findRelByCustNoAndStaffId(anyString(), anyString())).thenReturn(null);
		findGudie();
		when(guideService.saveStaffCustRel(any(StaffCustRel.class))).thenReturn(staffCustRel);
		RsfResponseDto<String> scanBindGuide2 = guideRsfServiceImpl.scanBindGuide("167", "0", "312312");
		assertEquals(scanBindGuide2.getData(), "167");

		// 异常分支
		ResponseContent<GuideInfoDto> content = new ResponseContent<>();
		content.setResponseCode("1");
		content.setResponseObject(null);
		when(queryGuideInfoService.queryGuideInfoByEmployeeId(anyString())).thenReturn(content);
		when(guideService.queryStaffCustListByCustNo(anyString())).thenReturn(null);
		guideRsfServiceImpl.scanBindGuide("167", "0", "312312");

	}

}
