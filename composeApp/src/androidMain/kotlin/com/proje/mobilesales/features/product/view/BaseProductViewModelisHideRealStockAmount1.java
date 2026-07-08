package com.proje.mobilesales.features.product.view;

import android.util.Log;
import kotlin.ResultKt;
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
import org.jspecify.annotations.Nullable;

import BaseProductViewModel;

public final class BaseProductViewModelisHideRealStockAmount1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final Ref.BooleanRef result;
    int label;
    final  BaseProductViewModel this0;

    public BaseProductViewModelisHideRealStockAmount1(final Ref.BooleanRef refBooleanRef, final java.com.proje.mobilesales.features.product.view.BaseProductViewModel baseProductViewModel, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        result = refBooleanRef;
        this0 = baseProductViewModel;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new BaseProductViewModelisHideRealStockAmount1(result, this0, continuation);
    }
     public Unit invoke(final CoroutineScope coroutineScope, final Continuation<? super Object> continuation) {
        return invoke (coroutineScope, (Continuation<Object>) continuation);
    }

    public @Nullable Object invoke(final CoroutineScope coroutineScope, final Continuation<Object> continuation) {
        return this.create (coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
     public static final class C03701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(final Object obj2, final Continuation<?> continuation) {
            return new C03701(refBooleanRef, baseProductViewModel, continuation);
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
                try {
                    refBooleanRef.element = baseProductViewModel.getBaseErp().isHideRealStockAmount();
                    return Unit.INSTANCE;
                } catch (final Exception e) {
                    return boxing.boxInt(Log.e(baseProductViewModel.TAG, "IsHideRealStockAmount", e));
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
            final C03701 r1 = new C03701();
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
