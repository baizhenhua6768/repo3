/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: VgoAppointmentJobTest
 * Author:   88395115_史小配
 * Date:     2018/9/8 14:52
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package job;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.sntk.admin.job.vgo.VgoAppointmentJob;
import com.suning.sntk.dao.vgo.GuideAppointmentDao;
import com.suning.sntk.entity.vgo.AppointmentInfo;

/**
 * 功能描述：
 *
 * @author 88395115_史小配
 * @since 2018/9/8
 */
public class VgoAppointmentJobTest {

    @InjectMocks
    private VgoAppointmentJob appointmentJob;

    @Mock
    private GuideAppointmentDao appointmentDao;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void systemCancelAppointmentTest() {
        BDDMockito.when(appointmentDao.queryAppointmentByCreatTime(BDDMockito.any(Timestamp.class))).thenReturn(getAppointmentInfo());
        appointmentJob.systemCancelAppointment();
    }

    private List<AppointmentInfo> getAppointmentInfo() {
        List<AppointmentInfo> list = new ArrayList<>();
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        appointmentInfo.setId(3L);
        list.add(appointmentInfo);
        return list;
    }
}
