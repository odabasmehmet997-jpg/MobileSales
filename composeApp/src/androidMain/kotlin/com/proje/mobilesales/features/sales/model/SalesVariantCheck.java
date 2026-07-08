package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.features.model.ItemVariantStock;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesVariantCheck implements Serializable, Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private long detailId;
    private boolean isChecked;
    private boolean isDivUnit;
    private boolean isEnabled;
    private ItemVariantStock mVariant;
    public SalesVariantCheck() {
        this(false, null, false, false, 0L, 31, null);
    }
    public static SalesVariantCheck copydefault(SalesVariantCheck salesVariantCheck, boolean z, ItemVariantStock itemVariantStock, boolean z2, boolean z3, long j2, int r11, Object obj) {
        if ((r11 & 1) != 0) {
            z = salesVariantCheck.isChecked;
        }
        if ((r11 & 2) != 0) {
            itemVariantStock = salesVariantCheck.mVariant;
        }
        ItemVariantStock itemVariantStock2 = itemVariantStock;
        if ((r11 & 4) != 0) {
            z2 = salesVariantCheck.isEnabled;
        }
        boolean z4 = z2;
        if ((r11 & 8) != 0) {
            z3 = salesVariantCheck.isDivUnit;
        }
        boolean z5 = z3;
        if ((r11 & 16) != 0) {
            j2 = salesVariantCheck.detailId;
        }
        return salesVariantCheck.copy(z, itemVariantStock2, z4, z5, j2);
    }
    public boolean component1() {
        return this.isChecked;
    }
    public ItemVariantStock component2() {
        return this.mVariant;
    }
    public boolean component3() {
        return this.isEnabled;
    }
    public boolean component4() {
        return this.isDivUnit;
    }
    public long component5() {
        return this.detailId;
    }
    public SalesVariantCheck copy(boolean z, ItemVariantStock itemVariantStock, boolean z2, boolean z3, long j2) {
        return new SalesVariantCheck(z, itemVariantStock, z2, z3, j2);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesVariantCheck salesVariantCheck)) {
            return false;
        }
        return this.isChecked == salesVariantCheck.isChecked && Intrinsics.areEqual(this.mVariant, salesVariantCheck.mVariant) && this.isEnabled == salesVariantCheck.isEnabled && this.isDivUnit == salesVariantCheck.isDivUnit && this.detailId == salesVariantCheck.detailId;
    }
    public int hashCode() {
        int r0 = Boolean.hashCode(this.isChecked) * 31;
        ItemVariantStock itemVariantStock = this.mVariant;
        return ((((((r0 + (itemVariantStock == null ? 0 : itemVariantStock.hashCode())) * 31) + Boolean.hashCode(this.isEnabled)) * 31) + Boolean.hashCode(this.isDivUnit)) * 31) + Long.hashCode(this.detailId);
    }
    public String toString() {
        return "SalesVariantCheck(isChecked=" + this.isChecked + ", mVariant=" + this.mVariant + ", isEnabled=" + this.isEnabled + ", isDivUnit=" + this.isDivUnit + ", detailId=" + this.detailId + ')';
    }
    public SalesVariantCheck(boolean z, ItemVariantStock itemVariantStock, boolean z2, boolean z3, long j2) {
        this.isChecked = z;
        this.mVariant = itemVariantStock;
        this.isEnabled = z2;
        this.isDivUnit = z3;
        this.detailId = j2;
    }
    public SalesVariantCheck(boolean z, ItemVariantStock itemVariantStock, boolean z2, boolean z3, long j2, int r8, DefaultConstructorMarker defaultConstructorMarker) {
        this((r8 & 1) == 0 && z, (r8 & 2) != 0 ? null : itemVariantStock, (r8 & 4) == 0 && z2, (r8 & 8) == 0 && z3, (r8 & 16) != 0 ? 0L : j2);
    }
    public boolean isChecked() {
        return this.isChecked;
    }
    public void setChecked(boolean z) {
        this.isChecked = z;
    }
    public ItemVariantStock getMVariant() {
        return this.mVariant;
    }
    public void setMVariant(ItemVariantStock itemVariantStock) {
        this.mVariant = itemVariantStock;
    }
    public boolean isEnabled() {
        return this.isEnabled;
    }
    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }
    public boolean isDivUnit() {
        return this.isDivUnit;
    }
    public void setDivUnit(boolean z) {
        this.isDivUnit = z;
    }
    public long getDetailId() {
        return this.detailId;
    }
    public void setDetailId(long j2) {
        this.detailId = j2;
    }
    private SalesVariantCheck(Parcel parcel) {
        this(false, null, false, false, 0L, 31, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.mVariant = parcel.readParcelable(SalesVariantCheck.class.getClassLoader());
        this.isChecked = parcel.readByte() != 0;
        this.isEnabled = parcel.readByte() != 0;
        this.isDivUnit = parcel.readByte() != 0;
        this.detailId = parcel.readLong();
    }
    public void writeToParcel(Parcel dest, int r4) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeParcelable(this.mVariant, r4);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isEnabled ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDivUnit ? (byte) 1 : (byte) 0);
        dest.writeLong(this.detailId);
    }
    public static final class CREATOR implements Creator<SalesVariantCheck> {
        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesVariantCheck createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesVariantCheck(source);
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesVariantCheck[] newArray(int r1) {
            return new SalesVariantCheck[r1];
        }
    }
}
