/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfoCacheJob
 * Author:   17061157_王薛
 * Date:     2018/9/21 14:05
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.job;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Nullable;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.common.StoreGeoDto;
import com.suning.sntk.rsf.common.StoreRsfService;
import com.suning.sntk.support.common.VgoStoreConstants;
import static com.suning.sntk.support.common.VgoStoreConstants.STORE_ELEC_KEY;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.StoreBusinessTypeEnum;
import com.suning.sntk.support.util.RandomNumberUtil;
import com.suning.sntk.web.util.LocationUtils;
import com.suning.sntk.web.util.ScmWebConfigUtil;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 加载门店经纬度信息 job
 *
 * @author 17061157_王薛
 * @since 2018/9/21
 */
@Component
public class StoreInfoCacheJob implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreInfoCacheJob.class);

    private StoreRsfService storeRsfService = ServiceLocator.getService(StoreRsfService.class, "1.0.0");

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    private final static int PAGE_SIZE = 1000;

    private final static int QUERY_STORE_CACHE_SIZE = 15;

    private static final String JOB_SWICTH_SCM_KEY = "store.cache.job.switch";

    // 默认更新门店任务开启状态
    private static final String DEFAULT_JOB_OPEN = "0";

    private static final String DEFAULT_JOB_CLOSE = "1";

    /**
     * 更新门店经纬度定时器-启动服务后延迟启动任务时间最小单位-2分钟
     */
    private static final long TIMER_DELAY_UNIT = 1000 * 60 * 2L;

    /**
     * 更新门店经纬度定时器-刷新门店信息时间间隔-0.5小时
     */
    private static final long TIMER_PERIOD = 1000 * 60 * 30L;

    /**
     * 门店-经纬度GEOHash缓存：电器业态，精度为4，误差20公里
     */
    private static final Map<String, List<StoreGeoDto>> STORE_PER4_ELEC_MAP = new HashMap<String, List<StoreGeoDto>>();

    /**
     * 门店-经纬度GEOHash缓存：电器业态，精度为3，误差78公里
     */
    private static final Map<String, List<StoreGeoDto>> STORE_PER3_ELEC_MAP = new HashMap<String, List<StoreGeoDto>>();

    /**
     * 门店-经纬度GEOHash缓存：母婴业态，精度为4，误差20公里
     */
    private static final Map<String, List<StoreGeoDto>> STORE_PER4_BABY_MAP = new HashMap<String, List<StoreGeoDto>>();

    /**
     * 门店-经纬度GEOHash缓存：母婴业态，精度为3，误差78公里
     */
    private static final Map<String, List<StoreGeoDto>> STORE_PER3_BABY_MAP = new HashMap<String, List<StoreGeoDto>>();

    @Override
    public void afterPropertiesSet() {
        try {
            loadGeoStoreMap();
            initTimer();
        } catch (Exception e) {
            LOGGER.error("loadGeoStoreMap Exception:{}", e);
        }
    }

    /**
     * 功能描述: 初始化GEOHASH值门店分组 <br>
     *
     * @return void
     * @author 17061157_王薛
     * @since 16:32  2018/9/3
     */
    public void loadGeoStoreMap() {
        LOGGER.info("加载门店经纬度信息开始...");
        Long count = storeRsfService.queryStoreCount().getData();
        LOGGER.info("待加载门店数量：{}", count);
        if (null != count && count > 0) {
            int queryTimes = (int) (count / PAGE_SIZE + 1);
            for (int i = 0; i < queryTimes; i++) {
                // 查询门店信息，并组装缓存信息
                List<StoreGeoDto> storeList = queryStoreInfoList(i);
                cacheStoreGeoHash(storeList);
            }
        }
        LOGGER.info("Redis已加载门店数量: ELEC={}, BABY={}",
                redisCacheUtils.hlen(VgoStoreConstants.STORE_ELEC_KEY), redisCacheUtils.hlen(VgoStoreConstants.STORE_BABY_KEY));
        LOGGER.info("加载门店经纬度信息结束...");
    }

    /**
     * 查询指定页的门店列表
     *
     * @param pageNo 分页参数
     * @return
     */
    private List<StoreGeoDto> queryStoreInfoList(int pageNo) {
        RsfResponseDto<List<StoreGeoDto>> response = storeRsfService.queryStorePageList(pageNo * PAGE_SIZE, PAGE_SIZE);
        if (response.isSuccess() && response.getData() != null) {
            return response.getData();
        }
        return Collections.emptyList();
    }

    /**
     * 缓存门店信息到本地缓存
     *
     * @param storeList
     */
    private void cacheStoreGeoHash(List<StoreGeoDto> storeList) {
        if (CollectionUtils.isEmpty(storeList)) {
            return;
        }

        for (StoreGeoDto store : storeList) {
            if (store != null) {
                LOGGER.info("cacheStoreGeoHash StoreInfo:{}", store);
                String geoHash = LocationUtils.calcGeoHashCode6(store.getLatitude(), store.getLongitude());
                String geoHashCode4 = geoHash.substring(0, 4);
                String geoHashCode3 = geoHash.substring(0, 3);

                store.setGeoHash6Code(geoHash);
                if (StoreBusinessTypeEnum.ELEC.getCode().equals(store.getBusinessType())) {
                    // 缓存电器业态门店位置信息
                    cacheStoreGeoHash0(store, geoHashCode4, STORE_PER4_ELEC_MAP);
                    cacheStoreGeoHash0(store, geoHashCode3, STORE_PER3_ELEC_MAP);
                    cacheStoreGeoHashToRedis(store, VgoStoreConstants.STORE_ELEC_KEY);
                } else if (StoreBusinessTypeEnum.BABY.getCode().equals(store.getBusinessType())) {
                    // 缓存母婴业态门店位置信息
                    cacheStoreGeoHash0(store, geoHashCode4, STORE_PER4_BABY_MAP);
                    cacheStoreGeoHash0(store, geoHashCode3, STORE_PER3_BABY_MAP);
                    cacheStoreGeoHashToRedis(store, VgoStoreConstants.STORE_BABY_KEY);
                }
            }
        }
    }

    /**
     * 将门店信息放入本地缓存
     *
     * @param geoStoreDto
     * @param geoHash
     * @param cacheMap
     */
    private void cacheStoreGeoHash0(StoreGeoDto geoStoreDto, String geoHash, Map<String, List<StoreGeoDto>> cacheMap) {
        if (cacheMap.containsKey(geoHash)) {
            List<StoreGeoDto> list = cacheMap.get(geoHash);
            if (CollectionUtils.isNotEmpty(list)) {
                Set<String> storeCds = new HashSet<String>();
                for (StoreGeoDto dto : list) {
                    if (null != dto) {
                        storeCds.add(dto.getStoreCode());
                        if (StringUtils.equals(dto.getStoreCode(), geoStoreDto.getStoreCode())) {
                            // 同一个门店，覆盖
                            dto.copy(geoStoreDto);
                        }
                    }
                }
                // 之前的list中没有就新增
                if (!storeCds.contains(geoStoreDto.getStoreCode())) {
                    list.add(geoStoreDto);
                }
            }
        } else {
            cacheMap.put(geoHash, Lists.newArrayList(geoStoreDto));
        }
    }

    /**
     * 功能描述: 将门店信息放入 redis 缓存 <br>
     *
     * @param geoStoreDto 门店信息
     * @param key         缓存键值
     * @return void
     * @author 17061157_王薛
     * @since 22:24  2018/10/8
     */
    private void cacheStoreGeoHashToRedis(StoreGeoDto geoStoreDto, String key) {
        if (null != geoStoreDto && StringUtils.isNotBlank(geoStoreDto.getStoreCode())) {
            redisCacheUtils.hset(key, geoStoreDto.getStoreCode(), JsonUtils.object2Json(geoStoreDto));
        }
    }

    /**
     * 功能描述: 取最近的门店 <br>
     *
     * @param latitude     当前位置纬度
     * @param longitude    当前位置经度
     * @param businessType 业态类型
     * @return java.lang.String
     * @author 17061157_王薛
     * @since 16:25  2018/9/3
     */
    public static String getNearestStore(double latitude, double longitude, String businessType) {
        String nearestStoreCode = StringUtils.EMPTY;
        try {
            List<StoreGeoDto> list = getNearStoreList(latitude, longitude, businessType);
            if (null == list) {
                // 如果还是没有，那么就是东西南北156公里的范围均没有，基本认为未能匹配到对应门店
                return StringUtils.EMPTY;
            }

            // 匹配到之后遍历计算最近的门店
            double nearDistance = Double.MAX_VALUE;

            for (StoreGeoDto store : list) {
                double distance = LocationUtils.getDistance(latitude, longitude, store.getLatitude(), store.getLongitude());
                if (nearDistance > distance) {
                    nearDistance = distance;
                    nearestStoreCode = store.getStoreCode();
                }
            }
        } catch (Exception e) {
            String errorMessage = String.format("Occur an exception when getNearestStore,latitude=%s,longitude=%s,businessType=%s",
                    latitude, longitude, businessType);
            LOGGER.info(errorMessage, e);
            return StringUtils.EMPTY;
        }
        return nearestStoreCode;
    }

    /**
     * 功能描述: 获取附近的15家门店 <br>
     *
     * @param latitude     当前位置纬度
     * @param longitude    当前位置经度
     * @param businessType 业态类型
     * @return java.util.List<java.lang.String>
     * @author 17061157_王薛
     * @since 16:28  2018/9/5
     */
    public static List<String> getNear15Stores(final double latitude, final double longitude, String businessType) {
        try {
            List<StoreGeoDto> list = getNearStoreList(latitude, longitude, businessType);
            if (CollectionUtils.isEmpty(list)) {
                // 如果还是没有，那么就是东西南北156公里的范围均没有，基本认为未能匹配到对应门店
                return Collections.emptyList();
            }

            if (CollectionUtils.size(list) <= QUERY_STORE_CACHE_SIZE) {
                return extractStoreCode(list);
            } else {
                // 将附近的门店按照距离由近及远排序
                Collections.sort(list, new Comparator<StoreGeoDto>() {
                    @Override
                    public int compare(StoreGeoDto s1, StoreGeoDto s2) {
                        double d1 = LocationUtils.getDistance(latitude, longitude, s1.getLatitude(), s1.getLongitude());
                        double d2 = LocationUtils.getDistance(latitude, longitude, s2.getLatitude(), s2.getLongitude());
                        return d1 <= d2 ? -1 : 1;
                    }
                });
                return extractStoreCode(list.subList(0, QUERY_STORE_CACHE_SIZE));
            }
        } catch (Exception e) {
            LOGGER.info("Occur an Exception when getNearStoreList", e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取GEOHash在同一范围的门店列表
     */
    private static List<StoreGeoDto> getNearStoreList(double lat, double lng, String businessType) {
        List<StoreGeoDto> list = null;
        // 转换用户经纬度为geohash值（6位精度）
        String geoHash = LocationUtils.calcGeoHashCode6(lat, lng);
        String geoHashCode4 = geoHash.substring(0, 4);
        String geoHashCode3 = geoHash.substring(0, 3);

        if (StoreBusinessTypeEnum.ELEC.getCode().equals(businessType)) {
            list = getNearStoreListByGeoHash(geoHashCode4, geoHashCode3, STORE_PER4_ELEC_MAP, STORE_PER3_ELEC_MAP);
        } else if (StoreBusinessTypeEnum.BABY.getCode().equals(businessType)) {
            list = getNearStoreListByGeoHash(geoHashCode4, geoHashCode3, STORE_PER4_BABY_MAP, STORE_PER3_BABY_MAP);
        }

        return list;
    }

    /**
     * 从指定业态缓存中获取GEOHash在同一范围的门店列表
     */
    private static List<StoreGeoDto> getNearStoreListByGeoHash(String geoHashCode4, String geoHashCode3,
            Map<String, List<StoreGeoDto>> pre4Map, Map<String, List<StoreGeoDto>> pre3Map) {
        // 如果4位精度没有匹配到门店，则缩小精度范围，将至3位匹配
        return CollectionUtils.isEmpty(pre4Map.get(geoHashCode4)) ? pre3Map.get(geoHashCode3) : pre4Map.get(geoHashCode4);
    }

    /**
     * 抽取门店编码
     */
    private static List<String> extractStoreCode(List<StoreGeoDto> list) {
        if (CollectionUtils.isEmpty(list)) {
            // 如果还是没有，那么就是东西南北156公里的范围均没有，基本认为未能匹配到对应门店
            return Collections.emptyList();
        }

        return Lists.transform(list, new Function<StoreGeoDto, String>() {
            @Nullable
            @Override
            public String apply(StoreGeoDto input) {
                return input != null ? input.getStoreCode() : StringUtils.EMPTY;
            }
        });
    }

    /**
     * 功能描述: 取JVM缓存门店经纬度信息，测试有无缓存使用<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String queryGeoMap(String key) {
        Map<String, List<StoreGeoDto>> map = new HashMap<String, List<StoreGeoDto>>();
        if ("STORE_PER4_ELEC_MAP".equals(key)) {
            map = STORE_PER4_ELEC_MAP;
        }
        if ("STORE_PER3_ELEC_MAP".equals(key)) {
            map = STORE_PER3_ELEC_MAP;
        }
        if ("STORE_PER4_BABY_MAP".equals(key)) {
            map = STORE_PER4_BABY_MAP;
        }
        if ("STORE_PER3_BABY_MAP".equals(key)) {
            map = STORE_PER3_BABY_MAP;
        }
        return JSON.toJSONString(map);
    }

    /**
     * 功能描述: 初始化，定时更新门店经纬的定时器<br>
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void initTimer() {
        Timer timer = new Timer();
        /*
         *  取1-10之间的随机数 * 2分钟基准单位时间，作为定时器延迟启动时间
         *  防止集群中一起同时触发更新，影响前台调用
         */
        int random = RandomNumberUtil.getRandomNum(10) + 1;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reloadLocalMap();
            }
        }, TIMER_DELAY_UNIT * random, TIMER_PERIOD);
    }

    /**
     * 功能描述: 定时任务执行的方法，重新加载缓存信息 <br>
     *
     * @return void
     * @author 17061157_王薛
     * @since 23:31  2018/10/8
     */
    private void reloadLocalMap() {
        LOGGER.info("reloadLocalMap start...");
        String openFlag = ScmWebConfigUtil.getConfig(JOB_SWICTH_SCM_KEY, DEFAULT_JOB_OPEN);
        if (DEFAULT_JOB_CLOSE.equals(openFlag)) {
            LOGGER.warn("updateStoreLocationCache close");
            return;
        }

        Map<String, List<StoreGeoDto>> elecPre4Map = new HashMap<>();
        Map<String, List<StoreGeoDto>> elecPre3Map = new HashMap<>();
        Map<String, List<StoreGeoDto>> babyPre4Map = new HashMap<>();
        Map<String, List<StoreGeoDto>> babyPre3Map = new HashMap<>();
        loadStoreFromRedis(VgoStoreConstants.STORE_ELEC_KEY, elecPre4Map, elecPre3Map);
        loadStoreFromRedis(VgoStoreConstants.STORE_BABY_KEY, babyPre4Map, babyPre3Map);

        LOGGER.info("reloadLocalMap elecPre4Map:{}, elecPre3Map:{}, babyPre4Map:{}, babyPre3Map:{}",
                elecPre4Map.size(), elecPre3Map.size(), babyPre4Map.size(), babyPre3Map.size());

        STORE_PER4_ELEC_MAP.clear();
        STORE_PER4_ELEC_MAP.putAll(elecPre4Map);

        STORE_PER3_ELEC_MAP.clear();
        STORE_PER3_ELEC_MAP.putAll(elecPre3Map);

        STORE_PER4_BABY_MAP.clear();
        STORE_PER4_BABY_MAP.putAll(babyPre4Map);

        STORE_PER3_BABY_MAP.clear();
        STORE_PER3_BABY_MAP.putAll(babyPre3Map);
        LOGGER.info("reloadLocalMap end...");
    }

    private void loadStoreFromRedis(String redisKey, Map<String, List<StoreGeoDto>> pre4Map,
            Map<String, List<StoreGeoDto>> pre3Map) {
        Map<String, String> storeElecPre4Map = redisCacheUtils.hgetAll(redisKey);
        Collection<String> values = storeElecPre4Map.values();
        for (String value : values) {
            if (value != null) {
                StoreGeoDto geoTemp = JsonUtils.json2Object(value, StoreGeoDto.class);
                if (geoTemp != null && StringUtils.length(geoTemp.getGeoHash6Code()) >= 6) {
                    cacheStoreGeoHash0(geoTemp, geoTemp.getGeoHash6Code().substring(0, 4), pre4Map);
                    cacheStoreGeoHash0(geoTemp, geoTemp.getGeoHash6Code().substring(0, 3), pre3Map);
                }
            }
        }
    }
}
