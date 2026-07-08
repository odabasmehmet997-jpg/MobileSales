package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.proje.mobilesales.features.model.FicheDiscountProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesFicheDiscountProps implements Cloneable, Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private FicheDiscountProp customerDiscount;
    private int discountCount;
    private ArrayList<SalesFicheDiscount> discounts;
    public SalesFicheDiscountProps() {
        this(0, null, null, 7, null);
    }
    public static SalesFicheDiscountProps copydefault(SalesFicheDiscountProps salesFicheDiscountProps, int r1, ArrayList arrayList, FicheDiscountProp ficheDiscountProp, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            r1 = salesFicheDiscountProps.discountCount;
        }
        if ((r4 & 2) != 0) {
            arrayList = salesFicheDiscountProps.discounts;
        }
        if ((r4 & 4) != 0) {
            ficheDiscountProp = salesFicheDiscountProps.customerDiscount;
        }
        return salesFicheDiscountProps.copy(r1, arrayList, ficheDiscountProp);
    }
    public int component1() {
        return this.discountCount;
    }

    public ArrayList<SalesFicheDiscount> component2() {
        return this.discounts;
    }

    public FicheDiscountProp component3() {
        return this.customerDiscount;
    }

    public SalesFicheDiscountProps copy(int r1, ArrayList<SalesFicheDiscount> discounts, FicheDiscountProp customerDiscount) {
        Intrinsics.checkNotNullParameter(discounts, "discounts");
        Intrinsics.checkNotNullParameter(customerDiscount, "customerDiscount");
        return new SalesFicheDiscountProps(r1, discounts, customerDiscount);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesFicheDiscountProps salesFicheDiscountProps)) {
            return false;
        }
        return this.discountCount == salesFicheDiscountProps.discountCount && Intrinsics.areEqual(this.discounts, salesFicheDiscountProps.discounts) && Intrinsics.areEqual(this.customerDiscount, salesFicheDiscountProps.customerDiscount);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.discountCount) * 31) + this.discounts.hashCode()) * 31) + this.customerDiscount.hashCode();
    }

    public String toString() {
        return "SalesFicheDiscountProps(discountCount=" + this.discountCount + ", discounts=" + this.discounts + ", customerDiscount=" + this.customerDiscount + ')';
    }

    public SalesFicheDiscountProps(int r2, ArrayList<SalesFicheDiscount> discounts, FicheDiscountProp customerDiscount) {
        Intrinsics.checkNotNullParameter(discounts, "discounts");
        Intrinsics.checkNotNullParameter(customerDiscount, "customerDiscount");
        this.discountCount = r2;
        this.discounts = discounts;
        this.customerDiscount = customerDiscount;
    }

    public int getDiscountCount() {
        return this.discountCount;
    }

    public void setDiscountCount(int r1) {
        this.discountCount = r1;
    }

    public /* synthetic */ SalesFicheDiscountProps(int r1, ArrayList arrayList, FicheDiscountProp ficheDiscountProp, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this((r4 & 1) != 0 ? 0 : r1, (r4 & 2) != 0 ? new ArrayList() : arrayList, (r4 & 4) != 0 ? new FicheDiscountProp() : ficheDiscountProp);
    }

    public ArrayList<SalesFicheDiscount> getDiscounts() {
        return this.discounts;
    }

    public void setDiscounts(ArrayList<SalesFicheDiscount> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.discounts = arrayList;
    }

    public FicheDiscountProp getCustomerDiscount() {
        return this.customerDiscount;
    }

    public void setCustomerDiscount(FicheDiscountProp ficheDiscountProp) {
        Intrinsics.checkNotNullParameter(ficheDiscountProp, "<set-?>");
        this.customerDiscount = ficheDiscountProp;
    }

    public SalesFicheDiscountProps(int r7) {
        this(0, null, null, 7, null);
        this.discountCount = r7;
        this.customerDiscount = new FicheDiscountProp();
        this.discounts = new ArrayList<>(r7);
        initDiscount();
    }

    /*  WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    private SalesFicheDiscountProps(Parcel parcel) {
        this(0, null, null, 7, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.discountCount = parcel.readInt();
        ArrayList<SalesFicheDiscount> arrayListCreateTypedArrayList = parcel.createTypedArrayList(SalesFicheDiscount.CREATOR);
        this.discounts = arrayListCreateTypedArrayList == null ? new ArrayList<>() : arrayListCreateTypedArrayList;
        FicheDiscountProp ficheDiscountProp = parcel.readParcelable(FicheDiscountProp.class.getClassLoader());
        this.customerDiscount = ficheDiscountProp == null ? new FicheDiscountProp() : ficheDiscountProp;
    }

    private void initDiscount() {
        int r0 = this.discountCount;
        for (int r1 = 0; r1 < r0; r1++) {
            this.discounts.add(new SalesFicheDiscount(null, null, null, 7, null));
        }
    }

    public com.proje.mobilesales.features.model.FicheDiscountProp getDiscountRatio(int r2) {
        if (r2 >= this.discountCount) {
            return null;
        }
        return this.discounts.get(r2).getDiscountRatio();
    }

    public com.proje.mobilesales.features.model.FicheDiscountProp getDiscountTotal(int r2) {
        if (r2 >= this.discountCount) {
            return null;
        }
        return this.discounts.get(r2).getDiscountTotal();
    }

    public com.proje.mobilesales.features.model.FicheDiscountRefProp getDiscountCard(int r2) {
        if (r2 >= this.discountCount) {
            return null;
        }
        return this.discounts.get(r2).getDiscountCard();
    }

    public void setDiscountCard(int r1, FicheDiscountRefProp ficheDiscountRefProp) {
        if (ficheDiscountRefProp != null) {
            this.discounts.get(r1).setDiscountCard(ficheDiscountRefProp);
        }
    }

    public FicheDiscountProp findDiscountByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        if (Intrinsics.areEqual(this.customerDiscount.getGuid(), guid)) {
            return this.customerDiscount;
        }
        Iterator<SalesFicheDiscount> it = this.discounts.iterator();
        FicheDiscountProp ficheDiscountPropFindDiscountByGuid = null;
        while (it.hasNext() && (ficheDiscountPropFindDiscountByGuid = it.next().findDiscountByGuid(guid)) == null) {
        }
        return ficheDiscountPropFindDiscountByGuid;
    }

    public int findDiscountIndexByGuid(String guid) {
        Intrinsics.checkNotNullParameter(guid, "guid");
        if (!Intrinsics.areEqual(this.customerDiscount.getGuid(), guid)) {
            Iterator<SalesFicheDiscount> it = this.discounts.iterator();
            int r0 = -1;
            while (it.hasNext()) {
                r0++;
                if (it.next().findDiscountByGuid(guid) != null) {
                    return r0;
                }
            }
        }
        return -1;
    }
    public SalesFicheDiscountProps m1317clone() throws CloneNotSupportedException {
        SalesFicheDiscountProps salesFicheDiscountProps = new SalesFicheDiscountProps(this.discountCount);
        FicheDiscountProp ficheDiscountPropMo1244clone = this.customerDiscount.mo1244clone();
        Intrinsics.checkNotNullExpressionValue(ficheDiscountPropMo1244clone, "clone(...)");
        salesFicheDiscountProps.customerDiscount = ficheDiscountPropMo1244clone;
        salesFicheDiscountProps.discounts = new ArrayList<>();
        int r1 = this.discountCount;
        for (int r2 = 0; r2 < r1; r2++) {
            salesFicheDiscountProps.discounts.add(this.discounts.get(r2).m1316clone());
        }
        return salesFicheDiscountProps;
    }

    public boolean hasCampaignApplied() {
        if (!TextUtils.isEmpty(this.customerDiscount.getCampaignCode())) {
            return true;
        }
        Iterator<SalesFicheDiscount> it = this.discounts.iterator();
        while (it.hasNext()) {
            if (it.next().hasCampaignApplied()) {
                return true;
            }
        }
        return false;
    }

    public void clearCampaign() {
        if (!TextUtils.isEmpty(this.customerDiscount.getCampaignCode())) {
            this.customerDiscount.reset();
        }
        Iterator<SalesFicheDiscount> it = this.discounts.iterator();
        while (it.hasNext()) {
            it.next().clearCampaign();
        }
    }
    public void writeToParcel(Parcel dest, int r3) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.discountCount);
        dest.writeTypedList(this.discounts);
        dest.writeParcelable(this.customerDiscount, r3);
    }
    public static final class CREATOR implements Creator<SalesFicheDiscountProps> {
        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesFicheDiscountProps createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SalesFicheDiscountProps(parcel);
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesFicheDiscountProps[] newArray(int r1) {
            return new SalesFicheDiscountProps[r1];
        }
    }
}
