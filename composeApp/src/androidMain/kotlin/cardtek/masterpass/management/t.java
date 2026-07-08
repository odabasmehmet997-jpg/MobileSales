package cardtek.masterpass.management;

import cardtek.masterpass.data.MasterPassCard;
import cardtek.masterpass.interfaces.GetCardsListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.GetCardsResult;
import cardtek.masterpass.util.a;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import k.h;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class t implements Runnable {
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;
    final /* synthetic */ GetCardsListener th;

    public t(b bVar, String str, String str2, GetCardsListener getCardsListener) {
        this.sz = bVar;
        this.sx = str;
        this.sy = str2;
        this.th = getCardsListener;
    }

    /*  DEBUG: Multi-variable search result rejected for r3v10, resolved type: cardtek.masterpass.response.ServiceError */
    /*  WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public void run() {
        a aVar;
        String str;
        GetCardsResult getCardsResult;
        try {
            JSONObject jSONObject = new JSONObject(this.sz.sh.a(new h(this.sx, this.sz.sl, this.sy, "ACCOUNT"), ab.tw));
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
                getCardsResult = serviceError;
            } else {
                getCardsResult = new GetCardsResult();
                getCardsResult.setRefNo(jSONObject2.getString("RefNo"));
                JSONArray jSONArray = jSONObject2.getJSONObject("ListItems").getJSONArray("ListItem");
                Gson create = new GsonBuilder().create();
                ArrayList<MasterPassCard> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add((MasterPassCard) create.fromJson(jSONArray.getJSONObject(i2).toString(), (Class<Object>) MasterPassCard.class));
                }
                getCardsResult.setCards(arrayList);
            }
            if (getCardsResult instanceof GetCardsResult) {
                this.th.onSuccess(getCardsResult);
            } else if (getCardsResult instanceof ServiceError) {
                this.th.onServiceError((ServiceError) getCardsResult);
            } else if (getCardsResult instanceof InternalError) {
                this.th.onInternalError((InternalError) getCardsResult);
            }
        } catch (Exception e2) {
            InternalError internalError = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError.setErrorDesc(str);
                    this.th.onInternalError(internalError);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError.setErrorDesc(str);
            this.th.onInternalError(internalError);
            e2.printStackTrace();
        }
    }
}
