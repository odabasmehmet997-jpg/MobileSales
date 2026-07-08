package com.proje.mobilesales.features.notification.util;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationChannelContent.kt */

public record NotificationChannelContent(String channelId, String channelName, String channelDescription,
                                         int importance) {
    public NotificationChannelContent {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        Intrinsics.checkNotNullParameter(channelDescription, "channelDescription");
    }
}
