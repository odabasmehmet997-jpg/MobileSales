package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.core.base.BaseRepository;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import java.lang.ref.WeakReference;
import java.util.List;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CustomerRiskInfoUtils.kt */

public final class CustomerRiskInfoUtils {
    public static final CustomerRiskInfoUtils INSTANCE = new CustomerRiskInfoUtils();
    private static final BaseRepository repository;
    private static final BaseViewModel viewModel;

    private CustomerRiskInfoUtils() {
    }

    static {
        BaseRepository baseRepository = new BaseRepository(baseRepositorybaseErp2);
        repository = baseRepository;
        viewModel = new BaseViewModel(baseRepository);
    }

    /* compiled from: CustomerRiskInfoUtils.kt */
    public static final class CommonRiskResponseListener<T> implements ResponseListener<List<? extends ClRisk>> {
        private final WeakReference<T> mActivity;
        private final Function2<T, String, Unit> onErrorHandler;
        private final Function2<T, List<ClRisk>, Unit> onResponseHandler;

        
        public CommonRiskResponseListener(T t, Function2<? super T, ? super List<ClRisk>, Unit> onResponseHandler, Function2<? super T, ? super String, Unit> onErrorHandler) {
            Intrinsics.checkNotNullParameter(onResponseHandler, "onResponseHandler");
            Intrinsics.checkNotNullParameter(onErrorHandler, "onErrorHandler");
            this.onResponseHandler = onResponseHandler;
            this.onErrorHandler = onErrorHandler;
            this.mActivity = new WeakReference<>(t);
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(PrintSlipModel list) {
            onResponse2((List<ClRisk>) list);
        }

        /* renamed from: onResponse, reason: avoid collision after fix types in other method */
        public void onResponse2(List<ClRisk> list) {
            T t = this.mActivity.get();
            if (t != null) {
                this.onResponseHandler.invoke(t, list);
            }
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            T t = this.mActivity.get();
            if (t != null) {
                this.onErrorHandler.invoke(t, errorMessage);
            }
        }
    }

    public List<ClRisk> getClRiskList(String clCode, int i2) {
        Intrinsics.checkNotNullParameter(clCode, "clCode");
        BaseViewModel baseViewModel = viewModel;
        ISqlHelper logoSqlHelper = baseViewModel.getBaseErp().getLogoSqlHelper();
        ErpType erpType = baseViewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        String str = erpType == erpType2 ? "CODE=?" : "CLREF=?";
        if (baseViewModel.erpType() != erpType2) {
            clCode = String.valueOf(i2);
        }
        List<ClRisk> table = logoSqlHelper.getTable(ClRisk.class, str, new String[]{clCode});
        Intrinsics.checkNotNull(table, "null cannot be cast to non-null type kotlin.collections.List<com.proje.mobilesales.features.customer.model.database.ClRisk>");
        return table;
    }
}
