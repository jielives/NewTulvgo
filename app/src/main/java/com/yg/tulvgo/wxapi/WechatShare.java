package com.yg.tulvgo.wxapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yg.common.base.BaseApplication;
import com.yg.common.network.ApiConstants;
import com.yg.common.utils.UIUtils;


/**
 * =====================
 * Author: shenjie
 * Date: 13-11-1
 * Time: 下午14:17
 * describe:微信分享
 * =====================
 */
public class WechatShare {
    private static IWXAPI wxApi;
    public WechatShare() {
//        //实例化
//        wxApi = WXAPIFactory.createWXAPI(UIUtils.getContext(),ApiConstants.WEIXIN_APP_ID);
//        wxApi.registerApp(ApiConstants.WEIXIN_APP_ID);
    }
    /**
     * 微信分享
     * (0:分享到微信好友，1：分享到微信朋友圈)
     */
    public static void wechatShare(int flag,String title,String url,String description,Bitmap bitmap){
        //实例化
        wxApi = WXAPIFactory.createWXAPI(BaseApplication.getContext(),ApiConstants.WEIXIN_APP_ID);
        wxApi.registerApp(ApiConstants.WEIXIN_APP_ID);

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = description;
        //图片资源
        //Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
//        byte[] bytes = ReduceBitMap.bitmap2Bytes(bitmap, 32);
//        Bitmap bp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        msg.setThumbImage(bp);
        msg.setThumbImage(bitmap);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag==0?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    //在需要分享的地方添加代码：   wechatShare(0);//分享到微信好友
//    wechatShare(1);//分享到微信朋友圈
}
