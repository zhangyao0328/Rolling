package com.rolling.bean.location;

import java.util.List;

/**
 * @author zhangyao
 * @date 2017/12/8:11:00
 * @E-mail android_n@163.com
 */

public class CityBean {


    /**
     * parentId : 1004
     * name : ALaShanMeng
     * nameZh : 阿拉善盟
     * nameZhAbbreviation :
     * abbreviation : ALSM
     * code : 152900
     */

    private int parentId;
    private String name;
    private String nameZh;
    private String nameZhAbbreviation;
    private String abbreviation;
    private String code;
    private int viewType;
    private List<CityBean> hotCity;

    public List<CityBean> getHotCity() {
        return hotCity;
    }

    public void setHotCity(List<CityBean> hotCity) {
        this.hotCity = hotCity;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getNameZhAbbreviation() {
        return nameZhAbbreviation;
    }

    public void setNameZhAbbreviation(String nameZhAbbreviation) {
        this.nameZhAbbreviation = nameZhAbbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
