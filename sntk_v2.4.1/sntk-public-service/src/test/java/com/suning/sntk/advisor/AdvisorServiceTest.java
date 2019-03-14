/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AdvisorServiceTest
 * Author:   88402362_欧小冬
 * Date:     2018/7/11 10:40
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.advisor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.suning.aimp.intf.dto.CustAliasInfo;
import com.suning.sntk.BaseTest;
import com.suning.sntk.constant.CustomerConstants;
import com.suning.sntk.consumer.WechatConsumerService;
import com.suning.sntk.consumer.esb.ShopFrequencyService;
import com.suning.sntk.dao.relation.CustomerAdvisorDao;
import com.suning.sntk.dao.relation.CustomerBuyAdditionDao;
import com.suning.sntk.dto.advisor.CustomerBrandDto;
import com.suning.sntk.dto.advisor.CustomerDetailDto;
import com.suning.sntk.dto.advisor.CustomerListDto;
import com.suning.sntk.dto.advisor.CustomerQueryDto;
import com.suning.sntk.dto.relation.CustomerLabelStringDto;
import com.suning.sntk.dto.relation.CustomerPurchasePriceDto;
import com.suning.sntk.entity.relation.CustomerAdvisor;
import com.suning.sntk.entity.relation.WechatFans;
import com.suning.sntk.service.advisor.AdvisorService;
import com.suning.sntk.service.customer.CustomerService;
import com.suning.sntk.support.common.utils.DateUtils;
import com.suning.store.commons.pagination.PageRequest;
import com.suning.store.commons.pagination.Pageable;
import com.suning.vgs.wgg.dto.member.WeixinSNSUserInfo;
import com.suning.vgs.wgg.dto.qrcode.QrcodeTempBussinessListDto;

/**
 * 功能描述：
 *
 * @author 88402362_欧小冬
 * @since 2018/7/11
 */
