package com.proje.mobilesales.features.sales.view.detail;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.features.product.model.ProductListParameter;
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

final class SalesDetailViewModelgetProductListParameter1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.ObjectRef<ProductListParameter> result;
    final SalesType salesType;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetProductListParameter1(Ref.ObjectRef<ProductListParameter> refObjectRef, SalesDetailViewModel salesDetailViewModel, SalesType salesType, Continuation<? super SalesDetailViewModelgetProductListParameter1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.result = refObjectRef;
        this.this0 = salesDetailViewModel;
        this.salesType = salesType;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetProductListParameter1(this.result, this.this0, this.salesType, (Continuation<? super SalesDetailViewModelgetProductListParameter1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    static final class C28481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<ProductListParameter> result;
        final SalesType salesType;
        int label;
        final SalesDetailViewModel this0;

        C28481(Ref.ObjectRef<ProductListParameter> refObjectRef, SalesDetailViewModel salesDetailViewModel, SalesType salesType, Continuation<? super C28481> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesDetailViewModel;
            this.salesType = salesType;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28481(this.result, this.this0, this.salesType, (Continuation<? super C28481>) continuation);
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int e2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                this.result.element = this.this0.getRepository().getProductListParameter(this.salesType);
                e2 = Log.i(this.this0.getTAG(), "GetProductListParameter");
            } catch (Exception e3) {
                e2 = Log.e(this.this0.getTAG(), "GetProductListParameter", e3);
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
            C28481 c28481 = new C28481(this.result, this.this0, this.salesType, null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, c28481, this);
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
