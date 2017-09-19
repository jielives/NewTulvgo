package com.yg.common.base;


public class RequestBean<T>  {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
