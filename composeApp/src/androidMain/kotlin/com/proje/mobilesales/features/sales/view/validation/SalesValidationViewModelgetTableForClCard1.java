package com.proje.mobilesales.features.sales.view.validation;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.customer.model.database.ClCard;

import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.coroutines.jvm.internal.boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesValidationViewModelgetTableForClCard1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.ObjectRef<List<ClCard>> result;
    final String selection;
    final String[] selectionArgs;
    final Class<ClCard> tableClass;
    int label;
    final SalesValidationViewModel this0;
 
    SalesValidationViewModelgetTableForClCard1(SalesValidationViewModel salesValidationViewModel, Ref.ObjectRef<List<ClCard>> refObjectRef, Class<ClCard> cls, String str, String[] strArr, Continuation<? super SalesValidationViewModelgetTableForClCard1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesValidationViewModel;
        this.result = refObjectRef;
        this.tableClass = cls;
        this.selection = str;
        this.selectionArgs = strArr;
    }
 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesValidationViewModelgetTableForClCard1(this.this0, this.result, this.tableClass, this.selection, this.selectionArgs, (Continuation<? super SalesValidationViewModelgetTableForClCard1>) continuation);
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
                C29171 c29171 = new C29171(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c29171, this);
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
            e2 = Log.e(this.this0.getTAG(), "GetTableForClCard", e3);
        }
        return boxing.boxInt(e2);
    }
 
    static final class C29171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<List<ClCard>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<ClCard> tableClass;
        int label;
        final SalesValidationViewModel this0; 
        C29171(Ref.ObjectRef<List<ClCard>> refObjectRef, SalesValidationViewModel salesValidationViewModel, Class<ClCard> cls, String str, String[] strArr, Continuation<? super C29171> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesValidationViewModel;
            this.tableClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C29171(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, (Continuation<? super C29171>) continuation);
        }
 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C29171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
  
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef<List<ClCard>> refObjectRef = this.result;
            ISqlHelper<ClCard> iSqlHelperClCard = this.this0.getRepository().getISqlHelperClCard();
             List<Object> table = singletonList(iSqlHelperClCard != null ? iSqlHelperClCard.getTable(this.tableClass) : (List<ClCard>) 0);
            Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.database.ClCard>");
            refObjectRef.element = singletonList(table);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "GetTableForClCard"));
        }

        private List<ClCard> singletonList(List<Object> table) {
            return table;
        }
    }
}
