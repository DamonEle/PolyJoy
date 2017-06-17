package com.damon43.polyjoy.ui.my.presenter;

import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.NewsRecordBean;
import com.damon43.polyjoy.ui.my.MyContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.*;

/**
 * @author damonmasty
 *         Created on 下午10:04 17-5-31.
 */
public class MyPresenterTest {
    private MyPresenter presenter;
    private MyContract.Model model;
    private MyContract.View view;

    @Before
    public void setUp() {
        presenter = new MyPresenter();
        model = Mockito.mock(MyContract.Model.class);
        view = Mockito.mock(MyContract.View.class);
        presenter.setAnything(model, view);
    }

    @Test
    public void showLocalRocordableBeans() throws Exception {
        //验证空集合 null 以及有的几种情况
        List<NewsRecordBean> datas = new ArrayList<>();

        datas.add(new NewsRecordBean(TheConstants.NEWS_TYPE_GIRL,3));
        datas.add(new NewsRecordBean(TheConstants.NEWS_TYPE_WECHAT,8));
        datas.add(new NewsRecordBean("top",10));
        datas.add(new NewsRecordBean("shehui",4));

        Mockito.when(model.loadLocalRocordableBeans()).thenReturn(Observable.just(datas));
        presenter.showLocalRocordableBeans();
        Mockito.verify(view).showLocalRocordableBeans(Mockito.anyList());
    }

    @Test
    public void loadLocalRocordableBeansByType() throws Exception {

    }

}