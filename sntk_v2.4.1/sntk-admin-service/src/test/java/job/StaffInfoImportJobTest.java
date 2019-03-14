/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffInfoImportJobTest
 * Author:   88397670_张辉
 * Date:     2018-7-11 19:43
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.suning.sntk.admin.dao.common.ImportFileDao;
import com.suning.sntk.admin.job.staffwhitelist.StaffInfoImportJob;
import com.suning.sntk.admin.service.common.ImportFileService;
import com.suning.sntk.entity.ImportFile;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-7-11
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class StaffInfoImportJobTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private StaffInfoImportJob staffInfoImportJob;

    @Autowired
    private ImportFileDao importFileDao;

    @Test
    public void processImportFile(){
        ImportFile importFile = new ImportFile();
        importFile.setFileName("ssss.xlsx");
        importFile.setFileType(1);
        importFile.setObjectId("th5PTZqFG2Z33kMmzXE11MyFvAju_2DTUNELHRDD2ttELsQh1ThuehXVPqxAeYB7.xlsx");
        importFile.setSize(new Long(10073));
        importFile.setStatus(0);
        importFile.setCreateUser("JobTest");
        importFile.setCreateTime(new Date());
        importFile.setUpdateUser("JobTest");
        importFile.setUpdateTime(new Date());
        ImportFile importFile1 = importFileDao.insert(importFile);
        staffInfoImportJob.processImportFile();
        importFileDao.delete(importFile1);
    }
}
