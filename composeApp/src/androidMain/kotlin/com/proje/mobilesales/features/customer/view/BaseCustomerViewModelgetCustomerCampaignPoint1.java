package com.proje.mobilesales.features.customer.view;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.base.BaseViewModelcheckCustomerRiskControl1;
import com.proje.mobilesales.features.customer.repository.IBaseCustomerRepository;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class BaseCustomerViewModelgetCustomerCampaignPoint1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int customerRef;
    int label;
    final BaseCustomerViewModel this0;
    BaseCustomerViewModelgetCustomerCampaignPoint1(final BaseCustomerViewModel baseCustomerViewModel, final int i2, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = baseCustomerViewModel;
        customerRef = i2;
    } 
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new BaseViewModelcheckCustomerRiskControl1(this0, customerRef, continuation);
    }

    public Object invokeSuspend(Object obj) {
        final String str;
        int e2;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        try {
            if (0 == i2) {
                ResultKt.throwOnFailure(obj);
                final CoroutineDispatcher io2 = Dispatchers.getIO();
                final C26551 c26551 = new C26551(this0, customerRef, null);
                label = 1;
                obj = BuildersKt.withContext(io2, c26551, this);
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
            e2 = Log.e(str, "GetCustomerCampaignPoint", e3);
        }
        return boxing.boxInt(e2);
    }

    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }

    public static final class C26551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int customerRef;
        int label;
        final BaseCustomerViewModel this0;

        C26551(final BaseCustomerViewModel baseCustomerViewModel, final int i2, final Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this0 = baseCustomerViewModel;
            customerRef = i2;
        }


        public Object invokeSuspend(final Object obj) {
            final IBaseCustomerRepository iBaseCustomerRepository;
            final String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 == label) {
                ResultKt.throwOnFailure(obj);
                iBaseCustomerRepository = this0.repository;
                iBaseCustomerRepository.getCustomerCampaignPoint(customerRef);
                str = this0.TAG;
                return boxing.boxInt(Log.i(str, "GetCustomerCampaignPoint"));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        public Object invoke(final Object p1, final CoroutineContext.Element p2) {
            return null;
        }

        @Override
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
}
