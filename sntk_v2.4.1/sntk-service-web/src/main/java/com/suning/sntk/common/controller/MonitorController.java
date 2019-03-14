/*
 * Copyright (C), 2002-2017, 南京苏宁软件技术有限公司
 * FileName: MonitorController.java
 * Author:   10070706
 * Date:     2017年7月19日 下午6:09:27
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈监控页面〉<br>
 *
 * @author
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

	/**
	 * 监控页
	 * 
	 * @param request http请求
	 * @return
	 */
	@RequestMapping("/sysinfo")
	public Map<String, Object> sysinfo(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("system", "sntk-rsf");
		param.put("localAddr", request.getLocalAddr());
		param.put("localPort", request.getLocalPort());
		param.put("remoteAddr", request.getRemoteAddr());
		param.put("cp", request.getContextPath());
		return param;
	}

}
