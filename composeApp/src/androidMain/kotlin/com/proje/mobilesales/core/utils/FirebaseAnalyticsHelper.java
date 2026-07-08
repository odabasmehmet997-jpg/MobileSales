package com.proje.mobilesales.core.utils;

import android.os.Bundle;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.enums.AnalyticsOperationType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FirebaseAnalyticsHelper implements AnalyticsHelper {
    public static final Companion Companion = new Companion(null);
    private static volatile FirebaseAnalyticsHelper INSTANCE;
    private final FirebaseAnalytics firebaseAnalytics;
    public FirebaseAnalyticsHelper(FirebaseAnalytics firebaseAnalytics) {
        Intrinsics.checkNotNullParameter(firebaseAnalytics, "firebaseAnalytics");
        this.firebaseAnalytics = firebaseAnalytics;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public synchronized FirebaseAnalyticsHelper getInstance(FirebaseAnalytics firebaseAnalytics) {
            FirebaseAnalyticsHelper firebaseAnalyticsHelper;
            Intrinsics.checkNotNullParameter(firebaseAnalytics, "firebaseAnalytics");
            firebaseAnalyticsHelper = FirebaseAnalyticsHelper.INSTANCE;
            if (firebaseAnalyticsHelper == null) {
                firebaseAnalyticsHelper = new FirebaseAnalyticsHelper(firebaseAnalytics);
                FirebaseAnalyticsHelper.INSTANCE = firebaseAnalyticsHelper;
            }
            return firebaseAnalyticsHelper;
        }
    }
    public void logEventFirebaseAnalyticsData(AnalyticsEventType eventType) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, eventType.getEvent());
        this.firebaseAnalytics.logEvent("pages", bundle);
        Log.d("ContentValues", "pages_" + bundle);
    }
    public void logOperationFirebaseAnalyticsData(AnalyticsOperationType operationType) {
        Intrinsics.checkNotNullParameter(operationType, "operationType");
        Bundle bundle = new Bundle();
        bundle.putString("operation_name", operationType.getOperation());
        this.firebaseAnalytics.logEvent("operations", bundle);
        Log.d("ContentValues", "operations_" + bundle);
    }
    public void logPrinterModelsFirebaseAnalyticsData(String printerName) {
        Intrinsics.checkNotNullParameter(printerName, "printerName");
        Bundle bundle = new Bundle();
        bundle.putString("printer_name", printerName);
        this.firebaseAnalytics.logEvent("printers", bundle);
        Log.d("ContentValues", "printers_" + bundle);
    }
}
