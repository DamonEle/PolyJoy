package com.damon43.polyjoy.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damon43.common.base.BaseFragment;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.bean.NewsRecordBean;
import com.damon43.polyjoy.bean.RecordableBean;
import com.damon43.polyjoy.ui.my.MyContract;
import com.damon43.polyjoy.ui.my.model.MyModel;
import com.damon43.polyjoy.ui.my.presenter.MyPresenter;

import java.util.List;

/**
 * @author damonmasty
 *         Created on 上午10:53 17-2-6.
 *         我的
 */

public class MyFragment extends BaseFragment<MyPresenter,MyModel> implements MyContract.View{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void showLocalRocordableBeans(List<NewsRecordBean> beans) {

    }

    @Override
    public void loadLocalRocordableBeansByType(List<RecordableBean> beans) {

    }
}
