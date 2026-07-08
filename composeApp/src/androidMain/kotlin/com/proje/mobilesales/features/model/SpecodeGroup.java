package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class SpecodeGroup {
    private String specode1;
    private String specode2;
    private String specode3;
    private String specode4;
    private String specode5;

    public SpecodeGroup() {
        this(null, null, null, null, null, 31, null);
    }

    public static SpecodeGroup copydefault(final SpecodeGroup specodeGroup, String str, String str2, String str3, String str4, String str5, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = specodeGroup.specode1;
        }
        if (0 != (i2 & 2)) {
            str2 = specodeGroup.specode2;
        }
        final String str6 = str2;
        if (0 != (i2 & 4)) {
            str3 = specodeGroup.specode3;
        }
        final String str7 = str3;
        if (0 != (i2 & 8)) {
            str4 = specodeGroup.specode4;
        }
        final String str8 = str4;
        if (0 != (i2 & 16)) {
            str5 = specodeGroup.specode5;
        }
        return specodeGroup.copy(str, str6, str7, str8, str5);
    }

    public String component1() {
        return specode1;
    }

    public String component2() {
        return specode2;
    }

    public String component3() {
        return specode3;
    }

    public String component4() {
        return specode4;
    }

    public String component5() {
        return specode5;
    }

    public SpecodeGroup copy(final String str, final String str2, final String str3, final String str4, final String str5) {
        return new SpecodeGroup(str, str2, str3, str4, str5);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpecodeGroup specodeGroup)) {
            return false;
        }
        return Intrinsics.areEqual(specode1, specodeGroup.specode1) && Intrinsics.areEqual(specode2, specodeGroup.specode2) && Intrinsics.areEqual(specode3, specodeGroup.specode3) && Intrinsics.areEqual(specode4, specodeGroup.specode4) && Intrinsics.areEqual(specode5, specodeGroup.specode5);
    }

    public int hashCode() {
        final String str = specode1;
        final int hashCode = (null == str ? 0 : str.hashCode()) * 31;
        final String str2 = specode2;
        final int hashCode2 = (hashCode + (null == str2 ? 0 : str2.hashCode())) * 31;
        final String str3 = specode3;
        final int hashCode3 = (hashCode2 + (null == str3 ? 0 : str3.hashCode())) * 31;
        final String str4 = specode4;
        final int hashCode4 = (hashCode3 + (null == str4 ? 0 : str4.hashCode())) * 31;
        final String str5 = specode5;
        return hashCode4 + (null != str5 ? str5.hashCode() : 0);
    }

    public String toString() {
        return "SpecodeGroup(specode1=" + specode1 + ", specode2=" + specode2 + ", specode3=" + specode3 + ", specode4=" + specode4 + ", specode5=" + specode5 + ')';
    }

    public SpecodeGroup(final String str, final String str2, final String str3, final String str4, final String str5) {
        specode1 = str;
        specode2 = str2;
        specode3 = str3;
        specode4 = str4;
        specode5 = str5;
    }

    public SpecodeGroup(final String str, final String str2, final String str3, final String str4, final String str5, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? null : str, 0 != (i2 & 2) ? null : str2, 0 != (i2 & 4) ? null : str3, 0 != (i2 & 8) ? null : str4, 0 != (i2 & 16) ? null : str5);
    }

    public String getSpecode1() {
        return specode1;
    }

    public void setSpecode1(final String str) {
        specode1 = str;
    }

    public String getSpecode2() {
        return specode2;
    }

    public void setSpecode2(final String str) {
        specode2 = str;
    }

    public String getSpecode3() {
        return specode3;
    }

    public void setSpecode3(final String str) {
        specode3 = str;
    }

    public String getSpecode4() {
        return specode4;
    }

    public void setSpecode4(final String str) {
        specode4 = str;
    }

    public String getSpecode5() {
        return specode5;
    }

    public void setSpecode5(final String str) {
        specode5 = str;
    }
}
