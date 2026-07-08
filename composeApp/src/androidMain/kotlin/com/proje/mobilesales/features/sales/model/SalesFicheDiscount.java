package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesFicheDiscount implements Parcelable, Cloneable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private FicheDiscountRefProp discountCard;
    private FicheDiscountProp discountRatio;
    private FicheDiscountProp discountTotal;
    public SalesFicheDiscount() {
        this(null, null, null, 7, null);
    }
    public static  SalesFicheDiscount copydefault(SalesFicheDiscount salesFicheDiscount, FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, FicheDiscountRefProp ficheDiscountRefProp, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            ficheDiscountProp = salesFicheDiscount.discountRatio;
        }
        if ((r4 & 2) != 0) {
            ficheDiscountProp2 = salesFicheDiscount.discountTotal;
        }
        if ((r4 & 4) != 0) {
            ficheDiscountRefProp = salesFicheDiscount.discountCard;
        }
        return salesFicheDiscount.copy(ficheDiscountProp, ficheDiscountProp2, ficheDiscountRefProp);
    }
    public FicheDiscountProp component1() {
        return this.discountRatio;
    }
    public FicheDiscountProp component2() {
        return this.discountTotal;
    }
    public FicheDiscountRefProp component3() {
        return this.discountCard;
    }
    public SalesFicheDiscount copy(FicheDiscountProp discountRatio, FicheDiscountProp discountTotal, FicheDiscountRefProp discountCard) {
        Intrinsics.checkNotNullParameter(discountRatio, "discountRatio");
        Intrinsics.checkNotNullParameter(discountTotal, "discountTotal");
        Intrinsics.checkNotNullParameter(discountCard, "discountCard");
        return new SalesFicheDiscount(discountRatio, discountTotal, discountCard);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesFicheDiscount salesFicheDiscount)) {
            return false;
        }
        return Intrinsics.areEqual(this.discountRatio, salesFicheDiscount.discountRatio) && Intrinsics.areEqual(this.discountTotal, salesFicheDiscount.discountTotal) && Intrinsics.areEqual(this.discountCard, salesFicheDiscount.discountCard);
    }
    public int hashCode() {
        return (((this.discountRatio.hashCode() * 31) + this.discountTotal.hashCode()) * 31) + this.discountCard.hashCode();
    }
    public String toString() {
        return "SalesFicheDiscount(discountRatio=" + this.discountRatio + ", discountTotal=" + this.discountTotal + ", discountCard=" + this.discountCard + ')';
    }
    public SalesFicheDiscount(FicheDiscountProp discountRatio, FicheDiscountProp discountTotal, FicheDiscountRefProp discountCard) {
        Intrinsics.checkNotNullParameter(discountRatio, "discountRatio");
        Intrinsics.checkNotNullParameter(discountTotal, "discountTotal");
        Intrinsics.checkNotNullParameter(discountCard, "discountCard");
        this.discountRatio = discountRatio;
        this.discountTotal = discountTotal;
        this.discountCard = discountCard;
    }
    public SalesFicheDiscount(FicheDiscountProp ficheDiscountProp, FicheDiscountProp ficheDiscountProp2, FicheDiscountRefProp ficheDiscountRefProp, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this((r4 & 1) != 0 ? new FicheDiscountProp() : ficheDiscountProp, (r4 & 2) != 0 ? new FicheDiscountProp() : ficheDiscountProp2, (r4 & 4) != 0 ? new FicheDiscountRefProp() : ficheDiscountRefProp);
    }
    public FicheDiscountProp getDiscountRatio() {
        return this.discountRatio;
    }
    public void setDiscountRatio(FicheDiscountProp ficheDiscountProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountProp, "<set-?>");
        this.discountRatio = ficheDiscountProp;
    }
    public FicheDiscountProp getDiscountTotal() {
        return this.discountTotal;
    }
    public void setDiscountTotal(FicheDiscountProp ficheDiscountProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountProp, "<set-?>");
        this.discountTotal = ficheDiscountProp;
    }
    public FicheDiscountRefProp getDiscountCard() {
        return this.discountCard;
    }
    public void setDiscountCard(FicheDiscountRefProp ficheDiscountRefProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "<set-?>");
        this.discountCard = ficheDiscountRefProp;
    }
    public FicheDiscountProp findDiscountByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        if (Intrinsics.areEqual(this.discountRatio.getGuid(), guid)) {
            return this.discountRatio;
        }
        if (Intrinsics.areEqual(this.discountTotal.getGuid(), guid)) {
            return this.discountTotal;
        }
        if (Intrinsics.areEqual(this.discountCard.getGuid(), guid)) {
            return this.discountCard;
        }
        return null;
    }
    public SalesFicheDiscount clone() throws CloneNotSupportedException {
        SalesFicheDiscount salesFicheDiscount = new SalesFicheDiscount(null, null, null, 7, null);
        FicheDiscountRefProp ficheDiscountRefPropMo1244clone = this.discountCard.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountRefPropMo1244clone, "clone(...)");
        salesFicheDiscount.discountCard = ficheDiscountRefPropMo1244clone;
        FicheDiscountProp ficheDiscountPropMo1244clone = this.discountTotal.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountPropMo1244clone, "clone(...)");
        salesFicheDiscount.discountTotal = ficheDiscountPropMo1244clone;
        FicheDiscountProp ficheDiscountPropMo1244clone2 = this.discountRatio.clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountPropMo1244clone2, "clone(...)");
        salesFicheDiscount.discountRatio = ficheDiscountPropMo1244clone2;
        return salesFicheDiscount;
    }
    public SalesFicheDiscount(Parcel parcel) {
        this(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        FicheDiscountProp ficheDiscountProp = parcel.readParcelable(FicheDiscountProp.class.getClassLoader());
        this.discountRatio = ficheDiscountProp == null ? new FicheDiscountProp() : ficheDiscountProp;
        FicheDiscountProp ficheDiscountProp2 = parcel.readParcelable(FicheDiscountProp.class.getClassLoader());
        this.discountTotal = ficheDiscountProp2 == null ? new FicheDiscountProp() : ficheDiscountProp2;
        FicheDiscountRefProp ficheDiscountRefProp = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.discountCard = ficheDiscountRefProp == null ? new FicheDiscountRefProp() : ficheDiscountRefProp;
    }
    public boolean hasCampaignApplied() {
        if (TextUtils.isEmpty(this.discountRatio.getCampaignCode()) && TextUtils.isEmpty(this.discountTotal.getCampaignCode())) {
            return !TextUtils.isEmpty(this.discountCard.getCampaignCode());
        }
        return true;
    }
    public void clearCampaign() {
        if (!TextUtils.isEmpty(this.discountRatio.getCampaignCode())) {
            this.discountRatio.reset();
        }
        if (!TextUtils.isEmpty(this.discountTotal.getCampaignCode())) {
            this.discountTotal.reset();
        }
        if (TextUtils.isEmpty(this.discountCard.getCampaignCode())) {
            return;
        }
        this.discountCard.reset();
    }
    public void writeToParcel(Parcel dest, int r3) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeParcelable(this.discountRatio, r3);
        dest.writeParcelable(this.discountTotal, r3);
        dest.writeParcelable(this.discountCard, r3);
    }
    public static final class CREATOR implements Creator<SalesFicheDiscount> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public SalesFicheDiscount createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesFicheDiscount(source);
        }
        public SalesFicheDiscount[] newArray(int r1) {
            return new SalesFicheDiscount[r1];
        }
    }
}
