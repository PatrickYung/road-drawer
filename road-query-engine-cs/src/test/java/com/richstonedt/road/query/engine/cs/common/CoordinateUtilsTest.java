/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.common;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * <b><code>CoordinateUtilsTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/3 10:17
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-be 0.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CoordinateUtilsTest {
    /**
     * test convertGCJ02ToBD09
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void convertGCJ02ToBD09() throws Exception {
        Map<String,Double> result = CoordinateUtils.convertGCJ02ToBD09(113.3215142,23.13647083);
        Assert.assertNotNull(result);
        Assert.assertEquals(113.32803507452,result.get("longitude"),0.0001);
        Assert.assertEquals(23.142274290399,result.get("latitude"),0.0001);
    }

    /**
     * test distanceBetweenTwoPoints
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void testDistanceBetweenTwoPoints() throws Exception{
        double distance = CoordinateUtils.distanceBetweenTwoPoints(113.32803507452,23.142274290399,0.0,0.0);
        Assert.assertTrue(distance>0);
        Assert.assertEquals(1.2395875254353331E7,distance,0.000001);
    }

    /**
     * test calculateGridCoordinate
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void testCalculateGridCoordinate() throws Exception{
        Map<String,Integer> result = CoordinateUtils.calculateGridCoordinate(113.32803507452,23.142274290399,50);
        Assert.assertNotNull(result);
        Assert.assertEquals(252313,(int)result.get("x"));
        Assert.assertEquals(51524,(int)result.get("y"));
    }

}