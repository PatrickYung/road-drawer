/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service.impl;

import com.richstonedt.road.query.engine.cs.dao.road.RoadTypeDao;
import com.richstonedt.road.query.engine.cs.service.RoadTypeService;
import com.richstonedt.road.query.engine.model.road.RoadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b><code>RoadTypeServiceImpl</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/22 17:26
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-cs 0.1.0
 */
@Service
public class RoadTypeServiceImpl implements RoadTypeService {

    /**
     * The Dao.
     */
    @Autowired
    private RoadTypeDao dao;

    /**
     * getRoadTypeByName
     *
     * @param name name
     * @return com.richstonedt.road.query.engine.model.road.RoadType road type by name
     * @see RoadType
     * @since road -query-engine-cs 0.1.0
     */
    @Override
    public RoadType getRoadTypeByName(String name) {
        return dao.getRoadTypeByName(name);
    }

    /**
     * getRoadTypeById
     *
     * @param id id
     * @return com.richstonedt.road.query.engine.model.road.RoadType
     * @see RoadType
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public RoadType getRoadTypeById(String id) {
        return dao.getRoadTypeById(id);
    }

    /**
     * getRoadTypes
     *
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.RoadType>
     * @see List < RoadType >
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public List<RoadType> getRoadTypes() {
        return dao.getRoadTypes();
    }


}
