package com.proje.mobilesales.features.penetration.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PenetrationShort implements Parcelable {
    private String date;
    private String guid;
    private int f1258id;
    private boolean isTransfer;
    private int penetrationID;
    private String penetrationName;
    public static final Companion Companion = new Companion(null);
    private static final Parcelable.Creator<PenetrationShort> CREATOR = new Parcelable.Creator<PenetrationShort>() {
        public PenetrationShort createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new PenetrationShort(parcel);
        }
        public PenetrationShort[] newArray(final int i) {
            return new PenetrationShort[i];
        }
    };
    public int describeContents() {
        return 0;
    }
    public String getPenetrationName() {
        return penetrationName;
    }
    public void setPenetrationName(final String str) {
        penetrationName = str;
    }
    public String getDate() {
        return date;
    }
    public void setDate(final String str) {
        date = str;
    }
    public boolean isTransfer() {
        return isTransfer;
    }
    public void setTransfer(final boolean z) {
        isTransfer = z;
    }
    public int getId() {
        return f1258id;
    }
    public void setId(final int i) {
        f1258id = i;
    }
    public String getGuid() {
        return guid;
    }
    public void setGuid(final String str) {
        guid = str;
    }
    public int getPenetrationID() {
        return penetrationID;
    }
    public void setPenetrationID(final int i) {
        penetrationID = i;
    }
    public PenetrationShort() {
    }
    public PenetrationShort(final Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "in");
        penetrationName = parcel.readString();
        date = parcel.readString();
        isTransfer = 0 != parcel.readByte();
        f1258id = parcel.readInt();
        guid = parcel.readString();
        penetrationID = parcel.readInt();
    }
    public void writeToParcel(final Parcel parcel, final int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeString(penetrationName);
        parcel.writeString(date);
        parcel.writeByte(isTransfer ? (byte) 1 : 0);
        parcel.writeInt(f1258id);
        parcel.writeString(guid);
        parcel.writeInt(penetrationID);
    }
    public static final class Companion {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Parcelable.Creator<PenetrationShort> getCREATOR() {
            return PenetrationShort.CREATOR;
        }
    }
}
