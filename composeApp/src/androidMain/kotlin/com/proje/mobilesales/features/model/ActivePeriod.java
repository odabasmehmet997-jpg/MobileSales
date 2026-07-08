package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.annotation.Column;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


public final class ActivePeriod {

    @Column(name = "ACTIVEPERIOD")
    private String activePeriod;

    @Column(name = "AUTH")
    private int auth;

    @Column(name = "BEGDATE")
    private String beginDate;

    @Column(name = "ENDDATE")
    private String endDate;

    public ActivePeriod() {
        this(null, 0, null, null, 15, null);
    }

    public static ActivePeriod copydefault(final ActivePeriod activePeriod, String str, int i2, String str2, String str3, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            str = activePeriod.activePeriod;
        }
        if (0 != (i3 & 2)) {
            i2 = activePeriod.auth;
        }
        if (0 != (i3 & 4)) {
            str2 = activePeriod.beginDate;
        }
        if (0 != (i3 & 8)) {
            str3 = activePeriod.endDate;
        }
        return activePeriod.copy(str, i2, str2, str3);
    }

    public String component1() {
        return activePeriod;
    }

    public int component2() {
        return auth;
    }

    public String component3() {
        return beginDate;
    }

    public String component4() {
        return endDate;
    }

    public ActivePeriod copy(final String str, final int i2, final String str2, final String str3) {
        return new ActivePeriod(str, i2, str2, str3);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActivePeriod activePeriod)) {
            return false;
        }
        return Intrinsics.areEqual(this.activePeriod, activePeriod.activePeriod) && auth == activePeriod.auth && Intrinsics.areEqual(beginDate, activePeriod.beginDate) && Intrinsics.areEqual(endDate, activePeriod.endDate);
    }

    public int hashCode() {
        final String str = activePeriod;
        final int hashCode = (((null == str ? 0 : str.hashCode()) * 31) + Integer.hashCode(auth)) * 31;
        final String str2 = beginDate;
        final int hashCode2 = (hashCode + (null == str2 ? 0 : str2.hashCode())) * 31;
        final String str3 = endDate;
        return hashCode2 + (null != str3 ? str3.hashCode() : 0);
    }

    public String toString() {
        return "ActivePeriod(activePeriod=" + activePeriod + ", auth=" + auth + ", beginDate=" + beginDate + ", endDate=" + endDate + ')';
    }

    public ActivePeriod(final String str, final int i2, final String str2, final String str3) {
        activePeriod = str;
        auth = i2;
        beginDate = str2;
        endDate = str3;
    }

    public ActivePeriod(final String str, final int i2, final String str2, final String str3, final int i3, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i3 & 1) ? null : str, 0 != (i3 & 2) ? 0 : i2, 0 != (i3 & 4) ? null : str2, 0 != (i3 & 8) ? null : str3);
    }

    public String getActivePeriod() {
        return activePeriod;
    }

    public void setActivePeriod(final String str) {
        activePeriod = str;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(final int i2) {
        auth = i2;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(final String str) {
        beginDate = str;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(final String str) {
        endDate = str;
    }
}
