package com.proje.mobilesales.features.notification.model;

import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import com.proje.mobilesales.core.extensions.DateExtensions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationModel.kt */
@SafeType

public final class NotificationModel {
    @SerializedName("SENDDATE")
    @Column(name = "SENDDATE")
    private String dateSend;
    @SerializedName("MESSAGE")
    @Column(name = "MESSAGE")
    private String message;
    @SerializedName("NOTIFICATIONGUID")
    @Column(name = "NOTIFICATIONGUID")
    private String notificationGuid;
    @SerializedName("NOTIFICATIONID")
    @Column(name = "NOTIFICATIONID")
    private int notificationId;
    @SerializedName("NOTIFIEDUSERGUID")
    @Column(name = "NOTIFIEDUSERGUID")
    private String notifiedUserGuid;
    @SerializedName("NOTIFIEDUSERID")
    @Column(name = "NOTIFIEDUSERID")
    private int notifiedUserId;
    @SerializedName("FNAME")
    @Column(name = "FNAME")
    private String senderFirstName;
    @SerializedName("LNAME")
    @Column(name = "LNAME")
    private String senderLastName;
    @SerializedName("SENDERREF")
    @Column(name = "SENDERREF")
    private int senderRef;
    @SerializedName("USERNAME")
    @Column(name = "USERNAME")
    private String senderUsername;
    @SerializedName("STATUS")
    @Column(name = "STATUS")
    private Integer status;
    @SerializedName("TITLE")
    @Column(name = "TITLE")
    private String title;
    @SerializedName("USERREF")
    @Column(name = "USERREF")
    private int userRef;
    @SerializedName("WORKINGHOURS")
    @Column(name = "WORKINGHOURS")
    private int workingHours;

    public int component1() {
        return this.notificationId;
    }

    public Integer component10() {
        return this.status;
    }

    public String component11() {
        return this.dateSend;
    }

    public int component12() {
        return this.senderRef;
    }

    public int component13() {
        return this.userRef;
    }

    public int component14() {
        return this.workingHours;
    }

    public String component2() {
        return this.notificationGuid;
    }

    public int component3() {
        return this.notifiedUserId;
    }

    public String component4() {
        return this.notifiedUserGuid;
    }

    public String component5() {
        return this.senderFirstName;
    }

    public String component6() {
        return this.senderLastName;
    }

    public String component7() {
        return this.senderUsername;
    }

    public String component8() {
        return this.title;
    }

    public String component9() {
        return this.message;
    }

