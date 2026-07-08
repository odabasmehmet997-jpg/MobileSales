package com.proje.mobilesales.core.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;


/* compiled from: PrintResponse.kt */

public final class PrintResponse extends PrinterServiceBaseResponse {

    @SerializedName("result")
    @Expose
    private boolean isResult;

    public PrintResponse() {
        this(false, 1, null);
    }

    public static PrintResponse copydefault(PrintResponse printResponse, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = printResponse.isResult;
        }
        return printResponse.copy(z);
    }

    public boolean component1() {
        return this.isResult;
    }

    public PrintResponse copy(boolean z) {
        return new PrintResponse(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PrintResponse) && this.isResult == ((PrintResponse) obj).isResult;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isResult);
    }

    public String toString() {
        return "PrintResponse(isResult=" + this.isResult + ')';
    }

    public PrintResponse(boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) == 0 && z);
    }

    public boolean isResult() {
        return this.isResult;
    }

    public void setResult(boolean z) {
        this.isResult = z;
    }

    public PrintResponse(boolean z) {
        this.isResult = z;
    }
}
