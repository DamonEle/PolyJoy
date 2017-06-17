package com.damon43.polyjoy.ui.joy.presenter;

import com.damon43.polyjoy.bean.JoyNewsBean;
import com.damon43.polyjoy.ui.joy.contract.JoyNewsContract;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author damonmasty
 *         Created on 下午4:13 17-2-7.
 */

public class JoyNewsPresenter extends JoyNewsContract.Presenter {
    @Override
    public void loadJoyNewsByType(String type, String appKey) {
        mView.onLoading();
        mModel.loadJoyNewsByType(type, appKey).subscribe(new Subscriber<List<JoyNewsBean.DataBean>>() {
            @Override
            public void onCompleted() {
                mView.onLoaded();
            }

            @Override
            public void onError(Throwable e) {
                mView.onLoadFailed(e,e.toString());
            }

            @Override
            public void onNext(List<JoyNewsBean.DataBean> datas) {
                mView.showJoyNews(datas);
            }
        });
    }
}
