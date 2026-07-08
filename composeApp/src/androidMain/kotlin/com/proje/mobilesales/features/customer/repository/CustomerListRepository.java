package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.Customer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerListRepository extends BaseCustomerRepository implements ICustomerListRepository {
    public Disposable getCustomerListFromSqlManager(String str, ResponseListener<ArrayList<Customer>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable customerList = getSqlManager().getCustomerList(str, responseListener);
        Intrinsics.checkNotNullExpressionValue(customerList, "getCustomerList(...)");
        return customerList;
    }
}
