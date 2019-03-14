/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: CategoryCacheJob
 * Author:   17061157_王薛
 * Date:     2018/9/21 14:05
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.job;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.dto.vgo.CategoryOutRelDto;
import com.suning.sntk.rsf.vgo.VgoCategoryRsfService;
import com.suning.sntk.support.util.RandomNumberUtil;
import com.suning.sntk.web.util.ScmWebConfigUtil;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * 加载三级类目和品类关系 job
 *
 * @author 17061157_王薛
 * @since 2018/10/09
 */
@Component
public class CategoryCacheJob implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryCacheJob.class);

    private VgoCategoryRsfService categoryRsfService = ServiceLocator.getService(VgoCategoryRsfService.class, "1.0.0");

    private static final String JOB_SWICTH_SCM_KEY = "store.cache.job.switch";

    // 默认更新任务开启状态
    private static final String DEFAULT_JOB_OPEN = "0";

    /**
     * 更新三级类目和品类的映射关系定时器-启动服务后延迟启动任务时间最小单位-2分钟
     */
    private static final long TIMER_DELAY_UNIT = 1000 * 60 * 2L;

    /**
     * 定时器-刷新信息时间间隔-0.5小时
     */
    private static final long TIMER_PERIOD = 1000 * 60 * 30L;

    /**
     * 三级类目和品类的映射关系
     */
    private static final ConcurrentHashMap<String, String> THREE_CLASS_CATEGORY_MAP = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() {
        try {
            loadCategoryRel();
            initTimer();
        } catch (Exception e) {
            LOGGER.error("loadCategoryCache Exception:{}", e);
        }
    }

    /**
     * 功能描述: 初始化三级类目和品类的映射关系 <br>
     *
     * @param
     * @return void
     * @author 17061157_王薛
     * @since 16:32  2018/9/3
     */
    private void loadCategoryRel() {
        LOGGER.info("加载三级类目和品类的映射关系开始...");

        String openFlag = ScmWebConfigUtil.getConfig(JOB_SWICTH_SCM_KEY, DEFAULT_JOB_OPEN);

        if (DEFAULT_JOB_OPEN.equals(openFlag)) {
            // 数量比较少，一次全部加载
            List<CategoryOutRelDto> relList = queryALLCategoryRel();
            cacheCategoryRel(relList);
        } else {
            LOGGER.warn("updateStoreLocationCache close");
        }

        LOGGER.info("加载三级类目和品类的映射关系结束...");
    }

    /**
     * 查询全部的品类映射关系
     *
     * @return
     */
    private List<CategoryOutRelDto> queryALLCategoryRel() {
        RsfResponseDto<List<CategoryOutRelDto>> response = categoryRsfService.queryAllThreeDirectoryCategory();
        if (response.isSuccess() && response.getData() != null) {
            return response.getData();
        }
        return Collections.emptyList();
    }

    /**
     * 加载品类关系到本地缓存
     *
     * @param cateList
     */
    public void cacheCategoryRel(List<CategoryOutRelDto> cateList) {
        if (CollectionUtils.isEmpty(cateList)) {
            return;
        }

        for (CategoryOutRelDto category : cateList) {
            LOGGER.info("cacheCategoryRel info:{}", category);
            if (category != null && StringUtils.isNotBlank(category.getOutCode())
                    && StringUtils.isNotBlank(category.getCategoryCode())) {
                THREE_CLASS_CATEGORY_MAP.put(category.getOutCode(), category.getCategoryCode());
            }
        }
    }

    /**
     * 功能描述: 取JVM缓存，测试有无缓存使用<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String queryCategoryMap(String key) {
        LOGGER.debug("queryCategoryMap:{}", key);
        return JSON.toJSONString(THREE_CLASS_CATEGORY_MAP);
    }

    /**
     * 功能描述: 初始化，定时更新类目关系的定时器<br>
     * 〈功能详细描述〉
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
                loadCategoryRel();
            }
        }, TIMER_DELAY_UNIT * random, TIMER_PERIOD);
    }

    /**
     * 功能描述: 根据三级类目查询品类 <br>
     * @author 17061157_王薛
     * @param thirdClass
     * @return java.lang.String
     * @since 6:23  2018/10/9
     */
    public static String getCategoryByThirdClass(String thirdClass) {
        if (StringUtils.isBlank(thirdClass)) {
            return StringUtils.EMPTY;
        }

        return THREE_CLASS_CATEGORY_MAP.get(thirdClass);
    }
}
