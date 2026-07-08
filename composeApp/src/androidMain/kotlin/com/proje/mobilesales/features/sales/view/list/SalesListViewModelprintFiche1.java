package com.proje.mobilesales.features.sales.view.list;

import android.content.Context;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.FicheType;
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

final class SalesListViewModelprintFiche1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final Context context;
    final int customerRef;
    final int ficheId;
    final FicheType ficheType;
    final boolean isTransfer;
    final int localFicheId;
    int label;
    final SalesListViewModel this0;

     
    SalesListViewModelprintFiche1(SalesListViewModel salesListViewModel, Context context, FicheType ficheType, int i2, int i3, boolean z, int i4, Continuation<? super SalesListViewModelprintFiche1> continuation) {
        super(2, continuation);
        this.this0 = salesListViewModel;
        this.context = context;
        this.ficheType = ficheType;
        this.ficheId = i2;
        this.localFicheId = i3;
        this.isTransfer = z;
        this.customerRef = i4;
    } 
    public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
        return new SalesListViewModelprintFiche1(this.this0, this.context, this.ficheType, this.ficheId, this.localFicheId, this.isTransfer, this.customerRef, continuation);
    }
 
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }
 
    static final class C28681 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Context context;
        final int customerRef;
        final int ficheId;
        final FicheType ficheType;
        final boolean isTransfer;
        final int localFicheId;
        int label;
        final SalesListViewModel this0;
 
        C28681(SalesListViewModel salesListViewModel, Context context, FicheType ficheType, int i2, int i3, boolean z, int i4, Continuation<? super C28681> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.this0 = salesListViewModel;
            this.context = context;
            this.ficheType = ficheType;
            this.ficheId = i2;
            this.localFicheId = i3;
            this.isTransfer = z;
            this.customerRef = i4;
        }
 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C28681(this.this0, this.context, this.ficheType, this.ficheId, this.localFicheId, this.isTransfer, this.customerRef, continuation);
        }
 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ((C28681) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
 
        public Object invokeSuspend(Object obj) {
            int e2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                this.this0.getRepository().printFiche(this.context, this.ficheType, this.ficheId, this.localFicheId, this.isTransfer, this.customerRef);
                e2 = Log.i(this.this0.getTAG(), "printFiche");
            } catch (Exception e3) {
                e2 = Log.e(this.this0.getTAG(), "printFiche", e3);
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
            C28681 c28681 = new C28681(this.this0, this.context, this.ficheType, this.ficheId, this.localFicheId, this.isTransfer, this.customerRef, null);
            this.label = 1;
            obj = BuildersKt.withContext(io2, c28681, this);
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
