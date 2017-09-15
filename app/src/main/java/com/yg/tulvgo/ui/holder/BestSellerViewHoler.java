package com.yg.tulvgo.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yg.tulvgo.R;
import com.yg.common.gallery.SpeedRecyclerView;

/**
 * Created by admin on 2017/6/29.
 * 当季度热卖
 */

public class BestSellerViewHoler extends RecyclerView.ViewHolder {
        SpeedRecyclerView rlMgallery;
        private ImageView multiShapeView;
        public BestSellerViewHoler(View itemView) {

        super(itemView);
        rlMgallery = (SpeedRecyclerView) itemView.findViewById(R.id.bestseller_recyclerview);
        multiShapeView = (ImageView) itemView.findViewById(R.id.imageView);
        multiShapeView.setImageResource(R.drawable.a);

    }
}
