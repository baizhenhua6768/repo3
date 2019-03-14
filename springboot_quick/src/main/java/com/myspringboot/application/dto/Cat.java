/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: Cat
 * Author:   88396455_白振华
 * Date:     2019-2-22 14:33
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.myspringboot.application.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2019-2-22
 */
@ConfigurationProperties(prefix = "person")
@Component
public class Cat {

    private String name;

    private String age;

    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
