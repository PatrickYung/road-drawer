/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.model.common;

/**
 * <b><code>RoadQueryEngineException</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/22 16:57
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-model 0.1.0
 */
public class RoadQueryEngineException extends RuntimeException {

    private static final long serialVersionUID = 1182266342163123255L;

    /**
     * The Constant CONFLICT.
     */
    public static final String CONFLICT = "Conflict";

    /**
     * The Constant NOT_FOUND.
     */
    public static final String NOT_FOUND = "NotFound";

    /**
     * error code
     *
     * @since road-query-engine-model 0.1.0
     */

    private String errorCode;

    /**
     * Returns the errorCode
     *
     * @return the errorCode
     * @since road-query-engine-model 0.1.0
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the errorCode
     *
     * @param errorCode the errorCode to set
     * @since road-query-engine-model 0.1.0
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Instantiates a new  service exception.
     */
    public RoadQueryEngineException() {
    }

    /**
     * Instantiates a new  service exception.
     *
     * @param message the message
     */
    public RoadQueryEngineException(String message) {
        super(message);
    }

    /**
     * Instantiates a new  service exception.
     *
     * @param message   the message
     * @param errorCode the error code
     */
    public RoadQueryEngineException(String message, String errorCode) {
        super(message);
        this.setErrorCode(errorCode);
    }

    /**
     * Instantiates a new  service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public RoadQueryEngineException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new  service exception.
     *
     * @param message   the message
     * @param cause     the cause
     * @param errorCode the error code
     */
    public RoadQueryEngineException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.setErrorCode(errorCode);
    }

    /**
     * Instantiates a new  service exception.
     *
     * @param cause the cause
     */
    public RoadQueryEngineException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new  service exception.
     *
     * @param cause     the cause
     * @param errorCode the error code
     */
    public RoadQueryEngineException(Throwable cause, String errorCode) {
        super(cause);
        this.setErrorCode(errorCode);
    }
}
