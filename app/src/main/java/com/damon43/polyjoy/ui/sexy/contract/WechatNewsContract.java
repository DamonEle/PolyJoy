package com.damon43.polyjoy.ui.sexy.contract;

import com.damon43.common.base.BaseModel;
import com.damon43.common.base.BasePresenter;
import com.damon43.common.base.BaseView;
import com.damon43.polyjoy.bean.NetResult;
import com.damon43.polyjoy.bean.WechatNewsBean;

import java.util.List;

import rx.Observable;

/**
 * Created by damon on 2017/4/5.
 * 微信新闻契约类
 */

public interface WechatNewsContract {
    interface Model extends BaseModel {
        Observable<List<WechatNewsBean.ListEntity>> loadWechatNews(int position);
    }

    interface View extends BaseView {
        void showWechatNews(List<WechatNewsBean.ListEntity> wechatNews);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void loadWechatNews(int position);
    }
}
