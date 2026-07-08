package com.proje.mobilesales.core.base;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
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

public final class BaseViewModelcheckCustomerRiskControl1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    IBaseRepository repository = null;
    Object L0;
    int label;
    private Object coroutineScope;
    private Object continuation;

    public BaseViewModelcheckCustomerRiskControl1(IBaseRepository iBaseRepository, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.repository = iBaseRepository;
    }

    public BaseViewModelcheckCustomerRiskControl1(BaseCustomerViewModel this0, int customerRef, Continuation<?> continuation) {

    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new BaseViewModelcheckCustomerRiskControl1(this.repository, continuation);
    }
    public Object invoke(Object coroutineScope, CoroutineContext.Element continuation) {
        this.coroutineScope = coroutineScope;
        this.continuation = continuation;
        Object o = create(coroutineScope, (Continuation<?>) continuation).invokeSuspend(Unit.INSTANCE);
        return o;
    }

    public Object invokeSuspend(Object obj) {
        Ref.BooleanRef refBooleanRef;
        Exception e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef refBooleanRef2 = new Ref.BooleanRef();
            try {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C24771 c24771 = new C24771(refBooleanRef2, this.repository, null);
                this.L0 = refBooleanRef2;
                this.label = 1;
                if (BuildersKt.withContext(io2, c24771, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                refBooleanRef = refBooleanRef2;
            } catch (Exception e3) {
                refBooleanRef = refBooleanRef2;
                e2 = e3;
                Log.e(BaseViewModel.TAG, "getCheckCustomerRiskControl", e2);
                return boxing.boxBoolean(refBooleanRef.element);
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            refBooleanRef = (Ref.BooleanRef) this.L0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e4) {
                e2 = e4;
                Log.e(BaseViewModel.TAG, "getCheckCustomerRiskControl", e2);
                return boxing.boxBoolean(refBooleanRef.element);
            }
        }
        return boxing.boxBoolean(refBooleanRef.element);
    }

    @Override
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }

    static final class C24771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final IBaseRepository repository;
        final Ref.BooleanRef result;
        int label;

        C24771(Ref.BooleanRef refBooleanRef, IBaseRepository iBaseRepository, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.repository = iBaseRepository;
        }

        public   Object invoke(Object coroutineScope,  CoroutineContext.Element continuation) {
            return ( create(coroutineScope, (Continuation<?>) continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.repository.getCheckCustomerRiskControl();
            return boxing.boxInt(Log.i(BaseViewModel.TAG, "getCheckCustomerRiskControl"));
        }
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
}
