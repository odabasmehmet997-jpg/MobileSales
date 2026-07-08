package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.enums.CommunicationType;
import com.proje.mobilesales.core.enums.ErpType;

public interface UserSettings {
    CommunicationType getCommunicationType();
    String getDatabaseName();
    ErpType getErpType();
    int getFirmNumber();
    String getServerAddress();
    boolean isDemo();
}
