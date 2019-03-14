/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfoServiceImpl
 * Author:   17061157
 * Date:     2018-8-31 14:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.sntk.admin.service.vgo.StoreInfoService;
import com.suning.sntk.dao.common.StoreInfoDao;
import com.suning.sntk.dto.common.StoreGeoDto;
import com.suning.sntk.support.common.VgoStoreConstants;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.sntk.support.enums.StoreBusinessTypeEnum;

/**
 * 功能描述：门店详情服务
 *
 * @author 17061157
 * @since 2018-10-11
 */
@Service
public class StoreInfoServiceImpl implements StoreInfoService {

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Override
    public void handleStoreLocationRedisCache(String storeCode, int operation) {
        // 当为新增V购,开启是V购的店员，设置店员为是V购时，将对应门店放入门店位置缓存
        if (operation == VGO_ADD || operation == VGO_OPEN || operation == VGO_SET) {
            StoreGeoDto storeInfo = storeInfoDao.queryStoreGeoInfo(storeCode);
            if (StoreBusinessTypeEnum.ELEC.getCode().equals(storeInfo.getBusinessType())) {
                redisCacheUtils.hset(VgoStoreConstants.STORE_ELEC_KEY, storeCode, JsonUtils.object2Json(storeInfo));
            } else {
                redisCacheUtils.hset(VgoStoreConstants.STORE_BABY_KEY, storeCode, JsonUtils.object2Json(storeInfo));
            }
        } else if (operation == VGO_DELETE || operation == VGO_CLOSE || operation == VGO_UNSET) {
            // 判断当前门店下是否有其他有效导购
            Long count = storeInfoDao.queryValidVgoCount(storeCode);
            if (count == 0) {
                redisCacheUtils.hdel(VgoStoreConstants.STORE_ELEC_KEY, storeCode);
                redisCacheUtils.hdel(VgoStoreConstants.STORE_BABY_KEY, storeCode);
            }
        }
    }

}
