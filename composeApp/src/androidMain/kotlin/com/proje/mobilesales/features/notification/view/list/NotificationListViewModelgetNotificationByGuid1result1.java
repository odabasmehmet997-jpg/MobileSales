package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.repository.NotificationListRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class NotificationListViewModelgetNotificationByGuid1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NotificationModel>, Object> {
    final String notificationGuid;
    int label;
    final NotificationListViewModel this0;
    NotificationListViewModelgetNotificationByGuid1result1(final NotificationListViewModel notificationListViewModel, final String str, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = notificationListViewModel;
        notificationGuid = str;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationListViewModelgetNotificationByGuid1result1(this0, notificationGuid, continuation);
    }

    public Object invoke(final Object coroutineScope, final ? super CoroutineContext.Element continuation) {
        return this.create(coroutineScope, (Continuation<?>) continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(final Object obj) {
        final NotificationListRepository notificationListRepository;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (0 == this.label) {
            ResultKt.throwOnFailure(obj);
            notificationListRepository = this0.repository;
            return notificationListRepository.getNotificationByGuid(notificationGuid);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
