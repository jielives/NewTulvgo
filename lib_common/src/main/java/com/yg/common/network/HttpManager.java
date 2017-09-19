package com.yg.common.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yg.common.base.BaseApplication;
import com.yg.common.utils.NetWorkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shenjie on 2017/9/15.
 */

public class HttpManager {
    /**读取超时时长*/
    private static final int READ_TIME_OUT = 7676;
    /**连接时长*/
    private static final int CONNECT_TIME_OUT = 7676;
    /**连写操作时长*/
    private static final int WRITE_TIME_OUT = 7676;
    private Retrofit mRetrofit;
    public ApiService apiService;
    protected OkHttpClient mOkHttpClient;
    public volatile static HttpManager mInstance;
    /**设置缓存有效期为两天*/
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询网络的Cache-Control设置
     * */
    private static final String CACHE_CONTROL_CACHE="only-if-cached,max-stale="+CACHE_STALE_SEC;
    private static final String CACHE_CONTROL_AGE = "max-age=0";
    private HttpManager(Context context) {
        /**缓存*/
        File cacheFile = new File(BaseApplication.getContext().getCacheDir(),"cache");
        Cache cache = new Cache(cacheFile,1024*1024*100);//100mb
//        /**增加头部信息*/
//        Interceptor headerInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request build = chain.request().newBuilder()
//                        .addHeader("Content-Type", "application/json")
//                        .build();
//                return chain.proceed(build);
//            }
//        };
        // 创建 OKHttpClient .addInterceptor(headerInterceptor)
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT,TimeUnit.MINUTES)
                .writeTimeout(WRITE_TIME_OUT,TimeUnit.MINUTES)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MINUTES)
                .cache(cache)
                .build();

//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.MINUTES);//连接超时时间
//        builder.writeTimeout(WRITE_TIME_OUT,TimeUnit.MINUTES);//写操作 超时时间
//        builder.readTimeout(READ_TIME_OUT,TimeUnit.MINUTES);//读操作超时时间
//        String token = SharedPreferencesUtil.getString("token", null);
//        // 添加公共参数拦截器
//        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
//                .addHeaderParams("token",token)
//                .build();
//        builder.addInterceptor(commonInterceptor);


        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiConfig.BASE_URL)
                .build();
        apiService = mRetrofit.create(ApiService.class);

    }

    public  ApiService getDefault(){
        return apiService;
    }


    //单例获取HttpManager,双重if检验
    public static HttpManager getInstance(Context context) {
        HttpManager temp = mInstance;
        if (temp == null) {
            synchronized (HttpManager.class) {
                temp = mInstance;
                if (temp == null) {
                    temp = new HttpManager(context);
                    mInstance = temp;
                }
            }
        }
        return temp;
    }

    /**根据网络状况获取缓存策略*/
    @NonNull
    public static String getCacheControl(){
        return NetWorkUtils.isNetConnected(BaseApplication.getContext()) ? CACHE_CONTROL_AGE:CACHE_CONTROL_CACHE;
    }

    /**配置缓存策略*/
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request =  chain.request();
            String cacheControl = request.cacheControl().toString();
            if(!NetWorkUtils.isNetConnected(BaseApplication.getContext())){
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK:CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if(NetWorkUtils.isNetConnected(BaseApplication.getContext())){
                /**有网络时读取接口上的@Headers里的配置，在次可统一配置*/
                return originalResponse.newBuilder()
                        .header("Cache-Control",cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }else{
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
    /**
     *
     * @param observable
     * @param <T>
     * @return
     * 创建Observable公共的抽取
     */
    protected <T> Observable<T> observe(Observable<T> observable){
        return  observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
     }
}
