/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: StaffWhiteListServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/11 15:39
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.staffwhitelist;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.staffwhitelist.StaffWhiteListDao;
import com.suning.sntk.entity.staffwhitelist.Staff;

/**
 * 功能描述：
 *
 * @author 88402362_欧小冬
 * @since 2018/7/11
 */
public class StaffWhiteListServiceTest extends BaseTest {

    @Autowired
    private StaffWhiteListDao staffWhiteListDao;

    @Test
    public void queryByStaffIdWithStoreTest() {
        String staffId = "14051865";
        String storeCode = "709P" ;
        Staff staff = this.staffWhiteListDao.queryByStaffIdWithStore(staffId, storeCode);
        System.out.println(staff);
    }
}
