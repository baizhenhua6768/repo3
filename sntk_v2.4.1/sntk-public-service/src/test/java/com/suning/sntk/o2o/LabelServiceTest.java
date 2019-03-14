/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/6 15:10
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.o2o;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.collect.Multimaps;
import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.o2o.LabelDao;
import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.PageRequest;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：标签测试类
 *
 * @author 88402362 欧小冬
 * @since 2018-7-6
 */
public class LabelServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LabelServiceTest.class);

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private LabelService labelService;


    private  Label label;

    @BeforeClass
    public void setUp(){
        label = new Label();
        label.setStarLevel(3);
        label.setLabelName("测试");
        label.setLabelCode("3456");
        label.setCreateTime(new Date());
    }

   @Test
    public void queryPageLabelsTest() {
        Pageable pageable = new PageRequest(0,10);
        Page<Label> lables = labelDao.findAll(pageable);
        LOGGER.info("queryPageLabelsTest,result[{}]", lables);
        lables =  labelService.findAll(pageable);
        LOGGER.info("labelServiceaTest.queryPageLabelsTest,result[{}]", lables);
    }

    @Test
    public void queryAllLabelMapTest(){
        List<Label> allLabels = labelDao.findAll();
        Map<String, Collection<Label>> dataMap = Multimaps.index(allLabels, new Function<Label, String>() {
            @Override
            public String apply(Label label) {
                return String.valueOf(label.getStarLevel());
            }
        }).asMap();
        LOGGER.info("queryAllLabelMapTest,result[{}]", dataMap);
        dataMap = this.labelService.queryAllLabelMap();
        LOGGER.info("labelServiceTest.queryAllLabelMapTest,result[{}]", dataMap);
    }

    @Test
    public void queryAllLabelTest(){
        List<Label> allLabels = labelDao.findAll();
        LOGGER.info("queryAllLabelTest,result[{}]" , allLabels);
        allLabels = this.labelService.queryAllLabel();
        LOGGER.info("labelServiceTest.queryAllLabelTest,result[{}]" , allLabels);
    }


    @Test
    public void updateLabelTest(){
        boolean result = this.labelDao.updateSkipNull(label);
        LOGGER.info("updateLabelTest,result[[]]" , result);
        result = this.labelService.updateSkipNull(label);
        LOGGER.info("labelServiceTest.updateLabelTest,result[[]]" , result);
    }

    @Test
    public void deleteLabelTest(){
        boolean deleteById = this.labelDao.deleteById(label.getLabelCode());
        LOGGER.info("deleteLabelTest, result[{}]" , deleteById);
        deleteById = this.labelService.deleteById(label.getLabelCode());
        LOGGER.info("labelServiceTest.deleteLabelTest, result[{}]" , deleteById);
    }

    @Test
    public void isRepeatCodeTest(){
        long count = labelDao.countByCode(label.getLabelCode());
        LOGGER.info("isRepeatCodeTest,result[{}]" , count);
        this.labelService.isRepeatCode(label.getLabelCode());
    }

    @Test
    public void isRepeatNameTest(){
        long count = this.labelDao.countByName(label.getStarLevel(),label.getLabelName());
        LOGGER.info("isRepeatNameTest,result[{}]" , count);
        this.labelService.isRepeatName(label.getStarLevel(),label.getLabelName());
    }

    @Test
    public void findNextLabelCodeByStarTest(){
        String next = labelDao.findLabelCodeByStar(label.getStarLevel());
        LOGGER.info("findNextLabelCodeByStarTest,result[{}]" , next);
        next = this.labelService.findNextLabelCodeByStar(label.getStarLevel());
        LOGGER.info("labelServiceTest.findNextLabelCodeByStarTest,result[{}]" , next);
    }

}
