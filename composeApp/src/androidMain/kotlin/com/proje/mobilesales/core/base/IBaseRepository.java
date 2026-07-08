package com.proje.mobilesales.core.base;

import android.content.Context;
import android.database.Cursor;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.ISqlManager;
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
import kotlin.Unit;
import kotlin.coroutines.Continuation;

import java.util.ArrayList;
import java.util.List;

public interface IBaseRepository {
    void addNewCustomer(CustomerNew customerNew, boolean z, ResponseListener<?> responseListener);

    boolean addProductOutOfTheOrder(SalesType salesType);

    List<Double> calculateLineUnitTotals(Sales sales);

    boolean canApplySelectedCampaign();

    boolean canCreateInvoiceForEInvoiceCustomer();

    boolean canOrderCanTransferEInvoiceOrEArchive();

    boolean canSendEDocuments();

    boolean canUseEDocumentOperations();

    void cancelInvoiceFiche(int i2, ResponseListener<Boolean> responseListener);

    boolean checkRouteVisitOutOfOrder(Context context, int i2, int i3, int i4);

    void checkSalesHasExchangeRates(Sales sales, ResponseListener<?> responseListener);

    void clearLocalDataStore();

    void clearObject(int i2);

    ValidationResult controlSecondUnitConversions(Sales sales);

    void createEDocumentDraft(int i2, FicheType ficheType, ResponseListener<?> responseListener);

    void createEDocumentDraftAndSend(int i2, FicheType ficheType, ResponseListener<?> responseListener);

    void deletePenetrationFicheLocalFromSqlManager(int i2, int i3, ResponseListener<Integer> responseListener);

    void deleteSalesFicheLocalFromSqlManager(int i2, SalesType salesType, int i3, ResponseListener<Integer> responseListener);

    void deleteSalesListFicheLocalFromSqlManager(List<Integer> list, SalesType salesType, ResponseListener<List<Integer>> responseListener);

    void docNoUniqueControl(FicheType ficheType, String str, ResponseListener<?> responseListener);

    boolean eDocControlTypeBranch();

    boolean enterPrice();

    void fillTransferUserWhInfo(Sales sales);

    boolean firmUseEArchive();

    boolean firmUseEDespatch();

    boolean firmUseEInvoice();

    void getAlternativePromotionItems(String str, String str2, ResponseListener<ArrayList<KeyValuePair>> responseListener);

    BaseErp<?> getBaseErp();

    BaseRepository getBaseRepository();

    int getCentOfUnitPriceDigit();

    boolean getChangePrice();

    boolean getCheckCustomerRiskAffect();

    boolean getCheckCustomerRiskControl();

    void getCheckSeriGroup(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2, ResponseListener<?> responseListener);

    Disposable getCustomer(int i2, ResponseListener<CustomerNew> responseListener);

    void getCustomerCampaignPoint(int i2);

    String getCustomerClCode(int i2);

    CustomerDiscount getCustomerDiscountRate(int i2);

    Observable<Resource<Boolean>> getCustomerHasDailyOperation(int i2);

    String getCustomerInCharge(String str);

    ClCard getCustomerInfo(int i2);

    void getCustomerNowAndBeforeBalance(int i2, Class<?> cls, int i3, ResponseListener<?> responseListener);

    RiskAlert getCustomerRiskAlert(SalesType salesType, int i2);

    void getCustomerRiskCalculate(int i2, ResponseListener<?> responseListener);

    void getCustomerRiskInfo(int i2, ResponseListener<?> responseListener);

    String getCustomerShipAddressSql(Context context, int i2, String str);

    String getDefaultFirstSpeCode();

    String getDefaultSecondSpeCode();

    ShipAddress getDefaultShipAddress(int i2);

    DueDate getDueDate(SalesType salesType, int i2);

    EDocInfoModel getEDocInfo(int i2, int i3);

    void getEDocument(Sales sales, FicheType ficheType, ResponseListener<?> responseListener);

    ErpType getErp();

    ErpInvoiceType getErpTypeFromSales(Sales sales);

    boolean getIsDueDateByLineEnabled();

    boolean getIsPromotionItemPriceEnabled();

    boolean getIsSalesDetailCurrTypeChange(int i2, int i3);

    void getItemDetailChart(int i2, ResponseListener<?> responseListener);

    void getItemSurplusDiscount(int i2, String str, ResponseListener<?> responseListener);

    double getLastCurRate(String str, int i2);

    void getLastCustomerSalesDateOfItem(int i2, int i3, ResponseListener<?> responseListener);

    NEDispatchInfoModel getLastEDespatchInfoModel(SalesType salesType);

    void getLastPurchaseInfo(LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams, ResponseListener<?> responseListener);

