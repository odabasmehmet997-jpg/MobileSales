package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


public class ShipAgent {
    private String code;
    private int logicalRef;
    private String taxNr;
    private String title;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String str) {
        this.title = str;
    }
    public String getTaxNr() {
        return this.taxNr;
    }
    public void setTaxNr(String str) {
        this.taxNr = str;
    }
}
