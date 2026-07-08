package com.proje.mobilesales.features.customer.view.general;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerActivityonOptionsItemSelected1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final   CustomerActivity this0;
 
    CustomerActivityonOptionsItemSelected1(CustomerActivity customerActivity, Continuation<? super CustomerActivityonOptionsItemSelected1> continuation) {
        super(2, continuation);
        this.this0 = customerActivity;
    }
 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerActivityonOptionsItemSelected1(this.this0, continuation);
    }
 
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
 
    static final class C26601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final CustomerActivity this0; 
        C26601(CustomerActivity customerActivity, Continuation<? super C26601> continuation) {
            super(2, continuation);
            this.this0 = customerActivity;
        }
 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26601(this.this0, continuation);
        }
 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(Object obj) {
            CustomerGeneralViewModel customerGeneralViewModel;
            CustomerDetail customerDetail;
            CustomerGeneralViewModel customerGeneralViewModel2;
            int i2;
            CustomerGeneralViewModel customerGeneralViewModel3;
            CustomerActivity customerActivity;
            String oneCustomerSql;
            CustomerActivity.CustomerResponseListener customerResponseListener;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                customerGeneralViewModel = this.this0.viewModel;
                customerDetail = this.this0.mCustomerDetail;
                Intrinsics.checkNotNull(customerDetail);
                int sRef = customerDetail.getSRef();
                this.label = 1;
                if (customerGeneralViewModel.updateShipAddress(sRef, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 != 3) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    ResultKt.throwOnFailure(obj);
                    customerGeneralViewModel3 = this.this0.viewModel;
                    customerActivity = this.this0;
                    oneCustomerSql = customerActivity.getOneCustomerSql();
                    customerResponseListener = new CustomerActivity.CustomerResponseListener(this.this0);
                    this.label = 3;
                    if (customerGeneralViewModel3.getOneSqlManager(customerActivity, oneCustomerSql, customerResponseListener, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            customerGeneralViewModel2 = this.this0.viewModel;
            i2 = this.this0.mLogicalRef;
            this.label = 2;
            if (customerGeneralViewModel2.getUpdateCustomer(i2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            customerGeneralViewModel3 = this.this0.viewModel;
            customerActivity = this.this0;
            oneCustomerSql = customerActivity.getOneCustomerSql();
            customerResponseListener = new CustomerActivity.CustomerResponseListener(this.this0);
            this.label = 3;
            if (customerGeneralViewModel3.getOneSqlManager(customerActivity, oneCustomerSql, customerResponseListener, this) == coroutine_suspended) {
            }
            return Unit.INSTANCE;
        }
    }
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C26601 c26601 = new C26601(this.this0, null);
            this.label = 1;
            if (BuildersKt.withContext(io2, c26601, this) == coroutine_suspended) {
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
