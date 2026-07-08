package cardtek.masterpass.management;

import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.interfaces.ValidateTransactionListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.ValidateTransactionResult;
import cardtek.masterpass.util.a;
import k.s;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class w implements Runnable {
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;
    final /* synthetic */ MasterPassEditText tk;
    final /* synthetic */ ValidateTransactionListener tl;

    public w(b bVar, MasterPassEditText masterPassEditText, ValidateTransactionListener validateTransactionListener, String str) {
        this.sz = bVar;
        this.tk = masterPassEditText;
        this.tl = validateTransactionListener;
        this.sy = str;
    }

    /*  DEBUG: Multi-variable search result rejected for r0v13, resolved type: cardtek.masterpass.management.ServiceResponse */
    /*  DEBUG: Multi-variable search result rejected for r0v14, resolved type: cardtek.masterpass.results.ValidateTransactionResult */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        ServiceError serviceError;
        try {
            MasterPassEditText masterPassEditText = this.tk;
            if (masterPassEditText == null || masterPassEditText.isEmpty()) {
                InternalError internalError = new InternalError();
                a aVar2 = a.E007;
                internalError.setErrorCode(aVar2.name);
                internalError.setErrorDesc(aVar2.value);
                this.tl.onInternalError(internalError);
            } else if (this.tk.validate()) {
                JSONObject jSONObject = new JSONObject(this.sz.sh.a(new s(this.sz.si.getEncData(this.tk), b.sm.getToken(), this.sy), ab.tC));
                JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
                JSONObject jSONObject3 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
                if (jSONObject2.has("RefNo") && !jSONObject2.getString("RefNo").equals("")) {
                    ValidateTransactionResult validateTransactionResult = new ValidateTransactionResult();
                    validateTransactionResult.setRefNo(jSONObject2.getString("RefNo"));
                    validateTransactionResult.setToken(jSONObject2.getString("Token"));
                    serviceError = validateTransactionResult;
                    if (jSONObject2.has("CardUniqueId")) {
                        validateTransactionResult.setCardUniqueId(jSONObject2.getString("CardUniqueId"));
                        serviceError = validateTransactionResult;
                    }
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
                    if (jSONObject3.has("CardUniqueId")) {
                        serviceError2.setCardUniqueId(jSONObject3.getString("CardUniqueId"));
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
                    if (jSONObject3.has("Url3DError")) {
                        serviceResponse.setUrl3DError(jSONObject3.getString("Url3DError"));
                    }
                    serviceError = serviceResponse;
                    if (jSONObject3.has("CardUniqueId")) {
                        serviceResponse.setCardUniqueId(jSONObject3.getString("CardUniqueId"));
                        serviceError = serviceResponse;
                    }
                }
                if (serviceError instanceof ServiceResponse) {
                    ServiceResponse unused = b.sm = (ServiceResponse) serviceError;
                    ServiceResult serviceResult = new ServiceResult();
                    serviceResult.setRefNo(b.sm.getRefNo());
                    serviceResult.setResponseCode(b.sm.getResponseCode());
                    serviceResult.setResponseDesc(b.sm.getResponseDesc());
                    serviceResult.setCardUniqueId(b.sm.getCardUniqueId());
                    this.tl.onVerifyUser(serviceResult);
                } else if (serviceError instanceof ValidateTransactionResult) {
                    this.tl.onSuccess((ValidateTransactionResult) serviceError);
                } else {
                    this.tl.onServiceError(serviceError);
                }
            } else {
                InternalError internalError2 = new InternalError();
                a aVar3 = a.E008;
                internalError2.setErrorCode(aVar3.name);
                internalError2.setErrorDesc(aVar3.value);
                this.tl.onInternalError(internalError2);
            }
        } catch (Exception e2) {
            InternalError internalError3 = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError3.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError3.setErrorDesc(str);
                    this.tl.onInternalError(internalError3);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError3.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError3.setErrorDesc(str);
            this.tl.onInternalError(internalError3);
            e2.printStackTrace();
        }
    }
}
