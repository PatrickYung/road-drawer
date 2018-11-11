/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.rs.utils;

import com.richstonedt.road.query.engine.model.common.RoadQueryEngineErrorCodes;
import com.richstonedt.road.query.engine.model.common.RoadQueryEngineException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <b><code>RoadQueryEngineRsUtils</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 10:11
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-rs 0.1.0
 */
public class RoadQueryEngineRsUtils {

    /**
     * newResponseEntity
     *
     * @param t  t
     * @return org.springframework.http.ResponseEntity<java.util.Map<java.lang.String,java.lang.Object>>
     * @see ResponseEntity<Map<String,Object>>
     * @since road-query-engine-rs 0.1.0
     */
    public static ResponseEntity<Map<String, Object>> newResponseEntity(Throwable t) {
        Map<String, Object> errorMap = new HashMap<>();
        if (t instanceof RoadQueryEngineException) {
            RoadQueryEngineException e = (RoadQueryEngineException) t;
            if (!StringUtils.isEmpty(e.getErrorCode())) {
                errorMap.put("errorCode", e.getErrorCode());
            } else {
                errorMap.put("errorCode", RoadQueryEngineErrorCodes.OTHER);
            }
        } else if (t instanceof IllegalArgumentException) {
            errorMap.put("errorCode", RoadQueryEngineErrorCodes.ILLEGAL_ARGUMENT);
        } else {
            errorMap.put("errorCode", RoadQueryEngineErrorCodes.OTHER);
        }
        errorMap.put("errorMessage", t.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
