package com.proje.mobilesales.features.tools.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.tools.repository.IOtherMenuRepository;
import kotlin.jvm.internal.Intrinsics;

public final class OtherMenuViewModel extends BaseViewModel {
    private final IOtherMenuRepository repository;
    private final String tag;
    public OtherMenuViewModel(final IOtherMenuRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "OtherMenuViewModel";
    }
    public boolean isGpsLocationInfo() {
        return repository.isGpsLocationInfo();
    }
    public boolean isWorkInfo() {
        return repository.isWorkInfo();
    }
    public boolean isInvoiceAvgCalc() {
        return repository.isInvoiceAvgCalc();
    }
}
