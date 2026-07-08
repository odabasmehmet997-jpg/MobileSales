package com.proje.mobilesales.features.notification.view.create;

import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class NotificationCreateActivityselectionCompleted1 extends Lambda implements Function1<NotificationUserSelectionModel, CharSequence> {
    public static final NotificationCreateActivityselectionCompleted1 INSTANCE = new NotificationCreateActivityselectionCompleted1();

    NotificationCreateActivityselectionCompleted1() {
        super(1);
    }

    public CharSequence invoke(NotificationUserSelectionModel notificationUserSelectionModel) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionModel, "t");
        return notificationUserSelectionModel.getUserDescription();
    }
}
