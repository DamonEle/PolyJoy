package com.damon43.polyjoy.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.damon43.common.base.BaseActivity;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.ui.main.fragment.JoyMainFragment;
import com.damon43.polyjoy.ui.main.fragment.MyFragment;
import com.damon43.polyjoy.ui.main.fragment.SexyNewsFragment;

import butterknife.BindView;

/**
 * @author damonmasty
 *         Created on 上午9:11 17-1-27.
 *         主界面
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomnavi)
    BottomNavigationView bottomnavi;

    private JoyMainFragment joyMainFragment ;
    private SexyNewsFragment sexyNewsFragment;
    private MyFragment myFragment ;

    @Override
    public void initView() {
        initTab();
    }

    /**
     * 初始化底部导航监听
     */
    private void initTab() {
        bottomnavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_time:
                        switchTo(0);
                        break;
                    case R.id.item_wechatnews:
                        switchTo(1);
                        break;
                    case R.id.item_my:
                        switchTo(2);
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public void initPresenter() {
        //简单页面不作mvp模式
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        initFtagment();
    }

    /**
     * 初始化碎片
     */
    private void initFtagment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        joyMainFragment = new JoyMainFragment();
        sexyNewsFragment = new SexyNewsFragment();
        myFragment = new MyFragment();

        transaction.add(R.id.fl_container,joyMainFragment,"joyMainFragment");
        transaction.add(R.id.fl_container, sexyNewsFragment,"sexyNewsFragment");
        transaction.add(R.id.fl_container,myFragment,"myFragment");
        transaction.commit();
        switchTo(0);

    }

    /**
     * 切换fragment
     * @param i
     */
    private void switchTo(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (i){
            case 0:
                transaction.hide(sexyNewsFragment);
                transaction.hide(myFragment);
                transaction.show(joyMainFragment);
                break;
            case 1:
                transaction.hide(joyMainFragment);
                transaction.hide(myFragment);
                transaction.show(sexyNewsFragment);
                break;
            case 2:
                transaction.hide(sexyNewsFragment);
                transaction.hide(joyMainFragment);
                transaction.show(myFragment);
                break;
        }
        transaction.commit();
    }
}
