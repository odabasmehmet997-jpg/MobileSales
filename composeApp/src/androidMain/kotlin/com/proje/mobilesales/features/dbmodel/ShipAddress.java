package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.utils.StringUtils;
import org.kxml2.wap.Wbxml;


public class ShipAddress {

    private String addr1;
    private String addr2;
    private String address;
    private String city;
    private String clCode;
    private int clRef;
    private double cmDate;
    private String code;
    private int defaultFlag;
    private String district;
    private String latitute;
    private int logicalRef;
    private String longtitude;
    private String name;
    private String town;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getClRef() {
        return this.clRef;
    }
    public void setClRef(int i2) {
        this.clRef = i2;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String str) {
        this.city = str;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String str) {
        this.address = str;
    }
    public String getAddr1() {
        return this.addr1;
    }
    public void setAddr1(String str) {
        this.addr1 = str;
    }
    public String getAddr2() {
        return this.addr2;
    }
    public void setAddr2(String str) {
        this.addr2 = str;
    }
    public String getDistrict() {
        return this.district;
    }
    public void setDistrict(String str) {
        this.district = str;
    }
    public String getLatitute() {
        return this.latitute;
    }
    public void setLatitute(String str) {
        this.latitute = str;
    }
    public String getLongtitude() {
        return this.longtitude;
    }
    public void setLongtitude(String str) {
        this.longtitude = str;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String str) {
        this.name = str;
    }
    public double getCmDate() {
        return this.cmDate;
    }
    public void setCmDate(double d2) {
        this.cmDate = d2;
    }
    public String getTown() {
        return this.town;
    }
    public void setTown(String str) {
        this.town = str;
    }
    public String getFullAddress() {
        return StringUtils.catStringNewLine(getAddr1(), getAddr2(), getTown(), getCity());
    }
    public String toString() {
        return StringUtils.catString(getCode(), getName(), getAddress(), ErpCreator.getInstance().getmBaseErp().getErpType() == ErpType.NETSIS ? getDistrict() : getCity());
    }
    public String getClCode() {
        return this.clCode;
    }
    public void setClCode(String str) {
        this.clCode = str;
    }
    public int getDefaultFlag() {
        return this.defaultFlag;
    }
    public void setDefaultFlag(int i2) {
        this.defaultFlag = i2;
    }
}
