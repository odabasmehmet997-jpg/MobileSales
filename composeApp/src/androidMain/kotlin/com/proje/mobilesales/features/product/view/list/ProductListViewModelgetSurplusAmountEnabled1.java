package com.proje.mobilesales.features.product.view.list;

import android.util.Log;
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

public final class ProductListViewModelgetSurplusAmountEnabled1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.BooleanRef result;
    int label;
    final ProductListViewModel this0;
 
    public ProductListViewModelgetSurplusAmountEnabled1(Ref.BooleanRef refBooleanRef, ProductListViewModel productListViewModel, Continuation<? super ProductListViewModelgetSurplusAmountEnabled1> continuation) {
        super(2, continuation);
        this.result = refBooleanRef;
        this.this0 = productListViewModel;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetSurplusAmountEnabled1(this.result, this.this0, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    } 
    public static final class C30811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public C30811(Object o) {
        }

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30811(refBooleanRef, productListViewModel, continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C30811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        } 
        public Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    refBooleanRef.element = productListViewModel.repository.surplusAmountFromLogoParamValue();
                    i = Log.i(productListViewModel.TAG, "GetSurplusAmountEnabled");
                } catch (Exception e) {
                    i = Log.e(productListViewModel.TAG, "GetSurplusAmountEnabled", e);
                }
                return boxing.boxInt(i);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
 
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final Ref.BooleanRef refBooleanRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            C30811 r1 = new C30811(null);
            this.label = 1;
            obj = BuildersKt.withContext(io, r1, this);
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
