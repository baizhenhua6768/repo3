/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: VgoCommonService
 * Author:   18010645_黄成
 * Date:     2018/9/4 10:02
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.vgo;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：统一处理接单数、获赞数
 *
 * @author 18010645_黄成
 * @since 2018/9/4
 */
public interface VgoCommonService {

    /**
     * 功能：统一处理接单数、获赞数
     *
     * @param guideId 导购工号
     * @author 18010645_黄成
     * @since 10:29 2018/9/4
     */
    Map<String, String> queryOrderNumAndReceivePraise(String guideId);

    /**
     * 查询母婴导购服务项目
     *
     * @param guideId 导购Id
     * @return java.lang.String
     * @author 18041004_余长杰
     * @since 19:06 2018/9/27
     */
    String queryInfantGuideServiceItem(String guideId);

    /**
     * 查询导购默认头像
     *
     * @param guideId 导购Id
     * @return java.lang.String
     * @author 18041004_余长杰
     * @since 19:12 2018/9/27
     */
    String queryGuideDefaultPhoto(String guideId);

    /**
     * 功能：批量查询接单数和获赞数
     *
     * @param guideIds 导购工号集合
     * @author 18010645_黄成
     * @since 9:59 2018/10/7
     */
    Map<String, Map<String, String>> queryBatchOrderNumAndReceivePraise(List<String> guideIds);
}
