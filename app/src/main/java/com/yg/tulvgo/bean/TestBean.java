package com.yg.tulvgo.bean;

import java.io.Serializable;

/**
 * Created by shenjie on 2017/9/15.
 */

public class TestBean implements Serializable {
    /**
     * code : success
     * data : {"createTime":"2017-09-05 13:07","description":"这是第二版apk","downLink":"http://lj-shanghai.oss-cn-shanghai.aliyuncs.com/pudong/sanlin/emergency/apk/fcecs/ae403158-a817-4091-ad0d-71eef4a80ad1/ae28202c-8f53-48a9-8512-dc74e4339264.apk","externalNo":"2.0.1","id":"904895282326396928","internalNo":"2","type":"Android"}
     * message : The request is successful
     */
    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * createTime : 2017-09-05 13:07
         * description : 这是第二版apk
         * downLink : http://lj-shanghai.oss-cn-shanghai.aliyuncs.com/pudong/sanlin/emergency/apk/fcecs/ae403158-a817-4091-ad0d-71eef4a80ad1/ae28202c-8f53-48a9-8512-dc74e4339264.apk
         * externalNo : 2.0.1
         * id : 904895282326396928
         * internalNo : 2
         * type : Android
         */

        private String createTime;
        private String description;
        private String downLink;
        private String externalNo;
        private String id;
        private String internalNo;
        private String type;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDownLink() {
            return downLink;
        }

        public void setDownLink(String downLink) {
            this.downLink = downLink;
        }

        public String getExternalNo() {
            return externalNo;
        }

        public void setExternalNo(String externalNo) {
            this.externalNo = externalNo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInternalNo() {
            return internalNo;
        }

        public void setInternalNo(String internalNo) {
            this.internalNo = internalNo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
