/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ShoppingGuideController
 * Author:   88396455_白振华
 * Date:     2018-8-16 9:07
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.controller.vgo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suning.sntk.admin.service.common.ExportFileService;
import com.suning.sntk.admin.service.common.impl.PageableExportProvider;
import com.suning.sntk.admin.service.vgo.ShoppingGuideService;
import com.suning.sntk.admin.service.vgo.StoreDetailService;
import com.suning.sntk.dto.region.EmployeeInfo;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.dto.vgo.GuideInfoRespDto;
import com.suning.sntk.dto.vgo.OrgInfoDto;
import com.suning.sntk.dto.vgo.StaffAuthDto;
import com.suning.sntk.entity.ExportFile;
import com.suning.sntk.enums.FileType;
import com.suning.sntk.interceptor.RequestUserHolder;
import com.suning.sntk.service.system.OssService;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.enums.vgo.VgoAttachEnum;
import com.suning.sntk.support.enums.vgo.VgoHierarchyEnum;
import com.suning.store.commons.lang.BeanUtils;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：导购管理controller
 *
 * @author 88396455_白振华
 * @since 2018-8-16
 */
@Trace
@RestController
@RequestMapping("/admin/guide")
public class ShoppingGuideController {

    /**
     * 导出文件content-type
     */
    private static final String CONTENT_TYPE_EXPORT = "application/force-download";

    /**
     * 下载文件前缀
     */
    private static final String FILENAME_PREFIX = "导购列表";

    /**
     * 下载文件后缀
     */
    private static final String FILENAME_SUFFIX = ".xls";

    /**
     * 文件头
     */
    private static final String HEARDER_CONTENT_DISPOSITION = "Content-Disposition";

    /**
     * 文件头前缀
     */
    private static final String HEADER_ATTACHMENT_PREFIX = "attachment;filename=";

    /**
     * utf-8编码
     */
    private static final String UTF_8 = "UTF-8";

    @Autowired
    private ShoppingGuideService shoppingGuideService;

    @Autowired
    private StoreDetailService storeDetailService;

    @Autowired
    private ExportFileService exportFileService;

    @Autowired
    private OssService ossService;

    /**
     * 根据条件分页查询导购信息
     *
     * @param guideInfoReqDto 　查询导购信息条件
     * @param pageable        　分页信息
     * @author 88396455_白振华
     * @since 10:43  2018-8-16
     */
    @RequestMapping("/queryGuideInfo")
    @ResponseBody
    public Page<GuideInfoRespDto> queryGuideInfoForPage(@RequestBody GuideInfoReqDto guideInfoReqDto, Pageable pageable) {
        return shoppingGuideService.queryGuideInfoForPage(guideInfoReqDto, pageable);
    }

    /**
     * 统计电器业态V购总数
     *
     * @author 88396455_白振华
     * @since 8:56  2018-9-6
     */
    @RequestMapping("/countVgoGuide")
    public Long countVgoGuide(@RequestBody GuideInfoReqDto guideInfoReqDto) {
        guideInfoReqDto.setBusinessType(BusinessTypesEnum.ELECTRIC.getCode());
        return shoppingGuideService.countGuideByBusinessType(guideInfoReqDto);
    }

    /**
     * 统计母婴业态顾问总数
     *
     * @author 88396455_白振华
     * @since 8:56  2018-9-6
     */
    @RequestMapping("/countMomInfantGuide")
    public Long countMomInfantGuide(@RequestBody GuideInfoReqDto guideInfoReqDto) {
        guideInfoReqDto.setBusinessType(BusinessTypesEnum.MOM_INFANT.getCode());
        return shoppingGuideService.countGuideByBusinessType(guideInfoReqDto);
    }

    /**
     * 根据导购工号查询导购详细信息
     *
     * @param guideId      导购工号
     * @param businessType 业态
     * @author 88396455_白振华
     * @since 15:06  2018-8-20
     */
    @RequestMapping("/queryGuideDetail")
    public GuideInfoDto queryGuideDetail(String guideId, String businessType) {
        return shoppingGuideService.queryGuideDetail(guideId, businessType);
    }

    /**
     * 删除导购
     *
     * @param guideId      导购工号
     * @param businessType 业态
     * @author 88396455_白振华
     * @since 14:54  2018-8-16
     */
    @RequestMapping("/deleteGuide")
    public Boolean deleteGuide(String guideId, String businessType) {
        EmployeeInfo requestUser = RequestUserHolder.getRequestUser();
        return shoppingGuideService.deleteGuide(guideId, requestUser.getStaffId(), requestUser.getStoreCode(), businessType);
    }

    /**
     * 根据筛选条件下载导购列表
     *
     * @param guidesInfoReqDto 查询导购信息条件
     * @param response         响应对象
     * @author 88396455_白振华
     * @since 14:58  2018-8-16
     */
    @RequestMapping("/exportGuideInfo")
    public Boolean exportGuideInfo(GuideInfoReqDto guidesInfoReqDto, HttpServletResponse response) throws IOException {
        ExportFile exportFile = exportFileService.exportFile(RequestUserHolder.getRequestUser().getStaffId(),
                new ShoppingGuideProvider(shoppingGuideService, guidesInfoReqDto));
        InputStream fileStream = ossService.getFileStream(CommonConstants.FILE_BUCKEN_NAME, exportFile.getObjectId());
        doExport(fileStream, response);
        return true;
    }

