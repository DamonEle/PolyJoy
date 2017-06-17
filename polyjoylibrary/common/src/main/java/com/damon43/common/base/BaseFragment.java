package com.damon43.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damon43.common.baserx.RxManager;
import com.damon43.common.commonutils.ClassUtil;
import com.damon43.common.commonutils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author damonmasty
 *         Created on 下午4:55 17-1-28.
 *
 * mvp示例
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, ClassUtils.get(this,1));
//    }
 */

public abstract class BaseFragment<T extends BasePresenter,E extends BaseModel> extends Fragment implements BaseView {
    protected View contentView;
    public T mPresenter;
    public RxManager rxManager;
    protected Context mContext;
    protected Unbinder mUnbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(getLayoutResource(),container,false);
        mUnbinder = ButterKnife.bind(this, contentView); //依赖注入
        rxManager = new RxManager();
        mContext = this.getContext();
        mPresenter = ClassUtil.getT(this,0); //实例化泛型presenter
        initPresenter();
        initView();
        return contentView;
    }

    public abstract int getLayoutResource();
    @Override
    public void onLoaded() {

    }

    public abstract void initView();

    public abstract void initPresenter();
    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadFailed(Throwable e,String error) {
        LogUtils.logE(e,error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rxManager.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
