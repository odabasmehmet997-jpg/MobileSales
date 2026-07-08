package com.proje.mobilesales.features.customer.view.general;

import android.annotation.SuppressLint;
import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.customer.repository.IBaseCustomerRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import io.reactivex.disposables.Disposable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt__BuildersKt;


/* compiled from: CustomerGeneralViewModel.kt */

public final class CustomerGeneralViewModel extends BaseCustomerViewModel {
    private final String TAG;
    private final IBaseCustomerRepository repository;

    
    public CustomerGeneralViewModel(IBaseCustomerRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.TAG = "CustomerGeneralViewModel";
    }

    @SuppressLint({"LongLogTag"})
    public Disposable getCustomerSqlManager(int i2, ResponseListener<CustomerNew> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable customer = this.repository.getCustomer(i2, responseListener);
        Log.i(this.TAG, "GetCustomerSqlManager");
        return customer;
    }

    
    @SuppressLint({"LongLogTag"})
    public String getCustomerInCharge(String str) {
        RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = "";
        BuildersKt.runBlockingdefault(null, new CustomerGeneralViewModelgetCustomerInCharge1(this, refObjectRef, str, null), 1, null);
        return (String) refObjectRef.element;
    }

    public void getCustomerRiskCalculate(int i2, ResponseListener<List<ClRisk>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCustomerRiskCalculate(i2, responseListener);
        Log.i(this.TAG, "getCustomerRiskCalculate");
    }
}
