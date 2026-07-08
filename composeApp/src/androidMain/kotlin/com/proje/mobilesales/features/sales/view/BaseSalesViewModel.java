package com.proje.mobilesales.features.sales.view;

import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.edocument.ShowEDocumentResponse;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.ErpParamType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.WorkTimeControlProcessType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.dbmodel.EmailTemplate;
import com.proje.mobilesales.features.dbmodel.Firm;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.model.EDocInfoModel;
import com.proje.mobilesales.features.model.EmailReplacerModel;
import com.proje.mobilesales.features.model.LastPurchaseInfoSqlParams;
import com.proje.mobilesales.features.model.RequiredFields;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheParameters;
import com.proje.mobilesales.features.sales.repository.IBaseSalesRepository;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.*;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public class BaseSalesViewModel extends BaseViewModel {
    private final String TAG;
    private final boolean isPromotionItemPriceEnabled;
    private final IBaseSalesRepository repository;
    public BaseSalesViewModel(IBaseSalesRepository bRepository) {
        super(bRepository);
        Intrinsics.checkNotNullParameter(bRepository, "bRepository");
        this.repository = bRepository;
        this.TAG = "BaseSalesViewModel";
        try {
            this.isPromotionItemPriceEnabled = BuildersKt.runBlocking(null, new C30541(null)).booleanValue();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public final String getTAG() {
        return this.TAG;
    }
    final class C30541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        Object L0;
        int label;
        C30541(Continuation<? super C30541> continuation) {
            super(2, (Continuation<Object>) continuation);
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30541((Continuation<? super C30541>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj){
            Ref.BooleanRef refBooleanRef;
            Exception e2;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.BooleanRef refBooleanRef2 = new Ref.BooleanRef();
                try {
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(refBooleanRef2, BaseSalesViewModel.this, null);
                    this.L0 = refBooleanRef2;
                    this.label = 1;
                    if (BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == obj2) {
                        return obj2;
                    }
                    refBooleanRef = refBooleanRef2;
                } catch (Exception e3) {
                    refBooleanRef = refBooleanRef2;
                    e2 = e3;
                    Log.e(BaseSalesViewModel.this.getTAG(), "IsPromotionItemPriceEnabled", e2);
                    return Boxing.boxBoolean(refBooleanRef.element);
                }
            } else {
                if (r1 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                refBooleanRef = (Ref.BooleanRef) this.L0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e4) {
                    e2 = e4;
                    Log.e(BaseSalesViewModel.this.getTAG(), "IsPromotionItemPriceEnabled", e2);
                    return Boxing.boxBoolean(refBooleanRef.element);
                }
            }
            return Boxing.boxBoolean(refBooleanRef.element);
        }
        final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.BooleanRef result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = baseSalesViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.repository.getIsPromotionItemPriceEnabled();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "IsPromotionItemPriceEnabled"));
            }
        }
    }
    public final boolean isPromotionItemPriceEnabled() {
        return this.isPromotionItemPriceEnabled;
    }
    final class C30451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final Ref.ObjectRef<String> result;
        int label;
        C30451(Ref.ObjectRef<String> refObjectRef, Continuation<? super C30451> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30451(this.result, (Continuation<Object>) continuation);
        }
        public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public   Object invokeSuspend(Object obj){
            int r6;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r6 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r6 = Log.e(BaseSalesViewModel.this.getTAG(), "GetLocalCurrencyCode", e2);
            }
            return Boxing.boxInt(r6);
        }
        final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
            final Ref.ObjectRef<String> result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.ObjectRef<String> refObjectRef, BaseSalesViewModel baseSalesViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = baseSalesViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<Object>) continuation);
            }
            public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public   Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.repository.getLocalCurrencyCode();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "GetLocalCurrencyCode"));
            }
        }
    }
    public final String getLocalCurrencyCode() throws InterruptedException {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        BuildersKt.runBlocking((CoroutineContext) null, new C30451(refObjectRef, null));
        return (String) refObjectRef.element;
    }
    final class C30471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final Ref.IntRef result;
        final Sales sales;
        int label;
        C30471(Ref.IntRef refIntRef, Sales sales, Continuation<? super C30471> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refIntRef;
            this.sales = sales;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30471(this.result, this.sales, (Continuation<Object>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public   Object invokeSuspend(Object obj){
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, this.sales, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r7 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r7 = Log.e(BaseSalesViewModel.this.getTAG(), "getSaveObjectWithSales", e2);
            }
            return Boxing.boxInt(r7);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final Ref.IntRef result;
            final Sales sales;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.IntRef refIntRef, BaseSalesViewModel baseSalesViewModel, Sales sales, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refIntRef;
                this.this0 = baseSalesViewModel;
                this.sales = sales;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.sales, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.repository.getSaveObject(this.sales);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getSaveObjectWithSales"));
            }
        }
    }
    public final int getSaveObjectWithSales(Sales sales) throws InterruptedException {
        Ref.IntRef refIntRef = new Ref.IntRef();
        BuildersKt.runBlocking(null, new C30471(refIntRef, sales, null));
        return refIntRef.element;
    }
    final class C30441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<Object>, Object> {
        final int branchLogicalRef;
        final int customerRef;
        final Ref.ObjectRef<EDocInfoModel> result;
        int label;
        C30441(Ref.ObjectRef<EDocInfoModel> refObjectRef, int r3, int r4, Continuation<Object> continuation) {
            super(2, continuation);
            this.result = refObjectRef;
            this.customerRef = r3;
            this.branchLogicalRef = r4;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30441(this.result, this.customerRef, this.branchLogicalRef, (Continuation<Object>) continuation);
        }
        public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public   Object invokeSuspend(Object obj){
            int r9;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, this.customerRef, this.branchLogicalRef, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r9 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r9 = Log.e(BaseSalesViewModel.this.getTAG(), "getEDocInfo", e2);
            }
            return Boxing.boxInt(r9);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final int branchLogicalRef;
            final int customerRef;
            final Ref.ObjectRef<EDocInfoModel> result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.ObjectRef<EDocInfoModel> refObjectRef, BaseSalesViewModel baseSalesViewModel, int r3, int r4, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = baseSalesViewModel;
                this.customerRef = r3;
                this.branchLogicalRef = r4;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.customerRef, this.branchLogicalRef, (Continuation<Object>) continuation);
            }
            public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public   Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.repository.getEDocInfo(this.customerRef, this.branchLogicalRef);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getEDocInfo"));
            }
        }
    }
    public final EDocInfoModel getEDocInfo(int r9, int r10) throws InterruptedException {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = new EDocInfoModel();
        BuildersKt.runBlocking(null, null);
        return (EDocInfoModel) refObjectRef.element;
    }
    public final boolean changeDemandLinesWarehouse() {
        String logoParamValue = getSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.DEMMGMT_EDITLINESRCIDX.getmValue()));
        Intrinsics.checkNotNullExpressionValue(logoParamValue, "getLogoParamValue(...)");
        return StringUtils.paramValueNumberCheck(logoParamValue);
    }
    public final boolean changeSalesLinesWarehouse() {
        String logoParamValue = getSqlHelper().getLogoParamValue(String.valueOf(ErpParamType.SALES_EDITLINESRCIDX.getmValue()));
        Intrinsics.checkNotNullExpressionValue(logoParamValue, "getLogoParamValue(...)");
        return StringUtils.paramValueNumberCheck(logoParamValue);
    }
    public final void getCheckRemoteWorkTimeControl(WorkTimeControlProcessType workTimeControlProcessType, ResponseListener<String> checkWorkResponseListener) {
        Intrinsics.checkNotNullParameter(workTimeControlProcessType, "workTimeControlProcessType");
        Intrinsics.checkNotNullParameter(checkWorkResponseListener, "checkWorkResponseListener");
        this.repository.checkRemoteWorkTimeControl(workTimeControlProcessType, checkWorkResponseListener);
        Log.i(this.TAG, "GetCheckRemoteWorkTimeControl");
    }
    static final class C30521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final int branchLogicalRef;
        final Ref.BooleanRef result;
        int label;
        final BaseSalesViewModel this0;
        C30521(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, int r3, Continuation<Object> continuation) {
            super(2, continuation);
            this.result = refBooleanRef;
            this.this0 = baseSalesViewModel;
            this.branchLogicalRef = r3;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C30521(this.result, this.this0, this.branchLogicalRef, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final int branchLogicalRef;

            final Ref.BooleanRef result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, int r3, Continuation<Object> continuation) {
                super(2, continuation);
                this.result = refBooleanRef;
                this.this0 = baseSalesViewModel;
                this.branchLogicalRef = r3;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.branchLogicalRef, (Continuation<Object>) continuation);
            }
            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj){
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.repository.getUseEdespatch(this.branchLogicalRef);
                    i2 = Log.i(this.this0.getTAG(), "getUseEdespatch");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getUseEdespatch", e2);
                }
                return Boxing.boxInt(i2);
            }
        }
        public Object invokeSuspend(Object obj) {
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.branchLogicalRef, null);
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
    public final boolean getUseEDispatch(int r4) throws InterruptedException {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new C30521(refBooleanRef, this, r4, null));
        return refBooleanRef.element;
    }
    static final class C30431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.BooleanRef result;
        int label;
        final BaseSalesViewModel this0;
        C30431(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<? super C30431> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = baseSalesViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C30431(this.result, this.this0, (Continuation<? super C30431>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.BooleanRef result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = baseSalesViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
            }
            public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public   Object invokeSuspend(Object obj) {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.repository.firmUseEInvoice();
                    i2 = Log.i(this.this0.getTAG(), "firmUseEInvoice");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "firmUseEInvoice", e2);
                }
                return Boxing.boxInt(i2);
            }
        }
        public   Object invokeSuspend(Object obj){
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, null);
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
    public final boolean firmUseEInvoice() throws InterruptedException {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new C30431(refBooleanRef, this, null));
        return refBooleanRef.element;
    }
    static final class C30421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.BooleanRef result;
        int label;
        final BaseSalesViewModel this0;
        C30421(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<? super C30421> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = baseSalesViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C30421(this.result, this.this0, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return ( create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final Ref.BooleanRef result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = baseSalesViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj){
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.repository.firmUseEArchive();
                    i2 = Log.i(this.this0.getTAG(), "firmUseEArchive");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "firmUseEArchive", e2);
                }
                return Boxing.boxInt(i2);
            }
        }
        public Object invokeSuspend(Object obj){
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, null);
                this.label = 1;
                obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
            } else if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }
    public final boolean firmUseEArchive() throws InterruptedException {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new C30421(refBooleanRef, this, null));
        return refBooleanRef.element;
    }
    final class C30501 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<List<Item>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<Item> tableClass;
        int label;
        C30501(Ref.ObjectRef<List<Item>> refObjectRef, Class<Item> cls, String str, String[] strArr, Continuation<? super C30501> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.tableClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30501(this.result, this.tableClass, this.selection, this.selectionArgs, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public  Object invokeSuspend(Object obj){
            int r10;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, this.tableClass, this.selection, this.selectionArgs, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r10 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r10 = Log.e(BaseSalesViewModel.this.getTAG(), "getTableForItemFromSqlHelper", e2);
            }
            return Boxing.boxInt(r10);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.ObjectRef<List<Item>> result;
            final String selection;
            final String[] selectionArgs;
            final Class<Item> tableClass;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.ObjectRef<List<Item>> refObjectRef, BaseSalesViewModel baseSalesViewModel, Class<Item> cls, String str, String[] strArr, Continuation<Object> continuation) {
                super(2, continuation);
                this.result = refObjectRef;
                this.this0 = baseSalesViewModel;
                this.tableClass = cls;
                this.selection = str;
                this.selectionArgs = strArr;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, (Continuation<Object>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<List<Item>> refObjectRef = this.result;
                ISqlHelper<Item> iSqlHelperItem = this.this0.repository.getISqlHelperItem();
                refObjectRef.element = iSqlHelperItem != null ? iSqlHelperItem.getTable(this.tableClass, this.selection, this.selectionArgs) : null;
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getTableForItemFromSqlHelper"));
            }

        }
    }
    public final List<Item> getTableForItemFromSqlHelper(Class<Item> tableClass, String selection, String[] selectionArgs) throws InterruptedException {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef<List<Item>> refObjectRef = new Ref.ObjectRef();
        BuildersKt.runBlocking(null, new C30501(refObjectRef, tableClass, selection, selectionArgs, null));
        return refObjectRef.element;
    }
    final class C30481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<List<EmailTemplate>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<EmailTemplate> tableClass;
        int label;
        C30481(Ref.ObjectRef<List<EmailTemplate>> refObjectRef, Class<EmailTemplate> cls, String str, String[] strArr, Continuation<? super C30481> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.tableClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30481(this.result, this.tableClass, this.selection, this.selectionArgs, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return ( create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj){
            int r10;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, this.tableClass, this.selection, this.selectionArgs, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else if (r1 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                r10 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r10 = Log.e(BaseSalesViewModel.this.getTAG(), "getTableForEmailTemplateFromSqlHelper", e2);
            }
            return Boxing.boxInt(r10);
        }
        final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.ObjectRef<List<EmailTemplate>> result;
            final String selection;
            final String[] selectionArgs;
            final Class<EmailTemplate> tableClass;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.ObjectRef<List<EmailTemplate>> refObjectRef, BaseSalesViewModel baseSalesViewModel, Class<EmailTemplate> cls, String str, String[] strArr, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = baseSalesViewModel;
                this.tableClass = cls;
                this.selection = str;
                this.selectionArgs = strArr;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, (Continuation<Object>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<List<EmailTemplate>> refObjectRef = this.result;
                List<EmailTemplate> table = this.this0.repository.getISqlHelperEmailTemplate().getTable(this.tableClass, this.selection, this.selectionArgs);
                Intrinsics.checkNotNullExpressionValue(table, "getTable(...)");
                refObjectRef.element = table;
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getTableForEmailTemplateFromSqlHelper"));
            }
        }
    }
    public final List<EmailTemplate> getTableForEmailTemplateFromSqlHelper(Class<EmailTemplate> tableClass, String selection, String[] selectionArgs) throws InterruptedException {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = CollectionsKt.emptyList();
        BuildersKt.runBlocking(null, new C30481(refObjectRef, tableClass, selection, selectionArgs, null));
        return (List) refObjectRef.element;
    }
    public final void getCustomerNowAndBeforeBalance(int r2, Class<?> tableClass, int r4, ResponseListener<ArrayList<CustomerBeforeBalance>> responseListener) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCustomerNowAndBeforeBalance(r2, tableClass, r4, responseListener);
        Log.i(this.TAG, "getTableForEmailTemplateFromSqlHelper");
    }
    public final void replaceSalesFicheHtml(EmailReplacerModel data, CustomerBeforeBalance customerBeforeBalance, ResponseListener<EmailObject> responseListener) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.replaceSalesFicheHtml(data, customerBeforeBalance, responseListener);
        Log.i(this.TAG, "replaceSalesFicheHtml");
    }
    public final Disposable getEDocumentContent(String ficheId, ResponseListener<?> responseListener, SalesType salesType, Boolean bool) {
        Intrinsics.checkNotNullParameter(ficheId, "ficheId");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable eDocumentContent = this.repository.getEDocumentContent(ficheId, responseListener, salesType, bool);
        Log.i(this.TAG, "getEDocumentContent");
        return eDocumentContent;
    }
    public final void showEDocument(int r2, FicheType ficheType, ResponseListener<ShowEDocumentResponse> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.showEDocument(r2, ficheType, responseListener);
        Log.i(this.TAG, "showEDocument");
    }
    public final void getEDocument(Sales sales, FicheType ficheType, ResponseListener<ShowEDocumentResponse> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getEDocument(sales, ficheType, responseListener);
        Log.i(this.TAG, "getEDocument");
    }
    final class C30491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.ObjectRef<List<Firm>> result;
        final String selection;
        final String[] selectionArgs;
        final Class<Firm> tableClass;
        int label;
        C30491(Ref.ObjectRef<List<Firm>> refObjectRef, Class<Firm> cls, String str, String[] strArr, Continuation<Object> continuation) {
            super(2, continuation);
            this.result = refObjectRef;
            this.tableClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30491(this.result, this.tableClass, this.selection, this.selectionArgs, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public Object invokeSuspend(Object obj){
            int r10;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, this.tableClass, this.selection, this.selectionArgs, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r10 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r10 = Log.e(BaseSalesViewModel.this.getTAG(), "getTableForFirmFromSqlHelper", e2);
            }
            return Boxing.boxInt(r10);
        }
        final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.ObjectRef<List<Firm>> result;
            final String selection;
            final String[] selectionArgs;
            final Class<Firm> tableClass;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.ObjectRef<List<Firm>> refObjectRef, BaseSalesViewModel baseSalesViewModel, Class<Firm> cls, String str, String[] strArr, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = baseSalesViewModel;
                this.tableClass = cls;
                this.selection = str;
                this.selectionArgs = strArr;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                ISqlHelper<Firm> iSqlHelperFirm = this.this0.repository.getISqlHelperFirm();
                if (iSqlHelperFirm != null)
                    this.result.element = iSqlHelperFirm.getTable(this.tableClass, this.selection, this.selectionArgs);
                else this.result.element = Collections.emptyList();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getTableForFirmFromSqlHelper"));
            }

        }
    }
    public final List<Firm> getTableForFirmFromSqlHelper(Class<Firm> tableClass, String selection, String[] selectionArgs) throws InterruptedException {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef<List<Firm>> refObjectRef = new Ref.ObjectRef<>();
        BuildersKt.runBlocking(null, new C30491(refObjectRef, tableClass, selection, selectionArgs, null));
        return refObjectRef.element;
    }
    public final double getDefinedPurchasePrice(int r2) {
        try {
            return this.repository.getLogoSqlHelper().getDefinedPurchasePrice(r2);
        } catch (Exception e2) {
            Log.e(this.TAG, "getDefinedPurchasePrice", e2);
            return 0.0d;
        }
    }
    public final void getLastPurchaseInfo(LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(lastPurchaseInfoSqlParams, "lastPurchaseInfoSqlParams");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.getLastPurchaseInfo(lastPurchaseInfoSqlParams, responseListener);
        } catch (Exception e) {
            Log.e(this.TAG, "getLastPurchaseInfo", e);
            return;
        }
        Log.i(this.TAG, "getLastPurchaseInfo");
    }
    public final RequiredFields requiredFields(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        try {
            return this.repository.getRequiredFields(salesType);
        } catch (Exception e) {
            Log.e(this.TAG, "requiredFields", e);
            return RequiredFields.NONE;
        }
    }
    final class C30461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<Object>, Object> {
        final Ref.ObjectRef<SalesFicheParameters> result;
        final SalesType salesType;
        int label;
        C30461(Ref.ObjectRef<SalesFicheParameters> refObjectRef, SalesType salesType, Continuation<Object> continuation) {
            super(2, continuation);
            this.result = refObjectRef;
            this.salesType = salesType;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30461(this.result, this.salesType, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public Object invokeSuspend(Object obj){
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, this.salesType, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r7 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r7 = Log.e(BaseSalesViewModel.this.getTAG(), "getSalesFicheParameters", e2);
            }
            return Boxing.boxInt(r7);
        }
         static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.ObjectRef<SalesFicheParameters> result;
            final SalesType salesType;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.ObjectRef<SalesFicheParameters> refObjectRef, BaseSalesViewModel baseSalesViewModel, SalesType salesType, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = baseSalesViewModel;
                this.salesType = salesType;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.salesType, (Continuation<Object>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj){
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.repository.getSalesFicheParameters(this.salesType);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getSalesFicheParameters"));
            }

        }
    }
    static final class C30411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.BooleanRef result;
        int label;
        final BaseSalesViewModel this0;
        C30411(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<Object> continuation) {
            super(2, continuation);
            this.result = refBooleanRef;
            this.this0 = baseSalesViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C30411(this.result, this.this0, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation)   {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
         final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<Object>, Object> {
            final Ref.BooleanRef result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<Object> continuation) {
                super(2, continuation);
                this.result = refBooleanRef;
                this.this0 = baseSalesViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<Object>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj) {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.repository.canOrderCanTransferEInvoiceOrEArchive();
                    i2 = Log.i(this.this0.getTAG(), "canOrderCanTransferEInvoiceOrEArchive");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "canOrderCanTransferEInvoiceOrEArchive", e2);
                }
                return Boxing.boxInt(i2);
            }
         }
        public Object invokeSuspend(Object obj)  {
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, null);
                this.label = 1;

            } else if (r1 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }
    public final boolean canOrderCanTransferEInvoiceOrEArchive() throws InterruptedException {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new C30411(refBooleanRef, this, null));
        return refBooleanRef.element;
    }
    final class C30531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final Ref.BooleanRef result;
        int label;
        C30531(Ref.BooleanRef refBooleanRef, Continuation<? super C30531> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30531(this.result, (Continuation<? super C30531>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj){
            int r6;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    new AnonymousClass1(this.result, BaseSalesViewModel.this, null);
                    this.label = 1;

                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r6 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r6 = Log.e(BaseSalesViewModel.this.getTAG(), "getVATEnabled", e2);
            }
            return Boxing.boxInt(r6);
        }
         static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.BooleanRef result;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.BooleanRef refBooleanRef, BaseSalesViewModel baseSalesViewModel, Continuation<Object> continuation) {
                super(2, continuation);
                this.result = refBooleanRef;
                this.this0 = baseSalesViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<Object>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.repository.getVATEnabled();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getVATEnabled"));
            }
        }
    }
    public final boolean getVATEnabled() throws InterruptedException {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        BuildersKt.runBlocking(null, new C30531(refBooleanRef, null));
        return refBooleanRef.element;
    }
    public final boolean isDifferentShipAddress() {
        Log.i(this.TAG, "isDifferentShipAddress");
        return this.repository.isDifferentShipAddress();
    }
    public final boolean isRequiredShippingAddressIgnored() {
        Log.i(this.TAG, "isRequiredShippingAddressIgnored");
        return this.repository.isRequiredShippingAddressIgnored();
    }
    final class C30511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<Object>, Object> {
        Ref.ObjectRef<List<ShipAddress>> result = null;
        String selection = "";
        String[] selectionArgs = new String[0];
        Class<ShipAddress> tableClass = null;
        int label;
        public C30511() {
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return BaseSalesViewModel.this.new C30511();
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj){
            int r10;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, BaseSalesViewModel.this, this.tableClass, this.selection, this.selectionArgs, null);
                    this.label = 1;
                    obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
                    if (obj == obj2) {
                        return obj2;
                    }
                } else {
                    if (r1 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                r10 = ((Number) obj).intValue();
            } catch (Exception e2) {
                r10 = Log.e(BaseSalesViewModel.this.getTAG(), "getTableForShipAddressFromSqlHelper", e2);
            }
            return Boxing.boxInt(r10);
        }

        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final Ref.ObjectRef<List<ShipAddress>> result;
            final String selection;
            final String[] selectionArgs;
            final Class<ShipAddress> tableClass;
            int label;
            final BaseSalesViewModel this0;
            AnonymousClass1(Ref.ObjectRef<List<ShipAddress>> refObjectRef, BaseSalesViewModel baseSalesViewModel, Class<ShipAddress> cls, String str, String[] strArr, Continuation<?> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = baseSalesViewModel;
                this.tableClass = cls;
                this.selection = str;
                this.selectionArgs = strArr;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.tableClass, this.selection, this.selectionArgs, continuation);
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.repository.getISqlHelperShipAddress().getTable(this.tableClass, this.selection, this.selectionArgs);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getTableForShipAddressFromSqlHelper"));
            }
            public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                return null;
            }
        }
    }
    public final List<ShipAddress> getTableForShipAddressFromSqlHelper(Class<ShipAddress> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        return (List) refObjectRef.element;
    }
}
