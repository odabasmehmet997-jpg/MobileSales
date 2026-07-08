package com.proje.mobilesales.features.customer.view.general;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.IBaseCustomerRepository;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;


/* compiled from: CustomerGeneralViewModel.kt */
@DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.general.CustomerGeneralViewModelgetCustomerInCharge1", m639f = "CustomerGeneralViewModel.kt", m640l = {33}, m641m = "invokeSuspend")

final class CustomerGeneralViewModelgetCustomerInCharge1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final String cloCode;
    final RefObjectRef<String> result;
    int label;
    final CustomerGeneralViewModel this0;

    
    CustomerGeneralViewModelgetCustomerInCharge1(CustomerGeneralViewModel customerGeneralViewModel, RefObjectRef<String> refObjectRef, String str, Continuation<? super CustomerGeneralViewModelgetCustomerInCharge1> continuation) {
        super(2, continuation);
        this.this0 = customerGeneralViewModel;
        this.result = refObjectRef;
        this.cloCode = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerGeneralViewModelgetCustomerInCharge1(this.this0, this.result, this.cloCode, continuation);
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
                C26611 c26611 = new C26611(this.result, this.this0, this.cloCode, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26611, this);
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
            e2 = Log.e(str, "GetCustomerInCharge", e3);
        }
        return boxing.boxInt(e2);
    }

    /* compiled from: CustomerGeneralViewModel.kt */
    @DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.general.CustomerGeneralViewModelgetCustomerInCharge11", m639f = "CustomerGeneralViewModel.kt", m640l = {}, m641m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.customer.view.general.CustomerGeneralViewModelgetCustomerInCharge11 */
    static final class C26611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final String cloCode;
        final RefObjectRef<String> result;
        int label;
        final CustomerGeneralViewModel this0;

        
        C26611(RefObjectRef<String> refObjectRef, CustomerGeneralViewModel customerGeneralViewModel, String str, Continuation<? super C26611> continuation) {
            super(2, continuation);
            this.result = refObjectRef;
            this.this0 = customerGeneralViewModel;
            this.cloCode = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26611(this.result, this.this0, this.cloCode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IBaseCustomerRepository iBaseCustomerRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            RefObjectRef<String> refObjectRef = this.result;
            iBaseCustomerRepository = this.this0.repository;
            refObjectRef.element = iBaseCustomerRepository.getCustomerInCharge(this.cloCode);
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetCustomerInCharge"));
        }
    }
}
