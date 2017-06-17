package com.damon43.polyjoy.ui.joy.model;

import com.damon43.common.baserx.RxSchedulers;
import com.damon43.common.commonutils.CustomCache;
import com.damon43.common.commonutils.LogUtils;
import com.damon43.polyjoy.api.HttpResultFunc1;
import com.damon43.polyjoy.api.Net;
import com.damon43.polyjoy.api.NetConstants;
import com.damon43.polyjoy.app.TheApplication;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.JoyNewsBean;
import com.damon43.polyjoy.ui.joy.contract.JoyNewsContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * @author damonmasty
 *         Created on 下午3:34 17-2-7.
 */

public class JoyNewsModel implements JoyNewsContract.Model {
    //当前时间标记
    private String nowDate;

    @Override
    public Observable<List<JoyNewsBean.DataBean>> loadJoyNewsByType(final String type, String appKey) {
        return Net.getDefult(NetConstants.HOST_JUHE).loadJoyNewsByType(type, appKey)
                .map(new HttpResultFunc1<JoyNewsBean>())
                .flatMap(new Func1<JoyNewsBean, Observable<JoyNewsBean.DataBean>>() {
                    @Override
                    public Observable<JoyNewsBean.DataBean> call(JoyNewsBean joyNewsBean) {
                        return Observable.from(joyNewsBean.getData());
                    }
                }).filter(new Func1<JoyNewsBean.DataBean, Boolean>() {
                    @Override
                    public Boolean call(JoyNewsBean.DataBean dataBean) {
                        //过滤掉旧数据 拿到新数据
                        if (nowDate != null) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            Date oldTime = null;
                            Date newTime = null;
                            try {
                                oldTime = format.parse(nowDate);
                                newTime = format.parse(dataBean.getDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return newTime.after(oldTime);
                        }
                        return true;
                    }
                })
                .toList()
                .map(new Func1<List<JoyNewsBean.DataBean>, List<JoyNewsBean.DataBean>>() {
                    @Override
                    public List<JoyNewsBean.DataBean> call(List<JoyNewsBean.DataBean> dataBeen) {
                        LogUtils.logD("新的请求返回新闻集合大小为：" + dataBeen.size());
                        if (dataBeen.size()>0) {
                            nowDate = dataBeen.get(0).getDate();
                        }
                        return dataBeen;
                    }
                })
                .compose(RxSchedulers.<List<JoyNewsBean.DataBean>>io_main());
    }

    @Override
    public void onFailed(String error) {

    }
}
