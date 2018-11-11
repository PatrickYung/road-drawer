/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.service.impl;

import com.richstonedt.road.query.engine.cs.common.*;
import com.richstonedt.road.query.engine.cs.dao.road.GaoDeRoadDao;
import com.richstonedt.road.query.engine.cs.dao.road.RoadCoordinateDao;
import com.richstonedt.road.query.engine.cs.service.GaoDeRoadService;
import com.richstonedt.road.query.engine.cs.service.RoadCoordinateService;
import com.richstonedt.road.query.engine.cs.service.RoadTypeService;
import com.richstonedt.road.query.engine.model.common.RoadQueryEngineException;
import com.richstonedt.road.query.engine.model.excel.ExcelFile;
import com.richstonedt.road.query.engine.model.excel.ExcelSheet;
import com.richstonedt.road.query.engine.model.road.GaoDeRoad;
import com.richstonedt.road.query.engine.model.road.RoadCoordinate;
import com.richstonedt.road.query.engine.model.road.RoadType;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.WorkbookParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b><code>GaoDeRoadServiceImpl</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 16:25
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
@Service
public class GaoDeRoadServiceImpl implements GaoDeRoadService {

    /**
     * The constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(GaoDeRoadServiceImpl.class);

    /**
     * The Road type service.
     */
    @Autowired
    private RoadTypeService roadTypeService;

    /**
     * The Gao de road dao.
     */
    @Autowired
    private GaoDeRoadDao gaoDeRoadDao;

    /**
     * The Road coordinate service.
     */
    @Autowired
    private RoadCoordinateService roadCoordinateService;


    /**
     * The Road coordinate dao.
     */
    @Autowired
    private RoadCoordinateDao roadCoordinateDao;

    /**
     * getRoadById
     *
     * @param id id
     * @return com.richstonedt.road.query.engine.model.road.GaoDeRoad
     * @see GaoDeRoad
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public GaoDeRoad getRoadById(String id) {
        return gaoDeRoadDao.searchById(id);
    }

    /**
     * batchSave
     *
     * @param roads roads
     * @return java.util.List<java.lang.String>
     * @see List<String>
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public List<String> batchSave(List<GaoDeRoad> roads) {
        List<String> result = new ArrayList<>();
        for (GaoDeRoad road : roads) {
            gaoDeRoadDao.save(road);
            result.add(road.getId());
        }
        return result;
    }

    /**
     * save
     *
     * @param road road
     * @return java.lang.String
     * @see String
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public String save(GaoDeRoad road) {
        gaoDeRoadDao.save(road);
        return road.getId();
    }

    /**
     * getRoadByTypeId
     *
     * @param typeId type id
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.GaoDeRoad>
     * @see List<GaoDeRoad>
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public List<GaoDeRoad> getRoadByTypeId(String typeId) {
        if (StringUtils.isBlank(typeId)){
            return null;
        }
        return gaoDeRoadDao.searchByTypeId(typeId);
    }

    /**
     * downloadAllRoads
     *
     * @since road-query-engine-cs 0.1.0
     */
    @SuppressWarnings("unchecked")
    @Override
    public int downloadAllRoads(int sideLength,boolean reload) {
        if (!reload){
            return 0;
        }

        //delete all old data ( may need MQ to notify Spark)
        gaoDeRoadDao.deleteAll();
        roadCoordinateDao.deleteAll();

        if (sideLength<=0){
            sideLength=50;
        }
        /*  Get road names to download divided by city code*/
        List<Map<String,Object>> roadList = getRoadNamesFromExcelFile();

        Map<String,GaoDeRoad> roadMap = new HashMap<>();
        List<String> missingRoads = new ArrayList<>();
        int missingRoadCount = 0;
        LOG.info("------------------ Start downloading roads info -----------------");
        for (Map<String, Object> map : roadList) {
            String cityCode = (String) map.get("cityCode");
            List<String> roadNames = (List<String>) map.get("roadNames");
            for (String roadName : roadNames) {
                Map<String,GaoDeRoad> rawMap;
                try {
                    //download data from GaoDe map api
                    LOG.info("road name:\""+roadName+"\"  Start download json data from GaoDe API");
                    rawMap=getRoadFromHttp(cityCode,roadName,sideLength);
                    LOG.info("road name:\"" + roadName + "\"  download complete");
                } catch (UnsupportedEncodingException e) {
                    String error = "Fail to get road info from the Internet.cityCode:"+cityCode+", roadName:"+roadName;
                    LOG.error(error);
                    throw new RoadQueryEngineException(error,e);
                }
                if (rawMap!=null&&rawMap.size()!=0){
                    roadMap.putAll(rawMap);
                }else{
                    missingRoads.add(roadName);
                    missingRoadCount++;
                }
            }
        }
        LOG.info("------------------ Roads info download complete ------------------");
        //decorate duplicate road names
        List<GaoDeRoad> finalRoadList = removeDuplicateRoadNames(roadMap);
        LOG.info("未查询到信息的街道数量："+missingRoadCount);
        LOG.info("未查询到信息的街道名称："+missingRoads.toString());
        if (!CollectionUtils.isEmpty(finalRoadList)){
            //do save roads
            List<String> keys = batchSave(finalRoadList);
            if (CollectionUtils.isEmpty(keys)){
                LOG.error("Fail to save roads. roads list:"+ StringUtils.join(finalRoadList));
                throw new RoadQueryEngineException("Fail to save roads.");
            }else{
                //set road id for each point coordinate
                List<RoadCoordinate> coordinates = new ArrayList<>();
                for (int i = 0; i < finalRoadList.size(); i++) {
                    List<RoadCoordinate> roadCoordinates = finalRoadList.get(i).getCoordinates();
                    for (RoadCoordinate coordinate : roadCoordinates) {
                        coordinate.setRoadId(keys.get(i));
                    }
                    coordinates.addAll(roadCoordinates);
                }
                //do save road coordinates
                roadCoordinateService.batchSave(coordinates);
            }
            return finalRoadList.size()-missingRoadCount;
        }else{
            throw new RoadQueryEngineException("Fail to download data using GaoDe map API.");
        }
    }

