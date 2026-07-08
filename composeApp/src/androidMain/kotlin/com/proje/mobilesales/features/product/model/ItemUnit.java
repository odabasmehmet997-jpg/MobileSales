package com.proje.mobilesales.features.product.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ItemUnit implements Parcelable {
    public String code;
    public double convFact1;
    public double convFact2;
    public double grossVolume;
    public double grossWeight;
    public String height;
    private boolean isDivUnit;
    public String length;
    public int lineNr;
    public int logicalRef;
    public double netVolume;
    public double netWeight;
    public String unitCode;
    public String width;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ItemUnit> CREATOR = new Parcelable.Creator<ItemUnit>() {
        public ItemUnit createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "source");
            return new ItemUnit(parcel);
        }
        public ItemUnit[] newArray(final int i) {
            return new ItemUnit[i];
        }
    };
    public int describeContents() {
        return 0;
    }

    public boolean isDivUnit() {
        return isDivUnit;
    }

    public void setDivUnit(final boolean z) {
        isDivUnit = z;
    }

    public ItemUnit() {
    }
    public void writeToParcel(final Parcel parcel, final int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeInt(logicalRef);
        parcel.writeString(code);
        parcel.writeString(unitCode);
        parcel.writeDouble(convFact1);
        parcel.writeDouble(convFact2);
        parcel.writeInt(lineNr);
        parcel.writeDouble(netVolume);
        parcel.writeDouble(grossVolume);
        parcel.writeDouble(netWeight);
        parcel.writeDouble(grossWeight);
        parcel.writeByte(isDivUnit ? (byte) 1 : 0);
        parcel.writeString(width);
        parcel.writeString(length);
        parcel.writeString(height);
    }
    public ItemUnit(final Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "in");
        logicalRef = parcel.readInt();
        code = parcel.readString();
        unitCode = parcel.readString();
        convFact1 = parcel.readDouble();
        convFact2 = parcel.readDouble();
        lineNr = parcel.readInt();
        netVolume = parcel.readDouble();
        grossVolume = parcel.readDouble();
        netWeight = parcel.readDouble();
        grossWeight = parcel.readDouble();
        isDivUnit = 0 != parcel.readByte();
        width = parcel.readString();
        length = parcel.readString();
        height = parcel.readString();
    }
    public static final class Companion {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
