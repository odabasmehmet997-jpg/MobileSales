package com.proje.mobilesales.features.driverinformation.model;

import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;

public final class NEDispatchInfoModel {
    private EDispatchAdditionalInfo additionalInfo;
    private String serial;

    public EDispatchAdditionalInfo getAdditionalInfo() {
        return this.additionalInfo;
    }

    public void setAdditionalInfo(EDispatchAdditionalInfo eDispatchAdditionalInfo) {
        this.additionalInfo = eDispatchAdditionalInfo;
    }

    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String str) {
        this.serial = str;
    }
}