    /**
     * fuzzyRoadSearchByNames
     *
     *
     * @param names name
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @see List<Map<String,Object>>
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public List<Map<String, Object>> fuzzyRoadSearchByNames(List<String> names) {
        List<String> searchWords = new ArrayList<>();
        if (CollectionUtils.isEmpty(names)){
            return null;
        }else {
            for (String name : names) {
                //add '%' for each keyword
                searchWords.add("%"+name+"%");
            }
        }

        // get all roads that matches condition
        List<GaoDeRoad> roads = gaoDeRoadDao.fuzzySearchByName(searchWords);

        /* search road type info and build a map for result */
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,List<GaoDeRoad>> roadTypeGroupedMap = new HashMap<>();
        for (GaoDeRoad road : roads) {
            String typeId = road.getTypeId();
            if (StringUtils.isBlank(typeId)){
                typeId="null";
            }
            if (roadTypeGroupedMap.containsKey(typeId)){
                roadTypeGroupedMap.get(typeId).add(road);
            }else{
                List<GaoDeRoad> roadList = new ArrayList<>();
                roadList.add(road);
                roadTypeGroupedMap.put(typeId,roadList);
            }
        }
        for (Map.Entry<String,List<GaoDeRoad>> entry : roadTypeGroupedMap.entrySet()) {
            String key = entry.getKey();
            Map<String,Object> map = new HashMap<>();
            if ("null".equalsIgnoreCase(key)){
                map.put("roadType",key);
                map.put("roads",entry.getValue());
            }else{
                String roadTypeName = roadTypeService.getRoadTypeById(key).getName();
                map.put("roadType",roadTypeName);
                map.put("roads",entry.getValue());
            }
            result.add(map);
        }

