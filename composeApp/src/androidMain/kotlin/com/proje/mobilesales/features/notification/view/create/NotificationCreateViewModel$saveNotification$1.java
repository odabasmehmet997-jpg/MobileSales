package com.proje.mobilesales.features.notification.view.create;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;

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

/* compiled from: NotificationCreateViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.create.NotificationCreateViewModelsaveNotification1", m22f = "NotificationCreateViewModel.kt", m21l = {34}, m20m = "invokeSuspend")

public final class NotificationCreateViewModelsaveNotification1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final NotificationModel notificationModel;
    final List<NotifiedUserModel> notifiedUsers;
    int label;
    final NotificationCreateViewModel this0;

    
    
    public NotificationCreateViewModelsaveNotification1(NotificationCreateViewModel notificationCreateViewModel, NotificationModel notificationModel, List<NotifiedUserModel> list, Continuation<? super NotificationCreateViewModelsaveNotification1> continuation) {
        super(2, continuation);
        this.this0 = notificationCreateViewModel;
        this.notificationModel = notificationModel;
        this.notifiedUsers = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationCreateViewModelsaveNotification1(this.this0, this.notificationModel, this.notifiedUsers, continuation);
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
                NotificationCreateViewModelsaveNotification1result1 notificationCreateViewModelsaveNotification1result1 = new NotificationCreateViewModelsaveNotification1result1(this.this0, this.notificationModel, this.notifiedUsers, null);
                this.label = 1;
                obj = BuildersKt.withContext(io, notificationCreateViewModelsaveNotification1result1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            BaseSelectResult baseSelectResult = (BaseSelectResult) obj;
            this.this0.saveNotificationUiState.setValue(baseSelectResult.isSuccess() ? new UiState.Success(this.notificationModel.getNotificationGuid()) : new UiState.Error(baseSelectResult.getErrorString()));
        } catch (Exception e) {
            Log.e(this.this0.TAG, "saveNotification", e);
            this.this0.saveNotificationUiState.setValue(new UiState.Error(e.getMessage()));
        }
        return Unit.INSTANCE;
    }
}
