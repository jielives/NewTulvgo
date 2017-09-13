

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
 * 首页当季热卖数据源
 */
public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.ViewHolder> {
    private List<Integer> mList = new ArrayList<>();
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public BestSellerAdapter(List<Integer> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bestseller_layout, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
//        holder.mImageView.setImageResource(mList.get(position));
//        holder.mImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                ToastUtils.show(holder.mImageView.getContext(), "" + position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView multiShapeView;
        public ViewHolder(final View itemView) {
            super(itemView);
            multiShapeView = (ImageView) itemView.findViewById(R.id.imageView);
            multiShapeView.setImageResource(R.drawable.a);
        }
    }
}
