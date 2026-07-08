package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class CustomerDocumentType extends Enum<CustomerDocumentType> {
    private static final  EnumEntries ENTRIES;
    private static final  CustomerDocumentType[] VALUES;
    public static final CustomerDocumentType cdtIMAGE = new CustomerDocumentType("cdtIMAGE", 0, 11, 0);
    public static final CustomerDocumentType cdtINFONOTE = new CustomerDocumentType("cdtINFONOTE", 1, 1, 3);
    public int docNumber;
    public int docType;

    public static CustomerDocumentType[] values() {
        return VALUES.clone();
    }
    public static EnumEntries<CustomerDocumentType> getEntries() {
        return ENTRIES;
    }
    public static CustomerDocumentType valueOf(String str) {
        return Enum.valueOf(CustomerDocumentType.class, str);
    }
    private CustomerDocumentType(String str, int i2, int i3, int i4) {
        super(str,i2);
        this.docNumber = i3;
        this.docType = i4;
    }
    static {
        CustomerDocumentType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
