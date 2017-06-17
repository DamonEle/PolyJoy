package com.damon43.polyjoy.db;

import android.content.res.Resources;

import com.damon43.polyjoy.R;
import com.damon43.polyjoy.app.TheApplication;
import com.damon43.polyjoy.bean.JoyNewsType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author damonmasty
 *         Created on 下午2:09 17-2-6.
 *         加载本地新闻频道
 */

public class NewsChannelManager {
    public static List<JoyNewsType> loadCustomNewsChannel() {
        Resources resources = TheApplication.getAppResources();
        List<String> types = Arrays.asList(resources.getStringArray(R.array.news_channel_name_static));
        List<String> typeIds = Arrays.asList(resources.getStringArray(R.array.news_channel_id_static));
        List<JoyNewsType> datas = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            JoyNewsType one = new JoyNewsType();
            one.setTypeId(typeIds.get(i));
            one.setTypeName(types.get(i));
            datas.add(one);
        }
        return datas;
    }
}
