package com.damon43.polyjoy.ui.main.fragment;

import android.view.View;
import android.widget.TextView;

import com.damon43.common.base.BaseFragment;
import com.damon43.common.commonutils.ClassUtil;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.bean.NewsRecordBean;
import com.damon43.polyjoy.bean.RecordableBean;
import com.damon43.polyjoy.ui.my.MyContract;
import com.damon43.polyjoy.ui.my.model.MyModel;
import com.damon43.polyjoy.ui.my.presenter.MyPresenter;
import com.damon43.polyjoy.widget.JoysRecordView;

import java.util.List;

import butterknife.BindView;

/**
 * @author damonmasty
 *         Created on 上午10:53 17-2-6.
 *         我的
 */

public class MyFragment extends BaseFragment<MyPresenter, MyModel> implements MyContract.View, View.OnClickListener {
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
    @BindView(R.id.jr_my)
    JoysRecordView jrMy;
    @BindView(R.id.tv_by_count)
    TextView tvByCount;
    @BindView(R.id.tv_by_type)
    TextView tvByType;
    @BindView(R.id.tv_by_time)
    TextView tvByTime;
    public static final int STATE_ALL = 101;
    public static final int STATE_TIME = 102;
    private int currentState = STATE_ALL;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        initListener();
        mPresenter.showLocalRocordableBeans();
    }

    private void initListener() {
        tvByCount.setOnClickListener(this);
        tvByTime.setOnClickListener(this);
        tvByType.setOnClickListener(this);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM((MyContract.Model) ClassUtil.getT(this, 1), this);
    }

    @Override
    public void showLocalRocordableBeans(List<NewsRecordBean> beans) {
        jrMy.showLocalRocordableBeans(beans);
    }

    @Override
    public void loadLocalRocordableBeansByType(List<RecordableBean> beans) {
        jrMy.showLocalRocordableBeanByType(beans);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_by_count:
                if (currentState != STATE_ALL) {
                    currentState = STATE_ALL;
                    tvByCount.setSelected(true);
                    tvByType.setSelected(false);
                    tvByTime.setSelected(false);
                    mPresenter.showLocalRocordableBeans();
                }
                break;
            case R.id.tv_by_time:
                if (currentState != STATE_TIME) {
                    currentState = STATE_TIME;
                    tvByCount.setSelected(false);
                    tvByType.setSelected(false);
                    tvByTime.setSelected(true);
                    mPresenter.loadLocalRocordableBeansByType("girl");
                }
                break;
            case R.id.tv_by_type:
                tvByCount.setSelected(false);
                tvByType.setSelected(true);
                tvByTime.setSelected(false);
                break;
        }
    }
}
