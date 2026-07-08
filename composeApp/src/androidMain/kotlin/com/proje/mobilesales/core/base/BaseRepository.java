package com.proje.mobilesales.core.base;

import android.content.Context;
import android.database.Cursor;
import com.google.firebase.messaging.Constants;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.design.Netsis;
import com.proje.mobilesales.core.design.Tiger;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.ISqlManager;
import com.proje.mobilesales.core.sql.SqlManagerCreator;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.CustomerDetail;
import com.proje.mobilesales.features.customer.model.CustomerDiscount;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.dbmodel.PaymentLine;
import com.proje.mobilesales.features.dbmodel.ShipAddress;
import com.proje.mobilesales.features.dbmodel.User;
import com.proje.mobilesales.features.driverinformation.model.NEDispatchInfoModel;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.product.model.*;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheMenuRights;
import com.proje.mobilesales.features.sales.model.SalesShort;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheParameters;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

import java.util.ArrayList;
import java.util.List;

public class BaseRepository implements IBaseRepository {
    private final Lazy baseErpdelegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, () -> ErpCreator.getInstance().getmBaseErp());
    private BaseRepository baseRepository = this;
    private ISqlManager<Object> sqlManager = null;
    public BaseRepository() {
    }
    public Object getOne(Context context, String str, ResponseListener<CustomerDetail> responseListener, Continuation<? super Unit> continuation) {
        return getOnesuspendImpl(this, context, str, responseListener);
    } 
    public Object updateCustomer(int i2, Continuation<? super Unit> continuation) {
        return updateCustomersuspendImpl(this, i2, continuation);
    } 
    public Object updateShipAddress(int i2, Continuation<? super Unit> continuation) {
        return updateShipAddresssuspendImpl(this, i2, continuation);
    }
    public BaseRepository(Object baseRepositorybaseErp2) {
        SqlManagerCreator companion = SqlManagerCreator.Companion.getInstance();
        Intrinsics.checkNotNull(companion);
        this.sqlManager = companion.getSqlManager();
        this.baseRepository = this;
    }
    public BaseErp<?> getBaseErp() {
        Object value = this.baseErpdelegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (BaseErp) value;
    }
    public ISqlManager<Object> getSqlManager() {
        return this.sqlManager;
    }
    public BaseRepository getBaseRepository() {
        return this.baseRepository;
    }
    static  Object getOnesuspendImpl(BaseRepository baseRepository, Context context, String str, ResponseListener<CustomerDetail> responseListener) {
        baseRepository.getSqlManager().getOne(context, str, responseListener);
        return Unit.INSTANCE;
    } 
    public Disposable getProductListFromSqlManager(String str, int i2, ResponseListener<ArrayList<Product>> responseListener, String str2) {
        Disposable productList = getSqlManager().getProductList(str, i2, responseListener, str2);
        Intrinsics.checkNotNullExpressionValue(productList, "getProductList(...)");
        return productList;
    } 
    public Disposable getProductGroupsFromSqlManager(String str, ResponseListener<ArrayList<ProductGroupModel>> responseListener) {
        Disposable productGroups = getSqlManager().getProductGroups(str, responseListener);
        Intrinsics.checkNotNullExpressionValue(productGroups, "getProductGroups(...)");
        return productGroups;
    } 
    public Disposable getSalesCursorToListFromSqlManager(Cursor cursor, ResponseListener<List<SalesShort>> responseListener) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable salesCursorToList = getSqlManager().getSalesCursorToList(cursor, responseListener);
        Intrinsics.checkNotNullExpressionValue(salesCursorToList, "getSalesCursorToList(...)");
        return salesCursorToList;
    } 
    public void getSalesOrderOneFromSqlManager(int i2, ResponseListener<Sales> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().getSalesOrderOne(i2, responseListener);
    } 
    public void deleteSalesFicheLocalFromSqlManager(int i2, SalesType salesType, int i3, ResponseListener<Integer> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().deleteSalesFicheLocal(i2, salesType, i3, responseListener);
    } 
    public void deleteSalesListFicheLocalFromSqlManager(List<Integer> list, SalesType salesType, ResponseListener<List<Integer>> responseListener) {
        Intrinsics.checkNotNullParameter(list, "logicalRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().deleteSalesListFicheLocal(list, salesType, responseListener);
    } 
    public void getSalesWhTransferFromSqlManager(int i2, ResponseListener<Sales> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().getSalesWhTransfer(i2, responseListener);
    }
    public void getSalesInvoiceExamFromSqlManager(int i2, ResponseListener<Sales> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().getSalesInvoiceExam(i2, responseListener);
    }
    public void savePenetrationFicheFromSqlManager(Penetration penetration, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(penetration, "penetration");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().savePenetrationFiche(penetration, responseListener);
    }
    public void updatePenetrationFicheFromSqlManager(Penetration penetration, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(penetration, "penetration");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().updatePenetrationFiche(penetration, responseListener);
    }
    public void deletePenetrationFicheLocalFromSqlManager(int i2, int i3, ResponseListener<Integer> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().deletePenetrationFicheLocal(i2, i3, responseListener);
    }
    public void saveSalesFicheFromSqlManager(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().saveSalesFiche(sales, salesType, responseListener);
    }
    public void updateSalesFicheFromSqlManager(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getSqlManager().updateSalesFiche(sales, salesType, responseListener);
    }
    public Disposable getCustomer(int i2, ResponseListener<CustomerNew> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Disposable customer = getSqlManager().getCustomer(i2, responseListener);
        Intrinsics.checkNotNullExpressionValue(customer, "getCustomer(...)");
        return customer;
    }
    public ErpType getErp() {
        ErpType erpType = getBaseErp().getErpType();
        Intrinsics.checkNotNullExpressionValue(erpType, "getErpType(...)");
        return erpType;
    }
    public User getUser() {
        User user = getBaseErp().user;
        Intrinsics.checkNotNullExpressionValue(user, "user");
        return user;
    }
    public ISqlHelper<?> getLogoSqlHelper() {
        ISqlHelper<?> iSqlHelper = getBaseErp().logoSqlHelper;
        Intrinsics.checkNotNullExpressionValue(iSqlHelper, "logoSqlHelper");
        return iSqlHelper;
    }
    public void getOnlineItems(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlineItems(responseListener);
    }
    public void getOnlinePrice(int i2, int i3, int i4, boolean z, int i5, int i6, String str, int i7, ResponseListener<?> responseListener, String str2, int i8) {

    }
    public int getSaveObject(Object obj) {
        return getBaseErp().saveObject(obj);
    }
    public void getSaveProductSortType(int i2) {
        getBaseErp().saveProductSortType(i2);
    }
    public ProductListParameter getProductListParameter(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        ProductListParameter productListParameter = getBaseErp().getProductListParameter(salesType);
        Intrinsics.checkNotNullExpressionValue(productListParameter, "getProductListParameter(...)");
        return productListParameter;
    }
    public boolean useProductGroupTree() {
        return getBaseErp().useProductGroupTree();
    }
    public void saveStockLastTransDate() {
        getBaseErp().saveStockLastTransDate();
    }
    public boolean shouldValidateBranch() {
        return getBaseErp().shouldValidateBranch();
    }
    public boolean shouldValidateFactory() {
        return getBaseErp().shouldValidateFactory();
    }
    public void updateSalesDetailItemStock(Sales sales) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        getBaseErp().updateSalesDetailItemStock(sales);
    }
    public boolean canOrderCanTransferEInvoiceOrEArchive() {
        return getBaseErp().canOrderCanTransferEInvoiceOrEArchive();
    }
    public void readOrderFiche(ArrayList<?> arrayList, DataObjectType dataObjectType, Sales sales, List<?> list, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(arrayList, "ficheRef");
        Intrinsics.checkNotNullParameter(dataObjectType, "dataObjectType");
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().readOrderFiche(arrayList, dataObjectType, sales, list, responseListener);
    }
    public void setNewMatter(Context context, FicheType ficheType, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        getBaseErp().setNewMatter(context, ficheType, str);
    }
    public void getSalesProductLinesPrice(Sales sales, List<Integer> list, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(list, "linePositions");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            getBaseErp().getSalesProductLinesPrice(sales, list, responseListener);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    public void getSalesProductLinePrice(Sales sales, int i2, WcfPriceType wcfPriceType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(wcfPriceType, "wcfPriceType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        try {
            getBaseErp().getSalesProductLinePrice(sales, i2, wcfPriceType, responseListener);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    public void getSalesAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(wcfPriceType, "wcfPriceType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getSalesAllProductLinePrice(sales, wcfPriceType, responseListener);
    }
    public ISqlBriteDatabase<?> getLogoSqlBriteDatabase() {
        ISqlBriteDatabase<?> logoSqlBriteDatabase = getBaseErp().getLogoSqlBriteDatabase();
        Intrinsics.checkNotNullExpressionValue(logoSqlBriteDatabase, "getLogoSqlBriteDatabase(...)");
        return logoSqlBriteDatabase;
    }
    public void getItemDetailChart(int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getProductDetailCharts(i2, responseListener);
    }
    public void getProductDetailsInformation(int i2, boolean z, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getProductDetails(i2, z, responseListener);
    }
    public void getOnlineStockAll(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlineStockAll(responseListener);
    }
    public void getOnlinePriceAll(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlinePriceAll(responseListener);
    }
    public void getItemSurplusDiscount(int i2, String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(str, "customerConditionCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getItemSurplusDiscount(i2, str, responseListener);
    }
    public void getProductTreeItems(ArrayList<String> arrayList, ProductTreeItem productTreeItem, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(arrayList, "groupCodeList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getProductTreeItems(arrayList, productTreeItem, responseListener);
    }
    public void printTransportDispatchNote(Context context, FicheType ficheType, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        getBaseErp().printTransportDispatchNote(context, ficheType, i2);
    }
    public boolean surplusAmountFromLogoParamValue() {
        return getBaseErp().getSurplusAmountEnabled();
    }
    public boolean salesFicheApplyPromotion(SalesType salesType) {
        return getBaseErp().getSalesFicheApplyPromotion(salesType);
    }
    public boolean promotionItemPriceEnabled() {
        return getBaseErp().isPromotionItemPriceEnabled();
    }
    public boolean enterPrice() {
        return getBaseErp().getEnterPrice();
    }
    public boolean getChangePrice() {
        return getBaseErp().getChangePrice();
    }
    public boolean canCreateInvoiceForEInvoiceCustomer() {
        return getBaseErp().canCreateInvoiceForEInvoiceCustomer();
    }
    public boolean isVisitUserMenu() {
        return getBaseErp().isVisit();
    }
    public boolean isPenetrationUserMenu() {
        return getBaseErp().isPenetration();
    }
    public boolean isCustomerReportUserMenu() {
        return getBaseErp().isCustomerReport();
    }
    public boolean isReceiptUserMenu() {
        return getBaseErp().isReceipt();
    }
    public boolean isSalesUserMenu() {
        return getBaseErp().isSales();
    }
    public boolean isOrderUserMenu() {
        return getBaseErp().isOrder();
    }
    public boolean isCashUserMenu() {
        return getBaseErp().isCash();
    }
    public boolean isCaseUserMenu() {
        return getBaseErp().isCase();
    }
    public boolean isCreditCardUserMenu() {
        return getBaseErp().isCreditCard();
    }
    public boolean isChequeUserMenu() {
        return getBaseErp().isCheque();
    }
    public boolean isDeedUserMenu() {
        return getBaseErp().isDeed();
    }
    public boolean isInvoiceUserMenu() {
        return getBaseErp().isInvoice();
    }
    public boolean isReturnInvoiceUserMenu() {
        return getBaseErp().isReturnInvoice();
    }
    public boolean isRetailInvoiceUserMenu() {
        return getBaseErp().isRetailInvoice();
    }
    public boolean isDispatchUserMenu() {
        return getBaseErp().isDispatch();
    }
    public boolean isReturnDispatchUserMenu() {
        return getBaseErp().isReturnDispatch();
    }
    public boolean isOneToOneChangeUserMenu() {
        return getBaseErp().isOneToOneChange();
    }
    public void getLoadCurrencyBalances(int i2, String str, String str2, ResponseListener<?> responseListener) {
        getBaseErp().loadCurrencyBalances(i2, str, str2, responseListener);
    }
    static  Object updateCustomersuspendImpl(BaseRepository baseRepository, int i2, Continuation<? super Unit> continuation) {
        baseRepository.getBaseErp().updateCustomer(i2);
        return Unit.INSTANCE;
    }
    static  Object updateShipAddresssuspendImpl(BaseRepository baseRepository, int i2, Continuation<? super Unit> continuation) {
        baseRepository.getBaseErp().updateShipAddress(i2);
        return Unit.INSTANCE;
    }
    public void getCustomerCampaignPoint(int i2) {
        getBaseErp().getCustomerCampaignPoint(i2);
    }
    public String getCustomerInCharge(String str) {
        String customerInCharge = getBaseErp().getCustomerInCharge(str);
        Intrinsics.checkNotNullExpressionValue(customerInCharge, "getCustomerInCharge(...)");
        return customerInCharge;
    }
    public void getCustomerRiskCalculate(int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getCustomerRiskCalculate(i2, responseListener);
    }
    public int getLocalCurrType() {
        return getBaseErp().getLocalCurrType();
    }
    public void getOnlineUpdateCustomer(ResponseListener<Boolean> responseListener, int i2) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlineUpdateCustomer(responseListener, i2);
    }
    public void getSaveCustomerSortType(int i2) {
        getBaseErp().saveCustomerSortType(i2);
    }
    public void getSaveClCardLastTransDate() {
        getBaseErp().saveClCardLastTransDate();
    }
    public Observable<Resource<Boolean>> getCustomerHasDailyOperation(int i2) {
        Observable<Resource<Boolean>> customerHasDailyOperation = getBaseErp().customerHasDailyOperation(i2);
        Intrinsics.checkNotNullExpressionValue(customerHasDailyOperation, "customerHasDailyOperation(...)");
        return customerHasDailyOperation;
    }
    public void getListAllCustomersOnline(String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().listAllCustomersOnline(str, responseListener);
    }
    public void getOnlineShipCustomer(String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlineShipCustomer(str, responseListener);
    }
    public void addNewCustomer(CustomerNew customerNew, boolean z, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().addNewCustomer(customerNew, z, responseListener);
    }
    public void getCustomerRiskInfo(int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getCustomerRiskInfo(i2, responseListener);
    }
    public String getCustomerShipAddressSql(Context context, int i2, String str) {
        String customerShipAddressSql = getLogoSqlHelper().getCustomerShipAddressSql(context, i2, str);
        Intrinsics.checkNotNullExpressionValue(customerShipAddressSql, "getCustomerShipAddressSql(...)");
        return customerShipAddressSql;
    }
    public Object getObject(int i2, boolean z) {
        return getBaseErp().getObject(i2, z);
    }
    public void setDueDateForSalesDetail(SalesDetail salesDetail, int i2) {
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        getBaseErp().setDueDateForSalesDetail(salesDetail, i2);
    }
    public int getCentOfUnitPriceDigit() {
        return getBaseErp().getCentOfUnitPriceDigit();
    }
    public String getSpeCodeTypeDetail(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        String speCodeTypeDetail = getBaseErp().getSpeCodeTypeDetail(salesType);
        Intrinsics.checkNotNullExpressionValue(speCodeTypeDetail, "getSpeCodeTypeDetail(...)");
        return speCodeTypeDetail;
    }
    public void getOrderGrossTotalOnline(int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOrderGrossTotalOnline(i2, responseListener);
    }
    public boolean getProductOnlinePrice() {
        return getBaseErp().getProductOnlinePrice();
    }
    public boolean getIsSalesDetailCurrTypeChange(int i2, int i3) {
        return getBaseErp().isSalesDetailCurrTypeChange(i2, i3);
    }
    public boolean getIsPromotionItemPriceEnabled() {
        return getBaseErp().isPromotionItemPriceEnabled();
    }
    public String getDefaultFirstSpeCode() {
        String defaultFirstSpeCode = getBaseErp().getDefaultFirstSpeCode();
        Intrinsics.checkNotNullExpressionValue(defaultFirstSpeCode, "getDefaultFirstSpeCode(...)");
        return defaultFirstSpeCode;
    }
    public String getDefaultSecondSpeCode() {
        String defaultSecondSpeCode = getBaseErp().getDefaultSecondSpeCode();
        Intrinsics.checkNotNullExpressionValue(defaultSecondSpeCode, "getDefaultSecondSpeCode(...)");
        return defaultSecondSpeCode;
    }
    public boolean getOrderDoReserve() {
        return getBaseErp().getOrderDoReserve();
    }
    public double getLastCurRate(String str, int i2) {
        Intrinsics.checkNotNullParameter(str, "currCode");
        return getBaseErp().getLastCurRate(str, i2);
    }
    public double getVariantRealStock(int i2) {
        return getBaseErp().getVariantRealStock(i2);
    }
    public void getSalesFicheSelectedItemPrice(LastItemPriceSqlParams lastItemPriceSqlParams, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(lastItemPriceSqlParams, "lastItemPriceSqlParams");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getSalesFicheSelectedItemPrice(lastItemPriceSqlParams, responseListener);
    }
    public boolean getIsDueDateByLineEnabled() {
        return getBaseErp().isDueDateByLineEnabled();
    }
    public void getLastCustomerSalesDateOfItem(int i2, int i3, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getLastCustomerSalesDateOfItem(i2, i3, responseListener);
    }
    public boolean showLastPurchaseInformation() {
        return getBaseErp().showLastPurchaseInformation();
    }
    public void getLastPurchaseInfo(LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(lastPurchaseInfoSqlParams, "lastPurchaseInfoSqlParams");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getLastPurchaseInfo(lastPurchaseInfoSqlParams, responseListener);
    }
    public String getLocalCurrencyCode() {
        String localCurrencyCode = getBaseErp().getLocalCurrencyCode();
        Intrinsics.checkNotNullExpressionValue(localCurrencyCode, "getLocalCurrencyCode(...)");
        return localCurrencyCode;
    }
    public boolean getUseVatIncForProductsDontHavePriceCard() {
        return getBaseErp().getUseVatIncForProductsDontHavePriceCard();
    }
    public void getOnlinePrice(OnlineItemPriceParameters onlineItemPriceParameters, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(onlineItemPriceParameters, "params");
        getBaseErp().getOnlinePrice(onlineItemPriceParameters, responseListener);
    }
    public SalesFicheMenuRights getSalesFicheMenuRights() {
        SalesFicheMenuRights salesFicheMenuRights = getBaseErp().getSalesFicheMenuRights();
        Intrinsics.checkNotNullExpressionValue(salesFicheMenuRights, "getSalesFicheMenuRights(...)");
        return salesFicheMenuRights;
    }
    public UserMenuRights getUserMenuRights() {
        UserMenuRights userMenuRights = getBaseErp().userMenuRights;
        Intrinsics.checkNotNullExpressionValue(userMenuRights, "userMenuRights");
        return userMenuRights;
    }

    @Override
    public boolean getVATDefaultValue() {
        return false;
    }

    public boolean getProductOnlineStock() {
        return getBaseErp().getProductOnlineStock();
    }
    public boolean getSalesShowDetail(FicheType ficheType) {
        return getBaseErp().getSalesShowDetail(ficheType);
    }
    public EDocInfoModel getEDocInfo(int i2, int i3) {
        EDocInfoModel eDocInfo = getBaseErp().getEDocInfo(i2, i3);
        Intrinsics.checkNotNullExpressionValue(eDocInfo, "getEDocInfo(...)");
        return eDocInfo;
    }
    public void getCustomerNowAndBeforeBalance(int i2, Class<?> cls, int i3, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(cls, "tableClass");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getCustomerNowAndBeforeBalance(i2, cls, i3, responseListener);
    }
    public void replaceSalesFicheHtml(EmailReplacerModel emailReplacerModel, CustomerBeforeBalance customerBeforeBalance, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(emailReplacerModel, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().replaceSalesFicheHtml(emailReplacerModel, customerBeforeBalance, responseListener);
    }
    public void getSalesDetailStockTracking(SalesDetail salesDetail, int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(salesDetail, "salesDetail");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getSalesDetailStockTracking(salesDetail, i2, responseListener);
    }
    public void showEDocument(int i2, FicheType ficheType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().showEDocument(i2, ficheType, responseListener);
    }
    public void getEDocument(Sales sales, FicheType ficheType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getEDocument(sales, ficheType, responseListener);
    }
    public void getOnlineStockAllInSingleResponse(ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlineStockAllInSingleResponse(responseListener);
    }
    public void getAlternativePromotionItems(String str, String str2, ResponseListener<ArrayList<KeyValuePair>> responseListener) {
        Intrinsics.checkNotNullParameter(str, "campaignCode");
        Intrinsics.checkNotNullParameter(str2, "productCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getAlternativePromotionItems(str, str2, responseListener);
    }
    public boolean addProductOutOfTheOrder(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return getBaseErp().addProductOutOfTheOrder(salesType);
    }
    public List<Double> calculateLineUnitTotals(Sales sales) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        List<Double> calculateLineUnitTotals = getBaseErp().calculateLineUnitTotals(sales);
        Intrinsics.checkNotNullExpressionValue(calculateLineUnitTotals, "calculateLineUnitTotals(...)");
        return calculateLineUnitTotals;
    }
    public boolean firmUseEDespatch() {
        return getBaseErp().firmUseEDespatch();
    }
    public boolean firmUseEInvoice() {
        return getBaseErp().firmUseEInvoice();
    }
    public boolean firmUseEArchive() {
        return getBaseErp().firmUseEArchive();
    }
    public boolean getUseEdespatch(int i2) {
        return getBaseErp().getUseEdespatch(i2);
    }
    public boolean canSendEDocuments() {
        return getBaseErp().canSendEDocuments();
    }
    public boolean canUseEDocumentOperations() {
        return getBaseErp().canUseEDocumentOperations();
    }
    public void printFiche(Context context, FicheType ficheType, int i2, int i3, boolean z, int i4) {
        Intrinsics.checkNotNullParameter(context, "context");
        getBaseErp().printFiche(context, ficheType, i2, i3, z, i4);
    }
    public void isOrderStatusStillEditable(FicheQueryParams ficheQueryParams, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(ficheQueryParams, "ficheQueryParams");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().isOrderStatusStillEditable(ficheQueryParams, responseListener);
    }
    public void insertFicheNoParamBroadcastMessage(int i2, int i3) {
        getBaseErp().insertFicheNoParamBroadcastMessage(i2, i3);
    }
    public void cancelInvoiceFiche(int i2, ResponseListener<Boolean> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().cancelInvoiceFiche(i2, responseListener);
    }
    public void createEDocumentDraft(int i2, FicheType ficheType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().createEDocumentDraft(i2, ficheType, responseListener);
    }
    public void createEDocumentDraftAndSend(int i2, FicheType ficheType, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().createEDocumentDraftAndSend(i2, ficheType, responseListener);
    }
    public void sendRecvEDispatchDocuments(int i2, int i3, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        BaseErp<?> baseErp = getBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Tiger");
        ((Tiger) baseErp).sendRecvEDispatchDocuments(i2, i3, responseListener);
    }
    public void sendRecvEInvoiceDocuments(int i2, int i3, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        BaseErp<?> baseErp = getBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Tiger");
        ((Tiger) baseErp).sendRecvEInvoiceDocuments(i2, i3, responseListener);
    }

    @Override
    public boolean setDeliveryDateAsToday() {
        return false;
    }

    public void sendEArchiveDocuments(int i2, int i3, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        BaseErp<?> baseErp = getBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Tiger");
        ((Tiger) baseErp).sendEArchiveDocuments(i2, i3, responseListener);
    }
    public void getSerialLotList(SerialLotListQueryParam serialLotListQueryParam, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(serialLotListQueryParam, "queryParam");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        BaseErp<?> baseErp = getBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Netsis");
        ((Netsis) baseErp).getSerialLotList(serialLotListQueryParam, responseListener);
    }
    public String getMainUnitCode(String str) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        BaseErp<?> baseErp = getBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Netsis");
        String mainUnitCode = ((Netsis) baseErp).getMainUnitCode(str);
        Intrinsics.checkNotNullExpressionValue(mainUnitCode, "getMainUnitCode(...)");
        return mainUnitCode;
    }
    public ValidationResult controlSecondUnitConversions(Sales sales) {
        BaseErp<?> baseErp = getBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.design.Netsis");
        ValidationResult controlSecondUnitConversions = ((Netsis) baseErp).controlSecondUnitConversions(sales);
        Intrinsics.checkNotNullExpressionValue(controlSecondUnitConversions, "controlSecondUnitConversions(...)");
        return controlSecondUnitConversions;
    }
    public void getOnlineSalesMans(ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlineSalesMans(responseListener);
    }
    public boolean checkRouteVisitOutOfOrder(Context context, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getBaseErp().checkRouteVisitOutOfOrder(context, i2, i3, i4);
    }
    public void clearLocalDataStore() {
        getBaseErp().clearLocalDataStore();
    }
    public void clearObject(int i2) {
        getBaseErp().clearObject(i2);
    }
    public SalesFicheParameters getSalesFicheParameters(SalesType salesType) {
        return getBaseErp().getSalesFicheParameters(salesType);
    }
    public RiskAlert getCustomerRiskAlert(SalesType salesType, int i2) {
        RiskAlert customerRiskAlert = getBaseErp().getCustomerRiskAlert(salesType, i2);
        Intrinsics.checkNotNullExpressionValue(customerRiskAlert, "getCustomerRiskAlert(...)");
        return customerRiskAlert;
    }
    public MatterSettings getMatterSettings(Context context, FicheType ficheType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        MatterSettings matterSettings = getBaseErp().getMatterSettings(context, ficheType);
        Intrinsics.checkNotNullExpressionValue(matterSettings, "getMatterSettings(...)");
        return matterSettings;
    }
    public CustomerDiscount getCustomerDiscountRate(int i2) {
        CustomerDiscount customerDiscountRate = getBaseErp().getCustomerDiscountRate(i2);
        Intrinsics.checkNotNullExpressionValue(customerDiscountRate, "getCustomerDiscountRate(...)");
        return customerDiscountRate;
    }
    public DueDate getDueDate(SalesType salesType, int i2) {
        return getBaseErp().getDueDate(salesType, i2);
    }
    public boolean getVATEnabled() {
        return getBaseErp().getVATEnabled();
    }
    public boolean getUseDocNoUniqueControl(FicheType ficheType) {
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        return getBaseErp().getUseDocNoUniqueControl(ficheType);
    }
    public boolean getOrderChangeOffer() {
        return getBaseErp().getOrderChangeOffer();
    }
    public boolean canApplySelectedCampaign() {
        return getBaseErp().canApplySelectedCampaign();
    }
    public void getUsableCampaignCards(Sales sales, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getUsableCampaignCards(sales, responseListener);
    }
    public void showOnlineCampaign(Sales sales, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().showOnlineCampaign(sales, responseListener);
    }
    public void fillTransferUserWhInfo(Sales sales) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        getBaseErp().fillTransferUserWhInfo(sales);
    }
    public void getPaySumCustomerForDueDate(int i2, int i3, ResponseListener<?> responseListener) {
        getBaseErp().getPaySumCustomerForDueDate(i2, i3, responseListener);
    }
    public void docNoUniqueControl(FicheType ficheType, String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        Intrinsics.checkNotNullParameter(str, "docNo");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().docNoUniqueControl(ficheType, str, responseListener);
    }
    public boolean getCheckCustomerRiskControl() {
        return getBaseErp().getCheckCustomerRiskControl();
    }
    public boolean getCheckCustomerRiskAffect() {
        return getBaseErp().getAffectRisk();
    }
    public void getOrderAvailableAmountsFromDetailRef(int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOrderAvailableAmountsFromDetailRef(i2, responseListener);
    }
    public void loadFicheDefaultProjectCode(FicheDiscountRefProp ficheDiscountRefProp, String str) {
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "projectProp");
        Intrinsics.checkNotNullParameter(str, "defaultProjectRef");
        Intrinsics.checkNotNullParameter(ficheDiscountRefProp, "ficheDiscountRefProp");
        Intrinsics.checkNotNullParameter(str, "defaultProjectRef");
        getBaseErp().loadFicheDefaultProjectCode(ficheDiscountRefProp, str);
    }
    public void getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(ficheType, "ficheType");
        Intrinsics.checkNotNullParameter(matterSettings, "matterSettings");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getMaxMatterNo(ficheType, matterSettings, responseListener);
    }
    public void getOrderValidationList(int i2, int i3, String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(str, "filter");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        Intrinsics.checkNotNullParameter(i2, "i2");
        Intrinsics.checkNotNullParameter(i3, "i3");
        getBaseErp().getOrderValidationList(i2, i3, str, responseListener);
    }
    public String getSpeCodeTypeHeader(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        String speCodeTypeHeader = getBaseErp().getSpeCodeTypeHeader(salesType);
        Intrinsics.checkNotNullExpressionValue(speCodeTypeHeader, "getSpeCodeTypeHeader(...)");
        return speCodeTypeHeader;
    }
    public String getWarehouseUnsentDataTypes(int i2) {
        Intrinsics.checkNotNullParameter(i2, "i2");
        String warehouseUnsentDataTypes = getBaseErp().getWarehouseUnsentDataTypes(i2);
        Intrinsics.checkNotNullExpressionValue(warehouseUnsentDataTypes, "getWarehouseUnsentDataTypes(...)");
        return warehouseUnsentDataTypes;
    }
    public String getCustomerClCode(int i2) {
        Intrinsics.checkNotNullParameter(i2, "i2");
        String customerClCode = getBaseErp().getCustomerClCode(i2);
        Intrinsics.checkNotNullExpressionValue(customerClCode, "getCustomerClCode(...)");
        return customerClCode;
    }
    public boolean getVATDefaultValue(int i2) {
        Intrinsics.checkNotNullParameter(i2, "i2");
        return getBaseErp().getVATDefaultValue(i2);
    }
    public boolean setDeliveryDateAsToday(int i2) {
        Intrinsics.checkNotNullParameter(i2, "i2");
        return getBaseErp().setDeliveryDateAsToday(i2);
    }
    public int getProductPayPlanRef(int i2) {
        Intrinsics.checkNotNullParameter(i2, "i2");
        return getBaseErp().getProductPayPlanRef(i2);
    }
    public boolean insertCabinTrans(int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(i2, "i2");
        Intrinsics.checkNotNullParameter(i3, "i3");
        Intrinsics.checkNotNullParameter(i4, "i4");
        Intrinsics.checkNotNullParameter(i5, "i5");
        return getBaseErp().insertCabinTrans(i2, i3, i4, i5);
    }
    public boolean updateCabinTrans(int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(i2, "i2");
        Intrinsics.checkNotNullParameter(i3, "i3");
        Intrinsics.checkNotNullParameter(i4, "i4");
        Intrinsics.checkNotNullParameter(i5, "i5");
        return getBaseErp().updateCabinTrans(i2, i3, i4, i5);
    }
    public boolean getShowWeightAndVolume(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return getBaseErp().getShowWeightAndVolume(salesType);
    }
    public void getWarehouseItems(int i2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getWarehouseItems(i2, responseListener);
    }
    public void showOnlineTotal(Sales sales, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().showOnlineTotal(sales, responseListener);
    }
    public void checkSalesHasExchangeRates(Sales sales, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().checkSalesHasExchangeRates(sales, responseListener);
    }
    public ShipAddress getDefaultShipAddress(int i2) {
        return getBaseErp().getDefaultShipAddress(i2);
    }
    public ErpInvoiceType getErpTypeFromSales(Sales sales) {
        return getBaseErp().getErpTypeFromSales(sales);
    }
    public NEDispatchInfoModel getLastEDespatchInfoModel(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        return getBaseErp().getLastEDespatchInfoModel(salesType);
    }
    public PaymentLine getSalesFichePayPlanTypeCash(String str) {
        Intrinsics.checkNotNullParameter(str, "payPlanCode");
        return getBaseErp().getSalesFichePayPlanTypeCash(str);
    }
    public Cursor getReceiptFicheRefFromInvoiceRef(ReceiptType receiptType, int i2) {
        Intrinsics.checkNotNullParameter(receiptType, "receiptType");
        Cursor receiptFicheRefFromInvoiceRef = getBaseErp().getReceiptFicheRefFromInvoiceRef(receiptType, i2);
        Intrinsics.checkNotNullExpressionValue(receiptFicheRefFromInvoiceRef, "getReceiptFicheRefFromInvoiceRef(...)");
        return receiptFicheRefFromInvoiceRef;
    }
    public void getOrderAvailableAmountsFromDetailWithRefs(ArrayList<?> arrayList, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOrderAvailableAmountsFromDetailWithRefs(arrayList, responseListener);
    }
    public void getOrderAvailableAmounts(ArrayList<?> arrayList, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOrderAvailableAmounts(arrayList, responseListener);
    }
    public void resetPricesOnDivisionChange(Sales sales, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(sales, "sales");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().resetPricesOnDivisionChange(sales, responseListener);
    }
    public void getOnlineSalesOrderList(SalesType salesType, int i2, boolean z, ArrayList<?> arrayList, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getOnlineSalesOrderList(salesType, i2, z, arrayList, responseListener);
    }
    public void getCheckSeriGroup(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getCheckSeriGroup(i2, i3, salesType, z, i4, z2, responseListener);
    }
    public ClCard getCustomerInfo(int i2) {
        return getBaseErp().getCustomerInfo(i2);
    }
    public String getOrderShipmentType(SalesType salesType) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        String orderShipmentType = getBaseErp().getOrderShipmentType(salesType);
        Intrinsics.checkNotNullExpressionValue(orderShipmentType, "getOrderShipmentType(...)");
        return orderShipmentType;
    }
    public List<ItemVariantStock> getVariantInfo(int i2, int i3, String str) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        List<ItemVariantStock> variantInfo = getBaseErp().getVariantInfo(i2, i3, str);
        Intrinsics.checkNotNullExpressionValue(variantInfo, "getVariantInfo(...)");
        return variantInfo;
    }
    public void updateOrderDispatchable(List<Integer> list, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(list, "orderLogicalRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().updateOrderDispatchable(list, responseListener);
    }
    public void updateOrderUnDispatchable(List<Integer> list, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(list, "orderLogicalRefList");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().updateOrderUnDispatchable(list, responseListener);
    }
    public void rejectOrder(String str, String str2, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(str, "orderNumber");
        Intrinsics.checkNotNullParameter(str2, "customerCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().rejectOrder(str, str2, responseListener);
    }
    public double getOrderMinLimit() {
        return getBaseErp().getOrderMinLimit();
    }
    public boolean eDocControlTypeBranch() {
        return getBaseErp().eDocControlTypeBranch();
    }
    public boolean isDifferentShipAddress() {
        return getBaseErp().isDifferentShipAddress();
    }
    public boolean isRequiredShippingAddressIgnored() {
        Boolean isRequiredShippingAddressIgnored = getBaseErp().isRequiredShippingAddressIgnored();
        Intrinsics.checkNotNullExpressionValue(isRequiredShippingAddressIgnored, "isRequiredShippingAddressIgnored(...)");
        return isRequiredShippingAddressIgnored.booleanValue();
    }
}
