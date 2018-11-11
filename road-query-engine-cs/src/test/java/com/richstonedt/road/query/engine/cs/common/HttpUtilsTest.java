/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.common;

import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URLEncoder;

/**
 * <b><code>HttpUtilsTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/24 11:00
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class HttpUtilsTest {

    /**
     * test loadJSON
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void loadJSON() throws Exception {
        String url = "http://restapi.amap.com/v3/road/roadname?keywords="+ URLEncoder.encode("天河大道","UTF-8")+
                "&city=020&output=json&offset=100&page=1&key="+PropertyUtils.getMapProperty("gaode.map.key");
        String result = HttpUtils.loadJSON(url);
        Assert.assertNotNull(result);
        JSONObject jsonObj = JSONObject.fromObject(result);
        Assert.assertEquals("1",jsonObj.get("status"));
    }

}