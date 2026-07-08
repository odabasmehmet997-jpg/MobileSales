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
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class NotificationCreateViewModelsaveNotification1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final NotificationModel notificationModel;
    final List<NotifiedUserModel> notifiedUsers;
    int label;
    final NotificationCreateViewModel this0;
 
    public NotificationCreateViewModelsaveNotification1(final NotificationCreateViewModel notificationCreateViewModel, final NotificationModel notificationModel, final List<NotifiedUserModel> list, final Continuation<? super NotificationCreateViewModelsaveNotification1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = notificationCreateViewModel;
        this.notificationModel = notificationModel;
        notifiedUsers = list;
    } 
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationCreateViewModelsaveNotification1(this0, notificationModel, notifiedUsers, continuation);
    }

    public Unit invoke(final CoroutineScope coroutineScope, final Continuation<? super Unit> continuation) {
        return this.create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    } 
    public Object invokeSuspend(Object obj) {
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i = label;
        try {
            if (0 == i) {
                ResultKt.throwOnFailure(obj);
                final CoroutineDispatcher io = Dispatchers.getIO();
                final NotificationCreateViewModelsaveNotification1result1 notificationCreateViewModelsaveNotification1result1 = new NotificationCreateViewModelsaveNotification1result1(this0, notificationModel, notifiedUsers, null);
                label = 1;
                obj = BuildersKt.withContext(io, notificationCreateViewModelsaveNotification1result1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (1 == i) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            final BaseSelectResult baseSelectResult = (BaseSelectResult) obj;
            this0.saveNotificationUiState.setValue(baseSelectResult.isSuccess() ? new UiState.Success(notificationModel.getNotificationGuid()) : new UiState.Error(baseSelectResult.getErrorString()));
        } catch (final Exception e) {
            Log.e(this0.TAG, "saveNotification", e);
            this0.saveNotificationUiState.setValue(new UiState.Error(e.getMessage()));
        }
        return Unit.INSTANCE;
    }
    public Object invoke(final Object p1, final CoroutineContext.Element p2) {
        return null;
    }
}