package com.proje.mobilesales.features.notification.view.list;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationListViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModeldeleteNotification1", m22f = "NotificationListViewModel.kt", m21l = {53}, m20m = "invokeSuspend")

public final class NotificationListViewModeldeleteNotification1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final NotificationModel notificationModel;
    int label;
    final NotificationListViewModel this0;

    public NotificationListViewModeldeleteNotification1(NotificationModel notificationModel, NotificationListViewModel notificationListViewModel, Continuation<? super NotificationListViewModeldeleteNotification1> continuation) {
        super(2, continuation);
        this.notificationModel = notificationModel;
        this.this0 = notificationListViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationListViewModeldeleteNotification1(this.notificationModel, this.this0, continuation);
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
                NotificationListViewModeldeleteNotification1result1 notificationListViewModeldeleteNotification1result1 = new NotificationListViewModeldeleteNotification1result1(this.this0, this.notificationModel, null);
                this.label = 1;
                obj = BuildersKt.withContext(io, notificationListViewModeldeleteNotification1result1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            BaseSelectResult baseSelectResult = (BaseSelectResult) obj;
            if (baseSelectResult.isSuccess()) {
                this.notificationModel.setStatus(boxing.boxInt(3));
                this.this0.deleteUiState.setValue(new UiState.Success(boxing.boxInt(this.notificationModel.getNotificationId())));
            } else {
                this.this0.deleteUiState.setValue(new UiState.Error(baseSelectResult.getErrorString(), baseSelectResult.getErrorResourceId()));
            }
        } catch (Exception e) {
            Log.e(this.this0.TAG, "deleteNotification", e);
            this.this0.deleteUiState.setValue(new UiState.Error(e.getMessage()));
        }
        return Unit.INSTANCE;
    }
}