    public NotificationModel copy(int i, String str, int i2, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, String str8, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(str, "notificationGuid");
        Intrinsics.checkNotNullParameter(str2, "notifiedUserGuid");
        Intrinsics.checkNotNullParameter(str3, "senderFirstName");
        Intrinsics.checkNotNullParameter(str4, "senderLastName");
        Intrinsics.checkNotNullParameter(str5, "senderUsername");
        return new NotificationModel(i, str, i2, str2, str3, str4, str5, str6, str7, num, str8, i3, i4, i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationModel notificationModel)) {
            return false;
        }
        return this.notificationId == notificationModel.notificationId && Intrinsics.areEqual(this.notificationGuid, notificationModel.notificationGuid) && this.notifiedUserId == notificationModel.notifiedUserId && Intrinsics.areEqual(this.notifiedUserGuid, notificationModel.notifiedUserGuid) && Intrinsics.areEqual(this.senderFirstName, notificationModel.senderFirstName) && Intrinsics.areEqual(this.senderLastName, notificationModel.senderLastName) && Intrinsics.areEqual(this.senderUsername, notificationModel.senderUsername) && Intrinsics.areEqual(this.title, notificationModel.title) && Intrinsics.areEqual(this.message, notificationModel.message) && Intrinsics.areEqual(this.status, notificationModel.status) && Intrinsics.areEqual(this.dateSend, notificationModel.dateSend) && this.senderRef == notificationModel.senderRef && this.userRef == notificationModel.userRef && this.workingHours == notificationModel.workingHours;
    }

    public int hashCode() {
        int hashCode = ((((((((((((Integer.hashCode(this.notificationId) * 31) + this.notificationGuid.hashCode()) * 31) + Integer.hashCode(this.notifiedUserId)) * 31) + this.notifiedUserGuid.hashCode()) * 31) + this.senderFirstName.hashCode()) * 31) + this.senderLastName.hashCode()) * 31) + this.senderUsername.hashCode()) * 31;
        String str = this.title;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.message;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.status;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.dateSend;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return ((((((hashCode4 + i) * 31) + Integer.hashCode(this.senderRef)) * 31) + Integer.hashCode(this.userRef)) * 31) + Integer.hashCode(this.workingHours);
    }

    public String toString() {
        return "NotificationModel(notificationId=" + this.notificationId + ", notificationGuid=" + this.notificationGuid + ", notifiedUserId=" + this.notifiedUserId + ", notifiedUserGuid=" + this.notifiedUserGuid + ", senderFirstName=" + this.senderFirstName + ", senderLastName=" + this.senderLastName + ", senderUsername=" + this.senderUsername + ", title=" + this.title + ", message=" + this.message + ", status=" + this.status + ", dateSend=" + this.dateSend + ", senderRef=" + this.senderRef + ", userRef=" + this.userRef + ", workingHours=" + this.workingHours + ')';
    }

    public NotificationModel(int i, String str, int i2, String str2, String str3, String str4, String str5, String str6, String str7, Integer num, String str8, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(str, "notificationGuid");
        Intrinsics.checkNotNullParameter(str2, "notifiedUserGuid");
        Intrinsics.checkNotNullParameter(str3, "senderFirstName");
        Intrinsics.checkNotNullParameter(str4, "senderLastName");
        Intrinsics.checkNotNullParameter(str5, "senderUsername");
        this.notificationId = i;
        this.notificationGuid = str;
        this.notifiedUserId = i2;
        this.notifiedUserGuid = str2;
        this.senderFirstName = str3;
        this.senderLastName = str4;
        this.senderUsername = str5;
        this.title = str6;
        this.message = str7;
        this.status = num;
        this.dateSend = str8;
        this.senderRef = i3;
        this.userRef = i4;
        this.workingHours = i5;
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(int i) {
        this.notificationId = i;
    }

    public String getNotificationGuid() {
        return this.notificationGuid;
    }

    public void setNotificationGuid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.notificationGuid = str;
    }

    public int getNotifiedUserId() {
        return this.notifiedUserId;
    }

    public void setNotifiedUserId(int i) {
        this.notifiedUserId = i;
    }

    public String getNotifiedUserGuid() {
        return this.notifiedUserGuid;
    }

    public void setNotifiedUserGuid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.notifiedUserGuid = str;
    }

    public String getSenderFirstName() {
        return this.senderFirstName;
    }

    public void setSenderFirstName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.senderFirstName = str;
    }

    public String getSenderLastName() {
        return this.senderLastName;
    }

    public void setSenderLastName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.senderLastName = str;
    }

    public String getSenderUsername() {
        return this.senderUsername;
    }

    public void setSenderUsername(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.senderUsername = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public String getDateSend() {
        return this.dateSend;
    }

    public void setDateSend(String str) {
        this.dateSend = str;
    }

    public int getSenderRef() {
        return this.senderRef;
    }

    public void setSenderRef(int i) {
        this.senderRef = i;
    }

    public int getUserRef() {
        return this.userRef;
    }

    public void setUserRef(int i) {
        this.userRef = i;
    }

    public int getWorkingHours() {
        return this.workingHours;
    }

    public void setWorkingHours(int i) {
        this.workingHours = i;
    }

    public NotificationModel() {
        this(0, "", 0, "", "", "", "", "", "", -1, "", 0, 0, 0);
    }

    public NotificationModel(String str, String str2, String str3, Integer num, String str4, int i) {
        this(0, str, 0, "", "", "", "", str2, str3, num, str4, 0, 0, i);
        Intrinsics.checkNotNullParameter(str, "notificationGuid");
    }

    public String getSenderName() {
        return this.senderUsername + " - " + this.senderFirstName + ' ' + this.senderLastName + ' ';
    }

    public CharSequence getRelativeTime() {
        String str = this.dateSend;
        if (str == null || str.length() == 0) {
            return "";
        }
        return DateExtensions.formatDatedefault(DateExtensions.toDatedefault(this.dateSend, null, 1, null), null, 1, null);
    }
}
