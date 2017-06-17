package com.damon43.polyjoy.ui.sexy.presenter;

import com.damon43.polyjoy.bean.WechatNewsBean;
import com.damon43.polyjoy.ui.sexy.contract.WechatNewsContract;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/7.
 */

public class WechatNewsPresener extends WechatNewsContract.Presenter {
    @Override
    public void loadWechatNews(int position) {
        mModel.loadWechatNews(position)
                .subscribe(new Subscriber<List<WechatNewsBean.ListEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.onLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onLoadFailed(e,e.toString());
                    }

                    @Override
                    public void onNext(List<WechatNewsBean.ListEntity> listEntities) {
                        mView.showWechatNews(listEntities);
                    }
                });
    }
}
