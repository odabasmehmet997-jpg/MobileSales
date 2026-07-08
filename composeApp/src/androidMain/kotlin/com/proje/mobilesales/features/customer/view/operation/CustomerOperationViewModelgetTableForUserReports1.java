package com.proje.mobilesales.features.customer.view.operation;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.ICustomerOperationRepository;
import com.proje.mobilesales.features.userreport.model.database.UserReports;
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

final class CustomerOperationViewModelgetTableForUserReports1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final String groupBy;
    final String having;
    final String orderBy;
    final Ref.ObjectRef<List<UserReports>> result;
    final String selection;
    final String[] selectionArgs;
    final Class<UserReports> tableClass;
    int label;
    final CustomerOperationViewModel this0;

    CustomerOperationViewModelgetTableForUserReports1(CustomerOperationViewModel customerOperationViewModel, Ref.ObjectRef refObjectRef, Class<UserReports> cls, String str, String[] strArr, String str2, String str3, String str4, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = customerOperationViewModel;
        this.result = refObjectRef;
        this.tableClass = cls;
        this.selection = str;
        this.selectionArgs = strArr;
        this.groupBy = str2;
        this.having = str3;
        this.orderBy = str4;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new CustomerOperationViewModelgetTableForUserReports1(this.this0, this.result, this.tableClass, this.selection, this.selectionArgs, this.groupBy, this.having, this.orderBy, continuation);
    }
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invokeSuspend(Object obj) {
        String str;
        int e2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io2 = Dispatchers.getIO();
                C26931 c26931 = new C26931(result, this.this0, this.tableClass, this.selection, this.selectionArgs, this.groupBy, this.having, this.orderBy, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c26931, this);
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
            str = this.this0.TAG;
            e2 = Log.e(str, "GetTableForUserReports", e3);
        }
        return boxing.boxInt(e2);
    }

    static final class C26931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final String groupBy;
        final String having;
        final String orderBy;
        final Ref.ObjectRef<List<UserReports>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<UserReports> tableClass;
        int label;
        final CustomerOperationViewModel this0;
        C26931(Ref.ObjectRef<List<UserReports>> refObjectRef, CustomerOperationViewModel customerOperationViewModel, Class<UserReports> cls, String str, String[] strArr, String str2, String str3, String str4, Continuation<? super C26931> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = customerOperationViewModel;
            this.tableClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
            this.groupBy = str2;
            this.having = str3;
            this.orderBy = str4;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C26931(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, this.groupBy, this.having, this.orderBy, continuation);
        }

        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            Object o = ((C26931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            ICustomerOperationRepository iCustomerOperationRepository;
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Ref.ObjectRef<List<UserReports>> refObjectRef = this.result;
            iCustomerOperationRepository = this.this0.repository;
            List<UserReports> table = iCustomerOperationRepository.getISqlHelperUserReports().getTable(this.tableClass, this.selection, this.selectionArgs, this.groupBy, this.having, this.orderBy);
            Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.userreport.model.database.UserReports>");
            refObjectRef.element = table;
            str = this.this0.TAG;
            return boxing.boxInt(Log.i(str, "GetTableForUserReports"));
        }
    }
}
