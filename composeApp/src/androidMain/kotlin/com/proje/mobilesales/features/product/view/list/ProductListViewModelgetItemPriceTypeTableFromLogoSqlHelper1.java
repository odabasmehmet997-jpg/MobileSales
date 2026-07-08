package com.proje.mobilesales.features.product.view.list;

import com.proje.mobilesales.features.product.model.database.ItemPrice;
import java.util.List;

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

public final class ProductListViewModelgetItemPriceTypeTableFromLogoSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Class<ItemPrice> aClass;
    final Ref.ObjectRef<List<ItemPrice>> result;
    final String selection;
    final String[] selectionArgs;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetItemPriceTypeTableFromLogoSqlHelper1(Ref.ObjectRef<List<ItemPrice>> refObjectRef, ProductListViewModel productListViewModel, Class<ItemPrice> cls, String str, String[] strArr, Continuation<? super ProductListViewModelgetItemPriceTypeTableFromLogoSqlHelper1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.aClass = cls;
        this.selection = str;
        this.selectionArgs = strArr;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetItemPriceTypeTableFromLogoSqlHelper1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public static final class C30691 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30691(refObjectRef, productListViewModel, cls, str, strArr, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object r6) {

            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListViewModelgetItemPriceTypeTableFromLogoSqlHelper1.C30691.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final Ref.ObjectRef<List<ItemPrice>> refObjectRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final Class<ItemPrice> cls = this.aClass;
            final String str = this.selection;
            final String[] strArr = this.selectionArgs;
            C30691 r1 = new C30691(null);
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
