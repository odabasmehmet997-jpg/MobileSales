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
import kotlin.jvm.internal.RefDoubleRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesDetailViewModelgetVariantRealStock1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final RefDoubleRef result;
    final int variantRef;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetVariantRealStock1(SalesDetailViewModel salesDetailViewModel, RefDoubleRef refDoubleRef, int i2, Continuation<? super SalesDetailViewModelgetVariantRealStock1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
        this.result = refDoubleRef;
        this.variantRef = i2;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetVariantRealStock1(this.this0, this.result, this.variantRef, (Continuation<? super SalesDetailViewModelgetVariantRealStock1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28561 c28561 = new C28561(this.result, this.this0, this.variantRef, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28561, this);
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
            e2 = Log.e(this.this0.getTAG(), "GetVariantRealStock", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final RefDoubleRef result;
        final int variantRef;
        int label;
        final SalesDetailViewModel this0;
        C28561(RefDoubleRef refDoubleRef, SalesDetailViewModel salesDetailViewModel, int i2, Continuation<? super C28561> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refDoubleRef;
            this.this0 = salesDetailViewModel;
            this.variantRef = i2;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28561(this.result, this.this0, this.variantRef, (Continuation<? super C28561>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getVariantRealStock(this.variantRef);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getVariantRealStock"));
        }
    }
}
