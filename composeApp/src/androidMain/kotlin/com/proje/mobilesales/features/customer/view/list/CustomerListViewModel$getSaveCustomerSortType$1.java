package com.proje.mobilesales.features.customer.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.ICustomerListRepository;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;


/* compiled from: CustomerListViewModel.kt */
@DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.list.CustomerListViewModelgetSaveCustomerSortType1", m639f = "CustomerListViewModel.kt", m640l = {58}, m641m = "invokeSuspend")

final class CustomerListViewModelgetSaveCustomerSortType1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int sortType;
    int label;
    final CustomerListViewModel this0;

    
    CustomerListViewModelgetSaveCustomerSortType1(final CustomerListViewModel customerListViewModel, final int i2, final Continuation<? super CustomerListViewModelgetSaveCustomerSortType1> continuation) {
        super(2, continuation);
        this0 = customerListViewModel;
        sortType = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new CustomerListViewModelgetSaveCustomerSortType1(this0, sortType, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return this.create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: CustomerListViewModel.kt */
    @DebugMetadata(m638c = "com.proje.mobilesales.features.customer.view.list.CustomerListViewModelgetSaveCustomerSortType11", m639f = "CustomerListViewModel.kt", m640l = {}, m641m = "invokeSuspend")
    /* renamed from: com.proje.mobilesales.features.customer.view.list.CustomerListViewModelgetSaveCustomerSortType11 */
    static final class C26691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int sortType;
        int label;
        final CustomerListViewModel this0;

        
        C26691(final CustomerListViewModel customerListViewModel, final int i2, final Continuation<? super C26691> continuation) {
            super(2, continuation);
            this0 = customerListViewModel;
            sortType = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
            return new C26691(this0, sortType, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26691) this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public Object invokeSuspend(final Object obj) {
            final String str;
            int e2;
            final ICustomerListRepository iCustomerListRepository;
            final String str2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 == this.label) {
                ResultKt.throwOnFailure(obj);
                try {
                    iCustomerListRepository = this0.repository;
                    iCustomerListRepository.getSaveCustomerSortType(sortType);
                    str2 = this0.TAG;
                    e2 = Log.i(str2, "getSaveCustomerSortType");
                } catch (final Exception e3) {
                    str = this0.TAG;
                    e2 = Log.e(str, "getSaveCustomerSortType", e3);
                }
                return boxing.boxInt(e2);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object obj) {
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        if (0 == i2) {
            ResultKt.throwOnFailure(obj);
            final CoroutineDispatcher io2 = Dispatchers.getIO();
            final C26691 c26691 = new C26691(this0, sortType, null);
            label = 1;
            obj = BuildersKt.withContext(io2, c26691, this);
        } else {
            if (1 != i2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
