/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: ScanCodeListenerTest
 * Author:   88396455_白振华
 * Date:     2018-7-11 15:46
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.suning.rsc.server.NullResponseDto;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.ScanCodeBodyDto;
import com.suning.sntk.admin.mq.listener.wechat.ScanCodeListener;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-11
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class ScanCodeListenerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ScanCodeListener scanCodeListener;

    @Test
    public void handleBizzTest1() {
        ScanCodeBodyDto scanCodeBodyDto = new ScanCodeBodyDto();
        scanCodeBodyDto.setSceneId("WGG_784e587a-2cfa-427a-9a6a-fabc856ae91b");
        scanCodeBodyDto.setOpenId("oZOqawcMcW4W29sxm7AlvJVES8A0");
        scanCodeBodyDto.setDevWeixinNo("gh_a0b2e8d8d8e0");
        NullResponseDto nullResponseDto = scanCodeListener.handleBizz(scanCodeBodyDto, null);
        System.out.println(nullResponseDto);
    }

    @Test
    public void handleBizzTest2() {
        ScanCodeBodyDto scanCodeBodyDto = new ScanCodeBodyDto();
        scanCodeBodyDto.setSceneId("WGG_c236b181-5cec-4168-9e9e-da65d1216ddc");
        scanCodeBodyDto.setOpenId("oZOqawWLURwwzur3LsYwU6NHgcek");
        scanCodeBodyDto.setDevWeixinNo("gh_a0b2e8d8d8e0");
        NullResponseDto nullResponseDto = scanCodeListener.handleBizz(scanCodeBodyDto, null);
        System.out.println(nullResponseDto);
    }
}
