package com.proje.mobilesales.features.notification.view.create;

import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: NotificationUserSelectionDialogFragment.kt */

/* renamed from: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionDialogFragmentNotificationUserSelectionAdapteronBindViewHolder11 */

public final class C3035xf29f47dc extends Lambda implements Function1<NotificationUserSelectionModel, Boolean> {
    final NotificationUserSelectionModel item;

    
    
    public C3035xf29f47dc(NotificationUserSelectionModel notificationUserSelectionModel) {
        super(1);
        this.item = notificationUserSelectionModel;
    }

    public Boolean invoke(NotificationUserSelectionModel notificationUserSelectionModel) {
        Intrinsics.checkNotNullParameter(notificationUserSelectionModel, "it");
        return Boolean.valueOf(notificationUserSelectionModel.getId() == this.item.getId());
    }
}
