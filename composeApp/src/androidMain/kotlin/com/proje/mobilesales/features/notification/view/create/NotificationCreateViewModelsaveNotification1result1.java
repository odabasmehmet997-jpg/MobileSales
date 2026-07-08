package com.proje.mobilesales.features.notification.view.create;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.base.BaseSelectResult;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class NotificationCreateViewModelsaveNotification1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BaseSelectResult<Object>>, Object> {
    final NotificationModel notificationModel;
    final List<NotifiedUserModel> notifiedUsers;
    int label;
    final NotificationCreateViewModel this0;
 
    NotificationCreateViewModelsaveNotification1result1(NotificationCreateViewModel notificationCreateViewModel, NotificationModel notificationModel, List<NotifiedUserModel> list, Continuation<? super NotificationCreateViewModelsaveNotification1result1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = notificationCreateViewModel;
        this.notificationModel = notificationModel;
        this.notifiedUsers = list;
    }
 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationCreateViewModelsaveNotification1result1(this.this0, this.notificationModel, this.notifiedUsers, continuation);
    }
 
    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super BaseSelectResult<Object>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
 
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.this0.repository.saveNotification(this.notificationModel, this.notifiedUsers);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    @Override
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }
}
