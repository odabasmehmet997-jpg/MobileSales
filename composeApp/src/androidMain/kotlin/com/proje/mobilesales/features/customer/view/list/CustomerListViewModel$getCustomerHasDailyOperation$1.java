package com.proje.mobilesales.features.customer.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.ICustomerListRepository;
import com.proje.mobilesales.features.model.Resource;
import io.reactivex.Observable;
import kotlin.Result;
import kotlin.ResultKt;
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


/* compiled from: CustomerListViewModel.kt */
@DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.list.CustomerListViewModelgetCustomerHasDailyOperation1", m639f = "CustomerListViewModel.kt", m640l = {43}, m641m = "invokeSuspend")

final class CustomerListViewModelgetCustomerHasDailyOperation1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int customerRef;
    final RefObjectRef<Observable<Resource<Boolean>>> result;
    int label;
    final CustomerListViewModel this0;

    
    CustomerListViewModelgetCustomerHasDailyOperation1(final CustomerListViewModel customerListViewModel, final RefObjectRef<Observable<Resource<Boolean>>> refObjectRef, final int i2, final Continuation<? super CustomerListViewModelgetCustomerHasDailyOperation1> continuation) {
        super(2, continuation);
        this0 = customerListViewModel;
        result = refObjectRef;
        customerRef = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new CustomerListViewModelgetCustomerHasDailyOperation1(this0, result, customerRef, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return this.create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        final String str;
        int e2;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        try {
            if (0 == i2) {
                ResultKt.throwOnFailure(obj);
                final CoroutineDispatcher io2 = Dispatchers.getIO();
                final C26661 c26661 = new C26661(result, this0, customerRef, null);
                label = 1;
                obj = BuildersKt.withContext(io2, c26661, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (1 != i2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            e2 = ((Number) obj).intValue();
        } catch (final Exception e3) {
            str = this0.TAG;
            e2 = Log.e(str, "getCustomerHasDailyOperation", e3);
        }
        return boxing.boxInt(e2);
    }

    /* compiled from: CustomerListViewModel.kt */
    @DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.list.CustomerListViewModelgetCustomerHasDailyOperation11", m639f = "CustomerListViewModel.kt", m640l = {}, m641m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.customer.view.list.CustomerListViewModelgetCustomerHasDailyOperation11 */
    static final class C26661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int customerRef;
        final RefObjectRef<Observable<Resource<Boolean>>> result;
        int label;
        final CustomerListViewModel this0;

        
        C26661(final RefObjectRef<Observable<Resource<Boolean>>> refObjectRef, final CustomerListViewModel customerListViewModel, final int i2, final Continuation<? super C26661> continuation) {
            super(2, continuation);
            result = refObjectRef;
            this0 = customerListViewModel;
            customerRef = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
            return new C26661(result, this0, customerRef, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26661) this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(final Object obj) {
            final ICustomerListRepository iCustomerListRepository;
            final String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 != this.label) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final RefObjectRef<Observable<Resource<Boolean>>> refObjectRef = result;
            iCustomerListRepository = this0.repository;
            refObjectRef.element = iCustomerListRepository.getCustomerHasDailyOperation(customerRef);
            str = this0.TAG;
            return boxing.boxInt(Log.i(str, "getCustomerHasDailyOperation"));
        }
    }
}
