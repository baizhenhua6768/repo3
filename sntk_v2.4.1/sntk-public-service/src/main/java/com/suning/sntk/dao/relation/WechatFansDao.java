/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CustomerAdvisorDao
 * Author:   17061157_王薛
 * Date:     2018/7/7 19:53
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.relation;

import com.suning.sntk.entity.relation.WechatFans;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：微信粉丝Dao
 *
 * @author 17061157_王薛
 * @since 2018/7/7
 */
@DalMapper("wechatFans")
public interface WechatFansDao extends DalBaseDao<WechatFans, Long> {

    /**
     * 根据openId查询粉丝关系
     *
     * @param openId 公众号客户唯一编号
     * @author 88396455_白振华
     * @since 10:18  2018-7-30
     */
    @DalParams({ "openId" })
    WechatFans findByOpenId(String openId);
}
