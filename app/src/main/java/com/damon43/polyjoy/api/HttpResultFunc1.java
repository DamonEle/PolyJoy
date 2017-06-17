package com.damon43.polyjoy.api;

import com.damon43.polyjoy.bean.NetResult;

import rx.functions.Func1;

/**
 * @author damonmasty
 *         Created on 下午4:09 17-2-7.
 *           用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */

public class HttpResultFunc1<T> implements Func1<NetResult<T>, T> {
    @Override
    public T call(NetResult<T> tNetResult) {
        if (tNetResult.getErrorCode() != 0) {
            throw new NetException(tNetResult.getErrorCode());
        }
        return tNetResult.getResult();
    }
}
