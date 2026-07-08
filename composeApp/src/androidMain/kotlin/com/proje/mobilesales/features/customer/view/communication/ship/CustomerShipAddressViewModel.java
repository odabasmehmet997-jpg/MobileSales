package com.proje.mobilesales.features.customer.view.communication.ship;

import android.annotation.SuppressLint;
import android.content.Context;
import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref ;
import kotlinx.coroutines.BuildersKt ;
 
public final class CustomerShipAddressViewModel extends BaseCustomerViewModel {
    final String TAG;
    final BaseCustomerRepository repository;

    public CustomerShipAddressViewModel(BaseCustomerRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.TAG = "CustomerShipAddressViewModel";
    }
 
    @SuppressLint({"LongLogTag"})
    public String getCustomerShipAddressSql(Context context, int i2, String str) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking(null, new CustomerShipAddressViewModelgetCustomerShipAddressSql1(this, refObjectRef, context, i2, str, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }
}
