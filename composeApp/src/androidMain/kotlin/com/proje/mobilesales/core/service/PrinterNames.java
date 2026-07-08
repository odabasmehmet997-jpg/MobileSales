package com.proje.mobilesales.core.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PrinterNames extends PrinterServiceBaseResponse {

    private List<PrinterName> result;

    public PrinterNames() {
        this(null, 1, 0 == true ? 1 : 0);
    }
    public static  PrinterNames copydefault(PrinterNames printerNames, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = printerNames.result;
        }
        return printerNames.copy(list);
    }

    public List<PrinterName> component1() {
        return this.result;
    }

    public PrinterNames copy(List<PrinterName> list) {
        return new PrinterNames(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PrinterNames) && Intrinsics.areEqual(this.result, ((PrinterNames) obj).result);
    }

    public int hashCode() {
        List<PrinterName> list = this.result;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "PrinterNames(result=" + this.result + ')';
    }

    public PrinterNames(List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : list);
    }

    public List<PrinterName> getResult() {
        return this.result;
    }

    public void setResult(List<PrinterName> list) {
        this.result = list;
    }

    public PrinterNames(List<PrinterName> list) {
        this.result = list;
    }
}
