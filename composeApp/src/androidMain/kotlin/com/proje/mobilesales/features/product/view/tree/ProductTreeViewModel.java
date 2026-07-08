package com.proje.mobilesales.features.product.view.tree;

import com.proje.mobilesales.features.product.repository.IProductTreeRepository;
import com.proje.mobilesales.features.product.view.BaseProductViewModel;
import kotlin.jvm.internal.Intrinsics;

public final class ProductTreeViewModel extends BaseProductViewModel {
    private final String TAG = "ProductTreeViewModel";
    private final IProductTreeRepository repository;

    public ProductTreeViewModel(IProductTreeRepository iProductTreeRepository) {
        super(iProductTreeRepository);
        Intrinsics.checkNotNullParameter(iProductTreeRepository, "repository");
        this.repository = iProductTreeRepository;
    }
}
