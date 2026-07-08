package com.proje.mobilesales.features.dbmodel;

import android.text.TextUtils;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;

public class Firm {
    private String city;
    private String country;
    private String district;
    private String doorNr;
    private String emailAddress;
    private String fax;
    private String firmEmailAddress;
    private int logicalRef;
    private String name;
    private int f1240nr;
    private String officalTitle;
    private String phone1;
    private String phone2;
    private String profileId;
    private String road;
    private String street;
    private String taxNr;
    private String taxOffice;
    private String title;
    private String webAddress;
    private String zipCode;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getNr() {
        return this.f1240nr;
    }
    public void setNr(int i2) {
        this.f1240nr = i2;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String str) {
        this.name = str;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String str) {
        this.title = str;
    }
    public String getOfficalTitle() {
        return this.officalTitle;
    }
    public void setOfficalTitle(String str) {
        this.officalTitle = str;
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String str) {
        this.street = str;
    }
    public String getRoad() {
        return this.road;
    }
    public void setRoad(String str) {
        this.road = str;
    }
    public String getDoorNr() {
        return this.doorNr;
    }
    public void setDoorNr(String str) {
        this.doorNr = str;
    }
    public String getDistrict() {
        return this.district;
    }
    public void setDistrict(String str) {
        this.district = str;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String str) {
        this.city = str;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String str) {
        this.country = str;
    }
    public String getZipCode() {
        return this.zipCode;
    }
    public void setZipCode(String str) {
        this.zipCode = str;
    }
    public String getPhone1() {
        return this.phone1;
    }
    public void setPhone1(String str) {
        this.phone1 = str;
    }
    public String getPhone2() {
        return this.phone2;
    }
    public void setPhone2(String str) {
        this.phone2 = str;
    }
    public String getFax() {
        return this.fax;
    }
    public void setFax(String str) {
        this.fax = str;
    }
    public String getTaxOffice() {
        return this.taxOffice;
    }
    public void setTaxOffice(String str) {
        this.taxOffice = str;
    }
    public String getTaxNr() {
        return this.taxNr;
    }
    public void setTaxNr(String str) {
        this.taxNr = str;
    }
    public String getWebAddress() {
        return this.webAddress;
    }
    public void setWebAddress(String str) {
        this.webAddress = str;
    }
    public String getProfileId() {
        return this.profileId;
    }
    public void setProfileId(String str) {
        this.profileId = str;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }
    public void setEmailAddress(String str) {
        this.emailAddress = str;
    }
    public String getFirmEmailAddress() {
        return this.firmEmailAddress;
    }
    public void setFirmEmailAddress(String str) {
        this.firmEmailAddress = str;
    }
    public String getAddress() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(getStreet())) {
            sb.append(getStreet());
            sb.append(" ");
        }
        if (!TextUtils.isEmpty(getRoad())) {
            sb.append(getRoad());
            sb.append(" ");
        }
        if (!TextUtils.isEmpty(getDoorNr())) {
            sb.append(getDoorNr());
            sb.append(" ");
        }
        return sb.toString();
    }
}
