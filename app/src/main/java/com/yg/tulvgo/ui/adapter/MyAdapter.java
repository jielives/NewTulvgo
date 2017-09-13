package com.yg.tulvgo.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * =====================
 * Author: shenjie
 * Date: 13-11-1
 * Time: 下午14:17
 * describe:景点详情页的轮播图适配器
 * =====================
 */

public class MyAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private List<ImageView> imageViewList;
    private ViewPager viewPager;
    private TextView tv1;
    private TextView tv2;
    public MyAdapter(List<ImageView> imageViewList, ViewPager viewPager, TextView tv1, TextView tv2) {
        this.imageViewList = imageViewList;
        this.viewPager = viewPager;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = imageViewList.get(position % imageViewList.size());
        if (iv.getParent() != null) {
            ((ViewPager) iv.getParent()).removeView(iv);
        }
        viewPager.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            viewpager.removeView(imageViewList.get(position % imageViewList.size()));
//            super.destroyItem(container, position, object);
    }

    /*页面被选中时，触发
    *@param position 被选中页面索引
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int p = position % imageViewList.size() + 1;
        tv1.setText("5A" + "|" + "已售1000");
        tv2.setText(p + "/" + imageViewList.size());
    }
    @Override
    public void onPageSelected(int position) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
