/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.rs;

import com.richstonedt.road.query.engine.cs.service.GaoDeRoadService;
import com.richstonedt.road.query.engine.model.road.GaoDeRoad;
import com.richstonedt.road.query.engine.rs.utils.RoadQueryEngineRsUtils;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <b><code>RoadController</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 14:38
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-rs 0.1.0
 */
@RestController
@RequestMapping("v1.0")
@Api(tags = "[road-query-engine 0.1]道路查询相关接口")
public class GaoDeRoadController {

    /**
     * LOG
     */
    private final static Logger LOG = LoggerFactory.getLogger(GaoDeRoadController.class);

    /**
     * Return the SideLength
     *
     * @return property value of sideLength
     * @since road-query-engine-rs 0.1.0
     */
    public int getSideLength() {
        return sideLength;
    }

    /**
     * Set the SideLength
     *
     * @param sideLength value to be assigned to property sideLength
     * @since road-query-engine-rs 0.1.0
     */
    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * The constant sideLength.
     */
    private int sideLength = 50;

    /**
     * The Service.
     */
    @Autowired
    private GaoDeRoadService gaoDeRoadService;

    /**
     * getRoadById
     *
     * @param id id
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/road/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据Id查询道路", notes = "Get road by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful query", response = GaoDeRoad.class, responseContainer = "one"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error")})
    public ResponseEntity<?> getRoadById(
            @ApiParam(value = "道路Id", required = true) @PathVariable(value = "id") String id
    ) {
        try{
            GaoDeRoad road = gaoDeRoadService.getRoadById(id);
            return new ResponseEntity<>(road,HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to search road by id. id:"+id,t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }

    /**
     * getRoadsByTypeId
     *
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/road/type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据道路类型Id查询道路", notes = "Get road by type Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful query", response = GaoDeRoad.class, responseContainer = "one"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error")})
    public ResponseEntity<?> getRoadsByTypeId(
            @ApiParam(value = "道路类型Id", required = true) @PathVariable(value = "id")String typeId
    ) {
        try{
            List<GaoDeRoad> result = gaoDeRoadService.getRoadByTypeId(typeId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to get roads by type id. type id:"+typeId,t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }

    /**
     * searchRoadByName
     *
     * @param searchWord    search word
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/road/search",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据名称模糊查询道路",notes = "Search road by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful query", response = GaoDeRoad.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ResponseEntity<?> searchRoadByName(
        @ApiParam(value = "查询名称,多个用英文逗号隔开",required = true) @RequestParam(value = "searchWord",defaultValue = "")String searchWord
    ) {
        try{
            List<String> searchWords = new ArrayList<>();
            Collections.addAll(searchWords,searchWord.split(","));
            List<Map<String,Object>> result = gaoDeRoadService.fuzzyRoadSearchByNames(searchWords);
            return new ResponseEntity<Object>(result,HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to fuzzy search road by search words. search words:"+ StringUtils.join(searchWord.split(",")),t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }

    /**
     * getRoadByPoint
     *
     * @param longitude     longitude
     * @param latitude      latitude
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/roadByPoint",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据坐标点查询道路", notes = "Get Road By point")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful query", response = GaoDeRoad.class, responseContainer = "one"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ResponseEntity<?> getRoadByPoint(
            @ApiParam(value = "经度",required = true) @RequestParam(value = "longitude")double longitude,
            @ApiParam(value = "纬度",required = true) @RequestParam(value = "latitude")double latitude
    ){
        try{
            return new ResponseEntity<>(gaoDeRoadService.getNearbyRoad(longitude,latitude, sideLength),HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to get a road to which this point belongs.",t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }

    /**
     * getRoadTree
     *
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/road/tree",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "道路结构树", notes = "Get Road tree")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful query"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ResponseEntity<?> getRoadTree(
            @ApiParam(value = "道路类型id,多个用英文逗号隔开",required = true) @RequestParam(value = "typeId")String typeIds
    ){
        try{
            List<String> typeIdList = new ArrayList<>();
            Collections.addAll(typeIdList,typeIds.split(","));
            return new ResponseEntity<Object>(gaoDeRoadService.getRoadTree(typeIdList),HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to get road tree by type ids. type ids:"+StringUtils.join(typeIds.split(",")),t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }

    /**
     * downloadAllRoads
     *
     * @return org.springframework.http.ResponseEntity<?>
     * @see ResponseEntity<?>
     * @since road-query-engine-rs 0.1.0
     */
    @RequestMapping(value = "/road/download",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "下载道路信息", notes = "Download all road info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful"),
            @ApiResponse(code = 404, message = "no found"),
            @ApiResponse(code = 500, message = "internal server error") })
    public ResponseEntity<?> downloadAllRoads(
            @ApiParam(value = "网格边长,默认值50米") @RequestParam(value = "sideLength",defaultValue = "50")int sideLength,
            @ApiParam(value = "是否强制重载数据,默认网格边长变化时才会重载") @RequestParam(value = "forceReload",defaultValue = "false")boolean forceReload
    ){
        try{
            int length = sideLength;
            // illegal value solution
            if (sideLength<=0){
                length = 50;
            }
            boolean reload = false;
            if (length!= this.sideLength){
                reload=true;
            }
            setSideLength(length);
            if (forceReload){
                // force reload
                gaoDeRoadService.downloadAllRoads(this.sideLength,true);
            }else{
                gaoDeRoadService.downloadAllRoads(this.sideLength,reload);
            }
            return new ResponseEntity<Object>(HttpStatus.OK);
        }catch(Throwable t){
            LOG.error("Fail to download all road info",t);
            return RoadQueryEngineRsUtils.newResponseEntity(t);
        }
    }
}
