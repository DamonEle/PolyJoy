package com.damon43.polyjoy.ui.main.presenter;

import com.damon43.polyjoy.bean.JoyNewsBean;
import com.damon43.polyjoy.bean.JoyNewsType;
import com.damon43.polyjoy.ui.main.contract.JoyMainContract;
import com.damon43.polyjoy.ui.main.model.JoyMainModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.*;

/**
 * @author damonmasty
 *         Created on 下午9:39 17-5-26.
 */
public class JoyMainPresenterTest {

    @Test
    public void loadCustomNewsChannel() throws Exception {
        JoyMainPresenter presenter = new JoyMainPresenter();
        JoyMainModel mockModel = Mockito.mock(JoyMainModel.class);
        JoyMainContract.View mockView = Mockito.mock(JoyMainContract.View.class);
        presenter.setAnything(mockModel,mockView);
        List<JoyNewsType> datas = new ArrayList<>();
        Mockito.when(mockModel.loadCustomNewsChannel()).thenReturn(Observable.
                just(datas));
        presenter.loadCustomNewsChannel();
        Mockito.verify(mockModel).loadCustomNewsChannel();
        Mockito.verify(mockView).showCustomNewsChannel(Mockito.anyList());
    }

}