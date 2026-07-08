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

final class NotificationListViewModelapplyEvents2 extends SuspendLambda implements Function2<NotificationModel, Continuation<? super NotificationModel>, Object> {
    final  NotificationListEvents notificationListEvents;
    Object L0;
    int label;
 
    NotificationListViewModelapplyEvents2(final NotificationListEvents notificationListEvents, final Continuation<? super NotificationListViewModelapplyEvents2> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.notificationListEvents = notificationListEvents;
    }
 
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        final NotificationListViewModelapplyEvents2 notificationListViewModelapplyEvents2 = new NotificationListViewModelapplyEvents2(notificationListEvents, continuation);
        notificationListViewModelapplyEvents2.L0 = obj;
        return notificationListViewModelapplyEvents2;
    }
 
    public   Object invoke(final Object notificationModel, final ? super CoroutineContext.Element continuation) {
        return (this.create(notificationModel, (Continuation<?>) continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public Object invokeSuspend(final Object obj) {
        final NotificationModel copy;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (0 != this.label) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final NotificationModel notificationModel = (NotificationModel) L0;
        if (((NotificationListEvents.StatusUpdate) notificationListEvents).getNotificationId() != notificationModel.getNotificationId()) {
            return notificationModel;
        }
        final int r30 = 0;
        copy = notificationModel.copy(0 != (r30 & 1) ? notificationModel.component1() : 0, 0 != (r30 & 2) ? notificationModel.component2() : null, 0 != (r30 & 4) ? notificationModel.component3() : 0, 0 != (r30 & 8) ? notificationModel.component4() : null, 0 != (r30 & 16) ? notificationModel.component5() : null, 0 != (r30 & 32) ? notificationModel.component6() : null, 0 != (r30 & 64) ? notificationModel.component7() : null, 0 != (r30 & 128) ? notificationModel.component8() : null, 0 != (r30 & 256) ? notificationModel.component9() : null, 0 != (r30 & 512) ? notificationModel.component10() : boxing.boxInt(((NotificationListEvents.StatusUpdate) notificationListEvents).getStatus()), 0 != (r30 & 1024) ? notificationModel.component11() : null, 0 != (r30 & 2048) ? notificationModel.component12() : 0, 0 != (r30 & 4096) ? notificationModel.component13() : 0, 0 != (r30 & 8192) ? notificationModel.component14() : 0);
        return copy;
    }
}
