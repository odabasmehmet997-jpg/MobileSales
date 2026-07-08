package com.proje.mobilesales.features.sales.view.detail;

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
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesDetailViewModelgetObjectWithSyncCode1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final boolean clearOnGet;
    final Ref.ObjectRef<Object> result;
    final int syncCode;
    int label;
    final SalesDetailViewModel this0;
    SalesDetailViewModelgetObjectWithSyncCode1(SalesDetailViewModel salesDetailViewModel, Ref.ObjectRef<Object> refObjectRef, int i2, boolean z, Continuation<? super SalesDetailViewModelgetObjectWithSyncCode1> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.this0 = salesDetailViewModel;
        this.result = refObjectRef;
        this.syncCode = i2;
        this.clearOnGet = z;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesDetailViewModelgetObjectWithSyncCode1(this.this0, this.result, this.syncCode, this.clearOnGet, (Continuation<? super SalesDetailViewModelgetObjectWithSyncCode1>) continuation);
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
                C28471 c28471 = new C28471(this.result, this.this0, this.syncCode, this.clearOnGet, null);
                this.label = 1;
                obj = BuildersKt.withContext(io2, c28471, this);
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
            e2 = Log.e(this.this0.getTAG(), "getObjectWithSyncCode", e3);
        }
        return boxing.boxInt(e2);
    }
    static final class C28471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final boolean clearOnGet;
        final Ref.ObjectRef<Object> result;
        final int syncCode;
        int label;
        final SalesDetailViewModel this0;
        C28471(Ref.ObjectRef<Object> refObjectRef, SalesDetailViewModel salesDetailViewModel, int i2, boolean z, Continuation<? super C28471> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesDetailViewModel;
            this.syncCode = i2;
            this.clearOnGet = z;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28471(this.result, this.this0, this.syncCode, this.clearOnGet, (Continuation<? super C28471>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.result.element = this.this0.getRepository().getObject(this.syncCode, this.clearOnGet);
            return boxing.boxInt(Log.i(this.this0.getTAG(), "getObjectWithSyncCode"));
        }
    }
}
