package com.proje.mobilesales.features.product.view.detail;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.product.model.ProductDetail;
import kotlin.Result;
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

public final class ProductDetailViewModelgetPaymentCodeFromSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    static ProductDetail.ItemPrice itemPrice = null;
    final Ref.ObjectRef<String> result;
    int label;
    final ProductDetailViewModel this0;
 
    public ProductDetailViewModelgetPaymentCodeFromSqlHelper1(ProductDetailViewModel productDetailViewModel, Ref.ObjectRef<String> refObjectRef, ProductDetail.ItemPrice itemPrice, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = productDetailViewModel;
        this.result = refObjectRef;
        ProductDetailViewModelgetPaymentCodeFromSqlHelper1.itemPrice = itemPrice;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductDetailViewModelgetPaymentCodeFromSqlHelper1(this.this0, this.result, itemPrice, continuation);
    }

    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io = Dispatchers.getIO();
                final Ref.ObjectRef<String> refObjectRef = this.result;
                final ProductDetailViewModel productDetailViewModel = this.this0;
                final ProductDetail.ItemPrice itemPrice = ProductDetailViewModelgetPaymentCodeFromSqlHelper1.itemPrice;
                C30611 r1 = new C30611(null);
                this.label = 1;
                obj = BuildersKt.withContext(io, r1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = ((Number) obj).intValue();
        } catch (Exception e) {
            i = Log.e(this.this0.TAG, "GetPaymentCodeFromSqlHelper", e);
        }
        return boxing.boxInt(i);
    }

    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }

    public static final class C30611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public C30611(Object o) {
        }


        public Object invokeSuspend(Object r3) {
            throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.view.detail.ProductDetailViewModelgetPaymentCodeFromSqlHelper1.C30611.invokeSuspend(java.lang.Object):java.lang.Object");
        }
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
}
