package com.yg.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yg.common.network.rx.RxManager;
import com.yg.common.utils.TUtil;
import com.yg.common.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shenjie on 2017/9/14.
 */

public abstract class BaseActivity<T extends BasePresenter,E extends BaseModel> extends AppCompatActivity {
    protected Unbinder mUnbinder;
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;
    private boolean isConfigChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isConfigChange = false;
        mRxManager = new RxManager();
        setContentView(setLayoutResouceId());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this,0);
        mModel = TUtil.getT(this,1);
        if(mPresenter != null){
            mPresenter.mContext = this;
        }
        this.initPresenter();
        initData();
        initView();
    }
    /** 初始化数据*/
    protected void initData(){}
    /** 初始化view*/
    protected void initView(){}
    /** 设置根布局资源id，子类必须实现*/
    protected abstract int setLayoutResouceId();
    /**简单页面无需mvp不用管此方法*/
    public abstract void initPresenter();
    /**开启浮动加载进度条*/
    protected void startProgressDialog(){
        LoadingDialog.showDialogForLoading(this);
    }
    /**开启浮动加载进度条 带提示信息*/
    protected void startProgressDialog(String msg){
        LoadingDialog.showDialogForLoading(this,msg,true);
    }
    /**停止浮动加载进度条*/
    protected void stopProgressDialog(){
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.onDestory();
        }
        if(mRxManager!=null){
            mRxManager.clear();
        }
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }
}
