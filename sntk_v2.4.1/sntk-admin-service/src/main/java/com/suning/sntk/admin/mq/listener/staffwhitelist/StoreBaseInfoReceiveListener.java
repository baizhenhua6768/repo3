package com.suning.sntk.admin.mq.listener.staffwhitelist;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.suning.rsc.dto.responsedto.MbfServiceResponse;
import com.suning.rsc.mqservice.ei.annotation.EsbEIServerService;
import com.suning.rsc.server.NullResponseDto;
import com.suning.rsc.server.mq.MQServerServiceHandler;
import com.suning.sntk.admin.dao.staffwhitelist.StoreInfoDao;
import com.suning.sntk.admin.mq.listener.NullRespService;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.NsfStoreMqDto;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.StoreBaseInfoDto;
import com.suning.sntk.entity.staffwhitelist.StoreInfo;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.sntk.util.ConversionBusinessTypeUtil;

/**
 * 监听门店数据mq接口
 * StoreService-syncStoreInfo
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/3
 */
@EsbEIServerService(connectionFactory = "sntkQueueConnectionFactory", receiveQueue = "MBF_syncStoreInfo_SNTK")
public class StoreBaseInfoReceiveListener extends MQServerServiceHandler<NsfStoreMqDto, NullResponseDto, NullRespService> {

    private static final Logger LOGGER_SBIRL = LoggerFactory.getLogger(StoreBaseInfoReceiveListener.class);

    private static final String DELETE_FLAG = "D";

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Override
    public NullResponseDto handleBizz(NsfStoreMqDto input,
            String uid) {
        LOGGER_SBIRL.info("MQ接收门店信息，input：{}, uid:{}", input, uid);
        NullResponseDto response = new NullResponseDto();
        if (null == input || null == input.getStoreBaseInfo() || StringUtils.isBlank(input.getStoreBaseInfo().getStoreId())) {
            LOGGER_SBIRL.info("MQ接收门店信息数据为空");
            return response;
        }
        // 判断操作标识是否为删除
        String storeId = input.getStoreBaseInfo().getStoreId();
        if (DELETE_FLAG.equals(input.getOperationFlag())) {
            LOGGER_SBIRL.info("删除对应门店信息 storeId:{}", storeId);
            storeInfoDao.deleteById(storeId);
        } else {
            // 其他场景走新增或更新
            StoreInfo storeInfo = storeInfoDao.findById(storeId);
            StoreInfo mqStoreInfo = buildStoreInfo(input.getStoreBaseInfo(), storeId);
            if (storeInfo != null) {
                LOGGER_SBIRL.info("数据库中存在此门店信息，进行更新操作");
                storeInfoDao.updateSkipNull(mqStoreInfo);
            } else {
                LOGGER_SBIRL.info("数据库中不存在此门店信息，进行插入操作");
                mqStoreInfo.setCreater("admin");
                mqStoreInfo.setCreateTime(DateUtils.format(new Date()));
                storeInfoDao.insert(mqStoreInfo);
            }
        }
        return response;
    }

    private StoreInfo buildStoreInfo(StoreBaseInfoDto baseInfo, String storeId) {
        StoreInfo storeInfo = new StoreInfo();
        storeInfo.setStoreId(storeId);
        storeInfo.setStoreCode(baseInfo.getStoreSaleCode());
        storeInfo.setStoreName(baseInfo.getStoreName());
        storeInfo.setStoreAddress(baseInfo.getStoreAddress());
        storeInfo.setLongitude(baseInfo.getLongitude());
        storeInfo.setLatitude(baseInfo.getLatitude());
        storeInfo.setBusinessType(ConversionBusinessTypeUtil.getBusinessType(baseInfo.getStoreTypeId()));
        storeInfo.setUpdater("admin");
        storeInfo.setUpdateTime(DateUtils.format(new Date()));
        return storeInfo;
    }

    @Override
    public void sendResponse(NullRespService nullRespService, NullResponseDto nullResponseDto, MbfServiceResponse mbfServiceResponse) {

    }

}
