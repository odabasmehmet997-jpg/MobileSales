package com.proje.mobilesales.features.customer.view.list;

import android.content.Context;
import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.utils.CustomerFilter;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.repository.ICustomerListRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import com.proje.mobilesales.features.model.Resource;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt__BuildersKt;


/* compiled from: CustomerListViewModel.kt */

public final class CustomerListViewModel extends BaseCustomerViewModel {
    final String TAG;
    final ICustomerListRepository repository;

    
    public CustomerListViewModel(final ICustomerListRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        TAG = "CustomerListViewModel";
    }

    public Disposable getCustomerList(final String str, final ResponseListener<ArrayList<Customer>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        final Disposable customerListFromSqlManager = repository.getCustomerListFromSqlManager(str, responseListener);
        Log.i(TAG, "getCustomerList");
        return customerListFromSqlManager;
    }

    public void getOnlineShipCustomer(final String str, final ResponseListener<ArrayList<ClCard>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.getOnlineShipCustomer(str, responseListener);
        Log.i(TAG, "getOnlineShipCustomer");
    }

    public void getListAllCustomersOnline(final String str, final ResponseListener<ArrayList<ClCard>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.getListAllCustomersOnline(str, responseListener);
        Log.i(TAG, "getListAllCustomersOnline");
    }

    
    public Observable<Resource<Boolean>> getCustomerHasDailyOperation(final int i2) {
        final RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlockingdefault(null, new CustomerListViewModelgetCustomerHasDailyOperation1(this, refObjectRef, i2, null), 1, null);
        final T t = refObjectRef.element;
        if (0 != t) {
            return (Observable) t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }

    public void getSaveCustomerSortType(final int i2) {
        BuildersKt.runBlockingdefault(null, new CustomerListViewModelgetSaveCustomerSortType1(this, i2, null), 1, null);
    }

    public void getSaveClCardLastTransDate() {
        BuildersKt.runBlockingdefault(null, new CustomerListViewModelgetSaveClCardLastTransDate1(this, null), 1, null);
    }

    
    public String getCustomerListSql(final Context context, final CustomerFilter customerFilter) {
        Intrinsics.checkNotNullParameter(customerFilter, "customerFilter");
        final RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = "";
        BuildersKt.runBlockingdefault(null, new CustomerListViewModelgetCustomerListSql1(this, refObjectRef, context, customerFilter, null), 1, null);
        return (String) refObjectRef.element;
    }

    public void getOnlineUpdateCustomer(final CustomerListFragmentcustomerOnlineUpdateListener1 responseListener, final int i2) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.getOnlineUpdateCustomer(responseListener, i2);
        Log.i(TAG, "getOnlineUpdateCustomer");
    }
}
