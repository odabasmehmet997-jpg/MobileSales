package com.proje.mobilesales.features.notification.view.detail;

import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationDetailRepository;
import java.util.List;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUsersJob1", m22f = "NotificationDetailViewModel.kt", m21l = {79}, m20m = "invokeSuspend")
/* renamed from: com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUsersJob1 */

public final class C3044x6af0b45b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends NotifiedUserModel>>, Object> {
    final int notificationId;
    final int notifiedUserId;
    int label;
    final NotificationDetailViewModel this0;

    
    
    public C3044x6af0b45b(int i, NotificationDetailViewModel notificationDetailViewModel, int i2, Continuation<? super C3044x6af0b45b> continuation) {
        super(2, continuation);
        this.notifiedUserId = i;
        this.this0 = notificationDetailViewModel;
        this.notificationId = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new C3044x6af0b45b(this.notifiedUserId, this.this0, this.notificationId, continuation);
    }
 
    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends NotifiedUserModel>> continuation) {
        return invoke(coroutineScope, (Continuation<? super List<NotifiedUserModel>>) continuation);
    }

    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<NotifiedUserModel>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: NotificationDetailViewModel.kt */
    
    @DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUsersJob11", m22f = "NotificationDetailViewModel.kt", m21l = {}, m20m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDatagetNotifiedUsersJob11 */
    
    public static final class C30451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends NotifiedUserModel>>, Object> {
        int label;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30451(i2, notificationDetailViewModel, i3, continuation);
        }
 
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends NotifiedUserModel>> continuation) {
            return invoke(coroutineScope, (Continuation<? super List<NotifiedUserModel>>) continuation);
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<NotifiedUserModel>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public Object invokeSuspend(Object obj) {
            NotificationDetailRepository notificationDetailRepository;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (i2 != 0) {
                    return CollectionsKt.emptyList();
                }
                notificationDetailRepository = notificationDetailViewModel.repository;
                return notificationDetailRepository.getNotifiedUsers(i3);
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
            final int i3 = this.notificationId;
            C30451 r1 = new C30451(null);
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
