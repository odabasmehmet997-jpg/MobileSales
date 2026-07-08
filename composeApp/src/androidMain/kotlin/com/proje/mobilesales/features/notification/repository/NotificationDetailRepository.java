package com.proje.mobilesales.features.notification.repository;

import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationDetailRepository.kt */

public final class NotificationDetailRepository extends BaseNotificationRepository {
    public NotificationModel getNotificationDetail(int i) {
        NotificationModel notification = getBaseErp().getNotification(i);
        Intrinsics.checkNotNullExpressionValue(notification, "getNotification(...)");
        return notification;
    }

    public List<NotifiedUserModel> getNotifiedUsers(int i) {
        List<NotifiedUserModel> notificationDetailsForSender = getBaseErp().getNotificationDetailsForSender(i);
        Intrinsics.checkNotNull(notificationDetailsForSender, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.notification.model.NotifiedUserModel>");
        return notificationDetailsForSender;
    }

    public NotifiedUserModel getNotifiedUser(int i) {
        NotifiedUserModel notifiedUser = getBaseErp().getNotifiedUser(i);
        Intrinsics.checkNotNullExpressionValue(notifiedUser, "getNotifiedUser(...)");
        return notifiedUser;
    }

    public BaseSelectResult<Object> updateNotifiedUserNotificationAsRead(int i) {
        BaseSelectResult<Object> updateNotifiedUserNotificationAsRead = getBaseErp().updateNotifiedUserNotificationAsRead(i);
        Intrinsics.checkNotNullExpressionValue(updateNotifiedUserNotificationAsRead, "updateNotifiedUserNotificationAsRead(...)");
        return updateNotifiedUserNotificationAsRead;
    }

    public BaseSelectResult<Object> updateNotificationAsReadIfAllUsersRead(String str) {
        Intrinsics.checkNotNullParameter(str, "notificationGuid");
        BaseSelectResult<Object> updateNotificationAsReadIfAllUsersRead = getBaseErp().updateNotificationAsReadIfAllUsersRead(str);
        Intrinsics.checkNotNullExpressionValue(updateNotificationAsReadIfAllUsersRead, "updateNotificationAsReadIfAllUsersRead(...)");
        return updateNotificationAsReadIfAllUsersRead;
    }
}
