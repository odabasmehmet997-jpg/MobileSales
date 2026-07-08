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

public final class CustomerInfoViewModelgetCurrCode1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int currType;
    final Ref.ObjectRef<String> result;
    final CustomerInfoViewModel this0;
    int label;

    public CustomerInfoViewModelgetCurrCode1(CustomerInfoViewModel customerInfoViewModel, final Ref.ObjectRef<String> refObjectRef, final int i, final Continuation<?> continuation) {
        super (2, (Continuation<Object>) continuation);
        this0 = customerInfoViewModel;
        result = refObjectRef;
        currType = i;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new CustomerInfoViewModelgetCurrCode1 (this0, result, currType, continuation);
    }

    public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return this.create(coroutineScope, continuation).invokeSuspend (Unit.INSTANCE);
    }

    public Object invokeSuspend(Object obj) {
        int i;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED ();
        final int i2 = label;
        try {
            if (0 == i2) {
                ResultKt.throwOnFailure (obj);
                final CoroutineDispatcher io = Dispatchers.getIO ();
                Ref.ObjectRef<String> refObjectRef = this.result;
                final CustomerInfoViewModel customerInfoViewModel = this.this0;
                final int i3 = this.currType;
                C29321 r1 = new C29321 ();
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
            i = Log.e (this.this0.TAG, "GetCurrCode", e);
        }
        return boxing.boxInt (i);
    }

    public static final class C29321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public C29321(int arity, Continuation<Object> completion) {
            super (arity, completion);
        }

        public C29321() {
        }

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            C29321 c29321 = new C29321 (refObjectRef, customerInfoViewModel, i3, continuation);
            return c29321;
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend (Unit.INSTANCE);
        }

        public Object invokeSuspend(Object r3) {
            throw new UnsupportedOperationException ("com.proje.mobilesales.features.customer.view.info.CustomerInfoViewModelgetCurrCode1.C29321.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
