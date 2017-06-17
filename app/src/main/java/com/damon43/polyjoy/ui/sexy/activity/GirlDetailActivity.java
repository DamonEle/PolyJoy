package com.damon43.polyjoy.ui.sexy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.damon43.common.base.BaseActivity;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheApplication;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.RecordableBean;
import com.damon43.polyjoy.db.RecordableBeanDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/4/17.
 */

public class GirlDetailActivity extends BaseActivity {
    public static final String PHOTO_URL = "0";
    public static final String EXTRA = "1";
    @BindView(R.id.tb_girl)
    Toolbar mTbGirl;
    @BindView(R.id.pv_girl)
    PhotoView mPvGirl;

    @Override
    public void initView() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra(EXTRA);
            String url = bundle.getString(PHOTO_URL);
            Glide.with(this).load(url).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    mPvGirl.setImageDrawable(resource);
                }
            });

            RecordableBeanDao dao = TheApplication.getDBSession().getRecordableBeanDao();
            dao.insert(new RecordableBean(TheConstants.NEWS_TYPE_GIRL, System.currentTimeMillis()));
        }
        mTbGirl.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_girl_detail;
    }


    public static void startAction(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GirlDetailActivity.class);
        intent.putExtra(EXTRA, bundle);
        context.startActivity(intent);
    }

}
