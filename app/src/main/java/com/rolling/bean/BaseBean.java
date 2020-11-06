package com.rolling.bean;

import java.util.Map;

/**
 * @author zhangyao
 * @date 2019-07-28  18:01
 * @E-mail android_n@163.com
 */
public class BaseBean {
    public BaseBean(){

    }

    public BaseBean(String url, String[] key, String[] value, int tag, boolean isDialog){
        setUrl(url);
        setKey(key);
        setValue(value);
        setTag(tag);
        setDoalog(isDialog);
    }

    public BaseBean(String url, Map<String, String> params, int tag, boolean isDialog, String method){
        setUrl(url);
        setParams(params);
        setTag(tag);
        setDoalog(isDialog);
        setMethod(method);
    }

    public BaseBean(String url, Map<String, String> params, int tag, boolean isDialog){
        setUrl(url);
        setParams(params);
        setTag(tag);
        setDoalog(isDialog);
    }

    public BaseBean(String url, String strJson, int tag, boolean isDialog, String method){
        setUrl(url);
        setStrJson(strJson);
        setTag(tag);
        setDoalog(isDialog);
        setMethod(method);
    }

    public BaseBean(String url, Map<String, String> params, int tag, boolean isDialog, String method, String fileKey, String fileValue){
        setUrl(url);
        setParams(params);
        setTag(tag);
        setDoalog(isDialog);
        setMethod(method);
        setFileKey(fileKey);
        setFileValue(fileValue);
    }

    String url;

    String[] key;

    String[] value;

    String strJson;

    int tag;

    boolean isDoalog;

    Map<String, String> params;

    String method;

    /**
     * 图片文件上传key
     */
    String fileKey;

    /**
     * 图片文件上传value
     */
    String fileValue;

    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStrJson() {
        return strJson;
    }

    public void setStrJson(String strJson) {
        this.strJson = strJson;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getKey() {
        return key;
    }

    public void setKey(String[] key) {
        this.key = key;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public boolean isDoalog() {
        return isDoalog;
    }

    public void setDoalog(boolean doalog) {
        isDoalog = doalog;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
