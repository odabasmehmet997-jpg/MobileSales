package com.proje.mobilesales.features.notification.view.create;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.proje.mobilesales.core.utils.UiState;

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

public final class NotificationUserSelectionViewModelgetUsersConnectedToMe1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final  String searchText;
    int label;
    final  NotificationUserSelectionViewModel this0;
 
    NotificationUserSelectionViewModelgetUsersConnectedToMe1(final NotificationUserSelectionViewModel notificationUserSelectionViewModel, final String str, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = notificationUserSelectionViewModel;
        searchText = str;
    } 
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationUserSelectionViewModelgetUsersConnectedToMe1(this0, searchText, continuation);
    } 
    public Object invoke(final Object coroutineScope, final CoroutineContext.Element continuation) {
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
                final C2768x1044c8ed c2768x1044c8ed = new C2768x1044c8ed(this.this0, this.searchText, null);
                label = 1;
                obj = BuildersKt.withContext(io2, c2768x1044c8ed, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (1 != i2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            mutableLiveData2 = this0.notificationUsersUiState;
            mutableLiveData2.setValue(new UiState.Success(obj));
        } catch (final Exception e2) {
            str = this0.TAG;
            Log.e(str, "getNotifiedUsers", e2);
            mutableLiveData = this0.notificationUsersUiState;
            mutableLiveData.setValue(new UiState.Error(e2.getMessage()));
        }
        return Unit.INSTANCE;
    }
}
