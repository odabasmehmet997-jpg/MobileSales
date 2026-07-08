package com.proje.mobilesales.features.customer.view.info;

import com.proje.mobilesales.features.customer.repository.BaseCustomerRepository;
import com.proje.mobilesales.features.customer.view.BaseCustomerViewModel;
import kotlin.jvm.internal.Intrinsics;

import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt  ;

public final class CustomerInfoViewModel extends BaseCustomerViewModel {
    private final String TAG = "CustomerInfoViewModel";
    private final BaseCustomerRepository repository;

    public CustomerInfoViewModel(BaseCustomerRepository baseCustomerRepository) {
        super (baseCustomerRepository);
        Intrinsics.checkNotNullParameter (baseCustomerRepository, "repository");
        this.repository = baseCustomerRepository;
    }

    public double getCurrRateWithoutDefaultValue(int i) {
        Ref.DoubleRef refDoubleRef = new Ref.DoubleRef ();
        try {
            Object unused = BuildersKt.runBlocking(null, new CustomerInfoViewModelgetCurrRateWithoutDefaultValue1 (this, refDoubleRef, i, null));
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refDoubleRef.element;
    }

    public int getClCardCurrency(int i) {
        Ref.IntRef refIntRef = new Ref.IntRef ();
        try {
            Object unused = BuildersKt.runBlocking(null, new CustomerInfoViewModelgetClCardCurrency1 (this, refIntRef, i, null));
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refIntRef.element;
    }

    public String getCurrCode(int i) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef ();
        refObjectRef.element = "";
        try {
            Object unused = BuildersKt.runBlocking(null, new CustomerInfoViewModelgetCurrCode1 (this, refObjectRef, i, null));
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }
}
