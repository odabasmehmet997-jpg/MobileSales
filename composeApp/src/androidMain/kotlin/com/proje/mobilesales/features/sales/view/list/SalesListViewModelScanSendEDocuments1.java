package com.proje.mobilesales.features.sales.view.list;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
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

public final class SalesListViewModelScanSendEDocuments1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Ref.BooleanRef result;
    int label;
    final SalesListViewModel this0;

    SalesListViewModelScanSendEDocuments1(Ref.BooleanRef refBooleanRef, SalesListViewModel salesListViewModel, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.result = refBooleanRef;
        this.this0 = salesListViewModel;
    }
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesListViewModelScanSendEDocuments1(this.result, this.this0, continuation);
    }

    public Object invoke(Object coroutineScope, ? super CoroutineContext.Element continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
    public Object invoke(Object p1, CoroutineContext.Element p2) {
        return null;
    }

    static final class C28631 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.BooleanRef result;
        int label;
        final SalesListViewModel this0;

        C28631(Ref.BooleanRef refBooleanRef, SalesListViewModel salesListViewModel, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesListViewModel;
        }

        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28631(this.result, this.this0, continuation);
        }

        public Object invoke(Object coroutineScope, ? super CoroutineContext.Element continuation) {
            return ((C28631) create(coroutineScope, (Continuation<?>) continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public Object invokeSuspend(Object obj) {
            int e2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                this.result.element = this.this0.getRepository().canSendEDocuments();
                e2 = Log.i(this.this0.getTAG(), "canSendEDocuments");
            } catch (Exception e3) {
                e2 = Log.e(this.this0.getTAG(), "canSendEDocuments", e3);
            }
            return boxing.boxInt(e2);
        }
          public Object invoke(Object p1, CoroutineContext.Element p2) {
              return null;
          }
      }
 
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C28631 c28631 = new C28631(this.result, this.this0, null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, c28631, this);
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
