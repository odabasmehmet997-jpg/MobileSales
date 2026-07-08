package com.proje.mobilesales.features.notification.model;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationDetailModel.kt */

public final class NotificationDetailModel {
    private NotificationModel notificationModel;
    private final List<NotifiedUserModel> notifiedUsers;
    private boolean statusChanged;

    
    public static NotificationDetailModel copydefault(NotificationDetailModel notificationDetailModel, NotificationModel notificationModel, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            notificationModel = notificationDetailModel.notificationModel;
        }
        if ((i & 2) != 0) {
            list = notificationDetailModel.notifiedUsers;
        }
        if ((i & 4) != 0) {
            z = notificationDetailModel.statusChanged;
        }
        return notificationDetailModel.copy(notificationModel, list, z);
    }

    public NotificationModel component1() {
        return this.notificationModel;
    }

    public List<NotifiedUserModel> component2() {
        return this.notifiedUsers;
    }

    public boolean component3() {
        return this.statusChanged;
    }

    public NotificationDetailModel copy(NotificationModel notificationModel, List<NotifiedUserModel> list, boolean z) {
        return new NotificationDetailModel(notificationModel, list, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationDetailModel notificationDetailModel)) {
            return false;
        }
        return Intrinsics.areEqual(this.notificationModel, notificationDetailModel.notificationModel) && Intrinsics.areEqual(this.notifiedUsers, notificationDetailModel.notifiedUsers) && this.statusChanged == notificationDetailModel.statusChanged;
    }

    public int hashCode() {
        NotificationModel notificationModel = this.notificationModel;
        int i = 0;
        int hashCode = (notificationModel == null ? 0 : notificationModel.hashCode()) * 31;
        List<NotifiedUserModel> list = this.notifiedUsers;
        if (list != null) {
            i = list.hashCode();
        }
        return ((hashCode + i) * 31) + Boolean.hashCode(this.statusChanged);
    }

    public String toString() {
        return "NotificationDetailModel(notificationModel=" + this.notificationModel + ", notifiedUsers=" + this.notifiedUsers + ", statusChanged=" + this.statusChanged + ')';
    }

    public NotificationDetailModel(NotificationModel notificationModel, List<NotifiedUserModel> list, boolean z) {
        this.notificationModel = notificationModel;
        this.notifiedUsers = list;
        this.statusChanged = z;
    }

    public NotificationDetailModel(NotificationModel notificationModel, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(notificationModel, list, (i & 4) == 0 && z);
    }

    public NotificationModel getNotificationModel() {
        return this.notificationModel;
    }

    public void setNotificationModel(NotificationModel notificationModel) {
        this.notificationModel = notificationModel;
    }

    public List<NotifiedUserModel> getNotifiedUsers() {
        return this.notifiedUsers;
    }

    public boolean getStatusChanged() {
        return this.statusChanged;
    }

    public void setStatusChanged(boolean z) {
        this.statusChanged = z;
    }

    public NotificationDetailModel() {
        this(new NotificationModel(), CollectionsKt.emptyList(), false, 4, null);
    }

    public boolean isNotifiedUsersEmpty() {
        List<NotifiedUserModel> list = this.notifiedUsers;
        return list == null || list.isEmpty();
    }
}
