package com.proje.mobilesales.features.tools.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import com.proje.mobilesales.features.tools.repository.IStartInfoEnterRepository;
import kotlin.jvm.internal.Intrinsics;

public final class StartInfoEnterViewModel extends BaseViewModel {
    private final IStartInfoEnterRepository repository;
    private final String tag;
    public StartInfoEnterViewModel(final IStartInfoEnterRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        tag = "StartInfoEnterViewModel";
    }
    public void sendStartInfoEnter(final StartInfo startInfo) {
        Intrinsics.checkNotNullParameter(startInfo, "startInfo");
        repository.sendStartInfoEnter(startInfo);
    }
}
