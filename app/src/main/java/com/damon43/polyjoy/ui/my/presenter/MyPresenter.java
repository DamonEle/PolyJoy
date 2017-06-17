package com.damon43.polyjoy.ui.my.presenter;

import com.damon43.polyjoy.bean.NewsRecordBean;
import com.damon43.polyjoy.bean.RecordableBean;
import com.damon43.polyjoy.ui.my.MyContract;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/5/10.
 */

public class MyPresenter extends MyContract.Presenter {
    @Override
    public void showLocalRocordableBeans() {
        mModel.loadLocalRocordableBeans().subscribe(new Action1<List<NewsRecordBean>>() {
            @Override
            public void call(List<NewsRecordBean> recordableBeen) {
                mView.showLocalRocordableBeans(recordableBeen);
            }
        });
    }

    @Override
    public void loadLocalRocordableBeansByType(String type) {
        mModel.loadLocalRocordableBeansByType(type).subscribe(new Action1<List<RecordableBean>>() {
            @Override
            public void call(List<RecordableBean> recordableBeen) {
                mView.loadLocalRocordableBeansByType(recordableBeen);
            }
        });
    }

    public void setAnything(MyContract.Model model, MyContract.View view) {
        this.mModel = model;
        this.mView = view;
    }
}
