package com.proje.mobilesales.features.customer.view;

import android.database.Cursor;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.ColType;
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

final class BaseCustomerViewModelgetDBVal1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Cursor f1230c;
    final ColType cType;
    final String fieldName;
    final Ref.ObjectRef<Object> result;
    int label;
    final BaseCustomerViewModel this0;
    BaseCustomerViewModelgetDBVal1(final BaseCustomerViewModel baseCustomerViewModel, final Ref.ObjectRef<Object> refObjectRef, final Cursor cursor, final String str, final ColType colType, final Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = baseCustomerViewModel;
        result = refObjectRef;
        f1230c = cursor;
        fieldName = str;
        cType = colType;
    } 
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new BaseCustomerViewModelgetDBVal1(this0, result, f1230c, fieldName, cType, continuation);
    } 
    public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return (this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                final C26561 c26561 = new C26561(result, this0, f1230c, fieldName, cType, null);
                label = 1;
                obj = BuildersKt.withContext(io2, c26561, this);
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
            e2 = Log.e(str, "GetDBVal", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C26561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Cursor f1231c;
        final ColType cType;
        final String fieldName;
        final Ref.ObjectRef<Object> result;
        int label;
        final BaseCustomerViewModel this0; 
        C26561(final Ref.ObjectRef<Object> refObjectRef, final BaseCustomerViewModel baseCustomerViewModel, final Cursor cursor, final String str, final ColType colType, final Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            result = refObjectRef;
            this0 = baseCustomerViewModel;
            f1231c = cursor;
            fieldName = str;
            cType = colType;
        } 
        public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
            return new C26561(result, this0, f1231c, fieldName, cType, continuation);
        } 
        public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(final Object obj) {
            final IBaseCustomerRepository iBaseCustomerRepository;
            final String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 != this.label) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final Ref.ObjectRef<Object> refObjectRef = result;
            iBaseCustomerRepository = this0.repository;
            refObjectRef.element = iBaseCustomerRepository.getLogoSqlHelper().dbVal(f1231c, fieldName, cType);
            str = this0.TAG;
            return boxing.boxInt(Log.i(str, "GetDBVal"));
        }
    }
}
