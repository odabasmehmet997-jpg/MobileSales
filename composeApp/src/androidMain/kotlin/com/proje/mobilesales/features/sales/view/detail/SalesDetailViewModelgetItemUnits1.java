package com.proje.mobilesales.features.sales.view.detail;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import java.util.List;

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

final class SalesDetailViewModelgetItemUnits1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int itemRef;
    final Ref.ObjectRef<List<ItemUnits>> result;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetItemUnits1(SalesDetailViewModel salesDetailViewModel, Ref.ObjectRef<List<ItemUnits>> refObjectRef, int i2, Continuation<? super SalesDetailViewModelgetItemUnits1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
        this.result = refObjectRef;
        this.itemRef = i2;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetItemUnits1(this.this0, this.result, this.itemRef, (Continuation<? super SalesDetailViewModelgetItemUnits1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28441 c28441 = new C28441(this.result, this.this0, this.itemRef, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28441, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            e2 = ((Number) obj).intValue();
        } catch (Exception e3) {
            e2 = Log.e(this.this0.getTAG(), "getTableForItemPriceFromSqlHelper", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int itemRef;
        final Ref.ObjectRef<List<ItemUnits>> result;
        int label;
        final SalesDetailViewModel this0;
        C28441(Ref.ObjectRef<List<ItemUnits>> refObjectRef, SalesDetailViewModel salesDetailViewModel, int i2, Continuation<? super C28441> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesDetailViewModel;
            this.itemRef = i2;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28441(this.result, this.this0, this.itemRef, (Continuation<? super C28441>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getItemUnits(this.itemRef);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getTableForItemPriceFromSqlHelper"));
        }
    }
}
