package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.Customer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
public final class CustomerRouteRepository extends BaseCustomerRepository {
    public Disposable getRouteListFromSqlManager(String str, ResponseListener<ArrayList<Customer>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable routeList = getSqlManager().getRouteList(str, responseListener);
        Intrinsics.checkNotNullExpressionValue(routeList, "getRouteList(...)");
        return routeList;
    }
}
