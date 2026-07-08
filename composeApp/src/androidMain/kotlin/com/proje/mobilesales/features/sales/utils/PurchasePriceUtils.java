package com.proje.mobilesales.features.sales.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.model.LastPurchaseInfoSqlParams;
import com.proje.mobilesales.features.model.PurchasePriceInfo;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.repository.BaseSalesRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;

import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
 
public final class PurchasePriceUtils {
    public static final PurchasePriceUtils INSTANCE = new PurchasePriceUtils();
    private static ProgressDialogBuilder<?> mProgressDialogBuilder;
    private static double purchasePrice;
    private static final BaseSalesRepository repository;
    public static Sales sales;
    private static final BaseSalesViewModel viewModel;
    final class C30391 extends ContinuationImpl {
        Object L0;
        int label;
         Object result;

        C30391(Continuation<? super C30391> continuation) {
            super((Continuation<Object>) continuation);
        } 
        public Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            try {
                return PurchasePriceUtils.this.getLastPurchasePrice(null, this);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
    private PurchasePriceUtils() {
    }
    static {
        BaseSalesRepository baseSalesRepository = new BaseSalesRepository();
        repository = baseSalesRepository;
        viewModel = new BaseSalesViewModel(baseSalesRepository);
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(double d2) {
        purchasePrice = d2;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        return mProgressDialogBuilder;
    }
    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        mProgressDialogBuilder = progressDialogBuilder;
    }
    public void initialize(Sales sales2) {
        Intrinsics.checkNotNullParameter(sales2, "sales");
        Activity activity = ContextUtils.getmActivity();
        if (activity == null || !(activity instanceof BaseInjectableActivity)) {
            return;
        }
        mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) activity);
        sales = sales2;
    }
    static final class C30402 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        C30402(Continuation<?> continuation) {
            super(2, (Continuation<Object>) continuation);
        }
        public Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ProgressDialogBuilder<?> mProgressDialogBuilder = PurchasePriceUtils.INSTANCE.getMProgressDialogBuilder();
            if (mProgressDialogBuilder == null) {
                return Unit.INSTANCE;
            }
            if (!mProgressDialogBuilder.isShowing()) {
                Context context = ContextUtils.getmContext();
                Intrinsics.checkNotNull(context);
                mProgressDialogBuilder.setMessage(context.getString(R.string.str_get_last_purchase_info)).show();
            }
            return Unit.INSTANCE;
        }

        @Override
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }
    public Unit getLastPurchasePrice(SalesDetail salesDetail, Continuation<? super Double> continuation) throws Throwable {
        C30391 c30391 = null;
        if (continuation instanceof C30391) {
            c30391 = (C30391) continuation;
            int r1 = c30391.label;
            if ((r1 & Integer.MIN_VALUE) != 0) {
                c30391.label = r1 - Integer.MIN_VALUE;
            } else {
                c30391 = new C30391((Continuation<? super C30391>) continuation);
            }
        }
        Object orThrow = c30391.result;
        Object obj = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = c30391.label;
        if (r12 == 0) {
            ResultKt.throwOnFailure(orThrow);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C30402 c30402 = new C30402(null);
            c30391.L0 = salesDetail;
            c30391.label = 1;
            if (BuildersKt.withContext(main, c30402, c30391) == obj) {
                return (Unit) obj;
            }
        } else {
            if (r12 != 1) {
                if (r12 != 2) {
                    if (r12 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object obj2 = c30391.L0;
                    ResultKt.throwOnFailure(orThrow);
                    return (Unit) obj2;
                }
                ResultKt.throwOnFailure(orThrow);
                ((Number) orThrow).doubleValue();
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                PurchasePriceUtilsgetLastPurchasePrice41 purchasePriceUtilsgetLastPurchasePrice41 = new PurchasePriceUtilsgetLastPurchasePrice41(null);
                c30391.L0 = orThrow;
                c30391.label = 3;
                return (Unit) (BuildersKt.withContext(main2, purchasePriceUtilsgetLastPurchasePrice41, c30391) != obj ? obj : orThrow);
            }
            salesDetail = (SalesDetail) c30391.L0;
            ResultKt.throwOnFailure(orThrow);
        }
        int itemRef = salesDetail.getItemRef();
        String code = salesDetail.getCode();
        Intrinsics.checkNotNull(code);
        LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams = new LastPurchaseInfoSqlParams(itemRef, code, Boxing.boxInt(salesDetail.getVariant().getLogicalRef()), TextUtils.isEmpty(salesDetail.getVariant().getCode()) ? "" : salesDetail.getVariant().getCode(), Boxing.boxInt(salesDetail.getWareHouse().getLogicalRef()));
        c30391.L0 = lastPurchaseInfoSqlParams;
        c30391.label = 2;
        final SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(c30391));
        viewModel.getBaseErp().getLastPurchaseInfo(lastPurchaseInfoSqlParams, new LastPurchaseInfoListener(new Function1<List<? extends PurchasePriceInfo>, Unit>() {
            public Unit invoke(Object p1) {
                invoke2((List<? extends PurchasePriceInfo>) p1);
                return Unit.INSTANCE;
            }
        }, new Function1<Object, Unit>() {
            public  Unit invoke(Object p1) {
                invoke2((String) p1);
                return Unit.INSTANCE;
            }
            public void invoke2(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d("checkLastPurchasePrice", "onError: " + errorMessage);
                Continuation<Double> continuation2 = safeContinuation;
                Result.Companion companion = Result.Companion;

            }
        }));
        orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(c30391);
        }
        if (orThrow == obj) {
            return (Unit) obj;
        }
        ((Number) orThrow).doubleValue();
        MainCoroutineDispatcher main22 = Dispatchers.getMain();
        PurchasePriceUtilsgetLastPurchasePrice41 purchasePriceUtilsgetLastPurchasePrice412 = new PurchasePriceUtilsgetLastPurchasePrice41(null);
        c30391.L0 = orThrow;
        c30391.label = 3;
        if (BuildersKt.withContext(main22, purchasePriceUtilsgetLastPurchasePrice412, c30391) != obj) {
        }
        return (Unit) orThrow;
    }
    private void invoke2(List<? extends PurchasePriceInfo> p1) {
    }
    private Object impl(Double aDouble) {
        return null;
    }

    private record LastPurchaseInfoListener(Function1<List<? extends PurchasePriceInfo>, Unit> onSuccessHandler,
                                            Function1<String, Unit> onErrorHandler) implements ResponseListener<List<? extends PurchasePriceInfo>> {
            private LastPurchaseInfoListener(Function1<? super List<? extends PurchasePriceInfo>, Unit> onSuccessHandler, Function1<? super String, Unit> onErrorHandler) {
                Intrinsics.checkNotNullParameter(onSuccessHandler, "onSuccessHandler");
                Intrinsics.checkNotNullParameter(onErrorHandler, "onErrorHandler");
                this.onSuccessHandler = (Function1<List<? extends PurchasePriceInfo>, Unit>) onSuccessHandler;
                this.onErrorHandler = (Function1<String, Unit>) onErrorHandler;
            }

        public void onResponse(PrintSlipModel list) {
                this.onSuccessHandler.invoke(list);
            }

        public void onFailure(Throwable throwable) {
            }

            public void onError(String errorMessage) {
                Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                Log.d(MobileSales.TAG, "onError: " + errorMessage);
                this.onErrorHandler.invoke(errorMessage);
            }
        }
    public double onLastPurchaseInfoResult(List<? extends PurchasePriceInfo> list, String str) {
        double price;
        if (!TextUtils.isEmpty(str)) {
            Log.e(MobileSales.TAG, str);
        }
        if (list == null || list.isEmpty()) {
            price = 0.0d;
        } else if (list.get(0).getPrCurr() != 0) {
            price = list.get(0).getPrPrice();
        } else {
            price = list.get(0).getPrice();
        }
        purchasePrice = price;
        return price;
    }
    public double getSalesDetailPrice(SalesDetail salesDetail) {
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        Double dValueOf = Double.valueOf(salesDetail.getPrice().getDefinitionDouble());
        if (dValueOf.doubleValue() == 0.0d) {
            dValueOf = null;
        }
        return dValueOf != null ? dValueOf.doubleValue() : salesDetail.getUsePrice();
    }
}
