package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.annotation.Tables;
@Tables(alterVersion = 155, name = "WORTABLES")

public class WorTables {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
