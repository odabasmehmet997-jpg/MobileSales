package com.proje.mobilesales.features.sales.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SalesCampaignPointDetail implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String campLineNo;
    private String pCampCode;

    public SalesCampaignPointDetail() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ SalesCampaignPointDetail copydefault(SalesCampaignPointDetail salesCampaignPointDetail, String str, String str2, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            str = salesCampaignPointDetail.pCampCode;
        }
        if ((r3 & 2) != 0) {
            str2 = salesCampaignPointDetail.campLineNo;
        }
        return salesCampaignPointDetail.copy(str, str2);
    }

    public String component1() {
        return this.pCampCode;
    }

    public String component2() {
        return this.campLineNo;
    }

    public SalesCampaignPointDetail copy(String pCampCode, String campLineNo) {
        Intrinsics.checkNotNullParameter(pCampCode, "pCampCode");
        Intrinsics.checkNotNullParameter(campLineNo, "campLineNo");
        return new SalesCampaignPointDetail(pCampCode, campLineNo);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesCampaignPointDetail salesCampaignPointDetail)) {
            return false;
        }
        return Intrinsics.areEqual(this.pCampCode, salesCampaignPointDetail.pCampCode) && Intrinsics.areEqual(this.campLineNo, salesCampaignPointDetail.campLineNo);
    }

    public int hashCode() {
        return (this.pCampCode.hashCode() * 31) + this.campLineNo.hashCode();
    }

    public String toString() {
        return "SalesCampaignPointDetail(pCampCode=" + this.pCampCode + ", campLineNo=" + this.campLineNo + ')';
    }

    public SalesCampaignPointDetail(String pCampCode, String campLineNo) {
        Intrinsics.checkNotNullParameter(pCampCode, "pCampCode");
        Intrinsics.checkNotNullParameter(campLineNo, "campLineNo");
        this.pCampCode = pCampCode;
        this.campLineNo = campLineNo;
    }

    public /* synthetic */ SalesCampaignPointDetail(String str, String str2, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this((r4 & 1) != 0 ? "" : str, (r4 & 2) != 0 ? "" : str2);
    }

    public String getPCampCode() {
        return this.pCampCode;
    }

    public void setPCampCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.pCampCode = str;
    }

    public String getCampLineNo() {
        return this.campLineNo;
    }

    public void setCampLineNo(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.campLineNo = str;
    }
    public SalesCampaignPointDetail(Parcel parcel) {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        this.pCampCode = string == null ? "" : string;
        String string2 = parcel.readString();
        this.campLineNo = string2 != null ? string2 : "";
    }
    public void writeToParcel(Parcel dest, int r2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.pCampCode);
        dest.writeString(this.campLineNo);
    }
    public static final class CREATOR implements Creator<SalesCampaignPointDetail> {
        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public SalesCampaignPointDetail createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new SalesCampaignPointDetail(source);
        }
        public SalesCampaignPointDetail[] newArray(int r1) {
            return new SalesCampaignPointDetail[r1];
        }
    }
}
