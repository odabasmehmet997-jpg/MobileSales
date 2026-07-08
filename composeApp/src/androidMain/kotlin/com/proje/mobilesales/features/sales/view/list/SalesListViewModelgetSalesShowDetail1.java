package com.proje.mobilesales.features.sales.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.FicheType;
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

final class SalesListViewModelgetSalesShowDetail1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final FicheType ficheType;
    final Ref.BooleanRef result;
    int label;
    final SalesListViewModel this0;

    
    SalesListViewModelgetSalesShowDetail1(SalesListViewModel salesListViewModel, Ref.BooleanRef refBooleanRef, FicheType ficheType, Continuation<? super SalesListViewModelgetSalesShowDetail1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesListViewModel;
        this.result = refBooleanRef;
        this.ficheType = ficheType;
    }

    
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesListViewModelgetSalesShowDetail1(this.this0, this.result, this.ficheType, continuation);
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
                C28661 c28661 = new C28661(this.result, this.this0, this.ficheType, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28661, this);
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
            e2 = Log.e(this.this0.getTAG(), "getSalesShowDetail", e3);
        }
        return boxing.boxInt(e2);
    }

    static final class C28661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final FicheType ficheType;
        final Ref.BooleanRef result;
        int label;
        final SalesListViewModel this0;

        
        C28661(Ref.BooleanRef refBooleanRef, SalesListViewModel salesListViewModel, FicheType ficheType, Continuation<? super C28661> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesListViewModel;
            this.ficheType = ficheType;
        }

        
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28661(this.result, this.this0, this.ficheType, continuation);
        }

        
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getSalesShowDetail(this.ficheType);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getSalesShowDetail"));
        }
    }
}
