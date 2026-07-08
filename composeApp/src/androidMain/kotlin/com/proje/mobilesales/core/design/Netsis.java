package com.proje.mobilesales.core.design;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTaskExternalSyntheticLambda20;
import com.proje.mobilesales.core.asynctask.NetsisRestAsyncTaskExternalSyntheticLambda73;
import com.proje.mobilesales.core.base.*;
import com.proje.mobilesales.core.data.SendCreatorImpl;
import com.proje.mobilesales.core.edocument.EDocumentResponse;
import com.proje.mobilesales.core.edocument.EDocumentType;
import com.proje.mobilesales.core.edocument.ShowEDocumentResponse;
import com.proje.mobilesales.core.emailreplacer.EmailObject;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.event.TransferEvent;
import com.proje.mobilesales.core.exception.CommunicationNotFoundException;
import com.proje.mobilesales.core.interfaces.ICustomerSendCompleted;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.*;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.cari.CariEkBilgi;
import com.proje.mobilesales.core.netsis.sendmodel.cari.CariTemelBilgi;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.CheckAndPNotesMainParam;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedMain;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedType;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.DocumentBoxType;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.DraftEDocumentParam;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.SendEDocumentParam;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.ShowEDocumentParam;
import com.proje.mobilesales.core.netsis.sendmodel.print.*;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceipt;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMain;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMainParam;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDeposit;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDepositParam;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipLine;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisInvoiceType;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisSlipType;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.service.LocationUpdatesService;
import com.proje.mobilesales.core.sql.SqlBrite;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlBrite;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlHelper;
import com.proje.mobilesales.core.sql.netsis.NetsisSqlManager;
import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.core.view.CustomToast;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.collections.casefiche.model.CaseFiche;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditFiche;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeedFiche;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClRisk;
import com.proje.mobilesales.features.dbmodel.*;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.gpsinfo.model.database.GpsInfo;
import com.proje.mobilesales.features.licence.LicenseUtils;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.model.Response;
import com.proje.mobilesales.features.notification.model.NotificationFilterModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
import com.proje.mobilesales.features.product.model.LastItemPriceSqlParams;
import com.proje.mobilesales.features.product.model.OnlineItemPriceParameters;
import com.proje.mobilesales.features.product.model.ProductDetail;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
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
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function5;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.UnknownNullability;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static com.proje.mobilesales.core.utils.AppUtils.handleNetworkError;
import static com.proje.mobilesales.core.utils.AppUtils.stopMyService;

