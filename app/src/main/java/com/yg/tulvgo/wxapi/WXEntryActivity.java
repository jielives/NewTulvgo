package com.yg.tulvgo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yg.common.utils.LogUtils;


/**
 * 微信登录页面
 * @author kevin_chen 2016-12-10 下午19:03:45
 * @version v1.0
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private static final String APP_SECRET = "7033790f1038448aa09257641d580571";
	private IWXAPI mWeixinAPI;
	public static final String WEIXIN_APP_ID = "wx2e250cd5ad26dca3";
//	private ThreeLoginPresenter threeLoginPresenter;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		threeLoginPresenter = new ThreeLoginPresenter();

		mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, true);
		mWeixinAPI.handleIntent(this.getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		mWeixinAPI.handleIntent(intent, this);//必须调用此句话
	}

	//微信发送的请求将回调到onReq方法
	@Override
	public void onReq(BaseReq req) {
		LogUtils.i("onReq");
	}

	//发送到微信请求的响应结果
	@Override
	public void onResp(BaseResp resp) {
		LogUtils.i("onResp");
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				LogUtils.i("ERR_OK");
//				//发送成功
//				SendAuth.Resp sendResp = (SendAuth.Resp) resp;
//				if (sendResp != null) {
//					String code = sendResp.code;
//					getAccess_token(code);
//				}
				finish();
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				LogUtils.i("ERR_USER_CANCEL");
				//发送取消
				finish();
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				LogUtils.i("ERR_AUTH_DENIED");
				//发送被拒绝
				finish();
				break;
			default:
				//发送返回
				break;
		}
	}
	/**
	 * 获取openid accessToken值用于后期操作
	 * @param code 请求码
	 */
	private void getAccess_token(final String code) {
		String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ WEIXIN_APP_ID
				+ "&secret="
				+ APP_SECRET
				+ "&code="
				+ code
				+ "&grant_type=authorization_code";
		LogUtils.i("getAccess_token：" + path);
//		//网络请求
//		OkHttp3Utils.getInstance(MyApplication.getContext()).doGetNoHearsParms(path).enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				finish();
//			}
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				String json = response.body().string();
//				LogUtils.d("CESHI", json);
//				try {
//					JSONObject jsonObject = new JSONObject(json);
//					String access_token = jsonObject.getString("access_token");
//					String openid = jsonObject.getString("unionid");
//					getUserInfor(access_token, openid);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * 获取微信的个人信息
	 * @param access_token
	 * @param openid
	 */
	private void getUserInfor(final String access_token, final String openid) {
		String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
				+ access_token
				+ "&openid="
				+ openid;
		LogUtils.i("getUserMesg：" + path);
		//网络请求，根据自己的请求方式
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid;
//		OkHttp3Utils.getInstance(MyApplication.getContext()).doGetNoHearsParms(url).enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				finish();
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				String json = response.body().string();
//				Gson gson = new Gson();
//				WXLoginBean WXLoginBean = gson.fromJson(json, WXLoginBean.class);
//				//第三方的登录时请求自己的服务器
//				//OkHttp3Utils.getInstance(MyApplication.getContext()).doGet()
//				threeLoginPresenter.getData(WXLoginBean);
//				//Util.thirdPartyLogin(WXEntryActivity.this, progress, openid, 2, WXLoginBean.getNickname(), WXLoginBean.getHeadimgurl());
//			}
//		});
	}
}