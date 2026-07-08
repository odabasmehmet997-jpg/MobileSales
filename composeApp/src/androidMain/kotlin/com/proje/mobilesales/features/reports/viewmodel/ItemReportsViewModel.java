package com.proje.mobilesales.features.reports.viewmodel;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.reports.repository.IItemReportsRepository;
import kotlin.jvm.internal.Intrinsics;
public final class ItemReportsViewModel extends BaseViewModel {
    private final IItemReportsRepository repository;
    public ItemReportsViewModel(final IItemReportsRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }
    public void getReportOnline(final ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.getReportOnline(responseListener);
        Log.i(TAG, "getReportOnline");
    }
}
