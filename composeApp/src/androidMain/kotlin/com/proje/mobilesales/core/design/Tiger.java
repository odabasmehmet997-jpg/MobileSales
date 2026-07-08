package com.proje.mobilesales.core.design;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.RequiresPermission;
import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.asynctask.AnonymousClass6;
import com.proje.mobilesales.core.asynctask.EmailAsyncTask;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.asynctask.TigerWcfAsyncTask;
import com.proje.mobilesales.core.base.*;
import com.proje.mobilesales.core.data.SendCreatorImpl;
import com.proje.mobilesales.core.data.TigerSendDataCreator;
import com.proje.mobilesales.core.edocument.EDocumentResponse;
import com.proje.mobilesales.core.edocument.EDocumentType;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.event.TransferEvent;
import com.proje.mobilesales.core.interfaces.ICustomerSendCompleted;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.NetsisSelectResult;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.LocationUpdatesService;
import com.proje.mobilesales.core.sql.SqlBrite;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.sql.tiger.TigerSqlBrite;
import com.proje.mobilesales.core.sql.tiger.TigerSqlHelper;
import com.proje.mobilesales.core.sql.tiger.TigerSqlManager;
import com.proje.mobilesales.core.tigerwcf.*;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.view.CustomToast;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.dbmodel.*;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.gpsinfo.model.database.GpsInfo;
import com.proje.mobilesales.features.licence.LicenseUtils;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.product.model.LastItemPriceSqlParams;
import com.proje.mobilesales.features.product.model.OnlineItemPriceParameters;
import com.proje.mobilesales.features.product.model.ProductDetail;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.settings.model.DeviceId;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.tools.model.database.StartInfo;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function6;
import io.reactivex.schedulers.Schedulers;
import org.xml.sax.SAXException;
import retrofit2.Response;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.proje.mobilesales.core.utils.AppUtils.stopMyService;


