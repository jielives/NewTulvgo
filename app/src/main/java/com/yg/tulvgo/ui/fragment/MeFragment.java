package com.yg.tulvgo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yg.tulvgo.BaseFragment;
import com.yg.tulvgo.R;

/**
 * Created by shenjie on 2017/9/12.
 * 个人中心
 */

public class MeFragment extends BaseFragment {


    @Override
    protected void initData(Bundle arguments) {
        String str = (String) arguments.get("str");
        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
        super.initData(arguments);
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_me;
    }
}
