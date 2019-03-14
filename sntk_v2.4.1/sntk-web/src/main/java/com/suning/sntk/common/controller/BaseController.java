/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: BaseController.java
 * Author:   15050536
 * Date:     2018年3月26日 上午10:23:39
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.sntk.common.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.sntk.support.common.utils.JsonUtils;
import com.suning.store.commons.rsf.RsfResponseDto;

/**
 * controller中的一些抽象方法
 *
 * @author 15050536 石键平
 */
public class BaseController {

	protected Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 基础返回方法，支持json 和 jsonp
	 *
	 * @param callback
	 * @param data
	 * @param response
	 */
	public void baseReturn(String callback, RsfResponseDto<?> responseDto, HttpServletResponse response) {
		if (StringUtils.isNotEmpty(callback)) {
			jsonpReturn(callback, responseDto, response);
		} else {
			normalReturn(responseDto, response);
		}
	}

	/**
	 * jsonp形式返回对象
	 * 
	 * @param callback
	 *            callback方法
	 * @param resposneDto
	 *            待返回的对象
	 * @param response
	 */
	private void jsonpReturn(String callback, RsfResponseDto<?> resposneDto, HttpServletResponse response) {
		response.setContentType("text/javascript");
		String returnMsg = callback + "(" + JsonUtils.object2Json(resposneDto) + ")";
		doReturn(returnMsg, response);
	}

	/**
	 * json形式返回对象
	 *
	 * @param resposneDto
	 * @param response
	 */
	private void normalReturn(RsfResponseDto<?> resposneDto, HttpServletResponse response) {
		response.setContentType("application/json");
		String returnMsg = JsonUtils.object2Json(resposneDto);
		doReturn(returnMsg, response);
	}

	/**
	 * response返回对象
	 */
	private void doReturn(String returnMsg, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("UTF-8");
		Writer out = null;
		try {
			out = response.getWriter();
			out.write(returnMsg);
		} catch (IOException e) {
			LOGGER.error("IOException 返回jsonp对象异常", e);
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				LOGGER.error("IOException 流关闭异常", e);
			}
		}
	}

}
