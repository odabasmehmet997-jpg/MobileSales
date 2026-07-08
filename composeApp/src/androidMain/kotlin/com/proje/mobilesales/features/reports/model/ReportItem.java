package com.proje.mobilesales.features.reports.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.strategy.Name;

public final class ReportItem implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String createdDate;
    public String description;
    private String designContent;
    private int id;
    private String modifiedBy;
    private String modifiedDate;
    private String name;
    private int platformType;
    private String queryContent;
    private int reportType;

    public ReportItem() {
        this(0, null, null, null, null, null, null, null, 0, 0, 1023, null);
    }

    public  ReportItem(final Parcel parcel, final DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public int component1() {
        return id;
    }

    public int component10() {
        return platformType;
    }

    public String component2() {
        return name;
    }

    public String component3() {
        return description;
    }

    public String component4() {
        return queryContent;
    }

    public String component5() {
        return designContent;
    }

    public String component6() {
        return createdDate;
    }

    public String component7() {
        return modifiedBy;
    }

    public String component8() {
        return modifiedDate;
    }

    public int component9() {
        return reportType;
    }

    public ReportItem copy(final int i2, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final int i3, final int i4) {
        return new ReportItem(i2, str, str2, str3, str4, str5, str6, str7, i3, i4);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReportItem reportItem)) {
            return false;
        }
        return id == reportItem.id && Intrinsics.areEqual(name, reportItem.name) && Intrinsics.areEqual(description, reportItem.description) && Intrinsics.areEqual(queryContent, reportItem.queryContent) && Intrinsics.areEqual(designContent, reportItem.designContent) && Intrinsics.areEqual(createdDate, reportItem.createdDate) && Intrinsics.areEqual(modifiedBy, reportItem.modifiedBy) && Intrinsics.areEqual(modifiedDate, reportItem.modifiedDate) && reportType == reportItem.reportType && platformType == reportItem.platformType;
    }

    public int hashCode() {
        final int hashCode = Integer.hashCode(id) * 31;
        final String str = name;
        final int hashCode2 = (hashCode + (null == str ? 0 : str.hashCode())) * 31;
        final String str2 = description;
        final int hashCode3 = (hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31;
        final String str3 = queryContent;
        final int hashCode4 = (hashCode3 + (null == str3 ? 0 : str3.hashCode())) * 31;
        final String str4 = designContent;
        final int hashCode5 = (hashCode4 + (null == str4 ? 0 : str4.hashCode())) * 31;
        final String str5 = createdDate;
        final int hashCode6 = (hashCode5 + (null == str5 ? 0 : str5.hashCode())) * 31;
        final String str6 = modifiedBy;
        final int hashCode7 = (hashCode6 + (null == str6 ? 0 : str6.hashCode())) * 31;
        final String str7 = modifiedDate;
        return ((((hashCode7 + (null != str7 ? str7.hashCode() : 0)) * 31) + Integer.hashCode(reportType)) * 31) + Integer.hashCode(platformType);
    }

    public String toString() {
        return "ReportItem(id=" + id + ", name=" + name + ", description=" + description + ", queryContent=" + queryContent + ", designContent=" + designContent + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", reportType=" + reportType + ", platformType=" + platformType + ')';
    }

    public ReportItem(final int i2, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final int i3, final int i4) {
        id = i2;
        name = str;
        description = str2;
        queryContent = str3;
        designContent = str4;
        createdDate = str5;
        modifiedBy = str6;
        modifiedDate = str7;
        reportType = i3;
        platformType = i4;
    }

    public   ReportItem(final int i2, final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final int i3, final int i4, final int i5, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i5 & 1) ? 0 : i2, 0 != (i5 & 2) ? null : str, 0 != (i5 & 4) ? null : str2, 0 != (i5 & 8) ? null : str3, 0 != (i5 & 16) ? null : str4, 0 != (i5 & 32) ? null : str5, 0 != (i5 & 64) ? null : str6, 0 == (i5 & 128) ? str7 : null, 0 != (i5 & 256) ? 0 : i3, 0 == (i5 & 512) ? i4 : 0);
    }

    public int getId() {
        return id;
    }

    public void setId(final int i2) {
        id = i2;
    }

    public String getName() {
        return name;
    }

    public void setName(final String str) {
        name = str;
    }

    public String getQueryContent() {
        return queryContent;
    }

    public void setQueryContent(final String str) {
        queryContent = str;
    }

    public String getDesignContent() {
        return designContent;
    }

    public void setDesignContent(final String str) {
        designContent = str;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final String str) {
        createdDate = str;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final String str) {
        modifiedBy = str;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final String str) {
        modifiedDate = str;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(final int i2) {
        reportType = i2;
    }

    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(final int i2) {
        platformType = i2;
    }

    private ReportItem(final Parcel parcel) {
        this(0, null, null, null, null, null, null, null, 0, 0, 1023, null);
        id = parcel.readInt();
        name = parcel.readString();
        description = parcel.readString();
        queryContent = parcel.readString();
        designContent = parcel.readString();
        createdDate = parcel.readString();
        modifiedBy = parcel.readString();
        modifiedDate = parcel.readString();
        reportType = parcel.readInt();
        platformType = parcel.readInt();
    }
    public void writeToParcel(final Parcel dest, final int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(queryContent);
        dest.writeString(designContent);
        dest.writeString(createdDate);
        dest.writeString(modifiedBy);
        dest.writeString(modifiedDate);
        dest.writeInt(reportType);
        dest.writeInt(platformType);
    }
    public static final class CREATOR implements Parcelable.Creator<ReportItem> {
        public   CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public ReportItem createFromParcel(final Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new ReportItem(source, null);
        }

        public ReportItem[] newArray(final int i2) {
            return new ReportItem[i2];
        }
    }
}
