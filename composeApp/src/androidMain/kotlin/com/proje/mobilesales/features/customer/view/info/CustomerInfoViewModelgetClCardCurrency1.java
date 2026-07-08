package com.proje.mobilesales.features.customer.view.info;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class CustomerInfoViewModelgetClCardCurrency1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int currType;
    final Ref.IntRef result;
    final CustomerInfoViewModel this0;
    int label;

    public CustomerInfoViewModelgetClCardCurrency1(CustomerInfoViewModel customerInfoViewModel, Ref.IntRef refIntRef, int i, Continuation<?> continuation) {
        super (2, (Continuation<Object>) continuation);
        this.this0 = customerInfoViewModel;
        this.result = refIntRef;
        this.currType = i;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerInfoViewModelgetClCardCurrency1 (this.this0, this.result, this.currType, continuation);
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
                final Ref.IntRef refIntRef = this.result;
                final CustomerInfoViewModel customerInfoViewModel = this.this0;
                final int i3 = this.currType;
                C29311 r1 = new C29311 ();
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
            i = Log.e (this.this0.TAG, "getClCardCurrency", e);
        }
        return boxing.boxInt (i);
    }

    public static final class C29311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C29311 (refIntRef, customerInfoViewModel, i3, continuation);
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend (Unit.INSTANCE);
        }

        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED ();
            if (0 == label) {
                ResultKt.throwOnFailure (obj);
                refIntRef.element = customerInfoViewModel.repository.getLogoSqlHelper ().getClCardCurrency (i3);
                return boxing.boxInt (Log.i (customerInfoViewModel.TAG, "getClCardCurrency"));
            }
            throw new IllegalStateException ("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