public class Netsis extends BaseErp {
    private static final String TAG = "com.proje.mobilesales.core.design.Netsis";
    public static C261711 AnonymousClass4;
    private final NetsisQueryCreator mQueryCreator;
    public SharedPreferences prefs;
    public void addNewCustomer(CustomerNew customerNew) {
    }
    public void addNewCustomer(CustomerNew customerNew, boolean z) {
    }
    public boolean canOrderCanTransferEInvoiceOrEArchive() {
        return false;
    }
    public boolean canShowLastPurchaseInfo() {
        return true;
    }
    public boolean canUseEDocumentOperations() {
        return true;
    }
    public void cancelInvoiceFiche(int r1, ResponseListener responseListener) {
    }
    public void deleteFicheSync(int r1, DataObjectType dataObjectType) {
    }
    public void getAlternativePromotionItems(String str, String str2, ResponseListener responseListener) {
    }
    public void getCheckSeriGroup(int r1, int r2, SalesType salesType, boolean z, int r5, boolean z2, ResponseListener responseListener) {
    }
    public void getCustomerCampaignPoint(int r1) {
    }
    public void getCustomerImage(int r1, ResponseListener responseListener) {
    }
    public int getLoadFicheDefaultCaseSql() {
        return R.string.net_qry_case_where;
    }
    public void getOnlineDistributionList(int r1, ResponseListener responseListener) {
    }
    public void getOnlinePrice(int i, int i2, int i3, boolean z, int i4, int i5, String str, int i6, ResponseListener responseListener, String str2, int i7) {
    }
    public void getOrderState(int r1, ResponseListener responseListener) {
    }
    public void getOrderStatus(int r1) {
    }
    public void getSalesDetailStockTracking(SalesDetail salesDetail, int r2, ResponseListener responseListener) {
    }
    public PaymentLine getSalesFichePayPlanTypeCash(String str) {
        return null;
    }
    public void getSelectedDistributions(int r1, int r2, ResponseListener responseListener) {
    }
    public String getTodoEmailBody(TodoInfoDb todoInfoDb) {
        return null;
    }
    public boolean isDemand() {
        return false;
    }
    public boolean isPromotionItemPriceEnabled() {
        return true;
    }
    public boolean isRetailInvoice() {
        return false;
    }
    public void readInvoiceFiche(int r1, DataObjectType dataObjectType, Sales sales, ResponseListener responseListener) {
    }
    public void saveClCardLastTransDate() {
    }
    public void saveStockLastTransDate() {
    }
    public void updateDataLogo(MobileSalesUpdateType mobileSalesUpdateType, int r2) {
    }
    public Netsis(ErpType erpType, CommunicationType communicationType) throws CommunicationNotFoundException {
        super(erpType, communicationType);
        getErpComponent().inject(this);
        if (createLogoDBPath()) {
            SqlBrite sqlBriteBuild = new NetsisSqlBrite.Builder().logger(null).sqlCreator(getLogoSqlHelper().getLogoSqlCreator()).build();
            this.logoSqlBrite = sqlBriteBuild;
            setLogoSqlBriteDatabase(sqlBriteBuild.wrapDatabaseHelper((SQLiteOpenHelper) getLogoSqlHelper()));
            this.logoSqlBriteDatabase.setLoggingEnabled(true);
        }
        createCommunication(this.mErpType, communicationType);
        this.mQueryCreator = new NetsisQueryCreator(getLogoSqlHelper(), getUser(), this.mUserSettings);
        this.mSendCreator = new SendCreatorImpl(new NetsisSqlManager(Schedulers.io()));
        this.prefs = Preferences.getSecurePreferences(ContextUtils.getmContext());
    }
    public RiskAlert getCustomerRiskAlert(SalesType salesType, int r7) {
        RiskAlert riskAlert;
        RiskAlert riskAlert2 = RiskAlert.UNDEFINED;
        InvoiceStatus.Companion companion = InvoiceStatus.Companion;
        if (getLogoSqlHelper().getLogoParamValue("MUSTERI_RISK_UYARISI").equals("H")) {
            return RiskAlert.CONTINUE;
        }
        String str = "";
        switch (C263931.SwitchMapcomlogomobilesalescoreenumsSalesType[salesType.ordinal()]) {
            case 1:
            case 5:
            case 6:
                riskAlert = riskAlert2;
                break;
            case 2:
                str = "SIPRISKDAVRAN";
                riskAlert = riskAlert2;
                break;
            case 3:
            case 4:
                str = "FATRISKDAVRAN";
                riskAlert = riskAlert2;
                break;
            case 7:
                str = "IRSRISKDAVRAN";
                riskAlert = riskAlert2;
                break;
            default:
                riskAlert = RiskAlert.CONTINUE;
                break;
        }
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = getLogoSqlBriteDatabase().query(ContextUtils.getmContext().getString(R.string.qry_risk_over_alert_value, str), String.valueOf(r7));
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    riskAlert2 = riskAlert;
                } else {
                    int r5 = cursorQuery.getInt(0);
                    if (r5 == 0) {
                        riskAlert2 = RiskAlert.ABORT;
                    } else if (r5 == 1 || r5 != 2) {
                        riskAlert2 = RiskAlert.CONTINUE;
                    }
                }
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
                return riskAlert2;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (true) {
                    return riskAlert;
                }
                cursor.close();
                return riskAlert;
            }
        } catch (Throwable th) {
            if (false) {
                cursor.close();
            }
            throw th;
        }
    } 
    static class C263931 {
        static final int[] SwitchMapcomlogomobilesalescoreenumsSalesType;

        static {
            int[] r0 = new int[SalesType.values().length];
            SwitchMapcomlogomobilesalescoreenumsSalesType = r0;
            try {
                r0[SalesType.FREE.ordinal()] = 1;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsSalesType[SalesType.ORDER.ordinal()] = 2;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsSalesType[SalesType.INVOICE.ordinal()] = 3;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsSalesType[SalesType.RETAIL_INVOICE.ordinal()] = 4;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsSalesType[SalesType.RETURN_INVOICE.ordinal()] = 5;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsSalesType[SalesType.RETAIL_RETURN_INVOICE.ordinal()] = 6;
            } catch (NoSuchFieldError _) {
            }
            try {
                SwitchMapcomlogomobilesalescoreenumsSalesType[SalesType.DISPATCH.ordinal()] = 7;
            } catch (NoSuchFieldError _) {
            }
        }
    }
    protected boolean createLogoDBPath() {
        try {
            String dbName = getDbName();
            ContextUtils.migratePreviousDbFilesIfExists(dbName);
            setLogoSqlHelper(new NetsisSqlHelper(ContextUtils.getmContext(), ContextUtils.getmContext().getDatabasePath(dbName).getPath()));
            return true;
        } catch (Exception e2) {
            Log.e(TAG, "createLogoDB: ", e2);
            return true;
        }
    }
    public void getWorTables() {
        try {
            if (ContextUtils.checkConnectionWithoutMessage()) {
                NetsisSelectResult worTables = this.mQueryCreator.getWorTables();
                getRestAsyncTask().lambdagetSelectRxn24();
                if (worTables.isSuccess() && worTables.getDataList() != null && worTables.getDataList().size() > 0) {
                    insertOrDeleteWorTables((ArrayList) worTables.getDataList());
                }
                List table = getLogoSqlHelper().getTable(WorTables.class);
                WorTableNames.getInstance().clearTableNames();
                Iterator it = table.iterator();
                while (it.hasNext()) {
                    WorTableNames.getInstance().addToTableNames(((WorTables) it.next()).getName());
                }
                return;
            }
            WorTableNames.getInstance().clearTableNames();
        } catch (Exception e2) {
            Log.e(TAG, "getWorTables", e2);
        }
    }
    public void loadCurrencyBalances(int r1, String str, String str2, ResponseListener responseListener) {
        responseListener.onResponse();
    }
    public void login(String str, String str2, boolean z, boolean z2) {
        if (z && !ContextUtils.checkConnectionWithoutMessage() && checkUserNamePassword(str, str2)) {
            postEvent(new Response(true));
            return;
        }
        getUser().setUserName(str);
        getUser().setPassword(str2);
        getLogoSharedPreferences().saveNetsisTokenManager(null);
        new Login(z2).execute();
    }
    public void checkEnterpriseBranchAuthorization(Response response) {
        try {
            @UnknownNullability NetsisServiceResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (netsisSelectResultRunSelect.isSuccess()) {
                if (((ActivePeriod) netsisSelectResultRunSelect.getDataList().get(0)).getAuth() == 0) {
                    response.setMessage(ContextUtils.getStringResource(R.string.exp_59_netsis_user_does_not_have_authorization));
                } else {
                    response.setMessage(ContextUtils.getStringResource(R.string.exp_5_user_not_found));
                }
            } else {
                response.setMessage(netsisSelectResultRunSelect.getErrorString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    final class Login extends AsyncTask<Void, String, Response> {
        private final boolean mDemo;
        private final ProgressDialogBuilder mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
        Login(boolean z) {
            this.mDemo = z;
        }
        protected void onPreExecute() {
            this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.type_login)).setCancelable(true).setOnCancelClickListener().show();
            super.onPreExecute();
        }
        public void lambdaonPreExecute0(DialogInterface dialogInterface) {
            cancel(true);
        }
        public Response doInBackground(Void... voidArr) {
            @UnknownNullability NetsisServiceResult netsisSelectResultRunSelect;
            NetsisSelectResult netsisSelectResultRunInsert;
            Response response = new Response(false);
            Log.d(Netsis.TAG, "doInBackground: async netsis start");
            publishProgress(ContextUtils.getStringResource(R.string.str_please_wait));
            Netsis.this.getWorTables();
            NetsisSelectResult loginUserInformation = Netsis.this.mQueryCreator.getLoginUserInformation();
            publishProgress(ContextUtils.getStringResource(loginUserInformation.getProcessType().resId));
            try {
                @UnknownNullability NetsisServiceResult netsisSelectResultRunSelect2 = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                if (!netsisSelectResultRunSelect2.isSuccess()) {
                    if (this.mDemo) {
                        response.setMessage(ContextUtils.getStringResource(R.string.exp_57_working_on_demo_server));
                    } else {
                        response.setMessage(netsisSelectResultRunSelect2.getErrorString());
                    }
                } else if (netsisSelectResultRunSelect2.getDataList().size() == 0) {
                    Netsis.this.checkEnterpriseBranchAuthorization(response);
                } else {
                    Netsis.this.user = (User) netsisSelectResultRunSelect2.getDataList().get(0);
                    Netsis.this.mQueryCreator.setUser(Netsis.this.user);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                        Netsis.this.checkAppVersionColumn();
                    }
                    Netsis.this.getLogoSharedPreferences().saveUser(Netsis.this.user);
                    if (!this.mDemo) {
                        @UnknownNullability NetsisServiceResult netsisSelectResultRunSelect3 = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                        publishProgress(ContextUtils.getStringResource(netsisSelectResultRunSelect3.getProcessType().resId));
                        if (netsisSelectResultRunSelect3.isSuccess()) {
                            String deviceSerialNo = ContextUtils.getDeviceSerialNo();
                            if (netsisSelectResultRunSelect3.getDataList().size() != 0) {
                                for (Object deviceId : netsisSelectResultRunSelect3.getDataList()) {
                                    if (!TextUtils.isEmpty(deviceId.deviceSerialNo1) && !deviceId.deviceSerialNo1.equals(deviceSerialNo) && !Netsis.this.getUserSettings().isDemo()) {
                                        response.setMessage(ContextUtils.getStringResource(R.string.exp_4_device_use_other));
                                        break;
                                    }
                                }
                            } else {
                                if (StringUtils.compareVersionNames(Preferences.getAppVersionAddedVersion(Netsis.this.prefs), ((PanelVersion) Netsis.this.getRestAsyncTask().lambdagetSelectRxn24().getDataList().get(0)).version) <= 0 && StringUtils.compareVersionNames(Preferences.getAppVersionAddedVersion(Netsis.this.prefs), BuildConfig.VERSION_NAME) <= 0) {
                                    netsisSelectResultRunInsert = Netsis.this.getRestAsyncTask().runInsert(Netsis.this.mQueryCreator.insertDeviceIdWithVersion(deviceSerialNo, BuildConfig.VERSION_NAME));
                                } else {
                                    netsisSelectResultRunInsert = Netsis.this.getRestAsyncTask().runInsert(Netsis.this.mQueryCreator.insertDeviceId(deviceSerialNo));
                                }
                                publishProgress(ContextUtils.getStringResource(netsisSelectResultRunInsert.getProcessType().resId));
                                if (!netsisSelectResultRunInsert.isSuccess()) {
                                    response.setMessage(netsisSelectResultRunInsert.getErrorString());
                                }
                            }
                            netsisSelectResultRunSelect = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                            publishProgress(ContextUtils.getStringResource(netsisSelectResultRunSelect.getProcessType().resId));
                            if (!netsisSelectResultRunSelect.isSuccess()) {
                                Netsis.this.insertData(netsisSelectResultRunSelect.getDataList(), netsisSelectResultRunSelect.isTableDelete());
                                Netsis.this.saveErpRights(netsisSelectResultRunSelect.getDataList());
                                LicenseUtils.setProductInfo(netsisSelectResultRunSelect.getDataList());
                                NetsisSelectResult netsisSelectResultRunSelect4 = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                                LicenseUtils.changeLicenseKey(LicenseUtils.getEncryptedProductInfo());
                                if (netsisSelectResultRunSelect4.isSuccess()) {
                                    Netsis.this.saveUserMenuRights();
                                    String strCheckWorkTimeControl = Netsis.this.checkWorkTimeControl(WorkTimeControlProcessType.Login);
                                    if (!strCheckWorkTimeControl.isEmpty()) {
                                        response.setMessage(strCheckWorkTimeControl);
                                        return response;
                                    } else if (LicenseUtils.isExpired()) {
                                        response.setSuccess(false);
                                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                                            public void run() {
                                                if (Netsis.this.getUser().getType().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                                    CustomToast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_license_expiry_date_sales_managers), 1, 5).show();
                                                } else if (Netsis.this.getUser().getType().equals("0")) {
                                                    CustomToast.makeText(ContextUtils.getmContext(), ContextUtils.getStringResource(R.string.exp_license_expiry_date_sales_persons), 1, 5).show();
                                                }
                                            }
                                        });
                                    } else if (LicenseUtils.isInWarningDays() && Netsis.this.getUser().getType().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                        response.setSuccess(true);
                                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                                            public void run() {
                                                CustomToast.makeText(ContextUtils.getmContext(), String.format(ContextUtils.getStringResource(R.string.exp_license_warning_sales_managers), DateAndTimeUtils.convertStringDate(LicenseUtils.getProductExpiryDate(), "yyyy.MM.dd", "dd/MM/yyy")), 1, 5).show();
                                            }
                                        });
                                    } else {
                                        response.setSuccess(true);
                                    }
                                } else {
                                    response.setMessage(netsisSelectResultRunSelect4.getErrorString());
                                }
                            } else {
                                response.setMessage(netsisSelectResultRunSelect.getErrorString());
                            }
                        } else {
                            response.setMessage(netsisSelectResultRunSelect3.getErrorString());
                            return response;
                        }
                    } else {
                        netsisSelectResultRunSelect = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                        publishProgress(ContextUtils.getStringResource(netsisSelectResultRunSelect.getProcessType().resId));
                        if (!netsisSelectResultRunSelect.isSuccess()) {
                            response.setMessage(netsisSelectResultRunSelect.getErrorString());
                            return response;
                        }
                    }
                }
            } catch (Exception e2) {
                Log.e(Netsis.TAG, "doInBackground: ", e2);
                response.setMessage(e2.getMessage());
                publishProgress(e2.getMessage());
            }
            return response;
        }
        public void onCancelled(Response response) {
            this.mProgressDialogBuilder.dismiss();
            if (response != null) {
                response.setSuccess(false);
                response.setMessage(ContextUtils.getStringResource(R.string.str_cancel_process));
                Netsis.this.postEvent(response);
            }
            super.onCancelled( response);
        }
        protected void onCancelled() {
            this.mProgressDialogBuilder.dismiss();
            Netsis.this.postEvent(new Response(ContextUtils.getStringResource(R.string.str_cancel_process), false));
            super.onCancelled();
        }
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate( strArr);
            this.mProgressDialogBuilder.setMessage(strArr[0]);
        }
        public void onPostExecute(Response response) {
            super.onPostExecute( response);
            this.mProgressDialogBuilder.dismiss();
            Netsis.this.postEvent(response);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void checkAppVersionColumn() {
        if (ContextUtils.checkConnectionWithoutMessage()) {
            try {
                @UnknownNullability NetsisServiceResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
                if (netsisSelectResultRunSelect.isSuccess() && netsisSelectResultRunSelect.getDataList() != null && !netsisSelectResultRunSelect.getDataList().isEmpty()) {
                    FDateUtils.getInstance().updateValues((CheckFDateModel) netsisSelectResultRunSelect.getDataList().getFirst());
                }
            } catch (Exception e2) {
                Log.e(TAG, "checkAppVersionColumn", e2);
            }
            Log.d(TAG, FDateUtils.getInstance().getValuesForLog());
        }
    }
    public void saveUserMenuRights() {
        getUserMenuRights().setMenuRights(this.mErpType, getLogoSqlHelper().getParamValue(ParameterTypes.ptAndroidMenuRights), getErpRights().isPro());
        getLogoSharedPreferences().saveUserMenuRights(getUserMenuRights());
    }
    public void saveErpRights(List<Param> list) {
        ErpType erpType = ErpType.NETSIS;
        while (true) {
            boolean z = false;
            for (Param param : list) {
                try {
                    if (param.getParamNo().equals("99")) {
                        if (!param.getParamValue().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                            if (param.getParamValue().equals("1")) {
                                break;
                            }
                        } else {
                            z = true;
                        }
                    } else {
                        continue;
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "saveErpRights : " + e2.getMessage());
                }
            }
            this.erpRights = new ErpRights(erpType, z);
            getLogoSharedPreferences().saveErpRights(getErpRights());
            return;
        }
    }
    private boolean checkUserNamePassword(String str, String str2) {
        return str != null && str.equals(getUser().getUserName()) && str2 != null && str2.equals(getUser().getPassword());
    }
    public void sendMail(EmailObject emailObject, boolean z) {
        if (MainActivity.getDataFlag) {
            new SendMail(emailObject, z).execute();
        }
    }
    public void sendMail(DataObjectType dataObjectType, int r3) {
        if (MainActivity.getDataFlag) {
            new SendMail(dataObjectType, r3).execute();
        }
    }
    class SendMail extends AsyncTask<Void, Void, Void> {
        Object data;
        EmailObject emailObject;
        ProgressDialogBuilder mProgressDialogBuilder;
        String msg;
        DataObjectType objectType;
        boolean showProgress;

        SendMail(DataObjectType dataObjectType, int r3) {
            this.objectType = dataObjectType;
            this.data = Netsis.this.getObject(r3, true);
            this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
        }

        SendMail(EmailObject emailObject, boolean z) {
            this.emailObject = emailObject;
            this.showProgress = z;
            if (z) {
                this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
            }
        }
        public void onPreExecute() {
            super.onPreExecute();
            if (this.showProgress) {
                this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.str_sending_email)).setOnCancelClickListener().setCancelable(false).show();
            }
        }
        public  void lambdaonPreExecute0(DialogInterface dialogInterface) {
            cancel(true);
        }
        public Void doInBackground(Void... voidArr) {
            BaseCommunication baseCommunication = Netsis.this.getBaseCommunication();
            try {
                EmailObject emailObject = this.emailObject;
                if (emailObject == null) {
                    return null;
                }
                this.msg = baseCommunication.sendMail(emailObject);
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        public void onPostExecute(Void r2) {
            super.onPostExecute(  r2);
            if (this.showProgress) {
                this.mProgressDialogBuilder.dismiss();
            }
            try {
                String str = this.msg;
                if (str != null) {
                    str.isEmpty();
                }
            } catch (Exception e2) {
                Log.e(Netsis.TAG, "checkConnection: ", e2);
            }
        }
    }
    public void getAllDataLogo(boolean z, boolean z2) {
        if (MainActivity.getDataFlag) {
            new GetAllData(z2).execute();
        }
    }
    class GetAllData extends AsyncTask<Void, String, TransferEvent> {
        private boolean forService;
        private ProgressDialogBuilder mProgressDialogBuilder;
        private final TransferGet mTransferGet;
        GetAllData(boolean z) {
            this.forService = false;
            MainActivity.getDataFlag = false;
            this.forService = z;
            if (!z) {
                this.mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
            }
            this.mTransferGet = new TransferGet();
        }
        protected void onPreExecute() {
            if (!this.forService) {
                this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.type_login)).setCancelable(true).setOnCancelClickListener().show();
            }
            super.onPreExecute();
        }
        public  void lambdaonPreExecute0(DialogInterface dialogInterface) {
            cancel(true);
        }
        public TransferEvent doInBackground(Void... voidArr) {
            TransferEvent transferEvent = new TransferEvent();
            publishProgress(ContextUtils.getStringResource(R.string.str_please_wait));
            boolean z = true;
            try {
                try {
                    try {
                    } catch (Throwable th) {
                        th = th;
                        transferEvent.setTransferGet(this.mTransferGet);
                        MainActivity.getDataFlag = z;
                        throw th;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    transferEvent.setTransferGet(this.mTransferGet);
                    MainActivity.getDataFlag = true;
                }
                if (Netsis.this.isDeviceInUseByAnotherUser()) {
                    transferEvent.setErrorMessage(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                    transferEvent.setSuccess(false);
                    transferEvent.setTransferGet(this.mTransferGet);
                    MainActivity.getDataFlag = true;
                    return transferEvent;
                }
                publishProgress(ContextUtils.getStringResource(R.string.type_get_user_param));
                Netsis.this.getWorTables();
                NetsisSelectResult netsisSelectResultRunSelect = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                if (netsisSelectResultRunSelect.isSuccess()) {
                    transferEvent.setSuccess(true);
                    ArrayList arrayList = new ArrayList(Arrays.asList(Netsis.this.getLogoSqlHelper().getParamValue(ParameterTypes.ptInformationToBeSentToTheMobileDevice).replace(" ", "").split(",")));
                    if (arrayList.size() > 0) {
                        for (TransferGetItem transferGetItem : Netsis.this.getTransferList()) {
                            transferGetItem.setTransferError("");
                            if (transferGetItem.getTransferGetType().id <= 0 || arrayList.contains(String.valueOf(transferGetItem.getTransferGetType().id))) {
                                this.mTransferGet.getTransferGetItems().add(transferGetItem);
                            }
                        }
                    }
                    int size = this.mTransferGet.getTransferGetItems().size();
                    int r7 = 1;
                    for (TransferGetItem transferGetItem2 : this.mTransferGet.getTransferGetItems()) {
                        String str = "";
                        for (NetsisSelectResult netsisSelectResult : Netsis.this.mQueryCreator.getTransferList(transferGetItem2, this.forService)) {
                            publishProgress(String.format("%d/%d %s\n%s", Integer.valueOf(r7), Integer.valueOf(size), ContextUtils.getStringResource(transferGetItem2.getTransferGetType().resId), ContextUtils.getStringResource(netsisSelectResult.getProcessType().resId)));
                            NetsisSelectResult netsisSelectResultRunSelect2 = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                            if (!netsisSelectResultRunSelect2.isSuccess()) {
                                str = str + netsisSelectResultRunSelect2.getErrorString() + " \n ";
                            }
                        }
                        if (!TextUtils.isEmpty(str)) {
                            transferGetItem2.setTransferError(str);
                            transferEvent.setSuccess(false);
                        }
                        Netsis.this.insertLastTransferDate();
                        r7++;
                    }
                } else {
                    transferEvent.setErrorMessage(netsisSelectResultRunSelect.getErrorString());
                }
                transferEvent.setTransferGet(this.mTransferGet);
                MainActivity.getDataFlag = true;
                return transferEvent;
            } catch (Throwable th2) {
                Throwable th = th2;
                z = true;
            }
            return transferEvent;
        }
        public void onCancelled(TransferEvent transferEvent) {
            if (!this.forService) {
                this.mProgressDialogBuilder.dismiss();
            }
            if (transferEvent != null) {
                transferEvent.setSuccess(false);
                transferEvent.setErrorMessage(ContextUtils.getStringResource(R.string.str_cancel_process));
                Netsis.this.postEvent(transferEvent);
            }
            super.onCancelled(  transferEvent);
        }
        protected void onCancelled() {
            if (!this.forService) {
                this.mProgressDialogBuilder.dismiss();
            }
            Netsis.this.postEvent(new ResponseEvent(ContextUtils.getStringResource(R.string.str_cancel_process), false));
            super.onCancelled();
        }
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate(  strArr);
            if (this.forService) {
                return;
            }
            this.mProgressDialogBuilder.setMessage(strArr[0]);
        }
        public void onPostExecute(TransferEvent transferEvent) {
            super.onPostExecute( transferEvent);
            if (!this.forService) {
                this.mProgressDialogBuilder.dismiss();
            }
            Netsis.this.postEvent(transferEvent);
        }
    }
    class GetKeyValues extends AsyncTask<Void, String, ResponseEvent> {
        private final ProgressDialogBuilder mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
        private final String orderBy;
        private final String sql;

        GetKeyValues(String str, String str2) {
            this.sql = str;
            this.orderBy = str2;
        }
        protected void onPreExecute() {
            this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.type_login)).setCancelable(true).setOnCancelClickListener().show();
            super.onPreExecute();
        }
        public   void lambdaonPreExecute0(DialogInterface dialogInterface) {
            super.cancel(true);
        }
        public ResponseEvent doInBackground(Void... voidArr) {
            ResponseEvent responseEvent = new ResponseEvent();
            try {
                NetsisSelectResult netsisSelectResultRunSelect = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                if (!netsisSelectResultRunSelect.isSuccess()) {
                    responseEvent.setErrorMessage(netsisSelectResultRunSelect.getErrorString());
                }
            } catch (Exception unused) {
            }
            return responseEvent;
        }
        public void onCancelled(ResponseEvent responseEvent) {
            this.mProgressDialogBuilder.dismiss();
            if (responseEvent != null) {
                responseEvent.setSuccess(false);
                responseEvent.setErrorMessage(ContextUtils.getStringResource(R.string.str_cancel_process));
                Netsis.this.postEvent(responseEvent);
            }
            super.onCancelled( responseEvent);
        }
        protected void onCancelled() {
            this.mProgressDialogBuilder.dismiss();
            Netsis.this.postEvent(new ResponseEvent(ContextUtils.getStringResource(R.string.str_cancel_process), false));
            super.onCancelled();
        }
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate(  strArr);
            this.mProgressDialogBuilder.setMessage(strArr[0]);
        }
        public void onPostExecute(ResponseEvent responseEvent) {
            super.onPostExecute(responseEvent);
            this.mProgressDialogBuilder.dismiss();
            Netsis.this.postEvent(responseEvent);
        }
    }
    public void getCurrRate() {
        new GetData(getQueryCreator().getCurrRates()).execute();
    }
    public void getCurrRate(ResponseListener responseListener) {
        getRestAsyncTask().runSelectRx(getQueryCreator().getCurrRates(), responseListener);
    }
    private void updateData(NetsisSelectResult netsisSelectResult, int r4) {
        try {
            getRestAsyncTask().lambdagetSelectRxn24();
            if (netsisSelectResult.isSuccess()) {
                update(netsisSelectResult.getDataList(), "LOGICALREF", r4);
            }
        } catch (Exception e2) {
            Log.e(TAG, "doInBackground: ", e2);
            netsisSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
        }
    }
    private class GetData extends AsyncTask<Void, Void, Void> {
        private final NetsisSelectResult baseResult;

        GetData(NetsisSelectResult netsisSelectResult) {
            this.baseResult = netsisSelectResult;
        }
        public Void doInBackground(Void... voidArr) {
            try {
                Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                if (!this.baseResult.isSuccess() || !this.baseResult.isDatabaseSave()) {
                    return null;
                }
                Netsis.this.insertData(this.baseResult.getDataList(), this.baseResult.isTableDelete());
                return null;
            } catch (Exception e2) {
                Log.e(Netsis.TAG, "doInBackground: ", e2);
                this.baseResult.setSuccess(ContextUtils.getStringResource(R.string.exp_10_transfer_get_error));
                return null;
            }
        }
        public void onPostExecute(Void r1) {
            super.onPostExecute( r1);
            Netsis.this.postEvent(this.baseResult);
        }
    }
    public boolean checkUserSettings() {
        return !TextUtils.isEmpty(getUserSettings().getServerAddress());
    }
    public Preferences.NetsisUserSettings getUserSettings() {
        return (Preferences.NetsisUserSettings) this.mUserSettings;
    }
    public NetsisRestAsyncTask getRestAsyncTask() {
        return (NetsisRestAsyncTask) getBaseCommunication();
    }
    public void getOnlinePrice(OnlineItemPriceParameters onlineItemPriceParameters, ResponseListener responseListener) {
        responseListener.onResponse();
    }
    public void runUserDefinedSQL(UserReportsActivity.UserDefinedResponseListener responseListener, String str, String str2) {
        getRestAsyncTask().runGenericRx(this.mQueryCreator.getUserDesignedSQL(str, str2), responseListener);
    }
    public void getOnlineItems(ResponseListener responseListener) {
        getRestAsyncTask().runSelectRx(this.mQueryCreator.getItems(false), responseListener);
    }

    
    public void getOnlineStockAll(ResponseListener responseListener) {
        getRestAsyncTask().runSelectRx(this.mQueryCreator.getItemStock(false), responseListener);
    }

    
    public void getOnlinePriceAll(ResponseListener responseListener) {
        getRestAsyncTask().runSelectRx(this.mQueryCreator.getItemPrices(false), responseListener);
    }

    
    public void getOnlineUpdateCustomer(ResponseListener responseListener, int r4) {
        getRestAsyncTask().runSelectRx(this.mQueryCreator.getCustomers(r4, false), responseListener);
    }

    
    public void saveCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener responseListener) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("CLCODE", custGpsInfo.clCode);
            contentValues.put("CLNAME", custGpsInfo.clName);
            contentValues.put("LATITUDE", Double.valueOf(custGpsInfo.latitude));
            contentValues.put("LONGTITUDE", Double.valueOf(custGpsInfo.longtitude));
            getLogoSqlBriteDatabase().insert("CUSTGPSINFO", contentValues);
            sendCustomerLocation(custGpsInfo, responseListener);
        } catch (Exception e2) {
            Log.e(TAG, "saveCustomerLocation : " + e2.getMessage());
            responseListener.onResponse(Boolean.FALSE);
        }
    }

    
    public void updateCustomerLocation(CustGpsInfo custGpsInfo, ResponseListener responseListener) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("LATITUDE", Double.valueOf(custGpsInfo.latitude));
            contentValues.put("LONGTITUDE", Double.valueOf(custGpsInfo.longtitude));
            getLogoSqlBriteDatabase().update("CUSTGPSINFO", contentValues, "CLCODE=?", String.valueOf(custGpsInfo.clCode));
            sendCustomerLocation(custGpsInfo, responseListener);
        } catch (Exception e2) {
            Log.e(TAG, "updateCustomerLocation : " + e2.getMessage());
            responseListener.onResponse(Boolean.FALSE);
        }
    }

    
    public void sendCustomerLocation(final CustGpsInfo custGpsInfo, final ResponseListener responseListener) {
        getRestAsyncTask().runSelectRx(getQueryCreator().deleteBeforeCustomerGpsLocation(custGpsInfo), new ResponseListener<Boolean>() {
            public void onResponse( PrintSlipModel bool) {
                if (bool.booleanValue()) {
                    Netsis.this.getRestAsyncTask().runSelectRx(Netsis.this.getQueryCreator().insertCustomerGpsLocation(custGpsInfo), responseListener);
                }
            }
            public void onFailure(Throwable throwable) {

            }
            public void onResponse(Boolean aBoolean) {

            }
            public void onResponse(ArrayList<Boolean> obj) {

            }
            public void onResponse() {

            }
            public void onResponse(Sales sales) {

            }
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }
            public void onError(String str) {
                responseListener.onError(str);
            }
        });
    }

    
    public void showOnlineTotal(Sales sales, ResponseListener responseListener) {
        FicheType ficheType = sales.getFicheType();
        if (FicheTypeControlUtils.isFicheTypeOrder(ficheType)) {
            getRestAsyncTask().getSalesOnlineTotal((NetsisServiceResult) getSendCreator().getOrder(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(ficheType)) {
            getRestAsyncTask().getSalesOnlineTotal((NetsisServiceResult) getSendCreator().getInvoice(sales), responseListener);
        } else if (FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(ficheType)) {
            getRestAsyncTask().getSalesOnlineTotal((NetsisServiceResult) getSendCreator().getDispatch(sales), responseListener);
        }
    }

    
    public void showOnlineCampaign(Sales sales, ResponseListener responseListener) {
        if (sales.getFicheType() == FicheType.ORDER) {
            getRestAsyncTask().getSalesCampaign((NetsisServiceResult) getSendCreator().getOrder(sales), responseListener);
            return;
        }
        if (sales.getFicheType() == FicheType.INVOICE) {
            getRestAsyncTask().getSalesCampaign((NetsisServiceResult) getSendCreator().getInvoice(sales), responseListener);
        } else if (sales.getFicheType() == FicheType.DISPATCH || sales.getFicheType() == FicheType.ONE_TO_ONE) {
            getRestAsyncTask().getSalesCampaign((NetsisServiceResult) getSendCreator().getDispatch(sales), responseListener);
        }
    }

    
    public void getSalesProductLinePrice(Sales sales, int r2, WcfPriceType wcfPriceType, ResponseListener responseListener) throws CloneNotSupportedException {
        responseListener.onResponse();
    }

    
    public void getSalesAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener) {
        responseListener.onResponse();
    }
    public void getSalesOrderAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener) {
        responseListener.onResponse();
    }

    
    public void getSalesInvoiceAllProductLinePrice(Sales sales, WcfPriceType wcfPriceType, ResponseListener responseListener) {
        responseListener.onResponse();
    }

    
    public void getSalesFicheSelectedItemPrice(LastItemPriceSqlParams lastItemPriceSqlParams, ResponseListener responseListener) {
        lastItemPriceSqlParams.setCustomerCode(lastItemPriceSqlParams.getCustomerRef() == -1 ? "" : getCustomerClCode(lastItemPriceSqlParams.getCustomerRef()));
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getLastItemPriceSalesPurchase(lastItemPriceSqlParams, getLocalCurrencyCode()), responseListener);
    }

    
    public void docNoUniqueControl(FicheType ficheType, String str, ResponseListener responseListener) {
        responseListener.onResponse();
    }

    
    public void getMaxMatterNo(FicheType ficheType, MatterSettings matterSettings, ResponseListener responseListener) {
        NetsisSelectResult maxMatterNo = this.mQueryCreator.getMaxMatterNo(ficheType, matterSettings);
        if (maxMatterNo == null || !maxMatterNo.isSuccess()) {
            responseListener.onResponse(getRestAsyncTask().getNewMaxMatterNo(ficheType, matterSettings, StringUtils.empty()));
        } else {
            getRestAsyncTask().getMaxMatterNo(ficheType, matterSettings, maxMatterNo, responseListener);
        }
    }

    
    public void getPaySumCustomerForDueDate(int r1, int r2, ResponseListener responseListener) {
        responseListener.onResponse();
    }

    
    public void getOnlinePrintFileNameList(FicheType ficheType, final ResponseListener responseListener) {
        getRestAsyncTask().getApi().getQueriesRx(new NetsisTSql(this.mQueryCreator.getDesignFileNameList(ficheType).getSql())).filter(new Predicate() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda17
            @Override // io.reactivex.functions.Predicate
            public boolean test(Object obj) {
                return ((NetsisDataHeader) obj).isSuccessful();
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                return this.f0.lambdagetOnlinePrintFileNameList0((NetsisDataHeader) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                Netsis.lambdagetOnlinePrintFileNameList2(responseListener, (Throwable) obj);
            }

            public Object invoke(Object obj) {

                return obj;
            }
        });
    }

    
    public List lambdagetOnlinePrintFileNameList0(NetsisDataHeader netsisDataHeader) throws Exception {
        return getRestAsyncTask().toListData(DesignJson.class, netsisDataHeader.getData());
    }

    
    public static void lambdagetOnlinePrintFileNameList2(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }

    
    public void getOnlineSalesOrderList(SalesType salesType, int r3, boolean z, ArrayList arrayList, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getSalesOrderList(salesType, r3, z, arrayList), responseListener);
    }

    
    public void getOnlineSalesMans(ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getSalesMansList(), responseListener);
    }

    
    public Disposable getInvoicePrintValues(int r8, final ResponseListener responseListener, int r10) {
        List table = getLogoSqlHelper().getTable(Invoice.class, "LOGICALREF=?", new String[]{Integer.toString(r8)});
        if (table.size() == 0) {
            return null;
        }
        String str = ((Invoice) table.get(0)).clCode;
        String str2 = ((Invoice) table.get(0)).shipAccCode;
        String str3 = ((Invoice) table.get(0)).ficheNo;
        if (((Invoice) table.get(0)).isTransfer == 1 && ContextUtils.checkConnectionWithoutMessage()) {
            getItemSlipPrintValues(buildPrintSlipParams(str, str2, str3, getInvoiceSlipType(((Invoice) table.get(0)).getFtype(), ((Invoice) table.get(0)).getInvType())), responseListener);
        } else {
            getSendCreator().getSqlManager().getSalesInvoiceExam(r8, new ResponseListener<Sales>() {
                public void onResponse(PrintSlipModel sales) {
                    BaseServiceResult invoice = Netsis.this.getSendCreator().getInvoice(sales);
                    if (invoice != null && invoice.getSendData() != null) {
                        Netsis.this.getItemSlipPrintValuesOffline((ItemSlip) invoice.getSendData(), sales, responseListener);
                    } else {
                        responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    }
                }
                public void onFailure(Throwable throwable) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                }
                public void onResponse(Boolean aBoolean) {}
                public void onResponse(Sales sales) {}
                public void onResponse(ArrayList<Sales> obj) { }
                public void onResponse() { }
                public void onResponse(TigerServiceResult tigerServiceResult) { }
                public void onError(String str4) {
                    responseListener.onError(str4);
                }
            });
        }
        return null;
    }
    private NetsisSlipType getInvoiceSlipType(int r1, int r2) {
        if ((r1 == 2 && r2 == 0) || (r1 == 1 && r2 == 1)) {
            return NetsisSlipType.ftAFat;
        }
        return NetsisSlipType.ftSFat;
    }
    public void getItemSlipPrintValuesOffline(ItemSlip itemSlip,   PrintSlipModel sales, ResponseListener responseListener) {
        itemSlip.getSlipHeader().setGrossTotal(sales.getGrossTotal());
        itemSlip.getSlipHeader().setGeneralTotal(sales.getTotalNet());
        itemSlip.getSlipHeader().setVat(sales.getTotalVat());
        for (ItemSlipLine itemSlipLine : itemSlip.getLines()) {
            itemSlipLine.setSatisKDVOran(itemSlipLine.getSTraKDV());
            itemSlipLine.setSTraFATIRSNO(itemSlip.getSlipHeader().getSlipNo());
            SalesDetail salesDetailFindDetailByLineNr = sales.findDetailByLineNr(itemSlipLine.getSira());
            if (salesDetailFindDetailByLineNr != null) {
                itemSlipLine.setSTraNF(salesDetailFindDetailByLineNr.getProductLineNet() / itemSlipLine.getSTraGCMIK());
                itemSlipLine.setSTraBF(salesDetailFindDetailByLineNr.getProductTotal() / itemSlipLine.getSTraGCMIK());
                itemSlipLine.setWeight(Double.valueOf(salesDetailFindDetailByLineNr.getNetWeight()));
                itemSlipLine.setVolume(Double.valueOf(salesDetailFindDetailByLineNr.getNetVolume()));
                itemSlipLine.setWidth(Double.valueOf(salesDetailFindDetailByLineNr.getWidth()));
                itemSlipLine.setLength(Double.valueOf(salesDetailFindDetailByLineNr.getLength()));
                itemSlipLine.setHeight(Double.valueOf(salesDetailFindDetailByLineNr.getHeight()));
            }
            for (Object itemUnits : getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=?", new String[]{itemSlipLine.getStokKodu()})) {
                int r4 = itemUnits.linenr;
                if (r4 == 1) {
                    itemSlipLine.setOBR1(itemUnits.code);
                } else if (r4 == 2) {
                    itemSlipLine.setOBR2(itemUnits.code);
                } else if (r4 == 3) {
                    itemSlipLine.setOBR3(itemUnits.code);
                }
            }
        }
        getSendCreator().getSqlManager().getCustomer(getCustomerClRef(itemSlip.getSlipHeader().getCustomerCode()), new ResponseListener<CustomerNew>() {
            public void onResponse(CustomerNew customerNew) {
                final ArrayList arrayList = new ArrayList();
                PrintExtraInfo printExtraInfo = new PrintExtraInfo();
                printExtraInfo.setPlasiyerName(Netsis.this.getUser().getName());
                arrayList.add(printExtraInfo);
                if (SalesUtils.isSalesTypeWhTransfer(sales.getmSalesType())) {
                    responseListener.onResponse(new PrintSlipModel(itemSlip, null, null, arrayList));
                    return;
                }
                CariTemelBilgi cariTemelBilgi = new CariTemelBilgi(customerNew);
                cariTemelBilgi.setCardType(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
                CariEkBilgi cariEkBilgi = new CariEkBilgi();
                cariEkBilgi.setCode(customerNew.getCode().toString());
                cariEkBilgi.setOdekod(customerNew.getPayPlan().toString());
                cariEkBilgi.setTckimlikno(customerNew.getTCNo().toString());
                final Cari cari = new Cari();
                cari.setCariTemelBilgi(cariTemelBilgi);
                cari.setCariEkBilgi(cariEkBilgi);
                ClCard customerInfo = Netsis.this.getCustomerInfo(customerNew.getLogicalRef());
                if (customerInfo != null) {
                    cariTemelBilgi.setDebit(customerInfo.getDebit());
                    cariTemelBilgi.setCredit(customerInfo.getCredit());
                    cariTemelBilgi.setSpecode(customerInfo.getSpecode());
                }
                if (TextUtils.isEmpty(itemSlip.getSlipHeader().getCustomerCode2())) {
                    responseListener.onResponse(new PrintSlipModel(itemSlip, cari, null, arrayList));
                } else {
                    Netsis.this.getSendCreator().getSqlManager().getCustomer(Netsis.this.getCustomerClRef(itemSlip.getSlipHeader().getCustomerCode2()), new ResponseListener<CustomerNew>() {
                        public void onResponse( PrintSlipModel customerNew2) {
                            CariTemelBilgi cariTemelBilgi2 = new CariTemelBilgi(customerNew2);
                            cariTemelBilgi2.setCardType(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
                            CariEkBilgi cariEkBilgi2 = new CariEkBilgi();
                            cariEkBilgi2.setCode(customerNew2.getCode().toString());
                            cariEkBilgi2.setOdekod(customerNew2.getPayPlan().toString());
                            cariEkBilgi2.setTckimlikno(customerNew2.getTCNo().toString());
                            Cari cari2 = new Cari();
                            cari2.setCariTemelBilgi(cariTemelBilgi2);
                            cari2.setCariEkBilgi(cariEkBilgi2);
                            responseListener.onResponse(new PrintSlipModel(itemSlip, cari, cari2, arrayList));
                        }

                        @Override
                        public void onError(String str) {
                            responseListener.onError(str);
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
                        public void onResponse(CustomerNew obj) {

                        }

                        @Override
                        public void onResponse(ArrayList<CustomerNew> obj) {

                        }

                        @Override
                        public void onResponse() {

                        }
                    });
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(str);
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
            public void onResponse(ArrayList<CustomerNew> obj) {

            }

            @Override
            public void onResponse() {

            }
        });
    }

    class C26404 implements ResponseListener<PrintSlipModel> {
        final PrintSlipParams valprintSlipParams;
        final ResponseListener valresponseListener;

        C26404(ResponseListener responseListener, PrintSlipParams printSlipParams) {
            this.valresponseListener = responseListener;
            this.valprintSlipParams = printSlipParams;
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(@Nullable final @UnknownNullability PrintSlipModel printSlipModel) {
            if (printSlipModel.getItemSlip() == null || printSlipModel.getItemSlip().getSlipHeader() == null) {
                this.valresponseListener.onResponse(printSlipModel);
                return;
            }
            boolean zAnyMatch = printSlipModel.getItemSlip().getLines().stream().anyMatch(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.Netsis4ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    return Netsis.C26404.lambdaonResponse0((ItemSlipLine) obj);
                }
            });
            printSlipModel.getItemSlip().setRiskControl(true);
            printSlipModel.getItemSlip().setLastNumberWrite(true);
            printSlipModel.getItemSlip().setOtoDiscountGet(true);
            printSlipModel.getItemSlip().setConnectControl(true);
            printSlipModel.getItemSlip().setTransactionSupport(true);
            printSlipModel.getItemSlip().setDiscountCalculate(true);
            printSlipModel.getItemSlip().setReceiptLineCount(0);
            printSlipModel.getItemSlip().setSendEposta(false);
            printSlipModel.getItemSlip().setSerialCalculate(zAnyMatch);
            printSlipModel.getItemSlip().setRecordNumberUpdate(true);
            printSlipModel.changeItemSlipFicheNo(printSlipModel.getItemSlip().generateFicheNumberForCalculate());
            printSlipModel.getItemSlip().getSlipHeader().seteInvoiceCustomer(false);
            printSlipModel.getItemSlip().getSlipHeader().seteDespatch(false);
            printSlipModel.getItemSlip().getSlipHeader().setGibInvoiceNo("");
            Observable<DataResponse<ItemSlip>> observableCalculateFiche = Netsis.this.getRestAsyncTask().getApi().calculateFiche(printSlipModel.getItemSlip());
            final PrintSlipParams printSlipParams = this.valprintSlipParams;
            Observable<DataResponse<ItemSlip>> observableSubscribeOn = observableCalculateFiche.doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                   lambdaonResponse1(printSlipModel, printSlipParams, (DataResponse) obj);
                }
                public Object invoke(Object obj) {
                    return null;
                }
            }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
            final ResponseListener responseListener = this.valresponseListener;
            Consumer<? super DataResponse<ItemSlip>> consumer = new Consumer() {
                @Override
                public void accept(Object obj) {
                    responseListener.onResponse(printSlipModel);
                }
                @Override
                public Object invoke(Object obj) {

                    return obj;
                }
            };
            final ResponseListener responseListener2 = this.valresponseListener;
            observableSubscribeOn.subscribe(consumer, new Consumer() {
                public void accept(Object obj) throws Exception {
                     lambdaonResponse3(responseListener2, (Throwable) obj);
                }

                @Override
                public Object invoke(Object obj) {

                    return obj;
                }
            });
        }

        @Override
        public void onResponse(ArrayList<PrintSlipModel> obj) {

        }

        @Override
        public void onResponse() {

        }

        public boolean lambdaonResponse0(ItemSlipLine itemSlipLine) {
            return !TextUtils.isEmpty(itemSlipLine.getSeriTakibi()) && itemSlipLine.getSeriTakibi().equals(ExifInterface.LONGITUDE_EAST);
        }

        
        public void lambdaonResponse1(PrintSlipModel printSlipModel, PrintSlipParams printSlipParams, DataResponse dataResponse) throws Exception {
            if (!dataResponse.isSuccessful()) {
                printSlipModel.changeItemSlipFicheNo(printSlipParams.getSlipNo());
                printSlipModel.setErrorDesc(dataResponse.getErrorDesc());
                Log.d(Netsis.TAG, dataResponse.getErrorDesc());
            } else {
                printSlipModel.setItemSlip((ItemSlip) dataResponse.getData());
                printSlipModel.changeItemSlipFicheNo(printSlipParams.getSlipNo());
            }
        }

        
        public void lambdaonResponse3(ResponseListener responseListener, Throwable th) throws Exception {
            responseListener.onError(handleNetworkError(th));
        }
        public void onError(String str) {
            this.valresponseListener.onError(str);
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
    }

    private void getItemSlipPrintValues(PrintSlipParams printSlipParams, ResponseListener responseListener) {
        getRestAsyncTask().getPrintItemSlipInfo(printSlipParams, new C26404(responseListener, printSlipParams));
    }

    
    public Object getOrderPrintValues(int r6, final ResponseListener responseListener, int r8) {
        List table = getLogoSqlHelper().getTable(Order.class, "LOGICALREF=?", new String[]{Integer.toString(r6)});
        if (table.size() == 0) {
            return null;
        }
        String str = ((Order) table.get(0)).clCode;
        String str2 = ((Order) table.get(0)).shipAccCode;
        String str3 = ((Order) table.get(0)).ficheNo;
        if (((Order) table.get(0)).isTransfer == 1 && ContextUtils.checkConnectionWithoutMessage()) {
            getItemSlipPrintValues(buildPrintSlipParams(str, str2, str3, NetsisSlipType.ftSSip), responseListener);
        } else {
            getSendCreator().getSqlManager().getSalesOrderOne(r6, new ResponseListener<Sales>() { // from class: com.proje.mobilesales.core.design.Netsis.5
                @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                public void onResponse(@Nullable @UnknownNullability PrintSlipModel sales) {
                    BaseServiceResult order = Netsis.this.getSendCreator().getOrder(sales);
                    if (order != null && order.getSendData() != null) {
                        Netsis.this.getItemSlipPrintValuesOffline((ItemSlip) order.getSendData(), sales, responseListener);
                    } else {
                        responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    }
                }

                @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                public void onError(String str4) {
                    responseListener.onError(str4);
                }
            });
        }
        return null;
    }
    public void getDispatchPrintValues(int r8, final ResponseListener responseListener, int r10) {
        List table = getLogoSqlHelper().getTable(Invoice.class, "LOGICALREF=?", new String[]{Integer.toString(r8)});
        if (table.size() == 0) {
            return null;
        }
        String str = ((Invoice) table.get(0)).clCode;
        String str2 = ((Invoice) table.get(0)).shipAccCode;
        String str3 = ((Invoice) table.get(0)).ficheNo;
        if (((Invoice) table.get(0)).isTransfer == 1 && ContextUtils.checkConnectionWithoutMessage()) {
            getItemSlipPrintValues(buildPrintSlipParams(str, str2, str3, getDispatchSlipType(((Invoice) table.get(0)).getFtype(), ((Invoice) table.get(0)).getInvType())), responseListener);
        } else {
            getSendCreator().getSqlManager().getSalesInvoiceExam(r8, new ResponseListener<Sales>() { // from class: com.proje.mobilesales.core.design.Netsis.6
                @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                public void onResponse(@Nullable @UnknownNullability PrintSlipModel sales) {
                    BaseServiceResult dispatch = Netsis.this.getSendCreator().getDispatch(sales);
                    if (dispatch != null && dispatch.getSendData() != null) {
                        Netsis.this.getItemSlipPrintValuesOffline((ItemSlip) dispatch.getSendData(), sales, responseListener);
                    } else {
                        responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    }
                }

                @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                public void onError(String str4) {
                    responseListener.onError(str4);
                }
            });
        }
        return null;
    }

    private NetsisSlipType getDispatchSlipType(int r1, int r2) {
        if (r1 == 0 && r2 == 1) {
            return NetsisSlipType.ftAIrs;
        }
        return NetsisSlipType.ftSIrs;
    }

    private PrintSlipParams buildPrintSlipParams(String str, String str2, String str3, NetsisSlipType netsisSlipType) {
        PrintSlipParams printSlipParams = new PrintSlipParams();
        if (getSendFicheToMainCustomer()) {
            String mainClCode = getMainClCode(str);
            if (!TextUtils.isEmpty(mainClCode)) {
                str2 = str;
                str = mainClCode;
            }
        }
        printSlipParams.setClCode(str);
        printSlipParams.setShipmentClCode(str2);
        printSlipParams.setSlipNo(str3);
        printSlipParams.setSlipType(netsisSlipType.ordinal());
        printSlipParams.setKey(netsisSlipType.ordinal() + ";" + str3 + ";" + str);
        return printSlipParams;
    }

    
    public Disposable getCashPrintValues(int r2, ResponseListener responseListener) {
        return getCashCreditPrintValues(r2, 0, responseListener);
    }

    
    public Disposable getCreditCardPrintValues(int r2, ResponseListener responseListener) {
        return getCashCreditPrintValues(r2, 1, responseListener);
    }

    private Disposable getCashCreditPrintValues(final int r3, int r4, final ResponseListener responseListener) {
        getSendCreator().getSqlManager().getCashCreditFiche(r3, r4, new ResponseListener<CashCreditFiche>() { // from class: com.proje.mobilesales.core.design.Netsis.7
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel cashCreditFiche) {
                if (cashCreditFiche == null) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    return;
                }
                String clCode = cashCreditFiche.getClCode();
                String ficheNo = cashCreditFiche.getFicheNo();
                String str = cashCreditFiche.getgDate();
                int r42 = cashCreditFiche.getfType();
                if (cashCreditFiche.isTransfer() == 1 && ContextUtils.checkConnectionWithoutMessage()) {
                    MixedReceiptsMainParam mixedReceiptsMainParam = new MixedReceiptsMainParam();
                    mixedReceiptsMainParam.setCaseCode(Netsis.this.getLogoSqlHelper().getMainCaseCashCode());
                    mixedReceiptsMainParam.setCustomerCode(clCode);
                    mixedReceiptsMainParam.setDocumentNumber(ficheNo);
                    mixedReceiptsMainParam.setDate(DateAndTimeUtils.convertDateSqlDateWithoutTime(str));
                    Netsis.this.getMixedReceiptPrintValues(mixedReceiptsMainParam, cashCreditFiche, responseListener);
                    return;
                }
                List<CashCreditX> creditAndCash = ((NetsisSqlManager) Netsis.this.getSendCreator().getSqlManager()).getCreditAndCash(r42, r3, cashCreditFiche.isTransfer());
                if (creditAndCash.isEmpty()) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    return;
                }
                creditAndCash.get(0).getCashCredit().bankDescription = cashCreditFiche.getBank().getDefinition();
                BaseServiceResult creditCard = Netsis.this.getSendCreator().getCreditCard(creditAndCash.get(0));
                if (creditCard != null && creditCard.getSendData() != null) {
                    Netsis.this.getMixedReceiptPrintValuesOffline((MixedReceiptsMain) creditCard.getSendData(), cashCreditFiche, responseListener);
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
            }
        });
        return null;
    }

    private Disposable getChequeAndDeedPrintValues(final int r3, int r4, final ResponseListener responseListener) {
        getSendCreator().getSqlManager().getChequeDeedFiche(r3, r4, new ResponseListener<ChequeDeedFiche>() { // from class: com.proje.mobilesales.core.design.Netsis.8
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel chequeDeedFiche) {
                BaseServiceResult cheque;
                if (chequeDeedFiche == null) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    return;
                }
                String clCode = chequeDeedFiche.getClCode();
                String ficheNo = chequeDeedFiche.getFicheNo();
                int r32 = chequeDeedFiche.getfType();
                if (chequeDeedFiche.isTransfer() == 1 && ContextUtils.checkConnectionWithoutMessage()) {
                    CheckAndPNotesMainParam checkAndPNotesMainParam = new CheckAndPNotesMainParam();
                    checkAndPNotesMainParam.setCustomerCode(clCode);
                    checkAndPNotesMainParam.setBordroNo(ficheNo);
                    checkAndPNotesMainParam.setNetsisChequeAndDeedType(r32 == 1 ? NetsisChequeAndDeedType.csMSEN : NetsisChequeAndDeedType.csMCEK);
                    Netsis.this.getCheckAndPNotesPrintValues(checkAndPNotesMainParam, chequeDeedFiche, responseListener);
                    return;
                }
                List<ChequeDeed> chequesAndDeed = ((NetsisSqlManager) Netsis.this.getSendCreator().getSqlManager()).getChequesAndDeed(r32, r3, chequeDeedFiche.isTransfer());
                if (chequesAndDeed.isEmpty()) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    return;
                }
                if (r32 == 1) {
                    cheque = Netsis.this.getSendCreator().getDeed(chequesAndDeed.get(0));
                } else {
                    cheque = Netsis.this.getSendCreator().getCheque(chequesAndDeed.get(0));
                }
                if (cheque != null && cheque.getSendData() != null) {
                    Netsis.this.getCheckAndPNotesPrintValuesOffline((NetsisChequeAndDeedMain) cheque.getSendData(), chequeDeedFiche, responseListener);
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
            }
        });
        return null;
    }

    
    public Disposable getChequePrintValues(int r2, ResponseListener responseListener) {
        return getChequeAndDeedPrintValues(r2, 0, responseListener);
    }

    
    public Disposable getDeedPrintValues(int r2, ResponseListener responseListener) {
        return getChequeAndDeedPrintValues(r2, 1, responseListener);
    }

    
    public Object getCasePrintValues(final int r3, final ResponseListener responseListener) {
        getSendCreator().getSqlManager().getCaseFiche(r3, new ResponseListener<CaseFiche>() {
            public void onResponse(CaseFiche caseFiche) {
                if (caseFiche == null) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    return;
                }
                String clCode = caseFiche.getClCode();
                String ficheNo = caseFiche.getFicheNo();
                if (caseFiche.isTransfer() == 1 && ContextUtils.checkConnectionWithoutMessage()) {
                    SafeDepositParam safeDepositParam = new SafeDepositParam();
                    safeDepositParam.setCustomerCode(clCode);
                    safeDepositParam.setFicheNo(ficheNo);
                    safeDepositParam.setBranchCode(Netsis.this.getUser().getPeridodNrInt());
                    Netsis.this.getSafeDepositPrintValues(safeDepositParam, caseFiche, responseListener);
                    return;
                }
                List<CaseCash> caseCashs = ((NetsisSqlManager) Netsis.this.getSendCreator().getSqlManager()).getCaseCashs(r3, caseFiche.isTransfer());
                if (caseCashs.isEmpty()) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    return;
                }
                BaseServiceResult caseCash = Netsis.this.getSendCreator().getCaseCash(caseCashs.get(0));
                if (caseCash != null && caseCash.getSendData() != null) {
                    Netsis.this.getSafeDepositPrintValuesOffline((SafeDeposit) caseCash.getSendData(), caseFiche, responseListener);
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                }
            }
            public void onError(String str) {
                responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
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
            public void onResponse(ArrayList<CaseFiche> obj) {

            }

            @Override
            public void onResponse() {

            }
        });
        return null;
    }

    
    public Object getDeliveryNotePrintValues(int r1, int r2, ResponseListener responseListener) {
        responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
        return null;
    }

    
    public Object getWhTransferPrintValues(int r6, final ResponseListener responseListener, int r8) {
        List table = getLogoSqlHelper().getTable(WhTransfer.class, "LOGICALREF=?", new String[]{Integer.toString(r6)});
        if (table.size() == 0) {
            return null;
        }
        String str = ((WhTransfer) table.get(0)).clCode;
        String str2 = ((WhTransfer) table.get(0)).shipAccCode;
        String str3 = ((WhTransfer) table.get(0)).ficheNo;
        if (((WhTransfer) table.get(0)).isTransfer == 1 && ContextUtils.checkConnectionWithoutMessage()) {
            getItemSlipPrintValues(buildPrintSlipParams(str, str2, str3, NetsisSlipType.ftLokalDepo), responseListener);
        } else {
            getSendCreator().getSqlManager().getSalesWhTransfer(r6, new ResponseListener<Sales>() {
                public void onResponse( PrintSlipModel sales) {
                    BaseServiceResult whTransfer = Netsis.this.getSendCreator().getWhTransfer(sales);
                    if (whTransfer != null && whTransfer.getSendData() != null) {
                        Netsis.this.getItemSlipPrintValuesOffline((ItemSlip) whTransfer.getSendData(), sales, responseListener);
                    } else {
                        responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                    }
                }
                public void onError(String str4) {
                    responseListener.onError(str4);
                }
                public void onFailure(Throwable throwable) {
                }
                public void onResponse(Boolean aBoolean) {

                }

                @Override
                public void onResponse(Sales sales) {

                }

                @Override
                public void onResponse(ArrayList<Sales> obj) {

                }

                @Override
                public void onResponse() {

                }

                @Override
                public void onResponse(TigerServiceResult tigerServiceResult) {

                }
            });
        }
        return null;
    }

    /* renamed from: com.proje.mobilesales.core.design.Netsis11 */
    public class C261711 implements ResponseListener<List<Object>> {
        final GroupItem valgroupItem;
        final ResponseListener valresponseListener;

        C261711(ResponseListener responseListener, GroupItem groupItem) {
            this.valresponseListener = responseListener;
            this.valgroupItem = groupItem;
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(@Nullable @UnknownNullability PrintSlipModel list) {
            if (list == null || list.size() == 0) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            List list2 = (List) list.stream().map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.Netsis11ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return Netsis.C261711.lambdaonResponse0(obj);
                }
            }).filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.Netsis11ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    return Netsis.C261711.lambdaonResponse1((DeviceId) obj);
                }
            }).collect(Collectors.toList());
            if ((list2 == null || list2.isEmpty()) && !Netsis.this.getUserSettings().isDemo()) {
                this.valgroupItem.setComplete(true);
                this.valgroupItem.setError(true);
                for (int r2 = 0; r2 < this.valgroupItem.getItemList().size(); r2++) {
                    BaseResult baseResult = (BaseResult) this.valgroupItem.getItemList().get(r2);
                    if (!baseResult.isCompleted()) {
                        baseResult.setErrorString(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                        baseResult.setSuccess(false);
                        baseResult.setCompleted(true);
                    }
                }
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            if (!Netsis.this.checkAppVersionOnWorProcessAgain()) {
                Netsis.this.getRestAsyncTask().transferFiche(this.valgroupItem, this.valresponseListener);
            } else {
                final NetsisSelectResult appVersionSql = Netsis.this.getQueryCreator().getAppVersionSql();
                Netsis.this.getRestAsyncTask().getSelectRx(appVersionSql, new ResponseListener<List<Object>>() { // from class: com.proje.mobilesales.core.design.Netsis.11.1
                    @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                    public void onResponse(@Nullable @UnknownNullability PrintSlipModel list3) {
                        if (appVersionSql.isSuccess() && appVersionSql.getDataList() != null && appVersionSql.getDataList().size() > 0) {
                            FDateUtils.getInstance().updateValues((CheckFDateModel) appVersionSql.getDataList().get(0));
                        }
                        NetsisRestAsyncTask restAsyncTask = Netsis.this.getRestAsyncTask();
                        C261711 c261711 = C261711.this;
                        restAsyncTask.transferFiche(c261711.valgroupItem, c261711.valresponseListener);
                    }

                    @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                    public void onError(String str) {
                        NetsisRestAsyncTask restAsyncTask = Netsis.this.getRestAsyncTask();
                        C261711 c261711 = C261711.this;
                        restAsyncTask.transferFiche(c261711.valgroupItem, c261711.valresponseListener);
                    }
                });
            }
        }

        
        public static DeviceId lambdaonResponse0(Object obj) {
            return (DeviceId) obj;
        }

        
        public static boolean lambdaonResponse1(DeviceId deviceId) {
            return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String str) {
            this.valresponseListener.onError(str);
        }
    }

    
    public void sendFiche(GroupItem groupItem, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(getQueryCreator().getDeviceId(), new C261711(responseListener, groupItem));
    }

    /* renamed from: com.proje.mobilesales.core.design.Netsis12 */
    class C261812 implements ResponseListener<List<Object>> {
        final List valgroupItem;
        final ResponseListener valresponseListener;

        C261812(ResponseListener responseListener, List list) {
            this.valresponseListener = responseListener;
            this.valgroupItem = list;
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(@Nullable @UnknownNullability PrintSlipModel list) {
            if (list == null || list.size() == 0) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            List list2 = (List) list.stream().map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.Netsis12ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return Netsis.C261812.lambdaonResponse0(obj);
                }
            }).filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.Netsis12ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    return Netsis.C261812.lambdaonResponse1((DeviceId) obj);
                }
            }).collect(Collectors.toList());
            if ((list2 == null || list2.isEmpty()) && !Netsis.this.getUserSettings().isDemo()) {
                for (GroupItem groupItem : this.valgroupItem) {
                    groupItem.setComplete(true);
                    groupItem.setError(true);
                    for (int r4 = 0; r4 < groupItem.getItemList().size(); r4++) {
                        BaseResult baseResult = (BaseResult) groupItem.getItemList().get(r4);
                        if (!baseResult.isCompleted()) {
                            baseResult.setErrorString(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                            baseResult.setSuccess(false);
                            baseResult.setCompleted(true);
                        }
                    }
                }
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            if (!Netsis.this.checkAppVersionOnWorProcessAgain()) {
                Netsis.this.getRestAsyncTask().transferFiches(this.valgroupItem, this.valresponseListener);
            } else {
                final NetsisSelectResult appVersionSql = Netsis.this.getQueryCreator().getAppVersionSql();
                Netsis.this.getRestAsyncTask().getSelectRx(appVersionSql, new ResponseListener<List<Object>>() { // from class: com.proje.mobilesales.core.design.Netsis.12.1
                    @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                    public void onResponse(@Nullable @UnknownNullability PrintSlipModel list3) {
                        if (appVersionSql.isSuccess() && appVersionSql.getDataList() != null && appVersionSql.getDataList().size() > 0) {
                            FDateUtils.getInstance().updateValues((CheckFDateModel) appVersionSql.getDataList().get(0));
                        }
                        NetsisRestAsyncTask restAsyncTask = Netsis.this.getRestAsyncTask();
                        C261812 c261812 = C261812.this;
                        restAsyncTask.transferFiches(c261812.valgroupItem, c261812.valresponseListener);
                    }

                    @Override // com.proje.mobilesales.core.interfaces.ResponseListener
                    public void onError(String str) {
                        NetsisRestAsyncTask restAsyncTask = Netsis.this.getRestAsyncTask();
                        C261812 c261812 = C261812.this;
                        restAsyncTask.transferFiches(c261812.valgroupItem, c261812.valresponseListener);
                    }
                });
            }
        }

        
        public static DeviceId lambdaonResponse0(Object obj) {
            return (DeviceId) obj;
        }

        
        public static boolean lambdaonResponse1(DeviceId deviceId) {
            return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String str) {
            this.valresponseListener.onError(str);
        }
    }

    
    public void sendFiche(List list, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(getQueryCreator().getDeviceId(), new C261812(responseListener, list));
    }

    
    public void getCustomerRiskInfo(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getCustomerRisk(r2), responseListener);
    }

    
    public void getItemImage(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getItemImage(r2), responseListener);
    }

    
    public void isOrderStatusStillEditable(final FicheQueryParams ficheQueryParams, final ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getOrderStatus(ficheQueryParams.getFicheId(), getCustomerClCode(ficheQueryParams.getCustomerId())), new ResponseListener<List<Object>>() {
            public void onResponse(PrintSlipModel list) {
                if (list.isEmpty()) {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_fiche_delete_erp) + System.getProperty("line.separator") + ficheQueryParams.getFicheId());
                    return;
                }
                OrderFicheStatus orderFicheStatus = (OrderFicheStatus) list.get(0);
                if (orderFicheStatus != null && orderFicheStatus.getStatus() == NetsisInvoiceType.ft_Muhtelif.getStatusValue()) {
                    responseListener.onResponse(Boolean.TRUE);
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_order_status_offer_error));
                }
            }
            public void onError(String str) {
                responseListener.onError(str);
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
        });
    }

    
    public void addNewCustomer(CustomerNew customerNew, boolean z, ResponseListener responseListener) {
        getRestAsyncTask().sendNewCustomer(customerNew, responseListener);
    }

    
    public void addOfflineCustomer(ICustomerSendCompleted iCustomerSendCompleted) {
        ArrayList arrayList = new ArrayList();
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "ISTRANSFER=?", new String[]{String.valueOf(0)});
        if (table != null) {
            for (int r2 = 0; r2 < table.size(); r2++) {
                arrayList.add(((ClCard) table.get(r2)).convert());
            }
        }
        getRestAsyncTask().sendAllOfflineCustomers(arrayList, iCustomerSendCompleted);
    }

    
    public void addOfflineCustomer(int r5, final ICustomerSendCompleted iCustomerSendCompleted) {
        CustomerNew customerNew = new CustomerNew();
        List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(r5)});
        if (table != null && table.size() > 0) {
            customerNew = ((ClCard) table.get(0)).convert();
        }
        getRestAsyncTask().sendNewCustomer(customerNew, new ResponseListener<CustomerNew>() { // from class: com.proje.mobilesales.core.design.Netsis.14
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel customerNew2) {
                iCustomerSendCompleted.onCustomerSendCompleted(new ResponseEvent("", true));
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                iCustomerSendCompleted.onCustomerSendCompleted(new ResponseEvent(str, false));
            }
        });
    }

    
    public void getOrderValidationList(int r3, int r4, String str, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getSalesOrderValidationList(getSalesManagerSalesmanFilter(), r3, r4, str), responseListener);
    }

    
    public void getOrderDetails(int r1, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }

    class C262115 implements ResponseListener<List<Object>> {
        final List valorderLogicalRefList;
        final ResponseListener valresponseListener;

        C262115(ResponseListener responseListener, List list) {
            this.valresponseListener = responseListener;
            this.valorderLogicalRefList = list;
        }
        public void onResponse(@Nullable @UnknownNullability PrintSlipModel list) {
            if (list == null || list.size() == 0) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            List list2 = (List) list.stream().map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.Netsis15ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return Netsis.C262115.lambdaonResponse0(obj);
                }
            }).filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.Netsis15ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    return Netsis.C262115.lambdaonResponse1((DeviceId) obj);
                }
            }).collect(Collectors.toList());
            if ((list2 == null || list2.isEmpty()) && !Netsis.this.getUserSettings().isDemo()) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            Observable observableObserveOn = Netsis.this.getRestAsyncTask().getApi().getQueriesRx(new NetsisTSql(Netsis.this.mQueryCreator.updateOrderStatus(this.valorderLogicalRefList, NetsisInvoiceType.ft_Acik).getSql())).map(new Function() { // from class: com.proje.mobilesales.core.design.Netsis15ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Function
                public Object apply(Object obj) {
                    return Netsis.C262115.lambdaonResponse2((NetsisDataHeader) obj);
                }
            }).subscribeOn(Schedulers.m474io()).observeOn(AndroidSchedulers.mainThread());
            final ResponseListener responseListener = this.valresponseListener;
            Consumer consumer = new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis15ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) {
                    responseListener.onResponse((Boolean) obj);
                }
            };
            final ResponseListener responseListener2 = this.valresponseListener;
            observableObserveOn.subscribe(consumer, new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis15ExternalSyntheticLambda4
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    Netsis.C262115.lambdaonResponse4(responseListener2, (Throwable) obj);
                }
            });
        }

        
        public static DeviceId lambdaonResponse0(Object obj) {
            return (DeviceId) obj;
        }

        
        public static boolean lambdaonResponse1(DeviceId deviceId) {
            return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
        }

        
        public static Boolean lambdaonResponse2(NetsisDataHeader netsisDataHeader) throws Exception {
            return Boolean.valueOf(netsisDataHeader.isSuccessful());
        }

        
        public static void lambdaonResponse4(ResponseListener responseListener, Throwable th) throws Exception {
            responseListener.onError(th.getMessage());
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String str) {
            this.valresponseListener.onError(str);
        }
    }

    
    public void updateOrderDispatchable(List list, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getDeviceId(), new C262115(responseListener, list));
    }

    /* renamed from: com.proje.mobilesales.core.design.Netsis16 */
    class C262216 implements ResponseListener<List<Object>> {
        final List valorderLogicalRefList;
        final ResponseListener valresponseListener;

        C262216(ResponseListener responseListener, List list) {
            this.valresponseListener = responseListener;
            this.valorderLogicalRefList = list;
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(@Nullable @UnknownNullability PrintSlipModel list) {
            if (list == null || list.size() == 0) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            List list2 = (List) list.stream().map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.Netsis16ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return Netsis.C262216.lambdaonResponse0(obj);
                }
            }).filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.Netsis16ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    return Netsis.C262216.lambdaonResponse1((DeviceId) obj);
                }
            }).collect(Collectors.toList());
            if ((list2 == null || list2.isEmpty()) && !Netsis.this.getUserSettings().isDemo()) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            Observable observableObserveOn = Netsis.this.getRestAsyncTask().getApi().getQueriesRx(new NetsisTSql(Netsis.this.mQueryCreator.updateOrderStatus(this.valorderLogicalRefList, NetsisInvoiceType.ft_Muhtelif).getSql())).map(new Function() { // from class: com.proje.mobilesales.core.design.Netsis16ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Function
                public Object apply(Object obj) {
                    return Netsis.C262216.lambdaonResponse2((NetsisDataHeader) obj);
                }
            }).subscribeOn(Schedulers.m474io()).observeOn(AndroidSchedulers.mainThread());
            final ResponseListener responseListener = this.valresponseListener;
            Consumer consumer = new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis16ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) {
                    responseListener.onResponse((Boolean) obj);
                }
            };
            final ResponseListener responseListener2 = this.valresponseListener;
            observableObserveOn.subscribe(consumer, new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis16ExternalSyntheticLambda4
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    Netsis.C262216.lambdaonResponse4(responseListener2, (Throwable) obj);
                }
            });
        }

        
        public static DeviceId lambdaonResponse0(Object obj) {
            return (DeviceId) obj;
        }

        
        public static boolean lambdaonResponse1(DeviceId deviceId) {
            return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
        }

        
        public static Boolean lambdaonResponse2(NetsisDataHeader netsisDataHeader) throws Exception {
            return Boolean.valueOf(netsisDataHeader.isSuccessful());
        }

        
        public static void lambdaonResponse4(ResponseListener responseListener, Throwable th) throws Exception {
            responseListener.onError(th.getMessage());
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String str) {
            this.valresponseListener.onError(str);
        }
    }

    
    public void updateOrderUnDispatchable(List list, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getDeviceId(), new C262216(responseListener, list));
    }

    
    public void updateCustomer(int r3) {
        updateData(this.mQueryCreator.getCustomers(r3, false), r3);
    }

    
    public void updateShipAddress(int r3) {
        updateData(this.mQueryCreator.getShipAddress(false), r3);
    }

    
    public void sendGpsLog() {
        try {
            List<NetsisSelectResult> listInsertGpsInformations = this.mQueryCreator.insertGpsInformations(getLogoSqlHelper().getTable(GpsInfo.class));
            if (listInsertGpsInformations.size() > 0) {
                String strCheckWorkTimeControl = checkWorkTimeControl(WorkTimeControlProcessType.TransferSend);
                if (TextUtils.isEmpty(strCheckWorkTimeControl)) {
                    for (NetsisSelectResult netsisSelectResult : listInsertGpsInformations) {
                        getRestAsyncTask().runInsert(netsisSelectResult);
                        if (netsisSelectResult.isSuccess()) {
                            Log.d(TAG, "run() called with: basarili");
                            getLogoSqlBriteDatabase().delete(GpsInfo.class, "ID=?", String.valueOf(netsisSelectResult.getLogicalRef()));
                        }
                    }
                    return;
                }
                if (strCheckWorkTimeControl.equals(ContextUtils.getStringResource(R.string.exp_68_cannot_send_data_outside_working_hours))) {
                    stopMyService(ContextUtils.getmContext(), LocationUpdatesService.class);
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "sendGpsLog : " + e2.getMessage());
        }
    }

    private class InsertLogoTable extends AsyncTask<NetsisSelectResult, Void, Void> {
        private final ResponseEvent responseEvent = new ResponseEvent();

        InsertLogoTable() {
        }

        
        @Override // android.os.AsyncTask
        public Void doInBackground(NetsisSelectResult... netsisSelectResultArr) {
            try {
                NetsisSelectResult netsisSelectResult = netsisSelectResultArr[0];
                Netsis.this.getRestAsyncTask().runInsert(netsisSelectResult);
                if (netsisSelectResult.isSuccess()) {
                    Netsis.this.insertData(netsisSelectResult.getDataList(), false);
                    this.responseEvent.setSuccess(true);
                } else {
                    this.responseEvent.setSuccess(netsisSelectResult.getErrorString());
                }
                return null;
            } catch (Exception e2) {
                Log.e(Netsis.TAG, "doInBackground: ", e2);
                this.responseEvent.setSuccess(ContextUtils.getStringResource(R.string.exp_17_send_error));
                return null;
            }
        }

        
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            super.onPostExecute((InsertLogoTable) r1);
            Netsis.this.postEvent(this.responseEvent);
        }
    }

    
    public void sendStartInfoEnter(StartInfo startInfo) {
        new InsertLogoTable().execute(getQueryCreator().insertStartInfo(startInfo));
    }

    
    public List<TransferGetItem> getTransferList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TransferGetItem(TransferGetType.ERP_PARAMS));
        arrayList.add(new TransferGetItem(TransferGetType.OTHER_INFO));
        arrayList.add(new TransferGetItem(TransferGetType.WAREHOUSE));
        arrayList.add(new TransferGetItem(TransferGetType.TODO));
        arrayList.add(new TransferGetItem(TransferGetType.MARKETING_MESSAGE));
        arrayList.add(new TransferGetItem(TransferGetType.VISIT));
        arrayList.add(new TransferGetItem(TransferGetType.PENETRATION));
        arrayList.add(new TransferGetItem(TransferGetType.SPECODE));
        arrayList.add(new TransferGetItem(TransferGetType.PAYMENT));
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
        arrayList.add(new TransferGetItem(TransferGetType.CASE));
        arrayList.add(new TransferGetItem(TransferGetType.DESIGN_FILES));
        arrayList.add(new TransferGetItem(TransferGetType.CURRENCY));
        arrayList.add(new TransferGetItem(TransferGetType.PROJECT));
        arrayList.add(new TransferGetItem(TransferGetType.EMAIL));
        arrayList.add(new TransferGetItem(TransferGetType.ITEM_IMAGE));
        arrayList.add(new TransferGetItem(TransferGetType.CUSTOMER_IMAGE));
        return arrayList;
    }

    
    public void getOrderGrossTotalOnline(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getOrderGrossTotal(r2), responseListener);
    }

    
    public void getOrderAvailableAmounts(ArrayList arrayList, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getOrderAvailableAmounts((ArrayList<String>) arrayList), responseListener);
    }

    
    public void getOrderAvailableAmountsFromDetailRef(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getOrderAvailableAmountsFromDetailRef(r2), responseListener);
    }

    
    public void getOrderAvailableAmountsFromDetailWithRefs(ArrayList arrayList, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getOrderAvailableAmountsFromDetailWithRefs(arrayList), responseListener);
    }

    
    public void readOrderFiche(ArrayList arrayList, DataObjectType dataObjectType, Sales sales, List list, ResponseListener responseListener) {
        getRestAsyncTask().readOrderFiches(arrayList, dataObjectType, sales, list, responseListener);
    }

    
    public void getCustomerSummaryTotalBalance(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getCustomerLastBalance(r2), responseListener);
    }

    
    public void getCustomerSummaryTopTenProduct(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getCustomerTopTenProduct(r2), responseListener);
    }

    
    public void getCustomerSummarySales(int r2, boolean z, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getCustomerMonthWeekSales(r2, z), responseListener);
    }

    
    public void getProductTopTenCustomer(int r2, ResponseListener responseListener) {
        String itemCode = getLogoSqlHelper().getItemCode(r2);
        if (itemCode == null || itemCode.isEmpty()) {
            responseListener.onResponse(null);
        }
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getProductTopTenCustomer(itemCode), responseListener);
    }

    
    public void getProductMonthlySales(int r1, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }

    public NetsisQueryCreator getQueryCreator() {
        return this.mQueryCreator;
    }

    
    public void getReportOnline(ResponseListener responseListener) {
        new GetUserReports(responseListener).execute();
    }

    
    class GetUserReports extends AsyncTask<Void, String, ResponseEvent> {
        private final ProgressDialogBuilder mProgressDialogBuilder = new ProgressDialogBuilder.Impl(ContextUtils.getmContext(), (BaseInjectableActivity) ContextUtils.getmActivity());
        private final ResponseListener responseListener;

        GetUserReports(ResponseListener responseListener) {
            this.responseListener = responseListener;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.mProgressDialogBuilder.setMessage(ContextUtils.getStringResource(R.string.str_reports_update_please_wait)).setCancelable(true).setOnCancelClickListener().show();
            super.onPreExecute();
        }

        
        public void lambdaonPreExecute0(DialogInterface dialogInterface) {
            cancel(true);
        }

        
        @Override // android.os.AsyncTask
        public ResponseEvent doInBackground(Void... voidArr) {
            ResponseEvent responseEvent = new ResponseEvent();
            try {
                NetsisSelectResult netsisSelectResultRunSelect = Netsis.this.getRestAsyncTask().lambdagetSelectRxn24();
                if (netsisSelectResultRunSelect.isSuccess()) {
                    Netsis.this.getRestAsyncTask().runSelectRx(Netsis.this.mQueryCreator.getReports(), this.responseListener);
                } else {
                    responseEvent.setErrorMessage(netsisSelectResultRunSelect.getErrorString());
                }
            } catch (Exception e2) {
                responseEvent.setErrorMessage(e2.getMessage());
            }
            return responseEvent;
        }

        
        @Override // android.os.AsyncTask
        public void onCancelled(ResponseEvent responseEvent) {
            this.mProgressDialogBuilder.dismiss();
            if (responseEvent != null) {
                responseEvent.setSuccess(false);
                responseEvent.setErrorMessage(ContextUtils.getStringResource(R.string.str_cancel_process));
            }
            super.onCancelled((GetUserReports) responseEvent);
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            this.mProgressDialogBuilder.dismiss();
            super.onCancelled();
        }

        
        @Override // android.os.AsyncTask
        public void onProgressUpdate(String... strArr) {
            super.onProgressUpdate((Object[]) strArr);
            this.mProgressDialogBuilder.setMessage(strArr[0]);
        }

        
        @Override // android.os.AsyncTask
        public void onPostExecute(ResponseEvent responseEvent) {
            super.onPostExecute((GetUserReports) responseEvent);
            this.mProgressDialogBuilder.dismiss();
        }
    }

    
    public boolean isFicheCustomerTransfered(String str, int r4) {
        int r2;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getLogoSqlBriteDatabase().query("SELECT ISTRANSFER FROM CLCARD WHERE CODE = (SELECT CLCODE FROM " + str + " WHERE LOGICALREF =" + r4 + ")", null);
                r2 = (cursorQuery == null || !cursorQuery.moveToFirst()) ? 0 : cursorQuery.getInt(0);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception e2) {
                Log.i(TAG, "isFicheCustomerTransfered: ", e2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                r2 = 0;
            }
            return r2 == 1;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    
    public int getFicheCustomerRef(String str, int r6) {
        int r0 = 0;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getLogoSqlBriteDatabase().query("SELECT LOGICALREF FROM CLCARD WHERE CODE = (SELECT CLCODE FROM " + str + " WHERE LOGICALREF =" + r6 + ")");
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    r0 = cursorQuery.getInt(0);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                }
            }
            return r0;
        } finally {
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
        }
    }

    
    public void getOnlineShipCustomer(String str, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getShipCustomers(str), responseListener);
    }

    
    public void deletePenetrationRemote(final String str, final ResponseListener responseListener) {
        getRestAsyncTask().runSelectRx(getQueryCreator().deletePenetrationHeader(str), new ResponseListener<Boolean>() { // from class: com.proje.mobilesales.core.design.Netsis.17
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel bool) {
                if (bool.booleanValue()) {
                    Netsis.this.getRestAsyncTask().runSelectRx(Netsis.this.getQueryCreator().deletePenetrationDetail(str), responseListener);
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str2) {
                responseListener.onError(str2);
            }
        });
    }

    
    public String getCustomerUnsentDataTypes(int r6) {
        String string;
        String stringResource = ContextUtils.getStringResource(R.string.net_qry_get_customer_unsent_data_count, getLogoSqlHelper().getClCode(r6));
        StringBuilder sb = new StringBuilder();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getLogoSqlBriteDatabase().query(stringResource);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    String[] stringArrayResource = ContextUtils.getStringArrayResource(R.array.array_unsentfiche_type_def);
                    do {
                        sb.append(stringArrayResource[cursorQuery.getInt(0)]);
                        sb.append(" ,");
                    } while (cursorQuery.moveToNext());
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                string = sb.toString();
                if (!string.endsWith(",")) {
                    return string;
                }
            } catch (Exception e2) {
                Log.i(TAG, "getCustomerUnsentDataTypes: ", e2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                string = sb.toString();
                if (!string.endsWith(",")) {
                    return string;
                }
            }
            return string.substring(0, string.length() - 1);
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            String string2 = sb.toString();
            if (string2.endsWith(",")) {
                string2.substring(0, string2.length() - 1);
            }
            throw th;
        }
    }

    
    public void setCustomerInchargeEmailAddress(ClCard clCard) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getLogoSqlBriteDatabase().query(ContextUtils.getStringResource(R.string.net_qry_customer_incharge_mails, clCard.getCode()));
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    clCard.setOrdSendEmailAddr(cursorQuery.getString(cursorQuery.getColumnIndex("ORDSENDEMAILADDR")));
                    clCard.setDspSendEmailAddr(cursorQuery.getString(cursorQuery.getColumnIndex("DSPSENDEMAILADDR")));
                    clCard.setInvSendEmailAddr(cursorQuery.getString(cursorQuery.getColumnIndex("INVSENDEMAILADDR")));
                    clCard.setExtSendEmailAddr(cursorQuery.getString(cursorQuery.getColumnIndex("EXTSENDEMAILADDR")));
                }
                if (cursorQuery == null || cursorQuery.isClosed()) {
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursorQuery == null || cursorQuery.isClosed()) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public void updateCustomerInchargeRecID(String str, String str2, GenericDataPrimitive genericDataPrimitive) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(genericDataPrimitive.getName(), String.valueOf(genericDataPrimitive.getValue()));
        getLogoSqlBriteDatabase().update("CLCARDINCHARGE", contentValues, "CLCODE=? AND CREATEDDATE=?", str, str2);
    }

    
    public boolean isSalesDetailCurrTypeChange(int r6, int r7) {
        List table = getLogoSqlHelper().getTable(ClCard.class, "LOGICALREF=?", new String[]{String.valueOf(r6)});
        boolean z = table != null && table.size() > 0 && ((ClCard) table.get(0)).getCurrencyCustomer() != 0;
        List table2 = getLogoSqlHelper().getTable(Item.class, "LOGICALREF=?", new String[]{String.valueOf(r7)});
        return z && (table2 != null && table2.size() > 0 && ((Item) table2.get(0)).currType != 0) && getEnterPrice();
    }

    
    public void getCustomerExtractReport(int r2, String str, String str2, String str3, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getCustomerExtractReport(r2, str, str2, str3), responseListener);
    }

    
    public void getSalesProductLinesPrice(Sales sales, List list, ResponseListener responseListener) throws CloneNotSupportedException {
        responseListener.onResponse(null);
    }

    
    public void getItemSurplusDiscount(int r2, String str, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getItemSurplusDiscount(r2, str), responseListener);
    }

    
    public String getServerTime() {
        String strNowTime2 = DateAndTimeUtils.nowTime2();
        try {
            NetsisSelectResult serverTime = this.mQueryCreator.getServerTime();
            getRestAsyncTask().lambdagetSelectRxn24();
            if (!serverTime.isSuccess() || serverTime.getDataList() == null || serverTime.getDataList().size() <= 0 || serverTime.getDataList().get(0) == null) {
                return strNowTime2;
            }
            User user = (User) serverTime.getDataList().get(0);
            Log.d("Server_Time", "Remote Server Time:" + user.getWorkTime());
            return user.getWorkTime();
        } catch (Exception e2) {
            Log.e("Server_Time", "Remote Server Time Error:" + e2.getMessage());
            return strNowTime2;
        }
    }

    
    public String getServerLongTime() {
        String strNowDateTime = DateAndTimeUtils.nowDateTime();
        try {
            NetsisSelectResult serverLongTime = this.mQueryCreator.getServerLongTime();
            getRestAsyncTask().lambdagetSelectRxn24();
            if (!serverLongTime.isSuccess() || serverLongTime.getDataList() == null || serverLongTime.getDataList().size() <= 0 || serverLongTime.getDataList().get(0) == null) {
                return strNowDateTime;
            }
            User user = (User) serverLongTime.getDataList().get(0);
            Log.d("Server_Time", "Remote Server Time:" + user.getWorkTime());
            return user.getWorkTime();
        } catch (Exception e2) {
            Log.e("Server_Time", "Remote Server Time Error:" + e2.getMessage());
            return strNowDateTime;
        }
    }

    
    public void getServerTime(final ResponseListener responseListener) {
        if (!ContextUtils.checkConnectionWithoutMessage()) {
            responseListener.onResponse(DateAndTimeUtils.nowTime2());
        } else {
            final NetsisSelectResult serverTime = getQueryCreator().getServerTime();
            Observable.just(serverTime).doOnNext(new Consumer() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda10
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    this.f0.lambdagetServerTime3(serverTime, (NetsisSelectResult) obj);
                }
            }).doOnError(new Consumer() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda11
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    Netsis.lambdagetServerTime4((Throwable) obj);
                }
            }).subscribeOn(Schedulers.m474io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda12
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    Netsis.lambdagetServerTime5(serverTime, responseListener, (NetsisSelectResult) obj);
                }
            }, new Consumer() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda13
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    Netsis.lambdagetServerTime6(responseListener, (Throwable) obj);
                }
            });
        }
    }

    
    public void lambdagetServerTime3(NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        getRestAsyncTask().lambdagetSelectRxn24();
    }

    
    public static void lambdagetServerTime4(Throwable th) throws Exception {
        Log.e(TAG, "runSelectRx: ", th);
    }

    
    public static void lambdagetServerTime5(NetsisSelectResult netsisSelectResult, ResponseListener responseListener, NetsisSelectResult netsisSelectResult2) throws Exception {
        if (netsisSelectResult.isSuccess() && netsisSelectResult.getDataList() != null && netsisSelectResult.getDataList().size() > 0 && netsisSelectResult.getDataList().get(0) != null) {
            User user = (User) netsisSelectResult.getDataList().get(0);
            Log.d("Server_Time", "Remote Server Time:" + user.getWorkTime());
            responseListener.onResponse(user.getWorkTime());
            return;
        }
        Log.d("Server_Time", "Remote Server Time Error:");
        responseListener.onResponse(DateAndTimeUtils.nowTime2());
    }

    
    public static void lambdagetServerTime6(ResponseListener responseListener, Throwable th) throws Exception {
        Log.e("Server_Time", "Remote Server Time Error:" + th.getMessage());
        responseListener.onResponse(DateAndTimeUtils.nowTime2());
    }

    
    public void getSalesManagers(ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getSalesManagers(), responseListener);
    }

    
    public void execWmsBarcodeSp(String str, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.execBarcodeSp(str), responseListener);
    }

    public List<ItemVariantStock> getVariantInfo(int r3, int r4, String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = this.logoSqlBriteDatabase.query("SELECT V.CODE,V.NAME,V.LOGICALREF,VS.ONHAND,VS.REALSTOCK,VS.WAREHOUSENR, V.ITEMCODE FROM VARIANTS V LEFT JOIN VARIANTSTOCK VS ON VS.VARIANTCODE = V.CODE WHERE VS.WAREHOUSENR=? AND V.ITEMCODE=?", String.valueOf(r4), str);
        if (cursorQuery != null) {
            try {
                try {
                    if (cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                        do {
                            ItemVariantStock itemVariantStock = new ItemVariantStock();
                            itemVariantStock.setVarintCode(cursorQuery.getString(cursorQuery.getColumnIndex("CODE")));
                            itemVariantStock.setVariantName(cursorQuery.getString(cursorQuery.getColumnIndex("NAME")));
                            itemVariantStock.setVariantRef(cursorQuery.getInt(cursorQuery.getColumnIndex("LOGICALREF")));
                            itemVariantStock.setVariantActualStok(cursorQuery.getDouble(cursorQuery.getColumnIndex("ONHAND")));
                            itemVariantStock.setVariantRealStok(cursorQuery.getDouble(cursorQuery.getColumnIndex("REALSTOCK")));
                            itemVariantStock.setWareHouseNr(cursorQuery.getInt(cursorQuery.getColumnIndex("WAREHOUSENR")));
                            itemVariantStock.setItemCode(cursorQuery.getString(cursorQuery.getColumnIndex("ITEMCODE")));
                            arrayList.add(itemVariantStock);
                        } while (cursorQuery.moveToNext());
                    }
                    if (!cursorQuery.isClosed()) {
                        cursorQuery.close();
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getCustomerClCode: ", e2);
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        } else if (cursorQuery != null) {
        }
        return arrayList;
    }

    
    public void getLastPurchaseInfo(LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getLastPurchaseInfo(lastPurchaseInfoSqlParams), responseListener);
    }

    
    public void getCustomerNowAndBeforeBalance(int r1, Class cls, int r3, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }

    
    public void getWarehouseItems(int r1, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }

    
    public List<Double> calculateLineUnitTotals(Sales sales) {
        double sTraGCMIK;
        double brCevrim2;
        BaseServiceResult invoice = getSendCreator().getInvoice(sales);
        ArrayList arrayList = new ArrayList();
        if (invoice.getData() == null) {
            return null;
        }
        ItemSlip itemSlip = (ItemSlip) invoice.getSendData();
        arrayList.add(Double.valueOf(0.0d));
        arrayList.add(Double.valueOf(0.0d));
        arrayList.add(Double.valueOf(0.0d));
        double sTraGCMIK2 = 0.0d;
        double dConvertByConfact = 0.0d;
        for (ItemSlipLine itemSlipLine : itemSlip.getLines()) {
            double sTraGCMIK3 = itemSlipLine.getSTraGCMIK();
            if (!getLogoSqlHelper().getLogoParamValue("OLCUBIRIMITABLODAN").equals("D") || itemSlipLine.getCEVRIM() == 0.0d) {
                double dConvertByConfact2 = convertByConfact(itemSlipLine.getStokKodu(), 1, 2, sTraGCMIK3);
                dConvertByConfact = convertByConfact(itemSlipLine.getStokKodu(), 1, 3, sTraGCMIK3);
                sTraGCMIK2 = dConvertByConfact2;
            } else {
                int olcubr = itemSlipLine.getOlcubr();
                if (olcubr == 1) {
                    sTraGCMIK2 = itemSlipLine.getSTraGCMIK() * itemSlipLine.getBrCevrim1();
                    sTraGCMIK = itemSlipLine.getSTraGCMIK();
                    brCevrim2 = itemSlipLine.getBrCevrim2();
                } else if (olcubr == 2) {
                    if (itemSlipLine.getCEVRIM() == 0.0d && itemSlipLine.getBrCevrim1() == 0.0d) {
                        sTraGCMIK2 = convertByConfact(itemSlipLine.getStokKodu(), 2, sTraGCMIK3);
                    } else {
                        sTraGCMIK2 = itemSlipLine.getSTraGCMIK() * (itemSlipLine.getCEVRIM() != 0.0d ? itemSlipLine.getCEVRIM() : itemSlipLine.getBrCevrim1());
                    }
                    sTraGCMIK = itemSlipLine.getSTraGCMIK();
                    brCevrim2 = itemSlipLine.getBrCevrim2();
                } else if (olcubr == 3) {
                    sTraGCMIK2 = itemSlipLine.getSTraGCMIK() * itemSlipLine.getBrCevrim1();
                    if (itemSlipLine.getCEVRIM() == 0.0d && itemSlipLine.getBrCevrim2() == 0.0d) {
                        dConvertByConfact = convertByConfact(itemSlipLine.getStokKodu(), 3, sTraGCMIK3);
                    } else {
                        sTraGCMIK = itemSlipLine.getSTraGCMIK();
                        brCevrim2 = itemSlipLine.getCEVRIM() != 0.0d ? itemSlipLine.getCEVRIM() : itemSlipLine.getBrCevrim2();
                    }
                }
                dConvertByConfact = sTraGCMIK * brCevrim2;
            }
            arrayList.set(0, Double.valueOf(((Double) arrayList.get(0)).doubleValue() + sTraGCMIK3));
            arrayList.set(1, Double.valueOf(((Double) arrayList.get(1)).doubleValue() + sTraGCMIK2));
            arrayList.set(2, Double.valueOf(((Double) arrayList.get(2)).doubleValue() + dConvertByConfact));
        }
        return arrayList;
    }

    
    public void getDiscountCardRatio(Sales sales, String str, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }

    
    public void fillTransferUserWhInfo(Sales sales) {
        sales.setTransferSourceWareHouse(new FicheRefProp(getUser().getWarehouseNr(), -1, ""));
    }

    
    public void listAllCustomersOnline(String str, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.listAllCustomersOnline(str), responseListener);
    }

    
    public Disposable getEDocumentContent(String str, ResponseListener responseListener, SalesType salesType, Boolean bool) {
        getRestAsyncTask().getSelectRxn(getQueryCreator().getEDocumentPdfHeader(str, salesType), getQueryCreator().getEdocumentPdfDetail(str, salesType), responseListener);
        return null;
    }

    
    public String getWarehouseUnsentDataTypes(int r6) {
        String string;
        String stringResource = ContextUtils.getStringResource(R.string.net_qry_get_warehouse_unsent_data_count, getLogoSqlHelper().getClCode(r6));
        StringBuilder sb = new StringBuilder();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = getLogoSqlBriteDatabase().query(stringResource);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    String[] stringArrayResource = ContextUtils.getStringArrayResource(R.array.array_unsentfiche_type_def);
                    do {
                        sb.append(stringArrayResource[cursorQuery.getInt(0)]);
                        sb.append(" ,");
                    } while (cursorQuery.moveToNext());
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                string = sb.toString();
                if (!string.endsWith(",")) {
                    return string;
                }
            } catch (Exception e2) {
                Log.i(TAG, "getCustomerUnsentDataTypes: ", e2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                string = sb.toString();
                if (!string.endsWith(",")) {
                    return string;
                }
            }
            return string.substring(0, string.length() - 1);
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            String string2 = sb.toString();
            if (string2.endsWith(",")) {
                string2.substring(0, string2.length() - 1);
            }
            throw th;
        }
    }

    
    public void updateSalesDetailItemStock(Sales sales) {
        String str = "";
        try {
            if (sales.getMSalesDetailList() != null && sales.getMSalesDetailList().size() != 0) {
                List<String> list = (List) sales.getMSalesDetailList().stream().filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda21
                    @Override // java.util.function.Predicate
                    public boolean test(Object obj) {
                        return Netsis.lambdaupdateSalesDetailItemStock7((SalesDetail) obj);
                    }
                }).map(new NetsisRestAsyncTaskExternalSyntheticLambda73()).distinct().collect(Collectors.toList());
                List list2 = (List) sales.getMSalesDetailList().stream().filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda22
                    @Override // java.util.function.Predicate
                    public boolean test(Object obj) {
                        return Netsis.lambdaupdateSalesDetailItemStock8((SalesDetail) obj);
                    }
                }).map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda23
                    @Override // java.util.function.Function
                    public Object apply(Object obj) {
                        return Netsis.lambdaupdateSalesDetailItemStock9((SalesDetail) obj);
                    }
                }).distinct().collect(Collectors.toList());
                if (list.size() == 0) {
                    return;
                }
                if (!SalesUtils.isSalesTypeWhTransfer(sales.getmSalesType())) {
                    if (getProductShowAllStock()) {
                        String strReplace = getLogoSqlHelper().getParamValue(ParameterTypes.ptGeneralSettings_Warehouse).replace(";", ",");
                        if (!strReplace.equals("")) {
                            str = " WAREHOUSENR IN (" + strReplace + ") ";
                        }
                        getProductShowAllStock(list, str, sales);
                        return;
                    }
                    if (list2.size() > 0) {
                        getItemStock("SELECT ITEMCODE, SUM(ONHAND) ONHAND, SUM(REALSTOCK) AS REALSTOCK FROM ITEMSTOCK WHERE " + list2.stream().map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda24
                            @Override // java.util.function.Function
                            public Object apply(Object obj) {
                                return Netsis.lambdaupdateSalesDetailItemStock10((ItemStock) obj);
                            }
                        }).collect(Collectors.joining(" OR ")) + " GROUP BY ITEMCODE", list, sales);
                        return;
                    }
                    return;
                }
                getProductShowAllStock(list, " WAREHOUSENR = " + getUser().getWarehouseNr() + " ", sales);
            }
        } catch (Exception e2) {
            Log.e("Sales", "updateSalesDetailItemStock", e2);
        }
    }

    
    public static boolean lambdaupdateSalesDetailItemStock7(SalesDetail salesDetail) {
        return !salesDetail.isHasVariant();
    }

    
    public static boolean lambdaupdateSalesDetailItemStock8(SalesDetail salesDetail) {
        return !salesDetail.isHasVariant();
    }

    
    public static ItemStock lambdaupdateSalesDetailItemStock9(SalesDetail salesDetail) {
        return new ItemStock(salesDetail.getCode(), salesDetail.getWareHouse().getLogicalRef());
    }

    
    public static String lambdaupdateSalesDetailItemStock10(ItemStock itemStock) {
        return "(ITEMCODE = '" + itemStock.itemCode + "' AND WAREHOUSENR = " + itemStock.wareHouseNr + ")";
    }

    private void getProductShowAllStock(List<String> list, String str, Sales sales) {
        if (list.size() > 0) {
            getItemStock("SELECT ITEMCODE, SUM(ONHAND) ONHAND, SUM(REALSTOCK) REALSTOCK FROM ITEMSTOCK WHERE " + str + " AND ITEMCODE IN (" + list.stream().map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda8
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return Netsis.lambdagetProductShowAllStock11((String) obj);
                }
            }).collect(Collectors.joining(",")) + ") GROUP BY ITEMCODE", list, sales);
        }
    }

    
    public static String lambdagetProductShowAllStock11(String str) {
        return "'" + str + "'";
    }

    private void getItemStock(String str, List<String> list, Sales sales) {
        List<ItemStock> itemStocksWithQuery = getItemStocksWithQuery(str);
        for (final String str2 : list) {
            for (SalesDetail salesDetail : (List) sales.getMSalesDetailList().stream().filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    return Netsis.lambdagetItemStock12(str2, (SalesDetail) obj);
                }
            }).collect(Collectors.toList())) {
                List list2 = (List) itemStocksWithQuery.stream().filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public boolean test(Object obj) {
                        return Netsis.lambdagetItemStock13(str2, (ItemStock) obj);
                    }
                }).collect(Collectors.toList());
                salesDetail.setActualStock(list2.size() == 0 ? 0.0d : ((ItemStock) list2.get(0)).onHand);
            }
        }
    }

    
    public static boolean lambdagetItemStock12(String str, SalesDetail salesDetail) {
        return salesDetail.getCode().equals(str);
    }

    
    public static boolean lambdagetItemStock13(String str, ItemStock itemStock) {
        return itemStock.itemCode.equals(str);
    }

    
    public void getOnlineStockAllInSingleResponse(ResponseListener responseListener) {
        getRestAsyncTask().runSelectRx(this.mQueryCreator.getItemStock(false), responseListener);
    }

    public List<ItemStock> getItemStocksWithQuery(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = this.logoSqlBriteDatabase.query(str);
        try {
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0 && cursorQuery.moveToFirst()) {
                        do {
                            ItemStock itemStock = new ItemStock();
                            itemStock.itemCode = cursorQuery.getString(cursorQuery.getColumnIndex("ITEMCODE"));
                            itemStock.onHand = cursorQuery.getDouble(cursorQuery.getColumnIndex("ONHAND"));
                            arrayList.add(itemStock);
                        } while (cursorQuery.moveToNext());
                    }
                    if (!cursorQuery.isClosed()) {
                        cursorQuery.close();
                    }
                } catch (Exception e2) {
                    Log.e(TAG, "getItemStocksWithQuery:", e2);
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } else if (cursorQuery != null) {
            }
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    
    public List<ItemVariantStock> getItemVariantStocksWithQuery(String str) {
        return new ArrayList();
    }

    
    public void getLastCustomerSalesDateOfItem(int r1, int r2, ResponseListener responseListener) {
        responseListener.onResponse(null);
    }

    
    public String getDbName() {
        return String.format("%s%s%s.db", this.mErpType.erpName, "_MSData", this.mUserSettings.getDatabaseName());
    }

    /* renamed from: com.proje.mobilesales.core.design.Netsis18 */
    class C262418 implements ResponseListener<Sales> {
        final EDocumentResponse valeDocumentResponse;
        final int valficheRef;
        final FicheType valficheType;
        final ResponseListener valresponseListener;

        C262418(FicheType ficheType, EDocumentResponse eDocumentResponse, int r4, ResponseListener responseListener) {
            this.valficheType = ficheType;
            this.valeDocumentResponse = eDocumentResponse;
            this.valficheRef = r4;
            this.valresponseListener = responseListener;
        }

        public void onResponse( PrintSlipModel sales) {
            EDocumentType eDocumentType;
            FicheType ficheType = this.valficheType;
            FicheType ficheType2 = FicheType.INVOICE;
            BaseServiceResult invoice = ficheType == ficheType2 ? Netsis.this.getSendCreator().getInvoice(sales) : Netsis.this.getSendCreator().getDispatch(sales);
            if (invoice != null && invoice.getSendData() != null) {
                ItemSlip itemSlip = (ItemSlip) invoice.getSendData();
                DraftEDocumentParam draftEDocumentParam = new DraftEDocumentParam();
                if (this.valficheType == ficheType2) {
                    eDocumentType = itemSlip.getSlipHeader().iseInvoiceCustomer() ? EDocumentType.ebtEFatura : EDocumentType.ebtArsiv;
                } else {
                    eDocumentType = EDocumentType.ebtEIrs;
                }
                draftEDocumentParam.setType(eDocumentType);
                draftEDocumentParam.setDesignControl(false);
                draftEDocumentParam.setDocumentNo(sales.getFicheNo());
                Observable<DataResponse> observablePostEDocument = Netsis.this.getRestAsyncTask().getApi().postEDocument(draftEDocumentParam);
                final EDocumentResponse eDocumentResponse = this.valeDocumentResponse;
                final int r1 = this.valficheRef;
                Observable<DataResponse> observableSubscribeOn = observablePostEDocument.doOnNext(new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis18ExternalSyntheticLambda0
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Object obj) throws Exception {
                        this.f0.lambdaonResponse0(eDocumentResponse, r1, (DataResponse) obj);
                    }
                }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
                final ResponseListener responseListener = this.valresponseListener;
                final EDocumentResponse eDocumentResponse2 = this.valeDocumentResponse;
                Consumer<? super DataResponse> consumer = new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis18ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Object obj) {
                        responseListener.onResponse(eDocumentResponse2);
                    }
                };
                final ResponseListener responseListener2 = this.valresponseListener;
                observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis18ExternalSyntheticLambda2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Object obj) throws Exception {
                        Netsis.C262418.lambdaonResponse2(responseListener2, (Throwable) obj);
                    }
                });
                return;
            }
            this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
        }

        
        public void lambdaonResponse0(EDocumentResponse eDocumentResponse, int r3, DataResponse dataResponse) throws Exception {
            eDocumentResponse.setSuccess(dataResponse.isSuccessful());
            if (!dataResponse.isSuccessful()) {
                eDocumentResponse.setSuccess(false);
                eDocumentResponse.setErrorDesc(dataResponse.getErrorDesc());
                Log.d(Netsis.TAG, dataResponse.getErrorDesc());
                return;
            }
            Netsis.this.getLogoSqlHelper().updateEDocStatus(EDocStatus.Draft, r3);
        }

        
        public static void lambdaonResponse2(ResponseListener responseListener, Throwable th) throws Exception {
            responseListener.onError(handleNetworkError(th));
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onError(String str) {
            this.valresponseListener.onError(str);
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
        public void onResponse(ArrayList<Sales> obj) {

        }

        @Override
        public void onResponse() {

        }

        @Override
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }
    }

    
    public void createEDocumentDraft(int r9, FicheType ficheType, ResponseListener responseListener) {
        getSendCreator().getSqlManager().getSalesInvoiceExam(r9, new C262418(ficheType, new EDocumentResponse(), r9, responseListener));
    }

    /* renamed from: com.proje.mobilesales.core.design.Netsis19 */
    class C262519 implements ResponseListener<Sales> {
        final EDocumentResponse valcreateAndSendResponse;
        final int valficheRef;
        final FicheType valficheType;
        final ResponseListener valresponseListener;

        C262519(FicheType ficheType, int r3, EDocumentResponse eDocumentResponse, ResponseListener responseListener) {
            this.valficheType = ficheType;
            this.valficheRef = r3;
            this.valcreateAndSendResponse = eDocumentResponse;
            this.valresponseListener = responseListener;
        }

        public void onResponse(@Nullable @UnknownNullability PrintSlipModel sales) {
            EDocumentType eDocumentType;
            FicheType ficheType = this.valficheType;
            FicheType ficheType2 = FicheType.INVOICE;
            BaseServiceResult invoice = ficheType == ficheType2 ? Netsis.this.getSendCreator().getInvoice(sales) : Netsis.this.getSendCreator().getDispatch(sales);
            if (invoice != null && invoice.getSendData() != null) {
                ItemSlip itemSlip = (ItemSlip) invoice.getSendData();
                itemSlip.getSlipHeader().setSlipNo(sales.getFicheNo());
                boolean z = this.valficheType == ficheType2 && !itemSlip.getSlipHeader().iseInvoiceCustomer();
                final SendEDocumentParam sendEDocumentParam = new SendEDocumentParam();
                sendEDocumentParam.setDocumentType(itemSlip.getSlipType());
                sendEDocumentParam.setCustomerCode(itemSlip.getSlipHeader().getCustomerCode());
                sendEDocumentParam.setDocumentNumber(sales.getFicheNo());
                if (this.valficheType == ficheType2) {
                    eDocumentType = itemSlip.getSlipHeader().iseInvoiceCustomer() ? EDocumentType.ebtEFatura : EDocumentType.ebtArsiv;
                } else {
                    eDocumentType = EDocumentType.ebtEIrs;
                }
                sendEDocumentParam.seteDocumentType(eDocumentType);
                Netsis.this.getRestAsyncTask().getSelectRx(Netsis.this.mQueryCreator.getEDocumentStatus(itemSlip, z), new ResponseListener<List<Object>>() {
                    public void onResponse(List<Object> list) {
                        if (list.size() == 0) {
                            C262519 c262519 = C262519.this;
                            Netsis.this.createEDocumentDraft(c262519.valficheRef, c262519.valficheType, new ResponseListener<EDocumentResponse>() {
                                public void onResponse(EDocumentResponse eDocumentResponse) {
                                    if (eDocumentResponse.isSuccess()) {
                                        Netsis.this.getLogoSqlHelper().updateEDocStatus(EDocStatus.Draft, C262519.this.valficheRef);
                                        C262519 c2625192 = C262519.this;
                                        Netsis.this.sendEDocumentAndReturnResponse(sendEDocumentParam, c2625192.valficheRef, c2625192.valcreateAndSendResponse, c2625192.valresponseListener);
                                        return;
                                    }
                                    C262519.this.valcreateAndSendResponse.setSuccess(false);
                                    C262519.this.valcreateAndSendResponse.setErrorDesc(eDocumentResponse.getErrorDesc());
                                    C262519 c2625193 = C262519.this;
                                    c2625193.valresponseListener.onResponse(c2625193.valcreateAndSendResponse);
                                }
                                public void onResponse(ArrayList<EDocumentResponse> obj) {

                                }
                                public void onResponse() {

                                }
                                public void onError(String str) {
                                    C262519.this.valcreateAndSendResponse.setSuccess(false);
                                    C262519.this.valcreateAndSendResponse.setErrorDesc(str);
                                    C262519 c2625192 = C262519.this;
                                    c2625192.valresponseListener.onResponse(c2625192.valcreateAndSendResponse);
                                }
                                public void onFailure(Throwable throwable) {

                                }
                                public void onResponse(Boolean aBoolean) {

                                }
                                public void onResponse(Sales sales) {

                                }
                                public void onResponse(TigerServiceResult tigerServiceResult) {

                                }
                            });
                        } else {
                            C262519 c2625192 = C262519.this;
                            Netsis.this.sendEDocumentAndReturnResponse(sendEDocumentParam, c2625192.valficheRef, c2625192.valcreateAndSendResponse, c2625192.valresponseListener);
                        }
                    }
                    public void onError(String str) {
                        C262519.this.valresponseListener.onError(str);
                    }
                    public void onFailure(Throwable throwable) {

                    }
                    public void onResponse(Boolean aBoolean) {

                    }
                    public void onResponse(Sales sales) {

                    }
                    public void onResponse(TigerServiceResult tigerServiceResult) {

                    }
                    public void onResponse(ArrayList<List<Object>> obj) {

                    }
                    public void onResponse() {

                    }
                });
                return;
            }
            this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
        }
        public void onError(String str) {
            this.valresponseListener.onError(str);
        }
        public void onFailure(Throwable throwable) {

        }
        public void onResponse(Boolean aBoolean) {

        }
        public void onResponse(Sales sales) {

        }
        public void onResponse(ArrayList<Sales> obj) {

        }
        public void onResponse() {

        }
        public void onResponse(TigerServiceResult tigerServiceResult) {

        }
    }

    
    public void createEDocumentDraftAndSend(int r9, FicheType ficheType, ResponseListener responseListener) {
        getSendCreator().getSqlManager().getSalesInvoiceExam(r9, new C262519(ficheType, r9, new EDocumentResponse(), responseListener));
    }

    
    public void sendEDocumentAndReturnResponse(SendEDocumentParam sendEDocumentParam, int r3, final EDocumentResponse eDocumentResponse, final ResponseListener responseListener) {
        sendEDocument(sendEDocumentParam, r3, new ResponseListener<EDocumentResponse>() {
            public void onResponse( PrintSlipModel eDocumentResponse2) {
                eDocumentResponse.setSuccess(eDocumentResponse2.isSuccess());
                if (!eDocumentResponse2.isSuccess()) {
                    eDocumentResponse.setErrorDesc(eDocumentResponse2.getErrorDesc());
                }
                responseListener.onResponse(eDocumentResponse);
            }
            public void onError(String str) {
                eDocumentResponse.setSuccess(false);
                eDocumentResponse.setErrorDesc(str);
                responseListener.onResponse(eDocumentResponse);
            }
            public void onFailure(Throwable throwable) { }
            public void onResponse(Boolean aBoolean) { }
            public void onResponse(Sales sales) { }
            public void onResponse(TigerServiceResult tigerServiceResult) { }
            public void onResponse(EDocumentResponse obj) { }
            public void onResponse(ArrayList<EDocumentResponse> obj) { }
            public void onResponse() {

            }
        });
    }

    private void sendEDocument(SendEDocumentParam sendEDocumentParam, final int r4, final ResponseListener responseListener) {
        final EDocumentResponse eDocumentResponse = new EDocumentResponse();
        getRestAsyncTask().getApi().sendEDocument(sendEDocumentParam).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendEDocument14(eDocumentResponse, r4, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(eDocumentResponse);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object o) throws Exception {

            }
            public Object invoke(Object obj) {
                try {
                    Netsis.lambdasendEDocument16(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
    public void lambdasendEDocument14(EDocumentResponse eDocumentResponse, int r3, DataResponse dataResponse) throws Exception {
        eDocumentResponse.setSuccess(dataResponse.isSuccessful());
        if (!dataResponse.isSuccessful()) {
            eDocumentResponse.setSuccess(false);
            eDocumentResponse.setErrorDesc(dataResponse.getErrorDesc());
            Log.d(TAG, dataResponse.getErrorDesc());
            return;
        }
        getLogoSqlHelper().updateEDocStatus(EDocStatus.Send, r3);
    }
    public static void lambdasendEDocument16(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    public void showEDocument(int r3, final FicheType ficheType, final ResponseListener responseListener) {
        getSendCreator().getSqlManager().getSalesInvoiceExam(r3, new ResponseListener<Sales>() {
            public void accept(Object o) throws Exception {

            }
            public Object invoke(Object obj) {
                return null;
            }

            public void onResponse(Sales sales) {
                Netsis.this.getEDocument(sales, ficheType, responseListener);
            }

            @Override
            public void onError(String str) {
                responseListener.onError(str);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onResponse(Boolean aBoolean) {

            }

            @Override
            public void onResponse(ArrayList<Sales> obj) {

            }

            @Override
            public void onResponse() {

            }

            @Override
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }
        });
    }

    
    public void getEDocument(Sales sales, FicheType ficheType, ResponseListener responseListener) {
        ShowEDocumentResponse showEDocumentResponse = new ShowEDocumentResponse();
        FicheType ficheType2 = FicheType.INVOICE;
        SendCreatorImpl sendCreator = getSendCreator();
        BaseServiceResult invoice = ficheType == ficheType2 ? sendCreator.getInvoice(sales) : (SendCreatorImpl) sendCreator.getDispatch(sales);
        if (invoice != null && invoice.getSendData() != null) {
            ItemSlip itemSlip = (ItemSlip) invoice.getSendData();
            itemSlip.getSlipHeader().setSlipNo(sales.getFicheNo());
            getRestAsyncTask().getSelectRx(this.mQueryCreator.getEDocumentShowInfo(itemSlip, ficheType == ficheType2 && !itemSlip.getSlipHeader().iseInvoiceCustomer()), new C262922(showEDocumentResponse, responseListener, ficheType, itemSlip));
            return;
        }
        responseListener.onError(ContextUtils.getStringResource(R.string.str_fiche_delete_erp));
    }

    class C262922 implements ResponseListener<List<Object>> {
        final FicheType valficheType;
        final ItemSlip valitemSlip;
        final ResponseListener valresponseListener;
        final ShowEDocumentResponse valshowEDocumentResponse;

        C262922(ShowEDocumentResponse showEDocumentResponse, ResponseListener responseListener, FicheType ficheType, ItemSlip itemSlip) {
            this.valshowEDocumentResponse = showEDocumentResponse;
            this.valresponseListener = responseListener;
            this.valficheType = ficheType;
            this.valitemSlip = itemSlip;
        }
        public void onResponse( PrintSlipModel list) {
            EDocumentType eDocumentType;
            if (list.size() == 0) {
                this.valshowEDocumentResponse.setSuccess(false);
                this.valshowEDocumentResponse.setErrorDesc(ContextUtils.getStringResource(R.string.str_edoc_draft_not_foud));
                this.valresponseListener.onResponse(this.valshowEDocumentResponse);
                return;
            }
            final ShowEDocumentParam showEDocumentParam = (ShowEDocumentParam) list.get(0);
            showEDocumentParam.setDocumentBoxType(DocumentBoxType.ebOutbox);
            if (this.valficheType == FicheType.INVOICE) {
                eDocumentType = this.valitemSlip.getSlipHeader().iseInvoiceCustomer() ? EDocumentType.ebtEFatura : EDocumentType.ebtArsiv;
            } else {
                eDocumentType = EDocumentType.ebtEIrs;
            }
            showEDocumentParam.seteDocumentType(eDocumentType);
            Observable<DataResponse> observableShowEDocument = Netsis.this.getRestAsyncTask().getApi().showEDocument(showEDocumentParam);
            final ShowEDocumentResponse showEDocumentResponse = this.valshowEDocumentResponse;
            Observable<DataResponse> observableSubscribeOn = observableShowEDocument.doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                    Netsis.C262922.lambdaonResponse0(showEDocumentResponse, showEDocumentParam, (DataResponse) obj);
                }
                public Object invoke(Object obj) {
                    return null;
                }
            }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
            final ResponseListener responseListener = this.valresponseListener;
            final ShowEDocumentResponse showEDocumentResponse2 = this.valshowEDocumentResponse;
            Consumer<? super DataResponse> consumer = new Consumer() {
                public void accept(Object o) throws Exception {

                }
                public Object invoke(Object obj) {
                    return null;
                }

                public   void accept(DataResponse obj) {
                    responseListener.onResponse(showEDocumentResponse2);
                }
            };
            final ResponseListener responseListener2 = this.valresponseListener;
            observableSubscribeOn.subscribe(consumer, new Consumer() {
                public void accept(Throwable obj) throws Exception {
                    Netsis.C262922.lambdaonResponse2(responseListener2, obj);
                }
                public void accept(Object o) throws Exception {

                }
                public Object invoke(Object obj) {
                    return null;
                }
            });
        }
        public static void lambdaonResponse0(ShowEDocumentResponse showEDocumentResponse, ShowEDocumentParam showEDocumentParam, DataResponse dataResponse) throws Exception {
            String str;
            showEDocumentResponse.setSuccess(dataResponse.isSuccessful());
            showEDocumentResponse.setEttn(showEDocumentParam.getEttn());
            if (!dataResponse.isSuccessful()) {
                showEDocumentResponse.setSuccess(false);
                showEDocumentResponse.setErrorDesc(dataResponse.getErrorDesc());
                Log.d(Netsis.TAG, dataResponse.getErrorDesc());
            } else if (dataResponse.getData() != null) {
                try {
                    String strReplace = dataResponse.getData().toString().replace("\r\n", "").replace(SqlLiteVariable._NEW_LINE, "").replace("\"", "");
                    if (strReplace.indexOf("charset=windows-1254") != -1) {
                        str = strReplace.replace("charset=windows-1254", "charset=utf-8");
                    } else {
                        str = new String(strReplace.getBytes("Cp1254"), StandardCharsets.UTF_8);
                    }
                    showEDocumentResponse.setHtmlContent(str);
                } catch (Exception e2) {
                    showEDocumentResponse.setSuccess(false);
                    showEDocumentResponse.setErrorDesc(ContextUtils.getStringResource(R.string.str_get_print_value_error));
                    Log.e(Netsis.TAG, "EDocument Html", e2);
                }
            }
        }

        
        public static void lambdaonResponse2(ResponseListener responseListener, Throwable th) throws Exception {
            responseListener.onError(handleNetworkError(th));
        }
        public void onError(String str) {  this.valresponseListener.onError(str);  }
        public void onFailure(Throwable throwable) {  }
        public void onResponse(Boolean aBoolean) { }
        public void onResponse(Sales sales) { }
        public void onResponse(TigerServiceResult tigerServiceResult) {  }
        public void onResponse(List<Object> obj) {  }
        public void onResponse(ArrayList<List<Object>> obj) {  }
        public void onResponse() { }
    }

    public void readInvoiceFiche(String str, Sales sales, ResponseListener responseListener) {
        getRestAsyncTask().readInvoiceFiche(str, sales, responseListener);
    }

    
    public boolean isDeviceInUseByAnotherUser() {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (!netsisSelectResultRunSelect.isSuccess()) {
                return false;
            }
            String deviceSerialNo = ContextUtils.getDeviceSerialNo();
            if (netsisSelectResultRunSelect.getDataList().size() == 0) {
                return true;
            }
            for (Object deviceId : netsisSelectResultRunSelect.getDataList()) {
                if (!TextUtils.isEmpty(deviceId.deviceSerialNo1) && !getUserSettings().isDemo() && !deviceId.deviceSerialNo1.equals(deviceSerialNo)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "isDeviceInUseByAnotherUser", e2);
            return false;
        }
    }

    
    public void getUsableCampaignCards(Sales sales, ResponseListener responseListener) {
        responseListener.onResponse();
    }
    public Disposable getMixedReceiptPrintValues(MixedReceiptsMainParam mixedReceiptsMainParam, final CashCreditFiche cashCreditFiche, final ResponseListener responseListener) {
        getRestAsyncTask().getPrintMixedReceiptsInfo(mixedReceiptsMainParam, new ResponseListener<PrintMixedReceiptModel>() {
            public void onResponse(  PrintSlipModel printMixedReceiptModel) {
                if (printMixedReceiptModel != null && printMixedReceiptModel.getMixedReceiptsMain() != null && printMixedReceiptModel.getMixedReceiptsMain().getTahsilats() != null) {
                    printMixedReceiptModel.getMixedReceiptsMain().setTime(DateAndTimeUtils.getTimeOnly(cashCreditFiche.getgDate()));
                    printMixedReceiptModel.getMixedReceiptsMain().setBankCode(cashCreditFiche.getBank().getCode());
                    printMixedReceiptModel.getMixedReceiptsMain().setBankDescription(cashCreditFiche.getBank().getDefinition());
                    printMixedReceiptModel.getMixedReceiptsMain().setSpeCode(cashCreditFiche.getSpecode().getDefinition());
                    Iterator<MixedReceipt> it = printMixedReceiptModel.getMixedReceiptsMain().getTahsilats().iterator();
                    while (it.hasNext()) {
                        it.next().setDocNo(cashCreditFiche.getDetails().get(0).getDocNo().getDefinition());
                    }
                    responseListener.onResponse(printMixedReceiptModel);
                    return;
                }
                responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
            }
            public void onError(String str) {
                responseListener.onError(str);
            }
            public void onFailure(Throwable throwable) {  }
            public void onResponse(Boolean aBoolean) { }
            public void onResponse(Sales sales) { }
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }

            @Override
            public void onResponse(PrintMixedReceiptModel obj) {

            }

            @Override
            public void onResponse(ArrayList<PrintMixedReceiptModel> obj) {

            }

            @Override
            public void onResponse() {

            }
        });
        return null;
    }

    
    public void getMixedReceiptPrintValuesOffline(final MixedReceiptsMain mixedReceiptsMain, CashCreditFiche cashCreditFiche, final ResponseListener responseListener) {
        final int customerClRef = getCustomerClRef(mixedReceiptsMain.getCariKod());
        getSendCreator().getSqlManager().getCustomer(customerClRef, new ResponseListener<CustomerNew>() { // from class: com.proje.mobilesales.core.design.Netsis.24
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel customerNew) {
                CariTemelBilgi cariTemelBilgi = new CariTemelBilgi(customerNew);
                cariTemelBilgi.setCardType(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
                CariEkBilgi cariEkBilgi = new CariEkBilgi();
                cariEkBilgi.setCode(customerNew.getCode().toString());
                cariEkBilgi.setOdekod(customerNew.getPayPlan().toString());
                cariEkBilgi.setTckimlikno(customerNew.getTCNo().toString());
                Cari cari = new Cari();
                ClCard customerInfo = Netsis.this.getCustomerInfo(customerClRef);
                if (customerInfo != null) {
                    cariTemelBilgi.setDebit(customerInfo.getDebit());
                    cariTemelBilgi.setCredit(customerInfo.getCredit());
                    cariTemelBilgi.setSpecode(customerInfo.getSpecode());
                }
                cari.setCariTemelBilgi(cariTemelBilgi);
                cari.setCariEkBilgi(cariEkBilgi);
                PrintExtraInfo printExtraInfo = new PrintExtraInfo();
                printExtraInfo.setBranchCode(Netsis.this.getUser().getPeridodNrInt());
                printExtraInfo.setEnterpriceCode(StringUtils.convertStringToInt(Netsis.this.getUser().getFirmNr()));
                printExtraInfo.setPlasiyerName(Netsis.this.getUser().getName());
                responseListener.onResponse(new PrintMixedReceiptModel(mixedReceiptsMain, cari, List.of(printExtraInfo)));
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(str);
            }
        });
    }

    public Disposable getCheckAndPNotesPrintValues(CheckAndPNotesMainParam checkAndPNotesMainParam, final ChequeDeedFiche chequeDeedFiche, final ResponseListener responseListener) {
        getRestAsyncTask().getPrintCheckAndPNotesInfo(checkAndPNotesMainParam, new ResponseListener<PrintCheckAndPNotesModel>() { // from class: com.proje.mobilesales.core.design.Netsis.25
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel printCheckAndPNotesModel) {
                if (printCheckAndPNotesModel != null && printCheckAndPNotesModel.getNetsisChequeAndDeedMain() != null && printCheckAndPNotesModel.getNetsisChequeAndDeedMain().getEvraklar() != null) {
                    printCheckAndPNotesModel.setChequeDeed(chequeDeedFiche);
                    responseListener.onResponse(printCheckAndPNotesModel);
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                }
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(str);
            }
        });
        return null;
    }

    
    public void getCheckAndPNotesPrintValuesOffline(final NetsisChequeAndDeedMain netsisChequeAndDeedMain, final ChequeDeedFiche chequeDeedFiche, final ResponseListener responseListener) {
        final int customerClRef = getCustomerClRef(chequeDeedFiche.getClCode());
        getSendCreator().getSqlManager().getCustomer(customerClRef, new ResponseListener<CustomerNew>() { // from class: com.proje.mobilesales.core.design.Netsis.26
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel customerNew) {
                CariTemelBilgi cariTemelBilgi = new CariTemelBilgi(customerNew);
                cariTemelBilgi.setCardType(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
                CariEkBilgi cariEkBilgi = new CariEkBilgi();
                cariEkBilgi.setCode(customerNew.getCode().toString());
                cariEkBilgi.setOdekod(customerNew.getPayPlan().toString());
                cariEkBilgi.setTckimlikno(customerNew.getTCNo().toString());
                Cari cari = new Cari();
                ClCard customerInfo = Netsis.this.getCustomerInfo(customerClRef);
                if (customerInfo != null) {
                    cariTemelBilgi.setDebit(customerInfo.getDebit());
                    cariTemelBilgi.setCredit(customerInfo.getCredit());
                    cariTemelBilgi.setSpecode(customerInfo.getSpecode());
                }
                cari.setCariTemelBilgi(cariTemelBilgi);
                cari.setCariEkBilgi(cariEkBilgi);
                PrintExtraInfo printExtraInfo = new PrintExtraInfo();
                printExtraInfo.setBranchCode(Netsis.this.getUser().getPeridodNrInt());
                printExtraInfo.setEnterpriceCode(StringUtils.convertStringToInt(Netsis.this.getUser().getFirmNr()));
                printExtraInfo.setPlasiyerName(Netsis.this.getUser().getName());
                responseListener.onResponse(new PrintCheckAndPNotesModel(netsisChequeAndDeedMain, cari, chequeDeedFiche, List.of(printExtraInfo)));
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(str);
            }
        });
    }

    public Disposable getSafeDepositPrintValues(SafeDepositParam safeDepositParam, final CaseFiche caseFiche, final ResponseListener responseListener) {
        getRestAsyncTask().getPrintSafeDepositInfo(safeDepositParam, new ResponseListener<PrintSafeDepositModel>() {
            public void onResponse( PrintSlipModel printSafeDepositModel) {
                if (printSafeDepositModel != null) {
                    printSafeDepositModel.setCaseFiche(caseFiche);
                    responseListener.onResponse(printSafeDepositModel);
                } else {
                    responseListener.onError(ContextUtils.getStringResource(R.string.str_get_print_value_not_found));
                }
            }
            public void onError(String str) {
                responseListener.onError(str);
            }
            public void onFailure(Throwable throwable) {  }
            public void onResponse(Boolean aBoolean) {  }
            public void onResponse(Sales sales) {  }
            public void onResponse(TigerServiceResult tigerServiceResult) { }
            public void onResponse(PrintSafeDepositModel obj) { }
            public void onResponse(ArrayList<PrintSafeDepositModel> obj) { }
            public void onResponse() { }
        });
        return null;
    }

    
    public void getSafeDepositPrintValuesOffline(final SafeDeposit safeDeposit, final CaseFiche caseFiche, final ResponseListener responseListener) {
        final int customerClRef = getCustomerClRef(caseFiche.getClCode());
        getSendCreator().getSqlManager().getCustomer(customerClRef, new ResponseListener<CustomerNew>() {
            public void onResponse( PrintSlipModel customerNew) {
                CariTemelBilgi cariTemelBilgi = new CariTemelBilgi(customerNew);
                cariTemelBilgi.setCardType(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
                CariEkBilgi cariEkBilgi = new CariEkBilgi();
                cariEkBilgi.setCode(customerNew.getCode().toString());
                cariEkBilgi.setOdekod(customerNew.getPayPlan().toString());
                cariEkBilgi.setTckimlikno(customerNew.getTCNo().toString());
                Cari cari = new Cari();
                ClCard customerInfo = Netsis.this.getCustomerInfo(customerClRef);
                if (customerInfo != null) {
                    cariTemelBilgi.setDebit(customerInfo.getDebit());
                    cariTemelBilgi.setCredit(customerInfo.getCredit());
                    cariTemelBilgi.setSpecode(customerInfo.getSpecode());
                }
                cari.setCariTemelBilgi(cariTemelBilgi);
                cari.setCariEkBilgi(cariEkBilgi);
                PrintExtraInfo printExtraInfo = new PrintExtraInfo();
                printExtraInfo.setBranchCode(Netsis.this.getUser().getPeridodNrInt());
                printExtraInfo.setEnterpriceCode(StringUtils.convertStringToInt(Netsis.this.getUser().getFirmNr()));
                printExtraInfo.setPlasiyerName(Netsis.this.getUser().getName());
                responseListener.onResponse(new PrintSafeDepositModel(safeDeposit, cari, caseFiche, List.of(printExtraInfo)));
            }
            public void onError(String str) {
                responseListener.onError(str);
            }
            public void onFailure(Throwable throwable) { }
            public void onResponse(Boolean aBoolean) {  }
            public void onResponse(Sales sales) {

            }

            @Override
            public void onResponse(TigerServiceResult tigerServiceResult) {

            }

            @Override
            public void onResponse(CustomerNew obj) {

            }

            @Override
            public void onResponse(ArrayList<CustomerNew> obj) {

            }

            @Override
            public void onResponse() {

            }
        });
    }

    
    public void getProductDetails(final int r11, boolean z, final ResponseListener responseListener) {
        final Observable<ArrayList<ProductDetail>> productDetailObservable = getSendCreator().getSqlManager().getProductDetailObservable(getLogoSqlHelper().getProductDetailInfoSql(r11, z));
        final Observable<ArrayList<ProductDetail.Ambar>> productWareHouseDetailObservable = getSendCreator().getSqlManager().getProductWareHouseDetailObservable(getLogoSqlHelper().getProductDetailStockSql(r11, z));
        final Observable<ArrayList<ProductDetail.ItemPrice>> productPriceDetailObservable = getSendCreator().getSqlManager().getProductPriceDetailObservable(getLogoSqlHelper().getProductDetailPriceSql(r11));
        final Observable<ArrayList<ProductDetail.DetailItemUnit>> productUnitDetailObservable = getSendCreator().getSqlManager().getProductUnitDetailObservable(getLogoSqlHelper().getProductDetailUnitSql(r11, z));
        final Observable<R> observableFlatMap = BaseErp.isInternetOn().flatMap(new Function() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public Object apply(Object obj) {
                return this.f0.lambdagetProductDetails18(r11, (Boolean) obj);
            }
        });
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable observableSubscribeOn = Observable.defer(new Callable() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public Object call() {
                return this.f0.lambdagetProductDetails20(productDetailObservable, productWareHouseDetailObservable, productPriceDetailObservable, productUnitDetailObservable, observableFlatMap, responseListener);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.m474io());
        Objects.requireNonNull(responseListener);
        compositeDisposable.add(observableSubscribeOn.subscribe(new NetsisExternalSyntheticLambda6(responseListener), new Consumer() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                Netsis.lambdagetProductDetails21(responseListener, (Throwable) obj);
            }
        }));
    }

    
    public ObservableSource lambdagetProductDetails18(int r1, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return getRestAsyncTask().getApi().getQueriesRx(new NetsisTSql(this.mQueryCreator.getProductTopTenCustomer(getItemCode(r1)).getSql())).onErrorReturn(new Function() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda3
                @Override // io.reactivex.functions.Function
                public Object apply(Object obj) {
                    return Netsis.lambdagetProductDetails17((Throwable) obj);
                }
            });
        }
        return Observable.just(new NetsisDataHeader());
    }

    
    public static NetsisDataHeader lambdagetProductDetails17(Throwable th) throws Exception {
        return new NetsisDataHeader();
    }

    
    public ObservableSource lambdagetProductDetails20(Observable observable, Observable observable2, Observable observable3, Observable observable4, Observable observable5, final ResponseListener responseListener) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), observable4.subscribeOn(Schedulers.newThread()), observable5.subscribeOn(Schedulers.newThread()), new Function5() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda0
            @Override // io.reactivex.functions.Function5
            public Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                return this.f0.lambdagetProductDetails19(responseListener, (ArrayList) obj, (ArrayList) obj2, (ArrayList) obj3, (ArrayList) obj4, (NetsisDataHeader) obj5);
            }
        });
    }

    
    public ArrayList lambdagetProductDetails19(ResponseListener responseListener, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, NetsisDataHeader netsisDataHeader) throws Exception {
        List listData = getRestAsyncTask().toListData(TopProduct.class, netsisDataHeader.getData());
        if (arrayList.isEmpty()) {
            responseListener.onError(ContextUtils.getStringResource(R.string.str_data_not_found));
        } else {
            ProductDetail productDetail = (ProductDetail) arrayList.get(0);
            productDetail.setTopProducts((ArrayList) listData);
            productDetail.setAmbar(arrayList2);
            productDetail.setItemPrices(arrayList3);
            productDetail.setItemUnits(arrayList4);
        }
        return arrayList;
    }

    
    public static void lambdagetProductDetails21(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    
    public void getProductDetailCharts(int r2, final ResponseListener responseListener) {
        getProductTopTenCustomer(r2, new ResponseListener<List<TopProduct>>() { // from class: com.proje.mobilesales.core.design.Netsis.29
            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onResponse(@Nullable @UnknownNullability PrintSlipModel list) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setTopProducts((ArrayList) list);
                responseListener.onResponse(productDetail);
            }

            @Override // com.proje.mobilesales.core.interfaces.ResponseListener
            public void onError(String str) {
                responseListener.onError(str);
            }
        });
    }

    
    public int getDateInt() {
        return DateAndTimeUtils.getNetsisDateInt();
    }
    class C263830 implements ResponseListener<List<Object>> {
        final String valcustomerCode;
        final String valorderNumber;
        final ResponseListener valresponseListener;
        final NetsisSelectResult valselectResult;

        
        public static NetsisSelectResult lambdaonResponse2(NetsisSelectResult netsisSelectResult, NetsisDataHeader netsisDataHeader) throws Exception {
            return netsisSelectResult;
        }

        C263830(ResponseListener responseListener, String str, NetsisSelectResult netsisSelectResult, String str2) {
            this.valresponseListener = responseListener;
            this.valorderNumber = str;
            this.valselectResult = netsisSelectResult;
            this.valcustomerCode = str2;
        }

        @Override // com.proje.mobilesales.core.interfaces.ResponseListener
        public void onResponse(@Nullable @UnknownNullability PrintSlipModel list) {
            if (list == null || list.size() == 0) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            List list2 = (List) list.stream().map(new java.util.function.Function() { // from class: com.proje.mobilesales.core.design.Netsis30ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public Object apply(Object obj) {
                    return Netsis.C263830.lambdaonResponse0(obj);
                }
            }).filter(new java.util.function.Predicate() { // from class: com.proje.mobilesales.core.design.Netsis30ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public boolean test(Object obj) {
                    return Netsis.C263830.lambdaonResponse1((DeviceId) obj);
                }
            }).collect(Collectors.toList());
            if ((list2 == null || list2.isEmpty()) && !Netsis.this.getUserSettings().isDemo()) {
                this.valresponseListener.onError(ContextUtils.getStringResource(R.string.str_device_use_other_logout));
                return;
            }
            Observable<NetsisDataHeader> queriesRx = Netsis.this.getRestAsyncTask().getApi().getQueriesRx(new NetsisTSql(Netsis.this.mQueryCreator.updateOrderStatus(new ArrayList<String>() { // from class: com.proje.mobilesales.core.design.Netsis.30.1
                {
                    add(C263830.this.valorderNumber);
                }
            }, NetsisInvoiceType.ft_Kapali).getSql()));
            final NetsisSelectResult netsisSelectResult = this.valselectResult;
            Observable<R> map = queriesRx.map(new Function() { // from class: com.proje.mobilesales.core.design.Netsis30ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Function
                public Object apply(Object obj) {
                    return Netsis.C263830.lambdaonResponse2(netsisSelectResult, (NetsisDataHeader) obj);
                }
            });
            final NetsisSelectResult netsisSelectResult2 = this.valselectResult;
            final String str = this.valorderNumber;
            final String str2 = this.valcustomerCode;
            Observable observableObserveOn = map.doOnNext(new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis30ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    this.f0.lambdaonResponse3(netsisSelectResult2, str, str2, (NetsisSelectResult) obj);
                }
            }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).subscribeOn(Schedulers.m474io()).observeOn(AndroidSchedulers.mainThread());
            final ResponseListener responseListener = this.valresponseListener;
            Consumer consumer = new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis30ExternalSyntheticLambda4
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    Netsis.C263830.lambdaonResponse4(responseListener, (NetsisSelectResult) obj);
                }
            };
            final ResponseListener responseListener2 = this.valresponseListener;
            observableObserveOn.subscribe(consumer, new Consumer() { // from class: com.proje.mobilesales.core.design.Netsis30ExternalSyntheticLambda5
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    Netsis.C263830.lambdaonResponse5(responseListener2, (Throwable) obj);
                }
            });
        }

        
        public static DeviceId lambdaonResponse0(Object obj) {
            return (DeviceId) obj;
        }

        
        public static boolean lambdaonResponse1(DeviceId deviceId) {
            return deviceId != null && !TextUtils.isEmpty(deviceId.deviceSerialNo1) && deviceId.deviceSerialNo1.equalsIgnoreCase(ContextUtils.getDeviceSerialNo());
        }

        
        public void lambdaonResponse3(NetsisSelectResult netsisSelectResult, String str, String str2, NetsisSelectResult netsisSelectResult2) throws Exception {
            if (netsisSelectResult.isSuccess()) {
                NetsisSelectResult netsisSelectResultUpdateOrderLinesAsRejected = ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().updateOrderLinesAsRejected(str, str2);
                netsisSelectResultUpdateOrderLinesAsRejected.setProcessType(ProcessType.INSERTWORPROCESS);
                Netsis.this.getRestAsyncTask().runInsert(netsisSelectResultUpdateOrderLinesAsRejected);
                netsisSelectResult.setSuccess(netsisSelectResultUpdateOrderLinesAsRejected.isSuccess());
            }
        }

        
        public static void lambdaonResponse4(ResponseListener responseListener, NetsisSelectResult netsisSelectResult) throws Exception {
            responseListener.onResponse(Boolean.valueOf(netsisSelectResult.isSuccess()));
        }

        
        public static void lambdaonResponse5(ResponseListener responseListener, Throwable th) throws Exception {
            responseListener.onError(th.getMessage());
        }
        public void onError(String str) {
            this.valresponseListener.onError(str);
        }
        public void onFailure(Throwable throwable) {}
        public void onResponse(Boolean aBoolean) {  }
        public void onResponse(Sales sales) {  }
        public void onResponse(TigerServiceResult tigerServiceResult) {  }
        public void onResponse(List<Object> obj) {  }
        public void onResponse(ArrayList<List<Object>> obj) {  }
        public void onResponse() {  }
    }

    
    public void rejectOrder(String str, String str2, ResponseListener responseListener) {
        NetsisSelectResult deviceId = this.mQueryCreator.getDeviceId();
        getRestAsyncTask().getSelectRx(deviceId, new C263830(responseListener, str, deviceId, str2));
    }

    public ValidationResult controlSecondUnitConversions(Sales sales) {
        ItemSlip itemSlip;
        ValidationResult validationResult = new ValidationResult();
        if (sales != null) {
            try {
                if (!sales.getMSalesDetailList().isEmpty() && getLogoSqlHelper().getLogoParamValue("OLCUBIRIMITABLODAN").equals("D") && (itemSlip = (ItemSlip) getSendCreator().getInvoice(sales).getSendData()) != null && itemSlip.getLines() != null && !itemSlip.getLines().isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (ItemSlipLine itemSlipLine : itemSlip.getLines()) {
                        if (itemSlipLine != null && itemSlipLine.getOlcubr() != 1 && (itemSlipLine.getBrCevrim1() > 0.0d || itemSlipLine.getBrCevrim2() > 0.0d)) {
                            if (itemSlipLine.getCEVRIM() <= 0.0d) {
                                validationResult.setSuccess(false);
                                sb.append(String.format("%1s kodlu ürünün miktar bilgileri hatalı", itemSlipLine.getStokKodu()));
                                sb.append(System.getProperty("line.separator"));
                            }
                        }
                    }
                    validationResult.setErrorMessage(sb.toString());
                }
                return validationResult;
            } catch (Exception e2) {
                Log.e(TAG, "conversionValidationError", e2);
            }
        }
        return validationResult;
    }

    
    public List<NotificationModel> getUserNotificationsToBeNotified() {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            return netsisSelectResultRunSelect.isSuccess() ? netsisSelectResultRunSelect.getDataList() : new ArrayList();
        } catch (Exception e2) {
            Log.e(TAG, "geUserNotificationsToBeNotified", e2);
            return new ArrayList();
        }
    }

    
    public List<NotificationModel> getUserNotifications(int r2, int r3, NotificationFilterModel notificationFilterModel) {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            return netsisSelectResultRunSelect.isSuccess() ? netsisSelectResultRunSelect.getDataList() : new ArrayList();
        } catch (Exception e2) {
            Log.e(TAG, "geUserNotifications", e2);
            return new ArrayList();
        }
    }

    
    public BaseSelectResult deleteNotification(NotificationModel notificationModel) {
        try {
            BaseSelectResult baseSelectResultCheckNotificationAvailableToDelete = checkNotificationAvailableToDelete(notificationModel);
            return !baseSelectResultCheckNotificationAvailableToDelete.isSuccess() ? baseSelectResultCheckNotificationAvailableToDelete : getRestAsyncTask().runInsert(this.mQueryCreator.deleteNotification(notificationModel));
        } catch (Exception e2) {
            Log.e(TAG, "deleteNotification", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public BaseSelectResult checkNotificationAvailableToDelete(NotificationModel notificationModel) {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (!netsisSelectResultRunSelect.isSuccess()) {
                return netsisSelectResultRunSelect;
            }
            if (netsisSelectResultRunSelect.getDataList() != null && netsisSelectResultRunSelect.getDataList().size() != 0 && (netsisSelectResultRunSelect.getDataList().get(0) instanceof KeyValuePair)) {
                if (Integer.parseInt(((KeyValuePair) netsisSelectResultRunSelect.getDataList().get(0)).getValue()) == 0) {
                    return netsisSelectResultRunSelect;
                }
                netsisSelectResultRunSelect.setSuccess(false);
                netsisSelectResultRunSelect.setErrorResourceId(R.string.str_notification_not_available_toDelete);
                return netsisSelectResultRunSelect;
            }
            netsisSelectResultRunSelect.setSuccess(false);
            netsisSelectResultRunSelect.setErrorResourceId(R.string.str_data_not_found);
            return netsisSelectResultRunSelect;
        } catch (Exception e2) {
            Log.e(TAG, "checkNotificationAvailableToDelete", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public List<NotifiedUserModel> getNotificationDetailsForSender(int r2) {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            return netsisSelectResultRunSelect.isSuccess() ? netsisSelectResultRunSelect.getDataList() : new ArrayList();
        } catch (Exception e2) {
            Log.e(TAG, "getNotificationDetailsForSender", e2);
            return new ArrayList();
        }
    }

    
    public NotificationModel getNotification(int r2) {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (netsisSelectResultRunSelect.isSuccess()) {
                return netsisSelectResultRunSelect.getDataList().isEmpty() ? new NotificationModel() : (NotificationModel) netsisSelectResultRunSelect.getDataList().get(0);
            }
            return new NotificationModel();
        } catch (Exception e2) {
            Log.e(TAG, "getNotification", e2);
            return new NotificationModel();
        }
    }

    
    public BaseSelectResult updateNotifiedUserNotificationAsRead(int r2) {
        try {
            return getRestAsyncTask().runInsert(this.mQueryCreator.updateNotifiedUserNotificationAsRead(r2));
        } catch (Exception e2) {
            Log.e(TAG, "updateNotifiedUserNotificationAsRead", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public TigerSelectResult updateNotifiedUserNotificationAsDelivered(int r2) {
        try {
            return getRestAsyncTask().runInsert(this.mQueryCreator.updateNotifiedUserNotificationAsDelivered(r2));
        } catch (Exception e2) {
            Log.e(TAG, "updateNotifiedUserNotificationAsDelivered", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public NotifiedUserModel getNotifiedUser(int r2) {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (netsisSelectResultRunSelect.isSuccess()) {
                return netsisSelectResultRunSelect.getDataList().isEmpty() ? new NotifiedUserModel() : (NotifiedUserModel) netsisSelectResultRunSelect.getDataList().get(0);
            }
            return new NotifiedUserModel();
        } catch (Exception e2) {
            Log.e(TAG, "getNotifiedUser", e2);
            return new NotifiedUserModel();
        }
    }

    
    public BaseSelectResult updateNotificationAsReadIfAllUsersRead(String str) {
        try {
            return getRestAsyncTask().runInsert(this.mQueryCreator.updateNotificationAsReadIfAllUsersRead(str));
        } catch (Exception e2) {
            Log.e(TAG, "updateNotificationAsReadIfAllUsersRead", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public List<NotificationUserSelectionModel> getUsersConnectedToMe(String str) {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            return netsisSelectResultRunSelect.isSuccess() ? netsisSelectResultRunSelect.getDataList() : new ArrayList();
        } catch (Exception e2) {
            Log.e(TAG, "getUsersConnectedToMe", e2);
            return new ArrayList();
        }
    }

    
    public NetsisSelectResult updateNotificationAsDeliveredIfAllDelivered(String str) {
        try {
            return getRestAsyncTask().runInsert(this.mQueryCreator.updateNotificationAsDeliveredIfAllDelivered(str));
        } catch (Exception e2) {
            Log.e(TAG, "updateNotificationAsDeliveredIfAllDelivered", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public BaseSelectResult saveNotificationAndUsers(NotificationModel notificationModel, List list) {
        try {
            NetsisSelectResult netsisSelectResultRunInsert = getRestAsyncTask().runInsert(this.mQueryCreator.saveNotification(notificationModel));
            if (netsisSelectResultRunInsert.isSuccess()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    netsisSelectResultRunInsert = getRestAsyncTask().runInsert(this.mQueryCreator.saveNotifiedUsers((NotifiedUserModel) it.next()));
                    if (!netsisSelectResultRunInsert.isSuccess()) {
                        return netsisSelectResultRunInsert;
                    }
                }
            }
            return netsisSelectResultRunInsert;
        } catch (Exception e2) {
            Log.e(TAG, "saveNotification", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public NotificationModel getNotificationByGuid(String str) {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (netsisSelectResultRunSelect.isSuccess() && !netsisSelectResultRunSelect.getDataList().isEmpty()) {
                return (NotificationModel) netsisSelectResultRunSelect.getDataList().get(0);
            }
            return null;
        } catch (Exception e2) {
            Log.e(TAG, "getNotificationByGuid", e2);
            return null;
        }
    }

    
    public int isServerTimeInWorkingHours() {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (!netsisSelectResultRunSelect.isSuccess()) {
                return -1;
            }
            if (netsisSelectResultRunSelect.getDataList() != null && netsisSelectResultRunSelect.getDataList().size() != 0) {
                if (netsisSelectResultRunSelect.getDataList().get(0) instanceof KeyValuePair) {
                    return Integer.parseInt(((KeyValuePair) netsisSelectResultRunSelect.getDataList().get(0)).getValue());
                }
                return -1;
            }
            return 0;
        } catch (Exception e2) {
            Log.e(TAG, "isServerTimeInWorkingHours", e2);
            return -1;
        }
    }

    
    public BaseSelectResult loginFromPeriodicWorker() {
        try {
            return getRestAsyncTask().lambdagetSelectRxn24();
        } catch (Exception e2) {
            Log.e(TAG, "loginFromPeriodicWorker", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public BaseSelectResult executeLoginFlow() {
        try {
            NetsisSelectResult netsisSelectResultRunSelect = getRestAsyncTask().lambdagetSelectRxn24();
            if (!netsisSelectResultRunSelect.isSuccess()) {
                return netsisSelectResultRunSelect;
            }
            if (netsisSelectResultRunSelect.getDataList().size() == 0) {
                netsisSelectResultRunSelect.setSuccess(ContextUtils.getStringResource(R.string.exp_5_user_not_found));
                return netsisSelectResultRunSelect;
            }
            getWorTables();
            if (isDeviceInUseByAnotherUser()) {
                netsisSelectResultRunSelect.setSuccess(ContextUtils.getStringResource(R.string.exp_4_device_use_other));
            }
            return netsisSelectResultRunSelect;
        } catch (Exception e2) {
            Log.e(TAG, "executeLoginFlow", e2);
            return NetsisSelectResult.createErrorResult(e2.getMessage());
        }
    }

    
    public String getLoadFicheDefaultCaseFirmNr() {
        return getUser().getPeridodNr();
    }

    
    public void loadFicheDefaultProjectCode(FicheDiscountRefProp ficheDiscountRefProp, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List table = getLogoSqlHelper().getTable(Project.class, "CODE=?", new String[]{str});
        if (table.isEmpty()) {
            return;
        }
        ficheDiscountRefProp.setLogicalRef(((Project) table.get(0)).getLogicalRef());
        FicheStringProp.setDefinition(((Project) table.get(0)).getProje());
        ficheDiscountRefProp.setCode(((Project) table.get(0)).getCode());
    }

    
    public void loadFicheDefaultProjectCode(FicheRefProp ficheRefProp, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List table = getLogoSqlHelper().getTable(Project.class, "CODE=?", new String[]{str});
        if (table.isEmpty()) {
            return;
        }
        ficheRefProp.setLogicalRef(((Project) table.get(0)).getLogicalRef());
        FicheStringProp.setDefinition(((Project) table.get(0)).getProje());
    }

    
    public Observable<Resource<Boolean>> customerHasDailyOperation(int r2) {
        final String customerClCode = getCustomerClCode(r2);
        return Observable.create(new ObservableOnSubscribe() { // from class: com.proje.mobilesales.core.design.NetsisExternalSyntheticLambda9
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter observableEmitter) throws Exception {
                this.f0.lambdacustomerHasDailyOperation22(customerClCode, observableEmitter);
            }
        });
    }

    
    public void lambdacustomerHasDailyOperation22(String str, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(new Resource.Loading());
        Cursor cursorQuery = null;
        boolean z = false;
        try {
            try {
                cursorQuery = this.logoSqlBriteDatabase.query(("SELECT * FROM (" + ContextUtils.getStringResource(R.string.net_get_isCustomerHasDoneOperationToday) + ")") + " WHERE CLCODE = '" + str + "'");
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    if (cursorQuery.getInt(cursorQuery.getColumnIndex("OPERATIONSTODAY")) > 0) {
                        z = true;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                observableEmitter.onError(e2);
                if (cursorQuery != null && !cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
            }
            observableEmitter.onNext(new Resource.Success(Boolean.valueOf(z)));
        } finally {
            if (cursorQuery != null && !cursorQuery.isClosed()) {
                cursorQuery.close();
            }
        }
    }
    public void getSerialLotList(SerialLotListQueryParam serialLotListQueryParam, ResponseListener responseListener) {
        try {
            getRestAsyncTask().lambdagetSelectRxn24();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getMainUnitCode(String str) {
        List table = getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=? AND LINENR=? ", new String[]{str, "1"});
        return (table == null || table.isEmpty()) ? "" : ((ItemUnits) table.get(0)).code;
    }
    public int insertWorProcess(VisitInfo visitInfo) {
        NetsisSelectResult netsisSelectResultInsertVisit = getQueryCreator().insertVisit(visitInfo);
        try {
            getRestAsyncTask().runInsert(netsisSelectResultInsertVisit);
            if (netsisSelectResultInsertVisit.isSuccess()) {
                getQueryCreator();
                NetsisServiceResult visitLogicalRef = NetsisQueryCreator.getVisitLogicalRef(visitInfo.id);
                getRestAsyncTask().lambdagetSelectRxn24();
                if (visitLogicalRef.isSuccess()) {
                    return ((DocNo) visitLogicalRef.getDataList().get(0)).getLogicalRef();
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "loginFromPeriodicWorker", e2);
            NetsisServiceResult.createErrorResult(e2.getMessage());
        }
        return 0;
    }
    public boolean updateWorProcess(String str, int r3) {
        BaseSelectResult netsisSelectResultUpdateWorProcess =  getQueryCreator().updateWorProcess(str, r3);
        try {
            getRestAsyncTask().lambdagetSelectRxn24();
        } catch (Exception e2) {
            Log.e(TAG, "loginFromPeriodicWorker", e2);
            NetsisServiceResult.createErrorResult(e2.getMessage());
        }
        return netsisSelectResultUpdateWorProcess.isSuccess();
    }
    public void getLastOrderProducts(int r2, boolean z, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(getQueryCreator().getLastOrderProducts(r2, z), responseListener);
    }
    public void getRiskLimit(int r1, ResponseListener responseListener) {
        getQueryCreator();
        getRestAsyncTask().getSelectRx(NetsisQueryCreator.getRiskLimit(r1), responseListener);
    }
    public ClRisk getCustomerRiskTotals(int r3) {
        NetsisSelectResult customerRiskTotals = getQueryCreator().getCustomerRiskTotals(r3, false);
        try {
            getRestAsyncTask().lambdagetSelectRxn24();
            if (!customerRiskTotals.isSuccess() || customerRiskTotals.getDataList() == null || customerRiskTotals.getDataList().size() <= 0) {
                return null;
            }
            return (ClRisk) customerRiskTotals.getDataList().get(0);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
    public void getRecommendedProducts(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(getQueryCreator().getRecommendedProducts(r2), responseListener);
    }
    public void getOrderFicheStatus(ArrayList arrayList, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(getQueryCreator().getOrderFicheStatus(arrayList), responseListener);
    }
    public void getCustomerRiskCalculate(int r2, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.getCustomerRiskCalculate(r2), responseListener);
    }
    public void getProductSerialInfo(String str, ResponseListener responseListener) {
        getRestAsyncTask().getSelectRx(this.mQueryCreator.geProductSerialInfo(str), responseListener);
    }
}
