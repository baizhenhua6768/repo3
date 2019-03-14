/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LabelController.java
 * Author:   15050536
 * Date:     2018年3月24日 下午2:47:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.controller.o2o;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suning.sntk.entity.o2o.Label;
import com.suning.sntk.service.o2o.LabelService;
import com.suning.store.commons.pagination.Page;
import com.suning.store.commons.pagination.Pageable;

/**
 * 标签Controller
 *
 * @author 15050536 石键平
 */
@RestController
@Validated
@RequestMapping("/o2o/label/")
public class LabelController {

	@Autowired
	private LabelService lableService;

	@RequestMapping("queryLabelList")
	public Page<Label> queryPageLabels(Pageable pageable) {
		return lableService.queryPageLabels(pageable);
	}

	/**
	 * 根据星级查询标签编码
	 *
	 * @param star
	 * @return
	 */
	@RequestMapping(value = "findLabelCodeByStar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findLabelCodeByStar(@RequestParam(defaultValue = "1") Integer star) {
		return lableService.findNextLabelCodeByStar(star);
	}

	@RequestMapping("saveLabel")
	public boolean save(@Valid @RequestBody Label label) {
		lableService.saveLabel(label);
		return true;
	}

	@RequestMapping("deleteLabel")
	public boolean delete(@NotBlank String labelCode) {
		return lableService.deleteLabel(labelCode);
	}

	@RequestMapping("updateLabel")
	public boolean update(@Valid @RequestBody Label label) {
		return lableService.updateLabel(label);
	}

}
