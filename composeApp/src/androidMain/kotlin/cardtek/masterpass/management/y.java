package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.LinkCardToClientListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.util.a;
import k.i;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class y implements Runnable {
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;
    final /* synthetic */ LinkCardToClientListener tn;

    public y(b bVar, String str, String str2, LinkCardToClientListener linkCardToClientListener) {
        this.sz = bVar;
        this.sx = str;
        this.sy = str2;
        this.tn = linkCardToClientListener;
    }

    /*  DEBUG: Multi-variable search result rejected for r2v8, resolved type: cardtek.masterpass.response.ServiceError */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        ServiceResponse serviceResponse;
        try {
            JSONObject jSONObject = new JSONObject(this.sz.sh.a(new i(this.sx, this.sz.sl, this.sy), ab.ty));
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
                if (jSONObject2.has("CardIssuerName")) {
                    serviceError.setCardIssuerName(jSONObject2.getString("CardIssuerName"));
                }
                if (jSONObject2.has("MaskedPan")) {
                    serviceError.setMaskedPan(jSONObject2.getString("MaskedPan"));
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
                if (jSONObject2.has("CardIssuerName")) {
                    serviceResponse.setCardIssuerName(jSONObject2.getString("CardIssuerName"));
                }
                if (jSONObject2.has("MaskedPan")) {
                    serviceResponse.setMaskedPan(jSONObject2.getString("MaskedPan"));
                }
            }
            if (serviceResponse instanceof ServiceResponse) {
                ServiceResponse unused = b.sm = serviceResponse;
                ServiceResult serviceResult = new ServiceResult();
                serviceResult.setRefNo(b.sm.getRefNo());
                serviceResult.setResponseCode(b.sm.getResponseCode());
                serviceResult.setResponseDesc(b.sm.getResponseDesc());
                serviceResult.setCardIssuerName(b.sm.getCardIssuerName());
                serviceResult.setMaskedPan(b.sm.getMaskedPan());
                this.tn.onVerifyUser(serviceResult);
                return;
            }
            this.tn.onServiceError((ServiceError) serviceResponse);
        } catch (Exception e2) {
            InternalError internalError = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError.setErrorDesc(str);
                    this.tn.onInternalError(internalError);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError.setErrorDesc(str);
            this.tn.onInternalError(internalError);
            e2.printStackTrace();
        }
    }
}
