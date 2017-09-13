package com.yg.tulvgo.ui.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yg.tulvgo.MyApplication;
import com.yg.tulvgo.R;
import com.yg.tulvgo.gallery.CardScaleHelper;
import com.yg.tulvgo.gallery.SpeedRecyclerView;
import com.yg.tulvgo.ui.adapter.HomeHotActivityAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/29.
 *
 * 热门活动
 */

public class HotActivityViewHolder extends RecyclerView.ViewHolder {
    SpeedRecyclerView rlMgallery;
    private HomeHotActivityAdapter homeHotActivityAdapter;
    private List<Integer> imageViewList = new ArrayList<>();//轮播图图片集合;
    private TextView point;
    private TextView sumPoint;
    public HotActivityViewHolder(View itemView) {
        super(itemView);
        rlMgallery = (SpeedRecyclerView) itemView.findViewById(R.id.gallery_recyclerview);
        point = (TextView) itemView.findViewById(R.id.point);

        sumPoint = (TextView) itemView.findViewById(R.id.sum_point);
        initdata();
        initViewHotActivity();
    }

    public void initdata() {
        for (int i = 0; i < 1; i++) {
            imageViewList.add(R.drawable.a);
            imageViewList.add(R.drawable.a);
            imageViewList.add(R.drawable.a);
        }
    }
    private void initViewHotActivity() {
        homeHotActivityAdapter = new HomeHotActivityAdapter(imageViewList,point,sumPoint);
        //设置布局管理器
        final LinearLayoutManager layoutMgr = new LinearLayoutManager(MyApplication.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlMgallery.setLayoutManager(layoutMgr);
        rlMgallery.setAdapter(homeHotActivityAdapter);
        final int  firstPosition= layoutMgr.findFirstVisibleItemPosition();
        //myRecyclerview绑定scale效果
        CardScaleHelper mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.attachToRecyclerView(rlMgallery);
        rlMgallery.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = layoutMgr.findLastCompletelyVisibleItemPosition();
                if(lastCompletelyVisibleItemPosition!=-1){
                    point.setText(lastCompletelyVisibleItemPosition+1+"/");
                }
            }
        });
        homeHotActivityAdapter.setOnSwitchRvBannerListener(new HomeHotActivityAdapter.OnSwitchRvBannerListener1() {
            @Override
            public void switchBanner(int position) {
                int firstVisibleItemPosition = layoutMgr.findFirstVisibleItemPosition();
            }
        });
    }
}
