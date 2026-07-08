package com.proje.mobilesales.features.sales.view.list;

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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

final class SalesListViewModelinsertFicheNoParamBroadcastMessage1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final int fTypeValue;
    final int ficheId;
    int label;
    final SalesListViewModel this0;

    
    SalesListViewModelinsertFicheNoParamBroadcastMessage1(SalesListViewModel salesListViewModel, int i2, int i3, Continuation<? super SalesListViewModelinsertFicheNoParamBroadcastMessage1> continuation) {
        super(2, continuation);
        this.this0 = salesListViewModel;
        this.ficheId = i2;
        this.fTypeValue = i3;
    }

    
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesListViewModelinsertFicheNoParamBroadcastMessage1(this.this0, this.ficheId, this.fTypeValue, continuation);
    }

    
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
     static final class C28671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int fTypeValue;
        final int ficheId;
        int label;
        final SalesListViewModel this0;

        
        C28671(SalesListViewModel salesListViewModel, int i2, int i3, Continuation<? super C28671> continuation) {
            super(2, continuation);
            this.this0 = salesListViewModel;
            this.ficheId = i2;
            this.fTypeValue = i3;
        }

        
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28671(this.this0, this.ficheId, this.fTypeValue, continuation);
        }

        
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        
        public Object invokeSuspend(Object obj) {
            int e2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                this.this0.getRepository().insertFicheNoParamBroadcastMessage(this.ficheId, this.fTypeValue);
                e2 = Log.i(this.this0.getTAG(), "insertFicheNoParamBroadcastMessage");
            } catch (Exception e3) {
                e2 = Log.e(this.this0.getTAG(), "insertFicheNoParamBroadcastMessage", e3);
            }
            return boxing.boxInt(e2);
        }
    }

    
    public Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C28671 c28671 = new C28671(this.this0, this.ficheId, this.fTypeValue, null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, c28671, this);
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
