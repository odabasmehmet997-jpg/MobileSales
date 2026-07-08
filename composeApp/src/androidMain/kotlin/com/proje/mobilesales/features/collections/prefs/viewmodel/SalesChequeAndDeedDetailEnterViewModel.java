package com.proje.mobilesales.features.collections.prefs.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.collections.prefs.repository.ISalesChequeAndDeedDetailEnterRepository;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: SalesChequeAndDeedDetailEnterViewModel.kt */

public final class SalesChequeAndDeedDetailEnterViewModel extends BaseViewModel {
    private final ISalesChequeAndDeedDetailEnterRepository repository;
    private final String tag;

    
    public SalesChequeAndDeedDetailEnterViewModel(final ISalesChequeAndDeedDetailEnterRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "SalesChequeAndDeedDetailEnterViewModel";
    }
}
