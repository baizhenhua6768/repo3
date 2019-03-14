/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: StoreBaseInfoReceiveListenerTest
 * Author:   18032490_赵亚奇
 * Date:     2018/7/13 14:31
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

import com.suning.sntk.admin.mq.listener.staffwhitelist.StoreBaseInfoReceiveListener;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.NsfStoreMqDto;
import com.suning.sntk.admin.mq.listener.staffwhitelist.dto.StoreBaseInfoDto;

/**
 * @author 18032490_赵亚奇
 * @since 2018/7/13
 */
@ContextConfiguration(locations = { "classpath:spring/spring-admin-test.xml" })
public class StoreBaseInfoReceiveListenerTest extends AbstractTestNGSpringContextTests {

    private static final String DELETE_FLAG = "D";
    private static final String INSERTE_FLAG = "I";
    @Autowired
    private StoreBaseInfoReceiveListener storeBaseInfoReceiveListener;

    @Test
    public void handleTest1(){
        NsfStoreMqDto input = new NsfStoreMqDto();
        storeBaseInfoReceiveListener.handleBizz(input,null);
    }

    @Test
    public void handleTest2(){
        NsfStoreMqDto input = new NsfStoreMqDto();
        input.setOperationFlag(DELETE_FLAG);
        StoreBaseInfoDto storeBaseInfoDto = new StoreBaseInfoDto();
        storeBaseInfoDto.setStoreId("10127075");
        input.setStoreBaseInfo(storeBaseInfoDto);
        storeBaseInfoReceiveListener.handleBizz(input,null);
    }

}
