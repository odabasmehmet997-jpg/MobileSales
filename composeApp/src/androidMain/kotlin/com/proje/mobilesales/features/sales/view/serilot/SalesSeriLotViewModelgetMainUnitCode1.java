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

final class SalesSeriLotViewModelgetMainUnitCode1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final String itemCode;
    final Ref.ObjectRef<String> result;
    int label;
    final SalesSeriLotViewModel this0;

    SalesSeriLotViewModelgetMainUnitCode1(Ref.ObjectRef<String> refObjectRef, SalesSeriLotViewModel salesSeriLotViewModel, String str, Continuation<? super SalesSeriLotViewModelgetMainUnitCode1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.result = refObjectRef     ;
        this.this0 = salesSeriLotViewModel;
        this.itemCode = str;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesSeriLotViewModelgetMainUnitCode1(this.result, this.this0, this.itemCode, (Continuation<? super SalesSeriLotViewModelgetMainUnitCode1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    static final class C29131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final String itemCode;
        final Ref.ObjectRef<String> result;
        int label;
        final SalesSeriLotViewModel this0;

        C29131(Ref.ObjectRef<String> refObjectRef, SalesSeriLotViewModel salesSeriLotViewModel, String str, Continuation<? super C29131> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesSeriLotViewModel;
            this.itemCode = str;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C29131(this.result, this.this0, this.itemCode, (Continuation<? super C29131>) continuation);
        }
        public Object invoke(SalesSeriLotViewModel coroutineScope, Continuation<Object> continuation) {
            return ((C29131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int e2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                this.result.element = this.this0.getRepository().getMainUnitCode(this.itemCode);
                e2 = Log.i(this.this0.getTAG(), "getMainUnitCode");
            } catch (Exception e3) {
                e2 = Log.e(this.this0.getTAG(), "getMainUnitCode", e3);
            }
            return boxing.boxInt(e2);
        }
        @Override
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }

    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C29131 c29131 = new C29131(this.result, this.this0, this.itemCode, null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, c29131, this);
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
