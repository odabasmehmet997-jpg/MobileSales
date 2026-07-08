package cardtek.masterpass.management;

import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.interfaces.CardUniqueIDListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.CardUniqueIDResult;
import cardtek.masterpass.util.a;

import org.json.JSONObject;

public final class s implements Runnable {
    MasterPassEditText sr = null;
    String sx = "";
    String sy = "";
    b sz = null;
    CardUniqueIDListener tg = null;
    public s(b bVar, MasterPassEditText masterPassEditText, CardUniqueIDListener cardUniqueIDListener, String cardNumber, String cardHolderName) {
        this.sz = bVar;
        this.sr = masterPassEditText;
        this.tg = cardUniqueIDListener;
        this.sx = cardNumber;
        this.sy = cardHolderName;
    }
    public void run() {
        a aVar;
        String str;
        CardUniqueIDResult cardUniqueIDResult;
        try {
            MasterPassEditText masterPassEditText = this.sr;
            if (masterPassEditText != null && !masterPassEditText.isEmpty()) {
                if (!this.sr.validate()) {
                    InternalError internalError = new InternalError();
                    a aVar2 = a.E003;
                    internalError.setErrorCode(aVar2.name);
                    internalError.setErrorDesc(aVar2.value);
                    this.tg.onInternalError(internalError);
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
                JSONObject jSONObject3 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
                if (!jSONObject2.has("RefNo") || jSONObject2.getString("RefNo").isEmpty()) {
                    CardUniqueIDResult serviceError = new CardUniqueIDResult();
                    serviceError.setRefNo(jSONObject3.getString("RefNo"));
                    serviceError.setResponseCode(jSONObject3.getString("ResponseCode"));
                    serviceError.setResponseDesc(jSONObject3.getString("ResponseDesc"));
                    if (jSONObject3.has("InternalResponseCode")) {
                        serviceError.setInternalRespCode(jSONObject3.getString("InternalResponseCode"));
                    }
                    if (jSONObject3.has("InternalResponseMessage")) {
                        serviceError.setInternalRespDesc(jSONObject3.getString("InternalResponseMessage"));
                    }
                    cardUniqueIDResult = serviceError;
                } else {
                    cardUniqueIDResult = new CardUniqueIDResult();
                    cardUniqueIDResult.setRefNo(jSONObject2.getString("RefNo"));
                    cardUniqueIDResult.setCardUniqueId(jSONObject2.getString("CardUniqueId"));
                }
                this.tg.onSuccess(cardUniqueIDResult);
                return;
            }
            InternalError internalError2 = new InternalError();
            a aVar3 = a.E002;
            internalError2.setErrorCode(aVar3.name);
            internalError2.setErrorDesc(aVar3.value);
            this.tg.onInternalError(internalError2);
        } catch (Exception e2) {
            InternalError internalError3 = new InternalError();
            aVar = a.E000;
            if (!e2.getMessage().isEmpty()) {
                str = e2.getMessage();
                internalError3.setErrorDesc(str);
                this.tg.onInternalError(internalError3);
                e2.printStackTrace();
            } else {
                aVar = a.E000;
                internalError3.setErrorCode(aVar.name);
                str = aVar.value;
                internalError3.setErrorDesc(str);
                this.tg.onInternalError(internalError3);
                e2.printStackTrace();
            }
        }
    }
}
