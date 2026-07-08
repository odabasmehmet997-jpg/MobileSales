package com.proje.mobilesales.features.sales.view.variant;

import android.util.Log;
import androidx.webkit.internal.ApiFeature;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.model.ItemVariantStock;
import com.proje.mobilesales.features.sales.repository.SalesVariantRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import java.util.ArrayList;
import java.util.List;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class SalesVariantViewModel extends BaseSalesViewModel {
    private final SalesVariantRepository repository;
    public SalesVariantViewModel(SalesVariantRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
    public SalesVariantRepository getRepository() {
        return this.repository;
    }
    public static final class C31721 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final String itemCode;
        final int itemRef;
        final Ref.ObjectRef<List<ItemVariantStock>> result;
        final int warehouseNr;
        int label;
        final SalesVariantViewModel this0;
        C31721(Ref.ObjectRef<List<ItemVariantStock>> refObjectRef, SalesVariantViewModel salesVariantViewModel, int r3, int r4, String str, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesVariantViewModel;
            this.itemRef = r3;
            this.warehouseNr = r4;
            this.itemCode = str;
        }
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final String itemCode;
            final int itemRef;
            final Ref.ObjectRef<List<ItemVariantStock>> result;
            final int warehouseNr;
            int label;
            final SalesVariantViewModel this0;
            AnonymousClass1(Ref.ObjectRef<List<ItemVariantStock>> refObjectRef, SalesVariantViewModel salesVariantViewModel, int r3, int r4, String str, Continuation<?> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesVariantViewModel;
                this.itemRef = r3;
                this.warehouseNr = r4;
                this.itemCode = str;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.itemRef, this.warehouseNr, this.itemCode, continuation);
            }
            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
                return  create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getVariantInfo(this.itemRef, this.warehouseNr, this.itemCode);
                    i2 = Log.i(this.this0.getTAG(), "getVariantInfo");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getVariantInfo", e2);
                }
                return Boxing.boxInt(i2);
            }
            public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                return null;
            }
        }
        public Object invokeSuspend(Object obj)  {
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.itemRef, this.warehouseNr, this.itemCode, null);
                this.label = 1;
                obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
            } else {
                if (r1 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }
    public List<ItemVariantStock> getVariantInfo(int i2, int i3, String itemCode) {
        Intrinsics.checkNotNullParameter(itemCode, "itemCode");
        RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = new ArrayList();
        try {
            BuildersKt.runBlocking((CoroutineContext) null, new SalesVariantViewModelgetVariantInfo1(refObjectRef, this, i2, i3, itemCode, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (List) refObjectRef.element;
    }

    private class SalesVariantViewModelgetVariantInfo1 implements Function2<CoroutineScope, Continuation<? super ApiFeature.T>, Object> {
        public SalesVariantViewModelgetVariantInfo1(RefObjectRef refObjectRef, SalesVariantViewModel salesVariantViewModel, int i2, int i3, String itemCode, Object o) {
        }

        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
}
