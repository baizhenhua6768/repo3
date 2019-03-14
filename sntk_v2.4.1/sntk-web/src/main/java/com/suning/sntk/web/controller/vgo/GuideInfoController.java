/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoController
 * Author:   18041004_余长杰
 * Date:     2018/8/18 10:35
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.controller.vgo;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suning.sip.SipHelper;
import com.suning.sntk.common.util.PageCacheUtil;
import com.suning.sntk.dto.vgo.GuideRegionInfoDto;
import com.suning.sntk.dto.vgo.GuideRegionInfoListDto;
import com.suning.sntk.dto.vgo.StaffPageDto;
import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.StoreGuideReqDto;
import com.suning.sntk.dto.vgo.StoreGuideRespDto;
import com.suning.sntk.support.common.GuideInfoConstants;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.VgoErrorEnum;
import com.suning.sntk.support.exception.vgo.GuideInfoExceptionEnum;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;
import com.suning.sntk.web.dto.vgo.BaseResponse;
import com.suning.sntk.web.job.CategoryCacheJob;
import com.suning.sntk.web.job.StoreInfoCacheJob;
import com.suning.sntk.web.service.vgo.GuideInfoService;
import com.suning.sntk.web.util.FunctionSwitchUtils;
import com.suning.sntk.web.util.ScmWebConfigUtil;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：易购app导购Controller
 *
 * @author 18041004_余长杰
 * @since 2018/8/18
 */
