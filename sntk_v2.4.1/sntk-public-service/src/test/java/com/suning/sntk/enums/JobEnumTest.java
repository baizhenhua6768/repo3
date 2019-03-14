package com.suning.sntk.enums;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-9-22
 */
public class JobEnumTest {

    @Test
    public void testGetJobName() {
        JobEnum.VGO_MATCHGUIDE_JOB.getJobName();
    }

    @Test
    public void testGetJobDesc() {
        JobEnum.VGO_UPDATEREDIS_JOB.getJobDesc();
    }
}