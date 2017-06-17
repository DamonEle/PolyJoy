package com.damon43.polyjoy.api;

import android.util.SparseArray;

import com.damon43.common.baseapp.BaseApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author damonmasty
 *         Created on 下午12:09 17-1-29.
 *         网络访问类
 */

public class Net {

    public static final int TIME_OUT = 5;
    private static final long READ_TIME_OUT = 5000;
    private static final long CONNECT_TIME_OUT = 5000;
    private Retrofit net;
    private NetService netService;
    private static SparseArray<Net> netManager = new SparseArray<>(NetConstants.HOST_COUNT);

    //创建retrofit实例
    private Net(int hostType) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(mRewriteCacheControlInterceptor)
//                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
//                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        net = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(NetConstants.getHostType(hostType))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        netService = net.create(NetService.class);
    }

    public static NetService getDefult(int hostType) {
        Net defultNet = netManager.get(hostType);
        if (defultNet == null) {
            defultNet = new Net(hostType);
            netManager.put(hostType, defultNet);
        }
        return defultNet.netService;
    }
}
