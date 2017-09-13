package com.yg.tulvgo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yg.tulvgo.R;
import com.yg.tulvgo.gallery.CardAdapterHelper;
import com.yg.tulvgo.view.MultiShapeView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2017/6/13.
 * 首页热门活动数据源
 *
 */

public class HomeHotActivityAdapter extends RecyclerView.Adapter<HomeHotActivityAdapter.MyViewHolder> {
    private List<Integer> mList = new ArrayList<>();
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();
    private TextView point;
    private TextView sumPoint;
    public OnSwitchRvBannerListener1 onSwitchRvBannerListener1;
    public void setOnSwitchRvBannerListener(HomeHotActivityAdapter.OnSwitchRvBannerListener1 listener) {
        this.onSwitchRvBannerListener1 = listener;
    }
    public HomeHotActivityAdapter(List<Integer> imageViewList, TextView point, TextView sumPoint) {
        this.mList = imageViewList;
        this.point = point;
        this.sumPoint = sumPoint;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_item, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new HomeHotActivityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        onSwitchRvBannerListener1.switchBanner(position);
        sumPoint.setText(mList.size()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
       LinearLayout itemRvHorizontal;
        ImageView multiShapeView;
        ImageView multiShapeViewa;
        public MyViewHolder(View itemView) {
            super(itemView);
            multiShapeView = (ImageView) itemView.findViewById(R.id.imageView);

            multiShapeViewa = (ImageView) itemView.findViewById(R.id.imageViewa);
            multiShapeView.setImageResource(R.drawable.a);
            multiShapeViewa.setImageResource(R.drawable.a);
        }
    }

    /**
     * 滑动切换监听接口
     */
    public interface OnSwitchRvBannerListener1 {
        void switchBanner(int position);
    }
}
