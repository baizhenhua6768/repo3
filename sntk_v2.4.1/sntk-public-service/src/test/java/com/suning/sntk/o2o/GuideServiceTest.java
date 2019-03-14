/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GuideServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/6 16:11
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.o2o;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.o2o.GuideDao;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.service.o2o.GuideService;


/**
 * 功能描述：中台导购业务试类
 *
 * @author 88402362 欧小冬
 * @since 2018-7-6
 */
public class GuideServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuideServiceTest.class);

    @Autowired
    private GuideDao guideDao;

    @Autowired
    private GuideService guideService;

    private String staffId ;

    private String custNo ;

    @BeforeClass
    public void init(){
        staffId = "";
        custNo= "";
    }

    //    @Test
    //    public void deleteRelByStaffIdAndCustNoTest(){
    //        boolean b = guideDao.deleteRelByStaffIdAndCustNo(staffId, custNo);
    //        LOGGER.info("deleteRelByStaffIdAndCustNoTest,result[{}]", b);
    //        guideService.deleteRelByStaffIdAndCustNo(staffId, custNo);
    //    }

    //    @Test
    //    public void saveStaffCustRelTest(){
    //        StaffCustRel staffCustRel= new StaffCustRel();
    //        staffCustRel.setId(9876L);
    //        StaffCustRel rel = guideService.create(staffCustRel);
    //        LOGGER.info("saveStaffCustRelTest,result[{}]" , rel);
    //    }

    @Test
    public void findRelByCustNoAndStaffIdTest(){
        StaffCustRel rel = guideDao.findRelByCustNoAndStaffId(staffId, custNo);
        LOGGER.info("findRelByCustNoAndStaffIdTest,result[{}]" , rel);
        rel = this.guideService.findRelByCustNoAndStaffId(staffId, custNo);
        LOGGER.info("serviceTest.findRelByCustNoAndStaffIdTest,result[{}]" , rel);
    }

    @Test
    public void queryStaffCustListByCustNoTest(){
        List<StaffCustRel> list = this.guideDao.queryStaffCustListByCustNo(custNo);
        LOGGER.info("queryStaffCustListByCustNoTest,result[{}]", list);
        list = this.guideService.queryStaffCustListByCustNo(custNo);
        LOGGER.info("serviceTest.queryStaffCustListByCustNoTest,result[{}]", list);
    }
}
