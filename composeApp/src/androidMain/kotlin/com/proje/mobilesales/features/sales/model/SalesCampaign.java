package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.FrameMetricsAggregator;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class SalesCampaign implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private ArrayList<SalesCampaignDetail> details;
    private boolean forNetsis;
    private float netTotal;
    private float total;
    private float totalDiscount;
    private float totalGeneralDiscount;
    private float totalKdv;
    private float totalLineDiscount;
    private float totalSurplusDiscount;
    public SalesCampaign() {
        this(null, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, 0.0f, 0.0f, FrameMetricsAggregator.EVERY_DURATION, null);
    }
    public  ArrayList<SalesCampaignDetail> component1() {
        return this.details;
    }
    public  float component2() {
        return this.totalDiscount;
    }
    public  float component3() {
        return this.total;
    }
    public  float component4() {
        return this.totalKdv;
    }
    public  float component5() {
        return this.netTotal;
    }
    public  boolean component6() {
        return this.forNetsis;
    }
    public  float component7() {
        return this.totalLineDiscount;
    }
    public  float component8() {
        return this.totalSurplusDiscount;
    }
    public  float component9() {
        return this.totalGeneralDiscount;
    }
    public  SalesCampaign copy(ArrayList<SalesCampaignDetail> details, float f2, float f3, float f4, float f5, boolean z, float f6, float f7, float f8) {
        Intrinsics.checkNotNullParameter(details, "details");
        return new SalesCampaign(details, f2, f3, f4, f5, z, f6, f7, f8);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesCampaign salesCampaign)) {
            return false;
        }
        return Intrinsics.areEqual(this.details, salesCampaign.details) && Float.compare(this.totalDiscount, salesCampaign.totalDiscount) == 0 && Float.compare(this.total, salesCampaign.total) == 0 && Float.compare(this.totalKdv, salesCampaign.totalKdv) == 0 && Float.compare(this.netTotal, salesCampaign.netTotal) == 0 && this.forNetsis == salesCampaign.forNetsis && Float.compare(this.totalLineDiscount, salesCampaign.totalLineDiscount) == 0 && Float.compare(this.totalSurplusDiscount, salesCampaign.totalSurplusDiscount) == 0 && Float.compare(this.totalGeneralDiscount, salesCampaign.totalGeneralDiscount) == 0;
    }
    public int hashCode() {
        return (((((((((((((((this.details.hashCode() * 31) + Float.hashCode(this.totalDiscount)) * 31) + Float.hashCode(this.total)) * 31) + Float.hashCode(this.totalKdv)) * 31) + Float.hashCode(this.netTotal)) * 31) + Boolean.hashCode(this.forNetsis)) * 31) + Float.hashCode(this.totalLineDiscount)) * 31) + Float.hashCode(this.totalSurplusDiscount)) * 31) + Float.hashCode(this.totalGeneralDiscount);
    }
    public String toString() {
        return "SalesCampaign(details=" + this.details + ", totalDiscount=" + this.totalDiscount + ", total=" + this.total + ", totalKdv=" + this.totalKdv + ", netTotal=" + this.netTotal + ", forNetsis=" + this.forNetsis + ", totalLineDiscount=" + this.totalLineDiscount + ", totalSurplusDiscount=" + this.totalSurplusDiscount + ", totalGeneralDiscount=" + this.totalGeneralDiscount + ')';
    }
    public SalesCampaign(ArrayList<SalesCampaignDetail> details, float f2, float f3, float f4, float f5, boolean z, float f6, float f7, float f8) {
        Intrinsics.checkNotNullParameter(details, "details");
        this.details = details;
        this.totalDiscount = f2;
        this.total = f3;
        this.totalKdv = f4;
        this.netTotal = f5;
        this.forNetsis = z;
        this.totalLineDiscount = f6;
        this.totalSurplusDiscount = f7;
        this.totalGeneralDiscount = f8;
    }
    public SalesCampaign(ArrayList arrayList, float f2, float f3, float f4, float f5, boolean z, float f6, float f7, float f8, int r20, DefaultConstructorMarker defaultConstructorMarker) {
        this((r20 & 1) != 0 ? new ArrayList() : arrayList, (r20 & 2) != 0 ? 0.0f : f2, (r20 & 4) != 0 ? 0.0f : f3, (r20 & 8) != 0 ? 0.0f : f4, (r20 & 16) != 0 ? 0.0f : f5, (r20 & 32) == 0 && z, (r20 & 64) != 0 ? 0.0f : f6, (r20 & 128) != 0 ? 0.0f : f7, (r20 & 256) == 0 ? f8 : 0.0f);
    }
    public  ArrayList<SalesCampaignDetail> getDetails() {
        return this.details;
    }
    public  void setDetails(ArrayList<SalesCampaignDetail> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.details = arrayList;
    }
    public  float getTotalDiscount() {
        return this.totalDiscount;
    }
    public  void setTotalDiscount(float f2) {
        this.totalDiscount = f2;
    }
    public  float getTotal() {
        return this.total;
    }
    public  void setTotal(float f2) {
        this.total = f2;
    }
    public  float getTotalKdv() {
        return this.totalKdv;
    }
    public  void setTotalKdv(float f2) {
        this.totalKdv = f2;
    }
    public  float getNetTotal() {
        return this.netTotal;
    }
    public  void setNetTotal(float f2) {
        this.netTotal = f2;
    }
    public  boolean getForNetsis() {
        return this.forNetsis;
    }
    public  void setForNetsis(boolean z) {
        this.forNetsis = z;
    }
    public  float getTotalLineDiscount() {
        return this.totalLineDiscount;
    }
    public  void setTotalLineDiscount(float f2) {
        this.totalLineDiscount = f2;
    }
    public  float getTotalSurplusDiscount() {
        return this.totalSurplusDiscount;
    }
    public  void setTotalSurplusDiscount(float f2) {
        this.totalSurplusDiscount = f2;
    }
    public  float getTotalGeneralDiscount() {
        return this.totalGeneralDiscount;
    }
    public  void setTotalGeneralDiscount(float f2) {
        this.totalGeneralDiscount = f2;
    }
    private SalesCampaign(Parcel parcel) {
        this(null, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, 0.0f, 0.0f, FrameMetricsAggregator.EVERY_DURATION, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        ArrayList<SalesCampaignDetail> arrayList = new ArrayList<>();
        this.details = arrayList;
        parcel.readList(arrayList, SalesCampaignDetail.class.getClassLoader());
        this.totalDiscount = parcel.readFloat();
        this.total = parcel.readFloat();
        this.totalKdv = parcel.readFloat();
        this.netTotal = parcel.readFloat();
        this.forNetsis = parcel.readByte() != 0;
        this.totalLineDiscount = parcel.readFloat();
        this.totalSurplusDiscount = parcel.readFloat();
        this.totalGeneralDiscount = parcel.readFloat();
    }
    public static final class CREATOR implements Creator<SalesCampaign> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public SalesCampaign createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesCampaign(source);
        }
        public SalesCampaign[] newArray(int r1) {
            return new SalesCampaign[r1];
        }
    }
    public void writeToParcel(Parcel dest, int r2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeList(this.details);
        dest.writeFloat(this.totalDiscount);
        dest.writeFloat(this.total);
        dest.writeFloat(this.totalKdv);
        dest.writeFloat(this.netTotal);
        dest.writeByte(this.forNetsis ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.totalLineDiscount);
        dest.writeFloat(this.totalSurplusDiscount);
        dest.writeFloat(this.totalGeneralDiscount);
    }
}
