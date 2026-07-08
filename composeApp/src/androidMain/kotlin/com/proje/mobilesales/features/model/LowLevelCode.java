package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.annotation.Column;

public class LowLevelCode {

    @Column(name = "LOGICALREF")
    String lowLevelCode;

    public String getLowLevelCode() {
        return lowLevelCode;
    }

    public void setLowLevelCode(final String str) {
        lowLevelCode = str;
    }
}
