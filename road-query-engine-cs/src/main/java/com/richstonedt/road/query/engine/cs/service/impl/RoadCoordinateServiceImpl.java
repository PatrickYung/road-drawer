/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service.impl;

import com.richstonedt.road.query.engine.cs.dao.road.RoadCoordinateDao;
import com.richstonedt.road.query.engine.cs.service.RoadCoordinateService;
import com.richstonedt.road.query.engine.model.road.RoadCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>RoadCoordinateServiceImpl</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/4 12:01
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-be 0.1.0
 */
@Service
public class RoadCoordinateServiceImpl implements RoadCoordinateService {
    /**
     * The Road coordinate dao.
     */
    @Autowired
    private RoadCoordinateDao roadCoordinateDao;

    /**
     * searchById
     *
     * @param id id
     * @return com.richstonedt.road.query.engine.model.road.RoadCoordinate
     * @see RoadCoordinate
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public RoadCoordinate searchById(String id) {
        return roadCoordinateDao.searchById(id);
    }

    /**
     * save
     *
     * @param coordinate coordinate
     * @return java.lang.String
     * @see String
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public String save(RoadCoordinate coordinate) {
        roadCoordinateDao.save(coordinate);
        return coordinate.getId();
    }

    /**
     * batchSave
     *
     * @param coordinates coordinates
     * @return java.util.List<java.lang.String>
     * @see List<String>
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public List<String> batchSave(List<RoadCoordinate> coordinates) {
        List<String> result = new ArrayList<>();
        for (RoadCoordinate coordinate : coordinates) {
            roadCoordinateDao.save(coordinate);
            result.add(coordinate.getId());
        }
        return result;
    }
}
