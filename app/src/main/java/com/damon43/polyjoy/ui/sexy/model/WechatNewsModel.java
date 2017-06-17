package com.damon43.polyjoy.ui.sexy.model;

import com.damon43.common.baserx.RxSchedulers;
import com.damon43.polyjoy.api.HttpResultFunc1;
import com.damon43.polyjoy.api.Net;
import com.damon43.polyjoy.api.NetConstants;
import com.damon43.polyjoy.bean.NetResult;
import com.damon43.polyjoy.bean.WechatNewsBean;
import com.damon43.polyjoy.ui.sexy.contract.WechatNewsContract;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/4/6.
 */

public class WechatNewsModel implements WechatNewsContract.Model {
    private List<String> filter;

    @Override
    public void onFailed(String error) {

    }

    @Override
    public Observable<List<WechatNewsBean.ListEntity>> loadWechatNews(int position) {
        return Net.getDefult(NetConstants.HOST_JUHE).loadWechatNewsByPage(position)
                .map(new HttpResultFunc1<WechatNewsBean>())
                .flatMap(new Func1<WechatNewsBean, Observable<WechatNewsBean.ListEntity>>() {
                    @Override
                    public Observable<WechatNewsBean.ListEntity> call(WechatNewsBean wechatNewsBean) {
                        return Observable.from(wechatNewsBean.getList());
                    }
                })
                .filter(new Func1<WechatNewsBean.ListEntity, Boolean>() {
                    @Override
                    public Boolean call(WechatNewsBean.ListEntity listEntity) {
                        String newId = listEntity.getId();
                        if (filter == null) {
                            filter = new ArrayList<String>();
                            filter.add(newId);
                            return true;
                        } else {
                            if (filter.contains(newId)) {
                                return false;
                            } else {
                                filter.remove(filter.size() - 1);
                                filter.add(newId);
                                return true;
                            }
                        }
                    }
                })
                .toList()
                .compose(RxSchedulers.<List<WechatNewsBean.ListEntity>>io_main());
    }
}
