package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class WcfPriceType extends Enum<WcfPriceType> {
    private static final  EnumEntries ENTRIES;
    private static final  WcfPriceType[] VALUES;
    private int type;
    public static final WcfPriceType DEFINE_PURCHASE_PRICE = new WcfPriceType("DEFINE_PURCHASE_PRICE", 0, 7);
    public static final WcfPriceType DEFINE_SALES_PRICE = new WcfPriceType("DEFINE_SALES_PRICE", 1, 8);
    public static final WcfPriceType LAST_PURCHASE_CUSTOMER = new WcfPriceType("LAST_PURCHASE_CUSTOMER", 2, 10);
    public static final WcfPriceType LAST_PURCHASE_ALL_CUSTOMER = new WcfPriceType("LAST_PURCHASE_ALL_CUSTOMER", 3, 11);
    public static final WcfPriceType LAST_SALES_CUSTOMER = new WcfPriceType("LAST_SALES_CUSTOMER", 4, 12);
    public static final WcfPriceType LAST_SALES_ALL_CUSTOMER = new WcfPriceType("LAST_SALES_ALL_CUSTOMER", 5, 13);
    public static final Companion Companion = new Companion(null);

    private static WcfPriceType[] values() {
        return new WcfPriceType[]{DEFINE_PURCHASE_PRICE, DEFINE_SALES_PRICE, LAST_PURCHASE_CUSTOMER, LAST_PURCHASE_ALL_CUSTOMER, LAST_SALES_CUSTOMER, LAST_SALES_ALL_CUSTOMER};
    }

    public static EnumEntries<WcfPriceType> getEntries() {
        return ENTRIES;
    }

    public static WcfPriceType valueOf(String str) {
        return Enum.valueOf(WcfPriceType.class, str);
    }

    public static WcfPriceType[] values() {
        return VALUES.clone();
    }

    private WcfPriceType(String str, int i, int i2) {
        super(str,i);
        this.type = i2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    static {
        WcfPriceType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public WcfPriceType fromWcfPriceType(int i) {
            for (WcfPriceType wcfPriceType : WcfPriceType.getEntries()) {
                if (wcfPriceType.getType() == i) {
                    return wcfPriceType;
                }
            }
            return WcfPriceType.DEFINE_SALES_PRICE;
        }
    }
}
