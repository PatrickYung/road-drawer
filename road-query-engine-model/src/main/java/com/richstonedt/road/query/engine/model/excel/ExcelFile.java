/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.model.excel;

import java.io.Serializable;
import java.util.List;

/**
 * <b><code>ExcelFile</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 16:50
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-model 0.1.0
 */
public class ExcelFile implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -1110001805042642663L;

    /**
     * The File path.
     */
    private String filePath;

    /**
     * The City code.
     */
    private String cityCode;

    /**
     * The Sheets.
     */
    private List<ExcelSheet> sheets;

    /**
     * Return the FilePath
     *
     * @return property value of filePath
     * @since road -query-engine-model 0.1.0
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Set the FilePath
     *
     * @param filePath value to be assigned to property filePath
     * @since road -query-engine-model 0.1.0
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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
     * Return the Sheets
     *
     * @return property value of sheets
     * @since road -query-engine-model 0.1.0
     */
    public List<ExcelSheet> getSheets() {
        return sheets;
    }

    /**
     * Set the Sheets
     *
     * @param sheets value to be assigned to property sheets
     * @since road -query-engine-model 0.1.0
     */
    public void setSheets(List<ExcelSheet> sheets) {
        this.sheets = sheets;
    }
}
