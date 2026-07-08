package com.proje.mobilesales.features.sales.view.detail;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.sales.model.SalesDetail;
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

final class SalesDetailViewModelsetDueDateForSalesDetail1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int customerRef;
    final SalesDetail salesDetail;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelsetDueDateForSalesDetail1(SalesDetailViewModel salesDetailViewModel, SalesDetail salesDetail, int i2, Continuation<? super SalesDetailViewModelsetDueDateForSalesDetail1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
        this.salesDetail = salesDetail;
        this.customerRef = i2;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelsetDueDateForSalesDetail1(this.this0, this.salesDetail, this.customerRef, (Continuation<? super SalesDetailViewModelsetDueDateForSalesDetail1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28601 c28601 = new C28601(this.this0, this.salesDetail, this.customerRef, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28601, this);
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
            e2 = Log.e(this.this0.getTAG(), "SetDueDateForSalesDetail", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int customerRef;
        final SalesDetail salesDetail;
        int label;
        final SalesDetailViewModel this0;
        C28601(SalesDetailViewModel salesDetailViewModel, SalesDetail salesDetail, int i2, Continuation<? super C28601> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.this0 = salesDetailViewModel;
            this.salesDetail = salesDetail;
            this.customerRef = i2;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28601(this.this0, this.salesDetail, this.customerRef, (Continuation<? super C28601>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this0.getRepository().setDueDateForSalesDetail(this.salesDetail, this.customerRef);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "SetDueDateForSalesDetail"));
        }
    }
}
