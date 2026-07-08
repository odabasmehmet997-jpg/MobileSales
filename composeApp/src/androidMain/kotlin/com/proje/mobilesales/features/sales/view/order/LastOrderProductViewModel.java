package com.proje.mobilesales.features.sales.view.order;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.sales.repository.LastOrderProductsRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

public final class LastOrderProductViewModel extends BaseSalesViewModel {
    private final LastOrderProductsRepository repository;

    public LastOrderProductViewModel(LastOrderProductsRepository repository) {
        super(repository);
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public LastOrderProductsRepository getRepository() {
        return this.repository;
    }

    public void getLastOrderProductList(int i2, boolean z, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            BuildersKt.runBlocking (null, new LastOrderProductViewModelgetLastOrderProductList1(this, i2, z, responseListener, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
