package com.proje.mobilesales.features.customer.view.communication.ship;

import android.content.Context;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
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

final class CustomerShipAddressViewModelgetCustomerShipAddressSql1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Context context;
    final int customerRef;
    final String filter;
    final Ref.ObjectRef<String> result;
    int label;
    final CustomerShipAddressViewModel this0;
    private static Boxing boxing;

    CustomerShipAddressViewModelgetCustomerShipAddressSql1(CustomerShipAddressViewModel customerShipAddressViewModel, Ref.ObjectRef refObjectRef, Context context, int i2, String str, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerShipAddressViewModel;
        this.result = refObjectRef;
        this.context = context;
        this.customerRef = i2;
        this.filter = str;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerShipAddressViewModelgetCustomerShipAddressSql1(this.this0, this.result, this.context, this.customerRef, this.filter, continuation);
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
                C26591 c26591 = new C26591(this.result, this.this0, this.context, this.customerRef, this.filter, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26591, this);
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
            e2 = Log.e(str, "getCustomerShipAddressSql", e3);
        }
        return Boxing.boxInt(e2);
    }
   static final class C26591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Context context;
        final int customerRef;
        final String filter;
        final Ref.ObjectRef<String> result;
        int label;
        final CustomerShipAddressViewModel this0;

        C26591(Ref.ObjectRef<String> refObjectRef, CustomerShipAddressViewModel customerShipAddressViewModel, Context context, int i2, String str, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = customerShipAddressViewModel;
            this.context = context;
            this.customerRef = i2;
            this.filter = str;
        }

        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26591(this.result, this.this0, this.context, this.customerRef, this.filter, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            BaseCustomerRepository baseCustomerRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef<String> refObjectRef = this.result;
            baseCustomerRepository = this.this0.repository;
            refObjectRef.element = baseCustomerRepository.getCustomerShipAddressSql(this.context, this.customerRef, this.filter);
            str = this.this0.TAG;
            return Boxing.boxInt(Log.i(str, "getCustomerShipAddressSql"));
        }
    }
}
