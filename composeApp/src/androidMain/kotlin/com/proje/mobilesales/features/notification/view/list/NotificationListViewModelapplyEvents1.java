package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import okio.internal._FileSystemKtcommonListRecursively1;

final class NotificationListViewModelapplyEvents1 extends SuspendLambda implements Function2<NotificationModel, Continuation<? super Boolean>, Object> {
    final  NotificationListEvents notificationListEvents;
      Object L0;
    int label;
    NotificationListViewModelapplyEvents1(final NotificationListEvents notificationListEvents, final Continuation<? super NotificationListViewModelapplyEvents1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.notificationListEvents = notificationListEvents;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        final NotificationListViewModelapplyEvents1 notificationListViewModelapplyEvents1 = new NotificationListViewModelapplyEvents1(notificationListEvents, continuation);
        notificationListViewModelapplyEvents1.L0 = obj;
        return notificationListViewModelapplyEvents1;
    } 
    public Object invoke(final Object notificationModel, final ? super CoroutineContext.Element continuation) {
        return ((NotificationListViewModelapplyEvents1) this.create(notificationModel, (Continuation<?>) continuation)).invokeSuspend(Unit.INSTANCE);
    }
 
    public Object invokeSuspend(final Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (0 != this.label) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return boxing.boxBoolean(((NotificationListEvents.Delete) notificationListEvents).getNotificationId() != ((NotificationModel) L0).getNotificationId());
    }
}
