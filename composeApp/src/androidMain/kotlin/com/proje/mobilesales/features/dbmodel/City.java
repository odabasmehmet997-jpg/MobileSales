package com.proje.mobilesales.features.dbmodel;

public final class City {

    private String cityCode;
    private String cityName;
    private String countryCode;
    private String logicalRef;
    public String getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(String str) {
        this.logicalRef = str;
    }
    public String getCountryCode() {
        return this.countryCode;
    }
    public void setCountryCode(String str) {
        this.countryCode = str;
    }
    public String getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(String str) {
        this.cityCode = str;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String str) {
        this.cityName = str;
    }
}
