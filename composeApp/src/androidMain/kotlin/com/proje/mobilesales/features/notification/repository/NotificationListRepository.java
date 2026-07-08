package com.proje.mobilesales.features.notification.repository;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingSource;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.pagingsources.NotificationListPagingSource;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* compiled from: NotificationListRepository.kt */

public final class NotificationListRepository extends BaseNotificationRepository {
    public Flow<PagingData<NotificationModel>> getNotificationList(NotificationFilterModel notificationFilterModel) {
        Intrinsics.checkNotNullParameter(notificationFilterModel, "filterModel");
        return new Pager(new PagingConfig(10, 0, false, 0, 0, 0, 58, null), null, new Functions<PagingSource<Integer, NotificationModel>>(notificationFilterModel) { // from class: com.proje.mobilesales.features.notification.repository.NotificationListRepositorygetNotificationList1
            final NotificationFilterModel filterModel;

            
            {
                this.filterModel = r1;
            }

            
            public PagingSource<Integer, NotificationModel> invoke() {
                return new NotificationListPagingSource(this.filterModel);
            }
        }, 2, null).getFlow();
    }

    public BaseSelectResult<Object> deleteNotification(NotificationModel notificationModel) {
        Intrinsics.checkNotNullParameter(notificationModel, "notificationModel");
        BaseSelectResult<Object> deleteNotification = getBaseErp().deleteNotification(notificationModel);
        Intrinsics.checkNotNullExpressionValue(deleteNotification, "deleteNotification(...)");
        return deleteNotification;
    }

    public NotificationModel getNotificationByGuid(String str) {
        Intrinsics.checkNotNullParameter(str, "notificationGuid");
        return getBaseErp().getNotificationByGuid(str);
    }

    public List<NotifiedUserModel> getNotificationDetailsForSender(int i) {
        List<NotifiedUserModel> notificationDetailsForSender = getBaseErp().getNotificationDetailsForSender(i);
        Intrinsics.checkNotNull(notificationDetailsForSender, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.notification.model.NotifiedUserModel>");
        return notificationDetailsForSender;
    }
}
