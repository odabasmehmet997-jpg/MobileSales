package com.proje.mobilesales.features.notification.view.create;

import android.util.Log;
import com.proje.mobilesales.core.utils.UiState;

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

/* compiled from: NotificationUserSelectionViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionViewModelgetUsersConnectedToMe1", m22f = "NotificationUserSelectionViewModel.kt", m21l = {26}, m20m = "invokeSuspend")

public final class NotificationUserSelectionViewModelgetUsersConnectedToMe1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final String searchText;
    int label;
    final NotificationUserSelectionViewModel this0;

    public NotificationUserSelectionViewModelgetUsersConnectedToMe1(NotificationUserSelectionViewModel notificationUserSelectionViewModel, String str, Continuation<? super NotificationUserSelectionViewModelgetUsersConnectedToMe1> continuation) {
        super(2, continuation);
        this.this0 = notificationUserSelectionViewModel;
        this.searchText = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationUserSelectionViewModelgetUsersConnectedToMe1(this.this0, this.searchText, continuation);
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
                C3038x1044c8ed notificationUserSelectionViewModelgetUsersConnectedToMe1result1 = new C3038x1044c8ed(this.this0, this.searchText, null);
                this.label = 1;
                obj = BuildersKt.withContext(io, notificationUserSelectionViewModelgetUsersConnectedToMe1result1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            this.this0.notificationUsersUiState.setValue(new UiState.Success(obj));
        } catch (Exception e) {
            Log.e(this.this0.TAG, "getNotifiedUsers", e);
            this.this0.notificationUsersUiState.setValue(new UiState.Error(e.getMessage()));
        }
        return Unit.INSTANCE;
    }
}
