package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;


/* compiled from: AnalyticsHelper.kt */

public interface AnalyticsHelper {
    void logEventFirebaseAnalyticsData(AnalyticsEventType analyticsEventType);

    void logOperationFirebaseAnalyticsData(AnalyticsOperationType analyticsOperationType);

    void logPrinterModelsFirebaseAnalyticsData(String str);
}
