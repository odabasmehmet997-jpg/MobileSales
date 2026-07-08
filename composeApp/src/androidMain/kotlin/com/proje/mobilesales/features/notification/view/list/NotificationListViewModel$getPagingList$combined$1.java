package com.proje.mobilesales.features.notification.view.list;

import androidx.paging.PagingData;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: NotificationListViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModelgetPagingListcombined1", m22f = "NotificationListViewModel.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationListViewModelgetPagingListcombined1 extends SuspendLambda implements Function3<PagingData<NotificationModel>, List<? extends NotificationListEvents>, Continuation<? super PagingData<NotificationModel>>, Object> {
    Object L0;
    Object L1;
    int label;
    final NotificationListViewModel this0;

    public NotificationListViewModelgetPagingListcombined1(NotificationListViewModel notificationListViewModel, Continuation<? super NotificationListViewModelgetPagingListcombined1> continuation) {
        super(3, continuation);
        this.this0 = notificationListViewModel;
    }

    public Object invoke(PagingData<NotificationModel> pagingData, List<? extends NotificationListEvents> list, Continuation<? super PagingData<NotificationModel>> continuation) {
        NotificationListViewModelgetPagingListcombined1 notificationListViewModelgetPagingListcombined1 = new NotificationListViewModelgetPagingListcombined1(this.this0, continuation);
        notificationListViewModelgetPagingListcombined1.L0 = pagingData;
        notificationListViewModelgetPagingListcombined1.L1 = list;
        return notificationListViewModelgetPagingListcombined1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PagingData pagingData = (PagingData) this.L0;
            NotificationListViewModel notificationListViewModel = this.this0;
            for (NotificationListEvents notificationListEvents : (List) this.L1) {
                pagingData = notificationListViewModel.applyEvents(pagingData, notificationListEvents);
            }
            return pagingData;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
