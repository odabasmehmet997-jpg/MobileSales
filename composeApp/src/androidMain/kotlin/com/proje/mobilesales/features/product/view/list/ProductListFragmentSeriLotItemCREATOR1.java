package com.proje.mobilesales.features.product.view.list;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

final class ProductListFragmentSeriLotItemCREATOR1 implements Parcelable.Creator<ProductListFragment.SeriLotItem> {
    public ProductListFragment.SeriLotItem createFromParcel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        return new ProductListFragment.SeriLotItem(parcel);
    }
    public ProductListFragment.SeriLotItem[] newArray(int i) {
        return new ProductListFragment.SeriLotItem[i];
    }
}
