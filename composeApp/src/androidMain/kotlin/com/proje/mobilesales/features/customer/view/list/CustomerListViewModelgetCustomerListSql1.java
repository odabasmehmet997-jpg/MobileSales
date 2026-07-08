package com.proje.mobilesales.features.customer.view.list;

import android.content.Context;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.utils.CustomerFilter;
import com.proje.mobilesales.features.customer.repository.ICustomerListRepository;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerListViewModelgetCustomerListSql1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final  Context context;
    final  CustomerFilter customerFilter;
    final  Ref.ObjectRef<String> result;
    int label;
    final  CustomerListViewModel this0;

    CustomerListViewModelgetCustomerListSql1(CustomerListViewModel customerListViewModel, Ref.ObjectRef<String> refObjectRef, Context context, CustomerFilter customerFilter, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerListViewModel;
        this.result = refObjectRef;
        this.context = context;
        this.customerFilter = customerFilter;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerListViewModelgetCustomerListSql1(this.this0, this.result, this.context, this.customerFilter, continuation);
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
                C26671 c26671 = new C26671(this.result, this.this0, this.context, this.customerFilter, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26671, this);
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
            e2 = Log.e(str, "getCustomerListSql", e3);
        }
        return boxing.boxInt(e2);
    }
     static final class C26671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Context context;
        final  CustomerFilter customerFilter;
        final  Ref.ObjectRef<String> result;
        int label;
        final  CustomerListViewModel this0;

        C26671(Ref.ObjectRef<String> refObjectRef, CustomerListViewModel customerListViewModel, Context context, CustomerFilter customerFilter, Continuation<? super C26671> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = customerListViewModel;
            this.context = context;
            this.customerFilter = customerFilter;
        }

        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26671(this.result, this.this0, this.context, this.customerFilter, continuation);
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        } 
        public Object invokeSuspend(Object obj) {
            ICustomerListRepository iCustomerListRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef<String> refObjectRef = this.result;
            iCustomerListRepository = this.this0.repository;
             customerListSql = iCustomerListRepository.getLogoSqlHelper().getCustomerListSql(this.context, this.customerFilter);
            Intrinsics.checkNotNullExpressionValue(customerListSql, "getCustomerListSql(...)");
            refObjectRef.element = customerListSql;
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "getCustomerListSql"));
        }
    }
}
