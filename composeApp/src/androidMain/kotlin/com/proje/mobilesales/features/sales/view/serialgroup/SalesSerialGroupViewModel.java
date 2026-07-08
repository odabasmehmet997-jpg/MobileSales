package com.proje.mobilesales.features.sales.view.serialgroup;

import android.util.Log;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.model.CheckSeriGroup;
import com.proje.mobilesales.features.sales.repository.SalesSerialGroupRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class SalesSerialGroupViewModel extends BaseSalesViewModel {
    private final SalesSerialGroupRepository repository;

    public SalesSerialGroupViewModel(SalesSerialGroupRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
    public SalesSerialGroupRepository getRepository() {
        return this.repository;
    }
    public void getCheckSeriGroup(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2, ResponseListener<ArrayList<CheckSeriGroup>> responseListener) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            this.repository.getCheckSeriGroup(i2, i3, salesType, z, i4, z2, responseListener);
        } catch (Exception e) {
            responseListener.onFailure(e);
        }
        Log.i(getTAG(), "getCheckSeriGroup");
    }
}
