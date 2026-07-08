package com.google.firebase.crashlytics.internal.model;

/*  INFO: loaded from: classes2.dex */
final class AutoValue_StaticSessionData extends StaticSessionData {
    private final AppData appData;
    private final DeviceData deviceData;
    private final OsData osData;

    AutoValue_StaticSessionData(AppData appData, OsData osData, DeviceData deviceData) {
        if (appData == null) {
            throw new NullPointerException("Null appData");
        }
        this.appData = appData;
        if (osData == null) {
            throw new NullPointerException("Null osData");
        }
        this.osData = osData;
        if (deviceData == null) {
            throw new NullPointerException("Null deviceData");
        }
        this.deviceData = deviceData;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public AppData appData() {
        return this.appData;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public OsData osData() {
        return this.osData;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public DeviceData deviceData() {
        return this.deviceData;
    }

    public String toString() {
        return "StaticSessionData{appData=" + this.appData + ", osData=" + this.osData + ", deviceData=" + this.deviceData + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StaticSessionData)) {
            return false;
        }
        StaticSessionData staticSessionData = (StaticSessionData) obj;
        return this.appData.equals(staticSessionData.appData()) && this.osData.equals(staticSessionData.osData()) && this.deviceData.equals(staticSessionData.deviceData());
    }

    public int hashCode() {
        return this.deviceData.hashCode() ^ ((((this.appData.hashCode() ^ 1000003) * 1000003) ^ this.osData.hashCode()) * 1000003);
    }
}
