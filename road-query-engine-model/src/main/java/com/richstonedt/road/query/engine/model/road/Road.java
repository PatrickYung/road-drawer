/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.model.road;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b><code>Road</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 11:42
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-model 0.1.0
 */
public class Road implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 7235772659963758566L;

    /**
     * The Id.
     */
    @ApiModelProperty(value = "道路id")
    private String id;

    /**
     * The Name.
     */
    @ApiModelProperty(value = "道路名称")
    private String name;

    /**
     * The Width.
     */
    @ApiModelProperty(value = "道路宽度")
    private double width;

    /**
     * Return the Id
     *
     * @return property value of id
     * @since road-query-engine-model 0.1.0
     */
    public String getId() {
        return id;
    }

    /**
     * Set the Id
     *
     * @param id value to be assigned to property id
     * @since road-query-engine-model 0.1.0
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return the Name
     *
     * @return property value of name
     * @since road-query-engine-model 0.1.0
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Name
     *
     * @param name value to be assigned to property name
     * @since road-query-engine-model 0.1.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Width
     *
     * @return property value of width
     * @since road-query-engine-model 0.1.0
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the Width
     *
     * @param width value to be assigned to property width
     * @since road-query-engine-model 0.1.0
     */
    public void setWidth(double width) {
        this.width = width;
    }
}
