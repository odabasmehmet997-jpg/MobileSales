package com.proje.mobilesales.features.sales.view.detail;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesDetailViewModelgetTableForItemPriceFromSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final RefObjectRef<List<ItemPrice>> result;
    final String selection;
    final String[] selectionArgs;
    final Class<ItemPrice> tableClass;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetTableForItemPriceFromSqlHelper1(SalesDetailViewModel salesDetailViewModel, RefObjectRef<List<ItemPrice>> refObjectRef, Class<ItemPrice> cls, String str, String[] strArr, Continuation<? super SalesDetailViewModelgetTableForItemPriceFromSqlHelper1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
        this.result = refObjectRef;
        this.tableClass = cls;
        this.selection = str;
        this.selectionArgs = strArr;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetTableForItemPriceFromSqlHelper1(this.this0, this.result, this.tableClass, this.selection, this.selectionArgs, (Continuation<? super SalesDetailViewModelgetTableForItemPriceFromSqlHelper1>) continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public  Object invokeSuspend(Object obj) {
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C28511 c28511 = new C28511(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28511, this);
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
    static final class C28511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final RefObjectRef<List<ItemPrice>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<ItemPrice> tableClass;
        int label;
        final SalesDetailViewModel this0;

        
        C28511(RefObjectRef<List<ItemPrice>> refObjectRef, SalesDetailViewModel salesDetailViewModel, Class<ItemPrice> cls, String str, String[] strArr, Continuation<? super C28511> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesDetailViewModel;
            this.tableClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28511(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, (Continuation<? super C28511>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            RefObjectRef<List<ItemPrice>> refObjectRef = this.result;
            ISqlHelper<ItemPrice> iSqlHelperItemPrice = this.this0.getRepository().getISqlHelperItemPrice();
            refObjectRef.element = iSqlHelperItemPrice != null ? iSqlHelperItemPrice.getTable(this.tableClass, this.selection, this.selectionArgs) : null;
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getTableForItemPriceFromSqlHelper"));
        }
    }
}
