package com.proje.mobilesales.features.collections.prefs.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.collections.prefs.repository.ISalesCaseCashHeaderEnterRepository;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: SalesCaseCashHeaderEnterViewModel.kt */

public final class SalesCaseCashHeaderEnterViewModel extends BaseViewModel {
    private final ISalesCaseCashHeaderEnterRepository repository;
    private final String tag;

    
    public SalesCaseCashHeaderEnterViewModel(final ISalesCaseCashHeaderEnterRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "SalesCaseCashHeaderEnterViewModel";
    }
}
