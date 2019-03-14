/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ShoppingGuideServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-8-17 15:38
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suning.baozi.rsfservice.dto.O2oVGuideInfoDto;
import com.suning.sntk.admin.dao.vgo.CategoryRelAdminDao;
import com.suning.sntk.admin.dao.vgo.GuideInfoAdminDao;
import com.suning.sntk.admin.job.vgo.BookingSendMsgJob;
import com.suning.sntk.admin.service.vgo.ShoppingGuideService;
import com.suning.sntk.admin.service.vgo.StoreInfoService;
import com.suning.sntk.consumer.BaoziConsumerService;
import com.suning.sntk.consumer.NsfbusConsumerService;
import com.suning.sntk.dao.vgo.O2oGuideInfoDao;
import com.suning.sntk.dao.vgo.ServerItemDao;
import com.suning.sntk.dto.vgo.GuideInfoDto;
import com.suning.sntk.dto.vgo.GuideInfoReqDto;
import com.suning.sntk.dto.vgo.GuideInfoRespDto;
import com.suning.sntk.dto.vgo.VcategoryRelInfoDto;
import com.suning.sntk.entity.vgo.GuideInfo;
import com.suning.sntk.entity.vgo.O2oGuideInfo;
import com.suning.sntk.service.vgo.VgoModifyRedisService;
import com.suning.sntk.support.common.VgoConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.redis.vgo.VgoCacheKeyConstants;
import com.suning.sntk.support.enums.vgo.BusinessTypesEnum;
import com.suning.sntk.support.enums.vgo.VgoHierarchyEnum;
import com.suning.sntk.support.util.CommonUtils;
import com.suning.sntk.util.VgoAdminUtils;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 功能描述：导购管理服务
 *
 * @author 88396455_白振华
 * @since 2018-8-17
 */
@Service
public class ShoppingGuideServiceImpl implements ShoppingGuideService {

    @Autowired
    private GuideInfoAdminDao guideInfoAdminDao;

    @Autowired
    private CategoryRelAdminDao categoryRelDao;

    @Autowired
    private ServerItemDao serverItemDao;

    @Autowired
    private O2oGuideInfoDao o2oGuideInfoDao;

    @Autowired
    private VgoModifyRedisService vgoModifyRedisService;

    @Autowired
    private NsfbusConsumerService nsfbusConsumerService;

    @Autowired
    private BaoziConsumerService baoziConsumerService;

