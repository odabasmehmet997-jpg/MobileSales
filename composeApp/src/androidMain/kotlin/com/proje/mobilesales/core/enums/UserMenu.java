package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class UserMenu extends Enum<UserMenu> {
    private static final EnumEntries ENTRIES;
    private static final UserMenu[] VALUES;
    private boolean isPro;
    private int resId;
    private int tigerId;
    public static final UserMenu ORDER = new UserMenu(DailyInfo.ORDER, 0, 0, false, 0);
    public static final UserMenu INVOICE = new UserMenu("INVOICE", 1, 0, true, 1);
    public static final UserMenu DISPATCH = new UserMenu("DISPATCH", 2, 0, true, 2);
    public static final UserMenu CASH = new UserMenu(DailyInfo.CASH, 3, 0, false, 3);
    public static final UserMenu CASE = new UserMenu(DailyInfo.CASE, 4, 0, false, 4);
    public static final UserMenu CREDIT_CARD = new UserMenu("CREDIT_CARD", 5, 0, false, 5);
    public static final UserMenu CHEQUE = new UserMenu("CHEQUE", 6, 0, false, 6);
    public static final UserMenu DEED = new UserMenu("DEED", 7, 0, false, 7);
    public static final UserMenu VISIT = new UserMenu(DailyInfo.VISIT, 8, 0, false, 8);
    public static final UserMenu REPORT = new UserMenu("REPORT", 9, 0, false, 9);
    public static final UserMenu TODO = new UserMenu("TODO", 10, 0, true, 10);
    public static final UserMenu MARKETING_MESSAGE = new UserMenu("MARKETING_MESSAGE", 11, 0, true, 11);
    public static final UserMenu GPS_LOCATION_INFO = new UserMenu("GPS_LOCATION_INFO", 12, 0, true, 12);
    public static final UserMenu INVOICE_AVG_CALC = new UserMenu("INVOICE_AVG_CALC", 13, 0, false, 13);
    public static final UserMenu NEW_CUSTOMER = new UserMenu("NEW_CUSTOMER", 14, 0, false, 14);
    public static final UserMenu RETURN_INVOICE = new UserMenu("RETURN_INVOICE", 15, 0, true, 15);
    public static final UserMenu RETURN_DISPATCH = new UserMenu("RETURN_DISPATCH", 16, 0, true, 16);
    public static final UserMenu ONE_TO_ONE = new UserMenu("ONE_TO_ONE", 17, 0, true, 17);
    public static final UserMenu ORDER_CLASSIC = new UserMenu("ORDER_CLASSIC", 18, 0, false, 18);
    public static final UserMenu ORDER_FORM = new UserMenu("ORDER_FORM", 19, 0, false, 19);
    public static final UserMenu INVOICE_CLASSIC = new UserMenu("INVOICE_CLASSIC", 20, 0, true, 20);
    public static final UserMenu INVOICE_FORM = new UserMenu("INVOICE_FORM", 21, 0, true, 21);
    public static final UserMenu DISPATCH_CLASSIC = new UserMenu("DISPATCH_CLASSIC", 22, 0, true, 22);
    public static final UserMenu DISPATCH_FORM = new UserMenu("DISPATCH_FORM", 23, 0, true, 23);
    public static final UserMenu PENETRATION = new UserMenu("PENETRATION", 24, 0, true, 24);
    public static final UserMenu DIST = new UserMenu("DIST", 25, 0, false, 25);
    public static final UserMenu DEMAND = new UserMenu(DailyInfo.DEMAND, 26, 0, false, 26);
    public static final UserMenu PRODUCTS = new UserMenu("PRODUCTS", 27, 0, false, 27);
    public static final UserMenu CUSTOMERREPORT = new UserMenu("CUSTOMERREPORT", 28, 0, false, 28);
    public static final UserMenu WORK_INFO = new UserMenu("WORK_INFO", 29, 0, false, -1);

    public static UserMenu[] values() {
        return new UserMenu[]{ORDER, INVOICE, DISPATCH, CASH, CASE, CREDIT_CARD, CHEQUE, DEED, VISIT, REPORT, TODO, MARKETING_MESSAGE, GPS_LOCATION_INFO, INVOICE_AVG_CALC, NEW_CUSTOMER, RETURN_INVOICE, RETURN_DISPATCH, ONE_TO_ONE, ORDER_CLASSIC, ORDER_FORM, INVOICE_CLASSIC, INVOICE_FORM, DISPATCH_CLASSIC, DISPATCH_FORM, PENETRATION, DIST, DEMAND, PRODUCTS, CUSTOMERREPORT, WORK_INFO};
    }

    public static EnumEntries<UserMenu> getEntries() {
        return ENTRIES;
    }

    public static UserMenu valueOf(String str) {
        return Enum.valueOf(UserMenu.class, str);
    }


    private UserMenu(String str, int i2, int i3, boolean z, int i4) {
        super(str,i2);
        this.resId = i3;
        this.isPro = z;
        this.tigerId = i4;
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i2) {
        this.resId = i2;
    }

    public boolean isPro() {
        return this.isPro;
    }

    public void setPro(boolean z) {
        this.isPro = z;
    }

    public int getTigerId() {
        return this.tigerId;
    }

    public void setTigerId(int i2) {
        this.tigerId = i2;
    }

    static {
        UserMenu[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
