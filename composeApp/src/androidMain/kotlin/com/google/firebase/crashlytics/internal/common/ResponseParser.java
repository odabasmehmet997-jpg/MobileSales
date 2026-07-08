package com.google.firebase.crashlytics.internal.common;

/*  INFO: loaded from: classes2.dex */
public class ResponseParser {
    public static final int ResponseActionDiscard = 0;
    public static final int ResponseActionRetry = 1;

    public static int parse(int i2) {
        if (i2 < 200 || i2 > 299) {
            return ((i2 < 300 || i2 > 399) && i2 >= 400 && i2 <= 499) ? 0 : 1;
        }
        return 0;
    }
}
