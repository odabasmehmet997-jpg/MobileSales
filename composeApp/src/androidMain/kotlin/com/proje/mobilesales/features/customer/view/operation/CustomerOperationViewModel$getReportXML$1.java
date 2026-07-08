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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerOperationViewModelgetReportXML1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int reportId;
    final Ref.ObjectRef<String> result;
    int label;
    final CustomerOperationViewModel this0;

    
    CustomerOperationViewModelgetReportXML1(CustomerOperationViewModel customerOperationViewModel, Ref.ObjectRef refObjectRef, int i2, Continuation<? super CustomerOperationViewModelgetReportXML1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerOperationViewModel;
        this.result = refObjectRef;
        this.reportId = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetReportXML1(this.this0, this.result, this.reportId, continuation);
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
                C26911 c26911 = new C26911(this.result, this.this0, this.reportId, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26911, this);
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
            e2 = Log.e(str, "getReportXML", e3);
        }
        return boxing.boxInt(e2);
    }

    static final class C26911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int reportId;
        final Ref.ObjectRef<String> result;
        int label;
        final CustomerOperationViewModel this0;

        
        C26911(Ref.ObjectRef<String> refObjectRef, CustomerOperationViewModel customerOperationViewModel, int i2, Continuation<? super C26911> continuation) {
            super(2, continuation);
            this.result = refObjectRef;
            this.this0 = customerOperationViewModel;
            this.reportId = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26911(this.result, this.this0, this.reportId, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26911) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(Object obj) {
            ICustomerOperationRepository iCustomerOperationRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef<String> refObjectRef = this.result;
            iCustomerOperationRepository = this.this0.repository;
              reportXML = iCustomerOperationRepository.getLogoSqlHelper().getReportXML(this.reportId);
            Intrinsics.checkNotNullExpressionValue(reportXML, "getReportXML(...)");
            refObjectRef.element = reportXML;
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "getReportXML"));
        }
    }
}
