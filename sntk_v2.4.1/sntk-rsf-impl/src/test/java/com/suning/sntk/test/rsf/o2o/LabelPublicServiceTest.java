package com.suning.sntk.test.rsf.o2o;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.dao.o2o.LabelDao;
import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.sntk.service.o2o.impl.LabelServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.store.commons.pagination.Pageable;

public class LabelPublicServiceTest {

	@InjectMocks
	private LabelServiceImpl labelServiceImpl;

	@Mock
	private LabelService labelService;

	@Mock
	private RedisCacheUtils redisClient;

	@Mock
	private LabelDao labelDao;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 测试保存标签方法 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void saveLabelTest() {
		Label label = new Label();
		// 测试成功状态
		label.setLabelName("很专业");
		label.setStarLevel(1);
		label.setLabelCode("4009");
		BDDMockito.when(labelDao.countByCode(label.getLabelCode())).thenReturn(1L);
		labelServiceImpl.isRepeatCode(label.getLabelCode());
		labelServiceImpl.isRepeatName(label.getStarLevel(), label.getLabelName());
		// 测试错误标签
		label.setLabelCode("88888");
		labelServiceImpl.saveLabel(label);
		// 测试编码重复
		label.setLabelCode("4004");
		BDDMockito.when(labelService.isRepeatCode(label.getLabelCode())).thenReturn(true);
		labelServiceImpl.saveLabel(label);
		BDDMockito.when(labelService.isRepeatCode(label.getLabelCode())).thenReturn(false);
		labelServiceImpl.saveLabel(label);
	}

	/**
	 * 测试通过星级获取到对应的标签 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void queryLabelsByStarTest() {
		int star = 3;
		labelServiceImpl.queryLabelsByStar(star);
	}

	@Test
	public void queryPageLabelsTest() {
		Pageable pageable = null;
		labelServiceImpl.queryPageLabels(pageable);
	}

	@Test
	public void queryAllLabelMapTest() {
		List<Label> labelList = createLabelList();
		BDDMockito.when(labelDao.findAll()).thenReturn(labelList);
		labelServiceImpl.queryAllLabel();
		BDDMockito.when(labelDao.findAll()).thenReturn(labelList);
		labelServiceImpl.queryAllLabelMap();
		// 测试null分支
		BDDMockito.when(labelDao.findAll()).thenReturn(null);
		labelServiceImpl.queryAllLabelMap();
	}

	@Test
	public void updateLabelTest() {
		Label label = createLabel();
		labelServiceImpl.updateLabel(label);
	}

	@Test
	public void deleteLabelTest() {
		String labelCode = "5006";
		labelServiceImpl.deleteLabel(labelCode);
	}

	@Test
	public void findNextLabelCodeByStarTest() {
		String labelCode = "5002";
		Integer starLevel = 5;
		// 非空分支
		BDDMockito.when(labelDao.findLabelCodeByStar(starLevel)).thenReturn(labelCode);
		labelServiceImpl.findNextLabelCodeByStar(starLevel);
		// 空分支
		BDDMockito.when(labelDao.findLabelCodeByStar(starLevel)).thenReturn(null);
		labelServiceImpl.findNextLabelCodeByStar(starLevel);
	}

	/**
	 * 创建标签集合labelList 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @return labelList
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private List<Label> createLabelList() {
		List<Label> labelList = new ArrayList<>();
		Label label01 = new Label();
		label01.setLabelCode("1001");
		label01.setLabelName("态度差");
		label01.setStarLevel(1);
		labelList.add(label01);
		Label label02 = new Label();
		label02.setLabelCode("1002");
		label02.setLabelName("不回复消息");
		label02.setStarLevel(1);
		labelList.add(label02);
		Label label03 = new Label();
		label03.setLabelCode("1003");
		label03.setLabelName("频繁联系我");
		label03.setStarLevel(1);
		labelList.add(label03);
		return labelList;
	}

	private Label createLabel() {
		Label label = new Label();
		label.setCreateTime(new Date());
		label.setLabelCode("5005");
		label.setLabelName("有礼貌");
		label.setStarLevel(5);
		return label;
	}
}
