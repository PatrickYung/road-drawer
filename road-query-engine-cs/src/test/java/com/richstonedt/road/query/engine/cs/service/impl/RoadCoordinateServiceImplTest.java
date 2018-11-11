/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service.impl;

import com.richstonedt.road.query.engine.cs.TestConfigs;
import com.richstonedt.road.query.engine.cs.service.RoadCoordinateService;
import com.richstonedt.road.query.engine.model.road.RoadCoordinate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>RoadCoordinateServiceImplTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/4 14:03
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
@SpringBootTest(classes = TestConfigs.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RoadCoordinateServiceImplTest {
    /**
     * setUp
     *
     */
    @Before
    public void setUp() throws Exception {
        jdbcTemplate.update(
                "INSERT INTO road_coordinates(id, longitude, latitude, road_id, x, y) VALUES ('id1',123.123,11.11,'roadId1',100,101)"
        );
        jdbcTemplate.update(
                "INSERT INTO road_coordinates(id, longitude, latitude, road_id, x, y) VALUES ('id2',111.111,22.22,'roadId2',200,201)"
        );
    }

    /**
     * tearDown
     *
     */
    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute(
                "DELETE FROM road_coordinates"
        );
    }

    /**
     * test searchById
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void searchById() throws Exception {
        RoadCoordinate coordinate = service.searchById("id1");
        Assert.assertNotNull(coordinate);
        Assert.assertEquals("id1",coordinate.getId());
        Assert.assertEquals(123.123,coordinate.getLongitude(),0);
        Assert.assertEquals(11.11,coordinate.getLatitude(),0);
        Assert.assertEquals("roadId1",coordinate.getRoadId());
        Assert.assertEquals(100,coordinate.getX());
        Assert.assertEquals(101,coordinate.getY());
    }

    /**
     * test save
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void save() throws Exception {
        RoadCoordinate coordinate = new RoadCoordinate();
        coordinate.setLatitude(33.33);
        coordinate.setLongitude(144.44);
        coordinate.setX(60);
        coordinate.setY(80);
        coordinate.setRoadId("testRoadId");
        service.save(coordinate);
        RoadCoordinate result = service.searchById(coordinate.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(coordinate.getLatitude(),result.getLatitude(),0);
        Assert.assertEquals(coordinate.getLongitude(),result.getLongitude(),0);
        Assert.assertEquals(coordinate.getRoadId(),result.getRoadId());
        Assert.assertEquals(coordinate.getX(),result.getX());
        Assert.assertEquals(coordinate.getY(),result.getY());
    }

    /**
     * test batchSave
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void batchSave() throws Exception {
        RoadCoordinate coordinate = new RoadCoordinate();
        coordinate.setLatitude(33.33);
        coordinate.setLongitude(144.44);
        coordinate.setX(60);
        coordinate.setY(80);
        coordinate.setRoadId("testRoadId");
        RoadCoordinate coordinate2 = new RoadCoordinate();
        coordinate2.setLatitude(33.33);
        coordinate2.setLongitude(144.44);
        coordinate2.setX(60);
        coordinate2.setY(80);
        coordinate2.setRoadId("testRoadId");
        List<RoadCoordinate> coordinates = new ArrayList<>();
        coordinates.add(coordinate);
        coordinates.add(coordinate2);
        List<String> result = service.batchSave(coordinates);
        Assert.assertEquals(2,result.size());
    }

    /**
     * JdbcTemplate
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * The Service.
     */
    @Autowired
    private RoadCoordinateService service;

}