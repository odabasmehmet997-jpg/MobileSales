package com.proje.mobilesales.features.notification.view;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.base.IBaseRepository;
import com.proje.mobilesales.features.notification.repository.BaseNotificationRepository;
import kotlin.jvm.internal.Intrinsics;

public class BaseNotificationViewModel extends BaseViewModel {
    public BaseNotificationViewModel(BaseNotificationRepository baseNotificationRepository) {
        super(baseNotificationRepository);
        Intrinsics.checkNotNullParameter(baseNotificationRepository, "bRepository");
    }
}
