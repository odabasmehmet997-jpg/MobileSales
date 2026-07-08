package com.proje.mobilesales.features.notification.view.detail;

import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
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

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelcontrolNotifiedUser21", m22f = "NotificationDetailViewModel.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationDetailViewModelcontrolNotifiedUser21 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BaseSelectResult<Object>>, Object> {
    final NotifiedUserModel it;
    int label;
    final NotificationDetailViewModel this0;

    public NotificationDetailViewModelcontrolNotifiedUser21(NotificationDetailViewModel notificationDetailViewModel, NotifiedUserModel notifiedUserModel, Continuation<? super NotificationDetailViewModelcontrolNotifiedUser21> continuation) {
        super(2, continuation);
        this.this0 = notificationDetailViewModel;
        this.it = notifiedUserModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationDetailViewModelcontrolNotifiedUser21(this.this0, this.it, continuation);
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
            return notificationDetailRepository.updateNotificationAsReadIfAllUsersRead(this.it.getNotificationGuid());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
