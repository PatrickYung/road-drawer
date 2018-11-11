/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <b><code>CoordinateUtils</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/3 9:38
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public class CoordinateUtils {

    /**
     * convertGCJ02ToBD09
     * <p>
     * convert GCJ02(GaoDe Map) coordinate to BD09(BaiDu Map) coordinate
     * return a map that contains key "longitude" and "latitude"
     * precision is 0.0001
     *
     * @param gcjLat GCJ02 latitude
     * @param gcjLng GCJ02 longitude
     * @return java.util.Map<java.lang.String,java.lang.Double>
     * @see Map<String,Double>
     * @since road-query-engine-cs 0.1.0
     */
    public static Map<String, Double> convertGCJ02ToBD09(double gcjLng, double gcjLat) {
        double bdLat, bdLng;
        double z = Math.sqrt(gcjLng * gcjLng + gcjLat * gcjLat) + 0.00002 * Math.sin(gcjLat + Math.PI);
        double theta = Math.atan2(gcjLat, gcjLng) + 0.000003 * Math.cos(gcjLng * Math.PI);
        bdLng = z * Math.cos(theta) + 0.0065;
        bdLat = z * Math.sin(theta) + 0.006;
        Map<String, Double> result = new HashMap<>();
        result.put("longitude", bdLng);
        result.put("latitude", bdLat);
        return result;
    }

    /**
     * calculateGridCoordinate
     * <p>
     * calculate grid coordinate(x,y) according to grid side length
     * default origin point is (0,0)
     *
     * @param targetLng target point longitude
     * @param targetLat target point latitude
     * @param length    grid length
     * @return java.util.Map<java.lang.String,java.lang.Integer>
     * @see Map<String,Integer>
     * @since road-query-engine-cs 0.1.0
     */
    public static Map<String, Integer> calculateGridCoordinate(double targetLng, double targetLat, int length) {
        if (length <= 0) {
            length=50;
        }
        double distanceX = distanceBetweenTwoPoints(targetLng, 0.0, 0.0, 0.0);
        double distanceY = distanceBetweenTwoPoints(0.0, targetLat, 0.0, 0.0);
        Map<String, Integer> result = new HashMap<>();
        result.put("x", (int) (((Double)distanceX).intValue() % length == 0 ? distanceX / length : (distanceX / length + 1)));
        result.put("y", (int) (((Double)distanceY).intValue() % length == 0 ? distanceY / length : (distanceY / length + 1)));
        return result;
    }

    /**
     * distanceBetweenTwoPoints
     *
     * calculate the distance between two point on the Earth
     * the result unit is meter(m)
     *
     * @param lng1 longitude1
     * @param lat1 latitude1
     * @param lng2 longitude2
     * @param lat2 latitude2
     * @return double
     * @see double
     * @since road-query-engine-cs 0.1.0
     */
    public static double distanceBetweenTwoPoints(double lng1, double lat1, double lng2, double lat2) {
        double R = 6378137;// radius of Earth
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        double a = lat1 - lat2;
        double b = (lng1 - lng2) * Math.PI / 180;
        double sa = Math.sin(a / 2.0);
        double sb = Math.sin(b / 2.0);
        return 2 * R * Math.asin(Math.sqrt(sa * sa + Math.cos(lat1) * Math.cos(lat2) * sb * sb));
    }
}
