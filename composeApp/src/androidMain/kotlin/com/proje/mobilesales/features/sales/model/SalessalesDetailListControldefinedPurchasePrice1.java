package com.proje.mobilesales.features.sales.model;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalessalesDetailListControldefinedPurchasePrice1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Double>, Object> {
    final  SalesDetail salesDetail;
    int label;
    final  Sales this0;
    SalessalesDetailListControldefinedPurchasePrice1(Sales sales, SalesDetail salesDetail, Continuation<? super SalessalesDetailListControldefinedPurchasePrice1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = sales;
        this.salesDetail = salesDetail;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalessalesDetailListControldefinedPurchasePrice1(this.this0, this.salesDetail, (Continuation<? super SalessalesDetailListControldefinedPurchasePrice1>) continuation);
    } 
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super kotlin.Result<com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        try {
            return ((SalessalesDetailListControldefinedPurchasePrice1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    } 
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxDouble(this.this0.viewModel.getDefinedPurchasePrice(this.salesDetail.getItemRef()));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
