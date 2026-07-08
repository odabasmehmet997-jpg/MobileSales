package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class CustomerMenuType extends Enum<CustomerMenuType> {
    private static final EnumEntries ENTRIES;
    private static final CustomerMenuType[] VALUES;
    public static final CustomerMenuType FILLREPORTEXTRACT = new CustomerMenuType("FILLREPORTEXTRACT", 0);
    public static final CustomerMenuType FILLREPORTORDER = new CustomerMenuType("FILLREPORTORDER", 1);
    public static final CustomerMenuType FILLREPORTINVOICE = new CustomerMenuType("FILLREPORTINVOICE", 2);
    public static final CustomerMenuType FILLREPORTRETURNINVOICE = new CustomerMenuType("FILLREPORTRETURNINVOICE", 3);
    public static final CustomerMenuType FILLREPORTCASH = new CustomerMenuType("FILLREPORTCASH", 4);
    public static final CustomerMenuType FILLREPORTCREDIT = new CustomerMenuType("FILLREPORTCREDIT", 5);
    public static final CustomerMenuType FILLREPORTCASHCASE = new CustomerMenuType("FILLREPORTCASHCASE", 6);
    public static final CustomerMenuType FILLREPORTCHEQUE = new CustomerMenuType("FILLREPORTCHEQUE", 7);
    public static final CustomerMenuType FILLREPORTDEED = new CustomerMenuType("FILLREPORTDEED", 8);
    public static final CustomerMenuType FILLREPORTVEHICLESTATUS = new CustomerMenuType("FILLREPORTVEHICLESTATUS", 9);
    public static final CustomerMenuType FILLREPORTSALESUMMARY = new CustomerMenuType("FILLREPORTSALESUMMARY", 10);
    public static final CustomerMenuType FILLREPORTSALESORD = new CustomerMenuType("FILLREPORTSALESORD", 11);
    public static final CustomerMenuType FILLREPORTORDERTOTAL = new CustomerMenuType("FILLREPORTORDERTOTAL", 12);
    public static final CustomerMenuType FILLREPORTINVOICEAVGTIME = new CustomerMenuType("FILLREPORTINVOICEAVGTIME", 13);
    public static final CustomerMenuType FILLREPORTAVGCALC = new CustomerMenuType("FILLREPORTAVGCALC", 14);
    public static final CustomerMenuType FILLREPORTDEBITFOLLOW = new CustomerMenuType("FILLREPORTDEBITFOLLOW", 15);
    public static final CustomerMenuType FILLREPORTSALESINV = new CustomerMenuType("FILLREPORTSALESINV", 16);
    public static final CustomerMenuType FILLREPORTCOLLECTIONSLIST = new CustomerMenuType("FILLREPORTCOLLECTIONSLIST", 17);
    public static final CustomerMenuType FILLREPORTORDERLIST = new CustomerMenuType("FILLREPORTORDERLIST", 18);
    public static final CustomerMenuType CUSTOMER_MAIN = new CustomerMenuType("CUSTOMER_MAIN", 19);
    public static final CustomerMenuType REPORTS = new CustomerMenuType("REPORTS", 20);
    public static final CustomerMenuType REPORT_PRODUCT_WAREHOUSE = new CustomerMenuType("REPORT_PRODUCT_WAREHOUSE", 21);
    public static final CustomerMenuType REPORT_PRODUCT_WAREHOUSE_EXIST = new CustomerMenuType("REPORT_PRODUCT_WAREHOUSE_EXIST", 22);
    public static final CustomerMenuType RECIPT = new CustomerMenuType("RECIPT", 23);
    public static final CustomerMenuType SALES = new CustomerMenuType("SALES", 24);
    public static final CustomerMenuType SALES_ORDER = new CustomerMenuType("SALES_ORDER", 25);
    public static final CustomerMenuType SALES_INVOICE = new CustomerMenuType("SALES_INVOICE", 26);
    public static final CustomerMenuType SALES_RETURN_INVOICE = new CustomerMenuType("SALES_RETURN_INVOICE", 27);
    public static final CustomerMenuType SALES_DISPATCH = new CustomerMenuType("SALES_DISPATCH", 28);
    public static final CustomerMenuType SALES_RETURN_DISPATCH = new CustomerMenuType("SALES_RETURN_DISPATCH", 29);
    public static final CustomerMenuType SALES_ONE_TO_ONE_CHANGE = new CustomerMenuType("SALES_ONE_TO_ONE_CHANGE", 30);
    public static final CustomerMenuType SALES_DEMAND = new CustomerMenuType("SALES_DEMAND", 31);
    public static final CustomerMenuType SALES_RETAIL_INVOICE = new CustomerMenuType("SALES_RETAIL_INVOICE", 32);
    public static final CustomerMenuType SALES_RETAIL_RETURN_INVOICE = new CustomerMenuType("SALES_RETAIL_RETURN_INVOICE", 33);
    public static final CustomerMenuType SALES_WHTRANSFER = new CustomerMenuType("SALES_WHTRANSFER", 34);

    public static CustomerMenuType[] values() {
        return new CustomerMenuType[]{FILLREPORTEXTRACT, FILLREPORTORDER, FILLREPORTINVOICE, FILLREPORTRETURNINVOICE, FILLREPORTCASH, FILLREPORTCREDIT, FILLREPORTCASHCASE, FILLREPORTCHEQUE, FILLREPORTDEED, FILLREPORTVEHICLESTATUS, FILLREPORTSALESUMMARY, FILLREPORTSALESORD, FILLREPORTORDERTOTAL, FILLREPORTINVOICEAVGTIME, FILLREPORTAVGCALC, FILLREPORTDEBITFOLLOW, FILLREPORTSALESINV, FILLREPORTCOLLECTIONSLIST, FILLREPORTORDERLIST, CUSTOMER_MAIN, REPORTS, REPORT_PRODUCT_WAREHOUSE, REPORT_PRODUCT_WAREHOUSE_EXIST, RECIPT, SALES, SALES_ORDER, SALES_INVOICE, SALES_RETURN_INVOICE, SALES_DISPATCH, SALES_RETURN_DISPATCH, SALES_ONE_TO_ONE_CHANGE, SALES_DEMAND, SALES_RETAIL_INVOICE, SALES_RETAIL_RETURN_INVOICE, SALES_WHTRANSFER};
    }

    public static EnumEntries<CustomerMenuType> getEntries() {
        return ENTRIES;
    }

    public static CustomerMenuType valueOf(String str) {
        return Enum.valueOf(CustomerMenuType.class, str);
    }

    private CustomerMenuType(String str, int i) {
        super(str,i);
    }

    static {
        CustomerMenuType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
