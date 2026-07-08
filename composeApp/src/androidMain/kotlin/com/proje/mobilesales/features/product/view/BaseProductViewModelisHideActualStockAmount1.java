package com.proje.mobilesales.features.product.view;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.base.BaseRepository;
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
import org.jspecify.annotations.Nullable;


public final class BaseProductViewModelisHideActualStockAmount1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final Ref.BooleanRef result;
    int label;
    final  BaseProductViewModel this0;
 
    public BaseProductViewModelisHideActualStockAmount1(final Ref.BooleanRef refBooleanRef, final java.com.proje.mobilesales.features.product.view.BaseProductViewModel baseProductViewModel, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        result = refBooleanRef;
        this0 = baseProductViewModel;
    } 
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new BaseProductViewModelisHideActualStockAmount1(result, this0, continuation);
    }

     public Unit invoke(final CoroutineScope coroutineScope, final Continuation<? super Object> continuation) {
        return invoke (coroutineScope, (Continuation<Object>) continuation);
    }

    public @Nullable Object invoke(final CoroutineScope coroutineScope, final Continuation<Object> continuation) {
        return this.create (coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }

    public static final class C03691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(final Object obj2, final Continuation<?> continuation) {
            return new C03691(refBooleanRef, baseProductViewModel, continuation);
        }
         public Unit invoke(final CoroutineScope coroutineScope, final Continuation<? super Object> continuation) {
            return invoke (coroutineScope, (Continuation<Object>) continuation);
        }

        public @Nullable Object invoke(final CoroutineScope coroutineScope, final Continuation<Object> continuation) {
            return this.create (coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(final Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 == this.label) {
                ResultKt.throwOnFailure(obj);
                BaseRepository baseProductViewModel = null;
                try {
                    refBooleanRef.element = baseProductViewModel.getBaseErp().isHideActualStockAmount();
                    return Unit.INSTANCE;
                } catch (final Exception e) {
                    return boxing.boxInt(Log.e(baseProductViewModel.TAG, "IsHideActualStockAmount", e));
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
    public Object invokeSuspend(Object obj) {
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i = label;
        if (0 == i) {
            ResultKt.throwOnFailure(obj);
            final CoroutineDispatcher io = Dispatchers.getIO();
            Ref.BooleanRef refBooleanRef = result;
            BaseProductViewModel baseProductViewModel = this0;
            final C03691 r1 = new C03691();
            label = 1;
            obj = BuildersKt.withContext(io, r1, this);
        } else if (1 == i) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
