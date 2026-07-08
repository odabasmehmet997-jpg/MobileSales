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

public final class ProductListViewModelgetSaveObjectForSelectedProducts1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.IntRef result;
    final Object saveObject;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetSaveObjectForSelectedProducts1(Ref.IntRef refIntRef, ProductListViewModel productListViewModel, Object obj, Continuation<? super ProductListViewModelgetSaveObjectForSelectedProducts1> continuation) {
        super(2, continuation);
        this.result = refIntRef;
        this.this0 = productListViewModel;
        this.saveObject = obj;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetSaveObjectForSelectedProducts1(this.result, this.this0, this.saveObject, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public static final class C30761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;
        public _FileSystemKtcommonListRecursively1 create(Object obj3, Continuation<?> continuation) {
            return new C30761(refIntRef, productListViewModel, obj2, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C30761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    refIntRef.element = productListViewModel.repository.getSaveObject(obj2);
                    i = Log.i(productListViewModel.TAG, "getSaveObjectForSelectedProducts");
                } catch (Exception e) {
                    i = Log.e(productListViewModel.TAG, "getSaveObjectForSelectedProducts", e);
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
            final Ref.IntRef refIntRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final Object obj2 = this.saveObject;
            C30761 r1 = new C30761();
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
