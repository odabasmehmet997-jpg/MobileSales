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
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesDetailViewModelgetLastCurRate1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final String currCode;
    final int currType;
    final Ref.DoubleRef result;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetLastCurRate1(SalesDetailViewModel salesDetailViewModel, Ref.DoubleRef refDoubleRef, String str, int i2, Continuation<? super SalesDetailViewModelgetLastCurRate1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
        this.result = refDoubleRef;
        this.currCode = str;
        this.currType = i2;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetLastCurRate1(this.this0, this.result, this.currCode, this.currType, (Continuation<? super SalesDetailViewModelgetLastCurRate1>) continuation);
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
                C28451 c28451 = new C28451(this.result, this.this0, this.currCode, this.currType, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28451, this);
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
            e2 = Log.e(this.this0.getTAG(), "GetLastCurRate", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final String currCode;
        final int currType;
        final Ref.DoubleRef result;
        int label;
        final SalesDetailViewModel this0;
 
        C28451(Ref.DoubleRef refDoubleRef, SalesDetailViewModel salesDetailViewModel, String str, int i2, Continuation<? super C28451> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refDoubleRef;
            this.this0 = salesDetailViewModel;
            this.currCode = str;
            this.currType = i2;
        } 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28451(this.result, this.this0, this.currCode, this.currType, (Continuation<? super C28451>) continuation);
        }
 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
 
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getLastCurRate(this.currCode, this.currType);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "GetLastCurRate"));
        }
    }
}
