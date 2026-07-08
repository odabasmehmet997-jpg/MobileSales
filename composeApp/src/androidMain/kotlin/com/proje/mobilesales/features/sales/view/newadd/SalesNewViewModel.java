package com.proje.mobilesales.features.sales.view.newadd;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.DataObjectType;
import com.proje.mobilesales.core.enums.ErpInvoiceType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.RiskAlert;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.enums.WcfPriceType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerDiscount;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.dbmodel.Branch;
import com.proje.mobilesales.features.dbmodel.PaymentLine;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.Specodes;
import com.proje.mobilesales.features.driverinformation.model.NEDispatchInfoModel;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.model.DueDate;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheParameters;
import com.proje.mobilesales.features.sales.repository.ISalesNewRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import java.util.ArrayList;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.RefBooleanRef;
import kotlin.jvm.internal.RefIntRef;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt; 
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okio.internal._FileSystemKtcommonListRecursively1;

public final class SalesNewViewModel extends BaseSalesViewModel {
    private final boolean checkCustomerRiskAffect;
    private final String getDefaultFirstSpeCode;
    private final String getDefaultSecondSpeCode;
    private final boolean getOrderDoReserve;
    private final ISalesNewRepository repository;
    final class C31251 extends ContinuationImpl {
        Object L0;
        int label;
        Object result;