public class Tiger extends BaseErp {
    private static final int LICENSE_USER_COUNT_FLAG = 4523;
    private static final String TAG = "TIGER";
    public static Netsis.C261711 AnonymousClass3;
    public static Netsis.C261711 AnonymousClass2;
    public SharedPreferences prefs;
    public List<Double> calculateLineUnitTotals(Sales sales) {
        return null;
    }
    public boolean canShowLastPurchaseInfo() {
        return true;
    }
    public void getCustomerExtractReport(int i2, String str, String str2, String str3, ResponseListener responseListener) {
    }
    public void getItemSurplusDiscount(int i2, String str, ResponseListener responseListener) {
    }
    public int getLoadFicheDefaultCaseSql() {
        return R.string.qry_case_where;
    }
    public void getProductSerialInfo(String str, ResponseListener responseListener) {
    }
    public void setCustomerInchargeEmailAddress(ClCard clCard) {
    }
    public Tiger(CommunicationType communicationType) {
        super(ErpType.TIGER, communicationType);
        getErpComponent().inject(this);
        this.mSendCreator = new SendCreatorImpl(new TigerSqlManager(Schedulers.io()));
        if (createLogoDBPath()) {
            SqlBrite build = new TigerSqlBrite.Builder().logger(null).sqlCreator(getLogoSqlHelper().getLogoSqlCreator()).build();
            this.logoSqlBrite = build;
            setLogoSqlBriteDatabase(build.wrapDatabaseHelper((SQLiteOpenHelper) getLogoSqlHelper()));
            this.logoSqlBriteDatabase.setLoggingEnabled(true);
        }
        createCommunication(this.mErpType, communicationType);
        this.prefs = Preferences.getSecurePreferences(ContextUtils.getmContext());
    }
    public Preferences.TigerUserSettings getUserSettings() {
        return (Preferences.TigerUserSettings) this.mUserSettings;
    }
    protected boolean createLogoDBPath() {
        try {
            String dbName = getDbName();
            ContextUtils.migratePreviousDbFilesIfExists(dbName);
            setLogoSqlHelper(new TigerSqlHelper(ContextUtils.getmContext(), ContextUtils.getmContext().getDatabasePath(dbName).getPath()) {
                @Override
                public int getClCardCurrency(int i2) {
                    return 0;
                }
            });
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "createLogoDB: ", e2);
            return true;
        }
    }
    public void login(String r6, String r7, boolean r8, boolean r9) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.design.Tiger.login(java.lang.String, java.lang.String, boolean, boolean):void");
    }
    public void sendMail(EmailObject emailObject, boolean z) {
        try {
            if (MainActivity.getDataFlag) {
                new SendMail(emailObject, z).execute();
            }
        } catch (Exception e2) {
            Log.e(TAG, "getAllDataLogo: ", e2);
        }
    }
    public void sendMail(DataObjectType dataObjectType, int i2) {
        try {
            if (MainActivity.getDataFlag) {
                new SendMail(dataObjectType, i2).execute();
            }
        } catch (Exception e2) {
            Log.e(TAG, "getAllDataLogo: ", e2);
        }
    }
    public void getAllDataLogo(boolean z, boolean z2) {
        try {
            if (MainActivity.getDataFlag) {
                new GetDataAll(z, z2).execute();
            }
        } catch (Exception e2) {
            Log.e(TAG, "getAllDataLogo: ", e2);
        }
    }
    public void addNewCustomer(CustomerNew customerNew) {
        new NewCustomerSend(customerNew, false).execute();
    }
    public void addOfflineCustomer(ICustomerSendCompleted iCustomerSendCompleted) {
        new OfflineCustomersSend(-1, iCustomerSendCompleted).execute();
    }
    public void addOfflineCustomer(int i2, ICustomerSendCompleted iCustomerSendCompleted) {
        new OfflineCustomersSend(i2, iCustomerSendCompleted).execute();
    }
    public void addNewCustomer(CustomerNew customerNew, boolean z) {
        new NewCustomerSend(customerNew, z).execute();
    }
    public void addNewCustomer(CustomerNew customerNew, boolean z, ResponseListener responseListener) {
        addNewCustomer(customerNew, z);
    }
    public void docNoUniqueControl(FicheType ficheType, String str, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getDocNo(ficheType, str), responseListener);
    }
    public void getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getMaxMatterNo(ficheType, matterSettings, getTigerQueryCreator().getMaxMatterNo(ficheType, matterSettings), responseListener);
    }
    public void getPaySumCustomerForDueDate(int i2, int i3, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCustomerPayTransSumForDueDate(i2, i3 == 0 ? DateAndTimeUtils.getSqlDate(Boolean.FALSE) : DateAndTimeUtils.convertDateSqlDate(DateAndTimeUtils.dateAdd(i3 * -1))), responseListener);
    }
    public void getInvoicePrintValues(int i2, ResponseListener responseListener, int i3) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintInvoiceHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintInvoiceDetail(i2, i3)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintInvoiceDiscount(i2)), responseListener);
    }
    public void getOnlinePrintFileNameList(FicheType ficheType, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getDesignFileNameList(ficheType), responseListener);
    }
    public void getOnlineSalesOrderList(SalesType salesType, int i2, boolean z, ArrayList arrayList, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getSalesOrderList(salesType, i2, z, arrayList), responseListener);
    }
    public void getOnlineDistributionList(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getDistributionList(i2), responseListener);
    }
    public void getOnlinePrice(int i, int i2, int i3, boolean z, int i4, int i5, String str, int i6, ResponseListener responseListener, String str2, int i7) {

    }
    public void getOnlineSalesMans(ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getSalesMansList(), responseListener);
    }
    public void getSelectedDistributions(int i2, int i3, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getSelectedDistributions(i2, i3), responseListener);
    }
    public void getOrderValidationList(int i2, int i3, String str, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getSalesOrderValidationList(getSalesManagerSalesmanFilter(), i2, i3, str), responseListener);
    }
    public void getOrderDetails(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getSalesOrderDetails(i2), responseListener);
    }
    public void updateCustomer(int i2) {
        updateData(getTigerQueryCreator().getCustomers(i2, false), i2);
    }
    public void updateShipAddress(int i2) {
        updateData(getTigerQueryCreator().getShipAddress(false), i2);
    }
    public void updateOrderDispatchable(final List list, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getDeviceId(), new ResponseListener<List<Object>>() {
            public void onResponse(PrintSlipModel list2) {
                if (list2 == null || list2.size() == 0) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                }
                List list3 = (List) list2.stream().map(new Tiger1ExternalSyntheticLambda0()).filter(new Tiger1ExternalSyntheticLambda1()).collect(Collectors.toList());
                if ((list3 == null || list3.isEmpty()) && !Tiger.this.getUserSettings().isDemo()) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                } else {
                    Tiger.this.getTigerWcfAsyncTask().directRxBool(Tiger.this.getTigerQueryCreator().updateSalesOrderStatus(list, OrderStatus.DISPATCHABLE), responseListener);
                }
            }
            @Override
            public void onFailure(Throwable throwable) {
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
            public void onResponse(List<Object> obj) {

            }

            @Override
            public void onResponse(ArrayList<List<Object>> obj) {

            }

            @Override
            public void onResponse() {

            }

            public DeviceId lambdaonResponse0(Object obj) {
                return (DeviceId) obj;
            }
            public boolean lambdaonResponse1(DeviceId deviceId) {
                return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
            }
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void updateOrderUnDispatchable(final List list, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getDeviceId(), new ResponseListener<List<Object>>() {
            public void onResponse(PrintSlipModel list2) {
                if (list2 == null || list2.size() == 0) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                }
                List list3 = (List) list2.stream().map(this::lambdaonResponse0).filter(this::lambdaonResponse1).collect(Collectors.toList());
                if ((list3 == null || list3.isEmpty()) && !getUserSettings().isDemo()) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                } else {
                    getTigerWcfAsyncTask().directRxBool(getTigerQueryCreator().updateSalesOrderStatus(list, OrderStatus.UNDISPATCHABLE), responseListener);
                }
            }
            @Override
            public void onFailure(Throwable throwable) {
            }
            public DeviceId lambdaonResponse0(Object obj) {
                return (DeviceId) obj;
            }
            public boolean lambdaonResponse1(DeviceId deviceId) {
                return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
            }
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void getCustomerCampaignPoint(int i2) {
        new GetData((TigerSelectResult) getTigerQueryCreator().getCustomerCampaignPoint(i2)).execute();
    }
    public void sendGpsLog() {
        List<TigerSelectResult> insertGpsInformations = getTigerQueryCreator().insertGpsInformations(getLogoSqlHelper().getTable(GpsInfo.class));
        if (insertGpsInformations.size() > 0) {
            String checkWorkTimeControl = checkWorkTimeControl(WorkTimeControlProcessType.TransferSend);
            if (TextUtils.isEmpty(checkWorkTimeControl)) {
                for (TigerSelectResult tigerSelectResult : insertGpsInformations) {
                    getTigerWcfAsyncTask().runDirect(tigerSelectResult);
                    if (tigerSelectResult.isSuccess()) {
                        Log.d(TAG, "run() called with: basarili");
                        getLogoSqlBriteDatabase().delete(GpsInfo.class, "ID=?", String.valueOf(tigerSelectResult.getLogicalRef()));
                    }
                }
            } else if (checkWorkTimeControl.equals(ContextUtils.getStringResource(R.string.exp_68_cannot_send_data_outside_working_hours))) {
                stopMyService(ContextUtils.getmContext(), LocationUpdatesService.class);
            }
        }
    }
    public void updateDataLogo(MobileSalesUpdateType mobileSalesUpdateType, int i2) {
        new MobileSalesUpdate(mobileSalesUpdateType, i2).execute();
    }
    public void sendStartInfoEnter(StartInfo startInfo) {
        new InsertLogoTable().execute(getTigerQueryCreator().insertStartInfo(startInfo));
    }
    public void getSalesFicheSelectedItemPrice(LastItemPriceSqlParams lastItemPriceSqlParams, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getLastItemPriceSalesPurchase(lastItemPriceSqlParams.getItemRef(), lastItemPriceSqlParams.getCustomerRef(), lastItemPriceSqlParams.getDispatchGroupCode()), responseListener);
    }
    public void getLastCustomerSalesDateOfItem(int i2, int i3, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getLastCustomerSalesDateOfItem(i2, i3), responseListener);
    }
    public void sendFiche(final GroupItem groupItem, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getDeviceId(), new ResponseListener<List<Object>>() {
            public void onResponse(PrintSlipModel list) {
                if (list == null || list.size() == 0) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                }
                List list2 = (List) list.stream().map(new Tiger3ExternalSyntheticLambda0()).filter(new Tiger3ExternalSyntheticLambda1()).collect(Collectors.toList());
                if ((list2 == null || list2.isEmpty()) && !Tiger.this.getUserSettings().isDemo()) {
                    groupItem.setComplete(true);
                    groupItem.setError(true);
                    for (int i2 = 0; i2 < groupItem.getItemList().size(); i2++) {
                        BaseResult baseResult = (BaseResult) groupItem.getItemList().get(i2);
                        if (!baseResult.isCompleted()) {
                            baseResult.setErrorString(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                            baseResult.setSuccess(false);
                            baseResult.setCompleted(true);
                        }
                    }
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                } else if (!Tiger.this.checkAppVersionOnWorProcessAgain()) {
                    Tiger.this.getTigerWcfAsyncTask().transferFiche(groupItem, responseListener);
                } else {
                    final TigerSelectResult checkFDateSql = getTigerQueryCreator().getCheckFDateSql();
                    getTigerWcfAsyncTask().getLastRx(checkFDateSql, new ResponseListener<List<Object>>() { 
                        public void onResponse(PrintSlipModel list3) {
                            if (checkFDateSql.isSuccess() && checkFDateSql.getDataList() != null && checkFDateSql.getDataList().size() > 0) {
                                FDateUtils.getInstance().updateValues((CheckFDateModel) checkFDateSql.getDataList().get(0));
                            }
                            TigerWcfAsyncTask tigerWcfAsyncTask = getTigerWcfAsyncTask();
                            tigerWcfAsyncTask.transferFiche(groupItem, responseListener);
                        }
                        public void onFailure(Throwable throwable) {
                        }
                        public void onError(String str) {
                            TigerWcfAsyncTask tigerWcfAsyncTask = getTigerWcfAsyncTask();
                            tigerWcfAsyncTask.transferFiche(groupItem, responseListener);
                        }
                    });
                }
            }
            public void onFailure(Throwable throwable) {
            }
            public DeviceId lambdaonResponse0(Object obj) {
                return (DeviceId) obj;
            }
            public boolean lambdaonResponse1(DeviceId deviceId) {
                return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
            }
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void sendFiche(final List list, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getDeviceId(), new ResponseListener<List<Object>>() { 
            public void onResponse(PrintSlipModel list2) {
                if (list2 == null || list2.size() == 0) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                    return;
                }
                List list3 = (List) list2.stream().map(this::lambdaonResponse0).filter(this::lambdaonResponse1).collect(Collectors.toList());
                if ((list3 == null || list3.isEmpty()) && !Tiger.this.getUserSettings().isDemo()) {
                    for (Object groupItem : list) {
                        groupItem.getClass();
                        for (int i2 = 0; i2 < groupItem.getItemList().size(); i2++) {
                            BaseResult baseResult = (BaseResult) groupItem.getItemList().get(i2);
                            if (!baseResult.isCompleted()) {
                                baseResult.setErrorString(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                                baseResult.setSuccess(false);
                                baseResult.setCompleted(true);
                            }
                        }
                    }
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                } else if (!Tiger.this.checkAppVersionOnWorProcessAgain()) {
                    Tiger.this.getTigerWcfAsyncTask().transferFiches(list, responseListener, false);
                } else {
                    final TigerSelectResult checkFDateSql = Tiger.this.getTigerQueryCreator().getCheckFDateSql();
                    Tiger.this.getTigerWcfAsyncTask().getLastRx(checkFDateSql, new ResponseListener<List<Object>>() { 
                        public void onResponse(PrintSlipModel list4) {
                            if (checkFDateSql.isSuccess() && checkFDateSql.getDataList() != null && checkFDateSql.getDataList().size() > 0) {
                                FDateUtils.getInstance().updateValues((CheckFDateModel) checkFDateSql.getDataList().get(0));
                            }
                            TigerWcfAsyncTask tigerWcfAsyncTask = Tiger.this.getTigerWcfAsyncTask();
                            tigerWcfAsyncTask.transferFiches(list, responseListener, false);
                        }
 
                        public void onError(String str) {
                            TigerWcfAsyncTask tigerWcfAsyncTask = Tiger.this.getTigerWcfAsyncTask();
                            tigerWcfAsyncTask.transferFiches(list, responseListener, false);
                        }
                        public void onFailure(Throwable throwable) {
                            
                        }
                    });
                }
            }
            public void onFailure(Throwable throwable) {
            }
            public DeviceId lambdaonResponse0(Object obj) {
                return (DeviceId) obj;
            }
            public boolean lambdaonResponse1(DeviceId deviceId) {
                return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
            }
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void getOrderPrintValues(int i2, ResponseListener responseListener, int i3) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintOrderHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintOrderDetail(i2, i3)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintOrderDiscount(i2)), responseListener);
    }
    public void getDispatchPrintValues(int i2, ResponseListener responseListener, int i3) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintDispatchHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintDispatchDetail(i2, i3)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintDispatchDiscount(i2)), responseListener);
    }
    public Disposable getCashPrintValues(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintCashHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintCashDetail(i2)), getTigerWcfAsyncTask().createZipRxOne(null), responseListener);
        return null;
    }
    public Disposable getCreditCardPrintValues(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintCreditCardHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintCreditCardDetail(i2)), getTigerWcfAsyncTask().createZipRxOne(null), responseListener);
        return null;
    }
    public Disposable getChequePrintValues(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintChequeHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintChequeDetail(i2)), getTigerWcfAsyncTask().createZipRxOne(null), responseListener);
        return null;
    }
    public Disposable getDeedPrintValues(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintChequeHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintChequeDetail(i2)), getTigerWcfAsyncTask().createZipRxOne(null), responseListener);
        return null;
    }
    public void getCasePrintValues(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintCaseHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintCaseDetail(i2)), getTigerWcfAsyncTask().createZipRxOne(null), responseListener);
    }
    public void getWhTransferPrintValues(int i2, ResponseListener responseListener, int i3) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintWhTransferHeader(i2)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintWhTransferDetail(i2, i3)), getTigerWcfAsyncTask().createZipRxOne(null), responseListener);
    }
    public void getDeliveryNotePrintValues(int i2, int i3, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getPrintRxZip(getTigerWcfAsyncTask().createZipRxOne(null), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getPrintItemStock(i2, i3)), getTigerWcfAsyncTask().createZipRxOne(null), responseListener);
    }
    public void getCurrRate() {
        new GetData(getTigerQueryCreator().getCurrRates()).execute();
    }
    public void getCurrRate(ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCurrRates(), responseListener);
    }
    public void getOrderStatus(int i2) {
        new GetData(getTigerQueryCreator().getOrderStatus(i2)).execute();
    }
    public void getOrderState(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getOrderState(i2), responseListener);
    }
    public String getTodoEmailBody(TodoInfoDb todoInfoDb) {
        String str;
        String textAssets = ContextUtils.getTextAssets("todo_template");
        try {
            String replace = textAssets.replace("@TODOINFOSTR", ContextUtils.getStringResource(R.string.str_todo_info)).replace("@TODODESCRIPTIONSTR", ContextUtils.getStringResource(R.string.str_todo_description)).replace("@TODODESCRIPTION", todoInfoDb.getDesc()).replace("@TODOSTATUSSTR", ContextUtils.getStringResource(R.string.str_todo_status));
            int i2 = todoInfoDb.priority;
            if (i2 == 0) {
                str = ContextUtils.getStringResource(R.string.str_todo_priority_high);
            } else if (i2 == 1) {
                str = ContextUtils.getStringResource(R.string.str_todo_priority_normal);
            } else {
                str = ContextUtils.getStringResource(R.string.str_todo_priority_low);
            }
            textAssets = replace.replace("@TODOSTATUS", str).replace("@TODOBEGDATESTR", ContextUtils.getStringResource(R.string.str_todo_beg_date)).replace("@TODOBEGDATE", todoInfoDb.begDate).replace("@TODOENDDATESTR", ContextUtils.getStringResource(R.string.str_todo_end_date)).replace("@TODOENDDATE", todoInfoDb.endDate).replace("@TODODETAILSTR", ContextUtils.getStringResource(R.string.str_todo_detail)).replace("@TODODETAIL", todoInfoDb.note).replace("@TODOREADTIMESTR", ContextUtils.getStringResource(R.string.str_todo_read_time)).replace("@TODOREADTIME", "").replace("@TODOCOMPLATETIMESTR", ContextUtils.getStringResource(R.string.str_todo_complete_time)).replace("@TODOCOMPLATETIME", "").replace("@TODOUSERNOTESTR", ContextUtils.getStringResource(R.string.str_todo_user_note)).replace("@TODOUSERNOTE", todoInfoDb.userNote).replace("@TODOUSERSTR", ContextUtils.getStringResource(R.string.str_todo_user));
            return textAssets.replace("@TODOUSER", this.user.getName());
        } catch (Exception e2) {
            Log.e(TAG, "getTodoEmailBody: ", e2);
            return textAssets;
        }
    }
    public void getOnlineUpdateCustomer(ResponseListener responseListener, int i2) {
        getTigerWcfAsyncTask().getLastRxBool(getTigerQueryCreator().getCustomers(i2, false), responseListener);
    }
    public void getReportOnline(ResponseListener responseListener) {
        new GetUserReports(responseListener).execute();
    }
    public void saveStockLastTransDate() {
        Observable.create(new ObservableOnSubscribe() { 
            @Override
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    Tiger.this.lambdasaveStockLastTransDate0(observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<DataResponse<ItemSlip>>() {
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
    public void lambdasaveStockLastTransDate0(ObservableEmitter observableEmitter) throws Exception {
        TigerSelectResult worDeletedRecsExistsQuery = getTigerQueryCreator().getWorDeletedRecsExistsQuery();
        getTigerWcfAsyncTask().runExec(worDeletedRecsExistsQuery);
        if (worDeletedRecsExistsQuery.isSuccess()) {
            getTigerQueryCreator().setWorDeletedRecsExistsFromResult(worDeletedRecsExistsQuery);
        }
        TigerSelectResult itemLastChangesDate = getTigerQueryCreator().getItemLastChangesDate();
        getTigerWcfAsyncTask().runExec(itemLastChangesDate);
        if (itemLastChangesDate.isSuccess()) {
            getLogoSharedPreferences().saveLastDate(false, ((LastTransferDate) itemLastChangesDate.getDataList().get(0)).getLastTransferDate());
        }
    }
    public void saveClCardLastTransDate() {
        Observable.create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    Tiger.this.lambdasaveClCardLastTransDate1(observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<DataResponse<ItemSlip>>() {
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
    public void lambdasaveClCardLastTransDate1(ObservableEmitter observableEmitter) throws Exception {
        TigerSelectResult worDeletedRecsExistsQuery = getTigerQueryCreator().getWorDeletedRecsExistsQuery();
        getTigerWcfAsyncTask().runExec(worDeletedRecsExistsQuery);
        if (worDeletedRecsExistsQuery.isSuccess()) {
            getTigerQueryCreator().setWorDeletedRecsExistsFromResult(worDeletedRecsExistsQuery);
        }
        TigerSelectResult clCardLastChangesDate = getTigerQueryCreator().getClCardLastChangesDate();
        getTigerWcfAsyncTask().runExec(clCardLastChangesDate);
        if (clCardLastChangesDate.isSuccess()) {
            getLogoSharedPreferences().saveLastDate(true, ((LastTransferDate) clCardLastChangesDate.getDataList().get(0)).getLastTransferDate());
        }
    }
    public void showOnlineCampaign(Sales sales, ResponseListener responseListener) {
        FicheType ficheType = sales.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            getTigerWcfAsyncTask().getSalesCampaign((TigerServiceResult) getSendCreator().getOrder(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
            getTigerWcfAsyncTask().getSalesCampaign((TigerServiceResult) getSendCreator().getInvoice(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(ficheType)) {
            getTigerWcfAsyncTask().getSalesCampaign((TigerServiceResult) getSendCreator().getDispatch(sales), responseListener);
        }
    }
    public void showOnlineTotal(Sales sales, ResponseListener responseListener) {
        FicheType ficheType = sales.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            getTigerWcfAsyncTask().getSalesOnlineTotal((TigerServiceResult) getSendCreator().getOrder(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
            getTigerWcfAsyncTask().getSalesOnlineTotal((TigerServiceResult) getSendCreator().getInvoice(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(ficheType)) {
            getTigerWcfAsyncTask().getSalesOnlineTotal((TigerServiceResult) getSendCreator().getDispatch(sales), responseListener);
        }
    }
    public void updateCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener responseListener) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("LATITUDE", Double.valueOf(custGpsInfo.latitude));
        contentValues.put("LONGTITUDE", Double.valueOf(custGpsInfo.longtitude));
        getLogoSqlBriteDatabase().update("CUSTGPSINFO", contentValues, "LOGICALREF=?", String.valueOf(custGpsInfo.logicalRef));
        sendCustomerLocation(custGpsInfo, responseListener);
    }
    public void getSalesProductLinePrice(Sales sales, int i2, WcfPriceType wcfPriceType, ResponseListener responseListener) throws CloneNotSupportedException {
        Sales clone = sales.clone();
        SalesDetail clone2 = clone.getMSalesDetailList().get(i2).clone();
        clone.getMSalesDetailList().clear();
        clone.getMSalesDetailList().add(clone2);
        getSalesAllProductLinePrice(clone, wcfPriceType, responseListener);
    }
    public void getSalesProductLinesPrice(Sales sales, List list, ResponseListener responseListener) throws CloneNotSupportedException {
        WcfPriceType wcfPriceType;
        ArrayList<TigerServiceResult> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            SalesDetail salesDetail = sales.getMSalesDetailList().get(num.intValue());
            if (salesDetail.getSelectedPriceType().getDefinition().equals(ContextUtils.getStringResource(CustomerPriceType.GET_ALL_CUSTOMER_LAST_SELL_PRICE.getmResourceId()))) {
                wcfPriceType = WcfPriceType.LAST_SALES_ALL_CUSTOMER;
            } else if (salesDetail.getSelectedPriceType().getDefinition().equals(ContextUtils.getStringResource(CustomerPriceType.GET_CUSTOMER_LAST_SELL_PRICE.getmResourceId()))) {
                wcfPriceType = WcfPriceType.LAST_SALES_CUSTOMER;
            } else if (salesDetail.getSelectedPriceType().getDefinition().equals(ContextUtils.getStringResource(CustomerPriceType.GET_ALL_CUSTOMER_LAST_BUY_PRICE.getmResourceId()))) {
                wcfPriceType = WcfPriceType.LAST_PURCHASE_ALL_CUSTOMER;
            } else if (salesDetail.getSelectedPriceType().getDefinition().equals(ContextUtils.getStringResource(CustomerPriceType.GET_CUSTOMER_LAST_BUY_PRICE.getmResourceId()))) {
                wcfPriceType = WcfPriceType.LAST_PURCHASE_CUSTOMER;
            } else {
                wcfPriceType = WcfPriceType.DEFINE_SALES_PRICE;
            }
            if (hashMap.containsKey(wcfPriceType)) {
                ((Sales) hashMap.get(wcfPriceType)).getMSalesDetailList().add(sales.getMSalesDetailList().get(num.intValue()));
            } else {
                Sales clone = sales.clone();
                SalesDetail clone2 = clone.getMSalesDetailList().get(num.intValue());
                clone.getMSalesDetailList().clear();
                clone.getMSalesDetailList().add(clone2);
                hashMap.put(wcfPriceType, clone);
                arrayList2.add(wcfPriceType);
            }
        }
        for (Object sales2 : hashMap.values()) {
            arrayList.add(getSalesAllProductLinePriceServiceResult(( Sales ) sales2));
        }
        getTigerWcfAsyncTask().getCalculateAddedProductLinesPriceRx(arrayList, arrayList2, responseListener);
    }
    public TigerServiceResult getSalesAllProductLinePriceServiceResult(Sales sales) {
        if (sales.getmSalesType() == SalesType.ORDER || sales.getmSalesType() == SalesType.DEMAND) {
            return getSalesOrderAllProductLinePriceServiceResult(sales);
        }
        return getSalesInvoiceAllProductLinePriceServiceResult(sales);
    }
    public TigerServiceResult getSalesOrderAllProductLinePriceServiceResult(Sales sales) {
        if (SalesUtils.isSalesTypeOrder(sales.getmSalesType())) {
            return (TigerServiceResult) getSendCreator().getOrder(sales);
        }
        if (SalesUtils.isSalesTypeDemand(sales.getmSalesType())) {
            return (TigerServiceResult) getSendCreator().getDemand(sales, 0);
        }
        return null;
    }
    public TigerServiceResult getSalesInvoiceAllProductLinePriceServiceResult(Sales sales) {
        SalesType salesType = sales.getmSalesType();
        if (SalesUtils.isSalesTypeOneToOne(salesType)) {
            return (TigerServiceResult) getSendCreator().getDispatch(sales);
        }
        if (SalesUtils.isSalesTypeDispatch(salesType)) {
            return (TigerServiceResult) getSendCreator().getDispatch(sales);
        }
        if (SalesUtils.isSalesTypeReturnDispatch(salesType)) {
            return (TigerServiceResult) getSendCreator().getReturnDispatch(sales);
        }
        if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(salesType)) {
            return (TigerServiceResult) getSendCreator().getInvoice(sales);
        }
        if (SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoice(salesType)) {
            return (TigerServiceResult) getSendCreator().getReturnInvoice(sales);
        }
        return null;
    }
    public void getCheckSeriGroup(int i2, int i3, SalesType salesType, boolean z, int i4, boolean z2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCheckSeriGroup(i2, i3, salesType, z, i4, z2), responseListener);
    }
    public void getSalesAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener) {
        if (SalesUtils.isSalesTypeOrderOrDemand(sales.getmSalesType())) {
            getSalesOrderAllProductLinePrice(sales, wcfPriceType, responseListener);
        } else {
            getSalesInvoiceAllProductLinePrice(sales, wcfPriceType, responseListener);
        }
    }
    public void getSalesOrderAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener) {
        TigerServiceResult tigerServiceResult;
        if (SalesUtils.isSalesTypeOrder(sales.getmSalesType())) {
            tigerServiceResult = (TigerServiceResult) getSendCreator().getOrder(sales);
        } else {
            tigerServiceResult = SalesUtils.isSalesTypeDemand(sales.getmSalesType()) ? (TigerServiceResult) getSendCreator().getDemand(sales, 0) : null;
        }
        getTigerWcfAsyncTask().getCalculateAllProductLinePriceRx(tigerServiceResult, wcfPriceType, responseListener);
    }
    public void getSalesInvoiceAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener) {
        TigerServiceResult tigerServiceResult;
        SalesType salesType = sales.getmSalesType();
        if (SalesUtils.isSalesTypeOneToOne(salesType)) {
            tigerServiceResult = (TigerServiceResult) getSendCreator().getDispatch(sales);
        } else if (SalesUtils.isSalesTypeDispatch(salesType)) {
            tigerServiceResult = (TigerServiceResult) getSendCreator().getDispatch(sales);
        } else if (SalesUtils.isSalesTypeReturnDispatch(salesType)) {
            tigerServiceResult = (TigerServiceResult) getSendCreator().getReturnDispatch(sales);
        } else if (SalesUtils.isSalesTypeInvoiceOrRetailInvoice(salesType)) {
            tigerServiceResult = (TigerServiceResult) getSendCreator().getInvoice(sales);
        } else {
            tigerServiceResult = SalesUtils.isSalesTypeReturnInvoiceOrRetailReturnInvoice(salesType) ? (TigerServiceResult) getSendCreator().getReturnInvoice(sales) : null;
        }
        getTigerWcfAsyncTask().getCalculateAllProductLinePriceRx(tigerServiceResult, wcfPriceType, responseListener);
    }
    public void sendCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener responseListener) {
        getTigerWcfAsyncTask().directRxBool(getTigerQueryCreator().insertCustomerGpsLocation(custGpsInfo), responseListener);
    }
    public void saveCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener responseListener) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CLIENTREF", Integer.valueOf(custGpsInfo.clientRef));
        contentValues.put("CLCODE", custGpsInfo.clCode);
        contentValues.put("CLNAME", custGpsInfo.clName);
        contentValues.put("LATITUDE", Double.valueOf(custGpsInfo.latitude));
        contentValues.put("LONGTITUDE", Double.valueOf(custGpsInfo.longtitude));
        contentValues.put("SHIPINFOREF", Integer.valueOf(custGpsInfo.shippingRef));
        try {
            getLogoSqlBriteDatabase().insert("CUSTGPSINFO", contentValues);
        } catch (Exception e2) {
            Log.e(TAG, "saveCustomerLocation: ", e2);
        }
        sendCustomerLocation(custGpsInfo, responseListener);
    }
    public void getOnlinePriceAll(ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRxBoolPaging(getTigerQueryCreator().getItemPrices(false), responseListener);
    }
    public void getOnlineItems(ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRxBoolPaging(getTigerQueryCreator().getItems(false), responseListener);
    }
    public void getOnlineStockAll(ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRxBoolPaging(getTigerQueryCreator().getItemStock(false), responseListener);
        getTigerWcfAsyncTask().getLastRxBoolPaging(getTigerQueryCreator().getVariantStock(false), responseListener);
    }
    public void getOnlinePrice(OnlineItemPriceParameters onlineItemPriceParameters, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getItemPriceOnlineWebService(onlineItemPriceParameters), responseListener);
    }
    public void runUserDefinedSQL(UserReportsActivity.UserDefinedResponseListener responseListener, String str, String str2) {
        getTigerWcfAsyncTask().getGenericSpRx(getTigerQueryCreator().getUserDesignedSQL(str, str2), responseListener);
    }
    public void getCustomerSummarySales(int i2, boolean z, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCustomerMonthWeekSales(i2, z), responseListener);
    }
    public void getCustomerSummaryTotalBalance(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCustomerLastBalance(i2), responseListener);
    }
    public void getProductTopTenCustomer(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getProductTopTenCustomer(i2), responseListener);
    }
    public void getProductMonthlySales(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getProductMonthlySales(i2), responseListener);
    }
    public void getCustomerSummaryTopTenProduct(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCustomerTopTenProduct(i2), responseListener);
    }
    public TigerWcfAsyncTask getTigerWcfAsyncTask() {
        return (TigerWcfAsyncTask) getBaseCommunication();
    }
    void initSelectQuery() {
        if (getTigerQueryCreator() == null) {
            this.mBaseQueryCreator = new TigerQueryCreator(this.logoSqlHelper, this.user, this.mUserSettings) {
                @Override
                public TigerSelectResult getWorDeletedRecsExistsQuery() {
                    return null;
                }
            };
            return;
        }
        getTigerQueryCreator().update(this.user);
        checkFDateColumns();
    }
    public void setProgressDialogMessage(ProgressDialogBuilder progressDialogBuilder, BaseSelectResult baseSelectResult) {
        progressDialogBuilder.setMessage(ContextUtils.getStringResource(baseSelectResult.getProcessType().resId));
    }
    public void setProgressDialogMessage(ProgressDialogBuilder progressDialogBuilder, BaseSelectResult baseSelectResult, String str) {
        progressDialogBuilder.setMessage(String.format("%s\n%s", str, ContextUtils.getStringResource(baseSelectResult.getProcessType().resId)));
    }
    private void setProgressDialogMessage(ProgressDialogBuilder progressDialogBuilder, String str) {
        progressDialogBuilder.setMessage(str);
    }
    public void saveUser(User user) {
        try {
            this.user = user;
            initSelectQuery();
            getLogoSharedPreferences().saveUser(user);
        } catch (Exception e2) {
            Log.e(TAG, "saveUser: ", e2);
        }
    }
    public void saveErpRights(List<Param> list) {
        ErpType erpType = ErpType.TIGER;
        boolean z = false;
        while (true) {
            for (Param param : list) {
                if (param.getParamNo().equals("99")) {
                    if (param.getParamValue().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                        z = true;
                    } else if (param.getParamValue().equals("1")) {
                        break;
                    }
                } else if (param.getParamNo().equals("98")) {
                    if (param.getParamValue().equals("0")) {
                        erpType = ErpType.GO;
                    } else if (param.getParamValue().equals("1")) {
                        erpType = ErpType.TIGER;
                    }
                }
            }
            this.erpRights = new ErpRights(erpType, z);
            getLogoSharedPreferences().saveErpRights(getErpRights());
            return;
        }
    }
    public void saveUserMenuRights() {
        getUserMenuRights().setMenuRights(this.mErpType, getLogoSqlHelper().getParamValue(ParameterTypes.ptAndroidMenuRights), getErpRights().isPro());
        getLogoSharedPreferences().saveUserMenuRights(getUserMenuRights());
    }
    public TigerQueryCreator getTigerQueryCreator() {
        if (this.mBaseQueryCreator == null)
            this.mBaseQueryCreator = new TigerQueryCreator(getLogoSqlHelper(), this.user, this.mUserSettings) {
                @Override
                public TigerSelectResult getWorDeletedRecsExistsQuery() {
                    return null;
                }
            };
        return (TigerQueryCreator) this.mBaseQueryCreator;
    }
    public void getCustomerRiskInfo(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx((TigerSelectResult) getTigerQueryCreator().getCustomerRiskInfo(i2), responseListener);
    }
    public void getItemImage(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getItemImage(i2), responseListener);
    }
    public void getCustomerImage(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCustomerImage(i2), responseListener);
    }
    public void isOrderStatusStillEditable(FicheQueryParams ficheQueryParams, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getOrderStatus(StringUtils.convertStringToInt(ficheQueryParams.getFicheId())), new ResponseListener<List<Object>>() { // from class: com.proje.mobilesales.core.design.Tiger.5
            public void onResponse(PrintSlipModel list) {
                responseListener.onResponse(Boolean.valueOf(list.size() > 0));
            }
            @Override
            public void onFailure(Throwable throwable) {
                responseListener.onFailure(throwable);  
            } 
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public boolean checkUserSettings() {
        return !TextUtils.isEmpty(getUserSettings().getServerAddress()) && getUserSettings().getFirmNumber() > 0;
    }
    public void getOrderGrossTotalOnline(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getOrderGrossTotal(i2), responseListener);
    }
    public void getOrderAvailableAmounts(ArrayList arrayList, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getOrderAvailableAmounts((ArrayList<String>) arrayList), responseListener);
    }
    public void getOrderAvailableAmountsFromDetailRef(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getOrderAvailableAmountsFromDetailRef(i2), responseListener);
    }
    public void getOrderAvailableAmountsFromDetailWithRefs(ArrayList arrayList, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getOrderAvailableAmountsFromDetailWithRefs(arrayList), responseListener);
    }
    public void getWorTables() {
        if (ContextUtils.checkConnectionWithoutMessage()) {
            TigerSelectResult worTables = getTigerQueryCreator().getWorTables();
            getTigerWcfAsyncTask().runExec(worTables);
            if (worTables.isSuccess() && worTables.getDataList() != null && worTables.getDataList().size() > 0) {
                insertOrDeleteWorTables((ArrayList) worTables.getDataList());
            }
            List<WorTables> table = getLogoSqlHelper().getTable(WorTables.class);
            WorTableNames.getInstance().clearTableNames();
            for (WorTables worTables2 : table) {
                WorTableNames.getInstance().addToTableNames(worTables2.getName());
            }
            return;
        }
        WorTableNames.getInstance().clearTableNames();
    }
    public void checkFDateColumns() {
        if (ContextUtils.checkConnectionWithoutMessage()) {
            TigerSelectResult checkFDateSql = getTigerQueryCreator().getCheckFDateSql();
            getTigerWcfAsyncTask().runExec(checkFDateSql);
            if (!checkFDateSql.isSuccess() || checkFDateSql.getDataList() == null || checkFDateSql.getDataList().size() <= 0) {
                FDateUtils.getInstance().clearValues();
            } else {
                FDateUtils.getInstance().updateValues((CheckFDateModel) checkFDateSql.getDataList().get(0));
            }
        } else {
            FDateUtils.getInstance().clearValues();
        }
        Log.d(TAG, FDateUtils.getInstance().getValuesForLog());
    }
    public void loadCurrencyBalances(int i2, String str, String str2, ResponseListener responseListener) {
        TigerSelectResult tigerSelectResult;
        if (TextUtils.isEmpty(getUser().getPeriodStart()) || TextUtils.isEmpty(getUser().getPeriodEnd())) {
            tigerSelectResult = getTigerQueryCreator().loadCurrencyBalances(i2, str, str2);
        } else {
            tigerSelectResult = getTigerQueryCreator().loadCurrencyBalances(i2, getUser().getPeriodStart(), getUser().getPeriodEnd());
        }
        getTigerWcfAsyncTask().getLastRx(tigerSelectResult, responseListener);
    }
    public void checkPeriodAuthorization(TigerSelectResult tigerSelectResult) {
        ActivePeriod activePeriod = getActivePeriod();
        if (activePeriod != null) {
            TigerSelectResult userAuthorizations = getTigerQueryCreator().getUserAuthorizations(activePeriod.getActivePeriod());
            getTigerWcfAsyncTask().runExec(userAuthorizations);
            if (!userAuthorizations.isSuccess()) {
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            } else if (((ActivePeriod) userAuthorizations.getDataList().get(0)).getAuth() == 0) {
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_58_tiger_user_does_not_have_authorization));
            } else {
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_5_user_not_found));
            }
        } else {
            tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_94_error_active_period_is_not_selected));
        }
    }
    public class Login extends AsyncTask<Void, Void, TigerSelectResult> {
        private TigerSelectResult baseResult;
        private final boolean mDemo;
        private final ProgressDialogBuilder mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
        Login(TigerSelectResult tigerSelectResult, boolean z) {
            this.baseResult = tigerSelectResult;
            this.mDemo = z;
        }

        protected void onPreExecute() {
            this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.type_login)).setCancelable(true).setOnCancelClickListener((v) -> lambdaonPreExecute0(v)).show();
            super.onPreExecute();
        }
        public void lambdaonPreExecute0(DialogInterface dialogInterface) {
            cancel(true);
        }
        public TigerSelectResult doInBackground(Void... voidArr) {
            UserCountModel userCountInfo;
            userCountInfo = null;
            try {
                Log.i(Tiger.TAG, "doInBackground: start");
                publishProgress(voidArr);
                Tiger.this.getWorTables();
                userCountInfo = Tiger.this.getUserCountInfo();
            } catch (Exception e2) {
                Log.e(Tiger.TAG, "doInBackground: ", e2);
                this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.exp_9_login_error));
            }
            if (userCountInfo != null && userCountInfo.hasError()) {
                return Tiger.this.getUserCountErrorSelectResult(userCountInfo);
            }
            this.baseResult = TigerQueryCreator.getLoginQuery(Tiger.this.user, String.valueOf(Tiger.this.mUserSettings.getFirmNumber()));
            Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
            if (this.baseResult.isSuccess()) {
                Tiger.this.saveUser((User) this.baseResult.getDataList().get(0));
                Tiger.this.getActivePeriod();
                if (this.baseResult.getDataList().size() == 0) {
                    Tiger.this.checkPeriodAuthorization(this.baseResult);
                } else {
                    Tiger.this.getObjectServiceVersion();
                    if (!this.mDemo) {
                        this.baseResult = Tiger.this.getTigerQueryCreator().getDeviceId();
                        publishProgress();
                        Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                        if (this.baseResult.isSuccess()) {
                            if (this.baseResult.getResultXML().isEmpty() || this.baseResult.getResultXML().contains(ContextUtils.getDeviceSerialNo()) || Tiger.this.getUserSettings().isDemo()) {
                                if (this.baseResult.getResultXML().isEmpty()) {
                                    this.baseResult = Tiger.this.getTigerQueryCreator().getPanelVersion();
                                    Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                                    if (StringUtils.compareVersionNames(Preferences.getAppVersionAddedVersion(Tiger.this.prefs), ((PanelVersion) this.baseResult.getDataList().get(0)).version) > 0 || StringUtils.compareVersionNames(Preferences.getAppVersionAddedVersion(Tiger.this.prefs), BuildConfig.VERSION_NAME) > 0) {
                                        this.baseResult = Tiger.this.getTigerQueryCreator().insertDeviceId(ContextUtils.getDeviceSerialNo());
                                    } else {
                                        this.baseResult = Tiger.this.getTigerQueryCreator().insertDeviceIdWithVersion(ContextUtils.getDeviceSerialNo(), BuildConfig.VERSION_NAME);
                                    }
                                    publishProgress();
                                    Tiger.this.getTigerWcfAsyncTask().runDirect(this.baseResult);
                                    if (!this.baseResult.isSuccess()) {
                                        return null;
                                    }
                                }
                                this.baseResult = Tiger.this.getTigerQueryCreator().getLicenseInformation();
                                publishProgress();
                                Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                                if (this.baseResult.isSuccess()) {
                                    Tiger.this.insertData(this.baseResult.getDataList(), this.baseResult.isTableDelete());
                                    Tiger.this.saveErpRights(this.baseResult.getDataList());
                                    LicenseUtils.setProductInfo(this.baseResult.getDataList());
                                    this.baseResult = Tiger.this.getTigerQueryCreator().getUserParameters();
                                    publishProgress();
                                    Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                                    LicenseUtils.changeLicenseKey(LicenseUtils.getEncryptedProductInfo());
                                    if (this.baseResult.isSuccess()) {
                                        Tiger.this.insertData(this.baseResult.getDataList(), this.baseResult.isTableDelete());
                                        Tiger.this.saveUserMenuRights();
                                        Log.i(Tiger.TAG, "doInBackground: end");
                                        String checkWorkTimeControl = Tiger.this.checkWorkTimeControl(WorkTimeControlProcessType.Login);
                                        if (!checkWorkTimeControl.isEmpty()) {
                                            this.baseResult.setSuccess(checkWorkTimeControl);
                                        } else if (LicenseUtils.isExpired()) {
                                            this.baseResult.setSuccess(false);
                                            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.proje.mobilesales.core.design.Tiger.Login.1

                                                public void run() {
                                                    if (Tiger.this.getUser().getType().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                                        CustomToast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_license_expiry_date_sales_managers), 1, 5).show();
                                                    } else if (Tiger.this.getUser().getType().equals("0")) {
                                                        CustomToast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_license_expiry_date_sales_persons), 1, 5).show();
                                                    }
                                                }
                                            });
                                        } else if (LicenseUtils.isInWarningDays() && Tiger.this.getUser().getType().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.proje.mobilesales.core.design.Tiger.Login.2

                                                public void run() {
                                                    CustomToast.makeText(ContextUtils.getmContext(), String.format(ContextUtils.getStringResource(R.string.exp_license_warning_sales_managers), DateAndTimeUtils.convertStringDate(LicenseUtils.getProductExpiryDate(), "yyyy.MM.dd", "dd/MM/yyy")), 1, 5).show();
                                                }
                                            });
                                        }
                                    }
                                }
                            } else {
                                this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.exp_4_device_use_other));
                            }
                        }
                    } else {
                        this.baseResult = Tiger.this.getTigerQueryCreator().getLicenseInformation();
                        publishProgress();
                        Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                        if (this.baseResult.isSuccess()) {
                            Tiger.this.saveErpRights(this.baseResult.getDataList());
                            this.baseResult = Tiger.this.getTigerQueryCreator().getUserParameters();
                            publishProgress();
                            Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                            if (this.baseResult.isSuccess()) {
                                Tiger.this.insertData(this.baseResult.getDataList(), this.baseResult.isTableDelete());
                                Tiger.this.saveUserMenuRights();
                                Log.i(Tiger.TAG, "doInBackground: end");
                            }
                        }
                    }
                }
            } else if (this.mDemo) {
                this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.exp_57_working_on_demo_server));
            }
            return this.baseResult;
        }
        public void onCancelled(TigerSelectResult tigerSelectResult) {
            this.mProgressDialogBuilder.dismiss();
            if (tigerSelectResult != null) {
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.str_cancel_process));
                Tiger.this.postEvent(tigerSelectResult);
            }
            super.onCancelled();
        }
        protected void onCancelled() {
            this.mProgressDialogBuilder.dismiss();
            TigerSelectResult tigerSelectResult = this.baseResult;
            if (tigerSelectResult != null) {
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.str_cancel_process));
                Tiger.this.postEvent(tigerSelectResult);
            }
            super.onCancelled();
        }
        public void onProgressUpdate(Void... voidArr) {
            Tiger.this.setProgressDialogMessage(this.mProgressDialogBuilder, this.baseResult);
            super.onProgressUpdate(voidArr);
        }
        public void onPostExecute(TigerSelectResult tigerSelectResult) {
            super.onPostExecute(tigerSelectResult);
            this.mProgressDialogBuilder.dismiss();
            Tiger.this.postEvent(tigerSelectResult);
        }
    }
    public class SendMail extends AsyncTask<Void, Void, Void> {
        private Object data;
        private EmailObject emailObject;
        private ProgressDialogBuilder mProgressDialogBuilder;
        private String msg;
        private DataObjectType objectType;
        private final boolean showProgressDialog;
        SendMail(DataObjectType dataObjectType, int i2) {
            this.objectType = dataObjectType;
            this.data = Tiger.this.getObject(i2, true);
            this.showProgressDialog = true;
            this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
        }
        SendMail(EmailObject emailObject, boolean z) {
            this.emailObject = emailObject;
            this.showProgressDialog = z;
            if (z) {
                this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
            }
        }
        protected void onPreExecute() {
            super.onPreExecute();
            if (this.showProgressDialog) {
                this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.str_sending_email)).setOnCancelClickListener().setCancelable(false).show();
            }
        }
        public void onCancel(DialogInterface dialogInterface) {
            cancel(true);
        }
        public Void doInBackground(Void... voidArr) {
            BaseCommunication baseCommunication = Tiger.this.getBaseCommunication();
            try {
                EmailObject emailObject = this.emailObject;
                if (emailObject == null) {
                    return null;
                }
                this.msg = baseCommunication.sendMail(emailObject);
                return null;
            } catch (Exception unused) {
                Log.e("ObjectService", ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                return null;
            }
        }
        public void onPostExecute(Void r2) {
            super.onPostExecute( r2);
            if (this.showProgressDialog) {
                this.mProgressDialogBuilder.dismiss();
            }
            try {
                String str = this.msg;
                if (str != null) {
                    str.isEmpty();
                }
            } catch (Exception e2) {
                Log.e(Tiger.TAG, "checkConnection: ", e2);
            }
        }
        public void lambdaonPreExecute0(DialogInterface dialogInterface) {
        }
    }
    public class GetDataAll extends AsyncTask<Void, Void, Void> {
        private boolean forService;
        private boolean isDemo;
        private ProgressDialogBuilder mProgressDialogBuilder;
        private TigerSelectResult tempSelectResult;
        private final TransferEvent transferEvent;
        private int index = 0;
        private int endIndex = 0;
        private final TransferGet transferGet = new TransferGet();
        GetDataAll(boolean z, boolean z2) {
            this.isDemo = false;
            this.forService = false;
            MainActivity.getDataFlag = false;
            this.isDemo = z;
            this.forService = z2;
            if (!z2) {
                this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
            }
            this.transferEvent = new TransferEvent();
        }
        protected void onPreExecute() {
            super.onPreExecute();
            if (!this.forService) {
                this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.str_transfer_get_starting)).setOnCancelClickListener().setCancelable(false).show();
            }
        }
        public void lambdaonPreExecute0(DialogInterface dialogInterface) {
            cancel(true);
        }
        public Void doInBackground(Void... voidArr) {
            boolean z;
            try {
                boolean z2 = false;
                try {
                } catch (Exception e2) {
                    Log.e(Tiger.TAG, "doInBackground: ", e2);
                    this.transferEvent.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
                }
                if (Tiger.this.isDeviceInUseByAnotherUser()) {
                    this.transferEvent.setErrorMessage(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                    this.transferEvent.setSuccess(false);
                    return null;
                }
                Tiger.this.getWorTables();
                this.tempSelectResult = Tiger.this.getTigerQueryCreator().getUserParameters();
                Tiger.this.getTigerWcfAsyncTask().runExec(this.tempSelectResult);
                if (this.tempSelectResult.isSuccess()) {
                    Tiger.this.insertData(this.tempSelectResult.getDataList(), this.tempSelectResult.isTableDelete());
                    Tiger.this.saveUserMenuRights();
                    ArrayList arrayList = new ArrayList(Arrays.asList(Tiger.this.getLogoSqlHelper().getParamValue(ParameterTypes.ptInformationToBeSentToTheMobileDevice).replace(" ", "").split(",")));
                    if (arrayList.size() > 0 || this.isDemo) {
                        for (TransferGetItem transferGetItem : Tiger.this.getTransferList()) {
                            transferGetItem.setTransferError("");
                            if (this.isDemo || transferGetItem.getTransferGetType().id <= 0 || arrayList.contains(String.valueOf(transferGetItem.getTransferGetType().id))) {
                                this.transferGet.getTransferGetItems().add(transferGetItem);
                            }
                        }
                    }
                    this.endIndex = this.transferGet.getTransferGetItems().size();
                    List<TransferGetItem> transferGetItems = this.transferGet.getTransferGetItems();
                    String str = "";
                    boolean z3 = true;
                    for (TransferGetItem transferGetItem2 : transferGetItems) {
                        if (isCancelled()) {
                            return null;
                        }
                        publishProgress();
                        String str2 = "";
                        for (TigerSelectResult tigerSelectResult : Tiger.this.getTigerQueryCreator().getTransferList(transferGetItem2, this.forService)) {
                            this.tempSelectResult = tigerSelectResult;
                            publishProgress();
                            TigerSelectResult runAllExec = Tiger.this.getTigerWcfAsyncTask().runAllExec(tigerSelectResult);
                            if (ErpCreator.getInstance().getmBaseErp().isPurchasePricesShouldBeGet()) {
                                TigerQueryCreator tigerQueryCreator = Tiger.this.getTigerQueryCreator();
                                if (this.forService) {
                                    z = false;
                                } else {
                                    z = Preferences.getTransferGetOptionsType(ContextUtils.getmContext());
                                }
                                runAllExec = Tiger.this.getTigerWcfAsyncTask().runAllExec(tigerQueryCreator.getPurchasePrices(z));
                            }
                            if (!runAllExec.isSuccess() && runAllExec.getProcessType() != ProcessType.GETCABINS) {
                                str2 = str2 + runAllExec.getErrorString() + SqlLiteVariable._NEW_LINE;
                                z3 = false;
                            }
                        }
                        transferGetItem2.setTransferError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                        this.index++;
                        Tiger.this.insertLastTransferDate();
                        str = str2;
                    }
                    TigerSelectResult worDeletedRecsExistsQuery = Tiger.this.getTigerQueryCreator().getWorDeletedRecsExistsQuery();
                    Tiger.this.getTigerWcfAsyncTask().runExec(worDeletedRecsExistsQuery);
                    if (worDeletedRecsExistsQuery.isSuccess()) {
                        Tiger.this.getTigerQueryCreator().setWorDeletedRecsExistsFromResult(worDeletedRecsExistsQuery);
                    }
                    for (TransferGetItem transferGetItem3 : transferGetItems) {
                        if (transferGetItem3.getTransferGetType() == TransferGetType.CUSTOMER) {
                            TigerSelectResult clCardLastChangesDate = Tiger.this.getTigerQueryCreator().getClCardLastChangesDate();
                            Tiger.this.getTigerWcfAsyncTask().runExec(clCardLastChangesDate);
                            if (clCardLastChangesDate.isSuccess()) {
                                Tiger.this.getLogoSharedPreferences().saveLastDate(true, ((LastTransferDate) clCardLastChangesDate.getDataList().get(0)).getLastTransferDate());
                            } else {
                                str = str + clCardLastChangesDate.getErrorString() + SqlLiteVariable._NEW_LINE;
                                z3 = false;
                            }
                        }
                        if (transferGetItem3.getTransferGetType() == TransferGetType.ITEM || transferGetItem3.getTransferGetType() == TransferGetType.STOCK) {
                            TigerSelectResult itemLastChangesDate = Tiger.this.getTigerQueryCreator().getItemLastChangesDate();
                            Tiger.this.getTigerWcfAsyncTask().runExec(itemLastChangesDate);
                            if (itemLastChangesDate.isSuccess()) {
                                Tiger.this.getLogoSharedPreferences().saveLastDate(false, ((LastTransferDate) itemLastChangesDate.getDataList().get(0)).getLastTransferDate());
                            } else {
                                str = str + itemLastChangesDate.getErrorString() + SqlLiteVariable._NEW_LINE;
                                z3 = false;
                            }
                        }
                    }
                    z2 = z3;
                } else {
                    this.transferEvent.setSuccess(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                }
                this.transferEvent.setTransferGet(this.transferGet);
                this.transferEvent.setSuccess(z2);
                return null;
            } finally {
                MainActivity.getDataFlag = true;
            }
        }
        public void onCancelled(Void r3) {
            this.transferEvent.setSuccess(ContextUtils.getStringResource(R.string.str_cancel_process));
            if (!this.forService) {
                this.mProgressDialogBuilder.dismiss();
            }
            Tiger.this.postEvent(this.transferEvent);
            super.onCancelled(r3);
        }
        protected void onCancelled() {
            this.transferEvent.setSuccess(ContextUtils.getStringResource(R.string.str_cancel_process));
            if (!this.forService) {
                this.mProgressDialogBuilder.dismiss();
            }
            Tiger.this.postEvent(this.transferEvent);
            super.onCancelled();
        }
        public void onProgressUpdate(Void... voidArr) {
            if (!this.forService) {
                Tiger.this.setProgressDialogMessage(this.mProgressDialogBuilder, this.tempSelectResult, String.format("%d/%d %s", Integer.valueOf(this.index + 1), Integer.valueOf(this.endIndex), ContextUtils.getStringResource(R.string.str_get_logo_data)));
                super.onProgressUpdate(voidArr);
            }
        }
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            if (!this.forService) {
                this.mProgressDialogBuilder.dismiss();
            }
            Tiger.this.postEvent(this.transferEvent);
        }
    }
    public class NewCustomerSend extends AsyncTask<Void, Void, Void> {
        private final boolean mAddPhoto;
        private final CustomerNew mCustomerNew;
        private final ResponseEvent mResponseEvent = new ResponseEvent();
        NewCustomerSend(CustomerNew customerNew, boolean z) {
            this.mCustomerNew = customerNew;
            this.mAddPhoto = z;
        }
        public Void doInBackground(Void... voidArr) {
            try {
                if (this.mAddPhoto) {
                    sendPhoto(this.mCustomerNew.getLogicalRef());
                    return null;
                }
                TigerServiceResult tigerServiceResult = (TigerServiceResult) Tiger.this.getSendCreator().getCustomer(this.mCustomerNew);
                Tiger.this.getTigerWcfAsyncTask().runAppend(tigerServiceResult);
                if (tigerServiceResult.isSuccess()) {
                    Log.i(Tiger.TAG, "doInBackground: dataReference" + tigerServiceResult.getDataReference());
                    TigerSelectResult customers = Tiger.this.getTigerQueryCreator().getCustomers(tigerServiceResult.getDataReference(), false);
                    Tiger.this.getTigerWcfAsyncTask().runExec(customers);
                    if (customers.isSuccess()) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("LOGICALREF", Integer.valueOf(tigerServiceResult.getDataReference()));
                        contentValues.put("ISTRANSFER", 1);
                        if (Tiger.this.getLogoSqlBriteDatabase().update("CLCARD", contentValues, "LOGICALREF=?", String.valueOf(this.mCustomerNew.getLogicalRef())) > 0) {
                            this.mResponseEvent.setSuccess(true);
                            if (Tiger.this.getRouteNewSystem()) {
                                insertSalesPersonCustomerRelation(tigerServiceResult.getDataReference());
                            } else {
                                insertSalesPersonCustomerRelationToTiger(tigerServiceResult.getDataReference());
                            }
                        }
                    }
                    sendPhoto(tigerServiceResult.getDataReference());
                    return null;
                }
                this.mResponseEvent.setSuccess(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                return null;
            } catch (Exception e2) {
                Log.e(Tiger.TAG, "doInBackground: ", e2);
                this.mResponseEvent.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
                return null;
            }
        }
        private void insertSalesPersonCustomerRelation(int i2) {
            Tiger.this.getTigerWcfAsyncTask().runDirect(Tiger.this.getTigerQueryCreator().insertSalesPersonCustomerRelation(i2));
        }
        private void insertSalesPersonCustomerRelationToTiger(int i2) {
            TigerSelectResult salesPersonCustomerRelationLineNo = (TigerSelectResult) Tiger.this.getTigerQueryCreator().getSalesPersonCustomerRelationLineNo();
            Tiger.this.getTigerWcfAsyncTask().runExec(salesPersonCustomerRelationLineNo);
            if (salesPersonCustomerRelationLineNo.isSuccess()) {
                Tiger.this.getTigerWcfAsyncTask().runDirect(Tiger.this.getTigerQueryCreator().insertSalesPersonCustomerRelationToTiger(i2, StringUtils.convertStringToInt(((KeyValuePair) salesPersonCustomerRelationLineNo.getDataList().get(0)).getValue()) + 1));
            }
        }
        private void sendPhoto(int i2) {
            if (this.mCustomerNew.getImage().getImageArray() != null && this.mCustomerNew.getImage().getImageArray().length > 0) {
                TigerSelectResult deleteCustomerImage = Tiger.this.getTigerQueryCreator().getDeleteCustomerImage(i2);
                Tiger.this.getTigerWcfAsyncTask().runDirect(deleteCustomerImage);
                if (deleteCustomerImage.isSuccess()) {
                    TigerSelectResult updateCustomerImageInc = (TigerSelectResult) Tiger.this.getTigerQueryCreator().getUpdateCustomerImageInc(i2);
                    Tiger.this.getTigerWcfAsyncTask().runDirect(updateCustomerImageInc);
                    if (updateCustomerImageInc.isSuccess()) {
                        TigerSelectResult insertCustomerImage = Tiger.this.getTigerQueryCreator().getInsertCustomerImage(i2, this.mCustomerNew.getImage());
                        Tiger.this.getTigerWcfAsyncTask().runDirect(insertCustomerImage);
                        if (insertCustomerImage.isSuccess()) {
                            TigerSelectResult customerDocs = Tiger.this.getTigerQueryCreator().getCustomerDocs(i2, true, CustomerDocumentType.cdtIMAGE);
                            Tiger.this.getTigerWcfAsyncTask().runExec(customerDocs);
                            if (customerDocs.isSuccess() && Tiger.this.insertData(customerDocs.getDataList(), false)) {
                                this.mResponseEvent.setSuccess(true);
                            }
                        }
                    }
                }
            }
        }
        public void onPostExecute(Void r1) {
            super.onPostExecute( r1);
            Tiger.this.postEvent(this.mResponseEvent);
        }
    }
    private class OfflineCustomersSend extends AsyncTask<Void, Void, Void> {
        private final ICustomerSendCompleted listener;
        private final int logicalRef;
        private final ResponseEvent mResponseEvent = new ResponseEvent();
        private final StringBuilder stringBuilder = new StringBuilder();
        public OfflineCustomersSend(int i2, ICustomerSendCompleted iCustomerSendCompleted) {
            this.listener = iCustomerSendCompleted;
            this.logicalRef = i2;
        }
        protected void onPreExecute() {
            super.onPreExecute();
        }
        public Void doInBackground(Void... voidArr) {
            List<ClCard> list = Collections.emptyList();
            try {
                int i2 = this.logicalRef;
                if (i2 == -1) {
                    list = Tiger.this.getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF < ?", new String[]{"0"});
                } else {
                    list = i2 < -1 ? Tiger.this.getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF = ?", new String[]{String.valueOf(this.logicalRef)}) : null;
                }
            } catch (Exception e2) {
                Log.e(Tiger.TAG, "doInBackground: ", e2);
                this.mResponseEvent.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
            }
            if (list == null) {
                return null;
            }
            for (ClCard clCard : list) {
                CustomerNew convert = clCard.convert();
                TigerServiceResult tigerServiceResult = (TigerServiceResult) Tiger.this.getSendCreator().getCustomer(convert);
                Tiger.this.getTigerWcfAsyncTask().runAppend(tigerServiceResult);
                if (!tigerServiceResult.isSuccess()) {
                    StringBuilder sb = this.stringBuilder;
                    sb.append(ContextUtils.getStringResource(R.string.str_customer_transfer, convert.getCode()) + SqlLiteVariable._NEW_LINE);
                    StringBuilder sb2 = this.stringBuilder;
                    sb2.append(ContextUtils.getStringResource(R.string.error_general_message_for_object_service) + SqlLiteVariable._NEW_LINE);
                } else if (Tiger.this.getTigerWcfAsyncTask().readAndUpdateClCard(tigerServiceResult)) {
                    this.mResponseEvent.setSuccess(true);
                } else {
                    sendPhoto(tigerServiceResult.getDataReference(), convert);
                }
            }
            this.mResponseEvent.setErrorMessage(this.stringBuilder.toString());
            return null;
        }
        private void sendPhoto(int i2, CustomerNew customerNew) {
            if (customerNew.getImage().getImageArray() != null && customerNew.getImage().getImageArray().length > 0) {
                TigerSelectResult deleteCustomerImage = Tiger.this.getTigerQueryCreator().getDeleteCustomerImage(i2);
                Tiger.this.getTigerWcfAsyncTask().runDirect(deleteCustomerImage);
                if (deleteCustomerImage.isSuccess()) {
                    TigerSelectResult updateCustomerImageInc = (TigerSelectResult) Tiger.this.getTigerQueryCreator().getUpdateCustomerImageInc(i2);
                    Tiger.this.getTigerWcfAsyncTask().runDirect(updateCustomerImageInc);
                    if (updateCustomerImageInc.isSuccess()) {
                        TigerSelectResult insertCustomerImage = Tiger.this.getTigerQueryCreator().getInsertCustomerImage(i2, customerNew.getImage());
                        Tiger.this.getTigerWcfAsyncTask().runDirect(insertCustomerImage);
                        if (insertCustomerImage.isSuccess()) {
                            TigerSelectResult customerDocs = Tiger.this.getTigerQueryCreator().getCustomerDocs(i2, true, CustomerDocumentType.cdtIMAGE);
                            Tiger.this.getTigerWcfAsyncTask().runExec(customerDocs);
                            if (customerDocs.isSuccess() && Tiger.this.insertData(customerDocs.getDataList(), false)) {
                                this.mResponseEvent.setSuccess(true);
                            }
                        }
                    }
                }
            }
        }
        public void onPostExecute(Void r2) {
            super.onPostExecute((OfflineCustomersSend) r2);
            this.listener.onCustomerSendCompleted(this.mResponseEvent);
            Tiger.this.postEvent(this.mResponseEvent);
        }
    }
    private class MobileSalesUpdate extends AsyncTask<Void, Void, Void> {
        private final MobileSalesUpdateType mobileSalesUpdateType;
        private final ResponseEvent responseEvent = new ResponseEvent();
        private final int todoLogicalRef;
        MobileSalesUpdate(MobileSalesUpdateType mobileSalesUpdateType, int i2) {
            this.mobileSalesUpdateType = mobileSalesUpdateType;
            this.todoLogicalRef = i2;
        }
        public Void doInBackground(Void... voidArr) {
            String str = ContextUtils.getmContext().getCacheDir().getAbsolutePath() + "/toDoHtml/";
            if (this.mobileSalesUpdateType != MobileSalesUpdateType.TODO) {
                return null;
            }
            try {
                List<TodoInfoDb> table = Tiger.this.getLogoSqlHelper().getTable(TodoInfoDb.class, "LOGICALREF=?", new String[]{String.valueOf(this.todoLogicalRef)});
                EmailAsyncTask emailAsyncTask = new EmailAsyncTask(false);
                String str2 = "";
                for (TodoInfoDb todoInfoDb : table) {
                    try {
                        TigerSelectResult updateTodo = Tiger.this.getTigerQueryCreator().updateTodo(todoInfoDb);
                        Tiger.this.getTigerWcfAsyncTask().runDirect(updateTodo);
                        if (!updateTodo.isSuccess()) {
                            str2 = str2 + SqlLiteVariable._NEW_LINE + updateTodo.getErrorString();
                        } else if (!TextUtils.isEmpty(todoInfoDb.emailAddr) && todoInfoDb.emailAddr.length() > 0) {
                            String str3 = todoInfoDb.logicalRef + ".html";
                            String str4 = str + todoInfoDb.logicalRef + ".html";
                            ContextUtils.createFile(str, str3, Tiger.this.getTodoEmailBody(todoInfoDb));
                            emailAsyncTask.setBody(ContextUtils.getStringResource(R.string.str_todo_info_body));
                            emailAsyncTask.get_to().addAll(new ArrayList(Collections.singletonList(todoInfoDb.emailAddr)));
                            emailAsyncTask.addAttachment(str4, str3);
                            if (emailAsyncTask.send()) {
                                Log.i(Tiger.TAG, "doInBackground: email send");
                            } else {
                                Log.i(Tiger.TAG, "doInBackground: email send failed");
                            }
                            ContextUtils.deleteNoteOnSD(str4);
                        }
                    } catch (Exception e2) {
                        Log.e(Tiger.TAG, "doInBackground: ", e2);
                    }
                }
                if (!str2.isEmpty()) {
                    this.responseEvent.setErrorMessage(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                    return null;
                }
                this.responseEvent.setSuccess(true);
                return null;
            } catch (Exception e3) {
                Log.e(Tiger.TAG, "doInBackground: ", e3);
                return null;
            }
        }
        public void onPostExecute(Void r1) {
            super.onPostExecute((MobileSalesUpdate) r1);
            Tiger.this.postEvent(this.responseEvent);
        }
    }
    private class InsertLogoTable extends AsyncTask<TigerSelectResult, Void, Void> {
        private final ResponseEvent responseEvent = new ResponseEvent();
        InsertLogoTable() {
        }
        public Void doInBackground(TigerSelectResult... tigerSelectResultArr) {
            try {
                TigerSelectResult tigerSelectResult = tigerSelectResultArr[0];
                Tiger.this.getTigerWcfAsyncTask().runDirect(tigerSelectResult);
                if (tigerSelectResult.isSuccess()) {
                    Tiger.this.insertData(tigerSelectResult.getDataList(), false);
                    this.responseEvent.setSuccess(true);
                    return null;
                }
                this.responseEvent.setSuccess(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                return null;
            } catch (Exception e2) {
                while (true) {
                    Log.e(Tiger.TAG, "doInBackground: ", e2);
                    this.responseEvent.setSuccess(ContextUtils.getStringResource(R.string.exp_17_send_error));
                    return null;
                }
            }
        }
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            Tiger.this.postEvent(this.responseEvent);
        }
    }
    private void updateData(TigerSelectResult tigerSelectResult, int i2) {
        try {
            getTigerWcfAsyncTask().runExec(tigerSelectResult);
            if (tigerSelectResult.isSuccess()) {
                update(tigerSelectResult.getDataList(), "LOGICALREF", i2);
            }
        } catch (Exception e2) {
            Log.e(TAG, "doInBackground: ", e2);
            tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
        }
    }
    private class GetData extends AsyncTask<Void, Void, Void> {
        private final TigerSelectResult baseResult;
        GetData(TigerSelectResult tigerSelectResult) {
            this.baseResult = tigerSelectResult;
        }
        public Void doInBackground(Void... voidArr) {
            try {
                Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                if (!this.baseResult.isSuccess() || !this.baseResult.isDatabaseSave()) {
                    return null;
                }
                Tiger.this.insertData(this.baseResult.getDataList(), this.baseResult.isTableDelete());
                return null;
            } catch (Exception e2) {
                Log.e(Tiger.TAG, "doInBackground: ", e2);
                this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
                return null;
            }
        }
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            Tiger.this.postEvent(this.baseResult);
        }
    }
    private class WorkTimeControl extends AsyncTask<Void, Void, Void> {
        private final TigerSelectResult baseResult;
        WorkTimeControl(TigerSelectResult tigerSelectResult) {
            this.baseResult = tigerSelectResult;
        }
        public Void doInBackground(Void... voidArr) {
            try {
                Tiger.this.getTigerWcfAsyncTask().runExec(this.baseResult);
                if (!this.baseResult.isSuccess()) {
                    return null;
                }
                if (this.baseResult.getDataList().isEmpty()) {
                    this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.exp_5_user_not_found));
                    return null;
                }
                User user = (User) this.baseResult.getDataList().get(0);
                if (user.getWorkTime() == null || !user.getWorkTime().equals("1")) {
                    return null;
                }
                this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.str_not_use_out_working_hours));
                return null;
            } catch (Exception e2) {
                while (true) {
                    Log.e(Tiger.TAG, "doInBackground: ", e2);
                    this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
                    return null;
                }
            }
        }
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            Tiger.this.postEvent(this.baseResult);
        }
    }
    public List<TransferGetItem> getTransferList() {
        ArrayList<TransferGetItem> arrayList = new ArrayList<>();
        arrayList.add(new TransferGetItem(TransferGetType.ERP_PARAMS));
        arrayList.add(new TransferGetItem(TransferGetType.OTHER_INFO));
        arrayList.add(new TransferGetItem(TransferGetType.USER_INFORMATION));
        arrayList.add(new TransferGetItem(TransferGetType.WAREHOUSE));
        arrayList.add(new TransferGetItem(TransferGetType.BRANCHES));
        arrayList.add(new TransferGetItem(TransferGetType.FACTORY));
        arrayList.add(new TransferGetItem(TransferGetType.DIVISION));
        arrayList.add(new TransferGetItem(TransferGetType.TODO));
        arrayList.add(new TransferGetItem(TransferGetType.MARKETING_MESSAGE));
        arrayList.add(new TransferGetItem(TransferGetType.VISIT));
        arrayList.add(new TransferGetItem(TransferGetType.PENETRATION));
        arrayList.add(new TransferGetItem(TransferGetType.SPECODE));
        arrayList.add(new TransferGetItem(TransferGetType.TRADING));
        arrayList.add(new TransferGetItem(TransferGetType.PAYMENT));
        arrayList.add(new TransferGetItem(TransferGetType.SHIP_TYPE));
        arrayList.add(new TransferGetItem(TransferGetType.SHIP_AGENT));
        arrayList.add(new TransferGetItem(TransferGetType.ITEM));
        arrayList.add(new TransferGetItem(TransferGetType.VARIANT));
        arrayList.add(new TransferGetItem(TransferGetType.UNIT));
        arrayList.add(new TransferGetItem(TransferGetType.BARCODE));
        arrayList.add(new TransferGetItem(TransferGetType.FORM));
        arrayList.add(new TransferGetItem(TransferGetType.STOCK));
        arrayList.add(new TransferGetItem(TransferGetType.PRICE));
        arrayList.add(new TransferGetItem(TransferGetType.DISCOUNT));
        arrayList.add(new TransferGetItem(TransferGetType.CUSTOMER));
        arrayList.add(new TransferGetItem(TransferGetType.SHIP_ADDRESS));
        arrayList.add(new TransferGetItem(TransferGetType.BANK));
        arrayList.add(new TransferGetItem(TransferGetType.BANKACCOUNT));
        arrayList.add(new TransferGetItem(TransferGetType.CASE));
        arrayList.add(new TransferGetItem(TransferGetType.VEHICLE));
        arrayList.add(new TransferGetItem(TransferGetType.DESIGN_FILES));
        arrayList.add(new TransferGetItem(TransferGetType.CURRENCY));
        arrayList.add(new TransferGetItem(TransferGetType.PROJECT));
        arrayList.add(new TransferGetItem(TransferGetType.EMAIL));
        arrayList.add(new TransferGetItem(TransferGetType.ITEM_IMAGE));
        arrayList.add(new TransferGetItem(TransferGetType.CUSTOMER_IMAGE));
        return arrayList;
    }
    public PaymentLine getSalesFichePayPlanTypeCash(String str) {
        List table = getLogoSqlHelper().getTable(PaymentLine.class, "PAYPLANREF=?", new String[]{str});
        if (table == null || table.size() <= 0) {
            return null;
        }
        return (PaymentLine) table.get(0);
    }
    public void cancelInvoiceFiche(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().cancelFiche(TigerSendDataCreator.getInstance().getReadServiceResult(i2), responseListener);
    }
    public void readOrderFiche(ArrayList arrayList, DataObjectType dataObjectType, Sales sales, List list, ResponseListener responseListener) {
        ArrayList<TigerServiceResult> arrayList2 = new ArrayList<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(TigerSendDataCreator.getInstance().getReadServiceResult(Integer.parseInt(arrayList.get(i2).toString()), dataObjectType));
        }
        getTigerWcfAsyncTask().readOrderFiche(arrayList2, sales, list, responseListener);
    }
    public void readInvoiceFiche(int i2, DataObjectType dataObjectType, Sales sales, ResponseListener responseListener) {
        ArrayList<TigerServiceResult> arrayList = new ArrayList<>();
        arrayList.add(TigerSendDataCreator.getInstance().getReadServiceResult(i2, dataObjectType));
        getTigerWcfAsyncTask().readFiche(arrayList, sales, responseListener);
    }
    public class GetUserReports extends AsyncTask<Void, String, ResponseEvent> {
        private final ProgressDialogBuilder mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
        private final ResponseListener responseListener;
        private TigerSelectResult tempSelectResult;
        GetUserReports(ResponseListener responseListener) {
            this.responseListener = responseListener;
        }
        protected void onPreExecute() {
            super.onPreExecute();
            this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.str_transfer_get_starting)).setOnCancelClickListener().setCancelable(false).show();
        }
        public void lambdaonPreExecute0(DialogInterface dialogInterface) {
            cancel(true);
        }
        public ResponseEvent doInBackground(Void... voidArr) {
            ResponseEvent responseEvent = new ResponseEvent();
            boolean z = false;
            try {
                this.tempSelectResult = Tiger.this.getTigerQueryCreator().getUserParameters();
                Tiger.this.getTigerWcfAsyncTask().runExec(this.tempSelectResult);
                if (this.tempSelectResult.isSuccess()) {
                    Tiger.this.insertData(this.tempSelectResult.getDataList(), this.tempSelectResult.isTableDelete());
                    Tiger.this.saveUserMenuRights();
                    Tiger.this.getTigerWcfAsyncTask().getLastRxBool(Tiger.this.getTigerQueryCreator().getReports(), this.responseListener);
                    z = true;
                } else {
                    responseEvent.setErrorMessage(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                }
            } catch (Exception e2) {
                Log.e(Tiger.TAG, "doInBackground: ", e2);
                responseEvent.setErrorMessage(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
            responseEvent.setSuccess(z);
            return responseEvent;
        }
        public void onCancelled(ResponseEvent responseEvent) {
            this.mProgressDialogBuilder.dismiss();
            if (responseEvent != null) {
                responseEvent.setSuccess(false);
                responseEvent.setErrorMessage(ContextUtils.getStringResource(R.string.str_cancel_process));
            }
            super.onCancelled(responseEvent);
        }
        protected void onCancelled() {
            this.mProgressDialogBuilder.dismiss();
            super.onCancelled();
        }
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate(strArr);
            this.mProgressDialogBuilder.setMessage(strArr[0]);
        }
        public void onPostExecute(ResponseEvent responseEvent) {
            super.onPostExecute(responseEvent);
            this.mProgressDialogBuilder.dismiss();
        }
    }
    public boolean isFicheCustomerTransfered(String str, int i2) {
        Cursor cursor = null;
        int i3;
        try {
            cursor = null;
            try {
                cursor = getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM CLCARD WHERE LOGICALREF = (SELECT CLREF FROM " + str + " WHERE LOGICALREF =" + i2 + ")", null);
                if (cursor == null || !cursor.moveToFirst()) {
                    i3 = 0;
                } else {
                    String string = cursor.getString(0);
                    i3 = string == null ? 1 : Integer.valueOf(string).intValue();
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e2) {
                Log.i(TAG, "isFicheCustomerTransfered: ", e2);
                if (cursor != null) {
                    cursor.close();
                }
                i3 = 0;
            }
            return i3 == 1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
    public int getFicheCustomerRef(String str, int i2) {
        return getLogoSqlHelper().getFicheCustomerRef(str, i2);
    }
    public void getOnlineShipCustomer(String str, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }
    public void deletePenetrationRemote(String str, ResponseListener responseListener) {
        getTigerWcfAsyncTask().directRxBool(getTigerQueryCreator().deletePenetrationHeader(str), responseListener);
    }
    public String getCustomerUnsentDataTypes(int r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.design.Tiger.getCustomerUnsentDataTypes(int):java.lang.String");
    }
    public boolean isSalesDetailCurrTypeChange(int i2, int i3) {
        return getEnterPrice();
    }
    public void getSalesDetailStockTracking(SalesDetail salesDetail, int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getSalesDetailStockTracking(salesDetail, i2), responseListener);
    }
    public String getServerLongTime() {
        String nowDateTime = DateAndTimeUtils.nowDateTime();
        try {
            TigerSelectResult serverLongTime = getTigerQueryCreator().getServerLongTime();
            getTigerWcfAsyncTask().runExec(serverLongTime);
            if (!serverLongTime.isSuccess() || serverLongTime.getDataList() == null || serverLongTime.getDataList().size() <= 0 || serverLongTime.getDataList().get(0) == null) {
                return nowDateTime;
            }
            User user = (User) serverLongTime.getDataList().get(0);
            Log.d("Server_Time", "Remote Server Time:" + user.getWorkTime());
            return user.getWorkTime();
        } catch (Exception e2) {
            Log.e("Server_Time", "Remote Server Time Error:" + e2.getMessage());
            return nowDateTime;
        }
    }
    public String getServerTime() {
        String nowTime2 = DateAndTimeUtils.nowTime2();
        try {
            TigerSelectResult serverTime = getTigerQueryCreator().getServerTime();
            getTigerWcfAsyncTask().runExec(serverTime);
            if (!serverTime.isSuccess() || serverTime.getDataList() == null || serverTime.getDataList().size() <= 0 || serverTime.getDataList().get(0) == null) {
                return nowTime2;
            }
            User user = (User) serverTime.getDataList().get(0);
            Log.d("Server_Time", "Remote Server Time:" + user.getWorkTime());
            return user.getWorkTime();
        } catch (Exception e2) {
            Log.e("Server_Time", "Remote Server Time Error:" + e2.getMessage());
            return nowTime2;
        }
    }
    public void getServerTime(ResponseListener responseListener) {
        if (!ContextUtils.checkConnectionWithoutMessage()) {
            responseListener.onResponse(DateAndTimeUtils.nowTime2());
            return;
        }
        TigerSelectResult serverTime = getTigerQueryCreator().getServerTime();
        Observable.just(serverTime).doOnNext(new Consumer(serverTime) {
            public final TigerSelectResult f1 = null;
            public void accept(Object obj) {
                try {
                    Tiger.this.lambdagetServerTime2(this.f1, (TigerSelectResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {

                return obj;
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) {
                Log.e(Tiger.TAG, "runExec: ", (Throwable) obj);
            }
            public Object invoke(Object obj) {

                return obj;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer(responseListener) {
            public final ResponseListener f1 = null;
            public void accept(Object obj) {
                try {
                    Tiger.lambdagetServerTime4((TigerSelectResult) responseListener, this.f1, (TigerSelectResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    Tiger.lambdagetServerTime5(responseListener, (Throwable) obj);
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
    public void lambdagetServerTime2(TigerSelectResult tigerSelectResult, TigerSelectResult tigerSelectResult2) throws Exception {
        getTigerWcfAsyncTask().runExec(tigerSelectResult);
    }
    public static void lambdagetServerTime4(TigerSelectResult tigerSelectResult, ResponseListener responseListener, TigerSelectResult tigerSelectResult2) throws Exception {
        if (!tigerSelectResult.isSuccess() || tigerSelectResult.getDataList() == null || tigerSelectResult.getDataList().size() <= 0 || tigerSelectResult.getDataList().get(0) == null) {
            Log.d("Server_Time", "Remote Server Time Error:");
            responseListener.onResponse(DateAndTimeUtils.nowTime2());
            return;
        }
        User user = (User) tigerSelectResult.getDataList().get(0);
        Log.d("Server_Time", "Remote Server Time:" + user.getWorkTime());
        responseListener.onResponse(user.getWorkTime());
    }
    public static void lambdagetServerTime5(ResponseListener responseListener, Throwable th) throws Exception {
        Log.e("Server_Time", "Remote Server Time Error:" + th.getMessage());
        responseListener.onResponse(DateAndTimeUtils.nowTime2());
    }
    public void getAlternativePromotionItems(String str, String str2, ResponseListener responseListener) {
        Observable.create(new ObservableOnSubscribe(str, str2, responseListener) {
            public final String f1 = "";
            public final String f2 = "";
            public final ResponseListener f3 = null;
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    Tiger.this.lambdagetAlternativePromotionItems6(this.f1, this.f2, this.f3, observableEmitter);
                } catch (Exception e) {
                    observableEmitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<DataResponse<ItemSlip>>() {
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
    public void lambdagetAlternativePromotionItems6(String str, String str2, ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        TigerSelectResult campaignPromotionRef = getTigerQueryCreator().getCampaignPromotionRef(str, str2);
        if (campaignPromotionRef.isSuccess()) {
            getTigerWcfAsyncTask().runExec(campaignPromotionRef);
        } else {
            observableEmitter.onError(new Exception(campaignPromotionRef.getErrorMessage()));
            return;
        }
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getAlternativePromotionItemRefs(((KeyValuePair) campaignPromotionRef.getDataList().get(0)).getValue()), responseListener);
    }
    private class GetAlternativePromotionItemsTask extends AsyncTask<Void, Void, Void> {
        private final String campaignCode;
        private final String campaignLineNo;
        private final ResponseListener responseListener;
        GetAlternativePromotionItemsTask(String str, String str2, ResponseListener responseListener) {
            this.campaignCode = str;
            this.campaignLineNo = str2;
            this.responseListener = responseListener;
        }
        public Void doInBackground(Void... voidArr) {
            try {
                TigerSelectResult campaignPromotionRef = Tiger.this.getTigerQueryCreator().getCampaignPromotionRef(this.campaignCode, this.campaignLineNo);
                Tiger.this.getTigerWcfAsyncTask().runExec(campaignPromotionRef);
                if (!campaignPromotionRef.isSuccess()) {
                    return null;
                }
                Tiger.this.getTigerWcfAsyncTask().getLastRx(Tiger.this.getTigerQueryCreator().getAlternativePromotionItemRefs("parentref"), this.responseListener);
                return null;
            } catch (Exception e2) {
                Log.e(Tiger.TAG, "doInBackground: ", e2);
                return null;
            }
        }
        @Override
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
        }
    }
    public void getSalesManagers(ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getSalesManagers(), responseListener);
    }
    public void getCustomerNowAndBeforeBalance(int i2, Class cls, int i3, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCustomerNowAndBeforeBalance(i2, cls, i3), responseListener);
    }
    public void execWmsBarcodeSp(String str, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().execBarcodeSp(str), responseListener);
    }
    public List<ItemVariantStock> getVariantInfo(int r3, int r4, String r5) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.design.Tiger.getVariantInfo(int, int, java.lang.String):java.util.List");
    }
    public boolean canOrderCanTransferEInvoiceOrEArchive() {
        return new Version(getLogoSharedPreferences().getWCFVersion()).isAtLeast("2.60", true);
    }
    public void getLastPurchaseInfo(LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getLastPurchaseInfo(lastPurchaseInfoSqlParams.getItemRef()), responseListener);
    }
    public void getWarehouseItems(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getWarehouseItems(i2), responseListener);
    }
    public void getDiscountCardRatio(Sales sales, final String str, final ResponseListener responseListener) {
        TigerServiceResult tigerServiceResult;
        AnonymousClass6 r0 = new AnonymousClass6() {
            public void onResponse( Sales sales2) {
                Double valueOf = Double.valueOf(0.0d);
                if (sales2 == null) {
                    responseListener.onResponse(valueOf);
                }
                if (sales2.getMSalesDetailList().size() == 0) {
                    responseListener.onResponse(valueOf);
                }
                int findDiscountIndexByGuid = sales2.findDiscountIndexByGuid(str);
                if (findDiscountIndexByGuid == -1) {
                    Iterator<SalesDetail> it = sales2.getMSalesDetailList().iterator();
                    while (it.hasNext()) {
                        SalesDetail next = it.next();
                        int findDiscountIndexByGuid2 = next.findDiscountIndexByGuid(str);
                        if (findDiscountIndexByGuid2 != -1) {
                            responseListener.onResponse(Double.valueOf(next.getSalesFicheDiscountProps().getDiscountRatio(findDiscountIndexByGuid2).getDefinitionDouble()));
                            return;
                        }
                    }
                    return;
                }
                responseListener.onResponse(Double.valueOf(sales2.getSalesFicheDiscountProps().getDiscountRatio(findDiscountIndexByGuid).getDefinitionDouble()));
            }
            public void onFailure(Throwable throwable) {
            }

            public void onError(String str2) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        };
        FicheType ficheType = sales.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            tigerServiceResult = (TigerServiceResult) getSendCreator().getOrder(sales);
        } else if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
            tigerServiceResult = (TigerServiceResult) getSendCreator().getInvoice(sales);
        } else {
            tigerServiceResult = FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(ficheType) ? (TigerServiceResult) getSendCreator().getDispatch(sales) : null;
        }
        if (tigerServiceResult == null) {
            r0.onResponse(null);
            return;
        }
        tigerServiceResult.setApplyCampaign(0);
        getTigerWcfAsyncTask().getSalesOnlineTotal(tigerServiceResult, r0);
    }
    public void getObjectServiceVersion() {
        TigerSelectResult build = ( TigerSelectResult ) TigerSelectResult.newBuilder().build();
        getTigerWcfAsyncTask().runGetInfo(build);
        if (build.isCompleted() && build.isSuccess()) {
            DOMParser newInstance = DOMParser.newInstance();
            try {
                getLogoSharedPreferences().saveWCFVersion(newInstance.getElementTextByPath(newInstance.parseXml(build.getResultXML(), "utf-16"), "/ServiceInfo/ServiceVersion"));
            } catch (IOException unused) {
                Log.e("ObjectService", ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            } catch (ParserConfigurationException unused2) {
                Log.e("ObjectService", ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            } catch (SAXException unused3) {
                Log.e("ObjectService", ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        }
    }
    private GetValueResponse getFlagValue(int i2, boolean z) {
        return getTigerWcfAsyncTask().runGetFlagValue(i2, z);
    }
    public void fillTransferUserWhInfo(Sales sales) {
        sales.setTransferSourceWareHouse(new FicheRefProp(getUser().getWarehouseNr(), -1, ""));
        sales.setTransferSourceBranch(new FicheRefProp(getUser().getBranchNr(), -1, ""));
        sales.setTransferSourceFactory(new FicheRefProp(getUser().getFactNr(), -1, ""));
        sales.setTransferSourceCostGrp(new FicheRefProp(getUser().getCostGrp(), -1, ""));
    }
    public void listAllCustomersOnline(String str, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }
    public Disposable getEDocumentContent(String str, ResponseListener responseListener, SalesType salesType, Boolean bool) {
        if (SalesUtils.isSalesTypeDispatchOrWhTransfer(salesType)) {
            getTigerWcfAsyncTask().getEDespatchRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getEdespatchPdfHeader(str, bool)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getEdocumentPdfDetail(str, salesType)), responseListener);
            return null;
        } else if (!SalesUtils.isSalesTypeOnlyInvoice(salesType)) {
            return null;
        } else {
            getTigerWcfAsyncTask().getEInvoiceRxZip(getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getEInvoicePdfHeader(str, bool)), getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getEdocumentPdfDetail(str, salesType)), responseListener);
            return null;
        }
    }
    public String getWarehouseUnsentDataTypes(int r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.design.Tiger.getWarehouseUnsentDataTypes(int):java.lang.String");
    }
    public void updateSalesDetailItemStock(Sales sales) {
        String str = "";
        try {
            if (!(sales.getMSalesDetailList() == null || sales.getMSalesDetailList().size() == 0)) {
                List<ItemVariantStock> list = (List) sales.getMSalesDetailList().stream().filter(new Predicate() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda19

                    public boolean test(Object obj) {
                        return ((SalesDetail) obj).isHasVariant();
                    }
                }).map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda20
                    @Override // java.util.function.Function
                    public Object apply(Object obj) {
                        return Tiger.lambdaupdateSalesDetailItemStock8((SalesDetail) obj);
                    }
                }).distinct().collect(Collectors.toList());
                List<Integer> list2 = (List) sales.getMSalesDetailList().stream().filter(new Predicate() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda21

                    public boolean test(Object obj) {
                        return Tiger.lambdaupdateSalesDetailItemStock9((SalesDetail) obj);
                    }
                }).map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda22
                    @Override // java.util.function.Function
                    public Object apply(Object obj) {
                        return Integer.valueOf(((SalesDetail) obj).getItemRef());
                    }
                }).distinct().collect(Collectors.toList());
                List list3 = (List) sales.getMSalesDetailList().stream().filter(new Predicate() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda23

                    public boolean test(Object obj) {
                        return Tiger.lambdaupdateSalesDetailItemStock10((SalesDetail) obj);
                    }
                }).map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda24
                    @Override // java.util.function.Function
                    public Object apply(Object obj) {
                        return Tiger.lambdaupdateSalesDetailItemStock11((SalesDetail) obj);
                    }
                }).distinct().collect(Collectors.toList());
                List list4 = (List) sales.getMSalesDetailList().stream().map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda25
                    @Override // java.util.function.Function
                    public Object apply(Object obj) {
                        return Tiger.lambdaupdateSalesDetailItemStock12((SalesDetail) obj);
                    }
                }).distinct().collect(Collectors.toList());
                if (list2.size() != 0 || list.size() != 0) {
                    if (SalesUtils.isSalesTypeWhTransfer(sales.getmSalesType())) {
                        String str2 = " WAREHOUSENR = " + getUser().getWarehouseNr() + " ";
                        getProductShowAllStock(list2, str2, sales);
                        getVariantProductShowAllStock(list, str2, sales);
                    } else if (ErpCreator.getInstance().getmBaseErp().getProductShowAllStock()) {
                        String replace = getLogoSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
                        if (!replace.equals(str)) {
                            str = " WAREHOUSENR IN (" + replace + ") ";
                        }
                        getProductShowAllStock(list2, str, sales);
                        getVariantProductShowAllStock(list, str, sales);
                    } else {
                        if (list3.size() > 0) {
                            getItemStock("SELECT ITEMREF, SUM(ONHAND) ONHAND, SUM(REALSTOCK) AS REALSTOCK FROM ITEMSTOCK WHERE " + list3.stream().map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda26
                                @Override // java.util.function.Function
                                public Object apply(Object obj) {
                                    return Tiger.lambdaupdateSalesDetailItemStock13((ItemStock) obj);
                                }
                            }).collect(Collectors.joining(" OR ")) + " GROUP BY ITEMREF", list2, sales);
                        }
                        if (list4.size() > 0) {
                            getVariantStock(list, "SELECT ITEMREF, VARIANTREF, SUM(REALSTOCK) REALSTOCK, SUM(ONHAND) AS ONHAND FROM VARIANTSTOCK WHERE " + list4.stream().map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda27
                                @Override // java.util.function.Function
                                public Object apply(Object obj) {
                                    return Tiger.lambdaupdateSalesDetailItemStock14((VariantStock) obj);
                                }
                            }).collect(Collectors.joining(" OR ")) + " GROUP BY ITEMREF, VARIANTREF", sales);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            Log.e("Sales", "updateSalesDetailItemStock", e2);
        }
    }
    public static ItemVariantStock lambdaupdateSalesDetailItemStock8(SalesDetail salesDetail) {
        return new ItemVariantStock(salesDetail.getItemRef(), salesDetail.getVariant().getLogicalRef());
    }
    public static boolean lambdaupdateSalesDetailItemStock9(SalesDetail salesDetail) {
        return !salesDetail.isHasVariant();
    }
    public static boolean lambdaupdateSalesDetailItemStock10(SalesDetail salesDetail) {
        return !salesDetail.isHasVariant();
    }
    public static ItemStock lambdaupdateSalesDetailItemStock11(SalesDetail salesDetail) {
        return new ItemStock(salesDetail.getItemRef(), salesDetail.getWareHouse().getLogicalRef());
    }
    public static VariantStock lambdaupdateSalesDetailItemStock12(SalesDetail salesDetail) {
        return new VariantStock(salesDetail.getItemRef(), salesDetail.getVariant().getLogicalRef(), salesDetail.getWareHouse().getLogicalRef());
    }
    public static String lambdaupdateSalesDetailItemStock13(ItemStock itemStock) {
        return "(ITEMREF = " + itemStock.itemRef + " AND WAREHOUSENR = " + itemStock.wareHouseNr + ")";
    }
    public static String lambdaupdateSalesDetailItemStock14(VariantStock variantStock) {
        return "(ITEMREF = " + variantStock.getItemRef() + " AND VARIANTREF = " + variantStock.getVariantRef() + " AND WAREHOUSENR = " + variantStock.getWareHouseNr() + ")";
    }
    private void getProductShowAllStock(List<Integer> list, String str, Sales sales) {
        if (list.size() > 0) {
            getItemStock("SELECT ITEMREF, SUM(ONHAND) ONHAND, SUM(REALSTOCK) REALSTOCK FROM ITEMSTOCK WHERE " + str + " AND ITEMREF IN (" + list.stream().map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda16
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return String.valueOf(obj);
                }
            }).collect(Collectors.joining(",")) + ") GROUP BY ITEMREF", list, sales);
        }
    }
    private void getItemStock(String str, List<Integer> list, Sales sales) {
        List<ItemStock> itemStocksWithQuery = getItemStocksWithQuery(str);
        for (Integer num : list) {
            for (Object salesDetail : (List) sales.getMSalesDetailList().stream().filter(new Predicate(num) {
                public final Integer f0 = 0;
                public boolean test(Object obj) {
                    return Tiger.lambdagetItemStock15(this.f0, (SalesDetail) obj);
                }
            }).collect(Collectors.toList())) {
                List list2 = (List) itemStocksWithQuery.stream().filter(new Predicate(num) {
                    public final Integer f0 = 0;
                    public boolean test(Object obj) {
                        return Tiger.lambdagetItemStock16(this.f0, (ItemStock) obj);
                    }
                }).collect(Collectors.toList());
                salesDetail.equals(list2.size() == 0 ? 0.0d : ((ItemStock) list2.get(0)).onHand);
            }
        }
    }
    public static boolean lambdagetItemStock15(Integer num, SalesDetail salesDetail) {
        return salesDetail.getItemRef() == num.intValue();
    }
    public static boolean lambdagetItemStock16(Integer num, ItemStock itemStock) {
        return itemStock.itemRef == num.intValue();
    }
    private void getVariantStock(List<ItemVariantStock> list, String str, Sales sales) {
        List<ItemVariantStock> itemVariantStocksWithQuery = getItemVariantStocksWithQuery(str);
        for (ItemVariantStock itemVariantStock : list) {
            for (Object salesDetail : (List) sales.getMSalesDetailList().stream().filter(new Predicate() {
                public boolean test(Object obj) {
                    return Tiger.lambdagetVariantStock17(itemVariantStock, (SalesDetail) obj);
                }
            }).collect(Collectors.toList())) {
                List list2 = (List) itemVariantStocksWithQuery.stream().filter(new Predicate() {
                    public boolean test(Object obj) {
                        return Tiger.lambdagetVariantStock18(itemVariantStock, (ItemVariantStock) obj);
                    }
                }).collect(Collectors.toList());
                salesDetail.equals(list2.size() == 0 ? 0.0d : ((ItemVariantStock) list2.get(0)).getVariantActualStok());
            }
        }
    }
    public static boolean lambdagetVariantStock17(ItemVariantStock itemVariantStock, SalesDetail salesDetail) {
        return salesDetail.getItemRef() == itemVariantStock.getItemRef() && salesDetail.getVariant().getLogicalRef() == itemVariantStock.getVariantRef();
    }
    public static boolean lambdagetVariantStock18(ItemVariantStock itemVariantStock, ItemVariantStock itemVariantStock2) {
        return itemVariantStock2.getItemRef() == itemVariantStock.getItemRef() && itemVariantStock2.getVariantRef() == itemVariantStock.getVariantRef();
    }
    private void getVariantProductShowAllStock(List<ItemVariantStock> list, String str, Sales sales) {
        if (list.size() > 0) {
            getVariantStock(list, "SELECT ITEMREF, VARIANTREF, SUM(REALSTOCK) REALSTOCK, SUM(ONHAND) ONHAND FROM VARIANTSTOCK WHERE" + str + " AND (" + list.stream().map(new Function() { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda8
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return Tiger.lambdagetVariantProductShowAllStock19((ItemVariantStock) obj);
                }
            }).collect(Collectors.joining(" OR ")) + ") GROUP BY ITEMREF, VARIANTREF", sales);
        }
    }
    public static StringBuilder lambdagetVariantProductShowAllStock19(ItemVariantStock itemVariantStock) {
        StringBuilder sb = new StringBuilder();
        sb.append(" (ITEMREF = ");
        sb.append(itemVariantStock.getItemRef());
        sb.append(" AND VARIANTREF = ");
        sb.append(itemVariantStock.getVariantRef());
        sb.append(")");
        return sb;
    }
    public void getOnlineStockAllInSingleResponse(final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRxBoolPaging(getTigerQueryCreator().getItemStock(false), new ResponseListener<Boolean>() {
            public void onResponse(PrintSlipModel bool) {
                Tiger.this.getTigerWcfAsyncTask().getLastRxBoolPaging(Tiger.this.getTigerQueryCreator().getVariantStock(false), responseListener);
            }
            @Override
            public void onFailure(Throwable throwable) {
            }
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public List<ItemStock> getItemStocksWithQuery(String r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.design.Tiger.getItemStocksWithQuery(java.lang.String):java.util.List");
    }
    public List<ItemVariantStock> getItemVariantStocksWithQuery(String r4) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.design.Tiger.getItemVariantStocksWithQuery(java.lang.String):java.util.List");
    }
    public void deleteFicheSync(int i2, DataObjectType dataObjectType) {
        getTigerWcfAsyncTask().runDelete(TigerServiceResult.newBuilder().withDataReference(i2).withDataType(DataObjectType.ADDORDER).build());
    }
    public String getDbName() {
        return String.format("%s%s%s.db", this.mErpType.erpName, "MSData", this.mUserSettings.getDatabaseName());
    }
    public void createEDocumentDraft(int i2, FicheType ficheType, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }
    public void createEDocumentDraftAndSend(int i2, FicheType ficheType, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }
    public void showEDocument(int i2, final FicheType ficheType, final ResponseListener responseListener) {
        getSendCreator().getSqlManager().getSalesInvoiceExam(i2, new ResponseListener<Sales>() { // from class: com.proje.mobilesales.core.design.Tiger.8
            public void onResponse(PrintSlipModel sales) {
                Tiger.this.getEDocument(sales, ficheType, responseListener);
            }
            public void onFailure(Throwable throwable) {
            }
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void getEDocument(Sales sales, FicheType ficheType, ResponseListener responseListener) {
        EDocumentType eDocumentType;
        if (ficheType == FicheType.DISPATCH) {
            eDocumentType = EDocumentType.ebtEIrs;
        } else {
            eDocumentType = sales.getErpInvoiceType().getLogicalRef() == ErpInvoiceType.EInvoice.getmValue() ? EDocumentType.ebtEFatura : EDocumentType.ebtArsiv;
        }
        getTigerWcfAsyncTask().getEDocumentContentRx(sales.getAndFicheNo(), eDocumentType, responseListener);
    }
    public boolean canUseEDocumentOperations() {
        return new Version(getLogoSharedPreferences().getWCFVersion()).isAtLeast("2.70", true);
    }
    public boolean isDeviceInUseByAnotherUser() {
        try {
            TigerSelectResult deviceId = getTigerQueryCreator().getDeviceId();
            getTigerWcfAsyncTask().runExec(deviceId);
            if (!deviceId.isSuccess()) {
                return false;
            }
            if (deviceId.getResultXML().isEmpty()) {
                return true;
            }
            if (deviceId.getResultXML().contains(ContextUtils.getDeviceSerialNo())) {
                return false;
            }
            return !getUserSettings().isDemo();
        } catch (Exception e2) {
            Log.e(TAG, "isDeviceInUseByAnotherUser", e2);
            return false;
        }
    }
    public void sendRecvEInvoiceDocuments(final int i2, final int i3, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getEDocumentStatus(i2, EDocumentType.ebtEFatura), new ResponseListener<List<Object>>() {
            public void onResponse(PrintSlipModel list) {
                if (list == null || list.size() == 0) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
                    return;
                }
                int convertStringToInt = StringUtils.convertStringToInt(((KeyValuePair) list.get(0)).getValue());
                if (convertStringToInt == 0 || convertStringToInt == 1 || convertStringToInt == 2 || convertStringToInt == 3) {
                    Tiger.this.getTigerWcfAsyncTask().sendRecvEInvoiceDocumentsRx(Integer.toString(i2), new ResponseListener<EDocumentResponse>() {
                        public void onResponse( final PrintSlipModel eDocumentResponse) {
                            if (!eDocumentResponse.isSuccess()) {
                                responseListener.onResponse(eDocumentResponse);
                            } else {
                                Tiger.this.getTigerWcfAsyncTask().getLastRx(Tiger.this.getTigerQueryCreator().getEDocumentStatus(i2, EDocumentType.ebtEFatura), new ResponseListener<List<Object>>() { // from class: com.proje.mobilesales.core.design.Tiger.9.1.1
                                    public void onResponse(PrintSlipModel list2) {
                                        if (list2 == null || list2.size() == 0) {
                                            eDocumentResponse.setSuccess(false);
                                            eDocumentResponse.setErrorDesc(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
                                            responseListener.onResponse(eDocumentResponse);
                                            return;
                                        }
                                        int convertStringToInt2 = StringUtils.convertStringToInt(((KeyValuePair) list2.get(0)).getValue());
                                        if (convertStringToInt2 == 0 || convertStringToInt2 == 1 || convertStringToInt2 == 2 || convertStringToInt2 == 3) {
                                            eDocumentResponse.setSuccess(false);
                                            EDocumentResponse eDocumentResponse2 = eDocumentResponse;
                                            eDocumentResponse2.setErrorDesc(eDocumentResponse2.getMessage());
                                        } else {
                                            eDocumentResponse.setSuccess(true);
                                            Tiger.this.getLogoSqlHelper().updateEDocStatus(EDocStatus.Send, i3);
                                        }
                                        responseListener.onResponse(eDocumentResponse);
                                    }
                                    @Override
                                    public void onFailure(Throwable throwable) {
                                    }
                                    public void onError(String str) {
                                        responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                                    }
                                });
                            }
                        }
                        @Override
                        public void onFailure(Throwable throwable) {
                            
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
                        public void onResponse(EDocumentResponse obj) {

                        }

                        @Override
                        public void onResponse(ArrayList<EDocumentResponse> obj) {

                        }

                        @Override
                        public void onResponse() {

                        }

                        public void onError(String str) {
                            responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                        }
                    });
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_edoc_status_not_suitable_to_send));
                }
            }
            @Override
            public void onFailure(Throwable throwable) {
                
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
            public void onResponse(List<Object> obj) {

            }

            @Override
            public void onResponse(ArrayList<List<Object>> obj) {

            }

            @Override
            public void onResponse() {

            }

            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void sendEArchiveDocuments(final int i2, final int i3, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getEDocumentStatus(i2, EDocumentType.ebtArsiv), new ResponseListener<List<Object>>() {
            public void onResponse(PrintSlipModel list) {
                if (list == null || list.size() == 0) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
                    return;
                }
                int convertStringToInt = StringUtils.convertStringToInt(((KeyValuePair) list.get(0)).getValue());
                if (convertStringToInt == 0 || convertStringToInt == 1 || convertStringToInt == 2) {
                    Tiger.this.getTigerWcfAsyncTask().sendEArchiveDocumentsRx(Integer.toString(i2), new ResponseListener<EDocumentResponse>() {
                        public void onResponse( final PrintSlipModel eDocumentResponse) {
                            if (!eDocumentResponse.isSuccess()) {
                                responseListener.onResponse(eDocumentResponse);
                            } else {
                                Tiger.this.getTigerWcfAsyncTask().getLastRx(Tiger.this.getTigerQueryCreator().getEDocumentStatus(i2, EDocumentType.ebtArsiv), new ResponseListener<List<Object>>() { // from class: com.proje.mobilesales.core.design.Tiger.10.1.1
                                    public void onResponse(PrintSlipModel list2) {
                                        if (list2 == null || list2.size() == 0) {
                                            eDocumentResponse.setSuccess(false);
                                            eDocumentResponse.setErrorDesc(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
                                            responseListener.onResponse(eDocumentResponse);
                                            return;
                                        }
                                        int convertStringToInt2 = StringUtils.convertStringToInt(((KeyValuePair) list2.get(0)).getValue());
                                        if (convertStringToInt2 == 0 || convertStringToInt2 == 1 || convertStringToInt2 == 2) {
                                            eDocumentResponse.setSuccess(false);
                                            EDocumentResponse eDocumentResponse2 = eDocumentResponse;
                                            eDocumentResponse2.setErrorDesc(eDocumentResponse2.getMessage());
                                        } else {
                                            eDocumentResponse.setSuccess(true);
                                            Tiger.this.getLogoSqlHelper().updateEDocStatus(EDocStatus.Send, i3);
                                        }
                                        responseListener.onResponse(eDocumentResponse);
                                    }

                                    @Override
                                    public void onFailure(Throwable throwable) {

                                    }
                                    @Override
                                    public void onError(String str) {
                                        responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }

                        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                        public void onError(String str) {
                            responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                        }
                    });
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_edoc_status_not_suitable_to_send));
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

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
            public void onResponse(List<Object> obj) {

            }

            @Override
            public void onResponse(ArrayList<List<Object>> obj) {

            }

            @Override
            public void onResponse() {

            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void sendRecvEDispatchDocuments(final int i2, final int i3, final ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getEDocumentStatus(i2, EDocumentType.ebtEIrs), new ResponseListener<List<Object>>() {
            public void onResponse(PrintSlipModel list) {
                if (list == null || list.size() == 0) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
                    return;
                }
                int convertStringToInt = StringUtils.convertStringToInt(((KeyValuePair) list.get(0)).getValue());
                if (convertStringToInt == 0 || convertStringToInt == 1 || convertStringToInt == 2 || convertStringToInt == 3) {
                    Tiger.this.getTigerWcfAsyncTask().sendRecvEDispatchDocumentsRx(Integer.toString(i2), new ResponseListener<EDocumentResponse>() {
                        @Override
                        public void onResponse( final PrintSlipModel eDocumentResponse) {
                            if (!eDocumentResponse.isSuccess()) {
                                responseListener.onResponse(eDocumentResponse);
                            } else {
                                Tiger.this.getTigerWcfAsyncTask().getLastRx(Tiger.this.getTigerQueryCreator().getEDocumentStatus(i2, EDocumentType.ebtEIrs), new ResponseListener<List<Object>>() {
                                    public void onResponse(PrintSlipModel list2) {
                                        if (list2 == null || list2.size() == 0) {
                                            eDocumentResponse.setSuccess(false);
                                            eDocumentResponse.setErrorDesc(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
                                            responseListener.onResponse(eDocumentResponse);
                                            return;
                                        }
                                        int convertStringToInt2 = StringUtils.convertStringToInt(((KeyValuePair) list2.get(0)).getValue());
                                        if (convertStringToInt2 == 0 || convertStringToInt2 == 1 || convertStringToInt2 == 2 || convertStringToInt2 == 3) {
                                            eDocumentResponse.setSuccess(false);
                                            EDocumentResponse eDocumentResponse2 = eDocumentResponse;
                                            eDocumentResponse2.setErrorDesc(eDocumentResponse2.getMessage());
                                        } else {
                                            eDocumentResponse.setSuccess(true);
                                            Tiger.this.getLogoSqlHelper().updateEDocStatus(EDocStatus.Send, i3);
                                        }
                                        responseListener.onResponse(eDocumentResponse);
                                    }
                                    @Override
                                    public void onFailure(Throwable throwable) {

                                    }
                                    @Override
                                    public void onError(String str) {
                                        responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                                    }
                                });
                            }
                        }
                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                        @Override
                        public void onError(String str) {
                            responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                        }
                    });
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_edoc_status_not_suitable_to_send));
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
        });
    }
    public void getUsableCampaignCards(Sales sales, ResponseListener responseListener) {
        FicheType ficheType = sales.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            getTigerWcfAsyncTask().getUsableCampaignCards((TigerServiceResult) getSendCreator().getOrder(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
            getTigerWcfAsyncTask().getUsableCampaignCards((TigerServiceResult) getSendCreator().getInvoice(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(ficheType)) {
            getTigerWcfAsyncTask().getUsableCampaignCards((TigerServiceResult) getSendCreator().getDispatch(sales), responseListener);
        }
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public void getProductDetails(int i2, boolean z, ResponseListener responseListener) {
        Observable<ArrayList<ProductDetail>> productDetailObservable = getSendCreator().getSqlManager().getProductDetailObservable(getLogoSqlHelper().getProductDetailInfoSql(i2, z));
        Observable<ArrayList<ProductDetail.Ambar>> productWareHouseDetailObservable = getSendCreator().getSqlManager().getProductWareHouseDetailObservable(getLogoSqlHelper().getProductDetailStockSql(i2, z));
        Observable<ArrayList<ProductDetail.ItemPrice>> productPriceDetailObservable = getSendCreator().getSqlManager().getProductPriceDetailObservable(getLogoSqlHelper().getProductDetailPriceSql(i2));
        Observable<ArrayList<ProductDetail.DetailItemUnit>> productUnitDetailObservable = getSendCreator().getSqlManager().getProductUnitDetailObservable(getLogoSqlHelper().getProductDetailUnitSql(i2, z));
        Observable<Object> flatMap = isInternetOn().flatMap(new io.reactivex.functions.Function<Integer, Observable<R>>(i2) {
            public final int f1 = 0;
            public Observable<R> apply(Integer integer) {
                try {
                    return (Observable<R>) Tiger.this.lambdagetProductDetails21(this.f1, integer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Observable<R> apply(Object t) throws Exception {
                return null;
            }
            public Object invoke(Object obj) {
                return null;
            }
        });
        Observable<R> flatMap2 = isInternetOn().flatMap(new io.reactivex.functions.Function<Integer, Observable<R>>(i2) {
            public final int f1 = 0;
            public Observable<R> apply(Object t) throws Exception {
                return null;
            }
            public Object invoke(Object obj) {
                return null;
            }
        });
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable subscribeOn = Observable.defer(new Callable<Observable<R>>(productDetailObservable, productWareHouseDetailObservable, productPriceDetailObservable, productUnitDetailObservable, flatMap, flatMap2, responseListener) { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda2
            public final Observable f1;
            public final Observable f2;
            public final Observable f3;
            public final Observable f4;
            public final Observable f5;
            public final Observable f6;
            public final ResponseListener f7;
            {
                this.f1 = productDetailObservable;
                this.f2 = productWareHouseDetailObservable;
                this.f3 = productPriceDetailObservable;
                this.f4 = productUnitDetailObservable;
                this.f5 = flatMap;
                this.f6 = flatMap2;
                this.f7 = responseListener;
            }
            @Override   
            public Observable<R> call() {
                try {
                    return (Observable<R>) Tiger.this.lambdagetProductDetails25(this.f1, this.f2, this.f3, this.f4, this.f5, this.f6, this.f7);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        compositeDisposable.add(subscribeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() {
            public void accept(Object obj) {
                try {
                    Tiger.lambdagetProductDetails26(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {

                return obj;
            }
        }));
    }
    public ObservableSource lambdagetProductDetails21(int i2,  Integer bool) throws Exception {
        if (bool != null) {
            return getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getProductTopTenCustomer(i2)).onErrorReturn(new io.reactivex.functions.Function() {
                public Object apply(Object obj) {
                    try {
                        return Tiger.this.lambdagetProductDetails20((Throwable) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                public Object invoke(Object obj) {
                    return null;
                }
            });
        }
        return Observable.just(getTigerWcfAsyncTask().createEmptyErrorResponse(ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found)));
    }
    public Response lambdagetProductDetails20(Throwable th) throws Exception {
        return getTigerWcfAsyncTask().createEmptyErrorResponse(th.getMessage());
    }
    public ObservableSource lambdagetProductDetails23(int i2, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getProductMonthlySales(i2)).onErrorReturn(new io.reactivex.functions.Function() {
                @Override
                public Object apply(Object t) throws Exception {
                    return null;
                }
                public Object invoke(Object obj) {
                    try {
                        return Tiger.this.lambdagetProductDetails22((Throwable) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        return Observable.just(getTigerWcfAsyncTask().createEmptyErrorResponse(ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found)));
    }
    public Response lambdagetProductDetails22(Throwable th) throws Exception {
        return getTigerWcfAsyncTask().createEmptyErrorResponse(th.getMessage());
    }
    public ObservableSource lambdagetProductDetails25(Observable observable, Observable observable2, Observable observable3, Observable observable4, Observable observable5, Observable observable6, ResponseListener responseListener) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), observable4.subscribeOn(Schedulers.newThread()), observable5.subscribeOn(Schedulers.newThread()), observable6.subscribeOn(Schedulers.newThread()), new Function6(responseListener) { // from class: com.proje.mobilesales.core.design.TigerExternalSyntheticLambda30
            public final ResponseListener f1;

            {
                this.f1 = responseListener;
            }

            public Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                try {
                    return Tiger.this.lambdagetProductDetails24(this.f1, (ArrayList) obj, (ArrayList) obj2, (ArrayList) obj3, (ArrayList) obj4, (Response) obj5, (Response) obj6);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public ArrayList lambdagetProductDetails24(ResponseListener responseListener, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, Response response, Response response2) throws Exception {
        TigerSelectResult build = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETPRODUCTTOPTENCUSTOMER).build();
        TigerSelectResult build2 = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETPRODUCTMONTHLYSALES).build();
        getTigerWcfAsyncTask().mapResponse(response, build);
        getTigerWcfAsyncTask().mapResponse(response2, build2);
        if (arrayList.isEmpty()) {
            responseListener.onError(ContextUtils.getStringResource(R.string.str_data_not_found));
        } else {
            ProductDetail productDetail = (ProductDetail) arrayList.get(0);
            productDetail.setTopProducts((ArrayList) build.getDataList());
            productDetail.setMonthlyProductSalesList((ArrayList) build2.getDataList());
            productDetail.setAmbar(arrayList2);
            productDetail.setItemPrices(arrayList3);
            productDetail.setItemUnits(arrayList4);
        }
        return arrayList;
    }
    public static void lambdagetProductDetails26(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public void getProductDetailCharts(int i2, ResponseListener responseListener) {
        Observable<Object> flatMap = isInternetOn().flatMap(new io.reactivex.functions.Function(i2) {
            public Object apply(Object obj) {
                try {
                    return Tiger.this.lambdagetProductDetailCharts28(i2, (Boolean) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        });
        Observable<Object> flatMap2 = isInternetOn().flatMap(new io.reactivex.functions.Function(i2) {
            public Object apply(Object obj) {
                try {
                    return Tiger.this.lambdagetProductDetailCharts30(i2, (Boolean) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        });
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable<Object>   subscribeOn = Observable.defer(new Callable(flatMap, flatMap2) {
            @Override
            public Object call() throws Exception {
                return null;
            }
            public Object invoke(Object obj) {
                try {
                    int f1 = i2;
                    f2 = (Boolean) obj;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        compositeDisposable.add(subscribeOn.subscribe(new Consumer<Object>() {
            public void accept(Object obj) {
                try {
                    responseListener.onResponse(obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return obj;
            }
        }, new Consumer<Object>() {
            public void accept(Object obj) {
                try {
                    Tiger.lambdagetProductDetailCharts33(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return obj;
            }
        }));
    }
    public Observable<Response<ResponseEnvelope>> lambdagetProductDetailCharts28(int i2, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getProductTopTenCustomer(i2)).onErrorReturn(new io.reactivex.functions.Function<Throwable, Object>() {
                @Override
                public Object apply(Object obj) {
                    try {
                        return Tiger.this.lambdagetProductDetailCharts27((Throwable) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            });
        }
        return Observable.just(getTigerWcfAsyncTask().createEmptyErrorResponse(ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found)));
    }
    public Response lambdagetProductDetailCharts27(Throwable th) throws Exception {
        return getTigerWcfAsyncTask().createEmptyErrorResponse(th.getMessage());
    }
    public ObservableSource lambdagetProductDetailCharts30(int i2, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return getTigerWcfAsyncTask().createZipRxOne(getTigerQueryCreator().getProductMonthlySales(i2)).onErrorReturn(new io.reactivex.functions.Function() {
                @Override
                public Object apply(Object t) throws Exception {
                    return null;
                }
                @Override
                public Object invoke(Object obj) {
                    try {
                        return Tiger.this.lambdagetProductDetailCharts29((Throwable) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        return Observable.just(getTigerWcfAsyncTask().createEmptyErrorResponse(ContextUtils.getStringResource(R.string.exp_23_internet_connection_not_found)));
    }
    public Response lambdagetProductDetailCharts29(Throwable th) throws Exception {
        return getTigerWcfAsyncTask().createEmptyErrorResponse(th.getMessage());
    }
    public ObservableSource lambdagetProductDetailCharts32(Observable observable, Observable observable2) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), new BiFunction() {
            @Override
            public Object apply(Object obj, Object obj2) {
                try {
                    return Tiger.this.lambdagetProductDetailCharts31((Response) obj, (Response) obj2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj, Object obj2) {
                return apply(obj, obj2);
            }
        });
    }
    public ProductDetail lambdagetProductDetailCharts31(Response response, Response response2) throws Exception {
        TigerSelectResult build = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETPRODUCTTOPTENCUSTOMER).build();
        TigerSelectResult build2 = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETPRODUCTMONTHLYSALES).build();
        getTigerWcfAsyncTask().mapResponse(response, build);
        getTigerWcfAsyncTask().mapResponse(response2, build2);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setMonthlyProductSalesList((ArrayList) build2.getDataList());
        productDetail.setTopProducts((ArrayList) build.getDataList());
        return productDetail;
    }
    public static void lambdagetProductDetailCharts33(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public int getDateInt() {
        return DateAndTimeUtils.getLogoDateInt();
    }
    public void rejectOrder(String str, String str2, ResponseListener responseListener) {
        responseListener.onResponse();
    }
    public List<NotificationModel> getUserNotificationsToBeNotified() {
        try {
            BaseSelectResult userNotificationsToBeNotified = this.mBaseQueryCreator.getUserNotificationsToBeNotified();
            getTigerWcfAsyncTask().runExec(userNotificationsToBeNotified);
            return userNotificationsToBeNotified.isSuccess() ? userNotificationsToBeNotified.getDataList() : new ArrayList<NotificationModel>();
        } catch (Exception e2) {
            Log.e(TAG, "geUserNotificationsToBeNotified", e2);
            return new ArrayList<NotificationModel>();
        }
    }
    public List<NotificationModel> getUserNotifications(int i2, int i3, NotificationFilterModel notificationFilterModel) {
        try {
            BaseSelectResult userNotifications = this.mBaseQueryCreator.getUserNotifications(i2, i3, notificationFilterModel);
            getTigerWcfAsyncTask().runExec(userNotifications);
            return userNotifications.isSuccess() ? userNotifications.getDataList() : new ArrayList<NotificationModel>();
        } catch (Exception e2) {
            Log.e(TAG, "geUserNotifications", e2);
            return new ArrayList<NotificationModel>();
        }
    }
    public BaseSelectResult deleteNotification(NotificationModel notificationModel) {
        try {
            BaseSelectResult checkNotificationAvailableToDelete = checkNotificationAvailableToDelete(notificationModel);
            if (!checkNotificationAvailableToDelete.isSuccess()) {
                return checkNotificationAvailableToDelete;
            }
            BaseSelectResult deleteNotification = this.mBaseQueryCreator.deleteNotification(notificationModel);
            getTigerWcfAsyncTask().runDirect(deleteNotification);
            return deleteNotification;
        } catch (Exception e2) {
            Log.e(TAG, "deleteNotification", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public BaseSelectResult checkNotificationAvailableToDelete(NotificationModel notificationModel) {
        try {
            BaseSelectResult checkNotificationAvailableToDelete = this.mBaseQueryCreator.checkNotificationAvailableToDelete(notificationModel);
            getTigerWcfAsyncTask().runExec(checkNotificationAvailableToDelete);
            if (!checkNotificationAvailableToDelete.isSuccess()) {
                return checkNotificationAvailableToDelete;
            }
            if (!(checkNotificationAvailableToDelete.getDataList() == null || checkNotificationAvailableToDelete.getDataList().size() == 0 || !(checkNotificationAvailableToDelete.getDataList().get(0) instanceof KeyValuePair))) {
                if (Integer.parseInt(((KeyValuePair) checkNotificationAvailableToDelete.getDataList().get(0)).getValue()) == 0) {
                    return checkNotificationAvailableToDelete;
                }
                checkNotificationAvailableToDelete.setSuccess(false);
                checkNotificationAvailableToDelete.setErrorResourceId(R.string.str_notification_not_available_toDelete);
                return checkNotificationAvailableToDelete;
            }
            checkNotificationAvailableToDelete.setSuccess(false);
            checkNotificationAvailableToDelete.setErrorResourceId(R.string.str_data_not_found);
            return checkNotificationAvailableToDelete;
        } catch (Exception e2) {
            Log.e(TAG, "checkNotificationAvailableToDelete", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public List<NotifiedUserModel> getNotificationDetailsForSender(int i2) {
        try {
            BaseSelectResult notificationDetailsForSender = this.mBaseQueryCreator.getNotificationDetailsForSender(i2);
            getTigerWcfAsyncTask().runExec(notificationDetailsForSender);
            return notificationDetailsForSender.isSuccess() ? notificationDetailsForSender.getDataList() : new ArrayList();
        } catch (Exception e2) {
            Log.e(TAG, "getNotificationDetailsForSender", e2);
            return new ArrayList();
        }
    }
    public NotificationModel getNotification(int i2) {
        try {
            BaseSelectResult notification = this.mBaseQueryCreator.getNotification(i2);
            getTigerWcfAsyncTask().runExec(notification);
            if (notification.isSuccess()) {
                return notification.getDataList().isEmpty() ? new NotificationModel() : (NotificationModel) notification.getDataList().get(0);
            }
            return new NotificationModel();
        } catch (Exception e2) {
            Log.e(TAG, "getNotification", e2);
            return new NotificationModel();
        }
    }
    public BaseSelectResult updateNotifiedUserNotificationAsRead(int i2) {
        try {
            BaseSelectResult updateNotifiedUserNotificationAsRead = this.mBaseQueryCreator.updateNotifiedUserNotificationAsRead(i2);
            getTigerWcfAsyncTask().runDirect(updateNotifiedUserNotificationAsRead);
            return updateNotifiedUserNotificationAsRead;
        } catch (Exception e2) {
            Log.e(TAG, "updateNotifiedUserNotificationAsRead", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public TigerSelectResult updateNotifiedUserNotificationAsDelivered(int i2) {
        try {
            BaseSelectResult updateNotifiedUserNotificationAsDelivered = this.mBaseQueryCreator.updateNotifiedUserNotificationAsDelivered(i2);
            getTigerWcfAsyncTask().runDirect(updateNotifiedUserNotificationAsDelivered);
            return (TigerSelectResult) updateNotifiedUserNotificationAsDelivered;
        } catch (Exception e2) {
            Log.e(TAG, "updateNotifiedUserNotificationAsDelivered", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public NotifiedUserModel getNotifiedUser(int i2) {
        try {
            BaseSelectResult notifiedUser = this.mBaseQueryCreator.getNotifiedUser(i2);
            getTigerWcfAsyncTask().runExec(notifiedUser);
            if (notifiedUser.isSuccess()) {
                return notifiedUser.getDataList().isEmpty() ? new NotifiedUserModel() : (NotifiedUserModel) notifiedUser.getDataList().get(0);
            }
            return new NotifiedUserModel();
        } catch (Exception e2) {
            Log.e(TAG, "getNotifiedUser", e2);
            return new NotifiedUserModel();
        }
    }
    public BaseSelectResult updateNotificationAsReadIfAllUsersRead(String str) {
        try {
            BaseSelectResult updateNotificationAsReadIfAllUsersRead = this.mBaseQueryCreator.updateNotificationAsReadIfAllUsersRead(str);
            getTigerWcfAsyncTask().runDirect(updateNotificationAsReadIfAllUsersRead);
            return updateNotificationAsReadIfAllUsersRead;
        } catch (Exception e2) {
            Log.e(TAG, "updateNotificationAsReadIfAllUsersRead", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public List<NotificationUserSelectionModel> getUsersConnectedToMe(String str) {
        try {
            BaseSelectResult usersConnectedToMe = this.mBaseQueryCreator.getUsersConnectedToMe(str);
            getTigerWcfAsyncTask().runExec(usersConnectedToMe);
            return usersConnectedToMe.isSuccess() ? usersConnectedToMe.getDataList() : new ArrayList();
        } catch (Exception e2) {
            Log.e(TAG, "getUsersConnectedToMe", e2);
            return new ArrayList();
        }
    }
    public NetsisSelectResult updateNotificationAsDeliveredIfAllDelivered(String str) {
        try {
            BaseSelectResult updateNotificationAsDeliveredIfAllDelivered = this.mBaseQueryCreator.updateNotificationAsDeliveredIfAllDelivered(str);
            getTigerWcfAsyncTask().runDirect(updateNotificationAsDeliveredIfAllDelivered);
            return (TigerSelectResult) updateNotificationAsDeliveredIfAllDelivered;
        } catch (Exception e2) {
            Log.e(TAG, "updateNotificationAsDeliveredIfAllDelivered", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public TigerSelectResult saveNotificationAndUsers(NotificationModel notificationModel, List list) {
        try {
            BaseSelectResult saveNotification = this.mBaseQueryCreator.saveNotification(notificationModel);
            getTigerWcfAsyncTask().runDirect(saveNotification);
            if (saveNotification.isSuccess()) {
                Iterator<NotifiedUserModel> it = list.iterator();
                while (it.hasNext()) {
                    saveNotification = this.mBaseQueryCreator.saveNotifiedUsers(it.next());
                    getTigerWcfAsyncTask().runDirect(saveNotification);
                    if (!saveNotification.isSuccess()) {
                        return (TigerSelectResult) saveNotification;
                    }
                }
            }
            return (TigerSelectResult) saveNotification;
        } catch (Exception e2) {
            Log.e(TAG, "saveNotification", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public NotificationModel getNotificationByGuid(String str) {
        try {
            BaseSelectResult notificationByGuid = this.mBaseQueryCreator.getNotificationByGuid(str);
            getTigerWcfAsyncTask().runExec(notificationByGuid);
            if (notificationByGuid.isSuccess() && !notificationByGuid.getDataList().isEmpty()) {
                return (NotificationModel) notificationByGuid.getDataList().get(0);
            }
            return null;
        } catch (Exception e2) {
            Log.e(TAG, "getNotificationByGuid", e2);
            return null;
        }
    }
    public int isServerTimeInWorkingHours() {
        try {
            BaseSelectResult isServerTimeInWorkingHours = this.mBaseQueryCreator.isServerTimeInWorkingHours();
            getTigerWcfAsyncTask().runExec(isServerTimeInWorkingHours);
            if (!isServerTimeInWorkingHours.isSuccess()) {
                return -1;
            }
            if (!(isServerTimeInWorkingHours.getDataList() == null || isServerTimeInWorkingHours.getDataList().size() == 0)) {
                if (!(isServerTimeInWorkingHours.getDataList().get(0) instanceof KeyValuePair)) {
                    return -1;
                }
                return Integer.parseInt(((KeyValuePair) isServerTimeInWorkingHours.getDataList().get(0)).getValue());
            }
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "isServerTimeInWorkingHours", e2);
            return -1;
        }
    }
    public BaseSelectResult loginFromPeriodicWorker() {
        try {
            TigerSelectResult loginQuery = TigerQueryCreator.getLoginQuery(getUser(), Integer.toString(getUserSettings().getFirmNumber()));
            getTigerWcfAsyncTask().runExec(loginQuery);
            return loginQuery;
        } catch (Exception e2) {
            Log.e(TAG, "loginFromPeriodicWorker", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public TigerSelectResult getUserCountErrorSelectResult(UserCountModel userCountModel) {
        TigerSelectResult build = (TigerSelectResult) TigerSelectResult.newBuilder().build();
        build.setSuccess(false);
        build.setHasLicenseError(true);
        build.setErrorString(ContextUtils.getUserCountErrorString(userCountModel));
        return build;
    }
    public BaseSelectResult executeLoginFlow() {
        try {
            UserCountModel userCountInfo = getUserCountInfo();
            if (userCountInfo.hasError()) {
                return getUserCountErrorSelectResult(userCountInfo);
            }
            TigerSelectResult loginQuery = TigerQueryCreator.getLoginQuery(getUser(), Integer.toString(getUserSettings().getFirmNumber()));
            getTigerWcfAsyncTask().runExec(loginQuery);
            if (!loginQuery.isSuccess()) {
                return loginQuery;
            }
            if (loginQuery.getDataList().size() == 0) {
                loginQuery.setSuccess(ContextUtils.getStringResource(R.string.exp_5_user_not_found));
                return loginQuery;
            }
            getWorTables();
            if (isDeviceInUseByAnotherUser()) {
                loginQuery.setSuccess(ContextUtils.getStringResource(R.string.exp_4_device_use_other));
            }
            return loginQuery;
        } catch (Exception e2) {
            Log.e(TAG, "executeLoginFlow", e2);
            return TigerSelectResult.createErrorResult(e2.getMessage());
        }
    }
    public boolean isPromotionItemPriceEnabled() {
        return getLogoSqlHelper().getLogoParamValue(StringUtils.convertIntToString(ErpParamType.SALES_SPROMPRICECTRL.getmValue())).equals("1");
    }
    public String getLoadFicheDefaultCaseFirmNr() {
        return getUser().getFirmNr();
    }
    public void loadFicheDefaultProjectCode(FicheDiscountRefProp ficheDiscountRefProp, String str) {
        int convertStringToInt;
        if (!TextUtils.isEmpty(str) && (convertStringToInt = StringUtils.convertStringToInt(str)) > 0) {
            ficheDiscountRefProp.setLogicalRef(convertStringToInt);
            List table = getLogoSqlHelper().getTable(Project.class, "LOGICALREF=?", new String[]{str});
            if (!table.isEmpty()) {
                FicheStringProp.setDefinition(((Project) table.get(0)).getProje());
                ficheDiscountRefProp.setCode(((Project) table.get(0)).getCode());
            }
        }
    }
    public void loadFicheDefaultProjectCode(FicheRefProp ficheRefProp, String str) {
        int convertStringToInt;
        if (!TextUtils.isEmpty(str) && (convertStringToInt = StringUtils.convertStringToInt(str)) > 0) {
            ficheRefProp.setLogicalRef(convertStringToInt);
            List table = getLogoSqlHelper().getTable(Project.class, "LOGICALREF=?", new String[]{str});
            if (!table.isEmpty()) {
                FicheStringProp.setDefinition(((Project) table.get(0)).getProje());
                ficheRefProp.hashCode();
            }
        }
    }
    public Observable<Resource<Boolean>> customerHasDailyOperation(int i2) {
        return Observable.create(new ObservableOnSubscribe(i2) {
            public final int f1 = i2;
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    this.lambdacustomerHasDailyOperation34(this.f1, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            private void lambdacustomerHasDailyOperation34(int f1, ObservableEmitter observableEmitter) {
            }
        });
    }
    public void lambdacustomerHasDailyOperation34(int r4, ObservableEmitter r5) throws Exception {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.core.design.Tiger.lambdacustomerHasDailyOperation34(int, io.reactivex.ObservableEmitter):void");
    }
    public UserCountModel getUserCountInfo() {
        try {
            if (getUserSettings().isDemo()) {
                return new UserCountModel(0, 0, UserCountResult.UserCountInLimits);
            }
            TigerSelectResult worUserCount = getTigerQueryCreator().getWorUserCount();
            getTigerWcfAsyncTask().runExec(worUserCount);
            if (!worUserCount.isSuccess()) {
                return new UserCountModel(0, 0, UserCountResult.CouldNotReadWorUserCount);
            }
            if (!(worUserCount.getDataList().get(0) instanceof KeyValuePair)) {
                return new UserCountModel(0, 0, UserCountResult.CouldNotReadWorUserCount);
            }
            int parseInt = Integer.parseInt(((KeyValuePair) worUserCount.getDataList().get(0)).getValue());
            GetValueResponse flagValue = getFlagValue(LICENSE_USER_COUNT_FLAG, false);
            if (!flagValue.isSuccess()) {
                return new UserCountModel(parseInt, 0, UserCountResult.CouldNotReadLicenseUserCount);
            }
            int parseInt2 = Integer.parseInt(flagValue.getValue().getValue());
            if (parseInt2 <= 0) {
                return new UserCountModel(0, 0, UserCountResult.UserCountInLimits);
            }
            return new UserCountModel(parseInt, parseInt2, parseInt <= parseInt2 ? UserCountResult.UserCountInLimits : UserCountResult.WorUserCountExceedsLicenseUserCount);
        } catch (Exception e2) {
            Log.e(TAG, "getUserCountInfo", e2);
            return new UserCountModel(0, 0, UserCountResult.UnknownError);
        }
    }
    public int insertWorProcess(VisitInfo visitInfo) {
        TigerSelectResult insertVisit = getTigerQueryCreator().insertVisit(visitInfo);
        getTigerWcfAsyncTask().runDirect(insertVisit);
        if (insertVisit.isSuccess()) {
            getTigerQueryCreator();
            TigerSelectResult visitLogicalRef = TigerQueryCreator.getVisitLogicalRef(visitInfo.id);
            getTigerWcfAsyncTask().runExec(visitLogicalRef);
            if (visitLogicalRef.isSuccess()) {
                return ((DocNo) visitLogicalRef.getDataList().get(0)).getLogicalRef();
            }
        }
        return 0;
    }
    public boolean updateWorProcess(String str, int i2) {
        TigerSelectResult updateWorProcess = getTigerQueryCreator().updateWorProcess(str, i2);
        getTigerWcfAsyncTask().runDirect(updateWorProcess);
        return updateWorProcess.isSuccess();
    }
    public ActivePeriod getActivePeriod() {
        if (Preferences.getActivePeriod(ContextUtils.getmContext()) != null) {
            return Preferences.getActivePeriod(ContextUtils.getmContext());
        }
        TigerSelectResult activePeriod = getTigerQueryCreator().getActivePeriod();
        getTigerWcfAsyncTask().runExec(activePeriod);
        if (!activePeriod.isSuccess() || activePeriod.getDataList() == null || activePeriod.getDataList().size() <= 0) {
            return null;
        }
        ActivePeriod activePeriod2 = (ActivePeriod) activePeriod.getDataList().get(0);
        Preferences.setActivePeriod(ContextUtils.getmContext(), new Gson().toJson(activePeriod2));
        return activePeriod2;
    }
    public void getLastOrderProducts(int i2, boolean z, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getLastOrderProducts(i2, z), responseListener);
    }
    public void getRecommendedProducts(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getRecommendedProducts(i2), responseListener);
    }
    public void getRiskLimit(int i2, ResponseListener responseListener) {
        getTigerQueryCreator();
        getTigerWcfAsyncTask().getLastRx(TigerQueryCreator.getRiskLimit(i2), responseListener);
    }
    public ClRisk getCustomerRiskTotals(int i2) {
        TigerSelectResult customerRiskTotals = getTigerQueryCreator().getCustomerRiskTotals(i2, false);
        getTigerWcfAsyncTask().runExec(customerRiskTotals);
        if (!customerRiskTotals.isSuccess() || customerRiskTotals.getDataList() == null || customerRiskTotals.getDataList().size() <= 0) {
            return null;
        }
        return (ClRisk) customerRiskTotals.getDataList().get(0);
    }
    public void getOrderFicheStatus(ArrayList arrayList, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getOrderFicheStatus(arrayList), responseListener);
    }
    public void getCustomerRiskCalculate(int i2, ResponseListener responseListener) {
        getTigerWcfAsyncTask().getLastRx(getTigerQueryCreator().getCustomerRiskTotals(i2, false), responseListener);
    }
}
