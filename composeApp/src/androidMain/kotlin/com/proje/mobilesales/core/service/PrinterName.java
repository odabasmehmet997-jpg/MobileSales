package com.proje.mobilesales.core.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: PrinterName.kt */

public final class PrinterName {

    @SerializedName("name")
    @Expose
    public String name;

    public PrinterName() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static PrinterName copydefault(PrinterName printerName, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = printerName.name;
        }
        return printerName.copy(str);
    }

    public String component1() {
        return this.name;
    }

    public PrinterName copy(String str) {
        return new PrinterName(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PrinterName) && Intrinsics.areEqual(this.name, ((PrinterName) obj).name);
    }

    public int hashCode() {
        String str = this.name;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return "PrinterName(name=" + this.name + ')';
    }

    public PrinterName(String str) {
        this.name = str;
    }

    public PrinterName(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str);
    }
}
