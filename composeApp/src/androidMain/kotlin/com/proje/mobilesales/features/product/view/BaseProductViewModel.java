package com.proje.mobilesales.features.product.view;

import android.util.Log;
import com.proje.mobilesales.core.base.BaseViewModel;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.product.model.ProductTreeItem;
import com.proje.mobilesales.features.product.repository.IBaseProductRepository;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import java.util.ArrayList;

public   class BaseProductViewModel extends BaseViewModel {
    private final String TAG = "BaseProductViewModel";
    private final IBaseProductRepository repository;
    public BaseProductViewModel(final IBaseProductRepository iBaseProductRepository) {
        super(iBaseProductRepository);
        Intrinsics.checkNotNullParameter(iBaseProductRepository, "repository");
        repository = iBaseProductRepository;
    }
    public final void getProductTreeListItems(final ArrayList<String> arrayList, final ProductTreeItem productTreeItem, final ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(arrayList, "groupCodeList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        repository.getProductTreeItems(arrayList, productTreeItem, responseListener);
        Log.i(TAG, "GetProductTreeListItems");
    }
    public final boolean isHideActualStockAmount() {
        final Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        refBooleanRef.element = true;
        try {
            final Object unused = BuildersKt.runBlocking(null, new BaseProductViewModelisHideActualStockAmount1 (refBooleanRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public final boolean isHideRealStockAmount() {
        final Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        refBooleanRef.element = true;
        try {
            final Object unused = BuildersKt.runBlocking(null, new BaseProductViewModelisHideRealStockAmount1 (refBooleanRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
}
