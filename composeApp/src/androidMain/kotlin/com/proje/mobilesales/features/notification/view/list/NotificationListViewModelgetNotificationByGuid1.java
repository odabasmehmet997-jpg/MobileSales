package com.proje.mobilesales.features.notification.view.list;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.proje.mobilesales.R;
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

public final class NotificationListViewModelgetNotificationByGuid1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final String notificationGuid;
    int label;
    final NotificationListViewModel this0;
 
    NotificationListViewModelgetNotificationByGuid1(final NotificationListViewModel notificationListViewModel, final String str, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = notificationListViewModel;
        notificationGuid = str;
    }
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationListViewModelgetNotificationByGuid1(this0, notificationGuid, continuation);
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
                final NotificationListViewModelgetNotificationByGuid1result1 notificationListViewModelgetNotificationByGuid1result1 = new NotificationListViewModelgetNotificationByGuid1result1(this0, notificationGuid, null);
                label = 1;
                obj = BuildersKt.withContext(io2, notificationListViewModelgetNotificationByGuid1result1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (1 != i2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final NotificationModel notificationModel = (NotificationModel) obj;
            mutableLiveData2 = this0.getInsertedNotificationUiState;
            mutableLiveData2.setValue(null != notificationModel ? new UiState.Success(notificationModel) : new UiState.Error(R.string.str_data_not_found));
        } catch (final Exception e2) {
            str = this0.TAG;
            Log.e(str, "getNotificationByGuid", e2);
            mutableLiveData = this0.getInsertedNotificationUiState;
            mutableLiveData.setValue(new UiState.Error(e2.getMessage()));
        }
        return Unit.INSTANCE;
    }

    public Object invoke(final Object p1, final CoroutineContext.Element p2) {
        return null;
    }
}
