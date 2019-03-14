/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoUpdateRedisServiceImpl
 * Author:   18041004_余长杰
 * Date:     2018/10/8 9:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.baozi.rsfservice.dto.StoreGuideDto;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dto.vgo.CategoryOutRelDto;
import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.StoreInfoDto;
import com.suning.sntk.service.vgo.VgoCommonService;
import com.suning.sntk.service.vgo.VgoUpdateRedisService;
import com.suning.sntk.support.common.GuideInfoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.redis.vgo.VgoCacheKeyConstants;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.util.RandomNumberUtil;
import com.suning.sntk.support.util.scm.ScmPropertiesUtil;
import com.suning.sntk.support.util.scm.ScmPropertyFileEnum;

/**
 * 功能描述：V购更新redis
 *
 * @author 18041004_余长杰
 * @since 2018/10/8
 */
@Service
public class VgoUpdateRedisServiceImpl implements VgoUpdateRedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VgoUpdateRedisServiceImpl.class);

    @Autowired
    private GuideInfoDao guideInfoDao;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private VgoCommonService vgoCommonService;

    @Autowired
    BaoziConsumerService baoziConsumerService;

    /**
     * 每次查询条数
     */
    private final static int PAGE_SIZE = 1000;

    @Override
    public void saveAllCustManagerToCache() {
        List<ManagerInfoDto> custManagerList = guideInfoDao.queryAllCustManager();
        LOGGER.info("queryAllCustManager param: custManagerList={}", custManagerList);

        if (CollectionUtils.isEmpty(custManagerList)) {
            return;
        }

        for (ManagerInfoDto managerInfo : custManagerList) {
            StoreGuideInfoDto guideInfo = guideInfoDao.queryAllGuideInfoByGuideId(managerInfo.getStaffId());
            if (null != guideInfo && StringUtils.isNotBlank(guideInfo.getGuideId())) {
                //查询导购服务数、获赞数、母婴导购服务项目、默认头像
                queryGuideOrderNumAndPraise(guideInfo);
                //将key:custNo field:businessType value:guideInfo存入redis
                String custNoKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CUST_MANAGER, managerInfo.getCustNo());
                redisCacheUtils.hset(custNoKey, managerInfo.getBusinessType(), JsonUtils.object2Json(guideInfo), queryExpireTimeByScm());
            }
        }
    }

    @Override
    public void saveAllThreeDirectoryCategoryToCache() {
        List<CategoryOutRelDto> categoryOutRelList = guideInfoDao.queryAllThreeDirectoryCategory();
        LOGGER.info("queryCategoryByThreeDirectory param: categoryOutRelList={}", categoryOutRelList);

        if (CollectionUtils.isEmpty(categoryOutRelList)) {
            return;
        }

        for (CategoryOutRelDto category : categoryOutRelList) {
            //key：三级目录 value：品类list
            String threeDirectoryKey = MessageFormat.format(VgoCacheKeyConstants.KEY_THREE_DIRECTORY, category.getOutCode());
            redisCacheUtils.setex(threeDirectoryKey, queryExpireTimeByScm(), category.getCategoryCode());
        }
    }

    @Override
    public void saveGuideInfoToCacheByStore() {
        List<StoreGuideDto> storeGuideList = new ArrayList<>();
        if (GuideInfoConstants.IS_MATCH.equals(ScmPropertiesUtil.getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG,
                GuideInfoConstants.MATCH_SWITCH))) {
            Long count = guideInfoDao.queryAllVgoStoreListCount(StringUtils.EMPTY);
            if (null != count && count > 0) {
                int queryTimes = (int) (count / PAGE_SIZE + 1);
                for (int i = 0; i < queryTimes; i++) {
                    List<StoreInfoDto> storeInfoList = guideInfoDao.queryAllVgoStoreList(i * PAGE_SIZE, PAGE_SIZE, StringUtils.EMPTY);
                    LOGGER.info("queryAllVgoStoreList param: storeInfoList={}", storeInfoList);

                    if (CollectionUtils.isEmpty(storeInfoList)) {
                        return;
                    }

                    String guideKey = StringUtils.EMPTY;
                    for (StoreInfoDto storeInfo : storeInfoList) {
                        String storeCode = storeInfo.getStoreId();
                        String businessType = storeInfo.getBusinessType();
                        // 调麦琪系统匹配导购
                        storeGuideList = baoziConsumerService.queryStoreBestGuideId(storeCode);
                        for (StoreGuideDto guideInfo : storeGuideList) {
                            String categoryId = guideInfo.getCategoryId();
                            if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                                guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode, categoryId,
                                        businessType);
                            }
                            if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
                                guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode,
                                        GuideInfoConstants.SERVER_NAME, businessType);
                            }
                            StoreGuideInfoDto guideInfoDto = guideInfoDao.queryAllGuideInfoByGuideId(guideInfo.getGuideId());
                            if (null != guideInfoDto && StringUtils.isNotBlank(guideInfo.getGuideId())) {
                                //查询导购服务数、获赞数、母婴导购服务项目、默认头像
                                queryGuideOrderNumAndPraise(guideInfoDto);
                                //将key:guideId  value:guideInfo存入redis
                                redisCacheUtils.setex(guideKey, queryExpireTimeByScm(), JsonUtils.object2Json(guideInfoDto));
                            }

                        }
                    }
                }
            }
            if(CollectionUtils.isEmpty(storeGuideList)){
                //匹配不到则查询sntk数据库
                //查询电器导购存入redis
                saveElectricStoreGuideToCache();
                //查询母婴导购存入redis
                saveInfantStoreGuideToCache();
            }
        } else {
            //匹配不到则查询sntk数据库
            //查询电器导购存入redis
            saveElectricStoreGuideToCache();
            //查询母婴导购存入redis
            saveInfantStoreGuideToCache();
        }

    }

    @Override
    public void saveGuideInfoToCacheByCity() {
        //查询所有城市Id
        List<String> cityList = guideInfoDao.queryAllGuideCity();
        LOGGER.info("queryAllGuideCity param: cityList={}", cityList);

        //查询所有三级类目
        List<CategoryOutRelDto> categoryList = guideInfoDao.queryAllThreeDirectoryCategory();
        LOGGER.info("queryAllThreeDirectoryCategory param: categoryList={}", categoryList);

        if (CollectionUtils.isEmpty(cityList) || CollectionUtils.isEmpty(categoryList)) {
            return;
        }

        for (String cityId : cityList) {
            for (CategoryOutRelDto category : categoryList) {
                String outCode = category.getOutCode();
                String cateCode = category.getCategoryCode();
                String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_THREE_CATEGORY, cityId, outCode);
                Set<String> guideInfoSet = new HashSet<>();
                //通过城市、品类查询导购信息
                List<StoreGuideInfoDto> guideInfoList = guideInfoDao.queryGuideByCityAndCategory(cityId, cateCode);
                for (StoreGuideInfoDto guideInfo : guideInfoList) {
                    queryGuideOrderNumAndPraise(guideInfo);
                    guideInfoSet.add(JsonUtils.object2Json(guideInfo));
                }
                redisCacheUtils.batchSetAdd(key, guideInfoSet);
                redisCacheUtils.expire(key, queryExpireTimeByScm());
            }
        }

    }

    @Override
    public void saveGuideInfoToCacheByCityAndBusinessType() {
        //查询所有城市Id
        List<String> cityList = guideInfoDao.queryAllGuideCity();
        LOGGER.info("queryAllGuideCity param: cityList={}", cityList);

        if (CollectionUtils.isEmpty(cityList)) {
            return;
        }

        for (String cityId : cityList) {
            //通过城市Id查询导购信息
            List<StoreGuideInfoDto> guideInfoList = guideInfoDao.queryGuideByCity(cityId);
            for (StoreGuideInfoDto guideInfo : guideInfoList) {
                queryGuideOrderNumAndPraise(guideInfo);
                String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_BUSINESS_TYPE, cityId, guideInfo.getBusinessType());
                redisCacheUtils.sadd(key, JsonUtils.object2Json(guideInfo));
                redisCacheUtils.expire(key, queryExpireTimeByScm());
            }
        }
    }

    @Override
    public void saveAllGuideInfoToCache() {
        Long count = guideInfoDao.queryAllGuideInfoCount();
        if (null != count && count > 0) {
            int queryTimes = (int) (count / PAGE_SIZE + 1);
            for (int i = 0; i < queryTimes; i++) {
                List<StoreGuideInfoDto> guideInfoList = guideInfoDao.queryAllGuideInfo(i * PAGE_SIZE, PAGE_SIZE);
                LOGGER.info("queryAllGuideInfo param: guideInfoList={}", guideInfoList);

                if (CollectionUtils.isEmpty(guideInfoList)) {
                    return;
                }
                //将导购信息guideId作为key，guideInfo作为value存入redis
                for (StoreGuideInfoDto guideInfo : guideInfoList) {
                    queryGuideOrderNumAndPraise(guideInfo);
                    String guideKey = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_INFO, guideInfo.getGuideId());
                    redisCacheUtils.setex(guideKey, queryExpireTimeByScm(), JsonUtils.object2Json(guideInfo));
                }
            }
        }

    }

    @Override
    public void saveAllVgoStoreListToCache() {
        Long count = guideInfoDao.queryAllVgoStoreListCount(StringUtils.EMPTY);
        if (null != count && count > 0) {
            int queryTimes = (int) (count / PAGE_SIZE + 1);
            for (int i = 0; i < queryTimes; i++) {
                List<StoreInfoDto> storeInfoList = guideInfoDao.queryAllVgoStoreList(i * PAGE_SIZE, PAGE_SIZE, StringUtils.EMPTY);
                LOGGER.info("queryVgoStoreListByCityId param: storeInfoList={}", storeInfoList);
                if (CollectionUtils.isEmpty(storeInfoList)) {
                    return;
                }
                String storeJsonInfo;
                for (StoreInfoDto storeInfo : storeInfoList) {
                    if (StringUtils.isNotBlank(storeInfo.getCityId())) {
                        storeJsonInfo = JsonUtils.object2Json(storeInfo);
                        //key:cityId、districtId、businessType value：storeInfo
                        String cityDistrictBusinessTypeKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_DISTRICT_BUSINESS_TYPE,
                                storeInfo.getCityId(), storeInfo.getDistrictId(), storeInfo.getBusinessType());
                        redisCacheUtils.sadd(cityDistrictBusinessTypeKey, storeJsonInfo);
                        redisCacheUtils.expire(cityDistrictBusinessTypeKey, queryExpireTimeByScm());

                        //key：cityId、businessType value：storeInfo
                        String cityBusinessTypeKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_STORE, storeInfo.getCityId(),
                                storeInfo.getBusinessType());
                        redisCacheUtils.sadd(cityBusinessTypeKey, storeJsonInfo);
                        redisCacheUtils.expire(cityBusinessTypeKey, queryExpireTimeByScm());
                    }
                }
            }
        }

    }

    /**
     * 查询导购服务数、获赞数、母婴导购服务项目、默认头像
     *
     * @param guideInfo 导购信息
     * @author 18041004_余长杰
     * @since 10:13 2018/10/8
     */
    @Override
    public void queryGuideOrderNumAndPraise(StoreGuideInfoDto guideInfo) {
        //接单数为：库中的orderNum + 预约数 * 3
        String orderNum = vgoCommonService.queryOrderNumAndReceivePraise(guideInfo.getGuideId()).get("orderNum");
        guideInfo.setOrderNum(orderNum);
        //获赞数
        String receivePraise = vgoCommonService.queryOrderNumAndReceivePraise(guideInfo.getGuideId()).get("receivePraise");
        guideInfo.setReceivePraise(receivePraise);

        //查询母婴导购服务项目
        if (BusinessTypesEnum.MOM_INFANT.getCode().equals(guideInfo.getBusinessType())) {
            guideInfo.setCategoryName(vgoCommonService.queryInfantGuideServiceItem(guideInfo.getGuideId()));
        }

        //导购头像为空则调HR系统查询店员性别，从SCM上获取默认头像
        if (StringUtils.isBlank(guideInfo.getGuidePhoto())) {
            guideInfo.setGuidePhoto(vgoCommonService.queryGuideDefaultPhoto(guideInfo.getGuideId()));
        }
    }

    /**
     * 根据门店编码、品类查询电器导购
     *
     * @author 18041004_余长杰
     * @since 14:23 2018/9/3
     */
    private void saveElectricStoreGuideToCache() {
        Long count = guideInfoDao.queryAllVgoStoreListCount(BusinessTypesEnum.ELECTRIC.getCode());
        if (null != count && count > 0) {
            int queryTimes = (int) (count / PAGE_SIZE + 1);
            for (int i = 0; i < queryTimes; i++) {
                //查询门店列表(电器业态)
                List<StoreInfoDto> storeInfoList = guideInfoDao.queryAllVgoStoreList(i * PAGE_SIZE, PAGE_SIZE,
                        BusinessTypesEnum.ELECTRIC.getCode());

                //查询品类
                List<CategoryOutRelDto> categoryList = guideInfoDao.queryAllThreeDirectoryCategory();

                if (CollectionUtils.isEmpty(storeInfoList) || CollectionUtils.isEmpty(categoryList)) {
                    return;
                }

                for (StoreInfoDto storeInfo : storeInfoList) {
                    for (CategoryOutRelDto categoryOutRel : categoryList) {
                        String storeCode = storeInfo.getStoreId();
                        String cateCode = categoryOutRel.getCategoryCode();
                        //根据门店编码、品类查询电器导购
                        List<StoreGuideInfoDto> electricGuideList = guideInfoDao.queryElectricStoreGuide(storeCode, cateCode);
                        if (CollectionUtils.isNotEmpty(electricGuideList)) {
                            //随机存一个到redis
                            String key = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode, cateCode,
                                    BusinessTypesEnum.ELECTRIC.getCode());
                            //获取接单数、获赞数
                            StoreGuideInfoDto guideInfo = electricGuideList.get(RandomNumberUtil.getRandomNum(electricGuideList.size()));
                            queryGuideOrderNumAndPraise(guideInfo);
                            redisCacheUtils.setex(key, queryExpireTimeByScm(), JsonUtils.object2Json(guideInfo));
                        }

                    }
                }
            }
        }
    }

    /**
     * 根据门店编码查询母婴导购
     *
     * @author 18041004_余长杰
     * @since 14:23 2018/9/3
     */
    private void saveInfantStoreGuideToCache() {
        Long count = guideInfoDao.queryAllVgoStoreListCount(BusinessTypesEnum.MOM_INFANT.getCode());
        if (null != count && count > 0) {
            int queryTimes = (int) (count / PAGE_SIZE + 1);
            for (int i = 0; i < queryTimes; i++) {
                //查询门店列表(母婴业态)
                List<StoreInfoDto> storeInfoList = guideInfoDao.queryAllVgoStoreList(i * PAGE_SIZE, PAGE_SIZE,
                        BusinessTypesEnum.MOM_INFANT.getCode());

                if (CollectionUtils.isEmpty(storeInfoList)) {
                    return;
                }
                for (StoreInfoDto storeInfo : storeInfoList) {
                    String storeCode = storeInfo.getStoreId();
                    //查询母婴导购信息
                    List<StoreGuideInfoDto> infantGuideList = guideInfoDao.queryInfantStoreGuide(storeCode);
                    if (CollectionUtils.isNotEmpty(infantGuideList)) {
                        //随机存一个到redis
                        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode,
                                GuideInfoConstants.SERVER_NAME, BusinessTypesEnum.MOM_INFANT.getCode());
                        //获取接单数、获赞数
                        StoreGuideInfoDto guideInfo = infantGuideList.get(RandomNumberUtil.getRandomNum(infantGuideList.size()));
                        queryGuideOrderNumAndPraise(guideInfo);
                        redisCacheUtils.setex(key, queryExpireTimeByScm(), JsonUtils.object2Json(guideInfo));
                    }

                }
            }
        }

    }

    /**
     * 从SCM上获取redis过期时间
     *
     * @return int
     * @author 18041004_余长杰
     * @since 9:57 2018/10/19
     */
    private int queryExpireTimeByScm() {
        int expireTime = NumberUtils.toInt(ScmPropertiesUtil
                .getConfig(ScmPropertyFileEnum.SNTK_ADMIN_CONFIG, GuideInfoConstants.EXPIRE_TIME));
        LOGGER.info("VgoUpdateRedisServiceImpl:获取scm配置的缓存时间为:{}", expireTime);
        return expireTime == 0 ? VgoCacheKeyConstants.ONE_DAY_TIME : expireTime;
    }

}
