package com.proje.mobilesales.features.notification.view.detail;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationDetailViewModel.kt */
@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotificationJob1", m22f = "NotificationDetailViewModel.kt", m21l = {74}, m20m = "invokeSuspend")
/* renamed from: com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotificationJob1 */

final class C3040xea44d9fe extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NotificationModel>, Object> {
    final int notificationId;
    int label;
    final NotificationDetailViewModel this0;

    public C3040xea44d9fe(NotificationDetailViewModel notificationDetailViewModel, int i, Continuation<? super C3040xea44d9fe> continuation) {
        super(2, continuation);
        this.this0 = notificationDetailViewModel;
        this.notificationId = i;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new C3040xea44d9fe(this.this0, this.notificationId, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super NotificationModel> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public static final class C30411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NotificationModel>, Object> {
        int label;
        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30411(notificationDetailViewModel, i2, continuation);
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super kotlin.Result<com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return NotificationDetailViewModel.accessgetRepositoryp(notificationDetailViewModel).getNotificationDetail(i2);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final NotificationDetailViewModel notificationDetailViewModel = this.this0;
            final int i2 = this.notificationId;
            C30411 r1 = new C30411(null);
            this.label = 1;
            obj = BuildersKt.withContext(io, r1, this);
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
