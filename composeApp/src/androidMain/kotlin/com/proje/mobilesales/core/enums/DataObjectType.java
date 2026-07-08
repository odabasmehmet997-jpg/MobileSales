package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

import static kotlin.enums.EnumEntriesKt.*;


public final class DataObjectType {
    private static final   EnumEntries ENTRIES;
    private static final   DataObjectType[] VALUES;
    public String tag;
    public int value;
    public static final DataObjectType ADDORDER = new DataObjectType("ADDORDER", 0, 3, "ORDER_SLIP");
    public static final DataObjectType ADDDISPATCH = new DataObjectType("ADDDISPATCH", 1, 17, "INVOICE");
    public static final DataObjectType ADDINVOICE = new DataObjectType("ADDINVOICE", 2, 19, "INVOICE");
    public static final DataObjectType ADDCASH = new DataObjectType("ADDCASH", 3, 31, "ARP_VOUCHER");
    public static final DataObjectType ADDCREDIT = new DataObjectType("ADDCREDIT", 4, 31, "ARP_VOUCHER");
    public static final DataObjectType ADDCHEQUE = new DataObjectType("ADDCHEQUE", 5, 21, "CHQPN_ROLL");
    public static final DataObjectType ADDDEED = new DataObjectType("ADDDEED", 6, 21, "CHQPN_ROLL");
    public static final DataObjectType ADDCASECASH = new DataObjectType("ADDCASECASH", 7, 29, "SD_TRANSACTION");
    public static final DataObjectType ADDCLCARD = new DataObjectType("ADDCLCARD", 8, 30, "doAccountsRP");
    public static final DataObjectType ADDREQEST = new DataObjectType("ADDREQEST", 9, 98, "doDemand");
    public static final DataObjectType ADDWHTRANSFER = new DataObjectType("ADDWHTRANSFER", 10, 1, "doMaterialSlip");

    public static  DataObjectType[] values() {
        return new DataObjectType[]{ADDORDER, ADDDISPATCH, ADDINVOICE, ADDCASH, ADDCREDIT, ADDCHEQUE, ADDDEED, ADDCASECASH, ADDCLCARD, ADDREQEST, ADDWHTRANSFER};
    }
    public static EnumEntries getEntries() {
        return ENTRIES;
    }
    public static Enum valueOf(String str) {
        return null;
    }
    private DataObjectType(String str, int r2, int r3, String str2) {

        this.value = r3;
        this.tag = str2;
    }
    static {
        DataObjectType[] dataObjectTypeArrvalues = values();
        VALUES = dataObjectTypeArrvalues;
        ENTRIES = enumEntries(dataObjectTypeArrvalues);
    }
    private static EnumEntries enumEntries(DataObjectType[] dataObjectTypeArrvalues) {
        return null;
    }
    public int ordinal() {
        return 0;
    }
}
