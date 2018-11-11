/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.model.excel;

import java.io.Serializable;

/**
 * <b><code>ExcelSheet</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 16:51
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road -query-engine-model 0.1.0
 */
public class ExcelSheet implements Serializable {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -3588603554939980405L;

    /**
     * The Name.
     */
    private String name;

    /**
     * The Start row.
     */
    private int startRow;

    /**
     * The End row.
     */
    private int endRow;

    /**
     * The Start col.
     */
    private int startCol;

    /**
     * The End col.
     */
    private int endCol;

    /**
     * Return the Name
     *
     * @return property value of name
     * @since road -query-engine-model 0.1.0
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Name
     *
     * @param name value to be assigned to property name
     * @since road -query-engine-model 0.1.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the StartRow
     *
     * @return property value of startRow
     * @since road -query-engine-model 0.1.0
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * Set the StartRow
     *
     * @param startRow value to be assigned to property startRow
     * @since road -query-engine-model 0.1.0
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * Return the EndRow
     *
     * @return property value of endRow
     * @since road -query-engine-model 0.1.0
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * Set the EndRow
     *
     * @param endRow value to be assigned to property endRow
     * @since road -query-engine-model 0.1.0
     */
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    /**
     * Return the StartCol
     *
     * @return property value of startCol
     * @since road -query-engine-model 0.1.0
     */
    public int getStartCol() {
        return startCol;
    }

    /**
     * Set the StartCol
     *
     * @param startCol value to be assigned to property startCol
     * @since road -query-engine-model 0.1.0
     */
    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    /**
     * Return the EndCol
     *
     * @return property value of endCol
     * @since road -query-engine-model 0.1.0
     */
    public int getEndCol() {
        return endCol;
    }

    /**
     * Set the EndCol
     *
     * @param endCol value to be assigned to property endCol
     * @since road -query-engine-model 0.1.0
     */
    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }
}