    /**
     * 导出excel
     */
    private void doExport(InputStream fileStream, HttpServletResponse response) throws IOException {
        response.setContentType(CONTENT_TYPE_EXPORT);
        String filename = FILENAME_PREFIX + DateUtils.formatPatten10(new Date()) + FILENAME_SUFFIX;
        response.setHeader(HEARDER_CONTENT_DISPOSITION, HEADER_ATTACHMENT_PREFIX + URLEncoder.encode(filename, UTF_8));
        response.setContentLength(fileStream.available());
        ServletOutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fileStream.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        fileStream.close();
    }

    /**
     * 查询当前登录人可以查看的大区列表
     *
     * @param
     * @author 88396455_白振华
     * @since 14:18  2018-8-31
     */
    @RequestMapping("/queryRegionList")
    public List<OrgInfoDto> queryRegionInfoList() {
        return storeDetailService.queryRegionInfoList(RequestUserHolder.getRequestUser());
    }

    /**
     * 查询大区下子公司列表
     *
     * @param regionId 大区编码
     * @author 88396455_白振华
     * @since 15:21  2018-8-31
     */
    @RequestMapping("/queryCompanyList")
    public List<OrgInfoDto> queryCompanyInfoList(String regionId) {
        return storeDetailService.queryCompanyInfoList(regionId, RequestUserHolder.getRequestUser());
    }

    /**
     * 查询分公司下的商店列表
     *
     * @param orgId 分公司编码
     * @author 88396455_白振华
     * @since 15:36  2018-8-31
     */
    @RequestMapping("/queryStoreList")
    public List<OrgInfoDto> queryStoreInfoList(String orgId) {
        return storeDetailService.queryStoreInfoList(orgId, RequestUserHolder.getRequestUser());
    }

    /**
     * 查询当前登陆人权限
     *
     * @author 88396455_白振华
     * @since 15:24  2018-9-7
     */
    @RequestMapping("/queryStaffAuth")
    public StaffAuthDto queryStaffAuth() {
        StaffAuthDto staffAuthDto = new StaffAuthDto();
        EmployeeInfo requestUser = RequestUserHolder.getRequestUser();
        BeanUtils.copy(requestUser, staffAuthDto);
        return staffAuthDto;
    }

    /**
     * 导出excel的内部类
     *
     * @author 88396455
     */
    private static class ShoppingGuideProvider extends PageableExportProvider<GuideInfoRespDto> {

        /**
         * 查询的service
         */
        private final ShoppingGuideService shoppingGuideService;

        /**
         * 查询条件
         */
        private final GuideInfoReqDto guideInfoReqDto;

        public ShoppingGuideProvider(ShoppingGuideService shoppingGuideService, GuideInfoReqDto guideInfoReqDto) {
            this.shoppingGuideService = shoppingGuideService;
            this.guideInfoReqDto = guideInfoReqDto;
        }

        @Override
        protected Page<GuideInfoRespDto> getNextPage(Pageable pageable) {
            return shoppingGuideService.queryGuideInfoPage(guideInfoReqDto, pageable);
        }

        @Override
        public long count() {
            return shoppingGuideService.queryGuideInfoCount(guideInfoReqDto);
        }

        @Override
        public FileType getFileType() {
            return FileType.SHOPPING_GUIDE;
        }

        @Override
        public String[] getColumnHeaders() {
            return VgoConstants.VGO_EXPORT_HEARDER;
        }

        @Override
        public Object getCellValue(GuideInfoRespDto data, int row, int column) {
            switch (column) {
                case 0:
                    return data.getGuideId();
                case 1:
                    return data.getGuideName();
                case 2:
                    return data.getStoreName();
                case 3:
                    return data.getRegionName();
                case 4:
                    return data.getBusinessType().equals(BusinessTypesEnum.ELECTRIC.getCode()) ?
                            BusinessTypesEnum.ELECTRIC.getName() :
                            BusinessTypesEnum
                                    .MOM_INFANT.getName();
                case 5:
                    return data.getOrderNum();
                case 6:
                    return data.getOpenFlag().equals(VgoConstants.OPEN_FLAG_OFF) ? VgoConstants.CLOSE_CN_STR : VgoConstants.OPEN_CN_STR;
                case 7:
                    return obtainHierarchy(data.getHierarchy());
                case 8:
                    return obtainAttach(data.getBusinessType(), data.getAttach());
                case 9:
                    return data.getIsVgo() == VgoConstants.IS_VGO_FLAG ? VgoConstants.YES_CN_STR : VgoConstants.NO_CN_STR;
                default:
                    return StringUtils.EMPTY;
            }
        }

        /**
         * 获取所属
         */
        private String obtainAttach(String businessType, String attach) {
            if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
                return VgoConstants.OTHER_CN_STR;
            }
            VgoAttachEnum[] attachs = VgoAttachEnum.values();
            for (int i = 0; i < attachs.length; ++i) {
                VgoAttachEnum attachEnum = attachs[i];
                if (attachEnum.getCode().equals(attach)) {
                    return attachEnum.getName();
                }
            }
            return VgoConstants.ATTACH_ELECTRIC;
        }

        /**
         * 将职位信息转化成中文
         */
        private String obtainHierarchy(String hierarchy) {
            VgoHierarchyEnum[] vgoHierarchies = VgoHierarchyEnum.values();
            for (int i = 0; i < vgoHierarchies.length; ++i) {
                VgoHierarchyEnum vgoHierarchy = vgoHierarchies[i];
                if (vgoHierarchy.getCode().equals(hierarchy)) {
                    return vgoHierarchy.getName();
                }
            }
            return VgoConstants.OTHER_CN_STR;
        }

        @Override
        public boolean isAsync() {
            //同步-false，异步-true
            return false;
        }
    }
}
