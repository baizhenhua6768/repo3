/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MaternalInfantController
 * Author:   88396455_白振华
 * Date:     2018-7-3 10:19
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.controller.mombaby;

import java.security.Principal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suning.aimp.intf.dto.AddressInfo;
import com.suning.aimp.intf.dto.IndividualBaseInfo;
import com.suning.aimp.intf.req.individual.QueryIndividualCompleteInfoReq;
import com.suning.sntk.support.common.MemberCenterPublicParams;
import com.suning.sntk.support.common.utils.PublicParamUtils;
import com.suning.sntk.support.enums.MeternalInfantErrorEnum;
import com.suning.sntk.support.enums.MotherStatus;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.sntk.web.dto.mombaby.BabyInfoDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantBuildReqDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantInfoDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantRespDto;
import com.suning.sntk.web.dto.mombaby.UpdateIndividualCompleteInfoReqDto;
import com.suning.sntk.web.service.mombaby.MaternalInfantService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：母婴关系维护Controller
 *
 * @author 88396455_白振华
 * @since 2018-7-3
 */
@RestController
@RequestMapping("/relationship")
@Trace
public class MaternalInfantController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MaternalInfantController.class);

    /**
     * 正则表达式--长度介于0-12个字符
     */
    private static final String REGEX_STR_TWELVE = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]{0,12}";

    /**
     * 正则表达式--长度介于0-30个字符
     */
    private static final String REGEX_STR_THRITY = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]{0,30}";

    @Autowired
    private MaternalInfantService maternalInfantService;

    /**
     * 查询母婴关系信息
     *
     * @author 88396455_白振华
     * @since 10:45  2018-7-3
     */
    @RequestMapping("/queryMotherInfantInfo")
    public MaternalInfantRespDto queryIndividualCompleteInfo(
            Principal principal) {
        QueryIndividualCompleteInfoReq queryIndividualReq = new QueryIndividualCompleteInfoReq();
        MemberCenterPublicParams publicParams = PublicParamUtils.obtainingPublicParameters();
        BeanUtils.copyProperties(publicParams, queryIndividualReq);
        queryIndividualReq.setCustNum(principal.getName());
        LOGGER.info("MaternalInfantController.queryIndividualCompleteInfo,params:[{}]", queryIndividualReq);
        return maternalInfantService.queryIndividualCompleteInfo(queryIndividualReq);
    }

    /**
     * 更新母婴关系
     *
     * @param maternalInfantReq 母婴关系维护入参
     * @author 88396455_白振华
     * @since 14:10  2018-7-3
     */
    @PostMapping("/updateMotherInfantInfo")
    public Boolean updateIndividualCompleteInfo(@RequestBody MaternalInfantBuildReqDto maternalInfantReq, Principal principal) {
        //参数校验
        validateParams(maternalInfantReq);
        //设置会员编号
        maternalInfantReq.getUpdateIndividualInfo().setCustNum(principal.getName());
        //调用会员中心更新资料
        maternalInfantService.updateIndividualCompleteInfo(maternalInfantReq);
        //返回成功
        return true;
    }

    /**
     * 参数校验
     *
     * @param maternalInfantBuildReqDto 母婴关系维护入参
     * @author 88396455_白振华
     * @since 14:17  2018-7-3
     */
    private void validateParams(MaternalInfantBuildReqDto maternalInfantBuildReqDto) {
        UpdateIndividualCompleteInfoReqDto updateIndividualInfo = maternalInfantBuildReqDto.getUpdateIndividualInfo();
        //1.校验家长信息
        validateMotherInfo(updateIndividualInfo);
        //2.有宝宝，校验宝宝信息
        if (MotherStatus.HAVA_BABY.getValue().equals(updateIndividualInfo.getMaternalInfantInfo().getMomStat())) {
            List<BabyInfoDto> babyInfoList = updateIndividualInfo.getMaternalInfantInfo().getBabyInfoList();
            validateBabyInfo(babyInfoList);
        }
    }

    /**
     * 校验家长信息
     *
     * @param completeInfoReq 完整会员信息
     * @author 88396455_白振华
     * @since 15:39  2018-7-3
     */
    private void validateMotherInfo(UpdateIndividualCompleteInfoReqDto completeInfoReq) {
        //会员编号必填
        if (null == completeInfoReq) {
            Validators.throwAnyway(CommonErrorEnum.PARAMS_EMPTY);
            return;
        }
        //家长姓名不能为空
        Validators.ifInValid(null == completeInfoReq.getIndividualBaseInfo() || StringUtils
                .isBlank(completeInfoReq.getIndividualBaseInfo().getPartyName()))
                .thenThrow(MeternalInfantErrorEnum.NAME_CAN_NOT_NULL);
        //家长姓名不能超过12个字符
        IndividualBaseInfo motherInfo = completeInfoReq.getIndividualBaseInfo();
        Validators.ifInValid(!motherInfo.getPartyName().matches(REGEX_STR_TWELVE)).thenThrow(MeternalInfantErrorEnum.NAME_SUPER_LONG);
        //所在地区必填
        AddressInfo addressInfo = completeInfoReq.getAddressInfo();
        if (null == addressInfo || StringUtils.isBlank(addressInfo.getState()) || StringUtils.isBlank(addressInfo
                .getCity()) || StringUtils.isBlank(addressInfo.getTown())) {
            Validators.throwAnyway(MeternalInfantErrorEnum.AREA_CAN_NOT_NULL);
            return;
        }
        //详细地址必填
        Validators.ifInValid(StringUtils.isBlank(addressInfo.getDetailAddress()))
                .thenThrow(MeternalInfantErrorEnum.DETAIL_ADDRESS_CAN_NOT_NULL);
        //详细地址不能超过30个字符
        String detailAddress = addressInfo.getDetailAddress();
        Validators.ifInValid(!detailAddress.matches(REGEX_STR_THRITY)).thenThrow(MeternalInfantErrorEnum.DETAIL_ADDRESS_SUPER_LONG);
        //生育情况必填
        MaternalInfantInfoDto maternalInfantInfo = completeInfoReq.getMaternalInfantInfo();
        if (null == maternalInfantInfo || StringUtils.isBlank(maternalInfantInfo.getMomStat())) {
            Validators.throwAnyway(MeternalInfantErrorEnum.MOM_STAT_CAN_NOT_NULL);
            return;
        }
        //怀孕中需维护预产期
        Validators.ifInValid(MotherStatus.IN_PRE4GNANCY.getValue().equals(maternalInfantInfo.getMomStat()) && StringUtils
                .isBlank(maternalInfantInfo
                        .getChildbirthDueDate()))
                .thenThrow(MeternalInfantErrorEnum.DUE_DATE_CAN_NOT_NULL);
    }

    /**
     * 校验宝宝信息
     *
     * @param babyInfoList 宝宝信息列表
     * @author 88396455_白振华
     * @since 15:33  2018-7-3
     */
    private void validateBabyInfo(List<BabyInfoDto> babyInfoList) {
        Validators.ifInValid(CollectionUtils.isEmpty(babyInfoList)).thenThrow(MeternalInfantErrorEnum.BABY_INFO_CAN_NOT_NULL);
        for (BabyInfoDto babyInfo : babyInfoList) {
            //宝宝性别必填
            if (null == babyInfo || StringUtils.isBlank(babyInfo.getBabySex())) {
                Validators.throwAnyway(MeternalInfantErrorEnum.BABY_GENDER_CAN_NOT_NULL);
                return;
            }
            //宝宝昵称必填
            String babyName = babyInfo.getBabyName();
            Validators.ifInValid(StringUtils.isBlank(babyInfo.getBabyName())).thenThrow(MeternalInfantErrorEnum.NAME_CAN_NOT_NULL);
            //宝宝昵称长度不能大于12个字符
            Validators.ifInValid(!babyName.matches(REGEX_STR_TWELVE)).thenThrow(MeternalInfantErrorEnum.NAME_SUPER_LONG);
            //宝宝生日必填
            Validators.ifInValid(null == babyInfo.getBabyBirthday()).thenThrow(MeternalInfantErrorEnum.BABY_BIRTHDAY_CAN_NOT_NULL);
        }
    }

}
