/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.dao.road;

import com.richstonedt.road.query.engine.model.road.GaoDeRoad;

import java.util.List;

/**
 * <b><code>GaoDeRoadDao</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/4 9:52
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public interface GaoDeRoadDao {

    /**
     * save
     *
     * @param road      road
     * @since road-query-engine-cs 0.1.0
     */
    void save(GaoDeRoad road);

    /**
     * searchById
     *
     * @param id    id
     * @return com.richstonedt.road.query.engine.model.road.GaoDeRoad
     * @see GaoDeRoad
     * @since road-query-engine-cs 0.1.0
     */
    GaoDeRoad searchById(String id);

    /**
     * searchByTypeId
     *
     * @param typeId    type id
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.GaoDeRoad>
     * @see List<GaoDeRoad>
     * @since road-query-engine-cs 0.1.0
     */
    List<GaoDeRoad> searchByTypeId(String typeId);

    /**
     * fuzzySearchByName
     *
     * @param names  names
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.GaoDeRoad>
     * @see List<GaoDeRoad>
     * @since road-query-engine-cs 0.1.0
     */
    List<GaoDeRoad> fuzzySearchByName(List<String> names);

    /**
     * deleteAll
     *
     * @since road-query-engine-cs 0.1.0
     */
    void deleteAll();
}
