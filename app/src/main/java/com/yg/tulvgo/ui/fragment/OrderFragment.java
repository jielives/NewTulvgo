package com.yg.tulvgo.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yg.common.base.BaseFragment;
import com.yg.tulvgo.R;
import com.yg.tulvgo.ui.adapter.OrderViewPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shenjie on 2017/9/12.
 * 订单
 */

public class OrderFragment extends BaseFragment {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    List<Fragment> fragments;
    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        super.initView();
        fragments = new ArrayList<>();
        fragments.add(setTitle(new AllOrdersFragment(),"全部"));
        fragments.add(setTitle(new UnUserOrdersFragment(),"未使用"));
        fragments.add(setTitle(new UserOrdersFragment(),"已使用"));
        fragments.add(setTitle(new CanceledOrdersFragment(),"已取消"));

        viewPager.setAdapter(new OrderViewPagerAdapter(getChildFragmentManager(),fragments));
        //关联Tablayout与viewpager
        tablayout.setupWithViewPager(viewPager);
        //Tablayout渲染出来后通过view.post方法实现对下划线的设置
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayout,20,20);
            }
        });
    }
    private Fragment setTitle(Fragment fragment,String title){
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }
    /*
   *  利用反射修改修改TabLayout的下划线的宽度
   * */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
