package com.proje.mobilesales.features.customer.view.risk;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.CustomerRisk;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerRiskViewModel extends BaseCustomerViewModel {
    private final String TAG;
    private final BaseCustomerRepository repository;
    public CustomerRiskViewModel(final BaseCustomerRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        TAG = "CustomerRiskViewModel";
    }

    public void getCustomerRiskInfo(final int i2, final ResponseListener<List<CustomerRisk>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.getCustomerRiskInfo(i2, responseListener);
        Log.i(TAG, "getCustomerRiskInfo");
    }
}
