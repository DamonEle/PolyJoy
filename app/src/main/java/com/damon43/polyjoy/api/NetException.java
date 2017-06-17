package com.damon43.polyjoy.api;

/**
 * @author damonmasty
 *         Created on 下午3:35 17-1-29.
 *         网络错误类
 */

public class NetException extends RuntimeException{
    private int errorCode;

    public NetException(int errorCode) {
        this.errorCode = errorCode;
    }

}
