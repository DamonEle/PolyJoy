package com.damon43.polyjoy.ui.sexy.presenter;

import com.damon43.polyjoy.bean.GirlBean;
import com.damon43.polyjoy.ui.sexy.contract.GirlsContract;

import java.util.List;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/12.
 */

public class GirlsPresenter extends GirlsContract.Presenter {
    @Override
    public void loadGirlsImages(int page) {
        mView.onLoading();
        mModel.loadGirlsImages(page)
                .subscribe(new Subscriber<List<GirlBean.ResultsEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.onLoaded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onLoadFailed(e,e.toString());
                    }

                    @Override
                    public void onNext(List<GirlBean.ResultsEntity> resultsEntities) {
                        mView.showGirlsImages(resultsEntities);
                    }
                });
    }
}
