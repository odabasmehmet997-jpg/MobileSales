package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.CompleteRegisterListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.CompleteRegisterResult;
import cardtek.masterpass.util.a;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import k.d;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class h implements Runnable {
    final /* synthetic */ CompleteRegisterListener sM;
    final /* synthetic */ String sw;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;

    public h(b bVar, CompleteRegisterListener completeRegisterListener, String str, String str2, String str3) {
        this.sz = bVar;
        this.sM = completeRegisterListener;
        this.sw = str;
        this.sx = str2;
        this.sy = str3;
    }

    /*  DEBUG: Multi-variable search result rejected for r0v9, resolved type: cardtek.masterpass.management.ServiceResponse */
    /*  DEBUG: Multi-variable search result rejected for r0v10, resolved type: cardtek.masterpass.results.CompleteRegisterResult */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        ServiceError serviceError;
        try {
            if (!(b.sn)) {
                InternalError internalError = new InternalError();
                a aVar2 = a.E014;
                internalError.setErrorCode(aVar2.name);
                internalError.setErrorDesc(aVar2.value);
                this.sM.onInternalError(internalError);
                return;
            }
            JSONObject jSONObject = new JSONObject(this.sz.sh.a(new d(this.sx, this.sz.sl, b.sm.getToken(), URLEncoder.encode(this.sw, StandardCharsets.UTF_8), this.sy), ab.tB));
            JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
            JSONObject jSONObject3 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
            if (jSONObject2.has("RefNo") && !jSONObject2.getString("RefNo").equals("null") && !jSONObject2.getString("RefNo").equals("")) {
                CompleteRegisterResult completeRegisterResult = new CompleteRegisterResult();
                completeRegisterResult.setRefNo(jSONObject2.getString("RefNo"));
                completeRegisterResult.setToken(jSONObject2.getString("Token"));
                serviceError = completeRegisterResult;
            } else if (!jSONObject3.has("Token") || jSONObject3.getString("Token").equals("null") || jSONObject3.getString("Token").equals("")) {
                ServiceError serviceError2 = new ServiceError();
                serviceError2.setRefNo(jSONObject3.getString("RefNo"));
                serviceError2.setResponseCode(jSONObject3.getString("ResponseCode"));
                serviceError2.setResponseDesc(jSONObject3.getString("ResponseDesc"));
                if (jSONObject3.has("InternalResponseCode")) {
                    serviceError2.setInternalRespCode(jSONObject3.getString("InternalResponseCode"));
                }
                if (jSONObject3.has("InternalResponseMessage")) {
                    serviceError2.setInternalRespDesc(jSONObject3.getString("InternalResponseMessage"));
                }
                serviceError = serviceError2;
            } else {
                ServiceResponse serviceResponse = new ServiceResponse();
                serviceResponse.setRefNo(jSONObject3.getString("RefNo"));
                serviceResponse.setToken(jSONObject3.getString("Token"));
                serviceResponse.setResponseCode(jSONObject3.getString("ResponseCode"));
                serviceResponse.setResponseDesc(jSONObject3.getString("ResponseDesc"));
                if (jSONObject3.has("Url3D")) {
                    serviceResponse.setUrl3D(jSONObject3.getString("Url3D"));
                }
                if (jSONObject3.has("Url3DSuccess")) {
                    serviceResponse.setUrl3DSuccess(jSONObject3.getString("Url3DSuccess"));
                }
                serviceError = serviceResponse;
                if (jSONObject3.has("Url3DError")) {
                    serviceResponse.setUrl3DError(jSONObject3.getString("Url3DError"));
                    serviceError = serviceResponse;
                }
            }
            if (serviceError instanceof ServiceResponse) {
                ServiceResponse unused = b.sm = (ServiceResponse) serviceError;
                ServiceResult serviceResult = new ServiceResult();
                serviceResult.setRefNo(b.sm.getRefNo());
                serviceResult.setResponseCode(b.sm.getResponseCode());
                serviceResult.setResponseDesc(b.sm.getResponseDesc());
                this.sM.onVerifyUser(serviceResult);
            } else if (serviceError instanceof CompleteRegisterResult) {
                this.sM.onSuccess((CompleteRegisterResult) serviceError);
            } else {
                this.sM.onServiceError(serviceError);
            }
        } catch (Exception e2) {
            InternalError internalError2 = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError2.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError2.setErrorDesc(str);
                    this.sM.onInternalError(internalError2);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError2.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError2.setErrorDesc(str);
            this.sM.onInternalError(internalError2);
            e2.printStackTrace();
        }
    }
}
