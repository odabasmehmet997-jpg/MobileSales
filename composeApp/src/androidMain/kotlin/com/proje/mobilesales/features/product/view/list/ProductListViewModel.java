package com.proje.mobilesales.features.product.view.list;

import android.content.Context;
import android.util.Log;
import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.dbmodel.Service;
import com.proje.mobilesales.features.model.BarcodeResult;
import com.proje.mobilesales.features.product.model.ItemUnit;
import com.proje.mobilesales.features.product.model.Product;
import com.proje.mobilesales.features.product.model.ProductGroupModel;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import com.proje.mobilesales.features.product.model.database.ItemPrice;
import com.proje.mobilesales.features.product.model.database.RecommendedProducts;
import com.proje.mobilesales.features.product.repository.IProductListRepository;
import com.proje.mobilesales.features.product.view.BaseProductViewModel;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.*;
import kotlinx.coroutines.BuildersKt;
import io.reactivex.disposables.Disposable;
import kotlinx.coroutines.CoroutineScope;

public final class ProductListViewModel extends BaseProductViewModel {
    private final String TAG = "ProductListVM";
    private final IProductListRepository repository; 
    public ProductListViewModel(IProductListRepository iProductListRepository) {
        super(iProductListRepository);
        Intrinsics.checkNotNullParameter(iProductListRepository, "repository");
        this.repository = iProductListRepository;
    }
    public static ProductListViewModel accessgetRepositoryp(ProductListViewModel this0) {
        return this0;
    }

    public static String accessgetTAG(ProductListViewModel this0) {
        return "";
    }

