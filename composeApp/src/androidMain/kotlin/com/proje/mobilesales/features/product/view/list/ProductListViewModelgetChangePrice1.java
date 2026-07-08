package com.proje.mobilesales.features.product.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
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

public abstract   class ProductListViewModelgetChangePrice1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    static Ref.BooleanRef result = null;
    int label;
    static ProductListViewModel this0 = null;
    public ProductListViewModelgetChangePrice1(Ref.BooleanRef refBooleanRef, ProductListViewModel productListViewModel, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        result = refBooleanRef;
        this0 = productListViewModel;
    }
    public final _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new ProductListViewModelgetChangePrice1(result, this0, continuation) {

            public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                return null;
            }
        };
    }
    public static final class C30631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ( create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    Ref.BooleanRef  refBooleanRef = null;
                    refBooleanRef.element = ProductListViewModel.accessgetRepositoryp(this0).getChangePrice();
                    i = Log.i(ProductListViewModel.accessgetTAG(this0), "GetChangePrice");
                } catch (Exception e) {
                    i = Log.e(ProductListViewModel.accessgetTAG(this0), "GetChangePrice", e);
                }
                return boxing.boxInt(i);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        @Override
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
    @Override
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io = Dispatchers.getIO();
            final Ref.BooleanRef refBooleanRef = result;
            final ProductListViewModel productListViewModel = this0;
            C30631 r1 = new C30631();
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
