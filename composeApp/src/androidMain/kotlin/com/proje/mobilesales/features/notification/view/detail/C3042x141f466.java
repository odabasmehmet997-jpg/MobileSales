package com.proje.mobilesales.features.notification.view.detail;

import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationDetailRepository;
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

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUserJob1", m22f = "NotificationDetailViewModel.kt", m21l = {86}, m20m = "invokeSuspend")
/* renamed from: com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUserJob1 */

public final class C3042x141f466 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NotifiedUserModel>, Object> {
    final int notifiedUserId;
    int label;
    final NotificationDetailViewModel this0;

    
    
    public C3042x141f466(int i, NotificationDetailViewModel notificationDetailViewModel, Continuation<? super C3042x141f466> continuation) {
        super(2, continuation);
        this.notifiedUserId = i;
        this.this0 = notificationDetailViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new C3042x141f466(this.notifiedUserId, this.this0, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super NotifiedUserModel> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: NotificationDetailViewModel.kt */
    
    @DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUserJob11", m22f = "NotificationDetailViewModel.kt", m21l = {}, m20m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUserJob11 */
    
    public static final class C30431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NotifiedUserModel>, Object> {
        int label;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30431(i2, notificationDetailViewModel, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super NotifiedUserModel> continuation) {
            return ((C30431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public Object invokeSuspend(Object obj) {
            NotificationDetailRepository notificationDetailRepository;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (i2 == 0) {
                    return null;
                }
                notificationDetailRepository = notificationDetailViewModel.repository;
                return notificationDetailRepository.getNotifiedUser(i2);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final int i2 = this.notifiedUserId;
            final NotificationDetailViewModel notificationDetailViewModel = this.this0;
            C30431 r1 = new C30431(null);
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
