package com.proje.mobilesales.core.enums;

import kotlin.jvm.internal.Intrinsics;

public final class AnalyticsOperationType {
    private String operation;
    public static final AnalyticsOperationType VISIT = new AnalyticsOperationType("Visit");
    public static final AnalyticsOperationType PENETRATION = new AnalyticsOperationType("Penetration");
    public static final AnalyticsOperationType TRANSFER_GET_DATA = new AnalyticsOperationType("Import Data");
    public static final AnalyticsOperationType SALES_ORDERS = new AnalyticsOperationType("Sales Orders");
    public static final AnalyticsOperationType INVOICE = new AnalyticsOperationType("Invoice");
    public static final AnalyticsOperationType RETURN_INVOICE = new AnalyticsOperationType("Return Invoice");
    public static final AnalyticsOperationType RETAIL_INVOICE = new AnalyticsOperationType("Retail Invoice");
    public static final AnalyticsOperationType RETAIL_RETURN_INVOICE = new AnalyticsOperationType("Retail Return Invoice");
    public static final AnalyticsOperationType DISPATCH = new AnalyticsOperationType("Dispatch");
    public static final AnalyticsOperationType RETURN_DISPATCH = new AnalyticsOperationType("Return Dispatch");
    public static final AnalyticsOperationType EXCHANGE = new AnalyticsOperationType("Exchange");
    public static final AnalyticsOperationType CASH_COLLECTION = new AnalyticsOperationType("Cash Collection");
    public static final AnalyticsOperationType SAFE_DEPOSIT_COLLECTION = new AnalyticsOperationType("Safe Deposit Collection");
    public static final AnalyticsOperationType CREDIT_CARD_COLLECTION = new AnalyticsOperationType("Credit Card Collection");
    public static final AnalyticsOperationType CHEQUE_COLLECTION = new AnalyticsOperationType("Cheque Collection");
    public static final AnalyticsOperationType DEED_COLLECTION = new AnalyticsOperationType("Deed Collection");

    private static AnalyticsOperationType[] values() {
        return new AnalyticsOperationType[]{VISIT, PENETRATION, TRANSFER_GET_DATA, SALES_ORDERS, INVOICE, RETURN_INVOICE, RETAIL_INVOICE, RETAIL_RETURN_INVOICE, DISPATCH, RETURN_DISPATCH, EXCHANGE, CASH_COLLECTION, SAFE_DEPOSIT_COLLECTION, CREDIT_CARD_COLLECTION, CHEQUE_COLLECTION, DEED_COLLECTION};
    }
    public static AnalyticsOperationType valueOf() {
        return valueOf();
    }
    private AnalyticsOperationType(String str2) {
        operation = str2;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        operation = str;
    }
}
