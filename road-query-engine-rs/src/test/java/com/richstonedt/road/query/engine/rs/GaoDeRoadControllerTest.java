/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.rs;

import com.richstonedt.road.query.engine.model.road.GaoDeRoad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * <b><code>GaoDeRoadControllerTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/4 15:48
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-rs 0.1.0
 */
@SpringBootTest(classes = TestConfigs.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GaoDeRoadControllerTest {
    /**
     * setUp
     *
     * @throws Exception exception
     */
    @Before
    public void setUp() throws Exception {
        jdbcTemplate.update(
                "INSERT INTO \"public\".\"road_info\" (\"id\", \"name\", \"city_code\", \"type_id\", \"point_count\", \"width\", \"center_lng\", \"center_lat\") VALUES ('1442617888253941259', 'S82佛山一环高速永安大道西出口3', '0757', '1368519156978160677', '14', '8', '113.079798', '23.213479');\n" +
                        "INSERT INTO \"public\".\"road_info\" (\"id\", \"name\", \"city_code\", \"type_id\", \"point_count\", \"width\", \"center_lng\", \"center_lat\") VALUES ('1442617888262329868', '321国道1', '0757', '1368519156978160673', '250', '12', '113.015445', '23.207463');\n" +
                        "INSERT INTO \"public\".\"road_info\" (\"id\", \"name\", \"city_code\", \"type_id\", \"point_count\", \"width\", \"center_lng\", \"center_lat\") VALUES ('1442617888270718477', '321国道2', '0757', '1368519156978160673', '1011', '20', '112.864871', '23.177211');\n" +
                        "INSERT INTO \"public\".\"road_info\" (\"id\", \"name\", \"city_code\", \"type_id\", \"point_count\", \"width\", \"center_lng\", \"center_lat\") VALUES ('1442617888279107086', 'S43广珠西线高速1', '0757', '1368519156810388512', '190', '12', '113.309309', '22.830581');\n" +
                        "INSERT INTO \"public\".\"road_info\" (\"id\", \"name\", \"city_code\", \"type_id\", \"point_count\", \"width\", \"center_lng\", \"center_lat\") VALUES ('1442617888287495695', 'S43广珠西线高速2', '0757', '1368519156810388512', '450', '12', '113.224058', '23.000891');\n" +
                        "INSERT INTO \"public\".\"road_info\" (\"id\", \"name\", \"city_code\", \"type_id\", \"point_count\", \"width\", \"center_lng\", \"center_lat\") VALUES ('1442617888295884304', '创业一路1', '0757', '1368519156978160677', '28', '4', '112.921075', '23.205503');\n"
        );
        jdbcTemplate.update(
                "INSERT INTO \"public\".\"road_coordinates\" (\"id\", \"longitude\", \"latitude\", \"road_id\", \"y\", \"x\") VALUES ('1442617954859489182', '113.007490514322498', '23.2151436649685508', '1442617888262329868', '51686', '251599');\n" +
                        "INSERT INTO \"public\".\"road_coordinates\" (\"id\", \"longitude\", \"latitude\", \"road_id\", \"y\", \"x\") VALUES ('1442617954867877791', '113.008134115163671', '23.2152853358723732', '1442617888262329868', '51687', '251601');\n"
        );
        jdbcTemplate.update(
                "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156810388512', '高速公路', '1');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160673', '国道', '2');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160674', '城市环路/城市快速路', '3');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160675', '省道', '4');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160676', '主要道路（城市主干道）', '5');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160677', '次要道路（城市次干道）', '6');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160678', '县道', '7');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160679', '乡村道路', '8');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160680', '区县内部道路', '9');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160681', '一般道路', '10');\n" +
                        "INSERT INTO \"public\".\"road_types\" (\"id\", \"name\", \"code\") VALUES ('1368519156978160682', '非导航道路', '11');"
        );

    }

    /**
     * tearDown
     *
     * @throws Exception exception
     */
    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("DELETE FROM road_info");
        jdbcTemplate.execute("DELETE FROM road_types");
        jdbcTemplate.execute("DELETE FROM road_coordinates");
    }

    /**
     * test getRoadById
     *
     * @since road-query-engine-rs 0.1.0
     */
    @Test
    public void getRoadById() throws Exception {
        ResponseEntity<?> responseEntity = controller.getRoadById("1442617888253941259");
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * test getRoadsByTypeId
     *
     * @since road-query-engine-rs 0.1.0
     */
    @Test
    public void getRoadsByTypeId() throws Exception {
        ResponseEntity<?> responseEntity = controller.getRoadsByTypeId("1368519156810388512");
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        List<GaoDeRoad> result = (List<GaoDeRoad>) responseEntity.getBody();
        Assert.assertEquals(2,result.size());
    }

    /**
     * test searchRoadByName
     *
     * @since road-query-engine-rs 0.1.0
     */
    @Test
    public void searchRoadByName() throws Exception {
        ResponseEntity<?> responseEntity = controller.searchRoadByName("国道,高速");
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * test getRoadByPoint
     *
     * @since road-query-engine-rs 0.1.0
     */
    @Test
    public void getRoadByPoint() throws Exception {
        ResponseEntity<?> responseEntity = controller.getRoadByPoint(113.0074,23.21514);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * test getRoadTree
     *
     * @since road-query-engine-rs 0.1.0
     */
    @Test
    public void getRoadTree() throws Exception {
        ResponseEntity<?> responseEntity = controller.getRoadTree("1368519156978160673,1368519156810388512");
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    /**
     * test downloadAllRoads
     *
     * @since road-query-engine-rs 0.1.0
     */
    @Test
    public void downloadAllRoads() throws Exception {
        // cost too long
    }

    /**
     * JdbcTemplate
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * The Controller.
     */
    @Autowired
    GaoDeRoadController controller;
}