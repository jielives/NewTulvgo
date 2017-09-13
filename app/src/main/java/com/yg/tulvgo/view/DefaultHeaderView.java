package com.yg.tulvgo.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yg.tulvgo.R;


/**
 * Created by Lilei on 2016.
 */

public class DefaultHeaderView extends RelativeLayout implements VRefreshLayout.UpdateHandler {

    private final ImageView mImageView;
    private final View mProgress;
    private TextView mTextView;
    private static final String TAG = "DefultHeaderView";
    private RelativeLayout head;
    public void setHead(RelativeLayout head){
        this.head = head;
    }
    public DefaultHeaderView(Context context) {
        this(context, null);
    }
    public DefaultHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.defult_header_layout, this, true);
        mTextView = (TextView) findViewById(R.id.text);
        mImageView = (ImageView) findViewById(R.id.image);
        mProgress = findViewById(R.id.progress);
    }
    //箭头是否朝上
    private boolean arrowUp;
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onProgressUpdate(VRefreshLayout layout, VRefreshLayout.Progress progress, int status) {
        switch (status) {
            case VRefreshLayout.STATUS_INIT:
                mImageView.setVisibility(VISIBLE);
                mImageView.setImageResource(R.drawable.arrow_down);
                mProgress.setVisibility(GONE);
                break;
            case VRefreshLayout.STATUS_DRAGGING://正在下拉
                head.setVisibility(GONE);
                if (progress.getCurrentY() >= progress.getRefreshY()) {
                    if (arrowUp) {
                        arrowUp = false;
                        mTextView.setText(R.string.release_to_refresh);
                        mImageView.setImageResource(R.drawable.arrow_up);
                    }
                } else {
                    if (!arrowUp) {
                        arrowUp = true;
                        mTextView.setText(R.string.pull_to_refresh);
                        mImageView.setImageResource(R.drawable.arrow_down);
                    }
                }
                break;
            case VRefreshLayout.STATUS_RELEASE_PREPARE://释放刷新
                mTextView.setText(R.string.begin_refresh);
                mImageView.setVisibility(GONE);
                break;
            case VRefreshLayout.STATUS_REFRESHING://正在刷新
                VRefreshLayout.isRefreshing = true;
                mTextView.setText(R.string.refreshing);
                mImageView.setImageResource(android.R.drawable.btn_dropdown);
                mProgress.setVisibility(VISIBLE);
                break;
            case VRefreshLayout.STATUS_COMPLETE://刷新完成 , 等待500毫秒载将首页搜索框显示出来，已达到状态等待时间与首页搜索框显示同步
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        head.setVisibility(VISIBLE);
                    }
                },500);
                mTextView.setText(R.string.refresh_complete);
                mProgress.setVisibility(GONE);
                mImageView.setVisibility(VISIBLE);
                mImageView.setImageResource(R.drawable.okey);
                VRefreshLayout.isRefreshing = false;
                break;
            case VRefreshLayout.STATUS_RELEASE_CANCEL://取消刷新
                head.setVisibility(VISIBLE);
                mTextView.setText(R.string.cancel_refresh);
                break;
        }
    }
}
