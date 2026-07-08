package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;

public enum QosTier {
    DEFAULT(0),
    UNMETERED_ONLY(1),
    UNMETERED_OR_DAILY(2),
    FAST_IF_RADIO_AWAKE(3),
    NEVER(4),
    UNRECOGNIZED(-1);
    private final int value;
    static {
        QosTier qosTier = null;
        QosTier qosTier2= null;
        QosTier qosTier3= null;
        QosTier qosTier4= null;
        QosTier qosTier5= null;
        QosTier qosTier6= null;
        SparseArray<QosTier> sparseArray = new SparseArray<>();
        sparseArray.put(0, qosTier);
        sparseArray.put(1, qosTier2);
        sparseArray.put(2, qosTier3);
        sparseArray.put(3, qosTier4);
        sparseArray.put(4, qosTier5);
        sparseArray.put(-1, qosTier6);
    }
    QosTier(int i2) {
        this.value = i2;
    }
    public final int getNumber() {
        return this.value;
    }
    public static QosTier forNumber(int i2) {
        if (0 == i2) {
            return DEFAULT;
        }
        if (1 == i2) {
            return UNMETERED_ONLY;
        }
        if (2 == i2) {
            return UNMETERED_OR_DAILY;
        }
        if (3 == i2) {
            return FAST_IF_RADIO_AWAKE;
        }
        if (4 != i2) {
            return null;
        }
        return NEVER;
    }
}
