package com.proje.mobilesales.features.sales.view.detail;

import android.util.Log;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.dbmodel.MuhRefCodes;
import com.proje.mobilesales.features.dbmodel.Price;
import com.proje.mobilesales.features.dbmodel.Units;
import com.proje.mobilesales.features.dbmodel.WareHouse;
import com.proje.mobilesales.features.model.KeyValuePair;
import com.proje.mobilesales.features.product.model.LastItemPriceSqlParams;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.repository.ISalesDetailRepository;
import com.proje.mobilesales.features.sales.view.BaseSalesViewModel;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.RefBooleanRef;
import kotlin.jvm.internal.RefDoubleRef;
import kotlin.jvm.internal.RefIntRef;
import kotlin.jvm.internal.RefObjectRef;
import kotlinx.coroutines.BuildersKt;

public final class SalesDetailViewModel extends BaseSalesViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SalesDetailViewModel";
    private final int getLocalCurrType;
    private final boolean getUseVatIncForProductsDontHavePriceCard;
    private final boolean isDueDateByLineEnabled;
    private final boolean productOnlinePrice;
    private final boolean productOnlineStock;
    private final ISalesDetailRepository repository;
    private final boolean showLastPurchaseInformation;

    public SalesDetailViewModel(ISalesDetailRepository repository) {
        super(repository);
        Object runBlockingdefault;
        Object runBlockingdefault2;
        Object runBlockingdefault3;
        Object runBlockingdefault4;
        Object runBlockingdefault5;
        Object runBlockingdefault6;
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
        runBlockingdefault = BuildersKt.runBlocking(null, new SalesDetailViewModelproductOnlineStock1(this, null));
        this.productOnlineStock = ((Boolean) runBlockingdefault).booleanValue();
        runBlockingdefault2 = BuildersKt.runBlocking(null, new SalesDetailViewModelgetLocalCurrType1(this, null));
        this.getLocalCurrType = ((Number) runBlockingdefault2).intValue();
        runBlockingdefault3 = BuildersKt.runBlocking(null, new SalesDetailViewModelproductOnlinePrice1(this, null));
        this.productOnlinePrice = ((Boolean) runBlockingdefault3).booleanValue();
        runBlockingdefault4 = BuildersKt.runBlocking(null, new SalesDetailViewModelisDueDateByLineEnabled1(this, null));
        this.isDueDateByLineEnabled = ((Boolean) runBlockingdefault4).booleanValue();
        runBlockingdefault5 = BuildersKt.runBlocking(null, new SalesDetailViewModelshowLastPurchaseInformation1(this, null));
        this.showLastPurchaseInformation = ((Boolean) runBlockingdefault5).booleanValue();
        runBlockingdefault6 = BuildersKt.runBlocking(null, new SalesDetailViewModelgetUseVatIncForProductsDontHavePriceCard1(this, null));
        this.getUseVatIncForProductsDontHavePriceCard = ((Boolean) runBlockingdefault6).booleanValue();
    }

    public ISalesDetailRepository getRepository() {
        return this.repository;
    }

    public boolean getProductOnlineStock() {
        return this.productOnlineStock;
    }

    public int getGetLocalCurrType() {
        return this.getLocalCurrType;
    }

    public boolean getProductOnlinePrice() {
        return this.productOnlinePrice;
    }

    public boolean isDueDateByLineEnabled() {
        return this.isDueDateByLineEnabled;
    }

    public boolean getShowLastPurchaseInformation() {
        return this.showLastPurchaseInformation;
    }

    public boolean getGetUseVatIncForProductsDontHavePriceCard() {
        return this.getUseVatIncForProductsDontHavePriceCard;
    }

    public void setDueDateForSalesDetail(SalesDetail salesDetail, int i2) {
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelsetDueDateForSalesDetail1(this, salesDetail, i2, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCentOfUnitPriceDigit() {
        RefIntRef refIntRef = new RefIntRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetCentOfUnitPriceDigit1(this, refIntRef, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refIntRef.element;
    }

    public String getSpeCodeTypeDetailFromErp(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = "";
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetSpeCodeTypeDetailFromErp1(this, refObjectRef, salesType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (String) refObjectRef.element;
    }

    public void getOrderGrossTotalOnlineResponse(int i2, SalesDetailFragment.SalesOrderGrossTotalListener responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOrderGrossTotalOnline(i2, responseListener);
        Log.i(getTAG(), "GetOrderGrossTotalOnlineResponse");
    }

    public boolean getIsSalesDetailCurrTypeChange(int i2, int i3) {
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetIsSalesDetailCurrTypeChange1(this, refBooleanRef, i2, i3, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }

    public double getLastCurRate(String currCode, int i2) {
        Intrinsics.checkNotNullParameter(currCode, "currCode");
        RefDoubleRef refDoubleRef = new RefDoubleRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetLastCurRate1(this, refDoubleRef, currCode, i2, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refDoubleRef.element;
    }

    public double getVariantRealStock(int i2) {
        RefDoubleRef refDoubleRef = new RefDoubleRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetVariantRealStock1(this, refDoubleRef, i2, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refDoubleRef.element;
    }

    public void getSalesFicheSelectedItemPrice(LastItemPriceSqlParams lastItemPriceSqlParams, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(lastItemPriceSqlParams, "lastItemPriceSqlParams");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getSalesFicheSelectedItemPrice(lastItemPriceSqlParams, responseListener);
        Log.i(getTAG(), "getSalesFicheSelectedItemPrice");
    }

    public void getLastCustomerSalesDateOfItem(int i2, int i3, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getLastCustomerSalesDateOfItem(i2, i3, responseListener);
        Log.i(getTAG(), "getLastCustomerSalesDateOfItem");
    }

    public void getOnlinePrice(int i2, int i3, int i4, boolean z, int i5, int i6, String str, int i7, ResponseListener<List<Price>> responseListener, String str2, int i8) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOnlinePrice(i2, i3, i4, z, i5, i6, str, i7, responseListener, str2, i8);
        Log.i(getTAG(), "getOnlinePrice");
    }

    public List<WareHouse> getTableForWarehouseFromSqlHelper(Class<WareHouse> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new SalesDetailViewModelgetTableForWarehouseFromSqlHelper1(this, refObjectRef, tableClass, selection, selectionArgs, null), 1, null);
        return (List) refObjectRef.element;
    }

    public List<MuhRefCodes> getTableForMuhRefCodesFromSqlHelper(Class<MuhRefCodes> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new SalesDetailViewModelgetTableForMuhRefCodesFromSqlHelper1(this, refObjectRef, tableClass, selection, selectionArgs, null), 1, null);
        return (List) refObjectRef.element;
    }

    public List<Units> getTableForUnitsFromSqlHelper(Class<Units> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new SalesDetailViewModelgetTableForUnitsFromSqlHelper1(this, refObjectRef, tableClass, selection, selectionArgs, null), 1, null);
        return (List) refObjectRef.element;
    }

    public void getOnlineStockAllInSingleResponse(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOnlineStockAllInSingleResponse(responseListener);
        Log.i(getTAG(), "getOnlineStockAllInSingleResponse");
    }

    public boolean addProductOutOfTheOrder(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        RefBooleanRef refBooleanRef = new RefBooleanRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModeladdProductOutOfTheOrder1(this, refBooleanRef, salesType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }

    public Object getObjectWithSyncCode(int i2, boolean z) {
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetObjectWithSyncCode1(this, refObjectRef, i2, z, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refObjectRef.element;
    }

    public List<Double> calculateLineUnitTotals(Sales sales) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelcalculateLineUnitTotals1(this, refObjectRef, sales, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = refObjectRef.element;
        Intrinsics.checkNotNull(t);
        return (List) t;
    }

    public List<ItemPrice> getTableForItemPriceFromSqlHelper(Class<ItemPrice> tableClass, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(tableClass, "tableClass");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        RefObjectRef refObjectRef = new RefObjectRef();
        BuildersKt.runBlocking(null, new SalesDetailViewModelgetTableForItemPriceFromSqlHelper1(this, refObjectRef, tableClass, selection, selectionArgs, null), 1, null);
        return (List) refObjectRef.element;
    }

    public void getSurplusDiscountForItem(int i2, String customerConditionCode, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(customerConditionCode, "customerConditionCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getItemSurplusDiscount(i2, customerConditionCode, responseListener);
        Log.i(getTAG(), "GetSurplusDiscountForItem");
    }

    public void getAlternativePromotionItems(String campaignCode, String str, ResponseListener<ArrayList<KeyValuePair>> responseListener) {
        Intrinsics.checkNotNullParameter(campaignCode, "campaignCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getAlternativePromotionItems(campaignCode, str, responseListener);
        Log.i(getTAG(), "getAlternativePromotionItems");
    }

    public ProductListParameter getProductListParameter(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        RefObjectRef refObjectRef = new RefObjectRef();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetProductListParameter1(refObjectRef, this, salesType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object t = refObjectRef.element;
        if (t != 0) {
            return (ProductListParameter) t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }

    public void getSaveStockLastTransDate() {
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetSaveStockLastTransDate1(this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSalesDetailItemStock(Sales sales) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelupdateSalesDetailItemStock1(this, sales, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Disposable getProductList(String str, int i2, ResponseListener<ArrayList<Product>> responseListener, String str2) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable productListFromSqlManager = this.repository.getProductListFromSqlManager(str, i2, responseListener, str2);
        Log.i(getTAG(), "GetProductProductList");
        return productListFromSqlManager;
    }
    public List<ItemUnits> getItemUnits(int i2) {
        RefObjectRef refObjectRef = new RefObjectRef();
        refObjectRef.element = new ArrayList();
        try {
            BuildersKt.runBlocking(null, new SalesDetailViewModelgetItemUnits1(this, refObjectRef, i2, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (List) refObjectRef.element;
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getTAG() {
            return SalesDetailViewModel.TAG;
        }
    }
}
