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
import kotlin.jvm.internal.RefBooleanRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;


final class SalesDetailViewModelgetUseVatIncForProductsDontHavePriceCard1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L0;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetUseVatIncForProductsDontHavePriceCard1(SalesDetailViewModel salesDetailViewModel, Continuation<? super SalesDetailViewModelgetUseVatIncForProductsDontHavePriceCard1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetUseVatIncForProductsDontHavePriceCard1(this.this0, (Continuation<? super SalesDetailViewModelgetUseVatIncForProductsDontHavePriceCard1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public  Object invokeSuspend(Object obj) {
        RefBooleanRef refBooleanRef;
        Exception e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            RefBooleanRef refBooleanRef2 = new RefBooleanRef();
            try {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28551 c28551 = new C28551(refBooleanRef2, this.this0, null);
                this.L0 = refBooleanRef2;
                this.label = 1;
                if (BuildersKt.withContext(io2, c28551, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                refBooleanRef = refBooleanRef2;
            } catch (Exception e3) {
                refBooleanRef = refBooleanRef2;
                e2 = e3;
                Log.e(this.this0.getTAG(), "GetUseVatIncForProductsDontHavePriceCard", e2);
                return boxing.boxBoolean(refBooleanRef.element);
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            refBooleanRef = (RefBooleanRef) this.L0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e4) {
                e2 = e4;
                Log.e(this.this0.getTAG(), "GetUseVatIncForProductsDontHavePriceCard", e2);
                return boxing.boxBoolean(refBooleanRef.element);
            }
        }
        return boxing.boxBoolean(refBooleanRef.element);
    }
    static final class C28551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final RefBooleanRef result;
        int label;
        final SalesDetailViewModel this0;
        C28551(RefBooleanRef refBooleanRef, SalesDetailViewModel salesDetailViewModel, Continuation<? super C28551> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesDetailViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28551(this.result, this.this0, (Continuation<? super C28551>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getUseVatIncForProductsDontHavePriceCard();
            return boxing.boxInt(Log.i(this.this0.getTAG(), "GetUseVatIncForProductsDontHavePriceCard"));
        }
    }
}
