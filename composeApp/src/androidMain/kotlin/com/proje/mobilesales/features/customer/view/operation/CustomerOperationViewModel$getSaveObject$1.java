package com.proje.mobilesales.features.customer.view.operation;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.reportparser.Report;
import com.proje.mobilesales.features.customer.repository.ICustomerOperationRepository;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.RefIntRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;


/* compiled from: CustomerOperationViewModel.kt */
@DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetSaveObject1", m639f = "CustomerOperationViewModel.kt", m640l = {71}, m641m = "invokeSuspend")

final class CustomerOperationViewModelgetSaveObject1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Report report;
    final RefIntRef result;
    int label;
    final CustomerOperationViewModel this0;

    
    CustomerOperationViewModelgetSaveObject1(CustomerOperationViewModel customerOperationViewModel, Ref.IntRef refIntRef, Report report, Continuation<? super CustomerOperationViewModelgetSaveObject1> continuation) {
        super(2, continuation);
        this.this0 = customerOperationViewModel;
        this.result = refIntRef;
        this.report = report;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetSaveObject1(this.this0, this.result, this.report, continuation);
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
                C26921 c26921 = new C26921(this.result, this.this0, this.report, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26921, this);
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
            e2 = Log.e(str, "GetReportXML", e3);
        }
        return boxing.boxInt(e2);
    }

    /* compiled from: CustomerOperationViewModel.kt */
    @DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetSaveObject11", m639f = "CustomerOperationViewModel.kt", m640l = {}, m641m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.customer.view.operation.CustomerOperationViewModelgetSaveObject11 */
    static final class C26921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Report report;
        final RefIntRef result;
        int label;
        final CustomerOperationViewModel this0;

        
        C26921(RefIntRef refIntRef, CustomerOperationViewModel customerOperationViewModel, Report report, Continuation<? super C26921> continuation) {
            super(2, continuation);
            this.result = refIntRef;
            this.this0 = customerOperationViewModel;
            this.report = report;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26921(this.result, this.this0, this.report, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            RefIntRef refIntRef = this.result;
            iCustomerOperationRepository = this.this0.repository;
            refIntRef.element = iCustomerOperationRepository.getSaveObject(this.report);
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetReportXML"));
        }
    }
}
