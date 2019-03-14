/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: SeparatePersonListenerTest
 * Author:   18032490_赵亚奇
 * Date:     2018/7/13 14:05
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package mq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.suning.sntk.admin.mq.listener.staffwhitelist.SeparatePersonListener;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.LeaveSys;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.LeaveTab;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.SeparatePersonDto;

/**
 * 测试人员离职的MQ
 *
 * @author 18032490_赵亚奇
 * @since 2018/7/13
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class SeparatePersonListenerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SeparatePersonListener separatePersonListener;

    @Test
    public void handleTest1(){
        SeparatePersonDto separatePersonDto = new SeparatePersonDto();
        separatePersonListener.handleBizz(separatePersonDto , null);
    }

    @Test
    public void handleTest2(){
        SeparatePersonDto separatePersonDto = new SeparatePersonDto();
        List<LeaveTab> iTabList = new ArrayList<>();
        LeaveTab tab = new LeaveTab();
        tab.setPersonNo("9xglx5gYIaC/Ir3neBc+zg==");
        iTabList.add(tab);
        separatePersonDto.setiTabList(iTabList);
        LeaveSys leaveSys = new LeaveSys();
        leaveSys.setSecretKey("ITP(fdsafasFR==),SNTK(IpeT0C6sFZa9g0AJo0HycA==)");
        separatePersonDto.setiSys(leaveSys);
        separatePersonListener.handleBizz(separatePersonDto , null);
    }

    @Test
    public void handleTest3(){
        SeparatePersonDto separatePersonDto = new SeparatePersonDto();
        List<LeaveTab> iTabList = new ArrayList<>();
        LeaveTab tab = new LeaveTab();
        tab.setPersonNo("9xglx5gYIaC/Ir3neBc+zg==");
        iTabList.add(tab);
        separatePersonDto.setiTabList(iTabList);
        LeaveSys leaveSys = new LeaveSys();
        leaveSys.setSecretKey("ITP(fdsafasFR==)");
        separatePersonDto.setiSys(leaveSys);
        separatePersonListener.handleBizz(separatePersonDto , null);
    }
}
