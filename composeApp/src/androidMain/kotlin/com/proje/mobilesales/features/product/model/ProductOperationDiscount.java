package com.proje.mobilesales.features.product.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;

public class ProductOperationDiscount implements Parcelable {
    private boolean isDoDiscount;
    private int ratioCount;
    private int totalCount;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ProductOperationDiscount> CREATOR = new Creator();

    public static final class Creator implements Parcelable.Creator<ProductOperationDiscount> {

        public ProductOperationDiscount createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return ProductOperationDiscount.Companion.create(parcel);
        }
        public ProductOperationDiscount[] newArray(int i) {
            return new ProductOperationDiscount[i];
        }
    }

    public ProductOperationDiscount() {
        this(false, 0, 0, 7, null);
    }
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        Companion.write(this, parcel, i);
    }

    public ProductOperationDiscount(boolean z, int i, int i2) {
        this.isDoDiscount = z;
        this.ratioCount = i;
        this.totalCount = i2;
    }

    public ProductOperationDiscount(boolean z, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) == 0 && z, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2);
    }

    public final boolean isDoDiscount() {
        return this.isDoDiscount;
    }

    public final void setDoDiscount(boolean z) {
        this.isDoDiscount = z;
    }

    public final int getRatioCount() {
        return this.ratioCount;
    }

    public final void setRatioCount(int i) {
        this.ratioCount = i;
    }

    public final int getTotalCount() {
        return this.totalCount;
    }

    public final void setTotalCount(int i) {
        this.totalCount = i;
    }

    public final boolean isDoRatio(int i) {
        return this.ratioCount > i;
    }

    public final boolean isDoTotal(int i) {
        return this.totalCount > i;
    }
    protected ProductOperationDiscount(Parcel parcel) {
        this(false, 0, 0, 7, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.isDoDiscount = parcel.readByte() != 0;
        this.ratioCount = parcel.readInt();
        this.totalCount = parcel.readInt();
    }
    public static final class Companion implements Parceler<ProductOperationDiscount> {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public BaseDbSalesFiche[] newArray(int i) {
            return   DefaultImpls.newArray(this, i);
        }

        public void write(ProductOperationDiscount productOperationDiscount, Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(productOperationDiscount, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeByte(productOperationDiscount.isDoDiscount() ? (byte) 1 : 0);
            parcel.writeInt(productOperationDiscount.getRatioCount());
            parcel.writeInt(productOperationDiscount.getTotalCount());
        }

        public ProductOperationDiscount create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ProductOperationDiscount(parcel);
        }
    }
}
