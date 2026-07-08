package com.proje.mobilesales.features.notification.repository;

import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationCreateRepository.kt */

public final class NotificationCreateRepository extends BaseNotificationRepository {
    public List<NotificationUserSelectionModel> getUsersConnectedToMe(String str) {
        Intrinsics.checkNotNullParameter(str, "searchText");
        List<NotificationUserSelectionModel> usersConnectedToMe = getBaseErp().getUsersConnectedToMe(str);
        Intrinsics.checkNotNull(usersConnectedToMe, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel>");
        return usersConnectedToMe;
    }

    public BaseSelectResult<Object> saveNotification(NotificationModel notificationModel, List<NotifiedUserModel> list) {
        Intrinsics.checkNotNullParameter(notificationModel, "notificationModel");
        Intrinsics.checkNotNullParameter(list, "notifiedUsers");
        BaseSelectResult<Object> saveNotificationAndUsers = getBaseErp().saveNotificationAndUsers(notificationModel, list);
        Intrinsics.checkNotNullExpressionValue(saveNotificationAndUsers, "saveNotificationAndUsers(...)");
        return saveNotificationAndUsers;
    }
}
