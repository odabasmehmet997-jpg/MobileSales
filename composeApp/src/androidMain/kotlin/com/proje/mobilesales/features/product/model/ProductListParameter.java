package com.proje.mobilesales.features.product.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import com.proje.mobilesales.core.enums.ProductTreeGroupListType;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;
 
public class ProductListParameter implements Parcelable {
    private String customFilter;
    private int customerRef;
    private int defOrderId;
    private double defaultAmount;
    private String defaultExplanation;
    private int defaultSelectIndex;
    private boolean isAllStock;
    private boolean isAutoAddProduct;
    private boolean isAutomaticSearch;
    private boolean isExplanation;
    private boolean isOnlinePriceGet;
    private boolean isOnlineStockGet;
    private boolean isShowOnlyStock;
    private boolean isShowPrices;
    private boolean isShowStockExist;
    private boolean isSortChanged;
    private boolean isWhichMaterialDesc;
    private int priority;
    private ProductOperationDiscount productOperationDiscount;
    private ProductSearchOption productSearchOption;
    private ProductTreeGroupListType productTreeGroupListType;
    private ArrayList<String> productTreeList;
    private int searchType;
    private int sortType;
    private int unitPriceDigit;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ProductListParameter> CREATOR = new Creator();
    public static final class Creator implements Parcelable.Creator<ProductListParameter> {
        public ProductListParameter createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return ProductListParameter.Companion.create(parcel);
        }
        public ProductListParameter[] newArray(int i) {
            return new ProductListParameter[i];
        }
    }
    public ProductListParameter() {
        this(false, 0, false, false, 0, 0, false, false, false, false, 0, 0, 0, 0, 0.0d, null, false, null, null, false, false, null, null, null, false, 33554431, null);
    } 
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        Companion.write(this, parcel, i);
    }
    public ProductListParameter(boolean z, int i, boolean z2, boolean z3, int i2, int i3, boolean z4, boolean z5, boolean z6, boolean z7, int i4, int i5, int i6, int i7, double d, ProductOperationDiscount productOperationDiscount, boolean z8, String str, ProductSearchOption productSearchOption, boolean z9, boolean z10, ArrayList<String> arrayList, ProductTreeGroupListType productTreeGroupListType, String str2, boolean z11) {
        Intrinsics.checkNotNullParameter(arrayList, "productTreeList");
        this.isWhichMaterialDesc = z;
        this.defaultSelectIndex = i;
        this.isAutomaticSearch = z2;
        this.isShowStockExist = z3;
        this.searchType = i2;
        this.sortType = i3;
        this.isOnlinePriceGet = z4;
        this.isOnlineStockGet = z5;
        this.isShowOnlyStock = z6;
        this.isAllStock = z7;
        this.priority = i4;
        this.defOrderId = i5;
        this.customerRef = i6;
        this.unitPriceDigit = i7;
        this.defaultAmount = d;
        this.productOperationDiscount = productOperationDiscount;
        this.isExplanation = z8;
        this.defaultExplanation = str;
        this.productSearchOption = productSearchOption;
        this.isAutoAddProduct = z9;
        this.isShowPrices = z10;
        this.productTreeList = arrayList;
        this.productTreeGroupListType = productTreeGroupListType;
        this.customFilter = str2;
        this.isSortChanged = z11;
    }
    public final boolean isWhichMaterialDesc() {
        return this.isWhichMaterialDesc;
    }
    public final void setWhichMaterialDesc(boolean z) {
        this.isWhichMaterialDesc = z;
    }
    public final int getDefaultSelectIndex() {
        return this.defaultSelectIndex;
    }
    public final void setDefaultSelectIndex(int i) {
        this.defaultSelectIndex = i;
    }
    public final boolean isAutomaticSearch() {
        return this.isAutomaticSearch;
    }
    public final void setAutomaticSearch(boolean z) {
        this.isAutomaticSearch = z;
    }
    public final boolean isShowStockExist() {
        return this.isShowStockExist;
    }
    public final void setShowStockExist(boolean z) {
        this.isShowStockExist = z;
    }
    public final int getSearchType() {
        return this.searchType;
    }
    public final void setSearchType(int i) {
        this.searchType = i;
    }
    public final int getSortType() {
        return this.sortType;
    }
    public final void setSortType(int i) {
        this.sortType = i;
    }
    public final boolean isOnlinePriceGet() {
        return this.isOnlinePriceGet;
    }
    public final void setOnlinePriceGet(boolean z) {
        this.isOnlinePriceGet = z;
    }
    public final boolean isOnlineStockGet() {
        return this.isOnlineStockGet;
    }
    public final void setOnlineStockGet(boolean z) {
        this.isOnlineStockGet = z;
    }
    public final boolean isShowOnlyStock() {
        return this.isShowOnlyStock;
    }
    public final void setShowOnlyStock(boolean z) {
        this.isShowOnlyStock = z;
    }
    public final boolean isAllStock() {
        return this.isAllStock;
    }
    public final void setAllStock(boolean z) {
        this.isAllStock = z;
    }
    public final int getPriority() {
        return this.priority;
    }
    public final void setPriority(int i) {
        this.priority = i;
    }
    public final int getDefOrderId() {
        return this.defOrderId;
    }
    public final void setDefOrderId(int i) {
        this.defOrderId = i;
    }
    public final int getCustomerRef() {
        return this.customerRef;
    }
    public final void setCustomerRef(int i) {
        this.customerRef = i;
    }
    public final int getUnitPriceDigit() {
        return this.unitPriceDigit;
    }
    public final void setUnitPriceDigit(int i) {
        this.unitPriceDigit = i;
    }
    public final double getDefaultAmount() {
        return this.defaultAmount;
    }
    public final void setDefaultAmount(double d) {
        this.defaultAmount = d;
    }
    public final ProductOperationDiscount getProductOperationDiscount() {
        return this.productOperationDiscount;
    }
    public final void setProductOperationDiscount(ProductOperationDiscount productOperationDiscount) {
        this.productOperationDiscount = productOperationDiscount;
    }
    public final boolean isExplanation() {
        return this.isExplanation;
    }
    public final void setExplanation(boolean z) {
        this.isExplanation = z;
    }
    public final String getDefaultExplanation() {
        return this.defaultExplanation;
    }
    public final void setDefaultExplanation(String str) {
        this.defaultExplanation = str;
    }
    public final ProductSearchOption getProductSearchOption() {
        return this.productSearchOption;
    }
    public final void setProductSearchOption(ProductSearchOption productSearchOption) {
        this.productSearchOption = productSearchOption;
    }
    public final boolean isAutoAddProduct() {
        return this.isAutoAddProduct;
    }
    public final void setAutoAddProduct(boolean z) {
        this.isAutoAddProduct = z;
    }
    public final boolean isShowPrices() {
        return this.isShowPrices;
    }
    public final void setShowPrices(boolean z) {
        this.isShowPrices = z;
    }
    public ProductListParameter(boolean z, int i, boolean z2, boolean z3, int i2, int i3, boolean z4, boolean z5, boolean z6, boolean z7, int i4, int i5, int i6, int i7, double d, ProductOperationDiscount productOperationDiscount, boolean z8, String str, ProductSearchOption productSearchOption, boolean z9, boolean z10, ArrayList arrayList, ProductTreeGroupListType productTreeGroupListType, String str2, boolean z11, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) == 0 && z, (i8 & 2) != 0 ? 0 : i, (i8 & 4) == 0 && z2, (i8 & 8) == 0 && z3, (i8 & 16) != 0 ? 0 : i2, (i8 & 32) != 0 ? 0 : i3, (i8 & 64) == 0 && z4, (i8 & 128) == 0 && z5, (i8 & 256) == 0 && z6, (i8 & 512) == 0 && z7, (i8 & 1024) != 0 ? 0 : i4, (i8 & 2048) != 0 ? 0 : i5, (i8 & 4096) != 0 ? 0 : i6, (i8 & 8192) != 0 ? 0 : i7, (i8 & 16384) != 0 ? 0.0d : d, (32768 & i8) != 0 ? null : productOperationDiscount, (i8 & 65536) == 0 && z8, (i8 & 131072) != 0 ? null : str, (i8 & 262144) != 0 ? null : productSearchOption, (i8 & 524288) == 0 && z9, (i8 & 1048576) == 0 && z10, (i8 & 2097152) != 0 ? new ArrayList() : arrayList, (i8 & 4194304) != 0 ? null : productTreeGroupListType, (i8 & 8388608) == 0 ? str2 : null, (i8 & 16777216) == 0 && z11);
    }
    public final ArrayList<String> getProductTreeList() {
        return this.productTreeList;
    }
    public final void setProductTreeList(ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.productTreeList = arrayList;
    }
    public final ProductTreeGroupListType getProductTreeGroupListType() {
        return this.productTreeGroupListType;
    }
    public final void setProductTreeGroupListType(ProductTreeGroupListType productTreeGroupListType) {
        this.productTreeGroupListType = productTreeGroupListType;
    }
    public final String getCustomFilter() {
        return this.customFilter;
    }
    public final void setCustomFilter(String str) {
        this.customFilter = str;
    }
    public final boolean isSortChanged() {
        return this.isSortChanged;
    }
    public final void setSortChanged(boolean z) {
        this.isSortChanged = z;
    }
    protected ProductListParameter(Parcel parcel) {
        this(false, 0, false, false, 0, 0, false, false, false, false, 0, 0, 0, 0, 0.0d, null, false, null, null, false, false, null, null, null, false, 33554431, null);
        boolean z;
        ProductListParameter productListParameter;
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        boolean z2 = false;
        if (parcel.readByte() != 0) {
            productListParameter = this;
            z = true;
        } else {
            productListParameter = this;
            z = false;
        }
        productListParameter.isWhichMaterialDesc = z;
        productListParameter.defaultSelectIndex = parcel.readInt();
        productListParameter.isAutomaticSearch = parcel.readByte() != 0;
        productListParameter.isShowStockExist = parcel.readByte() != 0;
        productListParameter.searchType = parcel.readInt();
        productListParameter.sortType = parcel.readInt();
        productListParameter.isOnlinePriceGet = parcel.readByte() != 0;
        productListParameter.isOnlineStockGet = parcel.readByte() != 0;
        productListParameter.isShowOnlyStock = parcel.readByte() != 0;
        productListParameter.isAllStock = parcel.readByte() != 0;
        productListParameter.priority = parcel.readInt();
        productListParameter.defOrderId = parcel.readInt();
        productListParameter.customerRef = parcel.readInt();
        productListParameter.unitPriceDigit = parcel.readInt();
        productListParameter.defaultAmount = parcel.readDouble();
        productListParameter.productOperationDiscount = parcel.readParcelable(ProductOperationDiscount.class.getClassLoader());
        productListParameter.isExplanation = parcel.readByte() != 0;
        productListParameter.defaultExplanation = parcel.readString();
        productListParameter.productSearchOption = parcel.readParcelable(ProductSearchOption.class.getClassLoader());
        productListParameter.isAutoAddProduct = parcel.readByte() != 0;
        productListParameter.isShowPrices = parcel.readByte() != 0;
        ArrayList<String> arrayList = new ArrayList<>();
        productListParameter.productTreeList = arrayList;
        parcel.readList(arrayList, null);
        productListParameter.productTreeGroupListType = ProductTreeGroupListType.values()[parcel.readInt()];
        productListParameter.customFilter = parcel.readString();
        productListParameter.isSortChanged = parcel.readByte() != 0 || z2;
    }
    public static final class Companion implements Parceler<ProductListParameter> {
        private Companion(Object o) {
        }
        public BaseDbSalesFiche[] newArray(int i) {
            return  DefaultImpls.newArray(this, i);
        }
        public void write(ProductListParameter productListParameter, Parcel parcel, int i) {
            int i2;
            Intrinsics.checkNotNullParameter(productListParameter, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeByte(productListParameter.isWhichMaterialDesc() ? (byte) 1 : 0);
            parcel.writeInt(productListParameter.getDefaultSelectIndex());
            parcel.writeByte(productListParameter.isAutomaticSearch() ? (byte) 1 : 0);
            parcel.writeByte(productListParameter.isShowStockExist() ? (byte) 1 : 0);
            parcel.writeInt(productListParameter.getSearchType());
            parcel.writeInt(productListParameter.getSortType());
            parcel.writeByte(productListParameter.isOnlinePriceGet() ? (byte) 1 : 0);
            parcel.writeByte(productListParameter.isOnlineStockGet() ? (byte) 1 : 0);
            parcel.writeByte(productListParameter.isShowOnlyStock() ? (byte) 1 : 0);
            parcel.writeByte(productListParameter.isAllStock() ? (byte) 1 : 0);
            parcel.writeInt(productListParameter.getPriority());
            parcel.writeInt(productListParameter.getDefOrderId());
            parcel.writeInt(productListParameter.getCustomerRef());
            parcel.writeInt(productListParameter.getUnitPriceDigit());
            parcel.writeDouble(productListParameter.getDefaultAmount());
            parcel.writeParcelable(productListParameter.getProductOperationDiscount(), i);
            parcel.writeByte(productListParameter.isExplanation() ? (byte) 1 : 0);
            parcel.writeString(productListParameter.getDefaultExplanation());
            parcel.writeParcelable(productListParameter.getProductSearchOption(), i);
            parcel.writeByte(productListParameter.isAutoAddProduct() ? (byte) 1 : 0);
            parcel.writeByte(productListParameter.isShowPrices() ? (byte) 1 : 0);
            parcel.writeList(productListParameter.getProductTreeList());
            if (productListParameter.getProductTreeGroupListType() == null) {
                i2 = 0;
            } else {
                ProductTreeGroupListType productTreeGroupListType = productListParameter.getProductTreeGroupListType();
                Intrinsics.checkNotNull(productTreeGroupListType);
                i2 = productTreeGroupListType.ordinal();
            }
            parcel.writeInt(i2);
            parcel.writeString(productListParameter.getCustomFilter());
            parcel.writeByte(productListParameter.isExplanation() ? (byte) 1 : 0);
        }
        public ProductListParameter create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ProductListParameter(parcel);
        }
    }
}
