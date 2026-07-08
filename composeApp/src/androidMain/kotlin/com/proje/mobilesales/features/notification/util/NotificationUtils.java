package com.proje.mobilesales.features.notification.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationUtils.kt */

public final class NotificationUtils {
    public static final NotificationHelper NotificationHelper = new NotificationHelper(null);

    /* compiled from: NotificationUtils.kt */
    
    public static final class NotificationHelper {
        public NotificationHelper(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private NotificationHelper() {
        }

        public void createNotificationChannel(Context context, NotificationChannelContent notificationChannelContent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationChannelContent, "channelContent");
            NotificationChannel notificationChannel = new NotificationChannel(notificationChannelContent.channelId(), notificationChannelContent.channelName(), notificationChannelContent.importance());
            notificationChannel.setDescription(notificationChannelContent.channelDescription());
            notificationChannel.setLockscreenVisibility(0);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            Intrinsics.checkNotNull(notificationManager);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
