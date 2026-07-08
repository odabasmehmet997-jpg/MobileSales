package com.proje.mobilesales.features.sales.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class Serilot implements Parcelable, Cloneable {
    public static CREATOR CREATOR = new CREATOR(null);
    public double amount;
    public String code;
    public int detailRef;
    public String expDate;
    public String grpBegCode;
    public String grpEndCode;
    public int f1277id;
    public String locationCode;
    public int logicalRef;
    public int mainUnitRef;
    public String name;
    public double reAmount;
    public int slFicheType;
    public int sourceUnitRef;
    public int stTransRef;
    public String unit;
    public int id;

    public Serilot() {
        this(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, 0, 0, 0, 0, 65535, null);
    }

    public int component1() {
        return this.f1277id;
    }

    public String component10() {
        return this.grpBegCode;
    }

    public String component11() {
        return this.grpEndCode;
    }

    public String component12() {
        return this.locationCode;
    }

    public int component13() {
        return this.mainUnitRef;
    }

    public int component14() {
        return this.sourceUnitRef;
    }

    public int component15() {
        return this.stTransRef;
    }

    public int component16() {
        return this.slFicheType;
    }

    public int component2() {
        return this.logicalRef;
    }

    public int component3() {
        return this.detailRef;
    }

    public String component4() {
        return this.code;
    }

    public double component5() {
        return this.amount;
    }

    public String component6() {
        return this.unit;
    }

    public String component7() {
        return this.expDate;
    }

    public String component8() {
        return this.name;
    }

    public double component9() {
        return this.reAmount;
    }

    public Serilot copy(int i2, int i3, int i4, String str, double d2, String str2, String str3, String name, double d3, String grpBegCode, String grpEndCode, String locationCode, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(grpBegCode, "grpBegCode");
        Intrinsics.checkNotNullParameter(grpEndCode, "grpEndCode");
        Intrinsics.checkNotNullParameter(locationCode, "locationCode");
        return new Serilot(i2, i3, i4, str, d2, str2, str3, name, d3, grpBegCode, grpEndCode, locationCode, i5, i6, i7, i8);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Serilot serilot)) {
            return false;
        }
        return this.f1277id == serilot.f1277id && this.logicalRef == serilot.logicalRef && this.detailRef == serilot.detailRef && Intrinsics.areEqual(this.code, serilot.code) && Double.compare(this.amount, serilot.amount) == 0 && Intrinsics.areEqual(this.unit, serilot.unit) && Intrinsics.areEqual(this.expDate, serilot.expDate) && Intrinsics.areEqual(this.name, serilot.name) && Double.compare(this.reAmount, serilot.reAmount) == 0 && Intrinsics.areEqual(this.grpBegCode, serilot.grpBegCode) && Intrinsics.areEqual(this.grpEndCode, serilot.grpEndCode) && Intrinsics.areEqual(this.locationCode, serilot.locationCode) && this.mainUnitRef == serilot.mainUnitRef && this.sourceUnitRef == serilot.sourceUnitRef && this.stTransRef == serilot.stTransRef && this.slFicheType == serilot.slFicheType;
    }

    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.f1277id) * 31) + Integer.hashCode(this.logicalRef)) * 31) + Integer.hashCode(this.detailRef)) * 31;
        String str = this.code;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Double.hashCode(this.amount)) * 31;
        String str2 = this.unit;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.expDate;
        return ((((((((((((((((((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.name.hashCode()) * 31) + Double.hashCode(this.reAmount)) * 31) + this.grpBegCode.hashCode()) * 31) + this.grpEndCode.hashCode()) * 31) + this.locationCode.hashCode()) * 31) + Integer.hashCode(this.mainUnitRef)) * 31) + Integer.hashCode(this.sourceUnitRef)) * 31) + Integer.hashCode(this.stTransRef)) * 31) + Integer.hashCode(this.slFicheType);
    }

    public String toString() {
        return "Serilot(id=" + this.f1277id + ", logicalRef=" + this.logicalRef + ", detailRef=" + this.detailRef + ", code=" + this.code + ", amount=" + this.amount + ", unit=" + this.unit + ", expDate=" + this.expDate + ", name=" + this.name + ", reAmount=" + this.reAmount + ", grpBegCode=" + this.grpBegCode + ", grpEndCode=" + this.grpEndCode + ", locationCode=" + this.locationCode + ", mainUnitRef=" + this.mainUnitRef + ", sourceUnitRef=" + this.sourceUnitRef + ", stTransRef=" + this.stTransRef + ", slFicheType=" + this.slFicheType + ')';
    }

    public Serilot(int i2, int i3, int i4, String str, double d2, String str2, String str3, String name, double d3, String grpBegCode, String grpEndCode, String locationCode, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(grpBegCode, "grpBegCode");
        Intrinsics.checkNotNullParameter(grpEndCode, "grpEndCode");
        Intrinsics.checkNotNullParameter(locationCode, "locationCode");
        this.f1277id = i2;
        this.logicalRef = i3;
        this.detailRef = i4;
        this.code = str;
        this.amount = d2;
        this.unit = str2;
        this.expDate = str3;
        this.name = name;
        this.reAmount = d3;
        this.grpBegCode = grpBegCode;
        this.grpEndCode = grpEndCode;
        this.locationCode = locationCode;
        this.mainUnitRef = i5;
        this.sourceUnitRef = i6;
        this.stTransRef = i7;
        this.slFicheType = i8;
    }

    public  Serilot(int i2, int i3, int i4, String str, double d2, String str2, String str3, String str4, double d3, String str5, String str6, String str7, int i5, int i6, int i7, int i8, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this((i9 & 1) != 0 ? 0 : i2, (i9 & 2) != 0 ? 0 : i3, (i9 & 4) != 0 ? 0 : i4, (i9 & 8) != 0 ? null : str, (i9 & 16) != 0 ? 0.0d : d2, (i9 & 32) != 0 ? null : str2, (i9 & 64) == 0 ? str3 : null, (i9 & 128) != 0 ? "" : str4, (i9 & 256) == 0 ? d3 : 0.0d, (i9 & 512) != 0 ? "" : str5, (i9 & 1024) != 0 ? "" : str6, (i9 & 2048) == 0 ? str7 : "", (i9 & 4096) != 0 ? 0 : i5, (i9 & 8192) != 0 ? 0 : i6, (i9 & 16384) != 0 ? 0 : i7, (i9 & 32768) != 0 ? 0 : i8);
    }
    public Serilot m1486clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.database.Serilot");
        Serilot serilot = (Serilot) clone;
        String str = this.code;
        if (str == null) {
            str = "";
        }
        serilot.code = str;
        String str2 = this.expDate;
        if (str2 == null) {
            str2 = "";
        }
        serilot.expDate = str2;
        String str3 = this.unit;
        serilot.unit = str3 != null ? str3 : "";
        return serilot;
    }

    private Serilot(Parcel parcel) {
      //  this(0, 0, 0, null, 0.0d, null, null, null, 0.0d, null, null, null, r16, r16, 0, 0, 65535, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        int i2 = 0;
        this.f1277id = parcel.readInt();
        this.logicalRef = parcel.readInt();
        this.detailRef = parcel.readInt();
        this.code = parcel.readString();
        this.amount = parcel.readDouble();
        this.unit = parcel.readString();
        this.expDate = parcel.readString();
        String readString = parcel.readString();
        this.name = readString != null ? readString : "";
        this.reAmount = parcel.readDouble();
        String readString2 = parcel.readString();
        this.grpBegCode = readString2 != null ? readString2 : "";
        String readString3 = parcel.readString();
        this.grpEndCode = readString3 != null ? readString3 : "";
        String readString4 = parcel.readString();
        this.locationCode = readString4 != null ? readString4 : "";
        this.mainUnitRef = parcel.readInt();
        this.sourceUnitRef = parcel.readInt();
        this.stTransRef = parcel.readInt();
        this.slFicheType = parcel.readInt();
    }
    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.f1277id);
        dest.writeInt(this.logicalRef);
        dest.writeInt(this.detailRef);
        dest.writeString(this.code);
        dest.writeDouble(this.amount);
        dest.writeString(this.unit);
        dest.writeString(this.expDate);
        dest.writeString(this.name);
        dest.writeDouble(this.reAmount);
        dest.writeString(this.grpBegCode);
        dest.writeString(this.grpEndCode);
        dest.writeString(this.locationCode);
        dest.writeInt(this.mainUnitRef);
        dest.writeInt(this.sourceUnitRef);
        dest.writeInt(this.stTransRef);
        dest.writeInt(this.slFicheType);
    }
    public static class CREATOR implements Parcelable.Creator<Serilot> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public Serilot createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Serilot(parcel);
        }

        public Serilot[] newArray(int i2) {
            return new Serilot[i2];
        }
    }
}
