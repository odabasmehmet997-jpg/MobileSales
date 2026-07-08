package com.proje.mobilesales.core.tigerwcf;

import android.os.AsyncTask;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import com.proje.mobilesales.core.interfaces.AsyncReportResponse;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.io.IOException;
import java.util.ArrayList;
import org.ksoap2.HeaderProperty;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.simpleframework.xml.core.Persister;
import org.springframework.http.HttpHeaders;
import org.xmlpull.v1.XmlPullParserException;



public class ServicesClientForTiger {
    private static final String METHOD_APPEND = "AppendDataObject";
    private static final String METHOD_CALC = "CalculateDataObject";
    private static final String METHOD_NAME = null;
    private static final String METODNAME_EXEC = "ExecQuery";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static String SERVICE_URL = null;
    private static final String SOAP_ACTION = null;
    private static final String SOAP_ACTIONBASE = "http://tempuri.org/ISvc/";
    private static final String SOAP_ACTION_EXEC = "http://tempuri.org/ISvc/ExecQuery";
    private static final String TAG = "ServicesClientForTiger";
    private static final int TIME_OUT = 120000;
    public static final int TRANSFER_PART_SIZE = 50000;
    private static String WCF_ADDRESS;
    private static String WCF_SECURITY_CODE;
    private static String _LBS_LOAD_P;
    public AsyncReportResponse delegateReport;

    public ServicesClientForTiger(AsyncReportResponse asyncReportResponse) {
        this.delegateReport = null;
        Preferences.TigerUserSettings tigerUserSettings = new Preferences.TigerUserSettings(ContextUtils.getmContext());
        WCF_ADDRESS = tigerUserSettings.getServerAddress();
        WCF_SECURITY_CODE = tigerUserSettings.getSecurityCode();
        _LBS_LOAD_P = TigerSecret.get(ContextUtils.getmContext());
        this.delegateReport = asyncReportResponse;
        setServiceParams();
    }

    private void setServiceParams() {
        SERVICE_URL = WCF_ADDRESS + "/LogoObjectService/Service/";
    }

    public SelectResult callList(SelectResult selectResult) {
        SoapObject soapObject = new SoapObject(NAMESPACE, METODNAME_EXEC);
        new CompressUtil();
        soapObject.addProperty("sqlText", CompressUtil.base64Encode(selectResult.sql));
        soapObject.addProperty("orderByText", selectResult.orderByText);
        soapObject.addProperty("securityCode", WCF_SECURITY_CODE);
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.type = PropertyInfo.OBJECT_CLASS;
        propertyInfo.setName("resultXML");
        propertyInfo.setValue("");
        soapObject.addProperty(propertyInfo);
        soapObject.addProperty("errorString", "");
        PropertyInfo propertyInfo2 = new PropertyInfo();
        propertyInfo2.type = byte[].class;
        propertyInfo2.setName(NotificationCompat.CATEGORY_STATUS);
        propertyInfo2.setValue(0);
        soapObject.addProperty(propertyInfo2);
        soapObject.addProperty("LbsLoadPass", _LBS_LOAD_P);
        SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(110);
        soapSerializationEnvelope.dotNet = true;
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        if (!SERVICE_URL.contains(RetrofitServiceFactory.HTTP)) {
            SERVICE_URL = RetrofitServiceFactory.HTTP + SERVICE_URL;
        }
        HttpTransportSE httpTransportSE = new HttpTransportSE(SERVICE_URL, TIME_OUT);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HeaderProperty(HttpHeaders.CONNECTION, "close"));
        try {
            httpTransportSE.debug = true;
            httpTransportSE.call(SOAP_ACTION_EXEC, soapSerializationEnvelope, arrayList);
            Object obj = soapSerializationEnvelope.bodyIn;
            if (obj instanceof SoapObject soapObject2) {
                selectResult.status = soapObject2.getProperty(NotificationCompat.CATEGORY_STATUS).toString();
                selectResult.errorString = soapObject2.getProperty("errorString").toString();
                if (selectResult.status.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    selectResult.resultXML = CompressUtil.decompress(soapObject2.getProperty("resultXML"));
                }
            }
        } catch (HttpResponseException e2) {
            e2.printStackTrace();
            selectResult.errorString += SqlLiteVariable._NEW_LINE + e2.getMessage();
        } catch (IOException e3) {
            e3.printStackTrace();
            selectResult.errorString += SqlLiteVariable._NEW_LINE + e3.getMessage();
        } catch (XmlPullParserException e4) {
            e4.printStackTrace();
            selectResult.errorString += SqlLiteVariable._NEW_LINE + e4.getMessage();
        }
        selectResult.isGetCompleted = true;
        return selectResult;
    }

    public class ReportRetrieve extends AsyncTask<String, String, SelectResult> {
        private final SelectResult selectResult;

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            ServicesClientForTiger.this.delegateReport.onPreExecute(this.selectResult.type);
        }

        public ReportRetrieve(SelectResult selectResult) {
            this.selectResult = selectResult;
        }

        @Override // android.os.AsyncTask
        protected SelectResult doInBackground(String... strArr) {
            try {
                return ServicesClientForTiger.this.callList(this.selectResult);
            } catch (Exception unused) {
                ServicesClientForTiger.this.delegateReport.processFinish(null, this.selectResult.type);
                return null;
            }
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(SelectResult selectResult) {
            super.onPostExecute((ReportRetrieve) selectResult);
            REPORTXML reportxml = null;
            if (selectResult != null && selectResult.status.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                Persister persister = new Persister();
                try {
                    if (!selectResult.resultXML.equals("")) {
                        String replace = selectResult.resultXML.replace("RESULTXML", "REPORTXML");
                        selectResult.resultXML = replace;
                        String replace2 = replace.replace("RESULTLINE", "REPORTLINE");
                        selectResult.resultXML = replace2;
                        reportxml = persister.read(REPORTXML.class, replace2);
                    } else {
                        selectResult.resultXML = "";
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            ServicesClientForTiger.this.delegateReport.processFinish(reportxml, selectResult.type);
        }
    }
}
