package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.Intrinsics;

public final class AnalyticsEventType{
    private static final  EnumEntries ENTRIES = null;
    private String event;
    public static final AnalyticsEventType ORDER_APPROVAL = new AnalyticsEventType("ORDER_APPROVAL", 0, "Order Approval");
    public static final AnalyticsEventType DEMAND = new AnalyticsEventType(DailyInfo.DEMAND, 1, "Demand");
    public static final AnalyticsEventType DISTRIBUTION = new AnalyticsEventType("DISTRIBUTION", 2, "Distribution");
    public static final AnalyticsEventType CUSTOMERS = new AnalyticsEventType("CUSTOMERS", 3, "Customers");
    public static final AnalyticsEventType PRODUCTS = new AnalyticsEventType("PRODUCTS", 4, "Products");
    public static final AnalyticsEventType UTILS = new AnalyticsEventType("UTILS", 5, "Utils");
    public static final AnalyticsEventType REPORTS = new AnalyticsEventType("REPORTS", 6, "Reports");
    public static final AnalyticsEventType DATA_TRANSFER = new AnalyticsEventType("DATA_TRANSFER", 7, "Data Transfer");
    public static final AnalyticsEventType WH_TRANSFER = new AnalyticsEventType("WH_TRANSFER", 8, "Transfer");
    public static final AnalyticsEventType IMPORT_DATA = new AnalyticsEventType("IMPORT_DATA", 9, "Import Data");
    public static final AnalyticsEventType CABIN_OPERATION = new AnalyticsEventType("CABIN_OPERATION", 10, "Cabin Operations");

    private static AnalyticsEventType[] values() {
        return new AnalyticsEventType[]{AnalyticsEventType.ORDER_APPROVAL, AnalyticsEventType.DEMAND, AnalyticsEventType.DISTRIBUTION, AnalyticsEventType.CUSTOMERS, AnalyticsEventType.PRODUCTS, AnalyticsEventType.UTILS, AnalyticsEventType.REPORTS, AnalyticsEventType.DATA_TRANSFER, AnalyticsEventType.WH_TRANSFER, AnalyticsEventType.IMPORT_DATA, AnalyticsEventType.CABIN_OPERATION};
    }
    public static AnalyticsEventType valueOf() {
        return valueOf();
    }
    private AnalyticsEventType(final String str, final int i2, final String str2) {
        event = str2;
    }
    public String getEvent() {
        return event;
    }
    public void setEvent(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        event = str;
    }
    static {
        final AnalyticsEventType[] values = AnalyticsEventType.values();
    }
}
