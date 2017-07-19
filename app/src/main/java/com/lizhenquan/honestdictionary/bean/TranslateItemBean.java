package com.lizhenquan.honestdictionary.bean;

/**
 * Created by zhenquan on 2017/6/27.
 */

public class TranslateItemBean {
    private String request;
    private String result;

    public TranslateItemBean() {
    }

    public TranslateItemBean(String request, String result) {
        this.request = request;
        this.result = result;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
