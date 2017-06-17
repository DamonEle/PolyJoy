package com.damon43.polyjoy.ui.sexy.contract;

import com.damon43.common.base.BaseModel;
import com.damon43.common.base.BasePresenter;
import com.damon43.common.base.BaseView;
import com.damon43.polyjoy.bean.GirlBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/11.
 */

public interface GirlsContract {
    interface Model extends BaseModel {
        Observable<List<GirlBean.ResultsEntity>> loadGirlsImages(int page);
    }

    interface View extends BaseView {
        void showGirlsImages(List<GirlBean.ResultsEntity> images);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract public void loadGirlsImages(int page);
    }
}
