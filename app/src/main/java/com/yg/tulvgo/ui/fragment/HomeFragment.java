package com.yg.tulvgo.ui.fragment;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yg.tulvgo.MainActivity;
import com.yg.tulvgo.MyApplication;
import com.yg.tulvgo.R;
import com.yg.tulvgo.gallery.CustomRecyclerView;
import com.yg.tulvgo.ui.adapter.HomeAdapter;
import com.yg.tulvgo.view.DefaultHeaderView;
import com.yg.tulvgo.view.VRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shenjie on 2017/9/12.
 * 首页
 */

public class HomeFragment extends Fragment{
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
    private MainActivity mainActivity;
    private HomeAdapter homeAdapter;
    private DefaultHeaderView mDefaultHeaderView;
    private Handler handler = new Handler();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        heard.setAlpha(0.0f);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rl.setLayoutManager(new LinearLayoutManager(MyApplication.getContext(),LinearLayoutManager.VERTICAL,false));
        rl.setAdapter(homeAdapter);//设置适配器
        rl.addOnScrollListener(listener);
        homeAdapter = new HomeAdapter(mainActivity);
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
        initView();
    }
    private int sumY=0;
    private ArgbEvaluator evaluator = new ArgbEvaluator();
    private float duration = 200.0f;//在0—150之间，去改变头部的透明度
    RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            sumY+=dy;
            //滚动的距离相对于0-150有个百分比，头部的透明度也是初始值变动到不透明，通过距离的百分比，设置透明度对应的值
            float f = 0.0f;
            if(sumY<0){
                f = 0.0f;
                heard.setAlpha(f);
//                bgColor = 0x00;
            }else if(sumY>200){
                f = 1.0f;
                heard.setAlpha(100%100);
//                bgColor = 0xFFF;
            }else{
                f = sumY / duration;
                heard.setAlpha(sumY / duration);
//                bgColor = (int)evaluator.evaluate(sumY / duration, 0x000, 0XFFF);
            }
            heard.setAlpha(f);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();

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
    public void initView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        rl.setLayoutManager(layoutManager);
        rl.setAdapter(homeAdapter);
    }
}