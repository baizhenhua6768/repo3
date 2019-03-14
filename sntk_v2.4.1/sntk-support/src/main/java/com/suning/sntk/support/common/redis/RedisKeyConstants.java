package com.suning.sntk.support.common.redis;

/**
 * redis key 常量<br>
 *
 * @author 14060886 祝翔龙
 */
public class RedisKeyConstants {

	private RedisKeyConstants() {
	}

	// 5秒
	public static final int FIVE_SECOND = 5;

	// 一分钟 60秒
	public static final int ONE_MINUTE_S = 60;

	// 五分钟
	public static final int FIVE_MINUTE = 300;

	// 十分钟
	public static final int TEN_MINUTE = 600;

	// 半小时
	public static final int HALF_AN_HOUR = 1800;

	// 一小时 3600秒
	public static final int ONE_HOUR_S = 60 * ONE_MINUTE_S;

	// 一天 24 小时
	public static final int ONE_DAY_S = 24 * ONE_HOUR_S;

	// 7天
	public static final int SEVEN_DAY_S = 7 * ONE_DAY_S;

	// 一秒的毫秒数
	public static final int MILIPERSECOND = 1000;

	// 一天的毫秒数
	public static final int MILIOFDAY = ONE_DAY_S * MILIPERSECOND;

	// 一分钟的毫秒数
	// 每分钟刷新一次
	public static final int FLUSH_ROUTER_INTERVAL = ONE_MINUTE_S * MILIPERSECOND;

	// 人员信息key(工号)
	public static final String REDIS_CACHE_EMP_INFO = "SSIP:REDIS:CACHE:EMP_INFO_%s";

	// 门店列表信息key(工号+组织层级)
	public static final String REDIS_CACHE_STORELIST_INFO = "SSIP:REDIS:CACHE:STORELIST_INFO_%s_%s";

	// 图片信息key(文件仓库地址)
	public static final String REDIS_CACHE_IMG_INFO = "SSIP:REDIS:CACHE:IMG_INFO_%s";

	// 商品类型
	public static final String CMMDTY_TYPE = "SSIP:CACHE:CMMDTY_TYPE:%s";

	// 商品参数
	public static final String CMMDTY_PARAMETER = "SSIP:CACHE:CMMDTY_PARAMETER:%s";

	// 商品图文详情
	public static final String CMMDTY_DESCRIPTION = "SSIP:CACHE:CMMDTY_DESCRIPTION:%s";

	// 商品子码商品，簇子码商品列表
	public static final String CMMDTY_SUB = "SSIP:CACHE:CMMDTY_SUB:%s";

	// 商品基本信息
	public static final String CMMDTY_PRODUCT_INFO = "SSIP:CACHE:CMMDTY_INFO:%s";

	// 缓存二级商品组的groupcode的顺序 每个门店一份
	public static final String DISPLAY_L2GROUP_CODE = "DISPALY:CACHE:L2GROUPCODE:%s";

	// 缓存监控设备信息列表
	public static final String DEVICE_MONITOR = "MONITOR:CACHE:DEVICE_MONITOR:%s";

	// 缓存上报的设备的告警信息
	public static final String DEVICE_MONITOR_UPLOD_ALARM = "MONITOR:CACHE:DEVICE_MONITOR_UPLOD_ALARM:%s:%s";

	// 商品四级页
	public static final String CMMDTY_DETAIL_INFO = "SSIP:CACHE:CMMDTY_DETAIL_INFO:%s:%s";

	// 缓存设备的心跳 无论是主动上报的设备 还是轮训的设备 如果在监控周期内 缓存中没有改设备的缓存了 则代表它这一段时间内没有心跳了
	// 则产生设备无心跳的告警
	// 缓存的时间为设备配置的监控周期时间（周期乘以告警阀值得到缓存时间），没有默认半小时
	public static final String DEVICE_MONITOR_HEAT_BEAT_CODE = "SSIP:MONITOR:CACHE:HEAT_BEAT_CODE:%s";

	public static final String DEVICE_MONITOR_NEED_TRIGGER_HASH = "SSIP:MONITOR:CACHE:NEED_TRIGGER:%s";

}
