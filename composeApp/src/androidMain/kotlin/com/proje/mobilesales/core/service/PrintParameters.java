package com.proje.mobilesales.core.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: PrintParameters.kt */

public final class PrintParameters {

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("value")
    @Expose
    private String value;

    public PrintParameters() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static PrintParameters copydefault(PrintParameters printParameters, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = printParameters.key;
        }
        if ((i2 & 2) != 0) {
            str2 = printParameters.value;
        }
        return printParameters.copy(str, str2);
    }

    public String component1() {
        return this.key;
    }

    public String component2() {
        return this.value;
    }

    public PrintParameters copy(String str, String str2) {
        return new PrintParameters(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrintParameters printParameters)) {
            return false;
        }
        return Intrinsics.areEqual(this.key, printParameters.key) && Intrinsics.areEqual(this.value, printParameters.value);
    }

    public int hashCode() {
        String str = this.key;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.value;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "PrintParameters(key=" + this.key + ", value=" + this.value + ')';
    }

    public PrintParameters(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public PrintParameters(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
