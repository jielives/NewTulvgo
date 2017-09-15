package com.yg.tulvgo.ui.fragment;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yg.common.base.BaseFragment;
import com.yg.common.statusbar.StatusBarCompat;
import com.yg.tulvgo.MyApplication;
import com.yg.tulvgo.R;
import com.yg.common.gallery.CustomRecyclerView;
import com.yg.tulvgo.ui.adapter.HomeAdapter;
import com.yg.tulvgo.view.DefaultHeaderView;
import com.yg.tulvgo.view.VRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shenjie on 2017/9/12.
 * 首页
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.refresh_layout)
    VRefreshLayout mRefreshLayout;
    @BindView(R.id.head_title)
    RelativeLayout headTitle;
    @BindView(R.id.swipe_target)
    CustomRecyclerView rl;
    @BindView(R.id.heard)
    RelativeLayout heard;
    @BindView(R.id.search_info)
    TextView searchInfo;
    private HomeAdapter homeAdapter;
    private DefaultHeaderView mDefaultHeaderView;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(Bundle arguments) {
        super.initData(arguments);
    }
    private int sumY=0;
    private ArgbEvaluator evaluator = new ArgbEvaluator();
    private float duration = 200.0f;//在0—150之间，去改变头部的透明度
    RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            sumY+=dy;
            float f = 0.0f;
            if(sumY<0){
                f = 0.0f;
                heard.setAlpha(f);
            }else if(sumY>200){
                f = 1.0f;
                heard.setAlpha(100%100);
            }else{
                f = sumY / duration;
                heard.setAlpha(sumY / duration);
            }
            heard.setAlpha(f);
        }
    };
    @Override
    protected void initView() {
        super.initView();
        heard.setAlpha(0.0f);
        rl.setLayoutManager(new LinearLayoutManager(MyApplication.getContext(),LinearLayoutManager.VERTICAL,false));
        rl.setAdapter(homeAdapter);//设置适配器
        rl.addOnScrollListener(listener);
        homeAdapter = new HomeAdapter(mContext);
        mDefaultHeaderView = mRefreshLayout.getmDefaultHeaderView();
        mDefaultHeaderView.setHead(headTitle);
        mRefreshLayout.addOnRefreshListener(new VRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.refreshComplete();
                    }
                }, 2000);
            }
        });
            final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            rl.setLayoutManager(layoutManager);
            rl.setAdapter(homeAdapter);
        StatusBarCompat.setStatusBarColor(getActivity(), Color.BLACK);
    }

    @OnClick(R.id.search_info)
    public void onClick() {
//        Intent i = new Intent(MyApplication.getContext(), SearchActivity.class);
//        startActivity(i);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        if (VRefreshLayout.isRefreshing) {
            mRefreshLayout.refreshComplete();
        }
    }
}