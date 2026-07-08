package com.proje.mobilesales.features.customer.view.info;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerInfoActivityonOptionsItemSelected1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final CustomerInfoActivity this0;
    int label;

    public CustomerInfoActivityonOptionsItemSelected1(CustomerInfoActivity customerInfoActivity, Continuation<? super CustomerInfoActivityonOptionsItemSelected1> continuation) {
        super (2, continuation);
        this.this0 = customerInfoActivity;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerInfoActivityonOptionsItemSelected1 (this.this0, continuation);
    }

    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend (Unit.INSTANCE);
    }

    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED ();
        int i = this.label;
        if (0 == i) {
            ResultKt.throwOnFailure (obj);
            CustomerInfoViewModel customerInfoViewModel = this.this0.viewModel;
            int clRef = this.this0.getClRef ();
            this.label = 1;
            if (customerInfoViewModel.getUpdateCustomer (clRef, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (1 == i) {
            ResultKt.throwOnFailure (obj);
        } else {
            throw new IllegalStateException ("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
