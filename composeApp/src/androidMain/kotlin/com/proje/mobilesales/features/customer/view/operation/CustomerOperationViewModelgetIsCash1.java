package com.proje.mobilesales.features.customer.view.operation;

import android.util.Log;
import com.proje.mobilesales.features.customer.repository.ICustomerOperationRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class CustomerOperationViewModelgetIsCash1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final   Ref.BooleanRef result;
    int label;
    final  CustomerOperationViewModel this0;

    CustomerOperationViewModelgetIsCash1(CustomerOperationViewModel customerOperationViewModel, Ref.BooleanRef refBooleanRef, Continuation<? super CustomerOperationViewModelgetIsCash1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerOperationViewModel;
        this.result = refBooleanRef;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetIsCash1(this.this0, this.result, continuation);
    }
    public Object invoke(Object coroutineScope, ? super CoroutineContext.Element continuation) {
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
                C26751 c26751 = new C26751(result, this.this0, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26751, this);
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
            e2 = Log.e(str, "GetIsCash", e3);
        }
        return boxing.boxInt(e2);
    }

    public Object invoke(Object p1, CoroutineContext.Element p2) {
        return null;
    }

    static final class C26751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final    Ref.BooleanRef result;
        int label;
        final   CustomerOperationViewModel this0;
        C26751(Ref.BooleanRef refBooleanRef, CustomerOperationViewModel customerOperationViewModel, Continuation<? super C26751> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = customerOperationViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26751(this.result, this.this0, continuation);
        }
        public Object invoke(Object coroutineScope, ? super CoroutineContext.Element continuation) {
            return ((C26751) create(coroutineScope, (Continuation<?>) continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            ICustomerOperationRepository iCustomerOperationRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef refBooleanRef = this.result;
            iCustomerOperationRepository = this.this0.repository;
            refBooleanRef.element = iCustomerOperationRepository.isCashUserMenu();
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetIsCash"));
        }

         public Object invoke(Object p1, CoroutineContext.Element p2) {
             return null;
         }
     }
}
