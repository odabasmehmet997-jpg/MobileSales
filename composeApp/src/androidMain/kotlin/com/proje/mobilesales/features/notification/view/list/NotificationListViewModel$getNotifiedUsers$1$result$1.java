package com.proje.mobilesales.features.notification.view.list;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.Comparator;
import java.util.List;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationListViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListViewModelgetNotifiedUsers1result1", m22f = "NotificationListViewModel.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationListViewModelgetNotifiedUsers1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends NotifiedUserModel>>, Object> {
    final NotificationModel notificationModel;
    int label;
    final NotificationListViewModel this0;

    public NotificationListViewModelgetNotifiedUsers1result1(NotificationListViewModel notificationListViewModel, NotificationModel notificationModel, Continuation<? super NotificationListViewModelgetNotifiedUsers1result1> continuation) {
        super(2, continuation);
        this.this0 = notificationListViewModel;
        this.notificationModel = notificationModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationListViewModelgetNotifiedUsers1result1(this.this0, this.notificationModel, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends NotifiedUserModel>> continuation) {
        return invoke(coroutineScope, (Continuation<? super List<NotifiedUserModel>>) continuation);
    }

    public Object invoke(CoroutineScope coroutineScope, Continuation<? super List<NotifiedUserModel>> continuation) {
        return ((NotificationListViewModelgetNotifiedUsers1result1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return CollectionsKt.sortedWith(this.this0.repository.getNotificationDetailsForSender(this.notificationModel.getNotificationId()), new Comparator() { // from class: com.proje.mobilesales.features.notification.view.list.NotificationListViewModelgetNotifiedUsers1result1invokeSuspendinlinedsortedByDescending1
                @Override // java.util.Comparator
                public int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((NotifiedUserModel) t2).getStatus(), ((NotifiedUserModel) t).getStatus());
                }
            });
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
