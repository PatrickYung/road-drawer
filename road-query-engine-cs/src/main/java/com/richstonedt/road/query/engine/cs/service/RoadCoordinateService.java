/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service;

import com.richstonedt.road.query.engine.model.road.RoadCoordinate;

import java.util.List;

/**
 * <b><code>RoadCoordinateService</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/4 11:58
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public interface RoadCoordinateService {
    /**
     * searchById
     *
     * @param id    id
     * @return com.richstonedt.road.query.engine.model.road.RoadCoordinate
     * @see RoadCoordinate
     * @since road-query-engine-cs 0.1.0
     */
    RoadCoordinate searchById(String id);

    /**
     * save
     *
     * @param coordinate    coordinate
     * @return java.lang.String
     * @see String
     * @since road-query-engine-cs 0.1.0
     */
    String save(RoadCoordinate coordinate);

    /**
     * batchSave
     *
     * @param coordinates   coordinates
     * @return java.util.List<java.lang.String>
     * @see List<String>
     * @since road-query-engine- 0.1.0
     */
    List<String> batchSave(List<RoadCoordinate> coordinates);
}
