package com.damon43.polyjoy.ui.joy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.damon43.common.base.BaseFragment;
import com.damon43.common.baseinterface.OnRecyclerViewItemClickListener;
import com.damon43.common.baseinterface.RecyclerViewOnScrollListener;
import com.damon43.common.commonutils.ClassUtil;
import com.damon43.common.commonutils.HeaderAndFooterWrapper;
import com.damon43.common.commonutils.LogUtils;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.api.NetConstants;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.JoyNewsBean;
import com.damon43.polyjoy.ui.joy.adapter.JoyNewsAdapter;
import com.damon43.polyjoy.ui.joy.contract.JoyNewsContract;
import com.damon43.polyjoy.ui.joy.model.JoyNewsModel;
import com.damon43.polyjoy.ui.joy.presenter.JoyNewsPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author damonmasty
 *         Created on 下午3:31 17-2-7.
 */

public class JoyNewsFragment extends BaseFragment<JoyNewsPresenter, JoyNewsModel> implements JoyNewsContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    private static final long SHOW_NEXT_PAGE_TIME = 5000;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.pb_show_news)
    ProgressBar pbShowNews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout srlNews;
    private JoyNewsAdapter joyNewsAdapter;
    private HeaderAndFooterWrapper wrapper;
    private String id;
    private Handler handler;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_joynews;
    }

    @Override
    public void initView() {
        handler = new Handler();
        srlNews.setColorSchemeResources(R.color.accent);
        srlNews.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvNews.setLayoutManager(manager);
        rvNews.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        rvNews.setItemAnimator(new DefaultItemAnimator());
        rvNews.addOnScrollListener(new RecyclerViewOnScrollListener(manager) {
            @Override
            protected void onLoadMore(final int page) {
                if (joyNewsAdapter != null) {
                    LogUtils.logD("加载下一页");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            joyNewsAdapter.loadNextPage(page);
                            wrapper.notifyDataSetChanged();
                        }
                    }, SHOW_NEXT_PAGE_TIME);
                }
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString(TheConstants.NEWS_TYPE);
            pbShowNews.setVisibility(View.VISIBLE);
            mPresenter.loadJoyNewsByType(id, NetConstants.JUHE_DATA_NEWS_APP_KEY);
        }
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM((JoyNewsContract.Model) ClassUtil.getT(this, 1), this);
    }

    @Override
    public void showJoyNews(List<JoyNewsBean.DataBean> datas) {
        if (joyNewsAdapter == null) {
            joyNewsAdapter = new JoyNewsAdapter(id,mContext, datas);
            wrapper = new HeaderAndFooterWrapper(joyNewsAdapter);
            wrapper.addFooterView(View.inflate(mContext, R.layout.view_rv_footer, null));
        } else {
            joyNewsAdapter.addDatas(datas);
            wrapper.notifyDataSetChanged();
        }
        rvNews.setAdapter(wrapper);
    }

    @Override
    public void onLoadFailed(Throwable e,String error) {
        super.onLoadFailed(e,error);
        pbShowNews.setVisibility(View.GONE);
        srlNews.setRefreshing(false);
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
        pbShowNews.setVisibility(View.GONE);
        srlNews.setRefreshing(false);
    }

    @Override
    public void onLoading() {
        super.onLoading();
//        pbShowNews.setVisibility(View.VISIBLE);
    }


    @Override
    public void onRefresh() {
        mPresenter.loadJoyNewsByType(id, NetConstants.JUHE_DATA_NEWS_APP_KEY);
    }
}