        return result;
    }

    /**
     * getRoadTree
     *
     * @param typeIds type ids
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @see List<Map<String,Object>>
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public List<Map<String, Object>> getRoadTree(List<String> typeIds) {
        List<Map<String,Object>> result = new ArrayList<>();
        for (String typeId : typeIds) {
            List<GaoDeRoad> roads = getRoadByTypeId(typeId);
            RoadType roadType = roadTypeService.getRoadTypeById(typeId);
            Map<String,Object> map = new HashMap<>();
            map.put("roads",roads);
            map.put("roadType",roadType.getName());
            result.add(map);
        }
        return result;
    }

    /**
     * getNearbyRoad
     *
     * the unit of side length is meter
     *
     * @param longitude  longitude
     * @param latitude   latitude
     * @param sideLength side length
     * @return com.richstonedt.road.query.engine.model.road.GaoDeRoad
     * @see GaoDeRoad
     * @since road-query-engine-cs 0.1.0
     */
    @Override
    public GaoDeRoad getNearbyRoad(double longitude, double latitude, int sideLength) {
        if (sideLength<=0){
            /*
              the proper value of side length depends on the distribution of road points
              50 is a default value
             */
            sideLength=50;
        }
        // to get grid coordinate according to the lng and lat
        Map<String,Integer> gridCoor = CoordinateUtils.calculateGridCoordinate(longitude,latitude,sideLength);
        if (gridCoor==null){
            return null;
        }
        int x = gridCoor.get("x");//grid coordinate x
        int y = gridCoor.get("y");//grid coordinate y

        // do search a point on roads that belongs to the same grid of target point or grids surrounded
        List<RoadCoordinate> nearbyCoordinates = roadCoordinateDao.searchNearByCoordinatesByGrid(x,y,false);
        if (CollectionUtils.isEmpty(nearbyCoordinates)){
            nearbyCoordinates = roadCoordinateDao.searchNearByCoordinatesByGrid(x,y,true);
        }
        if (CollectionUtils.isEmpty(nearbyCoordinates)){
            return null;
        }

        Map<String,List<RoadCoordinate>>  roadDividedMap = new HashMap<>();
        for (RoadCoordinate coordinate : nearbyCoordinates) {
            String roadId = coordinate.getRoadId();
            List<RoadCoordinate> coordinates = roadDividedMap.get(roadId);
            if (coordinates==null){
                List<RoadCoordinate> newCoordinates = new ArrayList<>();
                newCoordinates.add(coordinate);
                roadDividedMap.put(roadId,newCoordinates);
            }else {
                coordinates.add(coordinate);
            }
        }
        String roadId = null ;
        for (Map.Entry<String, List<RoadCoordinate>> entry : roadDividedMap.entrySet()) {

            /*
             * This distance(m) decide whether the point is on a road.
             */
            double distance = 12;
            GaoDeRoad road = getRoadById(entry.getKey());
            if (road!=null){
                distance = road.getWidth()/2;
            }
            for (RoadCoordinate coordinate : entry.getValue()) {
                if (CoordinateUtils.distanceBetweenTwoPoints(longitude,latitude,coordinate.getLongitude(),coordinate.getLatitude())-distance>0){
                    roadId = coordinate.getRoadId();
                    break;
                }
            }
        }
        return getRoadById(roadId);
    }

    /**
     * getRoadNamesFromExcelFile
     *
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @see List<Map<String,Object>>
     * @since road-query-engine-cs 0.1.0
     */
    private List<Map<String,Object>> getRoadNamesFromExcelFile(){

        /* Read roads data file info from json */
        List<ExcelFile> excelFiles = CityRoadsInfoReader.getRoadExcelInfo();

        List<Map<String,Object>> roadNamesInfo = new ArrayList<>();
        for (ExcelFile excelFile : excelFiles) {
            Map<String,List<String>> roadNameMap = getRoadNamesInfoFromExcelFile(excelFile);
            if (roadNameMap!=null){
                for (Map.Entry<String,List<String>> entry : roadNameMap.entrySet()) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("cityCode",excelFile.getCityCode());
                    map.put("roadNames",entry.getValue());
                    roadNamesInfo.add(map);
                }
            }
        }
        return roadNamesInfo;
    }

    /**
     * getRoadNamesInfoFromExcelFile
     *
     * @param excelFile     excel file
     * @return java.util.Map<java.lang.String,java.util.List<java.lang.String>>
     * @see Map<String,List<String>>
     * @since road-query-engine-cs 0.1.0
     */
    private static Map<String,List<String>> getRoadNamesInfoFromExcelFile(ExcelFile excelFile){
        if (excelFile==null){
            return null;
        }
        Map<String,List<String>> roadNameMap = new HashMap<>();

        /* Get road names info file(.xls) */
        String homePath = System.getProperty("user.dir");
        String sep = System.getProperty("file.separator");
        String finalExcelFilePath = homePath + sep + "RoadNamesInfoFiles"+ sep+ excelFile.getFilePath();
        File file = new File(finalExcelFilePath);
        if (!file.exists()) {
            String error = "No road names info file found. path:"+file.getAbsolutePath();
            LOG.error(error);
            throw new RoadQueryEngineException(error,RoadQueryEngineException.NOT_FOUND);
        }
        Workbook workbook = null;
        try {
            // get work book by call the implement
            workbook = WorkbookParser.getWorkbook(file);
            for (ExcelSheet excelSheet : excelFile.getSheets()) {
                String sheetName = excelSheet.getName();
                Sheet sheet = workbook.getSheet(sheetName);
                int numRows = sheet.getRows();
                List<String> roadNames = new ArrayList<>();
                for (int i = excelSheet.getStartRow(); i < numRows; i++) {// iterator rows
                    for (int j = excelSheet.getStartCol(); j < excelSheet.getEndCol(); j++) {// iterator columns
                        Cell cell = sheet.getCell(j,i);
                        roadNames.add(cell.getContents());
                    }
                }
                roadNameMap.put(sheetName,roadNames);
            }
        } catch (IOException | BiffException e) {
            String error = "Fail to get work book from file.path:"+file.getAbsolutePath();
            LOG.error(error);
            throw new RoadQueryEngineException(error,e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return roadNameMap;
    }

    /**
     * getRoadFromHttp
     *
     * @param cityCode      city code
     * @param roadName      road name
     * @return java.util.Map<java.lang.String,com.richstonedt.road.query.engine.model.road.GaoDeRoad>
     * @see Map<String,GaoDeRoad>
     * @since road-query-engine-cs 0.1.0
     */
    private static Map<String,GaoDeRoad> getRoadFromHttp(String cityCode,String roadName,int sideLength) throws UnsupportedEncodingException {
        Map<String,GaoDeRoad> result = new HashMap<>();
        String fuzzyRoadName = RoadDataFilter.convertToFuzzyRoadName(roadName);

        /* GaoDe map api */
        String url = PropertyUtils.getMapProperty("gaode.map.url")+"?keywords=" +
                URLEncoder.encode(fuzzyRoadName,"UTF-8")+"&city="+cityCode+
                "&output=json&offset=100&page=1&key=" + PropertyUtils.getMapProperty("gaode.map.key");
        String json = HttpUtils.loadJSON(url);
        if (StringUtils.isNotBlank(json)){
            JSONObject jsonObj = JSONObject.fromObject(json);
            String infoCode = jsonObj.getString("infocode");// info code from result (also can use info)
            if ("10000".equals(infoCode)){
                JSONArray roadsArr = jsonObj.getJSONArray("roads");
                if (CollectionUtils.isEmpty(roadsArr)){
                    return null;
                }else {
                    List<JSONObject> roadJsonList = new ArrayList<>();
                    for (int i = 0; i < roadsArr.size(); i++) {
                        roadJsonList.add(roadsArr.getJSONObject(i));
                    }
                    return RoadDataFilter.convertToGaoDeRoadFromJson(cityCode,roadJsonList,sideLength);
                }
            }
        }

        return result;
    }

    /**
     * removeDuplicateRoadNames
     *
     * @param roadMap   road map
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.GaoDeRoad>
     * @see List<GaoDeRoad>
     * @since road-query-engine-cs 0.1.0
     */
    private List<GaoDeRoad> removeDuplicateRoadNames(Map<String,GaoDeRoad> roadMap){
        List<GaoDeRoad> result = new ArrayList<>();
        List<GaoDeRoad> rawRoads = new ArrayList<>(roadMap.values());
        Map<String,GaoDeRoad> cacheRoadNameMap = new HashMap<>();
        Map<String,Integer> duplicateNameCountMap = new HashMap<>();

        for (GaoDeRoad road : rawRoads) {
            //before this line the prop 'typeId' is actually road type name
            RoadType roadType = roadTypeService.getRoadTypeByName(road.getTypeId());
            if (roadType!=null){
                road.setTypeId(roadType.getId());
            }
            String roadName = road.getName();
            if (cacheRoadNameMap.containsKey(roadName)){
                if (duplicateNameCountMap.containsKey(roadName)){
                    int count = duplicateNameCountMap.get(roadName);
                    count++;
                    road.setName(roadName+""+count);
                    result.add(road);
                    duplicateNameCountMap.put(roadName,count);
                }else {
                    int count = 1;
                    GaoDeRoad originRoad = cacheRoadNameMap.get(roadName);
                    originRoad.setName(roadName+""+count);
                    result.add(originRoad);
                    count++;
                    road.setName(roadName+""+count);
                    result.add(road);
                    duplicateNameCountMap.put(roadName,count);
                }
            }else {
                cacheRoadNameMap.put(roadName,road);
            }
        }
        if (!duplicateNameCountMap.isEmpty()){
            for (String key : duplicateNameCountMap.keySet()) {
                cacheRoadNameMap.remove(key);
            }
        }
        result.addAll(cacheRoadNameMap.values());
        return result;
    }

}
