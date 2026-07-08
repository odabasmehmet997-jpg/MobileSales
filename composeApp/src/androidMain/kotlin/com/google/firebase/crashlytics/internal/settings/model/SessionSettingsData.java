package com.google.firebase.crashlytics.internal.settings.model;

/*  INFO: loaded from: classes2.dex */
public class SessionSettingsData {
    public final int maxCompleteSessionsCount;
    public final int maxCustomExceptionEvents;

    public SessionSettingsData(int i2, int i3) {
        this.maxCustomExceptionEvents = i2;
        this.maxCompleteSessionsCount = i3;
    }
}
