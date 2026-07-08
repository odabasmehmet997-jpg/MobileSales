package com.proje.mobilesales.features.dbmodel;


public class Country {
    private String countryCode;
    private String countryName;
    private String countryRef;
    public String getCountryRef() {
        return countryRef;
    }
    public void setCountryRef(final String str) {
        countryRef = str;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(final String str) {
        countryCode = str;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(final String str) {
        countryName = str;
    }
}
