package com.suning.sntk.test.rsf.o2o;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.collect.Multimaps;
import com.suning.sntk.entity.o2o.GuideComment;
import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.rsf.dto.o2o.CommentReqDto;
import com.suning.sntk.rsf.impl.o2o.CommentRsfServiceImpl;
import com.suning.sntk.service.o2o.GuideCommentService;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.sntk.support.common.utils.DateUtils;

public class CommentRsfServiceTest {
	@InjectMocks
	private CommentRsfServiceImpl commentRsfServiceImpl;

	@Mock
	GuideCommentService commentService;

	@Mock
	LabelService labelService;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 测试保存评论方法 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void saveCommentTest() {
		CommentReqDto commentReqDto = createCommentReqDto();
		GuideComment guideComment = createGuideComment();
		// 会员对导购进行过评价
		BDDMockito.when(commentService.findCommentByCustNoAndStaffId(commentReqDto.getCustNo(), commentReqDto.getStaffId())).thenReturn(guideComment);
		commentRsfServiceImpl.doSave(commentReqDto);
		// 会员未对导购进行评价
		BDDMockito.when(commentService.findCommentByCustNoAndStaffId(commentReqDto.getCustNo(), guideComment.getStaffId())).thenReturn(null);
		commentRsfServiceImpl.doSave(commentReqDto);
	}

	/**
	 * 测试查询最后一次的导购评论 功能描述: <br>
	 * 〈功能详细描述〉
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@Test
	public void findLastGuideCommentTest() {
		Map<String, Collection<Label>> labelMap = queryAllLabelMap();
		BDDMockito.when(labelService.queryAllLabelMap()).thenReturn(labelMap);
		GuideComment guideComment = createGuideComment();
		String custNo = guideComment.getCustNo();
		String staffId = guideComment.getStaffId();
		// 会员已对导购进行过评价
		BDDMockito.when(commentService.findCommentByCustNoAndStaffId(custNo, staffId)).thenReturn(guideComment);
		commentRsfServiceImpl.findLastGuideComment(custNo, staffId);
		// 会员未对导购进行过评价
		BDDMockito.when(commentService.findCommentByCustNoAndStaffId(custNo, staffId)).thenReturn(null);
		commentRsfServiceImpl.findLastGuideComment(custNo, staffId);
	}

	private CommentReqDto createCommentReqDto() {
		CommentReqDto commentReqDto = new CommentReqDto();
		commentReqDto.setCustNo("100111221");
		commentReqDto.setLabelCodes("3001,3002");
		commentReqDto.setStaffId("88395508");
		commentReqDto.setStarNum(3);
		return commentReqDto;
	}

	private GuideComment createGuideComment() {
		GuideComment guideComment = new GuideComment();
		guideComment.setCreateTime(DateUtils.format(new Date()));
		guideComment.setCustNo("100231313");
		guideComment.setId(1L);
		guideComment.setLabelCodes("5001,5002");
		guideComment.setStaffId("88395508");
		guideComment.setStarLevel(5);
		return guideComment;
	}

	private Map<String, Collection<Label>> queryAllLabelMap() {
		List<Label> allLabels = createLabelList();
		return Multimaps.index(allLabels, new Function<Label, String>() {
			@Override
			public String apply(Label label) {
				return String.valueOf(label.getStarLevel());
			}
		}).asMap();
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
