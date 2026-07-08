package com.proje.mobilesales.core.interfaces.notification;

import com.proje.mobilesales.features.notification.model.NotificationFilterModel;

public interface INoticationFilterResult {
    void applyFilter(NotificationFilterModel notificationFilterModel);
    void filterChanged(NotificationFilterModel notificationFilterModel);
}
