package com.proje.mobilesales.features.product.view.serial;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.product.repository.IProductSerialRepository;
import com.proje.mobilesales.features.product.view.BaseProductViewModel;
import kotlin.jvm.internal.Intrinsics;

public final class ProductSerialViewModel extends BaseProductViewModel {
    private static String TAG;
    private final IProductSerialRepository repository;
    public ProductSerialViewModel(IProductSerialRepository iProductSerialRepository) {
        super(iProductSerialRepository);
        Intrinsics.checkNotNullParameter(iProductSerialRepository, "repository");
        this.repository = iProductSerialRepository;
    }
    public static void setTAG(String TAG) {
        ProductSerialViewModel.TAG = TAG;
    }
    public   void getProductSerialInfo(String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getProductSerialInfo(str, responseListener);
        Log.i(ProductSerialViewModel.TAG, "getProductSerialInfo");
    }

}
