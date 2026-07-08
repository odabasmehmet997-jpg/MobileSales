package com.proje.mobilesales.features.sales.view.detail;

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

final class SalesDetailViewModelgetLocalCurrType1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    Object L0;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetLocalCurrType1(SalesDetailViewModel salesDetailViewModel, Continuation<? super SalesDetailViewModelgetLocalCurrType1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetLocalCurrType1(this.this0, (Continuation<? super SalesDetailViewModelgetLocalCurrType1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        Ref.IntRef refIntRef;
        Exception e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.IntRef refIntRef2 = new Ref.IntRef();
            try {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28461 c28461 = new C28461(refIntRef2, this.this0, null);
                this.L0 = refIntRef2;
                this.label = 1;
                if (BuildersKt.withContext(io2, c28461, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                refIntRef = refIntRef2;
            } catch (Exception e3) {
                refIntRef = refIntRef2;
                e2 = e3;
                Log.e(this.this0.getTAG(), "GetLocalCurrType", e2);
                return boxing.boxInt(refIntRef.element);
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            refIntRef = (Ref.IntRef) this.L0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e4) {
                e2 = e4;
                Log.e(this.this0.getTAG(), "GetLocalCurrType", e2);
                return boxing.boxInt(refIntRef.element);
            }
        }
        return boxing.boxInt(refIntRef.element);
    }
    static final class C28461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.IntRef result;
        int label;
        final SalesDetailViewModel this0;
 
        C28461(Ref.IntRef refIntRef, SalesDetailViewModel salesDetailViewModel, Continuation<? super C28461> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refIntRef;
            this.this0 = salesDetailViewModel;
        }  
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28461(this.result, this.this0, (Continuation<? super C28461>) continuation);
        }
 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
 
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getLocalCurrType();
            return boxing.boxInt(Log.i(this.this0.getTAG(), "GetLocalCurrType"));
        }
    }
}
