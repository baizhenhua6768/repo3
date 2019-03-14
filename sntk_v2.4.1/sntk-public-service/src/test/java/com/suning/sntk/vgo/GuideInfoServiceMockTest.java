/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoServiceMockTest
 * Author:   18041004_余长杰
 * Date:     2018/9/21 11:01
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.vgo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;

import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.dao.vgo.GuideInfoDao;
import com.suning.sntk.service.vgo.impl.GuideInfoServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;

/**
 * 功能描述：
 *
 * @author 18041004_余长杰
 * @since 2018/9/21
 */
public class GuideInfoServiceMockTest {

    @InjectMocks
    private GuideInfoServiceImpl guideInfoService;

    @Mock
    private RedisCacheUtils redisCacheUtils;

    @Mock
    private GuideInfoDao guideInfoDao;

    @Mock
    private BaoziConsumerService baoziConsumerService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

}
