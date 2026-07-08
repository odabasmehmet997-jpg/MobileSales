package com.proje.mobilesales.features.sales.view.order;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class LastOrderProductViewModelgetLastOrderProductList1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int clientRef;
    final boolean isAllSalesman;
    final ResponseListener<?> responseListener;
    int label;
    final LastOrderProductViewModel this0;
 
    LastOrderProductViewModelgetLastOrderProductList1(LastOrderProductViewModel lastOrderProductViewModel, int i2, boolean z, ResponseListener<?> responseListener, Continuation<? super LastOrderProductViewModelgetLastOrderProductList1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = lastOrderProductViewModel;
        this.clientRef = i2;
        this.isAllSalesman = z;
        this.responseListener = responseListener;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new LastOrderProductViewModelgetLastOrderProductList1(this.this0, this.clientRef, this.isAllSalesman, this.responseListener, continuation);
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
                C29111 c29111 = new C29111(this.this0, this.clientRef, this.isAllSalesman, this.responseListener, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c29111, this);
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
            e2 = Log.e(this.this0.getTAG(), "getLastOrderProductList", e3);
        }
        return boxing.boxInt(e2);
    }
     static final class C29111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int clientRef;
        final boolean isAllSalesman;
        final ResponseListener<?> responseListener;
        int label;
        final LastOrderProductViewModel this0;
 
        C29111(LastOrderProductViewModel lastOrderProductViewModel, int i2, boolean z, ResponseListener<?> responseListener, Continuation<? super C29111> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.this0 = lastOrderProductViewModel;
            this.clientRef = i2;
            this.isAllSalesman = z;
            this.responseListener = responseListener;
        }
 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C29111(this.this0, this.clientRef, this.isAllSalesman, this.responseListener, continuation);
        }
 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C29111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
 
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this0.getRepository().getLastOrderProductList(this.clientRef, this.isAllSalesman, this.responseListener);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getLastOrderProductList"));
        }
    }
}
