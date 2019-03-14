/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LocationUtils
 * Author:   17061157_王薛
 * Date:     2018/9/3 15:51
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.suning.sntk.web.util;

import ch.hsr.geohash.GeoHash;

/**
 * 功能描述：经纬度计算距离
 *
 * @author 17061157_王薛
 * @since 2018/9/3
 */
public class LocationUtils {

    private final static double EARTH_RADIUS = 6378.137;

    private final static int GEO_HASH_DEFAULT_PRECISION = 6;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1 A点纬度
     * @param lng1 A点经度
     * @param lat2 B点纬度
     * @param lng2 B点经度
     * @return A和B之间的距离
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    /**
     * 功能描述: 计算经纬度的 6位 GEO hash值 <br>
     *
     * @param lat 纬度
     * @param lng 经度
     * @return java.lang.String
     * @author 17061157_王薛
     * @since 16:41  2018/9/3
     */
    public static String calcGeoHashCode6(String lat, String lng) {
        GeoHash geoHash = GeoHash.withCharacterPrecision(Double.valueOf(lat),
                Double.valueOf(lng), GEO_HASH_DEFAULT_PRECISION);
        return geoHash.toBase32();
    }

    public static String calcGeoHashCode6(double lat, double lng) {
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lng, GEO_HASH_DEFAULT_PRECISION);
        return geoHash.toBase32();
    }


}
