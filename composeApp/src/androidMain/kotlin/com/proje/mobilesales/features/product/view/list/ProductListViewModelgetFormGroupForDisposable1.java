package com.proje.mobilesales.features.product.view.list;

import android.util.Log;
import com.proje.mobilesales.features.model.FormGroupModel;
import com.proje.mobilesales.features.model.Resource;
import java.util.List;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import io.reactivex.Observable;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class ProductListViewModelgetFormGroupForDisposable1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.ObjectRef<Observable<Resource<List<FormGroupModel>>>> result;
    final int workType;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetFormGroupForDisposable1(Ref.ObjectRef<Observable<Resource<List<FormGroupModel>>>> refObjectRef, ProductListViewModel productListViewModel, int i, Continuation<? super ProductListViewModelgetFormGroupForDisposable1> continuation) {
        super(2, continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.workType = i;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetFormGroupForDisposable1(this.result, this.this0, this.workType, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public static final class C30661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30661(refObjectRef, productListViewModel, i2, continuation);
        }

        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C30661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    refObjectRef.element = productListViewModel.repository.getFormGroups(i2);
                    i = Log.i(productListViewModel.TAG, "GetFormGroupForDisposable");
                } catch (Exception e) {
                    i = Log.e(productListViewModel.TAG, "GetFormGroupForDisposable", e);
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
            final Ref.ObjectRef<Observable<Resource<List<FormGroupModel>>>> refObjectRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final int i2 = this.workType;
            C30661 r1 = new C30661(null);
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
