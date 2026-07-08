package com.proje.mobilesales.features.sales.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;

public class SalesView implements Parcelable {
    public double amount;
    public String code;
    public String dCode;
    public double discPer;
    public int globTrans;
    public int lineType;
    public int logicalRef;
    public String name;
    public double netTotal;
    public double price;
    public double total;
    public String unit;
    public double vatAmount;
    public static Companion Companion = new Companion(null);
    public static Parcelable.Creator<SalesView> CREATOR = new Creator();
    public static class Creator implements Parcelable.Creator<SalesView> {
        public SalesView createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return SalesView.Companion.m1484create(parcel);
        }

        public SalesView[] newArray(final int i2) {
            return new SalesView[i2];
        }
    }
    public SalesView() {
        this(0, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0, 0, null, 0.0d, 8191, null);
    }
    public int component1() {
        return this.logicalRef;
    }
    public int component10() {
        return this.lineType;
    }
    public int component11() {
        return this.globTrans;
    }
    public String component12() {
        return this.dCode;
    }
    public double component13() {
        return this.discPer;
    }
    public String component2() {
        return this.code;
    }
    public String component3() {
        return this.name;
    }
    public String component4() {
        return this.unit;
    }
    public double component5() {
        return this.amount;
    }
    public double component6() {
        return this.price;
    }
    public double component7() {
        return this.vatAmount;
    }
    public double component8() {
        return this.netTotal;
    }
    public double component9() {
        return this.total;
    }
    public SalesView copy(int i2, String str, String str2, String str3, double d2, double d3, double d4, double d5, double d6, int i3, int i4, String str4, double d7) {
        return new SalesView(i2, str, str2, str3, d2, d3, d4, d5, d6, i3, i4, str4, d7);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesView salesView)) {
            return false;
        }
        return this.logicalRef == salesView.logicalRef && Intrinsics.areEqual(this.code, salesView.code) && Intrinsics.areEqual(this.name, salesView.name) && Intrinsics.areEqual(this.unit, salesView.unit) && Double.compare(this.amount, salesView.amount) == 0 && Double.compare(this.price, salesView.price) == 0 && Double.compare(this.vatAmount, salesView.vatAmount) == 0 && Double.compare(this.netTotal, salesView.netTotal) == 0 && Double.compare(this.total, salesView.total) == 0 && this.lineType == salesView.lineType && this.globTrans == salesView.globTrans && Intrinsics.areEqual(this.dCode, salesView.dCode) && Double.compare(this.discPer, salesView.discPer) == 0;
    }
    public int hashCode() {
        int hashCode = Integer.hashCode(this.logicalRef) * 31;
        String str = this.code;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.name;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.unit;
        int hashCode4 = (((((((((((((((hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + Double.hashCode(this.amount)) * 31) + Double.hashCode(this.price)) * 31) + Double.hashCode(this.vatAmount)) * 31) + Double.hashCode(this.netTotal)) * 31) + Double.hashCode(this.total)) * 31) + Integer.hashCode(this.lineType)) * 31) + Integer.hashCode(this.globTrans)) * 31;
        String str4 = this.dCode;
        return ((hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31) + Double.hashCode(this.discPer);
    }
    public String toString() {
        return "SalesView(logicalRef=" + this.logicalRef + ", code=" + this.code + ", name=" + this.name + ", unit=" + this.unit + ", amount=" + this.amount + ", price=" + this.price + ", vatAmount=" + this.vatAmount + ", netTotal=" + this.netTotal + ", total=" + this.total + ", lineType=" + this.lineType + ", globTrans=" + this.globTrans + ", dCode=" + this.dCode + ", discPer=" + this.discPer + ')';
    }
    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Companion.write(this, out, i2);
    }
    public SalesView(int i2, String str, String str2, String str3, double d2, double d3, double d4, double d5, double d6, int i3, int i4, String str4, double d7) {
        this.logicalRef = i2;
        this.code = str;
        this.name = str2;
        this.unit = str3;
        this.amount = d2;
        this.price = d3;
        this.vatAmount = d4;
        this.netTotal = d5;
        this.total = d6;
        this.lineType = i3;
        this.globTrans = i4;
        this.dCode = str4;
        this.discPer = d7;
    }
    public  SalesView(int i2, String str, String str2, String str3, double d2, double d3, double d4, double d5, double d6, int i3, int i4, String str4, double d7, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i2, (i5 & 2) != 0 ? null : str, (i5 & 4) != 0 ? null : str2, (i5 & 8) != 0 ? null : str3, (i5 & 16) != 0 ? 0.0d : d2, (i5 & 32) != 0 ? 0.0d : d3, (i5 & 64) != 0 ? 0.0d : d4, (i5 & 128) != 0 ? 0.0d : d5, (i5 & 256) != 0 ? 0.0d : d6, (i5 & 512) != 0 ? 0 : i3, (i5 & 1024) != 0 ? 0 : i4, (i5 & 2048) != 0 ? null : str4, (i5 & 4096) == 0 ? d7 : 0.0d);
    }
    public SalesView(final Parcel parcel) {
     //   this(0, null, null, null, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, r16, r16, null, 0.0d, 8191, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int i2 = 0;
        this.logicalRef = parcel.readInt();
        this.code = parcel.readString();
        this.name = parcel.readString();
        this.unit = parcel.readString();
        this.amount = parcel.readDouble();
        this.price = parcel.readDouble();
        this.vatAmount = parcel.readDouble();
        this.netTotal = parcel.readDouble();
        this.total = parcel.readDouble();
        this.lineType = parcel.readInt();
        this.globTrans = parcel.readInt();
        this.dCode = parcel.readString();
        this.discPer = parcel.readDouble();
    }
    public static class Companion implements Parceler<SalesView> {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public SalesView[] newArray(final int i2) {
          //  SalesView[] salesViews = (SalesView[]) DefaultImpls.newArray(this, i2);
            return null;
        }

        public void write(SalesView salesView, Parcel dest, int i2) {
            Intrinsics.checkNotNullParameter(salesView, "<this>");
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(salesView.logicalRef);
            dest.writeString(salesView.code);
            dest.writeString(salesView.name);
            dest.writeString(salesView.unit);
            dest.writeDouble(salesView.amount);
            dest.writeDouble(salesView.price);
            dest.writeDouble(salesView.vatAmount);
            dest.writeDouble(salesView.netTotal);
            dest.writeDouble(salesView.total);
            dest.writeInt(salesView.lineType);
            dest.writeInt(salesView.globTrans);
            dest.writeString(salesView.dCode);
            dest.writeDouble(salesView.discPer);
        }
        public SalesView m1484create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SalesView(parcel);
        }
    }
}
