package com.proje.mobilesales.features.notification.model;

import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import com.proje.mobilesales.core.enums.NotificationStatus;
import com.proje.mobilesales.core.extensions.DateExtensions;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotifiedUserModel.kt */
@SafeType

public final class NotifiedUserModel {
    @SerializedName("DELETIONDATE")
    @Column(name = "DELETIONDATE")
    private String dateDeleted;
    @SerializedName("DELIVEREDDATE")
    @Column(name = "DELIVEREDDATE")
    private String dateDelivered;
    @SerializedName("READDATE")
    @Column(name = "READDATE")
    private String dateRead;
    @SerializedName("SENDDATE")
    @Column(name = "SENDDATE")
    private String dateSend;
    @SerializedName("NOTIFICATIONGUID")
    @Column(name = "NOTIFICATIONGUID")
    private String notificationGuid;
    @SerializedName("NOTIFIEDUSERGUID")
    @Column(name = "NOTIFIEDUSERGUID")
    private String notifiedUserGuid;
    @SerializedName("NOTIFIEDUSERID")
    @Column(name = "NOTIFIEDUSERID")
    private int notifiedUserId;
    @SerializedName("RECEIVERNAME")
    @Column(name = "RECEIVERNAME")
    private String receiverFirstName;
    @SerializedName("RECEIVERLASTNAME")
    @Column(name = "RECEIVERLASTNAME")
    private String receiverLastName;
    @SerializedName("RECEIVERUSERNAME")
    @Column(name = "RECEIVERUSERNAME")
    private String receiverUsername;
    @SerializedName("STATUS")
    @Column(name = "STATUS")
    private Integer status;
    @SerializedName("USERREF")
    @Column(name = "USERREF")
    private int userRef;

