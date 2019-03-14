/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MaternalInfantService
 * Author:   88396455_白振华
 * Date:     2018-7-6 9:28
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.mombaby;

import com.suning.aimp.intf.req.individual.QueryIndividualCompleteInfoReq;
import com.suning.sntk.web.dto.mombaby.MaternalInfantBuildReqDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantRespDto;

/**
 * 功能描述：母婴关系服务
 *
 * @author 88396455_白振华
 * @since 2018-7-6
 */
public interface MaternalInfantService {

    /**
     * 查询母婴关系信息
     *
     * @param queryIndividualInfoReq
     * @author 88396455_白振华
     * @since 10:45  2018-7-3
     */
    MaternalInfantRespDto queryIndividualCompleteInfo(QueryIndividualCompleteInfoReq queryIndividualInfoReq);

    /**
     * 更新母婴关系
     *
     * @param maternalInfantReq 母婴关系维护入参
     * @author 88396455_白振华
     * @since 14:10  2018-7-3
     */
    void updateIndividualCompleteInfo(MaternalInfantBuildReqDto maternalInfantReq);
}
