package com.yg.common.base;

import android.content.Context;

import com.yg.common.network.ApiService;
import com.yg.common.network.HttpManager;
import com.yg.common.network.rx.RxManager;

import org.json.JSONException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shenjie on 2017/9/15.
 * 业务处理基类
 */

public abstract class  BasePresenter<T,E> {
    public ApiService apiService;
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();
    public void setVM(T v,E m){
        this.mView = v;
        this.mModel = m;
        this.start();
    }
    public void BasePresenter(){
        apiService = HttpManager.getInstance(BaseApplication.getContext()).create(ApiService.class);
    }
    public void start(){}
    public void onDestory(){
        mRxManage.clear();
    }
    protected abstract void parserData(String json) throws JSONException;
}
