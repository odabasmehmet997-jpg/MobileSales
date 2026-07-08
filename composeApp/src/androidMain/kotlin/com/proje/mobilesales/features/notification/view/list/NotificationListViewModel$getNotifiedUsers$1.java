package com.proje.mobilesales.features.notification.view.list;

import android.util.Log;
import com.proje.mobilesales.core.utils.UiState;
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

/* compiled from: NotificationListViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModelgetNotifiedUsers1", m22f = "NotificationListViewModel.kt", m21l = {91}, m20m = "invokeSuspend")

public final class NotificationListViewModelgetNotifiedUsers1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final NotificationModel notificationModel;
    int label;
    final NotificationListViewModel this0;


    public NotificationListViewModelgetNotifiedUsers1(NotificationListViewModel notificationListViewModel, NotificationModel notificationModel, Continuation<? super NotificationListViewModelgetNotifiedUsers1> continuation) {
        super(2, continuation);
        this.this0 = notificationListViewModel;
        this.notificationModel = notificationModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationListViewModelgetNotifiedUsers1(this.this0, this.notificationModel, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io = Dispatchers.getIO();
                NotificationListViewModelgetNotifiedUsers1result1 notificationListViewModelgetNotifiedUsers1result1 = new NotificationListViewModelgetNotifiedUsers1result1(this.this0, this.notificationModel, null);
                this.label = 1;
                obj = BuildersKt.withContext(io, notificationListViewModelgetNotifiedUsers1result1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            this.this0.notifiedUsersUiState.setValue(new UiState.Success(obj));
        } catch (Exception e) {
            Log.e(this.this0.TAG, "getNotifiedUsers", e);
            this.this0.notifiedUsersUiState.setValue(new UiState.Error(e.getMessage()));
        }
        return Unit.INSTANCE;
    }
}