@Controller
@Trace
@RequestMapping("/vgo/guideInfo")
public class GuideInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideInfoController.class);

    @Autowired
    private GuideInfoService guideInfoService;

    private static final String TEXT_HTML = "text/html";

    /**
     * 开启标记
     */
    private static final String SWITCH_OPEN = "1";

    /**
     * 默认城市
     */
    private static final String DEFAULT_CITY = "DEFAULT_CITY";

    /**
     * jsonp转换方式
     */
    private static final String JSONP_CONVERT_TYPE = "SWITCH_CONTROLLER_STRTEGY";

    /**
     * BaseResponse成功失败标识
     */
    private static final String SUCCESS = "0";

    private static final String FAIL = "1";

    /**
     * 四级页查询门店导购
     *
     * @param response   返回
     * @param cityId     市编码
     * @param districtId 区编码
     * @param categoryId 目录编码
     * @param callback   跨域参数
     * @param latitude   纬度
     * @param longitude  经度
     * @return BaseResponse
     * @author 18041004_余长杰
     * @since 14:36 2018/8/19
     */
    @RequestMapping(value = "/queryStoreGuide/{cityId}-{districtId}-{categoryId}-{callback}")
    public String queryStoreGuide(HttpServletRequest request, HttpServletResponse response, @PathVariable String cityId, @PathVariable
            String districtId, @PathVariable String categoryId, @PathVariable String callback,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude, @RequestParam(value = "custNo",
            required = false) String custNo) {
        //校验入参
        BaseResponse baseResponse = new BaseResponse();
        //校验入参
        if (StringUtils.isBlank(cityId) || StringUtils.isBlank(districtId) || StringUtils.isBlank(categoryId)) {
            baseResponse.setCode(FAIL);
            baseResponse.setMsg(GuideInfoExceptionEnum.PARAM_IS_NULL.getName());
            LOGGER.error(GuideInfoExceptionEnum.PARAM_IS_NULL.getName());
            return ajaxCacahe(response, JsonUtils.object2Json(baseResponse), TEXT_HTML);
        }
        //组装入参
        StoreGuideReqDto storeGuide = new StoreGuideReqDto();
        //获取当前登录会员编码(优先从passport中取，取不到从请求中取)
        String currentCustNo = request.getRemoteUser();
        if (StringUtils.isEmpty(currentCustNo)) {
            currentCustNo = custNo;
        }
        storeGuide.setCustNo(currentCustNo);
        storeGuide.setCityId(cityId);
        storeGuide.setDistrictId(districtId);
        storeGuide.setCategoryId(categoryId);
        storeGuide.setLatitude(latitude);
        storeGuide.setLongitude(longitude);
        LOGGER.info("queryStoreGuide param: storeGuide={}", storeGuide);

        if (FunctionSwitchUtils.fourthPageSwitchOpen()) {
            RsfResponseDto<StoreGuideInfoDto> rsfResponse = guideInfoService.queryStoreGuide(storeGuide);
            boolean success = rsfResponse.isSuccess();
            //设置返回操作码
            baseResponse.setCode( success ? SUCCESS : FAIL);
            //操作成功则设置返回数据，否则不设置
            if(success){
                baseResponse.setData(rsfResponse.getData());
            }
        } else {
            baseResponse.setCode(FAIL);
            baseResponse.setMsg("closed!");
            LOGGER.error("GuideInfoController.queryStoreGuide closed");
        }
        baseResponse.setApi("sntk.GuideInfoController.queryStoreGuide");
        baseResponse.setV("sntk_v2.2.1");

        if (!FunctionSwitchUtils.vanishSwitchOpen()) {
            //设置Vanish缓存
            PageCacheUtil.setCacheHeader(response, NumberUtils.toInt(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                    GuideInfoConstants.VANISH_EXPIRE_TIME)));
        }

        return ajaxCacahe(response, supportJsonpConvert(callback, JsonUtils.object2Json(baseResponse)), TEXT_HTML);
    }

    /**
     * 我的客户经理入口（易购调用）
     *
     * @param response   返回
     * @param cityId     市编码
     * @param districtId 区编码
     * @param callback   跨域参数
     * @param latitude   纬度
     * @param longitude  经度
     * @return java.lang.String
     * @author 18041004_余长杰
     * @since 15:39 2018/9/4
     */
    @RequestMapping(value = "/queryCustManager/{cityId}-{districtId}-{callback}")
    public String queryCustManager(HttpServletRequest request, HttpServletResponse response, @PathVariable String cityId,
            @PathVariable String districtId, @PathVariable String callback,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude, @RequestParam(value = "custNo",
            required = false) String custNo) {
        BaseResponse baseResponse = new BaseResponse();
        LOGGER.info("queryCustManager:{}", request.getRemoteUser());
        //组装入参
        StoreGuideReqDto storeGuide = new StoreGuideReqDto();

        //获取当前登录会员编码(优先从passport中取，取不到从请求中取)
        String currentCustNo = request.getRemoteUser();
        if (StringUtils.isEmpty(currentCustNo)) {
            currentCustNo = custNo;
        }
        storeGuide.setCustNo(currentCustNo);
        //前台不传默认取SCM上默认城市
        storeGuide.setCityId(StringUtils.isBlank(cityId) ? ScmWebConfigUtil.getConfig(DEFAULT_CITY) : cityId);
        storeGuide.setDistrictId(districtId);
        storeGuide.setLatitude(latitude);
        storeGuide.setLongitude(longitude);
        LOGGER.info("queryCustManager param: storeGuide={}", storeGuide);

        if (FunctionSwitchUtils.custManagerSwitchOpen()) {
            baseResponse.setData(guideInfoService.queryCustManager(storeGuide).getData());
            baseResponse.setCode(SUCCESS);
        } else {
            baseResponse.setCode(FAIL);
            baseResponse.setMsg("closed!");
            LOGGER.error("GuideInfoController.queryCustManager closed");
        }
        baseResponse.setApi("sntk.GuideInfoController.queryCustManager");
        baseResponse.setV("sntk_v2.2.1");

        return ajaxCacahe(response, supportJsonpConvert(callback, JsonUtils.object2Json(baseResponse)), TEXT_HTML);
    }

    /**
     * 我的客户经理入口（H5调用）
     *
     * @param cityId     市编码
     * @param districtId 区编码
     * @param latitude   纬度
     * @param longitude  经度
     * @return RsfResponseDto<List               <               StoreGuideInfoDto>>
     * @author 18041004_余长杰
     * @since 15:39 2018/9/4
     */
    @RequestMapping(value = "/queryCustManager")
    @ResponseBody
    public RsfResponseDto<StoreGuideRespDto> queryCustManager(HttpServletRequest request, @RequestParam("cityId") String cityId,
            @RequestParam("districtId") String districtId, @RequestParam("latitude") String latitude,
            @RequestParam("longitude") String longitude) {

        //组装入参
        StoreGuideReqDto storeGuide = new StoreGuideReqDto();
        storeGuide.setCustNo(request.getRemoteUser());
        //前台不传默认取SCM上默认城市
        storeGuide.setCityId(StringUtils.isBlank(cityId) ? ScmWebConfigUtil.getConfig(DEFAULT_CITY) : cityId);
        storeGuide.setDistrictId(districtId);
        storeGuide.setLatitude(latitude);
        storeGuide.setLongitude(longitude);
        LOGGER.info("queryCustManager param: storeGuide={}", storeGuide);

        //降级
        Validators.ifInValid(!FunctionSwitchUtils.custManagerSwitchOpen()).thenThrow(GuideInfoExceptionEnum.PARAM_IS_NULL);

        return guideInfoService.queryCustManager(storeGuide);
    }

    /**
     * 四级页进入卡片页
     *
     * @param request    request
     * @param cityId     城市编码
     * @param districtId 区编码
     * @param categoryId 三级目录id
     * @param guideId    导购Id
     * @param latitude   纬度
     * @param longitude  经度
     * @return RsfResponseDto<List               <               StoreGuideInfoDto>>
     * @author 18041004_余长杰
     * @since 15:57 2018/9/5
     */
    @RequestMapping(value = "/fourthPageQueryGuideList")
    @ResponseBody
    public RsfResponseDto<List<StoreGuideInfoDto>> fourthPageQueryGuideList(HttpServletRequest request,
            @RequestParam("cityId") String cityId,
            @RequestParam("districtId") String districtId, @RequestParam("categoryId") String categoryId,
            @RequestParam("guideId") String guideId, @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude) {
        //校验入参
        Validators.ifInValid(StringUtils.isBlank(cityId) || StringUtils.isBlank(categoryId)
                || StringUtils.isBlank(guideId)).thenThrow(GuideInfoExceptionEnum.PARAM_IS_NULL);

        //组装入参
        StoreGuideReqDto storeGuide = new StoreGuideReqDto();
        storeGuide.setCustNo(request.getRemoteUser());
        storeGuide.setGuideId(guideId);
        storeGuide.setCityId(cityId);
        storeGuide.setDistrictId(districtId);
        storeGuide.setCategoryId(categoryId);
        storeGuide.setLatitude(latitude);
        storeGuide.setLongitude(longitude);
        //四级页标识
        storeGuide.setFourthPageMark(true);
        LOGGER.info("fourthPageQueryGuideList param: storeGuide={}", storeGuide);

        return guideInfoService.queryGuideList(storeGuide);
    }

    /**
     * 切换导购或切换城市进入卡片页
     *
     * @param request    request
     * @param cityId     城市编码
     * @param districtId 区编码
     * @param categoryId 三级目录id
     * @param guideId    导购Id
     * @param latitude   纬度
     * @param longitude  经度
     * @return RsfResponseDto<List               <               StoreGuideInfoDto>>
     * @author 18041004_余长杰
     * @since 15:57 2018/9/5
     */
    @RequestMapping(value = "/changeGuideQueryGuideList")
    @ResponseBody
    public RsfResponseDto<List<StoreGuideInfoDto>> changeGuideQueryGuideList(HttpServletRequest request,
            @RequestParam("cityId") String cityId, @RequestParam("districtId") String districtId,
            @RequestParam(value = "categoryId", required = false) String categoryId, @RequestParam("guideId") String guideId,
            @RequestParam(value = "latitude", required = false) String latitude,
            @RequestParam(value = "longitude", required = false) String longitude,
            @RequestParam(value = "businessType", required = false) String businessType
    ) {
        //校验入参
        Validators.ifInValid(StringUtils.isBlank(cityId)).thenThrow(GuideInfoExceptionEnum.PARAM_IS_NULL);

        //组装入参
        StoreGuideReqDto storeGuide = new StoreGuideReqDto();
        storeGuide.setCustNo(request.getRemoteUser());
        storeGuide.setGuideId(guideId);
        storeGuide.setCityId(cityId);
        storeGuide.setDistrictId(districtId);
        storeGuide.setCategoryId(categoryId);
        storeGuide.setLatitude(latitude);
        storeGuide.setLongitude(longitude);
        storeGuide.setBusinessType(businessType);
        LOGGER.info("changeGuideQueryGuideList param: storeGuide={}", storeGuide);

        return guideInfoService.queryGuideList(storeGuide);
    }

    /**
     * 功能：店员主页（店员详情、v购视频、最近预约信息）
     *
     * @param request  request
     * @param guideId  导购ID
     * @param fromType 渠道
     * @param pageNo   页数
     * @param pageSize 条数
     * @author 18010645_黄成
     * @since 15:01 2018/9/17
     */
    @RequestMapping("/queryStaffPageInfo.htm")
    @ResponseBody
    public RsfResponseDto<StaffPageDto> queryStaffPageInfos(HttpServletRequest request, String guideId, Integer fromType, Integer pageNo,
            Integer pageSize) {
        String customerNum = request.getRemoteUser();
        LOGGER.info("查询店员主页(导购详情、v购视频、预约情况),customerNum={},guideId={},pageNo={},pageSize={},fromType={}", customerNum, guideId, pageNo,
                pageSize, fromType);
        //参数校验
        Validators.ifInValid(StringUtils.isBlank(guideId)).thenThrow(VgoErrorEnum.PARAM_ERROR);
        return guideInfoService.queryStaffPageInfo(customerNum, guideId, fromType, pageNo, pageSize);
    }

    /**
     * 查询省市信息
     *
     * @param parentRegionCode 父区域编码
     * @param regionalLevel    层级编码
     * @return RsfResponseDto<GuideRegionInfoListDto>
     * @author 18041004_余长杰
     * @since 11:47 2018/9/11
     */
    @RequestMapping("/queryRegionListByParentCode")
    @ResponseBody
    public RsfResponseDto<GuideRegionInfoListDto> queryRegionListByParentCode(@RequestParam("parentRegionCode") String parentRegionCode,
            @RequestParam("regionalLevel") String regionalLevel) {
        //参数校验
        Validators.ifInValid(StringUtils.isBlank(parentRegionCode) || StringUtils.isBlank(regionalLevel))
                .thenThrow(VgoErrorEnum.PARAM_ERROR);
        return guideInfoService.queryRegionListByParentCode(parentRegionCode, regionalLevel);
    }

    /**
     * 查询当前省市信息
     *
     * @param regionCode    区域编码
     * @param regionalLevel 层级编码
     * @return RsfResponseDto<GuideRegionInfoDto>
     * @author 18041004_余长杰
     * @since 20:09 2018/9/11
     */
    @RequestMapping("/queryRegionInfoByRegionalCode")
    @ResponseBody
    public RsfResponseDto<GuideRegionInfoDto> queryRegionInfoByRegionalCode(@RequestParam("regionCode") String regionCode,
            @RequestParam("regionalLevel") String regionalLevel) {
        //参数校验
        Validators.ifInValid(StringUtils.isBlank(regionCode) || StringUtils.isBlank(regionalLevel))
                .thenThrow(VgoErrorEnum.PARAM_ERROR);
        return guideInfoService.queryRegionInfoByRegionalCode(regionCode, regionalLevel);
    }

    /**
     * AJAX输出，返回null 需要做缓存的json返回
     *
     * @param content content
     * @param type    type
     * @return String
     */
    private String ajaxCacahe(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            LOGGER.error("IOException:", e);
        }
        return null;
    }

    /**
     * 支持jsonp
     *
     * @param callback 回调
     * @param json     json
     * @return java.lang.String
     * @author 18041004_余长杰
     * @since 16:28 2018/9/4
     */
    private String supportJsonpConvert(String callback, String json) {
        // 默认1
        String switchFlag = ScmWebConfigUtil.getConfig(JSONP_CONVERT_TYPE, SWITCH_OPEN);
        // 默认为小王子过滤
        String escapeCallback = SWITCH_OPEN.equals(switchFlag) ? SipHelper.filterHtml(callback)
                : StringEscapeUtils.escapeHtml4(callback);
        // jsonp格式支持处理
        return escapeCallback != null && escapeCallback.trim().length() > 0 ? escapeCallback + "(" + json + ");" : json;
    }

    @RequestMapping("/queryStoreGeoMap")
    @ResponseBody
    public RsfResponseDto<String> queryStoreGeoMap(Integer a, Integer b, String key) {
        String result = StringUtils.EMPTY;
        if (null != a && null != b && (a + b) % 1001 == 0) {
            result = StoreInfoCacheJob.queryGeoMap(key);
        }

        return RsfResponseDto.of(result);
    }

    @RequestMapping("/queryCategoryMap")
    @ResponseBody
    public RsfResponseDto<String> queryCategoryMap(Integer a, Integer b, String key) {
        String result = StringUtils.EMPTY;
        if (null != a && null != b && (a + b) % 605 == 0) {
            result = CategoryCacheJob.queryCategoryMap(key);
        }

        return RsfResponseDto.of(result);
    }
}
