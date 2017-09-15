package com.yg.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by shenjie on 2017/9/14.
 */

public abstract class BaseFragment extends Fragment {
    protected Unbinder mUnbinder;
    /**宿主activity*/
    protected Activity mContext;
    /**根view*/
    protected View mRootView;
    /**是否对用户可见*/
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResouceId(),null);
        mUnbinder = ButterKnife.bind(this, mRootView);
        initData(getArguments());
        initView();
        mIsPrepare = true;
        onLazyLoad();
        setListener();
        return mRootView;
    }

    /** 初始化数据*/
    protected void initData(Bundle arguments) {}
    /** 初始化view*/
    protected void initView() {}
    /** 设置监听事件*/
    protected void setListener() {}
    /** 懒加载，仅当用户可见切view初始化结束后才会执行*/
    protected void onLazyLoad() {}
    /** 设置根布局资源id，子类必须实现*/
    protected abstract int setLayoutResouceId();
    /** 表示是否与用户可交互，在onCreateView之前执行
     * isVisibleToUser  ture 可见  false 不可见
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser)
        {
            onVisibleToUser();
        }
    }
    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser()
    {
        if (mIsPrepare && mIsVisible)
        {
            onLazyLoad();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }
}