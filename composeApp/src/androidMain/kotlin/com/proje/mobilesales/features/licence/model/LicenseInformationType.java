package com.proje.mobilesales.features.licence.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class LicenseInformationType extends Enum<LicenseInformationType> {
    private static final EnumEntries ENTRIES;
    private static final LicenseInformationType[] VALUES;
 
    private int f1252id;
    public static final LicenseInformationType PRODUCT_EXPIRY_DATE = new LicenseInformationType("PRODUCT_EXPIRY_DATE", 0, 4800);
    public static final LicenseInformationType WARNING_DAYS = new LicenseInformationType("WARNING_DAYS", 1, 4496);
    public static final LicenseInformationType TOLERANCE_DAYS = new LicenseInformationType("TOLERANCE_DAYS", 2, 4497);
    public static final LicenseInformationType LICENSE_RENEWAL_DATE = new LicenseInformationType("LICENSE_RENEWAL_DATE", 3, 4499);
    public static final LicenseInformationType LICENSE_KEY = new LicenseInformationType("LICENSE_KEY", 4, 1000);

    public static LicenseInformationType[] values() {
        return new LicenseInformationType[]{PRODUCT_EXPIRY_DATE, WARNING_DAYS, TOLERANCE_DAYS, LICENSE_RENEWAL_DATE, LICENSE_KEY};
    }

    public static EnumEntries<LicenseInformationType> getEntries() {
        return ENTRIES;
    }

    public static LicenseInformationType valueOf(String str) {
        return Enum.valueOf(LicenseInformationType.class, str);
    }



    private LicenseInformationType(String str, int i2, int i3) {
        super(str,i2);
        this.f1252id = i3;
    }

    public  int getId() {
        return this.f1252id;
    }

    public  void setId(int i2) {
        this.f1252id = i2;
    }

    static {
        LicenseInformationType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

}
