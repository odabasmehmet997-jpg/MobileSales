package com.proje.mobilesales.core.asynctask;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Build;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import com.fasterxml.jackson.core.JsonPointer;
import com.github.mikephil.charting.matrix.Vector3;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.CommunicationModule;
import com.proje.mobilesales.core.base.*;
import com.proje.mobilesales.core.data.TigerSendDataCreator;
import com.proje.mobilesales.core.data.TigerXmlParserForUpdate;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.design.Tiger;
import com.proje.mobilesales.core.edocument.EDocumentResponse;
import com.proje.mobilesales.core.edocument.EDocumentType;
import com.proje.mobilesales.core.edocument.ShowEDocumentResponse;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.NetsisSelectResult;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.ISqlBriteDatabase;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.tigerwcf.*;
import com.proje.mobilesales.core.tigerwcf.edocument.GetEDocumentContent;
import com.proje.mobilesales.core.tigerwcf.edocument.SendEArchiveDocuments;
import com.proje.mobilesales.core.tigerwcf.edocument.SendRecvEDispatchDocuments;
import com.proje.mobilesales.core.tigerwcf.edocument.SendRecvEInvoiceDocuments;
import com.proje.mobilesales.core.tigerwcf.xml.SaxGenericResultParser;
import com.proje.mobilesales.core.tigerwcf.xml.SaxResultParser;
import com.proje.mobilesales.core.utils.*;
import com.proje.mobilesales.features.collections.casefiche.model.database.CaseCash;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.CashCreditX;
import com.proje.mobilesales.features.collections.cashandcreditcardfiche.model.database.CashCredit;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.ChequeDeed;
import com.proje.mobilesales.features.collections.chequeanddeedfiche.model.database.Chequedeed;
import com.proje.mobilesales.features.customer.model.CustomerBeforeBalance;
import com.proje.mobilesales.features.customer.model.CustomerNew;
import com.proje.mobilesales.features.customer.model.database.CustomerRiskLimit;
import com.proje.mobilesales.features.dbmodel.Invoice;
import com.proje.mobilesales.features.dbmodel.Order;
import com.proje.mobilesales.features.dbmodel.OrderAvailableAmount;
import com.proje.mobilesales.features.model.*;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.xml.SalesTransactionXml;
import com.proje.mobilesales.features.sales.model.xml.SalesXml;
import com.proje.mobilesales.features.sales.model.xml.SalesXmlParser;
import com.proje.mobilesales.features.settings.expr.MatterRegex;
import com.proje.mobilesales.features.settings.model.Matter;
import com.proje.mobilesales.features.settings.model.MatterSettings;
import com.proje.mobilesales.features.visit.model.database.VisitInfo;
import org.jetbrains.annotations.UnknownNullability;
import org.simpleframework.xml.core.Persister;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import io.reactivex.*;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.*;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static android.text.TextUtils.*;
import static com.proje.mobilesales.core.utils.AppUtils.handleNetworkError;
import static com.proje.mobilesales.features.sales.utils.PurchasePriceUtils.sales;
import static io.reactivex.Observable.*;

public class TigerWcfAsyncTask extends BaseCommunication {
    private static final String TAG = "TigerWcfAsyncTask";
    private static String _LBS_LOAD_P;
    private static final Object lock = new Object();
    private static WcfEndpointApi adapter;
    private static DataQuery dataQuery;
    static WcfServiceFactory factory;
    static Preferences.TigerUserSettings mTigerUserSettings;
    private Query query;
    private final SaxGenericResultParser saxGenericResultParser;
    private final SaxResultParser saxResultParser;
    private int r6;
    private Object o;

