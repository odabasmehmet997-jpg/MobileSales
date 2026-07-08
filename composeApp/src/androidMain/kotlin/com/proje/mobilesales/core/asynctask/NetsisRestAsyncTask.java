package com.proje.mobilesales.core.asynctask;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.common.collect.Collections2;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.base.*;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.design.Netsis;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.event.ResponseEvent;
import com.proje.mobilesales.core.interfaces.ICustomerSendCompleted;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.*;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import com.proje.mobilesales.core.netsis.sendmodel.cari.CariEkBilgi;
import com.proje.mobilesales.core.netsis.sendmodel.cari.CariTemelBilgi;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.CheckAndPNotesMainParam;
import com.proje.mobilesales.core.netsis.sendmodel.chequedeed.NetsisChequeAndDeedMain;
import com.proje.mobilesales.core.netsis.sendmodel.print.*;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMain;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceiptsMainParam;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDeposit;
import com.proje.mobilesales.core.netsis.sendmodel.safedeposit.SafeDepositParam;
import com.proje.mobilesales.core.netsis.sendmodel.sales.*;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.SalesUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.ChequedeedDetail;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.customer.model.database.ClCardIncharge;
import com.proje.mobilesales.features.customer.model.database.CustomerRiskLimit;
import com.proje.mobilesales.features.dbmodel.*;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.product.model.database.Item;
import com.proje.mobilesales.features.product.model.database.ItemStock;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import com.proje.mobilesales.features.settings.expr.MatterRegex;
import com.proje.mobilesales.features.settings.model.Matter;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.userreport.view.activity.UserReportsActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.*;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.UnknownNullability;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.proje.mobilesales.core.utils.AppUtils.handleNetworkError;

