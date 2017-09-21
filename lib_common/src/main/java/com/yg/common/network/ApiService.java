package com.yg.common.network;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by shenjie on 2017/9/15.
 */

public interface ApiService {
    @POST("version/queryAppNewVersion")
    Observable<ResponseBody> getVersionInfo();

    @GET("http://wxpay.wxutil.com/pub_v2/app/app_pay.php")
    Observable<ResponseBody> getWxOrderInfo(

    );
}
