/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.dao.road;

import com.richstonedt.road.query.engine.model.road.RoadCoordinate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <b><code>RoadCoordinateDao</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/4 11:34
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public interface RoadCoordinateDao {

    /**
     * save
     *
     * @param coordinate    coordinate
     * @since road-query-engine-cs 0.1.0
     */
    void save(RoadCoordinate coordinate);

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
     * searchNearByCoordinatesByGrid
     *
     * if around  == true, 8 grids that surround center grid to which the point belongs will be included when querying.
     * the value 8 depends on the side length of grid and now 8 is temporary
     *
     * @param x         gird coordinate x
     * @param y         grid coordinate y
     * @param around    if the around grid is include
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.RoadCoordinate>
     * @see List<RoadCoordinate>
     * @since road-query-engine-cs 0.1.0
     */
    List<RoadCoordinate> searchNearByCoordinatesByGrid(@Param(value = "x") int x, @Param(value = "y") int y , @Param(value = "around") boolean around);

    /**
     * deleteAll
     *
     * @since road-query-engine-cs 0.1.0
     */
    void deleteAll();
}
