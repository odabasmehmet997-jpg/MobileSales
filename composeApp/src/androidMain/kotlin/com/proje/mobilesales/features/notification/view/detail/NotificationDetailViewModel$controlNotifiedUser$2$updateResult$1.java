package com.proje.mobilesales.features.notification.view.detail;

import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.features.notification.repository.NotificationDetailRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationDetailViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelcontrolNotifiedUser2updateResult1", m22f = "NotificationDetailViewModel.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationDetailViewModelcontrolNotifiedUser2updateResult1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BaseSelectResult<Object>>, Object> {
    final int notifiedUserId;
    int label;
    final NotificationDetailViewModel this0;

    public NotificationDetailViewModelcontrolNotifiedUser2updateResult1(NotificationDetailViewModel notificationDetailViewModel, int i, Continuation<? super NotificationDetailViewModelcontrolNotifiedUser2updateResult1> continuation) {
        super(2, continuation);
        this.this0 = notificationDetailViewModel;
        this.notifiedUserId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationDetailViewModelcontrolNotifiedUser2updateResult1(this.this0, this.notifiedUserId, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super BaseSelectResult<Object>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        NotificationDetailRepository notificationDetailRepository;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            notificationDetailRepository = this.this0.repository;
            return notificationDetailRepository.updateNotifiedUserNotificationAsRead(this.notifiedUserId);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
