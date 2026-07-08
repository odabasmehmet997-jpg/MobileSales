package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.core.base.BaseSelectResult;
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

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModeldeleteNotification1result1", m22f = "NotificationListViewModel.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationListViewModeldeleteNotification1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BaseSelectResult<Object>>, Object> {
    final NotificationModel notificationModel;
    int label;

    public NotificationListViewModeldeleteNotification1result1(NotificationListViewModel notificationListViewModel, NotificationModel notificationModel, Continuation<? super NotificationListViewModeldeleteNotification1result1> continuation) {
        super(2, continuation);
        this.this0 = notificationListViewModel;
        this.notificationModel = notificationModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationListViewModeldeleteNotification1result1(this.this0, this.notificationModel, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super BaseSelectResult<Object>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.this0.repository.deleteNotification(this.notificationModel);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
