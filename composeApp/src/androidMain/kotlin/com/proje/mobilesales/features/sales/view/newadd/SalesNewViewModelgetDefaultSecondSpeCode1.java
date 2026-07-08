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

final class SalesNewViewModelgetDefaultSecondSpeCode1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    Object L0;
    int label;
    final SalesNewViewModel this0; 
    SalesNewViewModelgetDefaultSecondSpeCode1(SalesNewViewModel salesNewViewModel, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesNewViewModel;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new com.proje.mobilesales.features.sales.view.newadd.SalesNewViewModelgetDefaultSecondSpeCode1(this.this0, (Continuation<? super com.proje.mobilesales.features.sales.view.newadd.SalesNewViewModelgetDefaultSecondSpeCode1>) continuation);
    }
 
    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    } 
    public Object invokeSuspend(Object obj) {
        Ref.ObjectRef refObjectRef;
        Exception e2;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef refObjectRef2 = new Ref.ObjectRef();
            refObjectRef2.element = "";
            try {
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                C31331 c31331 = new C31331(refObjectRef2, this.this0, null);
                this.L0 = refObjectRef2;
                this.label = 1;
                if (BuildersKt.withContext(coroutineDispatcher, c31331, this) == obj2) {
                    return obj2;
                }
                refObjectRef = refObjectRef2;
            } catch (Exception e3) {
                refObjectRef = refObjectRef2;
                e2 = e3;
                Log.e(this.this0.getTAG(), "getDefaultSecondSpeCode", e2);
                return refObjectRef.element;
            }
        } else {
            if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            refObjectRef = (Ref.ObjectRef) this.L0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e4) {
                e2 = e4;
                Log.e(this.this0.getTAG(), "getDefaultSecondSpeCode", e2);
                return refObjectRef.element;
            }
        }
        return refObjectRef.element;
    }

    @Override
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }

    static final class C31331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<String> result;
        int label;
        final SalesNewViewModel this0;
        C31331(Ref.ObjectRef refObjectRef, SalesNewViewModel salesNewViewModel, Continuation<? super C31331> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31331(this.result, this.this0, (Continuation<? super C31331>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C31331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public   Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getDefaultSecondSpeCode();
            return Boxing.boxInt(Log.i(this.this0.getTAG(), "getDefaultSecondSpeCode"));
        }
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
}