public class AdvisorServiceTest extends BaseTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdvisorServiceTest.class);

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerAdvisorDao customerAdvisorDao;

    @Autowired
    private WechatConsumerService wechatConsumerService;

    @Autowired
    private CustomerBuyAdditionDao customerBuyAdditionDao;

    @Autowired
    private ShopFrequencyService shopFrequencyService;

    private CustomerAdvisor customerAdvisor;

    private Pageable pageable;
    private String staffId;
    private String searchParam;

    @BeforeClass
    public void init() {
        pageable = new PageRequest(0, 10);
        staffId = "";
        searchParam = "";
        CustomerAdvisor customerAdvisorReq = new CustomerAdvisor();
        customerAdvisorReq.setUnionId("oM7Aytw_JP30ckAsPWRB1t0f8TO8");
        customerAdvisorReq.setStoreCode("7611");
        customerAdvisorReq.setSex("1");
        customerAdvisorReq.setRemarkName("");
        customerAdvisorReq.setStaffId("12061818");
        customerAdvisor = advisorService.createAdvisorEntity(customerAdvisorReq);
    }

    @AfterClass
    public void destoryed() {
        advisorService.delete(customerAdvisor);
    }

    @Test
    public void getDalDaoTest() {
        LOGGER.info("queryCustomerList,dalDao{}", customerAdvisorDao);
    }

    @Test
    public void queryCustomerTest() {
        String staffId = "00120009";
        String unionId = "oM7Aytw_JP30ckAsPWRB1t0f8TO8";
        String storeCode = "7611";
        CustomerAdvisor customerAdvisor = customerAdvisorDao.queryCustomer(staffId, unionId);
        LOGGER.info("queryCustomerInfo,result[{}]", customerAdvisor);
        customerAdvisor = advisorService.queryCustomer(staffId, unionId, storeCode);
        LOGGER.info("queryCustomer,result[{}]", customerAdvisor);
    }

    @Test
    public void setManagerFlagTest() {
        String customerNo = "";
        String staffId = "";
        customerAdvisorDao.setManagerFlag(customerNo, staffId);
        advisorService.setManagerFlag(customerNo, staffId);
    }

    @Test
    public void queryCustomerListTest() {
        CustomerQueryDto customerQueryDto = new CustomerQueryDto();
        customerQueryDto.setPageSize(1);
        customerQueryDto.setStartIndex(0);
        customerQueryDto.setIsToday("0");
        customerQueryDto.setBuyDateType(2);
        customerQueryDto.setStaffId(staffId);
        customerQueryDto.setExpectEndTime(DateUtils.parse("2018-09-09", DateUtils.PATTEN_10));

        List<CustomerListDto> customerList = customerAdvisorDao.queryCustomerList(customerQueryDto);
        List<String> unionIds = new ArrayList<>();
        unionIds.add("1");
        unionIds.add("2");
        LOGGER.info("queryCustomerList,result{}", customerList);

        // 标签测试
        fillLabel(customerList, unionIds, staffId);
        // 金额测试
        fillPurchasePrice(customerList, unionIds, staffId);
        // 首付购测试
        fillShopFrequecy(customerList, unionIds);

        List<CustomerListDto> customerListDtos = advisorService.queryCustomerList(customerQueryDto);
        LOGGER.info("advisorService.queryCustomerList,result{}", customerListDtos);
    }

    @Test
    public void newQueryCustomerListTest() {
        // 造数据
        CustomerAdvisor advisor = new CustomerAdvisor();
        String unionId = UUID.randomUUID().toString().substring(5);
        advisor.setUnionId(unionId);
        advisor.setStaffId("12061818");
        advisor.setRemarkName("李大叔");
        customerAdvisorDao.insert(advisor);
        CustomerDetailDto detail = new CustomerDetailDto();
        detail.setStaffId("12061818");
        detail.setUnionId(unionId);
        detail.setCustMobile("15267829090");
        detail.setCustLabel("1#3");
        detail.setSelfLabel("喜欢蓝色#中年");
        List<CustomerBrandDto> buyDatas = new ArrayList<>();
        CustomerBrandDto brandDto = new CustomerBrandDto();
        brandDto.setCategoryNo("00003");
        brandDto.setCategoryName("黑电");
        brandDto.setBrandName("海尔#美的#奥克斯");
        brandDto.setMinPrice(3000);
        brandDto.setMaxPrice(5000);
        buyDatas.add(brandDto);
        detail.setBuyDatas(buyDatas);
        detail.setBuyTime("2018-07-23");
        customerService.saveCustomerInfo(detail);

        // 测试方法
        CustomerQueryDto queryDto = new CustomerQueryDto();
        queryDto.setStaffId("12061818");
        queryDto.setCustomerName("顾客");
        queryDto.setIsToday("0");
        queryDto.setBuyDateType(1);
        queryDto.setStartIndex(0);
        queryDto.setPageSize(10);
        List<CustomerListDto> dtos = advisorService.queryCustomerList(queryDto);
        LOGGER.info("newQueryCustomerListTest,result:{}", dtos);
    }

    @Test
    public void buildFansAndAdvisorRelationTest() {
        WechatFans wechatFans = new WechatFans();
        wechatFans.setUnionId("12345");
        wechatFans.setOpenId("123333");
        wechatFans.setAppId("aa");
        wechatFans.setSceneId("323");
        advisorService.buildFansAndAdvisorRelation(customerAdvisor, wechatFans);
    }

    @Test
    public void updateAdvisorSkipNullTest() {
        advisorService.updateLastScanTimeSkipNull(1L, DateUtils.format(new Date(), DateUtils.PATTEN_19));
    }

    @Test
    public void createAdvisorEntityTest() {
        LOGGER.info("createBaseInfoAdvisorEntityTest,result:{}", customerAdvisor);
    }

    @Test
    public void wechatConsumerServiceTest() {
        QrcodeTempBussinessListDto bussinessListDto = wechatConsumerService.queryShoperInfoBySceneId("");
        WeixinSNSUserInfo snsUserInfo = this.wechatConsumerService.queryWeixinSNSUserInfo("", "");
    }

    private String getMemberPhone(List<CustAliasInfo> custAliasInfos) {
        if (CollectionUtils.isNotEmpty(custAliasInfos)) {
            for (CustAliasInfo custAliasInfo : custAliasInfos) {
                if (custAliasInfo.getAliasType().equals(CustomerConstants.MEMBER_MOBILE_KEY)) {
                    return custAliasInfo.getCustAlias();
                }
            }
        }
        return StringUtils.EMPTY;
    }

    private void fillLabel(List<CustomerListDto> customerList, List<String> unionIds, String staffId) {
        List<CustomerLabelStringDto> customerLabelStrList = customerAdvisorDao.queryCustomersLabel(staffId, unionIds);
        //自定义标签
        List<CustomerLabelStringDto> customerSelfLabelList = customerBuyAdditionDao.querySelfLabelList(staffId, unionIds);

        for (CustomerLabelStringDto dto : customerLabelStrList) {
            if (dto != null) {
                LOGGER.info("CustomerLabelStringDto.result", dto);
            }
        }

        for (CustomerLabelStringDto dto : customerSelfLabelList) {
            if (dto != null) {
                LOGGER.info("customerSelfLabelList.result", dto);
            }
        }
    }

    private void fillPurchasePrice(List<CustomerListDto> customerList, List<String> unionIds, String staffId) {
        List<CustomerPurchasePriceDto> customerPurchaseList = customerAdvisorDao.queryCustomersPurchase(staffId, unionIds);
        for (CustomerPurchasePriceDto dto : customerPurchaseList) {
            if (dto != null) {
                LOGGER.info("CustomerPurchasePriceDto.result[{}]", dto);
            }
        }
    }

    private void fillShopFrequecy(List<CustomerListDto> customerList, List<String> customerNos) {
        Map<String, String> stringMap = shopFrequencyService.queryShopFrequency(customerNos);
        LOGGER.info("queryShopFrequency.test[{}]", stringMap);
    }

}
