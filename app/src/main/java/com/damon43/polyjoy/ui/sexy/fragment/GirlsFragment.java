package com.damon43.polyjoy.ui.sexy.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damon43.common.base.BaseFragment;
import com.damon43.common.commonutils.ClassUtil;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.bean.GirlBean;
import com.damon43.polyjoy.ui.sexy.adapter.GirlsAdapter;
import com.damon43.polyjoy.ui.sexy.contract.GirlsContract;
import com.damon43.polyjoy.ui.sexy.model.GirlsModel;
import com.damon43.polyjoy.ui.sexy.presenter.GirlsPresenter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author damonmasty
 *         Created on 下午10:01 17-3-29.
 */

public class GirlsFragment extends BaseFragment<GirlsPresenter, GirlsModel> implements GirlsContract.View {
    @BindView(R.id.rv_girls)
    PullLoadMoreRecyclerView mRvGirls;
    protected int currentPage;
    GirlsAdapter mGirlsAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_girls;
    }

    @Override
    public void initView() {
        mRvGirls.setStaggeredGridLayout(2);
        mPresenter.loadGirlsImages(currentPage);
        mRvGirls.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadGirlsImages(0);
            }

            @Override
            public void onLoadMore() {
                mPresenter.loadGirlsImages(currentPage);
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM((GirlsContract.Model) ClassUtil.getT(this, 1), this);
    }

    @Override
    public void onLoaded() {
        super.onLoaded();
        currentPage++;
        mRvGirls.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoading() {
        super.onLoading();
    }

    @Override
    public void onLoadFailed(Throwable e, String error) {
        super.onLoadFailed(e, error);
        currentPage++;
        mRvGirls.setPullLoadMoreCompleted();
    }

    @Override
    public void showGirlsImages(List<GirlBean.ResultsEntity> images) {
        if (mGirlsAdapter == null) {
            mGirlsAdapter = new GirlsAdapter(mContext, images);
            mRvGirls.setAdapter(mGirlsAdapter);
        } else {
            mGirlsAdapter.addDatas(images);
        }
    }

}
