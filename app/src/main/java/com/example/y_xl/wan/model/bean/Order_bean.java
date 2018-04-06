package com.example.y_xl.wan.model.bean;

import java.util.List;

/**
 * Created by Y_xl on 2018/4/5.
 */

public class Order_bean {
    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-12-18T20:49:19","orderid":3485,"price":99,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-18T21:26:23","orderid":3489,"price":99,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T08:30:50","orderid":3596,"price":548945,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T08:58:53","orderid":3598,"price":548945,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T13:08:03","orderid":3728,"price":99,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T13:52:06","orderid":3740,"price":99,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T13:54:02","orderid":3744,"price":99,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T13:56:11","orderid":3745,"price":99,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T14:09:06","orderid":3760,"price":99,"status":2,"title":"订单测试标题2775","uid":2775},{"createtime":"2017-12-20T14:10:16","orderid":3763,"price":99,"status":2,"title":"订单测试标题2775","uid":2775}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-12-18T20:49:19
         * orderid : 3485
         * price : 99
         * status : 2
         * title : 订单测试标题2775
         * uid : 2775
         */

        private String createtime;
        private int orderid;
        private int price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
