package cardtek.masterpass.management;

import cardtek.masterpass.interfaces.ParseQrCodeListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.ParseQrCodeResult;
import cardtek.masterpass.util.a;
import org.json.JSONObject;

public final class k implements Runnable {
    final ParseQrCodeListener sQ;
    final String sx;
    final String sy;
    final b sz;

    public k(b bVar, String str, String str2, ParseQrCodeListener parseQrCodeListener) {
        this.sz = bVar;
        this.sx = str;
        this.sy = str2;
        this.sQ = parseQrCodeListener;
    }

    @Override
    public void run() {
        a aVar;
        String str;
        ParseQrCodeResult parseQrCodeResult;
        try {
            JSONObject jSONObject = new JSONObject(this.sz.sh.a(new k(this.sx, this.sz.sl, this.sy), ab.tH));
            JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
            JSONObject jSONObject3 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
            if (!jSONObject2.has("RefNo") || jSONObject2.getString("RefNo").equals("")) {
                ParseQrCodeResult serviceError = new ParseQrCodeResult();
                serviceError.setRefNo(jSONObject3.getString("RefNo"));
                ((ServiceError) serviceError).setResponseCode(jSONObject3.getString("ResponseCode"));
                serviceError.setResponseDesc(jSONObject3.getString("ResponseDesc"));
                if (jSONObject3.has("InternalResponseCode")) {
                    serviceError.setInternalRespCode(jSONObject3.getString("InternalResponseCode"));
                }
                if (jSONObject3.has("InternalResponseMessage")) {
                    serviceError.setInternalRespDesc(jSONObject3.getString("InternalResponseMessage"));
                }
                parseQrCodeResult = serviceError;
            } else {
                parseQrCodeResult = new ParseQrCodeResult();
                parseQrCodeResult.setRefNo(jSONObject2.getString("RefNo"));
                parseQrCodeResult.setAccountStatus(jSONObject2.getString("AccountStatus"));
                parseQrCodeResult.setAmount(jSONObject2.getString("Amount"));
                parseQrCodeResult.setOrderNo(jSONObject2.getString("OrderNo"));
                parseQrCodeResult.setInstallmentCount(jSONObject2.getString("InstallmentCount"));
            }
            if (parseQrCodeResult instanceof ParseQrCodeResult) {
                this.sQ.onSuccess(parseQrCodeResult);
            } else {
                this.sQ.onServiceError((ServiceError) parseQrCodeResult);
            }
        } catch (Exception e2) {
            InternalError internalError = new InternalError();
            aVar = a.E000;
            internalError.setErrorCode(aVar.name);
            str = aVar.value;
            internalError.setErrorDesc(str);
            this.sQ.onInternalError(internalError);
            e2.printStackTrace();
        }
    }
}
