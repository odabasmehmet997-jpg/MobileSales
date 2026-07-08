package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerReportNames extends Enum<CustomerReportNames> {
    private static final  EnumEntries ENTRIES;
    private static final  CustomerReportNames[] VALUES;
    private int mResId;
    private String value;
    public static final CustomerReportNames ACCOUNT_EXTRACT_REPORT = new CustomerReportNames("ACCOUNT_EXTRACT_REPORT", 0, BuildConfig.NETSIS_DEMO_PASSWORD, R.string.str_account_extract);
    public static final CustomerReportNames DEBT_TACKING_REPORT = new CustomerReportNames("DEBT_TACKING_REPORT", 1, ExifInterface.GPS_MEASUREMENT_2D, R.string.str_debt_tracking_report);
    public static final CustomerReportNames DETAILED_SLIP_LIST_REPORT = new CustomerReportNames("DETAILED_SLIP_LIST_REPORT", 2, ExifInterface.GPS_MEASUREMENT_3D, R.string.str_detailed_slip_list);
    public static final CustomerReportNames ORDER_REPORT = new CustomerReportNames("ORDER_REPORT", 3, "4", R.string.str_order_report);
    public static final CustomerReportNames INVOICE_AVERAGE_EXPIRY_REPORT = new CustomerReportNames("INVOICE_AVERAGE_EXPIRY_REPORT", 4, "5", R.string.str_invoice_average_expiry);
    public static final CustomerReportNames AVERAGE_DATE_REPORT = new CustomerReportNames("AVERAGE_DATE_REPORT", 5, "6", R.string.str_average_date_report);
    public static final CustomerReportNames SALES_REPORT_ORDER = new CustomerReportNames("SALES_REPORT_ORDER", 6, "7", R.string.str_sales_report_order);
    public static final CustomerReportNames SALES_REPORT_INVOICE = new CustomerReportNames("SALES_REPORT_INVOICE", 7, "8", R.string.str_sales_report_invoice);
    public static final CustomerReportNames ORDERS_REPORT = new CustomerReportNames("ORDERS_REPORT", 8, "9", R.string.str_orders);
    public static final CustomerReportNames INVOICES_REPORT = new CustomerReportNames("INVOICES_REPORT", 9, "10", R.string.str_invoices);
    public static final CustomerReportNames REFUND_INVOICES_REPORT = new CustomerReportNames("REFUND_INVOICES_REPORT", 10, "11", R.string.str_refund_invoices);
    public static final CustomerReportNames CASH_COLLECTIONS_REPORT = new CustomerReportNames("CASH_COLLECTIONS_REPORT", 11, "12", R.string.str_cash_collections);
    public static final CustomerReportNames CREDIT_CARD_COLLECTIONS_REPORT = new CustomerReportNames("CREDIT_CARD_COLLECTIONS_REPORT", 12, "13", R.string.str_credit_cart_collections);
    public static final CustomerReportNames CASE_COLLECTIONS_REPORT = new CustomerReportNames("CASE_COLLECTIONS_REPORT", 13, "14", R.string.str_case_collections);
    public static final CustomerReportNames CHECK_COLLECTIONS_REPORT = new CustomerReportNames("CHECK_COLLECTIONS_REPORT", 14, "15", R.string.str_check_collections);
    public static final CustomerReportNames PAYROLL_COLLECTIONS_REPORT = new CustomerReportNames("PAYROLL_COLLECTIONS_REPORT", 15, "16", R.string.str_payroll_note_collections);
    public static final CustomerReportNames VEHICLE_STOCK_STATUS_REPORT = new CustomerReportNames("VEHICLE_STOCK_STATUS_REPORT", 16, "17", R.string.str_vehicle_stock_status);
    public static final CustomerReportNames SALES_SUMMARY_REPORT = new CustomerReportNames("SALES_SUMMARY_REPORT", 17, "18", R.string.str_sales_summary_report);
    public static final CustomerReportNames ORDER_TOTALS_REPORT = new CustomerReportNames("ORDER_TOTALS_REPORT", 18, "19", R.string.str_order_totals_report);

    private static CustomerReportNames[] values() {
        return new CustomerReportNames[]{ACCOUNT_EXTRACT_REPORT, DEBT_TACKING_REPORT, DETAILED_SLIP_LIST_REPORT, ORDER_REPORT, INVOICE_AVERAGE_EXPIRY_REPORT, AVERAGE_DATE_REPORT, SALES_REPORT_ORDER, SALES_REPORT_INVOICE, ORDERS_REPORT, INVOICES_REPORT, REFUND_INVOICES_REPORT, CASH_COLLECTIONS_REPORT, CREDIT_CARD_COLLECTIONS_REPORT, CASE_COLLECTIONS_REPORT, CHECK_COLLECTIONS_REPORT, PAYROLL_COLLECTIONS_REPORT, VEHICLE_STOCK_STATUS_REPORT, SALES_SUMMARY_REPORT, ORDER_TOTALS_REPORT};
    }

    public static EnumEntries<CustomerReportNames> getEntries() {
        return ENTRIES;
    }

    public static CustomerReportNames valueOf(String str) {
        return Enum.valueOf(CustomerReportNames.class, str);
    }


    private CustomerReportNames(String str,   int i2, String str2, int i3) {
        super(str,i2);
        this.value = str2;
        this.mResId = i3;
    }

    public int getMResId() {
        return this.mResId;
    }

    public String getValue() {
        return this.value;
    }

    public void setMResId(int i2) {
        this.mResId = i2;
    }

    public void setValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    static {
        CustomerReportNames[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
