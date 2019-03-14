/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideRsfServiceImpl.java
 * Author:   15050536
 * Date:     2018年3月28日 上午11:44:29
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.rsf.impl.o2o;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.suning.nsfdas.dto.GuideInfoDto;
import com.suning.nsfdas.dto.ResponseContent;
import com.suning.nsfdas.service.MemGuidService;
import com.suning.nsfdas.service.QueryGuideInfoService;
import com.suning.nsfuaa.employee.EmployeeService;
import com.suning.nsfuaa.employee.dto.Employee;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.rsf.dto.o2o.CommentProfileDto;
import com.suning.sntk.rsf.dto.o2o.GuideDetailDto;
import com.suning.sntk.rsf.dto.o2o.GuideDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideReqDto;
import com.suning.sntk.rsf.dto.o2o.MyGuideRspDto;
import com.suning.sntk.rsf.o2o.GuideRsfService;
import com.suning.sntk.service.configuration.DictService;
import com.suning.sntk.service.o2o.GuideCommentService;
import com.suning.sntk.service.o2o.GuideService;
import com.suning.sntk.support.common.O2OConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.copy.OrikaMapperFactory;
import com.suning.sntk.support.enums.DictEnum;
import com.suning.sntk.support.exception.enums.GuideErrorEnum;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validator;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;
import com.suning.store.commons.rsf.advice.RsfAdvice;

/**
 * 导购业务的rsf实现类
 *
 * @author 15050536 石键平
 */
