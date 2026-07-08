package com.proje.mobilesales.features.dbmodel;

public final class Town {
    private String cityCode;
    private String countryCode;
    private String townCode;
    private String townName;
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
    public String getTownCode() {
        return this.townCode;
    }
    public void setTownCode(String str) {
        this.townCode = str;
    }
    public String getTownName() {
        return this.townName;
    }
    public void setTownName(String str) {
        this.townName = str;
    }
}
