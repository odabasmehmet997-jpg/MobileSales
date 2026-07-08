package com.proje.mobilesales.features.customer.view.operation;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
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
@DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetIsRetailInvoice1", m639f = "CustomerOperationViewModel.kt", m640l = {TypedValues.AttributesType.TYPE_EASING}, m641m = "invokeSuspend")

final class CustomerOperationViewModelgetIsRetailInvoice1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final RefBooleanRef result;
    int label;
    final CustomerOperationViewModel this0;

    
    CustomerOperationViewModelgetIsRetailInvoice1(CustomerOperationViewModel customerOperationViewModel, RefBooleanRef refBooleanRef, Continuation<? super CustomerOperationViewModelgetIsRetailInvoice1> continuation) {
        super(2, continuation);
        this.this0 = customerOperationViewModel;
        this.result = refBooleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetIsRetailInvoice1(this.this0, this.result, continuation);
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
                C26861 c26861 = new C26861(this.result, this.this0, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26861, this);
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
            e2 = Log.e(str, "GetIsRetailInvoice", e3);
        }
        return boxing.boxInt(e2);
    }

    /* compiled from: CustomerOperationViewModel.kt */
    @DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetIsRetailInvoice11", m639f = "CustomerOperationViewModel.kt", m640l = {}, m641m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetIsRetailInvoice11 */
    static final class C26861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final RefBooleanRef result;
        int label;
        final CustomerOperationViewModel this0;

        
        C26861(RefBooleanRef refBooleanRef, CustomerOperationViewModel customerOperationViewModel, Continuation<? super C26861> continuation) {
            super(2, continuation);
            this.result = refBooleanRef;
            this.this0 = customerOperationViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26861(this.result, this.this0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            refBooleanRef.element = iCustomerOperationRepository.isRetailInvoiceUserMenu();
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetIsRetailInvoice"));
        }
    }
}
