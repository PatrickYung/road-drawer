/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.model.road;

import java.io.Serializable;

/**
 * <b><code>RoadCoordinate</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 11:48
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-model 0.1.0
 */
public class RoadCoordinate implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 2658528084501053313L;

    /**
     * The Id.
     */
    private String id;

    /**
     * The Longitude.
     */
    private double longitude;

    /**
     * The Latitude.
     */
    private double latitude;

    /**
     * The Road id.
     */
    private String roadId;

    /**
     * The X.
     */
    private int x;

    /**
     * The Y.
     */
    private int y;

    /**
     * The Geom.
     */
    private String geom;

    /**
     * Return the Id
     *
     * @return property value of id
     * @since road -query-engine-model 0.1.0
     */
    public String getId() {
        return id;
    }

    /**
     * Set the Id
     *
     * @param id value to be assigned to property id
     * @since road -query-engine-model 0.1.0
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return the Longitude
     *
     * @return property value of longitude
     * @since road -query-engine-model 0.1.0
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the Longitude
     *
     * @param longitude value to be assigned to property longitude
     * @since road -query-engine-model 0.1.0
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Return the Latitude
     *
     * @return property value of latitude
     * @since road -query-engine-model 0.1.0
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the Latitude
     *
     * @param latitude value to be assigned to property latitude
     * @since road -query-engine-model 0.1.0
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Return the RoadId
     *
     * @return property value of roadId
     * @since road -query-engine-model 0.1.0
     */
    public String getRoadId() {
        return roadId;
    }

    /**
     * Set the RoadId
     *
     * @param roadId value to be assigned to property roadId
     * @since road -query-engine-model 0.1.0
     */
    public void setRoadId(String roadId) {
        this.roadId = roadId;
    }

    /**
     * Return the X
     *
     * @return property value of x
     * @since road -query-engine-model 0.1.0
     */
    public int getX() {
        return x;
    }

    /**
     * Set the X
     *
     * @param x value to be assigned to property x
     * @since road -query-engine-model 0.1.0
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Return the Y
     *
     * @return property value of y
     * @since road -query-engine-model 0.1.0
     */
    public int getY() {
        return y;
    }

    /**
     * Set the Y
     *
     * @param y value to be assigned to property y
     * @since road -query-engine-model 0.1.0
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Return the Geom
     *
     * @return property value of geom
     * @since road -query-engine-model 0.1.0
     */
    public String getGeom() {
        return geom;
    }

    /**
     * Set the Geom
     *
     * @param geom value to be assigned to property geom
     * @since road -query-engine-model 0.1.0
     */
    public void setGeom(String geom) {
        this.geom = geom;
    }
}
