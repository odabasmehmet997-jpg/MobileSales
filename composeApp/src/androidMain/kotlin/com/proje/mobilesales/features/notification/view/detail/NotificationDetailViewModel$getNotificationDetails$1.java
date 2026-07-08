package com.proje.mobilesales.features.notification.view.detail;

import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import java.util.List;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationDetailViewModel.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDetails1", m22f = "NotificationDetailViewModel.kt", m21l = {30, 37}, m20m = "invokeSuspend")

public final class NotificationDetailViewModelgetNotificationDetails1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final Deferred<NotificationModel> getNotificationJob;
    final Deferred<NotifiedUserModel> getNotifiedUserJob;
    final Deferred<List<NotifiedUserModel>> getNotifiedUsersJob;
    final int notifiedUserId;
    Object L0;
    int label;
    final NotificationDetailViewModel this0;

    public NotificationDetailViewModelgetNotificationDetails1(NotificationDetailViewModel notificationDetailViewModel, Deferred<NotificationModel> deferred, Deferred<? extends List<NotifiedUserModel>> deferred2, Deferred<NotifiedUserModel> deferred3, int i, Continuation<? super NotificationDetailViewModelgetNotificationDetails1> continuation) {
        super(2, continuation);
        this.this0 = notificationDetailViewModel;
        this.getNotificationJob = deferred;
        this.getNotifiedUsersJob = deferred2;
        this.getNotifiedUserJob = deferred3;
        this.notifiedUserId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationDetailViewModelgetNotificationDetails1(this.this0, this.getNotificationJob, this.getNotifiedUsersJob, this.getNotifiedUserJob, this.notifiedUserId, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public Object invokeSuspend(Object r10) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.notification.view.detail.NotificationDetailViewModelgetNotificationDetails1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
