package com.proje.mobilesales.features.customer.view.operation;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.ICustomerOperationRepository;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerOperationViewModelgetIsCase1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.BooleanRef result;
    int label;
    final  CustomerOperationViewModel this0;

    CustomerOperationViewModelgetIsCase1(CustomerOperationViewModel customerOperationViewModel, BooleanRef refBooleanRef, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerOperationViewModel;
        this.result = refBooleanRef;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetIsCase1(this.this0, this.result, continuation);
    }

    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        String str;
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C26741 c26741 = new C26741( result, this.this0, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26741, this);
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
            str = this.this0.TAG;
            e2 = Log.e(str, "GetIsCase", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C26741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  BooleanRef result;
        int label;
        final   CustomerOperationViewModel this0;
        C26741(BooleanRef refBooleanRef, CustomerOperationViewModel customerOperationViewModel, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = customerOperationViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26741(this.result, this.this0, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            Object o = ((C26741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            return o;
        }
        public Object invokeSuspend(Object obj) {
            ICustomerOperationRepository iCustomerOperationRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef refBooleanRef = this.result;
            iCustomerOperationRepository =  this0.repository;
            refBooleanRef.element = iCustomerOperationRepository.isCaseUserMenu();
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetIsCase"));
        }
    }
}
