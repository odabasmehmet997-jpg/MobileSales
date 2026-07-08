package com.proje.mobilesales.features.sales.model;

import com.proje.mobilesales.features.sales.utils.PurchasePriceUtils;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalessalesDetailListControllastPurchasePrice1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Double>, Object> {
    final SalesDetail salesDetail;
    int label; 
    SalessalesDetailListControllastPurchasePrice1(SalesDetail salesDetail, Continuation<? super SalessalesDetailListControllastPurchasePrice1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.salesDetail = salesDetail;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalessalesDetailListControllastPurchasePrice1(this.salesDetail, (Continuation<? super SalessalesDetailListControllastPurchasePrice1>) continuation);
    } 
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super kotlin.Result<com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    } 
    public Object invokeSuspend(Object obj) {
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            PurchasePriceUtils purchasePriceUtils = PurchasePriceUtils.INSTANCE;
            SalesDetail salesDetail = this.salesDetail;
            Intrinsics.checkNotNullExpressionValue(salesDetail, "salesDetail");
            this.label = 1;
            try {
                obj = purchasePriceUtils.getLastPurchasePrice(salesDetail, this);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else {
            if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
