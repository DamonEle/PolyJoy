package com.damon43.polyjoy.ui.main.model;

import com.damon43.common.base.BaseModel;
import com.damon43.common.baserx.RxSchedulers;
import com.damon43.common.commonutils.CustomCache;
import com.damon43.common.commonutils.LogUtils;
import com.damon43.polyjoy.app.TheApplication;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.JoyNewsType;
import com.damon43.polyjoy.db.NewsChannelManager;
import com.damon43.polyjoy.ui.main.contract.JoyMainContract;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author damonmasty
 *         Created on 上午10:55 17-2-6.
 */

public class JoyMainModel implements JoyMainContract.Model {

    /*类型: top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yul
    e(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)*/

    /**
     * 加载本地新闻频道集合
     *
     * @return
     */

    public static final String TAG = "时娱主页面";

    @Override
    public void onFailed(String error) {
        LogUtils.logD(TAG, error);
    }

    @Override
    public Observable<List<JoyNewsType>> loadCustomNewsChannel() {
        return Observable.create(new Observable.OnSubscribe<List<JoyNewsType>>() {
            @Override
            public void call(Subscriber<? super List<JoyNewsType>> subscriber) {
                ArrayList<JoyNewsType> types = (ArrayList<JoyNewsType>) CustomCache.get(TheApplication.getAppContext())
                        .getAsObject(TheConstants.CACHE_CHANNEL_TYPE);
                if (types == null) {
                    types = (ArrayList<JoyNewsType>) NewsChannelManager.loadCustomNewsChannel();
                    CustomCache.get(TheApplication.getAppContext()).put(TheConstants.CACHE_CHANNEL_TYPE, types);
                }
                subscriber.onNext(types);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<JoyNewsType>>io_main());
    }
}
