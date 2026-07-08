package com.google.firebase.crashlytics.internal.model;

import com.google.auto.value.AutoValue;
import com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider;

/*  INFO: loaded from: classes2.dex */
@AutoValue
public abstract class StaticSessionData {
    public abstract AppData appData();

    public abstract DeviceData deviceData();

    public abstract OsData osData();

    public static StaticSessionData create(AppData appData, OsData osData, DeviceData deviceData) {
        return new AutoValue_StaticSessionData(appData, osData, deviceData);
    }

    @AutoValue
    public static abstract class AppData {
        public abstract String appIdentifier();

        public abstract int deliveryMechanism();

        public abstract DevelopmentPlatformProvider developmentPlatformProvider();

        public abstract String installUuid();

        public abstract String versionCode();

        public abstract String versionName();

        public static AppData create(String str, String str2, String str3, String str4, int i2, DevelopmentPlatformProvider developmentPlatformProvider) {
            return new AutoValue_StaticSessionData_AppData(str, str2, str3, str4, i2, developmentPlatformProvider);
        }
    }

    @AutoValue
    public static abstract class OsData {
        public abstract boolean isRooted();

        public abstract String osCodeName();

        public abstract String osRelease();

        public static OsData create(String str, String str2, boolean z) {
            return new AutoValue_StaticSessionData_OsData(str, str2, z);
        }
    }

    @AutoValue
    public static abstract class DeviceData {
        public abstract int arch();

        public abstract int availableProcessors();

        public abstract long diskSpace();

        public abstract boolean isEmulator();

        public abstract String manufacturer();

        public abstract String model();

        public abstract String modelClass();

        public abstract int state();

        public abstract long totalRam();

        public static DeviceData create(int i2, String str, int i3, long j2, long j3, boolean z, int i4, String str2, String str3) {
            return new AutoValue_StaticSessionData_DeviceData(i2, str, i3, j2, j3, z, i4, str2, str3);
        }
    }
}
