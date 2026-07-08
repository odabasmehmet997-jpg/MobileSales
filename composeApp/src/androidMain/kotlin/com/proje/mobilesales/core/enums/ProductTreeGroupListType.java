package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class ProductTreeGroupListType extends Enum<ProductTreeGroupListType> {
    private static final   EnumEntries ENTRIES;
    private static final  ProductTreeGroupListType[] VALUES;
    public static final ProductTreeGroupListType All = new ProductTreeGroupListType("All", 0);
    public static final ProductTreeGroupListType Others = new ProductTreeGroupListType("Others", 1);
    public static ProductTreeGroupListType[] values() {
        return new ProductTreeGroupListType[]{All, Others};
    }
    public static EnumEntries<ProductTreeGroupListType> getEntries() {
        return ENTRIES;
    }
    public static ProductTreeGroupListType valueOf(String str) {
        return Enum.valueOf(ProductTreeGroupListType.class, str);
    }
    private ProductTreeGroupListType(String str, int i2) {
        super(str,i2);
    }
    static {
        ProductTreeGroupListType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
    public int ordinal() {
        return 0;
    }
}
