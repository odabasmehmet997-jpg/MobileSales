package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class ProductListShowType extends Enum<ProductListShowType> {
    private static final   EnumEntries ENTRIES;
    private static final   ProductListShowType[] VALUES;
    public static final ProductListShowType ALL = new ProductListShowType("ALL", 0);
    public static final ProductListShowType PRODUCT = new ProductListShowType("PRODUCT", 1);
    public static final ProductListShowType SERVICE = new ProductListShowType("SERVICE", 2);
    public ProductListShowType(String all, int i) {
        super(all,i);
    }
    private static ProductListShowType[] values() {
        return new ProductListShowType[]{ALL, PRODUCT, SERVICE};
    }
    public static EnumEntries<ProductListShowType> getEntries() {
        return ENTRIES;
    }
    public static ProductListShowType valueOf(String str) {
        return   Enum.valueOf(ProductListShowType.class, str);
    }
    static {
        ProductListShowType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
