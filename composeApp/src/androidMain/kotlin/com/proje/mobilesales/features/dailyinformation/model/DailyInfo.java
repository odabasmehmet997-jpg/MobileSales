package com.proje.mobilesales.features.dailyinformation.model;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class DailyInfo extends DailyInfoAbstract {
    public static final String BIREBIR = "BIREBIR";
    public static final String CASE = "CASE";
    public static final String CASH = "CASH";
    public static final String CEK = "CEK";
    public static final String CREDIT = "CREDIT";
    public static final Companion Companion = new Companion(null);
    public static final String DEMAND = "DEMAND";
    public static final String FATURA = "FATURA";
    public static final String IRSALIYE = "IRSALIYE";
    public static final String ORDER = "ORDER";
    public static final String PERAKENDEFATURA = "PERAKENDEFATURA";
    public static final String SENET = "SENET";
    public static final String VISIT = "VISIT";
    public static final String WHTRANSFER = "WHTRANSFER";
    public String accNo;
    public String bankBranch;
    public String bankName;
    public String debited;
    public String dueDate;
    private int fType;
    public int orderType;
    public String paymentPlan;
    public String shipAddress;
    public double total;
    public String visitNote;
    public String visitReason;

    public int getfType() {
        return this.fType;
    }

    public void setfType(int i2) {
        this.fType = i2;
    }

    /* compiled from: DailyInfo.kt */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
