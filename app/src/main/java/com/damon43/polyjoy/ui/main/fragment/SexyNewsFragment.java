package com.damon43.polyjoy.ui.main.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.damon43.common.base.BaseFragment;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.ui.main.adapter.SexyNewsViewPagerAdapter;
import com.damon43.polyjoy.ui.sexy.fragment.WechatNewsFragment;
import com.damon43.polyjoy.ui.sexy.fragment.GirlsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author damonmasty
 *         Created on 上午10:52 17-2-6.
 */

public class SexyNewsFragment extends BaseFragment {

    @BindView(R.id.tab_joys)
    TabLayout tabJoys;
    @BindView(R.id.vp_sexynews)
    ViewPager vpSexynews;
    SexyNewsViewPagerAdapter vpAdapter;
    private int[] titles = new int[]{ R.string.wechatnews,R.string.photogirl};

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_joysexy;
    }

    @Override
    public void initView() {
        tabJoys.setTabMode(TabLayout.MODE_FIXED);
        List<Fragment> fragments = new ArrayList<>();
        List<String> strs = new ArrayList<>();
        fragments.add(new WechatNewsFragment());
        fragments.add(new GirlsFragment());

        tabJoys.setupWithViewPager(vpSexynews);
        for (int i = 0; i < titles.length; i++) {
            String title = mContext.getString(titles[i]);
            strs.add(title);
            tabJoys.addTab(tabJoys.newTab().setText(title));
        }
        vpAdapter = new SexyNewsViewPagerAdapter(getChildFragmentManager(), fragments, strs);
        vpSexynews.setAdapter(vpAdapter);
    }

    @Override
    public void initPresenter() {

    }

}
