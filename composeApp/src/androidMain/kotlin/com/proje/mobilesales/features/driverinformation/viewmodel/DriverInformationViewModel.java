package com.proje.mobilesales.features.driverinformation.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.driverinformation.repository.IDriverInformationRepository;
import kotlin.jvm.internal.Intrinsics;

public final class DriverInformationViewModel extends BaseViewModel {
    private final IDriverInformationRepository repository;
    private final String tag;

    public DriverInformationViewModel(final IDriverInformationRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "DriverInformationViewModel";
    }
}
