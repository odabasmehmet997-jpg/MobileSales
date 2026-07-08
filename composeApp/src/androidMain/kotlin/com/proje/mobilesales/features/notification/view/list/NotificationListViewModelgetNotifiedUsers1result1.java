package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.notification.repository.NotificationListRepository;
import java.util.Comparator;
import java.util.List;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class NotificationListViewModelgetNotifiedUsers1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<NotifiedUserModel>>, Object> {
    final  NotificationModel notificationModel;
    int label;
    final  NotificationListViewModel this0;

    NotificationListViewModelgetNotifiedUsers1result1(final NotificationListViewModel notificationListViewModel, final NotificationModel notificationModel, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = notificationListViewModel;
        this.notificationModel = notificationModel;
    }
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationListViewModelgetNotifiedUsers1result1(this0, notificationModel, continuation);
    }
    public   Object invoke(final Object coroutineScope, final ? super CoroutineContext.Element continuation) {
        return this.invoke2((CoroutineScope) coroutineScope, (Continuation<? super List<NotifiedUserModel>>) continuation);
    }
    public   Object invoke2(final CoroutineScope coroutineScope, final Continuation<? super List<NotifiedUserModel>> continuation) {
        return ((NotificationListViewModelgetNotifiedUsers1result1) this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(final Object obj) {
        final NotificationListRepository notificationListRepository;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (0 == this.label) {
            ResultKt.throwOnFailure(obj);
            notificationListRepository = this0.repository;
            return CollectionsKt.sortedWith(notificationListRepository.getNotificationDetailsForSender(notificationModel.getNotificationId()), new Comparator() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListViewModelgetNotifiedUsers1result1invokeSuspendinlinedsortedByDescending1

                public   int compare(final NotifiedUserModel t, final NotifiedUserModel t2) {
                    return ComparisonsKt.compareValues(t2.getStatus(), t.getStatus());
                }
            });
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
