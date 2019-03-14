/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: FindGuideUtil
 * Author:   18041004_余长杰
 * Date:     2018/9/22 19:20
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.vgo.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.StoreInfoDto;
import com.suning.sntk.support.common.GuideInfoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.redis.vgo.VgoCacheKeyConstants;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.util.RandomNumberUtil;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;

/**
 * 功能描述：
 *
 * @author 18041004_余长杰
 * @since 2018/9/22
 */
public class FindGuideUtil {


    /**
     * 根据城市-区-业态查询门店信息
     *
     * @param cityId       城市编码
     * @param districtId   区编码
     * @param businessType 业态（1：电器 2：母婴）
     * @return java.util.List<java.lang.String>
     * @author 18041004_余长杰
     * @since 19:54 2018/9/22
     */
    public static List<String> queryStoreList(String cityId, String districtId, String businessType, RedisCacheUtils redisCacheUtils) {
        List<String> storeCodeList = new ArrayList<>();
        //1.判断区是否有值，若区有值则根据城市-区查询门店信息，否则根据城市查询门店信息
        if (StringUtils.isNotBlank(districtId)) {
            //根据城市-区-业态 查询门店信息（key:city-district-businessType）
            String cityKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_DISTRICT_BUSINESS_TYPE, cityId, districtId, businessType);
            Set<String> storeSet = redisCacheUtils.smembers(cityKey);
            for (String storeInfo : storeSet) {
                StoreInfoDto storeInfoDto = JsonUtils.json2Object(storeInfo, StoreInfoDto.class);
                storeCodeList.add(storeInfoDto.getStoreId());
            }
            //如果未查询到结果，则过滤出城市下指定业态的门店列表(key: city_busineseType)
            if (CollectionUtils.isEmpty(storeCodeList)) {
                storeCodeList = queryStoreList(cityId, businessType, redisCacheUtils);
            }

        } else {
            //根据城市查询城市下指定业态的所有门店（key= city_busineseType）
            storeCodeList = queryStoreList(cityId, businessType, redisCacheUtils);

        }
        //取最多15个门店
        int storeListSize = storeCodeList.size() > FindGuidConstants.MAX_STORE_INDEX ?
                FindGuidConstants.MAX_STORE_INDEX : storeCodeList.size();
        storeCodeList = new ArrayList<>(storeCodeList.subList(0, storeListSize));
        return storeCodeList;
    }

    /**
     * 根据城市-业态查询门店信息
     *
     * @param cityId       城市编码
     * @param businessType 业态（1：电器 2：母婴）
     * @return java.util.List<java.lang.String>
     * @author 18041004_余长杰
     * @since 19:54 2018/9/22
     */
    public static List<String> queryStoreList(String cityId, String businessType, RedisCacheUtils redisCacheUtils) {
        List<String> storeCodeList = new ArrayList<>();
        String cityBusinessTypeKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_STORE, cityId, businessType);
        Set<String> set = redisCacheUtils.smembers(cityBusinessTypeKey);
        for (String storeInfo : set) {
            StoreInfoDto storeInfoDto = JsonUtils.json2Object(storeInfo, StoreInfoDto.class);
            storeCodeList.add(storeInfoDto.getStoreId());
        }
        return storeCodeList;
    }

    /**
     * 根据三级类目查询品类信息
     *
     * @param thirdDirectoryId 三级类目
     * @param redisCacheUtils  缓存工具类
     * @return java.lang.String
     * @author 18041004_余长杰
     * @since 20:32 2018/9/22
     */
    public static String queryCategoryByThirdDirectory(String thirdDirectoryId, RedisCacheUtils redisCacheUtils) {

        //根据三级目录查询品类(初始化塞入redis)，三级目录不存在则随机匹配一个品类
        String category = StringUtils.EMPTY;
        //若三级类目有值，则根据三级类目查询品类信息，否则随机取
        if (StringUtils.isNotBlank(thirdDirectoryId)) {
            String threeDirectoryKey = MessageFormat.format(VgoCacheKeyConstants.KEY_THREE_DIRECTORY, thirdDirectoryId);
            category = redisCacheUtils.get(threeDirectoryKey);
        }
        return category;
    }

    /**
     * 判断是否需要计算经纬度(通过scm配置)
     *
     * @return boolean
     * @author 18041004_余长杰
     * @since 19:54 2018/9/22
     */
    public static boolean isNeedCalcDistance() {
        return equals(GuideInfoConstants.NEED_CALC_DISTANCE, ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                GuideInfoConstants.LATITUDE_LONGITUDE_SWITCH));
    }

    /**
     * 判断是否需要走麦琪匹配
     *
     * @param
     * @return boolean true:需要
     * @author 18041004_余长杰
     * @since 20:47 2018/9/22
     */
    public static boolean isNeedUseMatch() {
        return equals(GuideInfoConstants.NEET_USE_MATCH_FLAG, ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_WEB_CONFIG,
                GuideInfoConstants.MATCH_SWITCH));
    }

    /**
     * 判断字符串是否匹配
     *
     * @param aspectValue 期望值
     * @param value       实际值
     * @return boolean  比较结果
     * @author 18041004_余长杰
     * @since 20:46 2018/9/22
     */
    private static boolean equals(String aspectValue, String value) {
        return aspectValue.equals(value);
    }

    /**
     * 根据门店列表，品类，业态查询sntk缓存中对应导购信息
     *
     * @param storeCodeList   门店列表
     * @param category        品类
     * @param businessType    业态
     * @param redisCacheUtils 缓存工具类
     * @return com.suning.sntk.dto.vgo.StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 21:02 2018/9/22
     */
    public static StoreGuideInfoDto gainGuideFromSntkCache(List<String> storeCodeList, String category, String businessType, RedisCacheUtils
            redisCacheUtils) {

        //从门店列表中随机取一个门店对应的导购信息
        String randomStoreCode = RandomNumberUtil.getRandomElement(storeCodeList);
        StoreGuideInfoDto storeGuideInfoDto = gainGuideFromSntkCache(randomStoreCode, category, businessType, redisCacheUtils);

        //若随机的门店中找不到满足条件的导购，则遍历其他门店查找
        if (null == storeGuideInfoDto) {
            for (String storeCode : storeCodeList) {
                if (!storeCode.equals(randomStoreCode)) {
                    storeGuideInfoDto = gainGuideFromSntkCache(storeCode, category, businessType, redisCacheUtils);
                    if (null != storeGuideInfoDto) {
                        break;
                    }
                }
            }
        }
        return storeGuideInfoDto;
    }

    /**
     * 根据门店编码，品类，业态查询sntk缓存中对应导购信息
     *
     * @param storeCode       门店编码
     * @param category        品类
     * @param businessType    业态
     * @param redisCacheUtils 缓存工具类
     * @return com.suning.sntk.dto.vgo.StoreGuideInfoDto
     * @author 18041004_余长杰
     * @since 21:02 2018/9/22
     */
    public static StoreGuideInfoDto gainGuideFromSntkCache(String storeCode, String category, String businessType, RedisCacheUtils
            redisCacheUtils) {
        // redis中获取SNTK导购信息(初始化塞入redis)
        String storeGuideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode, category,
                businessType);
        return JsonUtils.json2Object(redisCacheUtils.get(storeGuideKey), StoreGuideInfoDto.class);
    }


}
