package com.proje.mobilesales.features.collections.prefs.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.collections.prefs.repository.ISalesChequeAndDeedDetailRepository;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: SalesChequeAndDeedDetailViewModel.kt */

public final class SalesChequeAndDeedDetailViewModel extends BaseViewModel {
    private final ISalesChequeAndDeedDetailRepository repository;
    private final String tag;

    
    public SalesChequeAndDeedDetailViewModel(final ISalesChequeAndDeedDetailRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "SalesChequeAndDeedDetailViewModel";
    }
}