@Implement(contract = GuideRsfService.class, implCode = "1.0.0")
@Service
@Trace
@Validated
@RsfAdvice
public class GuideRsfServiceImpl implements GuideRsfService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuideRsfServiceImpl.class);

	@Autowired
	private GuideService guideService;

	@Autowired
	private DictService dictService;

	@Autowired
	private GuideCommentService commentService;

	private QueryGuideInfoService queryGuideInfoService = ServiceLocator.getService(QueryGuideInfoService.class, null);

	private EmployeeService employeeService = ServiceLocator.getService(EmployeeService.class, null);

	private MemGuidService memGuidService = ServiceLocator.getService(MemGuidService.class, null);

	@Override
	public RsfResponseDto<MyGuideRspDto> queryMyGuide(String custNo, String cityCode) {

		// 查询本地库，是否有绑定关系
		List<StaffCustRel> staffCustList = guideService.queryStaffCustListByCustNo(custNo);

		MyGuideRspDto rsp = new MyGuideRspDto();

		// 如果没有，返回空列表
		if (CollectionUtils.isEmpty(staffCustList)) {
			// 城市校验
			Validators.ifInValid(!checkCity(cityCode)).thenThrow(GuideErrorEnum.CHECK_CITY_ERROR);

			// 返回品类列表
			rsp.setGuideList(new ArrayList<GuideDto>());
			rsp.setCategoryList(getCategories());

		} else {
			// 如果有，查询离职关系
			List<GuideDto> guideList = changeStaffCustToGuideDto(staffCustList);
			rsp.setGuideList(guideList);
		}

		return RsfResponseDto.of(rsp);
	}

	/**
	 * 将库里查询出来的导购关系列表，转换成导购dto,返回给前端，并且返回每个导购的离职状态
	 *
	 * @param staffCustList
	 * @return
	 */
	private List<GuideDto> changeStaffCustToGuideDto(List<StaffCustRel> staffCustList) {
		List<GuideDto> list = new ArrayList<>();
		for (StaffCustRel staffCust : staffCustList) {
			GuideDto guideDto = new GuideDto();
			String staffId = staffCust.getStaffId();
			Employee employee = null;
			try {
				employee = employeeService.queryEmployeeBasicInfo(staffId);
			} catch (Exception e) {
				LOGGER.error("调用nsf,查询员工离职信息发生异常:{}", staffId, e);
			}
			LOGGER.debug("查询员工是否离职，入参为：{}，回参为：{}", staffId, employee);
			// 如果查询到员工，并且员工离职，则记录离职
			if (employee != null && O2OConstants.UAA_QUIT == employee.getStatus()) {
				// 表示离职
				guideDto.setStatus(O2OConstants.O2O_QUIT);
			} else {
				// 表示在职
				guideDto.setStatus(O2OConstants.YES);
			}
			guideDto.setStaffId(staffId);
			guideDto.setStaffName(staffCust.getStaffName());
			list.add(guideDto);
		}
		return list;
	}

	/**
	 * 检查传入的cityCode是否在白名单城市内
	 *
	 * @param cityCode
	 *            城市编码
	 * @return
	 */
	private boolean checkCity(String cityCode) {

		// 查询白名单城市列表
		DictVo cityDict = dictService.findDictByDictEnum(DictEnum.CITY_WHILE_LIST);
		// 没有配置，或者是-1,那么就都可以访问
		if (cityDict == null || StringUtils.isEmpty(cityDict.getDictValue()) || O2OConstants.SKIP.equals(cityDict.getDictValue())) {
			return true;
		}
		LOGGER.debug("白名单城市有:{}", cityDict.getDictValue());
		// 城市在白名单列表里面
		String[] cities = cityDict.getDictValue().split(O2OConstants.SP_DH);
		return ArrayUtils.contains(cities, cityCode);
	}

	/**
	 * 从数据字典表，查询品类列表，如果没有，则取默认值<br/>
	 * 默认品类格式为：00001:空调,00002:冰洗,00003:黑电,00004:数码,00005:电脑,00006:通讯,00007:小家电,
	 * 000013:厨卫
	 * 
	 * @return
	 */
	private List<JSONObject> getCategories() {
		DictVo categoryDict = dictService.findDictByDictEnum(DictEnum.DEFAULE_CATEGORY_LIST);
		String defaultCategoryStr = O2OConstants.DEFAULT_CATEGORIES;
		// 数据字典表中有的话，则取数据字典内的数据
		if (categoryDict != null && StringUtils.isNotEmpty(categoryDict.getDictValue())) {
			defaultCategoryStr = categoryDict.getDictValue();
		}
		// 按逗号分隔
		String defaultCategories[] = defaultCategoryStr.split(O2OConstants.SP_DH);

		List<JSONObject> categoryList = new ArrayList<>();
		for (String category : defaultCategories) {
			JSONObject json = new JSONObject();
			// 按冒号分隔，取code和name
			String categoryCode = category.split(O2OConstants.SP_MH)[0];
			String categoryName = category.split(O2OConstants.SP_MH)[1];
			json.put("categoryCode", categoryCode);
			json.put("categoryName", categoryName);
			categoryList.add(json);
		}
		return categoryList;
	}

	/**
	 * 用户首次点击微服务按钮 绑定导购关系
	 */
	@Override
	public RsfResponseDto<String> bindGuide(MyGuideReqDto myGuideReq) {
		LOGGER.debug("绑定导购接口，入参：{}", myGuideReq);
		String cityCode = myGuideReq.getCityCode();
		String custNo = myGuideReq.getCustNo();
		// 城市校验
		Validators.ifInValid(!checkCity(cityCode)).thenThrow(GuideErrorEnum.CHECK_CITY_ERROR);

		// 查询本地库，是否有绑定关系,如果有了，直接返回现有的导购工号
		List<StaffCustRel> staffCustList = guideService.queryStaffCustListByCustNo(custNo);
		if (CollectionUtils.isNotEmpty(staffCustList)) {
			String staffId = staffCustList.get(NumberUtils.INTEGER_ZERO).getStaffId();
			LOGGER.debug("当前顾客已经有导购了,无需调用match。导购为：{}", staffCustList.get(NumberUtils.INTEGER_ZERO));
			return RsfResponseDto.of(staffId);
		}
		// 去麦琪系统匹配
		String[] stores = myGuideReq.getStoreList().split(O2OConstants.SP_DH);
		List<String> storeList = Lists.newArrayList(stores);
		String staffId = queryStaffFromMatch(custNo, myGuideReq.getCategoryCode(), storeList);
		LOGGER.debug("通过麦琪系统匹配到的工号为:{}", staffId);
		// Match系统查询失败的话，抛出异常
		Validators.ifBlank(staffId).thenThrow(GuideErrorEnum.MATCH_FAIL);

		// 将绑定关系，记录到数据库; 2018-04-19 :匹配到的用户不入库，不绑定关系
		// saveStaffCustRel(staffId, custNo, O2OConstants.CHANNEL_ONLINE)
		return RsfResponseDto.of(staffId);
	}

	/**
	 * 从麦琪系统匹配合适的导购
	 *
	 * @param custNo
	 * @param categoryCode
	 * @param storeList
	 * @return
	 */
	private String queryStaffFromMatch(String custNo, String categoryCode, List<String> storeList) {
		ResponseContent<Map<String, Double>> resonseMatch = memGuidService.queryBestEmployeeid(custNo, storeList, categoryCode);
		LOGGER.debug("麦琪系统匹配结果为：{}", resonseMatch);
		if (resonseMatch != null && resonseMatch.getResponseObject() != null && resonseMatch.getResponseObject().size() > 0) {
			Map<String, Double> map = resonseMatch.getResponseObject();
			for (Entry<String, Double> entry : map.entrySet()) {
				return entry.getKey();
			}
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 查询导购详细信息
	 */
	@Override
	public RsfResponseDto<GuideDetailDto> findGuideDetail(String staffId, String custNo) {
		GuideDetailDto guideDetail = new GuideDetailDto();
		// 从大数据获取导购基础信息
		GuideDto guide = findGuideInfoFromNsf(staffId);
		// 从评价service获取评价概况
		Map<String, Integer> commentProfile = commentService.findProfileCommentByStaffId(staffId);
		guideDetail.setProfile(getProfile(commentProfile));
		guideDetail.setGuideDto(guide);

		// 查询当前会员的导购信息
		List<StaffCustRel> guideList = guideService.queryStaffCustListByCustNo(custNo);
		String ifMyGuide = O2OConstants.NO;
		if (CollectionUtils.isNotEmpty(guideList)) {
			// 这一期只有一个导购，后面需要修改
			StaffCustRel staffCust = guideList.get(NumberUtils.INTEGER_ZERO);
			// 当前的查看的导购，并不是custNo对应会员的专属导购
			if (!StringUtils.equals(staffCust.getStaffId(), staffId)) {
				guideDetail.setOldStaffId(staffCust.getStaffId());
			} else {
				ifMyGuide = O2OConstants.YES;
			}
		}
		guideDetail.setIfMyGuide(ifMyGuide);

		return RsfResponseDto.of(guideDetail);
	}

	/**
	 * 将评论service查询到的评价信息，转换成dto
	 *
	 * @param commentProfile
	 * @return
	 */
	private List<CommentProfileDto> getProfile(Map<String, Integer> commentProfile) {
		List<CommentProfileDto> list = new ArrayList<>();
		if (commentProfile == null || commentProfile.isEmpty()) {
			return list;
		}
		for (Entry<String, Integer> entry : commentProfile.entrySet()) {
			CommentProfileDto profile = new CommentProfileDto();
			profile.setLabelName(entry.getKey());
			profile.setNum(entry.getValue());
			list.add(profile);
		}
		return list;
	}

	/**
	 * 根据店员工号，调用das rsf接口，查询对应的详细信息
	 *
	 * @return
	 */
	private GuideDto findGuideInfoFromNsf(String staffId) {
		ResponseContent<GuideInfoDto> responseContent = queryGuideInfoService.queryGuideInfoByEmployeeId(staffId);
		LOGGER.debug("[sntk->das]查询导购详细信息，入参:{},回参:{}", staffId, responseContent);
		// 接口非空判断
		Validators.ifInValid(new Validator<ResponseContent<GuideInfoDto>>() {
			@Override
			public boolean validate(ResponseContent<GuideInfoDto> responseContent) {
				return responseContent != null && StringUtils.equals(responseContent.getResponseCode(), O2OConstants.NSF_SCUUESS);
			}
		}, responseContent).thenThrow(GuideErrorEnum.QUERY_NSF_GUIDE_BY_STAFF_ID_FAIL);

		GuideInfoDto guideInfoDto = responseContent.getResponseObject();
		GuideDto guide = new GuideDto();
		String avgStar = commentService.findAvgStarByStaffId(staffId);
		guide.setStar(avgStar);
		OrikaMapperFactory.map(guideInfoDto, guide);
		return guide;
	}

	/**
	 * 更新专属导购
	 *
	 */
	@Override
	@Transactional
	public RsfResponseDto<String> modifyStaffCustRel(String custNo, String newStaffId, String oldStaffId) {
		// 查询顾客与老导购的绑定关系
		StaffCustRel custRel = guideService.findRelByCustNoAndStaffId(oldStaffId, custNo);
		// 判断是否是今天绑定的导购
		if (custRel != null) {
			Validators.ifInValid(DateUtils.isSameDay(DateUtils.parseByPatten19(custRel.getCreateTime()), new Date())).thenThrow(GuideErrorEnum.UPDATE_LIMIT);
		}

		// 删除老关系
		guideService.deleteRelByStaffIdAndCustNo(oldStaffId, custNo);
		// 建立新关系
		saveStaffCustRel(newStaffId, custNo, O2OConstants.CHANNEL_ONLINE);
		return RsfResponseDto.of(O2OConstants.UPDATE_SUCCESS);
	}

	/**
	 * 扫码绑定专属导购
	 */
	@Override
	public RsfResponseDto<String> scanBindGuide(String staffId, String channel, String custNo) {
		// 查询导购是否存在
		List<StaffCustRel> staffCustRel = guideService.queryStaffCustListByCustNo(custNo);
		// 不存在则绑定
		if (CollectionUtils.isEmpty(staffCustRel)) {
			saveStaffCustRel(staffId, custNo, channel);
		}
		return RsfResponseDto.of(staffId);
	}

	/**
	 * 根据会员编码和导购工号，做绑定关系
	 *
	 */
	private void saveStaffCustRel(String staffId, String custNo, String channel) {
		// 去das 查询导购详细信息
		GuideDto guide = findGuideInfoFromNsf(staffId);

		StaffCustRel staffCustRel = new StaffCustRel();
		staffCustRel.setCustNo(custNo);
		staffCustRel.setStaffId(staffId);
		staffCustRel.setStaffName(guide.getStaffName());
		staffCustRel.setStoreCode(guide.getStoreCode());
		staffCustRel.setCreateTime(DateUtils.format(new Date()));
		staffCustRel.setChannel(channel);

		guideService.saveStaffCustRel(staffCustRel);
	}

	/**
	 * 根据城市分页查询导购信息
	 *
	 * @param cityCode
	 *            城市编码
	 * @param page
	 *            页数
	 * @param size
	 *            每页多少条
	 */
	@Override
	public RsfResponseDto<MyGuideRspDto> queryGuidesByCity(String cityCode, Integer page, Integer size) {
		// 城市校验
		Validators.ifInValid(!checkCity(cityCode)).thenThrow(GuideErrorEnum.CHECK_CITY_ERROR);

		List<GuideInfoDto> resultObject = getGuideInfoDtoList(cityCode, page, size);

		// 判断返回的list集合是否为空
		List<GuideDto> arrayList = new ArrayList<>();
		for (GuideInfoDto guideInfo : resultObject) {

			// 4.根据得到导购工号列表遍历查询星级
			String avgStar = commentService.findAvgStarByStaffId(guideInfo.getStaffId());
			String star = O2OConstants.DEFAULT_AVG_STAR;
			if (StringUtils.isNotBlank(avgStar)) {
				star = avgStar;
			}
			// 5.参数分装
			GuideDto guideDto = new GuideDto();
			OrikaMapperFactory.map(guideInfo, guideDto);

			guideDto.setStar(star);
			arrayList.add(guideDto);
		}
		MyGuideRspDto guideRsp = new MyGuideRspDto();
		guideRsp.setGuideList(arrayList);
		return RsfResponseDto.of(guideRsp);
	}

	private List<GuideInfoDto> getGuideInfoDtoList(String cityCode, Integer page, Integer size) {
		// 2.根据城市编码查询导购列表
		ResponseContent<GuideInfoDto> content = queryGuideInfoService.queryGuideInfoByCity(cityCode, page, size);

		// 接口非空判断
		Validators.ifInValid(new Validator<ResponseContent<GuideInfoDto>>() {
			@Override
			public boolean validate(ResponseContent<GuideInfoDto> content) {
				return content != null && StringUtils.equals(content.getResponseCode(), O2OConstants.NSF_SCUUESS) && CollectionUtils.isNotEmpty(content.getQueryResult().getResultObject());
			}
		}, content).thenThrow(GuideErrorEnum.QUERY_NSF_GUIDE_BY_CITY_CODE_FAIL);

		LOGGER.debug("[sntk->das]根据城市：{}，获取导购列表信息为:{}", cityCode, content);

		// 获得导购信息列表
		return content.getQueryResult().getResultObject();
	}

}