        C31251(Continuation<? super C31251> continuation) {
            super((Continuation<Object>) continuation);
        } 
        public Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            try {
                return SalesNewViewModel.this.fillTransferUserWhInfo(null, this);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    } 
    public SalesNewViewModel(ISalesNewRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        try {
            this.getDefaultFirstSpeCode = BuildersKt.runBlocking(null, new SalesNewViewModelgetDefaultFirstSpeCode1(this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            this.getDefaultSecondSpeCode = BuildersKt.runBlocking(null, new SalesNewViewModelgetDefaultSecondSpeCode1(this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            this.getOrderDoReserve = BuildersKt.runBlocking(null, new SalesNewViewModelgetOrderDoReserve1(this, null)).booleanValue();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            this.checkCustomerRiskAffect = ((Boolean) BuildersKt.runBlocking(null, null)).booleanValue();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public ISalesNewRepository getRepository() {
        return this.repository;
    }
    public String getGetDefaultFirstSpeCode() {
        return this.getDefaultFirstSpeCode;
    }
    public String getGetDefaultSecondSpeCode() {
        return this.getDefaultSecondSpeCode;
    }
    public boolean getGetOrderDoReserve() {
        return this.getOrderDoReserve;
    }
    public boolean getCheckCustomerRiskAffect() {
        return this.checkCustomerRiskAffect;
    } 
    final class C31361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefObjectRef<ErpInvoiceType> result;
        final  Sales sales;
        int label;
        C31361(RefObjectRef<ErpInvoiceType> refObjectRef, Sales sales, Continuation<? super C31361> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.sales = sales;
        } 
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31361(this.result, this.sales, (Continuation<? super C31361>) continuation);
        } 
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        } 
        public Object invokeSuspend(Object obj){
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.sales, null);
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
                r7 = Log.e(SalesNewViewModel.this.getTAG(), "getErpTypeFromSales", e2);
            }
            return Boxing.boxInt(r7);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefObjectRef<ErpInvoiceType> result;
            final  Sales sales;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<ErpInvoiceType> refObjectRef, SalesNewViewModel salesNewViewModel, Sales sales, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.sales = sales;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.sales, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().getErpTypeFromSales(this.sales);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getErpTypeFromSales"));
            }
        }
    }
    public T getErpTypeFromSales(Sales sales) {
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new C31361(refObjectRef, sales, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        Intrinsics.checkNotNull(t);
        return  t;
    }
    static final class C31221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int clRef;
        final  Context context;
        final  RefBooleanRef result;
        final  int routeDayRef;
        final  int routePlanRef;
        int label;
        final  SalesNewViewModel this0;
        C31221(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Context context, int r4, int r5, int r6, Continuation<? super C31221> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
            this.context = context;
            this.clRef = r4;
            this.routePlanRef = r5;
            this.routeDayRef = r6;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31221(this.result, this.this0, this.context, this.clRef, this.routePlanRef, this.routeDayRef, (Continuation<? super C31221>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int clRef;
            final  Context context;
            final  RefBooleanRef result;
            final  int routeDayRef;
            final  int routePlanRef;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Context context, int r4, int r5, int r6, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
                this.context = context;
                this.clRef = r4;
                this.routePlanRef = r5;
                this.routeDayRef = r6;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.context, this.clRef, this.routePlanRef, this.routeDayRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().checkRouteVisitOutOfOrder(this.context, this.clRef, this.routePlanRef, this.routeDayRef);
                    i2 = Log.i(this.this0.getTAG(), "checkRouteVisitOutOfOrder");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "checkRouteVisitOutOfOrder", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.context, this.clRef, this.routePlanRef, this.routeDayRef, null);
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
    public boolean checkRouteVisitOutOfOrder(Context context, int r12, int r13, int r14) throws InterruptedException {
        Intrinsics.checkNotNullParameter(context, "context");
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31221(refBooleanRef, this, context, r12, r13, r14, null));
        return refBooleanRef.element;
    }
    final class C31421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  boolean clearOnGet;
        final  RefObjectRef<Sales> result;
        final  int syncCode;
        int label;
        C31421(RefObjectRef<Sales> refObjectRef, int r3, boolean z, Continuation<? super C31421> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.syncCode = r3;
            this.clearOnGet = z;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31421(this.result, this.syncCode, this.clearOnGet, (Continuation<? super C31421>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public Object invokeSuspend(Object obj) {
            int r9;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.syncCode, this.clearOnGet, null);
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
                r9 = Log.e(SalesNewViewModel.this.getTAG(), "getObjectForSales", e2);
            }
            return Boxing.boxInt(r9);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  boolean clearOnGet;
            final  RefObjectRef<Sales> result;
            final  int syncCode;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<Sales> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, boolean z, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.syncCode = r3;
                this.clearOnGet = z;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.syncCode, this.clearOnGet, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj)  {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = (Sales) this.this0.getRepository().getObject(this.syncCode, this.clearOnGet);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getObjectForSales"));
            }
        }
    }
    public Sales getObjectForSales(int r9, boolean z) throws InterruptedException {
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new C31421(refObjectRef, r9, z, null));
        return (Sales) refObjectRef.element;
    }
    final class C31231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int syncCode;
        int label;
        C31231(int r2, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.syncCode = r2;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31231(this.syncCode, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj)  {
            int r6;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(SalesNewViewModel.this, this.syncCode, null);
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
                r6 = Log.e(SalesNewViewModel.this.getTAG(), "clearLocalDataStore", e2);
            }
            return Boxing.boxInt(r6);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int syncCode;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(SalesNewViewModel salesNewViewModel, int r2, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.this0 = salesNewViewModel;
                this.syncCode = r2;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this0, this.syncCode, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this0.getRepository().clearObject(this.syncCode);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "clearLocalDataStore"));
            }
         }
    }
    public  void clearObject(int r3) {
        try {
            BuildersKt.runBlocking(null, new C31231(r3, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void getOrderAvailableAmountsFromDetailRef(int r2, SalesActivityNew.SetOrderAvailableAmountListener responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOrderAvailableAmountsFromDetailRef(r2, responseListener);
        Log.i(getTAG(), "getOrderAvailableAmountsFromDetailRef");
    }
    final class C31551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  String defaultProjectRef;
        final  FicheDiscountRefProp projectProp;
        int label;
        C31551(FicheDiscountRefProp ficheDiscountRefProp, String str, Continuation<? super C31551> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.projectProp = ficheDiscountRefProp;
            this.defaultProjectRef = str;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31551(this.projectProp, this.defaultProjectRef, (Continuation<? super C31551>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj){
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(SalesNewViewModel.this, this.projectProp, this.defaultProjectRef, null);
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
                r7 = Log.e(SalesNewViewModel.this.getTAG(), "loadFicheDefaultProjectCode", e2);
            }
            return Boxing.boxInt(r7);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  String defaultProjectRef;
            final  FicheDiscountRefProp projectProp;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(SalesNewViewModel salesNewViewModel, FicheDiscountRefProp ficheDiscountRefProp, String str, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.this0 = salesNewViewModel;
                this.projectProp = ficheDiscountRefProp;
                this.defaultProjectRef = str;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this0, this.projectProp, this.defaultProjectRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this0.getRepository().loadFicheDefaultProjectCode(this.projectProp, this.defaultProjectRef);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "loadFicheDefaultProjectCode"));
            }
        }
    }
    public void loadFicheDefaultProjectCode(FicheDiscountRefProp projectProp, String defaultProjectRef) {
        Intrinsics.checkNotNullParameter(projectProp, "projectProp");
        Intrinsics.checkNotNullParameter(defaultProjectRef, "defaultProjectRef");
        try {
            BuildersKt.runBlocking(null, new C31551(projectProp, defaultProjectRef, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Object fillTransferUserWhInfo(Sales sales, Continuation<? super Unit> continuation) throws Throwable {
        C31251 c31251 = null;
        if (continuation instanceof C31251) {
            c31251 = (C31251) continuation;
            int r1 = c31251.label;
            if ((r1 & Integer.MIN_VALUE) != 0) {
                c31251.label = r1 - Integer.MIN_VALUE;
            } else {
                c31251 = new C31251((Continuation<? super C31251>) continuation);
            }
        }
        Object obj = c31251.result;
        Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = c31251.label;
        try {
            if (r2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                C31262 c31262 = new C31262(sales, null);
                c31251.L0 = this;
                c31251.label = 1;
                Object objWithContext = BuildersKt.withContext(coroutineDispatcher, c31262, c31251);

                if (objWithContext == obj2) {
                    return obj2;
                }
            } else {
                if (r2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                SalesNewViewModel salesNewViewModel = (SalesNewViewModel) c31251.L0;
                ResultKt.throwOnFailure(obj);
            }
        } catch (Exception e2) {
            Log.e(this.getTAG(), "fillTransferUserWhInfo", e2);
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
    final class C31262 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Sales sales;
        int label;
        C31262(Sales sales, Continuation<? super C31262> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.sales = sales;
        }
        public SuspendLambda create(CoroutineScope coroutineScope, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31262(this.sales, (Continuation<? super C31262>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public Object invokeSuspend(Object obj)  {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SalesNewViewModel.this.getRepository().fillTransferUserWhInfo(this.sales);
            return Boxing.boxInt(Log.i(SalesNewViewModel.this.getTAG(), "fillTransferUserWhInfo"));
        }
    }
    public void getPaySumCustomerForDueDate(int r2, int r3, SalesActivityNew.SalesActivityDueDateTrackListener responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.getPaySumCustomerForDueDate(r2, r3, responseListener);
        } catch (Exception e) {
            Log.e(getTAG(), "Error in getPaySumCustomerForDueDate: " + e.getMessage());
            throw new RuntimeException(e);
        }
        Log.i(getTAG(), "getPaySumCustomerForDueDate");
    }
    public void docNoUniqueControl(FicheType ficheType, String docNo, SalesActivityNew.SalesActivityDocNoListener responseListener) {
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        Intrinsics.checkNotNullParameter(docNo, "docNo");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.docNoUniqueControl(ficheType, docNo, responseListener);
        } catch (Exception e) {
            Log.e(getTAG(), "Error in docNoUniqueControl: " + e.getMessage());
            throw new RuntimeException(e);
        }
        Log.i(getTAG(), "docNoUniqueControl");
    }
    final class C31501 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefObjectRef<String> result;
        final  SalesType salesType;
        int label;
        C31501(RefObjectRef<String> refObjectRef, SalesType salesType, Continuation<? super C31501> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.salesType = salesType;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31501(this.result, this.salesType, (Continuation<? super C31501>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj){
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.salesType, null);
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
                r7 = Log.e(SalesNewViewModel.this.getTAG(), "getSpeCodeTypeHeader", e2);
            }
            return Boxing.boxInt(r7);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefObjectRef<String> result;
            final  SalesType salesType;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<String> refObjectRef, SalesNewViewModel salesNewViewModel, SalesType salesType, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.salesType = salesType;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.salesType, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().getSpeCodeTypeHeader(this.salesType);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getSpeCodeTypeHeader"));
            }
         }
    }
    public String getSpeCodeTypeHeader(SalesType salesType) {
        RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking(null, new C31501(refObjectRef, salesType, null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }
    final class C31531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefObjectRef<String> result;
        final  int warehouseNr;
        int label;
        C31531(RefObjectRef<String> refObjectRef, int r3, Continuation<? super C31531> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.warehouseNr = r3;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31531(this.result, this.warehouseNr, (Continuation<? super C31531>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj)   {
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.warehouseNr, null);
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
                r7 = Log.e(SalesNewViewModel.this.getTAG(), "getWarehouseUnsentDataTypes", e2);
            }
            return Boxing.boxInt(r7);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefObjectRef<String> result;
            final  int warehouseNr;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<String> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.warehouseNr = r3;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.warehouseNr, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj)  {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().getWarehouseUnsentDataTypes(this.warehouseNr);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getWarehouseUnsentDataTypes"));
            }
        }
    }
    public String getWarehouseUnsentDataTypes(int r4) {
        RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking(null, new C31531(refObjectRef, r4, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }
    final class C31311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int clRef;
        final  RefObjectRef<RiskAlert> result;
        final  SalesType salesType;
        int label;
        C31311(RefObjectRef<RiskAlert> refObjectRef, SalesType salesType, int r4, Continuation<? super C31311> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.salesType = salesType;
            this.clRef = r4;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31311(this.result, this.salesType, this.clRef, (Continuation<? super C31311>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public Object invokeSuspend(Object obj)  {
            int r9;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.salesType, this.clRef, null);
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
                r9 = Log.e(SalesNewViewModel.this.getTAG(), "getCustomerRiskAlert", e2);
            }
            return Boxing.boxInt(r9);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int clRef;
            final  RefObjectRef<RiskAlert> result;
            final  SalesType salesType;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<RiskAlert> refObjectRef, SalesNewViewModel salesNewViewModel, SalesType salesType, int r4, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.salesType = salesType;
                this.clRef = r4;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.salesType, this.clRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj)  {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().getCustomerRiskAlert(this.salesType, this.clRef);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getCustomerRiskAlert"));
            }
         }
    }
    public RiskAlert getCustomerRiskAlert(SalesType salesType, int r10) throws InterruptedException {
        RefObjectRef<RiskAlert> refObjectRef = new RefObjectRef<>();
        refObjectRef.element = RiskAlert.ABORT;
        BuildersKt.runBlocking(null, new C31311(refObjectRef, salesType, r10, null));
        return refObjectRef.element;
    }
    public MatterSettings getMatterSettings(Context context, FicheType ficheType) throws InterruptedException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        RefObjectRef<MatterSettings> refObjectRef = new RefObjectRef<>();
        refObjectRef.element = new MatterSettings(false, null, null, null, 15, null);
        BuildersKt.runBlocking(null, new C31411(refObjectRef, context, ficheType, null));
        return refObjectRef.element;
    }
    final class C31411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Context context;
        final  FicheType ficheType;
        final  RefObjectRef<MatterSettings> result;
        int label;
        C31411(RefObjectRef<MatterSettings> refObjectRef, Context context, FicheType ficheType, Continuation<? super C31411> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.context = context;
            this.ficheType = ficheType;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31411(this.result, this.context, this.ficheType, (Continuation<? super C31411>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj)   {
            int r9;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.context, this.ficheType, null);
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
                r9 = Log.e(SalesNewViewModel.this.getTAG(), "getMatterSettings", e2);
            }
            return Boxing.boxInt(r9);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  Context context;
            final  FicheType ficheType;
            final  RefObjectRef<MatterSettings> result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<MatterSettings> refObjectRef, SalesNewViewModel salesNewViewModel, Context context, FicheType ficheType, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.context = context;
                this.ficheType = ficheType;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.context, this.ficheType, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getMatterSettings(this.context, this.ficheType);
                } catch (Exception e) {
                    Log.e(this.this0.getTAG(), "Error fetching matter settings: " + e.getMessage());
                    return Boxing.boxInt(Log.e(this.this0.getTAG(), "Error fetching matter settings: " + e.getMessage()));
                }
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getMatterSettings"));
            }
         }
    }
    final class C31291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int clRef;
        final  RefObjectRef<CustomerDiscount> result;
        int label;
        C31291(RefObjectRef<CustomerDiscount> refObjectRef, int r3, Continuation<? super C31291> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.clRef = r3;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31291(this.result, this.clRef, (Continuation<Object>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj)  {
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.clRef, null);
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
                r7 = Log.e(SalesNewViewModel.this.getTAG(), "getCustomerDiscountRate", e2);
            }
            return Boxing.boxInt(r7);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int clRef;
            final  RefObjectRef<CustomerDiscount> result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<CustomerDiscount> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.clRef = r3;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.clRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj)  {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().getCustomerDiscountRate(this.clRef);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getCustomerDiscountRate"));
            }
        }
    }
    public CustomerDiscount getCustomerDiscountRate(int r9) throws InterruptedException {
        RefObjectRef<CustomerDiscount> refObjectRef = new RefObjectRef<>();
        refObjectRef.element = new CustomerDiscount(0.0d, 0, 3, null);
        BuildersKt.runBlocking(null, new C31291(refObjectRef, r9, null));
        return refObjectRef.element;
    }
    final class C31271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefIntRef result;
        int label;
        C31271(RefIntRef refIntRef, Continuation<? super C31271> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refIntRef;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31271(this.result, (Continuation<? super C31271>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj)   {
            int r6;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, null);
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
                r6 = Log.e(SalesNewViewModel.this.getTAG(), "GetCentOfUnitPriceDigit", e2);
            }
            return Boxing.boxInt(r6);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefIntRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefIntRef refIntRef, SalesNewViewModel salesNewViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refIntRef;
                this.this0 = salesNewViewModel;
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
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().getCentOfUnitPriceDigit();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "GetCentOfUnitPriceDigit"));
            }
         }
    }
    public int getCentOfUnitPriceDigit() throws InterruptedException {
        RefIntRef refIntRef = new RefIntRef();
        BuildersKt.runBlocking(null, new C31271(refIntRef, null));
        return refIntRef.element;
    }
    final class C31351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final  int customerRef;
        final  RefObjectRef<DueDate> result;
        final  SalesType salesType;
        int label;
        C31351(RefObjectRef<DueDate> refObjectRef, SalesType salesType, int r4, Continuation<? super C31351> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.salesType = salesType;
            this.customerRef = r4;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31351(this.result, this.salesType, this.customerRef, (Continuation<? super C31351>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public Object invokeSuspend(Object obj)  {
            int r9;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.salesType, this.customerRef, null);
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
                r9 = Log.e(SalesNewViewModel.this.getTAG(), "getDueDate", e2);
            }
            return Boxing.boxInt(r9);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int customerRef;
            final  RefObjectRef<DueDate> result;
            final  SalesType salesType;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<DueDate> refObjectRef, SalesNewViewModel salesNewViewModel, SalesType salesType, int r4, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.salesType = salesType;
                this.customerRef = r4;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.salesType, this.customerRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public   Object invokeSuspend(Object obj)  {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().getDueDate(this.salesType, this.customerRef);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getDueDate"));
            }
        }
    }
    public DueDate getDueDate(SalesType salesType, int r10) throws InterruptedException {
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new C31351(refObjectRef, salesType, r10, null));
        return (DueDate) refObjectRef.element;
    }
    final class C31241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefBooleanRef result;
        int label;
        C31241(RefBooleanRef refBooleanRef, Continuation<? super C31241> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31241(this.result, (Continuation<? super C31241>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int r6;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, null);
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
                r6 = Log.e(SalesNewViewModel.this.getTAG(), "eDocControlTypeBranch", e2);
            }
            return Boxing.boxInt(r6);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefBooleanRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().eDocControlTypeBranch();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "eDocControlTypeBranch"));
            }
        }
    }
    public boolean eDocControlTypeBranch() throws InterruptedException {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31241(refBooleanRef, null));
        return refBooleanRef.element;
    }
    final class C31511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  FicheType ficheType;
        final  RefBooleanRef result;
        int label;
        C31511(RefBooleanRef refBooleanRef, FicheType ficheType, Continuation<? super C31511> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.ficheType = ficheType;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31511(this.result, this.ficheType, (Continuation<? super C31511>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int r7;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, this.ficheType, null);
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
                r7 = Log.e(SalesNewViewModel.this.getTAG(), "getUseDocNoUniqueControl", e2);
            }
            return Boxing.boxInt(r7);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  FicheType ficheType;
            final  RefBooleanRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, FicheType ficheType, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
                this.ficheType = ficheType;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.ficheType, (Continuation<? super AnonymousClass1>) continuation);
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
                this.result.element = this.this0.getRepository().getUseDocNoUniqueControl(this.ficheType);
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getUseDocNoUniqueControl"));
            }
        }
    }
    public boolean getUseDocNoUniqueControl(FicheType ficheType) throws InterruptedException {
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31511(refBooleanRef, ficheType, null));
        return refBooleanRef.element;
    }
    final class C31431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefBooleanRef result;
        int label;
        C31431(RefBooleanRef refBooleanRef, Continuation<? super C31431> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31431(this.result, (Continuation<? super C31431>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int r6;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, null);
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
                r6 = Log.e(SalesNewViewModel.this.getTAG(), "getOrderChangeOffer", e2);
            }
            return Boxing.boxInt(r6);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefBooleanRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
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
                this.result.element = this.this0.getRepository().getOrderChangeOffer();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "getOrderChangeOffer"));
            }
        }
    }
    public boolean getOrderChangeOffer() throws InterruptedException {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31431(refBooleanRef, null));
        return refBooleanRef.element;
    }
    final class C31201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final  RefBooleanRef result;
        int label;
        C31201(RefBooleanRef refBooleanRef, Continuation<? super C31201> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31201(this.result, (Continuation<? super C31201>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        public Object invokeSuspend(Object obj) {
            int r6;
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, SalesNewViewModel.this, null);
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
                r6 = Log.e(SalesNewViewModel.this.getTAG(), "canApplySelectedCampaign", e2);
            }
            return Boxing.boxInt(r6);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final  RefBooleanRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
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
            public Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.result.element = this.this0.getRepository().canApplySelectedCampaign();
                return Boxing.boxInt(Log.i(this.this0.getTAG(), "canApplySelectedCampaign"));
            }
        }
    }
    public boolean canApplySelectedCampaign() throws InterruptedException {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31201(refBooleanRef, null));
        return refBooleanRef.element;
    }
    public void getUsableCampaignCards(Sales sales, SalesActivityNew.UsableCampaignCardsListener responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getUsableCampaignCards(sales, responseListener);
        Log.i(getTAG(), "getUsableCampaignCards");
    }
    public void showOnlineCampaign(Sales sales, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.showOnlineCampaign(sales, responseListener);
        Log.i(getTAG(), "showOnlineCampaign");
    }
    static final class C31371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefBooleanRef result;
        int label;
        final  SalesNewViewModel this0;
        C31371(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31371(this.result, this.this0, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefBooleanRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
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
                    this.result.element = this.this0.getRepository().firmUseEDespatch();
                    i2 = Log.i(this.this0.getTAG(), "getFirmUseEDespatch");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getFirmUseEDespatch", e2);
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
            } else {
                if (r1 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }
    public boolean getFirmUseEDespatch() throws InterruptedException {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31371(refBooleanRef, this, null));
        return refBooleanRef.element;
    }
    static final class C31281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int clRef;
        final  RefObjectRef<String> result;
        int label;
        final  SalesNewViewModel this0;
        C31281(RefObjectRef<String> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super C31281> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.clRef = r3;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31281(this.result, this.this0, this.clRef, (Continuation<? super C31281>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int clRef;
            final  RefObjectRef<String> result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<String> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.clRef = r3;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.clRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj)   {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getCustomerClCode(this.clRef);
                    i2 = Log.i(this.this0.getTAG(), "getCustomerClCode");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getCustomerClCode", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.clRef, null);
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
    public String getCustomerClCode(int r4) throws InterruptedException {
        RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = "";
        BuildersKt.runBlocking(null, new C31281(refObjectRef, this, r4, null));
        return (String) refObjectRef.element;
    }
    static final class C31451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int clRef;
        final  RefIntRef result;
        int label;
        final  SalesNewViewModel this0;
        C31451(RefIntRef refIntRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super C31451> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refIntRef;
            this.this0 = salesNewViewModel;
            this.clRef = r3;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31451(this.result, this.this0, this.clRef, (Continuation<? super C31451>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int clRef;
            final  RefIntRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefIntRef refIntRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refIntRef;
                this.this0 = salesNewViewModel;
                this.clRef = r3;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.clRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getProductPayPlanRef(this.clRef);
                    i2 = Log.i(this.this0.getTAG(), "getProductPayPlanRef");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getProductPayPlanRef", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.clRef, null);
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
    public int getProductPayPlanRef(int r4) throws InterruptedException {
        RefIntRef refIntRef = new RefIntRef();
        BuildersKt.runBlocking(null, new C31451(refIntRef, this, r4, null));
        return refIntRef.element;
    }
    static final class C31521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefBooleanRef result;
        int label;
        final  SalesNewViewModel this0;
        C31521(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super C31521> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31521(this.result, this.this0, (Continuation<? super C31521>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefBooleanRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
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
                    this.result.element = this.this0.getRepository().getVATDefaultValue();
                    i2 = Log.i(this.this0.getTAG(), "getVATDefaultValue");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getVATDefaultValue", e2);
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
    public boolean getVATDefaultValue() throws InterruptedException {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31521(refBooleanRef, this, null));
        return refBooleanRef.element;
    }
    static final class C31561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final  RefBooleanRef result;
        int label;
        final  SalesNewViewModel this0;
        C31561(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super C31561> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31561(this.result, this.this0, (Continuation<? super C31561>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefBooleanRef result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().setDeliveryDateAsToday();
                    i2 = Log.i(this.this0.getTAG(), "setDeliveryDateAsToday");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "setDeliveryDateAsToday", e2);
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
    public boolean setDeliveryDateAsToday() throws InterruptedException {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        try {
            BuildersKt.runBlocking(null, new C31561(refBooleanRef, this, null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public void getWarehouseItems(int r2, SalesActivityNew.GetAllWarehouseItemsListener responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.getWarehouseItems(r2, responseListener);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Log.i(getTAG(), "getWarehouseItems");
    }
    public void showOnlineTotal(Sales sales, ResponseListener<Sales> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.showOnlineTotal(sales, responseListener);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Log.i(getTAG(), "showOnlineTotal");
    }
    public void getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings, ResponseListener<String> responseListener) {
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        Intrinsics.checkNotNullParameter(matterSettings, "matterSettings");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.getMaxMatterNo(ficheType, matterSettings, responseListener);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Log.i(getTAG(), "getMaxMatterNo");
    }
    public void checkSalesHasExchangeRates(Sales sales, ResponseListener<String> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.checkSalesHasExchangeRates(sales, responseListener);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Log.i(getTAG(), "checkSalesHasExchangeRates");
    }
    static final class C31401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final  RefObjectRef<NEDispatchInfoModel> result;
        final  SalesType salesType;
        int label;
        final  SalesNewViewModel this0;
        C31401(RefObjectRef<NEDispatchInfoModel> refObjectRef, SalesNewViewModel salesNewViewModel, SalesType salesType, Continuation<? super C31401> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.salesType = salesType;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31401(this.result, this.this0, this.salesType, (Continuation<? super C31401>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefObjectRef<NEDispatchInfoModel> result;
            final  SalesType salesType;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<NEDispatchInfoModel> refObjectRef, SalesNewViewModel salesNewViewModel, SalesType salesType, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.salesType = salesType;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.salesType, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getLastEDespatchInfoModel(this.salesType);
                    i2 = Log.i(this.this0.getTAG(), "getLastEDespatchInfoModel");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getLastEDespatchInfoModel", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.salesType, null);
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
    public NEDispatchInfoModel getLastEDespatchInfoModel(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new C31401(refObjectRef, this, salesType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (NEDispatchInfoModel) refObjectRef.element;
    }
    static final class C31341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int clientRef;
        final  RefObjectRef<ShipAddress> result;
        int label;
        final  SalesNewViewModel this0;
        C31341(RefObjectRef<ShipAddress> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.clientRef = r3;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31341(this.result, this.this0, this.clientRef, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int clientRef;
            final  RefObjectRef<ShipAddress> result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<ShipAddress> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.clientRef = r3;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.clientRef, (Continuation<? super AnonymousClass1>) continuation);
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
                    this.result.element = this.this0.getRepository().getDefaultShipAddress(this.clientRef);
                    i2 = Log.i(this.this0.getTAG(), "getDefaultShipAddress");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getDefaultShipAddress", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.clientRef, null);
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
    public ShipAddress getDefaultShipAddress(int r4) throws InterruptedException {
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new C31341(refObjectRef, this, r4, null));
        return (ShipAddress) refObjectRef.element;
    }
    static final class C31471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final  String payPlanCode;
        final  RefObjectRef<PaymentLine> result;
        int label;
        final  SalesNewViewModel this0;
        C31471(RefObjectRef<PaymentLine> refObjectRef, SalesNewViewModel salesNewViewModel, String str, Continuation<? super C31471> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.payPlanCode = str;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31471(this.result, this.this0, this.payPlanCode, (Continuation<? super C31471>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  String payPlanCode;
            final  RefObjectRef<PaymentLine> result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<PaymentLine> refObjectRef, SalesNewViewModel salesNewViewModel, String str, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.payPlanCode = str;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.payPlanCode, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj)   {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getSalesFichePayPlanTypeCash(this.payPlanCode);
                    i2 = Log.i(this.this0.getTAG(), "getSalesFichePayPlanTypeCash");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getSalesFichePayPlanTypeCash", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.payPlanCode, null);
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
    public SalesFicheParameters getSalesFichePayPlanTypeCash(String payPlanCode) {
        Intrinsics.checkNotNullParameter(payPlanCode, "payPlanCode");
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new C31471(refObjectRef, this, payPlanCode, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (SalesFicheParameters) refObjectRef.element;
    }
    static final class C31461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int invoiceRef;
        final  ReceiptType receiptType;
        final  RefObjectRef<Cursor> result;
        int label;
        final  SalesNewViewModel this0;
        C31461(RefObjectRef<Cursor> refObjectRef, SalesNewViewModel salesNewViewModel, ReceiptType receiptType, int r4, Continuation<? super C31461> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.receiptType = receiptType;
            this.invoiceRef = r4;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31461(this.result, this.this0, this.receiptType, this.invoiceRef, (Continuation<? super C31461>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int invoiceRef;
            final  ReceiptType receiptType;
            final  RefObjectRef<Cursor> result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<Cursor> refObjectRef, SalesNewViewModel salesNewViewModel, ReceiptType receiptType, int r4, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.receiptType = receiptType;
                this.invoiceRef = r4;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.receiptType, this.invoiceRef, (Continuation<Object>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj) {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getReceiptFicheRefFromInvoiceRef(this.receiptType, this.invoiceRef);
                    i2 = Log.i(this.this0.getTAG(), "getReceiptFicheRefFromInvoiceRef");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getReceiptFicheRefFromInvoiceRef", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.receiptType, this.invoiceRef, null);
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
    public Cursor getReceiptFicheRefFromInvoiceRef(ReceiptType receiptType, int r10) {
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new C31461(refObjectRef, this, receiptType, r10, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (Cursor) refObjectRef.element;
    }
    public void getOrderAvailableAmountsFromDetailWithRefs(ArrayList<String> arrayList, SalesActivityNew.OrderAvailableAmountListener responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOrderAvailableAmountsFromDetailWithRefs(arrayList, responseListener);
        Log.i(getTAG(), "getOrderAvailableAmountsFromDetailWithRefs");
    }
    public void getOrderAvailableAmounts(ArrayList<String> arrayList, SalesActivityNew.OrderAvailableAmountListener responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOrderAvailableAmounts(arrayList, responseListener);
        Log.i(getTAG(), "getOrderAvailableAmountsFromDetailWithRefs");
    }
    public void resetPricesOnDivisionChange(Sales sales, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.resetPricesOnDivisionChange(sales, responseListener);
        Log.i(getTAG(), "resetPricesOnDivisionChange");
    }
    public void readOrderFiche(ArrayList<?> ficheRef, DataObjectType dataObjectType, Sales sales, List<?> availableAmounts, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(ficheRef, "ficheRef");
        Intrinsics.checkNotNullParameter(dataObjectType, "dataObjectType");
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(availableAmounts, "availableAmounts");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.readOrderFiche(ficheRef, dataObjectType, sales, availableAmounts, responseListener);
        Log.i(getTAG(), "readOrderFiche");
    }
    final class C31571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Context context;
        final  FicheType ficheType;
        final  String matterNo;
        int label;
        C31571(Context context, FicheType ficheType, String str, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.context = context;
            this.ficheType = ficheType;
            this.matterNo = str;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return SalesNewViewModel.this.new C31571(this.context, this.ficheType, this.matterNo, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final  Context context;
            final  FicheType ficheType;
            final  String matterNo;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(SalesNewViewModel salesNewViewModel, Context context, FicheType ficheType, String str, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.this0 = salesNewViewModel;
                this.context = context;
                this.ficheType = ficheType;
                this.matterNo = str;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this0, this.context, this.ficheType, this.matterNo, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.this0.getRepository().setNewMatter(this.context, this.ficheType, this.matterNo);
                    i2 = Log.i(this.this0.getTAG(), "setNewMatter");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "setNewMatter", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(SalesNewViewModel.this, this.context, this.ficheType, this.matterNo, null);
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
    public void setNewMatter(Context context, FicheType ficheType, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        try {
            BuildersKt.runBlocking(null, new C31571(context, ficheType, str, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void getSalesProductLinesPrice(Sales sales, List<Integer> linePositions, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(linePositions, "linePositions");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesProductLinesPrice(sales, linePositions, responseListener);
        Log.i(getTAG(), "getSalesProductLinesPrice");
    }
    public void getSalesProductLinePrice(Sales sales, int r3, WcfPriceType wcfPriceType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(wcfPriceType, "wcfPriceType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesProductLinePrice(sales, r3, wcfPriceType, responseListener);
        Log.i(getTAG(), "getSalesProductLinePrice");
    }
    public void getSalesAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(wcfPriceType, "wcfPriceType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesAllProductLinePrice(sales, wcfPriceType, responseListener);
        Log.i(getTAG(), "getSalesAllProductLinePrice");
    }
    public void getSalesDetailStockTracking(SalesDetail salesDetail, int r3, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesDetailStockTracking(salesDetail, r3, responseListener);
        Log.i(getTAG(), "getSalesDetailStockTracking");
    }
    static final class C31541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final  int cabinId;
        final  int customerRef;
        final  int localFicheRef;
        final  RefBooleanRef result;
        final  int trType;
        int label;
        final  SalesNewViewModel this0;
        C31541(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, int r3, int r4, int r5, int r6, Continuation<? super C31541> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
            this.cabinId = r3;
            this.customerRef = r4;
            this.localFicheRef = r5;
            this.trType = r6;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31541(this.result, this.this0, this.cabinId, this.customerRef, this.localFicheRef, this.trType, (Continuation<? super C31541>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final  int cabinId;
            final  int customerRef;
            final  int localFicheRef;
            final  RefBooleanRef result;
            final  int trType;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, int r3, int r4, int r5, int r6, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
                this.cabinId = r3;
                this.customerRef = r4;
                this.localFicheRef = r5;
                this.trType = r6;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.cabinId, this.customerRef, this.localFicheRef, this.trType, (Continuation<? super AnonymousClass1>) continuation);
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
                    this.result.element = this.this0.getRepository().insertCabinTrans(this.cabinId, this.customerRef, this.localFicheRef, this.trType);
                    i2 = Log.i(this.this0.getTAG(), "insertCabinTrans");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "insertCabinTrans", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.cabinId, this.customerRef, this.localFicheRef, this.trType, null);
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
    public boolean insertCabinTrans(int r11, int r12, int r13, int r14) {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        try {
            BuildersKt.runBlocking(null, new C31541(refBooleanRef, this, r11, r12, r13, r14, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    static final class C31581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
        final int cabinId;
        final int customerRef;
        final int localFicheRef;
        final RefBooleanRef result;
        final int trType;
        int label;
        final  SalesNewViewModel this0;
        C31581(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, int r3, int r4, int r5, int r6, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
            this.cabinId = r3;
            this.customerRef = r4;
            this.localFicheRef = r5;
            this.trType = r6;
        }
        public Object invokeSuspend(Object obj) {
            return null;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31581(this.result, this.this0, this.cabinId, this.customerRef, this.localFicheRef, this.trType, (Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>) continuation);
        }
        public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final  int cabinId;
            final  int customerRef;
            final  int localFicheRef;
            final  RefBooleanRef result;
            final  int trType;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, int r3, int r4, int r5, int r6, Continuation<?> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
                this.cabinId = r3;
                this.customerRef = r4;
                this.localFicheRef = r5;
                this.trType = r6;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.cabinId, this.customerRef, this.localFicheRef, this.trType, continuation);
            }
            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return (  create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public   Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().updateCabinTrans(this.cabinId, this.customerRef, this.localFicheRef, this.trType);
                    i2 = Log.i(this.this0.getTAG(), "updateCabinTrans");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "updateCabinTrans", e2);
                }
                return Boxing.boxInt(i2);
            }
        }
        public Object invokeSuspend() {
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            Object obj = null;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.cabinId, this.customerRef, this.localFicheRef, this.trType, null);
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
    public boolean updateCabinTrans(int r11, int r12, int r13, int r14) {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        try {
            BuildersKt.runBlocking(null, new C31581(refBooleanRef, this, r11, r12, r13, r14, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    static final class C31491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  RefBooleanRef result;
        final  SalesType salesType;
        int label;
        final  SalesNewViewModel this0;
        C31491(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, SalesType salesType, Continuation<? super C31491> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refBooleanRef;
            this.this0 = salesNewViewModel;
            this.salesType = salesType;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31491(this.result, this.this0, this.salesType, (Continuation<? super C31491>) continuation);
        }

        public  Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  RefBooleanRef result;
            final  SalesType salesType;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefBooleanRef refBooleanRef, SalesNewViewModel salesNewViewModel, SalesType salesType, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refBooleanRef;
                this.this0 = salesNewViewModel;
                this.salesType = salesType;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.salesType, (Continuation<? super AnonymousClass1>) continuation);
            }
            public   Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public   Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getShowWeightAndVolume(this.salesType);
                    i2 = Log.i(this.this0.getTAG(), "updateCabinTrans");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "updateCabinTrans", e2);
                }
                return Boxing.boxInt(i2);
            }
        }
        public   Object invokeSuspend(Object obj)  {
            Object obj2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher coroutineDispatcher = Dispatchers.getIO();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.salesType, null);
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
    public boolean getShowWeightAndVolume(SalesType salesType) throws InterruptedException {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        BuildersKt.runBlocking(null, new C31491(refBooleanRef, this, salesType, null));
        return refBooleanRef.element;
    }
    static final class C31481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Class<EDispatchAdditionalInfo> aClass;
        final  RefObjectRef<List<EDispatchAdditionalInfo>> result;
        final  String selection;
        final  String[] selectionArgs;
        int label;
        final  SalesNewViewModel this0;
        C31481(RefObjectRef<List<EDispatchAdditionalInfo>> refObjectRef, SalesNewViewModel salesNewViewModel, Class<EDispatchAdditionalInfo> cls, String str, String[] strArr, Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.aClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31481(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return (create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>>, Object> {
            final  Class<EDispatchAdditionalInfo> aClass;
            final  RefObjectRef<List<EDispatchAdditionalInfo>> result;
            final  String selection;
            final  String[] selectionArgs;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<List<EDispatchAdditionalInfo>> refObjectRef, SalesNewViewModel salesNewViewModel, Class<EDispatchAdditionalInfo> cls, String str, String[] strArr, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.aClass = cls;
                this.selection = str;
                this.selectionArgs = strArr;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Unit invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    RefObjectRef<List<EDispatchAdditionalInfo>> refObjectRef = this.result;
                    List<EDispatchAdditionalInfo> table = this.this0.getRepository().getISqlHelperEDespatchAdditionalInfo().getTable(this.aClass, this.selection, this.selectionArgs);
                    Intrinsics.checkNotNullExpressionValue(table, "getTable(...)");
                    refObjectRef.element = table;
                    i2 = Log.i(this.this0.getTAG(), "getServiceTypeTableFromLogoSqlHelper");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getServiceTypeTableFromLogoSqlHelper", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, null);
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
    public List<EDispatchAdditionalInfo> getServiceTypeTableFromLogoSqlHelper(Class<EDispatchAdditionalInfo> aClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(aClass, "aClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new C31481(refObjectRef, this, aClass, selection, selectionArgs, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return (List) t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    static final class C31391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Class<Specodes> aClass;
        final  RefObjectRef<List<Specodes>> result;
        final  String selection;
        final  String[] selectionArgs;
        int label;
        final  SalesNewViewModel this0;
        C31391(RefObjectRef<List<Specodes>> refObjectRef, SalesNewViewModel salesNewViewModel, Class<Specodes> cls, String str, String[] strArr, Continuation<? super C31391> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.aClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31391(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, (Continuation<? super C31391>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<Integer>>, Object> {
            final  Class<Specodes> aClass;
            final  RefObjectRef<List<Specodes>> result;
            final  String selection;
            final  String[] selectionArgs;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<List<Specodes>> refObjectRef, SalesNewViewModel salesNewViewModel, Class<Specodes> cls, String str, String[] strArr, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.aClass = cls;
                this.selection = str;
                this.selectionArgs = strArr;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj)   {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    RefObjectRef<List<Specodes>> refObjectRef = this.result;
                    ISqlHelper<Specodes> iSqlHelperSpecodes = this.this0.getRepository().getISqlHelperSpecodes();
                    refObjectRef.element = iSqlHelperSpecodes != null ? iSqlHelperSpecodes.getTable(this.aClass, this.selection, this.selectionArgs) : null;
                    i2 = Log.i(this.this0.getTAG(), "getISqlHelperSpecodes");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getISqlHelperSpecodes", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, null);
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
    public List<Specodes> getISqlHelperSpecodes(Class<Specodes> aClass, String selection, String[] selectionArgs) throws InterruptedException {
        Intrinsics.checkNotNullParameter(aClass, "aClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        RefObjectRef<List<Specodes>> refObjectRef = new RefObjectRef<>();
        try {
            BuildersKt.runBlocking(null, new C31391(refObjectRef, this, aClass, selection, selectionArgs, null));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return refObjectRef.element;
    }
    static final class C31381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  Class<Branch> aClass;
        final  RefObjectRef<List<Branch>> result;
        final  String selection;
        final  String[] selectionArgs;
        int label;
        final  SalesNewViewModel this0;
        C31381(RefObjectRef<List<Branch>> refObjectRef, SalesNewViewModel salesNewViewModel, Class<Branch> cls, String str, String[] strArr, Continuation<? super C31381> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.aClass = cls;
            this.selection = str;
            this.selectionArgs = strArr;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31381(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, (Continuation<? super C31381>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  Class<Branch> aClass;
            final  RefObjectRef<List<Branch>> result;
            final  String selection;
            final  String[] selectionArgs;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<List<Branch>> refObjectRef, SalesNewViewModel salesNewViewModel, Class<Branch> cls, String str, String[] strArr, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.aClass = cls;
                this.selection = str;
                this.selectionArgs = strArr;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
            public Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    RefObjectRef<List<Branch>> refObjectRef = this.result;
                    ISqlHelper<Branch> iSqlHelperBranch = this.this0.getRepository().getISqlHelperBranch();
                    refObjectRef.element = iSqlHelperBranch != null ? iSqlHelperBranch.getTable(this.aClass, this.selection, this.selectionArgs) : null;
                    i2 = Log.i(this.this0.getTAG(), "getISqlHelperBranch");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getISqlHelperBranch", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.aClass, this.selection, this.selectionArgs, null);
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
    public List<Branch> getISqlHelperBranch(Class<Branch> aClass, String selection, String[] selectionArgs) throws InterruptedException {
        Intrinsics.checkNotNullParameter(aClass, "aClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new C31381(refObjectRef, this, aClass, selection, selectionArgs, null));
        return (List) refObjectRef.element;
    }
    static final class C31301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final  int clRef;
        final  RefObjectRef<ClCard> result;
        int label;
        final  SalesNewViewModel this0;

        
        C31301(RefObjectRef<ClCard> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super C31301> continuation) {
            super(2, (Continuation<Object>) continuation);
            this.result = refObjectRef;
            this.this0 = salesNewViewModel;
            this.clRef = r3;
        }
        public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
            return new C31301(this.result, this.this0, this.clRef, (Continuation<? super C31301>) continuation);
        }
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
            try {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
            final  int clRef;
            final  RefObjectRef<ClCard> result;
            int label;
            final  SalesNewViewModel this0;
            AnonymousClass1(RefObjectRef<ClCard> refObjectRef, SalesNewViewModel salesNewViewModel, int r3, Continuation<? super AnonymousClass1> continuation) {
                super(2, (Continuation<Object>) continuation);
                this.result = refObjectRef;
                this.this0 = salesNewViewModel;
                this.clRef = r3;
            }
            public _FileSystemKtcommonListRecursively1 create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.result, this.this0, this.clRef, (Continuation<? super AnonymousClass1>) continuation);
            }
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> continuation) {
                try {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invokeSuspend(Object obj)  {
                int i2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                try {
                    this.result.element = this.this0.getRepository().getCustomerInfo(this.clRef);
                    i2 = Log.i(this.this0.getTAG(), "getCustomerInfo");
                } catch (Exception e2) {
                    i2 = Log.e(this.this0.getTAG(), "getCustomerInfo", e2);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.result, this.this0, this.clRef, null);
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
    public ClCard getCustomerInfo(int r4) throws InterruptedException {
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new C31301(refObjectRef, this, r4, null));
        return (ClCard) refObjectRef.element;
    }
    public void saveSalesFicheFromSqlManager(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.saveSalesFicheFromSqlManager(sales, salesType, responseListener);
        Log.i(getTAG(), "saveSalesFicheFromSqlManager");
    }
    public void updateSalesFicheFromSqlManager(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.updateSalesFicheFromSqlManager(sales, salesType, responseListener);
        Log.i(getTAG(), "updateSalesFicheFromSqlManager");
    }
    public void getCustomerRiskCalculate(int r2, ResponseListener<List<ClRisk>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCustomerRiskCalculate(r2, responseListener);
        Log.i(getTAG(), "getCustomerRiskCalculate");
    }
    public boolean isSalesPersonWarrantyCodePriceFirst() {
        Log.i(getTAG(), "isSalesPersonWarrantyCodePriceFirst");
        return this.repository.isSalesPersonWarrantyCodePriceFirst();
    }
}
