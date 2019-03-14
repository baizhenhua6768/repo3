/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoModifyRedisServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-10-8 10:07
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo.impl;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Iterator;
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
import com.suning.sntk.dao.vgo.CategoryRelDao;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.dao.vgo.StoreDetailDao;
import com.suning.sntk.dto.vgo.StoreGuideInfoDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.sntk.service.vgo.VgoModifyRedisService;
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
 * 功能描述：删除V购redis缓存
 *
 * @author 88396455_白振华
 * @since 2018-10-8
 */
@Service
public class VgoModifyRedisServiceImpl implements VgoModifyRedisService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VgoModifyRedisServiceImpl.class);

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private CategoryRelDao categoryRelDao;

    @Autowired
    private StoreDetailDao storeDetailDao;

    @Autowired
    private GuideInfoDao guideInfoDao;

    @Autowired
    private VgoUpdateRedisService vgoUpdateRedisService;

    @Autowired
    private BaoziConsumerService baoziConsumerService;

    @Override
    public void changeCustomerManagerCache(String custNo, String guideId, String businessType) {
        try {
            String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CUST_MANAGER, custNo);
            if (redisCacheUtils.hexists(key, businessType)) {
                redisCacheUtils.hdel(key, businessType);
            }
            //根据staffId查询客户经理信息
            StoreGuideInfoDto guideInfo = guideInfoDao.queryAllGuideInfoByGuideId(guideId);
            if (null != guideInfo && StringUtils.isNotBlank(guideInfo.getGuideId())) {
                //查询导购服务数、获赞数、母婴导购服务项目、默认头像
                vgoUpdateRedisService.queryGuideOrderNumAndPraise(guideInfo);

                //将key:custNo field:businessType value:guideInfo存入redis
                String custNoKey = MessageFormat.format(VgoCacheKeyConstants.KEY_CUST_MANAGER, custNo);
                redisCacheUtils.hset(custNoKey, guideInfo.getBusinessType(), JsonUtils.object2Json(guideInfo), queryExpireTimeByScm());
            }
        } catch (Exception e) {
            LOGGER.error("VgoModifyRedisServiceImpl.changeCustomerManagerCache error{}", e);
        }
    }

    @Override
    public void deleteGuideInfoCache(String guideId, String storeCode, String businessType) {
        try {
            String cityCode = storeDetailDao.queryCityCodeByStoreCode(storeCode);
            //删除门店-品类-业态-导购信息
            deleteStoreCateGuide(guideId, storeCode, businessType);
            //电器业态，删除城市-三级类目-导购信息
            if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
                deleteCityThreeLevelCateGuide(guideId, cityCode);
            }
            //删除城市-业态-导购信息
            deleteCityBusinessTypeGuide(guideId, cityCode, businessType);
            //删除城市-会员编号后四位-业态-导购信息
            deleteCityCustBusinessTypeGuide(guideId);
            //删除导购信息redis缓存
            String key = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_ID, guideId);
            if (redisCacheUtils.exists(key)) {
                redisCacheUtils.del(key);
            }
        } catch (Exception e) {
            LOGGER.error("VgoModifyRedisServiceImpl.deleteGuideInfoCache error{}", e);
        }
    }

    @Override
    public void batchDeleteGuideInfoCache(List<String> guideIdList) {
        try {
            List<GuideInfo> guideInfoList = guideInfoDao.batchQueryGuideInfo(guideIdList);
            if (CollectionUtils.isNotEmpty(guideIdList)) {
                for (GuideInfo guideInfo : guideInfoList) {
                    deleteCityBusinessTypeGuide(guideInfo.getGuideId(), guideInfo.getStoreCode(), guideInfo.getBusinessType());
                }
            }
        } catch (Exception e) {
            LOGGER.error("VgoModifyRedisServiceImpl.batchDeleteGuideInfoCache error{}", e);
        }
    }

    /**
     * 随机匹配一个导购
     *
     * @param storeCode    门店编码
     * @param categoryCode 品类编码
     * @param key          redis的key
     */
    private void matchGuide(String storeCode, String categoryCode, String key) {
        //调用麦琪匹配导购
        List<StoreGuideDto> storeGuideList = baoziConsumerService.queryStoreBestGuideId(storeCode);
        if (CollectionUtils.isNotEmpty(storeGuideList)) {
            for (StoreGuideDto storeGuideDto : storeGuideList) {
                if (BusinessTypesEnum.ELECTRIC.getCode().equals(storeGuideDto.getBusinessType()) && categoryCode
                        .equals(storeGuideDto.getCategoryId())) {
                    String guide = storeGuideDto.getGuideId();
                    StoreGuideInfoDto storeGuideInfo = guideInfoDao.queryGuideInfoByGuideId(guide);
                    //查询导购服务数、获赞数、母婴导购服务项目、默认头像
                    vgoUpdateRedisService.queryGuideOrderNumAndPraise(storeGuideInfo);
                    //将key:guideId  value:guideInfo存入redis
                    redisCacheUtils.setex(key, queryExpireTimeByScm(), JsonUtils.object2Json(storeGuideInfo));
                }
            }
        } else {
            //匹配不到则查询sntk数据库
            //查询电器导购存入redis
            //根据门店编码、品类查询电器导购
            List<StoreGuideInfoDto> electricGuideList = guideInfoDao.queryElectricStoreGuide(storeCode, categoryCode);
            if (CollectionUtils.isNotEmpty(electricGuideList)) {
                StoreGuideInfoDto guideInfo = electricGuideList
                        .get(RandomNumberUtil.getRandomNum(electricGuideList.size()));
                //获取接单数、获赞数
                vgoUpdateRedisService.queryGuideOrderNumAndPraise(guideInfo);
                redisCacheUtils.setex(key, queryExpireTimeByScm(), JsonUtils.object2Json(guideInfo));
            }
        }
    }

    /**
     * 删除城市-会员编号后四位-业态-导购信息
     *
     * @param guideId 导购编号
     */
    private void deleteCityCustBusinessTypeGuide(String guideId) {
        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_GUIDE_ID, guideId);
        String delKey = redisCacheUtils.get(key);
        if (StringUtils.isNotBlank(delKey)) {
            redisCacheUtils.del(delKey);
        }
    }

    /**
     * 删除城市-业态-导购信息
     *
     * @param guideId      导购工号
     * @param cityCode     城市编码
     * @param businessType 业态
     */
    private void deleteCityBusinessTypeGuide(String guideId, String cityCode, String businessType) {
        String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_BUSINESS_TYPE, cityCode, businessType);
        Set<String> guideInfos = redisCacheUtils.smembers(key);
        if (CollectionUtils.isNotEmpty(guideInfos)) {
            Iterator<String> iterator = guideInfos.iterator();
            while (iterator.hasNext()) {
                String guideInfo = iterator.next();
                if (guideId.equals(JsonUtils.json2Object(guideInfo, StoreGuideInfoDto.class).getGuideId())) {
                    iterator.remove();
                }
            }
            redisCacheUtils.del(key);
            if (CollectionUtils.isNotEmpty(guideInfos)) {
                redisCacheUtils.batchSetAdd(key, guideInfos);
                redisCacheUtils.expire(key, queryExpireTimeByScm());
            }
        }
    }

    /**
     * 删除城市三级类目对应的导购信息
     *
     * @param guideId  导购工号
     * @param cityCode 城市编码
     */
    private void deleteCityThreeLevelCateGuide(String guideId, String cityCode) {
        List<String> outRelList = categoryRelDao.queryCategoryOutRel(guideId);
        Set<String> outRels = new HashSet<String>(outRelList);
        if (CollectionUtils.isNotEmpty(outRels)) {
            for (String outRel : outRels) {
                String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CITY_THREE_CATEGORY, cityCode, outRel);
                Set<String> guideInfos = redisCacheUtils.smembers(key);
                if (CollectionUtils.isNotEmpty(guideInfos)) {
                    Iterator<String> iterator = guideInfos.iterator();
                    while (iterator.hasNext()) {
                        String guideInfo = iterator.next();
                        StoreGuideInfoDto storeGuideInfoDto = JsonUtils.json2Object(guideInfo, StoreGuideInfoDto.class);
                        if (guideId.equals(storeGuideInfoDto.getGuideId())) {
                            iterator.remove();
                        }
                    }
                    redisCacheUtils.del(key);
                    if (CollectionUtils.isNotEmpty(guideInfos)) {
                        redisCacheUtils.batchSetAdd(key, guideInfos);
                        redisCacheUtils.expire(key, queryExpireTimeByScm());
                    }
                }
            }
        }
    }

    /**
     * 删除门店品类导购信息
     *
     * @param guideId      导购工号
     * @param storeCode    门店编码
     * @param businessType 业态
     */
    private void deleteStoreCateGuide(String guideId, String storeCode, String businessType) {
        if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
            List<VcategoryRelInfoDto> vcategoryRelInfoDtos = categoryRelDao.queryGuideCategoryRel(guideId);
            for (VcategoryRelInfoDto vcategoryRelInfoDto : vcategoryRelInfoDtos) {
                String key = MessageFormat.format(
                        VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode, vcategoryRelInfoDto.getCategoryCode(), businessType);
                changeStoreGuide(guideId, key);
            }
        } else if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
            String key = MessageFormat
                    .format(VgoCacheKeyConstants.KEY_STORE_GUIDE_CATEGORY, storeCode, GuideInfoConstants.SERVER_NAME, businessType);
            changeStoreGuide(guideId, key);
        } else {
            LOGGER.error("VgoModifyRedisServiceImpl.deleteStoreCateGuide businessType error:[businessType:{}]", businessType);
        }
    }

    /**
     * 更换门店导购
     *
     * @param guideId 导购工号
     * @param key     redis存储key
     */
    private void changeStoreGuide(String guideId, String key) {
        StoreGuideInfoDto storeGuideInfoDto = JsonUtils.json2Object(redisCacheUtils.get(key), StoreGuideInfoDto.class);
        //如果redis中的导购是当前删除的导购，则删除缓存，并重新匹配一个导购
        if (null != storeGuideInfoDto && StringUtils.isNotBlank(storeGuideInfoDto.getGuideId()) && guideId
                .equals(storeGuideInfoDto.getGuideId())) {
            redisCacheUtils.del(key);
            matchGuide(storeGuideInfoDto.getStoreCode(), storeGuideInfoDto.getCategoryId(), key);
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
        //先从后台scm配置中获取缓存时间
        int expireTime = NumberUtils.toInt(ScmPropertiesUtil
                .getConfig(ScmPropertyFileEnum.SNTK_ADMIN_CONFIG, GuideInfoConstants.EXPIRE_TIME));
        //如果是中台service调用，则后台获取不到scm配置的缓存时间，此时从中台scm获取
        if (0 == expireTime) {
            expireTime = NumberUtils.toInt(ScmPropertiesUtil
                    .getConfig(ScmPropertyFileEnum.SNTK_SERVICE_WEB_CONFIG, GuideInfoConstants.EXPIRE_TIME));
        }
        LOGGER.info("VgoModifyRedisServiceImpl:获取scm配置的缓存时间为:{}", expireTime);
        return expireTime == 0 ? VgoCacheKeyConstants.ONE_DAY_TIME : expireTime;
    }
}
