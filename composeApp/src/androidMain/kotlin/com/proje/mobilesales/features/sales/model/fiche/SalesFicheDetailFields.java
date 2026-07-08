package com.proje.mobilesales.features.sales.model.fiche;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.enums.SalesType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesFicheDetailFields extends SalesFicheFields {
    public static final CREATOR CREATOR = new CREATOR (null);
    private boolean isDeliveryCode;
    private boolean isExplanation;
    private boolean isIncludeVat;
    private boolean isReserve;
    private  SalesType salesType;
    public SalesFicheDetailFields() {
        isIncludeVat = true;
        salesType = SalesType.FREE;
    }
    private SalesFicheDetailFields(final Parcel parcel) {
        super (parcel);
        Intrinsics.checkNotNullParameter (parcel, "parcel");
        final boolean z = true;
        isIncludeVat = true;
        salesType = SalesType.FREE;
        isIncludeVat = 0 != parcel.readByte ();
        isReserve = 0 != parcel.readByte ();
        isDeliveryCode = 0 != parcel.readByte ();
        setDeliveryDate(0 != parcel.readByte ());
        isExplanation = 0 != parcel.readByte () && z;
        salesType = (SalesType) parcel.readValue (SalesType.class.getClassLoader ());
    }
    public SalesFicheDetailFields(final com.proje.mobilesales.core.enums.ErpType r18, final SalesType r19, final String r20, final int r21, final int r22, final int r23, final boolean r24) {
        throw new UnsupportedOperationException (" com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields.<init>(com.proje.mobilesales.core.enums.ErpType, com.proje.mobilesales.core.enums.SalesType, java.lang.String, int, int, int, boolean):void");
    }
    public int describeContents() {
        return 0;
    }
    public boolean isIncludeVat() {
        return isIncludeVat;
    }
    public void setIncludeVat(final boolean z) {
        isIncludeVat = z;
    }
    public boolean isReserve() {
        return isReserve;
    }
    public void setReserve(final boolean z) {
        isReserve = z;
    }
    public boolean isDeliveryCode() {
        return isDeliveryCode;
    }
    public void setDeliveryCode(final boolean z) {
        isDeliveryCode = z;
    }
    public boolean isExplanation() {
        return isExplanation;
    }
    public void setExplanation(final boolean z) {
        isExplanation = z;
    }
    public SalesType getSalesType() {
        return salesType;
    }
    public void setSalesType(final SalesType salesType) {
        this.salesType = salesType;
    }
    public void writeToParcel(final Parcel parcel, final int i) {
        Intrinsics.checkNotNullParameter (parcel, "dest");
        super.writeToParcel (parcel, i);
        parcel.writeByte (isIncludeVat ? (byte) 1 : 0);
        parcel.writeByte (isReserve ? (byte) 1 : 0);
        parcel.writeByte (isDeliveryCode ? (byte) 1 : 0);
        parcel.writeByte (isDeliveryDate() ? (byte) 1 : 0);
        parcel.writeByte (isExplanation ? (byte) 1 : 0);
        parcel.writeValue (salesType);
    }
    public static final class CREATOR implements Creator<SalesFicheDetailFields> {
        public CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this ();
        }

        private CREATOR() {
        }

        public SalesFicheDetailFields createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter (parcel, "parcel");
            return new SalesFicheDetailFields (parcel);
        }

        public SalesFicheDetailFields[] newArray(final int i) {
            return new SalesFicheDetailFields[i];
        }
    }
}
