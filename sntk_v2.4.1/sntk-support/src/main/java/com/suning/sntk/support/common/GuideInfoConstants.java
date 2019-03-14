/*
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * FileName: GuideInfoConstants
 * Author:   18041004_余长杰
 * Date:     2018/8/16 17:55
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.support.common;

/**
 * 功能描述：导购查询
 *
 * @author 18041004_余长杰
 * @since 2018/8/16
 */
public class GuideInfoConstants {

    /**
     * 所有城市开启V购
     */
    public static final String ALL_CITY = "ALL";

    /**
     * 是否麦琪匹配：1是0否
     */
    public static final String IS_MATCH = "1";

    /**
     * 是否麦琪匹配：1是0否
     */
    public static final String IS_NOT_MATCH = "0";

    /**
     * 是否计算经纬度
     */
    public static final String CACULATE_LONG_LATI = "1";

    public static final long INITIAL_RECEIVE_PRAISE = 1500L;

    /**
     * 试点城市
     */
    public static final String TEST_CITY = "TEST_CITY";

    /**
     * 查询经纬度开关
     */
    public static final String LATITUDE_LONGITUDE_SWITCH = "LATITUDE_LONGITUDE_SWITCH";

    /**
     * 是否需要计算经纬度标记：LATITUDE_LONGITUDE_SWITCH = 1 标识需要计算
     */
    public static final String NEED_CALC_DISTANCE = "1";

    /**
     * 麦琪开关:1开启0关闭
     */
    public static final String MATCH_SWITCH = "MATCH_SWITCH";

    /**
     * 需要走麦琪匹配的开关标识
     */
    public static final String NEET_USE_MATCH_FLAG = "1";


    /**
     * 四级页导购提示标题
     */
    public static final String FOURTH_PAGE_TIP_TITLE = "FOURTH_PAGE_TIP_TITLE";

    /**
     * 导购提示内容
     */
    public static final String TIP_CONTENT = "TIP_CONTENT";

    /**
     * 我的客户经理提示标题
     */
    public static final String CUST_MANAGER_TIP_TITLE = "CUST_MANAGER_TIP_TITLE";

    /**
     * V购导购图标url（区分业态）
     */
    public static final String VGO_GUIDE_LABEL_URL = "VGO_GUIDE_LABEL_URL";

    /**
     * 母婴导购图标url（区分业态）
     */
    public static final String INFANT_GUIDE_LABEL_URL = "INFANT_GUIDE_LABEL_URL";

    /**
     * 卡片页V购导购图标url（区分业态）
     */
    public static final String VGO_GUIDE_LIST_LABEL_URL = "VGO_GUIDE_LIST_LABEL_URL";

    /**
     * 卡片页母婴导购图标url（区分业态）
     */
    public static final String INFANT_GUIDE_LIST_LABEL_URL = "INFANT_GUIDE_LIST_LABEL_URL";

    /**
     * 默认男导购图片url
     */
    public static final String MALE_PHOTO_URL = "MALE_PHOTO_URL";

    /**
     * 默认女导购图片url
     */
    public static final String FEMALE_PHOTO_URL = "FEMALE_PHOTO_URL";

    /**
     * SCM业态
     */
    public static final String BUSINESS_TYPE = "BUSINESS_TYPE";

    /**
     * SCM品类
     */
    public static final String CATEGORY_CODE = "CATEGORY_CODE";

    /**
     * SCM专属导购
     */
    public static final String SPECIAL_GUIDE = "SPECIAL_GUIDE";

    /**
     * 易购APP我的客户经理需要支持的业态默认值：电器（1）+母婴（2），使用英文逗号分隔
     */
    public static final String DEFAULT_BUSINESS_TYPE = "1,2";

    /**
     * 英文逗号分隔
     */
    public static final String SPLIT_LABEL = ",";


    /**
     * 麦琪匹配标记
     */
    public static final String MAITCH_MARK = "match";

    /**
     * sntk匹配标记
     */
    public static final String SNTK_MARK = "sntk";

    /**
     * 母婴服务项目
     */
    public static final String SERVER_NAME = "ALL";


    /**
     * 四级页-店员主页url
     */
    public static final String FOURTH_PAGE_GUIDE_INFO_URL = "{0}/sntk-web/vgo/salesManInfo.html?"
            + "cityId={1}&districtId={2}&categoryId={3}&guideId={4}&channel=30&longitude={5}&latitude={6}";

    /**
     * 客户经理-店员主页url
     */
    public static final String CUST_MANAGER_GUIDE_INFO_URL = "{0}/sntk-web/vgo/salesManInfo.html?"
            + "cityId={1}&districtId={2}&guideId={3}&channel=32&longitude={4}&latitude={5}";

    /**
     * 店员卡片页url
     */
    public static final String GUIDE_LIST_URL = "{0}/sntk-web/vgo/salesManCard.html?"
            + "cityId={1}&districtId={2}&categoryId={3}&channel=31&longitude={4}&latitude={5}&guideId={6}";


    /**
     * 预约列表url（2个导购）
     */
    public static final String APPOINTMENT_LIST_URL_TWO_GUIDE = "{0}/sntk-web/vgo/appointmentList.html?"
            + "vgoGuideId={1}&momBabyGuideId={2}&cityId={3}&districtId={4}&channel=32&longitude={5}&latitude={6}";

    /**
     * redis缓存时间
     */
    public static final String EXPIRE_TIME = "EXPIRE_TIME";

    /**
     * vanish超时时间
     */
    public static final String VANISH_EXPIRE_TIME = "VANISH_EXPIRE_TIME";

    private GuideInfoConstants() {
    }

}

