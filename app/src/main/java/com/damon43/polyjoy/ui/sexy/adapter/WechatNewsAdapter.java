package com.damon43.polyjoy.ui.sexy.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.damon43.common.base.BaseRecyclerViewAdapter;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.WechatNewsBean;
import com.damon43.polyjoy.ui.joy.activity.JoyNewsDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */

public class WechatNewsAdapter extends BaseRecyclerViewAdapter<WechatNewsBean.ListEntity> implements View.OnClickListener {

    private String type;
    public WechatNewsAdapter(String type,Context mContext, List<WechatNewsBean.ListEntity> datas) {
        super(mContext, datas);
        this.type = type;
    }

    @Override
    public int onCreateView(int viewType) {
        return R.layout.item_wechatnews;
    }

    @Override
    public void onBindView(WechatNewsBean.ListEntity listEntity, BaseViewHolder holder) {
        Glide.with(mContext).load(listEntity.getFirstImg()).into(holder.getImageView(R.id.iv_head));
        holder.setText(R.id.tv_author, listEntity.getSource());
        holder.setText(R.id.tv_detail, listEntity.getTitle());
        holder.itemView.setTag(listEntity);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemViewType(WechatNewsBean.ListEntity listEntity) {
        return TheConstants.ITEM_STYLE_1;
    }

    @Override
    public void onClick(View v) {
        WechatNewsBean.ListEntity bean = (WechatNewsBean.ListEntity) v.getTag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_TITLE, bean.getTitle());
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_PIC, bean.getFirstImg());
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_URL, bean.getUrl());
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_TYPE, type);
        JoyNewsDetailActivity.startAction(mContext, bundle);
    }
}
