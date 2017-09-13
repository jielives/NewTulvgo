package com.yg.tulvgo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yg.tulvgo.R;
import com.yg.tulvgo.gallery.CardAdapterHelper;
import com.yg.tulvgo.view.MultiShapeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jameson on 8/30/16.
 * 首页最佳线路数据源
 */
public class BestLineAdapter extends RecyclerView.Adapter<BestLineAdapter.ViewHolder> {
    private List<Integer> mList = new ArrayList<>();
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public BestLineAdapter(List<Integer> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card_item, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        holder.multiShapeView.setImageResource(mList.get(position));
        holder.multiShapeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView multiShapeView;
        ImageView multiShapeViewa;

        public ViewHolder(final View itemView) {
            super(itemView);
            multiShapeView = (ImageView) itemView.findViewById(R.id.imageView);
            multiShapeViewa = (ImageView) itemView.findViewById(R.id.imageViewa);
            multiShapeView.setImageResource(R.drawable.a);
            multiShapeViewa.setImageResource(R.drawable.a);
        }
    }
}
