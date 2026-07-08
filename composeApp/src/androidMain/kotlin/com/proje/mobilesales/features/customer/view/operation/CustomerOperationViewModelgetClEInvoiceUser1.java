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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerOperationViewModelgetClEInvoiceUser1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int customerRef;
    final Ref.IntRef result;
    int label;
    final CustomerOperationViewModel this0;

    CustomerOperationViewModelgetClEInvoiceUser1(CustomerOperationViewModel customerOperationViewModel, Ref.IntRef refIntRef, int i2, Continuation<? super CustomerOperationViewModelgetClEInvoiceUser1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerOperationViewModel;
        this.result = refIntRef;
        this.customerRef = i2;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetClEInvoiceUser1(this.this0, this.result, this.customerRef, continuation);
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
                C26731 c26731 = new C26731(result, this.this0, this.customerRef, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26731, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            e2 = ((Number) obj).intValue();
        } catch (Exception e3) {
            str = this.this0.TAG;
            e2 = Log.e(str, "GetClEInvoiceUser", e3);
        }
        return boxing.boxInt(e2);
    }

    static final class C26731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int customerRef;
        final Ref.IntRef result;
        int label;
        final CustomerOperationViewModel this0;
        C26731(Ref.IntRef refIntRef, CustomerOperationViewModel customerOperationViewModel, int i2, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refIntRef;
            this.this0 = customerOperationViewModel;
            this.customerRef = i2;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26731(this.result, this.this0, this.customerRef, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public Object invokeSuspend(Object obj) {
            ICustomerOperationRepository iCustomerOperationRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.IntRef refIntRef = this.result;
            iCustomerOperationRepository = this.this0.repository;
            refIntRef.element = iCustomerOperationRepository.getLogoSqlHelper().getClEInvoiceUser(this.customerRef);
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetClEInvoiceUser"));
        }
    }
}
