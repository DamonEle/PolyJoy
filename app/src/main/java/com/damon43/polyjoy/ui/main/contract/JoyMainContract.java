package com.damon43.polyjoy.ui.main.contract;

import com.damon43.common.base.BaseModel;
import com.damon43.common.base.BasePresenter;
import com.damon43.common.base.BaseView;
import com.damon43.polyjoy.bean.JoyNewsType;

import java.util.List;

import rx.Observable;

/**
 * @author damonmasty
 *         Created on 下午2:52 17-2-6.
 *         时娱契约类
 */

public interface JoyMainContract {
    interface Model extends BaseModel {
        Observable<List<JoyNewsType>> loadCustomNewsChannel();
    }

    interface View extends BaseView {
        void showCustomNewsChannel(List<JoyNewsType> types);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void loadCustomNewsChannel();
    }
}
