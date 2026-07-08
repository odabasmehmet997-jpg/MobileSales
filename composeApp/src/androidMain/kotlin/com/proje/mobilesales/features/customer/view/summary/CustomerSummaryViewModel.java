package com.proje.mobilesales.features.customer.view.summary;

import android.annotation.SuppressLint;
import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.CustomerBalance;
import com.proje.mobilesales.features.customer.repository.CustomerSummaryRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import com.proje.mobilesales.features.model.MonthlySales;
import com.proje.mobilesales.features.model.TopProduct;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerSummaryViewModel extends BaseCustomerViewModel {
    private final String TAG;
    private final CustomerSummaryRepository repository;

    public CustomerSummaryViewModel(CustomerSummaryRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.TAG = "CustomerSummaryViewModel";
    }

    @SuppressLint({"LongLogTag"})
    public void getCustomerSummaryTotalBalance(int i2, ResponseListener<List<CustomerBalance>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCustomerSummaryTotalBalance(i2, responseListener);
        Log.i(this.TAG, "GetCustomerSummaryTotalBalance");
    }

    @SuppressLint({"LongLogTag"})
    public void getCustomerSummaryTopTenProduct(int i2, ResponseListener<List<TopProduct>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCustomerSummaryTopTenProduct(i2, responseListener);
        Log.i(this.TAG, "GetCustomerSummaryTopTenProduct");
    }

    @SuppressLint({"LongLogTag"})
    public void getCustomerSummarySales(int i2, boolean z, ResponseListener<List<MonthlySales>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCustomerSummarySales(i2, z, responseListener);
        Log.i(this.TAG, "GetCustomerSummarySales");
    }

    @SuppressLint({"LongLogTag"})
    public void getLoadCurrencyBalances(int i2, String str, String str2, ResponseListener<?> responseListener) {
        this.repository.getLoadCurrencyBalances(i2, str, str2, responseListener);
        Log.i(this.TAG, "GetLoadCurrencyBalances");
    }
}
