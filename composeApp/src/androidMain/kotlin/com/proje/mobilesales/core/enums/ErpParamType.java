package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class ErpParamType {
    private static final  EnumEntries ENTRIES;
    private static final  ErpParamType[] VALUES;
    private int mValue;
    public static final ErpParamType FIN_COLLRISKSALORD = new ErpParamType("FIN_COLLRISKSALORD", 0, 10469);
    public static final ErpParamType FIN_COLLRISKSALDSP = new ErpParamType("FIN_COLLRISKSALDSP", 1, 10470);
    public static final ErpParamType FIN_COLLRISKSALINV = new ErpParamType("FIN_COLLRISKSALINV", 2, 10471);
    public static final ErpParamType FIN_COLLRISKBNKTR = new ErpParamType("FIN_COLLRISKBNKTR", 3, 10472);
    public static final ErpParamType FIN_COLLRISKCSHTR = new ErpParamType("FIN_COLLRISKCSHTR", 4, 10473);
    public static final ErpParamType FIN_COLLRISK = new ErpParamType("FIN_COLLRISK", 5, 10474);
    public static final ErpParamType FIN_COLLRISKCLITR = new ErpParamType("FIN_COLLRISKCLITR", 6, 10475);
    public static final ErpParamType SALES_EDITLINESRCIDX = new ErpParamType("SALES_EDITLINESRCIDX", 7, 10476);
    public static final ErpParamType DEMMGMT_EDITLINESRCIDX = new ErpParamType("DEMMGMT_EDITLINESRCIDX", 8, 10477);
    public static final ErpParamType DEMMGMT_DEMFICHESTAT = new ErpParamType("DEMMGMT_DEMFICHESTAT", 9, 10483);
    public static final ErpParamType SALES_SINTPAYMENTTYP = new ErpParamType("SALES_SINTPAYMENTTYP", 10, 10478);
    public static final ErpParamType SALES_SPROMPRICECTRL = new ErpParamType("SALES_SPROMPRICECTRL", 11, 10479);
    public static final ErpParamType SALES_UPDPRCCURRFORTR = new ErpParamType("SALES_UPDPRCCURRFORTR", 12, 10480);
    public static final ErpParamType SALES_UPDTRNCURRFORTR = new ErpParamType("SALES_UPDTRNCURRFORTR", 13, 10481);
    public static final ErpParamType SALES_ASSIGNDUEDATE = new ErpParamType("SALES_ASSIGNDUEDATE", 14, 10482);
    public static final ErpParamType SALES_SALEPURCHPRICEINV = new ErpParamType("SALES_SALEPURCHPRICEINV", 15, 10484);
    public static final ErpParamType SALES_SALEPURCHPRICEDSP = new ErpParamType("SALES_SALEPURCHPRICEDSP", 16, 10485);
    public static final ErpParamType SALES_SALEPURCHPRCCNTRL = new ErpParamType("SALES_SALEPURCHPRCCNTRL", 17, 10486);
    public static final ErpParamType SALES_SALEPURCHPRCDEFINV = new ErpParamType("SALES_SALEPURCHPRCDEFINV", 18, 10487);
    public static final ErpParamType SALES_SALEPURCHPRCDEFDSP = new ErpParamType("SALES_SALEPURCHPRCDEFDSP", 19, 10488);
    public static final ErpParamType SALES_SALEPURCHPRCDEFORD = new ErpParamType("SALES_SALEPURCHPRCDEFORD", 20, 10489);
    public static final ErpParamType SALES_CANTCALCDEDUCT = new ErpParamType("SALES_CANTCALCDEDUCT", 21, 10490);
    public static final ErpParamType SALES_ORDPAYTYPE = new ErpParamType("SALES_ORDPAYTYPE", 22, 10491);

    private static ErpParamType[] values() {
        return new ErpParamType[]{FIN_COLLRISKSALORD, FIN_COLLRISKSALDSP, FIN_COLLRISKSALINV, FIN_COLLRISKBNKTR, FIN_COLLRISKCSHTR, FIN_COLLRISK, FIN_COLLRISKCLITR, SALES_EDITLINESRCIDX, DEMMGMT_EDITLINESRCIDX, DEMMGMT_DEMFICHESTAT, SALES_SINTPAYMENTTYP, SALES_SPROMPRICECTRL, SALES_UPDPRCCURRFORTR, SALES_UPDTRNCURRFORTR, SALES_ASSIGNDUEDATE, SALES_SALEPURCHPRICEINV, SALES_SALEPURCHPRICEDSP, SALES_SALEPURCHPRCCNTRL, SALES_SALEPURCHPRCDEFINV, SALES_SALEPURCHPRCDEFDSP, SALES_SALEPURCHPRCDEFORD, SALES_CANTCALCDEDUCT, SALES_ORDPAYTYPE};
    }

    public static EnumEntries<ErpParamType> getEntries() {
        return ENTRIES;
    }

    public static ErpParamType valueOf(String str) {
        return Enum.valueOf(ErpParamType.class, str);
    }

    public static ErpParamType[] values() {
        return VALUES.clone();
    }

    private ErpParamType(String str, int i2, int i3) {
        this.mValue = i3;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        ErpParamType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public int getmValue() {
        return this.mValue;
    }

    public void setmValue(int i2) {
        this.mValue = i2;
    }
}
