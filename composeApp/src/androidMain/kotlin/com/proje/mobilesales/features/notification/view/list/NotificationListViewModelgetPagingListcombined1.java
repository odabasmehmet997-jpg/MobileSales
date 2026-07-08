package com.proje.mobilesales.features.notification.view.list;

import androidx.paging.PagingData;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import java.util.Iterator;
import java.util.List;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
 
final class NotificationListViewModelgetPagingListcombined1 extends SuspendLambda implements Function3<PagingData<NotificationModel>, List<? extends NotificationListEvents>, Continuation<? super PagingData<NotificationModel>>, Object> {
    Object L0;
    Object L1;
    int label;
    final NotificationListViewModel this0;
 
    NotificationListViewModelgetPagingListcombined1(final NotificationListViewModel notificationListViewModel, final Continuation<? super NotificationListViewModelgetPagingListcombined1> continuation) {
        super(3, (Continuation<Object>) continuation);
        this0 = notificationListViewModel;
    } 
    public   Object invoke(final PagingData<NotificationModel> pagingData, final List<? extends NotificationListEvents> list, final Continuation<? super PagingData<NotificationModel>> continuation) {
        final NotificationListViewModelgetPagingListcombined1 notificationListViewModelgetPagingListcombined1 = new NotificationListViewModelgetPagingListcombined1(this0, (Continuation<? super NotificationListViewModelgetPagingListcombined1>) continuation);
        notificationListViewModelgetPagingListcombined1.L0 = pagingData;
        notificationListViewModelgetPagingListcombined1.L1 = list;
        return notificationListViewModelgetPagingListcombined1.invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(final Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (0 != this.label) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        PagingData pagingData = (PagingData) L0;
        final List list = (List) L1;
        final NotificationListViewModel notificationListViewModel = this0;
        final Iterator it = list.iterator();
        while (it.hasNext()) {
            pagingData = notificationListViewModel.applyEvents(pagingData, (NotificationListEvents) it.next());
        }
        return pagingData;
    }
}
