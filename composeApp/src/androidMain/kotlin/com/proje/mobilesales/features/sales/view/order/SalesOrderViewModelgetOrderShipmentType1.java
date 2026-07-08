package com.proje.mobilesales.features.sales.view.order;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
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

final class SalesOrderViewModelgetOrderShipmentType1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.ObjectRef<String> result;
    final SalesType salesType;
    int label;
    final SalesOrderViewModel this0;
 
    SalesOrderViewModelgetOrderShipmentType1(SalesOrderViewModel salesOrderViewModel, Ref.ObjectRef<String> refObjectRef, SalesType salesType, Continuation<? super SalesOrderViewModelgetOrderShipmentType1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesOrderViewModel;
        this.result = refObjectRef;
        this.salesType = salesType;
    }
 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesOrderViewModelgetOrderShipmentType1(this.this0, this.result, this.salesType, continuation);
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
                C29121 c29121 = new C29121(this.result, this.this0, this.salesType, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c29121, this);
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
            e2 = Log.e(this.this0.getTAG(), "getOrderShipmentType", e3);
        }
        return boxing.boxInt(e2);
    }
 
    static final class C29121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<String> result;
        final SalesType salesType;
        int label;
        final SalesOrderViewModel this0;
 
        C29121(Ref.ObjectRef<String> refObjectRef, SalesOrderViewModel salesOrderViewModel, SalesType salesType, Continuation<? super C29121> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesOrderViewModel;
            this.salesType = salesType;
        } 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C29121(this.result, this.this0, this.salesType, continuation);
        } 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C29121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
 
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getOrderShipmentType(this.salesType);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getOrderShipmentType"));
        }
    }
}
