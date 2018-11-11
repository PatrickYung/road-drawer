/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.common;

import com.richstonedt.road.query.engine.model.road.GaoDeRoad;
import com.richstonedt.road.query.engine.model.road.RoadCoordinate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b><code>RoadDataFilter</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/24 11:55
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public class RoadDataFilter {
    /**
     * convertToFuzzyRoadName
     *
     * @param roadName  road name
     * @return java.lang.String
     * @see String
     * @since road-query-engine-cs 0.1.0
     */
    public static String convertToFuzzyRoadName(String roadName){
        if(roadName.contains("高速公路")) {
            return "高速公路";
        }else if(roadName.contains("路")){
            return roadName.substring(0,roadName.indexOf('路'));
        }else if(roadName.contains("街")){
            return roadName.substring(0,roadName.indexOf('街'));
        }else if(roadName.contains("巷")){
            return roadName.substring(0,roadName.indexOf('巷'));
        }else if(roadName.contains("S")){
            return roadName.substring(roadName.indexOf('S')+1);
        }else if(roadName.contains("G")){
            return roadName.substring(roadName.indexOf('G')+1);
        }else{
            return roadName;
        }
    }

    /**
     * covertToRoadTypeName
     *
     * @param typeName    type name
     * @return java.lang.String
     * @see String
     * @since road-query-engine-cs 0.1.0
     */
    public static String covertToRoadTypeName(String typeName){
        if(typeName.contains("城市主干道")||typeName.contains("主要道路")) {
            return "主要道路（城市主干道）";
        }else if(typeName.contains("城市次干道")||typeName.contains("次要道路")){
            return "次要道路（城市次干道）";
        }else if(typeName.contains("省")){
            return "省道";
        }else if(typeName.contains("国")){
            return "国道";
        }else if(typeName.contains("县")){
            return "县道";
        }else if(typeName.contains("环")||typeName.contains("快")){
            return "城市环路/城市快速路";
        }else if(typeName.contains("乡村")||typeName.contains("乡")||typeName.contains("村")){
            return "乡村道路";
        }else if(typeName.contains("区县")||typeName.contains("内部")||typeName.contains("区县内部")){
            return "区县内部道路";
        }else if(typeName.contains("一般")||typeName.contains("般")||typeName.contains("一")){
            return "一般道路";
        }else if(typeName.contains("非导航")||typeName.contains("非")||typeName.contains("导航")){
            return "非导航道路";
        }else if(typeName.contains("高速")||typeName.contains("高")){
            return "高速公路";
        }else{
            return typeName;
        }
    }

    /**
     * convertToGaoDeRoadFromJson
     *
     * @param cityCode      city code
     * @param jsonObjects   json objects list
     * @return java.util.Map<java.lang.String,com.richstonedt.road.query.engine.model.road.GaoDeRoad>
     * @see Map<String,GaoDeRoad>
     * @since road-query-engine-cs 0.1.0
     */
    public static Map<String,GaoDeRoad> convertToGaoDeRoadFromJson(String cityCode, List<JSONObject> jsonObjects,int sideLength){
        if (jsonObjects.isEmpty()){
            return null;
        }
        Map<String,GaoDeRoad> result = new HashMap<>();
        //convert to model GaoDeRoad
        for (JSONObject jsonObject : jsonObjects) {
            String id = jsonObject.getString("id");
            String roadName = jsonObject.getString("name");
            String typeName =  covertToRoadTypeName(jsonObject.getString("type"));
            double width = jsonObject.getDouble("width");
            String[] centerCoorArray = jsonObject.getString("center").split(",");
            JSONArray polyLines = jsonObject.getJSONArray("polylines");
            List<RoadCoordinate> roadCoordinates = convertPolyLinesToCoordinates(polyLines,sideLength);
            if (roadCoordinates!=null){
                GaoDeRoad road = new GaoDeRoad();
                road.setName(roadName);
                road.setWidth(width);
                road.setCenterLng(Double.parseDouble(centerCoorArray[0]));
                road.setCenterLat(Double.parseDouble(centerCoorArray[1]));
                road.setTypeId(typeName);
                road.setPointCount(roadCoordinates.size());
                road.setCoordinates(roadCoordinates);
                road.setCityCode(cityCode);
                result.put(id,road);
            }

        }
        return result;
    }

    /**
     * convertPolyLinesToCoordinates
     *
     * @param polyLines     poly lines
     * @return java.util.List<com.richstonedt.road.query.engine.model.road.RoadCoordinate>
     * @see List<RoadCoordinate>
     * @since road-query-engine-cs 0.1.0
     */
    private static List<RoadCoordinate> convertPolyLinesToCoordinates(JSONArray polyLines,int sideLength){
        if (CollectionUtils.isEmpty(polyLines)){
            return null;
        }
        List<RoadCoordinate> result = new ArrayList<>();
        for (int i = 0; i < polyLines.size(); i++) {
            String points = polyLines.getString(i);
            String[] pointArray = points.split(";");
            for (String point : pointArray) {
                String[] coor = point.split(",");
                //do convert coordinate lng and lat to BaiDu map coordinate system
                Map<String,Double> convertedCoorMap = CoordinateUtils.convertGCJ02ToBD09(Double.parseDouble(coor[0]),Double.parseDouble(coor[1]));
                double lng = convertedCoorMap.get("longitude");
                double lat = convertedCoorMap.get("latitude");
                RoadCoordinate coordinate = new RoadCoordinate();
                coordinate.setLongitude(lng);
                coordinate.setLatitude(lat);
                //calculate grid coordinates
                Map<String,Integer> gridCoorMap = CoordinateUtils.calculateGridCoordinate(lng,lat,sideLength);
                if (gridCoorMap!=null){
                    coordinate.setX(gridCoorMap.get("x"));
                    coordinate.setY(gridCoorMap.get("y"));
                }
                result.add(coordinate);
            }
        }
        return result;
    }
}
