package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.Customer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
public interface ICustomerListRepository extends IBaseCustomerRepository {
    Disposable getCustomerListFromSqlManager(String str, ResponseListener<ArrayList<Customer>> responseListener);
}
