package com.damon43.polyjoy.ui.joy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.damon43.common.base.BaseFragmentAdapter;

import java.util.List;

/**
 * @author damonmasty
 *         Created on 下午9:21 17-2-9.
 */

public class JoyNewsFragmentAdapter extends BaseFragmentAdapter {
    public JoyNewsFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm, fragmentList);
    }

    public JoyNewsFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
        super(fm, fragmentList, mTitles);
    }
}
