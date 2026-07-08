package com.proje.mobilesales.features.notification.view.create;

import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import java.util.List;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationUserSelectionViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionViewModelgetUsersConnectedToMe1result1", m22f = "NotificationUserSelectionViewModel.kt", m21l = {}, m20m = "invokeSuspend")
/* renamed from: com.proje.mobilesales.features.notification.view.create.NotificationUserSelectionViewModelgetUsersConnectedToMe1result1 */

public final class C3038x1044c8ed extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends NotificationUserSelectionModel>>, Object> {
    final String searchText;
    int label;
    final NotificationUserSelectionViewModel this0;

    
    
    public C3038x1044c8ed(NotificationUserSelectionViewModel notificationUserSelectionViewModel, String str, Continuation<? super C3038x1044c8ed> continuation) {
        super(2, continuation);
        this.this0 = notificationUserSelectionViewModel;
        this.searchText = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new C3038x1044c8ed(this.this0, this.searchText, continuation);
    }
    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends NotificationUserSelectionModel>> continuation) {
        return invoke(coroutineScope, (Continuation<? super List<NotificationUserSelectionModel>>) continuation);
    }

    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<NotificationUserSelectionModel>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.this0.repository.getUsersConnectedToMe(this.searchText);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
