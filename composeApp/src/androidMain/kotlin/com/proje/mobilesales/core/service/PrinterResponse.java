package com.proje.mobilesales.core.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: PrinterResponse.kt */

public final class PrinterResponse extends PrinterServiceBaseResponse {

    @SerializedName("name")
    @Expose
    private final String name;

    public PrinterResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    private String component1() {
        return this.name;
    }

    public static PrinterResponse copydefault(PrinterResponse printerResponse, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = printerResponse.name;
        }
        return printerResponse.copy(str);
    }

    public PrinterResponse copy(String str) {
        return new PrinterResponse(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PrinterResponse) && Intrinsics.areEqual(this.name, ((PrinterResponse) obj).name);
    }

    public int hashCode() {
        String str = this.name;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return "PrinterResponse(name=" + this.name + ')';
    }

    public PrinterResponse(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str);
    }

    public PrinterResponse(String str) {
        this.name = str;
    }
}
