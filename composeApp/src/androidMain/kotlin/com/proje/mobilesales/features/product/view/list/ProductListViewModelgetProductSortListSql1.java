package com.proje.mobilesales.features.product.view.list;

import android.content.Context;
import com.proje.mobilesales.features.product.model.ProductListParameter;
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

public final class ProductListViewModelgetProductSortListSql1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Context context;
    final int defOrderId;
    final ProductListParameter productListParameter;
    final Ref.ObjectRef<String> result;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetProductSortListSql1(Ref.ObjectRef<String> refObjectRef, ProductListViewModel productListViewModel, Context context, ProductListParameter productListParameter, int i, Continuation<? super ProductListViewModelgetProductSortListSql1> continuation) {
        super(2, continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.context = context;
        this.productListParameter = productListParameter;
        this.defOrderId = i;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetProductSortListSql1(this.result, this.this0, this.context, this.productListParameter, this.defOrderId, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public static final class C30731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30731(refObjectRef, productListViewModel, context, productListParameter, i2, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(Object r6) {

            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListViewModelgetProductSortListSql1.C30731.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final Ref.ObjectRef<String> refObjectRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final Context context = this.context;
            final ProductListParameter productListParameter = this.productListParameter;
            final int i2 = this.defOrderId;
            C30731 r1 = new C30731();
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
