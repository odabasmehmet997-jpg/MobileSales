package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationListViewModel.kt */
@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModelapplyEvents1", m22f = "NotificationListViewModel.kt", m21l = {}, m20m = "invokeSuspend")

final class NotificationListViewModelapplyEvents1 extends SuspendLambda implements Function2<NotificationModel, Continuation<? super Boolean>, Object> {
    final NotificationListEvents notificationListEvents;
    Object L0;
    int label;

    public NotificationListViewModelapplyEvents1(NotificationListEvents notificationListEvents, Continuation<? super NotificationListViewModelapplyEvents1> continuation) {
        super(2, continuation);
        this.notificationListEvents = notificationListEvents;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        NotificationListViewModelapplyEvents1 notificationListViewModelapplyEvents1 = new NotificationListViewModelapplyEvents1(this.notificationListEvents, continuation);
        notificationListViewModelapplyEvents1.L0 = obj;
        return notificationListViewModelapplyEvents1;
    }

    public Unit invoke(NotificationModel notificationModel, Continuation<? super Boolean> continuation) {
        return ((NotificationListViewModelapplyEvents1) create(notificationModel, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return boxing.boxBoolean(((NotificationListEvents.Delete) this.notificationListEvents).getNotificationId() != ((NotificationModel) this.L0).getNotificationId());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
