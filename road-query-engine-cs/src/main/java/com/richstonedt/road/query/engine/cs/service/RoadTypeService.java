/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service;

import com.richstonedt.road.query.engine.model.road.RoadType;

import java.util.List;

/**
 * <b><code>RoadTypeService</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/22 17:25
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public interface RoadTypeService {
    /**
     * getRoadTypeByName
     *
     * @param name  name
     * @return com.richstonedt.road.query.engine.model.road.RoadType
     * @see RoadType
     * @since road-query-engine-cs 0.1.0
     */
    RoadType getRoadTypeByName(String name);

    /**
     * getRoadTypeById
     *
     * @param id    id
     * @return com.richstonedt.road.query.engine.model.road.RoadType
     * @see RoadType
     * @since road-query-engine-cs 0.1.0
     */
    RoadType getRoadTypeById(String id);

    /**
     * getRoadTypes
     *
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.RoadType>
     * @see List<RoadType>
     * @since road-query-engine-cs 0.1.0
     */
    List<RoadType> getRoadTypes();

}
