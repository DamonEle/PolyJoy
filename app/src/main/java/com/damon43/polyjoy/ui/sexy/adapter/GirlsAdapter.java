package com.damon43.polyjoy.ui.sexy.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.damon43.common.base.BaseRecyclerViewAdapter;
import com.damon43.common.commonutils.DensityUtil;
import com.damon43.common.commonutils.MathUtils;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.GirlBean;
import com.damon43.polyjoy.ui.sexy.activity.GirlDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class GirlsAdapter extends BaseRecyclerViewAdapter<GirlBean.ResultsEntity> {
    public GirlsAdapter(Context context, List<GirlBean.ResultsEntity> datas) {
        super(context, datas);
    }

    @Override
    public int onCreateView(int viewType) {
        return R.layout.item_girl;
    }

    @Override
    public void onBindView(final GirlBean.ResultsEntity resultsEntity, BaseViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if ("dmj".equals(resultsEntity.getWho())) {
            layoutParams.height = DensityUtil.dip2px(mContext, 220);
        } else {
            layoutParams.height = DensityUtil.dip2px(mContext, 200);
        }
        holder.itemView.setLayoutParams(layoutParams);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(GirlDetailActivity.PHOTO_URL,resultsEntity.getUrl());
                GirlDetailActivity.startAction(mContext,bundle);
            }
        });
        Glide.with(mContext).load(resultsEntity.getUrl()).into(holder.getImageView(R.id.iv_girl));
        holder.itemView.setTag(resultsEntity.getUrl());
    }

    @Override
    public int getItemViewType(GirlBean.ResultsEntity resultsEntity) {
        return TheConstants.ITEM_STYLE_1;
    }
}
