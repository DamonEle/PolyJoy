package com.damon43.polyjoy.ui.main.presenter;

import com.damon43.polyjoy.bean.JoyNewsType;
import com.damon43.polyjoy.ui.main.contract.JoyMainContract;

import java.util.List;

import rx.functions.Action1;

/**
 * @author damonmasty
 *         Created on 下午3:11 17-2-6.
 */

public class JoyMainPresenter extends JoyMainContract.Presenter {
    @Override
    public void loadCustomNewsChannel() {
        mModel.loadCustomNewsChannel().subscribe(new Action1<List<JoyNewsType>>() {
            @Override
            public void call(List<JoyNewsType> types) {
                mView.showCustomNewsChannel(types);
            }
        });
    }

    public void setAnything(JoyMainContract.Model model,JoyMainContract.View view) {
        this.mModel = model;
        this.mView = view;
    }
}
