package com.proje.mobilesales.features.notification.view.list;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.paging.PagingData;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationListActivity.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.list.NotificationListActivityinitObservers1", m22f = "NotificationListActivity.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationListActivityinitObservers1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final NotificationListActivity this0;

    public NotificationListActivityinitObservers1(NotificationListActivity notificationListActivity, Continuation<? super NotificationListActivityinitObservers1> continuation) {
        super(2, continuation);
        this.this0 = notificationListActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationListActivityinitObservers1(this.this0, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            LiveData<PagingData<NotificationModel>> pagingList = this.this0.viewModel.getPagingList(this.this0.filterModel);
            final NotificationListActivity notificationListActivity = this.this0;
            pagingList.observe(notificationListActivity, new NotificationListActivitysamandroidx_lifecycle_Observer0(new Function1<PagingData<NotificationModel>, Unit>() {
                public Unit invoke(PagingData<NotificationModel> pagingData) {
                    invoke(pagingData);
                    return Unit.INSTANCE;
                }

                public void invoke(PagingData<NotificationModel> pagingData) {
                    NotificationListAdapter notificationListAdapter = notificationListActivity.getNotificationListAdapter();
                    Lifecycle lifecycle = notificationListActivity.getLifecycle();
                    Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
                    Intrinsics.checkNotNull(pagingData);
                    notificationListAdapter.submitData(lifecycle, pagingData);
                }
            }));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
