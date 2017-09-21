package com.yg.tulvgo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yg.common.network.ApiConstants;
import com.yg.common.utils.LogUtils;

import jameson.io.library.util.ToastUtils;

/**
 * Created by shenjie on 2017/9/20.
 * 微信支付
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mWeixinAPI;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, ApiConstants.WEIXIN_APP_ID);
        mWeixinAPI.handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent,this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d("wechat",resp.toString());
        String msg = "";

        if(resp.errCode == 0){
            msg = "支付成功";
        }
        else if(resp.errCode == -1){
            msg = "已取消支付";
        }
        else if(resp.errCode == -2){
            msg = "支付失败";
        }
        ToastUtils.show(WXPayEntryActivity.this,msg);
        //关闭窗口
        finish();
    }
}
