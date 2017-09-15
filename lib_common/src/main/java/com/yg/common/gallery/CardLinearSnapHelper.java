package com.yg.common.gallery;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 防止卡片在第一页和最后一页因无法"居中"而一直循环调用onScrollStateChanged-->SnapHelper.snapToTargetExistingView-->onScrollStateChanged
 * Created by jameson on 9/3/16.
 */
public class CardLinearSnapHelper extends LinearSnapHelper {
//    public boolean mNoNeedToScroll = false;
//
//    @Override
//    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
//        if (mNoNeedToScroll) {
//            return new int[]{0, 0};
//        } else {
//            return super.calculateDistanceToFinalSnap(layoutManager, targetView);
//        }
//    }

    private OrientationHelper mVerticalHelper;

    private OrientationHelper mHorizontalHelper;

    private RecyclerView mRecyclerView;

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];

        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToCenter(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToCenter(targetView, getVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        this.mRecyclerView = recyclerView;
        super.attachToRecyclerView(recyclerView);
    }

    private int distanceToCenter(View targetView, OrientationHelper helper) {
        //如果已经滚动到尽头 并且判断是否是第一个item或者是最后一个，直接返回0，不用多余的滚动了
        if ((helper.getDecoratedStart(targetView) == 0 && mRecyclerView.getChildAdapterPosition(targetView) == 0)
                || (helper.getDecoratedEnd(targetView) == helper.getEndAfterPadding()
                && mRecyclerView.getChildAdapterPosition(targetView) == mRecyclerView.getAdapter().getItemCount() - 1) )
            return 0;

        int viewCenter = helper.getDecoratedStart(targetView) + (helper.getDecoratedEnd(targetView) - helper.getDecoratedStart(targetView))/2;
        int correctCenter = (helper.getEndAfterPadding() - helper.getStartAfterPadding())/2;
        return viewCenter - correctCenter;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
