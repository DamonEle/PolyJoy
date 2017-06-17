package com.damon43.polyjoy.ui.my.model;

import com.damon43.common.baserx.RxSchedulers;
import com.damon43.polyjoy.app.TheApplication;
import com.damon43.polyjoy.bean.NewsRecordBean;
import com.damon43.polyjoy.bean.RecordableBean;
import com.damon43.polyjoy.db.RecordableBeanDao;
import com.damon43.polyjoy.ui.my.MyContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/23.
 */

public class MyModel implements MyContract.Model {
    @Override
    public void onFailed(String error) {

    }

    @Override
    public Observable<List<NewsRecordBean>> loadLocalRocordableBeans() {
        final RecordableBeanDao dao = TheApplication.getDBSession().getRecordableBeanDao();
        final Map<String, Integer> datas = new HashMap<>();
        /*
        1.取到全部的记录
        2.按类型集合
        3.发送整个相同类型的list
         */
        return Observable.from(dao.loadAll())
                .toMap(new Func1<RecordableBean, String>() {
                    @Override
                    public String call(RecordableBean recordableBean) {
                        return recordableBean.getType();
                    }
                }, new Func1<RecordableBean, Integer>() {
                    @Override
                    public Integer call(RecordableBean recordableBean) {
                        String type = recordableBean.getType();
                        Integer count = datas.get(type);
                        if (count == null) {
                            count = 1;
                            datas.put(type, count);
                            return count;
                        }
                        datas.put(type, ++count);
                        return count;
                    }
                })
                .flatMap(new Func1<Map<String, Integer>, Observable<Map.Entry<String, Integer>>>() {
                    @Override
                    public Observable<Map.Entry<String, Integer>> call(Map<String, Integer> stringIntegerMap) {
                        return Observable.from(stringIntegerMap.entrySet());
                    }
                })
                .map(new Func1<Map.Entry<String, Integer>, NewsRecordBean>() {
                    @Override
                    public NewsRecordBean call(Map.Entry<String, Integer> stringIntegerEntry) {
                        return new NewsRecordBean(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
                    }
                })
                .toList()
                .compose(RxSchedulers.<List<NewsRecordBean>>io_main());
    }

    @Override
    public Observable<List<RecordableBean>> loadLocalRocordableBeansByType(String type) {
        final RecordableBeanDao dao = TheApplication.getDBSession().getRecordableBeanDao();
        return Observable.just(dao.queryBuilder()
                .where(RecordableBeanDao.Properties.Type.eq(type))
                .orderAsc(RecordableBeanDao.Properties.RecoedDate)
                .list())
                .compose(RxSchedulers.<List<RecordableBean>>io_main());
    }
}
