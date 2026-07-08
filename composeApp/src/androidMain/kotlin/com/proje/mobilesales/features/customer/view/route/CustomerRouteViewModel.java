package com.proje.mobilesales.features.customer.view.route;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.repository.CustomerRouteRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerRouteViewModel extends BaseCustomerViewModel {
    private final String TAG;
    private final CustomerRouteRepository repository;

    public CustomerRouteViewModel(final CustomerRouteRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        TAG = "CustomerRouteViewModel";
    }

    public Disposable getRouteList(final String str, final ResponseListener<ArrayList<Customer>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        final Disposable routeListFromSqlManager = repository.getRouteListFromSqlManager(str, responseListener);
        Log.i(TAG, "getRouteList");
        return routeListFromSqlManager;
    }
}
