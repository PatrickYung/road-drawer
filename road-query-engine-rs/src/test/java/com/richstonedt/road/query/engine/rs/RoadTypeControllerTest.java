/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.rs;

import com.richstonedt.road.query.engine.model.road.RoadType;
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
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <b><code>RoadTypeControllerTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 10:26
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-be 0.1.0
 */
@SpringBootTest(classes = TestConfigs.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RoadTypeControllerTest {

    /**
     * setUp
     *
     */
    @Before
    public void setUp() throws Exception {
        jdbcTemplate.update(
                "INSERT INTO road_types(id, name, code) VALUES ('id1','name1','code1')"
        );
        jdbcTemplate.update(
                "INSERT INTO road_types(id, name, code) VALUES ('id2','name2','code2')"
        );
    }

    /**
     * Road type controller
     */
    @Autowired
    RoadTypeController controller;

    /**
     * tearDown
     *
     */
    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute(
                "DELETE FROM road_types"
        );
    }

    /**
     * test getRoadTypeById
     *
     * @since road-query-engine-rs 0.1.0
     */
    @Test
    public void getRoadTypeById() throws Exception {
        ResponseEntity<?> responseEntity = controller.getRoadTypeById("id1");
        RoadType roadType = (RoadType) responseEntity.getBody();
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assert.assertNotNull(roadType);
        Assert.assertEquals("id1",roadType.getId());
        Assert.assertEquals("name1",roadType.getName());
        Assert.assertEquals("code1",roadType.getCode());
    }

    /**
     * test getRoadTypes
     *
     * @since road-query-engine-rs 0.1.0
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getRoadTypes() throws Exception {
        ResponseEntity<?> responseEntity = controller.getRoadTypes();
        List<RoadType> roadTypes = (List<RoadType>) responseEntity.getBody();
        Assert.assertFalse(CollectionUtils.isEmpty(roadTypes));
        Assert.assertEquals(2,roadTypes.size());
        RoadType roadType = roadTypes.get(1);
        Assert.assertEquals("id2",roadType.getId());
        Assert.assertEquals("name2",roadType.getName());
        Assert.assertEquals("code2",roadType.getCode());
    }

    /**
     * JdbcTemplate
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

}