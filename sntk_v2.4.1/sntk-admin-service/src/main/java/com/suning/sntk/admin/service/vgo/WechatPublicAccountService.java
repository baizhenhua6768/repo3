/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: WechatPublicAccountService
 * Author:   18032490_赵亚奇
 * Date:     2018/10/10 15:31
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo;

/**
 * 微信公众号相关服务
 *
 * @author 18032490_赵亚奇
 * @since 2018/10/10
 */
public interface WechatPublicAccountService {
    void scanCodeAffair(String openId, String staffId, String storeCode);
}
