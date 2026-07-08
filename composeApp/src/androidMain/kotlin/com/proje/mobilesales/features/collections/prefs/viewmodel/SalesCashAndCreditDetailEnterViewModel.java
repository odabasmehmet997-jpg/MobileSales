package com.proje.mobilesales.features.collections.prefs.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.collections.prefs.repository.ISalesCashAndCreditDetailEnterRepository;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: SalesCashAndCreditDetailEnterViewModel.kt */

public final class SalesCashAndCreditDetailEnterViewModel extends BaseViewModel {
    private final ISalesCashAndCreditDetailEnterRepository repository;
    private final String tag;

    
    public SalesCashAndCreditDetailEnterViewModel(final ISalesCashAndCreditDetailEnterRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "SalesCashAndCreditDetailEnterViewModel";
    }
}
