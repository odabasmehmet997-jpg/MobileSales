package com.proje.mobilesales.features.gpsinfo.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.gpsinfo.repository.IGpsInfoRepository;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class GpsInfoViewModel extends BaseViewModel {
    private final IGpsInfoRepository repository;
    private final String tag;

    public GpsInfoViewModel(IGpsInfoRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        this.tag = "GpsInfoViewModel";
    }

    public void saveCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(custGpsInfo, "custGpsInfo");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.saveCustomerLocation(custGpsInfo, responseListener);
    }

    public void updateCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(custGpsInfo, "custGpsInfo");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.updateCustomerLocation(custGpsInfo, responseListener);
    }

    public ArrayList<CustGpsInfo> getCustomerLocations() {
        return this.repository.getCustomerLocations();
    }

    public boolean getCustomerGpsEditMode() {
        return this.repository.getCustomerGpsEditMode();
    }
}
