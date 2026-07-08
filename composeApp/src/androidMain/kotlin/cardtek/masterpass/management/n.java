package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.CheckMasterPassListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.CheckMasterPassResult;
import cardtek.masterpass.util.a;
import k.c;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class n implements Runnable {
    final /* synthetic */ CheckMasterPassListener sW;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;

    public n(b bVar, String str, String str2, CheckMasterPassListener checkMasterPassListener) {
        this.sz = bVar;
        this.sx = str;
        this.sy = str2;
        this.sW = checkMasterPassListener;
    }

    /*  DEBUG: Multi-variable search result rejected for r3v10, resolved type: cardtek.masterpass.response.ServiceError */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        CheckMasterPassResult checkMasterPassResult;
        try {
            JSONObject jSONObject = new JSONObject(this.sz.sh.a(new c(this.sx, this.sz.sl, this.sy), ab.tt));
            JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
            JSONObject jSONObject3 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
            if (!jSONObject2.has("RefNo") || jSONObject2.getString("RefNo").equals("")) {
                ServiceError serviceError = new ServiceError();
                serviceError.setRefNo(jSONObject3.getString("RefNo"));
                serviceError.setResponseCode(jSONObject3.getString("ResponseCode"));
                serviceError.setResponseDesc(jSONObject3.getString("ResponseDesc"));
                if (jSONObject3.has("InternalResponseCode")) {
                    serviceError.setInternalRespCode(jSONObject3.getString("InternalResponseCode"));
                }
                if (jSONObject3.has("InternalResponseMessage")) {
                    serviceError.setInternalRespDesc(jSONObject3.getString("InternalResponseMessage"));
                }
                checkMasterPassResult = serviceError;
            } else {
                checkMasterPassResult = new CheckMasterPassResult();
                checkMasterPassResult.setRefNo(jSONObject2.getString("RefNo"));
                checkMasterPassResult.setAccountStatus(jSONObject2.getString("AccountStatus"));
            }
            if (checkMasterPassResult instanceof CheckMasterPassResult) {
                this.sW.onSuccess(checkMasterPassResult);
            } else {
                this.sW.onServiceError((ServiceError) checkMasterPassResult);
            }
        } catch (Exception e2) {
            InternalError internalError = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError.setErrorDesc(str);
                    this.sW.onInternalError(internalError);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError.setErrorDesc(str);
            this.sW.onInternalError(internalError);
            e2.printStackTrace();
        }
    }
}
