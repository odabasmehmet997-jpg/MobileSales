package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(name = "USERRESTPARAM")

public class UserRestParam {

    @Column(name = "PARAMNAME")
    private String paramName;

    @Column(name = "PARAMNO")
    private int paramNo;

    @Column(name = "PARAMVALUE")
    private String paramValue;

    public int getParamNo() {
        return this.paramNo;
    }

    public void setParamNo(int i2) {
        this.paramNo = i2;
    }

    public String getParamValue() {
        return this.paramValue;
    }

    public void setParamValue(String str) {
        this.paramValue = str;
    }

    public String getParamName() {
        return this.paramName;
    }

    public void setParamName(String str) {
        this.paramName = str;
    }
}
