package com.proje.mobilesales.features.product.view.list;

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

public final class ProductListViewModelgetServiceListSql1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final ProductListViewModel.ProductSqlDataModel productSqlDataModel;
    final Ref.ObjectRef<String> result;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetServiceListSql1(Ref.ObjectRef<String> refObjectRef, ProductListViewModel productListViewModel, ProductListViewModel.ProductSqlDataModel productSqlDataModel, Continuation<? super ProductListViewModelgetServiceListSql1> continuation) {
        super(2, continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.productSqlDataModel = productSqlDataModel;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetServiceListSql1(this.result, this.this0, this.productSqlDataModel, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public static final class C30791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30791(refObjectRef, productListViewModel, productSqlDataModel, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(Object r15) {

            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListViewModelgetServiceListSql1.C30791.invokeSuspend(java.lang.Object):java.lang.Object");
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
            final ProductListViewModel.ProductSqlDataModel productSqlDataModel = this.productSqlDataModel;
            C30791 r1 = new C30791();
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
