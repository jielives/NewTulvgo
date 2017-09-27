package com.yg.tulvgo.ui.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yg.tulvgo.MyApplication;
import com.yg.tulvgo.R;

/**
 * Created by shenjie on 2017/9/22.
 * 门票类型
 */

public class TicketTypeAdapter extends RecyclerView.Adapter<TicketTypeAdapter.TicketTypeViewHolder>{

    @Override
    public TicketTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(MyApplication.getContext());
        View itemView = inflater.inflate(R.layout.item_ticket_type_layout,null,true);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);

        TicketTypeAdapter.TicketTypeViewHolder holder = new TicketTypeAdapter.TicketTypeViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(TicketTypeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class TicketTypeViewHolder extends RecyclerView.ViewHolder{

        public TicketTypeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
