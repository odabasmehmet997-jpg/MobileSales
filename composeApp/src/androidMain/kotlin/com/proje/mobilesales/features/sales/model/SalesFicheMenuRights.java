package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.features.model.FicheMenuRights;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class SalesFicheMenuRights implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private FicheMenuRights dispatchMenuRights;
    private boolean isEditOfferStateOrder;
    private boolean isOnlinePrint;
    private FicheMenuRights mInvoiceMenuRights;
    private FicheMenuRights mOrderDispatchableMenuRights;
    private FicheMenuRights mOrderOfferMenuRights;
    private FicheMenuRights mOrderUnDispatchableMenuRights;
    public SalesFicheMenuRights() {
        this(null, null, null, null, null, false, false, 127, null);
    }
    public static SalesFicheMenuRights copydefault(SalesFicheMenuRights salesFicheMenuRights, FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, FicheMenuRights ficheMenuRights3, FicheMenuRights ficheMenuRights4, FicheMenuRights ficheMenuRights5, boolean z, boolean z2, int r13, Object obj) {
        if ((r13 & 1) != 0) {
            ficheMenuRights = salesFicheMenuRights.mInvoiceMenuRights;
        }
        if ((r13 & 2) != 0) {
            ficheMenuRights2 = salesFicheMenuRights.dispatchMenuRights;
        }
        FicheMenuRights ficheMenuRights6 = ficheMenuRights2;
        if ((r13 & 4) != 0) {
            ficheMenuRights3 = salesFicheMenuRights.mOrderOfferMenuRights;
        }
        FicheMenuRights ficheMenuRights7 = ficheMenuRights3;
        if ((r13 & 8) != 0) {
            ficheMenuRights4 = salesFicheMenuRights.mOrderDispatchableMenuRights;
        }
        FicheMenuRights ficheMenuRights8 = ficheMenuRights4;
        if ((r13 & 16) != 0) {
            ficheMenuRights5 = salesFicheMenuRights.mOrderUnDispatchableMenuRights;
        }
        FicheMenuRights ficheMenuRights9 = ficheMenuRights5;
        if ((r13 & 32) != 0) {
            z = salesFicheMenuRights.isEditOfferStateOrder;
        }
        boolean z3 = z;
        if ((r13 & 64) != 0) {
            z2 = salesFicheMenuRights.isOnlinePrint;
        }
        return salesFicheMenuRights.copy(ficheMenuRights, ficheMenuRights6, ficheMenuRights7, ficheMenuRights8, ficheMenuRights9, z3, z2);
    }
    public FicheMenuRights component1() {
        return this.mInvoiceMenuRights;
    }
    public FicheMenuRights component2() {
        return this.dispatchMenuRights;
    }
    public FicheMenuRights component3() {
        return this.mOrderOfferMenuRights;
    }
    public FicheMenuRights component4() {
        return this.mOrderDispatchableMenuRights;
    }
    public FicheMenuRights component5() {
        return this.mOrderUnDispatchableMenuRights;
    }
    public boolean component6() {
        return this.isEditOfferStateOrder;
    }
    public boolean component7() {
        return this.isOnlinePrint;
    }
    public SalesFicheMenuRights copy(FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, FicheMenuRights ficheMenuRights3, FicheMenuRights ficheMenuRights4, FicheMenuRights ficheMenuRights5, boolean z, boolean z2) {
        return new SalesFicheMenuRights(ficheMenuRights, ficheMenuRights2, ficheMenuRights3, ficheMenuRights4, ficheMenuRights5, z, z2);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesFicheMenuRights salesFicheMenuRights)) {
            return false;
        }
        return Intrinsics.areEqual(this.mInvoiceMenuRights, salesFicheMenuRights.mInvoiceMenuRights) && Intrinsics.areEqual(this.dispatchMenuRights, salesFicheMenuRights.dispatchMenuRights) && Intrinsics.areEqual(this.mOrderOfferMenuRights, salesFicheMenuRights.mOrderOfferMenuRights) && Intrinsics.areEqual(this.mOrderDispatchableMenuRights, salesFicheMenuRights.mOrderDispatchableMenuRights) && Intrinsics.areEqual(this.mOrderUnDispatchableMenuRights, salesFicheMenuRights.mOrderUnDispatchableMenuRights) && this.isEditOfferStateOrder == salesFicheMenuRights.isEditOfferStateOrder && this.isOnlinePrint == salesFicheMenuRights.isOnlinePrint;
    }
    public int hashCode() {
        FicheMenuRights ficheMenuRights = this.mInvoiceMenuRights;
        int r0 = (ficheMenuRights == null ? 0 : ficheMenuRights.hashCode()) * 31;
        FicheMenuRights ficheMenuRights2 = this.dispatchMenuRights;
        int r02 = (r0 + (ficheMenuRights2 == null ? 0 : ficheMenuRights2.hashCode())) * 31;
        FicheMenuRights ficheMenuRights3 = this.mOrderOfferMenuRights;
        int r03 = (r02 + (ficheMenuRights3 == null ? 0 : ficheMenuRights3.hashCode())) * 31;
        FicheMenuRights ficheMenuRights4 = this.mOrderDispatchableMenuRights;
        int r04 = (r03 + (ficheMenuRights4 == null ? 0 : ficheMenuRights4.hashCode())) * 31;
        FicheMenuRights ficheMenuRights5 = this.mOrderUnDispatchableMenuRights;
        return ((((r04 + (ficheMenuRights5 != null ? ficheMenuRights5.hashCode() : 0)) * 31) + Boolean.hashCode(this.isEditOfferStateOrder)) * 31) + Boolean.hashCode(this.isOnlinePrint);
    }
    public String toString() {
        return "SalesFicheMenuRights(mInvoiceMenuRights=" + this.mInvoiceMenuRights + ", dispatchMenuRights=" + this.dispatchMenuRights + ", mOrderOfferMenuRights=" + this.mOrderOfferMenuRights + ", mOrderDispatchableMenuRights=" + this.mOrderDispatchableMenuRights + ", mOrderUnDispatchableMenuRights=" + this.mOrderUnDispatchableMenuRights + ", isEditOfferStateOrder=" + this.isEditOfferStateOrder + ", isOnlinePrint=" + this.isOnlinePrint + ')';
    }
    public SalesFicheMenuRights(FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, FicheMenuRights ficheMenuRights3, FicheMenuRights ficheMenuRights4, FicheMenuRights ficheMenuRights5, boolean z, boolean z2) {
        this.mInvoiceMenuRights = ficheMenuRights;
        this.dispatchMenuRights = ficheMenuRights2;
        this.mOrderOfferMenuRights = ficheMenuRights3;
        this.mOrderDispatchableMenuRights = ficheMenuRights4;
        this.mOrderUnDispatchableMenuRights = ficheMenuRights5;
        this.isEditOfferStateOrder = z;
        this.isOnlinePrint = z2;
    }
    public SalesFicheMenuRights(FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, FicheMenuRights ficheMenuRights3, FicheMenuRights ficheMenuRights4, FicheMenuRights ficheMenuRights5, boolean z, boolean z2, int r9, DefaultConstructorMarker defaultConstructorMarker) {
        this((r9 & 1) != 0 ? null : ficheMenuRights, (r9 & 2) != 0 ? null : ficheMenuRights2, (r9 & 4) != 0 ? null : ficheMenuRights3, (r9 & 8) != 0 ? null : ficheMenuRights4, (r9 & 16) != 0 ? null : ficheMenuRights5, (r9 & 32) == 0 && z, (r9 & 64) == 0 && z2);
    }
    public FicheMenuRights getMInvoiceMenuRights() {
        return this.mInvoiceMenuRights;
    }
    public void setMInvoiceMenuRights(FicheMenuRights ficheMenuRights) {
        this.mInvoiceMenuRights = ficheMenuRights;
    }
    public FicheMenuRights getDispatchMenuRights() {
        return this.dispatchMenuRights;
    }
    public void setDispatchMenuRights(FicheMenuRights ficheMenuRights) {
        this.dispatchMenuRights = ficheMenuRights;
    }
    public FicheMenuRights getMOrderOfferMenuRights() {
        return this.mOrderOfferMenuRights;
    }
    public void setMOrderOfferMenuRights(FicheMenuRights ficheMenuRights) {
        this.mOrderOfferMenuRights = ficheMenuRights;
    }
    public FicheMenuRights getMOrderDispatchableMenuRights() {
        return this.mOrderDispatchableMenuRights;
    }
    public void setMOrderDispatchableMenuRights(FicheMenuRights ficheMenuRights) {
        this.mOrderDispatchableMenuRights = ficheMenuRights;
    }
    public FicheMenuRights getMOrderUnDispatchableMenuRights() {
        return this.mOrderUnDispatchableMenuRights;
    }
    public void setMOrderUnDispatchableMenuRights(FicheMenuRights ficheMenuRights) {
        this.mOrderUnDispatchableMenuRights = ficheMenuRights;
    }
    public boolean isEditOfferStateOrder() {
        return this.isEditOfferStateOrder;
    }
    public void setEditOfferStateOrder(boolean z) {
        this.isEditOfferStateOrder = z;
    }
    public boolean isOnlinePrint() {
        return this.isOnlinePrint;
    }
    public void setOnlinePrint(boolean z) {
        this.isOnlinePrint = z;
    }
    private SalesFicheMenuRights(Parcel parcel) {
        this(null, null, null, null, null, false, false, 127, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.mInvoiceMenuRights = parcel.readParcelable(FicheMenuRights.class.getClassLoader());
        this.dispatchMenuRights = parcel.readParcelable(FicheMenuRights.class.getClassLoader());
        this.mOrderOfferMenuRights = parcel.readParcelable(FicheMenuRights.class.getClassLoader());
        this.mOrderDispatchableMenuRights = parcel.readParcelable(FicheMenuRights.class.getClassLoader());
        this.mOrderUnDispatchableMenuRights = parcel.readParcelable(FicheMenuRights.class.getClassLoader());
        this.isEditOfferStateOrder = parcel.readByte() != 0;
        this.isOnlinePrint = parcel.readByte() != 0;
    }
    public SalesFicheMenuRights(String orderEdit, String orderDelete, String orderCopy, FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, boolean z, boolean z2) {
        this(null, null, null, null, null, false, false, 127, null);
        Intrinsics.checkNotNullParameter(orderEdit, "orderEdit");
        Intrinsics.checkNotNullParameter(orderDelete, "orderDelete");
        Intrinsics.checkNotNullParameter(orderCopy, "orderCopy");
        this.mInvoiceMenuRights = ficheMenuRights;
        this.dispatchMenuRights = ficheMenuRights2;
        this.mOrderOfferMenuRights = new FicheMenuRights(false, false, false);
        this.mOrderDispatchableMenuRights = new FicheMenuRights(false, false, false);
        this.mOrderUnDispatchableMenuRights = new FicheMenuRights(false, false, false);
        this.isEditOfferStateOrder = z;
        this.isOnlinePrint = z2;
        try {
            FicheMenuRights ficheMenuRights3 = this.mOrderOfferMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights3);
            FicheMenuRights ficheMenuRights4 = this.mOrderDispatchableMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights4);
            FicheMenuRights ficheMenuRights5 = this.mOrderUnDispatchableMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights5);
            setEditFicheMenuRight(orderEdit, ficheMenuRights3, ficheMenuRights4, ficheMenuRights5);
            FicheMenuRights ficheMenuRights6 = this.mOrderOfferMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights6);
            FicheMenuRights ficheMenuRights7 = this.mOrderDispatchableMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights7);
            FicheMenuRights ficheMenuRights8 = this.mOrderUnDispatchableMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights8);
            setCopyFicheMenuRight(orderCopy, ficheMenuRights6, ficheMenuRights7, ficheMenuRights8);
            FicheMenuRights ficheMenuRights9 = this.mOrderOfferMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights9);
            FicheMenuRights ficheMenuRights10 = this.mOrderDispatchableMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights10);
            FicheMenuRights ficheMenuRights11 = this.mOrderUnDispatchableMenuRights;
            Intrinsics.checkNotNull(ficheMenuRights11);
            setDeleteFicheMenuRight(orderDelete, ficheMenuRights9, ficheMenuRights10, ficheMenuRights11);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    private void setEditFicheMenuRight(String str, FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, FicheMenuRights ficheMenuRights3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (StringsKt.contains(str, "0", false)) {
            ficheMenuRights.setEdit(true);
        }
        if (StringsKt.contains(str, "1", false)) {
            ficheMenuRights2.setEdit(true);
        }
        if (StringsKt.contains(str, ExifInterface.GPS_MEASUREMENT_2D, false)) {
            ficheMenuRights3.setEdit(true);
        }
    }
    private void setDeleteFicheMenuRight(String str, FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, FicheMenuRights ficheMenuRights3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (StringsKt.contains(str, "0", false)) {
            ficheMenuRights.setDelete(true);
        }
        if (StringsKt.contains(str, "1", false)) {
            ficheMenuRights2.setDelete(true);
        }
        if (StringsKt.contains(str, ExifInterface.GPS_MEASUREMENT_2D, false)) {
            ficheMenuRights3.setDelete(true);
        }
    }
    private void setCopyFicheMenuRight(String str, FicheMenuRights ficheMenuRights, FicheMenuRights ficheMenuRights2, FicheMenuRights ficheMenuRights3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (StringsKt.contains(str, "0", false)) {
            ficheMenuRights.setCopy(true);
        }
        if (StringsKt.contains(str, "1", false)) {
            ficheMenuRights2.setCopy(true);
        }
        if (StringsKt.contains(str, ExifInterface.GPS_MEASUREMENT_2D, false)) {
            ficheMenuRights3.setCopy(true);
        }
    }
    public void writeToParcel(Parcel dest, int r3) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeParcelable(this.mInvoiceMenuRights, r3);
        dest.writeParcelable(this.dispatchMenuRights, r3);
        dest.writeParcelable(this.mOrderOfferMenuRights, r3);
        dest.writeParcelable(this.mOrderDispatchableMenuRights, r3);
        dest.writeParcelable(this.mOrderUnDispatchableMenuRights, r3);
        dest.writeByte(this.isEditOfferStateOrder ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOnlinePrint ? (byte) 1 : (byte) 0);
    }
    public static final class CREATOR implements Creator<SalesFicheMenuRights> {
        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesFicheMenuRights createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesFicheMenuRights(source);
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesFicheMenuRights[] newArray(int r1) {
            return new SalesFicheMenuRights[r1];
        }
    }
}
