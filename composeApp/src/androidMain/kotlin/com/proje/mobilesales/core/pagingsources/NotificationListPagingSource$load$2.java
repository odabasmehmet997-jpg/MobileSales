package com.proje.mobilesales.core.pagingsources;

import androidx.paging.PagingSource;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class NotificationListPagingSourceload2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PagingSource.LoadResult.Page<Integer, NotificationModel>>, Object> {
    final int endIndex;
    final PagingSource.LoadParams<Integer> params;
    final int position;
    final int startIndex;
    int label;
    final NotificationListPagingSource this0;
    NotificationListPagingSourceload2(int i2, int i3, NotificationListPagingSource notificationListPagingSource, int i4, PagingSource.LoadParams<Integer> loadParams, Continuation<? super NotificationListPagingSourceload2> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.startIndex = i2;
        this.endIndex = i3;
        this.this0 = notificationListPagingSource;
        this.position = i4;
        this.params = loadParams;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationListPagingSourceload2(this.startIndex, this.endIndex, this.this0, this.position, this.params, (Continuation<? super NotificationListPagingSourceload2>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return ((NotificationListPagingSourceload2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<NotificationModel> userNotifications = ErpCreator.getInstance().getmBaseErp().getUserNotifications(this.startIndex, this.endIndex, this.this0.getFilterModel());
        Intrinsics.checkNotNull(userNotifications, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.notification.model.NotificationModel>");
        return new PagingSource.LoadResult.Page(userNotifications, null, userNotifications.isEmpty() ? null : boxing.boxInt(this.position + (this.params.getLoadSize() / 10)));
    }
}
