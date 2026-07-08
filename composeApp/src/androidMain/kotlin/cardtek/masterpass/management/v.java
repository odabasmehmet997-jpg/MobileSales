package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.DeleteCardListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.DeleteCardResult;
import cardtek.masterpass.util.a;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import k.e;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class v implements Runnable {
    final /* synthetic */ String sw;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;
    final /* synthetic */ DeleteCardListener tj;

    public v(b bVar, String str, DeleteCardListener deleteCardListener, String str2, String str3) {
        this.sz = bVar;
        this.sw = str;
        this.tj = deleteCardListener;
        this.sx = str2;
        this.sy = str3;
    }

    /*  DEBUG: Multi-variable search result rejected for r3v10, resolved type: cardtek.masterpass.response.ServiceError */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        DeleteCardResult deleteCardResult;
        try {
            String str2 = this.sw;
            if (str2 != null && !str2.isEmpty()) {
                JSONObject jSONObject = new JSONObject(this.sz.sh.a(new e(this.sx, this.sz.sl, URLEncoder.encode(this.sw, StandardCharsets.UTF_8), this.sy), ab.tv));
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
                    deleteCardResult = serviceError;
                } else {
                    deleteCardResult = new DeleteCardResult();
                    deleteCardResult.setRefNo(jSONObject2.getString("RefNo"));
                }
                if (deleteCardResult instanceof DeleteCardResult) {
                    this.tj.onSuccess(deleteCardResult);
                    return;
                } else {
                    this.tj.onServiceError((ServiceError) deleteCardResult);
                    return;
                }
            }
            InternalError internalError = new InternalError();
            a aVar2 = a.E006;
            internalError.setErrorCode(aVar2.name);
            internalError.setErrorDesc(aVar2.value);
            this.tj.onInternalError(internalError);
        } catch (Exception e2) {
            InternalError internalError2 = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError2.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError2.setErrorDesc(str);
                    this.tj.onInternalError(internalError2);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError2.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError2.setErrorDesc(str);
            this.tj.onInternalError(internalError2);
            e2.printStackTrace();
        }
    }
}
