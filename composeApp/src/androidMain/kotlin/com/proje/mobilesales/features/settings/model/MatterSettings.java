package com.proje.mobilesales.features.settings.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.features.settings.model.enums.MatterArea;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class MatterSettings implements Parcelable {
    private String firstMatterNo;
    private boolean isUseMatterNo;
    private String lastMatterNo;
    private MatterArea matterArea;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<MatterSettings> CREATOR = new Parcelable.Creator<MatterSettings>() {  
 
        public MatterSettings createFromParcel(final Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new MatterSettings(source);
        } 
        public MatterSettings[] newArray(final int i2) {
            return new MatterSettings[i2];
        }
    };
    public MatterSettings() {
        this(false, null, null, null, 15, null);
    }
    public static   MatterSettings copydefault(final MatterSettings matterSettings, boolean z, String str, String str2, MatterArea matterArea, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            z = matterSettings.isUseMatterNo;
        }
        if (0 != (i2 & 2)) {
            str = matterSettings.firstMatterNo;
        }
        if (0 != (i2 & 4)) {
            str2 = matterSettings.lastMatterNo;
        }
        if (0 != (i2 & 8)) {
            matterArea = matterSettings.matterArea;
        }
        return matterSettings.copy(z, str, str2, matterArea);
    }
    public boolean component1() {
        return isUseMatterNo;
    }
    public String component2() {
        return firstMatterNo;
    }
    public String component3() {
        return lastMatterNo;
    }
    public MatterArea component4() {
        return matterArea;
    }
    public MatterSettings copy(final boolean z, final String str, final String str2, final MatterArea matterArea) {
        return new MatterSettings(z, str, str2, matterArea);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MatterSettings matterSettings)) {
            return false;
        }
        return isUseMatterNo == matterSettings.isUseMatterNo && Intrinsics.areEqual(firstMatterNo, matterSettings.firstMatterNo) && Intrinsics.areEqual(lastMatterNo, matterSettings.lastMatterNo) && matterArea == matterSettings.matterArea;
    }
    public int hashCode() {
        final int hashCode = Boolean.hashCode(isUseMatterNo) * 31;
        final String str = firstMatterNo;
        final int hashCode2 = (hashCode + (null == str ? 0 : str.hashCode())) * 31;
        final String str2 = lastMatterNo;
        final int hashCode3 = (hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31;
        final MatterArea matterArea = this.matterArea;
        return hashCode3 + (null != matterArea ? matterArea.hashCode() : 0);
    }
    public String toString() {
        return "MatterSettings(isUseMatterNo=" + isUseMatterNo + ", firstMatterNo=" + firstMatterNo + ", lastMatterNo=" + lastMatterNo + ", matterArea=" + matterArea + ')';
    }
    public MatterSettings(final boolean z, final String str, final String str2, final MatterArea matterArea) {
        isUseMatterNo = z;
        firstMatterNo = str;
        lastMatterNo = str2;
        this.matterArea = matterArea;
    }
    public MatterSettings(final boolean z, final String str, final String str2, final MatterArea matterArea, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 == (i2 & 1) && z, 0 != (i2 & 2) ? null : str, 0 != (i2 & 4) ? null : str2, 0 != (i2 & 8) ? null : matterArea);
    }
    public boolean isUseMatterNo() {
        return isUseMatterNo;
    }
    public void setUseMatterNo(final boolean z) {
        isUseMatterNo = z;
    }
    public String getFirstMatterNo() {
        return firstMatterNo;
    }
    public void setFirstMatterNo(final String str) {
        firstMatterNo = str;
    }
    public String getLastMatterNo() {
        return lastMatterNo;
    }
    public void setLastMatterNo(final String str) {
        lastMatterNo = str;
    }
    public MatterArea getMatterArea() {
        return matterArea;
    }
    public void setMatterArea(final MatterArea matterArea) {
        this.matterArea = matterArea;
    }
    public MatterSettings(final Parcel parcel) {
        this(false, null, null, null, 15, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        isUseMatterNo = 0 != parcel.readByte();
        firstMatterNo = parcel.readString();
        lastMatterNo = parcel.readString();
        final int readInt = parcel.readInt();
        matterArea = -1 == readInt ? null : MatterArea.values()[readInt];
    }
    public void writeToParcel(final Parcel dest, final int i2) {
        final int ordinal;
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeByte(isUseMatterNo ? (byte) 1 : (byte) 0);
        dest.writeString(firstMatterNo);
        dest.writeString(lastMatterNo);
        final MatterArea matterArea = this.matterArea;
        if (null == matterArea) {
            ordinal = -1;
        } else {
            Intrinsics.checkNotNull(matterArea);
            ordinal = matterArea.ordinal();
        }
        dest.writeInt(ordinal);
    }
    public static final class Companion {
        public  Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
