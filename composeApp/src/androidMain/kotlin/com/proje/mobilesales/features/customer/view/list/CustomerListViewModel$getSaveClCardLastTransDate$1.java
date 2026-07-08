package com.proje.mobilesales.features.customer.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.ICustomerListRepository;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerListViewModelgetSaveClCardLastTransDate1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    int label;
    final  CustomerListViewModel this0;

    CustomerListViewModelgetSaveClCardLastTransDate1(CustomerListViewModel customerListViewModel, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerListViewModel;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerListViewModelgetSaveClCardLastTransDate1(this.this0, continuation);
    }
 
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
 
    static final class C26681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;
        final  CustomerListViewModel this0;
 
        C26681(CustomerListViewModel customerListViewModel, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.this0 = customerListViewModel;
        } 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26681(this.this0, continuation);
        } 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        } 
        public Object invokeSuspend(Object obj) {
            String str;
            int e2;
            ICustomerListRepository iCustomerListRepository;
            String str2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    iCustomerListRepository = this.this0.repository;
                    iCustomerListRepository.getSaveClCardLastTransDate();
                    str2 = this.this0.TAG;
                    e2 = Log.i(str2, "getSaveClCardLastTransDate");
                } catch (Exception e3) {
                    str = this.this0.TAG;
                    e2 = Log.e(str, "getSaveClCardLastTransDate", e3);
                }
                return boxing.boxInt(e2);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
 
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C26681 c26681 = new C26681(this.this0, null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, c26681, this);
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
