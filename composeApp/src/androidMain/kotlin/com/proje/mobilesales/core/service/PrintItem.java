package com.proje.mobilesales.core.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: PrintItem.kt */

public final class PrintItem {

    @SerializedName("culture")
    @Expose
    private String culture;

    @SerializedName("numberOfCopies")
    @Expose
    private int numberOfCopies;

    @SerializedName("parameters")
    @Expose
    private ArrayList<PrintParameters> parameters;

    @SerializedName("printerName")
    @Expose
    private String printerName;

    @SerializedName("reportId")
    @Expose
    private int reportId;

    public PrintItem() {
        this(0, null, 0, null, null, 31, null);
    }

    public static PrintItem copydefault(PrintItem printItem, int i2, String str, int i3, String str2, ArrayList arrayList, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = printItem.reportId;
        }
        if ((i4 & 2) != 0) {
            str = printItem.printerName;
        }
        String str3 = str;
        if ((i4 & 4) != 0) {
            i3 = printItem.numberOfCopies;
        }
        int i5 = i3;
        if ((i4 & 8) != 0) {
            str2 = printItem.culture;
        }
        String str4 = str2;
        if ((i4 & 16) != 0) {
            arrayList = printItem.parameters;
        }
        return printItem.copy(i2, str3, i5, str4, arrayList);
    }

    public int component1() {
        return this.reportId;
    }

    public String component2() {
        return this.printerName;
    }

    public int component3() {
        return this.numberOfCopies;
    }

    public String component4() {
        return this.culture;
    }

    public ArrayList<PrintParameters> component5() {
        return this.parameters;
    }

    public PrintItem copy(int i2, String str, int i3, String str2, ArrayList<PrintParameters> arrayList) {
        return new PrintItem(i2, str, i3, str2, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrintItem printItem)) {
            return false;
        }
        return this.reportId == printItem.reportId && Intrinsics.areEqual(this.printerName, printItem.printerName) && this.numberOfCopies == printItem.numberOfCopies && Intrinsics.areEqual(this.culture, printItem.culture) && Intrinsics.areEqual(this.parameters, printItem.parameters);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.reportId) * 31;
        String str = this.printerName;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.numberOfCopies)) * 31;
        String str2 = this.culture;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        ArrayList<PrintParameters> arrayList = this.parameters;
        return hashCode3 + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "PrintItem(reportId=" + this.reportId + ", printerName=" + this.printerName + ", numberOfCopies=" + this.numberOfCopies + ", culture=" + this.culture + ", parameters=" + this.parameters + ')';
    }

    public PrintItem(int i2, String str, int i3, String str2, ArrayList<PrintParameters> arrayList) {
        this.reportId = i2;
        this.printerName = str;
        this.numberOfCopies = i3;
        this.culture = str2;
        this.parameters = arrayList;
    }

    public PrintItem(int i2, String str, int i3, String str2, ArrayList arrayList, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i2, (i4 & 2) != 0 ? null : str, (i4 & 4) == 0 ? i3 : 0, (i4 & 8) != 0 ? null : str2, (i4 & 16) != 0 ? null : arrayList);
    }

    public int getReportId() {
        return this.reportId;
    }

    public void setReportId(int i2) {
        this.reportId = i2;
    }

    public String getPrinterName() {
        return this.printerName;
    }

    public void setPrinterName(String str) {
        this.printerName = str;
    }

    public int getNumberOfCopies() {
        return this.numberOfCopies;
    }

    public void setNumberOfCopies(int i2) {
        this.numberOfCopies = i2;
    }

    public String getCulture() {
        return this.culture;
    }

    public void setCulture(String str) {
        this.culture = str;
    }

    public ArrayList<PrintParameters> getParameters() {
        return this.parameters;
    }

    public void setParameters(ArrayList<PrintParameters> arrayList) {
        this.parameters = arrayList;
    }
}
