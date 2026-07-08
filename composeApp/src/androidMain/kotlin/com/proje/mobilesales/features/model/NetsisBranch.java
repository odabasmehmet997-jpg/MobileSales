package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class NetsisBranch implements Parcelable {
    public static final Parcelable.Creator<NetsisBranch> CREATOR = new Parcelable.Creator<NetsisBranch>() {
        public NetsisBranch createFromParcel(final Parcel parcel) {
            return new NetsisBranch(parcel);
        }

        public NetsisBranch[] newArray(final int i2) {
            return new NetsisBranch[i2];
        }
    };

    private String isletmeIsmi;

    private int isletmeKodu;

    private boolean merkezMi;

    private String subeIsmi;

    private int subeKodu;

    public int describeContents() {
        return 0;
    }

    public int getSubeKodu() {
        return subeKodu;
    }

    public void setSubeKodu(final int i2) {
        subeKodu = i2;
    }

    public String getSubeIsmi() {
        return subeIsmi;
    }

    public void setSubeIsmi(final String str) {
        subeIsmi = str;
    }

    public int getIsletmeKodu() {
        return isletmeKodu;
    }

    public void setIsletmeKodu(final int i2) {
        isletmeKodu = i2;
    }

    public String getIsletmeIsmi() {
        return isletmeIsmi;
    }

    public void setIsletmeIsmi(final String str) {
        isletmeIsmi = str;
    }

    public boolean isMerkezMi() {
        return merkezMi;
    }

    public void setMerkezMi(final boolean z) {
        merkezMi = z;
    }

    public String getBranchCodeAndName() {
        return this.getCodeAndName(this.subeKodu, (TextUtils.isEmpty(this.subeIsmi) && this.merkezMi) ? "Merkez" : this.subeIsmi);
    }

    public String getEnterpriseCodeAndName() {
        return this.getCodeAndName(this.isletmeKodu, this.isletmeIsmi);
    }

    private String getCodeAndName(final int i2, final String str) {
        return String.format("%d - %s", Integer.valueOf(i2), str);
    }

    public String toString() {
        return String.format("%s\n%s", this.getEnterpriseCodeAndName(), this.getBranchCodeAndName());
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(subeKodu);
        parcel.writeString(subeIsmi);
        parcel.writeInt(isletmeKodu);
        parcel.writeString(isletmeIsmi);
        parcel.writeByte(merkezMi ? (byte) 1 : (byte) 0);
    }

    public NetsisBranch() {
    }

    protected NetsisBranch(final Parcel parcel) {
        subeKodu = parcel.readInt();
        subeIsmi = parcel.readString();
        isletmeKodu = parcel.readInt();
        isletmeIsmi = parcel.readString();
        merkezMi = 0 != parcel.readByte();
    }
}
