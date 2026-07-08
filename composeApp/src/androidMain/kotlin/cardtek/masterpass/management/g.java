package cardtek.masterpass.management;

import android.widget.CompoundButton;
import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.data.Card;
import cardtek.masterpass.interfaces.DirectPurchaseListener;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.response.ServiceResult;
import cardtek.masterpass.results.DirectPurchaseResult;
import cardtek.masterpass.util.MasterPassInfo;
import cardtek.masterpass.util.a;
import k.f;
import org.json.JSONObject;


/* loaded from: classes.dex */
public final class g implements Runnable {
    final /* synthetic */ DirectPurchaseListener sG;
    final /* synthetic */ int sH;
    final /* synthetic */ String sI;
    final /* synthetic */ Integer sJ;
    final /* synthetic */ String sK;
    final /* synthetic */ String sL;
    final /* synthetic */ CompoundButton sp;
    final /* synthetic */ MasterPassEditText sr;
    final /* synthetic */ MasterPassEditText ss;
    final /* synthetic */ int st;
    final /* synthetic */ int su;
    final /* synthetic */ String sv;
    final /* synthetic */ String sx;
    final /* synthetic */ String sy;
    final /* synthetic */ b sz;

    
    public g(b bVar, CompoundButton compoundButton, DirectPurchaseListener directPurchaseListener, MasterPassEditText masterPassEditText, MasterPassEditText masterPassEditText2, int i2, int i3, String str, String str2, int i4, String str3, String str4, Integer num, String str5, String str6) {
        this.sz = bVar;
        this.sp = compoundButton;
        this.sG = directPurchaseListener;
        this.sr = masterPassEditText;
        this.ss = masterPassEditText2;
        this.st = i2;
        this.su = i3;
        this.sv = str;
        this.sx = str2;
        this.sH = i4;
        this.sI = str3;
        this.sy = str4;
        this.sJ = num;
        this.sK = str5;
        this.sL = str6;
    }

