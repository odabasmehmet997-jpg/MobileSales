package cardtek.masterpass.management;

import android.os.Build;
import cardtek.masterpass.interfaces.AddCardToMasterPassListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.util.a;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public final class m implements Runnable {
    final /* synthetic */ AddCardToMasterPassListener sV;
    final /* synthetic */ String sw;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;

    public m(b bVar, String str, AddCardToMasterPassListener addCardToMasterPassListener, String str2, String str3) {
        this.sz = bVar;
        this.sw = str;
        this.sV = addCardToMasterPassListener;
        this.sx = str2;
        this.sy = str3;
    }

    @Override
    public void run() {
        a aVar;
        String str;
        ServiceError serviceError;
        try {
            String str2 = this.sw;
            if (str2 != null && !str2.isEmpty()) {
                JSONObject jSONObject = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    jSONObject = new JSONObject(this.sz.sh.a(new k.a(this.sx, this.sz.sl, URLEncoder.encode(this.sw, StandardCharsets.UTF_8), this.sy), ab.tJ));
                }
                if (jSONObject == null) {
                    serviceError = new ServiceError();
                    serviceError.setErrorCode("JSON_PARSE_ERROR");
                    serviceError.setErrorMessage("Failed to parse JSON response");
                    this.sV.onAddCardToMasterPassError(serviceError);
                    return;
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
                JSONObject jSONObject3 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
                if (jSONObject2.has("RefNo") && !jSONObject2.getString("RefNo").equals("")) {
                    ServiceError addCardToMasterPassResult = new ServiceError();
                    addCardToMasterPassResult.setRefNo(jSONObject2.getString("RefNo"));
                    serviceError = addCardToMasterPassResult;
                } else if (!jSONObject3.has("Token") || jSONObject3.getString("Token").equals("")) {
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
                    this.sV.onVerifyUser(serviceResult);
                    return;
                } else if (serviceError instanceof AddCardToMasterPassResult) {
                    this.sV.onSuccess((AddCardToMasterPassResult) serviceError);
                    return;
                } else {
                    this.sV.onServiceError(serviceError);
                    return;
                }
            }
            InternalError internalError = new InternalError();
            a aVar2 = a.E006;
            internalError.setErrorCode(aVar2.name);
            internalError.setErrorDesc(aVar2.value);
            this.sV.onInternalError(internalError);
        } catch (Exception e2) {
            InternalError internalError2 = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError2.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError2.setErrorDesc(str);
                    this.sV.onInternalError(internalError2);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError2.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError2.setErrorDesc(str);
            this.sV.onInternalError(internalError2);
            e2.printStackTrace();
        }
    }
}
