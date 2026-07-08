package com.proje.mobilesales.features.sales.model.fiche;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesFicheParameters implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private SalesFicheDetailFields mSalesFicheDetailFields;
    private SalesFicheHeaderFields mSalesFicheHeaderFields;
    private SalesFicheUserRights mSalesFicheUserRights;
    public SalesFicheParameters() {
        this(null, null, null, 7, null);
    }
    public static SalesFicheParameters copydefault(SalesFicheParameters salesFicheParameters, SalesFicheHeaderFields salesFicheHeaderFields, SalesFicheDetailFields salesFicheDetailFields, SalesFicheUserRights salesFicheUserRights, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            salesFicheHeaderFields = salesFicheParameters.mSalesFicheHeaderFields;
        }
        if ((i2 & 2) != 0) {
            salesFicheDetailFields = salesFicheParameters.mSalesFicheDetailFields;
        }
        if ((i2 & 4) != 0) {
            salesFicheUserRights = salesFicheParameters.mSalesFicheUserRights;
        }
        return salesFicheParameters.copy(salesFicheHeaderFields, salesFicheDetailFields, salesFicheUserRights);
    }
    public SalesFicheHeaderFields component1() {
        return this.mSalesFicheHeaderFields;
    }
    public SalesFicheDetailFields component2() {
        return this.mSalesFicheDetailFields;
    }
    public SalesFicheUserRights component3() {
        return this.mSalesFicheUserRights;
    }
    public SalesFicheParameters copy(SalesFicheHeaderFields salesFicheHeaderFields, SalesFicheDetailFields mSalesFicheDetailFields, SalesFicheUserRights mSalesFicheUserRights) {
        Intrinsics.checkNotNullParameter(mSalesFicheDetailFields, "mSalesFicheDetailFields");
        Intrinsics.checkNotNullParameter(mSalesFicheUserRights, "mSalesFicheUserRights");
        return new SalesFicheParameters(salesFicheHeaderFields, mSalesFicheDetailFields, mSalesFicheUserRights);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesFicheParameters salesFicheParameters)) {
            return false;
        }
        return Intrinsics.areEqual(this.mSalesFicheHeaderFields, salesFicheParameters.mSalesFicheHeaderFields) && Intrinsics.areEqual(this.mSalesFicheDetailFields, salesFicheParameters.mSalesFicheDetailFields) && Intrinsics.areEqual(this.mSalesFicheUserRights, salesFicheParameters.mSalesFicheUserRights);
    }
    public int hashCode() {
        SalesFicheHeaderFields salesFicheHeaderFields = this.mSalesFicheHeaderFields;
        return ((((salesFicheHeaderFields == null ? 0 : salesFicheHeaderFields.hashCode()) * 31) + this.mSalesFicheDetailFields.hashCode()) * 31) + this.mSalesFicheUserRights.hashCode();
    }
    public String toString() {
        return "SalesFicheParameters(mSalesFicheHeaderFields=" + this.mSalesFicheHeaderFields + ", mSalesFicheDetailFields=" + this.mSalesFicheDetailFields + ", mSalesFicheUserRights=" + this.mSalesFicheUserRights + ')';
    }
    public SalesFicheParameters(SalesFicheHeaderFields salesFicheHeaderFields, SalesFicheDetailFields mSalesFicheDetailFields, SalesFicheUserRights mSalesFicheUserRights) {
        Intrinsics.checkNotNullParameter(mSalesFicheDetailFields, "mSalesFicheDetailFields");
        Intrinsics.checkNotNullParameter(mSalesFicheUserRights, "mSalesFicheUserRights");
        this.mSalesFicheHeaderFields = salesFicheHeaderFields;
        this.mSalesFicheDetailFields = mSalesFicheDetailFields;
        this.mSalesFicheUserRights = mSalesFicheUserRights;
    }
    public  SalesFicheParameters(com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields r35, com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields r36, com.proje.mobilesales.features.sales.model.SalesFicheUserRights r37, int r38, kotlin.jvm.internal.DefaultConstructorMarker r39) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.sales.model.fiche.SalesFicheParameters.<init>(com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields, com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields, com.proje.mobilesales.features.sales.model.SalesFicheUserRights, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
    public SalesFicheHeaderFields getMSalesFicheHeaderFields() {
        return this.mSalesFicheHeaderFields;
    }
    public void setMSalesFicheHeaderFields(SalesFicheHeaderFields salesFicheHeaderFields) {
        this.mSalesFicheHeaderFields = salesFicheHeaderFields;
    }
    public SalesFicheDetailFields getMSalesFicheDetailFields() {
        return this.mSalesFicheDetailFields;
    }
    public void setMSalesFicheDetailFields(SalesFicheDetailFields salesFicheDetailFields) {
        Intrinsics.checkNotNullParameter(salesFicheDetailFields, "<set-?>");
        this.mSalesFicheDetailFields = salesFicheDetailFields;
    }
    public SalesFicheUserRights getMSalesFicheUserRights() {
        return this.mSalesFicheUserRights;
    }
    public void setMSalesFicheUserRights(SalesFicheUserRights salesFicheUserRights) {
        Intrinsics.checkNotNullParameter(salesFicheUserRights, "<set-?>");
        this.mSalesFicheUserRights = salesFicheUserRights;
    }
    public SalesFicheParameters(Parcel parcel) {
        this(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.mSalesFicheHeaderFields = (SalesFicheHeaderFields) parcel.readValue(SalesFicheHeaderFields.class.getClassLoader());
        Object readValue = parcel.readValue(SalesFicheDetailFields.class.getClassLoader());
        Intrinsics.checkNotNull(readValue, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields");
        this.mSalesFicheDetailFields = (SalesFicheDetailFields) readValue;
        Object readValue2 = parcel.readValue(SalesFicheUserRights.class.getClassLoader());
        Intrinsics.checkNotNull(readValue2, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.model.SalesFicheUserRights");
        this.mSalesFicheUserRights = (SalesFicheUserRights) readValue2;
    }
    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeValue(this.mSalesFicheHeaderFields);
        dest.writeValue(this.mSalesFicheDetailFields);
        dest.writeValue(this.mSalesFicheUserRights);
    }
    public static final class CREATOR implements Parcelable.Creator<SalesFicheParameters> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        public SalesFicheParameters createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SalesFicheParameters(parcel);
        }

        public SalesFicheParameters[] newArray(int i2) {
            return new SalesFicheParameters[i2];
        }
    }
}
