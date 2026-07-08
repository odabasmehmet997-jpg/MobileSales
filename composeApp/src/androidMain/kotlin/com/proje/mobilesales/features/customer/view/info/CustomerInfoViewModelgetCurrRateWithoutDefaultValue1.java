package com.proje.mobilesales.features.customer.view.info;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.RefDoubleRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class CustomerInfoViewModelgetCurrRateWithoutDefaultValue1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int currType;
    final RefDoubleRef result;
    final CustomerInfoViewModel this0;
    int label;

    public CustomerInfoViewModelgetCurrRateWithoutDefaultValue1(CustomerInfoViewModel customerInfoViewModel, Ref.DoubleRef refDoubleRef, int i, Continuation<? super CustomerInfoViewModelgetCurrRateWithoutDefaultValue1> continuation) {
        super (2, continuation);
        this.this0 = customerInfoViewModel;
        this.result = refDoubleRef;
        this.currType = i;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerInfoViewModelgetCurrRateWithoutDefaultValue1 (this.this0, this.result, this.currType, continuation);
    }

    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend (Unit.INSTANCE);
    }

    public Object invokeSuspend(Object obj) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED ();
        int i2 = this.label;
        try {
            if (0 == i2) {
                ResultKt.throwOnFailure (obj);
                CoroutineDispatcher io = Dispatchers.getIO ();
                final RefDoubleRef refDoubleRef = this.result;
                final CustomerInfoViewModel customerInfoViewModel = this.this0;
                final int i3 = this.currType;
                C29331 r1 = new C29331 (null);
                this.label = 1;
                obj = BuildersKt.withContext (io, r1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (1 == i2) {
                ResultKt.throwOnFailure (obj);
            } else {
                throw new IllegalStateException ("call to 'resume' before 'invoke' with coroutine");
            }
            i = ((Number) obj).intValue ();
        } catch (Exception e) {
            i = Log.e (this.this0.TAG, "GetCurrRateWithoutDefaultValue", e);
        }
        return boxing.boxInt (i);
    }

    public static final class C29331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C29331 (refDoubleRef, customerInfoViewModel, i3, continuation);
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C29331) create(coroutineScope, continuation)).invokeSuspend (Unit.INSTANCE);
        }

        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED ();
            if (0 == label) {
                ResultKt.throwOnFailure (obj);
                refDoubleRef.element = customerInfoViewModel.repository.getLogoSqlHelper ().getCurrRateWithoutDefaultValue (i3);
                return boxing.boxInt (Log.i (customerInfoViewModel.TAG, "GetCurrRateWithoutDefaultValue"));
            }
            throw new IllegalStateException ("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
