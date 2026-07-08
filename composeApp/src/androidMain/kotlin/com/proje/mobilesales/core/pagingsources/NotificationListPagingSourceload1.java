package com.proje.mobilesales.core.pagingsources;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class NotificationListPagingSourceload1 extends ContinuationImpl {
    int label;
    Object result;
    final NotificationListPagingSource this0;
    NotificationListPagingSourceload1(NotificationListPagingSource notificationListPagingSource, Continuation<? super NotificationListPagingSourceload1> continuation) {
        super((Continuation<Object>) continuation);
        this.this0 = notificationListPagingSource;
    } 
    public Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this0.load(null, this);
    }
}
