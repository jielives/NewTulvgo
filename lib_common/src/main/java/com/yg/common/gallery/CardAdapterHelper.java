package com.yg.common.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import jameson.io.library.util.ScreenUtil;

import static com.yg.common.utils.UIUtils.getResources;


/**
 * adapter中调用onCreateViewHolder, onBindViewHolder
 */
public class CardAdapterHelper {
    private int mPagePadding = 3;//卡片间距
    private int mShowLeftCardWidth = 5;//左边卡片距离

    public void onCreateViewHolder(ViewGroup parent,  View itemView) {
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) itemView.getLayoutParams();
//        lp.width = parent.getWidth() - ScreenUtil.dip2px(itemView.getContext(), 2 * (mPagePadding + mShowLeftCardWidth));
        //设置首页item的偏移量为屏幕
        int pagerWidth = 0;
        pagerWidth =  (int) (getResources().getDisplayMetrics().widthPixels*4.8f/5.0f);
        lp.width = pagerWidth;
        itemView.setLayoutParams(lp);
    }

    public void onBindViewHolder(View itemView, final int position, int itemCount) {
        int padding = ScreenUtil.dip2px(itemView.getContext(), mPagePadding);
        itemView.setPadding(padding, 0, padding, 0);
        int leftMarin = position == 0 ? padding + ScreenUtil.dip2px(itemView.getContext(), mShowLeftCardWidth) : 0;
        int rightMarin = position == itemCount - 1 ? padding + ScreenUtil.dip2px(itemView.getContext(), mShowLeftCardWidth) : 0;
        setViewMargin(itemView, leftMarin, 0, rightMarin, 0);
    }

    private void setViewMargin(View view, int left, int top, int right, int bottom) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom);
            view.setLayoutParams(lp);
        }
    }

    public void setPagePadding(int pagePadding) {
        mPagePadding = pagePadding;
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        mShowLeftCardWidth = showLeftCardWidth;
    }
}
