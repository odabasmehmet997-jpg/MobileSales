package com.proje.mobilesales.features.product.view.list;

import com.proje.mobilesales.features.product.model.ItemUnit;
import java.util.ArrayList;

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

public final class ProductListViewModelgetProductUnitsFromSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int itemRef;
    final Ref.ObjectRef<ArrayList<ItemUnit>> result;
    final boolean service;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetProductUnitsFromSqlHelper1(Ref.ObjectRef<ArrayList<ItemUnit>> refObjectRef, ProductListViewModel productListViewModel, int i, boolean z, Continuation<? super ProductListViewModelgetProductUnitsFromSqlHelper1> continuation) {
        super(2, continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.itemRef = i;
        this.service = z;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetProductUnitsFromSqlHelper1(this.result, this.this0, this.itemRef, this.service, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public static final class C30741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30741(refObjectRef, productListViewModel, i2, z, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(Object r5) {

            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListViewModelgetProductUnitsFromSqlHelper1.C30741.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final Ref.ObjectRef<ArrayList<ItemUnit>> refObjectRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final int i2 = this.itemRef;
            final boolean z = this.service;
            C30741 r1 = new C30741(null);
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
