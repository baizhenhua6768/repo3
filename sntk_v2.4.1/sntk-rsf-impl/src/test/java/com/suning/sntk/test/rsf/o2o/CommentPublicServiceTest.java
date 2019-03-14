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

import com.suning.sntk.dao.o2o.GuideCommentDao;
import com.suning.sntk.entity.o2o.GuideComment;
import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.sntk.service.o2o.impl.GuideCommentServiceImpl;
import com.suning.sntk.support.common.redis.RedisCacheUtils;
import com.suning.sntk.support.common.utils.DateUtils;

/**
 * 中台 导购评论测试类 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 88395508
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CommentPublicServiceTest {

	@InjectMocks
	private GuideCommentServiceImpl commentServiceImpl;

	@Mock
	private GuideCommentDao commentDao;

	@Mock
	private RedisCacheUtils redisClient;

	@Mock
	private LabelService labelService;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 保存导购评论测试方法 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void saveGuideCommentTest() {
		GuideComment guideComment = createGuideComment();
		commentServiceImpl.saveGuideComment(guideComment);
	}

	/**
	 * 保存导购评论到历史表测试方法 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void saveGuideCommentHTest() {
		GuideComment guideComment = createGuideComment();
		commentServiceImpl.saveGuideCommentH(guideComment);
	}

	/**
	 * 更新导购最后一次评论 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void updateGuideCommentTest() {
		GuideComment guideComment = createGuideComment();
		commentServiceImpl.updateGuideComment(guideComment);
	}

	/**
	 * 测试通过会员号和工号获取导购评论信息方法 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void findCommentByCustNoAndStaffIdTest() {
		GuideComment guideComment = createGuideComment();
		commentServiceImpl.findCommentByCustNoAndStaffId(guideComment.getCustNo(), guideComment.getStaffId());
	}

	/**
	 * 测试通过工号查询平均星级方法 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void findAvgStarByStaffIdTest() {
		String staffId = "88395508";
		// 测试结果集为null的分支
		BDDMockito.when(commentDao.findAvgStarByStaffId(staffId)).thenReturn(null);
		commentServiceImpl.findAvgStarByStaffId(staffId);
		// 测试存在结果集的分支
		BDDMockito.when(commentDao.findAvgStarByStaffId(staffId)).thenReturn("4.5");
		commentServiceImpl.findAvgStarByStaffId(staffId);
	}

	@Test
	public void findProfileCommentByStaffIdTest() {
		List<Label> labelList = createLabelList();
		String staffId = "88395508";
		// labelCodes为""的情况
		BDDMockito.when(commentDao.findProfileCommentByStaffId(staffId)).thenReturn("");
		commentServiceImpl.findProfileCommentByStaffId(staffId);
		// label == null的分支
		BDDMockito.when(commentDao.findProfileCommentByStaffId(staffId)).thenReturn("3001,3002");
		BDDMockito.when(labelService.queryAllLabel()).thenReturn(labelList);
		commentServiceImpl.findProfileCommentByStaffId(staffId);
		// label != null的分支
		// 无重复标签
		BDDMockito.when(commentDao.findProfileCommentByStaffId(staffId)).thenReturn("1001,1002");
		BDDMockito.when(labelService.queryAllLabel()).thenReturn(labelList);
		commentServiceImpl.findProfileCommentByStaffId(staffId);
		// 有重复标签
		BDDMockito.when(commentDao.findProfileCommentByStaffId(staffId)).thenReturn("1001,1001");
		BDDMockito.when(labelService.queryAllLabel()).thenReturn(labelList);
		commentServiceImpl.findProfileCommentByStaffId(staffId);
	}

	/**
	 * 创建GuideComment实体 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @return guideComment
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private GuideComment createGuideComment() {
		GuideComment guideComment = new GuideComment();
		guideComment.setCreateTime(DateUtils.format(new Date()));
		guideComment.setCustNo("10011111101");
		guideComment.setId(1L);
		guideComment.setLabelCodes("4001,4002");
		guideComment.setStaffId("88395508");
		guideComment.setStarLevel(4);
		return guideComment;
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

}
