package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesMan implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    public String code;
    public String defination;
    public int logicalRef;
    public SalesMan() {
        this(0, null, null, 7, null);
    }
    public static SalesMan copydefault(SalesMan salesMan, int r1, String str, String str2, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            r1 = salesMan.logicalRef;
        }
        if ((r4 & 2) != 0) {
            str = salesMan.code;
        }
        if ((r4 & 4) != 0) {
            str2 = salesMan.defination;
        }
        return salesMan.copy(r1, str, str2);
    }

    public int component1() {
        return this.logicalRef;
    }

    public String component2() {
        return this.code;
    }

    public String component3() {
        return this.defination;
    }

    public SalesMan copy(int r1, String str, String str2) {
        return new SalesMan(r1, str, str2);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesMan salesMan)) {
            return false;
        }
        return this.logicalRef == salesMan.logicalRef && Intrinsics.areEqual(this.code, salesMan.code) && Intrinsics.areEqual(this.defination, salesMan.defination);
    }

    public int hashCode() {
        int r0 = Integer.hashCode(this.logicalRef) * 31;
        String str = this.code;
        int r02 = (r0 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.defination;
        return r02 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SalesMan(logicalRef=" + this.logicalRef + ", code=" + this.code + ", defination=" + this.defination + ')';
    }

    public SalesMan(int r1, String str, String str2) {
        this.logicalRef = r1;
        this.code = str;
        this.defination = str2;
    }

    public /* synthetic */ SalesMan(int r2, String str, String str2, int r5, DefaultConstructorMarker defaultConstructorMarker) {
        this((r5 & 1) != 0 ? 0 : r2, (r5 & 2) != 0 ? null : str, (r5 & 4) != 0 ? null : str2);
    }
    private SalesMan(Parcel parcel) {
        this(0, null, null, 7, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.logicalRef = parcel.readInt();
        this.code = parcel.readString();
        this.defination = parcel.readString();
    }
    public void writeToParcel(Parcel dest, int r2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.logicalRef);
        dest.writeString(this.code);
        dest.writeString(this.defination);
    }
    public static final class CREATOR implements Creator<SalesMan> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public SalesMan createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SalesMan(parcel);
        }
        public SalesMan[] newArray(int r1) {
            return new SalesMan[r1];
        }
    }
}
