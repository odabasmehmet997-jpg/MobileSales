package com.proje.mobilesales.features.sales.view.newadd;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesNewViewModelgetOrderDoReserve1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    Object L0;
    int label;
    final SalesNewViewModel this0;
    SalesNewViewModelgetOrderDoReserve1(SalesNewViewModel salesNewViewModel, Continuation<? super SalesNewViewModelgetOrderDoReserve1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesNewViewModel;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesNewViewModelgetOrderDoReserve1(this.this0, (Continuation<? super SalesNewViewModelgetOrderDoReserve1>) continuation);
    }
    public  Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public  Object invokeSuspend(Object obj)  {
        Ref.BooleanRef refBooleanRef;
        Exception e2;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            refBooleanRef = new Ref.BooleanRef();
            try {
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                C31441 c31441 = new C31441(refBooleanRef, this.this0, null);
                this.L0 = refBooleanRef;
                this.label = 1;
                if (BuildersKt.withContext(coroutineDispatcher, c31441, this) == obj2) {
                    return obj2;
                }
            } catch (Exception e3) {
                e2 = e3;
                Log.e(this.this0.getTAG(), "getDefaultSecondSpeCode", e2);
                return Boxing.boxBoolean(refBooleanRef.element);
            }
        } else {
            if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            refBooleanRef = (Ref.BooleanRef) this.L0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e4) {
                e2 = e4;
                Log.e(this.this0.getTAG(), "getDefaultSecondSpeCode", e2);
                return Boxing.boxBoolean(refBooleanRef.element);
            }
        }
        return Boxing.boxBoolean(refBooleanRef.element);
    }
    static  final class C31441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final   Ref.BooleanRef result;
        int label;
        final  SalesNewViewModel this0;
        C31441(Ref.BooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super C31441> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31441(this.result, this.this0, (Continuation<? super C31441>) continuation);
        }
        public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C31441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public   Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getOrderDoReserve();
            return Boxing.boxInt(Log.i(this.this0.getTAG(), "getDefaultSecondSpeCode"));
        }

    }
}
