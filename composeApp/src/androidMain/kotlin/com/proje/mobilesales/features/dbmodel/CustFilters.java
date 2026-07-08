package com.proje.mobilesales.features.dbmodel;


public class CustFilters {
    private String code;
    private String filterDataJson;
    private int userId;
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public String getFilterDataJson() {
        return this.filterDataJson;
    }
    public void setFilterDataJson(String str) {
        this.filterDataJson = str;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int i2) {
        this.userId = i2;
    }
}
