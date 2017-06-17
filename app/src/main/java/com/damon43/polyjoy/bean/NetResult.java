package com.damon43.polyjoy.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author damonmasty
 *         Created on 下午3:20 17-1-29.
 */

public class NetResult<T> {

    /**
     * reason : 成功的返回
     * result : {}
     * error_code : 0
     */

    private String reason;
    @SerializedName("result")
    private T result;

    @SerializedName("error_code")
    private int errorCode;

    public T getResult() {
        return result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
