package com.damon43.polyjoy.ui.my;

import com.damon43.common.base.BaseModel;
import com.damon43.common.base.BasePresenter;
import com.damon43.common.base.BaseView;
import com.damon43.polyjoy.bean.NewsRecordBean;
import com.damon43.polyjoy.bean.RecordableBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/4/22.
 * 1.有一个贝塞尔曲线
 * 2.有一个坐标系，记录看过的一切，包括美女的次数，横坐标为日期，纵坐标为次数 (类别为全部时，显示柱状图，类别为单一时，采用折线图)
 * 3.可以按选择的类目更新坐标图
 * 4.
 */

public interface MyContract {
    interface Model extends BaseModel {
        Observable<List<NewsRecordBean>> loadLocalRocordableBeans();

        Observable<List<RecordableBean>> loadLocalRocordableBeansByType(String type);
    }

    interface View extends BaseView {
        void showLocalRocordableBeans(List<NewsRecordBean> beans);

        void loadLocalRocordableBeansByType(List<RecordableBean> beans);
    }

     abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void showLocalRocordableBeans();

        public abstract void loadLocalRocordableBeansByType(String type);
    }
}
