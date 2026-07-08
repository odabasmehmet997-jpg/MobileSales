package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(appTable = EmbeddingCompat.DEBUG, name = "USERPARAMS")

public class UserParam {

    @Column(name = "PARAMNAME")
    private String paramName;

    @Column(name = "PARAMNO")
    private String paramNo;

    @Column(name = "PARAMVALUE")
    private String paramValue;

    public String getParamNo() {
        return this.paramNo;
    }

    public void setParamNo(String str) {
        this.paramNo = str;
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
