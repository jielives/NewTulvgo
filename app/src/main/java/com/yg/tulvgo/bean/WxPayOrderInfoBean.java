package com.yg.tulvgo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shenjie on 2017/9/21.
 */

public class WxPayOrderInfoBean {
    /**
     * appid : wxb4ba3c02aa476ea1
     * partnerid : 1900006771
     * package : Sign=WXPay
     * noncestr : 2713c052dd6766d379ddc78145404946
     * timestamp : 1505959119
     * prepayid : wx201709210958399e1d42e22d0036020128
     * sign : 09274B4E1D6991B22A3AC2498CBC034B
     */
    private String appid;
    private String partnerid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private int timestamp;
    private String prepayid;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
