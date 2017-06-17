package com.damon43.polyjoy.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.damon43.common.base.BaseFragmentAdapter;

import java.util.List;

/**
 * @author damonmasty
 *         Created on 下午9:37 17-3-29.
 */

public class SexyNewsViewPagerAdapter extends BaseFragmentAdapter{

    public SexyNewsViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
        super(fm, fragmentList, mTitles);
    }
}
