package com.proje.mobilesales.features.notification.view.list;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.proje.mobilesales.core.utils.UiState;
import com.proje.mobilesales.features.notification.model.NotificationModel;

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

final class NotificationListViewModelgetNotifiedUsers1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final  NotificationModel notificationModel;
    int label;
    final  NotificationListViewModel this0;
 
    NotificationListViewModelgetNotifiedUsers1(final NotificationListViewModel notificationListViewModel, final NotificationModel notificationModel, final Continuation<? super NotificationListViewModelgetNotifiedUsers1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = notificationListViewModel;
        this.notificationModel = notificationModel;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationListViewModelgetNotifiedUsers1(this0, notificationModel, continuation);
    }
    public Object invoke(final Object coroutineScope, final ? super CoroutineContext.Element continuation) {
        return this.create(coroutineScope, (Continuation<?>) continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        final String str;
        final MutableLiveData mutableLiveData;
        final MutableLiveData mutableLiveData2;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        try {
            if (0 == i2) {
                ResultKt.throwOnFailure(obj);
                final CoroutineDispatcher io2 = Dispatchers.getIO();
                final NotificationListViewModelgetNotifiedUsers1result1 notificationListViewModelgetNotifiedUsers1result1 = new NotificationListViewModelgetNotifiedUsers1result1(this0, notificationModel, null);
                label = 1;
                obj = BuildersKt.withContext(io2, notificationListViewModelgetNotifiedUsers1result1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (1 != i2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            mutableLiveData2 = this0.notifiedUsersUiState;
            mutableLiveData2.setValue(new UiState.Success(obj));
        } catch (final Exception e2) {
            str = this0.TAG;
            Log.e(str, "getNotifiedUsers", e2);
            mutableLiveData = this0.notifiedUsersUiState;
            mutableLiveData.setValue(new UiState.Error(e2.getMessage()));
        }
        return Unit.INSTANCE;
    }
}
