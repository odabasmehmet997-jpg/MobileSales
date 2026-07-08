package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;

public class FicheDiscountProp extends FicheStringProp {
    public static final Parcelable.Creator<FicheDiscountProp> CREATOR = new Parcelable.Creator<FicheDiscountProp>() {
        public FicheDiscountProp createFromParcel(final Parcel parcel) {
            return new FicheDiscountProp(parcel);
        }

        public FicheDiscountProp[] newArray(final int i2) {
            return new FicheDiscountProp[i2];
        }
    };
    private boolean boundedToCard;
    private String campaignCode;
    private String campaignLineNo;
    private String guid;

    public int describeContents() {
        return 0;
    }

    public FicheDiscountProp(final String str) {
        super(str);
        guid = UUID.randomUUID().toString();
    }

    public FicheDiscountProp() {
        guid = UUID.randomUUID().toString();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(final String str) {
        guid = str;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(final String str) {
        campaignCode = str;
    }

    public String getCampaignLineNo() {
        return campaignLineNo;
    }

    public void setCampaignLineNo(final String str) {
        campaignLineNo = str;
    }

    public FicheDiscountProp mo1410clone() throws CloneNotSupportedException {
        final FicheDiscountProp ficheDiscountProp = (FicheDiscountProp) super.mo1410clone();
        final String str = guid;
        ficheDiscountProp.guid = null != str ? str : "";
        final String str2 = campaignCode;
        ficheDiscountProp.campaignCode = null != str2 ? str2 : "";
        final String str3 = campaignLineNo;
        ficheDiscountProp.campaignLineNo = null != str3 ? str3 : "";
        ficheDiscountProp.boundedToCard = boundedToCard;
        return ficheDiscountProp;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(guid);
        parcel.writeString(campaignCode);
        parcel.writeString(campaignLineNo);
        parcel.writeInt(boundedToCard ? 1 : 0);
    }

    protected FicheDiscountProp(final Parcel parcel) {
        super(parcel);
        guid = parcel.readString();
        campaignCode = parcel.readString();
        campaignLineNo = parcel.readString();
        boundedToCard = 1 == parcel.readInt();
    }

    public boolean isBoundedToCard() {
        return boundedToCard;
    }

    public void setBoundedToCard(final boolean z) {
        boundedToCard = z;
    }
}
