/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoServiceImpl
 * Author:   18041004_余长杰
 * Date:     2018/8/16 14:37
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.vgo.GuideRegionInfoDto;
import com.suning.sntk.dto.vgo.GuideRegionInfoListDto;
import com.suning.sntk.dto.vgo.StaffPageDto;
import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.StoreGuideReqDto;
import com.suning.sntk.dto.vgo.StoreGuideRespDto;
import com.suning.sntk.rsf.vgo.GuideInfoRsfService;
import com.suning.sntk.support.common.GuideInfoConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.redis.vgo.VgoCacheKeyConstants;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.enums.vgo.GuideInfoStatusEnum;
import com.suning.sntk.support.util.RandomNumberUtil;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;
import com.suning.sntk.web.job.CategoryCacheJob;
import com.suning.sntk.web.job.StoreInfoCacheJob;
import com.suning.sntk.web.service.vgo.GuideInfoService;
import com.suning.sntk.web.service.vgo.util.FindGuidConstants;
import com.suning.sntk.web.service.vgo.util.FindGuideUtil;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 功能描述：易购app导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
@Service
public class GuideInfoServiceImpl implements GuideInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideInfoServiceImpl.class);

    private GuideInfoRsfService guideInfoRsfService = ServiceLocator.getService(GuideInfoRsfService.class, "1.0.0");

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Value("${tipUrl}")
    private String tipUrl;

    @Override
    public RsfResponseDto<StoreGuideInfoDto> queryStoreGuide(StoreGuideReqDto storeGuideRequest) {
        //1.根据三级类目查询品类,或三级类目无对应的品类则直接返回，不再继续处理
        String category = this.gainCategoryByThirdDirectoryId(storeGuideRequest.getCategoryId());
        //未登录调麦琪匹配,三级类目没有对应的品类则直接返回
        if (StringUtils.isEmpty(category)) {
            //记录无法根据三级类目找到品类异常
            LOGGER.error("can not find the category by threeDirectoryKey:{}", storeGuideRequest.getCategoryId());
            return RsfResponseDto.error(null);
        }

        //2.根据匹配条件匹配合适的导购信息
        StoreGuideInfoDto availableGuide = this.gainAvailableGuide(storeGuideRequest, category);

        //3.对导购信息进行数据补充
        if (null != availableGuide) {
            this.enhanceGuideInfo(availableGuide, storeGuideRequest);
        }
        return RsfResponseDto.of(availableGuide);
    }

    @Override
    public RsfResponseDto<StoreGuideRespDto> queryCustManager(StoreGuideReqDto storeGuide) {

        StoreGuideRespDto response = new StoreGuideRespDto();

        //1. SCM中获取业态配置（默认电器+母婴）
        String businessTypeConfig = ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                GuideInfoConstants.BUSINESS_TYPE, GuideInfoConstants.DEFAULT_BUSINESS_TYPE);

        //2. 从SCM上获取配置的品类（注：此处为提升获取导购性能，将品类信息配置到了scm上，如后续有品类变更需要同步配置到scm上，否则不生效！！）
        String cateCode = StringUtils.EMPTY;
        String categoryCodeConfig = ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG, GuideInfoConstants.CATEGORY_CODE);
        if (StringUtils.isNotBlank(categoryCodeConfig)) {
            String[] categoryCodes = categoryCodeConfig.split(GuideInfoConstants.SPLIT_LABEL);
            //随机获取一个品类
            cateCode = RandomNumberUtil.getRandomElement(categoryCodes);
        }

        //3. 根据业态、品类进行V购信息获取处理
        String[] businessTypes = businessTypeConfig.split(GuideInfoConstants.SPLIT_LABEL);
        String infantGuideId = StringUtils.EMPTY;
        String electricGuideId = StringUtils.EMPTY;

        List<StoreGuideInfoDto> storeGuideList = new ArrayList<>();
        for (String businessType : businessTypes) {
            if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                //查询电器导购
                StoreGuideInfoDto electricGuideInfo = queryMyCustManager(storeGuide, cateCode, BusinessTypesEnum.ELECTRIC.getCode());
                if (null != electricGuideInfo && StringUtils.isNotBlank(electricGuideInfo.getGuideId())) {
                    electricGuideId = electricGuideInfo.getGuideId();
                    storeGuideList.add(electricGuideInfo);
                }
            } else if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
                //查询母婴导购
                StoreGuideInfoDto infantGuideInfo = queryMyCustManager(storeGuide, GuideInfoConstants.SERVER_NAME,
                        BusinessTypesEnum.MOM_INFANT.getCode());
                if (null != infantGuideInfo && StringUtils.isNotBlank(infantGuideInfo.getGuideId())) {
                    infantGuideId = infantGuideInfo.getGuideId();
                    storeGuideList.add(infantGuideInfo);
                }
            } else {
                LOGGER.warn("wrong businessType:{},please check the config of 'BUSINESS_TYPE' in web_scm ", businessType);
            }
        }

        //设置导购信息列表
        response.setStoreGuideInfoList(storeGuideList);
        //设置专属导购
        response.setSpecialtyGuide(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG, GuideInfoConstants.SPECIAL_GUIDE));
        //设置提示标题和返回url
        response.setTipTitle(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                GuideInfoConstants.CUST_MANAGER_TIP_TITLE));
        //设置预约列表url
        response.setTipUrl(MessageFormat.format(GuideInfoConstants.APPOINTMENT_LIST_URL_TWO_GUIDE, tipUrl, electricGuideId,
                infantGuideId, storeGuide.getCityId(), storeGuide.getDistrictId(), storeGuide.getLongitude(), storeGuide.getLatitude()));
        return RsfResponseDto.of(response);
    }

    @Override
    public RsfResponseDto<List<StoreGuideInfoDto>> queryGuideList(StoreGuideReqDto storeGuide) {
        List<StoreGuideInfoDto> storeGuideList = new ArrayList<>();

        //根据cityId取所有V购门店信息列表(初始化塞入redis)
        List<String> storeCodeList = new ArrayList<>();
        //匹配出的门店列表

        //1.获取业态信息：获取导购业态
        String businessType = storeGuide.getBusinessType();
        if (StringUtils.isBlank(businessType)) {
            String guideKey = redisCacheUtils.get(MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, storeGuide.getGuideId()));
            StoreGuideInfoDto guideInfo = JsonUtils.json2Object(guideKey, StoreGuideInfoDto.class);
            if (null != guideInfo) {
                businessType = JsonUtils.json2Object(guideKey, StoreGuideInfoDto.class).getBusinessType();
            }
        }

        //2.若计算经纬度开关打开时则通过经纬度计算最近门店，开关关闭则不计算从城市-区-业态下取门店信息
        if (StringUtils.isNotBlank(storeGuide.getLatitude()) && StringUtils.isNotBlank(storeGuide.getLongitude())) {
            if (FindGuideUtil.isNeedCalcDistance()) {
                //取出距离最近的15个门店
                storeCodeList = StoreInfoCacheJob.getNear15Stores(NumberUtils.toDouble(storeGuide.getLatitude()), NumberUtils.toDouble
                        (storeGuide.getLongitude()), businessType);
            } else {
                //记录日志，有经纬度，但计算开关未开启则不会根据经纬度查询附近门店信息
                LOGGER.warn("no need calc distance by latitude and longitude although the switch is open");
            }
        }

        //3.若门店信息为空则根据城市-区-业态查询门店信息
        String cityId = storeGuide.getCityId();
        String districtId = storeGuide.getDistrictId();
        if (CollectionUtils.isEmpty(storeCodeList)) {
            //根据城市-区-业态查询门店信息
            storeCodeList = FindGuideUtil.queryStoreList(cityId, districtId, businessType, redisCacheUtils);
        }

        //4.存在门店信息则继续处理，否则不再处理
        if (CollectionUtils.isNotEmpty(storeCodeList)) {
            //根据三级类目查询品类信息
            String category = FindGuideUtil.queryCategoryByThirdDirectory(storeGuide.getCategoryId(), redisCacheUtils);

            //判断是否需要走麦琪匹配，如需要则调用麦琪接口进行匹配，否则，
            if (FindGuideUtil.isNeedUseMatch()) {
                // 通过 门店列表、品类、当前登录用户会员编码调麦琪匹配获取导购信息列表
                List<String> guideIdList = guideInfoRsfService.queryGuideListFromMatch(storeCodeList, category, storeGuide.getCustNo())
                        .getData();
                if (CollectionUtils.isNotEmpty(guideIdList)) {
                    //处理麦琪匹配结果
                    storeGuideList = operateStoreGuideList(guideIdList, storeGuide);
                }
            } else {
                //根据门店列表，品类，业态随机匹配一个导购信息
                storeGuideList.add(FindGuideUtil.gainGuideFromSntkCache(storeCodeList, category, businessType, redisCacheUtils));
            }

        } else {
            //若查询不到门店信息，则不再继续处理，返回处理结果,记录日志，根据城市、区，品类查询不到门店信息日志
            LOGGER.warn("can not find any store by the quest(cityId={},districtId={},businessType={})", cityId, districtId, businessType);
        }

        return RsfResponseDto.of(storeGuideList);
    }

    /**
     * 对导购信息进行数据补充
     *
     * @param availableGuide    匹配到的导购信息
     * @param storeGuideRequest 匹配的查询条件信息
     * @author 18041004_余长杰
     * @since 16:54 2018/10/9
     */
    private void enhanceGuideInfo(StoreGuideInfoDto availableGuide, StoreGuideReqDto storeGuideRequest) {
        //设置导购提示的标题
        availableGuide.setTipTitle(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                GuideInfoConstants.FOURTH_PAGE_TIP_TITLE));
        //设置导购提示的内容
        availableGuide.setTipContent(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG, GuideInfoConstants.TIP_CONTENT));
        //设置是否路由到sntk的标识
        availableGuide.setIsRouteToSntk(gainRouteFlag(storeGuideRequest.getCityId()));
        //设置导购图标url
        availableGuide.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                GuideInfoConstants.VGO_GUIDE_LABEL_URL));
    }

    /**
     * 根据匹配条件匹配合适的导购信息
     *
     * @param storeGuide 匹配条件信息，包括：城市、经纬度、三级类目（用于获取其对应的品类）
     * @param category   品类信息
     * @return StoreGuideInfoDto 匹配到导购信息
     * @author 18041004_余长杰
     * @since 16:31 2018/10/9
     */
    private StoreGuideInfoDto gainAvailableGuide(StoreGuideReqDto storeGuide, String category) {
        StoreGuideInfoDto resultGuideInfo;
        //判断是否登录，已登录状态优先根据登录用户会员编码查询客户经理，否则根据其他条件进行匹配
        if (StringUtils.isNotBlank(storeGuide.getCustNo())) {
            //1.1 根据会员编号和业态查询客户经理
            resultGuideInfo = queryCustManager(storeGuide.getCustNo(), BusinessTypesEnum.ELECTRIC.getCode());
            if (null != resultGuideInfo && StringUtils.isNotBlank(resultGuideInfo.getGuideId())) {
                //设置客户经理的跳转url
                resultGuideInfo.setSntkGuideUrl(MessageFormat.format(GuideInfoConstants.FOURTH_PAGE_GUIDE_INFO_URL, tipUrl,
                        storeGuide.getCityId(), storeGuide.getDistrictId(), storeGuide.getCategoryId(),
                        resultGuideInfo.getGuideId(), storeGuide.getLongitude(), storeGuide.getLatitude()));
            } else {
                //1.2. 已登录，但无客户经理,再进行匹配非客户经理的导购处理
                resultGuideInfo = this.gainAvailableGuideForNotCustManager(storeGuide, category);
            }
        } else {
            //2. 未登录,匹配非客户经理的导购信息
            resultGuideInfo = this.gainAvailableGuideForNotCustManager(storeGuide, category);
        }

        return resultGuideInfo;
    }

    /**
     * 匹配非客户经理的导购信息，匹配逻辑：1.经纬度、品类匹配 2.若1未匹配到，则根据城市，三级类目匹配
     *
     * @param storeGuide 匹配条件信息
     * @param category   品类信息
     * @return com.suning.sntk.dto.vgo.StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 17:03 2018/10/9
     */
    private StoreGuideInfoDto gainAvailableGuideForNotCustManager(StoreGuideReqDto storeGuide, String category) {

        StoreGuideInfoDto resultGuideInfo;
        //1 不是客户经理根据经纬度查询门店匹配导购
        resultGuideInfo = queryGuideInfo(storeGuide.getLatitude(), storeGuide.getLongitude(), category,
                BusinessTypesEnum.ELECTRIC.getCode());

        //2 如果查不到则根据城市、三级目录查询导购
        if (null == resultGuideInfo) {
            resultGuideInfo = queryGuideByCityAndThreeDirectory(storeGuide.getCityId(), storeGuide.getCategoryId());
        }
        //设置导购客户经理标识及对应的跳转链接url
        if (null != resultGuideInfo) {
            //设置非客户经理标识
            resultGuideInfo.setIsCustManager(GuideInfoStatusEnum.NOT_CUST_MANAGER.getStatus());
            //设置非客户经理的跳转url
            resultGuideInfo.setSntkGuideUrl(MessageFormat.format(GuideInfoConstants.GUIDE_LIST_URL, tipUrl, storeGuide.getCityId(),
                    storeGuide.getDistrictId(), storeGuide.getCategoryId(), storeGuide.getLongitude(),
                    storeGuide.getLatitude(), resultGuideInfo.getGuideId()));
        }
        return resultGuideInfo;
    }

    /**
     * 通过三级类目获取其对应的品类
     *
     * @param thirdDirectoryId 三级类目id
     * @return java.lang.String  品类
     * @author 18041004_余长杰
     * @since 16:23 2018/10/9
     */

    private String gainCategoryByThirdDirectoryId(String thirdDirectoryId) {
        String category = CategoryCacheJob.getCategoryByThirdClass(thirdDirectoryId);
        //容错处理：jvm中获取不到则从redis中获取
        if (StringUtils.isBlank(category)) {
            //根据三级目录查询品类(初始化塞入redis)
            String threeDirectoryKey = MessageFormat.format(VgoCacheKeyConstants.KEY_THREE_DIRECTORY, thirdDirectoryId);
            category = redisCacheUtils.get(threeDirectoryKey);
        }
        return category;
    }

    /**
     * 根据经纬度,品类，业态匹配门店查询导购信息
     *
     * @param latitude     纬度
     * @param longitude    经度
     * @param category     品类
     * @param businessType 业态
     * @return StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 17:59 2018/10/8
     */
    private StoreGuideInfoDto queryGuideInfo(String latitude, String longitude, String category, String businessType) {
        StoreGuideInfoDto guideInfo = null;

        //是否有经纬度（判断条件还有SCM是否需要计算经纬度）
        if (StringUtils.isNotBlank(latitude) && StringUtils.isNotBlank(longitude) && FindGuideUtil.isNeedCalcDistance()) {
            //调用计算距离接口，按距离排序，取出最近的门店
            String storeCode = StoreInfoCacheJob.getNearestStore(NumberUtils.toDouble(latitude),
                    NumberUtils.toDouble(longitude), businessType);
            if (StringUtils.isNotBlank(storeCode)) {
                String guideKey = StringUtils.EMPTY;
                if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                    guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode, category,
                            businessType);
                } else if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
                    guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode,
                            GuideInfoConstants.SERVER_NAME, businessType);
                } else {
                    LOGGER.warn("not supported businessType:{}", businessType);
                }
                guideInfo = JsonUtils.json2Object(redisCacheUtils.get(guideKey), StoreGuideInfoDto.class);
            }
        }
        return guideInfo;
    }

    /**
     * 根据城市Id、三级类目查询导购
     *
     * @param cityId         城市Id
     * @param threeDirectory 三级类目
     * @return StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 19:24 2018/10/8
     */
    private StoreGuideInfoDto queryGuideByCityAndThreeDirectory(String cityId, String threeDirectory) {
        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_THREE_CATEGORY, cityId, threeDirectory);
        return JsonUtils.json2Object(redisCacheUtils.srandmember(key), StoreGuideInfoDto.class);
    }

    /**
     * 根据城市Id、业态查询导购
     *
     * @param cityId       城市Id
     * @param businessType 业态
     * @return StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 20:01 2018/10/8
     */
    private StoreGuideInfoDto queryGuideByCityAndBusinsessType(String cityId, String businessType) {
        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_BUSINESS_TYPE, cityId, businessType);
        return JsonUtils.json2Object(redisCacheUtils.srandmember(key), StoreGuideInfoDto.class);
    }

    /**
     * 根据城市Id、会员编码后4位、业态查询导购
     *
     * @param cityId       城市Id
     * @param custNo       会员编码
     * @param businessType 业态
     * @return StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 20:05 2018/10/8
     */
    private StoreGuideInfoDto queryGuideByCityAndCustNo(String cityId, String custNo, String businessType) {
        //取会员编号后4位
        if (StringUtils.isNotBlank(custNo)) {
            custNo = custNo.substring(custNo.length() - VgoCacheKeyConstants.SUB_COUNT_OF_CUSTNO);
        }
        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_CUSTNO_BUSINESS_TYPE, cityId, custNo, businessType);
        return JsonUtils.json2Object(redisCacheUtils.get(key), StoreGuideInfoDto.class);
    }

    /**
     * 根据城市、会员编码后4位、业态存入导购信息
     *
     * @param cityId       城市Id
     * @param custNo       会员编码
     * @param businessType 业态
     * @param guideInfo    导购信息
     * @author 18041004_余长杰
     * @since 15:39 2018/10/8
     */
    private void saveGuideInfoToCacheByCustNo(String cityId, String custNo, String businessType, StoreGuideInfoDto guideInfo) {
        //取会员编号后4位
        if (StringUtils.isNotBlank(custNo)) {
            custNo = custNo.substring(custNo.length() - VgoCacheKeyConstants.SUB_COUNT_OF_CUSTNO);
        }
        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_CUSTNO_BUSINESS_TYPE, cityId, custNo, businessType);
        redisCacheUtils.setex(key, VgoCacheKeyConstants.ONE_DAY_TIME, JsonUtils.object2Json(guideInfo));

        //逆向删除将key:guideId value:cityId-custNo-businessType
        String guideIdKey = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_ID, guideInfo.getGuideId());
        redisCacheUtils.setex(guideIdKey, VgoCacheKeyConstants.ONE_DAY_TIME, key);
    }

    /**
     * 根据城市id获取路由标记，判断城市是否属于试点城市（scm配置），如果属于试点城市则路由到sntk,标记为1，否则为0
     *
     * @param cityId 城市
     * @return String 是否路由的sntk的标记，是：1 否：0
     * @author 18041004_余长杰
     * @since 10:01 2018/9/14
     */
    private String gainRouteFlag(String cityId) {
        //SCM获取试点城市列表
        String cities = ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG, GuideInfoConstants.TEST_CITY);
        //判断是否为试点城市，是：路由到sntk；否：路由到moss
        if (StringUtils.isNotBlank(cities)) {
            String[] cityList = cities.split(VgoConstants.SPLIT_FLAG);
            // 入参的城市是SCM中的试点城市或所有城市都开启V购
            if (Arrays.asList(cityList).contains(cityId) || GuideInfoConstants.ALL_CITY.equals(cities)) {
                return GuideInfoStatusEnum.ROUTE_TO_SNTK.getStatus();
            }
        }
        return GuideInfoStatusEnum.ROUTE_TO_MOSS.getStatus();
    }

    /**
     * 根据匹配条件查询我的客户经理
     *
     * @param storeGuide   导购入参
     * @param cateCode     品类
     * @param businessType 业态
     * @return StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 11:43 2018/9/13
     */
    private StoreGuideInfoDto queryMyCustManager(StoreGuideReqDto storeGuide, String cateCode, String businessType) {
        //1.根据custNo和业态查询客户经理
        StoreGuideInfoDto guideInfo = queryCustManager(storeGuide.getCustNo(), businessType);
        if (null != guideInfo && StringUtils.isNotBlank(guideInfo.getGuideId())) {
            if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                        GuideInfoConstants.VGO_GUIDE_LABEL_URL));
            } else {
                guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                        GuideInfoConstants.INFANT_GUIDE_LABEL_URL));
            }
            guideInfo.setSntkGuideUrl(MessageFormat.format(GuideInfoConstants.CUST_MANAGER_GUIDE_INFO_URL, tipUrl, storeGuide.getCityId(),
                    storeGuide.getDistrictId(), guideInfo.getGuideId(), storeGuide.getLongitude(), storeGuide.getLatitude()));
        } else {
            //2.根据城市、会员编码后4位、业态查询导购
            guideInfo = queryGuideByCityAndCustNo(storeGuide.getCityId(), storeGuide.getCustNo(), businessType);
            //城市、会员编码后4位查不到根据经纬度查询门店匹配导购
            if (null == guideInfo) {
                //3.根据经纬度查询门店匹配导购
                guideInfo = queryGuideInfo(storeGuide.getLatitude(), storeGuide.getLongitude(), cateCode, businessType);
                //根据城市、业态查询导购，再将导购存入redis
                if (null == guideInfo) {
                    //4.兜底处理：根据城市和业态查询导购信息
                    guideInfo = queryGuideByCityAndBusinsessType(storeGuide.getCityId(), businessType);
                }
            }
            if (null != guideInfo) {
                //根据城市、会员编码后4位、业态存入导购信息，下次查询直接获取
                saveGuideInfoToCacheByCustNo(storeGuide.getCityId(), storeGuide.getCustNo(), businessType, guideInfo);
                //设置导购图标url
                if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                    guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                            GuideInfoConstants.VGO_GUIDE_LABEL_URL));
                } else {
                    guideInfo.setGuideLableUrl(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                            GuideInfoConstants.INFANT_GUIDE_LABEL_URL));
                }
                guideInfo.setSntkGuideUrl(
                        MessageFormat.format(GuideInfoConstants.CUST_MANAGER_GUIDE_INFO_URL, tipUrl, storeGuide.getCityId(),
                                storeGuide.getDistrictId(), guideInfo.getGuideId(), storeGuide.getLongitude(), storeGuide.getLatitude()));
                //设置非客户经理标识
                guideInfo.setIsCustManager(GuideInfoStatusEnum.NOT_CUST_MANAGER.getStatus());
            }
        }
        return guideInfo;
    }

    /**
     * 将麦琪匹配的结果处理：四级页列表和切换导购列表
     *
     * @param guideIdList 导购Id
     * @param storeGuide  导购信息
     * @author 18041004_余长杰
     * @since 15:27 2018/9/5
     */
    private List<StoreGuideInfoDto> operateStoreGuideList(List<String> guideIdList, StoreGuideReqDto storeGuide) {
        List<StoreGuideInfoDto> storeGuideList = new ArrayList<>();

        //根据会员编码查询客户经理
        StoreGuideInfoDto custManagerInfo = queryCustManager(storeGuide.getCustNo(), BusinessTypesEnum.ELECTRIC.getCode());

        //判断是否提供给四级页进入；否则提供给切换导购进入
        if (storeGuide.isFourthPageMark() && (StringUtils.isBlank(storeGuide.getCustNo()) || null == custManagerInfo)) {
            //四级页进入保留查询出的导购
            //四级页的导购放入List
            String fourthPageGuideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, storeGuide.getGuideId());
            storeGuideList.add(JsonUtils.json2Object(redisCacheUtils.get(fourthPageGuideKey), StoreGuideInfoDto.class));

            for (String guideId : guideIdList) {
                if (!guideId.equals(storeGuide.getGuideId())) {
                    String guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, guideId);
                    StoreGuideInfoDto storeGuideInfo = JsonUtils.json2Object(redisCacheUtils.get(guideKey), StoreGuideInfoDto.class);
                    if (storeGuideInfo != null) {
                        storeGuideInfo.setIsCustManager(GuideInfoStatusEnum.NOT_CUST_MANAGER.getStatus());
                        storeGuideList.add(storeGuideInfo);
                    }
                }

            }
        } else {
            //切换导购进入排除查询出的导购
            for (String guideId : guideIdList) {
                if (!guideId.equals(storeGuide.getGuideId())) {
                    String guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, guideId);
                    StoreGuideInfoDto storeGuideInfo = JsonUtils.json2Object(redisCacheUtils.get(guideKey), StoreGuideInfoDto.class);
                    if (storeGuideInfo != null) {
                        storeGuideInfo.setIsCustManager(GuideInfoStatusEnum.NOT_CUST_MANAGER.getStatus());
                        storeGuideList.add(storeGuideInfo);
                    }
                }
            }
        }
        //最多取10个导购
        int storeGuideListSize = storeGuideList.size() > FindGuidConstants.MAX_GUIDE_INDEX ?
                FindGuidConstants.MAX_GUIDE_INDEX : storeGuideList.size();

        storeGuideList = new ArrayList<>(storeGuideList.subList(0, storeGuideListSize));
        return storeGuideList;
    }

    /**
     * 根据会员编号和业态查询客户经理
     *
     * @param custNo       会员编号
     * @param businessType 业态
     * @return StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 11:57 2018/9/4
     */
    private StoreGuideInfoDto queryCustManager(String custNo, String businessType) {
        StoreGuideInfoDto guideInfo;

        //通过custNo获取客户经理信息
        String custNoKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CUST_MANAGER, custNo);
        guideInfo = JsonUtils.json2Object(redisCacheUtils.hget(custNoKey, businessType), StoreGuideInfoDto.class);
        if (null != guideInfo) {
            guideInfo.setIsCustManager(GuideInfoStatusEnum.IS_CUST_MANAGER.getStatus());
        }
        return guideInfo;
    }

    /**
     * 功能：查询店员主页信息（店员详情、最近预约记录、v购视频）
     *
     * @param customerNum 会员编码
     * @param guideId     导购工号
     * @param pageNo      页数
     * @param pageSize    条数
     * @param fromType    渠道
     * @author 18010645_黄成
     * @since 14:04 2018/9/13
     */
    @Override
    public RsfResponseDto<StaffPageDto> queryStaffPageInfo(String customerNum, String guideId, Integer fromType, Integer pageNo,
            Integer pageSize) {

        return guideInfoRsfService.queryStaffPageInfos(customerNum, guideId, fromType, pageNo, pageSize);
    }

    @Override
    public RsfResponseDto<GuideRegionInfoListDto> queryRegionListByParentCode(String parentRegionCode, String regionalLevel) {
        return guideInfoRsfService.queryRegionListByParentCode(parentRegionCode, regionalLevel);
    }

    @Override
    public RsfResponseDto<GuideRegionInfoDto> queryRegionInfoByRegionalCode(String regionCode, String regionalLevel) {
        return guideInfoRsfService.queryRegionInfoByRegionalCode(regionCode, regionalLevel);
    }

}
