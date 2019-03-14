/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StaffInfoImportJob
 * Author:   88397670_张辉
 * Date:     2018-7-6 15:54
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.job.staffwhitelist;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.nsfbus.organization.rsfservice.SiteRsfService;
import com.suning.nsfbus.organization.rsfservice.dto.DistrictSaleOrgDto;
import com.suning.nsfuaa.employee.EmployeeService;
import com.suning.nsfuaa.employee.dto.EmployeeExt;
import com.suning.nsfuaa.employee.dto.EmployeeOrgPositionMapping;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.admin.mq.sender.staffwhitelist.StaffSender;
import com.suning.sntk.admin.mq.sender.staffwhitelist.dto.StaffSenderDto;
import com.suning.sntk.admin.service.common.ImportFileService;
import com.suning.sntk.admin.service.staffwhitelist.StaffManageService;
import com.suning.sntk.entity.ImportFile;
import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.sntk.service.system.OssService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.enums.StaffTypeEnum;

/**
 * 功能描述：处理人员管理后台文件导入定时任务
 *
 * @author 88397670_张辉
 * @since 2018-7-6
 */
@Service("staffInfoImportJob")
public class StaffInfoImportJob {

    private Logger logger = LoggerFactory.getLogger(StaffInfoImportJob.class);

    private EmployeeService employeeService = ServiceLocator.getService(EmployeeService.class, null);

    private SiteRsfService siteRsfService = ServiceLocator.getService(SiteRsfService.class, null);

    @Autowired
    private ImportFileService importFileService;

    @Autowired
    private OssService ossService;

    @Autowired
    private StaffManageService staffManageService;

    @Autowired
    private StaffSender staffSender;

    private static final String STORE_LEVEL = "4";

    /**
     * 处理导入待处理的文件
     *
     * @author 88397670_张辉
     * @since 16:07 2018-7-6
     */
    public void processImportFile() {
        logger.info("Enter into StaffInfoImportJob.processImportFile");
        List<ImportFile> importFileList = importFileService.queryProcessedFile();
        ArrayList<StaffSenderDto> staffSenderList = new ArrayList<>();
        if (importFileList.isEmpty()) {
            logger.info("No files need to be processed now! Exit StaffInfoImportJob.processImportFile");
            return;
        }
        for (ImportFile importFile : importFileList) {
            InputStream fileStream = ossService.getFileStream(CommonConstants.FILE_BUCKEN_NAME, importFile.getObjectId());
            Workbook workbook;
            Sheet sheet = null;
            try {
                if (importFile.getFileName().endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook(fileStream);
                } else {
                    workbook = new HSSFWorkbook(fileStream);
                }
                sheet = workbook.getSheetAt(0);
            } catch (IOException | NullPointerException e) {
                logger.error(e.getMessage(), e);
            }
            if (sheet != null) {
                Long count = (long) sheet.getPhysicalNumberOfRows();
                for (int i = 1; i < count; i++) {
                    Row row = sheet.getRow(i);
                    logger.info("解析文件名称:{},第{}行:{}", importFile.getFileName(), i, row);
                    Staff staff = buildStaffInfo(row);
                    StaffSenderDto staffSenderDto = staffManageService.judgeAndConversionSender(staff);
                    if (staffSenderDto != null) {
                        staffSenderList.add(staffSenderDto);
                    }
                }

            }
            //更新文件处理状态为已处理
            importFile.setStatus(2);
        }
        staffSender.sendStaffInfo(staffSenderList, StaffTypeEnum.ADD_STAFF.getStaffStatus());
        importFileService.updateImportFileList(importFileList);
        logger.info("Exit StaffInfoImportJob.processImportFile!");
    }

    /**
     * 表格行转换为人员信息实体
     *
     * @param row 行
     * @author 88397670_张辉
     * @since 8:43 2018-7-9
     */
    private Staff buildStaffInfo(Row row) {
        if (null == row) {
            return null;
        }
        Staff staff = new Staff();
        Date date = new Date();
        staff.setStaffId(getCellSourceValue(row.getCell(0)));
        staff.setStoreCode(getCellSourceValue(row.getCell(1)));
        staff.setStation(getCellSourceValue(row.getCell(2)));
        staff.setCreater("system");
        staff.setCreateTime(date);
        staff.setUpdater("system");
        staff.setUpdateTime(date);
        staff.setDeleteFalg1(1);
        //默认设置工号查询不到组织信息，后面校验通过后再修改
        staff.setValidFlag(3);
        staff.setErrorFalg(1);
        staff.setRemark("工号查询不到对应组织信息");
        EmployeeExt ext = employeeService.queryEmployeeExt(staff.getStaffId());
        staff.setStaffName(ext.getEmployeeName());
        if (ext.getEmployeeOrgPostionMappingList() == null) {
            logger.info("白名单异步解析任务StaffInfoImportJob,工号为：" + staff.getStaffId() + "查询不到组织信息!");
            return staff;
        }
        for (EmployeeOrgPositionMapping employeeOrg : ext.getEmployeeOrgPostionMappingList()) {
            if (employeeOrg.getOrgLevelCode().equals(STORE_LEVEL)) {
                DistrictSaleOrgDto districtSaleOrgDto = siteRsfService.queryDistrictCompanyRelation(employeeOrg.getCompanyCode());
                if (districtSaleOrgDto == null) {
                    logger.info("白名单异步解析任务StaffInfoImportJob,工号为：" + staff.getStaffId() + "查询不到组织信息!");
                    return staff;
                }
                staff.setAreaCode(districtSaleOrgDto.getAreaCode());
                staff.setAreaName(districtSaleOrgDto.getAreaName());
                staff.setCompanyCode(districtSaleOrgDto.getSaleOrgCode());
                staff.setCompanyName(districtSaleOrgDto.getSaleOrgName());
                staff.setStoreName(employeeOrg.getOrgDisplayName());
                if (!staff.getStoreCode().equals(employeeOrg.getOrgCode())) {
                    staff.setValidFlag(3);
                    staff.setErrorFalg(1);
                    staff.setRemark("导入数据中的门店与查询人员的门店信息不符合");
                } else {
                    staff.setValidFlag(1);
                    staff.setErrorFalg(0);
                    staff.setRemark("normal");
                }
                break;
            }
        }
        return staff;
    }

    /**
     * 获取cell中的值
     *
     * @param cell 表格列
     * @author 88397670_张辉
     * @since 20:25 2018-6-6
     */
    private String getCellSourceValue(Cell cell) {
        if (cell == null) {
            return StringUtils.EMPTY;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return formatNumericCellValue(cell);
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
            default:
                return StringUtils.EMPTY;
        }
    }

    /**
     * 格式化cell中numeric类型的值
     *
     * @param cell 单元格
     * @author 88397670_张辉
     * @since 8:39 2018-7-9
     */
    private String formatNumericCellValue(Cell cell) {
        Object inputValue;// 单元格值
        Long longVal = Math.round(cell.getNumericCellValue());
        Double doubleVal = cell.getNumericCellValue();
        if (compareDoubleValue(Double.parseDouble(longVal + ".0"), doubleVal)) {   //判断是否含有小数位.0
            inputValue = longVal;
        } else {
            inputValue = doubleVal;
        }
        DecimalFormat df = new DecimalFormat("#");
        return String.valueOf(df.format(inputValue));
    }

    /**
     * 判断两个double型数据是否相等
     *
     * @param a double数据
     * @param b double数据
     * @author 88397670_张辉
     * @since 8:39 2018-7-9
     */
    private boolean compareDoubleValue(double a, double b) {
        return (a - b > -0.000001) && (a - b) < 0.000001;
    }

}
