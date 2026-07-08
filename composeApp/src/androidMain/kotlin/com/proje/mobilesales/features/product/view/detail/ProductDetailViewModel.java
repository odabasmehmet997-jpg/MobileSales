package com.proje.mobilesales.features.product.view.detail;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.product.model.ProductDetail;
import com.proje.mobilesales.features.product.repository.IProductDetailRepository;
import com.proje.mobilesales.features.product.view.BaseProductViewModel;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
 
public final class ProductDetailViewModel extends BaseProductViewModel {
    final String TAG = "ProductDetailVM";
    private final IProductDetailRepository repository;
 
    public ProductDetailViewModel(IProductDetailRepository iProductDetailRepository) {
        super(iProductDetailRepository);
        Intrinsics.checkNotNullParameter(iProductDetailRepository, "repository");
        this.repository = iProductDetailRepository;
    }

    public void getItemDetailsProductChart(int i, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getItemDetailChart(i, responseListener);
        Log.e(this.TAG, "GetItemDetailChart");
    }

    public void getItemDetailInformationForProduct(int i, boolean z, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getProductDetailsInformation(i, z, responseListener);
        Log.i(this.TAG, "GetItemDetailInformationForProduct");
    }

    public String getPaymentCodeFromSqlHelper(ProductDetail.ItemPrice itemPrice) {
        Intrinsics.checkNotNullParameter(itemPrice, "itemPrice");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = "";
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductDetailViewModelgetPaymentCodeFromSqlHelper1(this, refObjectRef, itemPrice, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }
}
