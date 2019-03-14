/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: QuickController
 * Author:   88396455_白振华
 * Date:     2019-2-18 15:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.myspringboot.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspringboot.application.dto.Cat;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2019-2-18
 */
@Controller
public class QuickController {

    @Value("${name}")
    private String name;

    @Value("${cat.age}")
    private String age;

    @Autowired
    private Cat cat;

    @RequestMapping("/quick")
    @ResponseBody
    public Cat quick() {
        return cat;
    }
}
