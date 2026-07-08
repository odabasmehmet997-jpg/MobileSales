package com.proje.mobilesales.features.customer.view.newadd;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.CustomerNewRepository;
import com.proje.mobilesales.features.dbmodel.User;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerNewViewModelgetUserInformation1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.ObjectRef<User> result;
    int label;
    final CustomerNewViewModel this0;

    CustomerNewViewModelgetUserInformation1(final Ref.ObjectRef<User> refObjectRef, final CustomerNewViewModel customerNewViewModel, final Continuation<? super CustomerNewViewModelgetUserInformation1> continuation) {
        super(2, (Continuation<Object>) continuation);
        result = refObjectRef;
        this0 = customerNewViewModel;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new CustomerNewViewModelgetUserInformation1(result, this0, continuation);
    }

    public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return this.create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    static final class C26711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<User> result;
        int label;
        final CustomerNewViewModel this0;

        C26711(final Ref.ObjectRef<User> refObjectRef, final CustomerNewViewModel customerNewViewModel, final Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            result = refObjectRef;
            this0 = customerNewViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
            return new C26711(result, this0, continuation);
        }

        public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26711) this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(final Object obj) {
            final String str;
            int e2;
            final CustomerNewRepository customerNewRepository;
            final String str2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 != this.label) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                final Ref.ObjectRef<User> refObjectRef = result;
                customerNewRepository = this0.repository;
                refObjectRef.element = customerNewRepository.getUser();
                str2 = this0.TAG;
                e2 = Log.i(str2, "GetUserInformation");
            } catch (final Exception e3) {
                str = this0.TAG;
                e2 = Log.e(str, "GetUserInformation", e3);
            }
            return boxing.boxInt(e2);
        }
    }
    public Object invokeSuspend(Object obj) {
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        if (0 == i2) {
            ResultKt.throwOnFailure(obj);
            final CoroutineDispatcher io2 = Dispatchers.getIO();
            final C26711 c26711 = new C26711(result, this0, null);
            label = 1;
            obj = BuildersKt.withContext(io2, c26711, this);
        } else {
            if (1 != i2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
