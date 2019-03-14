/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: StoreRsfServiceImpl
 * Author:   17061157_王薛
 * Date:     2018/9/22 11:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.rsf.impl.common;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.rsf.provider.annotation.Implement;
import com.suning.sntk.dao.common.StoreInfoDao;
import com.suning.sntk.dto.common.StoreGeoDto;
import com.suning.sntk.rsf.common.StoreRsfService;
import com.suning.store.commons.lang.advice.Trace;
import com.suning.store.commons.rsf.RsfResponseDto;
import com.suning.store.commons.rsf.advice.RsfAdvice;

/**
 * 功能描述：门店信息 rsf 接口
 *
 * @author 17061157_王薛
 * @since 2018/9/22
 */
@Implement(contract = StoreRsfService.class, implCode = "1.0.0")
@Service
@Trace
@RsfAdvice
public class StoreRsfServiceImpl implements StoreRsfService {

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Override
    public RsfResponseDto<Long> queryStoreCount() {
        return RsfResponseDto.of(storeInfoDao.queryStoreCount());
    }

    @Override
    public RsfResponseDto<List<StoreGeoDto>> queryStorePageList(int offset, int size) {
        return RsfResponseDto.of(storeInfoDao.queryStorePageList(offset, size));
    }

    @Override
    public RsfResponseDto<List<StoreGeoDto>> queryModifyStoreList(int modifyHour) {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.add(Calendar.HOUR, -modifyHour);
        return RsfResponseDto.of(storeInfoDao.queryModifyStoreList(nowCalendar.getTime()));
    }

}
