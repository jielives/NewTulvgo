package com.yg.tulvgo.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yg.tulvgo.MainActivity;
import com.yg.tulvgo.MyApplication;
import com.yg.tulvgo.R;
import com.yg.tulvgo.ui.holder.BestLineViewHolder;
import com.yg.tulvgo.ui.holder.BestSellerViewHoler;
import com.yg.tulvgo.ui.holder.HeadHolder;
import com.yg.tulvgo.ui.holder.HotActivityViewHolder;
import com.yg.tulvgo.ui.holder.OneCreatoonViewHolder;

/**
 * Created by shenjie on 2017/9/13.
 * 首页适配器
 */

public class HomeAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_HEAD = 0;//头（轮播图+资源分类）
    private final int TYPE_HOTACTIVITY = 1;//热门活动
    private final int TYPE_ONECARTOON = 2;//一卡通专区
    private final int TYPE_BESTLINE = 3;//最佳线路
    private final int TYPE_BESTSELLER = 4;//当季热卖
    private final int TYPE_FOOTER = 5;//脚布局
    private Activity mainActivity;

    public HomeAdapter(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(MyApplication.getContext());
        switch (viewType) {
            case TYPE_HEAD:
                ViewGroup head = (ViewGroup) mInflater.inflate(R.layout.home_head_layout, parent, false);
                HeadHolder headHolder = new HeadHolder(head,mainActivity);
                return headHolder;
            case TYPE_HOTACTIVITY:
                ViewGroup hot = (ViewGroup) mInflater.inflate(R.layout.home_hotactivity_layout,null);
                HotActivityViewHolder hotActivityViewHolder = new HotActivityViewHolder(hot);
                return hotActivityViewHolder;
            case TYPE_ONECARTOON:
                ViewGroup OneCartoon = (ViewGroup) mInflater.inflate(R.layout.home_onecartoon_layout, parent, false);
                OneCreatoonViewHolder oneCreatoonViewHolder = new OneCreatoonViewHolder(OneCartoon);
                return oneCreatoonViewHolder;
            case TYPE_BESTLINE:
                ViewGroup BestLine = (ViewGroup) mInflater.inflate(R.layout.home_bestline_layout, parent, false);
                BestLineViewHolder bestLineViewHolder = new BestLineViewHolder(BestLine);
                return bestLineViewHolder;
            case TYPE_BESTSELLER:
                ViewGroup bestSeller = (ViewGroup) mInflater.inflate(R.layout.item_bestseller_layout, parent, false);
                BestSellerViewHoler bestSellerViewHoler = new BestSellerViewHoler(bestSeller);
                return bestSellerViewHoler;
            case TYPE_FOOTER:
                ViewGroup Footerview = (ViewGroup) mInflater.inflate(R.layout.item_home_foot, parent, false);
                return new FootViewHolder(Footerview);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_HEAD:
                break;
            case TYPE_HOTACTIVITY:
                break;
            case TYPE_ONECARTOON:
                break;
            case TYPE_BESTLINE:
                break;
            case TYPE_BESTSELLER:
                break;
            case TYPE_FOOTER:
                break;
        }
    }
    /*
    *  return data.size() == 0 ? 0 : data.size() + 1;
    * */
    @Override
    public int getItemCount() {
        int count = 0;
        return 7;//测试数 9
    }
    /*
    *  如果已经滑动到底部就返回脚布局
    *  否则根据position位置返回item对应的布局
    */
    @Override
    public int getItemViewType(int position) {
        int viewType;
        if(position + 1 == getItemCount()){
            viewType =  TYPE_FOOTER;
        }else{
            if (position == 0) {
                viewType = TYPE_HEAD;
            } else if (position == 1) {
                viewType = TYPE_HOTACTIVITY;
            } else if (position == 2) {
                viewType = TYPE_ONECARTOON;
            } else if (position == 3) {
                viewType = TYPE_BESTLINE;
            } else {
                viewType = TYPE_BESTSELLER;
            }
        }
        return viewType;
    }
    class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View view) {
            super(view);
        }
    }
}
