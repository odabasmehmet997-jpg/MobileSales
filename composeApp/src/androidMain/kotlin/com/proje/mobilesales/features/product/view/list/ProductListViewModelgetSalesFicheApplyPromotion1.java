package com.proje.mobilesales.features.product.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.SalesType;
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

public final class ProductListViewModelgetSalesFicheApplyPromotion1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.BooleanRef result;
    final SalesType salesType;
    int label;
    final ProductListViewModel this0;

    public ProductListViewModelgetSalesFicheApplyPromotion1(Ref.BooleanRef refBooleanRef, ProductListViewModel productListViewModel, SalesType salesType, Continuation<? super ProductListViewModelgetSalesFicheApplyPromotion1> continuation) {
        super(2, continuation);
        this.result = refBooleanRef;
        this.this0 = productListViewModel;
        this.salesType = salesType;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetSalesFicheApplyPromotion1(this.result, this.this0, this.salesType, continuation);
    }
    public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    @Override
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }
    @Override
    public final class C30751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        public _FileSystemKtcommonListRecursively1 create(Object obj2, Continuation<?> continuation) {
            C30751 c30751 = new C30751(refBooleanRef, productListViewModel, salesType, continuation);
            return c30751;
        }
        @Override
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C30751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        @Override
        public Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    refBooleanRef.element = productListViewModel.repository.salesFicheApplyPromotion(salesType);
                    i = Log.i(productListViewModel.TAG, "GetSalesFicheApplyPromotion");
                } catch (Exception e) {
                    i = Log.e(productListViewModel.TAG, "GetSalesFicheApplyPromotion", e);
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
            final Ref.BooleanRef refBooleanRef = this.result;
            final ProductListViewModel productListViewModel = this.this0;
            final SalesType salesType = this.salesType;
            C30751 r1 = new C30751();
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
