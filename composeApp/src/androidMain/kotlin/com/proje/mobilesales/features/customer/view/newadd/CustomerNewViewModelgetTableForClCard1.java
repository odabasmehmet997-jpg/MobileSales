package com.proje.mobilesales.features.customer.view.newadd;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.repository.CustomerNewRepository;
import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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


final class CustomerNewViewModelgetTableForClCard1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.ObjectRef<List<ClCard>> result;
    final String selection;
    final String[] selectionArgs;
    final Class<ClCard> tableClass;
    int label;
    final CustomerNewViewModel this0;

    CustomerNewViewModelgetTableForClCard1(final CustomerNewViewModel customerNewViewModel, final Ref.ObjectRef<List<ClCard>> refObjectRef, final Class<ClCard> cls, final String str, final String[] strArr, final Continuation<? super CustomerNewViewModelgetTableForClCard1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this0 = customerNewViewModel;
        result = refObjectRef;
        tableClass = cls;
        selection = str;
        selectionArgs = strArr;
    }
    public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
        return new CustomerNewViewModelgetTableForClCard1(this0, result, tableClass, selection, selectionArgs, continuation);
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
                final C26701 c26701 = new C26701(result, this0, tableClass, selection, selectionArgs, null);
                label = 1;
                obj = BuildersKt.withContext(io2, c26701, this);
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
            e2 = Log.e(str, "GetTableForClCard", e3);
        }
        final Boxing boxing = null;
        return Boxing.boxInt(e2);
    }
     static final class C26701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<List<ClCard>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<ClCard> tableClass;
        int label;
        final CustomerNewViewModel this0;

        C26701(final Ref.ObjectRef<List<ClCard>> refObjectRef, final CustomerNewViewModel customerNewViewModel, final Class<ClCard> cls, final String str, final String[] strArr, final Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            result = refObjectRef;
            this0 = customerNewViewModel;
            tableClass = cls;
            selection = str;
            selectionArgs = strArr;
        }
 
        public _FileSystemKtcommonListRecursively1 create(final Object obj, final Continuation<?> continuation) {
            return new C26701(result, this0, tableClass, selection, selectionArgs, continuation);
        }
 
        public Object invoke(final CoroutineScope coroutineScope, final Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C26701) this.create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(final Object obj) {
            final CustomerNewRepository customerNewRepository;
            final String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (0 != this.label) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            final Ref.ObjectRef<List<ClCard>> refObjectRef = result;
            customerNewRepository = this0.repository;
            final List<ClCard> table = customerNewRepository.getISqlHelperClCard().getTable(tableClass, selection, selectionArgs);
            Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.database.ClCard>");
            refObjectRef.element = table;
            str = this0.TAG;
            return boxing.boxInt(Log.i(str, "GetTableForClCard"));
        }
    }
}
