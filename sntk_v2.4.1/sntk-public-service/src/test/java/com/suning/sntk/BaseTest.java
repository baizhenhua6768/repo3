/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: BaseTest
 * Author:   17061157_王薛
 * Date:     2018/7/6 10:07
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * 功能描述：基础测试类
 *
 * @author 17061157_王薛
 * @since 2018/7/4
 */
@ContextConfiguration(locations={"classpath:spring-test.xml"})
public abstract class BaseTest extends AbstractTestNGSpringContextTests {

}