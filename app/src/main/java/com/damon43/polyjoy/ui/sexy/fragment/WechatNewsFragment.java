package com.damon43.polyjoy.ui.sexy.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.damon43.common.base.BaseFragment;
import com.damon43.common.baseinterface.RecyclerViewOnScrollListener;
import com.damon43.common.commonutils.ClassUtil;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.WechatNewsBean;
import com.damon43.polyjoy.ui.sexy.adapter.WechatNewsAdapter;
import com.damon43.polyjoy.ui.sexy.contract.WechatNewsContract;
import com.damon43.polyjoy.ui.sexy.model.WechatNewsModel;
import com.damon43.polyjoy.ui.sexy.presenter.WechatNewsPresener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author damonmasty
 *         Created on 下午9:46 17-3-29.
 */

public class WechatNewsFragment extends BaseFragment<WechatNewsPresener, WechatNewsModel> implements WechatNewsContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.pb_wait)
    ProgressBar mPbWait;
    @BindView(R.id.rv_wechatnews)
    RecyclerView mRvWechatnews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout mSrlNews;
    Unbinder unbinder;
    WechatNewsAdapter mNewsAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_wechatnews;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRvWechatnews.setLayoutManager(linearLayoutManager);
        mRvWechatnews.addOnScrollListener(new RecyclerViewOnScrollListener(linearLayoutManager) {
            @Override
            protected void onLoadMore(int page) {
                mPresenter.loadWechatNews(page);
            }
        });
        mSrlNews.setOnRefreshListener(this);
        mPresenter.loadWechatNews(0);
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM((WechatNewsContract.Model) ClassUtil.getT(this, 1), this);
    }

    @Override
    public void showWechatNews(List<WechatNewsBean.ListEntity> wechatNews) {
        if (mNewsAdapter == null) {
            mNewsAdapter = new WechatNewsAdapter(TheConstants.NEWS_TYPE_WECHAT,mContext, wechatNews);
            mRvWechatnews.setAdapter(mNewsAdapter);
        } else {
            mNewsAdapter.addDatas(wechatNews);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.loadWechatNews(0);
    }
}
