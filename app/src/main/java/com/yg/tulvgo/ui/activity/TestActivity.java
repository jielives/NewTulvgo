package com.yg.tulvgo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.yg.common.base.BaseActivity;
import com.yg.tulvgo.MainActivity;
import com.yg.tulvgo.R;
import com.yg.tulvgo.bean.TestBean;
import com.yg.tulvgo.contract.TestContract;
import com.yg.tulvgo.model.TestModel;
import com.yg.tulvgo.presenter.TestPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shenjie on 2017/9/15.
 */

public class TestActivity extends BaseActivity<TestPresenter, TestModel> implements TestContract.View {
    @BindView(R.id.test)
    TextView test;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.activity_test;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.test)
    public void onViewClicked() {
        Intent i = new Intent(TestActivity.this, MainActivity.class);
        startActivity(i);
    }
}
