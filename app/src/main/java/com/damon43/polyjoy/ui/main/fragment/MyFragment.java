package com.damon43.polyjoy.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damon43.common.base.BaseFragment;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.bean.NewsRecordBean;
import com.damon43.polyjoy.bean.RecordableBean;
import com.damon43.polyjoy.ui.my.MyContract;
import com.damon43.polyjoy.ui.my.model.MyModel;
import com.damon43.polyjoy.ui.my.presenter.MyPresenter;
import com.damon43.polyjoy.widget.JoysRecordView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author damonmasty
 *         Created on 上午10:53 17-2-6.
 *         我的
 */

public class MyFragment extends BaseFragment<MyPresenter, MyModel> implements MyContract.View {
    private static final int FLAG_ALL_SEE = 101;
    private static final int FLAG_ONE_SEE = 101;
    @BindView(R.id.tv_today_read)
    TextView tvTodayRead;
    @BindView(R.id.tv_all_read)
    TextView tvAllRead;
    @BindView(R.id.tv_today_read_count)
    TextView tvTodayReadCount;
    @BindView(R.id.tv_all_read_count)
    TextView tvAllReadCount;
    @BindView(R.id.tab_change_view)
    TabLayout tabChangeView;
    @BindView(R.id.jr_my)
    JoysRecordView jrMy;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        tabChangeView.setTabMode(TabLayout.MODE_FIXED);
        tabChangeView.addTab(tabChangeView.newTab().setText(mContext.getString(R.string.allsee)).
                setTag(FLAG_ALL_SEE));
        tabChangeView.addTab(tabChangeView.newTab().setText(mContext.getString(R.string.choosesee))
                .setTag(FLAG_ONE_SEE));

        initListener();
    }

    private void initListener() {
        tabChangeView.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ((int) tab.getTag() == FLAG_ALL_SEE) {
                    mPresenter.showLocalRocordableBeans();
                } else {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
