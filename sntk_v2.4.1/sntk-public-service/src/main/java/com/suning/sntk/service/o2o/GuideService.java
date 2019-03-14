package com.suning.sntk.service.o2o;

import java.util.List;

import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.store.dal.service.DalService;

/**
 * 中台导购业务
 *
 * @author 17121439 朱稳俊
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface GuideService extends DalService<StaffCustRel, Long> {
    /**
     * 解除专属导购（删除）
     */
    public boolean deleteRelByStaffIdAndCustNo(String staffId, String custNo);

    /**
     * 添加专属导购关系
     */
    public StaffCustRel saveStaffCustRel(StaffCustRel saffCustRel);

    /**
     * 查询导购信息
     */
    public StaffCustRel findRelByCustNoAndStaffId(String staffId, String custNo);

    /**
     * 根据会员编码查询导购列表
     *
     * @param custNo
     * @return
     */
    public List<StaffCustRel> queryStaffCustListByCustNo(String custNo);

    /**
     * 查询会员绑定的客户经理
     * @param customerNo
     */
    public StaffCustRel queryRelByCustNo(String customerNo);

}