    @Autowired
    private StoreInfoService storeInfoService;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingGuideServiceImpl.class);

    @Override
    public Page<GuideInfoRespDto> queryGuideInfoPage(GuideInfoReqDto guideInfoReqDto, Pageable pageable) {
        String hierarchy = guideInfoReqDto.getHierarchy();
        List<String> codes = VgoHierarchyEnum.obtainCodeList();
        Page<GuideInfoRespDto> result = null;
        if (StringUtils.isNotBlank(hierarchy) && !codes.contains(hierarchy)) {
            result = guideInfoAdminDao.queryGuideInfoPage(guideInfoReqDto, pageable);
        } else {
            result = guideInfoAdminDao.queryGuideInfoForPage(guideInfoReqDto, pageable);
        }
        return result;
    }

    @Override
    public Page<GuideInfoRespDto> queryGuideInfoForPage(GuideInfoReqDto guidesInfoReqDto, Pageable pageable) {
        Page<GuideInfoRespDto> result = queryGuideInfoPage(guidesInfoReqDto, pageable);
        if (null != result && CollectionUtils.isNotEmpty(result.getContent())) {
            List<GuideInfoRespDto> content = result.getContent();
            for (GuideInfoRespDto guideInfoRespDto : content) {
                String orgId = guideInfoRespDto.getOrgId();
                if (StringUtils.isNotBlank(orgId)) {
                    guideInfoRespDto.setOrgName(nsfbusConsumerService.querySaleOrgName(orgId));
                }
            }
        }
        return result;
    }

    @Override
    public GuideInfoDto queryGuideDetail(String guideId, String businessType) {
        GuideInfoDto guideInfoDto = guideInfoAdminDao.queryByGuideId(guideId);
        if (StringUtils.isNotBlank(guideInfoDto.getOrgId())) {
            String orgId = guideInfoDto.getOrgId();
            if (StringUtils.isNotBlank(orgId)) {
                guideInfoDto.setOrgName(nsfbusConsumerService.querySaleOrgName(orgId));
            }
        }
        if (BusinessTypesEnum.ELECTRIC.getCode().equals(businessType)) {
            List<VcategoryRelInfoDto> vcategoryRelInfoDtos = categoryRelDao.queryByGuideId(guideId);
            guideInfoDto.setCategoryNames(VgoAdminUtils.obtainCategoryNames(vcategoryRelInfoDtos));
        } else if (BusinessTypesEnum.MOM_INFANT.getCode().equals(businessType)) {
            List<String> serviceItems = serverItemDao.queryByGuideId(guideId);
            guideInfoDto
                    .setServerItemNames(VgoAdminUtils.obtainServerItemNames(VgoAdminUtils.cutOffPicUrl(serviceItems)));
        }
        return guideInfoDto;
    }

    @Transactional
    @Override
    public Boolean deleteGuide(String guideId, String staffId, String storeCode, String businessType) {
        //软删除中台V购信息
        Long o2oGuideId = o2oGuideInfoDao.selectIdByGuideId(guideId);
        if (null != o2oGuideId) {
            O2oGuideInfo o2oGuideInfo = new O2oGuideInfo();
            o2oGuideInfo.setId(o2oGuideId);
            o2oGuideInfo.setDeleteFlag(VgoConstants.VGO_DELETE_FLAG);
            o2oGuideInfo.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
            o2oGuideInfo.setUpdatePerson(staffId);
            o2oGuideInfoDao.updateSkipNull(o2oGuideInfo);
        }
        //删除客户经理关系
        o2oGuideInfoDao.deleteByStaffId(guideId);
        //删除客户经理关系缓存
        List<String> list = o2oGuideInfoDao.selectRel(guideId);
        LOGGER.info("删除客户经理关系缓存,cust_list:{}",list);
        //删除客户经理缓存
        deleteCustRel(list);
        //软删除后台管理表V购信息
        GuideInfoDto guideInfoDto = guideInfoAdminDao.queryByGuideId(guideId);
        if (null != guideInfoDto && null != guideInfoDto.getId()) {
            GuideInfo guideInfo = new GuideInfo();
            guideInfo.setId(guideInfoDto.getId());
            guideInfo.setDeleteFlag(VgoConstants.VGO_DELETE_FLAG);
            guideInfo.setUpdateTime(CommonUtils.obtainCurrentTimestamp());
            guideInfo.setUpdatePerson(staffId);
            guideInfoAdminDao.updateSkipNull(guideInfo);
            //向麦琪推送删除信息
            List<O2oVGuideInfoDto> o2oVGuideInfoDtos = obtainNoticeBaoziParam(guideInfoDto);
            if (CollectionUtils.isNotEmpty(o2oVGuideInfoDtos)) {
                baoziConsumerService.updateGuideInfoTable(o2oVGuideInfoDtos);
            }
            //删除redis导购记录
            vgoModifyRedisService.deleteGuideInfoCache(guideId, guideInfoDto.getStoreCode(), businessType);
            storeInfoService.handleStoreLocationRedisCache(guideInfoDto.getStoreCode(), StoreInfoService.VGO_DELETE);
        }
        return true;
    }

    /**
     * 删除客户经理缓存
     */
    private void deleteCustRel(List<String> list) {
        if (list.isEmpty()) {
            return;
        }
        for (String custNo : list) {
            String key = MessageFormat.format(VgoCacheKeyConstants.KEY_CUST_MANAGER, custNo);
            if (redisCacheUtils.exists(key)) {
                redisCacheUtils.del(key);
            }
        }
    }

    /**
     * 获取通知麦琪参数
     */
    private List<O2oVGuideInfoDto> obtainNoticeBaoziParam(GuideInfoDto guideInfoDto) {
        List<O2oVGuideInfoDto> o2oVGuideInfoDtos = new ArrayList<O2oVGuideInfoDto>();
        O2oVGuideInfoDto o2oVGuideInfoDto = new O2oVGuideInfoDto();
        o2oVGuideInfoDto.setBusinessType(guideInfoDto.getBusinessType());
        o2oVGuideInfoDto.setDeleteFlag(VgoConstants.VGO_DELETE_FLAG.toString());
        o2oVGuideInfoDto.setGuideId(guideInfoDto.getGuideId());
        o2oVGuideInfoDto.setStoreCode(guideInfoDto.getStoreCode());
        o2oVGuideInfoDto.setDimissionFlag(guideInfoDto.getDimissionFlag());
        o2oVGuideInfoDto.setIsVgo(guideInfoDto.getIsVgo().toString());
        o2oVGuideInfoDto.setOpenFlag(guideInfoDto.getOpenFlag());
        o2oVGuideInfoDtos.add(o2oVGuideInfoDto);
        return o2oVGuideInfoDtos;
    }

    @Override
    public Long queryGuideInfoCount(GuideInfoReqDto guideInfoReqDto) {
        return guideInfoAdminDao.queryGuideInfoCount(guideInfoReqDto);
    }

    @Override
    public Long countGuideByBusinessType(GuideInfoReqDto guideInfoReqDto) {
        return guideInfoAdminDao.queryGuideInfoCount(guideInfoReqDto);
    }

}
