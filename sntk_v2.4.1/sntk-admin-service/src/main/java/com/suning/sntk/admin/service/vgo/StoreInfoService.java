/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreInfoService
 * Author:   17061157
 * Date:     2018-10-11 14:22
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.admin.service.vgo;

/**
 * 功能描述：门店详情服务
 *
 * @author 17061157
 * @since 2018-10-11
 */
public interface StoreInfoService {

    int VGO_ADD = 1;
    int VGO_DELETE = 2;
    int VGO_OPEN = 3;
    int VGO_CLOSE = 4;
    int VGO_SET = 5;
    int VGO_UNSET = 6;

    /**
     * 功能描述: 导购变更后，处理对应门店的redis缓存 <br>
     * a. 当导购被删除，禁用或设置为不是V购后，判断对应门店是否还有V购，否则删除redis缓存中对应的门店位置信息
     * b. 新增导购或设置为是V购后，将对应门店位置信息放入redis缓存
     *
     * @param storeCode 门店编码
     * @param operation 操作：删除/禁用/启用/新增/设置为不是V购/设置为是V购
     * @return void
     * @author 17061157_王薛
     * @since 14:56  2018/10/11
     */
    void handleStoreLocationRedisCache(String storeCode, int operation);

}
