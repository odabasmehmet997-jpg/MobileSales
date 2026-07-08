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

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModelapplyEvents2", m22f = "NotificationListViewModel.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationListViewModelapplyEvents2 extends SuspendLambda implements Function2<NotificationModel, Continuation<? super NotificationModel>, Object> {
    final NotificationListEvents notificationListEvents;
    Object L0;
    int label;

    public NotificationListViewModelapplyEvents2(NotificationListEvents notificationListEvents, Continuation<? super NotificationListViewModelapplyEvents2> continuation) {
        super(2, continuation);
        this.notificationListEvents = notificationListEvents;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        NotificationListViewModelapplyEvents2 notificationListViewModelapplyEvents2 = new NotificationListViewModelapplyEvents2(this.notificationListEvents, continuation);
        notificationListViewModelapplyEvents2.L0 = obj;
        return notificationListViewModelapplyEvents2;
    }

    public Unit invoke(NotificationModel notificationModel, Continuation<? super NotificationModel> continuation) {
        return create(notificationModel, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            NotificationModel notificationModel = (NotificationModel) this.L0;
            return ((NotificationListEvents.StatusUpdate) this.notificationListEvents).getNotificationId() == notificationModel.getNotificationId() ? notificationModel.copy((r30 & 1) != 0 ? notificationModel.notificationId : 0, (r30 & 2) != 0 ? notificationModel.notificationGuid : null, (r30 & 4) != 0 ? notificationModel.notifiedUserId : 0, (r30 & 8) != 0 ? notificationModel.notifiedUserGuid : null, (r30 & 16) != 0 ? notificationModel.senderFirstName : null, (r30 & 32) != 0 ? notificationModel.senderLastName : null, (r30 & 64) != 0 ? notificationModel.senderUsername : null, (r30 & 128) != 0 ? notificationModel.title : null, (r30 & 256) != 0 ? notificationModel.message : null, (r30 & 512) != 0 ? notificationModel.status : boxing.boxInt(((NotificationListEvents.StatusUpdate) this.notificationListEvents).getStatus()), (r30 & 1024) != 0 ? notificationModel.dateSend : null, (r30 & 2048) != 0 ? notificationModel.senderRef : 0, (r30 & 4096) != 0 ? notificationModel.userRef : 0, (r30 & 8192) != 0 ? notificationModel.workingHours : 0) : notificationModel;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
