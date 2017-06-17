package com.damon43.polyjoy.ui.main.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.damon43.common.base.BaseFragment;
import com.damon43.common.commonutils.ClassUtil;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.JoyNewsType;
import com.damon43.polyjoy.ui.joy.fragment.JoyNewsFragment;
import com.damon43.polyjoy.ui.joy.adapter.JoyNewsFragmentAdapter;
import com.damon43.polyjoy.ui.main.contract.JoyMainContract;
import com.damon43.polyjoy.ui.main.model.JoyMainModel;
import com.damon43.polyjoy.ui.main.presenter.JoyMainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author damonmasty
 *         Created on 上午10:50 17-2-6.
 *         时娱
 */

public class JoyMainFragment extends BaseFragment<JoyMainPresenter, JoyMainModel> implements JoyMainContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.iv_add_channel)
    ImageView ivAddChannel;
    @BindView(R.id.vp_mainnews)
    ViewPager vpMainnews;
    JoyNewsFragmentAdapter fragmentAdapter;
    List<String> titles;
    List<Fragment> fragments;
    @Override
    public int getLayoutResource() {
        return R.layout.fragment_joymain;
    }

    @Override
    public void initView() {
        mPresenter.loadCustomNewsChannel();
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(vpMainnews);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM((JoyMainContract.Model) ClassUtil.getT(this, 1), this);
    }

    @Override
    public void showCustomNewsChannel(List<JoyNewsType> types) {
        for (int i = 0; i < types.size(); i++) {
            String title = types.get(i).getTypeName();
            String id = types.get(i).getTypeId();
            tabs.addTab(tabs.newTab().setText(title).setTag(id), i);
            titles.add(title);
            JoyNewsFragment fragment = new JoyNewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TheConstants.NEWS_TYPE,id);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        fragmentAdapter = new JoyNewsFragmentAdapter(getChildFragmentManager(),fragments,titles);
        vpMainnews.setAdapter(fragmentAdapter);
    }

}
