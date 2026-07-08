package cardtek.masterpass.management;

import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.interfaces.ForgotPasswordListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.util.MasterPassInfo;
import cardtek.masterpass.util.a;
import k.g;
import org.json.JSONObject;


/* loaded from: classes.dex */
public final class z implements Runnable {
    final /* synthetic */ MasterPassEditText ss;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;
    final /* synthetic */ ForgotPasswordListener to;
    final /* synthetic */ String tp;


    public z(b bVar, MasterPassEditText masterPassEditText, ForgotPasswordListener forgotPasswordListener, String str, String str2, String str3) {
        this.sz = bVar;
        this.ss = masterPassEditText;
        this.to = forgotPasswordListener;
        this.sx = str;
        this.tp = str2;
        this.sy = str3;
    }

    /*  DEBUG: Multi-variable search result rejected for r3v8, resolved type: cardtek.masterpass.response.ServiceError */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        String str2;
        ServiceResponse serviceResponse;
        try {
            MasterPassEditText masterPassEditText = this.ss;
            if (masterPassEditText == null || masterPassEditText.isEmpty()) {
                if (MasterPassInfo.isCvvRequire()) {
                    InternalError internalError = new InternalError();
                    a aVar2 = a.E004;
                    internalError.setErrorCode(aVar2.name);
                    internalError.setErrorDesc(aVar2.value);
                    this.to.onInternalError(internalError);
                    return;
                }
                str2 = "";
            } else if (this.ss.validate()) {
                str2 = this.sz.si.getEncData(this.ss);
            } else {
                InternalError internalError2 = new InternalError();
                a aVar3 = a.E005;
                internalError2.setErrorCode(aVar3.name);
                internalError2.setErrorDesc(aVar3.value);
                this.to.onInternalError(internalError2);
                return;
            }
            JSONObject jSONObject = new JSONObject(this.sz.sh.a(new g(this.sx, this.sz.sl, this.tp, str2, this.sy), ab.tD));
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
                this.to.onVerifyUser(serviceResult);
            } else if (serviceResponse instanceof ServiceError) {
                this.to.onServiceError((ServiceError) serviceResponse);
            } else if (serviceResponse instanceof InternalError) {
                this.to.onInternalError((InternalError) serviceResponse);
            }
        } catch (Exception e2) {
            InternalError internalError3 = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError3.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError3.setErrorDesc(str);
                    this.to.onInternalError(internalError3);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError3.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError3.setErrorDesc(str);
            this.to.onInternalError(internalError3);
            e2.printStackTrace();
        }
    }
}
