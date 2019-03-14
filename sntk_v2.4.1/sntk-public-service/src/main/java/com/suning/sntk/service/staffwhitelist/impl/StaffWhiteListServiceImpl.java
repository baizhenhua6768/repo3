/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: StaffWhiteListServiceImpl
 * Author:   88402362_欧小冬
 * Date:     2018/7/11 15:32
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.service.staffwhitelist.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.dao.staffwhitelist.StaffWhiteListDao;
import com.suning.sntk.entity.staffwhitelist.Staff;
import com.suning.sntk.service.staffwhitelist.StaffWhiteListService;
import com.suning.store.dal.base.DalBaseDao;
import com.suning.store.dal.service.AbstractDalService;

/**
 * 功能描述：店员白名单service
 *
 * @author 88402362_欧小冬
 * @since 2018/7/11
 */
@Service
public class StaffWhiteListServiceImpl extends AbstractDalService<Staff, Long> implements StaffWhiteListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffWhiteListServiceImpl.class);

    @Autowired
    private StaffWhiteListDao staffWhiteListDao;

    @Override
    public Staff queryByStaffIdWithStore(String staffId, String storeCode) {
        LOGGER.info("StaffWhiteListServiceImpl.queryByStaffIdWithStore,param[{staffId:{},storeCode{}}]",
                staffId , storeCode);
        Staff staff =  staffWhiteListDao.queryByStaffIdWithStore(staffId,storeCode);
        LOGGER.info("StaffWhiteListServiceImpl.queryByStaffIdWithStore,result[{}]",staff);
        return staff;
    }

    @Override
    protected DalBaseDao<Staff, Long> getDalDao() {
        return staffWhiteListDao;
    }
}
