package com.proje.mobilesales.features.tools.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.tools.repository.IAverageCalcRepository;
import kotlin.jvm.internal.Intrinsics;

public final class AverageCalcViewModel extends BaseViewModel {
    private final IAverageCalcRepository repository;
    private final String tag;
    public AverageCalcViewModel(final IAverageCalcRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "AverageCalcViewModel";
    }
}
