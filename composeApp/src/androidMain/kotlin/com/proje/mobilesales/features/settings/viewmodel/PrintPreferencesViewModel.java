package com.proje.mobilesales.features.settings.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.settings.repository.IPrintPreferencesRepository;
import kotlin.jvm.internal.Intrinsics;

public final class PrintPreferencesViewModel extends BaseViewModel {
    private final IPrintPreferencesRepository repository;
    public PrintPreferencesViewModel(IPrintPreferencesRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
}
