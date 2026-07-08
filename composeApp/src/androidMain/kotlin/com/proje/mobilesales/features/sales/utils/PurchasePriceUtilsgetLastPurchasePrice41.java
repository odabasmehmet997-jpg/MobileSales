package com.proje.mobilesales.features.sales.utils;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
 
public final class PurchasePriceUtilsgetLastPurchasePrice41 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private Object coroutineScope;
    private Object continuation;

    PurchasePriceUtilsgetLastPurchasePrice41(Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
    }

    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ProgressDialogBuilder<?> mProgressDialogBuilder = PurchasePriceUtils.INSTANCE.getMProgressDialogBuilder();
        if (mProgressDialogBuilder != null) {
            mProgressDialogBuilder.dismiss();
        }
        return Unit.INSTANCE;
    }
    public Object invoke(Object p1, CoroutineContext.Element p2) {
        return null;
    }

    @Override
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }
}
