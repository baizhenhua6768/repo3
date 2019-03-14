package com.suning.sntk.dao.o2o;

import com.suning.sntk.dto.vgo.ManagerInfoDto;
import com.suning.sntk.dto.vgo.ServantInfoPcDto;
import com.suning.sntk.dto.vgo.StoreInfoPcDto;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMethod;
import com.suning.store.dal.annotation.DalParams;
import com.suning.store.dal.base.DalBaseDao;

import java.util.List;

@DalMapper("guideCust")
public interface GuideDao extends DalBaseDao<StaffCustRel, Long> {
    /**
     * 根据custNo和staffId，删除他们的绑定关系
     */
    @DalMethod(name = "deleteRelByCustNoAndStaffId", params = {"staffId", "custNo"})
    public boolean deleteRelByStaffIdAndCustNo(String staffId, String custNo);

    /**
     * 查询导购信息
     */
    @DalMethod(name = "findRelByCustNoAndStaffId", params = {"staffId", "custNo"})
    public StaffCustRel findRelByCustNoAndStaffId(String staffId, String custNo);

    /**
     * 根据会员编码查询导购列表
     *
     * @param custNo
     * @return
     */
    @DalMethod(name = "queryStaffCustListByCustNo", params = {"custNo"})
    public List<StaffCustRel> queryStaffCustListByCustNo(String custNo);

    @DalParams({"custNo"})
    public StaffCustRel queryRelByCustNo(String customerNo);

    /**
     * 查询客户经理列表(不同业态)
     *
     * @param custNo
     * @return
     */
    @DalParams({"custNo"})
    List<ManagerInfoDto> queryManagerByCustNo(String custNo);

    /**
     * 根据业态和会员编码查询客户经理
     *
     * @param custNo
     * @param businessType
     * @return
     */
    @DalParams({"custNo", "businessType"})
    ManagerInfoDto queryManagerByCustNoAndBusiness(String custNo, String businessType);

    /**
     * 根据主键解除客户经理关系
     *
     * @param id
     */
    @DalParams({"id"})
    void deleteFlagById(Long id);

    /**
     * 功能描述:pc获取门店V购人员 <br>
     *
     * @param storeCode 门店编码
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @DalParams({"storeCode"})
    List<ServantInfoPcDto> getStoreServants(String storeCode);

    /**
     * 功能描述：pc获取门店信息
     *
     * @author 18010645_黄成
     * @since 10:44 2018/8/31
     */
    @DalParams({"storeCode"})
    StoreInfoPcDto getPcStoreInfo(String storeCode);

}
