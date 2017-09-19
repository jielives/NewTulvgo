package com.yg.common.base;

import java.io.Serializable;

/**
 * Created by shenjie on 2017/9/15.
 * 封装服务器返回的数据
 */

public class BaseResponse <T> implements Serializable {
    public String code;
    public String msg;
    public T data;
    public boolean success(){
        return "1".equals(code);
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ",msg='" + msg + '\'' +
                ",data=" + data +
                '}';
     }
}
