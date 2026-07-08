package com.proje.mobilesales.features.sales.utils;

import android.content.Context;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.utils.ContextUtils;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
 
final class PurchasePriceUtilsgetLastPurchasePrice2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    PurchasePriceUtilsgetLastPurchasePrice2(Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
    }
    public Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ProgressDialogBuilder<?> mProgressDialogBuilder = PurchasePriceUtils.INSTANCE.getMProgressDialogBuilder();
        if (mProgressDialogBuilder == null) {
            return null;
        }
        if (!mProgressDialogBuilder.isShowing()) {
            Context context = ContextUtils.getmContext();
            Intrinsics.checkNotNull(context);
            mProgressDialogBuilder.setMessage(context.getString(R.string.str_get_last_purchase_info)).show();
        }
        return Unit.INSTANCE;
    }
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }
}
