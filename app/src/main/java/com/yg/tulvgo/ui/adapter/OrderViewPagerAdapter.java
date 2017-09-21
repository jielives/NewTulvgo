package com.yg.tulvgo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * =====================
 * Author: shenjie
 * Date: 13-11-1
 * Time: 下午14:17
 * describe:我的订单四个tab
 * =====================
 */

public class OrderViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public OrderViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getArguments().getString("title");
    }
}
