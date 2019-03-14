/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SubscribeListenerTest
 * Author:   88396455_白振华
 * Date:     2018-7-11 16:07
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
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.SubscribeBodyDto;
import com.suning.sntk.admin.mq.listener.wechat.SubscribeListener;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-11
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class SubscribeListenerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SubscribeListener subscribeListener;

    @Test
    public void handleBizzTest1() {
        SubscribeBodyDto subscribeBodyDto = new SubscribeBodyDto();
        subscribeBodyDto.setSceneId("WGG_4dc05592-e0e8-4bd0-9e08-a55300de16c6");
        subscribeBodyDto.setNickName("测试---不要重复");
        subscribeBodyDto.setOpenId("oZOqawemK4JrsA_clXNdoKIxXc4s");
        subscribeBodyDto.setUnionId("oM7AytytDxqskiV4sNqw3IYp2WFk");
        subscribeBodyDto.setAppId("gh_a0b2e8d8d8e0");
        NullResponseDto nullResponseDto = subscribeListener.handleBizz(subscribeBodyDto, null);
        System.out.println(nullResponseDto);
    }

    @Test
    public void handleBizzTest2() {
        SubscribeBodyDto subscribeBodyDto = new SubscribeBodyDto();
        subscribeBodyDto.setSceneId("WGG_c236b181-5cec-4168-9e9e-da65d1216ddc");
        subscribeBodyDto.setNickName("测试---不要重复2");
        subscribeBodyDto.setOpenId("oZOqawWLURwwzur3LsYwU6NHgcek");
        subscribeBodyDto.setUnionId("oM7Ayt3oNgzxV3HwcZqoi3VdhxEU");
        subscribeBodyDto.setAppId("gh_a0b2e8d8d8e0");
        NullResponseDto nullResponseDto = subscribeListener.handleBizz(subscribeBodyDto, null);
        System.out.println(nullResponseDto);
    }
}