    void getListAllCustomersOnline(String str, ResponseListener<?> responseListener);

    void getLoadCurrencyBalances(int i2, String str, String str2, ResponseListener<?> responseListener);

    int getLocalCurrType();

    String getLocalCurrencyCode();

    ISqlBriteDatabase<?> getLogoSqlBriteDatabase();

    ISqlHelper<?> getLogoSqlHelper();

    String getMainUnitCode(String str);

    MatterSettings getMatterSettings(Context context, FicheType ficheType);

    void getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings, ResponseListener<?> responseListener);

    Object getObject(int i2, boolean z);

    Object getOne(Context context, String str, ResponseListener<CustomerDetail> responseListener, Continuation<? super Unit> continuation);

    void getOnlineItems(ResponseListener<Boolean> responseListener);

    void getOnlinePrice(int i2, int i3, int i4, boolean z, int i5, int i6, String str, int i7, ResponseListener<?> responseListener, String str2, int i8);

    void getOnlinePriceAll(ResponseListener<Boolean> responseListener);

    void getOnlineSalesMans(ResponseListener<?> responseListener);

    void getOnlineSalesOrderList(SalesType salesType, int i2, boolean z, ArrayList<?> arrayList, ResponseListener<?> responseListener);

    void getOnlineShipCustomer(String str, ResponseListener<?> responseListener);

    void getOnlineStockAll(ResponseListener<Boolean> responseListener);

    void getOnlineStockAllInSingleResponse(ResponseListener<Boolean> responseListener);

    void getOnlineUpdateCustomer(ResponseListener<Boolean> responseListener, int i2);

    void getOrderAvailableAmounts(ArrayList<?> arrayList, ResponseListener<?> responseListener);

    void getOrderAvailableAmountsFromDetailRef(int i2, ResponseListener<?> responseListener);

    void getOrderAvailableAmountsFromDetailWithRefs(ArrayList<?> arrayList, ResponseListener<?> responseListener);

    boolean getOrderChangeOffer();

    boolean getOrderDoReserve();

    void getOrderGrossTotalOnline(int i2, ResponseListener<?> responseListener);

    double getOrderMinLimit();

    String getOrderShipmentType(SalesType salesType);

    void getOrderValidationList(int i2, int i3, String str, ResponseListener<?> responseListener);

    void getPaySumCustomerForDueDate(int i2, int i3, ResponseListener<?> responseListener);

    void getProductDetailsInformation(int i2, boolean z, ResponseListener<?> responseListener);

    Disposable getProductGroupsFromSqlManager(String str, ResponseListener<ArrayList<ProductGroupModel>> responseListener);

    Disposable getProductListFromSqlManager(String str, int i2, ResponseListener<ArrayList<Product>> responseListener, String str2);

    ProductListParameter getProductListParameter(SalesType salesType);

    boolean getProductOnlinePrice();

    boolean getProductOnlineStock();

    int getProductPayPlanRef(int i2);

    void getProductTreeItems(ArrayList<String> arrayList, ProductTreeItem productTreeItem, ResponseListener<?> responseListener);

    Cursor getReceiptFicheRefFromInvoiceRef(ReceiptType receiptType, int i2);

    void getSalesAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener<?> responseListener);

    Disposable getSalesCursorToListFromSqlManager(Cursor cursor, ResponseListener<List<SalesShort>> responseListener);

    void getSalesDetailStockTracking(SalesDetail salesDetail, int i2, ResponseListener<?> responseListener);

    SalesFicheMenuRights getSalesFicheMenuRights();

    SalesFicheParameters getSalesFicheParameters(SalesType salesType);

    PaymentLine getSalesFichePayPlanTypeCash(String str);

    void getSalesFicheSelectedItemPrice(LastItemPriceSqlParams lastItemPriceSqlParams, ResponseListener<?> responseListener);

    void getSalesInvoiceExamFromSqlManager(int i2, ResponseListener<Sales> responseListener);

    void getSalesOrderOneFromSqlManager(int i2, ResponseListener<Sales> responseListener);

    void getSalesProductLinePrice(Sales sales, int i2, WcfPriceType wcfPriceType, ResponseListener<?> responseListener);

    void getSalesProductLinesPrice(Sales sales, List<Integer> list, ResponseListener<?> responseListener);

    boolean getSalesShowDetail(FicheType ficheType);

    void getSalesWhTransferFromSqlManager(int i2, ResponseListener<Sales> responseListener);

    void getSaveClCardLastTransDate();

    void getSaveCustomerSortType(int i2);

    int getSaveObject(Object obj);

    void getSaveProductSortType(int i2);

    void getSerialLotList(SerialLotListQueryParam serialLotListQueryParam, ResponseListener<?> responseListener);

    boolean getShowWeightAndVolume(SalesType salesType);

    String getSpeCodeTypeDetail(SalesType salesType);

    String getSpeCodeTypeHeader(SalesType salesType);

    ISqlManager<Object> getSqlManager();

    void getUsableCampaignCards(Sales sales, ResponseListener<?> responseListener);

    boolean getUseDocNoUniqueControl(FicheType ficheType);

    boolean getUseEdespatch(int i2);

    boolean getUseVatIncForProductsDontHavePriceCard();

    User getUser();

    UserMenuRights getUserMenuRights();

    boolean getVATDefaultValue();

    boolean getVATEnabled();

    List<ItemVariantStock> getVariantInfo(int i2, int i3, String str);

    double getVariantRealStock(int i2);

    void getWarehouseItems(int i2, ResponseListener<?> responseListener);

    String getWarehouseUnsentDataTypes(int i2);

    boolean insertCabinTrans(int i2, int i3, int i4, int i5);

    void insertFicheNoParamBroadcastMessage(int i2, int i3);

    boolean isCaseUserMenu();

    boolean isCashUserMenu();

    boolean isChequeUserMenu();

    boolean isCreditCardUserMenu();

    boolean isCustomerReportUserMenu();

    boolean isDeedUserMenu();

    boolean isDifferentShipAddress();

    boolean isDispatchUserMenu();

    boolean isInvoiceUserMenu();

    boolean isOneToOneChangeUserMenu();

    void isOrderStatusStillEditable(FicheQueryParams ficheQueryParams, ResponseListener<?> responseListener);

    boolean isOrderUserMenu();

    boolean isPenetrationUserMenu();

    boolean isReceiptUserMenu();

    boolean isRequiredShippingAddressIgnored();

    boolean isRetailInvoiceUserMenu();

    boolean isReturnDispatchUserMenu();

    boolean isReturnInvoiceUserMenu();

    boolean isSalesUserMenu();

    boolean isVisitUserMenu();

    void loadFicheDefaultProjectCode(FicheDiscountRefProp ficheDiscountRefProp, String str);

    void printFiche(Context context, FicheType ficheType, int i2, int i3, boolean z, int i4);

    void printTransportDispatchNote(Context context, FicheType ficheType, int i2);

    boolean promotionItemPriceEnabled();

    void readOrderFiche(ArrayList<?> arrayList, DataObjectType dataObjectType, Sales sales, List<?> list, ResponseListener<?> responseListener);

    void rejectOrder(String str, String str2, ResponseListener<?> responseListener);

    void replaceSalesFicheHtml(EmailReplacerModel emailReplacerModel, CustomerBeforeBalance customerBeforeBalance, ResponseListener<?> responseListener);

    void resetPricesOnDivisionChange(Sales sales, ResponseListener<?> responseListener);

    boolean salesFicheApplyPromotion(SalesType salesType);

    void savePenetrationFicheFromSqlManager(Penetration penetration, ResponseListener<Boolean> responseListener);

    void saveSalesFicheFromSqlManager(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener);

    void saveStockLastTransDate();

    void sendEArchiveDocuments(int i2, int i3, ResponseListener<?> responseListener);

    void sendRecvEDispatchDocuments(int i2, int i3, ResponseListener<?> responseListener);

    void sendRecvEInvoiceDocuments(int i2, int i3, ResponseListener<?> responseListener);

    boolean setDeliveryDateAsToday();

    void setDueDateForSalesDetail(SalesDetail salesDetail, int i2);

    void setNewMatter(Context context, FicheType ficheType, String str);

    boolean shouldValidateBranch();

    boolean shouldValidateFactory();

    void showEDocument(int i2, FicheType ficheType, ResponseListener<?> responseListener);

    boolean showLastPurchaseInformation();

    void showOnlineCampaign(Sales sales, ResponseListener<?> responseListener);

    void showOnlineTotal(Sales sales, ResponseListener<?> responseListener);

    boolean surplusAmountFromLogoParamValue();

    boolean updateCabinTrans(int i2, int i3, int i4, int i5);

    Object updateCustomer(int i2, Continuation<? super Unit> continuation);

    void updateOrderDispatchable(List<Integer> list, ResponseListener<?> responseListener);

    void updateOrderUnDispatchable(List<Integer> list, ResponseListener<?> responseListener);

    void updatePenetrationFicheFromSqlManager(Penetration penetration, ResponseListener<Boolean> responseListener);

    void updateSalesDetailItemStock(Sales sales);

    void updateSalesFicheFromSqlManager(Sales sales, SalesType salesType, ResponseListener<Boolean> responseListener);

    Object updateShipAddress(int i2, Continuation<? super Unit> continuation);

    boolean useProductGroupTree();
}
