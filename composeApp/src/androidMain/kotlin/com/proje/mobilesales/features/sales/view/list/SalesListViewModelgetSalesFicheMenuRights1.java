package com.proje.mobilesales.features.sales.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.sales.model.SalesFicheMenuRights;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;


final class SalesListViewModelgetSalesFicheMenuRights1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final RefObjectRef<SalesFicheMenuRights> result;
    int label;
    final SalesListViewModel this0;

    
    SalesListViewModelgetSalesFicheMenuRights1(SalesListViewModel salesListViewModel, RefObjectRef<SalesFicheMenuRights> refObjectRef, Continuation<? super SalesListViewModelgetSalesFicheMenuRights1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesListViewModel;
        this.result = refObjectRef;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesListViewModelgetSalesFicheMenuRights1(this.this0, this.result, (Continuation<? super SalesListViewModelgetSalesFicheMenuRights1>) continuation);
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
                C28651 c28651 = new C28651(this.result, this.this0, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28651, this);
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
            e2 = Log.e(this.this0.getTAG(), "getSalesFicheMenuRights", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final RefObjectRef<SalesFicheMenuRights> result;
        int label;
        final SalesListViewModel this0;

        
        C28651(RefObjectRef<SalesFicheMenuRights> refObjectRef, SalesListViewModel salesListViewModel, Continuation<? super C28651> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesListViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28651(this.result, this.this0, (Continuation<? super C28651>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getSalesFicheMenuRights();
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getSalesFicheMenuRights"));
        }
    }
}
