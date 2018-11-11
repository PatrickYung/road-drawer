/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service.impl;

import com.richstonedt.road.query.engine.cs.TestConfigs;
import com.richstonedt.road.query.engine.cs.service.GaoDeRoadService;
import com.richstonedt.road.query.engine.model.road.GaoDeRoad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b><code>GaoDeRoadServiceImplTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/2/4 10:23
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
@SpringBootTest(classes = TestConfigs.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class GaoDeRoadServiceImplTest {

    /**
     * setUp
     *
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
     */
    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("DELETE FROM road_info");
        jdbcTemplate.execute("DELETE FROM road_coordinates");
        jdbcTemplate.execute("DELETE FROM road_types");
    }

    /**
     * test getRoadById
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void getRoadById() throws Exception {
        GaoDeRoad result = service.getRoadById("1442617888253941259");
        Assert.assertNotNull(result);
        Assert.assertEquals("1442617888253941259",result.getId());
        Assert.assertEquals("S82佛山一环高速永安大道西出口3",result.getName());
        Assert.assertEquals(8,result.getWidth(),0);
        Assert.assertEquals("0757",result.getCityCode());
        Assert.assertEquals("1368519156978160677",result.getTypeId());
        Assert.assertEquals(14,result.getPointCount());
        Assert.assertEquals(113.079798,result.getCenterLng(),0);
        Assert.assertEquals(23.213479,result.getCenterLat(),0);
    }

    /**
     * test batchSave
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void batchSave() throws Exception {
        GaoDeRoad road = new GaoDeRoad();
        road.setName("testRoad");
        road.setTypeId("typeId3");
        road.setWidth(10);
        road.setPointCount(20);
        road.setCityCode("cityCode1");
        road.setCenterLng(123.321321);
        road.setCenterLat(22.2222);
        GaoDeRoad road2 = new GaoDeRoad();
        road2.setName("testRoad");
        road2.setTypeId("typeId3");
        road2.setWidth(10);
        road2.setPointCount(20);
        road2.setCityCode("cityCode1");
        road2.setCenterLng(123.321321);
        road2.setCenterLat(22.2222);
        List<GaoDeRoad> roads = new ArrayList<>();
        roads.add(road);
        roads.add(road2);
        List<String> result = service.batchSave(roads);
        Assert.assertEquals(2,result.size());
    }

    /**
     * test save
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void save() throws Exception {
        GaoDeRoad road = new GaoDeRoad();
        road.setName("testRoad");
        road.setTypeId("typeId1");
        road.setWidth(10);
        road.setPointCount(20);
        road.setCityCode("cityCode1");
        road.setCenterLng(123.321321);
        road.setCenterLat(22.2222);
        service.save(road);
        String id = road.getId();
        GaoDeRoad result = service.getRoadById(id);
        Assert.assertEquals(road.getName(),result.getName());
        Assert.assertEquals(road.getCityCode(),result.getCityCode());
        Assert.assertEquals(road.getTypeId(),result.getTypeId());
        Assert.assertEquals(road.getWidth(),result.getWidth(),0);
        Assert.assertEquals(road.getPointCount(),result.getPointCount());
        Assert.assertEquals(road.getCenterLng(),result.getCenterLng(),0);
        Assert.assertEquals(road.getCenterLat(),result.getCenterLat(),0);
    }

    /**
     * test getRoadByTypeId
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void getRoadByTypeId() throws Exception {
        List<GaoDeRoad> result = service.getRoadByTypeId("1368519156978160677");
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        Assert.assertEquals(2,result.size());
    }

    /**
     * test downloadAllRoads
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void downloadAllRoads() throws Exception {
        long result = service.downloadAllRoads(50,true);
        Assert.assertEquals(24,result);
    }

    /**
     * test fuzzyRoadSearchByNames
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void fuzzyRoadSearchByNames() throws Exception {
        List<String> names = new ArrayList<>();
        names.add("国道");
        names.add("高速");
        List<Map<String,Object>> result = service.fuzzyRoadSearchByNames(names);
        Assert.assertEquals(3,result.size());
        Map<String,Object> map1 = result.get(0);
        Assert.assertEquals("次要道路（城市次干道）",map1.get("roadType"));
        Map<String,Object> map2 = result.get(1);
        Assert.assertEquals("高速公路",map2.get("roadType"));
        Map<String,Object> map3 = result.get(2);
        Assert.assertEquals("国道",map3.get("roadType"));
    }

    /**
     * test getRoadTree
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void getRoadTree() throws Exception {
        List<String> typeIds = new ArrayList<>();
        typeIds.add("1368519156978160673");
        typeIds.add("1368519156810388512");
        List<Map<String,Object>> result = service.getRoadTree(typeIds);
        Assert.assertFalse(CollectionUtils.isEmpty(result));
        Assert.assertEquals(2,result.size());
    }

    /**
     * test getNearbyRoad
     *
     * @since road-query-engine-cs 0.1.0
     */
    @Test
    public void getNearbyRoad() throws Exception {
        GaoDeRoad road = service.getNearbyRoad(113.0074,23.21514,50);
        Assert.assertNotNull(road);
        Assert.assertEquals("321国道1",road.getName());

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
    GaoDeRoadService service;
}