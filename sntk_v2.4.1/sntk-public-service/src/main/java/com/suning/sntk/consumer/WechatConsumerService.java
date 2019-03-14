/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: WeixinConsumerService
 * Author:   17061157_王薛
 * Date:     2018/7/7 11:27
 * Description: 模块目的、功能描述
 * History: 修改记录
 * <author>      <time>         <version>    <desc>
 * 修改人姓名     修改时间       版本号        描述
 */

package com.suning.sntk.consumer;

import com.suning.aimp.intf.resp.individual.QueryIndividualCompleteInfoResp;
import com.suning.aimp.intf.resp.social.QuerySocialInfoResp;
import com.suning.aimp.intf.resp.thirdparty.special.QueryWechatRelationResp;
import com.suning.vgs.wgg.dto.member.WeixinSNSUserInfo;
import com.suning.vgs.wgg.dto.qrcode.QrcodeTempBussinessListDto;

/**
 * 功能描述：微信相关的外部接口 consumer
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
public interface WechatConsumerService {

    /**
     * 功能描述: 根据场景值查询店员工号 <br>
     *
     * @param sceneId 微信场景值
     * @return com.suning.vgs.wgg.dto.qrcode.QrcodeTempBussinessListDto
     * @author 17061157_王薛
     * @since 11:49  2018/7/7
     */
    QrcodeTempBussinessListDto queryShoperInfoBySceneId(String sceneId);

    /**
     * 功能描述: 查询顾客社交信息 <br>
     *
     * @param customerNo 会员编号
     * @return com.suning.aimp.intf.resp.social.QuerySocialInfoResp
     * @author 17061157_王薛
     * @since 11:52  2018/7/7
     */
    QuerySocialInfoResp queryCustomerSocialInfo(String customerNo);

    /**
     * 功能描述: 查询客户微信绑定关系 <br>
     *
     * @param unionId 客户公众平台唯一编号
     * @return com.suning.aimp.intf.resp.thirdparty.special.QueryWechatRelationResp
     * @author 17061157_王薛
     * @since 11:53  2018/7/7
     */
    QueryWechatRelationResp queryWechatRelation(String unionId);

    /**
     * 功能描述: 查询公众号粉丝的 unionId <br>
     *
     * @param devWeixinNo
     * @param openId
     * @return com.suning.vgs.wgg.dto.member.WeixinSNSUserInfo
     * @author 17061157_王薛
     * @since 11:55  2018/7/7
     */
    WeixinSNSUserInfo queryWeixinSNSUserInfo(String devWeixinNo, String openId);

    /**
     * 查询会员的完整信息（生育资料，基本资料）
     *
     * @param customerNo 会员编码
     * @author 18032490_赵亚奇
     */
    QueryIndividualCompleteInfoResp queryCustCompleteInfo(String customerNo);
}
