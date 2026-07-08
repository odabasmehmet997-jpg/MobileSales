package com.proje.mobilesales.core.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.RequiresPermission;
import androidx.core.os.EnvironmentCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.ErpModule;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.asynctask.TigerWcfAsyncTask;
import com.proje.mobilesales.core.data.SendCreatorImpl;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.design.Netsis;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.exception.CommunicationNotFoundException;
import com.proje.mobilesales.core.interfaces.*;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.NetsisSelectResult;
import com.proje.mobilesales.core.netsis.NetsisServiceResult;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.printutil.PrintDesignReportType;
import com.proje.mobilesales.core.service.PrintService;
import com.proje.mobilesales.core.sql.ISqlBrite;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.Cabin;
import com.proje.mobilesales.features.cabinoperation.model.dbmodel.CabinTrans;
import com.proje.mobilesales.features.cabinoperation.model.enums.CabinEnums;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCreditDetail;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.collections.receipt.model.ReceiptFicheDefault;
import com.proje.mobilesales.features.collections.receipt.model.enums.ReceiptType;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.CustomerDiscount;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.dbmodel.*;
import com.proje.mobilesales.features.driverinformation.model.NEDispatchInfoModel;
import com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.penetration.model.Penetration;
import com.proje.mobilesales.features.penetration.model.PenetrationLine;
import com.proje.mobilesales.features.penetration.model.database.UserMainPenetration;
import com.proje.mobilesales.features.penetration.model.database.UserPenetration;
import com.proje.mobilesales.features.product.model.*;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheMenuRights;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheParameters;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.settings.model.enums.MatterArea;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import com.proje.mobilesales.features.visit.model.Visit;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.greenrobot.eventbus.EventBus;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static com.proje.mobilesales.core.utils.AppUtils.startMyService;

