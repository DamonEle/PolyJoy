package com.damon43.polyjoy.api;

import com.damon43.polyjoy.bean.GirlBean;
import com.damon43.polyjoy.bean.JoyNewsBean;
import com.damon43.polyjoy.bean.NetResult;
import com.damon43.polyjoy.bean.WechatNewsBean;

import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author damonmasty
 *         Created on 下午12:07 17-1-29.
 *         用作retrofit的接口类
 */

public interface NetService {
    //新闻头条
    @GET("/toutiao/index?")
    Observable<NetResult<JoyNewsBean>> loadJoyNewsByType(@Query("type") String type, @Query("key") String appKey);

    //微信精选
    @GET("/weixin/query?ps=&dtype=&key=" + NetConstants.JUHE_DATA_WECHAT_APP_KEY)
    Observable<NetResult<WechatNewsBean>> loadWechatNewsByPage(@Query("pno") int page);

    //妹子图
    @GET("福利/10/{page}")
    Observable<GirlBean> loadGirlsImages(@Path("page") int page);
}
