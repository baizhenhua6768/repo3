/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideCommentServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/6 16:29
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.o2o;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.o2o.GuideCommentDao;
import com.suning.sntk.entity.o2o.GuideComment;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.sntk.service.o2o.impl.GuideCommentServiceImpl;

/**
 * 功能描述：中台导购评测试类
 *
 * @author 88402362 欧小冬
 * @since 2018-7-6
 */
public class GuideCommentServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideCommentServiceTest.class);

    @Autowired
    private GuideCommentDao guideCommentDao;

    @Autowired
    private LabelService labelService;

    @Autowired
    private GuideCommentServiceImpl guideCommentService;

    private String custNo;

    private String staffId;

    private Long id ;

    @BeforeClass
    public void init(){
        custNo = "";
        staffId = "";
        id = new Long(0L);
    }

    @Test
    public void saveGuideCommentTest(){
        GuideComment guideComment = new GuideComment();
        guideComment.setId(id);
        GuideComment insertData = guideCommentService.create(guideComment);
        LOGGER.info("saveGuideCommentTest,result[{}]" , insertData);

    }

    @Test
    public void updateGuideCommentTest(){
        GuideComment guideComment = new GuideComment();
        guideComment.setId(id);
        guideComment.setCustNo(custNo);
        boolean updateSkipNull = guideCommentDao.updateSkipNull(guideComment);
        LOGGER.info("updateGuideCommentTest,result[{}]" , updateSkipNull);
        updateSkipNull = this.guideCommentService.updateGuideComment(guideComment);
        LOGGER.info("sericeTest.updateGuideCommentTest,result[{}]" , updateSkipNull);
    }

    @Test
    public void findCommentByCustNoAndStaffIdTest(){
        GuideComment guideComment = guideCommentDao.
                findCommentsByCustNoAndStaffId(custNo, staffId);
        LOGGER.info("findCommentByCustNoAndStaffIdTest,result[{}]" , guideComment);
        guideComment = this.guideCommentService.findCommentByCustNoAndStaffId(custNo, staffId);
        LOGGER.info("serviceTest.findCommentByCustNoAndStaffIdTest,result[{}]" , guideComment);
    }

    @Test
    public void saveGuideCommentHTest(){
        GuideComment guideComment = new GuideComment();
        guideComment.setId(id);
        guideComment.setCustNo(custNo);
        boolean saveGuideCommentH = guideCommentDao.saveGuideCommentH(guideComment);
        LOGGER.info("saveGuideCommentHTest,result[{}]" , saveGuideCommentH);
        this.guideCommentService.saveGuideCommentH(guideComment);
    }

    @Test
    public void findAvgStarByStaffIdTest(){
        String avgStarNum = guideCommentDao.findAvgStarByStaffId(staffId);
        LOGGER.info("findAvgStarByStaffIdTest,result[{}]" , avgStarNum);
        avgStarNum = this.guideCommentService.findAvgStarByStaffId(staffId);
        LOGGER.info("serviceTest.findAvgStarByStaffIdTest,result[{}]" , avgStarNum);
    }

    @Test
    public void findProfileCommentByStaffIdTest(){
        String labelCodes = guideCommentDao.findProfileCommentByStaffId(staffId);
        LOGGER.info("findProfileCommentByStaffIdTest,result[{}]", labelCodes);
        this.guideCommentService.findProfileCommentByStaffId(staffId);
        LOGGER.info("serviceTest.findProfileCommentByStaffIdTest,result[{}]", labelCodes);
    }

}
