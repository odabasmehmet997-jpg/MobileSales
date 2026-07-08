package com.proje.mobilesales.features.collections.prefs.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.collections.prefs.repository.ISalesCashAndCreditHeaderEnterRepository;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: SalesCashAndCreditHeaderEnterViewModel.kt */

public final class SalesCashAndCreditHeaderEnterViewModel extends BaseViewModel {
    private final ISalesCashAndCreditHeaderEnterRepository repository;
    private final String tag;

    
    public SalesCashAndCreditHeaderEnterViewModel(final ISalesCashAndCreditHeaderEnterRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "SalesCashAndCreditHeaderEnterViewModel";
    }
}
