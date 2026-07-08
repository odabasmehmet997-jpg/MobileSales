package com.proje.mobilesales.features.sales.view.campaign;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

final class SalesCampaignViewModelgetItemNameFromSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final String code;
    final Ref.ObjectRef<String> result;
    int label;
    final SalesCampaignViewModel this0;
    SalesCampaignViewModelgetItemNameFromSqlHelper1(SalesCampaignViewModel salesCampaignViewModel, Ref.ObjectRef<String> refObjectRef, String str, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesCampaignViewModel;
        this.result = refObjectRef;
        this.code = str;
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        Unit i = (Unit) (  create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        return i;
    }
    public Object invokeSuspend(Object obj) {
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28351 c28351 = new C28351(this.result, this.this0, this.code, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28351, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            e2 = ((Number) obj).intValue();
        } catch (Exception e3) {
            e2 = Log.e(this.this0.getTAG(), "GetObjectForItemSlip", e3);
        }
        return Unit.INSTANCE;
    }
    static final class C28351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final String code;
        final Ref.ObjectRef<String> result;
        int label;
        final SalesCampaignViewModel this0;
        C28351(Ref.ObjectRef<String> refObjectRef, SalesCampaignViewModel salesCampaignViewModel, String str, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesCampaignViewModel;
            this.code = str;
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (Unit) ( create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getLogoSqlHelper().getItemName(this.code);
             String itemName = this.this0.getRepository().getLogoSqlHelper().getItemName(this.code);
            Intrinsics.checkNotNullExpressionValue(itemName, "getItemName(...)");
            this.result.element = itemName;
            return boxing.boxInt(Log.i(this.this0.getTAG(), "GetObjectForItemSlip"));
        }
    }
}
