package com.yg.tulvgo.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yg.common.base.BaseApplication;
import com.yg.common.customview.CustomLinearLayoutManager;
import com.yg.tulvgo.MyApplication;
import com.yg.tulvgo.R;
import com.yg.tulvgo.bean.TestBean;
import com.yg.tulvgo.bean.TicketBean;
import com.yg.tulvgo.ui.activity.ScenicSpotDetailsPageActivity;
import com.yg.tulvgo.view.MyListView;

import java.util.ArrayList;

/**
 * Created by shenjie on 2017/9/22.
 * 门票
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>{
    ScenicSpotDetailsPageActivity scenicSpotDetailsPageActivity;
    public TicketAdapter(ScenicSpotDetailsPageActivity scenicSpotDetailsPageActivity) {
        this.scenicSpotDetailsPageActivity = scenicSpotDetailsPageActivity;
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(MyApplication.getContext());
        View itemView = inflater.inflate(R.layout.item_ticket_type_layout,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);

//        TicketAdapter.TicketViewHolder holder = new TicketAdapter.TicketViewHolder(LayoutInflater.from(MyApplication.getContext()
//                ).inflate(R.layout.item_ticket_layout,null));
        TicketAdapter.TicketViewHolder holder = new TicketAdapter.TicketViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {

//        TicketTypeAdapter ticketTypeAdapter = new TicketTypeAdapter();
//        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(BaseApplication.getContext());
//        layoutManager.setScrollEnabled(false);
//        holder.rl_ticket_type.setLayoutManager(layoutManager);
//        holder.rl_ticket_type.setAdapter(ticketTypeAdapter);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rl_ticket_type;
        MyListView listView;
        public TicketViewHolder(View itemView) {
            super(itemView);
            listView = (MyListView) itemView.findViewById(R.id.lv_ticket);
            ArrayList<TicketBean> mStationImages_Children = new ArrayList<TicketBean>();
            listView.setFocusable(false);
            FoldingTicketAdapter foldingTicketAdapter = new FoldingTicketAdapter(scenicSpotDetailsPageActivity,listView);
            listView.setAdapter(foldingTicketAdapter);
            for (int i = 0; i < 4; i++) {
                TicketBean mStationImage_Children = new TicketBean();
                mStationImage_Children.setImg_name("鄱阳湖3.8女神票特价优惠票（仅限18-36的女性预定）");
                mStationImage_Children.setImg_url_l("48起");
                mStationImage_Children.setImg_url_s("50.00");
                mStationImages_Children.add(mStationImage_Children);
                listView.setAdapter(foldingTicketAdapter);
                foldingTicketAdapter.setAdapterData(mStationImages_Children);
            }
        }
    }
}
