/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffManageServiceTest
 * Author:   88397670_张辉
 * Date:     2018-7-13 9:11
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package staffwhitelist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;
import com.suning.sntk.admin.service.staffwhitelist.StaffManageService;
import com.suning.sntk.admin.vo.StaffInfoVo;
import com.suning.sntk.dto.microservice.StaffBatchInfoDto;
import com.suning.sntk.dto.microservice.StaffInfoDto;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.sntk.support.enums.StationEnum;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;
import com.suning.store.dal.base.ParamBuilder;

/**
 * 功能描述：
 *
 * @author 88397670_张辉
 * @since 2018-7-13
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class StaffManageServiceTest extends AbstractTestNGSpringContextTests {

    private Logger logger = LoggerFactory.getLogger(StaffManageServiceTest.class);

    @Autowired
    private StaffManageService staffManageService;

    @Test
    public void queryStaffInfo(){
        StaffInfoDto staffInfoDto = new StaffInfoDto();
        Pageable pageable= ParamBuilder.newPageable(0,10);
        Page<StaffInfoVo> staffInfoVoPage = staffManageService.queryStaffInfo(staffInfoDto,pageable);
        logger.info("分页查询人员白名单信息，staffInfoVoPage:{}",staffInfoVoPage);
    }

    @Test
    public void modifyStaffStatus(){
        String ids = "123,2321,323,12333,4223";
        Integer validFlag = 1;
        String updateUser = "UnitTest";
        staffManageService.modifyStaffStatus(ids,validFlag,updateUser);
    }

    @Test
    public void deleteStaffInfo(){
       String ids = "123,2321,323,12333,4223";
       staffManageService.deleteStaffInfo(ids);
    }

    @Test
    public void queryExtByStaffId(){
        String staffId = "88323848";
        EmployeeInfo opreateUser = new EmployeeInfo();
        opreateUser.setOrgLevel("0");
        StaffBatchInfoDto staffBatchInfoDto = staffManageService.queryExtByStaffId(staffId,opreateUser);
        logger.info("根据工号查询员工组织信息，staffBatchInfoDto:{}",staffBatchInfoDto);
    }

    @Test
    public void addStaffInfoByOrgPos(){
        StaffInfoDto staffInfoDto = new StaffInfoDto();
        staffInfoDto.setAreaCode("10001");
        staffInfoDto.setAreaName("南京");
        staffInfoDto.setCompanyCode("1001");
        staffInfoDto.setCompanyName("南京");
        staffInfoDto.setStoreCode("7611");
        staffInfoDto.setStoreName("促销员组");
        staffInfoDto.setStationCode(StationEnum.SALE_PROMOTION.getStation());
        staffInfoDto.setStation(StationEnum.SALE_PROMOTION.getDescription());
        EmployeeInfo opreateUser = new EmployeeInfo();
        opreateUser.setOrgLevel("4");
        opreateUser.setStaffId("UnitTest");
        String message = staffManageService.addStaffInfoByOrgPos(staffInfoDto,opreateUser);
        logger.info("通过组织查询员工信息并添加到人员白名单,message:{}",message);
    }

    @Test
    public void addStaffInfo(){
        List<StaffInfoDto> staffInfoList = new ArrayList<>();
        EmployeeInfo opreateUser = new EmployeeInfo();
        opreateUser.setOrgLevel("0");
        opreateUser.setStaffId("UnitTest");
        StaffInfoDto staffInfoDto = new StaffInfoDto();
        staffInfoDto.setStaffId("10083099");
        staffInfoDto.setStaffName("刘芬");
        staffInfoDto.setAreaCode("10001");
        staffInfoDto.setAreaName("南京");
        staffInfoDto.setCompanyCode("1001");
        staffInfoDto.setCompanyName("南京");
        staffInfoDto.setStoreCode("7613");
        staffInfoDto.setStoreName("UnitTest");
        staffInfoDto.setStation("服务专员");
        staffInfoDto.setErrorFlag(1);
        staffInfoList.add(staffInfoDto);
        staffManageService.addStaffInfo(staffInfoList,opreateUser);
    }

    @Test
    public void judgeAndConversionSender(){
        Staff staff = new Staff();
        staff.setStaffId(null);
        staff.setStaffName("刘芬");
        staff.setAreaCode(null);
        staff.setAreaName("南京");
        staff.setCompanyCode("1001");
        staff.setCompanyName("南京");
        staff.setStoreCode(null);
        staff.setStoreName(StringUtils.EMPTY);
        staff.setStation("销售指导");
        staff.setErrorFalg(1);
        staff.setValidFlag(2);
        staff.setDeleteFalg1(1);
        staff.setRemark("UnitTest");
        staff.setCreater("UniTest");
        staff.setCreateTime(new Date());
        staff.setUpdater("UniTest");
        staff.setUpdateTime(new Date());
        StaffSenderDto staffSenderDto =staffManageService.judgeAndConversionSender(staff);
        logger.info("组装发送参数为：{}",staffSenderDto);
    }
}
