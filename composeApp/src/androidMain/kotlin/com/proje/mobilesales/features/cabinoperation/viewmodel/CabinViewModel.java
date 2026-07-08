package com.proje.mobilesales.features.cabinoperation.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.repository.ICabinRepository;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class CabinViewModel extends BaseViewModel {
    private final ICabinRepository repository;
    private final String tag;

    public CabinViewModel(ICabinRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.tag = "CabinViewModel";
    }

    @SuppressLint({"LongLogTag"})
    public void getCabinList(String query, ResponseListener<ArrayList<Cabin>> responseListener) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getCabinList(query, responseListener);
        Log.i(this.tag, "GetCabinList");
    }

    @SuppressLint({"LongLogTag"})
    public void deliverCabinToCustomer(Cabin cabin, int i2) {
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        this.repository.deliverCabinToCustomer(cabin, i2);
        Log.i(this.tag, "DeliverCabinToCustomer");
    }

    @SuppressLint({"LongLogTag"})
    public void deliverCabinToStorage(Cabin cabin, int i2) {
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        this.repository.deliverCabinToStorage(cabin, i2);
        Log.i(this.tag, "DeliverCabinToStorage");
    }

    @SuppressLint({"LongLogTag"})
    public void receiveCabinFromCustomer(Cabin cabin, int i2, int i3) {
        Intrinsics.checkNotNullParameter(cabin, "cabin");
        this.repository.receiveCabinFromCustomer(cabin, i2, i3);
        Log.i(this.tag, "ReceiveCabinFromCustomer");
    }
}
