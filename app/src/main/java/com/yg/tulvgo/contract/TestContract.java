package com.yg.tulvgo.contract;

import android.view.View;

import com.yg.common.base.BaseModel;
import com.yg.common.base.BasePresenter;
import com.yg.common.base.BaseView;
import com.yg.tulvgo.bean.TestBean;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by shenjie on 2017/9/15.
 */

public interface TestContract {
    interface Model extends BaseModel{
        Observable<ResponseBody> getTestData();
    }
    interface View extends BaseView {
        void returnTest(TestBean testBean);
    }

    abstract static class Presenter extends BasePresenter<View,Model>{
        public abstract void getTestInfo();
    }
}
