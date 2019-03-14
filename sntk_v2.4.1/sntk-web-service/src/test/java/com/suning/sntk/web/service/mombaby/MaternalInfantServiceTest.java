/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: MaternalInfantServiceTest
 * Author:   88396455_白振华
 * Date:     2018-7-16 17:07
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.service.mombaby;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.suning.aimp.intf.dto.AddressInfo;
import com.suning.aimp.intf.dto.IndividualBaseInfo;
import com.suning.aimp.intf.req.individual.QueryIndividualCompleteInfoReq;
import com.suning.sntk.support.common.MemberCenterPublicParams;
import com.suning.sntk.support.common.utils.PublicParamUtils;
import com.suning.sntk.web.dto.mombaby.MaternalInfantBuildReqDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantInfoDto;
import com.suning.sntk.web.dto.mombaby.MaternalInfantRespDto;
import com.suning.sntk.web.dto.mombaby.UpdateIndividualCompleteInfoReqDto;

/**
 * 功能描述：
 *
 * @author 88396455_白振华
 * @since 2018-7-16
 */
@ContextConfiguration(locations = { "classpath:spring/spring-test.xml" })
public class MaternalInfantServiceTest extends AbstractTestNGSpringContextTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaternalInfantServiceTest.class);

    @Autowired
    private MaternalInfantService maternalInfantService;

    @Test
    public void queryIndividualCompleteInfoTest() {
        QueryIndividualCompleteInfoReq completeInfoReq = new QueryIndividualCompleteInfoReq();
        MemberCenterPublicParams publicParams = PublicParamUtils.obtainingPublicParameters();
        BeanUtils.copyProperties(publicParams, completeInfoReq);
        completeInfoReq.setCustNum("6001919366");
        MaternalInfantRespDto maternalInfantRespDto = maternalInfantService.queryIndividualCompleteInfo(completeInfoReq);
        LOGGER.info("MaternalInfantServiceTest.queryIndividualCompleteInfoTest,result:{}", maternalInfantRespDto);
    }

    @Test
    public void updateIndividualCompleteInfo() {
        MaternalInfantBuildReqDto maternalInfantReq = new MaternalInfantBuildReqDto();
        MemberCenterPublicParams publicParams = PublicParamUtils.obtainingPublicParameters();
        BeanUtils.copyProperties(publicParams, maternalInfantReq);
        maternalInfantReq.setPersonNo("12345678");
        UpdateIndividualCompleteInfoReqDto completeInfoReqDto = new UpdateIndividualCompleteInfoReqDto();
        completeInfoReqDto.setCustNum("6001919366");
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setState("001");
        addressInfo.setTown("434");
        addressInfo.setCity("4343");
        addressInfo.setDetailAddress("fsdfdsfsdf");
        completeInfoReqDto.setAddressInfo(addressInfo);
        IndividualBaseInfo baseInfo = new IndividualBaseInfo();
        baseInfo.setPartyName("fdsfsd");
        baseInfo.setGender("124000000010");
        completeInfoReqDto.setIndividualBaseInfo(baseInfo);
        MaternalInfantInfoDto maternalInfantInfoDto = new MaternalInfantInfoDto();
        maternalInfantInfoDto.setMomStat("212000000020");
        maternalInfantInfoDto.setChildbirthDueDate("2018-07-09");
        completeInfoReqDto.setMaternalInfantInfo(maternalInfantInfoDto);
        maternalInfantReq.setUpdateIndividualInfo(completeInfoReqDto);
    }
}
