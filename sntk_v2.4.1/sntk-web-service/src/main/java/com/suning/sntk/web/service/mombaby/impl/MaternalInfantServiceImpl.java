/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MaternalInfantServiceImpl
 * Author:   88396455_白振华
 * Date:     2018-7-6 9:29
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.mombaby.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.aimp.intf.dto.AddressInfo;
import com.suning.aimp.intf.dto.BabyInfo;
import com.suning.aimp.intf.dto.IndividualBaseInfo;
import com.suning.aimp.intf.dto.MaternalInfantInfo;
import com.suning.aimp.intf.req.individual.QueryIndividualCompleteInfoReq;
import com.suning.aimp.intf.req.individual.UpdateIndividualCompleteInfoReq;
import com.suning.aimp.intf.resp.individual.QueryIndividualCompleteInfoResp;
import com.suning.aimp.intf.resp.individual.UpdateIndividualCompleteInfoResp;
import com.suning.aimp.intf.rsf.IndividualService;
import com.suning.rsf.consumer.ServiceLocator;
import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.MemberCenterPublicParams;
import com.suning.sntk.support.common.utils.PublicParamUtils;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.sntk.web.dto.mombaby.BabyInfoDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantBuildReqDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantInfoDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantRespDto;
import com.suning.sntk.web.dto.mombaby.UpdateIndividualCompleteInfoReqDto;
import com.suning.sntk.web.service.mombaby.MaternalInfantService;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：母婴关系服务
 *
 * @author 88396455_白振华
 * @since 2018-7-6
 */