public abstract class BaseErp<T> implements IErp, Injectable {
    public static Netsis.C261711 AnonymousClass4;
    private final String TAG = "BaseErp";
private com.proje.mobilesales.core.interfaces.di.ErpComponent mErpComponent = null;
protected ErpRights erpRights;
protected ISqlBrite logoSqlBrite;
protected ISqlBriteDatabase logoSqlBriteDatabase;
protected ISqlHelper logoSqlHelper;
protected BaseQueryCreator mBaseQueryCreator;
protected ErpType mErpType;
protected SendCreatorImpl mSendCreator;
public UserSettings mUserSettings;
public User user;
protected UserMenuRights userMenuRights;
private BaseCommunication baseCommunication;
protected BaseErp(ErpType erpType, CommunicationType communicationType) {
    this.mErpType = erpType;
    this.mErpComponent = MobileSales.getInstance ().getAppComponent ().plus (new ErpModule (MobileSales.getInstance ().getmContext (), this.mErpType));
    getLogoSharedPreferences().removeTransferGet ();
}
public static Iterable lambdacheckSalesHasExchangeRates1(ArrayList arrayList) throws Exception {
    return arrayList;
}
public static Iterable lambdaresetPricesOnDivisionChange22(ArrayList arrayList) throws Exception {
    return arrayList;
}
protected static void postStickyEvent(Object obj) {
    EventBus.getDefault ().postSticky (obj);
}
public static void lambdagetOfflinePrintFileNameList0(ResponseListener responseListener, Throwable th) throws Exception {
    responseListener.onError (null != th ? th.getMessage () : "");
}
public static ObservableSource lambdareplaceSalesFicheHtml3(Sales sales, CustomerBeforeBalance customerBeforeBalance) {
    return Observable.just (sales.replaceHtml (sales.getEmailTemplateType (), customerBeforeBalance));
}
public static void lambdareplaceSalesFicheHtml5(EmailReplacerModel emailReplacerModel, ResponseListener responseListener, EmailObject emailObject) {
    emailObject.setSendHTMLContent (true);
    if (!TextUtils.isEmpty (emailReplacerModel.getEmail ())) {
        emailObject.setTo (emailReplacerModel.getEmail ().split ("\\;"));
        emailObject.setIgnoreToListInParams (true);
    } else {
        emailObject.setTo (null);
    }
    responseListener.onResponse (emailObject);
}
public static void lambdareplaceSalesFicheHtml6(ResponseListener responseListener, Throwable th) {
    responseListener.onError (th.getMessage ());
}
public static ObservableSource lambdareplaceReceiptFicheHtml7(EmailReplacerModel emailReplacerModel, EmailTemplateType emailTemplateType, CustomerBeforeBalance customerBeforeBalance) throws Exception {
    return Observable.just (emailReplacerModel.getEmailReplacer ().replaceHtml (emailTemplateType, customerBeforeBalance));
}
public static void lambdareplaceReceiptFicheHtml9(EmailReplacerModel emailReplacerModel, ResponseListener responseListener, Object obj) throws Exception {
    EmailObject emailObject = (EmailObject) obj;
    emailObject.setSendHTMLContent (true);
    if (!TextUtils.isEmpty (emailReplacerModel.getEmail ())) {
        emailObject.setTo (emailReplacerModel.getEmail ().split ("\\;"));
        emailObject.setIgnoreToListInParams (true);
    } else {
        emailObject.setTo (null);
    }
    responseListener.onResponse (emailObject);
}
public static void lambdareplaceReceiptFicheHtml10(ResponseListener responseListener, Throwable th) throws Exception {
    responseListener.onError (th.getMessage ());
}
public static void lambdagetProductTreeItems14(ResponseListener responseListener, Throwable th) throws Exception {
    responseListener.onError (th.getMessage ());
}
public static void lambdagetEDespatchAdditionalInfo18(ResponseListener responseListener, Throwable th) throws Exception {
    responseListener.onError (th.getMessage ());
}
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
public static Observable<Boolean> isInternetOn() {
    NetworkInfo activeNetworkInfo = ((ConnectivityManager) ContextUtils.getmContext ().getSystemService (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo ();
    return Observable.just (Boolean.valueOf (null != activeNetworkInfo && activeNetworkInfo.isConnected ()));
}
public static boolean lambdagetFormGroups19(String str, DefOrder defOrder) {
    return defOrder.getFormGroup ().equals (str);
}
public abstract void addNewCustomer(CustomerNew customerNew);
public abstract void addNewCustomer(CustomerNew customerNew, boolean z);
public abstract void addNewCustomer(CustomerNew customerNew, boolean z, ResponseListener responseListener);
public abstract void addOfflineCustomer(int i, ICustomerSendCompleted iCustomerSendCompleted);
public abstract void addOfflineCustomer(ICustomerSendCompleted iCustomerSendCompleted);
public abstract List<Double> calculateLineUnitTotals(Sales sales);
public abstract boolean canOrderCanTransferEInvoiceOrEArchive();
public abstract boolean canShowLastPurchaseInfo();
public abstract boolean canUseEDocumentOperations();
public abstract void cancelInvoiceFiche(int i, ResponseListener<Boolean> responseListener);
public abstract BaseSelectResult checkNotificationAvailableToDelete(NotificationModel notificationModel);
public abstract boolean checkUserSettings();
public abstract void createEDocumentDraft(int i, FicheType ficheType, ResponseListener responseListener);
public abstract void createEDocumentDraftAndSend(int i, FicheType ficheType, ResponseListener responseListener);
protected abstract boolean createLogoDBPath();
public abstract Observable<Resource<Boolean>> customerHasDailyOperation(int i);
public abstract void deleteFicheSync(int i, DataObjectType dataObjectType);
public abstract BaseSelectResult deleteNotification(NotificationModel notificationModel);
public abstract void deletePenetrationRemote(String str, ResponseListener<Boolean> responseListener);
public abstract void docNoUniqueControl(FicheType ficheType, String str, ResponseListener responseListener);
public abstract void execWmsBarcodeSp(String str, ResponseListener responseListener);
public abstract BaseSelectResult executeLoginFlow();
public abstract void fillTransferUserWhInfo(Sales sales);
public abstract void getAllDataLogo(boolean z, boolean z2);
public abstract void getAlternativePromotionItems(String str, String str2, ResponseListener responseListener);
public abstract void getCasePrintValues(int i, ResponseListener responseListener);
public abstract Disposable getCashPrintValues(int i, ResponseListener responseListener);
public abstract void getCheckSeriGroup(int i, int i2, SalesType salesType, boolean z, int i3, boolean z2, ResponseListener responseListener);
public abstract Disposable getChequePrintValues(int i, ResponseListener responseListener);
public abstract Disposable getCreditCardPrintValues(int i, ResponseListener responseListener);
public abstract void getCurrRate();
public abstract void getCurrRate(ResponseListener responseListener);
public abstract void getCustomerCampaignPoint(int i);
public abstract void getCustomerExtractReport(int i, String str, String str2, String str3, ResponseListener responseListener);
public abstract void getCustomerImage(int i, ResponseListener responseListener);
public abstract void getCustomerNowAndBeforeBalance(int i, Class cls, int i2, ResponseListener responseListener);
public abstract void getCustomerRiskCalculate(int i, ResponseListener responseListener);
public abstract void getCustomerRiskInfo(int i, ResponseListener responseListener);
public abstract ClRisk getCustomerRiskTotals(int i);
public abstract void getCustomerSummarySales(int i, boolean z, ResponseListener<List<T>> responseListener);
public abstract void getCustomerSummaryTopTenProduct(int i, ResponseListener<List<T>> responseListener);
public abstract void getCustomerSummaryTotalBalance(int i, ResponseListener<List<T>> responseListener);
public abstract String getCustomerUnsentDataTypes(int i);
public abstract int getDateInt();
public abstract String getDbName();
public abstract Disposable getDeedPrintValues(int i, ResponseListener responseListener);
public abstract void getDeliveryNotePrintValues(int i, int i2, ResponseListener responseListener);
public abstract void getDiscountCardRatio(Sales sales, String str, ResponseListener responseListener);
public abstract void getDispatchPrintValues(int i, ResponseListener responseListener, int i2);
public boolean getDoCampaignSalesCondition() {
    return true;
}
public abstract void getEDocument(Sales sales, FicheType ficheType, ResponseListener responseListener);
public abstract Disposable getEDocumentContent(String str, ResponseListener responseListener, SalesType salesType, Boolean bool);
public abstract int getFicheCustomerRef(String str, int i);
public abstract void getInvoicePrintValues(int i, ResponseListener responseListener, int i2);
public abstract void getItemImage(int i, ResponseListener responseListener);
public abstract List<ItemStock> getItemStocksWithQuery(String str);
public abstract void getItemSurplusDiscount(int i, String str, ResponseListener responseListener);
public abstract List<ItemVariantStock> getItemVariantStocksWithQuery(String str);
public abstract void getLastCustomerSalesDateOfItem(int i, int i2, ResponseListener responseListener);
public abstract void getLastOrderProducts(int i, boolean z, ResponseListener responseListener);
public abstract void getLastPurchaseInfo(LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams, ResponseListener responseListener);
public abstract String getLoadFicheDefaultCaseFirmNr();
public abstract int getLoadFicheDefaultCaseSql();
public abstract void getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings, ResponseListener responseListener);
public abstract NotificationModel getNotification(int i);
public abstract NotificationModel getNotificationByGuid(String str);
public abstract List<NotifiedUserModel> getNotificationDetailsForSender(int i);
public abstract NotifiedUserModel getNotifiedUser(int i);
public abstract void getOnlineDistributionList(int i, ResponseListener responseListener);
public abstract void getOnlineItems(ResponseListener<Boolean> responseListener);
public abstract void getOnlinePrice(int i, int i2, int i3, boolean z, int i4, int i5, String str, int i6, ResponseListener responseListener, String str2, int i7);
public abstract void getOnlinePriceAll(ResponseListener<Boolean> responseListener);
public abstract void getOnlinePrintFileNameList(FicheType ficheType, ResponseListener responseListener);
public abstract void getOnlineSalesMans(ResponseListener responseListener);
public abstract void getOnlineSalesOrderList(SalesType salesType, int i, boolean z, ArrayList arrayList, ResponseListener responseListener);
public abstract void getOnlineShipCustomer(String str, ResponseListener responseListener);
public abstract void getOnlineStockAll(ResponseListener<Boolean> responseListener);
public abstract void getOnlineStockAllInSingleResponse(ResponseListener<Boolean> responseListener);
public abstract void getOnlineUpdateCustomer(ResponseListener<Boolean> responseListener, int i);
public abstract void getOrderAvailableAmounts(ArrayList arrayList, ResponseListener responseListener);
public abstract void getOrderAvailableAmountsFromDetailRef(int i, ResponseListener responseListener);
public abstract void getOrderAvailableAmountsFromDetailWithRefs(ArrayList arrayList, ResponseListener responseListener);
public abstract void getOrderDetails(int i, ResponseListener responseListener);
public abstract void getOrderFicheStatus(ArrayList arrayList, ResponseListener responseListener);
public abstract void getOrderGrossTotalOnline(int i, ResponseListener responseListener);
public abstract void getOrderPrintValues(int i, ResponseListener responseListener, int i2);
public abstract void getOrderState(int i, ResponseListener<OrderState> responseListener);
public abstract void getOrderStatus(int i);
public abstract void getOrderValidationList(int i, int i2, String str, ResponseListener responseListener);
public abstract void getPaySumCustomerForDueDate(int i, int i2, ResponseListener responseListener);
public int getProductDefaultSelectIndex() {
    return 0;
}
public abstract void getProductDetailCharts(int i, ResponseListener responseListener);
public abstract void getProductDetails(int i, boolean z, ResponseListener responseListener);
public abstract void getProductMonthlySales(int i, ResponseListener<List<T>> responseListener);
public abstract void getProductSerialInfo(String str, ResponseListener responseListener);
public abstract void getProductTopTenCustomer(int i, ResponseListener<List<T>> responseListener);
public abstract void getRecommendedProducts(int i, ResponseListener responseListener);
public abstract void getReportOnline(ResponseListener<UserReportsActivity> responseListener);
public abstract void getRiskLimit(int i, ResponseListener responseListener);
public abstract void getSalesAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener);
public abstract void getSalesDetailStockTracking(SalesDetail salesDetail, int i, ResponseListener responseListener);
public int getSalesFicheApplySalesCondition(SalesType salesType) {
    return 0;
}
public abstract PaymentLine getSalesFichePayPlanTypeCash(String str);
public abstract void getSalesFicheSelectedItemPrice(LastItemPriceSqlParams lastItemPriceSqlParams, ResponseListener responseListener);
public abstract void getSalesInvoiceAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener);
public abstract void getSalesManagers(ResponseListener responseListener);
public abstract void getSalesOrderAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener);
public abstract void getSalesProductLinePrice(Sales sales, int i, WcfPriceType wcfPriceType, ResponseListener responseListener) throws CloneNotSupportedException;
public abstract void getSalesProductLinesPrice(Sales sales, List<Integer> list, ResponseListener responseListener) throws CloneNotSupportedException;
public abstract void getSelectedDistributions(int i, int i2, ResponseListener responseListener);
public abstract String getServerLongTime();
public abstract String getServerTime();
public abstract void getServerTime(ResponseListener responseListener);
public abstract String getTodoEmailBody(TodoInfoDb todoInfoDb);
public abstract List<TransferGetItem> getTransferList();
public abstract void getUsableCampaignCards(Sales sales, ResponseListener responseListener);
public abstract List<NotificationModel> getUserNotifications(int i, int i2, NotificationFilterModel notificationFilterModel);
public abstract List<NotificationModel> getUserNotificationsToBeNotified();
public abstract List<NotificationUserSelectionModel> getUsersConnectedToMe(String str);
public abstract List<ItemVariantStock> getVariantInfo(int i, int i2, String str);
public abstract void getWarehouseItems(int i, ResponseListener responseListener);
public abstract String getWarehouseUnsentDataTypes(int i);
public abstract void getWhTransferPrintValues(int i, ResponseListener responseListener, int i2);
public abstract void getWorTables();
public abstract int insertWorProcess(VisitInfo visitInfo);
public abstract boolean isDeviceInUseByAnotherUser();
public abstract boolean isFicheCustomerTransfered(String str, int i);
public abstract void isOrderStatusStillEditable(FicheQueryParams ficheQueryParams, ResponseListener responseListener);
public boolean isPriceCanBeEnter(boolean z, boolean z2, boolean z3) {
    return z || (z2 && z3);
}
public abstract boolean isPromotionItemPriceEnabled();
public abstract boolean isSalesDetailCurrTypeChange(int i, int i2);
public abstract int isServerTimeInWorkingHours();
public abstract void listAllCustomersOnline(String str, ResponseListener responseListener);
public abstract void loadCurrencyBalances(int i, String str, String str2, ResponseListener responseListener);
public abstract void loadFicheDefaultProjectCode(FicheDiscountRefProp ficheDiscountRefProp, String str);
public abstract void loadFicheDefaultProjectCode(FicheRefProp ficheRefProp, String str);
public abstract void login(String str, String str2, boolean z, boolean z2);
public abstract BaseSelectResult loginFromPeriodicWorker();
public abstract void readInvoiceFiche(int i, DataObjectType dataObjectType, Sales sales, ResponseListener responseListener);
public abstract void readOrderFiche(ArrayList arrayList, DataObjectType dataObjectType, Sales sales, List list, ResponseListener responseListener);
public abstract void rejectOrder(String str, String str2, ResponseListener responseListener);
public abstract void runUserDefinedSQL(UserReportsActivity.UserDefinedResponseListener responseListener, String str, String str2);
public abstract void saveClCardLastTransDate();
public abstract void saveCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener);
public abstract BaseSelectResult saveNotificationAndUsers(NotificationModel notificationModel, List list);
public abstract void saveStockLastTransDate();
public abstract void sendCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener);
public abstract void sendFiche(GroupItem groupItem, ResponseListener responseListener);
public abstract void sendFiche(List<GroupItem> groupItemList, ResponseListener responseListener);
public abstract void sendGpsLog();
public abstract void sendMail(EmailObject emailObject, boolean z);
public abstract void sendMail(DataObjectType dataObjectType, int i);
public abstract void sendStartInfoEnter(StartInfo startInfo);
public abstract void setCustomerInchargeEmailAddress(ClCard clCard);
public abstract void showEDocument(int i, FicheType ficheType, ResponseListener responseListener);
public abstract void showOnlineCampaign(Sales sales, ResponseListener responseListener);
public abstract void showOnlineTotal(Sales sales, ResponseListener responseListener);
public void test() {
}
public abstract void updateCustomer(int i);
public abstract void updateCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener<Boolean> responseListener);
public abstract void updateDataLogo(MobileSalesUpdateType mobileSalesUpdateType, int i);
public abstract NetsisSelectResult updateNotificationAsDeliveredIfAllDelivered(String str);
public abstract BaseSelectResult updateNotificationAsReadIfAllUsersRead(String str);
public abstract TigerSelectResult updateNotifiedUserNotificationAsDelivered(int i);
public abstract BaseSelectResult updateNotifiedUserNotificationAsRead(int i);
public abstract void updateOrderDispatchable(List<Integer> orderIds, ResponseListener responseListener);
public abstract void updateOrderUnDispatchable(List<Integer> orderIds, ResponseListener responseListener);
public abstract void updateSalesDetailItemStock(Sales sales);
public abstract void updateShipAddress(int i);
public abstract boolean updateWorProcess(String str, int i);
public boolean useOnlinePayment() {
    return false;
}
public com.proje.mobilesales.core.interfaces.di.ErpComponent getErpComponent() {
    return this.mErpComponent;
}
protected void createCommunication(ErpType erpType, CommunicationType communicationType) throws CommunicationNotFoundException {
    if (erpType == ErpType.TIGER && communicationType == CommunicationType.WCF) {
        this.baseCommunication = new TigerWcfAsyncTask(communicationType, this.logoSqlBriteDatabase, this.mUserSettings) {
            public void lambdasendCashAndCredit120(GroupItem f1, boolean f2) {
            }

            @Override
            public void lambdasendCaseCashFiche70(GroupItem groupItem, boolean z) {

            }

            private void lambdasendSalesFiche100(GroupItem f1, NetsisServiceResult f2, boolean f3, DataResponse obj) {
            }
            private void lambdasendCaseCashFiche71(GroupItem f1, boolean f2) {
            }
            private void lambdasendOneToOneFiche57(GroupItem f1, boolean f3) {
            }
            private void lambdasendOneToOneFiche49(GroupItem f1, NetsisServiceResult f2, boolean f3, DataResponse obj) {
            }
            private void lambdasendFiche43(GroupItem f1, NetsisSelectResult f2, NetsisSelectResult obj) {
            }
            private void lambdasendFiche46(GroupItem f1, boolean f3) {
            }
            private void lambdatransferFiche28(ResponseListener f1, boolean f2, GroupItem obj) {
            }
            private void getSalesOnlineTotal(NetsisServiceResult order, ResponseListener responseListener) {
            }
            private void getSalesCampaign(NetsisServiceResult order, ResponseListener responseListener) {
            }
            private void updateFiche(NetsisServiceResult netsisServiceResult) {
            }
        };
    } else if (erpType == ErpType.NETSIS && communicationType == CommunicationType.REST) {
        this.baseCommunication = new NetsisRestAsyncTask(communicationType, this.logoSqlBriteDatabase, this.mUserSettings) {
            public Observable<BaseResult> getObservableResult(GroupItem groupItem) {
                return null;
            }
            public Disposable transferFiche(GroupItem groupItem, ResponseListener<GroupItem> responseListener) {
                return null;
            }
            public Disposable transferFiche(GroupItem groupItem, ResponseListener<GroupItem> responseListener, boolean z) {
                return null;
            }
            public Disposable transferFiches(List<GroupItem> list, ResponseListener<GroupItem> responseListener) {
                return null;
            }
            public Disposable transferFiches(List<GroupItem> list, ResponseListener<GroupItem> responseListener, boolean z) {
                return null;
            }
            public void lambdasendSalesFiche100(GroupItem f1, NetsisServiceResult f2, boolean f3, DataResponse obj) {

            }
            public void lambdasendCaseCashFiche71(GroupItem f1, boolean f2) {
            }
            public void lambdasendOneToOneFiche57(GroupItem f1, boolean f3) {
            }
            public void lambdasendOneToOneFiche49(GroupItem f1, NetsisServiceResult f2, boolean f3, DataResponse obj) {
            }
            public void lambdasendFiche43(GroupItem f1, NetsisSelectResult f2, NetsisSelectResult obj) {
            }
            public void lambdasendFiche46(GroupItem f1, boolean f3) {
            }
            public void lambdatransferFiche28(ResponseListener f1, boolean f2, GroupItem obj) {
            }
            public void getSalesOnlineTotal(NetsisServiceResult order, ResponseListener responseListener) {
            }
            public void getSalesCampaign(NetsisServiceResult order, ResponseListener responseListener) {
            }
            private void updateFiche(NetsisServiceResult netsisServiceResult) {
            }
            private void lambdagetGenericRx6(TigerSelectResult tigerSelectResult, String str, ResponseListener responseListener, ObservableEmitter observableEmitter) {
            }
        };
    } else {
        throw new CommunicationNotFoundException(ContextUtils.getStringResource(R.string.exp_2_communication_null));
    }
}
public boolean insertData(List<Class<?>> list, boolean z) {
    return logoSqlBriteDatabase.insert (list, z);
}
protected boolean insertDataList(List<T> list, boolean z) {
    return logoSqlBriteDatabase.insert (list, z);
}
public boolean update(List<T> list, String str, int i) {
    return logoSqlBriteDatabase.update (list, str, i);
}
public SharedPreferencesHelper getLogoSharedPreferences() {
    return new SharedPreferencesHelper (ContextUtils.getmContext ());
}
public com.proje.mobilesales.core.interfaces.di.IComponent getComponent() {
    return mErpComponent;
}
public User getUser() {
    User user = this.user;
    return null == user ? getLogoSharedPreferences().getUser () : user;
}
public UserMenuRights getUserMenuRights() {
    UserMenuRights userMenuRights = this.userMenuRights;
    return null == userMenuRights ? getLogoSharedPreferences().getUserMenuRights () : userMenuRights;
}
public ErpRights getErpRights() {
    ErpRights erpRights = this.erpRights;
    return null == erpRights ? getLogoSharedPreferences().getErpRights () : erpRights;
}
public ErpType getErpType() {
    return this.mErpType;
}
public SendCreatorImpl getSendCreator() {
    return this.mSendCreator;
}
public UserSettings getUserSettings() {
    return this.mUserSettings;
}
public void postEvent(Object obj) {
    if (null != obj) {
        EventBus.getDefault ().post (obj);
    }
}
public ISqlBriteDatabase getLogoSqlBriteDatabase() {
    return this.logoSqlBriteDatabase;
}
public void setLogoSqlBriteDatabase(ISqlBriteDatabase iSqlBriteDatabase) {
    this.logoSqlBriteDatabase = iSqlBriteDatabase;
}
public boolean saveSales(Sales sales, SalesType salesType) {
    long j;
    try {
        sales.setSalesStatus (getSalesStatus(sales).getStatus ());
        try {
            if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
                Order order = new Order ();
                order.convertSales (sales);
                j = logoSqlBriteDatabase.insert (order);
                for (OrderDetail orderDetail : order.getOrderDetails ()) {
                    orderDetail.salesFicheId = (int) j;
                    orderDetail.logicalRef = (int) logoSqlBriteDatabase.insert (orderDetail);
                    insertFicheDetailBarcodesIfAny(orderDetail, DetailBarcodeFicheType.Order.ordinal ());
                }
            } else if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
                WhTransfer whTransfer = new WhTransfer ();
                whTransfer.convertSales (sales);
                j = logoSqlBriteDatabase.insert (whTransfer);
                if (sales.getEDespatch ().isSelect ()) {
                    whTransfer.geteDespatchAdditionalInfo ().salesFicheId = (int) j;
                    whTransfer.geteDespatchAdditionalInfo ().salesType = whTransfer.salesType;
                    logoSqlBriteDatabase.insert (whTransfer.geteDespatchAdditionalInfo ());
                }
                for (WhTransferDetail whTransferDetail : whTransfer.getWhTransferDetails ()) {
                    whTransferDetail.salesFicheId = (int) j;
                    int insert = (int) logoSqlBriteDatabase.insert (whTransferDetail);
                    whTransferDetail.logicalRef = insert;
                    Iterator<Serilot> it = whTransferDetail.getSerilotList ().iterator ();
                    while (it.hasNext ()) {
                        Serilot next = it.next ();
                        next.detailRef = insert;
                        next.slFicheType = SeriLotFicheType.WhTransfer.ordinal ();
                        ArrayList arrayList = new ArrayList ();
                        arrayList.add (next);
                        logoSqlBriteDatabase.insert (arrayList);
                    }
                    insertFicheDetailBarcodesIfAny(whTransferDetail, DetailBarcodeFicheType.WhTransfer.ordinal ());
                }
            } else {
                Invoice invoice = new Invoice ();
                invoice.convertSales (sales);
                j = logoSqlBriteDatabase.insert (invoice);
                if (sales.getEDespatch ().isSelect ()) {
                    invoice.geteDespatchAdditionalInfo ().salesFicheId = (int) j;
                    invoice.geteDespatchAdditionalInfo ().salesType = invoice.salesType;
                    logoSqlBriteDatabase.insert (invoice.geteDespatchAdditionalInfo ());
                }
                for (InvoiceDetail invoiceDetail : invoice.getmInvoiceDetails ()) {
                    invoiceDetail.salesFicheId = (int) j;
                    int insert2 = (int) logoSqlBriteDatabase.insert (invoiceDetail);
                    invoiceDetail.logicalRef = insert2;
                    Iterator<Serilot> it2 = invoiceDetail.getSerilotList ().iterator ();
                    while (it2.hasNext ()) {
                        Serilot next2 = it2.next ();
                        next2.detailRef = insert2;
                        next2.slFicheType = SeriLotFicheType.InvoiceDispatch.ordinal ();
                        ArrayList arrayList2 = new ArrayList ();
                        arrayList2.add (next2);
                        logoSqlBriteDatabase.insert (arrayList2);
                    }
                    insertFicheDetailBarcodesIfAny(invoiceDetail, DetailBarcodeFicheType.InvoiceDispatch.ordinal ());
                }
            }
            insertFicheBroadcastMessage((int) j, salesType.mfType);
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            insertFicheBroadcastMessage(-1, salesType.mfType);
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage(-1, salesType.mfType);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
private void insertFicheDetailBarcodesIfAny(BaseDbSalesFicheDetail baseDbSalesFicheDetail, int i) {
    try {
        ArrayList<String> arrayList = baseDbSalesFicheDetail.ficheDetailBarcodes;
        if (null != arrayList && !arrayList.isEmpty ()) {
            ArrayList arrayList2 = new ArrayList ();
            Iterator<String> it = baseDbSalesFicheDetail.ficheDetailBarcodes.iterator ();
            while (it.hasNext ()) {
                FicheDetailBarcode ficheDetailBarcode = new FicheDetailBarcode ();
                ficheDetailBarcode.setBarcode (it.next ());
                ficheDetailBarcode.setSalesFicheRef (baseDbSalesFicheDetail.salesFicheId);
                ficheDetailBarcode.setDetailRef (baseDbSalesFicheDetail.logicalRef);
                ficheDetailBarcode.setFicheType (i);
                ficheDetailBarcode.setItemCode (baseDbSalesFicheDetail.itemCode);
                arrayList2.add (ficheDetailBarcode);
            }
            logoSqlBriteDatabase.insert (arrayList2);
        }
    } catch (Exception e) {
        Log.e ("BaseErp", "insertFicheDetailBarcodesIfAny", e);
    }
}
public long saveFactSales(Sales sales, SalesType salesType) {
    long j = -1;
    try {
        if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
            Order order = new Order ();
            order.convertSales (sales);
            order.fact = 1;
            j = logoSqlBriteDatabase.insert (order);
            for (OrderDetail orderDetail : order.getOrderDetails ()) {
                orderDetail.salesFicheId = (int) j;
            }
            logoSqlBriteDatabase.insert (order.getOrderDetails ());
        } else {
            Invoice invoice = new Invoice ();
            invoice.convertSales (sales);
            invoice.fact = 1;
            j = logoSqlBriteDatabase.insert (invoice);
            for (InvoiceDetail invoiceDetail : invoice.getmInvoiceDetails ()) {
                invoiceDetail.salesFicheId = (int) j;
                long insert = logoSqlBriteDatabase.insert (invoiceDetail);
                for (Serilot next : invoiceDetail.getSerilotList()) {
                    next.detailRef = (int) insert;
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(next);
                    logoSqlBriteDatabase.insert(arrayList);
                }
            }
        }
    } catch (Exception e) {
        Log.e ("BaseErp", "saveSales: ", e);
    }
    return j;
}
public boolean updateSales(Sales sales, SalesType salesType) {
    return updateSales(sales, salesType, true);
}
public boolean saveCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType) {
    long j = -1;
    try {
        try {
            CashCredit cashCredit = new CashCredit ();
            cashCredit.convertFiche (cashCreditFiche);
            j = logoSqlBriteDatabase.insert (cashCredit);
            for (CashCreditDetail cashCreditDetail : cashCredit.cashCreditDetails) {
                cashCreditDetail.cashCreditId = (int) j;
            }
            logoSqlBriteDatabase.insert (cashCredit.cashCreditDetails);
            insertFicheBroadcastMessage((int) j, receiptType.mfType);
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            insertFicheBroadcastMessage((int) j, receiptType.mfType);
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage((int) j, receiptType.mfType);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public boolean updateCashCreditFiche(CashCreditFiche cashCreditFiche, ReceiptType receiptType) {
    try {
        try {
            CashCredit cashCredit = new CashCredit ();
            cashCredit.convertFiche (cashCreditFiche);
            ArrayList arrayList = new ArrayList ();
            arrayList.add (cashCredit);
            logoSqlBriteDatabase.delete (CashCreditDetail.class, "CASHCREDITID=?", String.valueOf (cashCreditFiche.getLogicalRef ()));
            logoSqlBriteDatabase.update (arrayList, "LOGICALREF", cashCreditFiche.getLogicalRef ());
            for (CashCreditDetail cashCreditDetail : cashCredit.cashCreditDetails) {
                cashCreditDetail.cashCreditId = cashCredit.logicalRef;
                logoSqlBriteDatabase.insert (cashCreditDetail);
            }
            insertFicheBroadcastMessage(cashCreditFiche.getLogicalRef (), receiptType.mfType);
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "updateSales: ", e);
            insertFicheBroadcastMessage(cashCreditFiche.getLogicalRef (), receiptType.mfType);
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage(cashCreditFiche.getLogicalRef (), receiptType.mfType);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public boolean saveCaseFiche(CaseFiche caseFiche, ReceiptType receiptType)  {
    try {
        try {
            CaseCash caseCash = new CaseCash() {

                public SalesType getSalesType() {
                    return null;
                }
            };
            caseCash.convertFiche (caseFiche);
            insertFicheBroadcastMessage((int) logoSqlBriteDatabase.insert (caseCash), receiptType.mfType);
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            insertFicheBroadcastMessage(-1, receiptType.mfType);
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage(-1, receiptType.mfType);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public boolean updateCaseFiche(CaseFiche caseFiche, ReceiptType receiptType) {
    try {
        try {
            CaseCash caseCash = new CaseCash() {

                public SalesType getSalesType() {
                    return null;
                }
            };
            caseCash.convertFiche (caseFiche);
            ArrayList arrayList = new ArrayList ();
            arrayList.add (caseCash);
            logoSqlBriteDatabase.update (arrayList, "LOGICALREF", caseFiche.getLogicalRef ());
            insertFicheBroadcastMessage(caseFiche.getLogicalRef (), receiptType.mfType);
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "updateSales: ", e);
            insertFicheBroadcastMessage(caseFiche.getLogicalRef (), receiptType.mfType);
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage(caseFiche.getLogicalRef (), receiptType.mfType);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public boolean saveChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType) {
    long j = -1;
    try {
        try {
            Chequedeed chequedeed = new Chequedeed ();
            chequedeed.convertFiche (chequeDeedFiche);
            j = logoSqlBriteDatabase.insert (chequedeed);
            for (ChequedeedDetail chequedeedDetail : chequedeed.chequeDeedFicheDetails) {
                chequedeedDetail.chequedeedId = (int) j;
            }
            logoSqlBriteDatabase.insert (chequedeed.chequeDeedFicheDetails);
            insertFicheBroadcastMessage((int) j, receiptType.mfType);
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            insertFicheBroadcastMessage((int) j, receiptType.mfType);
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage((int) j, receiptType.mfType);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public boolean updateChequeDeedFiche(ChequeDeedFiche chequeDeedFiche, ReceiptType receiptType) {
    try {
        try {
            Chequedeed chequedeed = new Chequedeed ();
            chequedeed.convertFiche (chequeDeedFiche);
            ArrayList arrayList = new ArrayList ();
            arrayList.add (chequedeed);
            logoSqlBriteDatabase.delete (ChequedeedDetail.class, "CHEQUEDEEDID=?", String.valueOf (chequeDeedFiche.getLogicalRef ()));
            logoSqlBriteDatabase.update (arrayList, "LOGICALREF", chequeDeedFiche.getLogicalRef ());
            for (ChequedeedDetail chequedeedDetail : chequedeed.chequeDeedFicheDetails) {
                chequedeedDetail.chequedeedId = chequeDeedFiche.getLogicalRef ();
                logoSqlBriteDatabase.insert (chequedeedDetail);
            }
            insertFicheBroadcastMessage(chequeDeedFiche.getLogicalRef (), receiptType.mfType);
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "updateSales: ", e);
            insertFicheBroadcastMessage(chequeDeedFiche.getLogicalRef (), receiptType.mfType);
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage(chequeDeedFiche.getLogicalRef (), receiptType.mfType);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public boolean updateChequDeed(ChequeDeed chequeDeed) {
    logoSqlBriteDatabase.update (chequeDeed.getChequedeed (), "LOGICALREF", chequeDeed.getChequedeed ().logicalRef);
    return true;
}
public boolean updateSales(Sales sales, SalesType salesType, boolean z) {
    boolean z2 = false;
    try {
        if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
            Order order = new Order ();
            order.convertSales (sales);
            ArrayList arrayList = new ArrayList ();
            arrayList.add (order);
            logoSqlBriteDatabase.delete (OrderDetail.class, "SALESFICHEID=?", String.valueOf (sales.getLogicalRef ()));
            logoSqlBriteDatabase.update (arrayList, "LOGICALREF", sales.getLogicalRef ());
            for (OrderDetail orderDetail : order.getOrderDetails ()) {
                orderDetail.salesFicheId = order.logicalRef;
                orderDetail.logicalRef = (int) logoSqlBriteDatabase.insert (orderDetail);
                insertFicheDetailBarcodesIfAny(orderDetail, DetailBarcodeFicheType.Order.ordinal ());
            }
        } else if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
            WhTransfer whTransfer = new WhTransfer ();
            whTransfer.convertSales (sales);
            ArrayList arrayList2 = new ArrayList ();
            arrayList2.add (whTransfer);
            if (sales.getEDespatch ().isSelect ()) {
                logoSqlBriteDatabase.delete (EDispatchAdditionalInfo.class, "SALESFICHEID=? AND SALESTYPE=?", String.valueOf (sales.getLogicalRef ()), String.valueOf (sales.getmSalesType ().getmValue ()));
                whTransfer.geteDespatchAdditionalInfo ().salesFicheId = sales.getLogicalRef ();
                whTransfer.geteDespatchAdditionalInfo ().salesType = sales.getmSalesType ().getmValue ();
                logoSqlBriteDatabase.insert (whTransfer.geteDespatchAdditionalInfo ());
            }
            logoSqlBriteDatabase.delete (WhTransferDetail.class, "SALESFICHEID=?", String.valueOf (sales.getLogicalRef ()));
            logoSqlBriteDatabase.update (arrayList2, "LOGICALREF", sales.getLogicalRef ());
            for (WhTransferDetail whTransferDetail : whTransfer.getWhTransferDetails ()) {
                whTransferDetail.salesFicheId = whTransfer.logicalRef;
                int insert = (int) logoSqlBriteDatabase.insert (whTransferDetail);
                whTransferDetail.logicalRef = insert;
                for (Serilot next : whTransferDetail.getSerilotList()) {
                    next.detailRef = insert;
                    next.slFicheType = SeriLotFicheType.WhTransfer.ordinal();
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(next);
                    logoSqlBriteDatabase.insert(arrayList3);
                }
                insertFicheDetailBarcodesIfAny(whTransferDetail, DetailBarcodeFicheType.WhTransfer.ordinal ());
            }
        } else {
            Invoice invoice = new Invoice ();
            invoice.convertSales (sales);
            ArrayList arrayList4 = new ArrayList ();
            arrayList4.add (invoice);
            if (sales.getEDespatch ().isSelect ()) {
                logoSqlBriteDatabase.delete (EDispatchAdditionalInfo.class, "SALESFICHEID=? AND SALESTYPE=?", String.valueOf (sales.getLogicalRef ()), String.valueOf (sales.getmSalesType ().getmValue ()));
                invoice.geteDespatchAdditionalInfo ().salesFicheId = sales.getLogicalRef ();
                invoice.geteDespatchAdditionalInfo ().salesType = sales.getmSalesType ().getmValue ();
                logoSqlBriteDatabase.insert (invoice.geteDespatchAdditionalInfo ());
            }
            logoSqlBriteDatabase.delete (InvoiceDetail.class, "SALESFICHEID=?", String.valueOf (sales.getLogicalRef ()));
            logoSqlBriteDatabase.update (arrayList4, "LOGICALREF", sales.getLogicalRef ());
            for (InvoiceDetail invoiceDetail : invoice.getmInvoiceDetails ()) {
                invoiceDetail.salesFicheId = invoice.logicalRef;
                int insert2 = (int) logoSqlBriteDatabase.insert (invoiceDetail);
                invoiceDetail.logicalRef = insert2;
                for (Serilot next2 : invoiceDetail.getSerilotList()) {
                    next2.detailRef = insert2;
                    next2.slFicheType = SeriLotFicheType.InvoiceDispatch.ordinal();
                    ArrayList arrayList5 = new ArrayList();
                    arrayList5.add(next2);
                    logoSqlBriteDatabase.insert(arrayList5);
                }
                insertFicheDetailBarcodesIfAny(invoiceDetail, DetailBarcodeFicheType.InvoiceDispatch.ordinal ());
            }
        }
        z2 = true;
        if (z && 0 < sales.isEdit ()) {
            insertFicheBroadcastMessage(sales.getLogicalRef (), salesType.mfType);
        }
    } catch (Exception e) {
        Log.e ("BaseErp", "updateSales: ", e);
    }
    return z2;
}
public boolean deleteSalesLocal(int i, SalesType salesType) {
    Class<?> cls;
    boolean z = false;
    try {
        if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
            cls = Order.class;
        } else if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
            cls = WhTransfer.class;
        } else {
            cls = Invoice.class;
        }
        z = logoSqlBriteDatabase.delete (cls, "LOGICALREF=?", String.valueOf (i));
        Log.d ("BaseErp", "deleteSalesLocal: " + z);
        return z;
    } catch (Exception e) {
        Log.e ("BaseErp", "deleteSalesLocal: ", e);
        return z;
    }
}
public boolean deleteMultiSalesLocal(List<Integer> list, SalesType salesType) {
    Class<?> cls;
    boolean z = false;
    try {
        if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
            cls = Order.class;
        } else if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
            cls = WhTransfer.class;
        } else {
            cls = Invoice.class;
        }
        ISqlBriteDatabase logoSqlBriteDatabase = this.logoSqlBriteDatabase;
        z = logoSqlBriteDatabase.delete (cls, "LOGICALREF IN (" + catIntegerSpecial(",", list) + ")");
        final String sb = "deleteMultiSalesLocal: " +
                z;
        Log.d ("BaseErp", sb);
        return z;
    } catch (Exception e) {
        Log.e ("BaseErp", "deleteMultiSalesLocal: ", e);
        return z;
    }
}
private String catIntegerSpecial(String s, List<Integer> list) {
    return s;
}
public boolean deleteReceiptFicheLocal(int i, ReceiptType receiptType) {
    Class<?> cls;
    boolean z = false;
    try {
        if (!(receiptType == ReceiptType.CASH || receiptType == ReceiptType.CREDIT)) {
            if (receiptType == ReceiptType.CASE) {
                cls = CaseCash.class;
            } else {
                if (!(receiptType == ReceiptType.CHEQUE || receiptType == ReceiptType.DEED)) {
                    cls = null;
                }
                cls = Chequedeed.class;
            }
            z = logoSqlBriteDatabase.delete (cls, "LOGICALREF=?", String.valueOf (i));
            Log.d ("BaseErp", "deleteSalesLocal: " + z);
            return z;
        }
        cls = CashCredit.class;
        z = logoSqlBriteDatabase.delete (cls, "LOGICALREF=?", String.valueOf (i));
        Log.d ("BaseErp", "deleteSalesLocal: " + z);
        return z;
    } catch (Exception e) {
        Log.e ("BaseErp", "deleteSalesLocal: ", e);
        return z;
    }
}
public boolean deleteVisit(int i) {
    try {
        boolean delete = logoSqlBriteDatabase.delete (VisitInfo.class, "ID=?", String.valueOf (i));
        Log.d ("BaseErp", "deleteSalesLocal: " + delete);
        return delete;
    } catch (Exception e) {
        Log.e ("BaseErp", "deleteSalesLocal: ", e);
        return false;
    }
}
public boolean saveVisit(Visit visit) {
    try {
        try {
            VisitInfo visitInfo = new VisitInfo ();
            visitInfo.convertDbType (visit);
            insertFicheBroadcastMessage((int) logoSqlBriteDatabase.insert (visitInfo), FicheType.VISIT.getmValue ());
            return true;
        } catch (Exception e) {
            Log.e ("BaseErp", "saveVisit: ", e);
            insertFicheBroadcastMessage(0, FicheType.VISIT.getmValue ());
            return false;
        }
    } catch (Throwable th) {
        insertFicheBroadcastMessage(0, FicheType.VISIT.getmValue ());
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public int insertVisit(VisitInfo visitInfo) {
    long j;
    try {
        j = logoSqlBriteDatabase.insert (visitInfo);
    } catch (Exception e) {
        Log.e ("BaseErp", "saveVisit: ", e);
        j = 0;
    }
    return (int) j;
}
public boolean updateVisit(Visit visit) {
    boolean z = false;
    try {
        try {
            VisitInfo visitInfo = new VisitInfo ();
            visitInfo.convertDbType (visit);
            if (0 < this.logoSqlBriteDatabase.update (visitInfo, "ID", visit.getId ())) {
                z = true;
            }
        } catch (Exception e) {
            Log.e ("BaseErp", "updateVisit: ", e);
        }
        return z;
    } finally {
        insertFicheBroadcastMessage(visit.getId (), FicheType.VISIT.getmValue ());
    }
}
public boolean saveCustomer(CustomerNew customerNew) {
    try {
        ClCard clCard = new ClCard ();
        clCard.convertDbType (customerNew);
        if (0 >= this.logoSqlBriteDatabase.insert (clCard) || TextUtils.isEmpty (customerNew.getInCharge ().toString ())) {
            return true;
        }
        ClCardIncharge clCardIncharge = new ClCardIncharge ();
        clCardIncharge.convertDbType (customerNew);
        clCardIncharge.setCreatedDate (DateAndTimeUtils.getSqlDate (Boolean.FALSE));
        clCardIncharge.setActive (1);
        logoSqlBriteDatabase.insert (clCardIncharge);
        return true;
    } catch (Exception e) {
        Log.e ("BaseErp", "saveVisit: ", e);
        return false;
    }
}
public boolean updateCustomer(CustomerNew customerNew) {
    boolean z = false;
    try {
        try {
            ClCard clCard = new ClCard ();
            clCard.convertDbType (customerNew);
            if (0 < this.logoSqlBriteDatabase.update (clCard, "LOGICALREF", customerNew.getLogicalRef ())) {
                z = true;
            }
        } catch (Exception e) {
            Log.e ("BaseErp", "updateVisit: ", e);
        }
        return z;
    } finally {
        insertFicheBroadcastMessage(customerNew.getLogicalRef (), FicheType.CUSTOMER.getmValue ());
    }
}
public boolean savePenetration(Penetration penetration) {
    Throwable th;
    Exception e;
    int i = 0;
    boolean z = false;
    long j = 0;
    try {
        try {
            UserMainPenetration userMainPenetration = new UserMainPenetration ();
            userMainPenetration.convertDbType (penetration);
            long insert = logoSqlBriteDatabase.insert (userMainPenetration);
            if (0 < insert) {
                z = true;
            }
            if (z) {
                try {
                    ArrayList arrayList = new ArrayList ();
                    for (PenetrationLine penetrationLine : penetration.getPenetrations ()) {
                        UserPenetration userPenetration = new UserPenetration ();
                        userPenetration.convertDbType (penetrationLine);
                        userPenetration.setPenetFicheId ((int) insert);
                        arrayList.add (userPenetration);
                    }
                    z = logoSqlBriteDatabase.insert (arrayList);
                } catch (Exception e2) {
                    e = e2;
                    j = insert;
                    Log.e ("BaseErp", "savePenetration: ", e);
                    i = (int) j;
                    insertFicheBroadcastMessage(i, FicheType.PENETRATION.getmValue ());
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    j = insert;
                    insertFicheBroadcastMessage((int) j, FicheType.PENETRATION.getmValue ());
                    throw th;
                }
            }
            i = (int) insert;
        } catch (Exception e3) {
            e = e3;
        }
        insertFicheBroadcastMessage(i, FicheType.PENETRATION.getmValue ());
        return z;
    } catch (Throwable th3) {
        th = th3;
    }
    return z;
}
public boolean updatePenetration(Penetration penetration) {
    try {
        boolean z = false;
        try {
            UserMainPenetration userMainPenetration = new UserMainPenetration ();
            userMainPenetration.convertDbType (penetration);
            long update = logoSqlBriteDatabase.update (userMainPenetration, "ID", userMainPenetration.getId ());
            for (PenetrationLine penetrationLine : penetration.getPenetrations ()) {
                UserPenetration userPenetration = new UserPenetration ();
                userPenetration.convertDbType (penetrationLine);
                userPenetration.setPenetFicheId (userMainPenetration.getId ());
                logoSqlBriteDatabase.update (userPenetration, "ID", userPenetration.getId ());
            }
            if (0 < update) {
                z = true;
            }
        } catch (Exception e) {
            Log.e ("BaseErp", "updatePenetration: ", e);
        }
        return z;
    } finally {
        insertFicheBroadcastMessage(penetration.getId (), FicheType.PENETRATION.getmValue ());
    }
}
public boolean deletePenetration(int i) {
    try {
        boolean delete = logoSqlBriteDatabase.delete (UserMainPenetration.class, "ID=?", String.valueOf (i));
        Log.d ("BaseErp", "deletePenetration: " + delete);
        return delete;
    } catch (Exception e) {
        Log.e ("BaseErp", "deletePenetration: ", e);
        return false;
    }
}
public CustomerDiscount getCustomerDiscountRate(int i) {
    return logoSqlHelper.getCustomerDiscountRate (i);
}
public int getCustomerClRef(String r5) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getCustomerClRef(java.lang.String):int");
}
public String getCustomerClCode(int r5) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getCustomerClCode(int):java.lang.String");
}
public String getItemCode(int r5) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getItemCode(int):java.lang.String");
}
public double getLastCurRate(String str, int i) {
    double d;
    Cursor cursor = null;
    try {
        try {
            cursor = logoSqlBriteDatabase.query (ContextUtils.getStringResource (R.string.qry_get_rate),
                    str, StringUtils.convertIntToString (i), StringUtils.convertIntToString (getDateInt()));
            d = (null == cursor || !cursor.moveToFirst ()) ? 0.0d :
                    cursor.getDouble(cursor.getColumnIndex(ContextUtils.getStringResource(R.string.column_rate)));
            if (null != cursor && !cursor.isClosed ()) {
                cursor.close ();
            }
        } catch (Exception e) {
            Log.e ("BaseErp", "getLastCurRate: ", e);
            if (null != cursor && !cursor.isClosed ()) {
                cursor.close ();
            }
            d = 0.0d;
        }
        if (0.0d == d) {
            return 1.0d;
        }
        return d;
    } catch (Throwable th) {
        if (null != cursor && !cursor.isClosed ()) {
            cursor.close ();
        }
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public void insertFicheBroadcastMessage(int i, int i2) {
    Log.d ("BaseErp", "insertFicheBroadcastMessage() called with: ficheId = [" + i + "], ftypeValue = [" + i2 + "]");
    ContextUtils.startDataSyncService ();
    Intent intent = new Intent (IntentExtraName.ACTION_FICHE_INSERT);
    intent.putExtra (IntentExtraName.EXTRA_FICHE_ID, i);
    intent.putExtra (IntentExtraName.EXTRA_FICHE_TYPE, i2);
    LocalBroadcastManager.getInstance (ContextUtils.getmContext ()).sendBroadcast (intent);
}
public void insertFicheNoParamBroadcastMessage(int i, int i2) {
    Log.d ("BaseErp", "insertFicheNoParamBroadcastMessage() called with: ficheId = [" + i + "], ftypeValue = [" + i2 + "]");
    ContextUtils.startDataSyncService ();
    Intent intent = new Intent (IntentExtraName.ACTION_FICHE_NO_PARAM_INSERT);
    intent.putExtra (IntentExtraName.EXTRA_FICHE_ID, i);
    intent.putExtra (IntentExtraName.EXTRA_FICHE_TYPE, i2);
    LocalBroadcastManager.getInstance (ContextUtils.getmContext ()).sendBroadcast (intent);
}
public void getOfflinePrintFileNameList(FicheType ficheType, final ResponseListener<List<DesignJson>> responseListener) {
    List<T> table;
    ArrayList arrayList = new ArrayList ();
    try {
        int mValue = ficheType == FicheType.WHTRANSFER ? PrintDesignReportType.WHTRANSFER.getMValue () : ficheType.getmValue ();
        int defaultPrintDesign = getDefaultPrintDesign(ficheType);
        if (hasDefaultPrintDesign(ficheType)) {
            table = this.logoSqlHelper.getTable (DesignJson.class, "REPORTTYPE=? AND ID=?", new String[]{String.valueOf (mValue), String.valueOf (defaultPrintDesign)});
        } else {
            table = this.logoSqlHelper.getTable (DesignJson.class, "REPORTTYPE=?", new String[]{String.valueOf (mValue)});
        }
        arrayList = (ArrayList) table;
    } catch (Exception e) {
        e.printStackTrace ();
    }
    Observable<T> subscribeOn = (Observable<T>) Observable.just (arrayList).observeOn (AndroidSchedulers.mainThread ()).subscribeOn (Schedulers.io ());
    Objects.requireNonNull (responseListener);
    subscribeOn.subscribe (new BaseErpExternalSyntheticLambda12 (), new Consumer () {
        public void accept(Object obj) {
            try {
                BaseErp.lambdagetOfflinePrintFileNameList0(responseListener, (Throwable) obj);
            } catch (final Exception e) {
                throw new RuntimeException (e);
            }
        }
        @Override
        public Object invoke(Object obj) {

            return obj;
        }
    });
}
public void printFiche(Context context, FicheType ficheType, int i, int i2, int i3) {
    printFiche(context, ficheType, i, i2, true, i3);
}
public void printFiche(Context context, FicheType ficheType, int i, int i2, boolean z, int i3) {
    startMyService (context, PrintService.class);
    Intent intent = new Intent (IntentExtraName.ACTION_FICHE_PRINT);
    intent.putExtra (IntentExtraName.EXTRA_FICHE_ID, i).putExtra (IntentExtraName.EXTRA_FICHE_TYPE, ficheType).putExtra (IntentExtraName.EXTRA_LOCAL_FICHE_ID, i2).putExtra (IntentExtraName.EXTRA_TRANSFER_STATUS, z).putExtra (IntentExtraName.EXTRA_CUSTOMER_REF, i3);
    LocalBroadcastManager.getInstance (context).sendBroadcast (intent);
}
public void printTransportDispatchNote(Context context, FicheType ficheType, int i) {
    startMyService (context, PrintService.class);
    Intent intent = new Intent (IntentExtraName.ACTION_TRANSPORT_FICHE_PRINT);
    intent.putExtra (IntentExtraName.EXTRA_ITEM_ID, i).putExtra (IntentExtraName.EXTRA_FICHE_TYPE, ficheType);
    LocalBroadcastManager.getInstance (context).sendBroadcast (intent);
}
public boolean isFicheCustomerEInvoiceUser(Context r5, int r6) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.isFicheCustomerEInvoiceUser(android.content.Context, int):boolean");
}
public MatterSettings getMatterSettings(Context context, FicheType ficheType) {
    MatterSettings matterSettings = new MatterSettings ();
    if (FicheTypeControlUtils.isFicheTypeOrder (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getOrderPrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getOrderPrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getOrderPrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getOrderPrintMatterArea (context)));
    } else if (FicheTypeControlUtils.isFicheTypeOnlyInvoice (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getInvoicePrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getInvoicePrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getInvoicePrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getInvoicePrintMatterArea (context)));
    } else if (FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getRetailInvoicePrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getRetailInvoicePrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getRetailInvoicePrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getRetailInvoicePrintMatterArea (context)));
    } else if (FicheTypeControlUtils.isFicheTypeDispatch (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getDispatchPrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getDispatchPrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getDispatchPrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getDispatchPrintMatterArea (context)));
    } else if (FicheTypeControlUtils.isFicheTypeCashReceipt (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getCashPrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getCashPrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getCashPrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getCashPrintMatterArea ()));
    } else if (FicheTypeControlUtils.isFicheTypeCreditReceipt (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getCreditCardPrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getCreditCardPrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getCreditCardPrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getCreditCardPrintMatterArea ()));
    } else if (FicheTypeControlUtils.isFicheTypeCheckReceipt (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getChequePrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getChequePrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getChequePrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getChequePrintMatterArea ()));
    } else if (FicheTypeControlUtils.isFicheTypeDeedReceipt (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getDeedPrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getDeedPrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getDeedPrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getDeedPrintMatterArea ()));
    } else if (FicheTypeControlUtils.isFicheTypeCaseReceipt (ficheType)) {
        matterSettings.setUseMatterNo (Preferences.getCasePrintUseMatter (context));
        matterSettings.setFirstMatterNo (Preferences.getCasePrintFirstPrintedMatter (context));
        matterSettings.setLastMatterNo (Preferences.getCasePrintLastPrintedMatter (context));
        matterSettings.setMatterArea (MatterArea.fromMatterArea (Preferences.getCasePrintMatterArea ()));
    }
    return matterSettings;
}
public void setNewMatter(Context context, FicheType ficheType, String str) {
    if (FicheTypeControlUtils.isFicheTypeOrder (ficheType)) {
        Preferences.setOrderPrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeOnlyInvoice (ficheType)) {
        Preferences.setInvoicePrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeOnlyRetailInvoice (ficheType)) {
        Preferences.setRetailInvoicePrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeDispatch (ficheType)) {
        Preferences.setDispatchPrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeCaseReceipt (ficheType)) {
        Preferences.setCasePrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeCashReceipt (ficheType)) {
        Preferences.setCashPrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeCheckReceipt (ficheType)) {
        Preferences.setChequePrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeDeedReceipt (ficheType)) {
        Preferences.setDeedPrintLastPrintedMatter (context, str);
    } else if (FicheTypeControlUtils.isFicheTypeCreditReceipt (ficheType)) {
        Preferences.setCreditCardPrintLastPrintedMatter (context, str);
    }
}
public BaseCommunication getBaseCommunication() {
    return this.baseCommunication;
}
public ProductListParameter getProductListParameter(SalesType salesType) {
    ProductListParameter productListParameter = new ProductListParameter ();
    productListParameter.setWhichMaterialDesc (getUseProductNameDescription());
    productListParameter.setDefaultSelectIndex (getProductDefaultSelectIndex());
    productListParameter.setAutomaticSearch (getProductAutomaticSearch());
    productListParameter.setShowStockExist (getProductShowStockType());
    productListParameter.setSearchType (getProductSearchType());
    productListParameter.setSortType (getProductSortType());
    productListParameter.setOnlinePriceGet (getProductOnlinePrice());
    productListParameter.setOnlineStockGet (getProductOnlineStock());
    productListParameter.setPriority (getPricePriority());
    productListParameter.setShowOnlyStock (getProductShowOnlyStock(salesType));
    productListParameter.setAllStock (getProductShowAllStock());
    productListParameter.setUnitPriceDigit (getCentOfUnitPriceDigit());
    productListParameter.setDefaultAmount (getProductDefaultAmount(salesType));
    productListParameter.setProductOperationDiscount (SalesUtils.isSalesTypeDemandOrWhTransfer (salesType) ? new ProductOperationDiscount () : getSalesFicheOperationDiscount());
    productListParameter.setDefaultExplanation (getSalesFicheHeaderDefaultExplanation(salesType));
    productListParameter.setExplanation (getSalesShowDetailField(salesType, 6, 3, 3));
    productListParameter.setProductSearchOption (getProductSearchOption());
    productListParameter.setAutoAddProduct (getBarcodeAutoAddProduct());
    productListParameter.setShowPrices (!SalesUtils.isSalesTypeDemandOrWhTransfer (salesType));
    if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
        productListParameter.setAllStock (false);
        productListParameter.setShowOnlyStock (true);
        productListParameter.setOnlinePriceGet (false);
    }
    return productListParameter;
}
public boolean getProductOnlinePrice() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCheckPriceInfoOnline));
}
public boolean getProductOnlineStock() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCheckStockInfoOnline));
}
public boolean getUseProductNameDescription() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptWhichMaterialDescriptionWillBeUsed));
}
public boolean getProductAutomaticSearch() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptGeneral_NumberOfDiscountCardsToBeApplied));
}
public boolean getProductShowStockType() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptStockQuantityDisplayType));
}
public int getProductSearchType() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptListSearchSettings));
}
public boolean changeSalesLinesWarehouse() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getLogoParamValue (String.valueOf (ErpParamType.SALES_EDITLINESRCIDX.getmValue ())));
}
public boolean changeDemandLinesWarehouse() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getLogoParamValue (String.valueOf (ErpParamType.DEMMGMT_EDITLINESRCIDX.getmValue ())));
}
public int getProductSortType() {
    return getLogoSharedPreferences().getProductSortChoice ();
}
public int saveObject(Object obj) {
    return LocalDataStore.get ().setLargeData (obj);
}
public Object getObject(int i, boolean z) {
    Object largeData = LocalDataStore.get ().getLargeData (i);
    if (z) {
        clearObject(i);
    }
    return largeData;
}
public void clearObject(int i) {
    LocalDataStore.get ().clearKey (i);
}
public void clearLocalDataStore() {
    LocalDataStore.get ().clearStore ();
}
public void saveProductSortType(int i) {
    getLogoSharedPreferences().saveProductSortChoice (i);
}
public int getCustomerSortType() {
    return getLogoSharedPreferences().getCustomerSortChoice ();
}
public void saveCustomerSortType(int i) {
    getLogoSharedPreferences().saveCustomerSortChoice (i);
}
public int getPricePriority() {
    return StringUtils.convertStringToIntNegative (logoSqlHelper.getParamValue (ParameterTypes.ptPricePriority));
}
public boolean getProductShowOnlyStock(SalesType salesType) {
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        return getProductShowOnlyStockOrder();
    }
    if (SalesUtils.isSalesTypeDispatch (salesType)) {
        return getProductShowOnlyDispatch();
    }
    if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice (salesType)) {
        return getProductShowOnlyStockInvoice();
    }
    return false;
}
public boolean getProductShowOnlyDispatch() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_BillOnlyTheProductsInStock));
}
public String getOrderShipmentType(SalesType salesType) {
    if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice (salesType)) {
        return logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_OrderShipmentType);
    }
    if (SalesUtils.isSalesTypeDispatch (salesType)) {
        return logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_OrderShipmentType);
    }
    return "";
}
public boolean addProductOutOfTheOrder(SalesType salesType) {
    if (SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv (salesType)) {
        return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_DoYouWantToAddProductOutOfTheOrder));
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatchOrOneToOne (salesType)) {
        return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_DoYouWantToAddProductOutOfTheOrder));
    }
    return true;
}
public boolean getProductShowOnlyStockOrder() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_PlaceOrderOnlyForTheProductInStock));
}
public boolean getProductShowOnlyStockInvoice() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_BillOnlyTheProductsInStock));
}
public boolean getProductShowAllStock() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptStockWarehouseToDisplay));
}
public boolean isHideActualStockAmount() {
    return !StringUtils.paramValueFalseCheck(logoSqlHelper.getParamValue(ParameterTypes.ptHideActualStockAmount));
}
public boolean isHideRealStockAmount() {
    return !StringUtils.paramValueFalseCheck(logoSqlHelper.getParamValue(ParameterTypes.ptHideRealStockAmount));
}
public int getCentOfUnitPriceDigit() {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue ("10466"));
}
public ProductOperationDiscount getSalesFicheOperationDiscount() {
    return new ProductOperationDiscount (getDoDiscount(), getSalesFicheDetailDiscountRatioCount(), getSalesFicheDetailDiscountTotalCount());
}
public boolean getDoDiscount() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCanApplyDiscount));
}
public int getSalesFicheHeaderDiscountRatioCount() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptGeneral_NumberOfDiscountProportionalToBeApplied));
}
public int getSalesFicheHeaderDiscountTotalCount() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptGeneral_NumberOfDiscountAmountToBeApplied));
}
public int getSalesFicheHeaderDiscountCardCount() {
    int convertStringToInt = StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptGeneral_NumberOfDiscountCardsToBeApplied));
    if (this.mErpType != ErpType.NETSIS || convertStringToInt <= 0) {
        return convertStringToInt;
    }
    return 1;
}
public int getSalesFicheDetailDiscountRatioCount() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptLine_NumberOfDiscountProportionalToBeApplied));
}
public int getSalesFicheDetailDiscountTotalCount() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptLine_NumberOfDiscountAmountToBeApplied));
}
public int getSalesFicheDetailDiscountCardCount() {
    int convertStringToInt = StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptLine_NumberOfDiscountCardsToBeApplied));
    if (this.mErpType != ErpType.NETSIS || 1 >= convertStringToInt) {
        return convertStringToInt;
    }
    return 1;
}
public String getSalesFicheHeaderDefaultExplanation(SalesType salesType) {
    return getSalesParamSingleFieldString(salesType, ParameterTypes.ptOrder_Description, ParameterTypes.ptInvoice_Description, ParameterTypes.ptDispatch_Description);
}
public String getSalesParamSingleFieldString(SalesType salesType, ParameterTypes parameterTypes, ParameterTypes parameterTypes2, ParameterTypes parameterTypes3) {
    if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
        return logoSqlHelper.getParamValue (parameterTypes);
    }
    if (mErpType != ErpType.NETSIS ? SalesUtils.isSalesTypeDispatchOrReturnDispatchOrOneToOne (salesType) : SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        return logoSqlHelper.getParamValue (parameterTypes3);
    }
    if (!SalesUtils.isSalesTypeWhTransfer (salesType)) {
        return logoSqlHelper.getParamValue (parameterTypes2);
    }
    return "";
}
public boolean getSalesShowDetailField(SalesType salesType, int i, int i2, int i3) {
    if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
        return getSalesShowDetailField(logoSqlHelper.getParamValue (ParameterTypes.ptOrderDefinitionDetailInfo), i);
    }
    if (SalesUtils.isSalesTypeFree (salesType)) {
        return getSalesShowDetailField(logoSqlHelper.getParamValue (ParameterTypes.ptInvoiceDefinitionDetailInfo), i2);
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        return getSalesShowDetailField(logoSqlHelper.getParamValue (ParameterTypes.ptDispatchDefinitionDetailInfo), i3);
    }
    return false;
}
private boolean getSalesShowDetailField(String str, int i) {
    String[] split = str.split (",");
    for (String str2 : split) {
        if (str2.trim ().equals (String.valueOf (i))) {
            return true;
        }
    }
    return false;
}
public ProductSearchOption getProductSearchOption() {
    String paramValue = logoSqlHelper.getParamValue (ParameterTypes.ptItemSearchType);
    if (TextUtils.isEmpty (paramValue)) {
        paramValue = "0,1,2";
    }
    return new ProductSearchOption (paramValue);
}
public boolean getOnlinePrint() {
    if (this.mErpType == ErpType.NETSIS) {
        return false;
    }
    return ContextUtils.checkConnectionWithoutMessage ();
}
public boolean getAddNewCustomer() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptARAPCard));
}
public boolean getNewCustomerState() {
    return "0".equals (this.logoSqlHelper.getParamValue (ParameterTypes.ptNewARAPCard));
}
public boolean getUseWorkTime() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptTakeWorkingHoursIntoAccount));
}
protected boolean getSalesOrderShowDetail() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrderListDisplay));
}
protected boolean getSalesDispatchShowDetail() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatchListDisplay));
}
protected boolean getSalesInvoiceShowDetail() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoiceListDisplay));
}
public int getSortListType() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptARAPListingMethod));
}
public int getClCardTitleShowType() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptTitleSelection));
}
public int getUseNearRp() {
    return ((Integer) logoSqlHelper.getParamValue (ParameterTypes.ptShowARAPCards, SqlReturnType.INT)).intValue ();
}
public double getUseNearDistance() {
    return ((Double) logoSqlHelper.getParamValue (ParameterTypes.ptShowCustomersAvailableWithinTheSpecifiedKmRadius, SqlReturnType.DOUBLE)).doubleValue ();
}
public int getSearchType() {
    return ((Integer) logoSqlHelper.getParamValue (ParameterTypes.ptListSearchSettings, SqlReturnType.INT)).intValue ();
}
public boolean getDivFactWareHouseControl() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getLogoParamValue ("10467"));
}
public boolean getSurplusAmountEnabled() {
    return logoSqlHelper.getLogoParamValue ("MAL_FAZLASI_ISKONTO").equals (ExifInterface.LONGITUDE_EAST);
}
public SalesFicheUserRights getSalesFicheUserRight(SalesType salesType) {
    SalesFicheUserRights salesFicheUserRights = new SalesFicheUserRights ();
    boolean z = false;
    salesFicheUserRights.setEnterPrice (!SalesUtils.isSalesTypeWhTransfer (salesType) && getEnterPrice());
    salesFicheUserRights.setChangePrice (!SalesUtils.isSalesTypeWhTransfer (salesType) && getChangePrice());
    salesFicheUserRights.setChangeVat (getChangeVat());
    salesFicheUserRights.setDoCampaignSalesCondition (getDoCampaignSalesCondition());
    salesFicheUserRights.setEnterPaymentPlan (getEnterPaymentPlan());
    salesFicheUserRights.setCheckPriceEnter (getControlPrice());
    salesFicheUserRights.setLineIntegration (getLineIntegration(salesType));
    salesFicheUserRights.setGeneralCurrencyState (getSalesFicheGeneralCurrencyState(salesType));
    salesFicheUserRights.setLineCurrencyState (getSalesFicheLineCurrencyState(salesType));
    salesFicheUserRights.setApplyCampaign (getSalesFicheApplyCampaign(salesType));
    salesFicheUserRights.setApplySalesCondition (getSalesFicheApplySalesCondition(salesType));
    ErpType erpType = mErpType;
    ErpType erpType2 = ErpType.NETSIS;
    salesFicheUserRights.setDoPromotion (erpType != erpType2 && getSalesFicheApplyPromotion(salesType));
    if (!SalesUtils.isSalesTypeDemandOrWhTransfer (salesType)) {
        salesFicheUserRights.setGetCustomerLastBuyPrice (getCustomerLastBuyPrice());
        salesFicheUserRights.setGetCustomerLastSellPrice (getCustomerLastSellPrice());
        salesFicheUserRights.setGetAllCustomerLastBuyPrice (getAllCustomerLastBuyPrice());
        salesFicheUserRights.setGetAllCustomerLastSellPrice (getAllCustomerLastSellPrice());
    }
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        salesFicheUserRights.setReserve (getOrderDoReserve());
        salesFicheUserRights.setNotChangeReserve (getOrderNotChangeReserve());
        salesFicheUserRights.setChangeOfferOrder (getOrderChangeOffer());
        if (getSalesStatus(salesType) == InvoiceStatus.OFFER) {
            salesFicheUserRights.setOrderStatus (OrderStatus.OFFER.getmStatus ());
        } else {
            salesFicheUserRights.setOrderStatus (OrderStatus.DISPATCHABLE.getmStatus ());
        }
    } else if (SalesUtils.isSalesTypeDemandOrWhTransfer (salesType)) {
        salesFicheUserRights.setChangeVat (false);
        salesFicheUserRights.setDoCampaignSalesCondition (false);
        salesFicheUserRights.setDoPromotion (false);
    } else {
        salesFicheUserRights.setNotEnterPrice (getInvoiceNotEnterPrice());
        salesFicheUserRights.setOnlyInvoiceStockProduct (getInvoiceOnlyInvoiceStockProduct());
        salesFicheUserRights.setChangeInvoiceDate (getInvoiceChangeInvoiceDate());
    }
    salesFicheUserRights.setOpenForm (getClassicOrFormOpen(salesType, false));
    salesFicheUserRights.setOpenClassic (getClassicOrFormOpen(salesType, true));
    salesFicheUserRights.setDivFactWareHouseControl (getDivFactWareHouseControl());
    if (mErpType == erpType2 && getSurplusAmountEnabled()) {
        z = true;
    }
    salesFicheUserRights.setSurplusAmountEnabled (z);
    return salesFicheUserRights;
}
public SalesFicheHeaderFields getSalesFicheHeaderFieldsShow(SalesType salesType, String str, int i, int i2, int i3, boolean z) {
    SalesFicheHeaderFields salesFicheHeaderFields = new SalesFicheHeaderFields (this.mErpType, salesType, str, i, i2, i3, z);
    SalesType salesType2 = SalesType.ONE_TO_ONE_CHANGE;
    if (salesType == salesType2) {
        if (mErpType == ErpType.NETSIS) {
            salesFicheHeaderFields.setDefaultWareHouse (getSalesFicheHeaderDefaultWareHouse(SalesType.INVOICE));
        } else {
            salesFicheHeaderFields.setDefaultWareHouse (getSalesFicheHeaderDefaultWareHouse(SalesType.DISPATCH));
        }
        salesFicheHeaderFields.setDefaultReturnWareHouse (getSalesFicheHeaderDefaultWareHouse(salesType2));
    } else if (salesType == SalesType.RETURN_INVOICE || salesType == SalesType.RETAIL_RETURN_INVOICE || salesType == SalesType.RETURN_DISPATCH) {
        salesFicheHeaderFields.setDefaultReturnWareHouse (getSalesFicheHeaderDefaultWareHouse(salesType));
    } else {
        salesFicheHeaderFields.setDefaultWareHouse (getSalesFicheHeaderDefaultWareHouse(salesType));
    }
    salesFicheHeaderFields.setDefaultBranch (getSalesFicheHeaderDefaultBranch(salesType));
    salesFicheHeaderFields.setDefaultDivision (getSalesFicheHeaderDefaultDivision(salesType));
    salesFicheHeaderFields.setDefaultFactory (getSalesFicheHeaderDefaultFactory(salesType));
    salesFicheHeaderFields.setDefaultDocumentNo (getSalesFicheHeaderDefaultDocumentNo(salesType));
    salesFicheHeaderFields.setDefaultExplanation1 (getSalesFicheHeaderDefaultExplanation(salesType));
    salesFicheHeaderFields.setDefaultProject (getSalesFicheHeaderDefaultProjectCode(salesType));
    salesFicheHeaderFields.setDefaultSpeCode (getSalesFicheHeaderDefaultSpeCode(salesType));
    salesFicheHeaderFields.setDefaultTradeGroup (getSalesFicheHeaderDefaultTradingGroup(salesType));
    salesFicheHeaderFields.setDefaultWarrantyCode (getSalesFicheHeaderDefaultWarrantyCode(salesType));
    salesFicheHeaderFields.setDefaultTaxPayerCode (getUser().getTaxPayerCode ());
    salesFicheHeaderFields.setDefaultTaxPayerName (getUser().getTaxPayerName ());
    salesFicheHeaderFields.setDefaultEInvoiceTypSGK (getUser().geteInvoiceTypSGK ());
    return salesFicheHeaderFields;
}
public boolean getEnterPaymentPlan() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCanEnterPaymentPlan));
}
public int getSalesFicheHeaderDefaultBranch(SalesType salesType) {
    return getSalesParamSingleFieldInt(salesType, ParameterTypes.ptOrder_Division, ParameterTypes.ptInvoice_Division, ParameterTypes.ptDispatch_Division);
}
public int getSalesFicheHeaderDefaultDivision(SalesType salesType) {
    return getSalesParamSingleFieldInt(salesType, ParameterTypes.ptOrder_Department, ParameterTypes.ptInvoice_Department, ParameterTypes.ptDispatch_Department);
}
public int getSalesFicheHeaderDefaultFactory(SalesType salesType) {
    return getSalesParamSingleFieldInt(salesType, ParameterTypes.ptOrder_Factory, ParameterTypes.ptInvoice_Plant, ParameterTypes.ptDispatch_Plant);
}
public String getSalesFicheHeaderDefaultDocumentNo(SalesType salesType) {
    return getSalesParamSingleFieldString(salesType, ParameterTypes.ptOrder_DocumentNo, ParameterTypes.ptInvoice_DocumentNo, ParameterTypes.ptDispatch_DocumentNo);
}
public String getSalesFicheHeaderDefaultSpeCode(SalesType salesType) {
    return getSalesParamSingleFieldString(salesType, ParameterTypes.ptOrder_AuxiliaryCode, ParameterTypes.ptInvoice_AuxiliaryCode, ParameterTypes.ptDispatch_AuxiliaryCode);
}
public String getSalesFicheHeaderDefaultProjectCode(SalesType salesType) {
    return getSalesParamSingleFieldString(salesType, ParameterTypes.ptOrder_ProjectCode, ParameterTypes.ptInvoice_ProjectCode, ParameterTypes.ptDispatch_ProjectCode);
}
public String getSalesFicheHeaderDefaultWarrantyCode(SalesType salesType) {
    return getSalesParamSingleFieldString(salesType, ParameterTypes.ptOrder_AuthorizationCode, ParameterTypes.ptInvoice_AuthorizationCode, ParameterTypes.ptDispatch_AuthorizationCode);
}
public String getSalesFicheHeaderDefaultTradingGroup(SalesType salesType) {
    return getSalesParamSingleFieldString(salesType, ParameterTypes.ptOrder_TradingGroup, ParameterTypes.ptInvoice_TradingGroup, ParameterTypes.ptDispatch_TradingGroup);
}
public int getSalesFicheHeaderDefaultWareHouse(SalesType salesType) {
    if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice (salesType)) {
        return getSalesFicheHeaderDefaultInvoiceWareHouse();
    }
    if (SalesUtils.isSalesTypeDispatch (salesType)) {
        return getSalesFicheHeaderDefaultDispatchWareHouse();
    }
    if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoice (salesType)) {
        return getSalesFicheHeaderDefaultReturnInvoiceWareHouse();
    }
    if (SalesUtils.isSalesTypeReturnDispatch (salesType)) {
        return getSalesFicheHeaderDefaultReturnDispatchWareHouse();
    }
    if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
        return getSalesFicheHeaderDefaultOrderWareHouse();
    }
    if (SalesUtils.isSalesTypeOneToOne (salesType)) {
        return getSalesFicheHeaderDefaultOneToOneReturnWareHouse();
    }
    return 0;
}
public SalesFicheDetailFields getSalesFicheDetailFieldsShow(SalesType salesType, String str, int i, int i2, int i3, boolean z) {
    return new SalesFicheDetailFields (this.mErpType, salesType, str, i, i2, i3, z);
}
public int getSalesFicheHeaderDefaultInvoiceWareHouse() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_SourceWarehouse));
}
public int getSalesFicheHeaderDefaultReturnInvoiceWareHouse() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_ReturnWarehouse));
}
public int getSalesFicheHeaderDefaultDispatchWareHouse() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_SourceWarehouse));
}
public int getSalesFicheHeaderDefaultReturnDispatchWareHouse() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_ReturnWarehouse));
}
public int getSalesFicheHeaderDefaultOrderWareHouse() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_Warehouse));
}
public int getSalesFicheHeaderDefaultOneToOneReturnWareHouse() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptExchange_ReturnWarehouse));
}
public int getSalesParamSingleFieldInt(SalesType salesType, ParameterTypes parameterTypes, ParameterTypes parameterTypes2, ParameterTypes parameterTypes3) {
    int i;
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        i = StringUtils.convertStringToInt (logoSqlHelper.getParamValue (parameterTypes));
    } else if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        i = StringUtils.convertStringToInt (logoSqlHelper.getParamValue (parameterTypes3));
    } else {
        i = StringUtils.convertStringToInt (logoSqlHelper.getParamValue (parameterTypes2));
    }
    if (0 < i) {
        return i;
    }
    return -1;
}
public boolean getSalesParamSingleFieldBoolean(SalesType salesType, ParameterTypes parameterTypes, ParameterTypes parameterTypes2, ParameterTypes parameterTypes3) {
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (parameterTypes));
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (parameterTypes2));
    }
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (parameterTypes3));
}
public boolean getSalesParamSingleFieldIntToBoolean(SalesType salesType, ParameterTypes parameterTypes, ParameterTypes parameterTypes2, ParameterTypes parameterTypes3) {
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (parameterTypes));
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (parameterTypes3));
    }
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (parameterTypes2));
}
public String getSpeCodeTypeHeader(SalesType salesType) {
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        return "14";
    }
    if (SalesUtils.isSalesTypeDemand (salesType)) {
        return "115";
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatchOrOneToOne (salesType)) {
        return "19";
    }
    if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
        return ExifInterface.GPS_MEASUREMENT_2D;
    }
    return "23";
}
public String getSpeCodeTypeDetail(SalesType salesType) {
    if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
        return "16";
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        return "21";
    }
    if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
        return ExifInterface.GPS_MEASUREMENT_3D;
    }
    return "25";
}
public boolean getUseDocNoUniqueControl(FicheType ficheType) {
    if (FicheTypeControlUtils.isFicheTypeOrderOrDemand (ficheType)) {
        return getOrderUseDocNoUniqueControl();
    }
    if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice (ficheType)) {
        return getInvoiceUseDocNoUniqueControl();
    }
    if (FicheTypeControlUtils.isFicheTypeDispatch (ficheType)) {
        return getDispatchUseDocNoUniqueControl();
    }
    return false;
}
public boolean getOrderUseDocNoUniqueControl() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_DocumentNumberControl));
}
public boolean getInvoiceUseDocNoUniqueControl() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_DocumentNumberControl));
}
public boolean getDispatchUseDocNoUniqueControl() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_DocumentNumberControl));
}
public String[] getSalesManagerSalesmanFilter() {
    return StringUtils.splitInitValue (logoSqlHelper.getParamValue (ParameterTypes.ptSalesManagerSalesperson), ";", 3);
}
public int getSalesFicheGeneralCurrencyState(SalesType salesType) {
    return getSalesParamSingleFieldInt(salesType, ParameterTypes.ptOrder_GeneralCurrencyStatus, ParameterTypes.ptDispatch_GeneralCurrencyStatus, ParameterTypes.ptInvoice_GeneralCurrencyStatus);
}
public int getSalesFicheLineCurrencyState(SalesType salesType) {
    return getSalesParamSingleFieldInt(salesType, ParameterTypes.ptOrder_LineCurrencyStatus, ParameterTypes.ptDispatch_LineCurrencyStatus, ParameterTypes.ptInvoce_LineCurrencyStatus);
}
public int getSalesFicheApplyCampaign(SalesType salesType) {
    return getSalesParamSingleFieldInt(salesType, ParameterTypes.ptOrder_Campaign, ParameterTypes.ptInvoice_Campaign, ParameterTypes.ptDispatch_Campaign);
}
public boolean getSalesFicheApplyPromotion(SalesType salesType) {
    return mErpType != ErpType.NETSIS && getSalesParamSingleFieldBoolean(salesType, ParameterTypes.ptOrder_ApplyPromotion, ParameterTypes.ptDispathc_ApplyPromotion, ParameterTypes.ptInvoice_ApplyPromotion);
}
public boolean getInvoiceNotEnterPrice() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_Department));
}
public boolean getInvoiceOnlyInvoiceStockProduct() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_BillOnlyTheProductsInStock));
}
public boolean getInvoiceChangeInvoiceDate() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoiceDateCanBeChanged));
}
public boolean getOnlyOrderExistStockProduct() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_DocumentNumberControl));
}
public boolean getInvoiceLineIntegration() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptLineMergeInInvoice));
}
public boolean getDispatchLineIntegration() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatchLineMerge));
}
public boolean getReturnDispatchSaveOfferState() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptReturnDispatchStatus));
}
public FicheMenuRights getInvoiceFicheMenuRights() {
    FicheMenuRights ficheMenuRights = new FicheMenuRights (getInvoiceEditRight(), getInvoiceDeleteRight(), getInvoiceCopyRight());
    ficheMenuRights.setCancel (getInvoiceCancelFiche());
    return ficheMenuRights;
}
public FicheMenuRights getCashFicheMenuRights() {
    return new FicheMenuRights (getCashEditRight(), getCashDeleteRight(), getCashCopyRight());
}
public FicheMenuRights getCreditCardFicheMenuRights() {
    return new FicheMenuRights (getCreditCardEditRight(), getCreditCardDeleteRight(), getCreditCardCopyRight());
}
public FicheMenuRights getCaseFicheMenuRights() {
    return new FicheMenuRights (getCaseCashEditRight(), getCaseCashDeleteRight(), getCaseCashCopyRight());
}
public FicheMenuRights getChequeFicheMenuRights() {
    return new FicheMenuRights (getChequeEditRight(), getChequeDeleteRight(), getChequeCopyRight());
}
public FicheMenuRights getDeedFicheMenuRights() {
    return new FicheMenuRights (getDeedEditRight(), getDeedDeleteRight(), getDeedCopyRight());
}
public boolean getInvoiceDeleteRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_Delete));
}
public boolean getInvoiceEditRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_Update));
}
public boolean getInvoiceCopyRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_Copy));
}
public FicheMenuRights getDispatchFicheMenuRights() {
    return new FicheMenuRights (getDispatchEditRight(), getDispatchDeleteRight(), getDispatchCopyRight());
}
public boolean getDispatchDeleteRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_Delete));
}
public boolean getDispatchEditRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_Update));
}
public boolean getDispatchCopyRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_Copy));
}
public boolean getCustomerPriceFirst() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowARAPPricesFirstInThePriceList));
}
public boolean getSalesPersonWarrantyCodePriceFirst() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowPricesBasedOnTheAuthCodeOfTheSalesPerson));
}
public boolean getSalesPersonCanSeeTheirOwnOperations() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSalesPersonCanSeeTheirOwnOperations));
}
public boolean getOrderDoReserve() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_Reserved));
}
public boolean getOrderNotChangeReserve() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.prOrder_DoNotChangeReservationRecord));
}
public boolean getOrderChangeOffer() {
    return mErpType == ErpType.NETSIS || StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrderInProposalStatusCanBeEdited));
}
public boolean getOrderFormEnterBeforeOpenHeader() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptWorkingHours1));
}
public boolean getOrderChangeDate() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_Division));
}
public boolean getEnterPrice() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptMakePriceEntry));
}
public boolean getChangePrice() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCanChangeSelectedPrice));
}
public boolean getChangeVat() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptVATCanBeChanged));
}
public boolean getControlPrice() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptPriceControlInSlipEntries));
}
public boolean getSalesPersonWarrantyCodeSetSalesFiche() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_Copy));
}
public boolean getInvoiceCancelFiche() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoiceCancelable));
}
public double getProductDefaultAmount(SalesType salesType) {
    if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
        return StringUtils.convertStringToDouble (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_DefaultQuantity));
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        return StringUtils.convertStringToDouble (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_DefaultQuantity));
    }
    if (SalesUtils.isSalesTypeWhTransfer (salesType)) {
        return 1.0d;
    }
    return StringUtils.convertStringToDouble (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_DefaultQuantity));
}
public boolean getBarcodeAutoAddProduct() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptAutomaticProductAddition));
}
@SuppressLint("Range")
public BarcodeSettings getBarcodeSettings() {
    BarcodeSettings barcodeSettings = new BarcodeSettings();
    try {
        String[] split = StringUtils.split (logoSqlHelper.getParamValue (ParameterTypes.ptBarcodeSettings), ";");
        int i = 0;
        if (split == null) {
            return barcodeSettings;
        }
        assert split != null;
        barcodeSettings.setBarcodeStartCharacter (split.length > 0 ? StringUtils.convertStringToInt (split[0]) : 0);
        barcodeSettings.setBarcodeTotalCharacter (split.length > 1 ? StringUtils.convertStringToInt (split[1]) : 0);
        barcodeSettings.setAmountStartCharacter (split.length > 2 ? StringUtils.convertStringToInt (split[2]) : 0);
        barcodeSettings.setAmountTotalCharacter (split.length > 3 ? StringUtils.convertStringToInt (split[3]) : 0);
        if (split.length > 4) {
            i = StringUtils.convertStringToInt (split[4]);
        }
        barcodeSettings.setDecimalPoint (i);
    } catch (Exception e) {
        Log.d ("BaseErp", "getBarcodeSettings: ", e);
    }
    return barcodeSettings;
}
@RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
public int getOrderShipType() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptSendToSelectedEMail));
}
@RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
public boolean getOrderTypeAddProductShipType() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptWhenSendingToMultipleRecipientsUseCommaBetweenMailAddresses));
}
public SalesFicheMenuRights getSalesFicheMenuRights() {
    return new SalesFicheMenuRights (getOrderEditFicheMenuRightsParamValue(), getOrderDeleteFicheMenuRightsParamValue(), getOrderCopyFicheMenuRightsParamValue(), getInvoiceFicheMenuRights(), getDispatchFicheMenuRights(), getOrderChangeOffer(), getOnlinePrint());
}
public  FicheMenuRights getFicheMenuRights(ReceiptType receiptType) {
    if (receiptType == ReceiptType.CASH) {
        return getCashFicheMenuRights();
    }
    if (receiptType == ReceiptType.CREDIT) {
        return getCreditCardFicheMenuRights();
    }
    if (receiptType == ReceiptType.CASE) {
        return getCaseFicheMenuRights();
    }
    if (receiptType == ReceiptType.CHEQUE) {
        return getChequeFicheMenuRights();
    }
    if (receiptType == ReceiptType.DEED) {
        return getDeedFicheMenuRights();
    }
    return null;
}
public String getOrderDeleteFicheMenuRightsParamValue() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptOrder_Delete);
}
public String getOrderEditFicheMenuRightsParamValue() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptOrder_Update);
}
public String getOrderCopyFicheMenuRightsParamValue() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptOrder_Copy);
}
public FicheMenuRights getOrderOfferFicheMenuRights() {
    return getSalesFicheMenuRights().getMOrderOfferMenuRights ();
}
public FicheMenuRights getOrderDispatchableFicheMenuRights() {
    return getSalesFicheMenuRights().getMOrderDispatchableMenuRights ();
}
public FicheMenuRights getOrderUnDispatchableFicheMenuRights() {
    return getSalesFicheMenuRights().getMOrderUnDispatchableMenuRights ();
}
public boolean getOrderLineIntegration() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_LineMerge));
}
public boolean getCustomerLastSellPrice() {
    if (mErpType == ErpType.NETSIS) {
        return true;
    }
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowLastSalesPriceOfTheCustomer));
}
public boolean getCustomerLastBuyPrice() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowLastPurchasePriceOfTheCustomer));
}
public boolean getAllCustomerLastSellPrice() {
    if (mErpType == ErpType.NETSIS) {
        return true;
    }
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowLastSalesPriceOfAllCustomers));
}
public boolean getAllCustomerLastBuyPrice() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowLastPurchasePriceOfAllCustome));
}
public int getCustomerAccountType() {
    return ((Integer) logoSqlHelper.getParamValue (ParameterTypes.ptARAPType, SqlReturnType.INT)).intValue ();
}
public boolean getCustomerShowDetail() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCustomerListDisplay));
}
public boolean getSalesShowDetail(FicheType ficheType) {
    if (FicheTypeControlUtils.isFicheTypeOrderOrDemand (ficheType)) {
        return getSalesOrderShowDetail();
    }
    if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice (ficheType)) {
        return getSalesInvoiceShowDetail();
    }
    if (FicheTypeControlUtils.isFicheTypeDispatch (ficheType)) {
        return getSalesDispatchShowDetail();
    }
    return false;
}
public boolean useGpsLog() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptLogForGPS));
}
public boolean useGps() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptGPSInOperations));
}
public int getTimeBetweenGpsData() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptSensitivity));
}
public int getTimeBetweenGpsLocation() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptFrequencyOfGPSDataRefresh5Min));
}
public int getSuspiciousActivityDistance() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptSuspiciousActivityDistance));
}
public boolean getSuspiciousActivityAlertControl() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSuspiciousActivityAlert).equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public int getDataExportImportPeriods() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptDataExportImportPeriod));
}
public boolean getPrintLotDetail() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptLotInfos));
}
public boolean getOrderStatusChangeFiche() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptGeneral_NumberOfDiscountProportionalToBeApplied));
}
public boolean getAffectRisk() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptRiskWillAffect));
}
public boolean getCheckCustomerRiskControl() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptARAPRisk));
}
public int getOrderCurrselTotal() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_GeneralCurrencyStatus));
}
public int getOrderCurrselDetail() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_LineCurrencyStatus));
}
public int getInvoiceCurrselTotal() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_GeneralCurrencyStatus));
}
public int getInvoiceCurrselDetail() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptInvoce_LineCurrencyStatus));
}
public int getDispatchCurrselTotal() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_GeneralCurrencyStatus));
}
public int getDispatchCurrselDetail() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_LineCurrencyStatus));
}
public int getCaseCashCurrselDetails() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_GeneralCurrencyStatus));
}
public int getChequeCurrselTotal() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCheck_GeneralCurrencyStatus)) + 1;
}
public int getChequeCurrselDetail() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCheck_LineCurrencyStatus));
}
public int getDeedCurrselTotal() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_GeneralCurrencyStatus)) + 1;
}
public int getDeedCurrselDetail() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_LineCurrencyStatus));
}
public int getCashCurrselTotal() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCash_GeneralCurrencyStatus)) + 1;
}
public int getCashCurrselDetails() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCash_LineCurrencyStatus));
}
public int getCreditCardCurrselTotal() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_GeneralCurrencyStatus)) + 1;
}
public int getCreditCardCurrselDetails() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_LineCurrencyStatus));
}
public  String getMigrationDispatchInvoiceHeader() {
    String paramValue = logoSqlHelper.getParamValue (ParameterTypes.ptARAPCard);
    String paramValue2 = logoSqlHelper.getParamValue (ParameterTypes.ptOrder_TradingGroup);
    try {
        String[] split = paramValue.split (",");
        String[] split2 = paramValue2.split (",");
        split2[13] = split[0];
        split2[15] = split[1];
        split2[3] = split[2];
        split2[16] = split[3];
        split2[4] = split[4];
        split2[0] = split[5];
        String str = "";
        for (String str2 : split2) {
            str = str + str2 + ",";
        }
        return str.substring (0, str.length () - 1);
    } catch (Exception e) {
        Log.e ("BaseErp", "getMigrationDispatchInvoiceHeader: ", e);
        return paramValue2;
    }
}
public SalesFicheParameters getSalesFicheParameters(SalesType salesType) {
    boolean z;
    String str;
    String str2;
    String paramValue = "";
    String paramValue2 = "";
    boolean doDiscount = getDoDiscount();
    int salesFicheHeaderDiscountRatioCount = getSalesFicheHeaderDiscountRatioCount();
    int salesFicheHeaderDiscountTotalCount = getSalesFicheHeaderDiscountTotalCount();
    int salesFicheHeaderDiscountCardCount = getSalesFicheHeaderDiscountCardCount();
    int salesFicheDetailDiscountRatioCount = getSalesFicheDetailDiscountRatioCount();
    int salesFicheDetailDiscountTotalCount = getSalesFicheDetailDiscountTotalCount();
    int salesFicheDetailDiscountCardCount = getSalesFicheDetailDiscountCardCount();
    if (SalesUtils.isSalesTypeOrderOrDemand (salesType)) {
        String paramValue3 = logoSqlHelper.getParamValue (ParameterTypes.ptOrderDefinitionHeadingInfo);
        String paramValue4 = logoSqlHelper.getParamValue (ParameterTypes.ptOrderDefinitionDetailInfo);
        z = !SalesUtils.isSalesTypeDemand (salesType) && doDiscount;
        str = paramValue4;
        str2 = paramValue3;
    } else {
        if (SalesUtils.isSalesTypeFree (salesType)) {
            paramValue = logoSqlHelper.getParamValue (ParameterTypes.ptInvoiceDefinitionHeadingInfo);
            paramValue2 = logoSqlHelper.getParamValue (ParameterTypes.ptInvoiceDefinitionDetailInfo);
        } else if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
            paramValue = logoSqlHelper.getParamValue (ParameterTypes.ptDispatchDefinitionHeadingInfo);
            paramValue2 = logoSqlHelper.getParamValue (ParameterTypes.ptDispatchDefinitionDetailInfo);
        } else {
            str2 = "";
            z = !SalesUtils.isSalesTypeWhTransfer (salesType) && doDiscount;
            str = str2;
        }
        z = doDiscount;
        str2 = paramValue;
        str = paramValue2;
    }
    SalesFicheParameters salesFicheParameters = new SalesFicheParameters (getSalesFicheHeaderFieldsShow(salesType, str2, salesFicheHeaderDiscountRatioCount, salesFicheHeaderDiscountTotalCount, salesFicheHeaderDiscountCardCount, z), getSalesFicheDetailFieldsShow(salesType, str, salesFicheDetailDiscountRatioCount, salesFicheDetailDiscountTotalCount, salesFicheDetailDiscountCardCount, z), getSalesFicheUserRight(salesType));
    salesFicheParameters.getMSalesFicheDetailFields ().setSalesType (salesType);
    salesFicheParameters.getMSalesFicheHeaderFields ().setDeliveryDate (salesFicheParameters.getMSalesFicheDetailFields ().isDeliveryDate ());
    return salesFicheParameters;
}
public boolean getLineIntegration(SalesType salesType) {
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        return getOrderLineIntegration();
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatchOrOneToOne (salesType)) {
        return getDispatchLineIntegration();
    }
    return getInvoiceLineIntegration();
}
public boolean getShowWeightAndVolume(SalesType salesType) {
    if (SalesUtils.isSalesTypeOrder (salesType)) {
        return getOrderShowWeightAndVolume();
    }
    if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        return getDispatchShowWeightAndVolume();
    }
    return getInvoiceShowWeightAndVolume();
}
public boolean getOrderShowWeightAndVolume() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_WeightAndVolumeInfo));
}
public boolean getDispatchShowWeightAndVolume() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch_WeightAndVolumeInfo));
}
public boolean getInvoiceShowWeightAndVolume() {
    return StringUtils.inverseParamValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice_WeightAndVolumeInfo));
}
public int getLocalCurrType() {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue ("LOCALCTYP"));
}
public int getReportCurrType() {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue ("FIRMREPCURR"));
}
public int getSiteId() {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue ("SITEID"));
}
public int getAutoTransfer() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptInfoTransfer));
}
public RiskAlert getCustomerRiskAlert(SalesType r6, int r7) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getCustomerRiskAlert(com.proje.mobilesales.core.enums.SalesType, int):com.proje.mobilesales.core.enums.RiskAlert");
}
public DueDate getDueDate(SalesType salesType, int i) {
    DueDate dueDate = new DueDate ();
    List<T> table = logoSqlHelper.getTable (ClCard.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString (i)});
    if (null != table && !table.isEmpty()) {
        boolean z = false;
        ClCard clCard = (ClCard) table.get (0);
        dueDate.setDueDateCount (clCard.getDueDateCount ());
        dueDate.setDueDateLimit (clCard.getDueDateLimit ());
        dueDate.setDueDateAlert (RiskAlert.fromRiskAlert (clCard.getDueDateTrack ()));
        if (SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv (salesType)) {
            if (1 == clCard.getDueDateControlInv ()) {
                z = true;
            }
            dueDate.setControl (z);
        } else if (SalesUtils.isSalesTypeOrder (salesType)) {
            if (1 == clCard.getDueDateControlOrd ()) {
                z = true;
            }
            dueDate.setControl (z);
        }
    }
    return dueDate;
}
public boolean currRateControl() {
    return this.logoSqlHelper.getCurRateInt ();
}
public AppQuerable getAppQuerable() {
    return this.mBaseQueryCreator;
}
public boolean sendCustomerFicheMail() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSendEMailARAP));
}
public boolean sendOtherMail() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSendToSelectedEMail));
}
public String[] getOtherMailAddress() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptWhenSendingToMultipleRecipientsUseCommaBetweenMailAddresses).split (";");
}
public boolean canSendDataTypeMail(DataObjectType dataObjectType) {
    String str;
    String[] split = logoSqlHelper.getParamValue (ParameterTypes.prDataTypesToBeSendAsEmail).split (",");
    if (!(null == split || 0 == split.length)) {
        switch (C27164.SwitchMapcomprojemobilesalescoreenumsDataObjectType[dataObjectType.ordinal ()]) {
            case 1:
                str = BuildConfig.NETSIS_DEMO_PASSWORD;
                break;
            case 2:
                str = ExifInterface.GPS_MEASUREMENT_2D;
                break;
            case 3:
                str = ExifInterface.GPS_MEASUREMENT_3D;
                break;
            case 4:
                str = "4";
                break;
            case 5:
                str = "5";
                break;
            case 6:
                str = "6";
                break;
            case 7:
                str = "7";
                break;
            case 8:
                str = "8";
                break;
            default:
                str = EnvironmentCompat.MEDIA_UNKNOWN;
                break;
        }
        for (String str2 : split) {
            if (str2.trim ().equals (str)) {
                return true;
            }
        }
    }
    return false;
}
public boolean isOrder() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.ORDER;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isInvoice() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.INVOICE;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isDispatch() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.DISPATCH;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isCash() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.CASH;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isCase() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.CASE;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isCreditCard() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.CREDIT_CARD;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isCheque() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.CHEQUE;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isDeed() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.DEED;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isVisit() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.VISIT;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isReport() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.REPORT;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isCustomerReport() {
    return getUserMenuRights().getMenuValue (UserMenu.CUSTOMERREPORT.getTigerId (), UserMenu.REPORT.isPro ());
}
public boolean isTodo() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.TODO;
    return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
}
public boolean isRetailInvoice() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptRetailInvoice));
}
public boolean isMarketingMessage() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.MARKETING_MESSAGE;
    return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
}
public boolean isGpsLocationInfo() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.GPS_LOCATION_INFO;
    return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
}
public boolean isInvoiceAvgCalc() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.INVOICE_AVG_CALC;
    return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
}
public boolean isNewCustomer() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.NEW_CUSTOMER;
    return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
}
public boolean isReturnInvoice() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.RETURN_INVOICE;
    return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
}
public boolean isReturnDispatch() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.RETURN_DISPATCH;
    return userMenuRights.getMenuValue(userMenu.getTigerId(), userMenu.isPro());
}
public boolean isOneToOneChange() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.ONE_TO_ONE;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isOrderClassic() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.ORDER_CLASSIC;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isOrderForm() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.ORDER_FORM;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isInvoiceClassic() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.INVOICE_CLASSIC;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isInvoiceForm() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.INVOICE_FORM;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isDispatchClassic() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.DISPATCH_CLASSIC;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isDispatchForm() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.DISPATCH_FORM;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isPenetration() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.PENETRATION;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isDist() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.DIST;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isDemand() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.DEMAND;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isWorkInfo() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.WORK_INFO;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean isProducts() {
    UserMenuRights userMenuRights = getUserMenuRights();
    UserMenu userMenu = UserMenu.PRODUCTS;
    return userMenuRights.getMenuValue (userMenu.getTigerId (), userMenu.isPro ());
}
public boolean getClassicOrFormOpen(SalesType salesType, boolean z) {
    return SalesUtils.isSalesTypeOrderOrDemand (salesType) ? z ? isOrderClassic() : isOrderForm() : SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType) ? z ? isDispatchClassic() : isDispatchForm() : z ? isInvoiceClassic() : isInvoiceForm();
}
public boolean isReceipt() {
    return isCheque() || isCash() || isCreditCard() || isCase() || isDeed();
}
public boolean isSales() {
    return isInvoice() || isOneToOneChange() || isInvoiceForm() || isInvoiceClassic() || isDispatch() || isDispatchForm() || isDispatchClassic() || isReturnDispatch();
}
public boolean isOtherMenu() {
    return isGpsLocationInfo() || isInvoiceAvgCalc() || isWorkInfo();
}
public ISqlHelper getLogoSqlHelper() {
    return this.logoSqlHelper;
}
public void setLogoSqlHelper(ISqlHelper iSqlHelper) {
    this.logoSqlHelper = iSqlHelper;
}
public boolean getVATEnabled() {
    String paramValue = logoSqlHelper.getParamValue (ParameterTypes.pt_VATEnabled);
    if (null == paramValue || paramValue.isEmpty ()) {
        return true;
    }
    return StringUtils.paramValueNumberCheck (paramValue);
}
public int getDayOfWeek() {
    switch (Calendar.getInstance ().get (7)) {
        case 1:
            return 7;
        case 2:
            return 1;
        case 3:
            return 2;
        case 4:
            return 3;
        case 5:
            return 4;
        case 6:
            return 5;
        case 7:
            return 6;
        default:
            return 0;
    }
}
public boolean getRouteVisitinOutOfOrder() {
    if (1 == this.getCustomerAccountType()) {
        return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptRouteVisitinOutOfOrder));
    }
    return true;
}
public boolean checkRouteVisitOutOfOrder(Context r16, int r17, int r18, int r19) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.checkRouteVisitOutOfOrder(android.content.Context, int, int, int):boolean");
}
public boolean getOffRoadVisit() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptOffRouteVisit));
}
public boolean getRouteVisitOutOfOrder() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptRouteVisitinOutOfOrder));
}
public boolean getVATDefaultValue(int i2) {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.pt_VATDefaultValue));
}
public boolean getCustomerGpsEditMode() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptARAPCards_GPSEditMode));
}
public boolean isOfflineCustomersExist() {
    return !this.logoSqlHelper.getTable(ClCard.class, "ISTRANSFER = ?", new String[]{"0"}).isEmpty();
}
private WorkingHoursOption getWorkingHoursOption() {
    String paramValue = logoSqlHelper.getParamValue (ParameterTypes.ptWorkingHoursOptions);
    if (paramValue.isEmpty ()) {
        paramValue = "2,3,4,5";
    }
    return new WorkingHoursOption (paramValue);
}
public String getWorkingHours1() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptWorkingHours1);
}
public String getWorkingHours2() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptWorkingHours2);
}
private boolean getLoginInWorkingHours() {
    return getWorkingHoursOption().isLogin ();
}
private boolean getDataEntryInWorkingHours() {
    return getWorkingHoursOption().isDataEntry ();
}
private boolean getTransferGetInWorkingHours() {
    return getWorkingHoursOption().isTransferGet ();
}
private boolean getTransferSendInWorkingHours() {
    return getWorkingHoursOption().isTransferSend ();
}
private boolean getReportInWorkingHours() {
    return getWorkingHoursOption().isReport ();
}
private boolean getVisitInWorkingHours() {
    return getWorkingHoursOption().isVisit ();
}
private boolean getPenetrationInWorkingHours() {
    return getWorkingHoursOption().isPenetration ();
}
private boolean getReceiptInWorkingHours() {
    return getWorkingHoursOption().isReceipt ();
}
private boolean getSalesInWorkingHours() {
    return getWorkingHoursOption().isSales ();
}
private boolean getOrderInWorkingHours() {
    return getWorkingHoursOption().isOrder ();
}
public String checkWorkTimeControl(WorkTimeControlProcessType workTimeControlProcessType) {
    if (getUseWorkTime()) {
        Log.d ("Server_Time", "Working Hours:" + getWorkingHours1() + "-" + getWorkingHours2());
        if (!isCurrentTimeBetweenWorkingHours()) {
            return controlWorkingHourOptions(workTimeControlProcessType);
        }
    }
    return "";
}
private boolean isCurrentTimeBetweenWorkingHours() {
    return DateAndTimeUtils.isDateBetweenDatesWithFormat (ContextUtils.checkConnectionWithoutMessage () ? getServerTime() : DateAndTimeUtils.nowTime2 (), getWorkingHours1(), getWorkingHours2(), "HH:mm:ss");
}
private String getFicheStatusParam() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptFicheStatusOffer);
}
public InvoiceStatus getSalesStatus(Sales sales) {
    return this.getSalesStatus(sales.getmSalesType ());
}
public InvoiceStatus getSalesStatus(SalesType salesType) {
    InvoiceStatus invoiceStatus = InvoiceStatus.ACTUAL;
    switch (C27164.SwitchMapcomprojemobilesalescoreenumsSalesType[salesType.ordinal ()]) {
        case 1:
            return getFicheStatus(FicheStatusOptions.ORDER);
        case 2:
        case 3:
            return getFicheStatus(FicheStatusOptions.INVOICE);
        case 4:
            return getFicheStatus(FicheStatusOptions.RETURN_INVOICE);
        case 5:
            return getFicheStatus(FicheStatusOptions.DISPATCH);
        case 6:
            return getFicheStatus(FicheStatusOptions.RETURN_DISPATCH);
        case 7:
            return getFicheStatus(FicheStatusOptions.INVOICE);
        case 8:
            return getFicheStatus(FicheStatusOptions.RETURN_INVOICE);
        default:
            return invoiceStatus;
    }
}
public InvoiceStatus getCreditCardStatus() {
    return getFicheStatus(FicheStatusOptions.CREDIT_CART);
}
public InvoiceStatus getCashStatus() {
    return getFicheStatus(FicheStatusOptions.CASH);
}
public InvoiceStatus getCaseCashStatus() {
    return getFicheStatus(FicheStatusOptions.CASE_CASH);
}
public InvoiceStatus getChequeStatus() {
    return getFicheStatus(FicheStatusOptions.CHEQUE);
}
public InvoiceStatus getDeedStatus() {
    return getFicheStatus(FicheStatusOptions.DEED);
}
private InvoiceStatus getFicheStatus(FicheStatusOptions ficheStatusOptions) {
    InvoiceStatus invoiceStatus = InvoiceStatus.ACTUAL;
    String[] split = getFicheStatusParam().split (",");
    for (String str : split) {
        if (str.trim ().equals (String.valueOf (ficheStatusOptions.getmValue ()))) {
            return InvoiceStatus.OFFER;
        }
    }
    return invoiceStatus;
}
public boolean getSevkiyatHesabiTeslimatCari() {
    return logoSqlHelper.getLogoParamValue ("TESLIMAT_CARI").equals (ExifInterface.LONGITUDE_EAST);
}
public boolean isDifferentShipAddress() {
    return logoSqlHelper.getLogoParamValue ("FARKLI_TESLIM_YERI").equals (ExifInterface.LONGITUDE_EAST);
}
public boolean showNegativeBalance() {
    return logoSqlHelper.getLogoParamValue ("EKSI_BAKIYE_GOSTERIM").equals (ExifInterface.LONGITUDE_EAST);
}
public boolean stopNegativeBalance() {
    return logoSqlHelper.getLogoParamValue ("EKSIDE_CIKMA").equals (ExifInterface.LONGITUDE_EAST);
}
public boolean isEInvoiceCustomerPrintInvoice() {
    return 1 == StringUtils.convertStringToInt (this.logoSqlHelper.getParamValue (ParameterTypes.ptEInvoiceCustomerPrint));
}
public boolean getCashEditRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCash_Update));
}
public boolean getCashDeleteRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCash_Delete));
}
public boolean getCashCopyRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCash_Copy));
}
public boolean getCreditCardEditRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_Update));
}
public boolean getCreditCardDeleteRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_Delete));
}
public boolean getCreditCardCopyRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_Copy));
}
public boolean getChequeEditRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCheck_Update));
}
public boolean getChequeDeleteRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCheck_Delete));
}
public boolean getChequeCopyRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCheck_Copy));
}
public boolean getDeedEditRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_Update));
}
public boolean getDeedDeleteRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_Delete));
}
public boolean getDeedCopyRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_Copy));
}
public boolean getCaseCashEditRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_Update));
}
public boolean getCaseCashDeleteRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_Delete));
}
public boolean getCaseCashCopyRight() {
    return StringUtils.paramValueTrueCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_Copy));
}
public ReceiptFicheDefault getFicheDefault(ReceiptType receiptType) {
    ReceiptFicheDefault receiptFicheDefault = new ReceiptFicheDefault ();
    int i = C27164.f1164x3afa6613[receiptType.ordinal ()];
    if (i == 1) {
        return new ReceiptFicheDefault (getCashDefaultDivision(), getCashDefaultDepartment(), getCashDefaultSpeCode(), getCashDefaultProjectCode(), getCashDefaultCyphCode(), getCashDefaultTradingGroup(), "", "");
    }
    if (i == 2) {
        return new ReceiptFicheDefault (getCreditDefaultDivision(), getCreditDefaultDepartment(), getCreditDefaultSpeCode(), getCreditDefaultProjectCode(), getCreditDefaultCyphCode(), getCreditDefaultTradingGroup(), "", getDefaultBank());
    }
    if (i == 3) {
        return new ReceiptFicheDefault (getCaseDefaultDivision(), getCaseDefaultDepartment(), getCaseDefaultSpeCode(), getCaseDefaultProjectCode(), getCaseDefaultCyphCode(), getCaseDefaultTradingGroup(), getCaseDefaultCase(), "");
    }
    if (i == 4) {
        return new ReceiptFicheDefault (getDeedDefaultDivision(), getDeedDefaultDepartment(), getDeedDefaultSpeCode(), getDeedDefaultProjectCode(), getDeedDefaultCyphCode(), getDeedDefaultTradingGroup(), "", "");
    }
    if (i != 5) {
        return receiptFicheDefault;
    }
    return new ReceiptFicheDefault (getChequeDefaultDivision(), getChequeDefaultDepartment(), getChequeDefaultSpeCode(), getChequeDefaultProjectCode(), getChequeDefaultCyphCode(), getChequeDefaultTradingGroup(), "", "");
}
private String getCaseDefaultDivision() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_Division);
}
private String getCaseDefaultDepartment() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_Department);
}
private String getCaseDefaultSpeCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_AuxiliaryCode);
}
private String getCaseDefaultProjectCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_ProjectCode);
}
private String getCaseDefaultCyphCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_AuthorizationCode);
}
private String getCaseDefaultTradingGroup() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit_TradingGroup);
}
private String getCaseDefaultCase() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSafeDeposit);
}
public boolean getIsSalesPersonOnDesc() {
    return !StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSalesPersonOnDesc));
}
private String getCashDefaultDivision() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCash_Division);
}
private String getCashDefaultDepartment() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCash_Department);
}
private String getCashDefaultSpeCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCash_AuxiliaryCode);
}
private String getCashDefaultProjectCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCash_ProjectCode);
}
private String getDefaultBank() {
    return "";
}
private String getCashDefaultCyphCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCash_AuthorizationCode);
}
private String getCashDefaultTradingGroup() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCash_TradingGroup);
}
private String getCreditDefaultDivision() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_Division);
}
private String getCreditDefaultDepartment() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_Department);
}
private String getCreditDefaultSpeCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_AuxiliaryCode);
}
private String getCreditDefaultProjectCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_ProjectCode);
}
private String getCreditDefaultCyphCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_AuthorizationCode);
}
private String getCreditDefaultTradingGroup() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCreditCard_TradingGroup);
}
private String getChequeDefaultDivision() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCheck_Division);
}
private String getChequeDefaultDepartment() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCheck_Department);
}
private String getChequeDefaultSpeCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCheck_AuxiliaryCode);
}
private String getChequeDefaultProjectCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCheck_ProjectCode);
}
private String getChequeDefaultCyphCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCheck_AuthorizationCode);
}
private String getChequeDefaultTradingGroup() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCheck_TradingGroup);
}
private String getDeedDefaultDivision() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_Division);
}
private String getDeedDefaultDepartment() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_Department);
}
private String getDeedDefaultSpeCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_AuxiliaryCode);
}
private String getDeedDefaultProjectCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_ProjectCode);
}
private String getDeedDefaultCyphCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_AuthorizationCode);
}
private String getDeedDefaultTradingGroup() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNote_TradingGroup);
}
public boolean getSendFicheToMainCustomer() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptSendFicheToMainCustomer));
}
public boolean getShowMainCustomers() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowMainCustomers));
}
public boolean getUseMainCustomerPriceList() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptUseMainCustomerPriceList));
}
public String getDefaultFirstSpeCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptFirstSpeCode);
}
public String getDefaultSecondSpeCode() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptSecondSpeCode);
}
public BarcodeReaderType getDefaultBarcodeReaderType() {
    String paramValue = logoSqlHelper.getParamValue (ParameterTypes.ptDefaultBarcodeReaderType);
    if (!paramValue.equals(BarcodeReaderType.Laser)) {
        return BarcodeReaderType.Laser;
    }
    if (TextUtils.isEmpty(paramValue)) {
        return BarcodeReaderType.Camera;
    }
    return BarcodeReaderType.valueOf();
}
public boolean getShowDefinedPricesForBarcode() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowDefinedPricesForBarcode));
}
public boolean showLastPurchaseInformation() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptShowPurchaseInfo));
}
public boolean canCreateInvoiceForEInvoiceCustomer() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptCanCreateInvoiceForEInvoiceCustomer));
}
public UserCase getUserCase(int i) {
    UserCase userCase = new UserCase ();
    List<T> table = logoSqlHelper.getTable (UserCase.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString (i)});
    return (null == table || 0 >= table.size ()) ? userCase : (UserCase) table.get (0);
}
public RiskAlert getControlReceivedDailyInformation() {
    return RiskAlert.fromRiskAlert (StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptIfDailyInformationIsNotReceivedWhenStartingOperation)));
}
public RiskAlert getControlSentDailyInformation() {
    return RiskAlert.fromRiskAlert (StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptIfDailyInformationIsNotSentWhenStartingOperation)));
}
public boolean isDBReceivedToday() {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.isDBReceivedToday():boolean");
}
public void insertLastTransferDate() {
    TransferDate transferDate = new TransferDate ();
    transferDate.setDate (DateAndTimeUtils.getSqlDate (Boolean.TRUE));
    logoSqlBriteDatabase.insert (transferDate, true);
}
public boolean getRouteNewSystem() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptUseMobileSalesRoute));
}
public int getRouteNewSystemPeriod() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptMobileSalesRoutePeriod));
}
public boolean insertRouteProcess(int i, int i2, int i3, RouteProcessType routeProcessType, int i4) {
    try {
        logoSqlBriteDatabase.insert (new WorRouteProcess (i, i2, i3, routeProcessType.getmValue (), i4, 0 < i ? getPlannedSequenceOfRoute(i) : 0, 0, DateAndTimeUtils.getSqlDate (Boolean.FALSE)));
        return true;
    } catch (Exception e) {
        Log.e ("BaseErp", "insertRouteProcess: ", e);
        return false;
    }
}
private int getPlannedSequenceOfRoute(int r5) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getPlannedSequenceOfRoute(int):int");
}
public String getCustomerInCharge(String str) {
    List<T> table;
    if (!TextUtils.isEmpty (str) && null != (table = this.logoSqlHelper.getTable (ClCardIncharge.class, "CLCODE=? AND ACTIVE=?", new String[]{str, "0"})) && !table.isEmpty()) {
        return ((ClCardIncharge) table.get (0)).getInCharge ();
    }
    return "";
}
public String getTransferDataDelete() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptDataDelete);
}
public Cursor getReceiptFicheRefFromInvoiceRef(ReceiptType receiptType, int i) {
    String str;
    String str2 = " AND FTYPE=0";
    if (receiptType == ReceiptType.CASH) {
        str = " CASHCREDIT ";
    } else {
        if (receiptType == ReceiptType.CREDIT) {
            str = " CASHCREDIT";
        } else if (receiptType == ReceiptType.CASE) {
            str = " CASECASH ";
            str2 = "";
        } else if (receiptType == ReceiptType.CHEQUE) {
            str = " CHEQUEDEED ";
        } else if (receiptType == ReceiptType.DEED) {
            str = " CHEQUEDEED";
        } else {
            str = "";
            str2 = str;
        }
        str2 = " AND FTYPE=1";
    }
    return logoSqlBriteDatabase.query ("SELECT LOGICALREF, ISTRANSFER FROM " + str + " WHERE INVOICEREF = " + i + str2);
}
public String getNewCustomerRequiredFields() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptARAPCards_RequiredFields);
}
public boolean insertCabinTrans(int i, int i2, int i3, int i4) {
    try {
        logoSqlBriteDatabase.insert (new CabinTrans (i, i3, getCustomerClCode(i2), mErpType == ErpType.TIGER ? i2 : 0, DateAndTimeUtils.getSqlDate (Boolean.FALSE), getUser().getSalesPersonRef (), i4));
        return true;
    } catch (Exception e) {
        Log.e ("BaseErp", "saveSales: ", e);
        return false;
    }
}
public boolean updateCabinTrans(int i, int i2, int i3, int i4) {
    try {
        logoSqlBriteDatabase.update (new CabinTrans (i, i3, getCustomerClCode(i2), mErpType == ErpType.TIGER ? i2 : 0, DateAndTimeUtils.getSqlDate (Boolean.FALSE), getUser().getSalesPersonRef (), i4), "ID", getOldCabinTransIdForUpdate(i3));
        return true;
    } catch (Exception e) {
        Log.e ("BaseErp", "saveSales: ", e);
        return false;
    }
}
private int getOldCabinTransIdForUpdate(int r5) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getOldCabinTransIdForUpdate(int):int");
}
public void deliverCabinToCustomer(Cabin cabin, int i) {
    int i2;
    try {
        int i3 = 0;
        try {
            cabin.salesmanRef = 0;
            cabin.isTransfer = 0;
            Boolean bool = Boolean.FALSE;
            cabin.modifiedDate = DateAndTimeUtils.getSqlDate (bool);
            logoSqlBriteDatabase.update (cabin);
            CabinTrans cabinTrans = new CabinTrans ();
            cabinTrans.cabinID = cabin.id;
            cabinTrans.trtype = 9;
            cabinTrans.trcode = CabinEnums.CabinTransTrCode.CUSTOMER.getmValue ();
            cabinTrans.date = DateAndTimeUtils.getSqlDate (bool);
            cabinTrans.salesmanRef = getUser().getSalesPersonRef ();
            if (mErpType == ErpType.TIGER) {
                i3 = i;
            }
            cabinTrans.clientRef = i3;
            cabinTrans.clientCode = getCustomerClCode(i);
            i2 = (int) logoSqlBriteDatabase.insert (cabinTrans);
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            i2 = -1;
        }
        insertFicheBroadcastMessage(i2, FicheType.CABIN.getmValue () - 1);
    } catch (Throwable th) {
        insertFicheBroadcastMessage(-1, FicheType.CABIN.getmValue () - 1);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public void receiveCabinFromCustomer(Cabin cabin, int i, int i2) {
    int i3;
    try {
        try {
            cabin.locInfo = CabinEnums.CabinLocInfoEnum.STORAGE.getmValue ();
            cabin.status = i;
            cabin.salesmanRef = getUser().getSalesPersonRef ();
            int i4 = 0;
            cabin.isTransfer = 0;
            Boolean bool = Boolean.FALSE;
            cabin.modifiedDate = DateAndTimeUtils.getSqlDate (bool);
            logoSqlBriteDatabase.update (cabin);
            CabinTrans cabinTrans = new CabinTrans ();
            cabinTrans.cabinID = cabin.id;
            cabinTrans.trtype = 9;
            cabinTrans.trcode = CabinEnums.CabinTransTrCode.ROAD.getmValue ();
            cabinTrans.date = DateAndTimeUtils.getSqlDate (bool);
            cabinTrans.salesmanRef = getUser().getSalesPersonRef ();
            if (mErpType == ErpType.TIGER) {
                i4 = i2;
            }
            cabinTrans.clientRef = i4;
            cabinTrans.clientCode = getCustomerClCode(i2);
            i3 = (int) logoSqlBriteDatabase.insert (cabinTrans);
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            i3 = -1;
        }
        insertFicheBroadcastMessage(i3, FicheType.CABIN.getmValue () - 1);
    } catch (Throwable th) {
        insertFicheBroadcastMessage(-1, FicheType.CABIN.getmValue () - 1);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public void deliverCabinToStorage(Cabin cabin, int i) {
    int i2;
    try {
        try {
            cabin.clientCode = "";
            int i3 = 0;
            cabin.salesmanRef = 0;
            cabin.isTransfer = 0;
            Boolean bool = Boolean.FALSE;
            cabin.modifiedDate = DateAndTimeUtils.getSqlDate (bool);
            logoSqlBriteDatabase.update (cabin);
            CabinTrans cabinTrans = new CabinTrans ();
            cabinTrans.cabinID = cabin.id;
            cabinTrans.trtype = 9;
            cabinTrans.trcode = CabinEnums.CabinTransTrCode.STORAGE.getmValue ();
            cabinTrans.date = DateAndTimeUtils.getSqlDate (bool);
            cabinTrans.salesmanRef = getUser().getSalesPersonRef ();
            if (mErpType == ErpType.TIGER) {
                i3 = i;
            }
            cabinTrans.clientRef = i3;
            cabinTrans.clientCode = getCustomerClCode(i);
            i2 = (int) logoSqlBriteDatabase.insert (cabinTrans);
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            i2 = -1;
        }
        insertFicheBroadcastMessage(i2, FicheType.CABIN.getmValue () - 1);
    } catch (Throwable th) {
        insertFicheBroadcastMessage(-1, FicheType.CABIN.getmValue () - 1);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public void addAvailableCabinToCustomer(Cabin cabin, int i) {
    int i2;
    try {
        try {
            cabin.clientCode = getCustomerClCode(i);
            cabin.locInfo = CabinEnums.CabinLocInfoEnum.CUSTOMER.getmValue ();
            cabin.status = CabinEnums.CabinStatus.ACTIVE.getmValue ();
            Boolean bool = Boolean.FALSE;
            cabin.modifiedDate = DateAndTimeUtils.getSqlDate (bool);
            cabin.salesmanRef = getUser().getSalesPersonRef ();
            int i3 = 0;
            cabin.isTransfer = 0;
            logoSqlBriteDatabase.update (cabin);
            CabinTrans cabinTrans = new CabinTrans ();
            cabinTrans.cabinID = cabin.code;
            cabinTrans.trtype = 9;
            cabinTrans.trcode = CabinEnums.CabinTransTrCode.ROAD.getmValue ();
            cabinTrans.date = DateAndTimeUtils.getSqlDate (bool);
            cabinTrans.salesmanRef = getUser().getSalesPersonRef ();
            if (mErpType == ErpType.TIGER) {
                i3 = i;
            }
            cabinTrans.clientRef = i3;
            cabinTrans.clientCode = getCustomerClCode(i);
            i2 = (int) logoSqlBriteDatabase.insert (cabinTrans);
        } catch (Exception e) {
            Log.e ("BaseErp", "saveSales: ", e);
            i2 = -1;
        }
        insertFicheBroadcastMessage(i2, FicheType.CABIN.getmValue () - 1);
    } catch (Throwable th) {
        insertFicheBroadcastMessage(-1, FicheType.CABIN.getmValue () - 1);
        try {
            throw th;
        } catch (final Throwable e) {
            throw new RuntimeException (e);
        }
    }
}
public boolean isUnsentCabinExists() {
    return !this.logoSqlHelper.getTable(Cabin.class, "ISTRANSFER = ?", new String[]{"0"}).isEmpty();
}
public String getReceiptTotalFromInvoice(int r3, boolean r4, int r5) {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getReceiptTotalFromInvoice(int, boolean, int):java.lang.String");
}
public void checkSalesHasExchangeRates(Sales sales, final ResponseListener responseListener) {
    final int localCurrType = getLocalCurrType();
    final StringBuilder sb = new StringBuilder ();
    final ArrayList arrayList = new ArrayList ();
    final StringBuilder sb2 = new StringBuilder ();
    Disposable subscribe = Observable.just(sales.getMSalesDetailList()).flatMapIterable(new Function() {
        public Object apply(Object obj) {
            try {
                return BaseErp.lambdacheckSalesHasExchangeRates1((ArrayList) obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Object invoke(Object obj) {
            return null;
        }
    }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new io.reactivex.Observer<DataResponse<ItemSlip>>() {
        public void onSubscribe(Disposable disposable) {
        }

        public void onNext(DataResponse<ItemSlip> dataResponse) {
            if (null != dataResponse && dataResponse.isSuccessful()) {
                NetsisRestAsyncTask.itemSlipToSales(sales, dataResponse.getData());
                final String str2 = NetsisRestAsyncTask.TAG;
                Log.d(str2, " onNext : value : " + dataResponse.getData().getSlipHeader().getOrderNumber());
                return;
            }
            final String str3 = NetsisRestAsyncTask.TAG;
            Log.e(str3, " onNext : error : " + (null != dataResponse.getErrorDesc() ? dataResponse.getErrorDesc() : ""));
            StringBuilder sb2 = sb;
            sb2.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
        }

        public void onError(Throwable th) {
            final String str2 = NetsisRestAsyncTask.TAG;
            Log.d(str2, " onError : " + (null != th.getMessage() ? th.getMessage() : ""));
            sb.append(th.getMessage());
        }

        @Override
        public void onNext(Object t) {
        }

        public void onComplete() {
            if (0 < sb.length()) {
                responseListener.onError(sb.toString());
            } else {
                responseListener.onResponse(sales);
            }
        }
    });
}
public ObservableSource lambdacheckSalesHasExchangeRates2(int i, SalesDetail salesDetail) throws Exception {
    if (0 == salesDetail.getCurrType ().getLogicalRef () || i == salesDetail.getCurrType ().getLogicalRef () || 0.0d != this.logoSqlHelper.getCurrRateWithoutDefaultValue (salesDetail.getCurrType ().getLogicalRef ())) {
        return Observable.just ("");
    }
    return Observable.just (salesDetail.getCurrType ().getDefinition ());
}
public void insertOrDeleteWorTables(ArrayList<WorTablesModel> arrayList) {
    if (null != arrayList && !arrayList.isEmpty()) {
        StringBuilder sb = new StringBuilder ();
        ArrayList arrayList2 = new ArrayList ();
        for (WorTablesModel next : arrayList) {
            if (1 == next.getOptype()) {
                WorTables worTables = new WorTables();
                worTables.setName(next.getName());
                arrayList2.add(worTables);
            } else if (-1 == next.getOptype()) {
                sb.append("'");
                sb.append(next.getName());
                sb.append("'");
                sb.append(",");
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            if (!sb.isEmpty() && !"'NODATA',".contentEquals (sb)) {
                String sb2 = sb.toString ();
                ISqlBriteDatabase logoSqlBriteDatabase = this.logoSqlBriteDatabase;
                logoSqlBriteDatabase.delete (WorTables.class, "NAME IN (" + sb2.substring (0, sb2.length () - 1) + ")");
            }
        }
        if (!arrayList2.isEmpty()) {
            logoSqlBriteDatabase.insert (arrayList2);
        }
    }
}
public void checkRemoteWorkTimeControl(final WorkTimeControlProcessType workTimeControlProcessType, final ResponseListener responseListener) {
    if (getUseWorkTime()) {
        Log.d ("Server_Time", "Working Hours:" + getWorkingHours1() + "-" + getWorkingHours2());
        getServerTime(new ResponseListener<String> () {
            public void onResponse(PrintSlipModel str) {
                if (!DateAndTimeUtils.isDateBetweenDatesWithFormat (String.valueOf(str), BaseErp.this.getWorkingHours1(), BaseErp.this.getWorkingHours2(), "HH:mm:ss")) {
                    responseListener.onResponse (BaseErp.this.controlWorkingHourOptions(workTimeControlProcessType));
                    return;
                }
                responseListener.onResponse ("");
            }

            public void onFailure(Throwable throwable) {
                responseListener.onFailure(throwable);
            }

            @Override
            public void onResponse(Boolean aBoolean) {

            }

            @Override
            public void onResponse(Sales sales) {

            }

            @Override
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }

            @Override
            public void onResponse(String obj) {

            }

            @Override
            public void onResponse(ArrayList<String> obj) {

            }

            @Override
            public void onResponse() {

            }

            public void onSuccess(Object o) {
            }

            public void onResponse(List<Object> list) {
            }

            public void onError(String str) {
                responseListener.onError (str);
            }
        });
        return;
    }
    responseListener.onResponse ("");
}
public  String controlWorkingHourOptions(WorkTimeControlProcessType workTimeControlProcessType) {
    Context context = ContextUtils.getmContext ();
    boolean loginInWorkingHours = getLoginInWorkingHours();
    boolean dataEntryInWorkingHours = getDataEntryInWorkingHours();
    boolean transferSendInWorkingHours = getTransferSendInWorkingHours();
    boolean transferGetInWorkingHours = getTransferGetInWorkingHours();
    boolean reportInWorkingHours = getReportInWorkingHours();
    boolean visitInWorkingHours = getVisitInWorkingHours();
    boolean penetrationInWorkingHours = getPenetrationInWorkingHours();
    boolean receiptInWorkingHours = getReceiptInWorkingHours();
    boolean salesInWorkingHours = getSalesInWorkingHours();
    boolean orderInWorkingHours = getOrderInWorkingHours();
    if (18400 > this.getUser().getPanelVersion () && (workTimeControlProcessType == WorkTimeControlProcessType.Penetration || workTimeControlProcessType == WorkTimeControlProcessType.Order || workTimeControlProcessType == WorkTimeControlProcessType.Receipt || workTimeControlProcessType == WorkTimeControlProcessType.Sales || workTimeControlProcessType == WorkTimeControlProcessType.Visit)) {
        workTimeControlProcessType = WorkTimeControlProcessType.DataEntry;
    }
    try {
        switch (C27164.f1163xae5fb2d1[workTimeControlProcessType.ordinal ()]) {
            case 1:
                if (!loginInWorkingHours) {
                    return context.getString (R.string.str_not_use_out_working_hours);
                }
                break;
            case 2:
                if (!dataEntryInWorkingHours) {
                    return context.getString (R.string.exp_66_cannot_enter_data_outside_working_hours);
                }
                break;
            case 3:
                if (!reportInWorkingHours) {
                    return context.getString (R.string.exp_69_cannot_get_report_outside_working_hours);
                }
                break;
            case 4:
                if (!transferGetInWorkingHours) {
                    return context.getString (R.string.exp_67_cannot_import_data_outside_working_hours);
                }
                break;
            case 5:
                if (!transferSendInWorkingHours) {
                    return context.getString (R.string.exp_68_cannot_send_data_outside_working_hours);
                }
                break;
            case 6:
                if (!dataEntryInWorkingHours || !visitInWorkingHours) {
                    return context.getString (R.string.exp_66_cannot_enter_data_outside_working_hours);
                }
            case 7:
                if (!dataEntryInWorkingHours || !penetrationInWorkingHours) {
                    return context.getString (R.string.exp_66_cannot_enter_data_outside_working_hours);
                }
            case 8:
                if (!dataEntryInWorkingHours || !receiptInWorkingHours) {
                    return context.getString (R.string.exp_66_cannot_enter_data_outside_working_hours);
                }
            case 9:
                if (!dataEntryInWorkingHours || !salesInWorkingHours) {
                    return context.getString (R.string.exp_66_cannot_enter_data_outside_working_hours);
                }
            case 10:
                if (!dataEntryInWorkingHours || !orderInWorkingHours) {
                    return context.getString (R.string.exp_66_cannot_enter_data_outside_working_hours);
                }
        }
        return "";
    } catch (Exception e) {
        return e.getMessage ();
    }
}
public String getLocalCurrencyCode() {
    if (mErpType == ErpType.NETSIS) {
        return logoSqlHelper.getLogoParamValue ("LOKAL_KUR");
    }
    int localCurrType = getLocalCurrType();
    if (0 >= localCurrType) {
        return "";
    }
    return logoSqlHelper.getCurrCode (localCurrType);
}
public String getCurrencySubName(int i) {
    if (0 >= i) {
        return "";
    }
    List<T> table = logoSqlHelper.getTable (CurrType.class, "CURRTYPE=?", new String[]{StringUtils.convertIntToString (i)});
    if (0 >= table.size () || TextUtils.isEmpty (((CurrType) table.get (0)).getSubName ())) {
        return "";
    }
    return ((CurrType) table.get (0)).getSubName ();
}
public void replaceSalesFicheHtml(final EmailReplacerModel emailReplacerModel, final CustomerBeforeBalance customerBeforeBalance, final ResponseListener responseListener) {
    final Sales sales = (Sales) emailReplacerModel.getEmailReplacer ();
    if (mErpType == ErpType.NETSIS) {
        sales.setClRef (getCustomerClRef(sales.getClCode ()));
    }
    Observable defer = Observable.defer(new Callable() {
        public final CustomerBeforeBalance f1;

        {
            this.f1 = customerBeforeBalance;
        }

        public Object call() {
            return BaseErp.lambdareplaceSalesFicheHtml3(sales, this.f1);
        }
    });
    defer.subscribeOn(Schedulers.io());
    defer.observeOn(AndroidSchedulers.mainThread());

    defer.subscribe(new Consumer() {
        public final ResponseListener f1;

        {
            this.f1 = responseListener;
        }

        @Override
        public void accept(Object obj) {
            lambdareplaceSalesFicheHtml5(emailReplacerModel, this.f1, ( EmailObject ) obj);
        }

        @Override
        public Object invoke(Object obj) {
            return obj;
        }
    }, new Consumer() {
        public void accept(Object obj) {
            BaseErp.lambdareplaceSalesFicheHtml6(responseListener, ( Throwable ) obj);
        }

        @Override
        public Object invoke(Object obj) {

            return obj;
        }
    });
}
public void lambdareplaceSalesFicheHtml4(Throwable th) {
    Log.e ("BaseErp", "replaceHtml: ", th);
}
public void replaceReceiptFicheHtml(final EmailReplacerModel emailReplacerModel, final EmailTemplateType emailTemplateType, final CustomerBeforeBalance customerBeforeBalance, final ResponseListener responseListener) {
    Observable.defer (new Callable () {
        public final EmailTemplateType f1 = emailTemplateType;
        public final CustomerBeforeBalance f2 = customerBeforeBalance;

        public Object call() {
            try {
                return BaseErp.lambdareplaceReceiptFicheHtml7(emailReplacerModel, this.f1, this.f2);
            } catch (Exception e) {
                throw new RuntimeException (e);
            }
        }
    }).subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ()).doOnError (new Consumer () {
        public void accept(Object obj) {
            try {
                BaseErp.this.lambdareplaceReceiptFicheHtml8((Throwable) obj);
            } catch (Exception e) {
                throw new RuntimeException (e);
            }
        }

        @Override
        public Object invoke(Object obj) {

            return obj;
        }
    }).subscribe (new Consumer () {
        public final ResponseListener f1 = responseListener;

        public void accept(Object obj) {
            try {
                BaseErp.lambdareplaceReceiptFicheHtml9(emailReplacerModel, this.f1, obj);
            } catch (Exception e) {
                throw new RuntimeException (e);
            }
        }
        @Override
        public Object invoke(Object obj) {

            return obj;
        }
    }, new Consumer () {
        @Override
        public void accept(Object obj) {
            try {
                BaseErp.lambdareplaceReceiptFicheHtml10(responseListener, (Throwable) obj);
            } catch (final Exception e) {
                throw new RuntimeException (e);
            }
        }

        @Override
        public Object invoke(Object obj) {

            return obj;
        }
    });
}
public void lambdareplaceReceiptFicheHtml8(Throwable th) {
    Log.e ("BaseErp", "replaceHtml: ", th);
}
public EmailTemplateType getEmailTemplateTypeFromReceiptType(ReceiptType receiptType) {
    int i = C27164.f1164x3afa6613[receiptType.ordinal ()];
    if (1 == i) {
        return EmailTemplateType.CashCollection;
    }
    if (2 == i) {
        return EmailTemplateType.CreditCollection;
    }
    if (3 == i) {
        return EmailTemplateType.CaseCollection;
    }
    if (4 == i) {
        return EmailTemplateType.BillCollection;
    }
    if (5 != i) {
        return EmailTemplateType.Unknown;
    }
    return EmailTemplateType.CheckCollection;
}
public  EmailReplacerModel getReceipt(int i, ReceiptType receiptType) {
    try {
        if (!(receiptType == ReceiptType.CASH || receiptType == ReceiptType.CREDIT)) {
            if (receiptType == ReceiptType.CASE) {
                return getCaseCash(i);
            }
            if (receiptType == ReceiptType.CHEQUE || receiptType == ReceiptType.DEED) {
                return getChequesAndDeed(i);
            }
            return null;
        }
        return getCashCreditX(i);
    } catch (Exception e) {
        Log.e ("BaseErp", "getReceipt: ", e);
        return null;
    }
}
public  Class<?> getReciptClassFromReceiptType(ReceiptType receiptType) {
    if (receiptType == ReceiptType.CASH || receiptType == ReceiptType.CREDIT) {
        return CashCredit.class;
    }
    if (receiptType == ReceiptType.CASE) {
        return CaseCash.class;
    }
    if (receiptType == ReceiptType.CHEQUE || receiptType == ReceiptType.DEED) {
        return Chequedeed.class;
    }
    return null;
}
public  EmailReplacerModel getCashCreditX(int i) {
    EmailReplacerModel emailReplacerModel = new EmailReplacerModel ();
    List<T> table = logoSqlHelper.getTable (CashCredit.class, "LOGICALREF=?", new String[]{String.valueOf (i)});
    if (null == table || table.isEmpty()) {
        return null;
    }
    CashCredit cashCredit = (CashCredit) table.get (0);
    CashCreditX cashCreditX = new CashCreditX ();
    List<T> table2 = ErpCreator.getInstance ().getmBaseErp ().logoSqlHelper.getTable (CashCreditDetail.class, "CASHCREDITID=?", new String[]{String.valueOf (cashCredit.logicalRef)});
    if (null == table2) {
        table2 = new ArrayList<> ();
    }
    cashCreditX.setCashCredit (cashCredit);
    cashCreditX.setCashCreditDetails ((List<CashCreditDetail>) table2);
    if (mErpType == ErpType.NETSIS) {
        cashCredit.clRef = getCustomerClRef(cashCredit.clCode);
    }
    emailReplacerModel.setEmailReplacer (cashCreditX);
    emailReplacerModel.setClRef (cashCredit.clRef);
    emailReplacerModel.setFicheRef (cashCredit.ficheref);
    return emailReplacerModel;
}
public  EmailReplacerModel getCaseCash(int i) {
    EmailReplacerModel emailReplacerModel = new EmailReplacerModel ();
    List<T> table = this.logoSqlHelper.getTable (CaseCash.class, "LOGICALREF=?", new String[]{String.valueOf (i)});
    if (null == table || 0 == table.size ()) {
        return null;
    }
    CaseCash caseCash = (CaseCash) table.get (0);
    if (mErpType == ErpType.NETSIS) {
        caseCash.clRef = getCustomerClRef(caseCash.clCode);
    }
    emailReplacerModel.setEmailReplacer (caseCash);
    emailReplacerModel.setClRef (caseCash.clRef);
    emailReplacerModel.setFicheRef (caseCash.ficheref);
    return emailReplacerModel;
}
public  EmailReplacerModel getChequesAndDeed(int i) {
    EmailReplacerModel emailReplacerModel = new EmailReplacerModel ();
    List<T> table = this.logoSqlHelper.getTable (Chequedeed.class, "LOGICALREF=?", new String[]{String.valueOf (i)});
    if (null == table || table.isEmpty()) {
        return null;
    }
    Chequedeed chequedeed = (Chequedeed) table.get (0);
    ChequeDeed chequeDeed = new ChequeDeed ();
    List<T> table2 = logoSqlHelper.getTable (ChequedeedDetail.class, "CHEQUEDEEDID=?", new String[]{String.valueOf (chequedeed.logicalRef)});
    if (null == table2) {
        table2 = new ArrayList<> ();
    }
    chequeDeed.setChequedeed (chequedeed);
    chequeDeed.setChequedeedDetail ((List<ChequedeedDetail>) table2);
    if (mErpType == ErpType.NETSIS) {
        chequedeed.clRef = getCustomerClRef(chequedeed.clCode);
    }
    emailReplacerModel.setEmailReplacer (chequeDeed);
    emailReplacerModel.setClRef (chequedeed.clRef);
    emailReplacerModel.setFicheRef (chequedeed.ficheref);
    return emailReplacerModel;
}
public boolean getUseStoredProcedureForBarcode() {
    if (mErpType == ErpType.NETSIS) {
        return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptUseStoredProcedureForBarcodeNetsis));
    }
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptUseStoredProcedureForBarcodeTiger));
}
public boolean getUseVatIncForProductsDontHavePriceCard() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptUseVatIncForProductsDontHavePriceCard));
}
public TransferGetOptionType getTransferGetOptionType() {
    if (StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptDataExchange))) {
        return TransferGetOptionType.GET_ONLY_CHANGED;
    }
    return TransferGetOptionType.DELETE_AND_TRANSFER;
}
public double getVariantRealStock(int i) {
    List<T> table = logoSqlHelper.getTable (VariantStock.class, "VARIANTREF=?", new String[]{String.valueOf (i)});
    if (null == table || table.isEmpty()) {
        return 0.0d;
    }
    return ((VariantStock) table.get (0)).getRealStock ();
}
public List<String> getMainClCodeList() {

    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getMainClCodeList():java.util.List");
}
public String getMainClCode(String str) {
    List<T> table = logoSqlHelper.getTable (ClCard.class, "CODE=? AND MAINCLCODE IS NOT NULL AND MAINCLCODE<>'NULL' AND length(MAINCLCODE)>0", new String[]{str});
    if (null == table || table.isEmpty()) {
        return "";
    }
    return ((ClCard) table.get (0)).getMainClCode ();
}
public int getMainClRef(int i) {
    List<T> table = logoSqlHelper.getTable (ClCard.class, "LOGICALREF=? AND MAINCLCODE IS NOT NULL AND MAINCLCODE<>'NULL' AND length(MAINCLCODE)>0", new String[]{String.valueOf (i)});
    if (null == table || 0 == table.size () || TextUtils.isEmpty (((ClCard) table.get (0)).getMainClCode ())) {
        return 0;
    }
    return getCustomerClRef(((ClCard) table.get (0)).getMainClCode ());
}
public String getMainClCodeListNotInQuery() {
    StringBuilder sb = new StringBuilder ();
    List<String> mainClCodeList = getMainClCodeList();
    if (mainClCodeList.isEmpty()) {
        return "";
    }
    sb.append (" (");
    for (String str : mainClCodeList) {
        sb.append ("'");
        sb.append (str);
        sb.append ("'");
        sb.append (",");
    }
    sb.replace (sb.length () - 1, sb.length (), "");
    sb.append (")");
    return sb.toString ();
}
public void updateCustomerRiskTotals(ClRisk clRisk, String str, int i) {
    ClRisk clRisk2 = new ClRisk ();
    clRisk2.setRiskClosed (clRisk.getRiskClosed ());
    clRisk2.setRiskLimit (clRisk.getRiskLimit ());
    clRisk2.setRiskTotal (clRisk.getRiskTotal ());
    clRisk2.setCode (str);
    clRisk2.setClRef (i);
    if (mErpType == ErpType.TIGER) {
        clRisk2.setRiskControlType (clRisk.getRiskControlType ());
        clRisk2.setAccRiskTotal (clRisk.getAccRiskTotal ());
        clRisk2.setAccRiskLimit (clRisk.getAccRiskLimit ());
        clRisk2.setAccRiskClosed (clRisk.getAccRiskClosed ());
        clRisk2.setDespRiskTotal (clRisk.getDespRiskTotal ());
        clRisk2.setDespRiskLimit (clRisk.getDespRiskLimit ());
        clRisk2.setDespRiskClosed (clRisk.getDespRiskClosed ());
        clRisk2.setDespRiskTotalSug (clRisk.getDespRiskClosedSug ());
        clRisk2.setDespRiskLimitSug (clRisk.getDespRiskClosedSug ());
        clRisk2.setDespRiskClosedSug (clRisk.getDespRiskClosedSug ());
        clRisk2.setOrdRiskTotal (clRisk.getOrdRiskTotal ());
        clRisk2.setOrdRiskLimit (clRisk.getOrdRiskLimit ());
        clRisk2.setOrdRiskClosed (clRisk.getOrdRiskClosed ());
        clRisk2.setOrdRiskTotalSug (clRisk.getOrdRiskTotalSug ());
        clRisk2.setOrdRiskLimitSug (clRisk.getOrdRiskLimitSug ());
        clRisk2.setOrdRiskClosedSug (clRisk.getOrdRiskClosedSug ());
    }
    if (TextUtils.isEmpty (str)) {
        clRisk2.setCode (getCustomerClCode(i));
    }
    if (!this.logoSqlHelper.getTable(ClRisk.class, "CODE=?", new String[]{clRisk2.getCode()}).isEmpty()) {
        logoSqlHelper.updateCustomerRiskTotals (clRisk2);
    } else {
        logoSqlHelper.saveCustomerRiskTotals (clRisk2);
    }
}
public Date calculatePaymentDate(Date date, int i) {
    if (0 >= i) {
        return date;
    }
    List<T> table = logoSqlHelper.getTable (PaymentLine.class, "PAYPLANREF=?", new String[]{String.valueOf (i)}, null, null, "LOGICALREF DESC");
    if (0 == table.size ()) {
        return date;
    }
    if (0 == ((PaymentLine) table.get (0)).getDay () && 0 == ((PaymentLine) table.get (0)).getMonth () && 0 == ((PaymentLine) table.get (0)).getYear ()) {
        return date;
    }
    Calendar instance = Calendar.getInstance ();
    instance.setTime (date);
    instance.add (1, ((PaymentLine) table.get (0)).getYear ());
    instance.add (2, ((PaymentLine) table.get (0)).getMonth ());
    instance.add (5, ((PaymentLine) table.get (0)).getDay ());
    return instance.getTime ();
}
public boolean askUserForExchangeRateTransfer() {
    return 1 == StringUtils.convertStringToInt (this.logoSqlHelper.getParamValue (ParameterTypes.ptExchangeRateTransfers));
}
public EDocInfoModel getEDocInfo(int r11, int r12) {

    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getEDocInfo(int, int):com.proje.mobilesales.features.model.EDocInfoModel");
}
public double convertByConfact(String str, int i, int i2, double d) {
    double d2;
    double d3;
    double d4;
    double d5;
    List<T> table = ErpCreator.getInstance ().getmBaseErp ().logoSqlHelper.getTable (ItemUnits.class, "ITEMCODE=? AND LINENR=?", new String[]{str, String.valueOf (i)});
    List<T> table2 = ErpCreator.getInstance ().getmBaseErp ().logoSqlHelper.getTable (ItemUnits.class, "ITEMCODE=? AND LINENR=?", new String[]{str, String.valueOf (i2)});
    double d6 = 1.0d;
    if (!table.isEmpty()) {
        double d7 = 0.0d == ((ItemUnits) table.get (0)).convfact1 ? 1.0d : ((ItemUnits) table.get (0)).convfact1;
        d2 = 0.0d == ((ItemUnits) table.get (0)).convfact2 ? 1.0d : ((ItemUnits) table.get (0)).convfact2;
        d3 = d7;
    } else {
        d3 = 1.0d;
        d2 = 1.0d;
    }
    if (!table2.isEmpty()) {
        double d8 = 0.0d == ((ItemUnits) table2.get (0)).convfact1 ? 1.0d : ((ItemUnits) table2.get (0)).convfact1;
        if (0.0d != ((ItemUnits) table2.get (0)).convfact2) {
            d6 = ((ItemUnits) table2.get (0)).convfact2;
        }
        d5 = d8;
        d4 = d6;
    } else {
        d5 = 1.0d;
        d4 = 1.0d;
    }
    return CalculateUtils.reCalculateActualStock (d, d3, d2, d5, d4);
}
public double convertByConfact(String str, int i, double d) {
    List<T> table = ErpCreator.getInstance ().getmBaseErp ().logoSqlHelper.getTable (ItemUnits.class, "ITEMCODE=? AND LINENR=?", new String[]{str, String.valueOf (i)});
    if (null == table || 0 >= table.size ()) {
        return 0.0d;
    }
    return (d * ((ItemUnits) table.get (0)).convfact2) / ((ItemUnits) table.get (0)).convfact1;
}
public  ClCard getCustomerInfo(int i) {
    List<T> table = logoSqlHelper.getTable (ClCard.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString (i)});
    if (null == table || 0 >= table.size ()) {
        return null;
    }
    return (ClCard) table.get (0);
}
public  Item getItemInfo(int i) {
    List<T> table = logoSqlHelper.getTable (Item.class, "LOGICALREF=?", new String[]{StringUtils.convertIntToString (i)});
    if (null == table || 0 >= table.size ()) {
        return null;
    }
    return (Item) table.get (0);
}
public String getStockTotalsWhereDateExpression() {
    int convertStringToInt = StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptDateToBeAddedForStockTotals));
    if (0 < convertStringToInt) {
        return "DATEADD(day," + convertStringToInt + ",GETDATE())";
    }
    String paramValue = logoSqlHelper.getParamValue (ParameterTypes.ptDateToBeUsedForStockTotals);
    if (TextUtils.isEmpty (paramValue) || !DateAndTimeUtils.getDateCalendar (paramValue, "dd-MM-yyyy").getTime ().after (Calendar.getInstance ().getTime ())) {
        return "GETDATE()";
    }
    return "CONVERT(date,'" + paramValue + "',105)";
}
public void getProductTreeItems(final ArrayList<String> arrayList, final ProductTreeItem productTreeItem, final ResponseListener responseListener) {
    String[] strArr = {"I.STGRPCODE", "I.SPECODE", "I.SPECODE2", "I.SPECODE3", "I.SPECODE4", "I.SPECODE5"};
    String[] strArr2 = {"I.STGRPNAME AS GRPNAME", "G.GRPNAME", "G.GRPNAME", "G.GRPNAME", "G.GRPNAME", "G.GRPNAME"};
    if (6 == arrayList.size ()) {
        responseListener.onResponse (new ArrayList ());
        return;
    }
    String str = strArr[arrayList.size ()];
    String str2 = strArr2[arrayList.size ()];
    final StringBuilder sb = new StringBuilder ();
    sb.append (SqlLiteVariable._SELECT);
    sb.append (str);
    sb.append (",");
    sb.append (str2);
    sb.append (" FROM ITEMS I");
    sb.append (arrayList.isEmpty() ? "" : " LEFT JOIN ITMGROUPCODES G ON " + str + " = G.GRPCODE AND CODETYPE=" + arrayList.size ());
    for (int i = 0; i < arrayList.size (); i++) {
        if (0 == i) {
            sb.append (SqlLiteVariable._WHERE);
        }
        sb.append (strArr[i]);
        sb.append ("='");
        sb.append (arrayList.get (i));
        sb.append ("' AND ");
    }
    if (!arrayList.isEmpty()) {
        sb.replace (sb.length () - 5, sb.length () - 1, "");
    }
    sb.append (" GROUP BY ");
    sb.append (str);
    sb.append (" ORDER BY ");
    sb.append (str);
    Observable defer = Observable.defer(new Callable() {
        public final StringBuilder f1;
        {
            this.f1 = sb;
        }

        public Object call() {
            try {
                return BaseErp.this.lambdagetProductTreeItems11(sb);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });
    defer.subscribeOn(Schedulers.io());
    defer.observeOn(AndroidSchedulers.mainThread());

    defer.subscribe(new Consumer() {
        public final ProductTreeItem f1 = productTreeItem;
        public final ArrayList f2 = arrayList;
        public final ResponseListener f3 = responseListener;

        public void accept(Object obj) {
            try {
                BaseErp.this.lambdagetProductTreeItems13(this.f1, this.f2, this.f3, ( Cursor ) obj);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Object invoke(Object obj) {

            return obj;
        }

    });
}
public ObservableSource lambdagetProductTreeItems11(StringBuilder sb) throws Exception {
    return Observable.just (this.logoSqlBriteDatabase.query (sb.toString ()));
}
public void lambdagetProductTreeItems12(Throwable th) throws Exception {
    Log.e ("BaseErp", "get product tree items:", th);
}
public void lambdagetProductTreeItems13(ProductTreeItem r8, ArrayList r9, ResponseListener r10, Cursor r11) throws Exception {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.lambdagetProductTreeItems13(com.proje.mobilesales.features.product.model.ProductTreeItem, java.util.ArrayList, com.proje.mobilesales.core.interfaces.ResponseListener, android.database.Cursor):void");
}
public boolean useProductGroupTree() {
    return mErpType == ErpType.NETSIS && StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptUseProductGroupTree));
}
public boolean useWhouseForEachUser() {
    return StringUtils.paramValueNumberCheck (logoSqlHelper.getParamValue (ParameterTypes.ptUseWhouseForEachUser));
}
public boolean isWhTransfer() {
    return useWhouseForEachUser() && -999 != this.getUser().getWarehouseRef ();
}
public boolean firmUseEInvoice() {
    return logoSqlHelper.getLogoParamValue ("USEEINVOICE").equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public boolean firmUseEArchive() {
    return logoSqlHelper.getLogoParamValue ("USEEARCHIVE").equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public boolean firmUseEDespatch() {
    return logoSqlHelper.getLogoParamValue ("USEEDESPATCH").equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public boolean eDocControlTypeFirm() {
    return "0".equals (this.logoSqlHelper.getLogoParamValue ("EINVCONTTYPE"));
}
public boolean eDocControlTypeBranch() {
    return logoSqlHelper.getLogoParamValue ("EINVCONTTYPE").equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public boolean getUseEdespatch(int i) {
    if (mErpType == ErpType.NETSIS) {
        return firmUseEDespatch();
    }
    if (eDocControlTypeFirm() || 0 > i) {
        return firmUseEDespatch();
    }
    List<T> table = logoSqlHelper.getTable (Branch.class, "NR=?", new String[]{String.valueOf (i)});
    return null != table && !table.isEmpty() && 1 == ((Branch) table.get (0)).getUseEdespatch ();
}
public boolean getUseEarchive(int i) {
    if (mErpType == ErpType.NETSIS) {
        return firmUseEArchive();
    }
    if (eDocControlTypeFirm() || 0 > i) {
        return firmUseEArchive();
    }
    List<T> table = logoSqlHelper.getTable (Branch.class, "NR=?", new String[]{String.valueOf (i)});
    return null != table && !table.isEmpty() && 1 == ((Branch) table.get (0)).getUseEarchive ();
}
public void getEDespatchAdditionalInfo(String str, final ResponseListener responseListener) {
    final String QUERY = "SELECT C.TOWN, C.CITY, C.COUNTRYCODE,C.CODE,C.POSTCODE,CO.COUNTRYNAME, C.DEFINITION_,C.TAXNR FROM CLCARD C LEFT JOIN COUNTRY CO ON CO.COUNTRYCODE = C.COUNTRYCODE WHERE C.CODE = '" + str + "'";
    Observable defer = Observable.defer(new Callable() {

        public final String f1;

        {
            this.f1 = QUERY;
        }

        public Object call() {
            try {
                return BaseErp.this.lambdagetEDespatchAdditionalInfo15(this.f1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });
    defer.subscribeOn(Schedulers.io());
    defer.observeOn(AndroidSchedulers.mainThread());
    defer.doOnError(new Consumer() {
        public void accept(Object obj) {
            try {
                BaseErp.this.lambdagetEDespatchAdditionalInfo16(( Throwable ) obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Object invoke(Object obj) {

            return obj;
        }
    });
    defer.subscribe(new Consumer() {
        public final ResponseListener f1;

        {
            this.f1 = responseListener;
        }

        public void accept(Object obj) {
            try {
                lambdagetEDespatchAdditionalInfo17(this.f1, ( Cursor ) obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Object invoke(Object obj) {

            return obj;
        }

    });
}
public ObservableSource lambdagetEDespatchAdditionalInfo15(String str) throws Exception {
    return Observable.just (this.logoSqlBriteDatabase.query (str));
}
public void lambdagetEDespatchAdditionalInfo16(Throwable th) throws Exception {
    Log.e ("BaseErp", "get EDespatchAdditionalInfo:", th);
}
public void lambdagetEDespatchAdditionalInfo17(ResponseListener r5, Cursor r6) throws Exception {
    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.lambdagetEDespatchAdditionalInfo17(com.proje.mobilesales.core.interfaces.ResponseListener, android.database.Cursor):void");
}
@SuppressLint("Range")
public  NEDispatchInfoModel getLastEDespatchInfoModel(SalesType salesType) {
    String str;
    NEDispatchInfoModel nEDispatchInfoModel;
    Exception e;
    if (salesType == SalesType.DISPATCH) {
        str = "SELECT I.SERIAL, EDI.* FROM INVOICE  I  \n INNER JOIN EDESPATCHADDITIONALINFO EDI ON EDI.SALESFICHEID = I.LOGICALREF AND I.SALESTYPE = " + salesType.getmValue () + " WHERE I.EDESPATCH = 1 ORDER BY EDI.LOGICALREF DESC LIMIT 1";
    } else {
        str = "SELECT I.SERIAL, EDI.* FROM WHTRANSFER  I  \n INNER JOIN EDESPATCHADDITIONALINFO EDI ON EDI.SALESFICHEID = I.LOGICALREF AND I.SALESTYPE = " + salesType.getmValue () + " WHERE I.EDESPATCH = 1 ORDER BY EDI.LOGICALREF DESC LIMIT 1";
    }
    Cursor query = this.logoSqlBriteDatabase.query (str);
    NEDispatchInfoModel nEDispatchInfoModel2 = null;
    try {
        if (null != query) {
            try {
                if (0 < query.getCount () && query.moveToFirst ()) {
                    nEDispatchInfoModel = new NEDispatchInfoModel ();
                    try {
                        EDispatchAdditionalInfo eDispatchAdditionalInfo = new EDispatchAdditionalInfo ();
                        do {
                            nEDispatchInfoModel.setSerial (query.getString (query.getColumnIndex ("SERIAL")));
                            eDispatchAdditionalInfo.firstDriverPlate = query.getString (query.getColumnIndex ("PLATE"));
                            eDispatchAdditionalInfo.carrierTaxNr = query.getString (query.getColumnIndex ("CARRIERTAXNR"));
                            eDispatchAdditionalInfo.carrierName = query.getString (query.getColumnIndex ("CARRIERNAME"));
                            eDispatchAdditionalInfo.carrierCounty = query.getString (query.getColumnIndex ("CARRIERCOUNTY"));
                            eDispatchAdditionalInfo.carrierCity = query.getString (query.getColumnIndex ("CARRIERCITY"));
                            eDispatchAdditionalInfo.carrierCountry = query.getString (query.getColumnIndex ("CARRIERCOUNTRY"));
                            eDispatchAdditionalInfo.carrierPostCode = query.getString (query.getColumnIndex ("CARRIERPOSTCODE"));
                            eDispatchAdditionalInfo.firstDriverName = query.getString (query.getColumnIndex ("FIRSTDRIVERNAME"));
                            eDispatchAdditionalInfo.firstDriverLastName = query.getString (query.getColumnIndex ("FIRSTDRIVERLASTNAME"));
                            eDispatchAdditionalInfo.firstDriverDescription = query.getString (query.getColumnIndex ("FIRSTDRIVERDESCRIPTION"));
                            eDispatchAdditionalInfo.firstDriverIdentityNr = query.getString (query.getColumnIndex ("FIRSTDRIVERIDENTITYNR"));
                            eDispatchAdditionalInfo.secondDriverName = query.getString (query.getColumnIndex ("SECONDDRIVERNAME"));
                            eDispatchAdditionalInfo.secondDriverLastName = query.getString (query.getColumnIndex ("SECONDDRIVERLASTNAME"));
                            eDispatchAdditionalInfo.secondDriverDescription = query.getString (query.getColumnIndex ("SECONDDRIVERDESCRIPTION"));
                            eDispatchAdditionalInfo.secondDriverIdentityNr = query.getString (query.getColumnIndex ("SECONDDRIVERIDENTITYNR"));
                            eDispatchAdditionalInfo.thirdDriverName = query.getString (query.getColumnIndex ("THIRDDRIVERNAME"));
                            eDispatchAdditionalInfo.thirdDriverLastName = query.getString (query.getColumnIndex ("THIRDDRIVERLASTNAME"));
                            eDispatchAdditionalInfo.thirdDriverDescription = query.getString (query.getColumnIndex ("THIRDDRIVERDESCRIPTION"));
                            eDispatchAdditionalInfo.thirdDriverIdentityNr = query.getString (query.getColumnIndex ("THIRDDRIVERIDENTITYNR"));
                            eDispatchAdditionalInfo.firstTrailerPlate = query.getString (query.getColumnIndex ("FIRSTTRAILERPLATE"));
                            eDispatchAdditionalInfo.secondTrailerPlate = query.getString (query.getColumnIndex ("SECONDTRAILERPLATE"));
                            eDispatchAdditionalInfo.thirdTrailerPlate = query.getString (query.getColumnIndex ("THIRDTRAILERPLATE"));
                            eDispatchAdditionalInfo.salesType = query.getInt (query.getColumnIndex ("SALESTYPE"));
                            nEDispatchInfoModel.setAdditionalInfo (eDispatchAdditionalInfo);
                        } while (query.moveToNext ());
                        nEDispatchInfoModel2 = nEDispatchInfoModel;
                    } catch (Exception e2) {
                        e = e2;
                        Log.e ("BaseErp", "get product tree items:", e);
                        return nEDispatchInfoModel;
                    }
                }
                if (!query.isClosed ()) {
                    query.close ();
                }
            } catch (Exception e3) {
                e = e3;
                nEDispatchInfoModel = nEDispatchInfoModel2;
            }
        }
        if (null != query) {
        }
        return nEDispatchInfoModel2;
    } finally {
        query.close ();
    }
}
public boolean getDisableFicheCompare() {
    return !TextUtils.isEmpty (logoSqlHelper.getParamValue (ParameterTypes.ptDisableFicheCompare));
}
public boolean shouldValidateBranch() {
    return this.mErpType != ErpType.NETSIS && !this.logoSqlHelper.getTable(Branch.class, "1 = 1", null).isEmpty();
}
public boolean shouldValidateFactory() {
    return this.mErpType != ErpType.NETSIS && !this.logoSqlHelper.getTable(Factory.class, "1 = 1", null).isEmpty();
}
public double getOrderMinLimit() {
    return StringUtils.convertStringToDouble (logoSqlHelper.getParamValue (ParameterTypes.ptOrder_MinLimit));
}
public ErpInvoiceType getErpTypeFromSales(Sales sales) {
    boolean z;
    boolean z2;
    List<T> table;
    if (mErpType == ErpType.NETSIS || (!SalesUtils.isSalesTypeInvoiceOrRetailInvoiceOrDispatch (sales.getmSalesType ()) && !SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch (sales.getmSalesType ()) && (!SalesUtils.isSalesTypeOrder (sales.getmSalesType ()) || !canOrderCanTransferEInvoiceOrEArchive()))) {
        return ErpInvoiceType.None;
    }
    boolean z3 = true;
    int i = 0;
    boolean z4 = 1 == this.logoSqlHelper.getClEInvoiceUser (sales.getClRef ());
    if (eDocControlTypeFirm()) {
        z2 = firmUseEArchive();
        z = logoSqlHelper.getLogoParamValue ("10468").equals (ExifInterface.GPS_MEASUREMENT_2D);
    } else if (!eDocControlTypeBranch() || null == (table = this.logoSqlHelper.getTable (Branch.class, "NR=?", new String[]{String.valueOf (sales.getBranch ().getLogicalRef ())})) || 0 >= table.size ()) {
        z2 = false;
        z = false;
    } else {
        boolean z5 = 1 == ((Branch) table.get (0)).getUseEarchive ();
        if (2 != ((Branch) table.get (0)).geteArchiveType ()) {
            z3 = false;
        }
        z = z3;
        z2 = z5;
    }
    if (z4) {
        i = ErpInvoiceType.EInvoice.getmValue ();
    } else if (z2 && !SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoiceOrReturnDispatch (sales.getmSalesType ())) {
        i = (z ? ErpInvoiceType.EArchiveInvoice : ErpInvoiceType.EArchiveInternetInvoice).getmValue ();
    }
    return ErpInvoiceType.fromErpInvoiceType (i);
}
public double getLatitude() {
    if (null == MobileSales.getInstance () || null == MobileSales.getInstance ().getCurrentLocation ()) {
        return 0.0d;
    }
    return MobileSales.getInstance ().getCurrentLocation ().getLatitude ();
}
public double getLongitude() {
    if (null == MobileSales.getInstance () || null == MobileSales.getInstance ().getCurrentLocation ()) {
        return 0.0d;
    }
    return MobileSales.getInstance ().getCurrentLocation ().getLongitude ();
}
public int getProductPayPlanRef(int i) {
    List<T> table = logoSqlHelper.getTable (Item.class, "LOGICALREF = ?", new String[]{Integer.toString (i)});
    if (null == table || 0 == table.size ()) {
        return 0;
    }
    return ((Item) table.get (0)).paymentRef;
}
public boolean canSendEDocuments() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptCanSendEDocuments).equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public boolean canApplySelectedCampaign() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptApplySelectedCampaign).equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public ArrayList<BaseSearchModel> getBaseSearchModelList(String r7, String r8, String r9, int r10, int r11, String... r12) {

    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getBaseSearchModelList(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String[]):java.util.ArrayList");
}
public boolean checkAppVersionOnWorProcessAgain() {
    BaseErp baseErp;
    if (1 == FDateUtils.getInstance ().getFDateModel ().getAppVersionOnWorProcess () || null == (baseErp = ErpCreator.getInstance ().getmBaseErp ())) {
        return false;
    }
    return null == baseErp.getUser () || 17100 <= baseErp.getUser ().getPanelVersion ();
}
public PriceInfo getProductPriceInfo(int r25, boolean r26, String r27, String r28, String r29, int r30, String[] r31) {

    throw new UnsupportedOperationException (" com.proje.mobilesales.core.base.BaseErp.getProductPriceInfo(int, boolean, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String[]):com.proje.mobilesales.features.model.PriceInfo");
}
public Observable<Resource<List<FormGroupModel>>> getFormGroups(final int i) {
    return Observable.create (new ObservableOnSubscribe () {
        public final int f1;

        {
            this.f1 = i;
        }

        public void subscribe(ObservableEmitter observableEmitter) {
            try {
                BaseErp.this.lambdagetFormGroups20(this.f1, observableEmitter);
            } catch (Exception e) {
                throw new RuntimeException (e);
            }
        }
    });
}
public void lambdagetFormGroups20(int i, ObservableEmitter observableEmitter) throws Exception {
    observableEmitter.onNext (new Resource.Loading());
    List<T> table = logoSqlHelper.getTable (DefOrder.class, "WORKTYPE=2 OR WORKTYPE=?", new String[]{StringUtils.convertIntToString (i)});
    try {
        if (table.isEmpty ()) {
            observableEmitter.onNext (new Resource.Error (ContextUtils.getStringResource (R.string.str_not_found_definition_form)));
            return;
        }
        ArrayList arrayList = new ArrayList ();
        for (final Object str : (List) table.stream ().map (new java.util.function.Function () {
            public Object apply(Object obj) {
                return ((DefOrder) obj).getFormGroup ();
            }
        }).distinct ().sorted ().collect (Collectors.toList ())) {
            FormGroupModel formGroupModel = new FormGroupModel (TextUtils.isEmpty ((CharSequence) str) ? ContextUtils.getStringResource (R.string.str_other_forms) : str.toString ());
            formGroupModel.addAllForms ((List) table.stream ().filter (new Predicate () {
                public final String f0;

                {
                    this.f0 = str.toString ();
                }

                public boolean test(Object obj) {
                    return BaseErp.lambdagetFormGroups19(this.f0, (DefOrder) obj);
                }
            }).sorted (Comparator.comparingInt (new ToIntFunction () {
                public int applyAsInt(Object obj) {
                    return ((DefOrder) obj).getLogicalRef ();
                }
            })).collect (Collectors.toList ()));
            arrayList.add (formGroupModel);
        }
        observableEmitter.onNext (new Resource.Success (arrayList));
    } catch (Exception e) {
        observableEmitter.onError (e);
    }
}
public ArrayList<String> getFicheDetailBarcodesBySalesDetailRef(int i, int i2) {
    List<T> table = logoSqlHelper.getTable (FicheDetailBarcode.class, "DETAILREF =? AND FICHETYPE = ?", new String[]{Integer.toString (i), Integer.toString (i2)});
    if (table.isEmpty ()) {
        return new ArrayList<> ();
    }
    return new ArrayList<> ((Collection) table.stream ().map (new java.util.function.Function () {
        public Object apply(Object obj) {
            return ((FicheDetailBarcode) obj).getBarcode ();
        }
    }).collect (Collectors.toList ()));
}
public boolean canResetPriceOnDivisionChange() {
    return mErpType != ErpType.NETSIS;
}
public void resetPricesOnDivisionChange(final Sales sales, ResponseListener responseListener) {
    if (this.mErpType == ErpType.NETSIS || null == sales.getMSalesDetailList () || sales.getMSalesDetailList ().isEmpty ()) {
        responseListener.onResponse ("");
        return;
    }
    StringBuilder sb = new StringBuilder ();
    Observable.just (sales.getMSalesDetailList ()).flatMapIterable (new Function () {
        public Object apply(final Object obj) throws Exception {
            return lambdaresetPricesOnDivisionChange22((ArrayList) obj);
        }

        @Override
        public Object invoke(Object obj) {
            return null;
        }
    }).observeOn (AndroidSchedulers.mainThread ()).subscribeOn (Schedulers.io ()).subscribe (new io.reactivex.Observer<DataResponse<ItemSlip>>() {
        public void onSubscribe(Disposable disposable) {
        }

        public void onNext(DataResponse<ItemSlip> dataResponse) {
            if (null != dataResponse && dataResponse.isSuccessful()) {
                NetsisRestAsyncTask.itemSlipToSales(sales, dataResponse.getData());
                final String str2 = NetsisRestAsyncTask.TAG;
                Log.d(str2, " onNext : value : " + dataResponse.getData().getSlipHeader().getOrderNumber());
                return;
            }
            final String str3 = NetsisRestAsyncTask.TAG;
            Log.e(str3, " onNext : error : " + (null != dataResponse.getErrorDesc() ? dataResponse.getErrorDesc() : ""));
            StringBuilder sb2 = sb;
            sb2.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
        }

        public void onError(Throwable th) {
            final String str2 = NetsisRestAsyncTask.TAG;
            Log.d(str2, " onError : " + (null != th.getMessage() ? th.getMessage() : ""));
            sb.append(th.getMessage());
        }

        @Override
        public void onNext(Object t) {
        }

        public void onComplete() {
            if (0 < sb.length()) {
                responseListener.onError(sb.toString());
            } else {
                responseListener.onResponse(sales);
            }
        }
    });
}
private ObservableSource lambdaresetPricesOnDivisionChange23(final Sales sales, final SalesDetail salesDetail) throws Exception {
    final String convertIntToString;
    if (salesDetail.getPromotion ().isSelect () && !this.isPromotionItemPriceEnabled()) {
        return Observable.just (salesDetail);
    }
    final int clRef = sales.getClRef ();
    final boolean isProduct = salesDetail.isProduct ();
    final String code = salesDetail.getCode ();
    final String code2 = salesDetail.getUnit ().getCode ();
    final String code3 = salesDetail.getVariant ().getCode ();
    final int logicalRef = sales.getBranch ().getLogicalRef ();
    final String convertIntToString2 = StringUtils.convertIntToString (salesDetail.getItemRef ());
    final String convertIntToString3 = StringUtils.convertIntToString (sales.getClRef ());
    final String valueOf = String.valueOf (salesDetail.getPType ());
    final String convertIntToString4 = StringUtils.convertIntToString (salesDetail.getUnit ().getLogicalRef ());
    if (0 >= salesDetail.getPayment ().getLogicalRef ()) {
        convertIntToString = StringUtils.convertIntToString (sales.getPayPlan ().getLogicalRef ());
    } else {
        convertIntToString = StringUtils.convertIntToString (salesDetail.getPayment ().getLogicalRef ());
    }
    final PriceInfo productPriceInfo = this.getProductPriceInfo(clRef, isProduct, code, code2, code3, logicalRef, new String[]{convertIntToString2, convertIntToString3, valueOf, convertIntToString4, convertIntToString, sales.getTradeGroup ().getDefinition (), this.logoSqlHelper.getClCardTradingGrp (sales.getClRef ())});
    salesDetail.getPrice ().reset ();
    salesDetail.setEnteryPrice (productPriceInfo.getPrice ());
    salesDetail.getSelectedPrice ().setLogicalRef (productPriceInfo.getPriceRef ());
    FicheStringProp.setDefinition(productPriceInfo.getPriceWithDigits ());
    salesDetail.getCurrType ().reset ();
    salesDetail.curCodeStr = productPriceInfo.getCurrCode ();
    salesDetail.setPriceWithDigit (productPriceInfo.getPriceWithDigits ());
    salesDetail.setPriceWithCurCode (productPriceInfo.getFormattedPrice ());
    salesDetail.setPrRate (productPriceInfo.getRate ());
    salesDetail.prCurrType = productPriceInfo.getCurrNr ();
    salesDetail.getCurrType ().setLogicalRef (salesDetail.getPrCurrType ());
    FicheStringProp.setDefinition(salesDetail.getCurCodeStr ());
    salesDetail.setIncludeVat (new FicheBooleanProp (productPriceInfo.getIncVat ()));
    return Observable.just (salesDetail);
}
public boolean isInWorkingHoursForShowingNotifications() {
    try {
        int isServerTimeInWorkingHours = isServerTimeInWorkingHours();
        if (0 == isServerTimeInWorkingHours) {
            return false;
        }
        if (1 != isServerTimeInWorkingHours) {
            return DateAndTimeUtils.isDateBetweenDatesWithFormat (DateAndTimeUtils.nowTime2 (), getWorkingHours1(), getWorkingHours2(), "HH:mm:ss");
        }
        return true;
    } catch (Exception unused) {
        return DateAndTimeUtils.isDateBetweenDatesWithFormat (DateAndTimeUtils.nowTime2 (), getWorkingHours1(), getWorkingHours2(), "HH:mm:ss");
    }
}
public boolean isDueDateByLineEnabled() {
    return logoSqlHelper.getLogoParamValue ("SATIR_VADE_TAR").equals (ExifInterface.LONGITUDE_EAST);
}
public void setDueDateForSalesDetail(SalesDetail salesDetail, int i) {
    salesDetail.setDueDate (new FicheDateProp ());
    if (isDueDateByLineEnabled()) {
        salesDetail.setDueDate (new FicheDateProp (DateAndTimeUtils.getDateDMY (DateAndTimeUtils.dateAdd (logoSqlHelper.getClCardDueDate (i)))));
    }
}
public RequiredFields getRequiredFields(SalesType salesType) {
    String str;
    if (SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv (salesType)) {
        str = logoSqlHelper.getParamValue (ParameterTypes.ptInvoiceRequiredFields);
    } else if (SalesUtils.isSalesTypeDispatchOrReturnDispatch (salesType)) {
        str = logoSqlHelper.getParamValue (ParameterTypes.ptDispatchRequiredFields);
    } else if (SalesUtils.isSalesTypeOrder (salesType)) {
        str = logoSqlHelper.getParamValue (ParameterTypes.ptOrderRequiredFields);
    } else {
        str = "";
    }
    return new RequiredFields (str, salesType);
}
public boolean setDeliveryDateAsToday(int i2) {
    return "0".equals (this.logoSqlHelper.getLogoParamValue (StringUtils.convertIntToString (ErpParamType.SALES_ASSIGNDUEDATE.getmValue ())));
}
public  ShipAddress getDefaultShipAddress(int i) {
    List<T> table = logoSqlHelper.getTable (ShipAddress.class, "CLREF=? AND DEFAULTFLG=?", new String[]{Integer.toString (i), BuildConfig.NETSIS_DEMO_PASSWORD});
    if (0 == table.size ()) {
        return null;
    }
    return (ShipAddress) table.get (0);
}
public boolean isActiveAutoVisit() {
    return StringUtils.convertStringToBoolean (logoSqlHelper.getParamValue (ParameterTypes.ptAutoVisit));
}
public boolean isRequiredFieldForReturnDispatch() {
    return logoSqlHelper.getParamValue (ParameterTypes.ptReturnDispatchLineDescriptionRequired).equals (BuildConfig.NETSIS_DEMO_PASSWORD);
}
public boolean isHideCustomerBalance() {
    return "0".equals (this.logoSqlHelper.getParamValue (ParameterTypes.ptHideCustomerBalance));
}
public int getDemandFicheStatus() {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue (String.valueOf (ErpParamType.DEMMGMT_DEMFICHESTAT.getmValue ())));
}
public int getAverageExpiryDay() {
    return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptAvgExpiryDay));
}
public Boolean isOverdueDebt() {
    return Boolean.valueOf (logoSqlHelper.getParamValue (ParameterTypes.ptOverdueDebt).equals (BuildConfig.NETSIS_DEMO_PASSWORD));
}
public Boolean ptScannedBarcodeIsScannedAgain() {
    return Boolean.valueOf ("0".equals (this.logoSqlHelper.getParamValue (ParameterTypes.ptScannedBarcodeIsScannedAgain)));
}
public Boolean isHideDueDate() {
    return Boolean.valueOf ("0".equals (this.logoSqlHelper.getParamValue (ParameterTypes.ptHideDueDate)));
}
public CustomerReports customerReports() {
    CustomerReports customerReports = new CustomerReports ();
    customerReports.setParamValue (logoSqlHelper.getParamValue (ParameterTypes.ptCustomerReports));
    customerReports.updateReportStatus ();
    return customerReports;
}
private int getLogoParamValue(ErpParamType erpParamType) {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue (String.valueOf (erpParamType.getmValue ())));
}
public boolean isPurchasePricesShouldBeGet() {
    int logoParamValue = getLogoParamValue(ErpParamType.SALES_SALEPURCHPRCDEFINV);
    PurchasePriceParamValues purchasePriceParamValues = PurchasePriceParamValues.PROCEED;
    return logoParamValue != purchasePriceParamValues.getValue () || getLogoParamValue(ErpParamType.SALES_SALEPURCHPRCDEFDSP) != purchasePriceParamValues.getValue () || getLogoParamValue(ErpParamType.SALES_SALEPURCHPRCDEFORD) != purchasePriceParamValues.getValue ();
}
public int getLastPurchasePriceParam(SalesType salesType) {
    int i = C27164.SwitchMapcomprojemobilesalescoreenumsSalesType[salesType.ordinal ()];
    if (1 == i) {
        return getLogoParamValue(ErpParamType.SALES_SALEPURCHPRCCNTRL);
    }
    if (2 == i) {
        return getLogoParamValue(ErpParamType.SALES_SALEPURCHPRICEINV);
    }
    if (5 != i) {
        return PurchasePriceParamValues.PROCEED.getValue ();
    }
    return getLogoParamValue(ErpParamType.SALES_SALEPURCHPRICEDSP);
}
public int getDefinedPurchasePriceParam(SalesType salesType) {
    int i = C27164.SwitchMapcomprojemobilesalescoreenumsSalesType[salesType.ordinal ()];
    if (1 == i) {
        return getLogoParamValue(ErpParamType.SALES_SALEPURCHPRCDEFORD);
    }
    if (2 == i) {
        return getLogoParamValue(ErpParamType.SALES_SALEPURCHPRCDEFINV);
    }
    if (5 != i) {
        return PurchasePriceParamValues.PROCEED.getValue ();
    }
    return getLogoParamValue(ErpParamType.SALES_SALEPURCHPRCDEFDSP);
}
public int getCantCalcDeductParam() {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue (String.valueOf (ErpParamType.SALES_CANTCALCDEDUCT.getmValue ())));
}
public int getPaymentOrderParam() {
    return StringUtils.convertStringToInt (logoSqlHelper.getLogoParamValue (String.valueOf (ErpParamType.SALES_ORDPAYTYPE.getmValue ())));
}
public Boolean isRequiredShippingAddressIgnored() {
    return Boolean.valueOf (logoSqlHelper.getParamValue (ParameterTypes.ptDisregardRequiredShipAddress).equals (BuildConfig.NETSIS_DEMO_PASSWORD));
}
public int getDefaultPrintDesign(FicheType ficheType) {
    switch (C27164.SwitchMapcomprojemobilesalescoreenumsFicheType[ficheType.ordinal ()]) {
        case 1:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptInvoice));
        case 2:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptDispatch));
        case 3:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptOrder));
        case 4:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (mErpType == ErpType.NETSIS ? ParameterTypes.ptInvoice : ParameterTypes.ptDispatch));
        case 5:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCashCollection));
        case 6:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCreditCardCollection));
        case 7:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptCheckCollection));
        case 8:
            return StringUtils.convertStringToInt (logoSqlHelper.getParamValue (ParameterTypes.ptPromissoryNoteCollection));
        default:
            return 0;
    }
}
public boolean hasDefaultPrintDesign(FicheType ficheType) {
    return 0 != this.getDefaultPrintDesign(ficheType);
}
public NetsisSelectResult insertTodoWorProc(T todoInfoDb) {
    return null;
}
public NetsisSelectResult insertPenetration(T penetration) {
    return null;
}
public T insertPenetrationDetail(T penetration, T penetrationLine) {
    return penetration;
}
public NetsisSelectResult insertWorCabinTrans(T cabinTrans) {
    return null;
}
public NetsisSelectResult updateWorCabin(T i2) {
    return null;
}
public abstract void getOnlinePrice(OnlineItemPriceParameters onlineItemPriceParameters, ResponseListener<?> responseListener);
public enum C27164 {
    ;
    static final int[] SwitchMapcomprojemobilesalescoreenumsDataObjectType;
    static final int[] SwitchMapcomprojemobilesalescoreenumsFicheType;
    static final int[] SwitchMapcomprojemobilesalescoreenumsSalesType;
    static final int[] f1163xae5fb2d1;

