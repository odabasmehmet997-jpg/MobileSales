package com.proje.mobilesales.features.visit.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class VisitInfoShort implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private int auto;
    private String clCode;
    private String clName;
    private int id;
    private String shipAddress;
    private String shipAddressExplanation;
    private boolean transfer;
    private String visitDate;
    private String visitNote;
    private String visitReason;
    public VisitInfoShort() {
        this(0, null, null, null, null, null, null, null, false, 0, 1023, null);
    }
    public int component1() {
        return id;
    }
    public int component10() {
        return auto;
    }
    public String component2() {
        return shipAddress;
    }
    public String component3() {
        return shipAddressExplanation;
    }
    public String component4() {
        return visitReason;
    }
    public String component5() {
        return visitNote;
    }
    public String component6() {
        return visitDate;
    }
    public String component7() {
        return clCode;
    }
    public String component8() {
        return clName;
    }
    public boolean component9() {
        return transfer;
    }
    public VisitInfoShort copy(final int i2, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final boolean z, final int i3) {
        return new VisitInfoShort(i2, str, str2, str3, str4, str5, str6, str7, z, i3);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisitInfoShort visitInfoShort)) {
            return false;
        }
        return id == visitInfoShort.id && Intrinsics.areEqual(shipAddress, visitInfoShort.shipAddress) && Intrinsics.areEqual(shipAddressExplanation, visitInfoShort.shipAddressExplanation) && Intrinsics.areEqual(visitReason, visitInfoShort.visitReason) && Intrinsics.areEqual(visitNote, visitInfoShort.visitNote) && Intrinsics.areEqual(visitDate, visitInfoShort.visitDate) && Intrinsics.areEqual(clCode, visitInfoShort.clCode) && Intrinsics.areEqual(clName, visitInfoShort.clName) && transfer == visitInfoShort.transfer && auto == visitInfoShort.auto;
    }
    public int hashCode() {
        final int hashCode = Integer.hashCode(id) * 31;
        final String str = shipAddress;
        final int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        final String str2 = shipAddressExplanation;
        final int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        final String str3 = visitReason;
        final int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        final String str4 = visitNote;
        final int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        final String str5 = visitDate;
        final int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        final String str6 = clCode;
        final int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        final String str7 = clName;
        return ((((hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31) + Boolean.hashCode(transfer)) * 31) + Integer.hashCode(auto);
    }
    public String toString() {
        return "VisitInfoShort(id=" + id + ", shipAddress=" + shipAddress + ", shipAddressExplanation=" + shipAddressExplanation + ", visitReason=" + visitReason + ", visitNote=" + visitNote + ", visitDate=" + visitDate + ", clCode=" + clCode + ", clName=" + clName + ", transfer=" + transfer + ", auto=" + auto + ')';
    }

    public VisitInfoShort(final int i2, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final boolean z, final int i3) {
        id = i2;
        shipAddress = str;
        shipAddressExplanation = str2;
        visitReason = str3;
        visitNote = str4;
        visitDate = str5;
        clCode = str6;
        clName = str7;
        transfer = z;
        auto = i3;
    }

    public  VisitInfoShort(final int i2, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final boolean z, final int i3, final int i4, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i4 & 1) ? 0 : i2, 0 != (i4 & 2) ? null : str, 0 != (i4 & 4) ? null : str2, 0 != (i4 & 8) ? null : str3, 0 != (i4 & 16) ? null : str4, 0 != (i4 & 32) ? null : str5, 0 != (i4 & 64) ? null : str6, 0 == (i4 & 128) ? str7 : null, 0 == (i4 & 256) && z, 0 == (i4 & 512) ? i3 : 0);
    }

    public int getId() {
        return id;
    }

    public void setId(final int i2) {
        id = i2;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(final String str) {
        shipAddress = str;
    }

    public String getShipAddressExplanation() {
        return shipAddressExplanation;
    }

    public void setShipAddressExplanation(final String str) {
        shipAddressExplanation = str;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(final String str) {
        visitReason = str;
    }

    public String getVisitNote() {
        return visitNote;
    }

    public void setVisitNote(final String str) {
        visitNote = str;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(final String str) {
        visitDate = str;
    }

    public String getClCode() {
        return clCode;
    }

    public void setClCode(final String str) {
        clCode = str;
    }

    public String getClName() {
        return clName;
    }

    public void setClName(final String str) {
        clName = str;
    }

    public boolean getTransfer() {
        return transfer;
    }

    public void setTransfer(final boolean z) {
        transfer = z;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(final int i2) {
        auto = i2;
    }

    public VisitInfoShort(final Parcel parcel) {
        this(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), 0 != parcel.readByte(), parcel.readInt());
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(id);
        parcel.writeString(shipAddress);
        parcel.writeString(shipAddressExplanation);
        parcel.writeString(visitReason);
        parcel.writeString(visitNote);
        parcel.writeString(visitDate);
        parcel.writeString(clCode);
        parcel.writeString(clName);
        parcel.writeByte(transfer ? (byte) 1 : (byte) 0);
        parcel.writeInt(auto);
    }
    public static final class CREATOR implements Parcelable.Creator<VisitInfoShort> {
        public  CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public VisitInfoShort createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new VisitInfoShort(parcel);
        }

        public VisitInfoShort[] newArray(final int i2) {
            return new VisitInfoShort[i2];
        }
    }
}
