package com.suning.sntk.dao.vgo;

import com.suning.sntk.dto.vgo.StaffBeStoreRespDto;
import com.suning.sntk.entity.vgo.O2oStoreDetail;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 功能描述：查询门店地址
 *
 * @author 18010645_黄成
 * @since 2018/8/18
 */
@DalMapper(namespace = "staffBeStoreAddress")
public interface StaffBeStoreDao extends DalBaseDao<O2oStoreDetail, Long> {

    /**
     * 功能描述：店员所属门店地址信息
     *
     * @param storeCode 门店编码
     * @author 18010645_黄成
     * @since 2018/8/18
     */
    @DalParams({"storeCode"})
    StaffBeStoreRespDto queryStaffBeStoreAddressInfo(String storeCode);

    /**
     * 根据门店编码查询门店业态
     *
     * @param staffId 店员编码
     * @author 18032490_赵亚奇
     */
    @DalParams({"staffId"})
    String queryBusinessType(String staffId);

    /**
     * 功能：查询门店编码
     *
     * @param staffId 店员工号
     * @author 18010645_黄成
     * @since 16:42 2018/9/5
     */
    @DalParams({"staffId"})
    String queryStoreCodeByStaffId(String staffId);

    @DalParams({"customerNo", "businessType"})
    String queryManagerInfoNew(String customerNo, String businessType);

}
