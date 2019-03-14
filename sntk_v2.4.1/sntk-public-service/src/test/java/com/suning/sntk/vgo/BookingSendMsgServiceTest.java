package com.suning.sntk.vgo;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.BaseTest;
import com.suning.sntk.dao.vgo.BookingSendMsgDao;
import com.suning.sntk.service.vgo.impl.BookingSendMsgServiceImpl;

/**
 * @author 18032490_赵亚奇
 * @since 2018/9/13
 */
public class BookingSendMsgServiceTest extends BaseTest {

    @InjectMocks
    private BookingSendMsgServiceImpl bookingSendMsgService;

    @Mock
    private BookingSendMsgDao bookingSendMsgDao;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDelaySendMsg() throws Exception {
        String appointDate = "2018";
        String customerPhone = "123456";
        String guidePhone = "321654";
        String type = "1";
        String bookingCode = "123456789";
        bookingSendMsgService.delaySendMsg(appointDate, customerPhone, guidePhone, type, bookingCode);
    }
}