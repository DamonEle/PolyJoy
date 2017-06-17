package com.damon43.polyjoy.ui.sexy.model;

import com.damon43.common.baserx.RxSchedulers;
import com.damon43.polyjoy.api.Net;
import com.damon43.polyjoy.api.NetConstants;
import com.damon43.polyjoy.bean.GirlBean;
import com.damon43.polyjoy.ui.sexy.contract.GirlsContract;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/4/11.
 */

public class GirlsModel implements GirlsContract.Model {
    @Override
    public void onFailed(String error) {

    }

    @Override
    public Observable<List<GirlBean.ResultsEntity>> loadGirlsImages(int page) {
        return Net.getDefult(NetConstants.HOST_GANKIO).loadGirlsImages(page)
                .map(new Func1<GirlBean, List<GirlBean.ResultsEntity>>() {
                    @Override
                    public List<GirlBean.ResultsEntity> call(GirlBean girlBean) {
                        return girlBean.getResults();
                    }
                })
                .compose(RxSchedulers.<List<GirlBean.ResultsEntity>>io_main());
    }
}
