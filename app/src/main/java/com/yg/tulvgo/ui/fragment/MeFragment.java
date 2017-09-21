package com.yg.tulvgo.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yg.common.base.BaseApplication;
import com.yg.common.base.BaseFragment;
import com.yg.common.network.ApiConstants;
import com.yg.common.utils.LogUtils;
import com.yg.tulvgo.R;
import com.yg.tulvgo.wxapi.WXPayEntryActivity;
import com.yg.tulvgo.wxapi.WechatShare;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jameson.io.library.util.ToastUtils;

/**
 * Created by shenjie on 2017/9/12.
 * 个人中心
 */

public class MeFragment extends BaseFragment{
    Unbinder unbinder;

    @Override
    protected void initData(Bundle arguments) {
        String str = (String) arguments.get("str");
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
        super.initData(arguments);
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_me;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
