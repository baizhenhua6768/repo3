/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: PublicParamUtils
 * Author:   88396455_白振华
 * Date:     2018-7-10 20:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.sntk.support.common.CommonConstants;
import com.suning.sntk.support.common.MemberCenterPublicParams;
import com.suning.sntk.support.exception.enums.CommonErrorEnum;
import com.suning.store.commons.lang.validator.Validators;

/**
 * 功能描述：获取公共请求参数工具
 *
 * @author 88396455_白振华
 * @since 2018-7-10
 */
public class PublicParamUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PublicParamUtils.class);

    private PublicParamUtils() {
    }

    /**
     * 获取公共请求参数
     *
     * @param
     * @author 88396455_白振华
     * @since 15:47  2018-7-4
     */
    public static MemberCenterPublicParams obtainingPublicParameters() {
        MemberCenterPublicParams publicParams = new MemberCenterPublicParams();
        publicParams.setAppCode(CommonConstants.SYSTEM_ABBREVIATION_SNTK);
        publicParams.setSourceSystemNo(CommonConstants.SYSTEM_CODE_SNTK);
        publicParams.setTranTimestamp(new Date());
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            publicParams.setSourceChannel(ip);
            publicParams.setSrcIp(ip);
        } catch (UnknownHostException e) {
            LOGGER.error("MaternalInfantController.obtainingPublicParameters,exception:{}", e);
            Validators.throwAnyway(CommonErrorEnum.SYS_ERROR);
        }
        return publicParams;
    }
}
