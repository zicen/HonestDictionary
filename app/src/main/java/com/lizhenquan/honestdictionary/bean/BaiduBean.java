package com.lizhenquan.honestdictionary.bean;

import java.util.List;

/**
 * Created by lizhenquan on 2017/2/13.
 */

public class BaiduBean {
    /**
     * from : en
     * to : zh
     * trans_result : [{"src":"apple","dst":"苹果"}]
     */

    private String from;
    private String to;
    /**
     * src : apple
     * dst : 苹果
     */

    private List<TransResultBean> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResultBean> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResultBean> trans_result) {
        this.trans_result = trans_result;
    }

    public static class TransResultBean {
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        @Override
        public String toString() {
            return "TransResultBean{" +
                    "src='" + src + '\'' +
                    ", dst='" + dst + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BaiduBean{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", trans_result=" + trans_result +
                '}';
    }
}
