package com.proje.mobilesales.features.notification.view;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseSelectResult;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

/* compiled from: NotificationWelcomeActivity.kt */

@DebugMetadata(m23c = "com.proje.mobilesales.features.notification.view.NotificationWelcomeActivityonNewIntent1result1", m22f = "NotificationWelcomeActivity.kt", m21l = {}, m20m = "invokeSuspend")

public final class NotificationWelcomeActivityonNewIntent1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BaseSelectResult>, Object> {
    final BaseErp baseErp;
    int label;

    public NotificationWelcomeActivityonNewIntent1result1(BaseErp baseErp, Continuation<? super NotificationWelcomeActivityonNewIntent1result1> continuation) {
        super(2, continuation);
        this.baseErp = baseErp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new NotificationWelcomeActivityonNewIntent1result1(this.baseErp, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super BaseSelectResult> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.baseErp.executeLoginFlow();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
