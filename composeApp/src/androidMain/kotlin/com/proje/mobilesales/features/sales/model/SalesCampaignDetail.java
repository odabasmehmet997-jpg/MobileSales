package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesCampaignDetail implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private float campaignPoint;
    private String code;
    private float discount1;
    private float discount2;
    private float discount3;
    private float discountRate;
    private boolean forNetsis;
    private String name;
    private ArrayList<SalesCampaignPointDetail> pointDetails;
    private float quantity;
    private float surplus;
    private float surplusDiscount;
    private int type;
    private String unit;

    public SalesCampaignDetail() {
        this(null, null, null, 0.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 16383, null);
    }

    public String component1() {
        return this.name;
    }

    public float component10() {
        return this.discount1;
    }

    public float component11() {
        return this.discount2;
    }

    public float component12() {
        return this.discount3;
    }

    public ArrayList<SalesCampaignPointDetail> component13() {
        return this.pointDetails;
    }

    public boolean component14() {
        return this.forNetsis;
    }

    public String component2() {
        return this.code;
    }

    public String component3() {
        return this.unit;
    }

    public float component4() {
        return this.quantity;
    }

    public int component5() {
        return this.type;
    }

    public float component6() {
        return this.discountRate;
    }

    public float component7() {
        return this.campaignPoint;
    }

    public float component8() {
        return this.surplus;
    }

    public float component9() {
        return this.surplusDiscount;
    }

    public SalesCampaignDetail copy(String name, String code, String unit, float f2, int r21, float f3, float f4, float f5, float f6, float f7, float f8, float f9, ArrayList<SalesCampaignPointDetail> pointDetails, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(pointDetails, "pointDetails");
        return new SalesCampaignDetail(name, code, unit, f2, r21, f3, f4, f5, f6, f7, f8, f9, pointDetails, z);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesCampaignDetail salesCampaignDetail)) {
            return false;
        }
        return Intrinsics.areEqual(this.name, salesCampaignDetail.name) && Intrinsics.areEqual(this.code, salesCampaignDetail.code) && Intrinsics.areEqual(this.unit, salesCampaignDetail.unit) && Float.compare(this.quantity, salesCampaignDetail.quantity) == 0 && this.type == salesCampaignDetail.type && Float.compare(this.discountRate, salesCampaignDetail.discountRate) == 0 && Float.compare(this.campaignPoint, salesCampaignDetail.campaignPoint) == 0 && Float.compare(this.surplus, salesCampaignDetail.surplus) == 0 && Float.compare(this.surplusDiscount, salesCampaignDetail.surplusDiscount) == 0 && Float.compare(this.discount1, salesCampaignDetail.discount1) == 0 && Float.compare(this.discount2, salesCampaignDetail.discount2) == 0 && Float.compare(this.discount3, salesCampaignDetail.discount3) == 0 && Intrinsics.areEqual(this.pointDetails, salesCampaignDetail.pointDetails) && this.forNetsis == salesCampaignDetail.forNetsis;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((this.name.hashCode() * 31) + this.code.hashCode()) * 31) + this.unit.hashCode()) * 31) + Float.hashCode(this.quantity)) * 31) + Integer.hashCode(this.type)) * 31) + Float.hashCode(this.discountRate)) * 31) + Float.hashCode(this.campaignPoint)) * 31) + Float.hashCode(this.surplus)) * 31) + Float.hashCode(this.surplusDiscount)) * 31) + Float.hashCode(this.discount1)) * 31) + Float.hashCode(this.discount2)) * 31) + Float.hashCode(this.discount3)) * 31) + this.pointDetails.hashCode()) * 31) + Boolean.hashCode(this.forNetsis);
    }

    public String toString() {
        return "SalesCampaignDetail(name=" + this.name + ", code=" + this.code + ", unit=" + this.unit + ", quantity=" + this.quantity + ", type=" + this.type + ", discountRate=" + this.discountRate + ", campaignPoint=" + this.campaignPoint + ", surplus=" + this.surplus + ", surplusDiscount=" + this.surplusDiscount + ", discount1=" + this.discount1 + ", discount2=" + this.discount2 + ", discount3=" + this.discount3 + ", pointDetails=" + this.pointDetails + ", forNetsis=" + this.forNetsis + ')';
    }

    public SalesCampaignDetail(String name, String code, String unit, float f2, int r6, float f3, float f4, float f5, float f6, float f7, float f8, float f9, ArrayList<SalesCampaignPointDetail> pointDetails, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(pointDetails, "pointDetails");
        this.name = name;
        this.code = code;
        this.unit = unit;
        this.quantity = f2;
        this.type = r6;
        this.discountRate = f3;
        this.campaignPoint = f4;
        this.surplus = f5;
        this.surplusDiscount = f6;
        this.discount1 = f7;
        this.discount2 = f8;
        this.discount3 = f9;
        this.pointDetails = pointDetails;
        this.forNetsis = z;
    }

    public /* synthetic */ SalesCampaignDetail(String str, String str2, String str3, float f2, int r20, float f3, float f4, float f5, float f6, float f7, float f8, float f9, ArrayList arrayList, boolean z, int r30, DefaultConstructorMarker defaultConstructorMarker) {
        this((r30 & 1) != 0 ? "" : str, (r30 & 2) != 0 ? "" : str2, (r30 & 4) == 0 ? str3 : "", (r30 & 8) != 0 ? 0.0f : f2, (r30 & 16) != 0 ? 0 : r20, (r30 & 32) != 0 ? 0.0f : f3, (r30 & 64) != 0 ? 0.0f : f4, (r30 & 128) != 0 ? 0.0f : f5, (r30 & 256) != 0 ? 0.0f : f6, (r30 & 512) != 0 ? 0.0f : f7, (r30 & 1024) != 0 ? 0.0f : f8, (r30 & 2048) == 0 ? f9 : 0.0f, (r30 & 4096) != 0 ? new ArrayList() : arrayList, (r30 & 8192) == 0 && z);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.code = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.unit = str;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float f2) {
        this.quantity = f2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int r1) {
        this.type = r1;
    }

    public float getDiscountRate() {
        return this.discountRate;
    }

    public void setDiscountRate(float f2) {
        this.discountRate = f2;
    }

    public float getCampaignPoint() {
        return this.campaignPoint;
    }

    public void setCampaignPoint(float f2) {
        this.campaignPoint = f2;
    }

    public float getSurplus() {
        return this.surplus;
    }

    public void setSurplus(float f2) {
        this.surplus = f2;
    }

    public float getSurplusDiscount() {
        return this.surplusDiscount;
    }

    public void setSurplusDiscount(float f2) {
        this.surplusDiscount = f2;
    }

    public float getDiscount1() {
        return this.discount1;
    }

    public void setDiscount1(float f2) {
        this.discount1 = f2;
    }

    public float getDiscount2() {
        return this.discount2;
    }

    public void setDiscount2(float f2) {
        this.discount2 = f2;
    }

    public float getDiscount3() {
        return this.discount3;
    }

    public void setDiscount3(float f2) {
        this.discount3 = f2;
    }

    public ArrayList<SalesCampaignPointDetail> getPointDetails() {
        return this.pointDetails;
    }

    public void setPointDetails(ArrayList<SalesCampaignPointDetail> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.pointDetails = arrayList;
    }

    public boolean getForNetsis() {
        return this.forNetsis;
    }

    public void setForNetsis(boolean z) {
        this.forNetsis = z;
    }

    /*  WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public SalesCampaignDetail(Parcel parcel) {
        this(null, null, null, 0.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 16383, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        Intrinsics.checkNotNull(string);
        this.name = string;
        String string2 = parcel.readString();
        Intrinsics.checkNotNull(string2);
        this.code = string2;
        String string3 = parcel.readString();
        Intrinsics.checkNotNull(string3);
        this.unit = string3;
        this.quantity = parcel.readFloat();
        this.type = parcel.readInt();
        this.discountRate = parcel.readFloat();
        this.campaignPoint = parcel.readFloat();
        ArrayList<SalesCampaignPointDetail> arrayList = new ArrayList<>();
        this.pointDetails = arrayList;
        parcel.readList(arrayList, SalesCampaignPointDetail.class.getClassLoader());
        this.surplus = parcel.readFloat();
        this.surplusDiscount = parcel.readFloat();
        this.forNetsis = parcel.readByte() != 0;
        this.discount1 = parcel.readFloat();
        this.discount2 = parcel.readFloat();
        this.discount3 = parcel.readFloat();
    }

    /* compiled from: SalesCampaignDetail.kt */
    public static final class CREATOR implements Creator<SalesCampaignDetail> {
        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesCampaignDetail createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesCampaignDetail(source);
        }

        /*  WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SalesCampaignDetail[] newArray(int r1) {
            return new SalesCampaignDetail[r1];
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int r2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.name);
        dest.writeString(this.code);
        dest.writeString(this.unit);
        dest.writeFloat(this.quantity);
        dest.writeInt(this.type);
        dest.writeFloat(this.discountRate);
        dest.writeFloat(this.campaignPoint);
        dest.writeList(this.pointDetails);
        dest.writeFloat(this.surplus);
        dest.writeFloat(this.surplusDiscount);
        dest.writeByte(this.forNetsis ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.discount1);
        dest.writeFloat(this.discount2);
        dest.writeFloat(this.discount3);
    }
}