    public T getProductListParameter(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetProductListParameter1(refObjectRef, this, salesType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public boolean getUseProductGroupTree() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetUseProductGroupTree1(refBooleanRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public   Object getUserInformation() {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetUserInformation1(refObjectRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public List<Service> getServiceTypeTableFromLogoSqlHelper(Class<Service> cls, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(cls, "aClass");
        Intrinsics.checkNotNullParameter(str, "selection");
        Intrinsics.checkNotNullParameter(strArr, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetServiceTypeTableFromLogoSqlHelper1(refObjectRef, this, cls, str, strArr, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return (List) t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public List<ItemPrice> getItemPriceTypeTableFromLogoSqlHelper(Class<ItemPrice> cls, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(cls, "aClass");
        Intrinsics.checkNotNullParameter(str, "selection");
        Intrinsics.checkNotNullParameter(strArr, "selectionArgs");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetItemPriceTypeTableFromLogoSqlHelper1(refObjectRef, this, cls, str, strArr, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return (List) t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public String getProductSortListSql(Context context, ProductListParameter productListParameter, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetProductSortListSql1(refObjectRef, this, context, productListParameter, i, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return t.toString();
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public String getProductListSql(ProductSqlDataModel productSqlDataModel) {
        Intrinsics.checkNotNullParameter(productSqlDataModel, "productSqlDataModel");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetProductListSql1(refObjectRef, this, productSqlDataModel, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return t.toString();
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public String getServiceListSql(ProductSqlDataModel productSqlDataModel) {
        Intrinsics.checkNotNullParameter(productSqlDataModel, "productSqlDataModel");
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetServiceListSql1(refObjectRef, this, productSqlDataModel, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return  t.toString();
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public String getISqlHelperClCardTradingGrp(int i) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetISqlHelperClCardTradingGrp1(refObjectRef, this, i, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) return  t.toString();
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public   T getFormGroupForDisposable(int i) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetFormGroupForDisposable1(refObjectRef, this, i, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return  t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public void getOnlineItemsProduct(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOnlineItems(responseListener);
        Log.i(this.TAG, "GetOnlineItemsProduct");
    }
    public int getSaveObjectForSelectedProducts(Object obj) {
        Ref.IntRef refIntRef = new Ref.IntRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModel.getSaveObjectForSelectedProducts1(refIntRef, this, obj, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refIntRef.element;
    }
    public void getSavedProductsForToggleSortBySortType(int i) {
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetSavedProductsForToggleSortBySortType1(this, i, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Disposable getProductList(String str, int i, ResponseListener<ArrayList<Product>> responseListener, String str2) {
        Disposable productListFromSqlManager = this.repository.getProductListFromSqlManager(str, i, responseListener, str2);
        Log.i(this.TAG, "GetProductProductList");
        return productListFromSqlManager;
    }
    public void getProductGroups(String str, ResponseListener<ArrayList<ProductGroupModel>> responseListener) {
        this.repository.getProductGroupsFromSqlManager(str, responseListener);
        Log.i(this.TAG, "GetProductGroups");
    }
    public void getRecommendedProducts(int i, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getRecommendedProducts(i, responseListener);
        Log.i(this.TAG, "GetRecommendedProducts");
    }
    public void getSaveStockLastTransDate() {
        try {
            Object unused = BuildersKt.runBlocking(null, null);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void getOnlineStockAllByProductGetDone(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOnlineStockAll(responseListener);
        Log.i(this.TAG, "GetOnlineStockAllByProductGetDone");
    }
    public void getOnlinePriceAllByProductGetDone(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getOnlinePriceAll(responseListener);
        Log.i(this.TAG, "GetOnlinePriceAllByProductGetDone");
    }
    public void getSurplusDiscountForItem(int i, String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(str, "customerConditionCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.repository.getItemSurplusDiscount(i, str, responseListener);
        Log.i(this.TAG, "GetSurplusDiscountForItem");
    }
    public void getPrintTransportDispatchNote(Context context, FicheType ficheType, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetPrintTransportDispatchNote1(this, context, ficheType, i, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean getSurplusAmountEnabled() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetSurplusAmountEnabled1(refBooleanRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public boolean getSalesFicheApplyPromotion(SalesType salesType) {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetSalesFicheApplyPromotion1(refBooleanRef, this, salesType, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public ArrayList<ItemUnit> getProductUnitsFromSqlHelper(int i, boolean z) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        refObjectRef.element = new ArrayList();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetProductUnitsFromSqlHelper1(refObjectRef, this, i, z, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (ArrayList) refObjectRef.element;
    }
    public <T> String getCustomerConditionCodeFromSqlHelper(int i) {
        Ref.ObjectRef refObjectRef = new Ref.ObjectRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetCustomerConditionCodeFromSqlHelper1(refObjectRef, this, i, null) {
                @Override
                public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                    return null;
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T t = (T) refObjectRef.element;
        if (false) {
            return (String) t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }
    public boolean getIsPromotionItemPriceEnabled() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetIsPromotionItemPriceEnabled1(refBooleanRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public boolean getEnterPrice() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetEnterPrice1(refBooleanRef, this, null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public boolean getChangePrice() {
        Ref.BooleanRef refBooleanRef = new Ref.BooleanRef();
        try {
            Object unused = BuildersKt.runBlocking(null, new ProductListViewModelgetChangePrice1(refBooleanRef, this, null) {
                @Override
                public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
                    return null;
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return refBooleanRef.element;
    }
    public static final class ProductSqlDataModel {
        private final ArrayList<BarcodeResult> barcodeFilter;
        private final Context context;
        private final int customerRef;
        private final int defOrderId;
        private final int divisionNr;
        private final String filter;
        private final boolean isBarcodeSearch;
        private final String orderBySql;
        private final int paymentRef;
        private final String paymentTradeGroup;
        private final String productGroupCode;
        private final ProductListParameter productListParameter;
        private final ArrayList<RecommendedProducts> recommendedProductList;
        private final ArrayList<Integer> seriItemRefList;
        private final String variant;
        private final int wareHouseNr;
        public Context component1() {
            return this.context;
        }
        public boolean component10() {
            return this.isBarcodeSearch;
        }
        public ArrayList<Integer> component11() {
            return this.seriItemRefList;
        }
        public int component12() {
            return this.paymentRef;
        }
        public String component13() {
            return this.variant;
        }
        public String component14() {
            return this.productGroupCode;
        }
        public int component15() {
            return this.divisionNr;
        }
        public ArrayList<RecommendedProducts> component16() {
            return this.recommendedProductList;
        }
        public ProductListParameter component2() {
            return this.productListParameter;
        }
        public int component3() {
            return this.customerRef;
        }
        public String component4() {
            return this.paymentTradeGroup;
        }
        public int component5() {
            return this.wareHouseNr;
        }
        public ArrayList<BarcodeResult> component6() {
            return this.barcodeFilter;
        }
        public String component7() {
            return this.filter;
        }
        public int component8() {
            return this.defOrderId;
        }
        public String component9() {
            return this.orderBySql;
        }
        public ProductSqlDataModel copy(Context context, ProductListParameter productListParameter, int i, String str, int i2, ArrayList<BarcodeResult> arrayList, String str2, int i3, String str3, boolean z, ArrayList<Integer> arrayList2, int i4, String str4, String str5, int i5, ArrayList<RecommendedProducts> arrayList3) {
            return new ProductSqlDataModel(context, productListParameter, i, str, i2, arrayList, str2, i3, str3, z, arrayList2, i4, str4, str5, i5, arrayList3);
        }
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProductSqlDataModel productSqlDataModel)) {
                return false;
            }
            return Intrinsics.areEqual(this.context, productSqlDataModel.context) && Intrinsics.areEqual(this.productListParameter, productSqlDataModel.productListParameter) && this.customerRef == productSqlDataModel.customerRef && Intrinsics.areEqual(this.paymentTradeGroup, productSqlDataModel.paymentTradeGroup) && this.wareHouseNr == productSqlDataModel.wareHouseNr && Intrinsics.areEqual(this.barcodeFilter, productSqlDataModel.barcodeFilter) && Intrinsics.areEqual(this.filter, productSqlDataModel.filter) && this.defOrderId == productSqlDataModel.defOrderId && Intrinsics.areEqual(this.orderBySql, productSqlDataModel.orderBySql) && this.isBarcodeSearch == productSqlDataModel.isBarcodeSearch && Intrinsics.areEqual(this.seriItemRefList, productSqlDataModel.seriItemRefList) && this.paymentRef == productSqlDataModel.paymentRef && Intrinsics.areEqual(this.variant, productSqlDataModel.variant) && Intrinsics.areEqual(this.productGroupCode, productSqlDataModel.productGroupCode) && this.divisionNr == productSqlDataModel.divisionNr && Intrinsics.areEqual(this.recommendedProductList, productSqlDataModel.recommendedProductList);
        }
        public int hashCode() {
            Context context = this.context;
            int i = 0;
            int hashCode = (context == null ? 0 : context.hashCode()) * 31;
            ProductListParameter productListParameter = this.productListParameter;
            int hashCode2 = (((hashCode + (productListParameter == null ? 0 : productListParameter.hashCode())) * 31) + Integer.hashCode(this.customerRef)) * 31;
            String str = this.paymentTradeGroup;
            int hashCode3 = (((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.wareHouseNr)) * 31;
            ArrayList<BarcodeResult> arrayList = this.barcodeFilter;
            int hashCode4 = (hashCode3 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
            String str2 = this.filter;
            int hashCode5 = (((hashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31) + Integer.hashCode(this.defOrderId)) * 31;
            String str3 = this.orderBySql;
            int hashCode6 = (((hashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31) + Boolean.hashCode(this.isBarcodeSearch)) * 31;
            ArrayList<Integer> arrayList2 = this.seriItemRefList;
            int hashCode7 = (((hashCode6 + (arrayList2 == null ? 0 : arrayList2.hashCode())) * 31) + Integer.hashCode(this.paymentRef)) * 31;
            String str4 = this.variant;
            int hashCode8 = (hashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.productGroupCode;
            int hashCode9 = (((hashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31) + Integer.hashCode(this.divisionNr)) * 31;
            ArrayList<RecommendedProducts> arrayList3 = this.recommendedProductList;
            if (arrayList3 != null) {
                i = arrayList3.hashCode();
            }
            return hashCode9 + i;
        }
        public String toString() {
            return "ProductSqlDataModel(context=" + this.context + ", productListParameter=" + this.productListParameter + ", customerRef=" + this.customerRef + ", paymentTradeGroup=" + this.paymentTradeGroup + ", wareHouseNr=" + this.wareHouseNr + ", barcodeFilter=" + this.barcodeFilter + ", filter=" + this.filter + ", defOrderId=" + this.defOrderId + ", orderBySql=" + this.orderBySql + ", isBarcodeSearch=" + this.isBarcodeSearch + ", seriItemRefList=" + this.seriItemRefList + ", paymentRef=" + this.paymentRef + ", variant=" + this.variant + ", productGroupCode=" + this.productGroupCode + ", divisionNr=" + this.divisionNr + ", recommendedProductList=" + this.recommendedProductList + ')';
        }
        public ProductSqlDataModel(Context context, ProductListParameter productListParameter, int i, String str, int i2, ArrayList<BarcodeResult> arrayList, String str2, int i3, String str3, boolean z, ArrayList<Integer> arrayList2, int i4, String str4, String str5, int i5, ArrayList<RecommendedProducts> arrayList3) {
            this.context = context;
            this.productListParameter = productListParameter;
            this.customerRef = i;
            this.paymentTradeGroup = str;
            this.wareHouseNr = i2;
            this.barcodeFilter = arrayList;
            this.filter = str2;
            this.defOrderId = i3;
            this.orderBySql = str3;
            this.isBarcodeSearch = z;
            this.seriItemRefList = arrayList2;
            this.paymentRef = i4;
            this.variant = str4;
            this.productGroupCode = str5;
            this.divisionNr = i5;
            this.recommendedProductList = arrayList3;
        }
        public Context getContext() {
            return this.context;
        }
        public ProductListParameter getProductListParameter() {
            return this.productListParameter;
        }
        public int getCustomerRef() {
            return this.customerRef;
        }
        public String getPaymentTradeGroup() {
            return this.paymentTradeGroup;
        }
        public int getWareHouseNr() {
            return this.wareHouseNr;
        }
        public ArrayList<BarcodeResult> getBarcodeFilter() {
            return this.barcodeFilter;
        }
        public String getFilter() {
            return this.filter;
        }
        public int getDefOrderId() {
            return this.defOrderId;
        }
        public String getOrderBySql() {
            return this.orderBySql;
        }
        public boolean isBarcodeSearch() {
            return this.isBarcodeSearch;
        }
        public  ProductSqlDataModel(Context context, ProductListParameter productListParameter, int i, String str, int i2, ArrayList arrayList, String str2, int i3, String str3, boolean z, ArrayList arrayList2, int i4, String str4, String str5, int i5, ArrayList arrayList3, int i6, DefaultConstructorMarker defaultConstructorMarker) {
            this(context, productListParameter, i, str, i2, arrayList, str2, i3, str3, (i6 & 512) == 0 && z, (i6 & 1024) != 0 ? new ArrayList() : arrayList2, i4, (i6 & 4096) != 0 ? "" : str4, (i6 & 8192) != 0 ? "" : str5, (i6 & 16384) != 0 ? 0 : i5, arrayList3);
        }
        public ArrayList<Integer> getSeriItemRefList() {
            return this.seriItemRefList;
        }
        public int getPaymentRef() {
            return this.paymentRef;
        }
        public String getVariant() {
            return this.variant;
        }
        public String getProductGroupCode() {
            return this.productGroupCode;
        }
        public int getDivisionNr() {
            return this.divisionNr;
        }
        public ArrayList<RecommendedProducts> getRecommendedProductList() {
            return this.recommendedProductList;
        }
    }
    private class T {
        public Observable<Object> subscribeOn(Scheduler io) {
            return null;
        }
    }
    public class getSaveObjectForSelectedProducts1 implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        public getSaveObjectForSelectedProducts1(Ref.IntRef refIntRef, ProductListViewModel productListViewModel, Object obj, Object o) {
        }
        public Object invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
            return null;
        }
    }

}
