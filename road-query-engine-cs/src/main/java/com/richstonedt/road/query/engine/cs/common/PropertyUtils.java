/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.common;

import com.richstonedt.road.query.engine.model.common.RoadQueryEngineException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * <b><code>PropertyUtils</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/24 10:14
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public class PropertyUtils {

    /**
     * The constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PropertyUtils.class);

    /**
     * getMapProperty
     *
     * @param key key
     * @return java.lang.String string
     * @see String
     * @since road -query-engine-cs 0.1.0
     */
    public static String getMapProperty(String key){
        Properties props = new Properties();
        try {
            props.load(PropertyUtils.class.getResourceAsStream("/map.properties"));
            if (props.isEmpty()){
                return "";
            }
            return props.get(key).toString();
        } catch (IOException e) {
            String error = "Fail to read map.properties. key:"+key;
            LOG.error(error);
            throw new RoadQueryEngineException(error,e);
        }
    }
}
