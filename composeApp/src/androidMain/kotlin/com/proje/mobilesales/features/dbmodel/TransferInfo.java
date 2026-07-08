package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


public class TransferInfo {
    private int clRef;
    private String desc;
    private int ftype;
    private int f1247id;
    private int logicalRef;

    public int getId() {
        return this.f1247id;
    }

    public void setId(int i2) {
        this.f1247id = i2;
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getFtype() {
        return this.ftype;
    }

    public void setFtype(int i2) {
        this.ftype = i2;
    }

    public int getClRef() {
        return this.clRef;
    }

    public void setClRef(int i2) {
        this.clRef = i2;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }
}
