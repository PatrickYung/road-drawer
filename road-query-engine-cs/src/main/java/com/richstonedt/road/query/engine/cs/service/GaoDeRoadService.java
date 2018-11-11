/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service;

import com.richstonedt.road.query.engine.model.road.GaoDeRoad;

import java.util.List;
import java.util.Map;

/**
 * <b><code>GaoDeRoadService</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 16:30
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public interface GaoDeRoadService {

    /**
     * getRoadById
     *
     * @param id    id
     * @return com.richstonedt.road.query.engine.model.road.GaoDeRoad
     * @see GaoDeRoad
     * @since road-query-engine-cs 0.1.0
     */
    GaoDeRoad getRoadById(String id);

    /**
     * batchSave
     *
     * @param roads     roads
     * @return java.util.List<java.lang.String>
     * @see List<String>
     * @since road-query-engine-cs 0.1.0
     */
    List<String> batchSave(List<GaoDeRoad> roads);

    /**
     * save
     *
     * @param road  road
     * @since road-query-engine-cs 0.1.0
     */
    String save(GaoDeRoad road);

    /**
     * getRoadByTypeId
     *
     * @param typeId    type id
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.GaoDeRoad>
     * @see List<GaoDeRoad>
     * @since road-query-engine-cs 0.1.0
     */
    List<GaoDeRoad> getRoadByTypeId(String typeId);


    /**
     * downloadAllRoads
     *
     * @param sideLength    side length
     * @param reload        reload
     * @since road-query-engine-cs 0.1.0
     */
    int downloadAllRoads(int sideLength,boolean reload);


    /**
     * fuzzyRoadSearchByNames
     *
     * @param names  names
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @see List<Map<String,Object>>
     * @since road-query-engine-cs 0.1.0
     */
    List<Map<String,Object>> fuzzyRoadSearchByNames(List<String> names);

    /**
     * getRoadTree
     *
     * @param typeIds   type ids
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @see List<Map<String,Object>>
     * @since road-query-engine-cs 0.1.0
     */
    List<Map<String,Object>> getRoadTree(List<String> typeIds);

    /**
     * getNearbyRoad
     *
     * @param longitude     longitude
     * @param latitude      latitude
     * @param sideLength    side length
     * @return com.richstonedt.road.query.engine.model.road.GaoDeRoad
     * @see GaoDeRoad
     * @since road-query-engine-cs 0.1.0
     */
    GaoDeRoad getNearbyRoad(double longitude,double latitude,int sideLength);
}
