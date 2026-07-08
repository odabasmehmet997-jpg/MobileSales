package com.proje.mobilesales.features.driverinformation.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.Customer;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.driverinformation.repository.IEDispatchAdditionalInformationRepository;
import kotlin.jvm.internal.Intrinsics;

public final class EDispatchAdditionalInformationViewModel extends BaseViewModel {
    private final IEDispatchAdditionalInformationRepository repository;
    private final String tag;
    public EDispatchAdditionalInformationViewModel(final IEDispatchAdditionalInformationRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "EDispatchAdditionalInformationViewModel";
    }

    public void getEDispatchAdditionalInfo(final Customer customer, final ResponseListener<EDispatchAdditionalInfo> responseListener) {
        Intrinsics.checkNotNullParameter(customer, "customer");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.getEDispatchAdditionalInfo(customer, responseListener);
    }
}