public class NetsisRestAsyncTask extends BaseCommunication {
    static final Object LOCK = new Object();
    public static final String TAG = "com.proje.mobilesales.core.asynctask.NetsisRestAsyncTask";
    private NetsisRestEndPointApi mApi;
    private final NetsisDataConverter mConverter;
    NetsisRestServiceFactory mFactory;
    private final Preferences.NetsisUserSettings mNetsisUserSettings;
    private NetsisRestPublicApi mPublicApi;
    NetsisRestPublicFactory mPublicFactory;
    public static void lambdaexecuteWhenFicheAlreadySent86(GroupItem groupItem) throws Exception {
    }
    public static void lambdaexecuteWhenFicheAlreadySent91(BaseResult baseResult) throws Exception {
    }
    public static void lambdaonOrderIsNotEditableOrDeletedAtErp82(GroupItem groupItem) throws Exception {
    }
    public static Iterable lambdareadOrderFiches141(ArrayList arrayList) throws Exception {
        return arrayList;
    }
    public static Iterable lambdasendAllOfflineCustomers130(List list) throws Exception {
        return list;
    }
    public static NetsisServiceResult lambdasendCaseCashFiche68(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        return netsisServiceResult;
    }
    public static NetsisServiceResult lambdasendCashAndCredit117(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        return netsisServiceResult;
    }
    public static NetsisServiceResult lambdasendChequeAndDeedFiche107(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        return netsisServiceResult;
    }
    public static NetsisSelectResult lambdasendFiche44(NetsisSelectResult netsisSelectResult, NetsisDataHeader netsisDataHeader) throws Exception {
        return netsisSelectResult;
    }
    public static NetsisServiceResult lambdasendOneToOneFiche51(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        return netsisServiceResult;
    }
    public static NetsisServiceResult lambdasendOneToOneFiche53(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        return netsisServiceResult;
    }
    public static void lambdasendOneToOneFiche59(NetsisServiceResult netsisServiceResult) throws Exception {
    }
    public static void lambdasendOneToOneFiche63(NetsisServiceResult netsisServiceResult) throws Exception {
    }
    public static void lambdasendSalesFiche102(NetsisServiceResult netsisServiceResult) throws Exception {
    }
    public static NetsisServiceResult lambdasendSalesFiche96(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        return netsisServiceResult;
    }
    public static Iterable lambdatransferFiches31(List list) throws Exception {
        return list;
    }
    public NetsisRestAsyncTask(CommunicationType communicationType, ISqlBriteDatabase iSqlBriteDatabase, UserSettings userSettings) {
        super(communicationType, iSqlBriteDatabase, userSettings);
        getCommunicationComponent().inject(this);
        this.mNetsisUserSettings = (Preferences.NetsisUserSettings) userSettings;
        this.mConverter = new NetsisDataConverter();
        this.mCommunicationType = CommunicationType.REST;
    }
    public void lambdasendCashAndCredit120(GroupItem f1, boolean f2) {
    }
    public void lambdasendCaseCashFiche70(GroupItem groupItem, boolean z) {
    }
    public NetsisRestEndPointApi getApi() {
        synchronized (LOCK) {
            try {
                if (this.mApi == null) {
                    this.mApi = this.mFactory.rxEnabled(true).create(this.mNetsisUserSettings.getServerAddress(), NetsisRestEndPointApi.class, new CommunicationModule.BackgroundThreadExecutor());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.mApi;
    }
    public NetsisRestPublicApi getPublicApi() {
        synchronized (LOCK) {
            try {
                if (this.mPublicApi == null) {
                    this.mPublicApi = this.mPublicFactory.rxEnabled(true).create(this.mNetsisUserSettings.getServerAddress(), NetsisRestPublicApi.class, new CommunicationModule.BackgroundThreadExecutor());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.mPublicApi;
    }
    private NetsisSelectResult parseResponse(Response<NetsisDataHeader> response, NetsisSelectResult netsisSelectResult) throws Exception {
        if (response.isSuccessful()) {
            return parseNetsisData(response.body(), netsisSelectResult);
        }
        netsisSelectResult.setSuccess(response.message());
        return netsisSelectResult;
    }
    private NetsisSelectResult parseNetsisData(NetsisDataHeader netsisDataHeader, NetsisSelectResult netsisSelectResult) throws Exception {
        if (netsisDataHeader.isSuccessful()) {
            netsisSelectResult.setSuccess(true);
            if (netsisSelectResult.getProcessType().getaClass() != null) {
                netsisSelectResult.setDataList(toListData(netsisSelectResult.getProcessType().getaClass(), netsisDataHeader.getData()));
            }
        } else {
            netsisSelectResult.setSuccess(String.format("%s %s", netsisDataHeader.getErrorCode(), netsisDataHeader.getErrorDesc()));
        }
        return netsisSelectResult;
    }
    private NetsisSelectResult processNetsisData(NetsisDataHeader netsisDataHeader, NetsisSelectResult netsisSelectResult) throws Exception {
        return processSelectResult(parseNetsisData(netsisDataHeader, netsisSelectResult));
    }
    private NetsisSelectResult processResponse(Response<NetsisDataHeader> response, NetsisSelectResult netsisSelectResult) throws Exception {
        return processSelectResult(parseResponse(response, netsisSelectResult));
    }
    private NetsisSelectResult parseResponse(Response<NetsisDataHeader> response, NetsisServiceResult netsisSelectResult) {
        return null;
    }
    private NetsisSelectResult processSelectResult(NetsisSelectResult netsisSelectResult) throws Exception {
        if (netsisSelectResult.isTableDelete() && !deleteTable(netsisSelectResult.getProcessType().getaClass(), netsisSelectResult.getDeleteCondition())) {
            netsisSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_27_database_fiche_delete_error));
            return netsisSelectResult;
        }
        if (netsisSelectResult.isDatabaseSave() && !lambdagetCurrRateRx3(netsisSelectResult.getDataList())) {
            netsisSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_25_database_write_error));
            return netsisSelectResult;
        }
        netsisSelectResult.setCompleted(true);
        return netsisSelectResult;
    }
    public List toListData(Class<?> cls, List<NetsisData> list) throws Exception {
        return getConverter().convertData(cls, list);
    }
    private boolean insertOrUpdateData(List list, boolean z) {
        return list != null && this.mLogoSqlBriteDatabase.insertOrUpdate(list, z);
    }
    private boolean insertData(List list, boolean z) {
        return list != null && this.mLogoSqlBriteDatabase.insert(list, z);
    }
    public boolean lambdagetCurrRateRx3(List list) {
        return list != null && this.mLogoSqlBriteDatabase.insert(list);
    }
    private boolean deleteTable(Class<?> cls, String str) {
        return cls != null && this.mLogoSqlBriteDatabase.delete(cls, str);
    }
    public NetsisDataConverter getConverter() {
        return this.mConverter;
    }
    public NetsisSelectResult runInsert(NetsisSelectResult netsisSelectResult) throws Exception {
        return parseResponse(getApi().getQueries(new NetsisTSql(netsisSelectResult.getSqlWithWorCheck())).execute(), netsisSelectResult);
    }
    public NetsisSelectResult runGeneric(NetsisSelectResult netsisSelectResult) throws Exception {
        Response<NetsisDataHeader> responseExecute = getApi().getQueries(new NetsisTSql(netsisSelectResult.getSqlWithWorCheck())).execute();
        netsisSelectResult.setCompleted(true);
        if (responseExecute.isSuccessful()) {
            if (responseExecute.body().isSuccessful()) {
                ArrayList arrayList = new ArrayList();
                for (NetsisData netsisData : responseExecute.body().getData()) {
                    GenericData genericData = new GenericData();
                    ArrayList arrayList2 = new ArrayList();
                    for (NetsisDataPrimitive netsisDataPrimitive : netsisData.getNetsisDataPrimitives()) {
                        arrayList2.add(new GenericDataPrimitive(netsisDataPrimitive.getName(), netsisDataPrimitive.getValue()));
                    }
                    genericData.setGenericDataPrimitives(arrayList2);
                    arrayList.add(genericData);
                }
                netsisSelectResult.setDataList(arrayList);
                netsisSelectResult.setSuccess(true);
            } else {
                netsisSelectResult.setSuccess(responseExecute.body().getErrorDesc());
            }
        } else {
            netsisSelectResult.setSuccess(responseExecute.message());
        }
        return netsisSelectResult;
    }
    public NetsisSelectResult lambdagetSelectRxn24() throws Exception {
        int size;
        boolean zInsertOrUpdateData;
        NetsisSelectResult netsisSelectResult = null;
        String sqlWithWorCheck = netsisSelectResult.getSqlWithWorCheck();
        if (sqlWithWorCheck.endsWith("R")) {
            int transferPartSize = Preferences.getTransferPartSize(ContextUtils.getmContext());
            if (transferPartSize == 0) {
                transferPartSize = 1000;
            }
            if (netsisSelectResult.getProcessType() == ProcessType.GETITEMIMAGE && transferPartSize > 100) {
                transferPartSize = 25;
            }
            long jNanoTime = System.nanoTime();
            if (netsisSelectResult.isTableDelete()) {
                deleteTable(netsisSelectResult.getProcessType().getaClass(), netsisSelectResult.getDeleteCondition());
            }
            int i2 = transferPartSize;
            int i3 = 1;
            while (true) {
                netsisSelectResult.setSql(sqlWithWorCheck);
                netsisSelectResult.setSql(netsisSelectResult.getSql() + " WHERE R.RowNum  BETWEEN " + i3 + SqlLiteVariable._AND + i2);
                netsisSelectResult = parseResponse(getApi().getQueries(new NetsisTSql(netsisSelectResult.getSql())).execute(), netsisSelectResult);
                size = netsisSelectResult.getDataList().size();
                i2 += transferPartSize;
                int i4 = (i2 - transferPartSize) + 1;
                zInsertOrUpdateData = netsisSelectResult.isSuccess() && insertOrUpdateData(netsisSelectResult.getDataList(), netsisSelectResult.isTableDelete());
                if (size < transferPartSize || !zInsertOrUpdateData) {
                    break;
                }
                i3 = i4;
            }
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            String str = TAG;
            Log.d(str, "Bitis :" + millis + " ms");
            Log.d(str, "ClassName and Size : " + netsisSelectResult.getProcessType().getaClass().getName() + " - " + size);
            netsisSelectResult.setSuccess(zInsertOrUpdateData);
            netsisSelectResult.setCompleted(true);
        } else {
            processResponse(getApi().getQueries(new NetsisTSql(sqlWithWorCheck)).execute(), netsisSelectResult);
        }
        return netsisSelectResult;
    }
    public void getCurrRateRx(final ResponseListener<Boolean> responseListener) {
        getApi().getForExsRx().map(new Function() {
            public Object apply(Object obj) {
                try {
                    return this.f0.lambdagetCurrRateRx0((NetsisDataHeader) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetCurrRateRx1(responseListener, (Throwable) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdagetCurrRateRx2((List) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdagetCurrRateRx3((List) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).flatMap(new Function() {
            @Override
            public Object invoke(Object obj) {
                return this.f0.lambdagetCurrRateRx4((List) obj);
            }
        }).doOnError(new Consumer() {
            @Override
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetCurrRateRx5(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            @Override
            public Object invoke(Object obj) {
                return this.f0.lambdagetCurrRateRx6((NetsisDataHeader) obj);
            }
        }).doOnNext(new Consumer() {
            @Override
            public void accept(Object obj) throws Exception {
                this.f0.lambdagetCurrRateRx7((List) obj);
            }
        }).map(new Function() {
            @Override
            public Object apply(Object t) throws Exception {
                return null;
            }
            public Object invoke(Object obj) {
                return this.f0.lambdagetCurrRateRx8((List) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public void accept(Object obj) {
                responseListener.onResponse((Boolean) obj);
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            @Override
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetCurrRateRx10(responseListener, (Throwable) obj);
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        });
    }
    public List lambdagetCurrRateRx0(NetsisDataHeader netsisDataHeader) throws Exception {
        return toListData(CurrType.class, netsisDataHeader.getData());
    }
    public static void lambdagetCurrRateRx1(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }
    public void lambdagetCurrRateRx2(List list) throws Exception {
        deleteTable(CurrType.class, null);
        }
    public ObservableSource lambdagetCurrRateRx4(List list) throws Exception {
        return getApi().getExRatesRx();
    }


    public static void lambdagetCurrRateRx5(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }
    public List lambdagetCurrRateRx6(NetsisDataHeader netsisDataHeader) throws Exception {
        return toListData(Curr.class, netsisDataHeader.getData());
    }


    public void lambdagetCurrRateRx7(List list) throws Exception {
        deleteTable(CurrType.class, null);
    }


    public Boolean lambdagetCurrRateRx8(List list) throws Exception {
        return Boolean.valueOf(lambdagetCurrRateRx3(list));
    }


    public static void lambdagetCurrRateRx10(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }

    public void runSelectRx(final NetsisSelectResult netsisSelectResult, final ResponseListener<Boolean> responseListener) {
        Observable.just(netsisSelectResult).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdarunSelectRx11(netsisSelectResult, (NetsisSelectResult) obj);
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdarunSelectRx12((Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdarunSelectRx13(responseListener, netsisSelectResult, (NetsisSelectResult) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdarunSelectRx14(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        });
    }


    public void lambdarunSelectRx11(NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        lambdagetSelectRxn24();
    }


    public static void lambdarunSelectRx12(Throwable th) throws Exception {
        Log.e(TAG, "runSelectRx: ", th);
    }


    public static void lambdarunSelectRx13(ResponseListener responseListener, NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        responseListener.onResponse(Boolean.valueOf(netsisSelectResult.isSuccess()));
    }


    public static void lambdarunSelectRx14(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }

    public void runGenericRx(final NetsisSelectResult netsisSelectResult, final UserReportsActivity.UserDefinedResponseListener responseListener) {
        Observable.just(netsisSelectResult).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdarunGenericRx15(netsisSelectResult, (NetsisSelectResult) obj);
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdarunGenericRx16((Throwable) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(netsisSelectResult);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

            }

            @Override
            public Object invoke(Object obj)    {
                try {
                    NetsisRestAsyncTask.lambdarunGenericRx18(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }


    public void lambdarunGenericRx15(NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        runGeneric(netsisSelectResult);
    }


    public static void lambdarunGenericRx16(Throwable th) throws Exception {
        Log.e(TAG, "runGenericRx: ", th);
    }


    public static void lambdarunGenericRx18(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }

    public void getSelectRx(final NetsisSelectResult netsisSelectResult, final ResponseListener<List<Object>> responseListener) {
        Observable.just(netsisSelectResult).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdagetSelectRx19(netsisSelectResult, (NetsisSelectResult) obj);
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetSelectRx20((Throwable) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetSelectRx21(responseListener, netsisSelectResult, (NetsisSelectResult) obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetSelectRx22(responseListener, (Throwable) obj);
            }
        });
    }
    public void lambdagetSelectRx19(NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        lambdagetSelectRxn24();
    }
    public static void lambdagetSelectRx20(Throwable th) throws Exception {
        Log.e(TAG, "runSelectRx: ", th);
    }
    public static void lambdagetSelectRx21(ResponseListener responseListener, NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        responseListener.onResponse(netsisSelectResult.getDataList());
    }
    public static void lambdagetSelectRx22(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }
    public void getSelectRxn(final NetsisSelectResult netsisSelectResult, final NetsisSelectResult netsisSelectResult2, final ResponseListener responseListener) {
        final EDocumentPdfContent eDocumentPdfContent = new EDocumentPdfContent(new EDocumentPdfHeaderNetsis(), new ArrayList(), null);
        Observable.zip(Observable.fromCallable(new Callable() {
            public Object call() {
                return this.f0.lambdagetSelectRxn23(netsisSelectResult);
            }
        }), Observable.fromCallable(new Callable() {
            public Object call() {
                return this.f0.lambdagetSelectRxn24(netsisSelectResult2);
            }
        }), new BiFunction() {
            public Object apply(Object obj, Object obj2) {
                return NetsisRestAsyncTask.lambdagetSelectRxn25(eDocumentPdfContent, (NetsisSelectResult) obj, (NetsisSelectResult) obj2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetSelectRxn27(responseListener, (Throwable) obj);
            }
        });
    }
    public static EDocumentPdfContent lambdagetSelectRxn25(EDocumentPdfContent eDocumentPdfContent, NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        eDocumentPdfContent.setEDocumentPdfHeaderNetsis((EDocumentPdfHeaderNetsis) netsisSelectResult.getDataList().get(0));
        eDocumentPdfContent.setmEDocumentPdfDetailNetsisList(netsisSelectResult2.getDataList());
        return eDocumentPdfContent;
    }
    public static void lambdagetSelectRxn27(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }
    public Disposable transferFiche(GroupItem groupItem, ResponseListener<GroupItem> responseListener) {
        return transferFiche(groupItem, responseListener, false);
    }

    private CustomerRiskLimit getCustomerOverdueDebt(int i2) {
        try {
            NetsisSelectResult riskLimit = NetsisQueryCreator.getRiskLimit(i2);
            lambdagetSelectRxn24();
            if (riskLimit.isSuccess() && riskLimit.getDataList().size() > 0) {
                return (CustomerRiskLimit) riskLimit.getDataList().get(0);
            }
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage());
        }
        return new CustomerRiskLimit();
    }
    public Disposable transferFiche(final GroupItem groupItem, final ResponseListener<GroupItem> responseListener, final boolean z) {
        if (groupItem.getTransferOperationName() == TransferOperationName.ONE_TO_ONE_CHANGE) {
            return Observable.just(groupItem).doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                    this.f0.lambdatransferFiche28(responseListener, z, (GroupItem) obj);
                }
            }).subscribeOn(Schedulers.newThread()).subscribe(new Observer<DataResponse<ItemSlip>>() {
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
        return Observable.fromIterable(groupItem.getItemList()).filter(new Predicate() {
            public boolean test(Object obj) {
                return NetsisRestAsyncTask.lambdatransferFiche29((BaseResult) obj);
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdatransferFiche30(groupItem, responseListener, z, (BaseResult) obj);
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Observer<DataResponse<ItemSlip>>() {
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


    public static boolean lambdatransferFiche29(BaseResult baseResult) throws Exception {
        return !baseResult.isSuccess();
    }
    public void lambdatransferFiche30(GroupItem groupItem, ResponseListener responseListener, boolean z, BaseResult baseResult) throws Exception {
        BaseResult baseResult2 = (BaseResult) groupItem.getItemList().get(0);
        CustomerRiskLimit customerOverdueDebt = isOverdueDebtShouldBeChecked(groupItem) ? getCustomerOverdueDebt(((NetsisServiceResult) groupItem.getItemList().get(0)).getClRef()) : null;
        if (hasCustomerOverdueDebt(customerOverdueDebt, groupItem)) {
            baseResult2.setCompleted(true);
            baseResult2.setSuccess(false);
            baseResult2.setErrorString(String.format(ContextUtils.getmContext().getString(R.string.exp_has_overdue_debt), StringUtils.formatForDisplay(customerOverdueDebt.getDebit() - customerOverdueDebt.getCredit()) + " " + ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
            groupItem.setComplete(true);
            onOrderIsNotEditableOrDeletedAtErp(groupItem, responseListener);
            return;
        }
        baseResult.setRepeatCount(1);
        if (groupItem.isService()) {
            sendFiche(groupItem, (NetsisServiceResult) baseResult, responseListener, z);
        } else {
            sendFiche(groupItem, (NetsisSelectResult) baseResult, responseListener, z);
        }
    }
    private boolean hasCustomerOverdueDebt(CustomerRiskLimit customerRiskLimit, GroupItem groupItem) {
        return customerRiskLimit != null && SalesUtils.isSalesTypeOrderOrInvoiceOrRetailInvoiceOrDispatch(((Sales) ((NetsisServiceResult) groupItem.getItemList().get(0)).getData()).getmSalesType()) && customerRiskLimit.getDebit() > customerRiskLimit.getCredit();
    }
    private boolean isOverdueDebtShouldBeChecked(GroupItem groupItem) {
        return ErpCreator.getInstance().getmBaseErp().isOverdueDebt().booleanValue() && (groupItem.getTransferOperationName() == TransferOperationName.ORDER || groupItem.getTransferOperationName() == TransferOperationName.INVOICE || groupItem.getTransferOperationName() == TransferOperationName.DISPATCH);
    }
    public Disposable transferFiches(List<GroupItem> list, final ResponseListener<GroupItem> responseListener, final boolean z) {
        return Observable.just(list).flatMapIterable(new Function() {
            public Object apply(Object obj) {
                try {
                    return NetsisRestAsyncTask.lambdatransferFiches31((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdatransferFiches34(responseListener, z, (GroupItem) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Observer<DataResponse<ItemSlip>>() {
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
    public void lambdatransferFiches34(final ResponseListener responseListener, final boolean z, final GroupItem groupItem) throws Exception {
        BaseResult baseResult = (BaseResult) groupItem.getItemList().get(0);
        CustomerRiskLimit customerOverdueDebt = isOverdueDebtShouldBeChecked(groupItem) ? getCustomerOverdueDebt(((NetsisServiceResult) groupItem.getItemList().get(0)).getClRef()) : null;
        if (hasCustomerOverdueDebt(customerOverdueDebt, groupItem)) {
            baseResult.setCompleted(true);
            baseResult.setSuccess(false);
            baseResult.setErrorString(String.format(ContextUtils.getmContext().getString(R.string.exp_has_overdue_debt), StringUtils.formatForDisplay(customerOverdueDebt.getDebit() - customerOverdueDebt.getCredit()) + " " + ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
            groupItem.setComplete(true);
            onOrderIsNotEditableOrDeletedAtErp(groupItem, responseListener);
            return;
        }
        if (groupItem.getTransferOperationName() == TransferOperationName.ONE_TO_ONE_CHANGE) {
            Observable.just(groupItem).doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                    this.f0.lambdatransferFiches32(responseListener, z, (GroupItem) obj);
                }
            }).subscribeOn(Schedulers.newThread()).subscribe(new Observer<DataResponse<ItemSlip>>() {
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
        } else {
            getObservableResult(groupItem).doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                    this.f0.lambdatransferFiches33(groupItem, responseListener, z, (BaseResult) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Observer<DataResponse<ItemSlip>>() {
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
    }
    public void lambdatransferFiches33(GroupItem groupItem, ResponseListener responseListener, boolean z, BaseResult baseResult) throws Exception {
        baseResult.setRepeatCount(1);
        if (groupItem.isService()) {
            sendFiche(groupItem, (NetsisServiceResult) baseResult, responseListener, z);
        } else {
            sendFiche(groupItem, (NetsisSelectResult) baseResult, responseListener, z);
        }
    }

    private void sendRouteProcessData(GroupItem groupItem, BaseResult baseResult) {
        if (baseResult.isSuccess()) {
            try {
                runInsert(((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertRouteProcess(groupItem.getTransferOperationName(), baseResult));
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }

    private void sendCabinTransData(BaseResult baseResult) {
        if (baseResult.isSuccess()) {
            NetsisSelectResult netsisSelectResultInsertCabinTrans = ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertCabinTrans(baseResult);
            if (TextUtils.isEmpty(netsisSelectResultInsertCabinTrans.getSql())) {
                return;
            }
            try {
                runInsert(netsisSelectResultInsertCabinTrans);
                if (netsisSelectResultInsertCabinTrans.isSuccess()) {
                    Log.e(TAG, "send cabin trans data success");
                    updateCabinTransAfterTransfer(netsisSelectResultInsertCabinTrans.getLogicalRef());
                }
            } catch (Exception e2) {
                Log.e(TAG, e2.getMessage());
            }
        }
    }
    public Disposable transferFiches(List<GroupItem> list, ResponseListener<GroupItem> responseListener) {
        return transferFiches(list, responseListener, false);
    }

    public void getCustomerRisk(RiskParam riskParam, final ResponseListener responseListener) {
        getApi().getCustomerRisk(riskParam).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).map(new Function() {
            public Object apply(Object obj) {
                return this.f0.lambdagetCustomerRisk35((DataResponse) obj);
            }
        }).onErrorReturnItem(new ArrayList()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetCustomerRisk37(responseListener, (Throwable) obj);
            }
        });
    }

    public List lambdagetCustomerRisk35(DataResponse dataResponse) throws Exception {
        return getRiskResult((String) dataResponse.getData());
    }
    public static void lambdagetCustomerRisk37(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }

    public void getMaxMatterNo(final FicheType ficheType, final MatterSettings matterSettings, final NetsisSelectResult netsisSelectResult, final ResponseListener<String> responseListener) {
        Observable.just(netsisSelectResult).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdagetMaxMatterNo38(netsisSelectResult, (NetsisSelectResult) obj);
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetMaxMatterNo39((Throwable) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdagetMaxMatterNo40(responseListener, ficheType, matterSettings, (NetsisSelectResult) obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetMaxMatterNo41(responseListener, (Throwable) obj);
            }
        });
    }
    public void lambdagetMaxMatterNo38(NetsisSelectResult netsisSelectResult, NetsisSelectResult netsisSelectResult2) throws Exception {
        lambdagetSelectRxn24();
    }


    public static void lambdagetMaxMatterNo39(Throwable th) throws Exception {
        Log.e(TAG, "runSelectRx: ", th);
    }


    public void lambdagetMaxMatterNo40(ResponseListener responseListener, FicheType ficheType, MatterSettings matterSettings, NetsisSelectResult netsisSelectResult) throws Exception {
        String str;
        if (netsisSelectResult.getDataList().size() > 0) {
            str = ((Matter) netsisSelectResult.getDataList().get(0)).maxNo;
        } else {
            str = "";
        }
        responseListener.onResponse(getNewMaxMatterNo(ficheType, matterSettings, str));
    }


    public static void lambdagetMaxMatterNo41(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }

    public String getNewMaxMatterNo(FicheType ficheType, MatterSettings matterSettings, String str) {
        int length;
        long jConvertStringToLong;
        String maxMatterNo = getMaxMatterNo(ficheType, matterSettings);
        if (TextUtils.isEmpty(str)) {
            str = maxMatterNo;
        }
        if (TextUtils.isEmpty(str)) {
            return matterSettings.getFirstMatterNo();
        }
        MatterRegex matterRegex = new MatterRegex(ContextUtils.getmContext().getString(R.string.matter_parse_regex));
        ArrayList<String> matterFind = matterRegex.getMatterFind(str);
        ArrayList<String> matterFind2 = matterRegex.getMatterFind(maxMatterNo);
        if (matterFind.size() > 0) {
            jConvertStringToLong = StringUtils.convertStringToLong(matterFind.get(matterFind.size() - 1));
            length = matterFind.get(matterFind.size() - 1).length();
        } else {
            length = 0;
            jConvertStringToLong = 0;
        }
        long jConvertStringToLong2 = matterFind2.size() > 0 ? StringUtils.convertStringToLong(matterFind2.get(matterFind2.size() - 1)) : 0L;
        if (jConvertStringToLong2 > jConvertStringToLong) {
            length = matterFind2.get(matterFind2.size() - 1).length();
            jConvertStringToLong = jConvertStringToLong2;
        }
        String strValueOf = String.valueOf(jConvertStringToLong + 1);
        if (strValueOf.length() < length) {
            String str2 = "";
            for (int i2 = 0; i2 < length - strValueOf.length(); i2++) {
                str2 = str2 + "0";
            }
            strValueOf = str2 + strValueOf;
        }
        if (matterFind.size() < 1) {
            return strValueOf;
        }
        return (TextUtils.isEmpty(matterFind.get(0)) ? "" : matterFind.get(0)) + strValueOf;
    }

    private List<RiskResult> getRiskResult(String str) {
        ArrayList arrayList = new ArrayList();
        RiskResult riskResult = new RiskResult();
        if (str.indexOf(".") > 0) {
            riskResult.setRiskTotal(StringUtils.convertStringToDouble(str));
        } else {
            riskResult.setRiskTotal(StringUtils.convertStringToDoubleNetsis(str));
        }
        arrayList.add(riskResult);
        return arrayList;
    }
    public Observable<BaseResult> getObservableResult(GroupItem groupItem) {
        return Observable.just(groupItem).flatMapIterable(new Function() {
            public Object apply(Object obj) {
                return ((GroupItem) obj).getItemList();
            }
            public Object invoke(Object obj) {
                return null;
            }
        });
    }

    private void sendFiche(final GroupItem groupItem, final NetsisSelectResult netsisSelectResult, final ResponseListener responseListener, final boolean z) {
        this.mApi.getQueriesRx(new NetsisTSql(netsisSelectResult.getSqlWithWorCheck())).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendFiche43(groupItem, netsisSelectResult, z, (NetsisDataHeader) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                return NetsisRestAsyncTask.lambdasendFiche44(netsisSelectResult, (NetsisDataHeader) obj);
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).doOnNext(new Consumer() {
            public void accept(Object obj) {
                this.f0.updateFicheStatus((NetsisSelectResult) obj);
            }

            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendFiche45(netsisSelectResult, groupItem, (NetsisSelectResult) obj);
            }
        }).doOnComplete(new Action() {
            public void run() throws Exception {
                this.f0.lambdasendFiche46(groupItem, z);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(groupItem);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendFiche48(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        });
    }
    public void lambdasendFiche45(NetsisSelectResult netsisSelectResult, GroupItem groupItem, NetsisSelectResult netsisSelectResult2) throws Exception {
        if (!ErpCreator.getInstance().getmBaseErp().getRouteNewSystem() || netsisSelectResult.getProcessType() == ProcessType.INSERTPENETRATIONDETAIL) {
            return;
        }
        sendRouteProcessData(groupItem, netsisSelectResult2);
        Log.d(TAG, "send route process data failed");
    }

    public static void lambdasendFiche48(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }

    private void sendFiche(GroupItem groupItem, NetsisServiceResult netsisServiceResult, ResponseListener responseListener, boolean z) {
        if (netsisServiceResult.getSendData() instanceof ItemSlip) {
            sendSalesFiche(groupItem, netsisServiceResult, responseListener, z);
            return;
        }
        if (netsisServiceResult.getSendData() instanceof SafeDeposit) {
            sendCaseCashFiche(groupItem, netsisServiceResult, responseListener, z);
            return;
        }
        if (netsisServiceResult.getSendData() instanceof NetsisChequeAndDeedMain) {
            sendChequeAndDeedFiche(groupItem, netsisServiceResult, responseListener, z);
            return;
        }
        if (netsisServiceResult.getSendData() instanceof MixedReceiptsMain) {
            sendCashAndCredit(groupItem, netsisServiceResult, responseListener, z);
            return;
        }
        netsisServiceResult.setCompleted(true);
        netsisServiceResult.setSuccess(false);
        groupItem.setError(false);
        groupItem.setComplete(true);
        responseListener.onResponse(groupItem);
    }
    public void lambdatransferFiches32(final GroupItem groupItem, final ResponseListener responseListener, final boolean z) {
        List itemList = groupItem.getItemList();
        final ItemSlip itemSlip = (ItemSlip) ((NetsisServiceResult) itemList.get(0)).getSendData();
        final ItemSlip itemSlip2 = (ItemSlip) ((NetsisServiceResult) itemList.get(1)).getSendData();
        final NetsisServiceResult netsisServiceResult = (NetsisServiceResult) groupItem.getItemList().get(0);
        getApi().postItemSlipRx(itemSlip).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendOneToOneFiche49(groupItem, netsisServiceResult, z, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendOneToOneFiche50(netsisServiceResult, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return NetsisRestAsyncTask.lambdasendOneToOneFiche51(netsisServiceResult, (DataResponse) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public Object invoke(Object obj) {
                try {
                    this.f0.lambdasendOneToOneFiche62(netsisServiceResult, itemSlip2, groupItem, z, itemSlip, responseListener, (NetsisServiceResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {

            public Object invoke(Object obj) {
                try {
                    NetsisRestAsyncTask.lambdasendOneToOneFiche63((NetsisServiceResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        }, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

            }

            public Object invoke(Object obj) {
                try {
                    NetsisRestAsyncTask.lambdasendOneToOneFiche64(responseListener, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        }, new Action() {
            public void run() throws Exception {
                NetsisRestAsyncTask.lambdasendOneToOneFiche65(netsisServiceResult, responseListener, groupItem);
            }
        });
    }
    public Object lambdasendOneToOneFiche50(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        if (netsisServiceResult.isSuccess()) {
            netsisServiceResult.setFicheNo(((ItemSlip) dataResponse.getData()).getSlipHeader().getSlipNo());
            sendMail(netsisServiceResult.getDataType(), netsisServiceResult.getData(), null, netsisServiceResult.getFicheNo());
        }
        return null;
    }
    public void lambdasendOneToOneFiche62(final NetsisServiceResult netsisServiceResult, ItemSlip itemSlip, final GroupItem groupItem, final boolean z, final ItemSlip itemSlip2, final ResponseListener responseListener, NetsisServiceResult netsisServiceResult2) throws Exception {
        if (netsisServiceResult.isSuccess()) {
            getApi().postItemSlipRx(itemSlip).doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                    this.f0.lambdasendOneToOneFiche52(groupItem, netsisServiceResult, z, itemSlip2, (DataResponse) obj);
                }
                public Object invoke(Object obj) {
                    return null;
                }
            }).map(new Function() {
                public Object apply(Object obj) {
                    try {
                        return NetsisRestAsyncTask.lambdasendOneToOneFiche53(netsisServiceResult, (DataResponse) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                public Object invoke(Object obj) {
                    return null;
                }
            }).doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                    this.f0.lambdasendOneToOneFiche54(netsisServiceResult, groupItem, (NetsisServiceResult) obj);
                }

                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            }).doOnNext(new Consumer() {
                public   void accept(Object obj) throws Exception {
                    this.f0.lambdasendOneToOneFiche55(groupItem, (NetsisServiceResult) obj);
                }

                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            }).doOnNext(new Consumer() {
                private NetsisRestAsyncTask f0;

                public void accept(Object obj) throws Exception {
                    this.f0.lambdasendOneToOneFiche56((NetsisServiceResult) obj);
                }

                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).doOnComplete(new Action() {
                public void run() throws Exception {
                    this.f0.lambdasendOneToOneFiche57(groupItem, z);
                }
            }).doOnNext(new Consumer() {
                public void accept(Object obj) throws Exception {
                    this.f0.lambdasendOneToOneFiche58((NetsisServiceResult) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
                public void accept(Object obj) throws Exception {
                    NetsisRestAsyncTask.lambdasendOneToOneFiche59((NetsisServiceResult) obj);
                }

                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            }, new Consumer() {
                public void accept(Object obj) {
                    handleNetworkError((Throwable) obj);
                }

                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            }, new Action() {
                public void run() {
                    responseListener.onResponse(groupItem);
                }
            });
        }
    }
    public void lambdasendOneToOneFiche52(GroupItem groupItem, NetsisServiceResult netsisServiceResult, boolean z, ItemSlip itemSlip, DataResponse dataResponse) throws Exception {
        lambdasendSalesFiche94(dataResponse, groupItem, netsisServiceResult, z);
        if (netsisServiceResult.isSuccess()) {
            return;
        }
        ItemSlipParam itemSlipParam = new ItemSlipParam();
        itemSlipParam.setCustomerCode(itemSlip.getSlipHeader().getCustomerCode());
        itemSlipParam.setDocumentNumber(netsisServiceResult.getFicheNo());
        itemSlipParam.setDocumentType(itemSlip.getSlipType().ordinal());
        getApi().deleteItemSlip(itemSlipParam).execute();
        Log.e("TAG", "asd");
    }
    public void lambdasendOneToOneFiche54(NetsisServiceResult netsisServiceResult, GroupItem groupItem, NetsisServiceResult netsisServiceResult2) throws Exception {
        updateFicheStatus(netsisServiceResult, groupItem.getTransferOperationName());
    }
    public void lambdasendOneToOneFiche55(GroupItem groupItem, NetsisServiceResult netsisServiceResult) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
            sendRouteProcessData(groupItem, netsisServiceResult);
        }
    }
    public void lambdasendOneToOneFiche56(NetsisServiceResult netsisServiceResult) throws Exception {
        if (isCabinTransSend(netsisServiceResult)) {
            sendCabinTransData(netsisServiceResult);
            Log.d(TAG, "send cabin trans data failed");
        }
    }
    public void lambdasendOneToOneFiche58(NetsisServiceResult netsisServiceResult) throws Exception {
        if (netsisServiceResult == null || netsisServiceResult.getErrorString() != null) {
            return;
        }
        sendMail(netsisServiceResult.getDataType(), netsisServiceResult.getData(), null, netsisServiceResult.getFicheNo());
    }
    public static void lambdasendOneToOneFiche64(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }

    public static void lambdasendOneToOneFiche65(NetsisServiceResult netsisServiceResult, ResponseListener responseListener, GroupItem groupItem) throws Exception {
        if (netsisServiceResult.isSuccess()) {
            return;
        }
        responseListener.onResponse(groupItem);
    }
    private void sendCaseCashFiche(final GroupItem groupItem, final NetsisServiceResult netsisServiceResult, final ResponseListener responseListener, final boolean z) {
        getApi().postSafeDepositRx((SafeDeposit) netsisServiceResult.getSendData()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendCaseCashFiche66(groupItem, netsisServiceResult, z, (DataResponse) obj);
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendCaseCashFiche67(netsisServiceResult, (DataResponse) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                return NetsisRestAsyncTask.lambdasendCaseCashFiche68(netsisServiceResult, (DataResponse) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendCaseCashFiche69(netsisServiceResult, groupItem, (NetsisServiceResult) obj);
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendCaseCashFiche70(groupItem, (NetsisServiceResult) obj);
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).doOnComplete(new Action() {
            public void run() throws Exception {
                this.f0.lambdasendCaseCashFiche71(groupItem, z);
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendCaseCashFiche72((NetsisServiceResult) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(groupItem);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendCaseCashFiche74(responseListener, (Throwable) obj);
            }
        }, new Action() {
            public void run() {
                responseListener.onResponse(groupItem);
            }
        });
    }
    public static void lambdasendCaseCashFiche67(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        if (netsisServiceResult.isSuccess()) {
            netsisServiceResult.setFicheNo(((SafeDeposit) dataResponse.getData()).getFisno());
        }
    }
    public void lambdasendCaseCashFiche69(NetsisServiceResult netsisServiceResult, GroupItem groupItem, NetsisServiceResult netsisServiceResult2) throws Exception {
        updateFicheStatus(netsisServiceResult, groupItem.getTransferOperationName());
    }
    public void lambdasendCaseCashFiche70(GroupItem groupItem, NetsisServiceResult netsisServiceResult) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
            sendRouteProcessData(groupItem, netsisServiceResult);
            Log.d(TAG, "send route process data failed");
        }
    }
    public void lambdasendCaseCashFiche72(NetsisServiceResult netsisServiceResult) throws Exception {
        if (netsisServiceResult == null || netsisServiceResult.getErrorString() != null) {
            return;
        }
        sendMail(netsisServiceResult.getDataType(), netsisServiceResult.getData(), null, netsisServiceResult.getFicheNo());
    }
    public static void lambdasendCaseCashFiche74(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    public void getSalesOnlineTotal(final NetsisServiceResult netsisServiceResult, final ResponseListener responseListener) {
        ItemSlip itemSlip = (ItemSlip) netsisServiceResult.getSendData();
        itemSlip.getSlipHeader().setSlipNo(itemSlip.generateFicheNumberForCalculate());
        getApi().calculateFiche(itemSlip).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                try {
                    this.f0.lambdagetSalesOnlineTotal76(netsisServiceResult, (DataResponse) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                try {
                    NetsisRestAsyncTask.lambdagetSalesOnlineTotal77(responseListener, netsisServiceResult, (DataResponse) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetSalesOnlineTotal78(responseListener, (Throwable) obj);
            }
        });
    }
    public void lambdagetSalesOnlineTotal76(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        parseDataResponse(dataResponse, netsisServiceResult);
        calculateFiche(netsisServiceResult);
    }
    public static void lambdagetSalesOnlineTotal77(ResponseListener responseListener, NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        responseListener.onResponse(netsisServiceResult.getData());
    }


    public static void lambdagetSalesOnlineTotal78(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    public void getSalesCampaign(final NetsisServiceResult netsisServiceResult, final ResponseListener responseListener) {
        ItemSlip itemSlip = (ItemSlip) netsisServiceResult.getSendData();
        itemSlip.getSlipHeader().setSlipNo(itemSlip.generateFicheNumberForCalculate());
        getApi().calculateFiche(itemSlip).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdagetSalesCampaign79(netsisServiceResult, (DataResponse) obj);
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(netsisServiceResult);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetSalesCampaign81(responseListener, (Throwable) obj);
            }
        });
    }
    public void lambdagetSalesCampaign79(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        parseDataResponse(dataResponse, netsisServiceResult);
        calculateFiche(netsisServiceResult);
    }
    public static void lambdagetSalesCampaign81(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    private boolean isServiceResultAddOrderAndIsEditable(NetsisServiceResult netsisServiceResult) {
        Sales sales;
        return netsisServiceResult.getDataType() == DataObjectType.ADDORDER && (sales = (Sales) netsisServiceResult.getData()) != null && sales.isEdit() == 1 && sales.getTransferCount() > 0;
    }
    private boolean isEditableOrderStillExistsAtErpAsEditable(GroupItem groupItem, NetsisServiceResult netsisServiceResult) {
        String stringResource = "";
        try {
            Sales sales = (Sales) netsisServiceResult.getData();
            if (sales == null) {
                return true;
            }
            @UnknownNullability NetsisServiceResult orderStatus = ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getOrderStatus(sales.getFicheNo(), netsisServiceResult.getClCode());
            lambdagetSelectRxn24();
            if (!orderStatus.isSuccess() || orderStatus.getDataList() == null || orderStatus.getDataList().isEmpty()) {
                stringResource = ContextUtils.getStringResource(R.string.str_fiche_delete_erp) + System.getProperty("line.separator") + sales.getFicheNo();
            } else {
                OrderFicheStatus orderFicheStatus = (OrderFicheStatus) orderStatus.getDataList().get(0);
                if (orderFicheStatus == null || orderFicheStatus.getStatus() != NetsisInvoiceType.ft_Muhtelif.getStatusValue()) {
                    stringResource = ContextUtils.getStringResource(R.string.str_order_status_offer_error);
                }
            }
            if (!TextUtils.isEmpty(stringResource)) {
                groupItem.setComplete(true);
                groupItem.setError(true);
                for (int i2 = 0; i2 < groupItem.getItemList().size(); i2++) {
                    BaseResult baseResult = (BaseResult) groupItem.getItemList().get(i2);
                    if (!baseResult.isCompleted()) {
                        baseResult.setErrorString(stringResource);
                        baseResult.setSuccess(false);
                        baseResult.setCompleted(true);
                    }
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "isEditableOrderStillExistsAtErpAsEditable", e2);
        }
        return TextUtils.isEmpty(stringResource);
    }

    private void onOrderIsNotEditableOrDeletedAtErp(final GroupItem groupItem, final ResponseListener responseListener) {
        new CompositeDisposable().add(Observable.just(groupItem).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdaonOrderIsNotEditableOrDeletedAtErp82((GroupItem) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdaonOrderIsNotEditableOrDeletedAtErp83(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }, new Action() {
            public void run() {
                responseListener.onResponse(groupItem);
            }
        }));
    }
    public static void lambdaonOrderIsNotEditableOrDeletedAtErp83(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    private void deleteEditedOrderFromErpToSendNewOne(GroupItem groupItem, NetsisServiceResult netsisServiceResult) {
        try {
            Sales sales = (Sales) netsisServiceResult.getData();
            ItemSlipParam itemSlipParam = new ItemSlipParam();
            itemSlipParam.setCustomerCode(sales.getClCode());
            itemSlipParam.setDocumentNumber(sales.getFicheNo());
            itemSlipParam.setDocumentType(NetsisSlipType.ftSSip.ordinal());
            DataResponse dataResponseBody = getApi().deleteItemSlip(itemSlipParam).execute().body();
            if (dataResponseBody.isSuccessful()) {
                return;
            }
            groupItem.setComplete(true);
            groupItem.setError(true);
            for (int i2 = 0; i2 < groupItem.getItemList().size(); i2++) {
                BaseResult baseResult = (BaseResult) groupItem.getItemList().get(i2);
                if (!baseResult.isCompleted()) {
                    baseResult.setErrorString(StringUtils.formatNetsisDataResponse((DataResponse<?>) dataResponseBody));
                    baseResult.setSuccess(false);
                    baseResult.setCompleted(true);
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "deleteItemSlipOrder", e2);
        }
    }
    private boolean checkFicheAlreadySent(NetsisServiceResult netsisServiceResult) {
        NetsisSelectResult ficheDuplicateControlSql;
        try {
            if (!(netsisServiceResult.getSendData() instanceof ItemSlip) || isServiceResultAddOrderAndIsEditable(netsisServiceResult) || (ficheDuplicateControlSql = NetsisQueryCreator.getFicheDuplicateControlSql(((ItemSlip) netsisServiceResult.getSendData()).getSlipHeader())) == null) {
                return false;
            }
            lambdagetSelectRxn24();
            if (ficheDuplicateControlSql.isSuccess() && ficheDuplicateControlSql.getDataList().size() > 0) {
                List dataList = ficheDuplicateControlSql.getDataList();
                netsisServiceResult.setSuccess(true);
                netsisServiceResult.setFicheNo(((KeyValuePair) dataList.get(0)).getValue());
                return true;
            }
        } catch (Exception e2) {
            Log.e(TAG, "checkFicheAlreadySent", e2);
        }
        return false;
    }

    private void executeWhenFicheAlreadySent(final GroupItem groupItem, final NetsisServiceResult netsisServiceResult, final ResponseListener responseListener) {
        Observable.just(groupItem).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdaexecuteWhenFicheAlreadySent85(netsisServiceResult, groupItem, (GroupItem) obj);
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdaexecuteWhenFicheAlreadySent86((GroupItem) obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdaexecuteWhenFicheAlreadySent87(responseListener, (Throwable) obj);
            }
        }, new Action() {
            public void run() {
                responseListener.onResponse(groupItem);
            }
        });
        Observable.fromIterable(groupItem.getItemList()).filter(new Predicate() {
            public boolean test(Object obj) {
                return NetsisRestAsyncTask.lambdaexecuteWhenFicheAlreadySent89((BaseResult) obj);
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdaexecuteWhenFicheAlreadySent90((BaseResult) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdaexecuteWhenFicheAlreadySent91((BaseResult) obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdaexecuteWhenFicheAlreadySent92(responseListener, (Throwable) obj);
            }
        }, new Action() {
            public void run() {
                responseListener.onResponse(groupItem);
            }
        });
    }
    public void lambdaexecuteWhenFicheAlreadySent85(NetsisServiceResult netsisServiceResult, GroupItem groupItem, GroupItem groupItem2) throws Exception {
        updateFicheTransferAndFicheNo(netsisServiceResult.getLogicalRef(), netsisServiceResult.getDataType(), netsisServiceResult.getFicheNo());
        checkWorProcessInsertIfNotExists(groupItem.getTransferOperationName(), netsisServiceResult);
        groupItem2.setComplete(true);
        for (int i2 = 0; i2 < groupItem2.getItemList().size(); i2++) {
            BaseResult baseResult = (BaseResult) groupItem2.getItemList().get(i2);
            if (!baseResult.isCompleted()) {
                baseResult.setSuccess(true);
                baseResult.setCompleted(true);
            }
        }
    }
    public static void lambdaexecuteWhenFicheAlreadySent87(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    public static boolean lambdaexecuteWhenFicheAlreadySent89(BaseResult baseResult) throws Exception {
        return !baseResult.isSuccess();
    }
    public static void lambdaexecuteWhenFicheAlreadySent90(BaseResult baseResult) throws Exception {
        if (baseResult.isCompleted()) {
            return;
        }
        baseResult.setSuccess(true);
        baseResult.setCompleted(true);
    }
    public static void lambdaexecuteWhenFicheAlreadySent92(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    private void sendSalesFiche(final GroupItem groupItem, final NetsisServiceResult netsisServiceResult, final ResponseListener responseListener, final boolean z) {
        if (checkFicheAlreadySent(netsisServiceResult)) {
            Log.d(TAG, "executeWhenFicheAlreadySent");
            executeWhenFicheAlreadySent(groupItem, netsisServiceResult, responseListener);
            return;
        }
        if (isServiceResultAddOrderAndIsEditable(netsisServiceResult)) {
            if (!isEditableOrderStillExistsAtErpAsEditable(groupItem, netsisServiceResult)) {
                onOrderIsNotEditableOrDeletedAtErp(groupItem, responseListener);
                return;
            }
            deleteEditedOrderFromErpToSendNewOne(groupItem, netsisServiceResult);
            if (groupItem.isError()) {
                onOrderIsNotEditableOrDeletedAtErp(groupItem, responseListener);
                return;
            }
        }
        Log.d(TAG, "executePostItemSlipRx");
        getApi().postItemSlipRx((ItemSlip) netsisServiceResult.getSendData()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendSalesFiche94(groupItem, netsisServiceResult, z, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendSalesFiche95(netsisServiceResult, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return NetsisRestAsyncTask.lambdasendSalesFiche96(netsisServiceResult, (DataResponse) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {

            public void accept(Object obj) throws Exception {
                this.f0.lambdasendSalesFiche97(netsisServiceResult, groupItem, (NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendSalesFiche98(groupItem, (NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendSalesFiche99((NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda20()).doOnComplete(new Action() {
            public void run() throws Exception {
                this.f0.lambdasendSalesFiche100(groupItem, z);
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendSalesFiche101((NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendSalesFiche102((NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendSalesFiche103(responseListener, (Throwable) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Action() {
            public void run() {
                responseListener.onResponse(groupItem);
            }
        });
    }
    public static void lambdasendSalesFiche95(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        if (netsisServiceResult.isSuccess()) {
            netsisServiceResult.setFicheNo(((ItemSlip) dataResponse.getData()).getSlipHeader().getSlipNo());
        }
    }
    public void lambdasendSalesFiche97(NetsisServiceResult netsisServiceResult, GroupItem groupItem, NetsisServiceResult netsisServiceResult2) throws Exception {
        updateFicheStatus(netsisServiceResult, groupItem.getTransferOperationName());
    }
    public void lambdasendSalesFiche98(GroupItem groupItem, NetsisServiceResult netsisServiceResult) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
            if (groupItem.getTransferOperationName() != TransferOperationName.ONE_TO_ONE_CHANGE || isOneToOneReturnDispatch(netsisServiceResult)) {
                sendRouteProcessData(groupItem, netsisServiceResult);
            }
            Log.d(TAG, "send route process data failed");
        }
    }
    public void lambdasendSalesFiche99(NetsisServiceResult netsisServiceResult) throws Exception {
        if (isCabinTransSend(netsisServiceResult)) {
            sendCabinTransData(netsisServiceResult);
            Log.d(TAG, "send cabin trans data failed");
        }
    }

    public void lambdasendSalesFiche101(NetsisServiceResult netsisServiceResult) throws Exception {
        if (netsisServiceResult == null || netsisServiceResult.getErrorString() != null) {
            return;
        }
        sendMail(netsisServiceResult.getDataType(), netsisServiceResult.getData(), null, netsisServiceResult.getFicheNo());
    }
    public static void lambdasendSalesFiche103(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    private void sendChequeAndDeedFiche(final GroupItem groupItem, final NetsisServiceResult netsisServiceResult, final ResponseListener responseListener, final boolean z) {
        getApi().postChequeAndDeedRx((NetsisChequeAndDeedMain) netsisServiceResult.getSendData()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendChequeAndDeedFiche105(groupItem, netsisServiceResult, z, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendChequeAndDeedFiche106(netsisServiceResult, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return NetsisRestAsyncTask.lambdasendChequeAndDeedFiche107(netsisServiceResult, (DataResponse) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendChequeAndDeedFiche108(netsisServiceResult, groupItem, (NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public   void accept(Object obj) throws Exception {
                this.f0.lambdasendChequeAndDeedFiche109(groupItem, (NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).doOnComplete(new Action() {
            public void run() throws Exception {
                this.f0.lambdasendChequeAndDeedFiche110(groupItem, z);
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendChequeAndDeedFiche111((NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(groupItem);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendChequeAndDeedFiche113(responseListener, (Throwable) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Action() {
            public void run() {
                responseListener.onResponse(groupItem);
            }
        });
    }
    public static void lambdasendChequeAndDeedFiche106(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        if (netsisServiceResult.isSuccess()) {
            netsisServiceResult.setFicheNo(((NetsisChequeAndDeedMain) dataResponse.getData()).getBordroNo());
        }
    }
    public void lambdasendChequeAndDeedFiche108(NetsisServiceResult netsisServiceResult, GroupItem groupItem, NetsisServiceResult netsisServiceResult2) throws Exception {
        updateFicheStatus(netsisServiceResult, groupItem.getTransferOperationName());
        updateChequeDeedDetailFicheNo((ChequeDeed) netsisServiceResult.getData(), (NetsisChequeAndDeedMain) netsisServiceResult.getSendData());
    }
    public void lambdasendChequeAndDeedFiche109(GroupItem groupItem, NetsisServiceResult netsisServiceResult) throws Exception {
        if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
            sendRouteProcessData(groupItem, netsisServiceResult);
            Log.d(TAG, "send route process data failed");
        }
    }
    public void lambdasendChequeAndDeedFiche111(NetsisServiceResult netsisServiceResult) throws Exception {
        if (netsisServiceResult == null || netsisServiceResult.getErrorString() != null) {
            return;
        }
        sendMail(netsisServiceResult.getDataType(), netsisServiceResult.getData(), null, netsisServiceResult.getFicheNo());
    }
    public static void lambdasendChequeAndDeedFiche113(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(handleNetworkError(th));
    }
    private void sendCashAndCredit(final GroupItem groupItem, final NetsisServiceResult netsisServiceResult, final ResponseListener responseListener, final boolean z) {
        getApi().postMixedReceiptsMain((MixedReceiptsMain) netsisServiceResult.getSendData()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendCashAndCredit115(groupItem, netsisServiceResult, z, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendCashAndCredit116(netsisServiceResult, (DataResponse) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).map(new Function() {
            public Object apply(Object obj) {
                try {
                    return NetsisRestAsyncTask.lambdasendCashAndCredit117(netsisServiceResult, (DataResponse) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendCashAndCredit118(netsisServiceResult, groupItem, (NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendCashAndCredit119(groupItem, (NetsisServiceResult) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).doOnComplete(new Action() {
            public void run() throws Exception {
                this.f0.lambdasendCashAndCredit120(groupItem, z);
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                try {
                    this.f0.lambdasendCashAndCredit121((NetsisServiceResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(groupItem);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendCashAndCredit123(responseListener, (Throwable) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Action() {
            public void run() {
                responseListener.onResponse(groupItem);
            }
        });
    }
    public static void lambdasendCashAndCredit116(NetsisServiceResult netsisServiceResult, DataResponse dataResponse) throws Exception {
        if (netsisServiceResult.isSuccess()) {
            netsisServiceResult.setFicheNo(((MixedReceiptsMain) dataResponse.getData()).getBelgeNo());
        }
    }
    public void lambdasendCashAndCredit118(NetsisServiceResult netsisServiceResult, GroupItem groupItem, NetsisServiceResult netsisServiceResult2) throws Exception {
        try {
            updateFicheStatus(netsisServiceResult, groupItem.getTransferOperationName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdasendCashAndCredit119(GroupItem groupItem, NetsisServiceResult netsisServiceResult) throws Exception {
        try {
            if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
                sendRouteProcessData(groupItem, netsisServiceResult);
                Log.d(TAG, "send route process data failed");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdasendCashAndCredit121(NetsisServiceResult netsisServiceResult) throws Exception {
        try {
            if (netsisServiceResult == null || netsisServiceResult.getErrorString() != null) {
                return;
            }
            sendMail(netsisServiceResult.getDataType(), netsisServiceResult.getData(), null, netsisServiceResult.getFicheNo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void lambdasendCashAndCredit123(ResponseListener responseListener, Throwable th) throws Exception {
        try {
            responseListener.onError(handleNetworkError(th));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void parseDataResponse(DataResponse dataResponse, NetsisServiceResult netsisServiceResult) {
        if (dataResponse.isSuccessful()) {
            netsisServiceResult.setSendData(dataResponse.getData());
            netsisServiceResult.setSuccess(true);
        } else {
            netsisServiceResult.setSuccess(StringUtils.formatNetsisDataResponse((DataResponse<?>) dataResponse));
        }
        netsisServiceResult.setCompleted(true);
    }
    public void lambdasendSalesFiche94(DataResponse dataResponse, GroupItem groupItem, NetsisServiceResult netsisServiceResult, boolean z) {
        if (dataResponse.isSuccessful()) {
            netsisServiceResult.setSendData(dataResponse.getData());
            netsisServiceResult.setSuccess(true);
        } else {
            String netsisDataResponse = StringUtils.formatNetsisDataResponse((DataResponse<?>) dataResponse);
            netsisServiceResult.setSuccess(netsisDataResponse);
            groupItem.setError(true);
            groupItem.setErrorMessage(netsisDataResponse);
            if (z) {
                ContextUtils.showToast(netsisDataResponse);
            }
        }
        netsisServiceResult.setCompleted(true);
    }
    public void lambdasendFiche43(NetsisDataHeader netsisDataHeader, GroupItem groupItem, NetsisSelectResult netsisSelectResult, boolean z) {
        if (netsisDataHeader.isSuccessful()) {
            try {
                netsisSelectResult.setSuccess(true);
                netsisSelectResult.setDataList(toListData(netsisSelectResult.getProcessType().getaClass(), netsisDataHeader.getData()));
            } catch (Exception e2) {
                e2.printStackTrace();
                netsisSelectResult.setSuccess(false);
            }
        } else {
            String netsisDataResponse = StringUtils.formatNetsisDataResponse(netsisDataHeader);
            netsisSelectResult.setSuccess(netsisDataResponse);
            groupItem.setError(true);
            groupItem.setErrorMessage(netsisDataResponse);
            if (z) {
                ContextUtils.showToast(netsisDataResponse);
            }
        }
        netsisSelectResult.setCompleted(true);
    }
    private void checkWorProcessInsertIfNotExists(TransferOperationName transferOperationName, BaseResult baseResult) {
        List worProcess = getWorProcess(transferOperationName, baseResult);
        if (worProcess != null && worProcess.size() == 0) {
            insertFicheInfoToWorProcess(transferOperationName, baseResult);
        }
    }
    private List getWorProcess(TransferOperationName transferOperationName, BaseResult baseResult) {
        if (baseResult instanceof BaseSelectResult) {
            return null;
        }
        try {
            NetsisSelectResult worProcess = ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getWorProcess(transferOperationName, (BaseServiceResult) baseResult);
            lambdagetSelectRxn24();
            if (worProcess.isSuccess()) {
                return worProcess.getDataList();
            }
            return null;
        } catch (Exception e2) {
            Log.e(TAG, "getWorProcess", e2);
            return null;
        }
    }
    private boolean insertFicheInfoToWorProcess(TransferOperationName transferOperationName, BaseResult baseResult) {
        if (baseResult instanceof BaseSelectResult) {
            return true;
        }
        try {
            NetsisSelectResult netsisSelectResultInsertFicheProcess = ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertFicheProcess(transferOperationName, (NetsisServiceResult) baseResult);
            netsisSelectResultInsertFicheProcess.setProcessType(ProcessType.INSERTWORPROCESS);
            runInsert(netsisSelectResultInsertFicheProcess);
            return netsisSelectResultInsertFicheProcess.isSuccess();
        } catch (Exception e2) {
            Log.e(TAG, "insertWorProcess", e2);
            return false;
        }
    }
    private void updateFicheStatus(NetsisServiceResult netsisServiceResult, TransferOperationName transferOperationName) {
        if (netsisServiceResult.isSuccess()) {
            if (netsisServiceResult.getSendData().getClass() == ItemSlip.class) {
                updateFiche(netsisServiceResult);
            }
            updateFicheTransferAndFicheNo(netsisServiceResult.getLogicalRef(), netsisServiceResult.getDataType(), netsisServiceResult.getFicheNo());
            try {
                NetsisSelectResult netsisSelectResultInsertFicheProcess = ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().insertFicheProcess(transferOperationName, netsisServiceResult);
                netsisSelectResultInsertFicheProcess.setProcessType(ProcessType.INSERTWORPROCESS);
                runInsert(netsisSelectResultInsertFicheProcess);
                insertBarcodeTracking(netsisServiceResult);
            } catch (Exception e2) {
                Log.e(TAG, "insertWorProcess", e2);
            }
            Log.d(TAG, "sendFiche: updated");
        }
    }
    private void insertBarcodeTracking(NetsisServiceResult netsisServiceResult) {
        Sales sales;
        try {
            if (netsisServiceResult.getSendData().getClass() == ItemSlip.class && (sales = (Sales) netsisServiceResult.getData()) != null && !sales.getMSalesDetailList().isEmpty()) {
                Map map = (Map) sales.getMSalesDetailList().stream().filter(new java.util.function.Predicate() {
                    public boolean test(Object obj) {
                        return NetsisRestAsyncTask.lambdainsertBarcodeTracking125((SalesDetail) obj);
                    }
                }).collect(Collectors.groupingBy(new NetsisRestAsyncTaskExternalSyntheticLambda73()));
                if (map.isEmpty()) {
                    return;
                }
                for (String str : map.keySet()) {
                    NetsisSelectResult netsisSelectResultBuild = NetsisSelectResult.newBuilder().withSql(((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getInsertWorBarcodesRequestSql(netsisServiceResult, str, (String) ((List) map.get(str)).stream().map(new java.util.function.Function() {
                        public Object apply(Object obj) {
                            return ((SalesDetail) obj).getSearchBarcodes();
                        }
                    }).flatMap(new java.util.function.Function() {
                        public Object apply(Object obj) {
                            return ((ArrayList) obj).stream();
                        }
                    }).collect(Collectors.joining(";")))).build();
                    netsisSelectResultBuild.setProcessType(ProcessType.INSERTWORPROCESS);
                    runInsert(netsisSelectResultBuild);
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "insertBarcodeTracking", e2);
        }
    }
    public static boolean lambdainsertBarcodeTracking125(SalesDetail salesDetail) {
        return !salesDetail.getSearchBarcodes().isEmpty();
    }
    private void updateChequeDeedDetailFicheNo(ChequeDeed chequeDeed, NetsisChequeAndDeedMain netsisChequeAndDeedMain) {
        for (int i2 = 0; i2 < netsisChequeAndDeedMain.getEvraklar().size(); i2++) {
            int i3 = chequeDeed.getChequedeedDetail().get(i2).logicalRef;
            try {
                String str = netsisChequeAndDeedMain.getEvraklar().get(i2).getsCNO();
                ContentValues contentValues = new ContentValues();
                contentValues.put(this.mContext.getString(R.string.column_fiche_no), str);
                this.mLogoSqlBriteDatabase.update(ChequedeedDetail.class, contentValues, this.mContext.getString(R.string.update_fiche_where_logical_ref_cond), String.valueOf(i3));
                chequeDeed.getChequedeedDetail().get(i2).ficheNo = str;
            } catch (Exception e2) {
                Log.e(TAG, "updateChequeDeedDetailFicheNo: ", e2);
            }
        }
    }
    private void calculateFiche(NetsisServiceResult netsisServiceResult) {
        Sales sales = (Sales) netsisServiceResult.getData();
        ItemSlip itemSlip = (ItemSlip) netsisServiceResult.getSendData();
        setHeaderDiscountTotals(sales, itemSlip.getSlipHeader());
        setHeaderDiscountRatios(sales, itemSlip.getSlipHeader());
        setHeaderDiscountCards(sales, itemSlip.getSlipHeader());
        sales.setDiscTotal(itemSlip.getSlipHeader().getFazDiscount() + itemSlip.getSlipHeader().getGeneralDiscount() + itemSlip.getSlipHeader().getGeneralDiscount2() + itemSlip.getSlipHeader().getGeneralDiscount3() + itemSlip.getSlipHeader().getDetailDiscount());
        sales.setGrossTotal(itemSlip.getSlipHeader().getGrossTotal());
        sales.setTotalVat(itemSlip.getSlipHeader().getVat());
        sales.setTotalNet(itemSlip.getSlipHeader().getGeneralTotal());
        sales.setTotal(itemSlip.getSlipHeader().getGrossTotal() - sales.getDiscTotal());
        sales.setDueDate(new FicheDateProp(DateAndTimeUtils.convertReportDateToSimpleDate(itemSlip.getSlipHeader().getPaymentDate())));
        HashMap<Integer, ArrayList<String>> mapBackupSearchBarcodes = backupSearchBarcodes(sales.getMSalesDetailList());
        sales.getMSalesDetailList().clear();
        for (ItemSlipLine itemSlipLine : itemSlip.getLines()) {
            SalesDetail detail = parseDetail(itemSlipLine, sales, itemSlip.getSlipHeader().getNetsisInvoiceType());
            loadSearchBarcodesFromBackup(detail, mapBackupSearchBarcodes);
            setBarcodeCount(sales, detail, itemSlipLine.getStokKodu());
            sales.getMSalesDetailList().add(detail);
        }
    }

    private void setBarcodeCount(Sales sales, SalesDetail salesDetail, String str) {
        if (SalesUtils.isSalesTypeOrder(sales.getmSalesType())) {
            List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(OrderDetail.class, "SALESFICHEID=? AND ITEMCODE=?", new String[]{String.valueOf(sales.getLogicalRef()), str});
            if (table.size() > 0) {
                salesDetail.setBarcodeCount(((OrderDetail) table.get(0)).getBarcodeCount());
                return;
            }
            return;
        }
        List table2 = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(InvoiceDetail.class, "SALESFICHEID=? AND ITEMCODE=?", new String[]{String.valueOf(sales.getLogicalRef()), str});
        if (table2.size() > 0) {
            salesDetail.setBarcodeCount(((InvoiceDetail) table2.get(0)).getBarcodeCount());
        }
    }

    private void loadSearchBarcodesFromBackup(SalesDetail salesDetail, HashMap<Integer, ArrayList<String>> map) {
        if (map.isEmpty() || !map.containsKey(Integer.valueOf(salesDetail.getLineNr()))) {
            return;
        }
        salesDetail.setSearchBarcodes(map.get(Integer.valueOf(salesDetail.getLineNr())));
    }

    private HashMap<Integer, ArrayList<String>> backupSearchBarcodes(ArrayList<SalesDetail> arrayList) {
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        List<SalesDetail> list = (List) arrayList.stream().filter(new java.util.function.Predicate() {
            public boolean test(Object obj) {
                return NetsisRestAsyncTask.lambdabackupSearchBarcodes126((SalesDetail) obj);
            }
        }).collect(Collectors.toList());
        if (list.isEmpty()) {
            return map;
        }
        for (SalesDetail salesDetail : list) {
            map.put(Integer.valueOf(salesDetail.getLineNr() + 1), salesDetail.getSearchBarcodes());
        }
        return map;
    }
    public static boolean lambdabackupSearchBarcodes126(SalesDetail salesDetail) {
        return !salesDetail.getSearchBarcodes().isEmpty();
    }
    private SalesDetail parseDetail(ItemSlipLine itemSlipLine, Sales sales, NetsisInvoiceType netsisInvoiceType) {
        List table;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        SalesDetail salesDetail = new SalesDetail();
        salesDetail.setLineNr(itemSlipLine.getSira());
        salesDetail.setCode(itemSlipLine.getStokKodu());
        salesDetail.setProduct(true);
        List table2 = baseErp.getLogoSqlHelper().getTable(WareHouse.class, "CODE=?", new String[]{String.valueOf(itemSlipLine.getDEPOKODU())});
        if (table2 != null && table2.size() > 0) {
            salesDetail.setWareHouse(new FicheRefProp(((WareHouse) table2.get(0)).getLogicalRef(), -1, ((WareHouse) table2.get(0)).getAmbar()));
        }
        List table3 = baseErp.getLogoSqlHelper().getTable(Item.class, "CODE=?", new String[]{itemSlipLine.getStokKodu()});
        if (table3 != null && table3.size() > 0) {
            salesDetail.setName(((Item) table3.get(0)).name);
            salesDetail.setItemRef(((Item) table3.get(0)).logicalRef);
            salesDetail.setCardType(((Item) table3.get(0)).cardType);
            List table4 = baseErp.getLogoSqlHelper().getTable(ItemStock.class, "ITEMCODE=? AND WAREHOUSENR=?", new String[]{salesDetail.getCode(), Integer.toString(salesDetail.getWareHouse().getLogicalRef())});
            if (table4 != null && table4.size() > 0) {
                Iterator it = table4.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ItemStock itemStock = (ItemStock) it.next();
                    if (itemStock.wareHouseNr == salesDetail.getWareHouse().getLogicalRef()) {
                        salesDetail.setActualStock(itemStock.realStock);
                        break;
                    }
                }
            }
        }
        if (itemSlipLine.isKoliStok()) {
            salesDetail.lineType = LineType.COMPOSITE_COLI.value;
        }
        salesDetail.setAmount(new FicheStringProp(String.valueOf(itemSlipLine.getSTraGCMIK())));
        salesDetail.setSurplusAmount(new FicheStringProp(String.valueOf(itemSlipLine.getSTraMALFISK())));
        List table5 = baseErp.getLogoSqlHelper().getTable(ItemUnits.class, "ITEMCODE=? AND CODE=?", new String[]{salesDetail.getCode(), itemSlipLine.getOBR1()});
        if (table5 != null && table5.size() > 0) {
            salesDetail.setUnit(new FicheDiscountRefProp(((ItemUnits) table5.get(0)).logicalRef, -1, ((ItemUnits) table5.get(0)).code, ((ItemUnits) table5.get(0)).code));
            salesDetail.setConvFact1(((ItemUnits) table5.get(0)).convfact1);
            salesDetail.setConvFact2(((ItemUnits) table5.get(0)).convfact2);
        }
        salesDetail.setPrice(new FicheRefProp(-1, -1, StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraDOVTIP() == 0 ? itemSlipLine.getSTraBF() : itemSlipLine.getSTraDOVFIAT()))));
        salesDetail.setCalculateCurrPrice(itemSlipLine.getSTraBF());
        salesDetail.setVat(new FicheStringProp(String.valueOf(itemSlipLine.getSTraKDV())));
        salesDetail.setIncludeVat(sales.getIncludeVat());
        salesDetail.setDeliveryDate(new FicheDateProp(itemSlipLine.getSTraTestar()));
        FicheDiscountRefProp discountCard = getDiscountCard(itemSlipLine.getStraKosulK());
        if (itemSlipLine.getIskFlag() == 0) {
            salesDetail.getDiscountRatio(0);
            FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraSatIsk())));
            salesDetail.getDiscountRatio(0).setBoundedToCard(discountCard != null);
        } else {
            salesDetail.getDiscountTotal(0);
            FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraSatIsk())));
            salesDetail.getDiscountTotal(0).setBoundedToCard(discountCard != null);
        }
        salesDetail.getDiscountRatio(1);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraSatIsk2())));
        salesDetail.getDiscountRatio(1).setBoundedToCard(discountCard != null);
        salesDetail.getDiscountRatio(2);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraSatIsk3())));
        salesDetail.getDiscountRatio(2).setBoundedToCard(discountCard != null);
        salesDetail.getDiscountRatio(3);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraSatIsk4())));
        salesDetail.getDiscountRatio(3).setBoundedToCard(discountCard != null);
        salesDetail.getDiscountRatio(4);
        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraSatIsk5())));
        salesDetail.getDiscountRatio(4).setBoundedToCard(discountCard != null);
        salesDetail.setDiscountCard(0, discountCard);
        salesDetail.setSipNum(itemSlipLine.getSTraFATIRSNO());
        salesDetail.setSipKont(itemSlipLine.getSTraSIPKONT());
        salesDetail.setOrderDetailReference(itemSlipLine.getSTraSIPNUM() != null ? itemSlipLine.getIncKeyNo() : 0);
        salesDetail.prCurrType = itemSlipLine.getSTraDOVTIP();
        salesDetail.curCodeStr = itemSlipLine.getSTraDovizAdi();
        salesDetail.setCurrType(new FicheRefProp(itemSlipLine.getSTraDOVTIP(), -1, itemSlipLine.getSTraDovizAdi()));
        salesDetail.setPrRate(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getCurrRate(salesDetail.getPrCurrType()));
        if (!TextUtils.isEmpty(itemSlipLine.getYapKod()) && (table = baseErp.getLogoSqlHelper().getTable(Variant.class, "ITEMCODE = ? AND CODE = ?", new String[]{salesDetail.getCode(), itemSlipLine.getYapKod()})) != null && table.size() > 0) {
            FicheDiscountRefProp ficheDiscountRefProp = new FicheDiscountRefProp();
            ficheDiscountRefProp.setCode(itemSlipLine.getYapKod());
            FicheStringProp.setDefinition(((Variant) table.get(0)).getName());
            ficheDiscountRefProp.setLogicalRef(((Variant) table.get(0)).getLogicalRef());
            salesDetail.setVariant(ficheDiscountRefProp);
        }
        if (sales.getmSalesType() == SalesType.ORDER) {
            sales.setSalesStatus((netsisInvoiceType == NetsisInvoiceType.ft_Acik ? InvoiceStatus.ACTUAL : InvoiceStatus.OFFER).getStatus());
            if (!TextUtils.isEmpty(itemSlipLine.getSTraSIPTURU()) && itemSlipLine.getSTraSIPTURU().equals(ExifInterface.LATITUDE_SOUTH)) {
                FicheBooleanProp ficheBooleanProp = new FicheBooleanProp();
                ficheBooleanProp.setSelect(true);
                salesDetail.setReserve(ficheBooleanProp);
            }
        }
        if (!TextUtils.isEmpty(itemSlipLine.getVadetar())) {
            salesDetail.setDueDate(new FicheDateProp(DateAndTimeUtils.convertReportDateToSimpleDate(itemSlipLine.getVadetar())));
        }
        parseSerialLines(itemSlipLine, salesDetail);
        salesDetail.calculateFiche(sales.isNotUseGattribKdv());
        salesDetail.setSalesType(sales.getMSalesType());
        return salesDetail;
    }

    private void parseSerialLines(ItemSlipLine itemSlipLine, SalesDetail salesDetail) {
        if (itemSlipLine.getKalemSeri() == null || itemSlipLine.getKalemSeri().isEmpty()) {
            return;
        }
        salesDetail.setSalesSerialLots(new ArrayList<>());
        for (ItemSlipSerialLine itemSlipSerialLine : itemSlipLine.getKalemSeri()) {
            Serilot serilot = new Serilot();
            serilot.code = itemSlipSerialLine.getSerial1();
            serilot.amount = itemSlipSerialLine.getAmount();
            serilot.unit = (salesDetail.getUnit() == null || TextUtils.isEmpty(salesDetail.getUnit().getCode())) ? "" : salesDetail.getUnit().getCode();
            salesDetail.getSalesSerialLots().add(serilot);
        }
    }

    private void setHeaderDiscountTotals(Sales sales, ItemSlipHeader itemSlipHeader) {
        String[] strArr = {itemSlipHeader.getGeneralDiscount() > 0.0d ? String.valueOf(itemSlipHeader.getGeneralDiscount()) : "0", itemSlipHeader.getGeneralDiscount2() > 0.0d ? String.valueOf(itemSlipHeader.getGeneralDiscount2()) : "0", itemSlipHeader.getGeneralDiscount3() > 0.0d ? String.valueOf(itemSlipHeader.getGeneralDiscount3()) : "0"};
        FicheDiscountRefProp discountCard = getDiscountCard(itemSlipHeader.getDiscountCode());
        for (int i2 = 0; i2 < 3; i2++) {
            sales.getDiscountTotal(i2);
            FicheStringProp.setDefinition(strArr[i2]);
            sales.getDiscountTotal(i2).setBoundedToCard(!strArr[i2].equals("0") && discountCard != null);
        }
    }

    private void setHeaderDiscountRatios(Sales sales, ItemSlipHeader itemSlipHeader) {
        String[] strArr = {itemSlipHeader.getGeneralDiscountRatio() > 0.0d ? String.valueOf(itemSlipHeader.getGeneralDiscountRatio()) : "0", itemSlipHeader.getGeneralDiscountRatio2() > 0.0d ? String.valueOf(itemSlipHeader.getGeneralDiscountRatio2()) : "0", itemSlipHeader.getGeneralDiscountRatio3() > 0.0d ? String.valueOf(itemSlipHeader.getGeneralDiscountRatio3()) : "0"};
        FicheDiscountRefProp discountCard = getDiscountCard(itemSlipHeader.getDiscountCode());
        for (int i2 = 0; i2 < 3; i2++) {
            sales.getDiscountRatio(i2);
            FicheStringProp.setDefinition(strArr[i2]);
            sales.getDiscountRatio(i2).setBoundedToCard(!strArr[i2].equals("0") && discountCard != null);
        }
    }

    private void setHeaderDiscountCards(Sales sales, ItemSlipHeader itemSlipHeader) {
        FicheDiscountRefProp discountCard = getDiscountCard(itemSlipHeader.getDiscountCode());
        if (discountCard == null) {
            return;
        }
        boolean[] zArr = {itemSlipHeader.getGeneralDiscountRatio() + itemSlipHeader.getGeneralDiscount() > 0.0d, itemSlipHeader.getGeneralDiscountRatio2() + itemSlipHeader.getGeneralDiscount2() > 0.0d, itemSlipHeader.getGeneralDiscountRatio3() + itemSlipHeader.getGeneralDiscount3() > 0.0d};
        for (int i2 = 0; i2 < 3; i2++) {
            if (zArr[i2]) {
                sales.getDiscountCard(i2);
                FicheStringProp.setDefinition(discountCard.getDefinition());
                sales.getDiscountCard(i2).setCode(discountCard.getCode());
                sales.getDiscountCard(i2).setLogicalRef(discountCard.getLogicalRef());
                sales.getDiscountCard(i2).setWhich(discountCard.getWhich());
            }
        }
    }
    private void updateFiche(NetsisServiceResult netsisServiceResult) {
        if (netsisServiceResult.getDataType() == DataObjectType.ADDORDER) {
            setOrderInvoiceType(netsisServiceResult);
        }
        calculateFiche(netsisServiceResult);
        Sales sales = (Sales) netsisServiceResult.getData();
        ErpCreator.getInstance().getmBaseErp().updateSales(sales, sales.getmSalesType(), false);
    }
    private void setOrderInvoiceType(NetsisServiceResult netsisServiceResult) {
        try {
            ItemSlip itemSlip = (ItemSlip) netsisServiceResult.getSendData();
            if (itemSlip == null) {
                return;
            }
            NetsisSelectResult orderStatus = ((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getOrderStatus(itemSlip.getSlipHeader().getSlipNo(), itemSlip.getSlipHeader().getCustomerCode());
            lambdagetSelectRxn24();
            if (!orderStatus.isSuccess() || orderStatus.getDataList().size() <= 0) {
                return;
            }
            itemSlip.getSlipHeader().setNetsisInvoiceType(NetsisInvoiceType.values()[((OrderFicheStatus) orderStatus.getDataList().get(0)).getStatus()]);
        } catch (Exception e2) {
            Log.e(TAG, "checkOrderType", e2);
        }
    }

    private FicheDiscountRefProp getDiscountCard(int i2) {
        List table;
        if (i2 <= 0 || (table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Discount.class, "LOGICALREF=?", new String[]{String.valueOf(i2)})) == null || table.size() <= 0) {
            return null;
        }
        return new FicheDiscountRefProp(((Discount) table.get(0)).getLogicalRef(), -1, ((Discount) table.get(0)).getCode(), ((Discount) table.get(0)).getCode());
    }

    private FicheDiscountRefProp getDiscountCard(String str) {
        List table;
        if (TextUtils.isEmpty(str) || (table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(Discount.class, "CODE=?", new String[]{str})) == null || table.size() <= 0) {
            return null;
        }
        return new FicheDiscountRefProp(((Discount) table.get(0)).getLogicalRef(), -1, ((Discount) table.get(0)).getIndirim(), ((Discount) table.get(0)).getCode());
    }


    public void updateFicheStatus(NetsisSelectResult netsisSelectResult) {
        if (netsisSelectResult.isSuccess()) {
            if (netsisSelectResult.getProcessType() == ProcessType.INSERTWORPROCESS) {
                updateTodoWorProc(netsisSelectResult.getLogicalRef());
            } else {
                updateFicheTransferAndFicheRef(netsisSelectResult.getLogicalRef(), netsisSelectResult.getProcessType());
            }
            if (netsisSelectResult.getProcessType() == ProcessType.INSERTVISIT) {
                try {
                    NetsisSelectResult netsisSelectResultLambdagetSelectRxn24 = lambdagetSelectRxn24();
                    if (netsisSelectResultLambdagetSelectRxn24.isSuccess() && netsisSelectResultLambdagetSelectRxn24.getDataList().size() > 0) {
                        updateVisitAfterTransfer(netsisSelectResult.getLogicalRef(), ((DocNo) netsisSelectResultLambdagetSelectRxn24.getDataList().get(0)).getLogicalRef());
                    }
                } catch (Exception e2) {
                    Log.e(TAG, e2.getMessage());
                }
            }
            Log.d(TAG, "sendFiche: updated");
        }
    }

    private void updateTodoWorProc(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("LATITUDE", 0);
        contentValues.put("LONGTITUDE", 0);
        contentValues.put("ISTRANSFERWORPROC", "1");
        ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getWritableDatabase().update("TODOINFO", contentValues, "LOGICALREF=" + i2, null);
    }
    public void lambdasendSalesFiche100(GroupItem groupItem, boolean z) {
        Log.d(TAG, "sendFiche: complete");
        if (z) {
            ContextUtils.showToast(R.string.str_fiche_send_complete);
        }
        groupItem.setComplete(true);
    }

    public void getManageOrders(int i2, int i3, final ResponseListener responseListener) {
        getApi().getItemSlipsByTypeRx(i2, i3, NetsisSlipType.ftSSip.ordinal(), "TIPI!=2").doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).map(new Function() {
            public Object apply(Object obj) {
                return this.f0.lambdagetManageOrders127((DataResponse) obj);
            }
        }).onErrorReturnItem(new ArrayList()).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetManageOrders129(responseListener, (Throwable) obj);
            }
        });
    }
    public List lambdagetManageOrders127(DataResponse dataResponse) throws Exception {
        return itemSlipToManageOrders((List) dataResponse.getData());
    }
    public static void lambdagetManageOrders129(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }

    private List<ManagerOrder> itemSlipToManageOrders(List<ItemSlip> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ItemSlip> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(itemSlipToManageOrder(it.next()));
        }
        return arrayList;
    }

    private ManagerOrder itemSlipToManageOrder(ItemSlip itemSlip) {
        ManagerOrder managerOrder = new ManagerOrder();
        managerOrder.setFicheNo(itemSlip.getSlipHeader().getSlipNo());
        managerOrder.setDate(itemSlip.getSlipHeader().getDate());
        managerOrder.setExplanation(itemSlip.getSlipHeader().getExplanation());
        managerOrder.setPaymentDefinition(itemSlip.getSlipHeader().getPaymentCode());
        managerOrder.setTotal(itemSlip.getSlipHeader().getGeneralTotal());
        managerOrder.setcCode(itemSlip.getSlipHeader().getCustomerCode());
        managerOrder.setcName(ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getClName(managerOrder.getcCode()));
        return managerOrder;
    }

    public void sendAllOfflineCustomers(List<CustomerNew> list, final ICustomerSendCompleted iCustomerSendCompleted) {
        final StringBuilder sb = new StringBuilder();
        Observable.just(list).flatMapIterable(new Function() {
            public Object apply(Object obj) {
                return NetsisRestAsyncTask.lambdasendAllOfflineCustomers130((List) obj);
            }
        }).flatMap(new Function() {
            public Object apply(Object obj) {
                return this.f0.lambdasendAllOfflineCustomers131((CustomerNew) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<DataResponse<Cari>>() {
            public void onSubscribe(Disposable disposable) {
            }
            public void onNext(DataResponse<Cari> dataResponse) {
                if (dataResponse.isSuccessful()) {
                    CustomerNew customerNewCariTemelBilgiToCustomerNew = NetsisRestAsyncTask.this.cariTemelBilgiToCustomerNew(dataResponse.getData());
                    NetsisRestAsyncTask.this.lambdasendNewCustomer146(customerNewCariTemelBilgiToCustomerNew);
                    Log.d(NetsisRestAsyncTask.TAG, " onNext : value : " + dataResponse.getData().getCariTemelBilgi().getCode());
                    NetsisRestAsyncTask.this.sendCustomerInCharge(customerNewCariTemelBilgiToCustomerNew.getCode().toString());
                    return;
                }
                Log.e(NetsisRestAsyncTask.TAG, " onNext : error : " + dataResponse.getErrorDesc());
                sb.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
            }
            public void onError(Throwable th) {
                Log.d(NetsisRestAsyncTask.TAG, " onError : " + th.getMessage());
            }
            public void onComplete() {
                iCustomerSendCompleted.onCustomerSendCompleted(new ResponseEvent(sb.toString(), sb.length() == 0));
            }
        });
    }
    public ObservableSource lambdasendAllOfflineCustomers131(CustomerNew customerNew) throws Exception {
        CariTemelBilgi cariTemelBilgi = new CariTemelBilgi(customerNew);
        cariTemelBilgi.setCardType(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        CariEkBilgi cariEkBilgi = new CariEkBilgi();
        cariEkBilgi.setCode(customerNew.getCode().toString());
        cariEkBilgi.setOdekod(customerNew.getPayPlan().toString());
        cariEkBilgi.setTckimlikno(customerNew.getTCNo().toString());
        Cari cari = new Cari();
        cari.setCariTemelBilgi(cariTemelBilgi);
        cari.setCariEkBilgi(cariEkBilgi);
        return getApi().postArps(cari);
    }

    public void getPrintItemSlipInfo(PrintSlipParams printSlipParams, final ResponseListener<PrintSlipModel> responseListener) {
        final Observable<DataResponse<ItemSlip>> itemSlipRx = getApi().getItemSlipRx(printSlipParams.getKey());
        final Observable<DataResponse<Cari>> arp = getApi().getArp(printSlipParams.getClCode());
        final Observable<DataResponse<Cari>> arp2 = getApi().getArp(printSlipParams.getShipmentClCode());
        final Observable<NetsisDataHeader> queriesRx = getApi().getQueriesRx(new NetsisTSql(((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getPrintSlipExtraInfo(printSlipParams).getSql()));
        if (printSlipParams.getSlipType() == NetsisSlipType.ftLokalDepo.ordinal()) {
            Observable observableSubscribeOn = Observable.defer(new Callable() {
                public Object call() {
                    return this.f0.lambdagetPrintItemSlipInfo133(itemSlipRx, queriesRx);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            Objects.requireNonNull(responseListener);
            observableSubscribeOn.subscribe(new Consumer() {
                public void accept(Object obj) {
                    responseListener.onResponse((PrintSlipModel) obj);
                }
            }, new Consumer() {
                public void accept(Object obj) throws Exception {
                    NetsisRestAsyncTask.lambdagetPrintItemSlipInfo134(responseListener, (Throwable) obj);
                }
            });
        } else if (TextUtils.isEmpty(printSlipParams.getShipmentClCode())) {
            Observable observableSubscribeOn2 = Observable.defer(new Callable() {
                public Object call() {
                    return this.f0.lambdagetPrintItemSlipInfo136(itemSlipRx, arp, queriesRx);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            Objects.requireNonNull(responseListener);
            observableSubscribeOn2.subscribe(new Consumer() {
                public void accept(Object obj) {
                    responseListener.onResponse((PrintSlipModel) obj);
                }
            }, new Consumer() {
                public void accept(Object obj) throws Exception {
                    NetsisRestAsyncTask.lambdagetPrintItemSlipInfo137(responseListener, (Throwable) obj);
                }
            });
        } else {
            Observable observableSubscribeOn3 = Observable.defer(new Callable() {
                public Object call() {
                    return this.f0.lambdagetPrintItemSlipInfo139(itemSlipRx, arp, arp2, queriesRx);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            Objects.requireNonNull(responseListener);
            observableSubscribeOn3.subscribe(new Consumer() {
                public void accept(Object obj) {
                    responseListener.onResponse((PrintSlipModel) obj);
                }
            }, new Consumer() {
                public void accept(Object obj) throws Exception {
                    NetsisRestAsyncTask.lambdagetPrintItemSlipInfo140(responseListener, (Throwable) obj);
                }
            });
        }
    }
    public ObservableSource lambdagetPrintItemSlipInfo133(Observable observable, Observable observable2) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), new BiFunction() {
            public Object apply(Object obj, Object obj2) {
                return this.f0.lambdagetPrintItemSlipInfo132((DataResponse) obj, (NetsisDataHeader) obj2);
            }
        });
    }
    public PrintSlipModel lambdagetPrintItemSlipInfo132(DataResponse dataResponse, NetsisDataHeader netsisDataHeader) throws Exception {
        return new PrintSlipModel((ItemSlip) dataResponse.getData(), toListData(PrintExtraInfo.class, netsisDataHeader.getData()));
    }


    public static void lambdagetPrintItemSlipInfo134(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public ObservableSource lambdagetPrintItemSlipInfo136(Observable observable, Observable observable2, Observable observable3) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), new Function3() {
            public Object apply(Object obj, Object obj2, Object obj3) {
                return this.f0.lambdagetPrintItemSlipInfo135((DataResponse) obj, (DataResponse) obj2, (NetsisDataHeader) obj3);
            }
        });
    }
    public PrintSlipModel lambdagetPrintItemSlipInfo135(DataResponse dataResponse, DataResponse dataResponse2, NetsisDataHeader netsisDataHeader) throws Exception {
        return new PrintSlipModel((ItemSlip) dataResponse.getData(), (Cari) dataResponse2.getData(), null, toListData(PrintExtraInfo.class, netsisDataHeader.getData()));
    }
    public static void lambdagetPrintItemSlipInfo137(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public ObservableSource lambdagetPrintItemSlipInfo139(Observable observable, Observable observable2, Observable observable3, Observable observable4) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), observable4.subscribeOn(Schedulers.newThread()), new Function4() {
            public Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                return this.f0.lambdagetPrintItemSlipInfo138((DataResponse) obj, (DataResponse) obj2, (DataResponse) obj3, (NetsisDataHeader) obj4);
            }
        });
    }
    public PrintSlipModel lambdagetPrintItemSlipInfo138(DataResponse dataResponse, DataResponse dataResponse2, DataResponse dataResponse3, NetsisDataHeader netsisDataHeader) throws Exception {
        return new PrintSlipModel((ItemSlip) dataResponse.getData(), (Cari) dataResponse2.getData(), (Cari) dataResponse3.getData(), toListData(PrintExtraInfo.class, netsisDataHeader.getData()));
    }

    public static void lambdagetPrintItemSlipInfo140(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }

    public void readInvoiceFiche(String str, final Sales sales, final ResponseListener responseListener) {
        final StringBuilder sb = new StringBuilder();
        getApi().getItemSlipRx(str).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<DataResponse<ItemSlip>>() {
            public void onSubscribe(Disposable disposable) {
            }
            public void onNext(DataResponse<ItemSlip> dataResponse) {
                if (!dataResponse.isSuccessful()) {
                    Log.e(NetsisRestAsyncTask.TAG, " onNext : error : " + dataResponse.getErrorDesc());
                    sb.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
                    return;
                }
                itemSlipToSales(sales, dataResponse.getData());
                Log.d(NetsisRestAsyncTask.TAG, " onNext : value : " + dataResponse.getData().getSlipHeader().getOrderNumber());
            }
            public void onError(Throwable th) {
                Log.d(NetsisRestAsyncTask.TAG, " onError : " + th.getMessage());
                sb.append(th.getMessage());
            }
            public void onComplete() {
                if (sb.length() > 0) {
                    responseListener.onError(sb.toString());
                } else {
                    responseListener.onResponse(sales);
                }
            }
        });
    }

    public void readOrderFiches(ArrayList<String> arrayList, DataObjectType dataObjectType, final Sales sales, final List list, final ResponseListener responseListener) {
        final StringBuilder sb = new StringBuilder();
        Observable.just(arrayList).flatMapIterable(new Function() {
            public Object apply(Object obj) {
                return NetsisRestAsyncTask.lambdareadOrderFiches141((ArrayList) obj);
            }
        }).flatMap(new Function() {
            public Object apply(Object obj) {
                return this.f0.lambdareadOrderFiches142((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<DataResponse<ItemSlip>>() {
            public void onSubscribe(Disposable disposable) {
            }
            public void onNext(DataResponse<ItemSlip> dataResponse) {
                if (!dataResponse.isSuccessful()) {
                    Log.e(NetsisRestAsyncTask.TAG, " onNext : error : " + dataResponse.getErrorDesc());
                    sb.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
                    return;
                }
                NetsisRestAsyncTask.this.itemSlipToSales(sales, dataResponse.getData(), list);
                Log.d(NetsisRestAsyncTask.TAG, " onNext : value : " + dataResponse.getData().getSlipHeader().getOrderNumber());
            }
            public void onError(Throwable th) {
                Log.d(NetsisRestAsyncTask.TAG, " onError : " + th.getMessage());
                sb.append(th.getMessage());
            }
            public void onComplete() {
                if (sb.length() > 0) {
                    responseListener.onError(sb.toString());
                } else {
                    sales.calculateSalesTotal();
                    responseListener.onResponse(sales);
                }
            }
        });
    }
    public ObservableSource lambdareadOrderFiches142(String str) throws Exception {
        return getApi().getSalesOrderRx(str);
    }

    public static void itemSlipToSales(Sales sales, ItemSlip itemSlip) {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        try {
            sales.setEDocSerial(new FicheRefProp(-1, -1, itemSlip.getSerial()));
            getSlipHeader(baseErp, sales, itemSlip);
            getSlipDetails(baseErp, sales, itemSlip, null);
            sales.calculateSalesTotal();
            sales.getDiscountTotal(0);
            FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscount() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscount()) : "0");
            sales.getDiscountTotal(1);
            FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscount2() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscount2()) : "0");
            sales.getDiscountTotal(2);
            FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscount3() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscount3()) : "0");
            sales.getDiscountRatio(0);
            FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscountRatio() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscountRatio()) : "0");
            sales.getDiscountRatio(1);
            FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscountRatio2() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscountRatio2()) : "0");
            sales.getDiscountRatio(2);
            FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscountRatio3() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscountRatio3()) : "0");
            sales.setDiscTotal(itemSlip.getSlipHeader().getFazDiscount() + itemSlip.getSlipHeader().getGeneralDiscount() + itemSlip.getSlipHeader().getGeneralDiscount2() + itemSlip.getSlipHeader().getGeneralDiscount3() + itemSlip.getSlipHeader().getDetailDiscount());
            sales.setGrossTotal(itemSlip.getSlipHeader().getGrossTotal());
            sales.setTotalVat(itemSlip.getSlipHeader().getVat());
            sales.setTotalNet(itemSlip.getSlipHeader().getGeneralTotal());
            sales.setTotal(itemSlip.getSlipHeader().getGrossTotal() - sales.getDiscTotal());
            sales.setFicheNo(itemSlip.getSlipHeader().getSlipNo());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    public void itemSlipToSales(Sales sales, final ItemSlip itemSlip, List<OrderAvailableAmount> list) {
        List table;
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        try {
            new Sales();
            if (sales.getMSalesDetailList().size() == 0) {
                getSlipHeader(baseErp, sales, itemSlip);
            }
            Sales salesM820clone = sales.clone();
            salesM820clone.getMSalesDetailList().clear();
            salesM820clone.setEDocSerial(new FicheRefProp(-1, -1, itemSlip.getSerial()));
            getSlipHeader(baseErp, sales, itemSlip);
            if (list != null && list.size() > 0) {
                getSlipDetails(baseErp, salesM820clone, itemSlip, new ArrayList(Collections2.filter(list, new com.google.common.base.Predicate() {
                    public boolean apply(Object obj) {
                        return NetsisRestAsyncTask.lambdaitemSlipToSales143(itemSlip, (OrderAvailableAmount) obj);
                    }
                })));
            } else {
                getSlipDetails(baseErp, salesM820clone, itemSlip, Collections.emptyList());
            }
            salesM820clone.calculateSalesTotal();
            double generalDiscount = itemSlip.getSlipHeader().getGeneralDiscount() + itemSlip.getSlipHeader().getGeneralDiscount2() + itemSlip.getSlipHeader().getGeneralDiscount3();
            if (list != null && list.size() > 0) {
                for (int i2 = 0; i2 < salesM820clone.getMSalesDetailList().size(); i2++) {
                    SalesDetail salesDetail = salesM820clone.getMSalesDetailList().get(i2);
                    int i3 = 0;
                    while (true) {
                        if (i3 >= salesDetail.getDiscountLength()) {
                            break;
                        }
                        if (!salesDetail.getDiscountTotal(i3).getDefinition().isEmpty() && salesDetail.getDiscountRatio(i3).getDefinition().isEmpty() && salesDetail.getDiscountCard(i3).getDefinition().isEmpty()) {
                            salesDetail.getDiscountTotal(i3);
                            FicheStringProp.setDefinition(StringUtils.convertDoubleToStringDot(Double.valueOf((salesDetail.getAmount().getDefinitionDouble() / salesDetail.getOrderAmount()) * salesDetail.getDiscountTotal(i3).getDefinitionDouble())));
                        }
                        if (itemSlip.getSlipHeader().getGeneralTotal() > 0.0d && salesDetail.getDiscountRatio(i3).getDefinitionDouble() <= 0.0d) {
                            salesDetail.getDiscountTotal(i3);
                            FicheStringProp.setDefinition(StringUtils.convertDoubleToStringDot(Double.valueOf((salesDetail.getProductTotal() / salesM820clone.getGrossTotal()) * generalDiscount)));
                            salesDetail.getDiscountTotal(i3).setGuid("");
                            break;
                        }
                        i3++;
                    }
                }
            } else {
                sales.getDiscountTotal(0);
                FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscount() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscount()) : "0");
                sales.getDiscountTotal(1);
                FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscount2() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscount2()) : "0");
                sales.getDiscountTotal(2);
                FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscount3() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscount3()) : "0");
                sales.getDiscountRatio(0);
                FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscountRatio() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscountRatio()) : "0");
                sales.getDiscountRatio(1);
                FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscountRatio2() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscountRatio2()) : "0");
                sales.getDiscountRatio(2);
                FicheStringProp.setDefinition(itemSlip.getSlipHeader().getGeneralDiscountRatio3() > 0.0d ? String.valueOf(itemSlip.getSlipHeader().getGeneralDiscountRatio3()) : "0");
                sales.setDiscTotal(itemSlip.getSlipHeader().getFazDiscount() + itemSlip.getSlipHeader().getGeneralDiscount() + itemSlip.getSlipHeader().getGeneralDiscount2() + itemSlip.getSlipHeader().getGeneralDiscount3() + itemSlip.getSlipHeader().getDetailDiscount());
                sales.setGrossTotal(itemSlip.getSlipHeader().getGrossTotal());
                sales.setTotalVat(itemSlip.getSlipHeader().getVat());
                sales.setTotalNet(itemSlip.getSlipHeader().getGeneralTotal());
                sales.setTotal(itemSlip.getSlipHeader().getGrossTotal() - salesM820clone.getDiscTotal());
            }
            Iterator<SalesDetail> it = salesM820clone.getMSalesDetailList().iterator();
            while (it.hasNext()) {
                sales.getMSalesDetailList().add(it.next().clone());
            }
            if (list == null || list.isEmpty() || (table = baseErp.getLogoSqlHelper().getTable(WareHouse.class, "NR=?", new String[]{String.valueOf(sales.getMSalesDetailList().get(0).getWareHouse().getLogicalRef())})) == null || table.isEmpty()) {
                return;
            }
            sales.setWareHouse(new FicheRefProp(((WareHouse) table.get(0)).getLogicalRef(), -1, ((WareHouse) table.get(0)).getAmbar()));
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean lambdaitemSlipToSales143(ItemSlip itemSlip, OrderAvailableAmount orderAvailableAmount) {
        return orderAvailableAmount.getCode().equals(Integer.toString(itemSlip.getSlipHeader().getSlipType().ordinal()) + ';' + itemSlip.getSlipHeader().getSlipNo() + ";" + itemSlip.getSlipHeader().getCustomerCode());
    }

    public void getSlipHeader(BaseErp baseErp, Sales sales, ItemSlip itemSlip) {
        List table;
        List table2;
        List table3;
        List table4;
        List table5;
        sales.setEDespatch(new FicheBooleanProp(itemSlip.getSlipHeader().iseDespatch()));
        sales.setExplanation1(new FicheStringProp(itemSlip.getSlipHeader().getExplanation()));
        sales.setExplanation2(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation1()));
        sales.setExplanation3(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation2()));
        sales.setExplanation4(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation3()));
        sales.setExplanation5(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation4()));
        sales.setExplanation6(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation5()));
        sales.setExplanation7(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation6()));
        sales.setExplanation8(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation7()));
        sales.setExplanation9(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation8()));
        sales.setExplanation10(new FicheStringProp(itemSlip.getSlipHeader().getAppendixExplanation9()));
        FicheDiscountRefProp discountCard = getDiscountCard(itemSlip.getSlipHeader().getDiscountCode());
        if (discountCard == null) {
            discountCard = new FicheDiscountRefProp();
        }
        sales.getDiscountCard(0);
        FicheStringProp.setDefinition(discountCard.getDefinition());
        sales.getDiscountCard(0).setCode(discountCard.getCode());
        sales.getDiscountCard(0).setLogicalRef(discountCard.getLogicalRef());
        sales.getDiscountCard(0).setWhich(discountCard.getWhich());
        if (itemSlip.getSlipHeader().getProjectCode() != null && !itemSlip.getSlipHeader().getProjectCode().equals("") && (table5 = baseErp.getLogoSqlHelper().getTable(Project.class, "CODE=?", new String[]{itemSlip.getSlipHeader().getProjectCode()})) != null && table5.size() > 0) {
            sales.setProjectCode(new FicheDiscountRefProp(((Project) table5.get(0)).getLogicalRef(), -1, ((Project) table5.get(0)).getProje(), ((Project) table5.get(0)).getCode()));
        }
        if (itemSlip.getSlipHeader().getPaymentCode() != null && !itemSlip.getSlipHeader().getPaymentCode().equals("") && (table4 = baseErp.getLogoSqlHelper().getTable(Payment.class, "CODE=?", new String[]{itemSlip.getSlipHeader().getPaymentCode()})) != null && table4.size() > 0) {
            sales.setPayPlan(new FicheDiscountRefProp(((Payment) table4.get(0)).getLogicalRef(), -1, ((Payment) table4.get(0)).getOdemePlan(), ((Payment) table4.get(0)).getCode()));
        }
        if (itemSlip.getSlipHeader().getCustomerCode2() != null && !itemSlip.getSlipHeader().getCustomerCode2().equals("") && (table3 = baseErp.getLogoSqlHelper().getTable(ClCard.class, "CODE=?", new String[]{itemSlip.getSlipHeader().getCustomerCode2()})) != null && table3.size() > 0) {
            sales.setShipAccount(new FicheDiscountRefProp(((ClCard) table3.get(0)).getLogicalRef(), -1, ((ClCard) table3.get(0)).getDefinition(), ((ClCard) table3.get(0)).getCode()));
        }
        if (itemSlip.getSlipHeader().getCode1() != null && !TextUtils.isEmpty(itemSlip.getSlipHeader().getCode1()) && (table2 = baseErp.getLogoSqlHelper().getTable(Specodes.class, "CODETYPE=1 AND SPECODETYPE=1 AND SPECODE=?", new String[]{itemSlip.getSlipHeader().getCode1()})) != null && table2.size() > 0) {
            sales.setFirstSpeCode(new FicheDiscountRefProp(((Specodes) table2.get(0)).getLogicalRef(), -1, ((Specodes) table2.get(0)).getDefinition(), ((Specodes) table2.get(0)).getSpeCode()));
        }
        if (itemSlip.getSlipHeader().getCode2() != null && !TextUtils.isEmpty(itemSlip.getSlipHeader().getCode2()) && (table = baseErp.getLogoSqlHelper().getTable(Specodes.class, "CODETYPE=1 AND SPECODETYPE=2 AND SPECODE=?", new String[]{itemSlip.getSlipHeader().getCode2()})) != null && table.size() > 0) {
            sales.setSecondSpeCode(new FicheDiscountRefProp(((Specodes) table.get(0)).getLogicalRef(), -1, ((Specodes) table.get(0)).getDefinition(), ((Specodes) table.get(0)).getSpeCode()));
        }
        sales.setIncludeVat(new FicheBooleanProp(itemSlip.getSlipHeader().isInclueVat()));
        sales.setDueDate(new FicheDateProp(DateAndTimeUtils.convertReportDateToSimpleDate(itemSlip.getSlipHeader().getPaymentDate())));
    }

    public void getSlipDetails(BaseErp baseErp, Sales sales, ItemSlip itemSlip, List<OrderAvailableAmount> list) {
        for (ItemSlipLine itemSlipLine : itemSlip.getLines()) {
            boolean z = true;
            boolean z2 = list == null;
            if (list == null || list.size() <= 0) {
                z = z2;
            } else {
                Iterator<OrderAvailableAmount> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().getOrderLineLogicalRef() == itemSlipLine.getIncKeyNo()) {
                        break;
                    }
                }
                z = z2;
            }
            if (z) {
                SalesDetail detail = parseDetail(itemSlipLine, sales, itemSlip.getSlipHeader().getNetsisInvoiceType());
                if (list != null && list.size() > 0) {
                    for (OrderAvailableAmount orderAvailableAmount : list) {
                        if (orderAvailableAmount.getOrderLineLogicalRef() == detail.getOrderDetailReference()) {
                            detail.setOrderAvailableAmount(orderAvailableAmount.getAvailableAmount());
                            detail.setOrderAmount(orderAvailableAmount.getOrderAmount());
                            detail.setAmount(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(orderAvailableAmount.getAvailableAmount()))));
                        }
                    }
                } else {
                    detail.setAmount(new FicheStringProp(StringUtils.convertDoubleToString(Double.valueOf(itemSlipLine.getSTraGCMIK()))));
                }
                if (itemSlipLine.getEkalanneden().equals("1")) {
                    detail.setName(itemSlipLine.getEkalan());
                }
                sales.addSalesDetailItems(detail);
            }
        }
    }

    public void sendNewCustomer(CustomerNew customerNew, final ResponseListener responseListener) {
        CariTemelBilgi cariTemelBilgi = new CariTemelBilgi(customerNew);
        cariTemelBilgi.setCardType(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        if (ErpCreator.getInstance().getmBaseErp() != null) {
            cariTemelBilgi.setPlasiyerKodu(ErpCreator.getInstance().getmBaseErp().getUser().getCode());
        }
        CariEkBilgi cariEkBilgi = new CariEkBilgi();
        cariEkBilgi.setCode(customerNew.getCode().toString());
        cariEkBilgi.setOdekod(customerNew.getPayPlan().toString());
        cariEkBilgi.setTckimlikno(customerNew.getTCNo().toString());
        Cari cari = new Cari();
        cari.setCariTemelBilgi(cariTemelBilgi);
        cari.setCariEkBilgi(cariEkBilgi);
        getApi().postArps(cari).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendNewCustomer144((DataResponse) obj);
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                return this.f0.lambdasendNewCustomer145((DataResponse) obj);
            }
        }).onErrorReturnItem(new CustomerNew()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer() {
            public void accept(Object obj) throws Exception {
                this.f0.lambdasendNewCustomer146((CustomerNew) obj);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdasendNewCustomer148(responseListener, (Throwable) obj);
            }
        });
    }

    public void lambdasendNewCustomer144(DataResponse dataResponse) throws Exception {
        if (dataResponse.isSuccessful()) {
            sendCustomerInCharge(((Cari) dataResponse.getData()).getCariTemelBilgi().getCode());
        }
    }

    public CustomerNew lambdasendNewCustomer145(DataResponse dataResponse) throws Exception {
        return cariTemelBilgiToCustomerNew((Cari) dataResponse.getData());
    }
    public static void lambdasendNewCustomer148(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }
    public void sendCustomerInCharge(String str) {
        final BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        List table = baseErp.getLogoSqlHelper().getTable(ClCardIncharge.class, "CLCODE=?", new String[]{str});
        if (table == null || table.size() <= 0) {
            return;
        }
        final ClCardIncharge clCardIncharge = (ClCardIncharge) table.get(0);
        try {
            getApi().getQueries(new NetsisTSql(((Netsis) baseErp).getQueryCreator().insertCustomerInCharge(clCardIncharge).getSqlWithWorCheck())).enqueue(new Callback<NetsisDataHeader>() {
                public void onResponse(Call<NetsisDataHeader> call, Response<NetsisDataHeader> response) {
                    Log.e(NetsisRestAsyncTask.TAG, "onResponse: sendCustomerInCharge");
                    if (response.isSuccessful()) {
                        NetsisRestAsyncTask.this.getApi().getQueries(new NetsisTSql(((Netsis) baseErp).getQueryCreator().getCustomerInChargeRecIDAfterTransfer(clCardIncharge).getSqlWithWorCheck())).enqueue(new Callback<NetsisDataHeader>() {
                            public void onResponse(Call<NetsisDataHeader> call2, Response<NetsisDataHeader> response2) {
                                if (!response2.isSuccessful() || response2.body() == null || response2.body().getData() == null) {
                                    return;
                                }
                                Iterator<NetsisData> it = response2.body().getData().iterator();
                                while (it.hasNext()) {
                                    Iterator<NetsisDataPrimitive> it2 = it.next().getNetsisDataPrimitives().iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            NetsisDataPrimitive next = it2.next();
                                            if (next.getName().equals("RECID")) {
                                                GenericDataPrimitive genericDataPrimitive = new GenericDataPrimitive(next.getName(), next.getValue());
                                                AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                                ((Netsis) baseErp).updateCustomerInchargeRecID(clCardIncharge.getClCode(), clCardIncharge.getCreatedDate(), genericDataPrimitive);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            public void onFailure(Call<NetsisDataHeader> call2, Throwable th) {
                                Log.e(NetsisRestAsyncTask.TAG, "onFailure");
                            }
                        });
                    }
                }
                public void onFailure(Call<NetsisDataHeader> call, Throwable th) {
                    Log.e(NetsisRestAsyncTask.TAG, "onFailure: ");
                }
            });
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage());
        }
    }
    public CustomerNew cariTemelBilgiToCustomerNew(Cari cari) {
        CustomerNew customerNew = new CustomerNew();
        customerNew.setCode(new FicheStringProp(cari.getCariTemelBilgi().getCode()));
        customerNew.setName(new FicheStringProp(cari.getCariTemelBilgi().getDefinition()));
        return customerNew;
    }
    public void lambdasendNewCustomer146(CustomerNew customerNew) {
        if (customerNew.getCode() == null || customerNew.getCode().toString().isEmpty()) {
            return;
        }
        ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().updateTransferedCustomer(customerNew.getCode().toString());
    }
    private boolean isOneToOneReturnDispatch(NetsisServiceResult netsisServiceResult) {
        return netsisServiceResult.isSuccess() && (netsisServiceResult.getSendData() instanceof ItemSlip) && ((ItemSlip) netsisServiceResult.getSendData()).getSlipHeader().getNetsisInvoiceType() == NetsisInvoiceType.ft_Iade;
    }

    private boolean isCabinTransSend(NetsisServiceResult netsisServiceResult) {
        if (netsisServiceResult.isSuccess() && (netsisServiceResult.getData() instanceof Sales)) {
            return !SalesUtils.isSalesTypeOrderOrDemand(((Sales) netsisServiceResult.getData()).getmSalesType());
        }
        return false;
    }

    public void getPrintMixedReceiptsInfo(MixedReceiptsMainParam mixedReceiptsMainParam, final ResponseListener<PrintMixedReceiptModel> responseListener) {
        final Observable<DataResponse<MixedReceiptsMain>> mixedReceiptsMain = getApi().getMixedReceiptsMain(mixedReceiptsMainParam.getKey());
        final Observable<DataResponse<Cari>> arp = getApi().getArp(mixedReceiptsMainParam.getCustomerCode());
        final Observable<NetsisDataHeader> queriesRx = getApi().getQueriesRx(new NetsisTSql(((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getPrintMixedReceiptExtraInfo(mixedReceiptsMainParam).getSql()));
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable observableSubscribeOn = Observable.defer(new Callable() {
            public Object call() {
                return this.f0.lambdagetPrintMixedReceiptsInfo150(mixedReceiptsMain, arp, queriesRx);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        compositeDisposable.add(observableSubscribeOn.subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((PrintMixedReceiptModel) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetPrintMixedReceiptsInfo151(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }));
    }
    public ObservableSource lambdagetPrintMixedReceiptsInfo150(Observable observable, Observable observable2, Observable observable3) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), new Function3() {
            public Object apply(Object obj, Object obj2, Object obj3) {
                return this.f0.lambdagetPrintMixedReceiptsInfo149((DataResponse) obj, (DataResponse) obj2, (NetsisDataHeader) obj3);
            }
        });
    }
    public PrintMixedReceiptModel lambdagetPrintMixedReceiptsInfo149(DataResponse dataResponse, DataResponse dataResponse2, NetsisDataHeader netsisDataHeader) throws Exception {
        return new PrintMixedReceiptModel((MixedReceiptsMain) dataResponse.getData(), (Cari) dataResponse2.getData(), toListData(PrintExtraInfo.class, netsisDataHeader.getData()));
    }
    public static void lambdagetPrintMixedReceiptsInfo151(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public void getPrintCheckAndPNotesInfo(CheckAndPNotesMainParam checkAndPNotesMainParam, final ResponseListener<PrintCheckAndPNotesModel> responseListener) {
        final Observable<DataResponse<NetsisChequeAndDeedMain>> checkAndPNotesMain = getApi().getCheckAndPNotesMain(checkAndPNotesMainParam.getKey());
        final Observable<DataResponse<Cari>> arp = getApi().getArp(checkAndPNotesMainParam.getCustomerCode());
        final Observable<NetsisDataHeader> queriesRx = getApi().getQueriesRx(new NetsisTSql(((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getPrintCheckAndPNotesExtraInfo(checkAndPNotesMainParam).getSql()));
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable observableSubscribeOn = Observable.defer(new Callable() {
            public Object call() {
                return this.f0.lambdagetPrintCheckAndPNotesInfo153(checkAndPNotesMain, arp, queriesRx);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        compositeDisposable.add(observableSubscribeOn.subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((PrintCheckAndPNotesModel) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetPrintCheckAndPNotesInfo154(responseListener, (Throwable) obj);
            }
            public Object invoke(Object obj) {
                return null;
            }
        }));
    }
    public Object invoke(Object obj) {
        return null;
    }
    public ObservableSource lambdagetPrintCheckAndPNotesInfo153(Observable observable, Observable observable2, Observable observable3) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), new Function3() {
            private NetsisRestAsyncTask f0;

            public Object apply(Object obj, Object obj2, Object obj3) {
                try {
                    return this.f0.lambdagetPrintCheckAndPNotesInfo152((DataResponse) obj, (DataResponse) obj2, (NetsisDataHeader) obj3);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public PrintCheckAndPNotesModel lambdagetPrintCheckAndPNotesInfo152(DataResponse dataResponse, DataResponse dataResponse2, NetsisDataHeader netsisDataHeader) throws Exception {
        return new PrintCheckAndPNotesModel((NetsisChequeAndDeedMain) dataResponse.getData(), (Cari) dataResponse2.getData(), toListData(PrintExtraInfo.class, netsisDataHeader.getData()));
    }
    public static void lambdagetPrintCheckAndPNotesInfo154(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
    public void getPrintSafeDepositInfo(SafeDepositParam safeDepositParam, final ResponseListener<PrintSafeDepositModel> responseListener) {
        final Observable<DataResponse<List<SafeDeposit>>> safeDeposit = getApi().getSafeDeposit(safeDepositParam.getKey());
        final Observable<DataResponse<Cari>> arp = getApi().getArp(safeDepositParam.getCustomerCode());
        final Observable<NetsisDataHeader> queriesRx = getApi().getQueriesRx(new NetsisTSql(((Netsis) ErpCreator.getInstance().getmBaseErp()).getQueryCreator().getPrintSafeDepositExtraInfo(safeDepositParam).getSql()));
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable observableSubscribeOn = Observable.defer(new Callable() {
            public Object call() {
                return this.f0.lambdagetPrintSafeDepositInfo156(safeDeposit, arp, queriesRx);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        compositeDisposable.add(observableSubscribeOn.subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((PrintSafeDepositModel) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }, new Consumer() {
            public void accept(Object obj) throws Exception {
                NetsisRestAsyncTask.lambdagetPrintSafeDepositInfo157(responseListener, (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }));
    }
    public ObservableSource lambdagetPrintSafeDepositInfo156(Observable observable, Observable observable2, Observable observable3) throws Exception {
        return Observable.zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), new Function3() {
            public Object apply(Object obj, Object obj2, Object obj3) {
                return this.f0.lambdagetPrintSafeDepositInfo155((DataResponse) obj, (DataResponse) obj2, (NetsisDataHeader) obj3);
            }
        });
    }
    public PrintSafeDepositModel lambdagetPrintSafeDepositInfo155(DataResponse dataResponse, DataResponse dataResponse2, NetsisDataHeader netsisDataHeader) throws Exception {
        return new PrintSafeDepositModel((dataResponse.getData() == null || ((List) dataResponse.getData()).size() <= 0) ? new SafeDeposit() : (SafeDeposit) ((List) dataResponse.getData()).get(0), (Cari) dataResponse2.getData(), toListData(PrintExtraInfo.class, netsisDataHeader.getData()));
    }
    public static void lambdagetPrintSafeDepositInfo157(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th != null ? th.getMessage() : "");
    }
}