    static final int[] f1164x3afa6613;

    static {
        int[] iArr = new int[FicheType.values ().length];
        SwitchMapcomprojemobilesalescoreenumsFicheType = iArr;
        try {
            iArr[FicheType.INVOICE.ordinal ()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.DISPATCH.ordinal ()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.ORDER.ordinal ()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.ONE_TO_ONE.ordinal ()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.CASH.ordinal ()] = 5;
        } catch (NoSuchFieldError ignored) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.CREDIT_CART.ordinal ()] = 6;
        } catch (NoSuchFieldError ignored) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.CHEQUE.ordinal ()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsFicheType[FicheType.DEED.ordinal ()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        int[] iArr2 = new int[WorkTimeControlProcessType.values ().length];
        f1163xae5fb2d1 = iArr2;
        try {
            iArr2[WorkTimeControlProcessType.Login.ordinal ()] = 1;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.DataEntry.ordinal ()] = 2;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.Report.ordinal ()] = 3;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.TransferGet.ordinal ()] = 4;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.TransferSend.ordinal ()] = 5;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.Visit.ordinal ()] = 6;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.Penetration.ordinal ()] = 7;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.Receipt.ordinal ()] = 8;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.Sales.ordinal ()] = 9;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            f1163xae5fb2d1[WorkTimeControlProcessType.Order.ordinal ()] = 10;
        } catch (NoSuchFieldError unused18) {
        }
        int[] iArr3 = new int[ReceiptType.values ().length];
        f1164x3afa6613 = iArr3;
        try {
            iArr3[ReceiptType.CASH.ordinal ()] = 1;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            f1164x3afa6613[ReceiptType.CREDIT.ordinal ()] = 2;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            f1164x3afa6613[ReceiptType.CASE.ordinal ()] = 3;
        } catch (NoSuchFieldError unused21) {
        }
        try {
            f1164x3afa6613[ReceiptType.DEED.ordinal ()] = 4;
        } catch (NoSuchFieldError unused22) {
        }
        try {
            f1164x3afa6613[ReceiptType.CHEQUE.ordinal ()] = 5;
        } catch (NoSuchFieldError unused23) {
        }
        int[] iArr4 = new int[SalesType.values ().length];
        SwitchMapcomprojemobilesalescoreenumsSalesType = iArr4;
        try {
            iArr4[SalesType.ORDER.ordinal ()] = 1;
        } catch (NoSuchFieldError unused24) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.INVOICE.ordinal ()] = 2;
        } catch (NoSuchFieldError unused25) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.ONE_TO_ONE_CHANGE.ordinal ()] = 3;
        } catch (NoSuchFieldError unused26) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.RETURN_INVOICE.ordinal ()] = 4;
        } catch (NoSuchFieldError unused27) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.DISPATCH.ordinal ()] = 5;
        } catch (NoSuchFieldError unused28) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.RETURN_DISPATCH.ordinal ()] = 6;
        } catch (NoSuchFieldError unused29) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.RETAIL_INVOICE.ordinal ()] = 7;
        } catch (NoSuchFieldError unused30) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.RETAIL_RETURN_INVOICE.ordinal ()] = 8;
        } catch (NoSuchFieldError unused31) {
        }
        int[] iArr5 = new int[DataObjectType.values ().length];
        SwitchMapcomprojemobilesalescoreenumsDataObjectType = iArr5;
        try {
            iArr5[DataObjectType.ADDORDER.ordinal ()] = 1;
        } catch (NoSuchFieldError unused32) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDDISPATCH.ordinal ()] = 2;
        } catch (NoSuchFieldError unused33) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDINVOICE.ordinal ()] = 3;
        } catch (NoSuchFieldError unused34) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCASH.ordinal ()] = 4;
        } catch (NoSuchFieldError unused35) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCREDIT.ordinal ()] = 5;
        } catch (NoSuchFieldError unused36) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCHEQUE.ordinal ()] = 6;
        } catch (NoSuchFieldError unused37) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDDEED.ordinal ()] = 7;
        } catch (NoSuchFieldError unused38) {
        }
        try {
            SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDCASECASH.ordinal ()] = 8;
        } catch (NoSuchFieldError unused39) {
        }
    }
}
private abstract class MyTigerWcfAsyncTask extends TigerWcfAsyncTask {
    public MyTigerWcfAsyncTask(CommunicationType communicationType) {
        super(communicationType, BaseErp.this.logoSqlBriteDatabase, BaseErp.this.mUserSettings);
    }
    public void lambdasendCashAndCredit121(GroupItem f1, boolean f2) {
        super.lambdasendFicheRx32(f1, f2);
    }

