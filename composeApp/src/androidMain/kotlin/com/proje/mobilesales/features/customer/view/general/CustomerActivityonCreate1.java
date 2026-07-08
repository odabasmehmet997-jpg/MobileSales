package com.proje.mobilesales.features.customer.view.general;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerActivityonCreate1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final  CustomerActivity this0; 
    CustomerActivityonCreate1(CustomerActivity customerActivity, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerActivity;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerActivityonCreate1(this.this0, continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public Object invokeSuspend(Object obj) {
        CustomerGeneralViewModel customerGeneralViewModel;
        String oneCustomerSql;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            customerGeneralViewModel = this.this0.viewModel;
            CustomerActivity customerActivity = this.this0;
            oneCustomerSql = customerActivity.getOneCustomerSql();
            CustomerActivity.CustomerResponseListener customerResponseListener = new CustomerActivity.CustomerResponseListener(this.this0);
            this.label = 1;
            if (customerGeneralViewModel.getOneSqlManager(customerActivity, oneCustomerSql, customerResponseListener, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
