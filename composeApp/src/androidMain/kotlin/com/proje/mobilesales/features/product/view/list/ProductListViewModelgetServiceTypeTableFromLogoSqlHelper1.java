package com.proje.mobilesales.features.product.view.list;

import com.proje.mobilesales.features.dbmodel.Service;
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

public final class ProductListViewModelgetServiceTypeTableFromLogoSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Class<Service> aClass;
    final Ref.ObjectRef<List<Service>> result;
    final String selection;
    final String[] selectionArgs;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetServiceTypeTableFromLogoSqlHelper1(Ref.ObjectRef<List<Service>> refObjectRef, ProductListViewModel productListViewModel, Class<Service> cls, String str, String[] strArr, Continuation<? super ProductListViewModelgetServiceTypeTableFromLogoSqlHelper1> continuation) {
        super(2, continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.aClass = cls;
        this.selection = str;
        this.selectionArgs = strArr;
    }

    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetServiceTypeTableFromLogoSqlHelper1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public static final class C30801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30801(refObjectRef, productListViewModel, cls, str, strArr, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object r6) {

            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListViewModelgetServiceTypeTableFromLogoSqlHelper1.C30801.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final Ref.ObjectRef<List<Service>> refObjectRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final Class<Service> cls = this.aClass;
            final String str = this.selection;
            final String[] strArr = this.selectionArgs;
            C30801 r1 = new C30801(null);
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
