package com.proje.mobilesales.core.printutil;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.enums.EnumEntries;

public final class PrintDesignReportType {
    private static final  EnumEntries ENTRIES;
    private static final   PrintDesignReportType[] VALUES;
    private int mValue;
    public static final PrintDesignReportType INVOICE = new PrintDesignReportType("INVOICE", 0, 1);
    public static final PrintDesignReportType ORDER = new PrintDesignReportType(DailyInfo.ORDER, 1, 2);
    public static final PrintDesignReportType CASH = new PrintDesignReportType(DailyInfo.CASH, 2, 3);
    public static final PrintDesignReportType CREDIT_CART = new PrintDesignReportType("CREDIT_CART", 3, 4);
    public static final PrintDesignReportType CHEQUE = new PrintDesignReportType("CHEQUE", 4, 5);
    public static final PrintDesignReportType DEED = new PrintDesignReportType("DEED", 5, 6);
    public static final PrintDesignReportType DELIVERY_NOTE = new PrintDesignReportType("DELIVERY_NOTE", 6, 7);
    public static final PrintDesignReportType DISPATCH = new PrintDesignReportType("DISPATCH", 7, 8);
    public static final PrintDesignReportType CASE_CASH = new PrintDesignReportType("CASE_CASH", 8, 10);
    public static final PrintDesignReportType WHTRANSFER = new PrintDesignReportType(DailyInfo.WHTRANSFER, 9, 11);
    private static PrintDesignReportType[] values() {
        return new PrintDesignReportType[]{INVOICE, ORDER, CASH, CREDIT_CART, CHEQUE, DEED, DELIVERY_NOTE, DISPATCH, CASE_CASH, WHTRANSFER};
    }
    public static PrintDesignReportType valueOf(String str, Class<? extends T> printDesignReportType) {
        return Enum.valueOf(PrintDesignReportType, str);
    }
    private PrintDesignReportType(String str, int i2, int i3) {
        super();
        this.mValue = i3;
    }
    public int getMValue() {
        return this.mValue;
    }
    public void setMValue(int i2) {
        this.mValue = i2;
    }
    static {
        PrintDesignReportType[] values = values();
        VALUES = values;
        ENTRIES = enumEntries(values);
    }
    private static EnumEntries enumEntries(PrintDesignReportType[] values) {
        return null;
    }
}
