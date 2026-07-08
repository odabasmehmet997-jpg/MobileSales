package com.proje.mobilesales.features.product.view.list;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import kotlin.Result;
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

public abstract class ProductListViewModelgetCustomerConditionCodeFromSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int clRef;
    final Ref.ObjectRef<String> result;
    int label;
    final ProductListViewModel this0;
    public ProductListViewModelgetCustomerConditionCodeFromSqlHelper1(Ref.ObjectRef<String> refObjectRef, ProductListViewModel productListViewModel, int i, Continuation<? super ProductListViewModelgetCustomerConditionCodeFromSqlHelper1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.result = refObjectRef;
        this.this0 = productListViewModel;
        this.clRef = i;
    }
    public final _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetCustomerConditionCodeFromSqlHelper1(this.result, this.this0, this.clRef, continuation) {

            public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                return null;
            }
        };
    }

    public     class C30641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;
        public final _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            return new C30641(refObjectRef, productListViewModel, i2, continuation) {
                @Override
                public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                    return null;
                }
            };
        }
        public final Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public final Object invokeSuspend(Object r4) {

            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.list.ProductListViewModelgetCustomerConditionCodeFromSqlHelper1.C30641.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final Ref.ObjectRef<String> refObjectRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final int i2 = this.clRef;
            C30641 r1 = new C30641();
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
