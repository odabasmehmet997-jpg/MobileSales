package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.features.dbmodel.Price;
import com.proje.mobilesales.features.product.model.Product;
import java.util.ArrayList;
import java.util.List;

public class BarcodeItem implements Parcelable {
    public static final Parcelable.Creator<BarcodeItem> CREATOR = new Parcelable.Creator<BarcodeItem>() {
        public BarcodeItem createFromParcel(final Parcel parcel) {
            return new BarcodeItem(parcel);
        }
        public BarcodeItem[] newArray(final int i2) {
            return new BarcodeItem[i2];
        }
    };
    private BarcodeResult key;
    private List<Price> priceList;
    private FicheStringProp priceProp;
    private Product product;
    private FicheDiscountRefProp selectedPriceRefProp;
    public int describeContents() {
        return 0;
    }
    public BarcodeItem(final BarcodeResult barcodeResult, final Product product) {
        selectedPriceRefProp = new FicheDiscountRefProp();
        priceProp = new FicheStringProp();
        priceList = new ArrayList();
        this.key = barcodeResult;
        this.product = product;
    }
    public BarcodeItem(final BarcodeResult barcodeResult) {
        selectedPriceRefProp = new FicheDiscountRefProp();
        priceProp = new FicheStringProp();
        priceList = new ArrayList();
        this.key = barcodeResult;
    }
    public BarcodeItem() {
        selectedPriceRefProp = new FicheDiscountRefProp();
        priceProp = new FicheStringProp();
        priceList = new ArrayList();
    }
    protected BarcodeItem(final Parcel parcel) {
        selectedPriceRefProp = new FicheDiscountRefProp();
        priceProp = new FicheStringProp();
        priceList = new ArrayList();
        product = parcel.readParcelable(Product.class.getClassLoader());
        key = parcel.readParcelable(BarcodeResult.class.getClassLoader());
        selectedPriceRefProp = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        priceProp = parcel.readParcelable(FicheStringProp.class.getClassLoader());
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(final Product product) {
        this.product = product;
    }
    public BarcodeResult getKey() {
        return key;
    }
    public void setKey(final BarcodeResult barcodeResult) {
        key = barcodeResult;
    }
    public FicheDiscountRefProp getSelectedPriceRefProp() {
        return selectedPriceRefProp;
    }
    public void setSelectedPriceRefProp(final FicheDiscountRefProp ficheDiscountRefProp) {
        selectedPriceRefProp = ficheDiscountRefProp;
    }
    public FicheStringProp getPriceProp() {
        return priceProp;
    }
    public void setPriceProp(final FicheStringProp ficheStringProp) {
        priceProp = ficheStringProp;
    }
    public List<Price> getPriceList() {
        return priceList;
    }
    public void setPriceList(final List<Price> list) {
        priceList = list;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeParcelable(product, i2);
        parcel.writeParcelable(key, i2);
        parcel.writeParcelable(selectedPriceRefProp, i2);
        parcel.writeParcelable(priceProp, i2);
    }
}
