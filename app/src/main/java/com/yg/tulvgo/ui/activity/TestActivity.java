package com.yg.tulvgo.ui.activity;

import com.yg.common.base.BaseActivity;
import com.yg.tulvgo.R;
import com.yg.tulvgo.bean.TestBean;
import com.yg.tulvgo.contract.TestContract;
import com.yg.tulvgo.model.TestModel;
import com.yg.tulvgo.presenter.TestPresenter;

/**
 * Created by shenjie on 2017/9/15.
 */

public class TestActivity extends BaseActivity<TestPresenter,TestModel> implements TestContract.View{

    @Override
    protected int setLayoutResouceId() {
        return R.layout.activity_test;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getTestInfo();
    }

    @Override
    public void returnTest(TestBean testBean) {
        TestBean.DataBean data = testBean.getData();
        String description = data.getDescription();
    }
}
