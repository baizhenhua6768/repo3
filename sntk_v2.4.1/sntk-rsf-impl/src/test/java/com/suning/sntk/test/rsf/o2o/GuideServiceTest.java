package com.suning.sntk.test.rsf.o2o;

import com.suning.sntk.dao.o2o.GuideDao;
import com.suning.sntk.entity.o2o.StaffCustRel;
import com.suning.sntk.service.o2o.impl.GuideServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.store.commons.lang.exception.ServiceException;
import org.mockito.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GuideServiceTest {

	@InjectMocks
	private GuideServiceImpl guideServiceImpl;
	@Mock
	private GuideDao guideDao;
	@Mock
	private RedisCacheUtils redisCacheUtils;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 解除顾客和导购的关系
	 */
	@Test(expectedExceptions = ServiceException.class)
	public void deleteRelByStaffIdAndCustNo() {
		BDDMockito.when(redisCacheUtils.del(Mockito.anyString())).thenReturn(1L);
		BDDMockito.when(guideDao.deleteRelByStaffIdAndCustNo(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(true);
		boolean b = guideServiceImpl.deleteRelByStaffIdAndCustNo("123", "456");
		assertEquals(b, true);

		//异常
		BDDMockito.when(guideDao.deleteRelByStaffIdAndCustNo(Mockito.anyString(), Mockito.anyString())).thenThrow(ServiceException.class);
		guideServiceImpl.deleteRelByStaffIdAndCustNo("123", "456");
	}

	/**
	 * 保存顾客和导购的关系
	 */
	@Test(expectedExceptions = ServiceException.class)
	public void saveStaffCustRel() {
		StaffCustRel staffCustRel = getStaffCustRel();
		BDDMockito.when(guideServiceImpl.create(Mockito.any(StaffCustRel.class))).thenReturn(staffCustRel);
		StaffCustRel result = guideServiceImpl.saveStaffCustRel(staffCustRel);
		assertEquals(result.getCustNo(), "101");
		//异常
		BDDMockito.when(guideServiceImpl.create(Mockito.any(StaffCustRel.class))).thenThrow(ServiceException.class);
		guideServiceImpl.saveStaffCustRel(staffCustRel);

	}

	private StaffCustRel getStaffCustRel() {
		StaffCustRel staffCustRel = new StaffCustRel();
		staffCustRel.setCustNo("101");
		staffCustRel.setStaffId("123123");
		staffCustRel.setStaffName("导购2");
		staffCustRel.setStoreCode("002");
		staffCustRel.setCreateTime(DateUtils.format(new Date()));
		staffCustRel.setChannel("线上");
		return staffCustRel;
	}

	/**
	 * 查找顾客和导购的关系
	 */
	@Test(expectedExceptions = ServiceException.class)
	public void findRelByCustNoAndStaffId() {
		StaffCustRel staffCustRel = getStaffCustRel();
		BDDMockito.when(guideDao.findRelByCustNoAndStaffId(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(staffCustRel);
		StaffCustRel rel= guideServiceImpl.findRelByCustNoAndStaffId("123", "456");
		assertEquals(rel.getCustNo(),"101");

		//异常
		BDDMockito.when(guideDao.findRelByCustNoAndStaffId(Mockito.anyString(), Mockito.anyString())).thenThrow(ServiceException.class);
		guideServiceImpl.findRelByCustNoAndStaffId("123", "456");
	}

	/**
	 * 查找顾客和导购关系列表
	 */
	@Test(expectedExceptions = ServiceException.class)
	public void  queryStaffCustListByCustNo(){
		List<StaffCustRel> list = getStaffCustRelList();
		BDDMockito.when(guideDao.queryStaffCustListByCustNo(Mockito.anyString())).thenReturn(list);
		List<StaffCustRel> relList = guideServiceImpl.queryStaffCustListByCustNo("101");
		assertEquals(relList.get(0).getCustNo(),"101");

		BDDMockito.when(guideDao.queryStaffCustListByCustNo(Mockito.anyString())).thenThrow(ServiceException.class);
		guideServiceImpl.queryStaffCustListByCustNo("101");
	}

	private List<StaffCustRel> getStaffCustRelList() {
		List<StaffCustRel> list = new ArrayList<>();
		StaffCustRel staffCustRel = new StaffCustRel();
		staffCustRel.setCustNo("101");
		staffCustRel.setStaffId("123123");
		staffCustRel.setStaffName("导购2");
		staffCustRel.setStoreCode("002");
		staffCustRel.setCreateTime(DateUtils.format(new Date()));
		staffCustRel.setChannel("线上");
		list.add(staffCustRel);
		StaffCustRel staffCustRel2 = new StaffCustRel();
		staffCustRel2.setCustNo("102");
		staffCustRel2.setStaffId("111111");
		staffCustRel2.setStaffName("导购3");
		staffCustRel2.setStoreCode("003");
		staffCustRel2.setCreateTime(DateUtils.format(new Date()));
		staffCustRel2.setChannel("线上");
		list.add(staffCustRel2);
		return list;
	}

}
