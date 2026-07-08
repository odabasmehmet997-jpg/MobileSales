package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationListViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModelgetNotificationByGuid1result1", m22f = "NotificationListViewModel.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationListViewModelgetNotificationByGuid1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NotificationModel>, Object> {
    final String notificationGuid;
    int label;
    final NotificationListViewModel this0;

    public NotificationListViewModelgetNotificationByGuid1result1(NotificationListViewModel notificationListViewModel, String str, Continuation<? super NotificationListViewModelgetNotificationByGuid1result1> continuation) {
        super(2, continuation);
        this.this0 = notificationListViewModel;
        this.notificationGuid = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationListViewModelgetNotificationByGuid1result1(this.this0, this.notificationGuid, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super NotificationModel> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.this0.repository.getNotificationByGuid(this.notificationGuid);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