@Service
public class MaternalInfantServiceImpl implements MaternalInfantService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MaternalInfantServiceImpl.class);

    /**
     * 会员中心个人信息服务
     */
    private IndividualService individualService = ServiceLocator.getService(IndividualService.class, "individualServiceImpl");

    @Override
    public MaternalInfantRespDto queryIndividualCompleteInfo(QueryIndividualCompleteInfoReq queryIndividualInfoReq) {
        LOGGER.info("IndividualConsumerServiceImpl.queryIndividualCompleteInfo,params[{}]", queryIndividualInfoReq);
        QueryIndividualCompleteInfoResp completeInfoResp = individualService.queryIndividualCompleteInfo(queryIndividualInfoReq);
        LOGGER.info("IndividualConsumerServiceImpl.queryIndividualCompleteInfo,result[{}]", completeInfoResp);
        Validators.ifInValid(null == completeInfoResp || CommonConstants.FAIL.equals(completeInfoResp.getStatus()))
                .thenThrow(CommonErrorEnum.CALL_MEMBER_CENTER_ERROR);
        return createMaternalInfantResp(completeInfoResp);
    }

    /**
     * 会员信息返回值转换
     *
     * @param completeInfoResp 会员信息返回值
     * @author 88396455_白振华
     * @since 11:13  2018-7-12
     */
    private MaternalInfantRespDto createMaternalInfantResp(QueryIndividualCompleteInfoResp completeInfoResp) {
        MaternalInfantRespDto maternalInfantRespDto = new MaternalInfantRespDto();
        if (null != completeInfoResp) {
            IndividualBaseInfo individualBaseInfo = createIndividualBaseInfo(completeInfoResp.getIndividualBaseInfo());
            maternalInfantRespDto.setIndividualBaseInfo(individualBaseInfo);
            AddressInfo addressInfo = createAddressInfo(completeInfoResp.getAddressInfo());
            maternalInfantRespDto.setAddressInfo(addressInfo);
            MaternalInfantInfoDto maternalInfantInfo = convertedMaternalInfantSpaceFiled(completeInfoResp.getMaternalInfantInfo());
            maternalInfantRespDto.setMaternalInfantInfo(maternalInfantInfo);
        }
        return maternalInfantRespDto;
    }

    /**
     * 地址信息null字段转换
     *
     * @param addressInfo 地址信息
     * @author 88396455_白振华
     * @since 15:42  2018-7-13
     */
    private AddressInfo createAddressInfo(AddressInfo addressInfo) {
        AddressInfo address = new AddressInfo();
        if (null != addressInfo) {
            String state = addressInfo.getState();
            String city = addressInfo.getCity();
            String town = addressInfo.getTown();
            String detailAddress = addressInfo.getDetailAddress();
            address.setState(null == state ? StringUtils.EMPTY : state);
            address.setCity(null == city ? StringUtils.EMPTY : city);
            address.setTown(null == town ? StringUtils.EMPTY : town);
            address.setDetailAddress(null == detailAddress ? StringUtils.EMPTY : detailAddress);
        } else {
            address.setState(StringUtils.EMPTY);
            address.setCity(StringUtils.EMPTY);
            address.setTown(StringUtils.EMPTY);
            address.setDetailAddress(StringUtils.EMPTY);
        }
        return address;
    }

    /**
     * 转换会员基本信息null字段转换
     *
     * @param individualBaseInfo 会员基本信息
     * @author 88396455_白振华
     * @since 15:40  2018-7-13
     */
    private IndividualBaseInfo createIndividualBaseInfo(IndividualBaseInfo individualBaseInfo) {
        IndividualBaseInfo individualBase = new IndividualBaseInfo();
        if (null != individualBaseInfo) {
            String partyName = individualBaseInfo.getPartyName();
            String gender = individualBaseInfo.getGender();
            individualBase.setPartyName(null == partyName ? StringUtils.EMPTY : partyName);
            individualBase.setGender(null == gender ? StringUtils.EMPTY : gender);
        } else {
            individualBase.setPartyName(StringUtils.EMPTY);
            individualBase.setGender(StringUtils.EMPTY);
        }
        return individualBase;
    }

    /**
     * 母婴关系null字段转换
     *
     * @param maternalInfantInfo 母婴关系信息
     * @author 88396455_白振华
     * @since 11:13  2018-7-12
     */
    private MaternalInfantInfoDto convertedMaternalInfantSpaceFiled(MaternalInfantInfo maternalInfantInfo) {
        MaternalInfantInfoDto maternalInfantInfoDto = new MaternalInfantInfoDto();
        List<BabyInfoDto> babyInfoDtos = new ArrayList<>();
        if (null != maternalInfantInfo) {
            String momStat = maternalInfantInfo.getMomStat();
            Date childbirthDueDate = maternalInfantInfo.getChildbirthDueDate();
            List<BabyInfo> babyInfoList = maternalInfantInfo.getBabyInfoList();
            maternalInfantInfoDto.setMomStat(StringUtils.isBlank(momStat) ? StringUtils.EMPTY : momStat);
            maternalInfantInfoDto.setChildbirthDueDate(null == childbirthDueDate ? StringUtils.EMPTY : DateUtils.format(childbirthDueDate,
                    DateUtils.PATTEN_10));
            if (CollectionUtils.isNotEmpty(babyInfoList)) {
                for (BabyInfo babyInfo : babyInfoList) {
                    BabyInfoDto babyInfoDto = new BabyInfoDto();
                    String babyName = babyInfo.getBabyName();
                    String babySex = babyInfo.getBabySex();
                    Integer babyNum = babyInfo.getBabyNum();
                    Date babyBirthday = babyInfo.getBabyBirthday();
                    babyInfoDto.setBabyName(null == babyName ? StringUtils.EMPTY : babyName);
                    babyInfoDto.setBabySex(null == babySex ? StringUtils.EMPTY : babySex);
                    babyInfoDto.setBabyNum(babyNum);
                    babyInfoDto.setBabyBirthday(
                            null == babyBirthday ? StringUtils.EMPTY : DateUtils
                                    .format(babyBirthday, DateUtils.PATTEN_10));
                    babyInfoDtos.add(babyInfoDto);
                }
            }
        } else {
            maternalInfantInfoDto.setMomStat(StringUtils.EMPTY);
            maternalInfantInfoDto.setChildbirthDueDate(StringUtils.EMPTY);
        }
        maternalInfantInfoDto.setBabyInfoList(babyInfoDtos);
        return maternalInfantInfoDto;
    }

    @Override
    public void updateIndividualCompleteInfo(MaternalInfantBuildReqDto maternalInfantReq) {
        UpdateIndividualCompleteInfoReq updateIndividualInfo = createUpdateIndividualCompleteInfoReq(maternalInfantReq);
        LOGGER.info("IndividualConsumerServiceImpl.updateIndividualCompleteInfo,params[{}]", updateIndividualInfo);
        UpdateIndividualCompleteInfoResp updateIndividualResp = individualService
                .updateIndividualCompleteInfo(updateIndividualInfo);
        LOGGER.info("IndividualConsumerServiceImpl.updateIndividualCompleteInfo,result[{}]", updateIndividualResp);
        Validators.ifInValid(CommonConstants.FAIL.equals(updateIndividualResp.getStatus()))
                .thenThrow(CommonErrorEnum.CALL_MEMBER_CENTER_ERROR);
    }

    /**
     * 创建请求参数
     *
     * @param maternalInfantBuildReqDto 母婴关系请求参数
     * @author 88396455_白振华
     * @since 16:36  2018-7-12
     */
    private UpdateIndividualCompleteInfoReq createUpdateIndividualCompleteInfoReq(MaternalInfantBuildReqDto maternalInfantBuildReqDto) {
        UpdateIndividualCompleteInfoReqDto updateIndividualInfoReqDto = maternalInfantBuildReqDto.getUpdateIndividualInfo();
        UpdateIndividualCompleteInfoReq updateIndividualInfo = new UpdateIndividualCompleteInfoReq();
        MemberCenterPublicParams publicParams = PublicParamUtils.obtainingPublicParameters();
        BeanUtils.copyProperties(publicParams, updateIndividualInfo);
        updateIndividualInfo.setCustNum(updateIndividualInfoReqDto.getCustNum());
        updateIndividualInfo.setAddressInfo(updateIndividualInfoReqDto.getAddressInfo());
        updateIndividualInfo.setIndividualBaseInfo(updateIndividualInfoReqDto.getIndividualBaseInfo());
        MaternalInfantInfo maternalInfantInfo = new MaternalInfantInfo();
        MaternalInfantInfoDto maternalInfantInfoDto = updateIndividualInfoReqDto.getMaternalInfantInfo();
        maternalInfantInfo.setMomStat(maternalInfantInfoDto.getMomStat());
        String childbirthDueDate = maternalInfantInfoDto.getChildbirthDueDate();
        if (StringUtils.isNotBlank(childbirthDueDate)) {
            maternalInfantInfo.setChildbirthDueDate(DateUtils.parse(childbirthDueDate, DateUtils.PATTEN_10));
        }
        List<BabyInfoDto> babyInfoList = maternalInfantInfoDto.getBabyInfoList();
        List<BabyInfo> babyInfos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(babyInfoList)) {
            for (BabyInfoDto babyInfoDto : babyInfoList) {
                BabyInfo babyInfo = new BabyInfo();
                babyInfo.setBabySex(babyInfoDto.getBabySex());
                babyInfo.setBabyNum(babyInfoDto.getBabyNum());
                babyInfo.setBabyName(babyInfoDto.getBabyName());
                babyInfo.setBabyBirthday(DateUtils.parse(babyInfoDto.getBabyBirthday(), DateUtils.PATTEN_10));
                babyInfos.add(babyInfo);
            }
        }
        maternalInfantInfo.setBabyInfoList(babyInfos);
        updateIndividualInfo.setMaternalInfantInfo(maternalInfantInfo);
        return updateIndividualInfo;
    }
}
