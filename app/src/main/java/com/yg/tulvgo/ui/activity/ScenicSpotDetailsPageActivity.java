package com.yg.tulvgo.ui.activity;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.yg.common.base.BaseActivity;
import com.yg.common.base.BaseApplication;
import com.yg.common.customview.CustomLinearLayoutManager;
import com.yg.common.customview.ScrollViewTabIndicator;
import com.yg.common.statusbar.StatusBarCompat;
import com.yg.tulvgo.MyApplication;
import com.yg.tulvgo.R;
import com.yg.tulvgo.ui.adapter.NearResourcesAdapter;
import com.yg.tulvgo.ui.adapter.TicketAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScenicSpotDetailsPageActivity extends BaseActivity implements NestedScrollView.OnScrollChangeListener{
    @BindView(R.id.rl_near_resources)
    RecyclerView rlNearResources;//附近资源
    @BindView(R.id.head)
    RelativeLayout head;
    @BindView(R.id.sv)
    NestedScrollView mSv;
    @BindView(R.id.tab)
    ScrollViewTabIndicator mTab;
    @BindView(R.id.tab2)
    ScrollViewTabIndicator mTab2;
    @BindView(R.id.rl_ticket)
    RecyclerView rlTicket;
    private TicketAdapter ticketAdapter;
    private int[] mTabMiddleLocation = new int[2];
    private int[] mTabTopLocation = new int[2];
    /**景区详情页*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.activity_scenic_spot_details_page;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        super.initView();
        ticketAdapter = new TicketAdapter(this);

        head.setAlpha(0.0f);
        StatusBarCompat.setStatusBarColor(this, Color.BLACK);
        View view1 = findViewById(R.id.rl_ticket);
        View view2 = findViewById(R.id.tv2);
        View view3 = findViewById(R.id.tv3);
        View view4 = findViewById(R.id.tv4);

        List<String> names = new ArrayList<>();
        names.add("门票");
        names.add("景区简介");
        names.add("预定须知");
        names.add("温馨提示");

        List<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        mTab.setScrollView(mSv,this,names,views);
        mTab2.setScrollView(mSv,mTab,names,views);

        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(BaseApplication.getContext());
        layoutManager.setScrollEnabled(false);
        rlTicket.setLayoutManager(layoutManager);
        rlTicket.setAdapter(ticketAdapter);

        initNearResources();//初始化附近资源

    }

    private void initNearResources() {
        NearResourcesAdapter nearResourcesAdapter = new NearResourcesAdapter();
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(MyApplication.getContext());
        linearLayoutManager.setScrollEnabled(false);
        rlNearResources.setLayoutManager(linearLayoutManager);
        rlNearResources.setAdapter(nearResourcesAdapter);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        setVisibleAndGone();
        int sumY = 0;
        ArgbEvaluator evaluator = new ArgbEvaluator();
        float duration = 200.0f;
        sumY += scrollY;
        float f = 0.0f;
        if (sumY <= 0) {
            f = 0.0f;
        } else if (sumY > 200) {
            f = 1.0f;
        } else {
            f = sumY / duration;
        }
        head.setAlpha(f);
    }

    private void setVisibleAndGone() {
        mTab2.getLocationOnScreen(mTabMiddleLocation);
        mTab.getLocationOnScreen(mTabTopLocation);
        if (mTabMiddleLocation[1] <= mTabTopLocation[1]) {
            mTab.setVisibility(View.VISIBLE);
            mTab2.setVisibility(View.INVISIBLE);
        } else {
            mTab.setVisibility(View.INVISIBLE);
            mTab2.setVisibility(View.VISIBLE);
        }
    }
}
