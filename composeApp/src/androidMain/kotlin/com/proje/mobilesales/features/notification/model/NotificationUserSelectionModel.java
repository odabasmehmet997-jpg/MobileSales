package com.proje.mobilesales.features.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationUserSelectionModel.kt */
@SafeType

public final class NotificationUserSelectionModel implements Parcelable {
    public static final Parcelable.Creator<NotificationUserSelectionModel> CREATOR = new Creator();
    @SerializedName("USERID")
    @Column(name = "USERID")

    /* renamed from: id */
    private int f1255id;
    @SerializedName("FNAME")
    @Column(name = "FNAME")
    private String name;
    private boolean selected;
    @SerializedName("LNAME")
    @Column(name = "LNAME")
    private String surname;
    @SerializedName("USERNAME")
    @Column(name = "USERNAME")
    private String userName;

    /* compiled from: NotificationUserSelectionModel.kt */
    
    public static final class Creator implements Parcelable.Creator<NotificationUserSelectionModel> {
        
        public NotificationUserSelectionModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new NotificationUserSelectionModel(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
        }

        
        public NotificationUserSelectionModel[] newArray(int i) {
            return new NotificationUserSelectionModel[i];
        }
    }

    public static NotificationUserSelectionModel copydefault(NotificationUserSelectionModel notificationUserSelectionModel, int i, String str, String str2, String str3, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = notificationUserSelectionModel.f1255id;
        }
        if ((i2 & 2) != 0) {
            str = notificationUserSelectionModel.userName;
        }
        if ((i2 & 4) != 0) {
            str2 = notificationUserSelectionModel.name;
        }
        if ((i2 & 8) != 0) {
            str3 = notificationUserSelectionModel.surname;
        }
        if ((i2 & 16) != 0) {
            z = notificationUserSelectionModel.selected;
        }
        return notificationUserSelectionModel.copy(i, str, str2, str3, z);
    }

    public int component1() {
        return this.f1255id;
    }

    public String component2() {
        return this.userName;
    }

    public String component3() {
        return this.name;
    }

    public String component4() {
        return this.surname;
    }

    public boolean component5() {
        return this.selected;
    }

    public NotificationUserSelectionModel copy(int i, String str, String str2, String str3, boolean z) {
        Intrinsics.checkNotNullParameter(str, "userName");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "surname");
        return new NotificationUserSelectionModel(i, str, str2, str3, z);
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
        if (!(obj instanceof NotificationUserSelectionModel notificationUserSelectionModel)) {
            return false;
        }
        return this.f1255id == notificationUserSelectionModel.f1255id && Intrinsics.areEqual(this.userName, notificationUserSelectionModel.userName) && Intrinsics.areEqual(this.name, notificationUserSelectionModel.name) && Intrinsics.areEqual(this.surname, notificationUserSelectionModel.surname) && this.selected == notificationUserSelectionModel.selected;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return (((((((Integer.hashCode(this.f1255id) * 31) + this.userName.hashCode()) * 31) + this.name.hashCode()) * 31) + this.surname.hashCode()) * 31) + Boolean.hashCode(this.selected);
    }

    @Override // java.lang.Object
    public String toString() {
        return "NotificationUserSelectionModel(id=" + this.f1255id + ", userName=" + this.userName + ", name=" + this.name + ", surname=" + this.surname + ", selected=" + this.selected + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.f1255id);
        parcel.writeString(this.userName);
        parcel.writeString(this.name);
        parcel.writeString(this.surname);
        parcel.writeInt(this.selected ? 1 : 0);
    }

    public NotificationUserSelectionModel(int i, String str, String str2, String str3, boolean z) {
        Intrinsics.checkNotNullParameter(str, "userName");
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(str3, "surname");
        this.f1255id = i;
        this.userName = str;
        this.name = str2;
        this.surname = str3;
        this.selected = z;
    }

    public NotificationUserSelectionModel(int i, String str, String str2, String str3, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, (i2 & 16) == 0 && z);
    }

    public int getId() {
        return this.f1255id;
    }

    public void setId(int i) {
        this.f1255id = i;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.surname = str;
    }

    public boolean getSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public NotificationUserSelectionModel() {
        this(0, "", "", "", false);
    }

    public String getUserDescription() {
        return this.userName + " - " + this.name + ' ' + this.surname;
    }
}
