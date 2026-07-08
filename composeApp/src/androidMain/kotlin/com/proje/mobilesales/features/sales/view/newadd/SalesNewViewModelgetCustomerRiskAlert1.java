package com.proje.mobilesales.features.sales.view.newadd;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.RiskAlert;
import com.proje.mobilesales.core.enums.SalesType;
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

final class SalesNewViewModelgetCustomerRiskAlert1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int clRef;
    final Ref.ObjectRef<RiskAlert> result;
    final SalesType salesType;
    int label;
    final SalesNewViewModel this0; 
    SalesNewViewModelgetCustomerRiskAlert1(SalesNewViewModel salesNewViewModel, Ref.ObjectRef<RiskAlert> refObjectRef, SalesType salesType, int i2, Continuation<? super SalesNewViewModelgetCustomerRiskAlert1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesNewViewModel;
        this.result = refObjectRef;
        this.salesType = salesType;
        this.clRef = i2;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesNewViewModelgetCustomerRiskAlert1(this.this0, this.result, this.salesType, this.clRef, (Continuation<? super SalesNewViewModelgetCustomerRiskAlert1>) continuation);
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
                C28831 c28831 = new C28831(this.result, this.this0, this.salesType, this.clRef, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28831, this);
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
            e2 = Log.e(this.this0.getTAG(), "getCustomerRiskAlert", e3);
        }
        return boxing.boxInt(e2);
    } 
    static final class C28831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int clRef;
        final Ref.ObjectRef<RiskAlert> result;
        final SalesType salesType;
        int label;
        final SalesNewViewModel this0; 
        C28831(Ref.ObjectRef<RiskAlert> refObjectRef, SalesNewViewModel salesNewViewModel, SalesType salesType, int i2, Continuation<? super C28831> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.salesType = salesType;
            this.clRef = i2;
        } 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28831(this.result, this.this0, this.salesType, this.clRef, (Continuation<? super C28831>) continuation);
        } 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        } 
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getCustomerRiskAlert(this.salesType, this.clRef);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getCustomerRiskAlert"));
        }
    }
}
