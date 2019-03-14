package com.suning.sntk.common.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suning.sntk.service.configuration.DictService;
import com.suning.sntk.vo.DictListVo;
import com.suning.sntk.vo.DictVo;
import com.suning.store.commons.pagination.Pageable;

@Controller
@Validated
@RequestMapping("/dict")
public class DictController {

	@Autowired
	private DictService dictService;

	/**
	 * 字典表的数据删除
	 *
	 * @param dictId
	 *            字典表的主键
	 * @return 删除结果
	 */
	@RequestMapping("/delDictList")
	@ResponseBody
	public boolean deleteDict(@RequestParam Long dictId, Principal principal) {
		return dictService.deleteDict(dictId, principal.getName());
	}

	/**
	 * 分页查询字典
	 *
	 * @return 结果
	 */
	@RequestMapping("/queryDictList")
	@ResponseBody
	public DictListVo queryDictList(Pageable pageable) {
		return dictService.queryPageDictList(pageable);
	}

	/**
	 * 新增数据字典
	 *
	 * @param dictVo
	 *            字典对象
	 * @return 信息
	 */
	@RequestMapping("/addDictList")
	@ResponseBody
	public boolean addDict(@Valid @RequestBody DictVo dictVo, Principal principal) {
		dictVo.setCreateUser(principal.getName());
		return dictService.addDict(dictVo);
	}

	/**
	 * 新增数据字典
	 *
	 * @param dictVo
	 *            字典对象
	 * @return 信息
	 */
	@RequestMapping("/editDict")
	@ResponseBody
	public boolean editDict(@Valid @RequestBody DictVo dictVo, Principal principal) {
		return dictService.editDictVo(dictVo, principal.getName());
	}

}
