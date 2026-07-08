package com.proje.mobilesales.features.customer.view.operation;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.ICustomerOperationRepository;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.RefBooleanRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;


/* compiled from: CustomerOperationViewModel.kt */
@DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetIsReceipt1", m639f = "CustomerOperationViewModel.kt", m640l = {155}, m641m = "invokeSuspend")

final class CustomerOperationViewModelgetIsReceipt1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final RefBooleanRef result;
    int label;
    final CustomerOperationViewModel this0;

    
    CustomerOperationViewModelgetIsReceipt1(CustomerOperationViewModel customerOperationViewModel, RefBooleanRef refBooleanRef, Continuation<? super CustomerOperationViewModelgetIsReceipt1> continuation) {
        super(2, continuation);
        this.this0 = customerOperationViewModel;
        this.result = refBooleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetIsReceipt1(this.this0, this.result, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        String str;
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C26851 c26851 = new C26851(this.result, this.this0, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26851, this);
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
            e2 = Log.e(str, "GetIsReceipt", e3);
        }
        return boxing.boxInt(e2);
    }

    /* compiled from: CustomerOperationViewModel.kt */
    @DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetIsReceipt11", m639f = "CustomerOperationViewModel.kt", m640l = {}, m641m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetIsReceipt11 */
    static final class C26851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final RefBooleanRef result;
        int label;
        final CustomerOperationViewModel this0;

        
        C26851(RefBooleanRef refBooleanRef, CustomerOperationViewModel customerOperationViewModel, Continuation<? super C26851> continuation) {
            super(2, continuation);
            this.result = refBooleanRef;
            this.this0 = customerOperationViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26851(this.result, this.this0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            RefBooleanRef refBooleanRef = this.result;
            iCustomerOperationRepository = this.this0.repository;
            refBooleanRef.element = iCustomerOperationRepository.isReceiptUserMenu();
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetIsReceipt"));
        }
    }
}
