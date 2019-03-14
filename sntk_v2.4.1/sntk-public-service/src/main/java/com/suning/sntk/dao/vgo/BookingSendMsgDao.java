/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: sendMsgDao
 * Author:   18032490_赵亚奇
 * Date:     2018/8/31 9:49
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.dao.vgo;

import java.util.List;

import com.suning.sntk.entity.vgo.BookingSendMsg;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.base.DalBaseDao;

/**
 * @author 18032490_赵亚奇
 * @since 2018/8/31
 */
@DalMapper(namespace = "bookingSendMsg")
public interface BookingSendMsgDao extends DalBaseDao<BookingSendMsg, Long> {

    /**
     * 查找未发送的短信
     *
     * @return
     */
    List<BookingSendMsg> queryBookingByStatus();

}