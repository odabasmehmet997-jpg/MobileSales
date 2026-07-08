package com.proje.mobilesales.features.product.view.list;

import android.util.Log;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.features.product.model.ProductListParameter;
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

public final class ProductListViewModelgetProductListParameter1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.ObjectRef<ProductListParameter> result;
    final SalesType salesType;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetProductListParameter1(Ref.ObjectRef<ProductListParameter> refObjectRef, ProductListViewModel productListViewModel, SalesType salesType, Continuation<? super ProductListViewModelgetProductListParameter1> continuation) {
        super(2, continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.salesType = salesType;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetProductListParameter1(this.result, this.this0, this.salesType, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public static final class C30711 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30711(refObjectRef, productListViewModel, salesType, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C30711) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    refObjectRef.element = productListViewModel.repository.getProductListParameter(salesType);
                    i = Log.i(productListViewModel.TAG, "GetProductListParameter");
                } catch (Exception e) {
                    i = Log.e(productListViewModel.TAG, "GetProductListParameter", e);
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
            final Ref.ObjectRef<ProductListParameter> refObjectRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final SalesType salesType = this.salesType;
            C30711 r1 = new C30711(null);
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
