package cardtek.masterpass.results;

import java.io.Serializable;

/* loaded from: classes.dex */
public class ValidateTransactionResult implements Serializable {
    private String cardUniqueId;
    private String refNo;
    private String token;

    public String getCardUniqueId() {
        return this.cardUniqueId;
    }

    public String getRefNo() {
        return this.refNo;
    }

    public String getToken() {
        return this.token;
    }

    public void setCardUniqueId(String str) {
        this.cardUniqueId = str;
    }

    public void setRefNo(String str) {
        this.refNo = str;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
