/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: CommentService.java
 * Author:   15050536
 * Date:     2018年3月29日 下午2:53:30
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.service.o2o;

import java.util.Map;

import com.suning.sntk.entity.o2o.GuideComment;

/**
 * 中台导购评价 service
 *
 * @author 15050536 石键平
 */
public interface GuideCommentService {

	/**
	 * 评价导购
	 * 
	 * @param guideComment
	 */
	public void saveGuideComment(GuideComment guideComment);

	/**
	 * 更新导购评价
	 * 
	 * @param guideComment
	 */
	public boolean updateGuideComment(GuideComment guideComment);

	/**
	 * 根据会员编码和导购工号，查询评价信息
	 *
	 * @param custNo
	 *            会员编码
	 * @param staffId
	 *            导购工号
	 * @return 查询的导购评价对象
	 */
	public GuideComment findCommentByCustNoAndStaffId(String custNo, String staffId);

	/**
	 * 将评价信息保存到历史表
	 *
	 * @param guideComment
	 */
	public void saveGuideCommentH(GuideComment guideComment);

	/**
	 * 通过导购工号获取到其的平均星级，如果库中未查询到，则返回5星
	 * 
	 * @param staffId
	 *            导购工号
	 * @return
	 */
	public String findAvgStarByStaffId(String staffId);

	/**
	 * 根据导购工号，查询其对应的评价概况。如："服务周到":"12" "态度好":"3"
	 *
	 * @param staffId
	 * @return
	 */
	public Map<String, Integer> findProfileCommentByStaffId(String staffId);

}