    public void run() {
        a aVar;
        String str;
        String str2;
        ServiceError serviceError;
        ServiceError serviceError2;
        try {
            if (this.sp == null) {
                InternalError internalError = new InternalError();
                a aVar2 = a.E013;
                internalError.setErrorCode(aVar2.name);
                internalError.setErrorDesc(aVar2.value);
                this.sG.onInternalError(internalError);
                return;
            }
            MasterPassEditText masterPassEditText = this.sr;
            if (masterPassEditText != null && !masterPassEditText.isEmpty()) {
                if (!this.sr.validate()) {
                    InternalError internalError2 = new InternalError();
                    a aVar3 = a.E003;
                    internalError2.setErrorCode(aVar3.name);
                    internalError2.setErrorDesc(aVar3.value);
                    this.sG.onInternalError(internalError2);
                    return;
                }
                MasterPassEditText masterPassEditText2 = this.ss;
                if (masterPassEditText2 == null || masterPassEditText2.isEmpty()) {
                    if (MasterPassInfo.isCvvRequire()) {
                        InternalError internalError3 = new InternalError();
                        a aVar4 = a.E004;
                        internalError3.setErrorCode(aVar4.name);
                        internalError3.setErrorDesc(aVar4.value);
                        this.sG.onInternalError(internalError3);
                        return;
                    }
                    str2 = "";
                } else if (this.ss.validate()) {
                    str2 = this.sz.si.getEncData(this.ss);
                } else {
                    InternalError internalError4 = new InternalError();
                    a aVar5 = a.E005;
                    internalError4.setErrorCode(aVar5.name);
                    internalError4.setErrorDesc(aVar5.value);
                    this.sG.onInternalError(internalError4);
                    return;
                }
                a unused = this.sz.sh;
                if (!a.b(this.st, this.su)) {
                    InternalError internalError5 = new InternalError();
                    a aVar6 = a.E012;
                    internalError5.setErrorCode(aVar6.name);
                    internalError5.setErrorDesc(aVar6.value);
                    this.sG.onInternalError(internalError5);
                    return;
                }
                a unused2 = this.sz.sh;
                String c2 = a.c(this.st, this.su);
                if (!this.sp.isChecked()) {
                    if (MasterPassInfo.getSystemID() != null && !MasterPassInfo.getSystemID().isEmpty() && MasterPassInfo.getSystemKey() != null && !MasterPassInfo.getSystemKey().isEmpty()) {
                        a unused3 = this.sz.sh;
                        if (a.h("446972656374205075726368617365")) {
                            DirectPurchaseResult directPurchaseResult = new DirectPurchaseResult();
                            directPurchaseResult.setResult(false);
                            Card card = new Card();
                            card.setCardNumber(this.sz.sj.getText(this.sr));
                            card.setExpiryMonth(this.st);
                            card.setExpiryYear(this.su);
                            if (this.ss != null) {
                                card.setCvv(this.sz.sj.getText(this.ss));
                            }
                            card.setCardholderName(this.sv);
                            directPurchaseResult.setCard(card);
                            this.sG.onSuccess(directPurchaseResult);
                            return;
                        }
                    }
                    boolean unused4 = b.sn = false;
                } else {
                    boolean unused5 = b.sn = true;
                }
                JSONObject jSONObject = new JSONObject(this.sz.sh.a(new f(this.sx, this.sz.si.getEncData(this.sr), c2, String.valueOf(this.sH), this.sI, this.sy, str2, this.sv, this.sJ, this.sK, this.sL, MasterPassInfo.getMacroMerchantId(), MasterPassInfo.getAdditionalParameters()), ab.tA));
                JSONObject jSONObject2 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Response").getJSONObject("Result").getJSONObject("TransactionBody");
                JSONObject jSONObject3 = jSONObject.getJSONObject("Data").getJSONObject("Body").getJSONObject("Fault").getJSONObject("Detail").getJSONObject("ServiceFaultDetail");
                if (!jSONObject2.has("RefNo") || jSONObject2.getString("RefNo").equals("null") || jSONObject2.getString("RefNo").equals("")) {
                    if (!jSONObject3.has("Token") || jSONObject3.getString("Token").equals("null") || jSONObject3.getString("Token").equals("")) {
                        ServiceError serviceError3 = new ServiceError();
                        serviceError3.setRefNo(jSONObject3.getString("RefNo"));
                        serviceError3.setResponseCode(jSONObject3.getString("ResponseCode"));
                        serviceError3.setResponseDesc(jSONObject3.getString("ResponseDesc"));
                        if (jSONObject3.has("InternalResponseCode")) {
                            serviceError3.setInternalRespCode(jSONObject3.getString("InternalResponseCode"));
                        }
                        if (jSONObject3.has("InternalResponseMessage")) {
                            serviceError3.setInternalRespDesc(jSONObject3.getString("InternalResponseMessage"));
                        }
                        serviceError2 = serviceError3;
                        if (jSONObject3.has("UniqueId")) {
                            serviceError3.setCardUniqueId(jSONObject3.getString("UniqueId"));
                            serviceError2 = serviceError3;
                        }
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
                        serviceError2 = serviceResponse;
                        if (jSONObject3.has("UniqueId")) {
                            serviceResponse.setCardUniqueId(jSONObject3.getString("UniqueId"));
                            serviceError2 = serviceResponse;
                        }
                    }
                    serviceError = serviceError2;
                } else {
                    DirectPurchaseResult directPurchaseResult2 = new DirectPurchaseResult();
                    directPurchaseResult2.setResult(true);
                    directPurchaseResult2.setRefNo(jSONObject2.getString("RefNo"));
                    directPurchaseResult2.setToken(jSONObject2.getString("Token"));
                    serviceError = directPurchaseResult2;
                    if (jSONObject2.has("UniqueId")) {
                        directPurchaseResult2.setCardUniqueId(jSONObject2.getString("UniqueId"));
                        serviceError = directPurchaseResult2;
                    }
                }
                if (serviceError instanceof ServiceResponse) {
                    ServiceResponse unused6 = b.sm = (ServiceResponse) serviceError;
                    ServiceResult serviceResult = new ServiceResult();
                    serviceResult.setRefNo(b.sm.getRefNo());
                    serviceResult.setResponseCode(b.sm.getResponseCode());
                    serviceResult.setResponseDesc(b.sm.getResponseDesc());
                    serviceResult.setCardUniqueId(b.sm.getCardUniqueId());
                    this.sG.onVerifyUser(serviceResult);
                    return;
                } else if (serviceError instanceof DirectPurchaseResult) {
                    ServiceResponse serviceResponse2 = new ServiceResponse();
                    serviceResponse2.setToken(((DirectPurchaseResult) serviceError).getToken());
                    ServiceResponse unused7 = b.sm = serviceResponse2;
                    this.sG.onSuccess((DirectPurchaseResult) serviceError);
                    return;
                } else if (serviceError instanceof ServiceError) {
                    this.sG.onServiceError(serviceError);
                    return;
                } else if (serviceError instanceof InternalError) {
                    this.sG.onInternalError((InternalError) serviceError);
                    return;
                } else {
                    return;
                }
            }
            InternalError internalError6 = new InternalError();
            a aVar7 = a.E002;
            internalError6.setErrorCode(aVar7.name);
            internalError6.setErrorDesc(aVar7.value);
            this.sG.onInternalError(internalError6);
        } catch (Exception e2) {
            InternalError internalError7 = new InternalError();
            if (e2 instanceof i.a) {
                aVar = a.E001;
                internalError7.setErrorCode(aVar.name);
                if (!e2.getMessage().isEmpty()) {
                    str = e2.getMessage();
                    internalError7.setErrorDesc(str);
                    this.sG.onInternalError(internalError7);
                    e2.printStackTrace();
                }
            } else {
                aVar = a.E000;
                internalError7.setErrorCode(aVar.name);
            }
            str = aVar.value;
            internalError7.setErrorDesc(str);
            this.sG.onInternalError(internalError7);
            e2.printStackTrace();
        }
    }
}
