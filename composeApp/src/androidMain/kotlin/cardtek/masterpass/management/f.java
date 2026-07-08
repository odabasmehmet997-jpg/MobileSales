package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.VerifyMPinListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.util.a;
import k.t;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class f implements Runnable {
    final /* synthetic */ VerifyMPinListener sF;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;

    public f(b bVar, String str, String str2, VerifyMPinListener verifyMPinListener) {
        this.sz = bVar;
        this.sx = str;
        this.sy = str2;
        this.sF = verifyMPinListener;
    }

    /*  DEBUG: Multi-variable search result rejected for r2v8, resolved type: cardtek.masterpass.response.ServiceError */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        ServiceResponse serviceResponse;
        try {
            JSONObject jSONObject = new JSONObject(this.sz.sh.a(new t(this.sx, this.sz.sl, this.sy), ab.tG));
            jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
            JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
            if (!jSONObject2.has("Token") || jSONObject2.getString("Token").equals("null") || jSONObject2.getString("Token").equals("")) {
                ServiceError serviceError = new ServiceError();
                serviceError.setRefNo(jSONObject2.getString("RefNo"));
                serviceError.setResponseCode(jSONObject2.getString("ResponseCode"));
                serviceError.setResponseDesc(jSONObject2.getString("ResponseDesc"));
                if (jSONObject2.has("InternalResponseCode")) {
                    serviceError.setInternalRespCode(jSONObject2.getString("InternalResponseCode"));
                }
                if (jSONObject2.has("InternalResponseMessage")) {
                    serviceError.setInternalRespDesc(jSONObject2.getString("InternalResponseMessage"));
                }
                serviceResponse = serviceError;
            } else {
                serviceResponse = new ServiceResponse();
                serviceResponse.setRefNo(jSONObject2.getString("RefNo"));
                serviceResponse.setToken(jSONObject2.getString("Token"));
                serviceResponse.setResponseCode(jSONObject2.getString("ResponseCode"));
                serviceResponse.setResponseDesc(jSONObject2.getString("ResponseDesc"));
                if (jSONObject2.has("Url3D")) {
                    serviceResponse.setUrl3D(jSONObject2.getString("Url3D"));
                }
                if (jSONObject2.has("Url3DSuccess")) {
                    serviceResponse.setUrl3DSuccess(jSONObject2.getString("Url3DSuccess"));
                }
                if (jSONObject2.has("Url3DError")) {
                    serviceResponse.setUrl3DError(jSONObject2.getString("Url3DError"));
                }
            }
            if (serviceResponse instanceof ServiceResponse) {
                ServiceResponse unused = b.sm = serviceResponse;
                ServiceResult serviceResult = new ServiceResult();
                serviceResult.setRefNo(b.sm.getRefNo());
                serviceResult.setResponseCode(b.sm.getResponseCode());
                serviceResult.setResponseDesc(b.sm.getResponseDesc());
                this.sF.onVerifyUser(serviceResult);
                return;
            }
            this.sF.onServiceError((ServiceError) serviceResponse);
        } catch (Exception e2) {
            InternalError internalError = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError.setErrorDesc(str);
                    this.sF.onInternalError(internalError);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError.setErrorDesc(str);
            this.sF.onInternalError(internalError);
            e2.printStackTrace();
        }
    }
}
