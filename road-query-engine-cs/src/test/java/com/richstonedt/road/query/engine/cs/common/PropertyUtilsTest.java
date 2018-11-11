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

/**
 * <b><code>PropertyUtilsTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/24 11:18
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PropertyUtilsTest {
    /**
     * test getMapProperty
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void getMapProperty() throws Exception {
        String result = PropertyUtils.getMapProperty("baidu.map.key");
        Assert.assertEquals("linnry1VNYwWks7SsEs28ho2lfXxYGO1",result);
    }
}