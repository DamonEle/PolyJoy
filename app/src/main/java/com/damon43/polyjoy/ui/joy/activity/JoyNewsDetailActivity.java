package com.damon43.polyjoy.ui.joy.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.damon43.common.base.BaseActivity;
import com.damon43.common.commonutils.TimeUtils;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheApplication;
import com.damon43.polyjoy.bean.JoyNewsBean;
import com.damon43.polyjoy.bean.RecordableBean;
import com.damon43.polyjoy.db.RecordableBeanDao;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author damonmasty
 *         Created on 上午11:42 17-2-12.
 */

public class JoyNewsDetailActivity extends BaseActivity {

    public static final String NEWS_BEAN = "0";
    public static final String NEWS_TYPE = "4";
    @BindView(R.id.tb_newsdetail)
    Toolbar toolbar;
    @BindView(R.id.ctoolbarlayout)
    CollapsingToolbarLayout ctoolbarlayout;
    @BindView(R.id.wbcontent)
    WebView wbcontent;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.iv_newsdetail)
    ImageView ivNewsdetail;
    public static final String NEWS_TITLE = "1";
    public static final String NEWS_PIC = "2";
    public static final String NEWS_URL = "3";

    @Override
    public void initView() {
        // 设置navigation button 点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置 Toolbar menu
        toolbar.inflateMenu(R.menu.news_detail_menu);
        // 设置溢出菜单的图标
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_more_vert));
        // 设置menu item 点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.item_setting:
                    //点击设置
//                        break;
                }
                return false;
            }
        });
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra(NEWS_BEAN);
            String title = (String) bundle.getSerializable(NEWS_TITLE);
            String picUrl = (String) bundle.getSerializable(NEWS_PIC);
            String url = (String) bundle.getSerializable(NEWS_URL);
            String type = (String) bundle.getSerializable(NEWS_TYPE);

            toolbar.setTitle(title);
            Glide.with(this).load(picUrl).into(ivNewsdetail);

            //webview的设置初始化
            //声明WebSettings子类
            WebSettings webSettings = wbcontent.getSettings();

            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            webSettings.setJavaScriptEnabled(true);

            //设置自适应屏幕，两者合用
            webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

            //缩放操作
            webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
            webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

            //其他细节操作
            //缓存模式如下：
            //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
            //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
            //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
            //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webSettings.setAllowFileAccess(true); //设置可以访问文件
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
            webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

            wbcontent.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(request.getUrl().toString());
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {

                }

                @Override
                public void onPageFinished(WebView view, String url) {

                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

                }
            });
            wbcontent.loadUrl(url);
            RecordableBeanDao dao = TheApplication.getDBSession().getRecordableBeanDao();
            dao.insert(new RecordableBean(type, System.currentTimeMillis()));
        }
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_joynews_detail;
    }


    public static void startAction(Context context, Bundle bundle) {
        Intent data = new Intent(context, JoyNewsDetailActivity.class);
        data.putExtra(NEWS_BEAN, bundle);
        context.startActivity(data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wbcontent.onPause();
    }

    @Override
    protected void onDestroy() {
        ((ViewGroup) wbcontent.getParent()).removeView(wbcontent);
        wbcontent.destroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wbcontent.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        wbcontent.pauseTimers();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        wbcontent.resumeTimers();
    }

}
