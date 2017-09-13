package com.yg.tulvgo.ui.holder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yg.tulvgo.MainActivity;

import com.yg.tulvgo.R;
import com.yg.tulvgo.ui.adapter.MyAdapter;
import com.yg.tulvgo.view.RecyclerViewBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/6/29.
 */

public //头部viewHolder
class HeadHolder extends RecyclerView.ViewHolder {
    RecyclerViewBanner recyclerViewBanner;
    TextView tv_ticket;
    TextView yw;
    TextView food;
    TextView hotel;
    TextView nc_near;
    TextView line_recommend;
    private MainActivity mainActivity;
    private MyAdapter adapter;//轮播图适配器
    private List<ImageView> imageViewList = new ArrayList<>();//轮播图图片集合;
    int image[] = {
            R.drawable.a,
            R.drawable.a,
            R.drawable.a
    };

    public HeadHolder(View itemView, MainActivity mainActivity) {
        super(itemView);
        ButterKnife.bind(mainActivity);
        this.mainActivity = mainActivity;
        tv_ticket = (TextView) itemView.findViewById(R.id.tv_ticket);
        yw = (TextView) itemView.findViewById(R.id.yw);
        food = (TextView) itemView.findViewById(R.id.food);
        hotel = (TextView) itemView.findViewById(R.id.hotel);
        nc_near = (TextView) itemView.findViewById(R.id.nc_near);
        line_recommend = (TextView) itemView.findViewById(R.id.line_recommend);
        recyclerViewBanner = (RecyclerViewBanner) itemView.findViewById(R.id.rv_banner);

        initSliderLayout();
        initBannerView();
    }



    private void initBannerView() {
        initdata();
        final List<String> banners = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            banners.add("http://pic.qiantucdn.com/58pic/11/31/58/97p58PICV26.jpg");
            banners.add("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg");
            banners.add("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg");
        }
        recyclerViewBanner.isShowIndicatorPoint(true);
        recyclerViewBanner.setRvBannerDatas(banners);
        recyclerViewBanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int position, ImageView bannerView) {
                Glide.with(bannerView.getContext()).load(banners.get(position % banners.size())).placeholder(R.mipmap.ic_launcher).into(bannerView);
            }
        });
        recyclerViewBanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(mainActivity,""+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initdata() {
        ImageView iv;
        for (int i = 0; i < image.length; i++) {
            iv = new ImageView(mainActivity);
            iv.setBackgroundResource(image[i]);
            imageViewList.add(iv);
        }
    }


    private void initSliderLayout() {

//        tv_ticket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mainActivity, ProductListActicity.class);
//                mainActivity.startActivity(i);
//            }
//        });
//        yw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mainActivity, SpecialtyDetailsPageActivity.class);
//                mainActivity.startActivity(i);
//            }
//        });
//        food.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mainActivity, FoodDetalisPageActivity.class);
//                mainActivity.startActivity(i);
//            }
//        });
//        hotel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mainActivity, HotelDetailsPageActivity.class);
//                mainActivity.startActivity(i);
//            }
//        });
//        nc_near.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mainActivity, NanchangSurroundingActivity.class);
//                mainActivity.startActivity(i);
//            }
//        });
//        line_recommend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mainActivity, LineRecommendActivity.class);
//                mainActivity.startActivity(i);
//            }
//        });
    }
}
