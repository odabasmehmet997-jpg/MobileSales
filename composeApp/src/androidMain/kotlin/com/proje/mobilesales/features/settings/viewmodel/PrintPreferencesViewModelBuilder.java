package com.proje.mobilesales.features.settings.viewmodel;

import com.proje.mobilesales.features.settings.repository.IPrintPreferencesRepository;

public class PrintPreferencesViewModelBuilder {
    private IPrintPreferencesRepository repository;
    public PrintPreferencesViewModelBuilder setRepository(final IPrintPreferencesRepository repository) {
        this.repository = repository;
        return this;
    }
    public PrintPreferencesViewModel createPrintPreferencesViewModel() {
        return new PrintPreferencesViewModel(this.repository);
    }
}