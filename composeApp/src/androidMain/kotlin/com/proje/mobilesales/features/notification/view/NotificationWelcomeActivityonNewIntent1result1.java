package com.proje.mobilesales.features.notification.view;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.base.BaseSelectResult;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class NotificationWelcomeActivityonNewIntent1result1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BaseSelectResult>, Object> {
    final   BaseErp baseErp;
    int label;

    NotificationWelcomeActivityonNewIntent1result1(final BaseErp baseErp, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.baseErp = baseErp;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new NotificationWelcomeActivityonNewIntent1result1(baseErp, continuation);
    }

    public Object invoke(final Object coroutineScope, final ? super CoroutineContext.Element continuation) {
        return this.create(coroutineScope, (Continuation<?>) continuation).invokeSuspend(Unit.INSTANCE);
    }

    public Object invokeSuspend(final Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (0 != this.label) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return baseErp.executeLoginFlow();
    }
}
