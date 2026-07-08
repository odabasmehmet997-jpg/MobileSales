package com.proje.mobilesales.features.customer.view;

import android.database.Cursor;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.repository.IBaseCustomerRepository;
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

final class BaseCustomerViewModelgetRowQueryInReadableDatabase1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final  Ref.ObjectRef<Cursor> result;
    final  String[] selectionArgs;
    final  String sql;
    int label;
    final  BaseCustomerViewModel this0;

    BaseCustomerViewModelgetRowQueryInReadableDatabase1(final BaseCustomerViewModel baseCustomerViewModel, final Ref.ObjectRef<Cursor> refObjectRef, final String str, final String[] strArr, final Continuation<? super BaseCustomerViewModelgetRowQueryInReadableDatabase1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = baseCustomerViewModel;
        result = refObjectRef;
        sql = str;
        selectionArgs = strArr;
    }

    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new BaseCustomerViewModelgetRowQueryInReadableDatabase1(this0, result, sql, selectionArgs, continuation);
    }
    public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return this.create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public Object invokeSuspend(Object obj) {
        final String str;
        int e2;
        final Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        final int i2 = label;
        try {
            if (0 == i2) {
                ResultKt.throwOnFailure(obj);
                final CoroutineDispatcher io2 = Dispatchers.getIO();
                final C26571 c26571 = new C26571(result, sql, this0, selectionArgs, null);
                label = 1;
                obj = BuildersKt.withContext(io2, c26571, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (1 != i2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            e2 = ((Number) obj).intValue();
        } catch (final Exception e3) {
            str = this0.TAG;
            e2 = Log.e(str, "GetRowQueryInReadableDatabase", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C26571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Ref.ObjectRef<Cursor> result;
        final  String[] selectionArgs;
        final  String sql;
        int label;
        final  BaseCustomerViewModel this0;
        C26571(final Ref.ObjectRef<Cursor> refObjectRef, final String str, final BaseCustomerViewModel baseCustomerViewModel, final String[] strArr, final Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            result = refObjectRef;
            sql = str;
            this0 = baseCustomerViewModel;
            selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
            return new C26571(result, sql, this0, selectionArgs, continuation);
        }
        public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26571) this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(final Object obj) {
            final T t;
            final String str;
            final IBaseCustomerRepository iBaseCustomerRepository;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 != this.label) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final Ref.ObjectRef<Cursor> refObjectRef = result;
            final String str2 = sql;
            if (null != str2) {
                final BaseCustomerViewModel baseCustomerViewModel = this0;
                final String[] strArr = selectionArgs;
                iBaseCustomerRepository = baseCustomerViewModel.repository;
                t = (T) iBaseCustomerRepository.getLogoSqlHelper().getReadableDatabase().rawQuery(str2, strArr);
            } else {
                t = 0;
            }
            refObjectRef.element = (Cursor) t;
            str = this0.TAG;
            return boxing.boxInt(Log.i(str, "GetRowQueryInReadableDatabase"));
        }
    }

    private static class T {
    }
}
