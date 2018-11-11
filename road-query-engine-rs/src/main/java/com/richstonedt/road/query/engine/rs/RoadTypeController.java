/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.rs;

import com.richstonedt.road.query.engine.cs.service.RoadTypeService;
import com.richstonedt.road.query.engine.model.road.RoadType;
import com.richstonedt.road.query.engine.rs.utils.RoadQueryEngineRsUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b><code>RoadTypeController</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/22 18:20
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-rs 0.1.0
 */
@RestController
@RequestMapping("/v1.0")
@Api(tags = "[road-query-engine 0.1]道路类型查询相关接口")
public class RoadTypeController {

    /**
     * The constant LOG.
     */
    private final static Logger LOG = LoggerFactory.getLogger(RoadTypeController.class);

    /**
     * The Road type service.
     */
    @Autowired
    private RoadTypeService roadTypeService;


    /**
     * getRoadTypeById
     *
     * @param id    the id
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/roadType/{id}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "按ID查询道路类型", notes = "Get road type by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful query", response = RoadType.class, responseContainer = "one"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ResponseEntity<?> getRoadTypeById(
            @ApiParam(value = "道路类型ID")@PathVariable(value = "id") String id
    ){
        try{
            RoadType result = roadTypeService.getRoadTypeById(id);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to get road type by id.id:"+id,t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }

    /**
     * getRoadTypes
     *
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/roadType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询道路类型列表", notes = "Get road types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful query", response = RoadType.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ResponseEntity<?> getRoadTypes(){
        try{
            List<RoadType> roadTypes = roadTypeService.getRoadTypes();
            return new ResponseEntity<>(roadTypes, HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to get all road types",t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }
}