    /* compiled from: NotifiedUserModel.kt */
    
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[NotificationStatus.values().length];
            try {
                iArr[NotificationStatus.Created.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NotificationStatus.Delivered.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NotificationStatus.Read.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[NotificationStatus.Deleted.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    public int component1() {
        return this.notifiedUserId;
    }

    public int component10() {
        return this.userRef;
    }

    public String component11() {
        return this.dateSend;
    }

    public String component12() {
        return this.notificationGuid;
    }

    public String component2() {
        return this.notifiedUserGuid;
    }

    public String component3() {
        return this.receiverFirstName;
    }

    public String component4() {
        return this.receiverLastName;
    }

    public String component5() {
        return this.receiverUsername;
    }

    public Integer component6() {
        return this.status;
    }

    public String component7() {
        return this.dateDelivered;
    }

    public String component8() {
        return this.dateRead;
    }

    public String component9() {
        return this.dateDeleted;
    }

    public NotifiedUserModel copy(int i, String str, String str2, String str3, String str4, Integer num, String str5, String str6, String str7, int i2, String str8, String str9) {
        Intrinsics.checkNotNullParameter(str, "notifiedUserGuid");
        Intrinsics.checkNotNullParameter(str2, "receiverFirstName");
        Intrinsics.checkNotNullParameter(str3, "receiverLastName");
        Intrinsics.checkNotNullParameter(str4, "receiverUsername");
        Intrinsics.checkNotNullParameter(str9, "notificationGuid");
        return new NotifiedUserModel(i, str, str2, str3, str4, num, str5, str6, str7, i2, str8, str9);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotifiedUserModel notifiedUserModel)) {
            return false;
        }
        return this.notifiedUserId == notifiedUserModel.notifiedUserId && Intrinsics.areEqual(this.notifiedUserGuid, notifiedUserModel.notifiedUserGuid) && Intrinsics.areEqual(this.receiverFirstName, notifiedUserModel.receiverFirstName) && Intrinsics.areEqual(this.receiverLastName, notifiedUserModel.receiverLastName) && Intrinsics.areEqual(this.receiverUsername, notifiedUserModel.receiverUsername) && Intrinsics.areEqual(this.status, notifiedUserModel.status) && Intrinsics.areEqual(this.dateDelivered, notifiedUserModel.dateDelivered) && Intrinsics.areEqual(this.dateRead, notifiedUserModel.dateRead) && Intrinsics.areEqual(this.dateDeleted, notifiedUserModel.dateDeleted) && this.userRef == notifiedUserModel.userRef && Intrinsics.areEqual(this.dateSend, notifiedUserModel.dateSend) && Intrinsics.areEqual(this.notificationGuid, notifiedUserModel.notificationGuid);
    }

    public int hashCode() {
        int hashCode = ((((((((Integer.hashCode(this.notifiedUserId) * 31) + this.notifiedUserGuid.hashCode()) * 31) + this.receiverFirstName.hashCode()) * 31) + this.receiverLastName.hashCode()) * 31) + this.receiverUsername.hashCode()) * 31;
        Integer num = this.status;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.dateDelivered;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.dateRead;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.dateDeleted;
        int hashCode5 = (((hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31) + Integer.hashCode(this.userRef)) * 31;
        String str4 = this.dateSend;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return ((hashCode5 + i) * 31) + this.notificationGuid.hashCode();
    }

    public String toString() {
        return "NotifiedUserModel(notifiedUserId=" + this.notifiedUserId + ", notifiedUserGuid=" + this.notifiedUserGuid + ", receiverFirstName=" + this.receiverFirstName + ", receiverLastName=" + this.receiverLastName + ", receiverUsername=" + this.receiverUsername + ", status=" + this.status + ", dateDelivered=" + this.dateDelivered + ", dateRead=" + this.dateRead + ", dateDeleted=" + this.dateDeleted + ", userRef=" + this.userRef + ", dateSend=" + this.dateSend + ", notificationGuid=" + this.notificationGuid + ')';
    }

    public NotifiedUserModel(int i, String str, String str2, String str3, String str4, Integer num, String str5, String str6, String str7, int i2, String str8, String str9) {
        Intrinsics.checkNotNullParameter(str, "notifiedUserGuid");
        Intrinsics.checkNotNullParameter(str2, "receiverFirstName");
        Intrinsics.checkNotNullParameter(str3, "receiverLastName");
        Intrinsics.checkNotNullParameter(str4, "receiverUsername");
        Intrinsics.checkNotNullParameter(str9, "notificationGuid");
        this.notifiedUserId = i;
        this.notifiedUserGuid = str;
        this.receiverFirstName = str2;
        this.receiverLastName = str3;
        this.receiverUsername = str4;
        this.status = num;
        this.dateDelivered = str5;
        this.dateRead = str6;
        this.dateDeleted = str7;
        this.userRef = i2;
        this.dateSend = str8;
        this.notificationGuid = str9;
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

    public String getReceiverFirstName() {
        return this.receiverFirstName;
    }

    public void setReceiverFirstName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.receiverFirstName = str;
    }

    public String getReceiverLastName() {
        return this.receiverLastName;
    }

    public void setReceiverLastName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.receiverLastName = str;
    }

    public String getReceiverUsername() {
        return this.receiverUsername;
    }

    public void setReceiverUsername(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.receiverUsername = str;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public String getDateDelivered() {
        return this.dateDelivered;
    }

    public void setDateDelivered(String str) {
        this.dateDelivered = str;
    }

    public String getDateRead() {
        return this.dateRead;
    }

    public void setDateRead(String str) {
        this.dateRead = str;
    }

    public String getDateDeleted() {
        return this.dateDeleted;
    }

    public void setDateDeleted(String str) {
        this.dateDeleted = str;
    }

    public int getUserRef() {
        return this.userRef;
    }

    public void setUserRef(int i) {
        this.userRef = i;
    }

    public String getDateSend() {
        return this.dateSend;
    }

    public void setDateSend(String str) {
        this.dateSend = str;
    }

    public String getNotificationGuid() {
        return this.notificationGuid;
    }

    public void setNotificationGuid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.notificationGuid = str;
    }

    public NotifiedUserModel() {
        this(0, "", "", "", "", -1, null, null, null, 0, null, "");
    }

    public NotifiedUserModel(String str, String str2, int i, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i2 & 8) != 0 ? 0 : num);
    }

    public NotifiedUserModel(String str, String str2, int i, Integer num) {
        this(0, str, "", "", "", num, null, null, null, i, null, str2);
        Intrinsics.checkNotNullParameter(str, "notifiedUserGuid");
        Intrinsics.checkNotNullParameter(str2, "notificationGuid");
    }

    public String getReceiverName() {
        return this.receiverUsername + " - " + this.receiverFirstName + ' ' + this.receiverLastName;
    }

    public String getFormattedDateAccordingToStatus() {
        Integer num = this.status;
        if (num == null) {
            return "";
        }
        int i = WhenMappings.EnumSwitchMapping0[NotificationStatus.Companion.fromValue(num != null ? num.intValue() : 0).ordinal()];
        if (i == 1) {
            return DateExtensions.toDateStringdefault(this.dateSend, null, null, null, 7, null);
        }
        if (i == 2) {
            return DateExtensions.toDateStringdefault(this.dateDelivered, null, null, null, 7, null);
        }
        if (i == 3 || i == 4) {
            return DateExtensions.toDateStringdefault(this.dateRead, null, null, null, 7, null);
        }
        throw new NoWhenBranchMatchedException();
    }
}
