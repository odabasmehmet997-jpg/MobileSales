package com.proje.mobilesales.features.sales.view.serilot;

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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesSeriLotViewModelgetSurplusAmountEnabled1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final  Ref.BooleanRef result;
    int label;
    final  SalesSeriLotViewModel this0; 
    SalesSeriLotViewModelgetSurplusAmountEnabled1(Ref.BooleanRef refBooleanRef, SalesSeriLotViewModel salesSeriLotViewModel, Continuation<? super SalesSeriLotViewModelgetSurplusAmountEnabled1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.result = refBooleanRef;
        this.this0 = salesSeriLotViewModel;
    }
 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesSeriLotViewModelgetSurplusAmountEnabled1(this.result, this.this0, continuation);
    }
 
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    } 
    static final class C29141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final   Ref.BooleanRef result;
        int label;
        final   SalesSeriLotViewModel this0;
 
        C29141(Ref.BooleanRef refBooleanRef, SalesSeriLotViewModel salesSeriLotViewModel, Continuation<? super C29141> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesSeriLotViewModel;
        }
 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C29141(this.result, this.this0, continuation);
        }
 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C29141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
 
        public Object invokeSuspend(Object obj) {
            int e2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                this.result.element = this.this0.getRepository().surplusAmountFromLogoParamValue();
                e2 = Log.i(this.this0.getTAG(), "GetSurplusAmountEnabled");
            } catch (Exception e3) {
                e2 = Log.e(this.this0.getTAG(), "GetSurplusAmountEnabled", e3);
            }
            return boxing.boxInt(e2);
        }
    }
 
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C29141 c29141 = new C29141(this.result, this.this0, null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, c29141, this);
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
