package com.proje.mobilesales.features.product.view.list;

import android.content.Context;
import android.util.Log;
import com.proje.mobilesales.core.enums.FicheType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class ProductListViewModelgetPrintTransportDispatchNote1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Context context;
    final FicheType ficheType;
    final int itemId;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetPrintTransportDispatchNote1(ProductListViewModel productListViewModel, Context context, FicheType ficheType, int i, Continuation<? super ProductListViewModelgetPrintTransportDispatchNote1> continuation) {
        super(2, continuation);
        this.this0 = productListViewModel;
        this.context = context;
        this.ficheType = ficheType;
        this.itemId = i;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetPrintTransportDispatchNote1(this.this0, this.context, this.ficheType, this.itemId, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public static final class C30701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;
        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30701(productListViewModel, context, ficheType, i2, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C30701) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    productListViewModel.repository.printTransportDispatchNote(context, ficheType, i2);
                    i = Log.i(productListViewModel.TAG, "GetPrintTransportDispatchNote");
                } catch (Exception e) {
                    i = Log.e(productListViewModel.TAG, "GetPrintTransportDispatchNote", e);
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
            final ProductListViewModel productListViewModel = this.this0;
            final Context context = this.context;
            final FicheType ficheType = this.ficheType;
            final int i2 = this.itemId;
            C30701 r1 = new C30701();
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
