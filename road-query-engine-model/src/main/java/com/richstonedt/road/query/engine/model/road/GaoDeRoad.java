/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.model.road;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * <b><code>GaoDeRoad</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 11:44
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-model 0.1.0
 */
public class GaoDeRoad extends Road {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 7684311652344100352L;

    /**
     * The City code.
     */
    @ApiModelProperty(value = "道路城市代码")
    private String cityCode;

    /**
     * The Type id.
     */
    @ApiModelProperty(value = "道路类型Id")
    private String typeId;

    /**
     * The Center lng.
     */
    @ApiModelProperty(value = "中心经度")
    private double centerLng;

    /**
     * The Center lat.
     */
    @ApiModelProperty(value = "中心经度")
    private double centerLat;

    /**
     * The Point count.
     */
    @ApiModelProperty(value = "点数量")
    private int pointCount;

    /**
     * The Coordinates.
     */
    @ApiModelProperty(value = "坐标集合")
    List<RoadCoordinate> coordinates = new ArrayList<>();

    /**
     * Return the CityCode
     *
     * @return property value of cityCode
     * @since road -query-engine-model 0.1.0
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Set the CityCode
     *
     * @param cityCode value to be assigned to property cityCode
     * @since road -query-engine-model 0.1.0
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Return the TypeId
     *
     * @return property value of typeId
     * @since road -query-engine-model 0.1.0
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * Set the TypeId
     *
     * @param typeId value to be assigned to property typeId
     * @since road -query-engine-model 0.1.0
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * Return the Centerlng
     *
     * @return property value of centerLng
     * @since road -query-engine-model 0.1.0
     */
    public double getCenterLng() {
        return centerLng;
    }

    /**
     * Set the Centerlng
     *
     * @param centerLng value to be assigned to property centerLng
     * @since road -query-engine-model 0.1.0
     */
    public void setCenterLng(double centerLng) {
        this.centerLng = centerLng;
    }

    /**
     * Return the CenterLat
     *
     * @return property value of centerLat
     * @since road -query-engine-model 0.1.0
     */
    public double getCenterLat() {
        return centerLat;
    }

    /**
     * Set the CenterLat
     *
     * @param centerLat value to be assigned to property centerLat
     * @since road -query-engine-model 0.1.0
     */
    public void setCenterLat(double centerLat) {
        this.centerLat = centerLat;
    }

    /**
     * Return the PointCount
     *
     * @return property value of pointCount
     * @since road -query-engine-model 0.1.0
     */
    public int getPointCount() {
        return pointCount;
    }

    /**
     * Set the PointCount
     *
     * @param pointCount value to be assigned to property pointCount
     * @since road -query-engine-model 0.1.0
     */
    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

    /**
     * Return the Coordintates
     *
     * @return property value of coordinates
     * @since road -query-engine-model 0.1.0
     */
    public List<RoadCoordinate> getCoordinates() {
        return coordinates;
    }

    /**
     * Set the Coordinates
     *
     * @param coordinates value to be assigned to property coordinates
     * @since road -query-engine-model 0.1.0
     */
    public void setCoordinates(List<RoadCoordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
