package com.rolling.bean;

/**
 * @author zhangyao
 * @date 2018/8/5  01:50
 * @E-mail android_n@163.com
 * 首页-每日推荐banner
 */
public class MainBannerBean{

    /**
     * banner图片地址
     */
    String url;

    /**
     * banner详情链接
     */
    String link;

    /**
     * banner类型（户外、古着……）
     */
    Integer type;

    /**
     * 推荐作者
     */
    String username;

    /**
     * 推荐作者头像
     */
    String headUrl;

    /**
     * 推荐内容标题
     */
    String title;

    /**
     * 推荐日期
     */
    String recommendDate;

    public String getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(String recommendDate) {
        this.recommendDate = recommendDate;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
