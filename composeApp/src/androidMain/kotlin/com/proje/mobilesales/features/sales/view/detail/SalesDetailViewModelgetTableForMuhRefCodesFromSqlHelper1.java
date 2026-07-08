package com.proje.mobilesales.features.sales.view.detail;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.dbmodel.MuhRefCodes;
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

final class SalesDetailViewModelgetTableForMuhRefCodesFromSqlHelper1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final RefObjectRef<List<MuhRefCodes>> result;
    final String selection;
    final String[] selectionArgs;
    final Class<MuhRefCodes> tableClass;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetTableForMuhRefCodesFromSqlHelper1(SalesDetailViewModel salesDetailViewModel, RefObjectRef<List<MuhRefCodes>> refObjectRef, Class<MuhRefCodes> cls, String str, String[] strArr, Continuation<? super SalesDetailViewModelgetTableForMuhRefCodesFromSqlHelper1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
        this.result = refObjectRef;
        this.tableClass = cls;
        this.selection = str;
        this.selectionArgs = strArr;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetTableForMuhRefCodesFromSqlHelper1(this.this0, this.result, this.tableClass, this.selection, this.selectionArgs, (Continuation<? super SalesDetailViewModelgetTableForMuhRefCodesFromSqlHelper1>) continuation);
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
                C28521 c28521 = new C28521(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28521, this);
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
            e2 = Log.e(this.this0.getTAG(), "getTableForMuhRefCodesFromSqlHelper", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final RefObjectRef<List<MuhRefCodes>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<MuhRefCodes> tableClass;
        int label;
        final SalesDetailViewModel this0;

        
        C28521(RefObjectRef<List<MuhRefCodes>> refObjectRef, SalesDetailViewModel salesDetailViewModel, Class<MuhRefCodes> cls, String str, String[] strArr, Continuation<? super C28521> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesDetailViewModel;
            this.tableClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28521(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, (Continuation<? super C28521>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getISqlHelperMuhRefCodes().getTable(this.tableClass, this.selection, this.selectionArgs);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getTableForMuhRefCodesFromSqlHelper"));
        }
    }
}
