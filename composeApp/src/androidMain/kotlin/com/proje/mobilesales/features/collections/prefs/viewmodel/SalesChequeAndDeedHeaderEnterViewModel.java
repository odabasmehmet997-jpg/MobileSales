package com.proje.mobilesales.features.collections.prefs.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.collections.prefs.repository.ISalesChequeAndDeedHeaderEnterRepository;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: SalesChequeAndDeedHeaderEnterViewModel.kt */

public final class SalesChequeAndDeedHeaderEnterViewModel extends BaseViewModel {
    private final ISalesChequeAndDeedHeaderEnterRepository repository;
    private final String tag;

    
    public SalesChequeAndDeedHeaderEnterViewModel(final ISalesChequeAndDeedHeaderEnterRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "SalesChequeAndDeedHeaderEnterViewModel";
    }
}
