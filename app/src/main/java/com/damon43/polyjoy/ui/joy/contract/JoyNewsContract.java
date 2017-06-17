package com.damon43.polyjoy.ui.joy.contract;

import com.damon43.common.base.BaseModel;
import com.damon43.common.base.BasePresenter;
import com.damon43.common.base.BaseView;
import com.damon43.polyjoy.bean.JoyNewsBean;

import java.util.List;

import rx.Observable;

/**
 * @author damonmasty
 *         Created on 下午3:34 17-2-7.
 *         tabs新闻 契约类
 */

public interface JoyNewsContract {
     interface Model extends BaseModel {
        /**
         * 通过类型获取时势界面数据
         *
         * @param type
         * @param appKey
         */
        Observable<List<JoyNewsBean.DataBean>> loadJoyNewsByType(String type, String appKey);
    }

    interface View extends BaseView {
        void showJoyNews(List<JoyNewsBean.DataBean> datas);
    }
    abstract class Presenter extends BasePresenter<Model,View>{
        public abstract void loadJoyNewsByType(String type, String appKey);
    }
}