    public static void lambdadirectRxBool26(TigerSelectResult tigerSelectResult) throws Exception {
    }
    public static Iterable lambdagetCalculateAddedProductLinesPriceRx96(ArrayList arrayList) throws Exception {
        return arrayList;
    }
    public static void lambdagetCalculateAllProductLinePriceRx39(TigerSelectResult tigerSelectResult) throws Exception {
    }
    public static void lambdagetEDocumentContentRx108(GetEDocumentContent getEDocumentContent) throws Exception {
    }
    public static void lambdagetGenericRx7(TigerSelectResult tigerSelectResult) throws Exception {
    }
    public static void lambdagetLastRx16(TigerSelectResult tigerSelectResult) throws Exception {
    }
    public static void lambdagetLastRxBool21(TigerSelectResult tigerSelectResult) throws Exception {
    }
    public static void lambdagetMaxMatterNo47(TigerSelectResult tigerSelectResult) throws Exception {
    }
    public static Iterable lambdareadFiche64(ArrayList arrayList) throws Exception {
        return arrayList;
    }
    public static Iterable lambdareadOrderFiche62(ArrayList arrayList) throws Exception {
        return arrayList;
    }
    public static void lambdasendEArchiveDocumentsRx118(SendEArchiveDocuments sendEArchiveDocuments) throws Exception {
    }
    public static TigerServiceResult lambdasendFicheRx29(TigerServiceResult tigerServiceResult) throws Exception {
        return tigerServiceResult;
    }
    public static void lambdasendFicheRx33(TigerServiceResult tigerServiceResult) throws Exception {
    }
    public static void lambdasendRecvEDispatchDocumentsRx123(SendRecvEDispatchDocuments sendRecvEDispatchDocuments) throws Exception {
    }
    public static void lambdasendRecvEInvoiceDocumentsRx113(SendRecvEInvoiceDocuments sendRecvEInvoiceDocuments) throws Exception {
    }
    public static Iterable lambdatransferFiches75(List list) throws Exception {
        return list;
    }
    public TigerWcfAsyncTask(CommunicationType communicationType, ISqlBriteDatabase iSqlBriteDatabase, UserSettings userSettings) {
        super(communicationType, iSqlBriteDatabase, userSettings);
        adapter = null;
        this.query = null;
        dataQuery = null;
        getCommunicationComponent ().inject(this);
        this.saxResultParser = new SaxResultParser();
        this.saxGenericResultParser = new SaxGenericResultParser();
        mTigerUserSettings = (Preferences.TigerUserSettings) userSettings;
        this.mCommunicationType = CommunicationType.WCF;
        _LBS_LOAD_P = TigerSecret.get(ContextUtils.getmContext());
    }
    public void lambdasendCashAndCredit120(GroupItem f1, boolean f2) {

    }
    public void lambdasendCaseCashFiche70(GroupItem groupItem, boolean z) {
    }
    private static String getBaseWcfAddress() {
        String serverAddress = mTigerUserSettings.getServerAddress();
        String str = RetrofitServiceFactory.HTTP;
        if (serverAddress.startsWith(RetrofitServiceFactory.HTTP)) {
            str = "";
        }
        return String.format("%s%s", str, mTigerUserSettings.getServerAddress());
    }
    public static WcfEndpointApi createAdapter() {
        if (null == adapter) {
            adapter = factory.rxEnabled(true).create(getBaseWcfAddress(), WcfEndpointApi.class, new CommunicationModule.BackgroundThreadExecutor());
        }
        return adapter;
    }
    public Query createQuery(TigerSelectResult tigerSelectResult) {
        synchronized (lock) {
            try {
                Query query = new Query();
                this.query = query;
                if (null != tigerSelectResult) {
                    query.setSecurityCode(new StringElement(mTigerUserSettings.getSecurityCode()));
                    this.query.setResultXML(new StringElement(""));
                    this.query.setErrorString(new StringElement(""));
                    this.query.setStatus(new IntElement(0));
                    this.query.setLbsLoadPass(new StringElement(_LBS_LOAD_P));
                    this.query.setAsqlText(new StringElement(CompressUtil.base64Encode(tigerSelectResult.getSqlWithWorCheck())));
                    this.query.setOrderByText(new StringElement(tigerSelectResult.getOrderBy()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.query;
    }
    private DataQuery createGetUsableCampaignCardsQuery(TigerServiceResult tigerServiceResult) {
        synchronized (lock) {
            try {
                DataQuery dataQuery = new DataQuery();
                TigerWcfAsyncTask.dataQuery = dataQuery;
                if (null != tigerServiceResult) {
                    dataQuery.setEerrorString(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setFstatus(new IntElement(0));
                    TigerWcfAsyncTask.dataQuery.setgLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
                    TigerWcfAsyncTask.dataQuery.sethFirmNr(new StringElementAppend(String.valueOf(mTigerUserSettings.getFirmNumber())));
                    TigerWcfAsyncTask.dataQuery.setKsecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
                    TigerWcfAsyncTask.dataQuery.setAdataType(new IntElement(tigerServiceResult.getDataType().value));
                    TigerWcfAsyncTask.dataQuery.setBdataReference(new IntElement(tigerServiceResult.getDataReference()));
                    TigerWcfAsyncTask.dataQuery.setCdataXML(new StringElementAppend(CompressUtil.compress(tigerServiceResult.getSendData())));
                    TigerWcfAsyncTask.dataQuery.setDparamXML(new StringElementAppend(getParamXmlForUsableCampaignCards (findGetStockLinePrice (tigerServiceResult))));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return dataQuery;
    }
    private DataQuery createDataQuery(TigerServiceResult tigerServiceResult) {
        synchronized (lock) {
            try {
                DataQuery dataQuery = new DataQuery();
                TigerWcfAsyncTask.dataQuery = dataQuery;
                if (null != tigerServiceResult) {
                    dataQuery.setEerrorString(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setFstatus(new IntElement(0));
                    TigerWcfAsyncTask.dataQuery.setgLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
                    TigerWcfAsyncTask.dataQuery.sethFirmNr(new StringElementAppend(String.valueOf(mTigerUserSettings.getFirmNumber())));
                    TigerWcfAsyncTask.dataQuery.setKsecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
                    TigerWcfAsyncTask.dataQuery.setAdataType(new IntElement(tigerServiceResult.getDataType().value));
                    TigerWcfAsyncTask.dataQuery.setBdataReference(new IntElement(tigerServiceResult.getDataReference()));
                    TigerWcfAsyncTask.dataQuery.setCdataXML(new StringElementAppend(CompressUtil.compress(tigerServiceResult.getSendData())));
                    TigerWcfAsyncTask.dataQuery.setDparamXML(new StringElementAppend(getParamXml (tigerServiceResult.getDataType(), tigerServiceResult.getApplyCampaign(), tigerServiceResult.getData())));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return dataQuery;
    }
    private DataQuery createDeleteDataQuery(TigerServiceResult tigerServiceResult) {
        synchronized (lock) {
            try {
                DataQuery dataQuery = new DataQuery();
                TigerWcfAsyncTask.dataQuery = dataQuery;
                if (null != tigerServiceResult) {
                    dataQuery.setEerrorString(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setFstatus(new IntElement(0));
                    TigerWcfAsyncTask.dataQuery.setgLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
                    TigerWcfAsyncTask.dataQuery.sethFirmNr(new StringElementAppend(String.valueOf(mTigerUserSettings.getFirmNumber())));
                    TigerWcfAsyncTask.dataQuery.setKsecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
                    TigerWcfAsyncTask.dataQuery.setCdataXML(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setDparamXML(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setAdataType(new IntElement(tigerServiceResult.getDataType().value));
                    TigerWcfAsyncTask.dataQuery.setBdataReference(new IntElement(tigerServiceResult.getDataReference()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return dataQuery;
    }
    private DataQuery createCalculateDataQuery(TigerServiceResult tigerServiceResult, int i2) {
        return createCalculateDataQuery (tigerServiceResult, i2, false);
    }
    private DataQuery createCalculateDataQuery(TigerServiceResult tigerServiceResult, int i2, boolean z) {
        synchronized (lock) {
            try {
                DataQuery dataQuery = new DataQuery();
                TigerWcfAsyncTask.dataQuery = dataQuery;
                if (null != tigerServiceResult) {
                    dataQuery.setEerrorString(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setFstatus(new IntElement(0));
                    TigerWcfAsyncTask.dataQuery.setgLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
                    TigerWcfAsyncTask.dataQuery.sethFirmNr(new StringElementAppend(String.valueOf(mTigerUserSettings.getFirmNumber())));
                    TigerWcfAsyncTask.dataQuery.setKsecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
                    TigerWcfAsyncTask.dataQuery.setCdataXML(new StringElementAppend(CompressUtil.compress(tigerServiceResult.getSendData())));
                    TigerWcfAsyncTask.dataQuery.setDparamXML(new StringElementAppend(getParamXml (tigerServiceResult.getDataType(), i2, z)));
                    TigerWcfAsyncTask.dataQuery.setAdataType(new IntElement(tigerServiceResult.getDataType().value));
                    TigerWcfAsyncTask.dataQuery.setBdataReference(new IntElement(0));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return dataQuery;
    }
    private static DataQuery createReadDataQuery(TigerServiceResult tigerServiceResult) {
        synchronized (lock) {
            try {
                DataQuery dataQuery = new DataQuery();
                TigerWcfAsyncTask.dataQuery = dataQuery;
                if (tigerServiceResult != null) {
                    dataQuery.setEerrorString(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setFstatus(new IntElement(0));
                    TigerWcfAsyncTask.dataQuery.setgLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
                    TigerWcfAsyncTask.dataQuery.sethFirmNr(new StringElementAppend(String.valueOf(mTigerUserSettings.getFirmNumber())));
                    TigerWcfAsyncTask.dataQuery.setKsecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
                    TigerWcfAsyncTask.dataQuery.setCdataXML(new StringElementAppend(""));
                    TigerWcfAsyncTask.dataQuery.setDparamXML(new StringElementAppend(getParamXml(null, 0, false)));
                    TigerWcfAsyncTask.dataQuery.setAdataType(new IntElement(tigerServiceResult.getDataType().value));
                    TigerWcfAsyncTask.dataQuery.setBdataReference(new IntElement(tigerServiceResult.getDataReference()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return TigerWcfAsyncTask.dataQuery;
    }
    private Query createGetInfoQuery(TigerSelectResult tigerSelectResult) {
        synchronized (lock) {
            try {
                Query query = new Query();
                this.query = query;
                if (null != tigerSelectResult) {
                    query.setSecurityCode(new StringElement(mTigerUserSettings.getSecurityCode()));
                    this.query.setResultXML(new StringElement(""));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.query;
    }
    private GetValueRequest createGetValueQuery(int i2, boolean z) {
        GetValueRequest getValueRequest = new GetValueRequest();
        synchronized (lock) {
            try {
                getValueRequest.setFlag(new IntElement(i2));
                getValueRequest.setSecurityCode(new StringElement(mTigerUserSettings.getSecurityCode()));
                getValueRequest.setLbsLoadPass(new StringElement(_LBS_LOAD_P));
                getValueRequest.setIsFlag(new StringElement(Boolean.toString(z)));
            } catch (Throwable th) {
                throw th;
            }
        }
        return getValueRequest;
    }
    @SuppressLint("StringFormatInvalid")
    private TigerSelectResult callSp(Call<ResponseEnvelope> call, TigerSelectResult tigerSelectResult, int i2, int i3) {
        try {
            try {
                try {
                    Response<ResponseEnvelope> execute = call.execute();
                    if (!execute.isSuccessful()) {
                        Tiger tiger = (Tiger) ErpCreator.getInstance().getmBaseErp();
                        if (tigerSelectResult.getProcessType() == ProcessType.GETSTOCK) {
                            tigerSelectResult = tiger.getTigerQueryCreator().getItemStockWithFDate(false);
                            tigerSelectResult.setSql(getTransferSizeSql(tigerSelectResult.getSql(), i2, i3));
                        } else if (tigerSelectResult.getProcessType() == ProcessType.GETVARIANTSTOCK) {
                            tigerSelectResult = tiger.getTigerQueryCreator().getVariantStockWithFDate(false);
                            tigerSelectResult.setSql(getTransferSizeSql(tigerSelectResult.getSql(), i2, i3));
                        }
                        callExecQuery(tigerSelectResult);
                    } else if (execute.body().getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                        tigerSelectResult.setSuccess(true);
                        if (execute.body().getBody().getQuery().getResultXML() != null) {
                            tigerSelectResult.setResultXML(CompressUtil.decompress(execute.body().getBody().getQuery().getResultXML().getValue()));
                            tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), tigerSelectResult.getProcessType().getaClass()));
                        }
                    } else
                        tigerSelectResult.setSuccess(this.mContext.getString(R.string.exp_30_wcf_select_result_error, execute.body().getBody().getQuery().getErrorString().getValue(), tigerSelectResult.getSql()));
                } catch (IOException e2) {
                    Log.e(TAG, "callSp: ", e2);
                    tigerSelectResult.setSuccess(this.mContext.getString(R.string.exp_29_io));
                }
            } catch (Exception e4) {
                Log.e(TAG, "callSp: ", e4);
                tigerSelectResult.setSuccess(e4.getMessage());
            }
            tigerSelectResult.setCompleted(true);
            return tigerSelectResult;
        } catch (Throwable th) {
            tigerSelectResult.setCompleted(true);
            throw th;
        }
    }
    private void callQuery(Call<ResponseEnvelope> call, TigerSelectResult tigerSelectResult) {
        try {
            try {
                try {
                    Response<ResponseEnvelope> responseExecute = call.execute();
                    if (responseExecute.isSuccessful()) {
                        if (responseExecute.body().getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                            tigerSelectResult.setSuccess(true);
                            if (responseExecute.body().getBody().getQuery().getResultXML() != null) {
                                tigerSelectResult.setResultXML(CompressUtil.decompress(responseExecute.body().getBody().getQuery().getResultXML().getValue()));
                                tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), tigerSelectResult.getProcessType().getaClass()));
                            }
                        } else {
                            tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                        }
                    } else {
                        tigerSelectResult.setSuccess(responseExecute.message());
                    }
                } catch (IOException e2) {
                    Log.e(TAG, "callQuery: ", e2);
                    tigerSelectResult.setSuccess(ContextUtils.getStringResource( R.string.exp_29_io));
                }
            } catch (Exception e4) {
                Log.e(TAG, "callQuery: ", e4);
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
            }
            tigerSelectResult.setCompleted(true);
        } catch (Throwable th) {
            tigerSelectResult.setCompleted(true);
            throw th;
        }
    }
    private void callGetInfoQuery(Call<ResponseEnvelope> call, TigerSelectResult tigerSelectResult) {
        try {
            try {
                Response<ResponseEnvelope> execute = call.execute();
                if (execute.isSuccessful()) {
                    tigerSelectResult.setSuccess(true);
                    tigerSelectResult.setResultXML(execute.body().getBody().getQuery().getResultXML().getValue());
                } else {
                    tigerSelectResult.setSuccess(execute.message());
                }
            } catch (SocketTimeoutException e2) {
                Log.e(TAG, "callGetInfoQuery: ", e2);
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_28_socket_timeout));
            } catch (IOException e3) {
                Log.e(TAG, "callGetInfoQuery: ", e3);
                tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_29_io));
            } catch (Exception e4) {
                Log.e(TAG, "callGetInfoQuery: ", e4);
                tigerSelectResult.setSuccess(e4.getMessage());
            }
            tigerSelectResult.setCompleted(true);
        } catch (Throwable th) {
            tigerSelectResult.setCompleted(true);
            throw th;
        }
    }
    private GetValueResponse callGetValueQuery(Call<ResponseEnvelope> call) {
        GetValueResponse getValueResponse = new GetValueResponse();
        try {
            Response<ResponseEnvelope> execute = call.execute();
            if (execute.isSuccessful()) {
                getValueResponse = execute.body().getBody().getGetValueResponse();
                getValueResponse.setSuccess(true);
            } else {
                getValueResponse.setSuccess(false);
                getValueResponse.setErrorDesc(execute.message());
            }
        } catch (SocketTimeoutException e2) {
            Log.e(TAG, "callGetValueQuery: ", e2);
            getValueResponse.setSuccess(false);
            getValueResponse.setErrorDesc(ContextUtils.getStringResource(R.string.exp_28_socket_timeout));
        } catch (IOException e3) {
            Log.e(TAG, "callGetValueQuery: ", e3);
            getValueResponse.setSuccess(false);
            getValueResponse.setErrorDesc(ContextUtils.getStringResource(R.string.exp_29_io));
        } catch (Exception e4) {
            Log.e(TAG, "callGetValueQuery: ", e4);
            getValueResponse.setSuccess(false);
            getValueResponse.setErrorDesc(e4.getMessage());
        }
        return getValueResponse;
    }
    private void callDataQuery(Call<ResponseEnvelope> call, TigerServiceResult tigerServiceResult) {
        try {
            Response<ResponseEnvelope> execute = call.execute();
            if (execute.isSuccessful()) {
                if (execute.body().getBody().getDataQuery().getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerServiceResult.setSuccess(true);
                    tigerServiceResult.setDataReference(null != execute.body ().getBody ().getDataQuery ().getBdataReference () ? execute.body().getBody().getDataQuery().getBdataReference().getValue() : 0);
                    if (null != execute.body ().getBody ().getDataQuery ().getCdataXML ()) {
                        tigerServiceResult.setParamXML(null != execute.body ().getBody ().getDataQuery ().getDparamXML () ? execute.body().getBody().getDataQuery().getDparamXML().getValue() : "");
                        tigerServiceResult.setDataXML(null != execute.body ().getBody ().getDataQuery ().getCdataXML () ? CompressUtil.decompress(execute.body().getBody().getDataQuery().getCdataXML().getValue()) : "");
                    }
                } else {
                    tigerServiceResult.setSuccess(execute.body().getBody().getDataQuery().getEerrorString().getValue());
                }
            } else {
                tigerServiceResult.setSuccess(execute.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "callDataQuery: ", e2);
            tigerServiceResult.setSuccess(e2.getMessage());
        }
        tigerServiceResult.setCompleted(true);
    }
    private void callExecQuery(TigerSelectResult tigerSelectResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
        callQuery (createAdapter ().execQuery(requestEnvelope), tigerSelectResult);
    }
    public TigerSelectResult callExecSp(TigerSelectResult tigerSelectResult, int i2, int i3) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        tigerSelectResult.setSql(tigerSelectResult.getSql() + " " + this.mContext.getString(R.string.wcf_result_xml));
        requestEnvelope.getBody().setExecSP(createQuery (tigerSelectResult));
        return callSp (createAdapter ().execSp(requestEnvelope), tigerSelectResult, i2, i3);
    }
    private void callGetInfoQuery(TigerSelectResult tigerSelectResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setGetInfoQuery(createGetInfoQuery (tigerSelectResult));
        callGetInfoQuery (createAdapter ().getInfoQuery(requestEnvelope), tigerSelectResult);
    }
    private GetValueResponse callGetValueQuery(int i2, boolean z) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setGetValue(createGetValueQuery (i2, z));
        return callGetValueQuery (createAdapter ().getValueQuery(requestEnvelope));
    }
    private void callDirectQuery(TigerSelectResult tigerSelectResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setDirectQuery(createQuery (tigerSelectResult));
        callQuery (createAdapter ().directQuery(requestEnvelope), tigerSelectResult);
    }
    private void callAppendDataQuery(TigerServiceResult tigerServiceResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setAppendDataObject(createDataQuery (tigerServiceResult));
        callDataQuery (createAdapter ().append(requestEnvelope), tigerServiceResult);
    }
    private void callReadDataQuery(TigerServiceResult tigerServiceResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setReadDataObject(createReadDataQuery (tigerServiceResult));
        callDataQuery (createAdapter ().read(requestEnvelope), tigerServiceResult);
    }
    private void callCalculateDataQuery(TigerServiceResult tigerServiceResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setCalculateDataObject(createDataQuery (tigerServiceResult));
        callDataQuery (createAdapter ().calculate(requestEnvelope), tigerServiceResult);
    }
    private void callDeleteDataQuery(TigerServiceResult tigerServiceResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setDeleteDataObject(createDeleteDataQuery (tigerServiceResult));
        callDataQuery (createAdapter ().delete(requestEnvelope), tigerServiceResult);
    }
    private int findGetStockLinePrice(TigerServiceResult tigerServiceResult) {
        Sales sales;
        WcfPriceType wcfPriceType;
        if (!ErpCreator.getInstance().getmBaseErp().getProductOnlinePrice() || (!(tigerServiceResult.getDataType() == DataObjectType.ADDORDER || tigerServiceResult.getDataType() == DataObjectType.ADDINVOICE || tigerServiceResult.getDataType() == DataObjectType.ADDDISPATCH || tigerServiceResult.getDataType() == DataObjectType.ADDREQEST) || null == (sales = (Sales) tigerServiceResult.getData ()) || null == sales.getMSalesDetailList () || 0 == sales.getMSalesDetailList ().size () || 1 < sales.getMSalesDetailList ().size ())) {
            return 0;
        }
        SalesDetail salesDetail = sales.getMSalesDetailList().get(0);
        if (isEmpty(salesDetail.getSelectedPriceType().getDefinition())) {
            return 0;
        }
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
        return wcfPriceType.getType();
    }
    private String getParamXml(DataObjectType dataObjectType, int i2, Object obj) {
        int salesFicheApplyCampaign;
        try {
            int i5 = 0;
            if (dataObjectType == DataObjectType.ADDORDER) {
                salesFicheApplyCampaign = ErpCreator.getInstance().getmBaseErp().getSalesFicheApplyCampaign(SalesType.ORDER);
            } else {
                if (dataObjectType != DataObjectType.ADDINVOICE && dataObjectType != DataObjectType.ADDDISPATCH) {
                    salesFicheApplyCampaign = 0;
                }
                salesFicheApplyCampaign = ErpCreator.getInstance().getmBaseErp().getSalesFicheApplyCampaign(SalesType.INVOICE);
            }
            if (0 != salesFicheApplyCampaign) {
                if (1 != salesFicheApplyCampaign) {
                    if (obj instanceof Sales) {
                        if (!((Sales) obj).hasCampaignApplied()) {
                            i5 = i2;
                        }
                        i2 = i5;
                    }
                    if (isEmpty(ErpCreator.getInstance().getmBaseErp().getUser().getLogoUserName())) {
                        return ContextUtils.formatStringEnglish(ContextUtils.getStringResource(R.string.wcf_param_xml), Integer.valueOf(i2), Integer.valueOf(i2), "");
                    }
                    return ContextUtils.formatStringEnglish(ContextUtils.getStringResource(R.string.wcf_param_switchUser_xml), Integer.valueOf(i2), Integer.valueOf(i2), ErpCreator.getInstance().getmBaseErp().getUser().getLogoUserName(), "");
                }
                i2 = 1;
                isEmpty(ErpCreator.getInstance().getmBaseErp().getUser().getLogoUserName());
            }
            i2 = 0;
            isEmpty(ErpCreator.getInstance().getmBaseErp().getUser().getLogoUserName());
        } catch (Exception e2) {
            Log.e(TAG, "getParamXml: ", e2);
            Log.d(TAG, "getParamXml: param xml change resource ");
            return this.mContext.getString(R.string.wcf_param_xml_exp);
        }
        return "";
    }
    private static String getParamXml(TigerServiceResult tigerServiceResult, int i2, boolean z) {
        String campaingRefs = "";
        boolean i3;
        if (null != tigerServiceResult && (tigerServiceResult.getData() instanceof Sales)) {
            campaingRefs = ((Sales) tigerServiceResult.getData()).getCampaingRefs();
            i3 = isEmpty(campaingRefs) && z;
        } else {
            i3 = z;
        }
        try {
            return ContextUtils.formatStringEnglish(R.string.wcf_param_xml_price_calc, Integer.valueOf(i2), Integer.valueOf(i3 ? 1 : 0), campaingRefs);
        } catch (Exception e2) {
            Log.e(TAG, "getParamXml: ", e2);
            return ContextUtils.getStringResource(R.string.wcf_param_xml_exp);
        }
    }
    private String getParamXmlForUsableCampaignCards(int i2) {
        String formatStringEnglish;
        try {
            if (isEmpty(ErpCreator.getInstance().getmBaseErp().getUser().getLogoUserName())) {
                formatStringEnglish = ContextUtils.formatStringEnglish(ContextUtils.getStringResource(R.string.wcf_param_xml), 0, Integer.valueOf(i2), "");
            } else {
                formatStringEnglish = ContextUtils.formatStringEnglish(ContextUtils.getStringResource(R.string.wcf_param_switchUser_xml), 0, Integer.valueOf(i2), ErpCreator.getInstance().getmBaseErp().getUser().getLogoUserName(), "");
            }
            return formatStringEnglish;
        } catch (Exception e2) {
            Log.e(TAG, "getParamXml: ", e2);
            Log.d(TAG, "getParamXml: param xml change resource ");
            return this.mContext.getString(R.string.wcf_param_xml_exp);
        }
    }
    public void runCalculate(BaseResult baseResult) {
        callCalculateDataQuery ((TigerServiceResult) baseResult);
    }
    public void runRead(BaseResult baseResult) {
        callReadDataQuery ((TigerServiceResult) baseResult);
    }
    public void runDelete(BaseResult baseResult) {
        callDeleteDataQuery ((TigerServiceResult) baseResult);
    }
    public void runAppend(BaseResult baseResult) {
        callAppendDataQuery ((TigerServiceResult) baseResult);
    }
    public void runDirect(BaseResult baseResult) {
        callDirectQuery ((TigerSelectResult) baseResult);
    }
    public void runExec(BaseResult baseResult) {
        callExecQuery ((TigerSelectResult) baseResult);
    }
    public void runGetInfo(BaseResult baseResult) {
        callGetInfoQuery ((TigerSelectResult) baseResult);
    }
    public GetValueResponse runGetFlagValue(int i2, boolean z) {
        return callGetValueQuery (i2, z);
    }
    public Observable<Response<ResponseEnvelope>> runExecRx(TigerSelectResult tigerSelectResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
        return createAdapter ().execQueryRx(requestEnvelope);
    }
    private boolean isFDateColumnAdded() {
        return 1 == FDateUtils.getInstance ().getFDateModel ().getfDateOnOrFiche () && 1 == FDateUtils.getInstance ().getFDateModel ().getfDateOnDeletedRecs () && 1 == FDateUtils.getInstance ().getFDateModel ().getfDateOnStFiche ();
    }
    public String getTransferSizeSql(String str, int i2, int i3) {
        return str + " WHERE R.RowNum  BETWEEN " + i2 + SqlLiteVariable._AND + i3;
    }
    public TigerSelectResult runAllExec(BaseSelectResult baseSelectResult) {
        boolean z;
        TigerSelectResult tigerSelectResult = (TigerSelectResult) baseSelectResult;
        String sql = tigerSelectResult.getSql();
        int transferPartSize = Preferences.getTransferPartSize(ContextUtils.getmContext());
        if (0 == transferPartSize) {
            transferPartSize = 1000;
        }
        if (tigerSelectResult.getProcessType() == ProcessType.GETITEMIMAGE && 100 < transferPartSize) {
            transferPartSize = 25;
        }
        long nanoTime = System.nanoTime();
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        if (tigerSelectResult.isTableDelete()) {
            baseErp.getLogoSqlBriteDatabase().delete(tigerSelectResult.getProcessType().getaClass(), tigerSelectResult.getDeleteCondition());
        }
        int i2 = transferPartSize;
        int i3 = 1;
        while (true) {
            tigerSelectResult.setSql(sql);
            tigerSelectResult.setSql(tigerSelectResult.getSql() + " WHERE R.RowNum  BETWEEN " + i3 + SqlLiteVariable._AND + i2);
            if (isFDateColumnAdded () && !tigerSelectResult.isTableDelete() && (tigerSelectResult.getProcessType() == ProcessType.GETSTOCK || tigerSelectResult.getProcessType() == ProcessType.GETVARIANTSTOCK)) {
                tigerSelectResult = callExecSp (tigerSelectResult, i3, i2);
            } else {
                callExecQuery (tigerSelectResult);
            }
            if (!tigerSelectResult.isCompleted()) {
                z = false;
                break;
            }
            int size = tigerSelectResult.getDataList().size();
            i2 += transferPartSize;
            int i4 = (i2 - transferPartSize) + 1;
            z = tigerSelectResult.isSuccess () && baseErp.getLogoSqlBriteDatabase ().insertOrUpdate (tigerSelectResult.getDataList (), tigerSelectResult.isTableDelete ());
            if (size < transferPartSize || !z) {
                break;
            }
            i3 = i4;
        }
        Log.d("AA", "Bitis :" + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime) + " ms");
        tigerSelectResult.setSuccess(z);
        return tigerSelectResult;
    }
    public TigerSelectResult mapSelectResult(Response<ResponseEnvelope> response, TigerSelectResult tigerSelectResult) {
        try {
            try {
                if (response.isSuccessful()) {
                    if (response.body().getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                        tigerSelectResult.setSuccess(true);
                        if (null != response.body ().getBody ().getQuery ().getResultXML ().getValue ()) {
                            tigerSelectResult.setResultXML(CompressUtil.decompress(response.body().getBody().getQuery().getResultXML().getValue()));
                            tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), tigerSelectResult.getProcessType().getaClass()));
                            if (tigerSelectResult.isDatabaseSave()) {
                                synchronized (lock) {
                                    ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().insertOrUpdate(tigerSelectResult.getDataList(), tigerSelectResult.isTableDelete());
                                }
                            }
                        }
                    } else {
                        tigerSelectResult.setSuccess(response.body().getBody().getQuery().getErrorString().getValue());
                    }
                } else {
                    tigerSelectResult.setSuccess(response.message());
                }
            } catch (Exception e2) {
                Log.e(TAG, "callQuery: ", e2);
                tigerSelectResult.setSuccess(e2.getMessage());
            }
            return tigerSelectResult;
        } finally {
            tigerSelectResult.setCompleted(true);
        }
    }
    public void getLastRxBoolPaging(final TigerSelectResult tigerSelectResult, ResponseListener<Boolean> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.lambdagetLastRxBoolPaging0(tigerSelectResult, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new AnonymousClass1(tigerSelectResult, responseListener));
    }
    public static void lambdagetLastRxBoolPaging0(TigerSelectResult tigerSelectResult, ObservableEmitter observableEmitter) throws Exception {
        try {
            if (observableEmitter.isDisposed()) {
                return;
            }
            observableEmitter.onNext(tigerSelectResult);
        } catch (Exception e2) {
            if (observableEmitter.isDisposed()) {
                return;
            }
            observableEmitter.onError(e2);
        }
    }
    class AnonymousClass1 extends DisposableObserver<TigerSelectResult> {
        int endCount;
        String sql;
        int startCount;
        int transferPartSize;
        final ResponseListener valresponseListener;
        final TigerSelectResult valselectResult;
        public void onComplete() {
        }
        AnonymousClass1(TigerSelectResult tigerSelectResult, ResponseListener responseListener) {
            this.valselectResult = tigerSelectResult;
            this.valresponseListener = responseListener;
        }
        public void onStart() {
            int transferPartSize = Preferences.getTransferPartSize(ContextUtils.getmContext());
            this.transferPartSize = transferPartSize;
            if (0 == transferPartSize) {
                transferPartSize = 1000;
            }
            this.transferPartSize = transferPartSize;
            this.startCount = 1;
            this.endCount = transferPartSize;
            this.sql = this.valselectResult.getSql();
            if (this.valselectResult.isTableDelete()) {
                ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().delete(this.valselectResult.getProcessType().getaClass(), null, (String[]) null);
            }
        }
        public void onError(Throwable th) {
            this.valresponseListener.onError(null == th ? this.valselectResult.getErrorString() : th.getMessage());
        }
        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
        public void onNext(Object tigerSelectResult) {
            final TigerSelectResult[] tigerSelectResultArr = {( TigerSelectResult ) tigerSelectResult};
            tigerSelectResultArr[0].setSql(this.sql + " WHERE R.RowNum  BETWEEN " + this.startCount + SqlLiteVariable._AND + this.endCount);
            final RequestEnvelope[] requestEnvelopeArr = {new RequestEnvelope()};
            requestEnvelopeArr[0].setBody(new RequestBody());
            tigerSelectResultArr[0].setSql(tigerSelectResultArr[0].getSql() + " " + TigerWcfAsyncTask.this.mContext.getString(R.string.wcf_result_xml));
            requestEnvelopeArr[0].getBody().setExecSP(TigerWcfAsyncTask.this.createQuery (tigerSelectResultArr[0]));
            createAdapter ().execSpRx(requestEnvelopeArr[0]).flatMap(new Function() {
                public Object apply(Object obj) {
                    ObservableSource lambdaonNext1;
                    try {
                        lambdaonNext1 = AnonymousClass1.this.lambdaonNext1(tigerSelectResultArr, requestEnvelopeArr, (Response) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return lambdaonNext1;
                }

                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            }).filter(new Predicate() {
                public boolean test(Object obj) {
                    boolean lambdaonNext2;
                    try {
                        lambdaonNext2 = AnonymousClass1.this.lambdaonNext2 (tigerSelectResultArr, (TigerSelectResult) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return lambdaonNext2;
                }
            }).doOnComplete(new Action() {
                public void run() {
                    try {
                        AnonymousClass1.this.lambdaonNext3 (tigerSelectResultArr);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<TigerSelectResult>() {
                public void onNext(Object tigerSelectResult2) {
                }
                public void onComplete() {
                    try {
                        TigerSelectResult tigerSelectResult2 = tigerSelectResultArr[0];
                        if (null == tigerSelectResult2 || tigerSelectResult2.isCompleted()) {
                            AnonymousClass1.this.valresponseListener.onResponse(Boolean.valueOf(tigerSelectResultArr[0].isSuccess()));
                            dispose ();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                public void onError(Throwable th) {
                    String message;
                    ResponseListener responseListener = AnonymousClass1.this.valresponseListener;
                    if (null == th) {
                        message = tigerSelectResultArr[0].getErrorString();
                    } else {
                        message = th.getMessage();
                    }
                    responseListener.onError(message);
                }
                @Override
                public <T> Observable<T> observeOn(Scheduler scheduler) {
                    return null;
                }
            });
        }
        public ObservableSource lambdaonNext1(final TigerSelectResult[] tigerSelectResultArr, RequestEnvelope[] requestEnvelopeArr, Response response) throws Exception {
            if (response.isSuccessful()) {
                return just(TigerWcfAsyncTask.this.mapSelectResult (response, tigerSelectResultArr[0]));
            }
            Tiger tiger = (Tiger) ErpCreator.getInstance().getmBaseErp();
            if (!tigerSelectResultArr[0].isTableDelete()) {
                if (tigerSelectResultArr[0].getProcessType() == ProcessType.GETSTOCK) {
                    TigerSelectResult itemStockWithFDate = tiger.getTigerQueryCreator().getItemStockWithFDate(false);
                    tigerSelectResultArr[0] = itemStockWithFDate;
                    itemStockWithFDate.setSql(TigerWcfAsyncTask.this.getTransferSizeSql (itemStockWithFDate.getSql(), this.startCount, this.endCount));
                } else if (tigerSelectResultArr[0].getProcessType() == ProcessType.GETVARIANTSTOCK) {
                    TigerSelectResult variantStockWithFDate = tiger.getTigerQueryCreator().getVariantStockWithFDate(false);
                    tigerSelectResultArr[0] = variantStockWithFDate;
                    variantStockWithFDate.setSql(TigerWcfAsyncTask.this.getTransferSizeSql (variantStockWithFDate.getSql(), this.startCount, this.endCount));
                } else {
                    TigerSelectResult tigerSelectResult = tigerSelectResultArr[0];
                    tigerSelectResult.setSql(tigerSelectResult.getSql().replace(TigerWcfAsyncTask.this.mContext.getString(R.string.wcf_result_xml), ""));
                }
            }
            RequestEnvelope requestEnvelope = new RequestEnvelope();
            requestEnvelopeArr[0] = requestEnvelope;
            requestEnvelope.setBody(new RequestBody());
            requestEnvelopeArr[0].getBody().setExecQuery(TigerWcfAsyncTask.this.createQuery (tigerSelectResultArr[0]));
            return createAdapter ().execQueryRx(requestEnvelopeArr[0]).map(new Function() {
                public Object apply(Object obj) {
                    TigerSelectResult lambdaonNext0;
                    try {
                        lambdaonNext0 = AnonymousClass1.this.lambdaonNext0(tigerSelectResultArr, (Response) obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return lambdaonNext0;
                }

                @Override
                public Object invoke(Object obj) {
                    return null;
                }
            });
        }
        public TigerSelectResult lambdaonNext0(TigerSelectResult[] tigerSelectResultArr, Response response) throws Exception {
            try {
                return TigerWcfAsyncTask.this.mapSelectResult (response, tigerSelectResultArr[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        public boolean lambdaonNext2(TigerSelectResult[] tigerSelectResultArr, TigerSelectResult tigerSelectResult) throws Exception {
            if (null == tigerSelectResult) {
                return false;
            }
            if (tigerSelectResult.getDataList().size() == this.transferPartSize) {
                tigerSelectResultArr[0].setCompleted(false);
                return true;
            }
            tigerSelectResult.setCompleted(true);
            return false;
        }
        public void lambdaonNext3(TigerSelectResult[] tigerSelectResultArr) {
            int size = tigerSelectResultArr[0].getDataList().size();
            int i2 = this.endCount;
            int i3 = this.transferPartSize;
            int i4 = i2 + i3;
            this.endCount = i4;
            this.startCount = (i4 - i3) + 1;
            if (size >= i3) {
                onNext (tigerSelectResultArr[0]);
            }
        }
    }
    public void getPrintRxZip(final Observable<Response<ResponseEnvelope>> observable, final Observable<Response<ResponseEnvelope>> observable2,
                              final Observable<Response<ResponseEnvelope>> observable3, final ResponseListener<PrintValueMultiple> responseListener) {
        Observable subscribeOn = defer(new Callable() {
            public Object call() {
                ObservableSource lambdagetPrintRxZip1;
                try {
                    lambdagetPrintRxZip1 = TigerWcfAsyncTask.this.lambdagetPrintRxZip1(observable, observable2, observable3);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetPrintRxZip1;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        subscribeOn.subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((PrintValueMultiple) obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetPrintRxZip2 (responseListener, (Throwable) obj);
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
    public ObservableSource lambdagetPrintRxZip1(Observable observable, Observable observable2, Observable observable3) throws Exception {
        return zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), observable3.subscribeOn(Schedulers.newThread()), new Function3<Response<ResponseEnvelope>, Response<ResponseEnvelope>, Response<ResponseEnvelope>, PrintValueMultiple>() {
            public PrintValueMultiple apply(Response<ResponseEnvelope> response, Response<ResponseEnvelope> response2, Response<ResponseEnvelope> response3) throws Exception {
                return new PrintValueMultiple(TigerWcfAsyncTask.this.mapPrintValue (response), TigerWcfAsyncTask.this.mapPrintValue (response2), TigerWcfAsyncTask.this.mapPrintValue (response3));
            }
        });
    }
    public static void lambdagetPrintRxZip2(ResponseListener responseListener, Throwable th) {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    public RESULTXML mapPrintValue(Response<ResponseEnvelope> response) {
        RESULTXML resultxml = new RESULTXML();
        try {
            if (!response.isSuccessful() || response.body().getBody().getQuery().getStatus().getValue() != WcfResponseStatus.SUCCESS.getValue() || null == response.body ().getBody ().getQuery ().getResultXML ().getValue ()) {
                return resultxml;
            }
            String decompress = CompressUtil.decompress(response.body().getBody().getQuery().getResultXML().getValue());
            if (isEmpty(decompress)) {
                return resultxml;
            }
            try {
                return new Persister().read(RESULTXML.class, decompress);
            } catch (Exception e2) {
                e2.printStackTrace();
                return resultxml;
            }
        } catch (Exception e3) {
            Log.e(TAG, "mapPrintValue: ", e3.getCause());
            return resultxml;
        }
    }
    public Observable<Response<ResponseEnvelope>> createZipRxOne(TigerSelectResult tigerSelectResult) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
        return createAdapter ().execQueryRx(requestEnvelope);
    }
    public void getGenericRx(final TigerSelectResult tigerSelectResult, final ResponseListener<BaseSelectResult> responseListener, final String str) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) throws Exception {
                TigerWcfAsyncTask.this.lambdagetGenericRx6 (tigerSelectResult, str, responseListener);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetGenericRx7 ((TigerSelectResult) obj);
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
    public void lambdagetGenericRx6(final TigerSelectResult tigerSelectResult, String str, final ResponseListener responseListener) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        tigerSelectResult.setSql(str);
        requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
        createAdapter ().execQueryRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                TigerSelectResult lambdagetGenericRx3;
                try {
                    lambdagetGenericRx3 = TigerWcfAsyncTask.this.lambdagetGenericRx3(tigerSelectResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetGenericRx3;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((ArrayList) obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetGenericRx5 (responseListener, (Throwable) obj);
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
    public TigerSelectResult lambdagetGenericRx3(TigerSelectResult tigerSelectResult, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerSelectResult.setSuccess(true);
                    if (null != ((ResponseEnvelope) response.body ()).getBody ().getQuery ().getResultXML ().getValue ()) {
                        tigerSelectResult.setResultXML(CompressUtil.decompress(((ResponseEnvelope) response.body()).getBody().getQuery().getResultXML().getValue()));
                        tigerSelectResult.setDataList(this.saxGenericResultParser.tigerGetDataParser(tigerSelectResult.getResultXML()));
                    }
                } else {
                    tigerSelectResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getQuery().getErrorString().getValue());
                }
            } else {
                tigerSelectResult.setSuccess(response.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "callQuery: ", e2);
            tigerSelectResult.setSuccess(e2.getMessage());
        }
        tigerSelectResult.setCompleted(true);
        return tigerSelectResult;
    }
    public static void lambdagetGenericRx5(ResponseListener responseListener, Throwable th) {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    public void getGenericSpRx(final TigerSelectResult tigerSelectResult, final ResponseListener<BaseSelectResult> responseListener) {
        Observable observable = create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdagetGenericSpRx11(tigerSelectResult, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        observable.subscribeOn(Schedulers.newThread());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<DataResponse<ItemSlip>>() {
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
                StringBuilder sb = null;
                StringBuilder sb2 = sb;
                sb2.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
            }
            public void onError(Throwable th) {
                final String str2 = NetsisRestAsyncTask.TAG;
                Log.d(str2, " onError : " + (null != th.getMessage() ? th.getMessage() : ""));
                JsonPointer sb = null;
                sb.append(th.getMessage());
            }

            @Override
            public void onNext(Object t) {
            }

            public void onComplete() {
                Vector3 sb = null;
                if (0 < sb.length()) {
                    responseListener.onError(sb.toString());
                } else {
                    responseListener.onResponse(sales);
                }
            }
        });
    }
    public void lambdagetGenericSpRx11(final TigerSelectResult tigerSelectResult, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        final String sql = tigerSelectResult.getSql();
        tigerSelectResult.setSql(tigerSelectResult.getSql() + " " + this.mContext.getString(R.string.wcf_result_xml));
        requestEnvelope.getBody().setExecSP(createQuery (tigerSelectResult));
        createAdapter ().execSpRx(requestEnvelope).flatMap(new Function() {
            public Object apply(Object obj) {
                ObservableSource lambdagetGenericSpRx8;
                try {
                    lambdagetGenericSpRx8 = TigerWcfAsyncTask.this.lambdagetGenericSpRx8 (tigerSelectResult, responseListener, sql, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetGenericSpRx8;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    responseListener.onResponse((ArrayList) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetGenericSpRx10 (responseListener, (Throwable) obj);
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
    public ObservableSource lambdagetGenericSpRx8(TigerSelectResult tigerSelectResult, ResponseListener responseListener, String str, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerSelectResult.setSuccess(true);
                    if (null != ((ResponseEnvelope) response.body ()).getBody ().getQuery ().getResultXML ().getValue ()) {
                        tigerSelectResult.setResultXML(CompressUtil.decompress(((ResponseEnvelope) response.body()).getBody().getQuery().getResultXML().getValue()));
                        tigerSelectResult.setDataList(this.saxGenericResultParser.tigerGetDataParser(tigerSelectResult.getResultXML()));
                    }
                } else {
                    tigerSelectResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getQuery().getErrorString().getValue());
                }
            } else {
                getGenericRx (tigerSelectResult, responseListener, str);
            }
        } catch (Exception e2) {
            Log.e(TAG, "callQuery: ", e2);
            tigerSelectResult.setSuccess(false);
            tigerSelectResult.setErrorString(e2.getMessage());
        }
        tigerSelectResult.setCompleted(true);
        if (tigerSelectResult.isSuccess()) {
            return just(tigerSelectResult);
        }
        return error(new Throwable(tigerSelectResult.getErrorString()));
    }
    public static void lambdagetGenericSpRx10(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    public void getLastRx(final TigerSelectResult tigerSelectResult, final ResponseListener<List<Object>> responseListener) {
        Observable observable = create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdagetLastRx15(tigerSelectResult, responseListener, observableEmitter);
                } catch (Exception e) {
                    observableEmitter.onError(new RuntimeException(e));
                }
            }
        });
        observable.subscribeOn(Schedulers.newThread());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetLastRx16((TigerSelectResult) obj);
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
    public void lambdagetLastRx15(final TigerSelectResult tigerSelectResult, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
        createAdapter ().execQueryRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                List lambdagetLastRx12;
                try {
                    lambdagetLastRx12 = TigerWcfAsyncTask.this.lambdagetLastRx12 (tigerSelectResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetLastRx12;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).defaultIfEmpty(new ArrayList()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((ArrayList) obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetLastRx14 (responseListener, (Throwable) obj);
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
    public List lambdagetLastRx12(TigerSelectResult tigerSelectResult, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerSelectResult.setSuccess(true);
                    if (null != ((ResponseEnvelope) response.body ()).getBody ().getQuery ().getResultXML ().getValue ()) {
                        tigerSelectResult.setResultXML(CompressUtil.decompress(((ResponseEnvelope) response.body()).getBody().getQuery().getResultXML().getValue()));
                        tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), tigerSelectResult.getProcessType().getaClass()));
                        if (tigerSelectResult.isDatabaseSave()) {
                            ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().insert(tigerSelectResult.getDataList(), tigerSelectResult.isTableDelete());
                        }
                    }
                } else {
                    tigerSelectResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getQuery().getErrorString().getValue());
                }
            } else {
                tigerSelectResult.setSuccess(response.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "callQuery: ", e2);
            tigerSelectResult.setSuccess(e2.getMessage());
        }
        tigerSelectResult.setCompleted(true);
        return tigerSelectResult.getDataList();
    }
    public static void lambdagetLastRx14(ResponseListener responseListener, Throwable th) throws Exception {
        try {
            responseListener.onError(null != th ? th.getMessage() : "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void getLastRxBool(final TigerSelectResult tigerSelectResult, final ResponseListener<Boolean> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdagetLastRxBool20 (tigerSelectResult, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetLastRxBool21 ((TigerSelectResult) obj);
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
    public void lambdagetLastRxBool20(final TigerSelectResult tigerSelectResult, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
        createAdapter ().execQueryRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                List lambdagetLastRxBool17;
                try {
                    lambdagetLastRxBool17 = TigerWcfAsyncTask.this.lambdagetLastRxBool17 (tigerSelectResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetLastRxBool17;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new NetsisRestAsyncTaskExternalSyntheticLambda5()).defaultIfEmpty(new ArrayList()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetLastRxBool18 (responseListener, tigerSelectResult, (List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetLastRxBool19(responseListener, (Throwable) obj);
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
    public List lambdagetLastRxBool17(TigerSelectResult tigerSelectResult, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerSelectResult.setSuccess(true);
                    if (null != ((ResponseEnvelope) response.body ()).getBody ().getQuery ().getResultXML ().getValue ()) {
                        tigerSelectResult.setResultXML(CompressUtil.decompress(((ResponseEnvelope) response.body()).getBody().getQuery().getResultXML().getValue()));
                        tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), tigerSelectResult.getProcessType().getaClass()));
                        if (tigerSelectResult.isDatabaseSave()) {
                            ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().insert(tigerSelectResult.getDataList(), tigerSelectResult.isTableDelete());
                        }
                    }
                } else {
                    tigerSelectResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getQuery().getErrorString().getValue());
                }
            } else {
                tigerSelectResult.setSuccess(response.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "callQuery: ", e2);
            tigerSelectResult.setSuccess(e2.getMessage());
        }
        tigerSelectResult.setCompleted(true);
        return tigerSelectResult.getDataList();
    }
    public static void lambdagetLastRxBool18(ResponseListener responseListener, TigerSelectResult tigerSelectResult, List list) throws Exception {
        responseListener.onResponse(Boolean.valueOf(tigerSelectResult.isSuccess()));
    }
    public static void lambdagetLastRxBool19(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? handleNetworkError(th) : "");
    }
    public void directRxBool(final TigerSelectResult tigerSelectResult, final ResponseListener<Boolean> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdadirectRxBool25 (tigerSelectResult, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdadirectRxBool26 ((TigerSelectResult) obj);
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
    public void lambdadirectRxBool25(final TigerSelectResult tigerSelectResult, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setDirectQuery(createQuery (tigerSelectResult));
        createAdapter ().directQueryRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                List lambdadirectRxBool22;
                try {
                    lambdadirectRxBool22 = TigerWcfAsyncTask.lambdadirectRxBool22 (tigerSelectResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdadirectRxBool22;
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).defaultIfEmpty(new ArrayList()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdadirectRxBool23(responseListener, tigerSelectResult, (List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdadirectRxBool24 (responseListener, (Throwable) obj);
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
    public static List lambdadirectRxBool22(TigerSelectResult tigerSelectResult, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerSelectResult.setSuccess(true);
                } else {
                    tigerSelectResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getQuery().getErrorString().getValue());
                }
            } else {
                tigerSelectResult.setSuccess(response.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "callQuery: ", e2);
            tigerSelectResult.setSuccess(e2.getMessage());
        }
        tigerSelectResult.setCompleted(true);
        return tigerSelectResult.getDataList();
    }
    public static void lambdadirectRxBool23(ResponseListener responseListener, TigerSelectResult tigerSelectResult, List list) throws Exception {
        responseListener.onResponse(Boolean.valueOf(tigerSelectResult.isSuccess()));
    }
    public static void lambdadirectRxBool24(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    public void sendFicheRx(final TigerServiceResult tigerServiceResult, final ResponseListener<TigerServiceResult> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdasendFicheRx32 (tigerServiceResult, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendFicheRx33 ((TigerServiceResult) obj);
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
    public void lambdasendFicheRx32(final TigerServiceResult tigerServiceResult, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setAppendDataObject(createDataQuery (tigerServiceResult));
        createAdapter ().appendRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdasendFicheRx27;
                try {
                    lambdasendFicheRx27 = TigerWcfAsyncTask.lambdasendFicheRx27(tigerServiceResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdasendFicheRx27;
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                boolean isSuccess;
                isSuccess = ((TigerServiceResult) obj).isSuccess();
                return isSuccess;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdasendFicheRx29;
                try {
                    lambdasendFicheRx29 = TigerWcfAsyncTask.lambdasendFicheRx29((TigerServiceResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdasendFicheRx29;
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).defaultIfEmpty(tigerServiceResult).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((TigerServiceResult) obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendFicheRx31(responseListener, (Throwable) obj);
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
    public static TigerServiceResult lambdasendFicheRx27(TigerServiceResult tigerServiceResult, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerServiceResult.setSuccess(true);
                    if (null != ((ResponseEnvelope) response.body ()).getBody ().getQuery ().getResultXML ().getValue ()) {
                        tigerServiceResult.setDataReference(((ResponseEnvelope) response.body()).getBody().getDataQuery().getBdataReference().getValue());
                        tigerServiceResult.setParamXML(((ResponseEnvelope) response.body()).getBody().getDataQuery().getDparamXML().getValue());
                        tigerServiceResult.setDataXML(CompressUtil.decompress(((ResponseEnvelope) response.body()).getBody().getDataQuery().getCdataXML().getValue()));
                    }
                } else {
                    tigerServiceResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getDataQuery().getEerrorString().getValue());
                }
            } else {
                tigerServiceResult.setSuccess(response.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "sendFicheRx: ", e2);
            tigerServiceResult.setSuccess(e2.getMessage());
        }
        tigerServiceResult.setCompleted(true);
        return tigerServiceResult;
    }
    public static void lambdasendFicheRx31(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    private TigerServiceResult mapServiceResult(Response<ResponseEnvelope> response, TigerServiceResult tigerServiceResult) {
        try {
            try {
                if (response.isSuccessful()) {
                    if (response.body().getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                        tigerServiceResult.setSuccess(true);
                        ResponseBody body = response.body().getBody();
                        if (null != body.getDataQuery ().getCdataXML ().getValue ()) {
                            tigerServiceResult.setDataXML(CompressUtil.decompress(body.getDataQuery().getCdataXML().getValue()));
                        }
                        tigerServiceResult.setDataReference(body.getDataQuery().getBdataReference().getValue());
                    }
                } else {
                    tigerServiceResult.setSuccess(response.body().getBody().getDataQuery().getEerrorString().getValue());
                }
            } catch (Exception e2) {
                Log.e(TAG, "mapServiceResult: ", e2);
                tigerServiceResult.setSuccess(e2.getMessage());
            }
            tigerServiceResult.setCompleted(true);
            return tigerServiceResult;
        } catch (Throwable th) {
            tigerServiceResult.setCompleted(true);
            throw th;
        }
    }
    public void getCalculateAllProductLinePriceRx(final TigerServiceResult tigerServiceResult, final WcfPriceType wcfPriceType,
                                                  final ResponseListener<List<ResultPrice>> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdagetCalculateAllProductLinePriceRx38(tigerServiceResult, wcfPriceType, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetCalculateAllProductLinePriceRx39((TigerSelectResult) obj);
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
    public void lambdagetCalculateAllProductLinePriceRx38(final TigerServiceResult tigerServiceResult, WcfPriceType wcfPriceType,
                                                          final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setCalculateDataObject(createCalculateDataQuery (tigerServiceResult, wcfPriceType.getType()));
        createAdapter ().calculateRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdagetCalculateAllProductLinePriceRx34;
                try {
                    lambdagetCalculateAllProductLinePriceRx34 = TigerWcfAsyncTask.lambdagetCalculateAllProductLinePriceRx34(tigerServiceResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetCalculateAllProductLinePriceRx34;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                List<TigerServiceResult> lambdagetCalculateAllProductLinePriceRx35;
                try {
                    lambdagetCalculateAllProductLinePriceRx35 = TigerWcfAsyncTask.this.lambdagetCalculateAllProductLinePriceRx35 ((TigerServiceResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetCalculateAllProductLinePriceRx35;
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).defaultIfEmpty(new ArrayList<TigerServiceResult>()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    responseListener.onResponse((ArrayList<TigerServiceResult>) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetCalculateAllProductLinePriceRx37 (responseListener, (Throwable) obj);
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
    public static TigerServiceResult lambdagetCalculateAllProductLinePriceRx34(TigerServiceResult tigerServiceResult, Response response) throws Exception {
        try {
            Log.d(TAG, "getCalculateAllProductLinePriceRx: send xml: " + tigerServiceResult.getSendData());
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getDataQuery().getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerServiceResult.setSuccess(true);
                    if (null != ((ResponseEnvelope) response.body ()).getBody ().getDataQuery ().getCdataXML ().getValue ()) {
                        tigerServiceResult.setDataXML(CompressUtil.decompress(((ResponseEnvelope) response.body()).getBody().getDataQuery().getCdataXML().getValue()));
                        Log.d(TAG, "getCalculateAllProductLinePriceRx: return xml: " + tigerServiceResult.getDataXML());
                    }
                } else {
                    tigerServiceResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getDataQuery().getEerrorString().getValue());
                }
            } else {
                tigerServiceResult.setSuccess(response.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "callQuery: ", e2);
            tigerServiceResult.setSuccess(e2.getMessage());
        }
        tigerServiceResult.setCompleted(true);
        return tigerServiceResult;
    }
    public List lambdagetCalculateAllProductLinePriceRx35(TigerServiceResult tigerServiceResult) throws Exception {
        try {
            return xmlParser (tigerServiceResult.getDataXML());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void lambdagetCalculateAllProductLinePriceRx37(ResponseListener responseListener, Throwable th) throws Exception {
        try {
            responseListener.onError(null != th ? th.getMessage() : "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<ResultPrice> xmlParser(String str) {
        ArrayList arrayList = new ArrayList();
        if (null != str && !str.isEmpty()) {
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                try {
                    XmlPullParser newPullParser = newInstance.newPullParser();
                    String str2 = "";
                    Log.d(TAG, "xmlParser: source : " + str);
                    newPullParser.setInput(new StringReader(str));
                    try {
                        int eventType = newPullParser.getEventType();
                        ResultPrice resultPrice = null;
                        boolean z = false;
                        while (!z) {
                            String name = newPullParser.getName();
                            if (1 == eventType) {
                                z = true;
                            } else if (2 != eventType) {
                                if (3 != eventType) {
                                    if (4 == eventType) {
                                        str2 = newPullParser.getText();
                                    }
                                } else if ("TRANSACTION".equals(name)) {
                                    arrayList.add(null == resultPrice ? new ResultPrice() : resultPrice);
                                } else if (null != resultPrice) {
                                    setTotalXml (resultPrice, name, str2);
                                }
                            } else if ("TRANSACTION".equals(name)) {
                                resultPrice = new ResultPrice();
                            }
                            eventType = newPullParser.next();
                        }
                    } catch (Exception e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            } catch (Exception e4) {
                throw new RuntimeException(e4);
            }
        }
        return arrayList;
    }
    private void setTotalXml(ResultPrice resultPrice, String str, String str2) {
        if ("MASTER_CODE".equals(str)) {
            resultPrice.setMasterCode(str2);
            return;
        }
        if ("TYPE".equals(str)) {
            resultPrice.setType(StringUtils.convertStringToInt(str2));
            return;
        }
        if ("VAT_RATE".equals(str)) {
            resultPrice.setVat(StringUtils.convertStringToDouble(str2));
            return;
        }
        if ("CURR_PRICE".equals(str)) {
            resultPrice.setCurType(StringUtils.convertStringToInt(str2));
            return;
        }
        if ("PRICE".equals(str)) {
            resultPrice.setPrice(StringUtils.convertStringToDouble(str2));
            return;
        }
        if ("PC_PRICE".equals(str)) {
            resultPrice.setPcPrice(StringUtils.convertStringToDouble(str2));
            return;
        }
        if ("PRCLISTCODE".equals(str)) {
            resultPrice.setPriceCode(str2);
        } else if ("UNIT_CODE".equals(str)) {
            resultPrice.setUnitCode(str2);
        } else if ("VARIANTCODE".equals(str)) {
            resultPrice.setVariantCode(str2);
        }
    }
    public void getMaxMatterNo(final FicheType ficheType, final MatterSettings matterSettings, final TigerSelectResult tigerSelectResult, final ResponseListener<String> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdagetMaxMatterNo46 (tigerSelectResult, ficheType, matterSettings, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetMaxMatterNo47 ((TigerSelectResult) obj);
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
    public void lambdagetMaxMatterNo46(final TigerSelectResult tigerSelectResult, final FicheType ficheType, final MatterSettings matterSettings, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
        createAdapter ().execQueryRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                List lambdagetMaxMatterNo40;
                try {
                    lambdagetMaxMatterNo40 = TigerWcfAsyncTask.this.lambdagetMaxMatterNo40 (tigerSelectResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetMaxMatterNo40;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                String lambdagetMaxMatterNo41;
                try {
                    lambdagetMaxMatterNo41 = TigerWcfAsyncTask.lambdagetMaxMatterNo41 ((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetMaxMatterNo41;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                String lambdagetMaxMatterNo42;
                lambdagetMaxMatterNo42 = TigerWcfAsyncTask.this.lambdagetMaxMatterNo42 (ficheType, matterSettings, (String) obj);
                return lambdagetMaxMatterNo42;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).onErrorReturn(new Function() {
            public Object apply(Object obj) {
                String lambdagetMaxMatterNo43;
                try {
                    lambdagetMaxMatterNo43 = TigerWcfAsyncTask.this.lambdagetMaxMatterNo43 (ficheType, matterSettings, (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetMaxMatterNo43;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).defaultIfEmpty("").observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((ArrayList) obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetMaxMatterNo45(responseListener, (Throwable) obj);
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
    public List lambdagetMaxMatterNo40(TigerSelectResult tigerSelectResult, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                if (((ResponseEnvelope) response.body()).getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerSelectResult.setSuccess(true);
                    if (null != ((ResponseEnvelope) response.body ()).getBody ().getQuery ().getResultXML ().getValue ()) {
                        tigerSelectResult.setResultXML(CompressUtil.decompress(((ResponseEnvelope) response.body()).getBody().getQuery().getResultXML().getValue()));
                        tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), tigerSelectResult.getProcessType().getaClass()));
                    }
                } else {
                    tigerSelectResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getQuery().getErrorString().getValue());
                }
            } else {
                tigerSelectResult.setSuccess(response.message());
            }
        } catch (Exception e2) {
            Log.e(TAG, "callQuery: ", e2);
            tigerSelectResult.setSuccess(e2.getMessage());
        }
        tigerSelectResult.setCompleted(true);
        return tigerSelectResult.getDataList();
    }
    public static String lambdagetMaxMatterNo41(List list) throws Exception {
        if (0 >= list.size ()) {
            return "";
        }
        return ((Matter) list.get(0)).maxNo;
    }
    public String lambdagetMaxMatterNo43(FicheType ficheType, MatterSettings matterSettings, Throwable th) throws Exception {
        return lambdagetMaxMatterNo42 (ficheType, matterSettings, "");
    }
    public static void lambdagetMaxMatterNo45(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? handleNetworkError(th) : "");
    }
    private String lambdagetMaxMatterNo42(FicheType ficheType, MatterSettings matterSettings, String str) {
        long j2;
        int i2;
        String maxMatterNo = getMaxMatterNo (ficheType, matterSettings);
        if (isEmpty(str)) {
            str = maxMatterNo;
        }
        if (isEmpty(str)) {
            return matterSettings.getFirstMatterNo();
        }
        MatterRegex matterRegex = new MatterRegex(ContextUtils.getmContext().getString(R.string.matter_parse_regex));
        ArrayList<String> matterFind = matterRegex.getMatterFind(str);
        ArrayList<String> matterFind2 = matterRegex.getMatterFind(maxMatterNo);
        if (!matterFind.isEmpty()) {
            j2 = StringUtils.convertStringToLong(matterFind.get(matterFind.size() - 1));
            i2 = matterFind.get(matterFind.size() - 1).length();
        } else {
            j2 = 0;
            i2 = 0;
        }
        long convertStringToLong = !matterFind2.isEmpty() ? StringUtils.convertStringToLong(matterFind2.get(matterFind2.size() - 1)) : 0L;
        if (convertStringToLong > j2) {
            i2 = matterFind2.get(matterFind2.size() - 1).length();
            j2 = convertStringToLong;
        }
        String valueOf = String.valueOf(j2 + 1);
        if (valueOf.length() < i2) {
            String str2 = "";
            for (int i3 = 0; i3 < i2 - valueOf.length(); i3++) {
                str2 = str2 + "0";
            }
            valueOf = str2 + valueOf;
        }
        if (1 > matterFind.size ()) {
            return valueOf;
        }
        return (isEmpty(matterFind.get(0)) ? "" : matterFind.get(0)) + valueOf;
    }
    public void cancelFiche(final TigerServiceResult tigerServiceResult, final ResponseListener<Boolean> responseListener) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setReadDataObject(createReadDataQuery (tigerServiceResult));
        createAdapter ().readRx(requestEnvelope).filter(new Predicate() {
            public boolean test(Object obj) {
                boolean isSuccessful;
                isSuccessful = ((Response) obj).isSuccessful();
                return isSuccessful;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                ResponseBody lambdacancelFiche49;
                try {
                    lambdacancelFiche49 = TigerWcfAsyncTask.lambdacancelFiche49 ((Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdacancelFiche49;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).filter(new Predicate() {
            public boolean test(Object obj) {
                boolean lambdacancelFiche50;
                try {
                    lambdacancelFiche50 = TigerWcfAsyncTask.lambdacancelFiche50 ((ResponseBody) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdacancelFiche50;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                String lambdacancelFiche51;
                try {
                    lambdacancelFiche51 = TigerWcfAsyncTask.lambdacancelFiche51 ((ResponseBody) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdacancelFiche51;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdacancelFiche52;
                try {
                    lambdacancelFiche52 = TigerWcfAsyncTask.lambdacancelFiche52 ((String) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdacancelFiche52;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                RequestEnvelope lambdacancelFiche53;
                lambdacancelFiche53 = TigerWcfAsyncTask.this.lambdacancelFiche53 ((TigerServiceResult) obj);
                return lambdacancelFiche53;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) {
                Log.e(TigerWcfAsyncTask.TAG, "cancelFiche: ", (Throwable) obj);
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.this.lambdacancelFiche61 (tigerServiceResult, responseListener, (RequestEnvelope) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
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
                StringBuilder sb = null;
                StringBuilder sb2 = sb;
                sb2.append(dataResponse.getErrorDesc() + SqlLiteVariable._NEW_LINE);
            }
            public void onError(Throwable th) {
                final String str2 = NetsisRestAsyncTask.TAG;
                Log.d(str2, " onError : " + (null != th.getMessage() ? th.getMessage() : ""));
                JsonPointer sb = null;
                sb.append(th.getMessage());
            }
            public void onNext(Object t) {
            }
            public void onComplete() {
                Vector3 sb = null;
                if (0 < sb.length()) {
                    responseListener.onError(sb.toString());
                } else {
                    responseListener.onResponse(sales);
                }
            }
        });
    }
    public static ResponseBody lambdacancelFiche49(Response response) throws Exception {
        return ((ResponseEnvelope) response.body()).getBody();
    }
    public static boolean lambdacancelFiche50(ResponseBody responseBody) throws Exception {
        return responseBody.getDataQuery().getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue();
    }
    public static String lambdacancelFiche51(ResponseBody responseBody) throws Exception {
        return CompressUtil.decompress(responseBody.getDataQuery().getCdataXML().getValue());
    }
    public static TigerServiceResult lambdacancelFiche52(String str) throws Exception {
        try {
            return TigerSendDataCreator.getInstance().getCanceledFiche(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdacancelFiche61(final TigerServiceResult tigerServiceResult, final ResponseListener responseListener, RequestEnvelope requestEnvelope) throws Exception {
        createAdapter ().appendRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdacancelFiche55;
                try {
                    lambdacancelFiche55 = TigerWcfAsyncTask.lambdacancelFiche55 (tigerServiceResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdacancelFiche55;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                return Boolean.valueOf(((TigerServiceResult) obj).isSuccess());
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.this.lambdacancelFiche56 (tigerServiceResult, (Boolean) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) {
                try {
                    tigerServiceResult.setCompleted(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }).doOnError(new Consumer() {
            public void accept(Object obj) {
                try {
                    Log.e(TigerWcfAsyncTask.TAG, "cancelFiche: ", (Throwable) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdacancelFiche59 (responseListener, tigerServiceResult, (Boolean) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdacancelFiche60 (responseListener, (Throwable) obj);
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
    public static TigerServiceResult lambdacancelFiche55(TigerServiceResult tigerServiceResult, Response response) throws Exception {
        if (response.isSuccessful()) {
            if (((ResponseEnvelope) response.body()).getBody().getDataQuery().getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                tigerServiceResult.setSuccess(true);
            } else {
                tigerServiceResult.setSuccess(((ResponseEnvelope) response.body()).getBody().getDataQuery().getEerrorString().getValue());
            }
        } else {
            tigerServiceResult.setSuccess(response.message());
        }
        return tigerServiceResult;
    }
    public void lambdacancelFiche56(TigerServiceResult tigerServiceResult, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            updateCancelFiche (tigerServiceResult.getDataReference(), tigerServiceResult.getDataType());
        }
    }
    public static void lambdacancelFiche59(ResponseListener responseListener, TigerServiceResult tigerServiceResult, Boolean bool) throws Exception {
        responseListener.onResponse(Boolean.valueOf(tigerServiceResult.isSuccess()));
    }
    public static void lambdacancelFiche60(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(th.getMessage());
    }
    public void readOrderFiche(ArrayList<TigerServiceResult> arrayList, final Sales sales, final List<OrderAvailableAmount> list, final ResponseListener responseListener) {
        final StringBuilder sb = new StringBuilder();
        just(arrayList).flatMapIterable(new Function() {
            @Override
            public Object apply(Object o) {
                Iterable lambdareadOrderFiche62;
                try {
                    lambdareadOrderFiche62 = TigerWcfAsyncTask.lambdareadOrderFiche62((ArrayList) o);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdareadOrderFiche62;
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Response<ResponseEnvelope>>() {
            public void onSubscribe(Disposable disposable) {
            }
            public void onNext( Response<ResponseEnvelope> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getBody().getDataQuery().getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                            if (null != response.body ().getBody ().getDataQuery ().getCdataXML ().getValue ()) {
                                String decompress = CompressUtil.decompress(response.body().getBody().getDataQuery().getCdataXML().getValue());
                                SalesType salesType = sales.getmSalesType();
                                sales.setMSalesType(SalesType.ORDER.getmValue());
                                TigerSendDataCreator.getInstance().getXmlToSales(sales, decompress, list);
                                sales.setMSalesType(salesType.getmValue());
                                if (SalesUtils.isSalesTypeRetailInvoiceOrRetailReturnInvoice(salesType)) {
                                    Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
                                    while (it.hasNext()) {
                                        SalesDetail next = it.next();
                                        double calculateCurrPrice = next.getCalculateCurrPrice();
                                        next.resetSelectedPrice();
                                        FicheStringProp.setDefinition(StringUtils.convertDoubleToString(Double.valueOf(CalculateUtils.calculatePriceAddVat(calculateCurrPrice, next.getVat().getDefinitionDouble(), next.getIncludeVat().isSelect()))));
                                        next.getIncludeVat().setSelect(true);
                                    }
                                }
                                Log.d(TigerWcfAsyncTask.TAG, " onNext : value : " + sales.getOrderRef());
                                return;
                            }
                            return;
                        }
                        sb.append(response.body().getBody().getDataQuery().getEerrorString().getValue());
                        return;
                    }
                    Log.e(TigerWcfAsyncTask.TAG, " onNext : error : " + response.body().getBody().getDataQuery().getEerrorString().getValue());
                    sb.append(response.body().getBody().getDataQuery().getEerrorString().getValue() + SqlLiteVariable._NEW_LINE);
                } catch (Exception e2) {
                    Log.e(TigerWcfAsyncTask.TAG, "callQuery: ", e2);
                    throw new RuntimeException(e2);
                }
            }
            public void onError(Throwable th) {
                Log.d(TigerWcfAsyncTask.TAG, " onError : " + th.getMessage());
                sb.append(th.getMessage());
            }

            @Override
            public void onNext(Object t) {

            }

            public void onComplete() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                    if (!sb.isEmpty()) {
                        responseListener.onError(sb.toString());
                    } else {
                        sales.calculateSalesTotal();
                        TigerWcfAsyncTask.this.getItemsName (sales, responseListener);
                    }
                }
            }
        });
    }
    public ObservableSource lambdareadOrderFiche63(TigerServiceResult tigerServiceResult) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setReadDataObject(createReadDataQuery (tigerServiceResult));
        return createAdapter ().readRx(requestEnvelope);
    }
    public void readFiche(ArrayList<TigerServiceResult> arrayList, final Sales sales, final ResponseListener responseListener) {
        final StringBuilder sb = new StringBuilder();
        just(arrayList).flatMapIterable(new Function() {
            public Object apply(Object obj) {
                Iterable lambdareadFiche64;
                try {
                    lambdareadFiche64 = TigerWcfAsyncTask.lambdareadFiche64((ArrayList) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdareadFiche64;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Response<ResponseEnvelope>>() {
            public void onSubscribe(Disposable disposable) {
            }
            public void onNext(Response<ResponseEnvelope> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getBody().getDataQuery().getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                            if (null != response.body ().getBody ().getDataQuery ().getCdataXML ().getValue ()) {
                                TigerSendDataCreator.getInstance().getXmlToSales(sales, CompressUtil.decompress(response.body().getBody().getDataQuery().getCdataXML().getValue()), null);
                                Log.d(TigerWcfAsyncTask.TAG, " onNext : value : " + sales.getOrderRef());
                            }
                        } else {
                            sb.append(response.body().getBody().getDataQuery().getEerrorString().getValue());
                        }
                    } else {
                        Log.e(TigerWcfAsyncTask.TAG, " onNext : error : " + response.body().getBody().getDataQuery().getEerrorString().getValue());
                        sb.append(response.body().getBody().getDataQuery().getEerrorString().getValue() + SqlLiteVariable._NEW_LINE);
                    }
                } catch (Exception e2) {
                    Log.e(TigerWcfAsyncTask.TAG, "callQuery: ", e2);
                }
            }
            public void onError(Throwable th) {
                Log.d(TigerWcfAsyncTask.TAG, " onError : " + th.getMessage());
                sb.append(th.getMessage());
            }

            @Override
            public void onNext(Object t) {

            }

            public void onComplete() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                    if (!sb.isEmpty()) {
                        responseListener.onError(sb.toString());
                    } else {
                        sales.calculateSalesTotal();
                        TigerWcfAsyncTask.this.getItemsName (sales, responseListener);
                    }
                }
            }
        });
    }
    public static ObservableSource lambdareadFiche65(TigerServiceResult tigerServiceResult) throws Exception {
        try {
            RequestEnvelope requestEnvelope = new RequestEnvelope();
            requestEnvelope.setBody(new RequestBody());
            requestEnvelope.getBody().setReadDataObject(createReadDataQuery(tigerServiceResult));
            return createAdapter().readRx(requestEnvelope);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error reading fiche: %s", e.getMessage()));
        }
    }
    public void getItemsName(final Sales sales, final ResponseListener responseListener) {
        final HashMap<String, SalesDetail> hashMap = new HashMap<>();
        Iterator<SalesDetail> it = sales.getMSalesDetailList().iterator();
        String str = "";
        String str2 = "";
        while (it.hasNext()) {
            SalesDetail next = it.next();
            if (next.getName().isEmpty()) {
                if (next.isProduct()) {
                    str = str + " '" + next.getCode() + "',";
                    hashMap.put("0-" + next.getCode(), next);
                } else {
                    str2 = str2 + " '" + next.getCode() + "',";
                    hashMap.put("1-" + next.getCode(), next);
                }
            }
        }
        if (str.isEmpty() && str2.isEmpty()) {
            responseListener.onResponse(sales);
            return;
        }
        TigerSelectResult itemRemoteFromCode = TigerQueryCreator.getItemRemoteFromCode(!str.isEmpty() ? str.substring(0, str.length() - 1) : "''", str2.isEmpty() ? "''" : str2.substring(0, str2.length() - 1));
        try {
            getResponseEnvelopeCall (itemRemoteFromCode).enqueue(new BaseCallback(itemRemoteFromCode) {
                public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {
                    try {
                        super.onResponse(call, response);
                        if (response.isSuccessful() && response.body().getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                            for (int i2 = 0; i2 < this.selectResult.getDataList().size(); i2++) {
                                KeyValuePair keyValuePair = (KeyValuePair) this.selectResult.getDataList().get(i2);
                                hashMap.get(keyValuePair.getKey()).setName(keyValuePair.getValue());
                            }
                        }
                        responseListener.onResponse(sales);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                public void onFailure(Call<ResponseEnvelope> call, Throwable th) {
                    try {
                        super.onFailure(call, th);
                        Log.e(TigerWcfAsyncTask.TAG, "onFailure");
                        responseListener.onError(th.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
    private Call<ResponseEnvelope> getResponseEnvelopeCall(TigerSelectResult tigerSelectResult) {
        try {
            RequestEnvelope requestEnvelope = new RequestEnvelope();
            requestEnvelope.setBody(new RequestBody());
            requestEnvelope.getBody().setExecQuery(createQuery (tigerSelectResult));
            return createAdapter ().execQuery(requestEnvelope);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private RequestEnvelope lambdacancelFiche53(TigerServiceResult tigerServiceResult) {
        try {
            RequestEnvelope requestEnvelope = new RequestEnvelope();
            requestEnvelope.setBody(new RequestBody());
            requestEnvelope.getBody().setAppendDataObject(createDataQuery (tigerServiceResult));
            return requestEnvelope;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void updateCancelFiche(int i2, DataObjectType dataObjectType) {
        try {
            updateCancelFicheTransferByFicheRef (i2, dataObjectType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void getSalesOnlineTotal(final TigerServiceResult tigerServiceResult, Object responseListener) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setCalculateDataObject(createCalculateDataQuery (tigerServiceResult, findGetStockLinePrice (tigerServiceResult), 1 <= tigerServiceResult.getApplyCampaign ()));
        createAdapter ().calculateRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdagetSalesOnlineTotal66;
                try {
                    lambdagetSalesOnlineTotal66 = TigerWcfAsyncTask.lambdagetSalesOnlineTotal66(tigerServiceResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetSalesOnlineTotal66;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).map(new Function() {
            public Object apply(Object obj) {
                Sales lambdagetSalesOnlineTotal67;
                try {
                    lambdagetSalesOnlineTotal67 = TigerWcfAsyncTask.lambdagetSalesOnlineTotal67(tigerServiceResult, (TigerServiceResult) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetSalesOnlineTotal67;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetSalesOnlineTotal69 (( AnonymousClass6 ) responseListener, (Throwable) obj);
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
    public static TigerServiceResult lambdagetSalesOnlineTotal66(TigerServiceResult tigerServiceResult, Response response) throws Exception {
        if (response.isSuccessful()) {
            try {
                DataQuery dataQuery = ((ResponseEnvelope) response.body()).getBody().getDataQuery();
                if (dataQuery.getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerServiceResult.setDataXML(CompressUtil.decompress(dataQuery.getCdataXML().getValue()));
                } else {
                    tigerServiceResult.setErrorString(dataQuery.getEerrorString().getValue());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            tigerServiceResult.setErrorString(response.message());
        }
        return tigerServiceResult;
    }
    public static Sales lambdagetSalesOnlineTotal67(TigerServiceResult tigerServiceResult, TigerServiceResult tigerServiceResult2) throws Exception {
        try {
            Sales parseXml = new TigerXmlParserForUpdate().parseXml(tigerServiceResult);
            tigerServiceResult.setData(parseXml);
            return parseXml;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void lambdagetSalesOnlineTotal69(@UnknownNullability AnonymousClass6 responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? handleNetworkError(th) : "");
    }
    public void getSalesCampaign(final TigerServiceResult tigerServiceResult, final ResponseListener responseListener) {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setCalculateDataObject(createCalculateDataQuery (tigerServiceResult, findGetStockLinePrice (tigerServiceResult), true));
        createAdapter ().calculateRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdagetSalesCampaign70;
                try {
                    lambdagetSalesCampaign70 = TigerWcfAsyncTask.lambdagetSalesCampaign70(tigerServiceResult, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetSalesCampaign70;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetSalesCampaign72(responseListener, (Throwable) obj);
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
    public static TigerServiceResult lambdagetSalesCampaign70(TigerServiceResult tigerServiceResult, Response response) throws Exception {
        try {
            if (response.isSuccessful()) {
                DataQuery dataQuery = ((ResponseEnvelope) response.body()).getBody().getDataQuery();
                if (dataQuery.getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                    tigerServiceResult.setSuccess(true);
                    tigerServiceResult.setDataXML(CompressUtil.decompress(dataQuery.getCdataXML().getValue()));
                } else {
                    tigerServiceResult.setErrorString(dataQuery.getEerrorString().getValue());
                }
            } else {
                tigerServiceResult.setErrorString(response.message());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tigerServiceResult;
    }
    public static void lambdagetSalesCampaign72(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? handleNetworkError(th) : "");
    }
    @Override
    public Observable<BaseResult> getObservableResult(GroupItem groupItem) {
        return null;
    }
    public Disposable transferFiche(GroupItem groupItem, ResponseListener<GroupItem> responseListener) {
        return transferFiche (groupItem, responseListener, false);
    }
    public Disposable transferFiche(final GroupItem groupItem, final ResponseListener<GroupItem> responseListener, boolean z) {
        return getObservableResult (groupItem).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(groupItem);
            }
            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        });
    }
    public static void lambdatransferFiche74(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? handleNetworkError(th) : "");
    }
    public Disposable transferFiches(List<GroupItem> list, ResponseListener<GroupItem> responseListener, boolean z) {
        return transferFiches (list, responseListener);
    }
    public Disposable transferFiches(List<GroupItem> list, final ResponseListener<GroupItem> responseListener) {
        return just(list).flatMapIterable(new Function() {
            public Object apply(Object obj) {
                Iterable lambdatransferFiches75;
                try {
                    lambdatransferFiches75 = TigerWcfAsyncTask.lambdatransferFiches75((List) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdatransferFiches75;
            }
            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).doOnNext(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.this.lambdatransferFiches78(responseListener, (GroupItem) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.single()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse((GroupItem) obj);
            }
            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        });
    }
    public void lambdatransferFiches78(final ResponseListener responseListener, final GroupItem groupItem) throws Exception {
        getObservableResult (groupItem).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.single()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    responseListener.onResponse(groupItem);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdatransferFiches77(responseListener, (Throwable) obj);
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
    public static void lambdatransferFiches77(ResponseListener responseListener, Throwable th) throws Exception {
        try {
            responseListener.onError(null != th ? handleNetworkError(th) : "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void lambdatransferFiches80(ResponseListener responseListener, Throwable th) throws Exception {
        try {
            responseListener.onError(null != th ? handleNetworkError(th) : "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdagetObservableResult82(GroupItem GroupItem, BaseResult<Object> baseResult) throws Exception {
        Object BaseServiceResult = null;
        if (null == BaseServiceResult) {
            return;
        }
        if (BaseServiceResult instanceof BaseServiceResult) {
            TigerServiceResult tigerServiceResult = (TigerServiceResult) BaseServiceResult;
            DataObjectType dataType = tigerServiceResult.getDataType();
            DataObjectType dataObjectType = DataObjectType.ADDORDER;
            if (null == dataType) {
                return;
            }
            if (null == tigerServiceResult.getClCode()) {
                return;
            }
            if (dataType == dataObjectType && isEmpty(tigerServiceResult.getClCode())) {
                baseResult.setCompleted(true);
                baseResult.setSuccess(false);
                baseResult.setErrorString(ContextUtils.getStringResource(R.string.str_customer_ref_not_found));
                return;
            }
            CustomerRiskLimit customerOverdueDebt = getCustomerOverdueDebt (baseResult.getClRef());
            if (ErpCreator.getInstance().getmBaseErp().isOverdueDebt().booleanValue() && ((tigerServiceResult.getDataType() == dataObjectType || tigerServiceResult.getDataType() == DataObjectType.ADDINVOICE || tigerServiceResult.getDataType() == DataObjectType.ADDDISPATCH) && SalesUtils.isSalesTypeOrderOrInvoiceOrRetailInvoiceOrDispatch(((Sales) ((TigerServiceResult) baseResult).getData()).getmSalesType()) && 0.0d < customerOverdueDebt.getTotal ())) {
                baseResult.setCompleted(true);
                baseResult.setSuccess(false);
                baseResult.setErrorString(String.format(ContextUtils.getmContext().getString(R.string.exp_has_overdue_debt), customerOverdueDebt.getTotal() + " " + ErpCreator.getInstance().getmBaseErp().getLocalCurrencyCode()));
                return;
            }
            if (checkFicheNotDuplicate (tigerServiceResult, GroupItem.getTransferOperationName())) {
                sendServiceFiche (tigerServiceResult);
                GroupItem.setError(false);
                baseResult.setCompleted(true);
                baseResult.setSuccess(true);
                return;
            } else if (baseResult.isSuccess()) {
                baseResult.setCompleted(true);
                baseResult.setSuccess(true);
                GroupItem.setError(false);
                return;
            } else {
                baseResult.setCompleted(true);
                GroupItem.setErrorMessage(!isEmpty(baseResult.getErrorString()) ? baseResult.getErrorString() : "");
                GroupItem.setError(true);
                return;
            }
        }
        sendSelectFiche ((TigerSelectResult) baseResult);
    }
    public void lambdagetObservableResult83(GroupItem groupItem, BaseResult<Object> baseResult) {
        if ( baseResult.isSuccess() || isEmpty(baseResult.getErrorString()) || !baseResult.getErrorString().contains("DuplicateGuidError") || checkFicheNotDuplicate ((TigerServiceResult) baseResult, groupItem.getTransferOperationName())) {
            return;
        }
        try {
            baseResult.setCompleted(true);
            baseResult.setSuccess(true);
            groupItem.setError(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdagetObservableResult84(GroupItem groupItem, BaseResult baseResult) {
        if (checkResultCompleted (baseResult)) {
            return;
        }
        try {
            Log.d(TAG, "EMREtransferFiche: insert wor process");
            if (insertFicheInfoToWorProcess (groupItem.getTransferOperationName(), baseResult)) {
                return;
            }
            Log.d(TAG, "transferFiche: insert wor process failed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdagetObservableResult85(GroupItem groupItem, BaseResult baseResult) {
        if (checkResultCompleted (baseResult)) {
            return;
        }
        try {
            Log.d(TAG, "transferFiche: read and update fiche");
            if (!readAndUpdateFiche (baseResult, groupItem.getTransferOperationName())) {
                Log.d(TAG, "transferFiche: read and update fiche failed");
                if (baseResult.isLocalDifferentFromRemote()) {
                    try {
                        updateFicheDiffErrorAndFicheRef (baseResult);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            baseResult.setCompleted(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdagetObservableResult86(GroupItem groupItem, BaseResult baseResult) {
        if (checkResultCompleted (baseResult)) {
            return;
        }
        try {
            Log.d(TAG, "transferFiche: update Fiche");
            if (!updateFicheTransferAndFicheRef (baseResult)) {
                Log.d(TAG, "transferFiche: transfer update failed");
            }
            if (ErpCreator.getInstance().getmBaseErp().getRouteNewSystem()) {
                if (groupItem.getTransferOperationName() != TransferOperationName.ONE_TO_ONE_CHANGE || isOneToOneReturnDispatch (baseResult)) {
                    sendRouteProcessData (groupItem, baseResult);
                }
                Log.d(TAG, "send route process data failed");
            }
            if (isCabinTransSend (baseResult)) {
                sendCabinTransData (baseResult);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void lambdagetObservableResult87(GroupItem groupItem, BaseResult baseResult) {
        if (checkResultCompleted (baseResult)) {
            return;
        }
        Log.d(TAG, "transferFiche: update clfline");
        if (updateClfline (groupItem.getTransferOperationName(), baseResult)) {
            return;
        }
        Log.d(TAG, "transferFiche: update clfline failed");
    }
    public void lambdagetObservableResult88(BaseResult baseResult) {
        if (!checkResultCompleted (baseResult) && (baseResult instanceof BaseServiceResult baseServiceResult)) {
            if (ErpCreator.getInstance().getmBaseErp().sendCustomerFicheMail() || ErpCreator.getInstance().getmBaseErp().sendOtherMail()) {
                if (ErpCreator.getInstance().getmBaseErp().canSendDataTypeMail(baseServiceResult.getDataType())) {
                    try {
                        sendMail (baseServiceResult.getDataType(), baseServiceResult.getData(), getCustomerBeforeBalance (baseResult), baseServiceResult.getData() instanceof Sales ? ((Sales) ((TigerServiceResult) baseResult).getData()).getFicheNo() : StringUtils.empty());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public void lambdagetObservableResult89(BaseResult baseResult) {
        if (!checkResultCompleted (baseResult) && (baseResult instanceof TigerSelectResult) && ((TigerSelectResult) baseResult).getProcessType() == ProcessType.INSERTWORPROCESS) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("LATITUDE", 0);
                contentValues.put("LONGTITUDE", 0);
                contentValues.put("ISTRANSFERWORPROC", BuildConfig.NETSIS_DEMO_PASSWORD);
                ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getWritableDatabase().update("TODOINFO", contentValues, "LOGICALREF=" + baseResult.getLogicalRef(), null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public CustomerBeforeBalance getCustomerBeforeBalance(BaseResult baseResult) {
        TigerSelectResult tigerSelectResult;
        TigerServiceResult tigerServiceResult = (TigerServiceResult) baseResult;
        Tiger tiger = (Tiger) ErpCreator.getInstance().getmBaseErp();
        if (tigerServiceResult.getData() instanceof ChequeDeed) {
            tigerSelectResult = tiger.getTigerQueryCreator().getCustomerNowAndBeforeBalance(((ChequeDeed) tigerServiceResult.getData()).getChequedeed().clRef, Chequedeed.class, tigerServiceResult.getDataReference());
        } else if (tigerServiceResult.getData() instanceof CashCreditX) {
            tigerSelectResult = tiger.getTigerQueryCreator().getCustomerNowAndBeforeBalance(((CashCreditX) tigerServiceResult.getData()).getCashCredit().clRef, CashCredit.class, tigerServiceResult.getDataReference());
        } else if (tigerServiceResult.getData() instanceof CaseCash) {
            tigerSelectResult = tiger.getTigerQueryCreator().getCustomerNowAndBeforeBalance(((CaseCash) tigerServiceResult.getData()).clRef, CaseCash.class, tigerServiceResult.getDataReference());
        } else if (tigerServiceResult.getData() instanceof Sales) {
            tigerSelectResult = tiger.getTigerQueryCreator().getCustomerNowAndBeforeBalance(((Sales) tigerServiceResult.getData()).getClRef(), SalesUtils.isSalesTypeOrderOrDemand(((Sales) tigerServiceResult.getData()).getmSalesType()) ? Order.class : Invoice.class, tigerServiceResult.getDataReference());
        } else {
            tigerSelectResult = null;
        }
        if (null != tigerSelectResult) {
            runExec (tigerSelectResult);
        }
        if (null == tigerSelectResult || !tigerSelectResult.isCompleted() || !tigerSelectResult.isSuccess() || null == tigerSelectResult.getDataList () || 0 >= tigerSelectResult.getDataList ().size ()) {
            return null;
        }
        return (CustomerBeforeBalance) tigerSelectResult.getDataList().get(0);
    }
    private boolean checkResultCompleted(BaseResult baseResult) {
        return !(baseResult instanceof BaseSelectResult) && (!baseResult.isSuccess() || baseResult.isCompleted());
    }
    private boolean compareSendAndReceivedSales(BaseResult baseResult) {
        Sales sales;
        String str;
        if (!(baseResult instanceof BaseSelectResult) && (((TigerServiceResult) baseResult).getData() instanceof Sales)) {
            try {
                String guid = ((TigerServiceResult) baseResult).getGuid();
                if (guid.isEmpty() || null == (sales = (Sales) ((TigerServiceResult) baseResult).getData ())) {
                    return true;
                }
                switch (AnonymousClass8.SwitchMapcomprojemobilesalescoreenumsSalesType[sales.getmSalesType().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        str = "/SALES_INVOICES/INVOICE/GUID";
                        break;
                    case 5:
                        str = "/SALES_ORDERS/ORDER_SLIP/GUID";
                        break;
                    case 6:
                    case 7:
                    case 8:
                        str = "/SALES_DISPATCHES/DISPATCH/GUID";
                        break;
                    default:
                        return true;
                }
                String str2 = "";
                DOMParser newInstance = DOMParser.newInstance();
                try {
                    try {
                        try {
                            str2 = newInstance.getElementTextByPath(newInstance.parseXml(((TigerServiceResult) baseResult).getDataXML()), str);
                        } catch (SAXException e2) {
                            e2.printStackTrace();
                        }
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                } catch (ParserConfigurationException e4) {
                    e4.printStackTrace();
                }
                if (str2.isEmpty()) {
                    return true;
                }
                return guid.equals(str2);
            } catch (Exception e5) {
                Log.e(TAG, "compareSendAndReceivedSales: ", e5);
            }
        }
        return true;
    }
    private boolean isOneToOneReturnDispatch(BaseResult baseResult) {
        if (!(baseResult instanceof BaseSelectResult) && (((TigerServiceResult) baseResult).getData() instanceof Sales)) {
            try {
                if (null == ((TigerServiceResult) baseResult).getData ()) {
                    return true;
                }
                String str = "";
                DOMParser newInstance = DOMParser.newInstance();
                try {
                    str = newInstance.getElementTextByPath(newInstance.parseXml(((TigerServiceResult) baseResult).getDataXML()), "/SALES_DISPATCHES/DISPATCH/TYPE");
                } catch (IOException | ParserConfigurationException | SAXException e2) {
                    e2.printStackTrace();
                }
                return str.equals(ExifInterface.GPS_MEASUREMENT_3D);
            } catch (Exception e5) {
                Log.e(TAG, "compareSendAndReceivedSales: ", e5);
            }
        }
        return true;
    }
    private boolean readAndUpdateFiche(BaseResult baseResult, TransferOperationName transferOperationName) {
        if (baseResult instanceof BaseSelectResult baseSelectResult) {
            if (baseSelectResult.getProcessType() != ProcessType.INSERTVISIT) {
                return true;
            }
            TigerSelectResult visitLogicalRef = TigerQueryCreator.getVisitLogicalRef(baseSelectResult.getLogicalRef());
            runExec (visitLogicalRef);
            if (!visitLogicalRef.isSuccess() || 0 >= visitLogicalRef.getDataList ().size ()) {
                return true;
            }
            updateVisitAfterTransfer (baseSelectResult.getLogicalRef(), ((DocNo) visitLogicalRef.getDataList().get(0)).getLogicalRef());
            return true;
        }
        TigerServiceResult tigerServiceResult = (TigerServiceResult) baseResult;
        DataObjectType dataType = tigerServiceResult.getDataType();
        DataObjectType dataObjectType = DataObjectType.ADDWHTRANSFER;
        if (dataType != dataObjectType && (tigerServiceResult.getData() instanceof Sales)) {
            if (ErpCreator.getInstance().getmBaseErp().getDisableFicheCompare()) {
                runRead (baseResult);
                if (baseResult.isSuccess() && compareSendAndReceivedSales (baseResult)) {
                    TigerServiceResult tigerServiceResult2 = (TigerServiceResult) baseResult;
                    tigerServiceResult2.setData(new TigerXmlParserForUpdate().parseXml(tigerServiceResult2));
                    return updateFiche ((Sales) tigerServiceResult2.getData());
                }
                baseResult.setSuccess(false);
                return false;
            }
            return tryToResendFicheIfHasDiffError (tigerServiceResult, tigerServiceResult.getDataReference(), transferOperationName);
        }
        if (tigerServiceResult.getDataType() == dataObjectType) {
            runRead (baseResult);
            if (baseResult.isSuccess()) {
                DOMParser newInstance = DOMParser.newInstance();
                try {
                    updateFicheTransferAndFicheNo (baseResult.getLogicalRef(), ((TigerServiceResult) baseResult).getDataType(), newInstance.getElementTextByPath(newInstance.parseXml(((TigerServiceResult) baseResult).getDataXML()), "/MATERIAL_SLIPS/SLIP/NUMBER"));
                } catch (IOException | ParserConfigurationException | SAXException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (tigerServiceResult.getData() instanceof ChequeDeed) {
            runRead (baseResult);
            DOMParser newInstance2 = DOMParser.newInstance();
            try {
                ((ChequeDeed) ((TigerServiceResult) baseResult).getData()).getChequedeed().number = newInstance2.getElementTextByPath(newInstance2.parseXml(((TigerServiceResult) baseResult).getDataXML()), "/CQPN_ROLLS/CHQPN_ROLL/NUMBER");
            } catch (IOException | ParserConfigurationException | SAXException e5) {
                e5.printStackTrace();
            }
            if (baseResult.isSuccess()) {
                return updateChequeDeed ((ChequeDeed) ((TigerServiceResult) baseResult).getData());
            }
        }
        return false;
    }
    private boolean tryToResendFicheIfHasDiffError(TigerServiceResult tigerServiceResult, int i2, TransferOperationName transferOperationName) {
        FicheCompareResult ficheCompareResult;
        Sales sales = (Sales) tigerServiceResult.getData();
        boolean hasCampaignApplied = sales.hasCampaignApplied();
        FicheCompareResult ficheCompareResult2 = FicheCompareResult.REMOTEANDLOCALDIFFERENT;
        int i3 = 3;
        while (true) {
            ficheCompareResult = FicheCompareResult.REMOTEANDLOCALEQUAL;
            if (ficheCompareResult2 == ficheCompareResult || 0 >= i3) {
                break;
            }
            if (!hasCampaignApplied) {
                runRead (tigerServiceResult);
                tigerServiceResult.setData(new TigerXmlParserForUpdate().parseXml(tigerServiceResult));
                updateFiche ((Sales) tigerServiceResult.getData());
            }
            ficheCompareResult2 = compareTransferedAndLocalFiche (sales, tigerServiceResult, i2, hasCampaignApplied);
            Log.d("tryToResend", ficheCompareResult2.toString());
            if (ficheCompareResult2 == FicheCompareResult.REMOTEANDLOCALDIFFERENT) {
                TigerServiceResult build = (TigerServiceResult) TigerServiceResult.newBuilder().withDataReference(i2).withDataType(tigerServiceResult.getDataType()).build();
                runDelete (build);
                Log.d("tryToResend", "try count:" + i3 + " delete logicalRef:" + i2);
                build.setClRef(tigerServiceResult.getClRef());
                build.setDataReference(i2);
                deleteFromWorProcess (transferOperationName, build);
                Log.d("tryToResend", "try count:" + i3 + " delete process logicalRef:" + i2);
                tigerServiceResult.setSuccess(false);
                tigerServiceResult.setCompleted(false);
                tigerServiceResult.setErrorString(null);
                tigerServiceResult.setLocalDifferentFromRemote(false);
                sendServiceFiche (tigerServiceResult);
                i2 = tigerServiceResult.getDataReference();
                ficheCompareResult2 = compareTransferedAndLocalFiche (sales, tigerServiceResult, i2, hasCampaignApplied);
                Log.d("tryToResend", ficheCompareResult2.toString());
            }
            i3--;
        }
        if (ficheCompareResult2 == FicheCompareResult.REMOTEANDLOCALDIFFERENT) {
            insertDiffFicheToWorLog (i2, tigerServiceResult);
            if (hasCampaignApplied) {
                tigerServiceResult.setSuccess(false);
                tigerServiceResult.setErrorString(this.mContext.getString(R.string.str_diff_between_erpAndLocal));
                tigerServiceResult.setLocalDifferentFromRemote(true);
            } else {
                ficheCompareResult2 = ficheCompareResult;
            }
        }
        if (0 == tigerServiceResult.getDataReference ()) {
            tigerServiceResult.setDataReference(i2);
        } else {
            checkWorProcessInsertIfNotExists (transferOperationName, tigerServiceResult);
        }
        return ficheCompareResult2 == ficheCompareResult;
    }
    private FicheCompareResult compareTransferedAndLocalFiche(Sales sales, TigerServiceResult tigerServiceResult, int i2, boolean z) {
        List<Sales> retailInvoice;
        String string;
        retailInvoice = switch (AnonymousClass8.SwitchMapcomprojemobilesalescoreenumsSalesType[sales.getmSalesType().ordinal()]) {
            case 1 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getRetailInvoice(sales.getLogicalRef());
            case 2 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getRetailReturnInvoice(sales.getLogicalRef());
            case 3 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getReturnInvoice(sales.getLogicalRef());
            case 4 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getInvoice(sales.getLogicalRef());
            case 5 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getSalesList(sales.getLogicalRef());
            case 6 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getDispatch(sales.getLogicalRef());
            case 7 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getReturnDispatch(sales.getLogicalRef());
            case 8 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getOneToOne(sales.getLogicalRef());
            case 9 ->
                    ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getDemandList(sales.getLogicalRef());
            default -> null;
        };
        if (null == retailInvoice || 0 == retailInvoice.size ()) {
            return FicheCompareResult.COULDNOTREADLOCALFICHE;
        }
        TigerServiceResult build = (TigerServiceResult) TigerServiceResult.newBuilder().withDataType(tigerServiceResult.getDataType()).withDataReference(i2).build();
        runRead (build);
        if (!build.isSuccess()) {
            tigerServiceResult.setSuccess(false);
            if (!isEmpty(build.getErrorString())) {
                string = build.getErrorString();
            } else {
                string = this.mContext.getString(R.string.exp_29_io);
            }
            tigerServiceResult.setErrorString(string);
            tigerServiceResult.setLocalDifferentFromRemote(true);
            return FicheCompareResult.COULDNOTREADREMOTEFICHE;
        }
        SalesXml parse = SalesXmlParser.parse(tigerServiceResult.getDataType(), build.getDataXML());
        if (null == parse) {
            return FicheCompareResult.COULDNOTPARSEREMOTEXML;
        }
        List list = (List) parse.salesTransactions().stream().filter(( java.util.function.Predicate ) obj -> {
            boolean lambdacompareTransferedAndLocalFiche93;
            lambdacompareTransferedAndLocalFiche93 = TigerWcfAsyncTask.lambdacompareTransferedAndLocalFiche93 ((SalesTransactionXml) obj);
            return lambdacompareTransferedAndLocalFiche93;
        }).collect(Collectors.toList());
        return (1.0d <= Math.abs (((Double) retailInvoice.get (0).getMSalesDetailList ().stream ().map (( java.util.function.Function ) obj -> {
            final Double lambdacompareTransferedAndLocalFiche94;
            lambdacompareTransferedAndLocalFiche94 = lambdacompareTransferedAndLocalFiche94 ((SalesDetail) obj);
            return lambdacompareTransferedAndLocalFiche94;
        }).reduce (Double.valueOf (0.0d), new BinaryOperator () {
            public Object apply(final Object obj, final Object obj2) {
                return Double.valueOf (Double.sum (((Double) obj).doubleValue (), ((Double) obj2).doubleValue ()));
            }
        })).doubleValue () - ((Double) list.stream ().map (obj -> {
            final Double lambdacompareTransferedAndLocalFiche95;
            lambdacompareTransferedAndLocalFiche95 = lambdacompareTransferedAndLocalFiche95 ((SalesTransactionXml) obj);
            return lambdacompareTransferedAndLocalFiche95;
        }).reduce (Double.valueOf (0.0d), (obj, obj2) -> Double.valueOf (Double.sum (((Double) obj).doubleValue (), ((Double) obj2).doubleValue ())))).doubleValue ()) || list.size() != retailInvoice.get(0).getMSalesDetailList().size()) ?
                FicheCompareResult.REMOTEANDLOCALDIFFERENT : FicheCompareResult.REMOTEANDLOCALEQUAL;
    }
    public static boolean lambdacompareTransferedAndLocalFiche93(SalesTransactionXml salesTransactionXml) {
        return !isEmpty(salesTransactionXml.guid()) && (salesTransactionXml.type() == LineType.PRODUCT.value || salesTransactionXml.type() == LineType.COMPOSITE_COLI.value || salesTransactionXml.type() == LineType.PROMOTION.value || salesTransactionXml.type() == LineType.SERVICE.value);
    }
    public static Double lambdacompareTransferedAndLocalFiche94(SalesDetail salesDetail) {
        return Double.valueOf(salesDetail.getAmount().getDefinitionDouble());
    }
    public static Double lambdacompareTransferedAndLocalFiche95(SalesTransactionXml salesTransactionXml) {
        return Double.valueOf(salesTransactionXml.quantity());
    }
    private boolean insertDiffFicheToWorLog(int i2, TigerServiceResult tigerServiceResult) {
        String replace = tigerServiceResult.getSendData().replace("<?xml version='1.0' encoding='ISO-8859-9' ?>", "<?xml version=\"1.0\" encoding=\"ISO-8859-9\" ?>");
        int i3 = AnonymousClass8.SwitchMapcomprojemobilesalescoreenumsDataObjectType[tigerServiceResult.getDataType().ordinal()];
        BaseSelectResult insertDiffLog = ErpCreator.getInstance().getmBaseErp().getAppQuerable().insertDiffLog(1 != i3 ? 2 != i3 ? 3 != i3 ? 0 : 1 : 2 : 3, i2, tigerServiceResult.getGuid(), replace);
        runDirect (insertDiffLog);
        return insertDiffLog.isSuccess();
    }
    private boolean deleteFromWorProcess(TransferOperationName transferOperationName, BaseResult baseResult) {
        if (baseResult instanceof BaseSelectResult) {
            return true;
        }
        BaseSelectResult deleteFromWorProcess = ErpCreator.getInstance().getmBaseErp().getAppQuerable().deleteFromWorProcess(transferOperationName, (TigerServiceResult) baseResult);
        runDirect (deleteFromWorProcess);
        return deleteFromWorProcess.isSuccess();
    }
    private void checkWorProcessInsertIfNotExists(TransferOperationName transferOperationName, BaseResult baseResult) {
        List worProcess = getWorProcess (transferOperationName, baseResult);
        if (null != worProcess && 0 == worProcess.size ()) {
            insertFicheInfoToWorProcess (transferOperationName, baseResult);
        }
    }
    private List getWorProcess(TransferOperationName transferOperationName, BaseResult baseResult) {
        if (baseResult instanceof BaseSelectResult) {
            return null;
        }
        BaseSelectResult worProcess = ErpCreator.getInstance().getmBaseErp().getAppQuerable().getWorProcess(transferOperationName, (TigerServiceResult) baseResult);
        runExec (worProcess);
        if (worProcess.isSuccess()) {
            return worProcess.getDataList();
        }
        return null;
    }
    private List getVisitIdFromWorProcess(VisitInfo visitInfo) {
        BaseSelectResult visitIdFromWorProcess = ErpCreator.getInstance().getmBaseErp().getAppQuerable().getVisitIdFromWorProcess(visitInfo);
        runExec (visitIdFromWorProcess);
        if (visitIdFromWorProcess.isSuccess()) {
            return visitIdFromWorProcess.getDataList();
        }
        return null;
    }
    private boolean insertFicheInfoToWorProcess(TransferOperationName transferOperationName, BaseResult baseResult) {
        if (baseResult instanceof BaseSelectResult) {
            return true;
        }
        BaseSelectResult insertFicheProcess = ErpCreator.getInstance().getmBaseErp().getAppQuerable().insertFicheProcess(transferOperationName, (TigerServiceResult) baseResult);
        runDirect (insertFicheProcess);
        return insertFicheProcess.isSuccess();
    }
    private boolean updateClfline(TransferOperationName transferOperationName, BaseResult baseResult) {
        if (baseResult instanceof BaseSelectResult) {
            return true;
        }
        int i2 = AnonymousClass8.SwitchMapcomprojemobilesalescoreenumsTransferOperationName[transferOperationName.ordinal()];
        if (1 != i2 && 2 != i2 && 3 != i2 && 4 != i2 && 5 != i2) {
            return true;
        }
        TigerSelectResult tigerSelectResult = null;
        try {
            tigerSelectResult = TigerQueryCreator.getUpdateClfline(transferOperationName, (TigerServiceResult) baseResult);
        } catch (Exception e2) {
            Log.e(TAG, "getBeforeBalanceSql: ", e2);
        }
        if (null == tigerSelectResult) {
            return true;
        }
        runDirect (tigerSelectResult);
        return null != tigerSelectResult && tigerSelectResult.isSuccess();
    }
    private boolean updateFicheTransferAndFicheRef(BaseResult baseResult) {
        if (baseResult.isSuccess()) {
            if (baseResult instanceof BaseSelectResult baseSelectResult) {
                if (baseSelectResult.getProcessType() != ProcessType.INSERTWORPROCESS) {
                    return updateFicheTransferAndFicheRef (baseResult.getLogicalRef(), baseSelectResult.getProcessType());
                }
            }
            if (baseResult instanceof BaseServiceResult baseServiceResult) {
                int logicalRef = baseResult.getLogicalRef();
                return updateFicheTransferAndFicheRef (logicalRef, baseServiceResult.getDataType(), baseServiceResult.getDataReference());
            }
        }
        return false;
    }
    private boolean updateFicheDiffErrorAndFicheRef(BaseResult baseResult) {
        if (!baseResult.isLocalDifferentFromRemote() || !(baseResult instanceof BaseServiceResult baseServiceResult)) {
            return false;
        }
        int logicalRef = baseResult.getLogicalRef();
        return updateFicheDiffErrorAndFicheRef (logicalRef, baseServiceResult.getDataType(), baseServiceResult.getDataReference());
    }
    private void sendRouteProcessData(GroupItem groupItem, BaseResult baseResult) {
        if (baseResult.isSuccess()) {
            runDirect (ErpCreator.getInstance().getmBaseErp().getAppQuerable().insertRouteProcess(groupItem.getTransferOperationName(), baseResult));
        }
    }
    private void sendCabinTransData(BaseResult baseResult) {
        if (baseResult.isSuccess()) {
            BaseSelectResult insertCabinTrans = ErpCreator.getInstance().getmBaseErp().getAppQuerable().insertCabinTrans(baseResult);
            if (isEmpty(insertCabinTrans.getSql())) {
                return;
            }
            runDirect (insertCabinTrans);
            if (insertCabinTrans.isSuccess()) {
                Log.e(TAG, "insertCabinTransSuccess");
                updateCabinTransAfterTransfer (insertCabinTrans.getLogicalRef());
            }
        }
    }
    private void sendServiceFiche(TigerServiceResult tigerServiceResult) {
        runAppend (tigerServiceResult);
    }
    private void sendSelectFiche(TigerSelectResult tigerSelectResult) {
        if (tigerSelectResult.getProcessType() == ProcessType.INSERTVISIT) {
            List<VisitInfo> visits = ErpCreator.getInstance().getmBaseErp().getSendCreator().getSqlManager().getVisits(tigerSelectResult.getLogicalRef());
            if (null == visits || 0 == visits.size ()) {
                return;
            }
            List visitIdFromWorProcess = getVisitIdFromWorProcess (visits.get(0));
            if (null == visitIdFromWorProcess || 0 == visitIdFromWorProcess.size ()) {
                runDirect (tigerSelectResult);
                return;
            } else {
                tigerSelectResult.setSuccess(true);
                return;
            }
        }
        runDirect (tigerSelectResult);
    }
    private CustomerRiskLimit getCustomerOverdueDebt(int i2) {
        TigerSelectResult riskLimit = TigerQueryCreator.getRiskLimit(i2);
        runExec (riskLimit);
        if (riskLimit.isSuccess() && !riskLimit.getDataList().isEmpty()) {
            return (CustomerRiskLimit) riskLimit.getDataList().get(0);
        }
        return new CustomerRiskLimit();
    }
    private boolean checkFicheNotDuplicate(TigerServiceResult tigerServiceResult, TransferOperationName transferOperationName) {
        if (tigerServiceResult.isNotDuplicateControl()) {
            return true;
        }
        TigerSelectResult ficheDuplicateControlSql = TigerQueryCreator.getFicheDuplicateControlSql(tigerServiceResult.getDataType(), tigerServiceResult.getGuid());
        runExec (ficheDuplicateControlSql);
        if (ficheDuplicateControlSql.isSuccess()) {
            if (0 >= ficheDuplicateControlSql.getDataList ().size ()) {
                return true;
            }
            FicheDuplicate ficheDuplicate = (FicheDuplicate) ficheDuplicateControlSql.getDataList().get(0);
            if ((tigerServiceResult.getDataType() == DataObjectType.ADDDISPATCH || tigerServiceResult.getDataType() == DataObjectType.ADDINVOICE || tigerServiceResult.getDataType() == DataObjectType.ADDORDER) && !ErpCreator.getInstance().getmBaseErp().getDisableFicheCompare()) {
                tigerServiceResult.setDataReference(ficheDuplicate.getLogicalRef());
                if (doOnFicheDuplicateForInvoiceDispatchOrder (tigerServiceResult, ficheDuplicate.getLogicalRef(), transferOperationName)) {
                    tigerServiceResult.setSuccess(true);
                    updateFicheTransferAndFicheRef (tigerServiceResult.getLogicalRef(), tigerServiceResult.getDataType(), tigerServiceResult.getDataReference());
                    checkWorProcessInsertIfNotExists (transferOperationName, tigerServiceResult);
                } else {
                    tigerServiceResult.setSuccess(false);
                    if (tigerServiceResult.isLocalDifferentFromRemote()) {
                        updateFicheDiffErrorAndFicheRef (tigerServiceResult.getLogicalRef(), tigerServiceResult.getDataType(), tigerServiceResult.getDataReference());
                    }
                    checkWorProcessInsertIfNotExists (transferOperationName, tigerServiceResult);
                }
            } else {
                tigerServiceResult.setSuccess(true);
                updateFicheTransferAndFicheRef (tigerServiceResult.getLogicalRef(), tigerServiceResult.getDataType(), ficheDuplicate.getLogicalRef());
                checkWorProcessInsertIfNotExists (transferOperationName, tigerServiceResult);
            }
        } else {
            tigerServiceResult.setErrorString(ficheDuplicateControlSql.getErrorString());
        }
        return false;
    }
    private boolean doOnFicheDuplicateForInvoiceDispatchOrder(TigerServiceResult tigerServiceResult, int i2, TransferOperationName transferOperationName) {
        Sales sales = (Sales) tigerServiceResult.getData();
        if (null == sales) {
            updateFicheTransferAndFicheRef (tigerServiceResult.getLogicalRef(), tigerServiceResult.getDataType(), i2);
            return true;
        }
        if (1 == this.getIsTransfer (sales.getLogicalRef (), tigerServiceResult.getDataType ())) {
            tigerServiceResult.setErrorString(this.mContext.getString(R.string.str_fiche_already_sent));
            return false;
        }
        return tryToResendFicheIfHasDiffError (tigerServiceResult, i2, transferOperationName);
    }
    private Sales parseXml(TigerServiceResult tigerServiceResult) {
        if (tigerServiceResult.getDataType() == DataObjectType.ADDORDER || tigerServiceResult.getDataType() == DataObjectType.ADDINVOICE || tigerServiceResult.getDataType() == DataObjectType.ADDDISPATCH) {
            return TigerSendDataCreator.getInstance().getXmlToSales((Sales) tigerServiceResult.getData(), tigerServiceResult.getDataXML(), null);
        }
        return null;
    }
    private boolean updateFiche(Sales sales) {
        if (null != sales) {
            return updateFiche (sales, sales.getmSalesType());
        }
        return false;
    }
    private boolean updateChequeDeed(ChequeDeed chequeDeed) {
        if (null != chequeDeed) {
            return updateChequeDeed (chequeDeed, false);
        }
        return false;
    }
    public boolean readAndUpdateClCard(BaseResult baseResult) {
        if (baseResult instanceof BaseSelectResult) {
            return true;
        }
        if (!(((TigerServiceResult) baseResult).getData() instanceof CustomerNew)) {
            return false;
        }
        runRead (baseResult);
        if (!baseResult.isSuccess()) {
            return false;
        }
        TigerServiceResult tigerServiceResult = (TigerServiceResult) baseResult;
        return updateClCard (tigerServiceResult.getDataReference(), ((CustomerNew) tigerServiceResult.getData()).getLogicalRef());
    }
    public void getCalculateAddedProductLinesPriceRx(ArrayList<TigerServiceResult> arrayList, final List<WcfPriceType> list, final ResponseListener responseListener) {
        final StringBuilder sb = new StringBuilder();
        final ArrayList arrayList2 = new ArrayList();
        just(arrayList).flatMapIterable(new Function() {
            public Object apply(Object obj) {
                Iterable lambdagetCalculateAddedProductLinesPriceRx96;
                try {
                    lambdagetCalculateAddedProductLinesPriceRx96 = TigerWcfAsyncTask.lambdagetCalculateAddedProductLinesPriceRx96 ((ArrayList) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetCalculateAddedProductLinesPriceRx96;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Response<ResponseEnvelope>>() {
            public void onSubscribe(Disposable disposable) {
            }
            public void onNext(Response<ResponseEnvelope> response) {
                try {
                    list.remove(0);
                    if (response.isSuccessful()) {
                        if (response.body().getBody().getDataQuery().getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                            if (null != response.body ().getBody ().getDataQuery ().getCdataXML ().getValue ()) {
                                arrayList2.addAll(TigerWcfAsyncTask.this.xmlParser (CompressUtil.decompress(response.body().getBody().getDataQuery().getCdataXML().getValue())));
                            }
                        } else {
                            sb.append(response.body().getBody().getDataQuery().getEerrorString().getValue());
                        }
                    } else {
                        Log.e(TigerWcfAsyncTask.TAG, " onNext : error : " + response.body().getBody().getDataQuery().getEerrorString().getValue());
                        sb.append(response.body().getBody().getDataQuery().getEerrorString().getValue() + SqlLiteVariable._NEW_LINE);
                    }
                } catch (Exception e2) {
                    Log.e(TigerWcfAsyncTask.TAG, "callQuery: ", e2);
                }
            }
            public void onError(Throwable th) {
                Log.d(TigerWcfAsyncTask.TAG, " onError : " + th.getMessage());
                sb.append(th.getMessage());
            }
            public void onNext(Object t) { }
            public void onComplete() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                    if (!sb.isEmpty()) {
                        responseListener.onError(sb.toString());
                    } else {
                        responseListener.onResponse((ArrayList<Sales>) arrayList2);
                    }
                }
            }
        });
    }
    public ObservableSource lambdagetCalculateAddedProductLinesPriceRx97(List list, TigerServiceResult tigerServiceResult) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setCalculateDataObject(createCalculateDataQuery (tigerServiceResult, ((WcfPriceType) list.get(0)).getType()));
        return createAdapter ().calculateRx(requestEnvelope);
    }
    private boolean isCabinTransSend(BaseResult baseResult) {
        boolean z;
        boolean z2;
        if (baseResult instanceof BaseSelectResult) {
            return false;
        }
        TigerServiceResult tigerServiceResult = (TigerServiceResult) baseResult;
        if (tigerServiceResult.getData() instanceof Sales sales) {
            z2 = SalesUtils.isSalesTypeInvoiceOrReturnInvoiceOrRetailInvOrRetailReturnInv(sales.getmSalesType());
            z = SalesUtils.isSalesTypeDispatchOrReturnDispatch(sales.getmSalesType());
        } else {
            z = false;
            z2 = false;
        }
        return z2 || z || isOneToOneReturnDispatch (baseResult);
    }
    public void getEDespatchRxZip(final Observable<Response<ResponseEnvelope>> observable, final Observable<Response<ResponseEnvelope>> observable2, final ResponseListener<EDocumentPdfContent> responseListener) {
        Observable subscribeOn = defer(new Callable() {
            public Object call() {
                ObservableSource lambdagetEDespatchRxZip99;
                try {
                    lambdagetEDespatchRxZip99 = TigerWcfAsyncTask.this.lambdagetEDespatchRxZip99(observable, observable2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetEDespatchRxZip99;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        subscribeOn.subscribe(new TigerWcfAsyncTaskExternalSyntheticLambda25(responseListener), new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetEDespatchRxZip100(responseListener, (Throwable) obj);
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
    public ObservableSource lambdagetEDespatchRxZip99(Observable observable, Observable observable2) throws Exception {
        return zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), new BiFunction() {
            public Object apply(Object obj, Object obj2) {
                EDocumentPdfContent lambdagetEDespatchRxZip98;
                try {
                    lambdagetEDespatchRxZip98 = TigerWcfAsyncTask.this.lambdagetEDespatchRxZip98((Response) obj, (Response) obj2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetEDespatchRxZip98;
            }
        });
    }
    public EDocumentPdfContent lambdagetEDespatchRxZip98(Response response, Response response2) throws Exception {
        String str;
        TigerSelectResult build = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETEDESPATCHPDFHEADER).build();
        TigerSelectResult build2 = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETEDOCUMENTPDFDETAIL).build();
        mapResponse (response, build);
        mapResponse (response2, build2);
        StringBuilder sb = new StringBuilder();
        if (build.isSuccess()) {
            str = "";
        } else {
            str = build.getErrorString() + SqlLiteVariable._NEW_LINE;
        }
        sb.append(str);
        sb.append(build2.isSuccess() ? "" : build2.getErrorString());
        return new EDocumentPdfContent(!build.isSuccess() ? null : (EDespatchPdfHeader) build.getDataList().get(0), (List<EDocumentPdfDetail>) (!build2.isSuccess() ? new ArrayList() : build2.getDataList()), sb.toString());
    }
    public static void lambdagetEDespatchRxZip100(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    public void getEInvoiceRxZip(final Observable<Response<ResponseEnvelope>> observable, final Observable<Response<ResponseEnvelope>> observable2, final ResponseListener<EDocumentPdfContent> responseListener) {
        Observable subscribeOn = defer(new Callable() {
            public Object call() {
                ObservableSource lambdagetEInvoiceRxZip102;
                try {
                    lambdagetEInvoiceRxZip102 = TigerWcfAsyncTask.this.lambdagetEInvoiceRxZip102 (observable, observable2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetEInvoiceRxZip102;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        Objects.requireNonNull(responseListener);
        subscribeOn.subscribe(new TigerWcfAsyncTaskExternalSyntheticLambda25(responseListener), new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetEInvoiceRxZip103 (responseListener, (Throwable) obj);
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
    public ObservableSource lambdagetEInvoiceRxZip102(Observable observable, Observable observable2) throws Exception {
        return zip(observable.subscribeOn(Schedulers.newThread()), observable2.subscribeOn(Schedulers.newThread()), new BiFunction() {
            public Object apply(Object obj, Object obj2) {
                EDocumentPdfContent lambdagetEInvoiceRxZip101;
                try {
                    lambdagetEInvoiceRxZip101 = TigerWcfAsyncTask.this.lambdagetEInvoiceRxZip101 ((Response) obj, (Response) obj2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetEInvoiceRxZip101;
            }
        });
    }
    public EDocumentPdfContent lambdagetEInvoiceRxZip101(Response response, Response response2) throws Exception {
        String str;
        TigerSelectResult build = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETEINVOICEPDFHEADER).build();
        TigerSelectResult build2 = (TigerSelectResult) TigerSelectResult.newBuilder().withProcessType(ProcessType.GETEDOCUMENTPDFDETAIL).build();
        mapResponse (response, build);
        mapResponse (response2, build2);
        StringBuilder sb = new StringBuilder();
        if (build.isSuccess()) {
            str = "";
        } else {
            str = build.getErrorString() + SqlLiteVariable._NEW_LINE;
        }
        sb.append(str);
        sb.append(build2.isSuccess() ? "" : build2.getErrorString());
        return new EDocumentPdfContent(!build.isSuccess() ? null : (EInvoicePdfHeader) build.getDataList().get(0), (List<EDocumentPdfDetail>) (!build2.isSuccess() ? new ArrayList() : build2.getDataList()), sb.toString());
    }
    public static void lambdagetEInvoiceRxZip103(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    public void mapResponse(Response<ResponseEnvelope> response, TigerSelectResult tigerSelectResult) {
        try {
            try {
                if (response.isSuccessful()) {
                    if (response.body().getBody().getQuery().getStatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                        tigerSelectResult.setSuccess(true);
                        if (null != response.body ().getBody ().getQuery ().getResultXML ()) {
                            tigerSelectResult.setResultXML(CompressUtil.decompress(response.body().getBody().getQuery().getResultXML().getValue()));
                            tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), tigerSelectResult.getProcessType().getaClass()));
                        }
                    } else
                        tigerSelectResult.setSuccess(ContextUtils.getStringResource(R.string.error_general_message_for_object_service));
                } else {
                    tigerSelectResult.setSuccess(response.message());
                }
            } catch (Exception e2) {
                Log.e(TAG, "callQuery: ", e2);
                tigerSelectResult.setSuccess(e2.getMessage());
            }
            tigerSelectResult.setCompleted(true);
        } catch (Throwable th) {
            tigerSelectResult.setCompleted(true);
            throw th;
        }
    }
    public void getEDocumentContentRx(final String str, final EDocumentType eDocumentType, final ResponseListener<ShowEDocumentResponse> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdagetEDocumentContentRx107 (str, eDocumentType, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetEDocumentContentRx108 ((GetEDocumentContent) obj);
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
    public void lambdagetEDocumentContentRx107(final String str, EDocumentType eDocumentType, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setEdocumentContent(createGetEDocumentContent (str, eDocumentType));
        createAdapter ().getEdocumentContentRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                ShowEDocumentResponse lambdagetEDocumentContentRx104;
                try {
                    lambdagetEDocumentContentRx104 = TigerWcfAsyncTask.this.lambdagetEDocumentContentRx104 (str, (Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdagetEDocumentContentRx104;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetEDocumentContentRx106 (responseListener, (Throwable) obj);
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
    public ShowEDocumentResponse lambdagetEDocumentContentRx104(String str, Response response) throws Exception {
        ShowEDocumentResponse showEDocumentResponse = new ShowEDocumentResponse();
        showEDocumentResponse.setEttn(str);
        try {
            if (response.isSuccessful()) {
                if (null != ((ResponseEnvelope) response.body ()).getBody ().getEdocumentContent () && null != ((ResponseEnvelope) response.body ()).getBody ().getEdocumentContent ().getMsg ()) {
                    showEDocumentResponse.setHtmlContent(((ResponseEnvelope) response.body()).getBody().getEdocumentContent().getMsg().getValue());
                    showEDocumentResponse.setSuccess(true);
                    if (!isEmpty(showEDocumentResponse.getHtmlContent()) && !showEDocumentResponse.getHtmlContent().startsWith("<!DOCTYPE")) {
                        showEDocumentResponse.setErrorDesc(showEDocumentResponse.getHtmlContent());
                        showEDocumentResponse.setSuccess(false);
                    }
                }
            } else {
                showEDocumentResponse.setSuccess(false);
                showEDocumentResponse.setErrorDesc(this.mContext.getString(R.string.str_empty_list_text));
            }
        } catch (Exception e2) {
            Log.e(TAG, "getEDocumentContentRx: ", e2);
            showEDocumentResponse.setSuccess(false);
            showEDocumentResponse.setErrorDesc(e2.getMessage());
        }
        return showEDocumentResponse;
    }
    public static void lambdagetEDocumentContentRx106(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    private GetEDocumentContent createGetEDocumentContent(String str, EDocumentType eDocumentType) {
        GetEDocumentContent getEDocumentContent;
        synchronized (lock) {
            try {
                String str2 = "";
                int i2 = AnonymousClass8.SwitchMapcomprojemobilesalescoreedocumentEDocumentType[eDocumentType.ordinal()];
                if (1 == i2) {
                    str2 = "DESPATCHADVICE";
                } else if (2 == i2) {
                    str2 = "EARCHIVEINVOICE";
                } else if (3 == i2) {
                    str2 = "EINVOICE";
                }
                getEDocumentContent = new GetEDocumentContent();
                getEDocumentContent.setGuid(new StringElementAppend(str));
                getEDocumentContent.setDocTyp(new StringElementAppend(str2));
                getEDocumentContent.setOutFormat(new StringElementAppend("HTML"));
                getEDocumentContent.setSecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
                getEDocumentContent.setLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
            } catch (Throwable th) {
                throw th;
            }
        }
        return getEDocumentContent;
    }
    enum AnonymousClass8 {
        ;
        static final int[] SwitchMapcomprojemobilesalescoreedocumentEDocumentType;
        static final int[] SwitchMapcomprojemobilesalescoreenumsDataObjectType;
        static final int[] SwitchMapcomprojemobilesalescoreenumsSalesType;
        static final int[] SwitchMapcomprojemobilesalescoreenumsTransferOperationName;

        static {
            int[] iArr = new int[EDocumentType.values().length];
            SwitchMapcomprojemobilesalescoreedocumentEDocumentType = iArr;
            try {
                iArr[EDocumentType.ebtEIrs.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomprojemobilesalescoreedocumentEDocumentType[EDocumentType.ebtArsiv.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomprojemobilesalescoreedocumentEDocumentType[EDocumentType.ebtEFatura.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[TransferOperationName.values().length];
            SwitchMapcomprojemobilesalescoreenumsTransferOperationName = iArr2;
            try {
                iArr2[TransferOperationName.CASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CREDIT_CARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CASE_CASH.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.CHEQUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsTransferOperationName[TransferOperationName.DEED.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[DataObjectType.values().length];
            SwitchMapcomprojemobilesalescoreenumsDataObjectType = iArr3;
            try {
                iArr3[DataObjectType.ADDORDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDDISPATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsDataObjectType[DataObjectType.ADDINVOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
                throw new RuntimeException(unused11);
            }
            int[] iArr4 = new int[SalesType.values().length];
            SwitchMapcomprojemobilesalescoreenumsSalesType = iArr4;
            try {
                iArr4[SalesType.RETAIL_INVOICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
                throw new RuntimeException(unused12);
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.RETAIL_RETURN_INVOICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.RETURN_INVOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.INVOICE.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.ORDER.ordinal()] = 5;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.DISPATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.RETURN_DISPATCH.ordinal()] = 7;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.ONE_TO_ONE_CHANGE.ordinal()] = 8;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsSalesType[SalesType.DEMAND.ordinal()] = 9;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }
    public void sendRecvEInvoiceDocumentsRx(final String str, final ResponseListener<EDocumentResponse> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdasendRecvEInvoiceDocumentsRx112 (str, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendRecvEInvoiceDocumentsRx113 ((SendRecvEInvoiceDocuments) obj);
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
    public void lambdasendRecvEInvoiceDocumentsRx112(String str, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setSendRecvEInvoiceDocuments(createSendRecvEInvoiceDocuments (str));
        createAdapter ().sendRecvEInvoiceDocumentsRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                EDocumentResponse lambdasendRecvEInvoiceDocumentsRx109;
                try {
                    lambdasendRecvEInvoiceDocumentsRx109 = TigerWcfAsyncTask.this.lambdasendRecvEInvoiceDocumentsRx109 ((Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdasendRecvEInvoiceDocumentsRx109;
            }
            @Override
            public Object invoke(Object obj) { return null; }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendRecvEInvoiceDocumentsRx111 (responseListener, (Throwable) obj);
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
    public EDocumentResponse lambdasendRecvEInvoiceDocumentsRx109(Response response) throws Exception {
        EDocumentResponse eDocumentResponse = new EDocumentResponse();
        try {
            if (response.isSuccessful()) {
                if (response.body() == null) {
                    throw new IllegalArgumentException("Response body is null");
                }
                if (null != ((ResponseEnvelope) response.body()).getBody().getSendRecvEInvoiceDocuments() && null != ((ResponseEnvelope) response.body()).getBody().getSendRecvEInvoiceDocuments().getMsg()) {
                    eDocumentResponse.setMessage(((ResponseEnvelope) response.body()).getBody().getSendRecvEInvoiceDocuments().getMsg().getValue());
                    eDocumentResponse.setSuccess(true);
                }
            } else {
                eDocumentResponse.setSuccess(false);
                eDocumentResponse.setErrorDesc(this.mContext.getString(R.string.str_empty_list_text));
            }
        } catch (Exception e2) {
            Log.e(TAG, "sendRecvEInvoiceDocumentsRx: ", e2);
            eDocumentResponse.setSuccess(false);
            eDocumentResponse.setErrorDesc(e2.getMessage());
        }
        return eDocumentResponse;
    }
    public static void lambdasendRecvEInvoiceDocumentsRx111(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    private SendRecvEInvoiceDocuments createSendRecvEInvoiceDocuments(String str) {
        SendRecvEInvoiceDocuments sendRecvEInvoiceDocuments;
        synchronized (lock) {
            sendRecvEInvoiceDocuments = new SendRecvEInvoiceDocuments();
            sendRecvEInvoiceDocuments.setRefs(new StringElementAppend(str));
            sendRecvEInvoiceDocuments.setSend(new IntElement(1));
            sendRecvEInvoiceDocuments.setRecv(new IntElement(0));
            sendRecvEInvoiceDocuments.setSecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
            sendRecvEInvoiceDocuments.setLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
        }
        return sendRecvEInvoiceDocuments;
    }
    public void sendEArchiveDocumentsRx(final String str, final ResponseListener<EDocumentResponse> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdasendEArchiveDocumentsRx117 (str, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendEArchiveDocumentsRx118 ((SendEArchiveDocuments) obj);
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
    public void lambdasendEArchiveDocumentsRx117(String str, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setSendEArchiveDocuments(createSendEArchiveDocuments (str));
        createAdapter ().sendEArchiveDocumentsRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                EDocumentResponse lambdasendEArchiveDocumentsRx114;
                try {
                    lambdasendEArchiveDocumentsRx114 = TigerWcfAsyncTask.this.lambdasendEArchiveDocumentsRx114 ((Response) obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return lambdasendEArchiveDocumentsRx114;
            }

            @Override
            public Object invoke(Object obj) {
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
            @Override
            public Object invoke(Object obj) {

                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendEArchiveDocumentsRx116 (responseListener, (Throwable) obj);
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
    public EDocumentResponse lambdasendEArchiveDocumentsRx114(Response response) throws Exception {
        EDocumentResponse eDocumentResponse = new EDocumentResponse();
        try {
            if (response.isSuccessful()) {
                if (null != ((ResponseEnvelope) response.body ()).getBody ().getSendEArchiveDocuments () && null != ((ResponseEnvelope) response.body ()).getBody ().getSendEArchiveDocuments ().getMsg ()) {
                    eDocumentResponse.setMessage(((ResponseEnvelope) response.body()).getBody().getSendEArchiveDocuments().getMsg().getValue());
                    eDocumentResponse.setSuccess(true);
                }
            } else {
                eDocumentResponse.setSuccess(false);
                eDocumentResponse.setErrorDesc(this.mContext.getString(R.string.str_empty_list_text));
            }
        } catch (Exception e2) {
            Log.e(TAG, "sendEArchiveDocumentsRx: ", e2);
            eDocumentResponse.setSuccess(false);
            eDocumentResponse.setErrorDesc(e2.getMessage());
        }
        return eDocumentResponse;
    }
    public static void lambdasendEArchiveDocumentsRx116(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    private SendEArchiveDocuments createSendEArchiveDocuments(String str) {
        SendEArchiveDocuments sendEArchiveDocuments;
        synchronized (lock) {
            sendEArchiveDocuments = new SendEArchiveDocuments();
            sendEArchiveDocuments.setRefs(new StringElementAppend(str));
            sendEArchiveDocuments.setSecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
            sendEArchiveDocuments.setLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
        }
        return sendEArchiveDocuments;
    }
    public void sendRecvEDispatchDocumentsRx(final String str, final ResponseListener<EDocumentResponse> responseListener) {
        create(new ObservableOnSubscribe() {
            public void subscribe(ObservableEmitter observableEmitter) {
                try {
                    TigerWcfAsyncTask.this.lambdasendRecvEDispatchDocumentsRx122 (str, responseListener, observableEmitter);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendRecvEDispatchDocumentsRx123 ((SendRecvEDispatchDocuments) obj);
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
    public void lambdasendRecvEDispatchDocumentsRx122(String str, final ResponseListener responseListener, ObservableEmitter observableEmitter) throws Exception {
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setSendRecvEDispatchDocuments(createSendRecvEDispatchDocuments (str));
        createAdapter ().sendRecvEDispatchDocumentsRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                EDocumentResponse lambdasendRecvEDispatchDocumentsRx119;
                try {
                    lambdasendRecvEDispatchDocumentsRx119 = TigerWcfAsyncTask.this.lambdasendRecvEDispatchDocumentsRx119 ((Response) obj);
                } catch (Exception e) {  throw new RuntimeException(e); }
                return lambdasendRecvEDispatchDocumentsRx119;
            }
            @Override
            public Object invoke(Object obj) { return null; }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public void accept(Object obj) {
                responseListener.onResponse(obj);
            }
            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdasendRecvEDispatchDocumentsRx121 (responseListener, (Throwable) obj);
                } catch (Exception e) {  throw new RuntimeException(e); }
            }
            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        });
    }
    public EDocumentResponse lambdasendRecvEDispatchDocumentsRx119(Response response) throws Exception {
        EDocumentResponse eDocumentResponse = new EDocumentResponse();
        try {
            if (response.isSuccessful()) {
                if (null != ((ResponseEnvelope) response.body ()).getBody ().getSendRecvEDispatchDocuments () && null != ((ResponseEnvelope) response.body ()).getBody ().getSendRecvEDispatchDocuments ().getMsg ()) {
                    eDocumentResponse.setMessage(((ResponseEnvelope) response.body()).getBody().getSendRecvEDispatchDocuments().getMsg().getValue());
                    eDocumentResponse.setSuccess(true);
                }
            } else {
                eDocumentResponse.setSuccess(false);
                eDocumentResponse.setErrorDesc(this.mContext.getString(R.string.str_empty_list_text));
            }
        } catch (Exception e2) {
            Log.e(TAG, "sendRecvEDispatchDocumentsRx: ", e2);
            eDocumentResponse.setSuccess(false);
            eDocumentResponse.setErrorDesc(e2.getMessage());
        }
        return eDocumentResponse;
    }
    public static void lambdasendRecvEDispatchDocumentsRx121(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? th.getMessage() : "");
    }
    private SendRecvEDispatchDocuments createSendRecvEDispatchDocuments(String str) {
        SendRecvEDispatchDocuments sendRecvEDispatchDocuments;
        synchronized (lock) {
            sendRecvEDispatchDocuments = new SendRecvEDispatchDocuments();
            sendRecvEDispatchDocuments.setSend(new IntElement(1));
            sendRecvEDispatchDocuments.setRecv(new IntElement(0));
            sendRecvEDispatchDocuments.setDispatchRefs(new StringElementAppend(str));
            sendRecvEDispatchDocuments.setSecurityCode(new StringElementAppend(mTigerUserSettings.getSecurityCode()));
            sendRecvEDispatchDocuments.setLbsLoadPass(new StringElementAppend(_LBS_LOAD_P));
        }
        return sendRecvEDispatchDocuments;
    }
    public void getUsableCampaignCards(final TigerServiceResult tigerServiceResult, final ResponseListener responseListener) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        RequestEnvelope requestEnvelope = new RequestEnvelope();
        requestEnvelope.setBody(new RequestBody());
        requestEnvelope.getBody().setUsableCampaignCards(createGetUsableCampaignCardsQuery (tigerServiceResult));
        compositeDisposable.add(createAdapter ().getUsableCampaignCardsRx(requestEnvelope).map(new Function() {
            public Object apply(Object obj) {
                TigerServiceResult lambdagetUsableCampaignCards124;
                try {
                    lambdagetUsableCampaignCards124 = TigerWcfAsyncTask.lambdagetUsableCampaignCards124(tigerServiceResult, (Response) obj);
                } catch (Exception e) {  throw new RuntimeException(e); }
                return lambdagetUsableCampaignCards124;
            }
            @Override
            public Object invoke(Object obj) { return null; }
        }).map(new Function() {
            public Object apply(Object obj) {
                List lambdagetUsableCampaignCards125;
                try {
                    lambdagetUsableCampaignCards125 = TigerWcfAsyncTask.this.lambdagetUsableCampaignCards125((TigerServiceResult) obj);
                } catch (Exception e) {  throw new RuntimeException(e); }
                return lambdagetUsableCampaignCards125;
            }
            public Object invoke(Object obj) { return null;  }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() {
            public void accept(Object obj) {
                try {
                    responseListener.onResponse(obj);
                } catch (Exception e) {  throw new RuntimeException(e); }
            }
            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        }, new Consumer() {
            public void accept(Object obj) {
                try {
                    TigerWcfAsyncTask.lambdagetUsableCampaignCards127 (responseListener, (Throwable) obj);
                } catch (Exception e) {  throw new RuntimeException(e); }
            }
            @Override
            public Object invoke(Object obj) {
                return obj;
            }
        }));
    }
    public static TigerServiceResult lambdagetUsableCampaignCards124(TigerServiceResult tigerServiceResult, Response response) throws Exception {
        if (response.isSuccessful()) {
            DataQuery dataQuery = ((ResponseEnvelope) response.body()).getBody().getDataQuery();
            if (dataQuery.getFstatus().getValue() == WcfResponseStatus.SUCCESS.getValue()) {
                tigerServiceResult.setSuccess(true);
                tigerServiceResult.setDataXML(CompressUtil.decompress(dataQuery.getCdataXML().getValue()));
            } else {
                tigerServiceResult.setErrorString(dataQuery.getEerrorString().getValue());
            }
        } else {
            tigerServiceResult.setErrorString(response.message());
        }
        return tigerServiceResult;
    }
    public List lambdagetUsableCampaignCards125(TigerServiceResult tigerServiceResult) throws Exception {
        Type type = new TypeToken<ArrayList<UsableCampaignCard>>() {
        }.getType();
        ArrayList arrayList = new ArrayList();
        try {
            return new Gson().fromJson(tigerServiceResult.getDataXML(), type);
        } catch (Exception e2) {
            Log.e(TAG, "getUsableCampaignCards", e2);
            return arrayList;
        }
    }
    public static void lambdagetUsableCampaignCards127(ResponseListener responseListener, Throwable th) throws Exception {
        responseListener.onError(null != th ? handleNetworkError(th) : "");
    }
}
