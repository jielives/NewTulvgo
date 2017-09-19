package com.yg.tulvgo.presenter;

import com.google.gson.Gson;
import com.yg.common.network.rx.RxSubscriber;
import com.yg.common.utils.LogUtils;
import com.yg.tulvgo.bean.TestBean;
import com.yg.tulvgo.contract.TestContract;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by shenjie on 2017/9/15.
 */

public class TestPresenter extends TestContract.Presenter{

    @Override
    public void getTestInfo() {
        mRxManage.add(mModel.getTestData().subscribe(new RxSubscriber<ResponseBody>(mContext,false) {

            @Override
            protected void _onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    parserData(string);
                    LogUtils.i("");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected void _onError(String message) {

            }
        }));
    }

    @Override
    protected void parserData(String json) throws JSONException {
        Gson gson = new Gson();
        TestBean testBean = gson.fromJson(json, TestBean.class);
        mView.returnTest(testBean);
    }
}
