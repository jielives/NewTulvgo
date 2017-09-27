package com.yg.common.base;

/**
 * Created by shenjie on 2017/9/22.
 * 客户端请求服务器Bean基类
 */
public class BaseRequest<T>  {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
