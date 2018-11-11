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
 * <b><code>RoadType</code></b>
 * <p>
 * road type
 * </p>
 * <b>Create Time:</b> 2017/1/22 16:45
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-model 0.1.0
 */
public class RoadType implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1760453693268098955L;

    /**
     * The Id.
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * The Name.
     */
    @ApiModelProperty(value = "道路类型名称")
    private String name;
    /**
     * The Code.
     */
    @ApiModelProperty(value = "道路类型编码")
    private String code;

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
     * Return the Code
     *
     * @return property value of code
     * @since road-query-engine-model 0.1.0
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the Code
     *
     * @param code value to be assigned to property code
     * @since road-query-engine-model 0.1.0
     */
    public void setCode(String code) {
        this.code = code;
    }
}
