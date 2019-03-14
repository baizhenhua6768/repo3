package com.suning.sntk.dao.o2o;

import com.suning.sntk.entity.o2o.GuideComment;
import com.suning.store.dal.annotation.DalMapper;
import com.suning.store.dal.annotation.DalMethod;
import com.suning.store.dal.base.DalBaseDao;

/**
 * 最近一次评论导购dao接口
 * 
 * @author 88395508 高俊
 *
 */
@DalMapper("comment")
public interface GuideCommentDao extends DalBaseDao<GuideComment, Long> {

	@DalMethod(name = "findCommentsByCustNoAndStaffId", params = { "custNo", "staffId" })
	public GuideComment findCommentsByCustNoAndStaffId(String custNo, String staffId);

	@DalMethod(name = "saveGuideCommentH",params = {"guideComment"})
	public boolean saveGuideCommentH(GuideComment guideComment);

	/**
	 * 通过导购工号查询到该导购的平均星级 默认保留一位小数
	 * 
	 * @param staffId
	 * @return
	 */
	@DalMethod(name = "findAvgStarByStaffId", params = { "staffId" })
	public String findAvgStarByStaffId(String staffId);

	/**
	 * 根据导购工号，查询他的所有评价
	 * 
	 * @param staffId
	 * @return
	 */
	@DalMethod(name = "findProfileCommentByStaffId", params = { "staffId" })
	public String findProfileCommentByStaffId(String staffId);
}
