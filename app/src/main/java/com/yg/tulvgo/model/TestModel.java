package com.yg.tulvgo.model;

import com.yg.common.base.BaseApplication;
import com.yg.common.network.HttpManager;
import com.yg.common.network.rx.RxSchedulers;
import com.yg.tulvgo.contract.TestContract;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by shenjie on 2017/9/15.
 *
 */

public class TestModel implements TestContract.Model {

    @Override
    public Observable<ResponseBody> getTestData() {
        return HttpManager.getInstance(BaseApplication.getContext())
                .getDefault()
                .getVersionInfo()
                .compose(RxSchedulers.<ResponseBody>io_main());
    }
}