    public void lambdasendCashAndCredit121(GroupItem f1, NetsisServiceResult f2, boolean f3, DataResponse obj) {
    }
    public void lambdasendSalesFiche100(GroupItem f1, NetsisServiceResult f2, boolean f3, DataResponse obj) {
    }
    public void lambdasendCaseCashFiche71(GroupItem f1, boolean f2) {
    }
    public void lambdasendOneToOneFiche57(GroupItem f1, boolean f2) {
    }
    public void lambdasendOneToOneFiche49(GroupItem f1, NetsisServiceResult f2, boolean f3, DataResponse obj) {
    }
    public void lambdasendFiche43(GroupItem f1, NetsisSelectResult f2, NetsisSelectResult obj) {
    }
    public void lambdasendFiche46(GroupItem f1, boolean f2) {
    }
    public void lambdatransferFiche28(ResponseListener f1, boolean f2, GroupItem obj) {

    }

    public void getSalesOnlineTotal(final NetsisServiceResult order, final ResponseListener responseListener) {
    }

    public void getSalesCampaign(NetsisServiceResult order, ResponseListener responseListener) {
    }

    protected void updateFiche(NetsisServiceResult netsisServiceResult) {
    }

    @Override
    public Observable getObservableResult(GroupItem GroupItem) {
        return super.getObservableResult(GroupItem);
    }
}
}
