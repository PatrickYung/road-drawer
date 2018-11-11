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
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * <b><code>HttpUtils</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/24 10:21
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-cs 0.1.0
 */
public class HttpUtils {

    /**
     * The constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * loadJSON
     *
     * @param url url
     * @return java.lang.String string
     * @see String
     * @since road -query-engine-cs 0.1.0
     */
    public static String loadJSON(String url){
        StringBuilder builder = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            connection.connect();
            InputStream in = connection.getInputStream();
            byte[] bytes = new byte[1024];
            int i;
            while ((i=in.read(bytes))!=-1){
                builder.append(new String(bytes,0,i,"UTF-8"));
            }
            in.close();
            return builder.toString();
        } catch (IOException e) {
            String error = "Error when get connection.url:"+url;
            LOG.error(error,e);
            throw new RoadQueryEngineException(error,e);
        }
    }
}
