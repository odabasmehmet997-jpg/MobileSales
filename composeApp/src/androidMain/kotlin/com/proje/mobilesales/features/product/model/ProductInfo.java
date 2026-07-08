package com.proje.mobilesales.features.product.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProductInfo implements Parcelable {
    public static final Parcelable.Creator<ProductInfo> CREATOR = new Creator();
    private final boolean isService;
    private final String itemCode;
    private final String itemName;
    private final int itemRef;

    public static final class Creator implements Parcelable.Creator<ProductInfo> {

        public ProductInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ProductInfo(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
        }

        public ProductInfo[] newArray(int i) {
            return new ProductInfo[i];
        }
    }

    public ProductInfo() {
        this(null, null, 0, false, 15, null);
    }

    public static ProductInfo copydefault(ProductInfo productInfo, String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = productInfo.itemCode;
        }
        if ((i2 & 2) != 0) {
            str2 = productInfo.itemName;
        }
        if ((i2 & 4) != 0) {
            i = productInfo.itemRef;
        }
        if ((i2 & 8) != 0) {
            z = productInfo.isService;
        }
        return productInfo.copy(str, str2, i, z);
    }

    public String component1() {
        return this.itemCode;
    }

    public String component2() {
        return this.itemName;
    }

    public int component3() {
        return this.itemRef;
    }

    public boolean component4() {
        return this.isService;
    }

    public ProductInfo copy(String str, String str2, int i, boolean z) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(str2, "itemName");
        return new ProductInfo(str, str2, i, z);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductInfo productInfo)) {
            return false;
        }
        return Intrinsics.areEqual(this.itemCode, productInfo.itemCode) && Intrinsics.areEqual(this.itemName, productInfo.itemName) && this.itemRef == productInfo.itemRef && this.isService == productInfo.isService;
    }

    public int hashCode() {
        return (((((this.itemCode.hashCode() * 31) + this.itemName.hashCode()) * 31) + Integer.hashCode(this.itemRef)) * 31) + Boolean.hashCode(this.isService);
    }

    public String toString() {
        return "ProductInfo(itemCode=" + this.itemCode + ", itemName=" + this.itemName + ", itemRef=" + this.itemRef + ", isService=" + this.isService + ')';
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.itemCode);
        parcel.writeString(this.itemName);
        parcel.writeInt(this.itemRef);
        parcel.writeInt(this.isService ? 1 : 0);
    }

    public ProductInfo(String str, String str2, int i, boolean z) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(str2, "itemName");
        this.itemCode = str;
        this.itemName = str2;
        this.itemRef = i;
        this.isService = z;
    }

    public   ProductInfo(String str, String str2, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? 0 : i, (i2 & 8) == 0 && z);
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getItemRef() {
        return this.itemRef;
    }

    public boolean isService() {
        return this.isService;
    }
}
