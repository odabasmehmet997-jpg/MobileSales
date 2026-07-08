package com.proje.mobilesales.features.sales.view.mans;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.sales.model.SalesMan;
import com.proje.mobilesales.features.sales.repository.SalesMansRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class SalesMansViewModel extends BaseSalesViewModel {
    private final SalesMansRepository repository;

    public SalesMansViewModel(SalesMansRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public   SalesMansRepository getRepository() {
        return this.repository;
    }

    public   void getOnlineSalesMans(ResponseListener<ArrayList<SalesMan>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOnlineSalesMans(responseListener);
        Log.i(getTAG(), "getOnlineSalesMans");
    }
}
