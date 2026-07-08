package com.proje.mobilesales.features.sales.view.campaign;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

final class SalesCampaignViewModelgetObjectForItemSlip1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final boolean clearOnGet;
    final Ref.ObjectRef<ItemSlip> result;
    final int syncCode;
    int label;
    final SalesCampaignViewModel this0;
    SalesCampaignViewModelgetObjectForItemSlip1(SalesCampaignViewModel salesCampaignViewModel, Ref.ObjectRef<ItemSlip> refObjectRef, int i2, boolean z, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesCampaignViewModel;
        this.result = refObjectRef;
        this.syncCode = i2;
        this.clearOnGet = z;
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return ( create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28361 c28361 = new C28361(this.result, this.this0, this.syncCode, this.clearOnGet, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28361, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            assert obj != null;
            e2 = ((Number) obj).intValue();
        } catch (Exception e3) {
            e2 = Log.e(this.this0.getTAG(), "GetObjectForItemSlip", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final boolean clearOnGet;
        final Ref.ObjectRef<ItemSlip> result;
        final int syncCode;
        int label;
        final SalesCampaignViewModel this0;
 
        C28361(Ref.ObjectRef<ItemSlip> refObjectRef, SalesCampaignViewModel salesCampaignViewModel, int i2, boolean z, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesCampaignViewModel;
            this.syncCode = i2;
            this.clearOnGet = z;
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ( create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
 
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getItemSlipObject(this.syncCode, this.clearOnGet);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "GetObjectForItemSlip"));
        }
    }
}
