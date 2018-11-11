/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service.impl;

import com.richstonedt.road.query.engine.cs.TestConfigs;
import com.richstonedt.road.query.engine.cs.service.RoadTypeService;
import com.richstonedt.road.query.engine.model.road.RoadType;
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

import java.util.List;

/**
 * <b><code>RoadTypeServiceImplTest</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/22 17:35
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-cs 0.1.0
 */
@SpringBootTest(classes = TestConfigs.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RoadTypeServiceImplTest {

    /**
     * The Service.
     */
    @Autowired
    private RoadTypeService service;

    /**
     * setUp
     *
     * @throws Exception the exception
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
     * tearDown
     *
     * @throws Exception the exception
     */
    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute(
                "DELETE FROM road_types"
        );
    }

    /**
     * test getRoadTypeByName
     *
     * @throws Exception the exception
     * @since road -query-engine-cs 0.1.0
     */
    @Test
    public void getRoadTypeByName() throws Exception {
        RoadType roadType = service.getRoadTypeByName("name1");
        Assert.assertNotNull(roadType);
        Assert.assertEquals("id1",roadType.getId());
        Assert.assertEquals("name1",roadType.getName());
        Assert.assertEquals("code1",roadType.getCode());
    }

    /**
     * getRoadTypes
     *
     * @throws Exception the exception
     * @since road -query-engine-cs 0.1.0
     */
    @Test
    public void getRoadTypes() throws Exception{
        List<RoadType> roadTypes = service.getRoadTypes();
        Assert.assertNotNull(roadTypes);
        Assert.assertFalse(CollectionUtils.isEmpty(roadTypes));
        Assert.assertEquals(2,roadTypes.size());
        RoadType data = roadTypes.get(0);
        Assert.assertEquals("id1",data.getId());
        Assert.assertEquals("name1",data.getName());
        Assert.assertEquals("code1",data.getCode());
    }

    /**
     * JdbcTemplate
     */
    @Autowired
    JdbcTemplate jdbcTemplate;
}