package job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.admin.job.vgo.BookingSendMsgJob;
import com.suning.sntk.dao.vgo.BookingSendMsgDao;
import com.suning.sntk.entity.vgo.BookingSendMsg;
import com.suning.sntk.service.message.SendMsgService;

/**
 * @author 18032490_赵亚奇
 * @since 2018/9/7
 */
public class BookingSendMsgJobTest{

    @InjectMocks
    private BookingSendMsgJob bookingSendMsgJob;

    @Mock
    private BookingSendMsgDao bookingSendMsgDao;

    @Mock
    private SendMsgService sendMsgService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMsgExecute1() throws Exception {
        List<BookingSendMsg> list = Collections.emptyList();
        BDDMockito.when(bookingSendMsgDao.queryBookingByStatus()).thenReturn(list);
        bookingSendMsgJob.sendMsgExecute();
    }

    @Test
    public void testSendMsgExecute2() throws Exception {
        List<BookingSendMsg> list =new ArrayList<>();
        BookingSendMsg msg = new BookingSendMsg();
        msg.setType("1");
        list.add(msg);
        BDDMockito.when(bookingSendMsgDao.queryBookingByStatus()).thenReturn(list);
        bookingSendMsgJob.sendMsgExecute();
    }

    @Test
    public void testSendMsgExecute3() throws Exception {
        List<BookingSendMsg> list =new ArrayList<>();
        BookingSendMsg msg = new BookingSendMsg();
        msg.setType("2");
        list.add(msg);
        BDDMockito.when(bookingSendMsgDao.queryBookingByStatus()).thenReturn(list);
        bookingSendMsgJob.sendMsgExecute();
    }
}