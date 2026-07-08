package com.proje.mobilesales.core.enums;


import kotlin.enums.EnumEntries;


public final class FDateField extends Enum<FDateField> {

    public static final FDateField CL_CARD = new FDateField("CL_CARD", 0);
    public static final FDateField CLF_LINE = new FDateField("CLF_LINE", 1);
    public static final FDateField ITEMS = new FDateField("ITEMS", 2);
    public static final FDateField SERVICES = new FDateField("SERVICES", 3);
    public static final FDateField PRICE_LIST = new FDateField("PRICE_LIST", 4);
    public static final FDateField ADD_TAX = new FDateField("ADD_TAX", 5);
    public static final FDateField PAY_PLANS = new FDateField("PAY_PLANS", 6);
    public static final FDateField SHIP_INFO = new FDateField("SHIP_INFO", 7);
    public static final FDateField BANKS = new FDateField("BANKS", 8);
    public static final FDateField BANK_ACCOUNTS = new FDateField("BANK_ACCOUNTS", 9);
    public static final FDateField ST_FICHE = new FDateField("ST_FICHE", 10);
    public static final FDateField OR_FICHE = new FDateField("OR_FICHE", 11);
    public static final FDateField DELETED_RECS = new FDateField("DELETED_RECS", 12);
    public static final FDateField CABINS = new FDateField("CABINS", 13);

    private static FDateField[] VALUES = values();
    private static EnumEntries<FDateField> ENTRIES = enumEntries(VALUES);

    private static EnumEntries<FDateField> enumEntries(FDateField[] values) {
        return null;
    }

    public static EnumEntries<FDateField> getEntries() {
        return ENTRIES;
    }

    public static FDateField valueOf(String str) {
        return Enum.valueOf(FDateField.class, str);
    }

    public static FDateField[] values() {
        return VALUES.clone();
    }

    private FDateField(String str, int i2) {
        super(str,i2);
    }

    static {
        FDateField[] values = values();
        VALUES = values;
        ENTRIES = enumEntries(values);
    }
}


