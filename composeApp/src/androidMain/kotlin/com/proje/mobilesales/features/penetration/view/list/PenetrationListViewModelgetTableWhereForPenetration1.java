package com.proje.mobilesales.features.penetration.view.list;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.penetration.model.database.Penetration;
import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
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
 
public final class PenetrationListViewModelgetTableWhereForPenetration1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final String orderBy;
    final Ref.ObjectRef<List<Penetration>> result;
    final Class<Penetration> tableClass;
    final String where;
    final String[] whereParams;
    int label;
    final PenetrationListViewModel this0;

    public PenetrationListViewModelgetTableWhereForPenetration1(Ref.ObjectRef<List<Penetration>> refObjectRef, PenetrationListViewModel penetrationListViewModel, Class<Penetration> cls, String str, String[] strArr, String str2, Continuation<?> continuation) {
        super(2, (Continuation<Object>) continuation);
        this.result = refObjectRef;
        this.this0 = penetrationListViewModel;
        this.tableClass = cls;
        this.where = str;
        this.whereParams = strArr;
        this.orderBy = str2;
    }

    @Override
    public Object invokeSuspend(Object obj) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io = Dispatchers.getIO();
                final Ref.ObjectRef<List<Penetration>> refObjectRef = this.result;
                final PenetrationListViewModel penetrationListViewModel = this.this0;
                final Class<Penetration> cls = this.tableClass;
                final String str = this.where;
                final String[] strArr = this.whereParams;
                final String str2 = this.orderBy;

                this.label = 1;
                Function2<? super CoroutineScope, ? super Continuation<? super Object>, ?> r1 = null;
                obj = BuildersKt.withContext(io, r1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i2 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = ((Number) obj).intValue();
        } catch (Exception e) {
            i = Log.e(BaseViewModel.TAG, "getTableWhereForPenetration", e);
        }
        return boxing.boxInt(i);
    }
    @Override
    public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return null;
    }
}
