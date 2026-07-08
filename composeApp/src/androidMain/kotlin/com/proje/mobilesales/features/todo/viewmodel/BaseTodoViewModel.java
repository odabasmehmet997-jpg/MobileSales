package com.proje.mobilesales.features.todo.viewmodel;

import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.enums.MobileSalesUpdateType;
import com.proje.mobilesales.features.todo.repository.IBaseTodoRepository;
import kotlin.jvm.internal.Intrinsics;

public final class BaseTodoViewModel extends BaseViewModel {
    private final IBaseTodoRepository repository;
    public BaseTodoViewModel(IBaseTodoRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
    public void updateDataLogo(MobileSalesUpdateType mobileSalesUpdateType, int i2) {
        this.repository.updateDataLogo(mobileSalesUpdateType, i2);
    }
}
