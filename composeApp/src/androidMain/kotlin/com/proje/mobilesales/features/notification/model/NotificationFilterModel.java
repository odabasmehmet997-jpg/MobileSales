package com.proje.mobilesales.features.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationFilterModel.kt */

public final class NotificationFilterModel implements Parcelable {
    public static final Parcelable.Creator<NotificationFilterModel> CREATOR = new Creator();
    private Date endDate;
    private String searchText;
    private boolean showDeleted;
    private Date startDate;

    /* compiled from: NotificationFilterModel.kt */
    
    public static final class Creator implements Parcelable.Creator<NotificationFilterModel> {
        
        public NotificationFilterModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NotificationFilterModel((Date) parcel.readSerializable(), (Date) parcel.readSerializable(), parcel.readInt() != 0, parcel.readString());
        }

        
        public NotificationFilterModel[] newArray(int i) {
            return new NotificationFilterModel[i];
        }
    }

    public static NotificationFilterModel copydefault(NotificationFilterModel notificationFilterModel, Date date, Date date2, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            date = notificationFilterModel.startDate;
        }
        if ((i & 2) != 0) {
            date2 = notificationFilterModel.endDate;
        }
        if ((i & 4) != 0) {
            z = notificationFilterModel.showDeleted;
        }
        if ((i & 8) != 0) {
            str = notificationFilterModel.searchText;
        }
        return notificationFilterModel.copy(date, date2, z, str);
    }

    public Date component1() {
        return this.startDate;
    }

    public Date component2() {
        return this.endDate;
    }

    public boolean component3() {
        return this.showDeleted;
    }

    public String component4() {
        return this.searchText;
    }

    public NotificationFilterModel copy(Date date, Date date2, boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "searchText");
        return new NotificationFilterModel(date, date2, z, str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationFilterModel notificationFilterModel)) {
            return false;
        }
        return Intrinsics.areEqual(this.startDate, notificationFilterModel.startDate) && Intrinsics.areEqual(this.endDate, notificationFilterModel.endDate) && this.showDeleted == notificationFilterModel.showDeleted && Intrinsics.areEqual(this.searchText, notificationFilterModel.searchText);
    }

    @Override // java.lang.Object
    public int hashCode() {
        Date date = this.startDate;
        int i = 0;
        int hashCode = (date == null ? 0 : date.hashCode()) * 31;
        Date date2 = this.endDate;
        if (date2 != null) {
            i = date2.hashCode();
        }
        return ((((hashCode + i) * 31) + Boolean.hashCode(this.showDeleted)) * 31) + this.searchText.hashCode();
    }

    @Override // java.lang.Object
    public String toString() {
        return "NotificationFilterModel(startDate=" + this.startDate + ", endDate=" + this.endDate + ", showDeleted=" + this.showDeleted + ", searchText=" + this.searchText + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeSerializable(this.startDate);
        parcel.writeSerializable(this.endDate);
        parcel.writeInt(this.showDeleted ? 1 : 0);
        parcel.writeString(this.searchText);
    }

    public NotificationFilterModel(Date date, Date date2, boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "searchText");
        this.startDate = date;
        this.endDate = date2;
        this.showDeleted = z;
        this.searchText = str;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public String getSearchText() {
        return this.searchText;
    }

    public boolean getShowDeleted() {
        return this.showDeleted;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public void setSearchText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.searchText = str;
    }

    public void setShowDeleted(boolean z) {
        this.showDeleted = z;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public NotificationFilterModel() {
        this(null, null, false, "");
    }
}
