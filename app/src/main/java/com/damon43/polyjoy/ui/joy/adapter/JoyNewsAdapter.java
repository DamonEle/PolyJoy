package com.damon43.polyjoy.ui.joy.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.damon43.common.base.BaseRecyclerViewAdapter;
import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheConstants;
import com.damon43.polyjoy.bean.JoyNewsBean;
import com.damon43.polyjoy.ui.joy.activity.JoyNewsDetailActivity;

import java.util.List;

/**
 * @author damonmasty
 *         Created on 下午9:02 17-2-7.
 */

public class JoyNewsAdapter extends BaseRecyclerViewAdapter<JoyNewsBean.DataBean> implements View.OnClickListener {

    private static int PAGE = 1;
    private String type;
    @Override
    public int getItemCount() {
        return datas.size() / 3 * PAGE;
    }

    public JoyNewsAdapter(String type ,Context mContext, List<JoyNewsBean.DataBean> dataBeanList) {
        super(mContext);
        this.type = type;
        datas = dataBeanList;
    }

    @Override
    public void onClick(View view) {
        JoyNewsBean.DataBean bean = (JoyNewsBean.DataBean) view.getTag();
        Bundle bundle = new Bundle();
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_TITLE, bean.getTitle());
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_PIC, bean.getThumbnailPicS());
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_URL, bean.getUrl());
        bundle.putSerializable(JoyNewsDetailActivity.NEWS_TYPE, type);
        JoyNewsDetailActivity.startAction(mContext, bundle);
    }

    @Override
    public int onCreateView(int viewType) {
        if (viewType == TheConstants.ITEM_STYLE_1) {
            return R.layout.item_joy_news_style_1;
        } else {
            return R.layout.item_joy_news_style_2;
        }
    }

    @Override
    public void onBindView(JoyNewsBean.DataBean dataBean, BaseViewHolder holder) {

        if (TextUtils.isEmpty(dataBean.getThumbnailPicS03())) {
            // FIXME: 17-2-8 2 月 8 号 被通知可以跳槽彩讯
            holder.setText(R.id.tv_author, dataBean.getAuthorName());
            holder.setText(R.id.tv_date, dataBean.getDate());
            holder.setText(R.id.tv_title, dataBean.getTitle());
        } else {
            holder.setText(R.id.tv_author, dataBean.getAuthorName());
            holder.setText(R.id.tv_date, dataBean.getDate());
            holder.setText(R.id.tv_title, dataBean.getTitle());
            Glide.with(mContext).load(dataBean.getThumbnailPicS02()).into(holder.getImageView(R.id.iv_img_2));
            Glide.with(mContext).load(dataBean.getThumbnailPicS03()).into(holder.getImageView(R.id.iv_img_3));
        }
        Glide.with(mContext).load(dataBean.getThumbnailPicS()).into(
                holder.getImageView(R.id.iv_img_1));
        holder.itemView.setTag(dataBean);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemViewType(JoyNewsBean.DataBean dataBean) {
        if (TextUtils.isEmpty(dataBean.getThumbnailPicS03())) {
            return TheConstants.ITEM_STYLE_1;
        } else {
            return TheConstants.ITEM_STYLE_2;
        }
    }

    public void loadNextPage(int page) {
        if (page <= 3) {
            PAGE = page;
            notifyDataSetChanged();
        }
    }
}
